package by.tms.partshop.repositories;

import by.tms.partshop.entities.partSpecification.TypeDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDirectionRepository extends JpaRepository<TypeDirection, Long> {
  TypeDirection getByDirection(String direction);
}
