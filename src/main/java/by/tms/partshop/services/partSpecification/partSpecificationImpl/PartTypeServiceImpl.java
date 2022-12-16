package by.tms.partshop.services.partSpecification.partSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.PART_TYPE_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.PART_TYPE_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.dto.converter.PartTypeConverter;
import by.tms.partshop.entities.partSpecification.Type;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.partSpecification.IPartTypeService;
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
public class PartTypeServiceImpl implements IPartTypeService {

  private final PartTypeRepository partTypeRepository;
  private final PartTypeConverter typeConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewPartType(PartTypeDto newPartType, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Type type = typeConverter.fromDto(newPartType);
      partTypeRepository.save(type);
      iAdminService.loadPartTypesInSession(session);
      modelMap.addAttribute(PART_TYPE_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(PART_TYPE_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deletePartType(String deleteType, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Type type = partTypeRepository.getByType(deleteType);
      partTypeRepository.delete(type);
      iAdminService.loadPartTypesInSession(session);
      modelMap.addAttribute(PART_TYPE_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(PART_TYPE_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
