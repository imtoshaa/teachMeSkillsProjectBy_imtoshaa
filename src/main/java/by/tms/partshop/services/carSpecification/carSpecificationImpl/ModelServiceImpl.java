package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.MODELS_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.MODEL_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.MODELS;

import by.tms.partshop.dto.BrandDto;
import by.tms.partshop.dto.ModelDto;
import by.tms.partshop.dto.converter.ModelConverter;
import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.carSpecification.Model;
import by.tms.partshop.repositories.BrandRepository;
import by.tms.partshop.repositories.ModelRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.IModelService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class ModelServiceImpl implements IModelService {

  private final BrandRepository brandRepository;

  private final ModelRepository modelRepository;

  private final ModelConverter modelConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewModel(ModelDto newModel, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Model model = modelConverter.fromDto(newModel);
      modelRepository.save(model);
      iAdminService.loadModelsInSession(session);
      modelMap.addAttribute(MODELS_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(MODELS_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteModel(BrandDto getBrand, String deleteModel, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      if (Optional.ofNullable(getBrand.getBrand()).isPresent()) {
        List<Model> models = getModelsByBrand(getBrand.getBrand());
        if (!models.isEmpty()) {
          modelMap.addAttribute(MODELS, models);
          return new ModelAndView(ADMIN_PAGE, modelMap);
        }
        if (Optional.ofNullable(deleteModel).isPresent()) {
          Model model = modelRepository.getModelByModel(deleteModel);
          modelRepository.delete(model);
          iAdminService.loadModelsInSession(session);
          modelMap.addAttribute(MODEL_DELETE_MESSAGE, HttpStatus.ACCEPTED);
          return new ModelAndView(ADMIN_PAGE, modelMap);
        }
      }
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(MODEL_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  private List<Model> getModelsByBrand(String paramBrand) {
    try {
      Brand brand = brandRepository.getBrandByBrand(paramBrand);
      List<Model> models = modelRepository.findAllByBrand(brand);
      return models;
    } catch (Exception ex) {
      log.error(ex.getMessage());
      return new ArrayList<>();
    }
  }
}
