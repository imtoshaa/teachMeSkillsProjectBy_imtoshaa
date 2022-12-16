package by.tms.partshop.services.partSpecification.partSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.SIDE_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.SIDE_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.TypeSideDto;
import by.tms.partshop.dto.converter.SideConverter;
import by.tms.partshop.entities.partSpecification.TypeSide;
import by.tms.partshop.repositories.SideRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.partSpecification.ITypeSideService;
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
public class TypeSideServiceImpl implements ITypeSideService {

  private final SideRepository sideRepository;
  private final SideConverter sideConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewTypeSide(TypeSideDto newSide, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      TypeSide side = sideConverter.fromDto(newSide);
      sideRepository.save(side);
      iAdminService.loadTypeSidesInSession(session);
      modelMap.addAttribute(SIDE_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(SIDE_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteTypeSide(String deleteSide, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      TypeSide side = sideRepository.getBySide(deleteSide);
      sideRepository.delete(side);
      iAdminService.loadTypeSidesInSession(session);
      modelMap.addAttribute(SIDE_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(SIDE_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
