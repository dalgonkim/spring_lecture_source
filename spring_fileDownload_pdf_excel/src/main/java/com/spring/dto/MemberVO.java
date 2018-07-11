package com.spring.dto;

public class MemberVO {
	
	private String userid;
	private String userpwd;
	private String email;
	private String name;
	
		
	public MemberVO(String userid, String userpwd, String email, String name) {
		super();
		this.userid = userid;
		this.userpwd = userpwd;
		this.email = email;
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}
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
	
	
}




