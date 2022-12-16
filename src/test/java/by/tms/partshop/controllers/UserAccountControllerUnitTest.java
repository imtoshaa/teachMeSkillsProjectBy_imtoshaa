//package by.tms.partshop.controllers;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserAccountControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenAdminPage() throws Exception {
//    mockMvc.perform(get("/mypage"))
//        .andDo(print())
//        .andExpect(status().is3xxRedirection())
//        .andExpect(redirectedUrl("http://localhost/login"));
//  }
//}
