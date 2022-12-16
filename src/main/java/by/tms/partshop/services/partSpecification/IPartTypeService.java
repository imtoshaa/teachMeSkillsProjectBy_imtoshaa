package by.tms.partshop.services.partSpecification;

import by.tms.partshop.dto.PartTypeDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IPartTypeService {

  ModelAndView saveNewPartType(PartTypeDto newPartType, HttpSession session);

  ModelAndView deletePartType(String deleteType, HttpSession session);
}
