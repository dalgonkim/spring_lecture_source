package com.board.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.board.dao.BoardDAO;

public class InDBInitListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent ctxEvent) {
		ApplicationContext ctx=
				new GenericXmlApplicationContext(
						"classpath:context/root-context.xml");
		BoardDAO dao=(BoardDAO)ctx.getBean("boardDAO");
		
		try {
			dao.selectBoardAll();
			System.out.println("dao.selectBoardAll();");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}




