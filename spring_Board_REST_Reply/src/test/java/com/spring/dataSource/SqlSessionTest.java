package com.spring.dataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.BoardVO;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/spring/context/dataSource-context.xml")
@TransactionConfiguration(transactionManager="transactionManager",
						  defaultRollback=false)
@Transactional
public class SqlSessionTest {
	
	@Autowired
	private SqlSession session;
	
		
	@Test
	public void board_list_search_test(){
		String searchType="t";
		String keyword="mimi";
		
		Map<String,String> paramMap = new HashMap<String,String>();
		
		paramMap.put("searchType", searchType);
		paramMap.put("keyword", keyword);
		
		List<BoardVO> boardList = 
				session.selectList("BoardMapper.selectSearchBoardList",paramMap);
		
		boolean result=true;
		
		for(BoardVO board:boardList){
			if(!board.getTitle().contains(keyword)){
				result=false;
				break;
			}
		}
		
		Assert.assertEquals(true, result);
	}
	

}






