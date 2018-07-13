package com.nonage.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dao.MemberDAO;
import com.nonage.dto.MemberVO;
import com.nonage.service.AdminCommonService;

@Controller
@RequestMapping("/admin")
public class AdminCommonController {

	@Autowired
	AdminCommonService adminCommonService;
	@Autowired
	MemberDAO memberDAO;

	@RequestMapping(value = "/loginForm.do")
	public String index() {
		String url = "/admin/main";
		return url;
	}

	@RequestMapping(value = "/login_success.do")
	public String adminLogin(HttpSession session, Model model) {
		String url = "redirect:/admin/productList.do";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String workerId=auth.getName();		
		session.setAttribute("workerId", workerId);
		MemberVO member=null;
		try {
			member = memberDAO.getMember(workerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}				
		session.setAttribute("loginUser", member);
		return url;
	}
	
	@RequestMapping("/accessDenied.do")
	public String adminAccessDenied(){
		return "/admin/security/accessDenied";
	}

	@RequestMapping("/logout.do")
	public String adminLogout(HttpSession session, Model model) throws ServletException, IOException {
		String url = "redirect:/admin/loginForm.do";

		if (session != null) {
			session.invalidate();
			model.addAttribute("message", "");
		}

		return url;
	}

}
