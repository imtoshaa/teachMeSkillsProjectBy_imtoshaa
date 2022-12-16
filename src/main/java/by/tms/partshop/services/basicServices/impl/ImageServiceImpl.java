package by.tms.partshop.services.basicServices.impl;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.dto.converter.ImagesConverter;
import by.tms.partshop.entities.Images;
import by.tms.partshop.repositories.ImagesRepository;
import by.tms.partshop.services.basicServices.IImageService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@AllArgsConstructor
public class ImageServiceImpl implements IImageService {

  private ImagesRepository imagesRepository;

  private ImagesConverter imagesConverter;


  @Override
  public List<String> getAllCarImagePath(long carId) {
    try {
      return Arrays.stream(imagesRepository.getByCar_Id(carId)
                      .getImagePath()
                      .split(";"))
              .toList();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  @Override
  public List<String> getAllPartImagePath(long partId) {
    try {
      return Arrays.stream(imagesRepository.getByPart_Id(partId)
                      .getImagePath()
                      .split(";"))
              .toList();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  @Override
  public void saveCarImages(MultipartFile file) throws Exception {
    List<ImagesDto> csvCarImages = parseCsv(file);
    log.info(csvCarImages.toString());
    List<Images> images = Optional.ofNullable(csvCarImages)
        .map(list -> list.stream()
            .map(imagesConverter::imagesCarFromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(images).isPresent()) {
      images.forEach(imagesRepository::save);
    }
  }

  @Override
  public void savePartImages(MultipartFile file) throws Exception {
    List<ImagesDto> csvPartImages = parseCsv(file);
    log.info(csvPartImages.toString());
    List<Images> images = Optional.ofNullable(csvPartImages)
        .map(list -> list.stream()
            .map(imagesConverter::imagesPartFromDto)
            .toList())
        .orElse(null);
    if (Optional.ofNullable(images).isPresent()) {
      images.forEach(imagesRepository::save);
    }
  }

  private List<ImagesDto> parseCsv(MultipartFile file) {
    if (Optional.ofNullable(file).isPresent()) {
      try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
        CsvToBean<ImagesDto> csvToBean = new CsvToBeanBuilder(reader)
            .withType(ImagesDto.class)
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
