package by.tms.partshop.util;


import static by.tms.partshop.util.constants.PatternConstants.PASSWORD_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

  private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

  @Override
  public boolean isValid(String password, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(password);
    return matcher.matches();
  }
}
