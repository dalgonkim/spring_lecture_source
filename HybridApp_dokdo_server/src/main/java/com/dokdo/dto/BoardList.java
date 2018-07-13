package com.dokdo.dto;

import java.util.List;

public class BoardList {
	private List<BoardVO> boardList;
	public BoardList(){}
	public BoardList(List<BoardVO> boardList) {
		super();
		this.boardList = boardList;
	}

	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
	public List<BoardVO> getBoardList() {
		return boardList;
	}
	
	
	
}
