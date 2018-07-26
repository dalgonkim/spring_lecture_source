package com.test.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="",propOrder={"id","content","creationTime"})
public class GuestMessage {
	private Integer id;
	private String content;
	private Date creationTime;
	
	public GuestMessage(){}
	public GuestMessage(Integer id, String content, Date creationTime) {		
		this.id = id;
		this.content = content;
		this.creationTime = creationTime;
	}
	
	public Integer getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	
	
}
