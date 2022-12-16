//package by.tms.partshop.controllers;
//
//import static by.tms.partshop.util.constants.PagesPathConstants.HOME_PAGE;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class HomeControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  private static final String BRAND = "Renault";
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenHomePage_givenNotEmptyBrand()
//      throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.get("/home"))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString(BRAND)))
//        .andExpect(view().name(HOME_PAGE));
//  }
//
//}