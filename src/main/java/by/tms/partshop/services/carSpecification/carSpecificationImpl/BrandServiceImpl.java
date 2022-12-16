package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.BRANDS_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.BRANDS_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.BrandDto;
import by.tms.partshop.dto.converter.BrandConverter;
import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.repositories.BrandRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.IBrandService;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Slf4j
@Service
public class BrandServiceImpl implements IBrandService {

  private final BrandRepository brandRepository;
  private final BrandConverter brandConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewBrand(BrandDto newBrand, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Brand brand = brandConverter.fromDto(newBrand);
      brandRepository.save(brand);
      iAdminService.loadBrandsInSession(session);
      modelMap.addAttribute(BRANDS_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(BRANDS_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteBrand(String deleteBrand, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Brand brand = brandRepository.getBrandByBrand(deleteBrand);
      brandRepository.delete(brand);
      iAdminService.loadBrandsInSession(session);
      modelMap.addAttribute(BRANDS_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(BRANDS_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
