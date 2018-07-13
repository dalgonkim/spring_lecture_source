package com.spring.service;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.MemberVO;

public interface MemberService {
	
	List<MemberVO> getMemberList()throws SQLException;
	MemberVO getMemberById(String id)throws SQLException;
	void register(MemberVO member)throws SQLException;
	void modify(MemberVO member)throws SQLException;
	void remove(String id)throws SQLException;
	
}
