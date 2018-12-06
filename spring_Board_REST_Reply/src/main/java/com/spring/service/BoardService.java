package com.spring.service;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.controller.SearchCriteria;
import com.spring.dto.BoardVO;

public interface BoardService {

	public void create(BoardVO board) throws Exception;

	public BoardVO read(int bno) throws Exception;

	public BoardVO readByBno(int bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public void remove(int bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;

	public List<BoardVO> listCriteria(Criteria cri) throws Exception;

	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;

	int readSearchBoardCount(SearchCriteria cri) throws Exception;

}






