package com.example.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegonValidator implements ConstraintValidator<ValidRegon, String> {

	private int min;
	private byte REGON[] = new byte[14];
	private int REGONLength;
	private boolean valid = false;

	@Override
	public void initialize(ValidRegon validEmail) {
		min = validEmail.min();
	}

	@Override
	public boolean isValid(String regon, ConstraintValidatorContext context) {
		if ((regon.length() != 9) && (regon.length() != 14)) {
			valid = false;
		} else {
			REGONLength = regon.length();
			for (int i = 0; i < REGONLength; i++) {
				REGON[i] = Byte.parseByte(regon.substring(i, i + 1));
			}
			if (checkSum()) {
				valid = true;
			} else {
				valid = false;
			}
		}
		return valid;
	}

	// for 9 digit REGON
	private boolean checkSum9() {
		int sum = 8 * REGON[0] + 9 * REGON[1] + 2 * REGON[2] + 3 * REGON[3] + 4 * REGON[4] + 5 * REGON[5] + 6 * REGON[6]
				+ 7 * REGON[7];
		sum %= 11;
		if (sum == 10) {
			sum = 0;
		}
		if (sum == REGON[8]) {
			return true;
		} else {
			return false;
		}
	}

	// for 14 digit REGON
	private boolean checkSum14() {
		int sum = 2 * REGON[0] + 4 * REGON[1] + 8 * REGON[2] + 5 * REGON[3] + 0 * REGON[4] + 9 * REGON[5] + 7 * REGON[6]
				+ 3 * REGON[7] + 6 * REGON[8] + 1 * REGON[9] + 2 * REGON[10] + 4 * REGON[11] + 8 * REGON[12];
		sum %= 11;
		if (sum == 10) {
			sum = 0;
		}
		if (sum == REGON[13]) {
			return true;
		} else {
			return false;
		}
	}

	private boolean checkSum() {
		if (REGONLength == 9) {
			return checkSum9();
		} else {
			return (checkSum9() && checkSum14());
		}
	}
}