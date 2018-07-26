package com.nonage.controller;

import java.util.List;

import com.nonage.dto.ProductVO;

public class ProductListJson {
	
	private List<ProductVO> productList;
	
	public ProductListJson(){}
	public ProductListJson(List<ProductVO> productList){
		this.productList=productList;
	}
	
	public List<ProductVO> getProductList(){
		return this.productList;
	}
}








