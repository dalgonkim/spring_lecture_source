package com.test.test2;

import java.util.Date;

import org.jboss.logging.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")

//각 테스트 메서드를 실행한 후 트랜젝션을 롤백하려면 @TransactionConfiguration 과 @Transactional 에노테이션을 붙인다.
@TransactionConfiguration
//전체 메서드가 아닌 특정 메서드에서만 롤백을 원한 다면 (defaultRollback=false)를 주고 해당 메서드에 @Rollback(true)를 준다.
@Transactional

public class MessageDaoIntTest {
	
	@Autowired
	private MessageDao messageDao;
	
	@Test
	public void counts(){
		assertThat(messageDao.counts(),equalTo(22));
	}
	
	@Test
	//@Rollback(true)
	public void insert(){
		Message message=new Message();
		message.setName("kim");
		message.setMessage("message");
		message.setCreationTime(new Date());
		int newMessageId=messageDao.insert(message);
		assertThat(newMessageId,greaterThan(0));
	}
}
