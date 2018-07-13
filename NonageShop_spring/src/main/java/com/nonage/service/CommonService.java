package com.nonage.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.nonage.dao.AddressDAO;
import com.nonage.dao.MemberDAO;
import com.nonage.dao.ProductDAO;
import com.nonage.dto.AddressVO;
import com.nonage.dto.MemberVO;
import com.nonage.dto.ProductVO;

public class CommonService {
	
	private ProductDAO productDAO;
	private MemberDAO memberDAO;	
	private AddressDAO addressDAO;	
	

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	
	
	
	public Map<String, ArrayList> getNewBestList() {

		Map<String, ArrayList> result = new HashMap<String, ArrayList>();
		ArrayList<ProductVO> newProductList = null;
		ArrayList<ProductVO> bestProductList = null;
		try {
			newProductList = productDAO.listNewProduct();
			bestProductList = productDAO.listBestProduct();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		result.put("newProductList", newProductList);
		result.put("bestProductList", bestProductList);

		return result;
	}

		
	/*
	public Map<String, Object> getLogin(String id, String password) {

		int result = -1; // 아이디불일치 : -1, 패스워드 불일치 : 0, 로그인성공 : 1
		Map<String, Object> resultMap = new HashMap<String, Object>();

		MemberVO memberVO = null;
		try {
			memberVO = memberDAO.getMember(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (memberVO != null) {
			if (memberVO.getPwd().equals(password)) {
				result = 1;
			} else {
				result = 0;
			}
		} else {
			result = -1;
		}

		resultMap.put("result", result);
		resultMap.put("memberVO", memberVO);

		return resultMap;
	}
*/
	public int idCheck(String id) {
		int message = -1;
		try {
			message = memberDAO.confirmID(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	public ArrayList<AddressVO> getFindZipNum(String dong) {
		ArrayList<AddressVO> addressList=null;
		if (dong != null && dong.trim().equals("") == false) {			
			try {
				addressList = addressDAO.selectAddressByDong(dong.trim());			
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return addressList;
	}
	
	public void joinMember(MemberVO memberVO){		
		try {
			memberDAO.insertMember(memberVO);			
			if(!(memberDAO.insertAuthority(memberVO)>0)) throw new SQLException();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}





