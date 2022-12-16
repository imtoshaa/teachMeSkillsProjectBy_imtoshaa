package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.FuelTypeDto;
import by.tms.partshop.entities.carSpecification.FuelType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FuelTypeConverter {

  public FuelTypeDto toDto(FuelType fuelType) {
    return FuelTypeDto.builder()
        .id(fuelType.getId())
        .typeFuel(fuelType.getTypeFuel())
        .build();
  }

  public FuelType fromDto(FuelTypeDto fuelTypeDto) {
    return FuelType.builder()
        .typeFuel(fuelTypeDto.getTypeFuel())
        .build();
  }
}
