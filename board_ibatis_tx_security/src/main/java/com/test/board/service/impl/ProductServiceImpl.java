package com.test.board.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.test.board.dao.ProductDAO;
import com.test.board.dto.ProductVO;
import com.test.board.service.ProductService;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO ProductDAO;
	public void setProductDAO(ProductDAO ProductDAO){
		this.ProductDAO=ProductDAO;
	}
	
	@Override
	public List<ProductVO> getProductList() throws SQLException {
		List<ProductVO> productList=ProductDAO.getProductList();
		return productList;
	}

	@Override
	public List<ProductVO> getProductList(String name) throws SQLException {
		List<ProductVO> productList=ProductDAO.getProductList(name);
		return productList;
	}

	@Override
	public ProductVO getProduct(int pseq) throws SQLException {		
		return (ProductVO)ProductDAO.getProduct(pseq);
	}

	@Override
	public int insertProduct(ProductVO product) throws SQLException {		
		
		int result=ProductDAO.insertProduct(product);
		
		return result;
	}

	@Override
	public int updateProduct(ProductVO product) throws SQLException {
		int result=ProductDAO.updateProduct(product);
		return result;
	}

	@Override
	public int deleteProduct(int pseq) throws SQLException {
		int result=ProductDAO.deleteProduct(pseq);
		return result;
	}

}
