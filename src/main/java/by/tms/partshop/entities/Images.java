package by.tms.partshop.entities;

import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.entities.partSpecification.Part;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@Entity
@Table(name = "images")
public class Images extends BaseEntity {

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "car_id", referencedColumnName = "id")
  private Car car;
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "part_id", referencedColumnName = "id")
  private Part part;
  @Column(name = "image_paths", nullable = false)
  private String imagePath;
}
