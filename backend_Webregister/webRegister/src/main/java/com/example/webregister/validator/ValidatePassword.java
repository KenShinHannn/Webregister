package com.example.webregister.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = ComparePassword.class)
@Target({ElementType.TYPE_USE, TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface ValidatePassword{
    String message() default "must be 8-14 characters long, at least 1 of uppercase, lowercase, number and special characters";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

}