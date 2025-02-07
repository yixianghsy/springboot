package hsy.com.aop.section;

import hsy.com.aop.validator.ValidNull;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NullValidator implements ConstraintValidator<ValidNull, String> {
    @Override
    public void initialize(ValidNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (null == value || "".equals(value)){
            return false;
        }
        return true;
    }
}