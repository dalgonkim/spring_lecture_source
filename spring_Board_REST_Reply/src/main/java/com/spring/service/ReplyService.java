package com.spring.service;

import java.util.List;

import com.spring.controller.Criteria;
import com.spring.dto.ReplyVO;

public interface ReplyService {
	List<ReplyVO> getReplyList(int bno)throws Exception;
	void createReply(ReplyVO reply)throws Exception;
	void modifyReply(ReplyVO reply)throws Exception;
	void deleteReply(int rno)throws Exception;
	
	List<ReplyVO> getReplyListPage(int bno,Criteria cri) throws Exception;
	int countReply(int bno) throws Exception;
}
