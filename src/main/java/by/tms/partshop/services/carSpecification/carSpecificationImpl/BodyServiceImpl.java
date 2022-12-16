package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.BODY_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.BODY_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.BodyDto;
import by.tms.partshop.dto.converter.BodyConverter;
import by.tms.partshop.entities.carSpecification.Body;
import by.tms.partshop.repositories.BodyRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.IBodyService;
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
public class BodyServiceImpl implements IBodyService {

  private final BodyRepository bodyRepository;
  private final BodyConverter bodyConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewBody(BodyDto newBody, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Body body = bodyConverter.fromDto(newBody);
      bodyRepository.save(body);
      iAdminService.loadBodiesInSession(session);
      modelMap.addAttribute(BODY_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(BODY_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteBody(String deleteBody, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Body body = bodyRepository.getBodyByBody(deleteBody);
      bodyRepository.delete(body);
      iAdminService.loadBodiesInSession(session);
      modelMap.addAttribute(BODY_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(BODY_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
