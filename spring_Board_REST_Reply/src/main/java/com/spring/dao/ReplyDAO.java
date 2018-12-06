package com.spring.dao;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.dto.ReplyVO;

public interface ReplyDAO {
	
	List<ReplyVO> selectReplyList(int bno)throws Exception;
	void insertReply(ReplyVO reply)throws Exception;
	void updateReply(ReplyVO reply)throws Exception;
	void deleteReply(int rno)throws Exception;
	
	List<ReplyVO> selectReplyListPage(int bno,Criteria cri)	throws Exception;
	int countReply(int bno) throws Exception;
}





