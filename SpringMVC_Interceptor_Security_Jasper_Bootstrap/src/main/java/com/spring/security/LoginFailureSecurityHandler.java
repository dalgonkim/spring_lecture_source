package com.spring.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

public class LoginFailureSecurityHandler 
		extends SimpleUrlAuthenticationFailureHandler{


	private RequestCache requestCache=
			new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
									    HttpServletResponse response,
									    AuthenticationException exception) throws IOException, ServletException {
		
		String accept = request.getHeader("accept");
		
		if(accept.indexOf("html")>-1) { //html
			request.setAttribute("msg","아이디/패스워드가 올바르지 않습니다.");
			super.onAuthenticationFailure(request, response, exception);
		}else if(accept.indexOf("json")>-1) { //ajax
			String retUrl=request.getParameter("retUrl");
			response.setContentType("application/json;charset=utf-8");
			String data="{"
					+ "\"message\":\"아이디/패스워드가 올바르지 않습니다.\","
					+ "\"retUrl\":\""+retUrl+"\","
					+ "\"error\" : true"					
					+ "}";
			PrintWriter out=response.getWriter();
			out.println(data);
			out.flush();
			out.close();
		}
		
	}
	
	
}











