package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.dto.ProductVO;

@Controller
public class ModelController {
	
	private static final Logger logger
	=LoggerFactory.getLogger(ModelController.class);
	
	@RequestMapping("/doD")
	public String doD(ProductVO product,Model model){
			
		logger.info("doD...................");
		
		model.addAttribute("product",product);
		
		return "productDetail";
	}
		
}










