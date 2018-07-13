package com.test.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.test.dao.MemberDAO;
import com.test.dto.MemberVO;


public class MemberDAOImpl implements MemberDAO {
	

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession=sqlSession;
	}
	
	@Override
	public String getTime() throws SQLException {
		String time=(String)sqlSession.selectOne("Member-Mapper.getTime",null);
		return time;
	}

	@Override
	public void insertMember(MemberVO member) throws SQLException {
		sqlSession.update("Member-Mapper.insertMember",member);
	}

	@Override
	public MemberVO selectMemberByID(String userid) throws SQLException {
		MemberVO member=
				(MemberVO)sqlSession.selectOne("Member-Mapper.selectMemberByID",userid);
		return member;
	}

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		List<MemberVO> memberList=
				sqlSession.selectList("Member-Mapper.selectMemberList",null);
		return memberList;
	}

}









