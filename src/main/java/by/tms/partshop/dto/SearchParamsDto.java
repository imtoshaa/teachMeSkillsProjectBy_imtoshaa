package by.tms.partshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchParamsDto {

  private String searchQuery;
  private String brand;
  private String model;
  private String fuelType;
  private String body;
  private String constructionNumber;
  private String type;
  private String direction;
  private String side;
  private long partCode;
}
