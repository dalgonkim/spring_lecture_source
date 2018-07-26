package com.test.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.spring.domain.BoardVO;
import com.test.dao.BoardDAO;
import com.test.service.BoardService;

public class BoardServiceImpl implements BoardService{
	
	private BoardDAO boardDao;
	public void setBoardDao(BoardDAO boardDao){
		this.boardDao=boardDao;
	}
	
	@Override
	public int createBoard(BoardVO board) throws SQLException {
		return boardDao.insertBoard(board);	
	}

	@Override
	public List<BoardVO> getBoardList() throws SQLException {		
		return boardDao.selectBoardList();
	}

}
