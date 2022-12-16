package by.tms.partshop.entities.carSpecification;

import by.tms.partshop.entities.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "transmissions")
public class Transmission extends BaseEntity {

  @Column(name = "transmission")
  private String transmission;
  @OneToMany(mappedBy = "transmission", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Car> cars;
}
