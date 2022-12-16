package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.BrandDto;
import by.tms.partshop.entities.carSpecification.Brand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BrandConverter {

  public BrandDto toDto(Brand brand) {
    return BrandDto.builder()
        .id(brand.getId())
        .brand(brand.getBrand())
        .imagePath(brand.getImagePath())
        .build();
  }

  public Brand fromDto(BrandDto brandDto) {
    return Brand.builder()
        .brand(brandDto.getBrand())
        .imagePath(brandDto.getImagePath())
        .build();
  }
}