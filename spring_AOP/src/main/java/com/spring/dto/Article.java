package com.spring.dto;

import java.util.Date;

public class Article {
	private int id;
	private String writerName;
	private String title;
	private String content;
	private Date creationTime;
	
	public Article(){}
	public Article(int id, String writerName, String title, String content, Date creationTime) {
		super();
		this.id = id;
		this.writerName = writerName;
		this.title = title;
		this.content = content;
		this.creationTime = creationTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
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
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", writerName=" + writerName + ", title=" + title + ", content=" + content
				+ ", creationTime=" + creationTime + "]";
	}
	
	
	
}



