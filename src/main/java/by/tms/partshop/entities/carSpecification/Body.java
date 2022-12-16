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
@Table(name = "bodies")
public class Body extends BaseEntity {

  @Column(name = "body")
  private String body;
  @OneToMany(mappedBy = "body", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @ToString.Exclude
  private List<Car> cars;
}
