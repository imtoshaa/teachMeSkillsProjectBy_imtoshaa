package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.ModelDto;
import by.tms.partshop.entities.carSpecification.Model;
import by.tms.partshop.repositories.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ModelConverter {

  private BrandRepository brandRepository;

  public ModelDto toDto(Model model) {
    return ModelDto.builder()
        .id(model.getId())
        .model(model.getModel())
        .build();
  }

  public Model fromDto(ModelDto modelDto) {
    return Model.builder()
        .brand(brandRepository.getBrandByBrand(modelDto.getBrand()))
        .model(modelDto.getModel())
        .build();
  }
}
