package by.tms.partshop.services.partSpecification;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.PartDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IPartService {

  ModelAndView saveNewPart(PartDto newPart, ImagesDto newPartImages, HttpSession session);

  ModelAndView deletePart(String deletePartCode, HttpSession session);

  ModelAndView getPartData(long partCode);
}
