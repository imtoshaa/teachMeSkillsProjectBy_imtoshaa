package by.tms.partshop.repositories;

import by.tms.partshop.entities.carSpecification.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyRepository extends JpaRepository<Body, Long> {
    Body getBodyByBody(String body);
}
