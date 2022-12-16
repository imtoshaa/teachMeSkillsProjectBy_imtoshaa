package by.tms.partshop.services.basicServices;

import by.tms.partshop.dto.SearchParamsDto;
import javax.servlet.http.HttpSession;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface ISearchService {

  ModelAndView openFilterPage(int pageNum, int pageSize, HttpSession session);
  ModelAndView searchQuery(SearchParamsDto searchParams, int pageNum, int pageSize);
  ModelAndView filterQuery(SearchParamsDto searchParams, int pageNum, int pageSize,
      HttpSession session, BindingResult bindingResult);
}
