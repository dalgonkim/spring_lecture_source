package com.spring.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.board.dao.MemberDAO;
import com.board.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/*-context.xml")
@TransactionConfiguration(transactionManager="transactionManager",
                                defaultRollback=false)
@Transactional
public class MemberDAOTest {
	
	@Autowired
	private MemberDAO dao;
	
	@Test
	public void testTime()throws Exception{
		System.out.println(dao.getTime());
	}
	
	@Test
	@Rollback
	public void testInsertMember() throws Exception{
		MemberVO member=new MemberVO();
		member.setUserid("user01");
		member.setUserpw("user01");
		member.setUsername("user01");
		member.setEmail("user01@naver.com");
		
		dao.inserMember(member);
	}

}






