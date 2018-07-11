package com.spring.interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserLogInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session= request.getSession();
		Map<String,String> loginUser=(Map)session.getAttribute("loginUser");
		
		if(loginUser==null) {return true;}
		
		String id=loginUser.get("id");
		String pwd=loginUser.get("pwd");
		
		
		String savePath="/log";
		ServletContext context=session.getServletContext();
		String logFilePath=context.getRealPath(savePath)
				           +"/userLog.txt";
		
		BufferedWriter out=
					new BufferedWriter(new FileWriter(logFilePath,true));
		
		 Date date=new Date();
		 String dateStr=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				 	    .format(date);
		 String reqURL=request.getRequestURI()
				 			  .replace(request.getContextPath(), "/");
		 String ip=request.getRemoteAddr();
		 String log=dateStr+","+id+","+reqURL+","+ip;
		 
		 out.write(log);
		 out.newLine();
		 out.close();
		
		return true;
	}
	
	
	
	

}
