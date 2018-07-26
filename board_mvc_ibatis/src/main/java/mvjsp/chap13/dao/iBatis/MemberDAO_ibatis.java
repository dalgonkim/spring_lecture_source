package mvjsp.chap13.dao.iBatis;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.dao.MemberDAO;
import mvjsp.chap13.model.Member;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDAO_ibatis extends MemberDAO{

	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}
	
	
	@Override
	public void updateMember( Member member) throws SQLException {
		client.update("updateMember",member);
		
	}

	@Override
	public int selectCount() throws SQLException {
		int result=(Integer)client.queryForObject("selectCount");
		return result;
	}

	@Override
	public void insertMember( Member member) throws SQLException {
		client.update("insertMember",member);
	}

	@Override
	public Member getMember( String id) throws SQLException {
		Member member=(Member)client.queryForObject("getMember",id);
		return member;
	}

	@Override
	public List getMemberList( int firstRow, int endRow)
			throws SQLException {
		
		int startRow=firstRow-1;
		int counts=endRow-firstRow+1;
		
		List<Member> memberList		
		=(List<Member>)client.queryForList("getMemberList",null,startRow,counts);
		
		return memberList;
	}

	@Override
	public void deleteMember( String id) throws SQLException {
		client.update("deleteMember",id);
		
	}

}






