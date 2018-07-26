package com.test.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.domain.BoardVO;
import com.test.dao.BoardDAO;

public class BoardDAOImpl implements BoardDAO{
	
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	private static final String NAMESPACE="BoardMapper.";
	
	@Override
	public List<BoardVO> selectBoardList() throws SQLException {
		List<BoardVO> boardList=
				sqlSession.selectList(NAMESPACE+"selectBoardList",null);
		return boardList;
	}

	@Override
	public int insertBoard(BoardVO board) throws SQLException {
		return sqlSession.update(NAMESPACE+"insertBoard",board);		
	}

}




