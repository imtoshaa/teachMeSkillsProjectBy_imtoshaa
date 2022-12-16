package by.tms.partshop.util;

import static by.tms.partshop.util.constants.PatternConstants.EMAIL_PATTERN;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

  private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
