package by.tms.partshop.services.csv;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ICsvModelService {
  ModelAndView saveModels(MultipartFile file, HttpSession session) throws Exception;
}
