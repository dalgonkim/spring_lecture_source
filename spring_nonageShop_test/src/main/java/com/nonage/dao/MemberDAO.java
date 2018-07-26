package com.nonage.dao;

import java.sql.SQLException;
import java.util.List;

import com.nonage.dto.MemberVO;

public interface MemberDAO {
	
	List<MemberVO> getMemberList() throws SQLException;
	List<MemberVO> getMemberList(String name) throws SQLException;
	
	
	MemberVO getMember(String userid) throws SQLException;
	int insertMember(MemberVO member) throws SQLException;
	int insertAuth(MemberVO member) throws SQLException;
	int updateMember(MemberVO member) throws SQLException;	
	int updateAuth(MemberVO member) throws SQLException;
	int deleteMember(String userid) throws SQLException;
	int deleteAuth(String userid) throws SQLException;
}
