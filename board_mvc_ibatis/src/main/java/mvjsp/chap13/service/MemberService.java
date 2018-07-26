package mvjsp.chap13.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import mvjsp.chap13.dao.MemberDAO;
import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Member;
import mvjsp.chap13.model.MemberListView;


public class MemberService {
	private static final int MEMBER_COUNT_PER_PAGE = 5;
	

	// 회원가입, 로그인, 로그아웃
	
	private MemberDAO memberDAO; 

	public void setMemberDAO(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}
	
	// 회원가입
	public void joinMember(Member member) {
		try {
			memberDAO.insertMember(member);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 로그인
	public Member loginMember(String id, String pwd) {
		Member member = null;
		try {
			member = memberDAO.getMember( id);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return member;

	}

	public Member getMember(String id) {
		Member member = null;
		try {
			member = memberDAO.getMember( id);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return member;
	}

	/*
	 * 관리자 서비스 메소드
	 */
	public MemberListView getMemberList(int pageNumber) throws ServiceException {

		int currentPageNumber = pageNumber;
		try {
			int memberTotalCount = memberDAO.selectCount();

			List<Member> memberList = null;
			int firstRow = 0;
			int endRow = 0;
			if (memberTotalCount > 0) {
				firstRow = (pageNumber - 1) * MEMBER_COUNT_PER_PAGE + 1;
				endRow = firstRow + MEMBER_COUNT_PER_PAGE - 1;
				memberList = memberDAO.getMemberList( firstRow, endRow);
			} else {
				currentPageNumber = 0;
				memberList = Collections.emptyList();
			}
			return new MemberListView(memberList, memberTotalCount,
					currentPageNumber, MEMBER_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("회원 목록 구하기 실패 ", e);
		} 
	}

	public void updateMember(Member member) throws SQLException {
		memberDAO.updateMember(member);
	}

	public void deleteMember(String id) throws SQLException {
		memberDAO.deleteMember(id);

	}

}
