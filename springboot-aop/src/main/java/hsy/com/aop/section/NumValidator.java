package hsy.com.aop.section;

import hsy.com.aop.validator.ValidNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumValidator implements ConstraintValidator<ValidNum,Integer> {
    private Integer value;

    @Override
    public void initialize(ValidNum constraintAnnotation) {
        this.value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value > this.value){
            return false;
        }
        return true;
    }
}