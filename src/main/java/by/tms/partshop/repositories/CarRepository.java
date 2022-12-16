package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

  Car getByCarCode(String carCode);
}

