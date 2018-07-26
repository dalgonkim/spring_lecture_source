package mvjsp.chap13.service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import mvjsp.chap13.dao.MessageDao;
import mvjsp.chap13.exception.InvalidMessagePasswordException;
import mvjsp.chap13.exception.MessageNotFoundException;
import mvjsp.chap13.exception.ServiceException;
import mvjsp.chap13.model.Message;
import mvjsp.chap13.model.MessageListView;

public class MessageService {

	private MessageDao messageDao;

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	private static final int MESSAGE_COUNT_PER_PAGE = 5;

	public MessageListView getMessageList(int pageNumber)
			throws ServiceException {
		int currentPageNumber = pageNumber;
		try {

			int messageTotalCount = messageDao.selectCount();

			List<Message> messageList = null;
			int firstRow = 0;
			int endRow = 0;
			if (messageTotalCount > 0) {
				firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE - 1;
				messageList = messageDao.selectList(firstRow, endRow);
			} else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			return new MessageListView(messageList, messageTotalCount,
					currentPageNumber, MESSAGE_COUNT_PER_PAGE, firstRow, endRow);
		} catch (SQLException e) {
			throw new ServiceException("메세지 목록 구하기 실패: " + e.getMessage(), e);
		}
	}

	public void write(Message message) throws ServiceException {
		try {
			messageDao.insert(message);

		} catch (SQLException e) {
			throw new ServiceException("메세지 등록 실패: " + e.getMessage(), e);
		}
	}

	public Message getMessage(int messageId) throws SQLException {
		Message message = messageDao.select(messageId);
		return message;
	}

	public void updateMessage(Message message) throws SQLException {
		messageDao.update(message);
	}

	public void deleteMessage(int messageId, String password)
			throws ServiceException, InvalidMessagePasswordException,
			MessageNotFoundException {
		try {
			Message message = messageDao.select(messageId);
			if (message == null) {
				throw new MessageNotFoundException("메세지가 없습니다.:" + messageId);
			}
			if (!message.hasPassword()) {
				throw new InvalidMessagePasswordException();
			}
			if (!message.getPassword().equals(password)) {
				throw new InvalidMessagePasswordException();
			}
			messageDao.delete(messageId);
		} catch (SQLException ex) {
			throw new ServiceException(
					"삭제 처리 중 에러가 발생했습니다.:" + ex.getMessage(), ex);
		} catch (InvalidMessagePasswordException ex) {
			throw ex;
		} catch (MessageNotFoundException ex) {
			throw ex;
		}
	}

}
