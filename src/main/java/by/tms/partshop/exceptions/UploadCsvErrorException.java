package by.tms.partshop.exceptions;

public class UploadCsvErrorException extends Exception{
  public UploadCsvErrorException(String message) {
    super(message);
  }

  public UploadCsvErrorException(String message, Throwable cause) {
    super(message, cause);
  }

}
