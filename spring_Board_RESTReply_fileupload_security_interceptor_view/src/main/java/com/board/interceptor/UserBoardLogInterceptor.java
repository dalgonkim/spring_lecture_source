package com.board.interceptor;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.board.dto.MemberVO;
import com.board.util.LogFileUtils;

public class UserBoardLogInterceptor extends HandlerInterceptorAdapter{

	private String logPath="c:\\log";
	public void setLogPath(String logPath){
		this.logPath=logPath;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		Integer bno =(Integer)modelAndView.getModel().get("bno");		
		
		if(bno!=null){
			HttpSession session=request.getSession();
			MemberVO member = (MemberVO)session.getAttribute("loginUser");
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date=format.format(new Date());
			
			// bno,uri,id,ip,date
			String log=bno
					   +","+request.getRequestURI()
					   			   .replace(request.getContextPath(), "")
					   +","+member.getUserid()
					   +","+request.getRemoteAddr()
					   +","+date;
			
			LogFileUtils.writeLog(logPath, log);			
		}
		
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
	
}
