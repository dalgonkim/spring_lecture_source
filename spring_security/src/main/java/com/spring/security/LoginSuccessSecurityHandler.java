package com.spring.security;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

public class LoginSuccessSecurityHandler 
				extends SavedRequestAwareAuthenticationSuccessHandler{
	

	@Override
	public void onAuthenticationSuccess(
			HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String successID = authentication.getName();
		
		ApplicationContext ctx=
				new GenericXmlApplicationContext
				("classpath:context/root-context.xml");
		
		MemberService service=(MemberService)ctx.getBean("memerService");
		try {
			MemberVO member=service.getMemberById(successID);
			session.setAttribute("loginUser", member);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
}










