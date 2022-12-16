package by.tms.partshop.dto.converter;

import static by.tms.partshop.util.constants.RolesConstants.ROLE_ADMIN;
import static by.tms.partshop.util.constants.RolesConstants.ROLE_CLIENT;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.UserDataDto;
import by.tms.partshop.entities.User;
import by.tms.partshop.repositories.RoleRepository;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserConverter {

  private final RoleRepository roleRepository;

  public User newClientFromDto(NewUserDto newUser) {
    return User.builder()
        .login(newUser.getLogin())
        .password(newUser.getPassword())
        .name(newUser.getName())
        .surname(newUser.getSurname())
        .birthday(LocalDate.parse(newUser.getBirthday()))
        .email(newUser.getEmail())
        .phoneNumber(newUser.getPhoneNumber())
        .role(roleRepository.getByRole(ROLE_CLIENT))
        .build();
  }

  public User newAdminFromDto(NewUserDto newUser) {
    return User.builder()
        .login(newUser.getLogin())
        .password(newUser.getPassword())
        .name(newUser.getName())
        .surname(newUser.getSurname())
        .birthday(LocalDate.parse(newUser.getBirthday()))
        .email(newUser.getEmail())
        .phoneNumber(newUser.getPhoneNumber())
        .role(roleRepository.getByRole(ROLE_ADMIN))
        .build();
  }

  public UserDataDto userDataToDto(User user) {
    return UserDataDto.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .birthday(user.getBirthday())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .balance(user.getBalance())
        .build();
  }

  public User userDataFromDto(UserDataDto user) {
    return User.builder()
        .id(user.getId())
        .name(user.getName())
        .surname(user.getSurname())
        .birthday(user.getBirthday())
        .email(user.getEmail())
        .phoneNumber(user.getPhoneNumber())
        .balance(user.getBalance())
        .build();
  }
}
