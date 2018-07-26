package com.test.interceptor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.test.dto.MemberVO;

public class UserLogInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		System.out.println(request.getRequestURI());
		if (member != null) {
			try {
				String savePath = "/WEB-INF/log";
				ServletContext context = session.getServletContext();
				String logFilePath = context.getRealPath(savePath)+"/userLog.txt";
				//이어쓰기 : new FileWriter(logFilePath,true)
				//덮어쓰기 : new FileWriter(logFilePath,false)  : 기본값
				BufferedWriter out = new BufferedWriter(new FileWriter(logFilePath,true));
				
				String log=member.getId()+","+member.getPwd();
				out.write(log);
				out.newLine();
				out.close();				
			} catch (IOException e) {
				System.err.println(e); // 에러가 있다면 메시지 출력
				
			}
		}

		return true;
	}

}
