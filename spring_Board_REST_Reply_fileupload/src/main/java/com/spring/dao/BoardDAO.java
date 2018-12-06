package com.spring.dao;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.controller.SearchCriteria;
import com.spring.dto.BoardVO;

public interface BoardDAO {
	public void insertBoard(BoardVO board)throws Exception;
	public BoardVO selectBoardByBNO(int bno)throws Exception;
	public void updateBoard(BoardVO board)throws Exception;
	public void deleteBoard(int bno)throws Exception;
	
	void increaseViewcnt(int bno) throws Exception;
	
	List<BoardVO> selectBoardCriteria(Criteria cri)	throws Exception;
	
	List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws Exception;
	int selectSearchBoardCount(SearchCriteria cri) throws Exception;
	List<BoardVO> selectBoardAll() throws Exception;
	
	int getSeqNextValue() throws Exception;
}






