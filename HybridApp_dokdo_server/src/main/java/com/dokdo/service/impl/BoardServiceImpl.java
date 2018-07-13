package com.dokdo.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.dokdo.dao.BoardDAO;
import com.dokdo.dto.BoardVO;
import com.dokdo.service.BoardService;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO BoardDAO;
	public void setBoardDAO(BoardDAO BoardDAO){
		this.BoardDAO=BoardDAO;
	}
	
	@Override
	public List<BoardVO> getBoardList() throws SQLException {
		List<BoardVO> boardList=BoardDAO.getBoardList();
		return boardList;
	}

	@Override
	public List<BoardVO> getBoardList(String board_title) throws SQLException {
		List<BoardVO> boardList=BoardDAO.getBoardList(board_title);
		return boardList;
	}

	@Override
	public BoardVO getBoard(int pseq) throws SQLException {		
		return (BoardVO)BoardDAO.getBoard(pseq);
	}

	@Override
	public int insertBoard(BoardVO board) throws SQLException {		
		
		int result=BoardDAO.insertBoard(board);
		
		return result;
	}

	@Override
	public int updateBoard(BoardVO board) throws SQLException {
		int result=BoardDAO.updateBoard(board);
		return result;
	}

	@Override
	public int deleteBoard(int pseq) throws SQLException {
		int result=BoardDAO.deleteBoard(pseq);
		return result;
	}

}
