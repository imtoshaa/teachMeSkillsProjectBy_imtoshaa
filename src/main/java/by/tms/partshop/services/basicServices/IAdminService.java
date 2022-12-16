package by.tms.partshop.services.basicServices;

import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface IAdminService {

  ModelAndView openAdminPage(HttpSession session);

  void loadBrandsInSession(HttpSession session);

  void loadModelsInSession(HttpSession session);

  void loadTransmissionsInSession(HttpSession session);

  void loadFuelTypesInSession(HttpSession session);

  void loadBodiesInSession(HttpSession session);

  void loadCarsInSession(HttpSession session);

  void loadPartTypesInSession(HttpSession session);

  void loadTypeDirectionsInSession(HttpSession session);

  void loadTypeSidesInSession(HttpSession session);

  void loadPartsInSession(HttpSession session);
}
