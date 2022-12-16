package by.tms.partshop.services.partSpecification;

import by.tms.partshop.dto.TypeSideDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface ITypeSideService {

  ModelAndView saveNewTypeSide(TypeSideDto newSide, HttpSession session);

  ModelAndView deleteTypeSide(String deleteSide, HttpSession session);
}
