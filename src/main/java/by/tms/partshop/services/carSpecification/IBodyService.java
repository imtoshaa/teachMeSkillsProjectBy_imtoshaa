package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.BodyDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IBodyService {

  ModelAndView saveNewBody(BodyDto newBody, HttpSession session);

  ModelAndView deleteBody(String deleteBody, HttpSession session);
}
