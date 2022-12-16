package by.tms.partshop.controllers;

import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvBodyService;
import by.tms.partshop.services.csv.ICsvBrandService;
import by.tms.partshop.services.csv.ICsvCarService;
import by.tms.partshop.services.csv.ICsvFuelTypeService;
import by.tms.partshop.services.csv.ICsvModelService;
import by.tms.partshop.services.csv.ICsvPartService;
import by.tms.partshop.services.csv.ICsvPartTypeService;
import by.tms.partshop.services.csv.ICsvTransmissionService;
import by.tms.partshop.services.csv.ICsvTypeDirectionService;
import by.tms.partshop.services.csv.ICsvTypeSideService;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/admins")
public class AdminCsvController {

  private final IAdminService iAdminService;

  private final ICsvBrandService csvBrandService;
  private final ICsvModelService csvModelService;
  private final ICsvBodyService csvBodyService;
  private final ICsvFuelTypeService csvFuelTypeService;
  private final ICsvTransmissionService csvTransmissionService;
  private final ICsvCarService csvCarService;
  private final ICsvPartTypeService csvPartTypeService;
  private final ICsvTypeDirectionService csvTypeDirectionsService;
  private final ICsvTypeSideService csvTypeSidesService;
  private final ICsvPartService csvPartService;

  @GetMapping
  public ModelAndView openUploadPage(HttpSession session) {
    return iAdminService.openAdminPage(session);
  }

  @PostMapping("/upload/brands")
  public ModelAndView uploadBrands(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvBrandService.saveBrands(file, session);
  }

  @PostMapping("/upload/models")
  public ModelAndView uploadModels(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvModelService.saveModels(file, session);
  }

  @PostMapping("/upload/transmission")
  public ModelAndView uploadTransmissions(@RequestParam("file") MultipartFile file,
      HttpSession session)
      throws Exception {
    return csvTransmissionService.saveTransmissions(file, session);
  }

  @PostMapping("/upload/bodies")
  public ModelAndView uploadBodies(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvBodyService.saveBodies(file, session);
  }

  @PostMapping("/upload/fuels")
  public ModelAndView uploadFuels(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvFuelTypeService.saveFuelTypes(file, session);
  }

  @PostMapping("/upload/cars")
  public ModelAndView uploadCars(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvCarService.saveCars(file, session);
  }

  @PostMapping("/upload/types")
  public ModelAndView uploadTypes(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvPartTypeService.savePartType(file, session);
  }

  @PostMapping("/upload/directions")
  public ModelAndView uploadDirections(@RequestParam("file") MultipartFile file,
      HttpSession session)
      throws Exception {
    return csvTypeDirectionsService.saveDirections(file, session);
  }

  @PostMapping("/upload/sides")
  public ModelAndView uploadSides(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvTypeSidesService.saveSides(file, session);
  }

  @PostMapping("/upload/parts")
  public ModelAndView uploadParts(@RequestParam("file") MultipartFile file, HttpSession session)
      throws Exception {
    return csvPartService.saveParts(file, session);
  }
}
