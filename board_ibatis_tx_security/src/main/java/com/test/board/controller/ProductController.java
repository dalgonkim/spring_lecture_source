package com.test.board.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.test.board.dto.ProductVO;
import com.test.board.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productSvc;
	public void setProductSvc(ProductService productService){
		this.productSvc=productService;
	}
	
	@ModelAttribute("kindList")
	public String[] kindList(){
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", 
				"Shcakers", "Sale" };
		return kindList;
	}
	
	
	
	@RequestMapping("/productList")
	public String productList(@RequestParam(value="name",defaultValue="")
								String name,Model model){
		String url="product/productList";
		List<ProductVO> productList=null;
		try {
			productList = productSvc.getProductList(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("productList",productList);
		return url;
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.GET)
	public String addProductForm(Model model){
		String url="product/insertForm";		
		return url;
	}
	
	@RequestMapping(value="/addProduct",method=RequestMethod.POST)
	public String addProduct(ProductRequest productReq,
			HttpSession session,Model model){
		String url="redirect:productList";
		
		ProductVO product=productReq.toProductVO();	

		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartFile image=productReq.getImage();
		
		if (!image.isEmpty()) {
			File file = new File(uploadFilePath, 
					image.getOriginalFilename()+"$$"+System.currentTimeMillis());
			try {
				image.transferTo(file);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage(file.getName());
		} 
		
		try {
			productSvc.insertProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/error";
			model.addAttribute("message","상품 등록에 실패했습니다.");
			model.addAttribute("url","main/index");
		}
		return url;
	}
	
	@RequestMapping("/detailProduct")
	public String detailProduct(@RequestParam int pseq,Model model){
		String url="product/detailProduct";
		ProductVO product=null;
		try {
			product = (ProductVO)productSvc.getProduct(pseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		int index = Integer.parseInt(product.getKind().trim());
		model.addAttribute("kind", kindList[index]);
		model.addAttribute("product",product);
		return url;
	}
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.GET)
	public String updateProductForm(@RequestParam int pseq,Model model){
		String url="product/updateForm";
		ProductVO product=null;
		try {
			product=productSvc.getProduct(pseq);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}		
		model.addAttribute("product",product);
		return url;
	}
	
	
	@RequestMapping(value="/updateProduct",method=RequestMethod.POST)
	public String updateProduct(ProductRequest productReq,
								@RequestParam("nonmakeImg") String nonmakeImg,
								HttpServletRequest request){
		String url="redirect:productList";
		
		ProductVO product=productReq.toProductVO();
		
		HttpSession session = request.getSession();

		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		MultipartFile image=productReq.getImage();
		if (!image.isEmpty()) {
			File file = new File(uploadFilePath, 
					image.getOriginalFilename()+"$$"+System.currentTimeMillis());
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
			productSvc.updateProduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;	
		
	}
	
	@RequestMapping("/deleteProduct")
	public String deleteProduct(@RequestParam int pseq){
		String url="redirect:productList";
		try {
			productSvc.deleteProduct(pseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
	
	@RequestMapping("/searchProduct")
	public String searchProduct(@RequestParam String key,Model model){
		String url="product/productList";
		List<ProductVO> productList=null;
		try {
			productList=productSvc.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		model.addAttribute("productList",productList);
		model.addAttribute("key",key);
		return url;
	}
	
	@RequestMapping("/productListXml")
	@ResponseBody
	public ProductListXml productListXml(@RequestParam(value="key",defaultValue="")
										  String key){
		
		List<ProductVO> productList=null;
		try {
			productList = productSvc.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProductListXml productListXml=new ProductListXml(productList);
		return productListXml;
	}
	
	@RequestMapping("/productListJson")
	@ResponseBody
	public ProductListJson productListJson(@RequestParam(value="key",defaultValue="") String key){
		
		List<ProductVO> productList=null;
		try {
			productList = productSvc.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ProductListJson productListJson=new ProductListJson(productList);
		return productListJson;
	}
	
	@RequestMapping(value="/productListExcel")
	public String productListExcel(String key,Model model){
		// Excel 에 들어갈 내용 List (ProductVO)
		List<ProductVO> productList=null;
		try {
			productList = productSvc.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		model.addAttribute("productList",productList); 		
		return "productListExcel"; 
	}	
	
	@RequestMapping(value="/productListPdf")
	public String productListPdf(String key,Model model){
		// pdf 에 들어갈 내용 List (ProductVO)
		List<ProductVO> productList=null;
		try {
			productList = productSvc.getProductList(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		model.addAttribute("productList",productList); 
		return "productListPdf"; 
	}	
}













