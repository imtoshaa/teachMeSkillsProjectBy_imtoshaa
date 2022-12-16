package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.TypeDirectionDto;
import by.tms.partshop.entities.partSpecification.TypeDirection;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TypeDirectionConverter {

  public TypeDirection fromDto(TypeDirectionDto typeDirectionDto) {
    return TypeDirection.builder()
        .direction(typeDirectionDto.getDirection())
        .build();
  }

  public TypeDirectionDto toDto(TypeDirection direction) {
    return TypeDirectionDto.builder()
        .id(direction.getId())
        .direction(direction.getDirection())
        .build();
  }
}
