package by.tms.partshop.entities.partSpecification;

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
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@SuperBuilder
@Entity
@Table(name = "part_type_directions")
public class TypeDirection extends BaseEntity {

  @Column(name = "direction")
  private String direction;

  @OneToMany(mappedBy = "direction", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private List<Part> parts;
}
