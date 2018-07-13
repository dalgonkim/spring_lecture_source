package com.nonage.mail;

import org.springframework.mail.MailSender;

import com.nonage.dto.MemberVO;

public abstract class AbstractMailSender {	
	
	protected MailSender mailSender;		
	
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public abstract void sendMail(MemberVO member,MailRequest mail);
	
	
}
