package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.FuelTypeDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IFuelTypeService {

  ModelAndView saveNewFuelType(FuelTypeDto newFuelType, HttpSession session);

  ModelAndView deleteFuelType(String deleteFuelType, HttpSession session);
}
