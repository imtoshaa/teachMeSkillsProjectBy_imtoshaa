package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.repositories.SideRepository;
import by.tms.partshop.repositories.TypeDirectionRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class PartConverter {

  private final CarRepository carRepository;
  private final PartTypeRepository partTypeRepository;
  private final TypeDirectionRepository directionRepository;
  private final SideRepository sideRepository;

  public Part fromDto(PartDto partDto) {
    return Part.builder()
        .partCode(partDto.getPartCode())
        .car(carRepository.getByCarCode(partDto.getCarCode()))
        .type(partTypeRepository.getByType(partDto.getType()))
        .direction(Optional.ofNullable(directionRepository.getByDirection(partDto.getDirection()))
            .orElse(directionRepository.getByDirection("-")))
        .side(Optional.ofNullable(sideRepository.getBySide(partDto.getSide()))
            .orElse(sideRepository.getBySide("-")))
        .constructionNumber(partDto.getConstructionNumber())
        .description(partDto.getDescription())
        .price(partDto.getPrice())
        .availableToBuy(partDto.isAvailableToBuy())
        .build();
  }

  public PartDto toDto(Part part) {
    return PartDto.builder()
        .partCode(part.getPartCode())
        .carCode(part.getCar().getCarCode())
        .type(part.getType().getType())
        .direction(part.getDirection().getDirection())
        .side(part.getSide().getSide())
        .constructionNumber(part.getConstructionNumber())
        .description(part.getDescription())
        .price(part.getPrice())
        .imagePath(part.getImages().getImagePath())
        .build();
  }

  public PartDto toCartDto(Part part) {
    return PartDto.builder()
        .id(part.getId())
        .partCode(part.getPartCode())
        .carCode(part.getCar().getCarCode())
        .type(part.getType().getType())
        .direction(part.getDirection().getDirection())
        .side(part.getSide().getSide())
        .constructionNumber(part.getConstructionNumber())
        .description(part.getDescription())
        .price(part.getPrice())
        .imagePath(part.getImages().getImagePath())
        .build();
  }

  public Part fromCartDto(PartDto partDto) {
    return Part.builder()
        .id(partDto.getId())
        .partCode(partDto.getPartCode())
        .car(carRepository.getByCarCode(partDto.getCarCode()))
        .type(partTypeRepository.getByType(partDto.getType()))
        .direction(Optional.ofNullable(directionRepository.getByDirection(partDto.getDirection()))
            .orElse(directionRepository.getByDirection("-")))
        .side(Optional.ofNullable(sideRepository.getBySide(partDto.getSide()))
            .orElse(sideRepository.getBySide("-")))
        .constructionNumber(partDto.getConstructionNumber())
        .description(partDto.getDescription())
        .price(partDto.getPrice())
        .availableToBuy(partDto.isAvailableToBuy())
        .build();
  }
}
