package com.spring.service;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.dto.BoardVO;
import com.board.service.BoardService;
import com.board.service.BoardServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/root-context.xml")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardServiceImplTest {

	@Autowired
	private BoardService service;	
	
	@Autowired
	private BoardService serviceDB;	

	@Before
	public void init(){				
		BoardServiceImpl serviceImpl=(BoardServiceImpl)service;
		serviceImpl.setBoardDAO(new MockBoardDAOImpl());	
	}
	
	
	@Test
	public void inMemory_listAll()throws Exception{	
		List<BoardVO> boardList=service.listAll();
		boardList=service.listAll();
		boardList=service.listAll();
		boardList=service.listAll();
		Assert.assertThat(boardList.size(),Matchers.greaterThan(0));
	}	
	
	@Test
	public void RDBMS_listAll()throws Exception{
		List<BoardVO> boardList=serviceDB.listAll();
		boardList=serviceDB.listAll();
		boardList=serviceDB.listAll();
		boardList=serviceDB.listAll();
		Assert.assertThat(boardList.size(),Matchers.greaterThan(0));
	}
}







