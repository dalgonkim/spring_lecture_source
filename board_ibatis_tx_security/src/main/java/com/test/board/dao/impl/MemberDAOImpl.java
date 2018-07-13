package com.test.board.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.test.board.dao.MemberDAO;
import com.test.board.dto.MemberVO;


public class MemberDAOImpl implements MemberDAO {

	
	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws SQLException{
		List<MemberVO> memberList 
			=(List<MemberVO>)client.queryForList("getMemberList",null); 
		return memberList;
	}

	@Override
	public List<MemberVO> getMemberList(String name) throws SQLException{
		List<MemberVO> memberList 
		=(List<MemberVO>)client.queryForList("getMemberList",name); 
	return memberList;
		
	}

	@Override
	public MemberVO getMember(String userid) throws SQLException {
		MemberVO member=(MemberVO)client.queryForObject("getMember",userid);
		return member;
	}

	@Override
	public int insertMember(MemberVO member) throws SQLException {		
		return client.update("insertMember",member);
	}

	@Override
	public int insertAuth(MemberVO member) throws SQLException {
		return client.update("insertAuth",member);
	}

	@Override
	public int updateMember(MemberVO member) throws SQLException {
		return client.update("updateMember",member);
	}

	@Override
	public int updateAuth(MemberVO member) throws SQLException {
		return client.update("updateAuth",member);
	}

	@Override
	public int deleteMember(String userid) throws SQLException {
		return client.update("deleteMember",userid);
	}

	@Override
	public int deleteAuth(String userid) throws SQLException {
		return client.update("deleteAuth",userid);
	}

}





