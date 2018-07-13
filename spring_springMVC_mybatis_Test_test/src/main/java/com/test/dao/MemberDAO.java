package com.test.dao;

import java.sql.SQLException;
import java.util.List;

import com.test.dto.MemberVO;

public interface MemberDAO {
	
	String getTime()throws SQLException;
	void insertMember(MemberVO member)throws SQLException;
	
	MemberVO selectMemberByID(String userid)throws SQLException;
	List<MemberVO> selectMemberList()throws SQLException;
	

}
