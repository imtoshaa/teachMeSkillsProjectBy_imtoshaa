package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.ERROR_PAGE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice(basePackages = "by.tms.partshop.controllers")
public class GlobalControllerException {

  @ExceptionHandler
  public ModelAndView globalException(Exception e) {
    log.error(e.getMessage());
    return new ModelAndView(ERROR_PAGE);
  }
}