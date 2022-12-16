package by.tms.partshop.repositories;

import by.tms.partshop.entities.partSpecification.TypeSide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SideRepository extends JpaRepository<TypeSide, Long> {
  TypeSide getBySide(String side);
}
