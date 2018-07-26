package com.nonage.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory
			.getLogger(LoginSuccessHandler.class);
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		HttpSession session = request.getSession();

		session.setAttribute("loginUser", auth.getName());

		String accept = request.getHeader("accept");
		String retUrl=request.getParameter("returl");		
		System.out.println(retUrl);
		if (StringUtils.indexOf(accept, "html") > -1) {			
			if (retUrl == null || retUrl.isEmpty()) {
				response.sendRedirect(request.getContextPath() + getDefaultTargetUrl());				
				return;
			}
			response.sendRedirect(retUrl);

		} else if (StringUtils.indexOf(accept, "xml") > -1) {
			response.setContentType("application/xml;charset=utf-8");
			String data = StringUtils.join(new String[] {
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<response>",
					"<error>false</error>", 
					"<message>로그인하였습니다.</message>",
					"<returl>",retUrl,"</returl>",
					"</response>" });
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} else if (StringUtils.indexOf(accept, "json") > -1) {
			response.setContentType("application/json;charset=utf-8");			
			String data = StringUtils.join(new String[] {
					" { \"response\" : {", " \"error\" : false , ",
								   	      " \"message\" : \"로그인하였습니다.\", ",
								   	      " \"returl\" : \"",retUrl, "\"",
								   	  "} } " });		
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		}

	}

}
