package com.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.dto.MemberVO;

@Controller
public class CommonController {
	
	@RequestMapping(value="/main")
	public String main(){
		String url="main";
		return url;
	}
	
	@RequestMapping(value="/loginForm")
	public String loginForm(){
		String url="loginForm";
		return url;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session){
		String url="main";
		session.invalidate();
		return url;
	}
	@RequestMapping(value="/login")
	public String loginForm(@RequestParam(value="id")String id, 
			@RequestParam(value="pwd")String pwd, HttpSession session){
		MemberVO member=new MemberVO();
		
		String url="main";
		
		member.setId(id);
		member.setPwd(pwd);
		
		session.setAttribute("loginUser", member);
		
		return url;
	}
	
	@RequestMapping(value="/page1")
	public String page1(){
		String url="page1";
		return url;
	}
	@RequestMapping(value="/page2")
	public String page2(){
		String url="page2";
		return url;
	}
}
