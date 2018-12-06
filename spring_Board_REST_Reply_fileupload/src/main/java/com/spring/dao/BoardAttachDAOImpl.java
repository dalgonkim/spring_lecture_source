package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.spring.dto.BoardAttachVO;

public class BoardAttachDAOImpl implements BoardAttachDAO {
	
	private SqlSession session;
	public void setSession(SqlSession session){
		this.session=session;
	}
	
	private String namespace;
	public void setNamespace(String namespace){
		this.namespace=namespace;
	}
	
	@Override
	public void insertAttach(BoardAttachVO attach) throws SQLException {
		session.update(namespace+".insertAttach",attach);
	}

	@Override
	public void deleteAttach(String uuid) throws SQLException {
		session.update(namespace+".deleteAttach",uuid);
	}

	@Override
	public List<BoardAttachVO> selectAttachesByBno(int bno) throws SQLException {
		List<BoardAttachVO> attachList=
				session.selectList(namespace+".selectAttachByBno",bno);
		return attachList;
	}

	@Override
	public void deleteAllAttach(int bno) throws SQLException {
		session.update(namespace+".deleteAllAttach",bno);
	}

}
