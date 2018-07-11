package com.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.board.dao.BoardDAO;
import com.board.dto.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:context/root-context.xml")
@TransactionConfiguration(transactionManager="transactionManager",
						  defaultRollback=false)
@Transactional
public class BoardDAOImplTest {

	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private BasicDataSource dataSource;
	
		
	private int getLastBno()throws Exception{
		Connection conn=dataSource.getConnection();
		Statement stmt=conn.createStatement();
		String sql="select max(bno) as bno from tbl_board";
		ResultSet rs=stmt.executeQuery(sql);
		int bno=-1;
		if(rs.next())
			bno=rs.getInt("bno");
		
		return bno;
	}
	
	
	private BoardVO insertBoard() throws Exception{
		BoardVO board=new BoardVO();		
		board.setContent("내용입니다.");
		board.setTitle("제목입니다.");
		board.setWriter("user999");
		
		dao.insertBoard(board);
		return board;
	}
	
	@Test
	@Rollback
	public void insertTest()throws Exception {
		BoardVO board=insertBoard();
		System.out.println(board);
	}
	
	@Test	
	public void readBoardByBNOTest()throws Exception{
		
		int bno=getLastBno();	
		
		BoardVO board=dao.selectBoardByBNO(bno);
		
		Assert.assertThat(board.getWriter(),Matchers.equalTo("user999"));
		Assert.assertEquals(board.getBno(), 4196);
		
	}
	
	
	@Test
	@Rollback
	public void updateTest()throws Exception{
		
		int bno=getLastBno();
		
		BoardVO board=dao.selectBoardByBNO(bno);
		
		board.setTitle("title888");
		
		dao.updateBorad(board);	
		
		board=dao.selectBoardByBNO(bno);
		
		Assert.assertThat(board.getTitle(),Matchers.equalTo("title888"));
	}
	
	@Test
	@Rollback
	public void deleteTest()throws Exception{
		int bno=getLastBno();
		dao.deleteBoard(bno);
		
		BoardVO board=dao.selectBoardByBNO(bno);

		Assert.assertEquals(board, null);
	}
	
	@Test	
	public void listTest()throws Exception{
		List<BoardVO> boardList=dao.selectBoardAll();
		
		Assert.assertThat(boardList.size(),Matchers.greaterThan(0));
	} 

}





