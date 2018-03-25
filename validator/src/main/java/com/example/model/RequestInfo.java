package com.example.model;

import org.hibernate.validator.constraints.Email;

import com.example.validators.ValidNip;
import com.example.validators.ValidPesel;
import com.example.validators.ValidPostalCode;
import com.example.validators.ValidRegon;

public class RequestInfo {

	@Email
	private String email;
	
	@ValidNip
	private String nip;
	
	@ValidPesel
	private String pesel;
	
	@ValidRegon
	private String regon;
	
	@ValidPostalCode
	private String code;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String text) {
		this.nip = text;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getRegon() {
		return regon;
	}

	public void setRegon(String regon) {
		this.regon = regon;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "RequestInfo [email=" + email + ", nip=" + nip + ", pesel=" + pesel + ", regon=" + regon + ", code="
				+ code + "]";
	}


}
