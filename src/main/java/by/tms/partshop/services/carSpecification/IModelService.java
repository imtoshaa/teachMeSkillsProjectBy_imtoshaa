package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.BrandDto;
import by.tms.partshop.dto.ModelDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IModelService {

  ModelAndView saveNewModel(ModelDto newModel, HttpSession session);

  ModelAndView deleteModel(BrandDto getBrand, String deleteModel, HttpSession session);
}
