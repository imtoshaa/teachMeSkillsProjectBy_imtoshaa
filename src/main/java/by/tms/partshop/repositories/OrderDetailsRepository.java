package by.tms.partshop.repositories;

import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.OrderDetails;
import by.tms.partshop.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

  List<OrderDetails> getAllByOrder_User(User user);

  List<OrderDetails> getAllByOrder_UserAndOrder(User user, Order order);

}
