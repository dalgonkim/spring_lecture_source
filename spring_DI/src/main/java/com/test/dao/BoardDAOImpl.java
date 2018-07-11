package com.test.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.dto.BoardVO;


public class BoardDAOImpl implements BoardDAO {

	private SqlSession session;
	
	public void setSession(SqlSession session){
		this.session=session;
	}
	
	private static final String NAMESPACE="BoardMapper";
	
	@Override
	public List<BoardVO> selectBoardAll() throws Exception {
		List<BoardVO> boardList=
				session.selectList(NAMESPACE+".selectBoardAll", null);
		return boardList;
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		session.update(NAMESPACE+".insertBoard",board);		
	}

}





