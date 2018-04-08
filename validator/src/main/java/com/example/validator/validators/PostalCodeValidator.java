package com.example.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PostalCodeValidator implements ConstraintValidator<ValidPostalCode, String> {

	private int min;
	private boolean valid;

	@Override
	public void initialize(ValidPostalCode validPostalCode) {
		min = validPostalCode.min();
	}

	@Override
	public boolean isValid(String postalCode, ConstraintValidatorContext context) {
		
		if(postalCode.matches("\\d{2}-\\d{3}")) {
			valid = true;
		}
		else {
			valid = false;
		}

		return valid;
	}

}