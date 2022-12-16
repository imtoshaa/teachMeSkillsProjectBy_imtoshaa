package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.BODIES_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.BodyDto;
import by.tms.partshop.dto.converter.BodyConverter;
import by.tms.partshop.entities.carSpecification.Body;
import by.tms.partshop.repositories.BodyRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvBodyService;
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
public class CsvBodyServiceImpl implements ICsvBodyService {

  private final BodyConverter bodyConverter;
  private final BodyRepository bodyRepository;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveBodies(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
      List<BodyDto> csvFile = parseCsv(file);
      List<Body> bodies = Optional.ofNullable(csvFile)
              .map(list -> list.stream()
                      .map(bodyConverter::fromDto)
                      .toList())
              .orElse(null);
      if (!bodies.isEmpty()) {
        bodyRepository.saveAll(bodies);
        iAdminService.loadBodiesInSession(session);
        modelMap.addAttribute(BODIES_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
        return new ModelAndView(ADMIN_PAGE, modelMap);
      }
      modelMap.addAttribute(BODIES_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<BodyDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<BodyDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(BodyDto.class)
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
