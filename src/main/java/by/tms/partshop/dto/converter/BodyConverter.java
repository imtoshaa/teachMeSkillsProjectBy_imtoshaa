package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.BodyDto;
import by.tms.partshop.entities.carSpecification.Body;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BodyConverter {

  public BodyDto toDto(Body body) {
    return BodyDto.builder()
        .id(body.getId())
        .body(body.getBody())
        .build();
  }

  public Body fromDto(BodyDto bodyDto) {
    return Body.builder()
        .body(bodyDto.getBody())
        .build();
  }
}
