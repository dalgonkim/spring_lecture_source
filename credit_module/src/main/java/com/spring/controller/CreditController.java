package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/credit")
public class CreditController {

	
	@RequestMapping(value="/form",method=RequestMethod.GET)
	public void creditForm()throws Exception{}
}
