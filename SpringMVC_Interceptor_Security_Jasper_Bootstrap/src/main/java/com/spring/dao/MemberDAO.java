package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;

public interface MemberDAO {
		
	//member 전체 조회
	List<MemberVO> selectMemberList()throws SQLException;
	
	//id에 의한 member
	MemberVO selectMemberById(String id)throws SQLException;
		
	// member insert
	void insertMember(MemberVO member)throws SQLException;
	
	// member update
	void updateMember(MemberVO member)throws SQLException;
	
	// member delete
	void deleteMember(String id)throws SQLException;
	
}







