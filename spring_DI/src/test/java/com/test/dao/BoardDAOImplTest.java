package com.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.test.dto.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
@TransactionConfiguration(transactionManager="transactionManager",
                          defaultRollback=false)
@Transactional
public class BoardDAOImplTest {

	@Autowired
	private BoardDAO dao;
	
	@Test
	public void listTest() throws Exception{
		
		List<BoardVO> boardList=dao.selectBoardAll();
		
		for(BoardVO board:boardList){
			System.out.println(board);
		}
	}
	
	@Test	
	public void inertTest() throws Exception{
		BoardVO board=new BoardVO();
		board.setContent("테스트입니다1");
		board.setTitle("테스트1");
		board.setWriter("test01");
		
		dao.insertBoard(board);
	}
	
	@Test
	@Rollback
	public void updateTest() throws Exception{
		
	}

}











