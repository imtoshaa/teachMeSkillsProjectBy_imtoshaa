package by.tms.partshop.repositories;

import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

  Page<Order> findAllByUser(User user, Pageable pageable);

  List<Order> getAllByUser(User user);
  Order getById(long orderId);
}
