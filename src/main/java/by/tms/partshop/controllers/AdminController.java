package by.tms.partshop.controllers;

import by.tms.partshop.dto.BodyDto;
import by.tms.partshop.dto.BrandDto;
import by.tms.partshop.dto.CarDto;
import by.tms.partshop.dto.FuelTypeDto;
import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.ModelDto;
import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.dto.TransmissionDto;
import by.tms.partshop.dto.TypeDirectionDto;
import by.tms.partshop.dto.TypeSideDto;
import by.tms.partshop.services.carSpecification.IBodyService;
import by.tms.partshop.services.carSpecification.IBrandService;
import by.tms.partshop.services.carSpecification.ICarService;
import by.tms.partshop.services.carSpecification.IFuelTypeService;
import by.tms.partshop.services.carSpecification.IModelService;
import by.tms.partshop.services.carSpecification.ITransmissionService;
import by.tms.partshop.services.partSpecification.IPartService;
import by.tms.partshop.services.partSpecification.IPartTypeService;
import by.tms.partshop.services.partSpecification.ITypeDirectionService;
import by.tms.partshop.services.partSpecification.ITypeSideService;
import javax.servlet.http.HttpSession;
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
@RequestMapping("/admins")
public class AdminController {

  private final IBrandService iBrandService;
  private final IModelService iModelService;
  private final ITransmissionService iTransmissionService;
  private final IFuelTypeService iFuelTypeService;
  private final IBodyService iBodyService;
  private final ICarService iCarService;
  private final IPartTypeService partTypeService;
  private final ITypeDirectionService directionService;
  private final ITypeSideService sideService;
  private final IPartService partService;

  @PostMapping("/create/brand")
  public ModelAndView saveNewBrand(@ModelAttribute BrandDto newBrand, HttpSession session) {
    return iBrandService.saveNewBrand(newBrand, session);
  }

  @PostMapping("/delete/brand")
  public ModelAndView deleteBrand(@RequestParam("brand") String brand, HttpSession session) {
    return iBrandService.deleteBrand(brand, session);
  }

  @PostMapping("/create/model")
  public ModelAndView saveNewModel(@ModelAttribute ModelDto newModel, HttpSession session) {
    return iModelService.saveNewModel(newModel, session);
  }

  @PostMapping("/delete/model")
  public ModelAndView deleteModel(@ModelAttribute BrandDto getBrand,
      @RequestParam("model") String model, HttpSession session) {
    return iModelService.deleteModel(getBrand, model, session);
  }

  @PostMapping("/create/transmission")
  public ModelAndView saveNewTransmission(@ModelAttribute TransmissionDto newTransmission,
      HttpSession session) {
    return iTransmissionService.saveNewTransmission(newTransmission, session);
  }

  @PostMapping("/delete/transmission")
  public ModelAndView deleteTransmission(@RequestParam("transmission") String deleteTransm,
      HttpSession session) {
    return iTransmissionService.deleteTransmission(deleteTransm, session);
  }

  @PostMapping("/create/fuel-type")
  public ModelAndView saveNewFuelType(@ModelAttribute FuelTypeDto newFuelType,
      HttpSession session) {
    return iFuelTypeService.saveNewFuelType(newFuelType, session);
  }

  @PostMapping("/delete/fuel-type")
  public ModelAndView deleteFuelType(@RequestParam("fuelType") String deleteFuelType,
      HttpSession session) {
    return iFuelTypeService.deleteFuelType(deleteFuelType, session);
  }

  @PostMapping("/create/body")
  public ModelAndView saveNewBody(@ModelAttribute BodyDto newBody, HttpSession session) {
    return iBodyService.saveNewBody(newBody, session);
  }

  @PostMapping("/delete/body")
  public ModelAndView deleteBody(@RequestParam("body-type") String deleteBody, HttpSession session) {
    return iBodyService.deleteBody(deleteBody, session);
  }

  @PostMapping("/create/car")
  public ModelAndView saveNewCar(@ModelAttribute CarDto newCar,
      @ModelAttribute ImagesDto newCarImages, HttpSession session) {
    return iCarService.saveNewCar(newCar, newCarImages, session);
  }

  @PostMapping("/delete/car")
  public ModelAndView deleteCar(@RequestParam("car-code") String deleteCar, HttpSession session) {
    return iCarService.deleteCar(deleteCar, session);
  }

  @PostMapping("/create/part-type")
  public ModelAndView saveNewPartType(@ModelAttribute PartTypeDto newPartType,
      HttpSession session) {
    return partTypeService.saveNewPartType(newPartType, session);
  }

  @PostMapping("/delete/part-type")
  public ModelAndView deletePartType(@RequestParam("part-type") String deletePartType,
      HttpSession session) {
    return partTypeService.deletePartType(deletePartType, session);
  }

  @PostMapping("/create/direction")
  public ModelAndView saveNewTypeDirection(@ModelAttribute TypeDirectionDto newDirection,
      HttpSession session) {
    return directionService.saveNewTypeDirection(newDirection, session);
  }

  @PostMapping("/delete/direction")
  public ModelAndView deleteTypeDirection(@RequestParam("direction") String deleteDirection,
      HttpSession session) {
    return directionService.deleteTypeDirection(deleteDirection, session);
  }

  @PostMapping("/create/side")
  public ModelAndView saveNewTypeSIde(@ModelAttribute TypeSideDto newSide,
      HttpSession session) {
    return sideService.saveNewTypeSide(newSide, session);
  }

  @PostMapping("/delete/side")
  public ModelAndView deleteTypeSIde(@RequestParam("side") String deleteSide,
      HttpSession session) {
    return sideService.deleteTypeSide(deleteSide, session);
  }

  @PostMapping("/create/part")
  public ModelAndView saveNewPart(@ModelAttribute PartDto newPart,
      @ModelAttribute ImagesDto newPartImages,
      HttpSession session) {
    return partService.saveNewPart(newPart, newPartImages, session);
  }

  @PostMapping("/delete/part")
  public ModelAndView deletePart(@RequestParam("part-code") String deletePartCode,
      HttpSession session) {
    return partService.deletePart(deletePartCode, session);
  }

  @ModelAttribute
  public BrandDto newBrand() {
    return new BrandDto();
  }

  @ModelAttribute
  public ModelDto newModel() {
    return new ModelDto();
  }

  @ModelAttribute
  public TransmissionDto newTransmission() {
    return new TransmissionDto();
  }

  @ModelAttribute
  public FuelTypeDto newFuelType() {
    return new FuelTypeDto();
  }

  @ModelAttribute
  public BodyDto newBody() {
    return new BodyDto();
  }

  @ModelAttribute
  public CarDto newCar() {
    return new CarDto();
  }

  @ModelAttribute
  public PartTypeDto newPartType() {
    return new PartTypeDto();
  }

  @ModelAttribute
  public TypeDirectionDto newTypeDirection() {
    return new TypeDirectionDto();
  }

  @ModelAttribute
  public TypeSideDto newTypeSide() {
    return new TypeSideDto();
  }

  @ModelAttribute
  public PartDto newPart() {
    return new PartDto();
  }

  @ModelAttribute
  public ImagesDto newImagePaths() {
    return new ImagesDto();
  }
}
