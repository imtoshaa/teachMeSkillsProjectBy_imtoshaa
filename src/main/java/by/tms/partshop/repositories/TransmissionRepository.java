package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
  Transmission getTransmissionByTransmission(String transmission);
}
