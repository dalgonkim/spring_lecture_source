package mvjsp.chap13.dao.iBatis;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.dao.MessageDao;
import mvjsp.chap13.model.Message;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MessageDAO_ibatis extends MessageDao{

	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}	
	
	@Override
	public int insert( Message message) throws SQLException {
		int result=(Integer)client.update("insertMessage",message);
		return result;
	}

	@Override
	public Message select( int messageId) throws SQLException {
		Message message=(Message)client.queryForObject("getMessage",messageId);
		return message;
	}	

	@Override
	public int selectCount() throws SQLException {
		int result=(Integer)client.queryForObject("messageCount",null);
		return result;
	}

	@Override
	public List<Message> selectList( int firstRow, int endRow)
			throws SQLException {

		int startRow=firstRow-1;
		int counts=endRow-firstRow+1;
		
		List<Message> messageList
		=(List<Message>)client.queryForList("getMessageList",null,startRow,counts);
		return messageList;
	}

	@Override
	public int update( Message message) throws SQLException {
		int result=(Integer)client.update("updateMessage",message);
		return result;
	}

	@Override
	public int delete( int messageId) throws SQLException {
		int result=(Integer)client.update("deleteMessage",messageId);
		return result;
	}
	
}









