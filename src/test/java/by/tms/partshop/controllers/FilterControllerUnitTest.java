//package by.tms.partshop.controllers;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class FilterControllerUnitTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//  private static final String URL = "/filter";
//  private MultiValueMap<String, String> searchParams = new LinkedMultiValueMap<>();
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenFilterPage_givenDefaultPagingParams() throws Exception {
//    searchParams.add("pageNumber", "0");
//    searchParams.add("pageSize", "600");
//    mockMvc.perform(get(URL).params(searchParams))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString("Audi")))
//        .andExpect(content().string(containsString("A4 (B7)")));
//  }
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenFilterPage_givenFilterParams() throws Exception {
//    searchParams.add("pageNumber", "0");
//    searchParams.add("pageSize", "600");
//    searchParams.add("brand", "Mercedes");
//    searchParams.add("model", "AW169");
//    searchParams.add("type", "Амортизатор подвески");
//    searchParams.add("direction", "Перед");
//    searchParams.add("side", "Лев=Прав");
//    mockMvc.perform(post(URL).params(searchParams))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString("7544335")))
//        .andExpect(content().string(containsString("Mercedes")))
//        .andExpect(content().string(containsString("AW169")))
//        .andExpect(content().string(containsString("Амортизатор подвески")))
//        .andExpect(content().string(containsString("Перед")))
//        .andExpect(content().string(containsString("Лев=Прав")));
//  }
//
//  @Test
//  void shouldReturnNotEmptyModelAndView_whenOpenFilterPage_givenBrandFromHomePage() throws Exception {
//    searchParams.add("pageNumber", "0");
//    searchParams.add("pageSize", "600");
//    searchParams.add("brand", "Jaguar");
//    mockMvc.perform(post("/filter/brand").params(searchParams))
//        .andDo(print())
//        .andExpect(status().isOk())
//        .andExpect(content().string(containsString("7533069")))
//        .andExpect(content().string(containsString("Двигатель (ДВС)")))
//        .andExpect(content().string(containsString("Jaguar")))
//        .andExpect(content().string(containsString("X type")));
//  }
//}