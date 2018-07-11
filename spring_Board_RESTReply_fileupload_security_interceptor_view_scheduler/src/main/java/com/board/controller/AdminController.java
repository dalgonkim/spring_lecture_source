package com.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping(value="/index")
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping(value="/login")
	public void login(){}
}







