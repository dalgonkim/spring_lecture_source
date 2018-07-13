package com.dokdo.dto;

import java.sql.Timestamp;

public class BoardVO {
	private Integer board_id;	
	private String board_title;
	private String board_content;
	private Timestamp board_indate;
	
	
	public BoardVO(){}
	
	
	public BoardVO(Integer board_id, String board_title, String board_content, Timestamp board_indate) {
		this.board_id = board_id;
		this.board_title = board_title;
		this.board_content = board_content;
		this.board_indate = board_indate;
	}

	public Integer getBoard_id() {
		return board_id;
	}
	public void setBoard_id(Integer board_id) {
		this.board_id = board_id;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public Timestamp getBoard_indate() {
		return board_indate;
	}
	public void setBoard_indate(Timestamp board_indate) {
		this.board_indate = board_indate;
	}
	
	@Override
	public String toString() {
		return "BoardVO [board_id=" + board_id + ", board_title=" + board_title + ", board_content=" + board_content
				+ ", board_indate=" + board_indate + "]";
	}
	
}
