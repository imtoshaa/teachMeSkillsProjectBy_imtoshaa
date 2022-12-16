package by.tms.partshop.services.csv.csvImpl;

import by.tms.partshop.dto.OrderDetailsDto;
import by.tms.partshop.dto.converter.OrderDetailsConverter;
import by.tms.partshop.entities.Order;
import by.tms.partshop.entities.User;
import by.tms.partshop.repositories.OrderDetailsRepository;
import by.tms.partshop.repositories.OrderRepository;
import by.tms.partshop.repositories.UserRepository;
import by.tms.partshop.services.csv.ICsvWriterService;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CsvWriterServiceImpl implements ICsvWriterService {

  private final OrderDetailsRepository orderDetailsRepository;
  private final OrderRepository orderRepository;
  private final UserRepository userRepository;
  private final OrderDetailsConverter orderDetailsConverter;

  @Override
  public Writer getWriter(HttpServletResponse response, String fileName) throws IOException {
    response.setContentType("text/csv");
    response.setCharacterEncoding("UTF8");
    response.addHeader("Content-Disposition", "attachment; file=" + fileName);
    return response.getWriter();
  }

  @Override
  public void downloadAllOrders(Writer writer) {
    String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.getUserByLogin(userLogin);
    List<OrderDetailsDto> orderDetailsDto = orderDetailsRepository.getAllByOrder_User(user)
        .stream()
        .map(orderDetailsConverter::toDto)
        .toList();
    try (CSVWriter csvWriter = getCsvWriter(writer)) {

      StatefulBeanToCsv<OrderDetailsDto> beanToCsv = new StatefulBeanToCsvBuilder<OrderDetailsDto>(
          csvWriter)
          .build();

      beanToCsv.write(orderDetailsDto);
    } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException ex) {
      log.error(ex.getMessage());
    }
  }

  @Override
  public void downloadOrder(Writer writer, long orderId) {
    String userLogin = SecurityContextHolder.getContext().getAuthentication().getName();
    User user = userRepository.getUserByLogin(userLogin);
    Order order = orderRepository.getById(orderId);
    List<OrderDetailsDto> orderDetailsDto = orderDetailsRepository.getAllByOrder_UserAndOrder(user,
            order)
        .stream()
        .map(orderDetailsConverter::toDto)
        .toList();
    try (CSVWriter csvWriter = getCsvWriter(writer)) {

      StatefulBeanToCsv<OrderDetailsDto> beanToCsv = new StatefulBeanToCsvBuilder<OrderDetailsDto>(
          csvWriter)
          .build();

      beanToCsv.write(orderDetailsDto);
    } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException ex) {
      log.error(ex.getMessage());
    }
  }

  private CSVWriter getCsvWriter(Writer writer) {
    return new CSVWriter(writer,
        CSVWriter.DEFAULT_SEPARATOR,
        CSVWriter.NO_QUOTE_CHARACTER,
        CSVWriter.NO_ESCAPE_CHARACTER,
        CSVWriter.DEFAULT_LINE_END);
  }
}
