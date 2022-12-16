package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
  Brand getBrandByBrand(String brand);
}
