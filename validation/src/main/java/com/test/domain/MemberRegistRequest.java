package com.test.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MemberRegistRequest {
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private Date birthday;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Date getBirthday() {
		return birthday;
	}
	
	@DateTimeFormat(pattern="yyyyMMdd")
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
