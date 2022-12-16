package by.tms.partshop.services.basicServices;

import by.tms.partshop.entities.Cart;
import by.tms.partshop.exceptions.UnavailableForPurchaseException;
import org.springframework.web.servlet.ModelAndView;

public interface ICartService {

  ModelAndView getAllPartsFromCart(Cart cart);

  ModelAndView addPartToCart(long partCode, Cart cart) throws UnavailableForPurchaseException;

  ModelAndView deletePartFromCart(long partIndex, Cart cart);

  ModelAndView clearUserCart(Cart cart);

  ModelAndView confirmOrder(Cart cart) throws Exception;
}
