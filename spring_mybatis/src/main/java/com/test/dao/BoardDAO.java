package com.test.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.domain.BoardVO;

public interface BoardDAO {
	
	List<BoardVO> selectBoardList()throws SQLException;
	
	int insertBoard(BoardVO board)throws SQLException;
}









