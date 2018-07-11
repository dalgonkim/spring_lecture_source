package com.spring.mail;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.spring.dto.MemberVO;

public class MimeAttachmentNotifier{
	
	private JavaMailSender mailSender;
	public void setMailSender(JavaMailSender mailSender){
		this.mailSender=mailSender;
	}
	
	public void sendMail(MemberVO member,String filePath){
		MimeMessage message=mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper=
					new MimeMessageHelper(message,true,"utf-8");
			
			messageHelper.setSubject("회원가입 안내문");
			
			String htmlContent="<h2>왕대박 이벤트 행사 안내</h2>"
							   + "<ul>"
							   + "	<li>이름 : "+member.getName()+"님 가입을 환영합니다.</li>"							   
							   + "	<li>이번 기회를 놓치지마시고 바로 응모하세요.</li>"
							   + "	<li>행사 안내</li>"
							   + "	<li>아래 배너를 클릭하세요.</li>"
							   + "	<li>"
							   + "		<a href='http://dnm.actoz.com/?utm_source=naver&utm_medium=timeboard&utm_campaign=launching' >"
							   + "			<img src='cid:banner' />"
							   + "		</a>"
							   + "	</li>"
							   + "</ul>";
			messageHelper.setText(htmlContent,true);
			messageHelper.addInline("banner", new FileDataSource(filePath+"/image.jpg"));
			messageHelper.setFrom("보내는사람 이메일 주소", "운영자");
			messageHelper.setTo(new InternetAddress(member.getEmail(),"utf-8"));
			
			DataSource dataSource = new FileDataSource(filePath+"/이용약관.pdf");
			messageHelper.addAttachment(
					MimeUtility.encodeText("이용약관.pdf","utf-8","B"),dataSource);
		} catch (Exception e) {			
			e.printStackTrace();
			return;
		}		
		
		mailSender.send(message);
	}
}









