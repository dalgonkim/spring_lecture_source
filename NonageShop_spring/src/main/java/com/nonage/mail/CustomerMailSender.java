package com.nonage.mail;

import org.springframework.mail.SimpleMailMessage;

import com.nonage.dto.MemberVO;

public class CustomerMailSender extends AbstractMailSender{

	@Override
	public void sendMail(MemberVO member, MailRequest mail) {
		SimpleMailMessage message=new SimpleMailMessage();
		
		message.setSubject(mail.getTitle());
		message.setFrom("ddd@naver.com");
		message.setText(mail.getMessage());
		message.setTo("운영자메일주소");
		mailSender.send(message);
	}
	
}
