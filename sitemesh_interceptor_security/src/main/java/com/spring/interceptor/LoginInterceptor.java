package com.spring.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.spring.dto.MemberVO;

public class LoginInterceptor  extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result=false;
		
		HttpSession session=request.getSession();
		MemberVO member=(MemberVO)session.getAttribute("loginUser");
		if(member!=null) {
			result=true;
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>"
						+ "alert('로그인이 필요합니다.');"
						+ "location.href='/board/commons/login'"
						+ "</script>");
			result=false;
		}
		return result;
	}

	
	
}
