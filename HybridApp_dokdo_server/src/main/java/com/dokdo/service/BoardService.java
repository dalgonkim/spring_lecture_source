package com.dokdo.service;

import java.sql.SQLException;
import java.util.List;

import com.dokdo.dto.BoardVO;


public interface BoardService {
	
	List<BoardVO> getBoardList() throws SQLException;
	List<BoardVO> getBoardList(String board_title) throws SQLException;
	BoardVO getBoard(int board_id) throws SQLException;

	int insertBoard(BoardVO board) throws SQLException;
	int updateBoard(BoardVO board) throws SQLException;	
	int deleteBoard(int board_id) throws SQLException;
}
