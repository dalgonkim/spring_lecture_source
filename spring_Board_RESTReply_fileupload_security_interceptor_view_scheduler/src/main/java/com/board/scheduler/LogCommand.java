package com.board.scheduler;

import java.util.Date;

public class LogCommand {
	
	private String usno;
	private int bno;
	private String userid;
	private String uri;
	private String ip;
	private Date indate;
	
	public LogCommand(){}
	public LogCommand(String usno, int bno, String userid, String uri, String ip, Date indate) {
		super();
		this.usno = usno;
		this.bno = bno;
		this.userid = userid;
		this.uri = uri;
		this.ip = ip;
		this.indate = indate;
	}
	public String getUsno() {
		return usno;
	}
	public void setUsno(String usno) {
		this.usno = usno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Override
	public String toString() {
		return "LogCommand [usno=" + usno + ", bno=" + bno + ", userid=" + userid + ", uri=" + uri + ", ip=" + ip
				+ ", indate=" + indate + "]";
	}
	
	
	
	
	
}
