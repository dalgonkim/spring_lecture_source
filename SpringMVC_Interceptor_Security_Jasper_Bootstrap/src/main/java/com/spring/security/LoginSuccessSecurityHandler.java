package com.spring.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.spring.dto.MemberVO;
import com.spring.service.MemberService;

public class LoginSuccessSecurityHandler 
				extends SavedRequestAwareAuthenticationSuccessHandler{
	
	private RequestCache requestCache=
			new HttpSessionRequestCache();
	
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
		
		MemberService service=(MemberService)ctx.getBean("memberService");
		try {
			MemberVO member=service.getMemberById(successID);
			session.setAttribute("loginUser", member);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		
		String accept=request.getHeader("accept");
		if(accept.indexOf("html")>-1) {//html submit 방식
			
			super.onAuthenticationSuccess(request, response, authentication);
			
		}else if(accept.indexOf("json")>-1) { //ajax 방식
			SavedRequest savedRequest=
					requestCache.getRequest(request,response);
			
			String targetUrl=null;
			if(savedRequest != null) {
				savedRequest.getRedirectUrl();
			}
			
			String retUrl=null;
			if(targetUrl==null || targetUrl.isEmpty()) {
				if(request.getParameter("retUrl")!=null) {
					retUrl=request.getParameter("retUrl");
				}else {
					retUrl=request.getContextPath()+getDefaultTargetUrl();
				}
			}else {
				retUrl=targetUrl.substring(
						targetUrl.indexOf(request.getContextPath()));				
			}
			
			
			response.setContentType("application/json;charset=utf-8");
			String data="{"
					+ "\"message\":\"로그인하였습니다.\","
					+ "\"retUrl\":\""+retUrl+"\","
					+ "\"error\" : false"					
					+ "}";
			PrintWriter out=response.getWriter();
			out.println(data);
			out.flush();
			out.close();
		}
	}
	
	
}










