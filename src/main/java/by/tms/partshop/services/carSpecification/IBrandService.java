package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.BrandDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IBrandService {

  ModelAndView saveNewBrand(BrandDto newBrand, HttpSession session);

  ModelAndView deleteBrand(String deleteBrand, HttpSession session);
}
