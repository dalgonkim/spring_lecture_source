package com.test.board.mail;

import java.sql.SQLException;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.test.board.controller.MailRequest;
import com.test.board.dto.MemberVO;
import com.test.board.service.MemberService;

public class SimpleRegistrationNotifier{	
	
	private MemberService memberSvc;
	
	private MailSender mailSender;
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setMemberSvc(MemberService memberSvc) {
		this.memberSvc = memberSvc;
	}

	public void sendMail(MailRequest mailReq) {
		SimpleMailMessage message = new SimpleMailMessage();
		for(String userid:mailReq.getUserid()){
			MemberVO member=null;
			try {
				member = memberSvc.getMember(userid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			message.setSubject(mailReq.getTitle());
			message.setFrom("보내는사람메일주소");
			message.setText(mailReq.getMessage());
			message.setTo(member.getEmail());
			System.out.println(member.getEmail());
			try {
				mailSender.send(message);
			} catch (MailException ex) {
				ex.printStackTrace();
			}
		}
	}
}
