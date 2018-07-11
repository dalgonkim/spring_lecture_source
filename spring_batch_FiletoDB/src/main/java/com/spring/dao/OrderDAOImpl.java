package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.OrderVO;

public class OrderDAOImpl {

	private BasicDataSource dataSource;

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void save(final OrderVO order) throws Exception {

		Connection conn = dataSource.getConnection();

		String sql = "insert into orders(isbn,quantity,price,customer) " 
		           + "values(?,?,?,?)";
		PreparedStatement pstmt =null;
		try {
			pstmt= conn.prepareStatement(sql);

			pstmt.setString(1, order.getIsbn());
			pstmt.setInt(2, order.getQuantity());
			pstmt.setDouble(3, order.getPrice());
			pstmt.setString(4, order.getCustomer());

			pstmt.executeUpdate();
		} finally {
			pstmt.close();
			conn.close();
		}
	}

}
