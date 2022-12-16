package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.dto.ImagesDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface ICarService {

  ModelAndView saveNewCar(CarDto newCar, ImagesDto newCarImages, HttpSession session);

  ModelAndView deleteCar(String deleteCar, HttpSession session);
}
