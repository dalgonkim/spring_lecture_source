package com.nonage.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler{

	private String loginFormPath;
	public void setLoginFormPath(String loginFormPath){
		this.loginFormPath=loginFormPath;
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String accept = request.getHeader("accept");
		
		String retUrl=request.getParameter("returl");
		
		String error = "true";
		HttpSession session=request.getSession();
		String message = "로그인실패하였습니다.";
		if (StringUtils.indexOf(accept, "html") > -1) {			
			request.getRequestDispatcher(loginFormPath).forward(request,response);
				
		} else if (StringUtils.indexOf(accept, "xml") > -1) {
			response.setContentType("application/xml;charset=utf-8");
			String data = StringUtils.join(new String[] { 
					"<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<response>",
					"<error>", error, "</error>", "<message>", message, "</message>",
					"</response>" });
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
			
		} else if (StringUtils.indexOf(accept, "json") > -1) {
			response.setContentType("application/json;charset=utf-8");
			String data = StringUtils.join(new String[] { 
					" { \"response\" : {",
					" \"error\" : ", error, ", ",
					" \"message\" : \"", message, "\" ",				
					"} } " });
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		}
	}

}





