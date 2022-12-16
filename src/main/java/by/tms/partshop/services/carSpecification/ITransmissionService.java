package by.tms.partshop.services.carSpecification;

import by.tms.partshop.dto.TransmissionDto;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.ModelAndView;

public interface ITransmissionService {

  ModelAndView saveNewTransmission(TransmissionDto transmissionDto, HttpSession session);

  ModelAndView deleteTransmission(String deleteTransmission, HttpSession session);
}
