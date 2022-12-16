package by.tms.partshop.entities.carSpecification;

import by.tms.partshop.entities.BaseEntity;
import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.carSpecification.Car;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "models")
public class Model extends BaseEntity {

  @Column(name = "model")
  private String model;
  @ManyToOne(optional = false)
  @JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "id")
  private Brand brand;
  @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Car> cars;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Model model1)) {
      return false;
    }
    return getModel().equals(model1.getModel()) && getBrand().equals(model1.getBrand());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getModel(), getBrand());
  }
}
