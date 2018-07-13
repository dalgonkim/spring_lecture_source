package com.nonage.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class CustomAuthenticationFailureHandler 
	implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException auth)
			throws IOException, ServletException {
		
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
	
		HttpSession session=request.getSession();
		session.setAttribute("id", id);
		
		response.sendRedirect("/member/loginForm.do");
	}

}







