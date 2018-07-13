package com.test.dto;

public class ProductVO {
	
	private String prod_name;
	private int prod_price=0;
	
	//멤버변수(필드) 초기화
	/*
	1. 묵시적 초기화 ('zero' 개념적 초기화)
		ex) public String str;
	2. 명시적 초기화
	    ex) public String str="abcdefg";
	3. 블럭 초기화
			public String str;
	    ex) {
	    		if(str.isEmpty()) {
	    			str="empty";
	    		}else{
	    			str="not empty";
	    		} 
	        }
	4. 생성자초기화*/	
	ProductVO(){}

	public ProductVO(String prod_name, int prod_price) {
		this.prod_name = prod_name;
		this.prod_price = prod_price;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public int getProd_price() {
		return prod_price;
	}

	public void setProd_price(int prod_price) {
		this.prod_price = prod_price;
	}
	
	
	
}











