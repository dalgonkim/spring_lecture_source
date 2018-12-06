package com.spring.service;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.controller.SearchCriteria;
import com.spring.dao.BoardDAO;
import com.spring.dto.BoardVO;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO){
		this.boardDAO=boardDAO;
	}	
	
	@Override
	public void create(BoardVO board) throws Exception {
		boardDAO.insertBoard(board);
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board=boardDAO.selectBoardByBNO(bno);
		boardDAO.increaseViewcnt(bno);
		return board;
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws Exception {
		boardDAO.deleteBoard(bno);		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> boardList=boardDAO.selectBoardAll();
		return boardList;
	}

	@Override
	public BoardVO readByBno(int bno) throws Exception {
		BoardVO board=boardDAO.selectBoardByBNO(bno);
		return board;
	}
	
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> boardList=boardDAO.selectBoardCriteria(cri);
		return boardList;
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		List<BoardVO> boardList=boardDAO.selectSearchBoardList(cri);		
		return boardList;
	}

	@Override
	public int readSearchBoardCount(SearchCriteria cri) throws Exception {
		int count=boardDAO.selectSearchBoardCount(cri);
		return count;
	}

}

