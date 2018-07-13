package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.ProductVO;
import com.nonage.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productDetail.do")
	public String productDetail(@RequestParam("pseq")String pseq,Model model)
			throws ServletException, IOException {
		
		String url="product/productDetail";			
		ProductVO productVO=productService.getProductByNum(pseq);			
		model.addAttribute("productVO", productVO);
		
		return url;
	}
	
	@RequestMapping("/catagory.do")
	public String productKind(@RequestParam("kind")String kind,Model model)
			throws ServletException, IOException {

		String url = "product/productKind";
		ArrayList<ProductVO> productKindList=productService.getProductKind(kind);
		model.addAttribute("productKindList", productKindList);

		return url;

	}
	
	
}
