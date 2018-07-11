package com.test.di;

public class Calculator {
	
	private int x;
	private int y;
	
	
		
	public Calculator(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int sum(){
		return x+y;
	}
	
	public int sub(){
		return x-y;
	}
	
	public int multi(){
		return x*y;
	}
	
	public float div(){
		return x/(float)y;
	}
}




