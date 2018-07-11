package com.test.di;

public class CalculatorResult {
	
	private Calculator cal;

	public Calculator getCal() {
		return cal;
	}

	public void setCal(Calculator cal) {
		this.cal = cal;
	}
	
	public void result(){
		System.out.println(cal.sum());
		System.out.println(cal.sub());
		System.out.println(cal.multi());
		System.out.println(cal.div());
	}
}






