package com.nonage.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.CartVO;
import com.nonage.dto.MemberVO;
import com.nonage.service.OrderService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	OrderService orderService;
	
	
	@RequestMapping("/cartList.do")
	public String cartList(HttpSession session, Model model)
			throws ServletException, IOException {

		String url = "mypage/cartList";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		/*if (loginUser == null) {
			url = "redirect:loginForm.do";
		} else {*/

			model.addAllAttributes(orderService.getCartList(loginUser.getId()));
		/*}*/
		return url;
	}

	@RequestMapping("/cartInsert.do")
	public String cartInsert(CartVO cartVO, HttpSession session)
			throws ServletException, IOException {
		String url = "redirect:cartList.do";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if (loginUser == null) {
			url = "redirect:loginForm.do";
		} else {
			cartVO.setId(loginUser.getId());
			orderService.insertCart(cartVO);
		}
		return url;
	}

	@RequestMapping("/cartDelete.do")
	public String cartDelete(@RequestParam("cseq") String[] cseqArr,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "redirect:cartList.do";
		orderService.deleteCart(cseqArr);
		return url;
	}
}
