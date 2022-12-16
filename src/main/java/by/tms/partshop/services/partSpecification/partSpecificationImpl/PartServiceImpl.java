package by.tms.partshop.services.partSpecification.partSpecificationImpl;

import static by.tms.partshop.util.constants.AdminConstants.PART_CREATE_MESSAGE;
import static by.tms.partshop.util.constants.AdminConstants.PART_DELETE_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.PART_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.PART;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.ImagesConverter;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.entities.Images;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.repositories.ImagesRepository;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.partSpecification.IPartService;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class PartServiceImpl implements IPartService {

  private final PartRepository partRepository;
  private final ImagesRepository imagesRepository;
  private final PartConverter partConverter;
  private final ImagesConverter imagesConverter;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveNewPart(PartDto newPart, ImagesDto newPartImages, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      Part part = partConverter.fromDto(newPart);
      part.setAvailableToBuy(true);
      partRepository.save(part);
      Images images = imagesConverter.imagesPartFromDto(newPartImages);
      imagesRepository.save(images);
      iAdminService.loadPartsInSession(session);
      modelMap.addAttribute(PART_CREATE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(PART_CREATE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView deletePart(String deletePartCode, HttpSession session) {
    ModelMap modelMap = new ModelMap();
    try {
      long partCode = Long.parseLong(deletePartCode);
      Part part = partRepository.getByPartCode(partCode);
      Images images = imagesRepository.getByPart(part);
      imagesRepository.delete(images);
      partRepository.delete(part);
      iAdminService.loadPartsInSession(session);
      modelMap.addAttribute(PART_DELETE_MESSAGE, HttpStatus.ACCEPTED);
    } catch (Exception ex) {
      log.error(ex.getMessage());
      modelMap.addAttribute(PART_DELETE_MESSAGE, HttpStatus.FORBIDDEN);
    }
    return new ModelAndView(ADMIN_PAGE, modelMap);
  }

  @Override
  public ModelAndView getPartData(long partCode) {
    ModelMap modelMap = new ModelMap();
    Part part = partRepository.getByPartCode(partCode);
    modelMap.addAttribute(PART, part);
    return new ModelAndView(PART_PAGE, modelMap);
  }
}
