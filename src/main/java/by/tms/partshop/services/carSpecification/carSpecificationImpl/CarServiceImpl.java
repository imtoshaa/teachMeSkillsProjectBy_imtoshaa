package by.tms.partshop.services.carSpecification.carSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.CAR_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.CAR_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.converter.CarConverter;
import by.tms.partshop.dto.converter.ImagesConverter;
import by.tms.partshop.entities.Images;
import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.ImagesRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.carSpecification.ICarService;
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
public class CarServiceImpl implements ICarService {

  private final CarRepository carRepository;
  private final ImagesRepository imagesRepository;
  private final CarConverter carConverter;
  private final ImagesConverter imagesConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewCar(CarDto newCar, ImagesDto newCarImages, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Car car = carConverter.fromDto(newCar);
      carRepository.save(car);
      Images images = imagesConverter.imagesCarFromDto(newCarImages);
      imagesRepository.save(images);
      iAdminService.loadCarsInSession(session);
      modelMap.addAttribute(CAR_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(CAR_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deleteCar(String deleteCar, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Car car = carRepository.getByCarCode(deleteCar);
      Images images = imagesRepository.getByCar(car);
      imagesRepository.delete(images);
      carRepository.delete(car);
      iAdminService.loadCarsInSession(session);
      modelMap.addAttribute(CAR_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(CAR_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }
}
