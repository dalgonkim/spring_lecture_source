package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.domain.MemberRegistRequest;
import com.test.validation.MemberRegistValidator;

@Controller
@RequestMapping("/regist")
public class RegistrationController {
	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public String form(@ModelAttribute("memberInfo")MemberRegistRequest memRegReq){
		String url="member/joinForm";
		
		return url;
	}
	
	@RequestMapping(value="/join",method=RequestMethod.POST)
	public String regist(@ModelAttribute("memberInfo")MemberRegistRequest memRegReq,
						BindingResult bindingResult){
		String url="member/registered";
		
		new MemberRegistValidator().validate(memRegReq,bindingResult);
		if(bindingResult.hasErrors()){
			url="member/joinForm";			
		}else{
			//서비스 호출....
		}
		return url;
	}
	
}






