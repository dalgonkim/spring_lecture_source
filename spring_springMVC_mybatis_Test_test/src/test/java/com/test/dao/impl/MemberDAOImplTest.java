package com.test.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.test.dao.MemberDAO;
import com.test.dto.MemberVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager",
			defaultRollback=false)
@Transactional
public class MemberDAOImplTest {

	@Autowired
	private MemberDAO memberDAO;
	
	@Test
	public void testGetTime()throws Exception{
		System.out.println(memberDAO.getTime());
	}
	
	@Test
	public void testSelectMemberList()throws Exception{
		System.out.println(memberDAO.selectMemberList());
	}
	
	@Test
	public void testSelectMemberByID()throws Exception{
		System.out.println(memberDAO.selectMemberByID("1"));
	}
	
	@Test
	@Rollback(true)
	public void testInsertMember()throws Exception{
		MemberVO member=new MemberVO();
		member.setEmail("user00@email");
		member.setUserid("user00");
		member.setUsername("user00");
		member.setUserpw("user00");
		
		memberDAO.insertMember(member);
		
		System.out.println(memberDAO.selectMemberByID("user00").getEmail());
		
		
	}
	
}















