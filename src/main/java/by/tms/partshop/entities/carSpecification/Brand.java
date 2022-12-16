package by.tms.partshop.entities.carSpecification;

import by.tms.partshop.entities.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "brands")
public class Brand extends BaseEntity {

  @Column(name = "brand")
  @NotEmpty(message = "Поле бренд не может быть пустым!")
  private String brand;
  @Column(name = "image_path")
  private String imagePath;
  @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Model> models;
  @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Car> cars;
}
