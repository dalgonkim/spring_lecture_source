package com.nonage.dao.iBatis;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.MemberDAO;
import com.nonage.dto.MemberVO;

public class MemberDAO_iBatis implements MemberDAO {
	private SqlMapClient client;
	public void setClient(SqlMapClient client) {
		this.client = client;
	}

	@Override
	public int confirmID(String userid) throws SQLException {
		int result = -1;
		MemberVO member = (MemberVO) client.queryForObject("confirmID", userid);
		if (member != null) {
			result = 1;
		} else {
			result = -1;
		}
		return result;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = (MemberVO) client.queryForObject("getMember", id);
		return member;
	}

	@Override
	public int insertMember(MemberVO memberVO) throws SQLException {
		int result = -1;
		if (client.insert("insertMember", memberVO) != null) {
			result=-1;
		} else {
			result=1;
		}
		return result;
	}
	
	@Override
	public int insertAuthority(MemberVO memberVO)throws SQLException{
		int result=0;
		result=client.update("insertAuthority",memberVO);
		return result;				
	}

	@Override
	public ArrayList<MemberVO> listMember(String member_name)
			throws SQLException {
		if (member_name == "") {
			member_name= "%";
		}
		ArrayList<MemberVO> memberList =(ArrayList<MemberVO>)client.queryForList("listMember",member_name);		
		return memberList;
	}

}
