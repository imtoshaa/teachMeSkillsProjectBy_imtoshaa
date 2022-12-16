package by.tms.partshop.services.basicServices.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.FILTER_PAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.SEARCH_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.BRAND;
import static by.tms.partshop.util.constants.ShopConstants.BRANDS;
import static by.tms.partshop.util.constants.ShopConstants.FUEL_TYPES;
import static by.tms.partshop.util.constants.ShopConstants.MODELS;
import static by.tms.partshop.util.constants.ShopConstants.NAME_BRAND_OPTION;
import static by.tms.partshop.util.constants.ShopConstants.PART_DIRECTIONS;
import static by.tms.partshop.util.constants.ShopConstants.PART_TYPES;
import static by.tms.partshop.util.constants.ShopConstants.SEARCH_RESULT;
import static by.tms.partshop.util.constants.ShopConstants.TYPE_SIDES;

import by.tms.partshop.dto.SearchParamsDto;
import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.repositories.BrandRepository;
import by.tms.partshop.repositories.FuelTypeRepository;
import by.tms.partshop.repositories.ModelRepository;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.repositories.PartSearchSpecification;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.repositories.SideRepository;
import by.tms.partshop.repositories.TypeDirectionRepository;
import by.tms.partshop.services.basicServices.ISearchService;
import by.tms.partshop.util.PageAttributes;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class SearchServiceImpl implements ISearchService {

  private final PartRepository partRepository;
  private final BrandRepository brandRepository;
  private final ModelRepository modelRepository;
  private final PartTypeRepository partTypeRepository;
  private final TypeDirectionRepository directionRepository;
  private final SideRepository sideRepository;
  private final FuelTypeRepository fuelTypeRepository;

  private final PageAttributes pageAttributes;

  @Override
  public ModelAndView openFilterPage(int pageNum, int pageSize, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      List<Brand> brands = brandRepository.findAll();
      session.setAttribute(BRANDS, brands);
      Pageable paging = PageRequest.of(pageNum, pageSize);
      Page<Part> partPage = partRepository.findAllByAvailableToBuyTrue(paging);
      pageAttributes.setPageAttributes(modelMap, partPage, pageNum, pageSize);
      modelMap.addAttribute(NAME_BRAND_OPTION, "Бренд автомобиля");
      modelMap.addAttribute(SEARCH_RESULT, partPage.getContent());
      return new ModelAndView(FILTER_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(FILTER_PAGE);
    }
  }

  @Override
  public ModelAndView searchQuery(SearchParamsDto searchParams, int pageNum, int pageSize) {
    ModelMap modelMap = new ModelMap();
    try {
      Pageable paging = PageRequest.of(pageNum, pageSize);
      PartSearchSpecification searchSpecification = new PartSearchSpecification(searchParams);
      Page<Part> partPage = partRepository.findAll(searchSpecification, paging);
      pageAttributes.setPageAttributes(modelMap, partPage, pageNum, pageSize);
      log.info(partPage.getContent().size() + " " + partPage.getContent());
      modelMap.addAttribute(SEARCH_RESULT, partPage.getContent());
      return new ModelAndView(SEARCH_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(SEARCH_PAGE);
    }
  }

  @Override
  public ModelAndView filterQuery(SearchParamsDto searchParams, int pageNum, int pageSize,
      HttpSession session, BindingResult bindingResult) {
    ModelMap modelMap = new ModelMap();
    try {
      if (Optional.ofNullable(searchParams.getBrand()).isEmpty()) {
        searchParams.setBrand((String) session.getAttribute(BRAND));
        modelMap.addAttribute(NAME_BRAND_OPTION, searchParams.getBrand());
        Page<Part> partPage = getParts(searchParams, pageNum, pageSize, modelMap);
        pageAttributes.setPageAttributes(modelMap, partPage, pageNum, pageSize);
        modelMap.addAttribute(FILTER_PAGE, partPage.getContent());
      } else {
        session.setAttribute(BRAND, searchParams.getBrand());
        modelMap.addAttribute(NAME_BRAND_OPTION, searchParams.getBrand());
        Page<Part> partPage = getParts(searchParams, pageNum, pageSize, modelMap);
        pageAttributes.setPageAttributes(modelMap, partPage, pageNum, pageSize);
        modelMap.addAttribute(MODELS, modelRepository.findAllByBrand(
                brandRepository.getBrandByBrand(
                        searchParams.getBrand())));
        modelMap.addAttribute(PART_TYPES, partTypeRepository.findAll());
        modelMap.addAttribute(PART_DIRECTIONS, directionRepository.findAll());
        modelMap.addAttribute(TYPE_SIDES, sideRepository.findAll());
        modelMap.addAttribute(FUEL_TYPES, fuelTypeRepository.findAll());
        modelMap.addAttribute(SEARCH_RESULT, partPage.getContent());
      }
      return new ModelAndView(FILTER_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(FILTER_PAGE);
    }
  }

  private Page<Part> getParts(SearchParamsDto searchParams, int pageNum, int pageSize,
      ModelMap modelMap) {
    Pageable paging = PageRequest.of(pageNum, pageSize);
    PartSearchSpecification searchSpecification = new PartSearchSpecification(searchParams);
    Page<Part> partPage = partRepository.findAll(searchSpecification, paging);
    return partPage;
  }
}
