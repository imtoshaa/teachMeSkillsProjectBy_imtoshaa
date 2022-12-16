//package by.tms.partshop.repositories;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.hasItems;
//
//import by.tms.partshop.entities.partSpecification.Part;
//import java.util.List;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class PartRepositoryTest {
//
//  @Autowired
//  private PartRepository partRepository;
//
//  private Part part1;
//  private Part part2;
//  private Part part3;
//  private Part part4;
//
//  @BeforeEach
//  void setUp() {
//    part1 = partRepository.getByPartCode(7544344);
//    part2 = partRepository.getByPartCode(7542679);
//    part3 = partRepository.getByPartCode(7542365);
//    part4 = partRepository.getByPartCode(7538741);
//  }
//
//  @Test
//  void shouldReturnNotEmptyPart_givenPartCode() {
//    assertThat(part1).isNotNull();
//    assertThat(part1.getPartCode()).isEqualTo(7544344);
//    assertThat(part1.getType().getType()).isEqualTo("Амортизатор подвески");
//    assertThat(part1.getDirection().getDirection()).isEqualTo("Перед");
//    assertThat(part1.getSide().getSide()).isEqualTo("Лев");
//  }
//
//  @Test
//  void shouldReturnNotEmptyListOfAvailableToBuyParts() {
//    Pageable paging = PageRequest.of(0, 600);
//    Page<Part> partPage = partRepository.findAllByAvailableToBuyTrue(paging);
//    List<Part> parts = partPage.getContent();
//    assertThat(parts, hasItems(part1, part2, part3, part4));
//  }
//
//  @Test
//  void shouldReturnNotEmptyAvailableToBuyPart_givenPartCode() {
//    assertThat(part1.isAvailableToBuy()).isTrue();
//  }
//}