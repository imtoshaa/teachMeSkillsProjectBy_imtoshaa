package by.tms.partshop.entities;

import by.tms.partshop.entities.partSpecification.Part;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "order_details")
public class OrderDetails extends BaseEntity {

  @ManyToOne(optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @OneToOne(optional = false)
  @JoinColumn(name = "part_id", nullable = false)
  private Part part;
}
