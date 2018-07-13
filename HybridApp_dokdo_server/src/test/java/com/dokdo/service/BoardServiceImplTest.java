package com.dokdo.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.dokdo.dto.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:com/dokdo/context/root-context.xml",
								 "classpath:com/dokdo/context/dataSource-context.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class BoardServiceImplTest {
	
	@Autowired
	private BoardService service;
	
	private BoardVO boardVO;
	
	@Before
	public void set()throws Exception{
		boardVO=new BoardVO(1,"독도후기","너무좋았어요..",new Timestamp(new Date().getTime()));
	}
	
	@Test
	public void testBoardServiceInsertBoard()throws Exception{		
		service.insertBoard(boardVO);
		List<BoardVO> boardList=service.getBoardList();
		System.out.println(boardList);
	}


}
