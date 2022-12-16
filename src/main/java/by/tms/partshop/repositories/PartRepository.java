package by.tms.partshop.repositories;

import by.tms.partshop.entities.partSpecification.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long>, JpaSpecificationExecutor<Part> {
  Part getByPartCode(long partIndex);
  Page<Part> findAllByAvailableToBuyTrue(Pageable pageable);
  boolean existsByPartCodeAndAvailableToBuyIsTrue(long partCode);
}
