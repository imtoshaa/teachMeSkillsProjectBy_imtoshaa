package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.FUEL_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.FUEL_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.FuelTypeDto;
import by.tms.partshop.dto.converter.FuelTypeConverter;
import by.tms.partshop.entities.carSpecification.FuelType;
import by.tms.partshop.repositories.FuelTypeRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.IFuelTypeService;
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
public class FuelTypeServiceImpl implements IFuelTypeService {

  private final FuelTypeRepository fuelTypeRepository;
  private final FuelTypeConverter fuelTypeConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewFuelType(FuelTypeDto newFuelType, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      FuelType fuelType = fuelTypeConverter.fromDto(newFuelType);
      fuelTypeRepository.save(fuelType);
      iAdminService.loadFuelTypesInSession(session);
      modelMap.addAttribute(FUEL_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(FUEL_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteFuelType(String deleteFuelType, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      FuelType fuelType = fuelTypeRepository.getFuelTypeByTypeFuel(deleteFuelType);
      fuelTypeRepository.delete(fuelType);
      iAdminService.loadFuelTypesInSession(session);
      modelMap.addAttribute(FUEL_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(FUEL_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
