package by.tms.partshop.services.csv;

import java.io.IOException;
import java.io.Writer;
import javax.servlet.http.HttpServletResponse;

public interface ICsvWriterService {

  Writer getWriter(HttpServletResponse response, String fileName) throws IOException;

  void downloadAllOrders(Writer writer);

  void downloadOrder(Writer writer, long orderId);
}
