package by.tms.partshop.dto;

import com.opencsv.bean.CsvBindByName;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {

  @CsvBindByName(column = "Номер заказа")
  private long orderId;
  @CsvBindByName(column = "Дата заказа")
  private String orderDate;
  @CsvBindByName(column = "Артикул детали")
  private long partCode;
  @CsvBindByName(column = "Наименование детали")
  private String partType;
  @CsvBindByName(column = "Цена детали")
  private int price;
}