package com.nonage.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.nonage.dao.MemberDAO;
import com.nonage.dao.OrderDAO;
import com.nonage.dao.QnaDAO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.OrderVO;
import com.nonage.dto.QnaVO;

public class MemberService {
	
	private OrderDAO orderDAO;
	private QnaDAO qnaDAO;
	private MemberDAO memberDAO;
	
	public void setOrderDAO(OrderDAO orderDAO){
		this.orderDAO=orderDAO;
	}
	public void setQnaDAO(QnaDAO qnaDAO){
		this.qnaDAO=qnaDAO;
	}
	public void setMemberDAO(MemberDAO memberDAO){
		this.memberDAO=memberDAO;
	}
	
	public ArrayList<MemberVO> getMemberList(String key){
		ArrayList<MemberVO> memberList=null;
		try {
			memberList = memberDAO.listMember(key);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}
	
	public MemberVO getMember(String id){
		MemberVO member=null;
		try {
			member=memberDAO.getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
	
	
	public ArrayList<OrderVO> getOrderList(String loginID){
		ArrayList<OrderVO> orderList = new ArrayList<OrderVO>();
		ArrayList<Integer> oseqList = null;
		ArrayList<OrderVO> orderListIng = null;

		try {
			oseqList = orderDAO.selectSeqOrderIng(loginID);

			for (int oseq : oseqList) {
				orderListIng = orderDAO.listOrderById(loginID, "1", oseq);

				OrderVO orderVO = orderListIng.get(0);
				orderVO.setPname(orderVO.getPname() + " 외 " + orderListIng.size() + "건");

				int totalPrice = 0;
				for (OrderVO ovo : orderListIng) {
					totalPrice += ovo.getPrice2() * ovo.getQuantity();
				}
				orderVO.setPrice2(totalPrice);
				orderList.add(orderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderList;
	}
	
	
	public ArrayList<QnaVO> getQnaList(String loginID){
		ArrayList<QnaVO> qnaList=null;
		try {
			qnaList = qnaDAO.listQna(loginID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaList;
	}
	
	public QnaVO getQnaVO(int qseq){
		QnaVO qnaVO = null;
		try {
			qnaVO = qnaDAO.getQna(qseq);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaVO;
	}
	
	public void insertQna(QnaVO qnaVO,String id){		
		try {
			qnaDAO.insertqna(qnaVO, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}







