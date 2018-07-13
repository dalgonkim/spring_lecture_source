package com.dokdo.dao;

import java.sql.SQLException;
import java.util.List;

import com.dokdo.dto.BoardVO;

public interface BoardDAO {
	
	List<BoardVO> getBoardList() throws SQLException;
	List<BoardVO> getBoardList(String title) throws SQLException;
	
	
	BoardVO getBoard(int pseq) throws SQLException;
	int insertBoard(BoardVO Board) throws SQLException;
	int updateBoard(BoardVO Board) throws SQLException;	
	int deleteBoard(int pseq) throws SQLException;
}
