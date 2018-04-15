package com.example.validator.model;

public class ResponseBody {

	boolean isValid;

	public ResponseBody(boolean isValid) {
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
