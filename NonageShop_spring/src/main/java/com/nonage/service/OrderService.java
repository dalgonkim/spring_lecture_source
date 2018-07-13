package com.nonage.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nonage.dao.CartDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.iBatis.CartDAO_iBatis;
import com.nonage.dao.iBatis.OrderDAO_iBatis;
import com.nonage.dto.CartVO;
import com.nonage.dto.OrderVO;

public class OrderService {

	private CartDAO cartDAO;
	private OrderDAO orderDAO;

	public void setCartDAO(CartDAO cartDAO) {
		this.cartDAO = cartDAO;
	}

	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	public Map<String, Object> getCartList(String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			ArrayList<CartVO> cartList = cartDAO.listCart(id);
			int totalPrice = 0;
			for (CartVO cartVO : cartList) {
				totalPrice += cartVO.getPrice2() * cartVO.getQuantity();
			}
			resultMap.put("cartList", cartList);
			resultMap.put("totalPrice", totalPrice);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	public void insertCart(CartVO cartVO) {
		try {
			cartDAO.insertCart(cartVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCart(String[] cseqArr) {
		for (String cseq : cseqArr) {
			try {
				cartDAO.deleteCart(Integer.parseInt(cseq));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int insertOrder(String[] cseqArr, String id) {
		int maxOseq = 0;
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {

			for (String cseq : cseqArr) {
				CartVO cartVO = cartDAO.getCart(cseq);
				cartList.add(cartVO);
			}
			maxOseq = orderDAO.insertOrder(cartList, id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxOseq;
	}

	public ArrayList<OrderVO> getOrderList(String key){
		ArrayList<OrderVO> orderList=null;
		try {
			orderList = orderDAO.listOrder(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}
	public Map<String, Object> getOrderListById(int oseq, String id) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ArrayList<OrderVO> orderList = null;
		int totalPrice = 0;
		try {
			orderList = orderDAO.listOrderById(id, "1", oseq);

			for (OrderVO orderVO : orderList) {
				totalPrice += orderVO.getPrice2() * orderVO.getQuantity();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resultMap.put("orderList", orderList);
		resultMap.put("totalPrice", totalPrice);

		return resultMap;
	}

	public Map<String, Object> getOrderDetail(int oseq, String id) {
		Map<String,Object> resultMap=new HashMap<String,Object>();
		ArrayList<OrderVO> orderList = null;
		try {
			orderList = orderDAO.listOrderById(id, "%", oseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int totalPrice = 0;
		for (OrderVO ovo : orderList) {
			totalPrice += ovo.getPrice2() * ovo.getQuantity();
		}
		resultMap.put("orderDetail", orderList.get(0));
		resultMap.put("orderList", orderList);
		resultMap.put("totalPrice", totalPrice);
		
		return resultMap;
	}

	public int insertDirectOrder(ArrayList<CartVO> cartList,String id){
		int maxOseq =0;
		try {				 
			maxOseq = orderDAO.insertOrder(cartList,id);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return maxOseq;
	}	
	
	public void insertAdminOrder(String[] resultArr){
		for (String oseq : resultArr) {			
			try {
				orderDAO.updateOrderResult(oseq);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Map<String,Object> getOrderAllById(String loginID){
		Map<String,Object> resultMap=new HashMap<String,Object>();
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();

		try {
			ArrayList<Integer> oseqList = orderDAO.selectSeqOrderTotal(loginID);
			for (int oseq : oseqList) {
				ArrayList<OrderVO> orderListIng = orderDAO.listOrderById(loginID, "%", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");
				System.out.println(orderVO.getIndate());

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderList.add(orderVO);
			}
			resultMap.put("title", "총 주문 내역");
			resultMap.put("orderList", orderList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return resultMap;
	}
}
