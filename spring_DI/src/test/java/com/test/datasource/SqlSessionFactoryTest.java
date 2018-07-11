package com.test.datasource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class SqlSessionFactoryTest {

	@Autowired
	private SqlSessionFactory factory;
	
	@Test
	public void test()throws Exception {
		SqlSession session=factory.openSession();
		
		System.out.println(session);
		
		
	}

}






