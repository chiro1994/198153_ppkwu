package com.example.validators;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = com.example.validators.NipValidator.class)
@Documented
public @interface ValidNip {

	String message() default "Nieprawidlowy NIP";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	int min() default 10;
}