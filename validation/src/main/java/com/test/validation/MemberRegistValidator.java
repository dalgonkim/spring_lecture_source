package com.test.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.test.domain.MemberRegistRequest;

public class MemberRegistValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		
		return MemberRegistRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberRegistRequest regReq=(MemberRegistRequest)target;
		
		//email 검사
		if (regReq.getEmail() == null || regReq.getEmail().trim().isEmpty())
			errors.rejectValue("email", "required");
		
		//name 검사.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");
		if(regReq.getPassword()!=null&&regReq.getPassword().trim().length()>0){
			if(regReq.getPassword().length()<5){
				errors.rejectValue("password","shortPassword");
			}else if(!regReq.getPassword().equals(regReq.getConfirmPassword())){
				errors.rejectValue("confirmPassword","notSame");
			}
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthday", "required");
	}

}












