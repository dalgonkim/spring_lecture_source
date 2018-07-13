package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.dto.ProductVO;

@Controller
public class JsonController {
	
	@RequestMapping("/doJson")
	@ResponseBody
	public List<ProductVO> doJson(){
		List<ProductVO> productList=new ArrayList<ProductVO>();
		
		ProductVO product1=new ProductVO("상품1",12000);
		ProductVO product2=new ProductVO("상품2",23000);
		
		productList.add(product1);
		productList.add(product2);
		
		return productList;
	}
	
}














