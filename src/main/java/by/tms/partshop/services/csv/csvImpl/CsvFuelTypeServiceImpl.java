package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.FUELS_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.FuelTypeDto;
import by.tms.partshop.dto.converter.FuelTypeConverter;
import by.tms.partshop.entities.carSpecification.FuelType;
import by.tms.partshop.repositories.FuelTypeRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.csv.ICsvFuelTypeService;
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
public class CsvFuelTypeServiceImpl implements ICsvFuelTypeService {

  private final FuelTypeConverter fuelTypeConverter;
  private final FuelTypeRepository fuelTypeRepository;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveFuelTypes(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
      List<FuelTypeDto> csvFile = parseCsv(file);
      log.info(csvFile.toString());
      List<FuelType> fuelTypes = Optional.ofNullable(csvFile)
              .map(list -> list.stream()
                      .map(fuelTypeConverter::fromDto)
                      .toList())
              .orElse(null);
      if (!fuelTypes.isEmpty()) {
        fuelTypeRepository.saveAll(fuelTypes);
        iAdminService.loadFuelTypesInSession(session);
        modelMap.addAttribute(FUELS_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
        return new ModelAndView(ADMIN_PAGE, modelMap);
      }
      modelMap.addAttribute(FUELS_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<FuelTypeDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<FuelTypeDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(FuelTypeDto.class)
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
