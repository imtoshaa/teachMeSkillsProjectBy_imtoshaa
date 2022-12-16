package by.tms.partshop.exceptions;

public class UnavailableForPurchaseException extends Exception{
  public UnavailableForPurchaseException(String message) {
    super(message);
  }

  public UnavailableForPurchaseException(String message, Throwable cause) {
    super(message, cause);
  }
}
