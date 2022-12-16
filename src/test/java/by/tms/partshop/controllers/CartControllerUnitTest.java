//package by.tms.partshop.controllers;
//
//import static by.tms.partshop.util.constants.PagesPathConstants.CART_PAGE;
//import static by.tms.partshop.util.constants.ShopConstants.SHOPPING_CART;
//import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.not;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import by.tms.partshop.dto.converter.PartConverter;
//import by.tms.partshop.entities.Cart;
//import by.tms.partshop.repositories.PartRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CartControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//  @Autowired
//  private PartRepository partRepository;
//  @Autowired
//  private PartConverter partConverter;
//
//  private static final long PART_CODE = 7545271;
//  private static final String PART_CODE_CATALOGUE_MESSAGE = "Номер запчасти в каталоге: ";
//  private static final Cart cart = new Cart();
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenCartPage()
//      throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/cart/open"))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString("Shopping cart")))
//        .andExpect(view().name(CART_PAGE));
//  }
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenAddPartInCart_givenPartParam()
//      throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/cart/add").param("part-code", String.valueOf(
//            PART_CODE)))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString(PART_CODE_CATALOGUE_MESSAGE + PART_CODE)))
//        .andExpect(view().name(CART_PAGE));
//  }
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenDeletePartFromCart_givenPartParam()
//      throws Exception {
//    cart.addPartToCart(partConverter.toCartDto(partRepository.getByPartCode(PART_CODE)));
//    mockMvc.perform(MockMvcRequestBuilders.get("/cart/delete").param("part-code", String.valueOf(
//            PART_CODE)).flashAttr(SHOPPING_CART, cart))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(not(containsString(PART_CODE_CATALOGUE_MESSAGE + PART_CODE))))
//        .andExpect(view().name(CART_PAGE));
//  }
//
//  @Test
//  public void testPurchaseWithoutAuthentication() throws Exception {
//    cart.addPartToCart(partConverter.toCartDto(partRepository.getByPartCode(PART_CODE)));
//    mockMvc.perform(get("/cart/confirmOrder").flashAttr(SHOPPING_CART, cart))
//        .andDo(print())
//        .andExpect(status().is3xxRedirection())
//        .andExpect(redirectedUrl("http://localhost/login"));
//  }
//}
