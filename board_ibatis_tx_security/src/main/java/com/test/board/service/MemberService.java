package com.test.board.service;

import java.sql.SQLException;
import java.util.List;

import com.test.board.dto.MemberVO;


public interface MemberService {
	
	List<MemberVO> getMemberList() throws SQLException;
	List<MemberVO> getMemberList(String name) throws SQLException;
	MemberVO getMember(String userid) throws SQLException;
	int insertMember(MemberVO member) throws SQLException;
	int updateMember(MemberVO member) throws SQLException;	
	int deleteMember(String userid) throws SQLException;
}
