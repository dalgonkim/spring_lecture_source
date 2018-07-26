package com.test.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mc/simple")
public class SimpleConverterController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String simpleForm(){

		return "inputForm";
	}
	
	@RequestMapping(method=RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String simple(@RequestBody String data,HttpServletResponse response){
		
		return "<h1>안녕하세요...</h1>";
	}
}





