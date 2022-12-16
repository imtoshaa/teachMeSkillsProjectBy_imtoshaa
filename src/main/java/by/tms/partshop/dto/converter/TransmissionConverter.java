package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.TransmissionDto;
import by.tms.partshop.entities.carSpecification.Transmission;
import org.springframework.stereotype.Component;

@Component
public class TransmissionConverter {

  public TransmissionDto toDto(Transmission transmission) {
    return TransmissionDto.builder()
        .transmission(transmission.getTransmission())
        .build();
  }

  public Transmission fromDto(TransmissionDto transmissionDto) {
    return Transmission.builder()
        .transmission(transmissionDto.getTransmission())
        .build();
  }
}
