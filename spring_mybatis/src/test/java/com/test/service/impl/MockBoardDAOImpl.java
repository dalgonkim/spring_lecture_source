package com.test.service.impl;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.spring.domain.BoardVO;
import com.test.dao.BoardDAO;

public class MockBoardDAOImpl implements BoardDAO {

	@Override
	public List<BoardVO> selectBoardList() throws SQLException {
		//bno;title;content;writer;regdate;viewcnt;
		
		List<BoardVO> boardList=Arrays.asList(
				new BoardVO(1,"제목1","내용1","작성자1",new Date(),0),
				new BoardVO(2,"제목2","내용2","작성자2",new Date(),0)
				);
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO board) throws SQLException {
		return 1;
	}

}


