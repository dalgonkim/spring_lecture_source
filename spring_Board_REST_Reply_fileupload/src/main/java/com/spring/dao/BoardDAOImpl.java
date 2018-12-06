package com.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.spring.controller.Criteria;
import com.spring.controller.SearchCriteria;
import com.spring.dto.BoardVO;

public class BoardDAOImpl implements BoardDAO {

	private SqlSession session;
	public void setSession(SqlSession session){
		this.session=session;
	}
	
	private static final String NAMESPACE="BoardMapper";
	
	@Override
	public void insertBoard(BoardVO board) throws Exception {
		session.update(NAMESPACE+".insertBoard",board);
	}

	@Override
	public BoardVO selectBoardByBNO(int bno) throws Exception {
		BoardVO board=
				(BoardVO)session.selectOne(NAMESPACE+".selectBoardByBNO",bno);
				return board;
	}

	@Override
	public void updateBoard(BoardVO board) throws Exception {
		session.update(NAMESPACE+".updateBoard",board);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		session.update(NAMESPACE+".deleteBoard",bno);
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		session.update(NAMESPACE+".increaseViewCnt",bno);

	}

	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws Exception {
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<BoardVO> boardList=
		session.selectList(NAMESPACE+".selectSearchBoardList",cri,rowBounds);
		return boardList;
	}

	@Override
	public int selectSearchBoardCount(SearchCriteria cri) throws Exception {
		
		int rowCount = 
				session.selectOne(NAMESPACE+".selectSearchBoardCount",cri);
		return rowCount;
	}

	@Override
	public List<BoardVO> selectBoardAll() throws Exception {
		List<BoardVO> boardList=
				session.selectList(NAMESPACE+".selectBoardAll");
		return boardList;
	}

	@Override
	public List<BoardVO> selectBoardCriteria(Criteria cri) throws Exception {
		
		int offset = cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		
		RowBounds bounds=new RowBounds(offset,limit);
		
		List<BoardVO> boardList=
				session.selectList(NAMESPACE+".selectBoardAll",null,bounds);
		
		return boardList;
	}

	@Override
	public int getSeqNextValue() throws Exception {		
		return session.selectOne(NAMESPACE+".getSeqNextValue");
	}

}




