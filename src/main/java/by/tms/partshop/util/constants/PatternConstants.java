package by.tms.partshop.util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PatternConstants {

  public static final String LOGIN_PATTERN = "^[a-zA-Z][a-zA-Z0-9-_\\.]{5,20}$";
  public static final String EMAIL_PATTERN = "^(?=.{1,20}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]"
      + "+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$";
  public static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,100})";
}
