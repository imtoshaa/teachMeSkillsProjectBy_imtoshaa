package by.tms.partshop.controllers;

import by.tms.partshop.dto.SearchParamsDto;
import by.tms.partshop.services.basicServices.ISearchService;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/filter")
public class FilterController {

  private final ISearchService searchService;

  @GetMapping
  public ModelAndView getFilters(@ModelAttribute SearchParamsDto searchParamsDto,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "15") int pageSize, HttpSession session) {
    return searchService.openFilterPage(pageNumber, pageSize, session);
  }

  @PostMapping
  public ModelAndView postFilters(@ModelAttribute SearchParamsDto searchParamsDto,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
      HttpSession session, BindingResult bindingResult) {
    return searchService.filterQuery(searchParamsDto, pageNumber, pageSize, session,
        bindingResult);
  }

  @PostMapping("/brand")
  public ModelAndView openFilterWithBrandFromHome(@ModelAttribute SearchParamsDto searchParamsDto,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
      HttpSession session, BindingResult bindingResult) {
    return searchService.filterQuery(searchParamsDto, pageNumber, pageSize, session, bindingResult);
  }

  @ModelAttribute
  public SearchParamsDto setUpSearchForm() {
    return new SearchParamsDto();
  }
}
