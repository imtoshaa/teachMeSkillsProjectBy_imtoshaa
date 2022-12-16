package by.tms.partshop.services.basicServices.impl;

import static by.tms.partshop.util.constants.PagesPathConstants.CART_PAGE;
import static by.tms.partshop.util.constants.ShopConstants.TOTAL_PRICE;
import static by.tms.partshop.util.constants.ShopConstants.USER_CART;

import by.tms.partshop.dto.PartDto;
import by.tms.partshop.dto.converter.PartConverter;
import by.tms.partshop.entities.Cart;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.services.basicServices.ICartService;
import by.tms.partshop.services.basicServices.IOrderService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class CartServiceImpl implements ICartService {

  private final PartRepository partRepository;
  private final PartConverter partConverter;
  private final IOrderService orderService;

  @Override
  public ModelAndView getAllPartsFromCart(Cart cart) {
    ModelMap modelMap = new ModelMap();
    try {
      if (Optional.ofNullable(cart).isPresent()) {
        modelMap.addAttribute(USER_CART, cart.getCart());
        modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
      }
      return new ModelAndView(CART_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(CART_PAGE);
    }
  }

  @Override
  public ModelAndView addPartToCart(long partCode, Cart cart) {
    ModelMap modelMap = new ModelMap();
    try {
      if (partRepository.existsByPartCodeAndAvailableToBuyIsTrue(partCode)) {
        PartDto part = partConverter.toCartDto(partRepository.getByPartCode(partCode));
        cart.addPartToCart(part);
      }
      modelMap.addAttribute(USER_CART, cart.getCart());
      modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
      return new ModelAndView(CART_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(CART_PAGE);
    }
  }

  @Override
  public ModelAndView deletePartFromCart(long partCode, Cart cart) {
    ModelMap modelMap = new ModelMap();
    try {
      cart.delUnnecessaryPart(partCode);
      modelMap.addAttribute(USER_CART, cart.getCart());
      modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
      return new ModelAndView(CART_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(CART_PAGE);
    }
  }

  @Override
  public ModelAndView clearUserCart(Cart cart) {
    ModelMap modelMap = new ModelMap();
    try {
      cart.clear();
      modelMap.addAttribute(USER_CART, cart.getCart());
      modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
      return new ModelAndView(CART_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(CART_PAGE);
    }
  }

  @Override
  public ModelAndView confirmOrder(Cart cart) throws Exception {
    ModelMap modelMap = new ModelMap();
    try {
      String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
      orderService.createOrder(userLogin, cart);
      modelMap.addAttribute(USER_CART, cart.getCart());
      modelMap.addAttribute(TOTAL_PRICE, cart.getUserCartTotalPrice());
      return new ModelAndView(CART_PAGE, modelMap);
    } catch (Exception e) {
      return new ModelAndView(CART_PAGE);
    }
  }

}
