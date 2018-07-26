package com.test.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.test.dto.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result=false;
		HttpSession session=request.getSession();
		MemberVO loginUser=(MemberVO)session.getAttribute("loginUser");
		if(loginUser!=null){
			result=true;
		}else{
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().println(
					"<script>alert('로그인이 필요합니다.')</script>");
			response.getWriter().println("<script>history.go(-1);</script>");
			result=false;
		}
		
		return result;
	}
	
	

}
