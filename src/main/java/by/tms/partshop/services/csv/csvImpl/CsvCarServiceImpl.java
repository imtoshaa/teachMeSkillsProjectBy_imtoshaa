package by.tms.partshop.services.csv.csvImpl;

import static by.tms.partshop.util.constants.AdminConstants.CARS_UPLOAD_MESSAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.dto.converter.CarConverter;
import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import by.tms.partshop.services.basicServices.IImageService;
import by.tms.partshop.services.csv.ICsvCarService;
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
public class CsvCarServiceImpl implements ICsvCarService {

  private final CarConverter carConverter;
  private final CarRepository carRepository;
  private final IImageService IImageService;
  private final IAdminService iAdminService;

  @Override
  public ModelAndView saveCars(MultipartFile file, HttpSession session) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
      List<CarDto> csvCars = parseCsv(file);
      log.info(csvCars.toString());
      List<Car> cars = Optional.ofNullable(csvCars)
              .map(list -> list.stream()
                      .map(carConverter::fromDto)
                      .toList())
              .orElse(null);
      if (!cars.isEmpty()) {
        carRepository.saveAll(cars);
        IImageService.saveCarImages(file);
        iAdminService.loadCarsInSession(session);
        modelMap.addAttribute(CARS_UPLOAD_MESSAGE, HttpStatus.ACCEPTED);
        return new ModelAndView(ADMIN_PAGE, modelMap);
      }
      modelMap.addAttribute(CARS_UPLOAD_MESSAGE, HttpStatus.NO_CONTENT);
      return new ModelAndView(ADMIN_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(ADMIN_PAGE);
    }
  }

  private List<CarDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<CarDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(CarDto.class)
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
