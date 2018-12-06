package com.spring.service;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.controller.SearchCriteria;
import com.spring.dao.BoardAttachDAO;
import com.spring.dao.BoardDAO;
import com.spring.dto.BoardAttachVO;
import com.spring.dto.BoardVO;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO){
		this.boardDAO=boardDAO;
	}	
	
	private BoardAttachDAO attachDAO;
	public void setAttachDAO(BoardAttachDAO attachDAO){
		this.attachDAO=attachDAO;
	}
	
	@Override
	public void create(BoardVO board) throws Exception {
		int bno = boardDAO.getSeqNextValue();
		board.setBno(bno);		
		boardDAO.insertBoard(board);
		
		List<BoardAttachVO> attachList=board.getAttachList();
		for(BoardAttachVO attach : attachList){
			attach.setBno(bno);
			System.out.println(attach);
			attachDAO.insertAttach(attach);			
		}
		
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board=boardDAO.selectBoardByBNO(bno);
		boardDAO.increaseViewcnt(bno);
		return board;
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		int bno = board.getBno();		
		
		boardDAO.updateBoard(board);
		
		attachDAO.deleteAllAttach(bno);
		
		for(BoardAttachVO attach : board.getAttachList()){
			attach.setBno(bno);
			attachDAO.insertAttach(attach);
		}
		
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

	@Override
	public List<BoardAttachVO> getAttach(int bno) throws Exception {
		List<BoardAttachVO> attachList=attachDAO.selectAttachesByBno(bno);				
		return attachList;
	}

}

