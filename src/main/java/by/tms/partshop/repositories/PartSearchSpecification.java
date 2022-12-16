package by.tms.partshop.repositories;

import by.tms.partshop.dto.SearchParamsDto;
import by.tms.partshop.entities.carSpecification.Body;
import by.tms.partshop.entities.carSpecification.Brand;
import by.tms.partshop.entities.carSpecification.Car;
import by.tms.partshop.entities.carSpecification.FuelType;
import by.tms.partshop.entities.carSpecification.Model;
import by.tms.partshop.entities.carSpecification.Transmission;
import by.tms.partshop.entities.partSpecification.Part;
import by.tms.partshop.entities.partSpecification.Type;
import by.tms.partshop.entities.partSpecification.TypeDirection;
import by.tms.partshop.entities.partSpecification.TypeSide;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;

@Slf4j
@AllArgsConstructor
public class PartSearchSpecification implements Specification<Part> {

  private SearchParamsDto searchParamsDto;

  @Override
  public Predicate toPredicate(Root<Part> root, CriteriaQuery<?> query,
      CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(criteriaBuilder.isTrue(root.get("availableToBuy")));

    Join<Part, Car> carJoin = root.join("car");
    Join<Car, Model> modelJoin = carJoin.join("model");
    Join<Model, Brand> brandJoin = modelJoin.join("brand");
    Join<Car, Body> bodyJoin = carJoin.join("body");
    Join<Car, Transmission> transmissionJoin = carJoin.join("transmission");
    Join<Car, FuelType> fuelTypeJoin = carJoin.join("fuelType");
    Join<Part, Type> typeJoin = root.join("type");
    Join<Part, TypeDirection> directionJoin = root.join("direction");
    Join<Part, TypeSide> sideJoin = root.join("side");

    if (Optional.ofNullable(searchParamsDto.getSearchQuery()).isPresent()
        && !searchParamsDto.getSearchQuery()
        .isBlank()) {
      predicates.addAll(Arrays.stream(searchParamsDto.getSearchQuery().split(" "))
          .map(param -> criteriaBuilder.or(
              criteriaBuilder.like(brandJoin.get("brand"), "%" + param + "%"),
              criteriaBuilder.like(modelJoin.get("model"), "%" + param + "%"),
              criteriaBuilder.like(bodyJoin.get("body"), "%" + param + "%"),
              criteriaBuilder.like(transmissionJoin.get("transmission"), "%" + param + "%"),
              criteriaBuilder.like(fuelTypeJoin.get("typeFuel"), "%" + param + "%"),
              criteriaBuilder.like(typeJoin.get("type"), "%" + param + "%"),
              criteriaBuilder.like(root.get("partCode").as(String.class), "%" + param + "%"),
              criteriaBuilder.like(directionJoin.get("direction"), "%" + param + "%"),
              criteriaBuilder.like(sideJoin.get("side"), "%" + param + "%")
          )).toList());
    }

    if (Optional.ofNullable(searchParamsDto.getBrand()).isPresent() && !searchParamsDto.getBrand()
        .equals("Бренд автомобиля")) {
      predicates.add(
          criteriaBuilder.equal(carJoin.join("brand").get("brand"), searchParamsDto.getBrand()));
    }
    if (Optional.ofNullable(searchParamsDto.getModel()).isPresent() && !searchParamsDto.getModel()
        .equals("Модель автомобиля")) {
      predicates.add(criteriaBuilder.equal(modelJoin.get("model"), searchParamsDto.getModel()));
    }
    if (Optional.ofNullable(searchParamsDto.getFuelType()).isPresent()
        && !searchParamsDto.getFuelType().equals("Тип топлива")) {
      predicates.add(
          criteriaBuilder.equal(fuelTypeJoin.get("typeFuel"), searchParamsDto.getFuelType()));
    }
    if (Optional.ofNullable(searchParamsDto.getType()).isPresent() && !searchParamsDto.getType()
        .equals("Тип запчасти")) {
      predicates.add(criteriaBuilder.equal(typeJoin.get("type"), searchParamsDto.getType()));
    }
    if (Optional.ofNullable(searchParamsDto.getDirection()).isPresent()
        && !searchParamsDto.getDirection().equals("Направление запчасти")) {
      predicates.add(
          criteriaBuilder.equal(directionJoin.get("direction"), searchParamsDto.getDirection()));
    }
    if (Optional.ofNullable(searchParamsDto.getSide()).isPresent() && !searchParamsDto.getSide()
        .equals("Сторона")) {
      predicates.add(criteriaBuilder.equal(sideJoin.get("side"), searchParamsDto.getSide()));
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }
}