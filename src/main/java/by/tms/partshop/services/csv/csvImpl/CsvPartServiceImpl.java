package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.PARTS_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.basicServices.IImageService;
import by.tms.partshop.services.csv.ICsvPartService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class CsvPartServiceImpl implements ICsvPartService {

  private final PartConverter partConverter;
  private final PartRepository partRepository;
  private final IImageService iImageService;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveParts(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
    List<PartDto> csvFile = parseCsv(file);
    log.info(file.toString());
    List<Part> parts = Optional.ofNullable(csvFile)
        .map(list -> list.stream()
            .map(partConverter::fromDto)
            .toList())
        .orElse(null);
    if (!parts.isEmpty()) {
      partRepository.saveAll(parts);
      iImageService.savePartImages(file);
      iAdminService.loadPartsInSession(session);
      modelMap.addAttribute(PARTS_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute(PARTS_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<PartDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<PartDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(PartDto.class)
            .withIgnoreLeadingWhiteSpace(true)
            .withSeparator(',')
            .build();

        return csvToBean.parse();
      } catch (Exception ex) {
        log.error("Exception occurred during CSV parsing: {}", ex.getMessage());
      }
    } else {
      log.error("Empty CSV file is uploaded.");
    }
    return Collections.emptyList();
  }
}
