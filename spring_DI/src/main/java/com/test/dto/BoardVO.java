package com.test.dto;

import java.util.Date;

public class BoardVO {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;
	private Date regDate;
	
	
	public BoardVO(){}
	public BoardVO(int bno, String writer, String title, String content, int viewcnt, Date regDate) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.viewcnt = viewcnt;
		this.regDate = regDate;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewcnt() {
		return viewcnt;
	}
	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", writer=" + writer + ", title=" + title + ", content=" + content + ", viewcnt="
				+ viewcnt + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
}






