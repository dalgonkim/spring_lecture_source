package com.nonage.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.nonage.dao.ProductDAO;
import com.nonage.dto.ProductVO;


public class ProductService {	
	
	@Autowired
	private ProductDAO productDAO;	
	
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ProductVO getProductByNum(String pseq){		
		ProductVO productVO=null;		
		try {
			productVO= productDAO.getProduct(pseq);
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return productVO;
	}
	
	public ArrayList<ProductVO> getProductKind(String kind){
		ArrayList<ProductVO> productKindList=null;
		try {
			productKindList = productDAO.listKindProduct(kind);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return productKindList;
	}
	
	public Map<String,Object> getProductList(String tpage, String key){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		ArrayList<ProductVO> productList=null;
		String paging=null;
		try {
			productList = productDAO.listProduct(Integer.parseInt(tpage), key);
			paging = productDAO.pageNumber(Integer.parseInt(tpage), key);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int n = productList.size();
		resultMap.put("productList", productList);
		resultMap.put("paging", paging);
		resultMap.put("n", n);
		return resultMap;
	}
	
	public ArrayList<ProductVO> getProductList(){
		ArrayList<ProductVO> productList=null;
		try {
			productList = (ArrayList<ProductVO>) productDAO.listProduct();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return productList;
	}
	
	public void insertProduct(ProductVO productVO){
		try {
			productDAO.insertProduct(productVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void updateProduct(ProductVO productVO){
		try {
			productDAO.updateProduct(productVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}















