package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.TypeSideDto;
import by.tms.partshop.entities.partSpecification.TypeSide;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SideConverter {

  public TypeSide fromDto(TypeSideDto sideDto) {
    return TypeSide.builder()
        .side(sideDto.getSide())
        .build();
  }

  public TypeSideDto toDto(TypeSide side) {
    return TypeSideDto.builder()
        .id(side.getId())
        .side(side.getSide())
        .build();
  }
}
