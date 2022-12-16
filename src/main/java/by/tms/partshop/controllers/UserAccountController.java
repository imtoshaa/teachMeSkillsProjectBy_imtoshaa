package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.PagesPathConstants.HOME_PAGE;

import by.tms.partshop.services.basicServices.IOrderService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/mypage")
public class UserAccountController {

  private final IOrderService orderService;

  @GetMapping
  public ModelAndView openAccountPage(@RequestParam(defaultValue = "0") int pageNumber,
      @RequestParam(defaultValue = "10") int pageSize) {
    return orderService.openUserPage(pageNumber, pageSize);
  }

  @GetMapping("/download-orders")
  public ModelAndView downloadAllOrders(HttpServletResponse response) throws IOException {
    return orderService.downloadAllOrders(response);
  }

  @GetMapping("/download-order")
  public ModelAndView downloadOrder(@RequestParam("orderId") long orderId,
      HttpServletResponse response) throws IOException {
    return orderService.downloadOrder(response, orderId);
  }
}