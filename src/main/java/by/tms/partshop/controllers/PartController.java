package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.ShopConstants.PART_CODE;

import by.tms.partshop.services.partSpecification.IPartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/part")
public class PartController {

  private final IPartService partService;

  @GetMapping
  public ModelAndView openPartPage(@RequestParam(PART_CODE) String code) {
    long partCode = Long.parseLong(code);
    return partService.getPartData(partCode);
  }
}