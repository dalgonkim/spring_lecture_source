package com.nonage.service;

import java.sql.SQLException;
import java.util.List;

import com.nonage.dto.ProductVO;


public interface ProductService {
	
	List<ProductVO> getProductList() throws SQLException;
	List<ProductVO> getProductList(String name) throws SQLException;
	ProductVO getProduct(int pseq) throws SQLException;
	int insertProduct(ProductVO Product) throws SQLException;
	int updateProduct(ProductVO Product) throws SQLException;	
	int deleteProduct(int pseq) throws SQLException;
}
