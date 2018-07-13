package com.nonage.controller.admin;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.nonage.dto.ProductVO;


public class ProductListJson {
	
	private List<ProductVO> productList;

	public ProductListJson() {}
	public ProductListJson(List<ProductVO> productList) {
		this.productList = productList;
	}
	public List<ProductVO> getProductList() {
		return productList;
	}	

}











