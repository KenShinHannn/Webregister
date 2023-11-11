package com.example.webregister.validator;

import com.example.webregister.repositories.UserRepo;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CompareUnique implements ConstraintValidator<ValidateUnique, String> {
    @Autowired
    private UserRepo userRepo;
    private String propertyToCheck;

    @Override
    public void initialize(ValidateUnique constraintAnnotation) {
        this.propertyToCheck = constraintAnnotation.property();
    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // if  have a value is mean not unique
        if ("username".equals(propertyToCheck)) {
            return userRepo.findByUsername(value) == null;
        } else if ("email".equals(propertyToCheck)) {
            return userRepo.findByEmail(value) == null;
        }
        return true;
    }
}