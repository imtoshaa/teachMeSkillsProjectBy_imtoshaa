package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.carSpecification.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
    Model getModelByModel(String model);
    List<Model> findAllByBrand(Brand brand);
}
