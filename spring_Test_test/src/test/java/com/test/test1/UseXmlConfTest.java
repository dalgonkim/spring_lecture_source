package com.test.test1;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.test.Calculator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/application-context.xml")
//xml이 두개 이상일때 : ({"classpath:/application-context.xml","classpath:spring-context.xml"})
//지정하지 않으면 테스트 클래스와 같은 패키지에 위치한 "테스트클래스-context.xml"파일을 설정 파일로 사용.

//@WebAppConfiguration : springMVC 위한 설정에서 추가함.기준경로 "src/main/webapp"임.

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD) 
//각 테스트 메서드 실행 후 다음 테스트 메서드를 실행하기 전에 컨텍스트를 초기화 한다. 이것이 
//이것이 없으면 각 테스트 메서드에서 하나의 컨텍스트를 공유하게 된다.
public class UseXmlConfTest {
	@Autowired
	private Calculator calculator;

	@Test
	public void sum() {
		assertThat(calculator.sum(1, 2), equalTo(3L));
	}
	
	@Test
	public void sum1() {
		assertThat(calculator.sum(3, 4), equalTo(7L));
	}

	@Test
	public void sum2() {
		assertThat(calculator.sum(5, 6), equalTo(11L));
	}
	
}
