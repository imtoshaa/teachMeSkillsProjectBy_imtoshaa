package by.tms.partshop.services.csv;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ICsvCarService {
  ModelAndView saveCars(MultipartFile file, HttpSession session) throws Exception;
}
