package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import com.spring.dto.MemberVO;

public class MemberServiceImpl implements MemberService{

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		
		List<MemberVO> memberList=new ArrayList<MemberVO>();
		
		for(int i=0;i<10;i++){
			memberList.add(new MemberVO("userid"+i,
							            "userpwd"+i,
							            "email"+i+"@naver.com",
							            "name"+i));
		}
		return memberList;
	}

}










