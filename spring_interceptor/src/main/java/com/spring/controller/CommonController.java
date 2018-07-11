package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping("/loginForm")
	public void loginForm(){}
	
	@RequestMapping("/login")
	public String login(String id, String pwd,
						HttpServletRequest request){
		
		HttpSession session = request.getSession();
		Map<String,String> loginUser=new HashMap<String,String>();
		loginUser.put("id", id);
		loginUser.put("pwd", pwd);
		
		session.setAttribute("loginUser",loginUser);
		
		return "redirect:/main";
	}
}








