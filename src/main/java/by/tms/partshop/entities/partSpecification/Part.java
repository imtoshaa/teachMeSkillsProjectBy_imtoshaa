package by.tms.partshop.entities.partSpecification;

import by.tms.partshop.entities.BaseEntity;
import by.tms.partshop.entities.Images;
import by.tms.partshop.entities.carSpecification.Car;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity
@Table(name = "parts")
public class Part extends BaseEntity {

  @Column(name = "part_code", nullable = false)
  private Long partCode;
  @Column(name = "construction_number")
  private String constructionNumber;
  @Column(name = "description")
  private String description;
  @Column(name = "price")
  private int price;
  @Column(name = "in_stock", nullable = false)
  private boolean availableToBuy;
  @ManyToOne
  @JoinColumn(name = "car_id", nullable = false, referencedColumnName = "id")
  private Car car;
  @ManyToOne
  @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
  private Type type;

  @ManyToOne
  @JoinColumn(name = "direction_id", referencedColumnName = "id")
  private TypeDirection direction;

  @ManyToOne
  @JoinColumn(name = "side_id", referencedColumnName = "id")
  private TypeSide side;

  @OneToOne(mappedBy = "part", cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private Images images;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Part part)) {
      return false;
    }
    return getPrice() == part.getPrice() && isAvailableToBuy() == part.isAvailableToBuy()
        && getPartCode().equals(part.getPartCode()) && Objects.equals(
        getConstructionNumber(), part.getConstructionNumber()) && Objects.equals(
        getDescription(), part.getDescription());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getPartCode(), getConstructionNumber(), getDescription(),
        getPrice(), isAvailableToBuy());
  }
}