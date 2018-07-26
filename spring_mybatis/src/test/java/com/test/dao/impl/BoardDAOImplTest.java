package com.test.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardVO;
import com.test.dao.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/test/context/dataSource-context.xml",      
								 "classpath:com/test/context/root-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class BoardDAOImplTest {
	
	@Autowired
	private BoardDAO boardDao;
	
	@Test
	public void testInsertBoard()throws Exception{
		BoardVO board=new BoardVO();
		board.setBno(13333333);
		board.setTitle("제목1");
		board.setWriter("작성자1");
		board.setContent("내용1");
		
		boardDao.insertBoard(board);	
		
		System.out.println(boardDao.selectBoardList());
		
	}
	
	@Test
	public void testSelectBoardList()throws Exception{
		System.out.println(boardDao.selectBoardList());		
	}
	
	
}









