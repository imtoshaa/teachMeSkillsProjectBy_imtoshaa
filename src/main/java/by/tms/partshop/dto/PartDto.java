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
public class PartDto {

  private Long id;
  private Long partCode;
  private Long carId;
  private String carCode;
  private String type;
  private String direction;
  private String side;
  private String constructionNumber;
  private String description;
  private int price;
  private boolean availableToBuy;
  private String imagePath;
}
