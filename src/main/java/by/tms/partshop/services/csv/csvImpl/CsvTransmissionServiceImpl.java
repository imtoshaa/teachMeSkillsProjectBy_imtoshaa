package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.TRANSMISSIONS_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.TransmissionDto;
import by.tms.partshop.dto.converter.TransmissionConverter;
import by.tms.partshop.entities.carSpecification.Transmission;
import by.tms.partshop.repositories.TransmissionRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvTransmissionService;
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
@Service
@AllArgsConstructor
public class CsvTransmissionServiceImpl implements ICsvTransmissionService {

  private final TransmissionConverter transmissionConverter;
  private final TransmissionRepository transmissionRepository;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveTransmissions(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
    List<TransmissionDto> csvFile = parseCsv(file);
    log.info(csvFile.toString());
    List<Transmission> transmissions = Optional.ofNullable(csvFile)
        .map(list -> list.stream()
            .map(transmissionConverter::fromDto)
            .toList())
        .orElse(null);
    if (!transmissions.isEmpty()) {
      transmissionRepository.saveAll(transmissions);
      iAdminService.loadTransmissionsInSession(session);
      modelMap.addAttribute(TRANSMISSIONS_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    }
    modelMap.addAttribute(TRANSMISSIONS_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
    return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<TransmissionDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<TransmissionDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(TransmissionDto.class)
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
