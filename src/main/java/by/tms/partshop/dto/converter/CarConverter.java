package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.CarDto;
import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.repositories.BodyRepository;
import by.tms.partshop.repositories.BrandRepository;
import by.tms.partshop.repositories.FuelTypeRepository;
import by.tms.partshop.repositories.ModelRepository;
import by.tms.partshop.repositories.TransmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CarConverter {

  private final BrandRepository brandRepository;
  private final ModelRepository modelRepository;
  private final BodyRepository bodyRepository;
  private final TransmissionRepository transmissionRepository;
  private final FuelTypeRepository fuelTypeRepository;


  public Car fromDto(CarDto carDto) {
    return Car.builder()
        .carCode(carDto.getCarCode())
        .brand(brandRepository.getBrandByBrand(carDto.getBrand()))
        .model(modelRepository.getModelByModel(carDto.getModel()))
        .year(carDto.getYear())
        .body(bodyRepository.getBodyByBody(carDto.getBody()))
        .transmission(
            transmissionRepository.getTransmissionByTransmission(carDto.getTransmission()))
        .color(carDto.getColor())
        .engineId(carDto.getEngineId())
        .fuelType(fuelTypeRepository.getFuelTypeByTypeFuel(carDto.getTypeFuel()))
        .engineCC(carDto.getEngineCC())
        .engineFeatures(carDto.getEngineFeatures())
        .vin(carDto.getVin())
        .build();
  }

  public CarDto toDto(Car car) {
    return CarDto.builder()
        .carCode(car.getCarCode())
        .brand(car.getBrand().getBrand())
        .model(car.getModel().getModel())
        .body(car.getBody().getBody())
        .year(car.getYear())
        .vin(car.getVin())
        .build();
  }
}
