package by.tms.partshop.controllers;

import by.tms.partshop.dto.SearchParamsDto;
import by.tms.partshop.services.basicServices.ISearchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {

  private final ISearchService searchService;

  @PostMapping
  public ModelAndView getResultFromSearchRequest(@ModelAttribute SearchParamsDto searchParamsDto,
      @RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "15") int pageSize) {
    return searchService.searchQuery(searchParamsDto, pageNumber, pageSize);
  }

  @ModelAttribute
  public SearchParamsDto setUpSearchForm() {
    return new SearchParamsDto();
  }
}
