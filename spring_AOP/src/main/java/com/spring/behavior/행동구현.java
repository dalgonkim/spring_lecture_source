package com.spring.behavior;

public class 행동구현 implements 행동{

	@Override
	public void sleeping() {
		System.out.println("잠자기");
		
	}

	@Override
	public void eatting() {
		System.out.println("먹기");		
	}

	@Override
	public void shower() {
		System.out.println("샤워하기");		
	}

	

}
