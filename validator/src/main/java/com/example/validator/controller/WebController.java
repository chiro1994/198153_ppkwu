package com.example.validator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.validator.model.ResponseBody;

import static com.example.validator.validators.NipValidator.isNipValid;
import static com.example.validator.validators.PeselValidator.isPeselValid;
import static com.example.validator.validators.RegonValidator.isRegonValid;
import static com.example.validator.validators.EmailValidator.isEmailValid;
import static com.example.validator.validators.PostalCodeValidator.isPostalCodeValid;

@RestController
public class WebController {

	@RequestMapping("/nip")
	public ResponseBody checkNip(@RequestParam("nip") String nip) {
		return new ResponseBody(isNipValid(nip));
	}
	
	@RequestMapping("/pesel")
	public ResponseBody checkPesel(@RequestParam("pesel") String pesel) {
		return new ResponseBody(isPeselValid(pesel));
	}
	
	@RequestMapping("/regon")
	public ResponseBody checkRegon(@RequestParam("regon") String regon) {
		return new ResponseBody(isRegonValid(regon));
	}
	
	@RequestMapping("/code")
	public ResponseBody checkPostalCode(@RequestParam("code") String code) {
		return new ResponseBody(isPostalCodeValid(code));
	}
	
	@RequestMapping("/email")
	public ResponseBody checkEmail(@RequestParam("email") String email){
		return new ResponseBody(isEmailValid(email));
	}
	
}
