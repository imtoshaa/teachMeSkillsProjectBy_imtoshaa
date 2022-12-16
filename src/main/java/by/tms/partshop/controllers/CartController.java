package by.tms.partshop.controllers;

import static by.tms.partshop.util.constants.ShopConstants.PART_CODE;
import static by.tms.partshop.util.constants.ShopConstants.SHOPPING_CART;

import by.tms.partshop.entities.Cart;
import by.tms.partshop.services.basicServices.ICartService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@AllArgsConstructor
@SessionAttributes({SHOPPING_CART})
@RequestMapping("/cart")
public class CartController {

  private final ICartService cartService;

  @GetMapping("/open")
  public ModelAndView openCartPage(@ModelAttribute(SHOPPING_CART) Cart cart) {
    return cartService.getAllPartsFromCart(cart);
  }

  @GetMapping("/add")
  public ModelAndView addProductToCart(@ModelAttribute(SHOPPING_CART) Cart cart,
      @RequestParam(PART_CODE) String code) throws Exception {
    log.info("added part to cart: " + code);
    long partCode = Long.parseLong(code);
    return cartService.addPartToCart(partCode, cart);
  }

  @GetMapping("/delete")
  public ModelAndView delProductFromCart(@ModelAttribute(SHOPPING_CART) Cart cart,
      @RequestParam(PART_CODE) String id) {
    long partCode = Long.parseLong(id);
    return cartService.deletePartFromCart(partCode, cart);
  }

  @GetMapping("/clear")
  public ModelAndView clearUserCart(@ModelAttribute(SHOPPING_CART) Cart cart) {
    return cartService.clearUserCart(cart);
  }

  @GetMapping("/confirmOrder")
  public ModelAndView confirmOrder(@ModelAttribute(SHOPPING_CART) Cart cart)
      throws Exception {
    return cartService.confirmOrder(cart);
  }

  @ModelAttribute(SHOPPING_CART)
  public Cart shoppingCart() {
    return new Cart();
  }
}
