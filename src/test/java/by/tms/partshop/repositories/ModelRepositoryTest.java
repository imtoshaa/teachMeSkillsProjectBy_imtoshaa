//package by.tms.partshop.repositories;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.Matchers.hasItem;
//
//import by.tms.partshop.entities.carSpecification.Model;
//import java.util.List;
//import static org.hamcrest.MatcherAssert.assertThat;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class ModelRepositoryTest {
//
//  @Autowired
//  private ModelRepository modelRepository;
//  @Autowired
//  private BrandRepository brandRepository;
//
//  @Test
//  void shouldReturnNotEmptyModel_givenModel() {
//    Model model = modelRepository.getModelByModel("Ceed");
//    assertThat(model).isNotNull();
//    assertThat(model.getBrand().getBrand()).isEqualTo("KIA");
//    assertThat(model.getModel()).isEqualTo("Ceed");
//  }
//
//  @Test
//  void shouldReturnNotEmptyListModels_givenBrand() { // не работает иквалс, надо переопределить
//    List<Model> models = modelRepository.findAllByBrand(brandRepository.getBrandByBrand("BMW"));
//    Model model = Model.builder().brand(brandRepository.getBrandByBrand("BMW"))
//        .model("3 E90 E91 E92 E93").build();
//    assertThat(models).isNotNull();
//    assertThat(models, hasItem(model));
//  }
//}