package by.tms.partshop.services.basicServices;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface IImageService {

  List<String> getAllCarImagePath(long carId);

  List<String> getAllPartImagePath(long partId);

  void saveCarImages(MultipartFile file) throws Exception;
  void savePartImages(MultipartFile file) throws Exception;
}
