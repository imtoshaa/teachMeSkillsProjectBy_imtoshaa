package by.tms.partshop.util;

import static by.tms.partshop.util.constants.PatternConstants.LOGIN_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginConstraint, String> {

  private static final Pattern pattern = Pattern.compile(LOGIN_PATTERN);

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(value);
    return matcher.matches();
  }
}
