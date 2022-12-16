package by.tms.partshop.repositories;

import by.tms.partshop.entities.Images;
import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.entities.partSpecification.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

  Images getByCar_Id(long carId);

  Images getByPart_Id(long partId);

  Images getByCar(Car car);

  Images getByPart(Part part);
}