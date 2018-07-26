package mvjsp.chap13.dao;

import java.sql.SQLException;
import java.util.List;

import mvjsp.chap13.model.Message;

public abstract class MessageDao {

	public abstract int insert( Message message)
			throws SQLException;

	public abstract Message select( int messageId) throws SQLException;

	public abstract int selectCount() throws SQLException;

	public abstract List<Message> selectList( int firstRow,int endRow) throws SQLException;

	public abstract int update( Message message) throws SQLException;

	public abstract int delete( int messageId) throws SQLException;
}
