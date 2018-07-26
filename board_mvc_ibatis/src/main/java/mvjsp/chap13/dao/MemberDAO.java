package mvjsp.chap13.dao;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.model.Member;

public abstract class MemberDAO {
	// 회원가입, 로그인, 로그아웃....
	
	public abstract void updateMember(Member member) throws SQLException;	
	
	public abstract int selectCount() throws SQLException;	
	
	public abstract void insertMember(Member member) throws SQLException;

	public abstract Member getMember(String id) throws SQLException; 
	/*
	 * 관리자
	 */
	public abstract List getMemberList(int firstRow, int endRow)
			throws SQLException;

	public abstract void deleteMember(String id) throws SQLException;
}
