//package by.tms.partshop.controllers;
//
//import static by.tms.partshop.util.constants.PagesPathConstants.SEARCH_PAGE;
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class SearchControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenSearchResultPage_givenString() throws Exception {
//    mockMvc.perform(post("/search").param("searchQuery", "audi q7"))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString("Audi")))
//        .andExpect(content().string(containsString("Q7")))
//        .andExpect(view().name(SEARCH_PAGE));
//  }
//}