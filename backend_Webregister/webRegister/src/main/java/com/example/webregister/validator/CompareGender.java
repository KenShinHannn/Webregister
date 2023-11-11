package com.example.webregister.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompareGender implements ConstraintValidator<ValidateGender, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.equals("Male") || value.equals("Female") || value.equals("Other");
    }

}
