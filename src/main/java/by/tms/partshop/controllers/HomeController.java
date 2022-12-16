package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.HOME_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.BRANDS;

import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.repositories.BrandRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomeController {

  private final BrandRepository brandRepository;

  @GetMapping()
  public ModelAndView openHomePage(HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    List<Brand> brands = brandRepository.findAll();
    session.setAttribute(BRANDS, brands);
    return new ModelAndView(HOME_PAGE, modelMap);
  }
}
