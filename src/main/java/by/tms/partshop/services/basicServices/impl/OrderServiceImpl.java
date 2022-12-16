package by.tms.partshop.services.basicServices.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.MY_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.USER;
import static by.tms.partshop.util.constants.ShopConstants.USER_HISTORY;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.dto.converter.UserConverter;
import by.tms.partshop.entities.Cart;
import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.OrderDetails;
import by.tms.partshop.entities.User;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.repositories.OrderDetailsRepository;
import by.tms.partshop.repositories.OrderRepository;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.basicServices.IOrderService;
import by.tms.partshop.services.basicServices.IUserService;
import by.tms.partshop.services.csv.ICsvWriterService;
import by.tms.partshop.telegramBot.ShopTelegramBot;
import by.tms.partshop.util.PageAttributes;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

  private final OrderRepository orderRepository;
  private final OrderDetailsRepository orderDetailsRepository;
  private final UserRepository userRepository;
  private final UserConverter userConverter;
  private final PartRepository partRepository;
  private final PartConverter partConverter;
  private final PageAttributes pageAttributes;
  private final ShopTelegramBot shopTelegramBot;
  private final ICsvWriterService writerService;
  private final IUserService userService;

  @Override
  public void createOrder(String userLogin, Cart cart) throws Exception {
    Order order = Order.builder()
        .user(userRepository.getUserByLogin(userLogin))
        .orderDate(LocalDate.now())
        .orderPrice(cart.getUserCartTotalPrice())
        .build();
    orderRepository.save(order);
    for (PartDto part : cart.getCart()) {
      Part part1 = partConverter.fromCartDto(part);
      part1.setAvailableToBuy(false);
      partRepository.save(part1);

      OrderDetails orderDetails = OrderDetails.builder()
          .order(order)
          .part(partConverter.fromCartDto(part))
          .build();

      orderDetailsRepository.save(orderDetails);
    }
    shopTelegramBot.sendMessageToChat(
        userRepository.getUserByLogin(userLogin).getChatId().getChatId(),
        "Заказ успешно сформирован! Номер заказа: " + order.getId() + "\n"
            + "Время заказа: " + order.getOrderDate() + "\n"
            + "Сумма заказа: " + order.getOrderPrice() + "$");
    cart.clear();
  }

  @Override
  public ModelAndView openUserPage(int pageNum, int pageSize) {
    ModelMap modelMap = new ModelMap();
    try {
      getUserData(pageNum, pageSize, modelMap);
      return new ModelAndView(MY_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(MY_PAGE);
    }
  }

  @Override
  public ModelAndView downloadAllOrders(HttpServletResponse response) {
    ModelMap modelMap = new ModelMap();
    try {
      Writer writer = writerService.getWriter(response, "orders.csv");
      writerService.downloadAllOrders(writer);
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
    getUserData(0, 10, modelMap);
    return new ModelAndView(MY_PAGE, modelMap);
  }

  @Override
  public ModelAndView downloadOrder(HttpServletResponse response, long orderId) throws IOException {
    ModelMap modelMap = new ModelMap();
    try {
      Writer writer = writerService.getWriter(response, "order " + orderId + ".csv");
      writerService.downloadOrder(writer, orderId);
    } catch (Exception ex) {
      log.error(ex.getMessage());
    }
    getUserData(0, 10, modelMap);
    return new ModelAndView(MY_PAGE, modelMap);
  }

  private void getUserData(int pageNum, int pageSize, ModelMap modelMap) {
    String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.getUserByLogin(userLogin);
    Pageable paging = PageRequest.of(pageNum, pageSize);
    Page<Order> order = orderRepository.findAllByUser(user, paging);
    pageAttributes.setPageAttributes(modelMap, order, pageNum, pageSize);
    modelMap.addAttribute(USER_HISTORY, order.getContent());
    modelMap.addAttribute(USER, userConverter.userDataToDto(user));
  }
}
