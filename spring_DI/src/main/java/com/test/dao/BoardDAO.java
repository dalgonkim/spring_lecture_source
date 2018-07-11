package com.test.dao;

import java.util.List;

import com.test.dto.BoardVO;

public interface BoardDAO {
	
	public List<BoardVO> selectBoardAll() throws Exception;
	
	public void insertBoard(BoardVO board)throws Exception;
	
}
