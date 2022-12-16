package by.tms.partshop.services.basicServices.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.LOGIN_PAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.MY_PAGE;
import static by.tms.partshop.util.constants.PagesPathConstants.REGISTRATION_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.USER_DATA;
import static by.tms.partshop.util.constants.ShopConstants.USER_HISTORY;

import by.tms.partshop.dto.NewUserDto;
import by.tms.partshop.dto.converter.UserConverter;
import by.tms.partshop.entities.OrderDetails;
import by.tms.partshop.entities.TelegramBot;
import by.tms.partshop.entities.User;
import by.tms.partshop.repositories.OrderDetailsRepository;
import by.tms.partshop.repositories.TelegramRepository;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.basicServices.IUserService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;
  private final TelegramRepository telegramRepository;
  private final UserConverter userConverter;
  private OrderDetailsRepository orderDetailsRepository;
  private PasswordEncoder passwordEncoder;

  @Override
  public ModelAndView registration(NewUserDto userDto) {
    try {
      userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
      User user = userConverter.newClientFromDto(userDto);
      TelegramBot bot = telegramRepository.getByChatId(Long.parseLong(userDto.getChatId()));
      user.setChatId(bot);
      userRepository.save(user);
      User userWithId = userRepository.getUserByLogin(user.getLogin());
      bot.setUser(userWithId);
      telegramRepository.save(bot);
      return new ModelAndView(LOGIN_PAGE);
    } catch (Exception e) {
      log.info(e.getMessage());
      return new ModelAndView(REGISTRATION_PAGE);
    }
  }

  @Override
  public ModelAndView getUserData(long id) {
    ModelMap modelMap = new ModelMap();
    try {
      User user = userRepository.getUserById(id);
      List<OrderDetails> orderHistory = orderDetailsRepository.getAllByOrder_User(user);
      modelMap.addAttribute(USER_DATA, userConverter.userDataToDto(userRepository.getUserById(id)));
      modelMap.addAttribute(USER_HISTORY, orderHistory);
      return new ModelAndView(MY_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(MY_PAGE);
    }
  }
}
