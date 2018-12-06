package com.spring.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	
	
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e){		
		e.printStackTrace();
		
		ModelAndView modelnView=new ModelAndView();
		
		modelnView.setViewName("error/error_common");
		modelnView.addObject("exception",e);
		modelnView.addObject("message","Exception");
		
		return modelnView;
	}
	
}
