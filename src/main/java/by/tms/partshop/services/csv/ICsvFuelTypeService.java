package by.tms.partshop.services.csv;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ICsvFuelTypeService {
  ModelAndView saveFuelTypes(MultipartFile file, HttpSession session) throws Exception;
}
