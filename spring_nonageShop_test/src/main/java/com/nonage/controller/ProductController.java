package com.nonage.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nonage.dto.ProductVO;
import com.nonage.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(value="/productList",method=RequestMethod.GET)
	public String getProductList(
			@RequestParam(value="name",defaultValue="")String name,
			Model model
								)throws Exception{
		String url="product/productList";		
		List<ProductVO> productList=null;		
		productList=service.getProductList(name);		
		model.addAttribute("productList",productList);		
		return url;
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public String writeProductForm(Model model){
		String url="product/insertForm";
		String kindList[]={"Heels","Boots","Sandals","Slipers",
					"Shcakerts","Sale"};
		model.addAttribute("kindList",kindList);
		
		return url;
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String writeProduct(ProductRequest productReq,
							   HttpSession session,
							   Model model)throws Exception{
		String url="redirect:productList";
		ProductVO product=productReq.toProductVO();
		
		String savePath="resources/product_images";
		ServletContext context=session.getServletContext();
		String uploadPath=context.getRealPath(savePath);
		
		MultipartFile image=productReq.getImage();
		
		if(image.getSize()>5*1024*1024){
			return "error/exceedSizeLimited";
		}
		
		//파일 업로드
		if(!image.isEmpty()){
			if(image.getOriginalFilename().endsWith(".jpg")){
			//업로드 파일명 유지 중복불가 : 고유명_파일명 → 자료실 
			//업로드 파일명 유지X 중복불가 : 고유명 → 썸네일
			/*String fileName = 
			System.currentTimeMillis()+"_"+image.getOriginalFilename();*/
			String fileName = UUID.randomUUID()+"_"
			+image.getOriginalFilename();
			
			File file=new File(uploadPath,fileName);
			image.transferTo(file);
			product.setImage(fileName);
			}else{
				return "error/noJPGFile";
			}
		}
		
		//DBMS 넣기
		service.insertProduct(product);
		
		return url;		
	}
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.GET)
	public String updateProductForm(@RequestParam int pseq,Model model){
		String url="product/updateForm";
		ProductVO product=null;
		try {
			product=service.getProduct(pseq);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		model.addAttribute("kindList", kindList);
		model.addAttribute("product",product);
		return url;
	}
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.POST)
	public String updateProduct(ProductRequest productReq,@RequestParam("nonmakeImg") String nonmakeImg,
								HttpServletRequest request){
		String url="redirect:productList";
		
		ProductVO product=productReq.toProductVO();
		
		HttpSession session = request.getSession();

		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartFile image=productReq.getImage();
		if (!image.isEmpty()) {
			File file = new File(uploadFilePath, image.getOriginalFilename()+"$$"+System.currentTimeMillis());
			try {
				image.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			product.setImage(file.getName());
		} else {
			product.setImage(nonmakeImg);
		}
		try {
			service.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct(@RequestParam int pseq){
		String url="redirect:productList";
		try {
			service.deleteProduct(pseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/productListJson")
	@ResponseBody
	public ProductListJson productListJson(
				@RequestParam(value="key",defaultValue="")String key){    
		
		List<ProductVO> productList=null;
		try {
			productList=service.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ProductListJson(productList);
		
	}
	
	@RequestMapping("/productListXml")
	@ResponseBody
	public ProductListXml productListXml(
			@RequestParam(value="key",defaultValue="")String name){
		List<ProductVO> productList=null;
		try {
			productList=service.getProductList(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ProductListXml(productList);
	}
	
	
	@RequestMapping(value="/productListExcel")
	public String productListExcel(Model model){
		
		List<ProductVO> productList=null;
		try {
			productList=service.getProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("productList",productList);
		return "productListExcel";
		
	}
	
	@RequestMapping(value="/productListPdf")
	public String productListPdf(Model model){
		
		List<ProductVO> productList=null;
		try {
			productList=service.getProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("productList",productList);
		return "productListPdf";
		
	}
	
}












