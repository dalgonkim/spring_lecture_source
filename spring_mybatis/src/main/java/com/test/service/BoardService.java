package com.test.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.domain.BoardVO;

public interface BoardService {
	
	int createBoard(BoardVO board) throws SQLException;
	List<BoardVO> getBoardList()throws SQLException;
}








