package com.spring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.MemberVO;
import com.spring.mail.MimeAttachmentNotifier;

@Controller
public class MailController {
	
	@Autowired
	private MimeAttachmentNotifier notifier;
	
	
	@RequestMapping("/mail")
	public void mailSender(MemberVO member,
						   HttpServletRequest request,
						   HttpServletResponse response) throws IOException{
		String filePath=request.getSession().getServletContext().
				getRealPath("/resources/file");
		notifier.sendMail(member,filePath);
		
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("<script>"
				+ "alert('메일을 성공적으로 보냈습니다.');"
				+ "history.go(-1);"
				+ "</script>");
	}
	
	@RequestMapping("/mailForm")
	public void mailForm(){}
}









