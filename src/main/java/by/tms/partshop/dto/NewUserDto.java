package by.tms.partshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewUserDto {

  private String login;
  private String password;
  private String name;
  private String surname;
  private String birthday;
  private String email;
  private String phoneNumber;
  private String role;
  private String chatId;
}
