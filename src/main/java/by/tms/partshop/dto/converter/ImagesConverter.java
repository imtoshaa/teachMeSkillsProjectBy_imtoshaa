package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.ImagesDto;
import by.tms.partshop.entities.Images;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.PartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImagesConverter {

  private CarRepository carRepository;
  private PartRepository partRepository;

  public Images imagesCarFromDto(ImagesDto imagesDto) {
    return Images.builder()
        .car(carRepository.getByCarCode(imagesDto.getCarCode()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }

  public ImagesDto imagesCarToDto(Images images) {
    return ImagesDto.builder()
        .carId(images.getCar().getId())
        .carCode(images.getCar().getCarCode())
        .imagePath(images.getImagePath())
        .build();
  }

  public Images imagesPartFromDto(ImagesDto imagesDto) {
    return Images.builder()
        .part(partRepository.getByPartCode(imagesDto.getPartCode()))
        .imagePath(imagesDto.getImagePath())
        .build();
  }

  public ImagesDto imagesPartToDto(Images images) {
    return ImagesDto.builder()
        .partCode(images.getPart().getPartCode())
        .imagePath(images.getImagePath())
        .build();
  }
}
