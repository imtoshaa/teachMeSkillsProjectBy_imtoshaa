package by.tms.partshop.services.basicServices.impl;

import static by.tms.partshop.util.constants.AdminConstants.ADMIN_BODIES;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_BRANDS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_CARS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_FUEL_TYPES;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_MODELS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_PARTS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_PART_TYPES;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_TRANSMISSIONS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_TYPE_DIRECTIONS;
import static by.tms.partshop.util.constants.AdminConstants.ADMIN_TYPE_SIDES;
import static by.tms.partshop.util.constants.PagesPathConstants.ADMIN_PAGE;

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
import by.tms.partshop.repositories.BodyRepository;
import by.tms.partshop.repositories.BrandRepository;
import by.tms.partshop.repositories.CarRepository;
import by.tms.partshop.repositories.FuelTypeRepository;
import by.tms.partshop.repositories.ModelRepository;
import by.tms.partshop.repositories.PartRepository;
import by.tms.partshop.repositories.PartTypeRepository;
import by.tms.partshop.repositories.SideRepository;
import by.tms.partshop.repositories.TransmissionRepository;
import by.tms.partshop.repositories.TypeDirectionRepository;
import by.tms.partshop.services.basicServices.IAdminService;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
@Service
public class AdminServiceImpl implements IAdminService {

  private final BrandRepository brandRepository;
  private final ModelRepository modelRepository;
  private final TransmissionRepository transmissionRepository;
  private final FuelTypeRepository fuelTypeRepository;
  private final BodyRepository bodyRepository;
  private final CarRepository carRepository;
  private final PartTypeRepository typeRepository;
  private final TypeDirectionRepository directionRepository;
  private final SideRepository sideRepository;
  private final PartRepository partRepository;

  @Override
  public ModelAndView openAdminPage(HttpSession session) {
    loadBrandsInSession(session);
    loadModelsInSession(session);
    loadTransmissionsInSession(session);
    loadFuelTypesInSession(session);
    loadBodiesInSession(session);
    loadCarsInSession(session);
    loadPartTypesInSession(session);
    loadTypeDirectionsInSession(session);
    loadTypeSidesInSession(session);
    loadPartsInSession(session);
    return new ModelAndView(ADMIN_PAGE);
  }

  @Override
  public void loadBrandsInSession(HttpSession session) {
    List<Brand> brands = brandRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_BRANDS)).isEmpty() ||
        !session.getAttribute(ADMIN_BRANDS).equals(brands)) {
      session.setAttribute(ADMIN_BRANDS, brands);
    }
  }
  @Override
  public void loadModelsInSession(HttpSession session) {
    List<Model> models = modelRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_MODELS)).isEmpty() ||
        !session.getAttribute(ADMIN_MODELS).equals(models)) {
      session.setAttribute(ADMIN_MODELS, models);
    }
  }
  @Override
  public void loadTransmissionsInSession(HttpSession session) {
    List<Transmission> transmissions = transmissionRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_TRANSMISSIONS)).isEmpty() ||
        !session.getAttribute(ADMIN_TRANSMISSIONS).equals(transmissions)) {
      session.setAttribute(ADMIN_TRANSMISSIONS, transmissions);
    }
  }
  @Override
  public void loadFuelTypesInSession(HttpSession session) {
    List<FuelType> fuelTypes = fuelTypeRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_FUEL_TYPES)).isEmpty() ||
        !session.getAttribute(ADMIN_FUEL_TYPES).equals(fuelTypes)) {
      session.setAttribute(ADMIN_FUEL_TYPES, fuelTypes);
    }
  }
  @Override
  public void loadBodiesInSession(HttpSession session) {
    List<Body> bodies = bodyRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_BODIES)).isEmpty() ||
        !session.getAttribute(ADMIN_BODIES).equals(bodies)) {
      session.setAttribute(ADMIN_BODIES, bodies);
    }
  }
  @Override
  public void loadCarsInSession(HttpSession session) {
    List<Car> cars = carRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_CARS)).isEmpty() ||
        !session.getAttribute(ADMIN_CARS).equals(cars)) {
      session.setAttribute(ADMIN_CARS, cars);
    }
  }
  @Override
  public void loadPartTypesInSession(HttpSession session) {
    List<Type> types = typeRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_PART_TYPES)).isEmpty() ||
        !session.getAttribute(ADMIN_PART_TYPES).equals(types)) {
      session.setAttribute(ADMIN_PART_TYPES, types);
    }
  }
  @Override
  public void loadTypeDirectionsInSession(HttpSession session) {
    List<TypeDirection> directions = directionRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_TYPE_DIRECTIONS)).isEmpty() ||
        !session.getAttribute(ADMIN_TYPE_DIRECTIONS).equals(directions)) {
      session.setAttribute(ADMIN_TYPE_DIRECTIONS, directions);
    }
  }
  @Override
  public void loadTypeSidesInSession(HttpSession session) {
    List<TypeSide> sides = sideRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_TYPE_SIDES)).isEmpty() ||
        !session.getAttribute(ADMIN_TYPE_SIDES).equals(sides)) {
      session.setAttribute(ADMIN_TYPE_SIDES, sides);
    }
  }
  @Override
  public void loadPartsInSession(HttpSession session) {
    List<Part> parts = partRepository.findAll();
    if (Optional.ofNullable(session.getAttribute(ADMIN_PARTS)).isEmpty() ||
        !session.getAttribute(ADMIN_PARTS).equals(parts)) {
      session.setAttribute(ADMIN_PARTS, parts);
    }
  }
}
