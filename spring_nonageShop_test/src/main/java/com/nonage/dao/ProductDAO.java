package com.nonage.dao;

import java.sql.SQLException;
import java.util.List;

import com.nonage.dto.ProductVO;

public interface ProductDAO {
	
	List<ProductVO> getProductList() throws SQLException;
	List<ProductVO> getProductList(String name) throws SQLException;
	
	
	ProductVO getProduct(int pseq) throws SQLException;
	int insertProduct(ProductVO product) throws SQLException;
	int updateProduct(ProductVO product) throws SQLException;	
	int deleteProduct(int pseq) throws SQLException;
}
