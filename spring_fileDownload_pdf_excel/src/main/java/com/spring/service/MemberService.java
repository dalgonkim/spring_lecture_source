package com.spring.service;

import java.util.List;

import com.spring.dto.MemberVO;

public interface MemberService {
	
	List<MemberVO> getMemberList()throws Exception;
}
