package by.tms.partshop.services.csv;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ICsvTransmissionService {
  ModelAndView saveTransmissions(MultipartFile file, HttpSession session) throws Exception;
}
