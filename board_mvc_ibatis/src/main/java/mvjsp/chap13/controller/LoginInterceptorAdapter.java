package mvjsp.chap13.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvjsp.chap13.model.Member;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptorAdapter extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		boolean result=false;
		
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session=request.getSession();
		Member member=(Member)session.getAttribute("loginUser");
		
		if(member!=null){
			result=true;
		}else{
			
			PrintWriter out=response.getWriter();
			
			out.println("<script>alert('로그인 하십시요.')</script>");
			response.sendRedirect(request.getContextPath()+"/index");
			result=false;
		}
		return result;
	}

	
}









