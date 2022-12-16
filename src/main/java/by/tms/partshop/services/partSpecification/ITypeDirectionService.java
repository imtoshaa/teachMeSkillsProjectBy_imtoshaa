package by.tms.partshop.services.partSpecification;

import by.tms.partshop.dto.TypeDirectionDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface ITypeDirectionService {

  ModelAndView saveNewTypeDirection(TypeDirectionDto newTypeDirection, HttpSession session);

  ModelAndView deleteTypeDirection(String deleteDirection, HttpSession session);
}
