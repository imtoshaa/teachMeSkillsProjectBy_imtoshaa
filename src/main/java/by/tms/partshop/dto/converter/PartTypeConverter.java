package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.PartTypeDto;
import by.tms.partshop.entities.partSpecification.Type;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PartTypeConverter {

  public Type fromDto(PartTypeDto typeDto) {
    return Type.builder()
        .type(typeDto.getType())
        .build();
  }

  public PartTypeDto toDto(Type type) {
    return PartTypeDto.builder()
        .id(type.getId())
        .type(type.getType())
        .build();
  }
}
