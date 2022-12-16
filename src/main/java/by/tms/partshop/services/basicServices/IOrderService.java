package by.tms.partshop.services.basicServices;

import by.tms.partshop.entities.Cart;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface IOrderService {

  void createOrder(String userLogin, Cart cart) throws Exception;

  ModelAndView openUserPage(int pageNumber, int pageSize);

  ModelAndView downloadAllOrders(HttpServletResponse response) throws IOException;

  ModelAndView downloadOrder(HttpServletResponse response, long orderId) throws IOException;
}
