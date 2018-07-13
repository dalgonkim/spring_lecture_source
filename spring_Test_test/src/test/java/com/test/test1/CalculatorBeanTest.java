package com.test.test1;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.test.test.Calculator;

public class CalculatorBeanTest {

	private Calculator calculator;

	@Before
	public void setup() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(
				"classpath:/application-context.xml");
		calculator = ctx.getBean(Calculator.class);
	}

	@Test
	public void sum() {
		assertThat(calculator.sum(1, 2), equalTo(3L));
	}
}
