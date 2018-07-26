package com.test.service.impl;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.BoardVO;
import com.test.service.BoardService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/test/context/dataSource-context.xml",      
								 "classpath:com/test/context/root-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager",
						   defaultRollback=false)
@Transactional
public class BoardServiceImplTest {

	@Autowired
	private BoardService boardService;
	
	/*@Before
	public void setBoardDAO(){
		boardService=new BoardServiceImpl();		
		((BoardServiceImpl)boardService).setBoardDao(new MockBoardDAOImpl());
	}*/
	
	@Test
	public void testGetBoardList()throws Exception{
		System.out.println(boardService.getBoardList());
	}
	
	@Test
	@Rollback(true)
	public void testCreateBoard()throws Exception{

		BoardVO board=new BoardVO(3,"제목3","내용3","작성자3",new Date(),0);
		System.out.println(boardService.createBoard(board));
	}
}








