//package by.tms.partshop.repositories;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//
//import by.tms.partshop.entities.Images;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class ImagesRepositoryTest {
//
//  @Autowired
//  private ImagesRepository imagesRepository;
//
//  @Test
//  void shouldReturnNotEmptyImageCar_givenCarId() {
//    Images image = imagesRepository.getByCar_Id(125);
//    assertThat(image).isNotNull();
//    assertThat(image.getPart()).isNull();
//    assertThat(image.getCar().getBrand().getBrand()).isEqualTo("Audi");
//    assertThat(image.getCar().getModel().getModel()).isEqualTo("A6 (C5)");
//    assertThat(image.getImagePath()).isNotNull().isNotBlank().isNotEmpty();
//  }
//
//  @Test
//  void shouldReturnNotEmptyImagePart_givenPartId() {
//    Images image = imagesRepository.getByPart_Id(555);
//    assertThat(image).isNotNull();
//    assertThat(image.getCar()).isNull();
//    assertThat(image.getPart().getCar().getCarCode()).isEqualTo("105771");
//    assertThat(image.getPart().getType().getType()).isEqualTo("Руль");
//    assertThat(image.getImagePath()).isNotNull().isNotBlank().isNotEmpty();
//  }
//}