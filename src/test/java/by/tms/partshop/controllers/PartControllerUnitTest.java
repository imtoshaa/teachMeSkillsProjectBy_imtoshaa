//package by.tms.partshop.controllers;
//
//import static by.tms.partshop.util.constants.PagesPathConstants.PART_PAGE;
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
//class PartControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  private static final long PART_CODE = 7545271;
//  private static final String PART_NAME = "Амортизатор подвески";
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenPartPage_givenNotEmptyPartCode() throws Exception {
//    mockMvc.perform(
//            MockMvcRequestBuilders.get("/part").param("part-code", String.valueOf(PART_CODE)))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString(PART_NAME)))
//        .andExpect(view().name(PART_PAGE));
//  }
//}