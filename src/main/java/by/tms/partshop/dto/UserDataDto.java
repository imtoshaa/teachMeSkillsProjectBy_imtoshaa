package by.tms.partshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDto {

  private Long id;
  private String name;
  private String surname;
  private LocalDate birthday;
  private String email;
  private String phoneNumber;
  private BigDecimal balance;
}
