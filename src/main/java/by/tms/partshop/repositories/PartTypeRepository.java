package by.tms.partshop.repositories;

import by.tms.partshop.entities.partSpecification.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartTypeRepository extends JpaRepository<Type, Long> {
  Type getByType(String type);
}
