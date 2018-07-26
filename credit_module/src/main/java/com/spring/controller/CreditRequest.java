package com.spring.controller;

public class CreditRequest {
	private String sndGoodName;
	private String sndAmount;
	private String sndOrdername;
	private String sndEmail;
	private String sndMobile;
	public String getSndGoodName() {
		return sndGoodName;
	}
	public void setSndGoodName(String sndGoodName) {
		this.sndGoodName = sndGoodName;
	}
	public String getSndAmount() {
		return sndAmount;
	}
	public void setSndAmount(String sndAmount) {
		this.sndAmount = sndAmount;
	}
	public String getSndOrdername() {
		return sndOrdername;
	}
	public void setSndOrdername(String sndOrdername) {
		this.sndOrdername = sndOrdername;
	}
	public String getSndEmail() {
		return sndEmail;
	}
	public void setSndEmail(String sndEmail) {
		this.sndEmail = sndEmail;
	}
	public String getSndMobile() {
		return sndMobile;
	}
	public void setSndMobile(String sndMobile) {
		this.sndMobile = sndMobile;
	}
	@Override
	public String toString() {
		return "CreditRequest [sndGoodName=" + sndGoodName + ", sndAmount=" + sndAmount + ", sndOrdername="
				+ sndOrdername + ", sndEmail=" + sndEmail + ", sndMobile=" + sndMobile + "]";
	}
	
	
	
}
