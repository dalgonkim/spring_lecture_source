package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

//@Controller
@RequestMapping("/commons")
public class CommonsController {

	@Autowired
	private MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPOST(String id, String pwd,
							HttpSession session,
							Model model) throws Exception {
		String view = "redirect:/member/list";
		MemberVO member = service.getMemberById(id);

		if (member != null && member.getPwd().equals(pwd)) {
			session.setAttribute("loginUser", member);
			view = "redirect:/member/list";
		} else {
			model.addAttribute("id", id);
			model.addAttribute("pwd", pwd);
			model.addAttribute("msg", "아이디/패스워드 가 올바르지 않습니다.");
			view = "/commons/login";
		}

		return view;
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(HttpSession session)throws Exception{
		String view = "redirect:/commons/login";
		session.invalidate();
		return view;
	} 
}









