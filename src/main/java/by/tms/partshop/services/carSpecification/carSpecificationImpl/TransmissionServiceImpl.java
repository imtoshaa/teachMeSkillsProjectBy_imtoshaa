package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.TRANSMISSION_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.TRANSMISSION_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.TransmissionDto;
import by.tms.partshop.dto.converter.TransmissionConverter;
import by.tms.partshop.entities.carSpecification.Transmission;
import by.tms.partshop.repositories.TransmissionRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.ITransmissionService;
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
public class TransmissionServiceImpl implements ITransmissionService {

  private final TransmissionRepository transmissionRepository;
  private final TransmissionConverter transmissionConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewTransmission(TransmissionDto transmissionDto, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Transmission transmission = transmissionConverter.fromDto(transmissionDto);
      transmissionRepository.save(transmission);
      iAdminService.loadTransmissionsInSession(session);
      modelMap.addAttribute(TRANSMISSION_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(TRANSMISSION_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteTransmission(String deleteTransmission, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Transmission transmission = transmissionRepository.getTransmissionByTransmission(
          deleteTransmission);
      transmissionRepository.delete(transmission);
      iAdminService.loadTransmissionsInSession(session);
      modelMap.addAttribute(TRANSMISSION_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(TRANSMISSION_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}