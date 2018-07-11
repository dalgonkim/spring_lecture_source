package com.board.dao;

import java.util.List;

import com.board.dto.BoardVO;
import com.board.dto.Criteria;
import com.board.dto.SearchCriteria;

public interface BoardDAO {
	
	public int selectBoardSeqNextVal() throws Exception;
	public void insertBoard(BoardVO board)throws Exception;	
	public BoardVO selectBoardByBNO(int bno)throws Exception;
	public void updateBorad(BoardVO board)throws Exception;
	public void deleteBoard(int bno)throws Exception;
	public List<BoardVO> selectBoardAll()throws Exception;
	
	public List<BoardVO> selectBoardPage(int page)
								throws Exception;
	public List<BoardVO> selectBOardCriteria(Criteria cri)
								throws Exception;
	
	public int countBoard() throws Exception;
	
	List<BoardVO> selectSearchBoardList(SearchCriteria cri)
								throws Exception;
	List<BoardVO> adminSelectSearchBoardList(SearchCriteria cri)
			throws Exception;
	int selectSearchBoardCount(SearchCriteria cri)
								throws Exception;
	int adminSelectSearchBoardCount(SearchCriteria cri)
								throws Exception;
	
	void increaseViewcnt(int bno) throws Exception;
	
	
	// 파일 추가/삭제
	void insertAttach(String fullname,int bno)throws Exception;
	void deleteAttach(int bno)throws Exception;
	List<String> selectAttach(int bno)throws Exception;
	void replayAttach(String fullname,int bno)throws Exception;
	
}








