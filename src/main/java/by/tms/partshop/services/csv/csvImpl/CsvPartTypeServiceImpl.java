package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.PART_TYPES_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.dto.converter.PartTypeConverter;
import by.tms.partshop.entities.partSpecification.Type;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvPartTypeService;
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
public class CsvPartTypeServiceImpl implements ICsvPartTypeService {

  private final PartTypeConverter typeConverter;
  private final PartTypeRepository partTypeRepository;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView savePartType(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
    List<PartTypeDto> partTypeDtos = parseCsv(file);
    log.info(partTypeDtos.toString());
    List<Type> types = Optional.ofNullable(partTypeDtos)
        .map(list -> list.stream()
            .map(typeConverter::fromDto)
            .toList())
        .orElse(null);
    if (!partTypeDtos.isEmpty()) {
      partTypeRepository.saveAll(types);
      iAdminService.loadPartTypesInSession(session);
      modelMap.addAttribute(PART_TYPES_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute(PART_TYPES_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<PartTypeDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<PartTypeDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(PartTypeDto.class)
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
