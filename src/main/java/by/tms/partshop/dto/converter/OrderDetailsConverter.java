package by.tms.partshop.dto.converter;

import by.tms.partshop.dto.OrderDetailsDto;
import by.tms.partshop.entities.OrderDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderDetailsConverter {


  public OrderDetailsDto toDto(OrderDetails orderDetails) {
    return OrderDetailsDto.builder()
        .orderId(orderDetails.getOrder().getId())
        .orderDate(orderDetails.getOrder().getOrderDate().toString())
        .partCode(orderDetails.getPart().getPartCode())
        .partType(orderDetails.getPart().getType().getType())
        .price(orderDetails.getPart().getPrice())
        .build();
  }

//  public Order fromDto(OrderDetailsDto orderDetailsDto) {
//    return Order.builder()
//        .user(orderDetailsDto.getUser())
//        .orderDate(orderDetailsDto.getDate())
//        .orderPrice(orderDetailsDto.getPrice())
//        .build();
//  }
}
