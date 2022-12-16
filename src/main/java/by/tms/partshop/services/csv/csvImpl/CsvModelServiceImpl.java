package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.MODELS_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.ModelDto;
import by.tms.partshop.dto.converter.ModelConverter;
import by.tms.partshop.entities.carSpecification.Model;
import by.tms.partshop.repositories.ModelRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvModelService;
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
public class CsvModelServiceImpl implements ICsvModelService {

  private final ModelConverter modelConverter;
  private final ModelRepository modelRepository;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveModels(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
    List<ModelDto> csvFile = parseCsv(file);
    log.info(csvFile.toString());
    List<Model> models = Optional.ofNullable(csvFile)
        .map(list -> list.stream()
            .map(modelConverter::fromDto)
            .toList())
        .orElse(null);
    if (!models.isEmpty()) {
      modelRepository.saveAll(models);
      iAdminService.loadModelsInSession(session);
      modelMap.addAttribute(MODELS_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute(MODELS_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<ModelDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<ModelDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(ModelDto.class)
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
