package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.REGISTRATION_PAGE;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.services.basicServices.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/registration")
public class RegistrationController {

  private final IUserService userService;

  @GetMapping
  public ModelAndView openRegistrationPage() {
    return new ModelAndView(REGISTRATION_PAGE);
  }

  @PostMapping
  public ModelAndView addNewUser(@ModelAttribute @Validated NewUserDto user, Errors errors) {
    return userService.registration(user);
  }

  @ModelAttribute()
  public NewUserDto setUpUserForm() {
    return new NewUserDto();
  }
}
