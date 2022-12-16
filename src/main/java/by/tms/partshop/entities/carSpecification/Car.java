package by.tms.partshop.entities.carSpecification;

import by.tms.partshop.entities.BaseEntity;
import by.tms.partshop.entities.Images;
import by.tms.partshop.entities.partSpecification.Part;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Getter
@Setter
@ToString
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

  @Column(name = "car_code", nullable = false)
  private String carCode;
  @Column(name = "engine_code")
  private String engineId;
  @Column(name = "engine_cc")
  private String engineCC;
  @Column(name = "engine_features")
  private String engineFeatures;
  @Column(name = "vin")
  private String vin;
  @Column(name = "year")
  private Integer year;
  @Column(name = "color")
  private String color;
  @ManyToOne(optional = false)
  @JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "id")
  private Brand brand;
  @ManyToOne(optional = false)
  @JoinColumn(name = "model_id", nullable = false, referencedColumnName = "id")
  private Model model;
  @ManyToOne
  @JoinColumn(name = "body_id", nullable = false, referencedColumnName = "id")
  private Body body;
  @ManyToOne
  @JoinColumn(name = "transmission_id", nullable = false, referencedColumnName = "id")
  private Transmission transmission;
  @ManyToOne
  @JoinColumn(name = "fuel_type_id", nullable = false, referencedColumnName = "id")
  private FuelType fuelType;
  @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Part> parts;
  @OneToOne(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private Images images;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    Car car = (Car) o;
    return getId() != null && Objects.equals(getId(), car.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}