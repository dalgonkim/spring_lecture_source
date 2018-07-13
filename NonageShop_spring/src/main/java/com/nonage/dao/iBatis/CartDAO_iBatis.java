package com.nonage.dao.iBatis;

import java.sql.SQLException;
import java.util.ArrayList;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.nonage.dao.CartDAO;
import com.nonage.dto.CartVO;

public class CartDAO_iBatis implements CartDAO {
	private SqlMapClient client;
	public void setClient(SqlMapClient client) {
		this.client = client;
	}

	@Override
	public void insertCart(CartVO cartVO) throws SQLException {
		client.insert("insertCart", cartVO);
	}

	@Override
	public ArrayList<CartVO> listCart(String userId) throws SQLException {
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		cartList = (ArrayList<CartVO>) client.queryForList("listCart", userId);
		return cartList;
	}
	
	@Override
	public CartVO getCart(String cseq) throws SQLException {
		CartVO cartVO=null;
		cartVO=(CartVO)client.queryForObject("getCart", Integer.parseInt(cseq));
		return cartVO;
	}

	@Override
	public void deleteCart(int cseq) throws SQLException {
		client.delete("deleteCart",cseq);
	}

}
