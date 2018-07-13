package com.test.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RedirectController {
	
	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr){
		rttr.addFlashAttribute("msg","Message with redirect"); //request
		//rttr.addAttribute("msg","Message with redirect"); //param
		return "redirect:doF";
	}
	
	@RequestMapping("/doF")
	public String doF(){
		return "doE";
	}
	
	
}






