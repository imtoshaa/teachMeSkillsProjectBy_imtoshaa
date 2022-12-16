package by.tms.partshop.services.partSpecification.partSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.DIRECTION_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.DIRECTION_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.TypeDirectionDto;
import by.tms.partshop.dto.converter.TypeDirectionConverter;
import by.tms.partshop.entities.partSpecification.TypeDirection;
import by.tms.partshop.repositories.TypeDirectionRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.partSpecification.ITypeDirectionService;
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
public class TypeDirectionServiceImpl implements ITypeDirectionService {

  private final TypeDirectionRepository directionRepository;
  private final TypeDirectionConverter directionConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewTypeDirection(TypeDirectionDto newTypeDirection, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      TypeDirection direction = directionConverter.fromDto(newTypeDirection);
      directionRepository.save(direction);
      iAdminService.loadTypeDirectionsInSession(session);
      modelMap.addAttribute(DIRECTION_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(DIRECTION_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteTypeDirection(String deleteDirection, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      TypeDirection direction = directionRepository.getByDirection(deleteDirection);
      directionRepository.delete(direction);
      iAdminService.loadTypeDirectionsInSession(session);
      modelMap.addAttribute(DIRECTION_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(DIRECTION_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
