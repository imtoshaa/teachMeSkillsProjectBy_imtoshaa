package by.tms.partshop.services.csv;

import javax.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

public interface ICsvBrandService {

  ModelAndView saveBrands(MultipartFile file, HttpSession session) throws Exception;
}
