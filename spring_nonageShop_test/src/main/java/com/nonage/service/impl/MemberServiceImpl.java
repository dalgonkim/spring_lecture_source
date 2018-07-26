package com.nonage.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.nonage.dao.MemberDAO;
import com.nonage.dto.MemberVO;
import com.nonage.service.MemberService;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}
	
	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> memberList=memberDAO.getMemberList();
		return memberList;
	}

	@Override
	public List<MemberVO> getMemberList(String name) throws SQLException {
		List<MemberVO> memberList=memberDAO.getMemberList(name);
		return memberList;
	}

	@Override
	public MemberVO getMember(String userid) throws SQLException {		
		return (MemberVO)memberDAO.getMember(userid);
	}

	@Override
	public int insertMember(MemberVO member) throws SQLException {		
		int result=memberDAO.insertMember(member)*memberDAO.insertAuth(member);
		return result;
	}

	@Override
	public int updateMember(MemberVO member) throws SQLException {
		int result=memberDAO.updateMember(member)*memberDAO.updateAuth(member);
		return result;
	}

	@Override
	public int deleteMember(String userid) throws SQLException {
		int result=memberDAO.deleteAuth(userid)*memberDAO.deleteMember(userid);
		return result;
	}

}
