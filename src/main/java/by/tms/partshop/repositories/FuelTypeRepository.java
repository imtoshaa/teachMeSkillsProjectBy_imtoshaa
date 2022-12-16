package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
    FuelType getFuelTypeByTypeFuel(String typeFuel);
}
