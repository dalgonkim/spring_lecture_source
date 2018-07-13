package com.nonage.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;
import com.nonage.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/productList.do")
	public String adminProductList(@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "tpage", defaultValue = "1") String tpage, Model model)
			throws ServletException, IOException {

		String url = "admin/product/productList";

		model.addAttribute("key", key);
		model.addAttribute("tpage", tpage);		
		
		model.addAllAttributes(productService.getProductList(tpage, key));
		return url;
	}

	@RequestMapping(value="/productListExcel.do")
	public String productListExcel(Model model){
		// Excel 에 들어갈 내용 List (ProductVO)
		ArrayList<ProductVO> productList=productService.getProductList();		
		model.addAttribute("productList",productList); 		
		return "productListExcel"; 
	}	
	
	@RequestMapping(value="/productListPdf.do")
	public String productListPdf(Model model){
		// pdf 에 들어갈 내용 List (ProductVO)
		ArrayList<ProductVO> productList=productService.getProductList();		
		model.addAttribute("productList",productList); 
		return "productListPdf"; 
	}	
	
	@RequestMapping(value = "productDetail.do")
	public String adminProductDetail(@RequestParam("pseq") String pseq,
			@RequestParam(value = "tpage", defaultValue = "1") String tpage, Model model)
			throws ServletException, IOException {

		String url = "admin/product/productDetail";

		ProductVO productVO = productService.getProductByNum(pseq);
		
		model.addAttribute("productVO", productVO);

		String kindList[] = { "0", "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		model.addAttribute("tpage", tpage);
		int index = Integer.parseInt(productVO.getKind().trim());
		model.addAttribute("kind", kindList[index]);

		return url;
	}

	@RequestMapping(value = "productSearch.do")
	public String adminProductSearch(@RequestParam(value = "key", defaultValue = "") String key,
			@RequestParam(value = "tpage", defaultValue = "1") String tpage, Model model)
			throws ServletException, IOException {
		String url = "admin/product/productList";

		model.addAttribute("key", key);
		model.addAttribute("tpage", tpage);
		
		model.addAllAttributes(productService.getProductList(tpage, key));
		return url;
	}

	@RequestMapping("productWriteForm.do")
	public String adminProductWriteForm(Model model) throws ServletException, IOException {

		String url = "admin/product/productWrite";
		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		model.addAttribute("kindList", kindList);

		return url;
	}

	@RequestMapping("productWrite.do")
	public String adminProductWrite(
			@RequestParam(value="file",defaultValue="") MultipartFile image,
			ProductVO productVO,
			HttpServletRequest request,Model model) throws ServletException, IOException {
		String url = "redirect:productList.do";

		HttpSession session = request.getSession();

		String savePath = "resources/product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		if (!image.isEmpty()) {
			File file = new File(uploadFilePath, System.currentTimeMillis() + image.getOriginalFilename());
			image.transferTo(file);
			productVO.setImage(file.getName());
		} 

		productService.insertProduct(productVO);
		model.addAttribute("writeProduct",productVO); //interceptor 에게 전달하기 위함.
		return url;
	}

	@RequestMapping("productUpdateForm.do")
	public String adminProductUpdateForm(@RequestParam("pseq") String pseq,
			@RequestParam(value = "tpage", defaultValue = "1") String tpage, Model model)
			throws ServletException, IOException {

		String url = "admin/product/productUpdate";

		
		ProductVO productVO = productService.getProductByNum(pseq);
		
		model.addAttribute("productVO", productVO);
		model.addAttribute("tpage", tpage);

		String kindList[] = { "Heels", "Boots", "Sandals", "Slipers", "Shcakers", "Sale" };
		model.addAttribute("kindList", kindList);

		return url;
	}

	@RequestMapping("productUpdate.do")
	public String adminProductUpdate(@RequestParam("file") MultipartFile image,
			@RequestParam("nonmakeImg") String nonmakeImg, ProductVO productVO, HttpServletRequest request)
			throws ServletException, IOException {
		String url = "redirect:productList.do";

		HttpSession session = request.getSession();

		String savePath = "product_images";
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath(savePath);

		if (!image.isEmpty()) {
			File file = new File(uploadFilePath, System.currentTimeMillis() + image.getOriginalFilename());
			image.transferTo(file);

			productVO.setImage(file.getName());
		} else {
			productVO.setImage(nonmakeImg);
		}
		productService.updateProduct(productVO);

		return url;
	}
	
	@RequestMapping("/productListXml.do")
	@ResponseBody
	public ProductListXml productListXml(@RequestParam(value="tpage",defaultValue="1") String tpage,
										 @RequestParam(value="key",defaultValue="") String key){
		
		Map<String,Object> pMap=productService.getProductList(tpage, key);
		List<ProductVO> productList=productService.getProductList();
		//ProductListXml productListXml=new ProductListXml((List<ProductVO>)pMap.get("productList"));
		ProductListXml productListXml=new ProductListXml(productList);
		return productListXml;
	}
	
	@RequestMapping("/productListJson.do")
	@ResponseBody
	public ProductListJson productListJson(@RequestParam(value="tpage",defaultValue="") String tpage,
										 @RequestParam(value="key",defaultValue="") String key){
		
		Map<String,Object> pMap=productService.getProductList(tpage, key);
		List<ProductVO> productList=productService.getProductList();
		//ProductListXml productListXml=new ProductListXml((List<ProductVO>)pMap.get("productList"));
		ProductListJson productListJson=new ProductListJson(productList);
		return productListJson;
	}
}









