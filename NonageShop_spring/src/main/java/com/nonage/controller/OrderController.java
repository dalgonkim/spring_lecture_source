package com.nonage.controller;

import java.io.IOException;
import java.util.ArrayList;

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
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@RequestMapping("/orderInsert.do")
	public String orderInsert(@RequestParam("cseq") String[] cseqArr, HttpSession session)
			throws ServletException, IOException {
		String url = "redirect:orderList.do";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		int maxOseq = orderService.insertOrder(cseqArr, loginUser.getId());
		url = "redirect:orderList.do?oseq=" + maxOseq;

		return url;
	}

	@RequestMapping("/orderList.do")
	public String orderList(@RequestParam("oseq") int oseq, HttpSession session, Model model)
			throws ServletException, IOException {
		String url = "mypage/orderList";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		model.addAllAttributes(orderService.getOrderListById(oseq, loginUser.getId()));

		return url;
	}

	@RequestMapping("/orderDetail.do")
	public String orderDetail(@RequestParam("oseq") int oseq, HttpSession session, Model model)
			throws ServletException, IOException {
		String url = "mypage/orderDetail";
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		model.addAllAttributes(orderService.getOrderDetail(oseq, loginUser.getId()));

		return url;
	}

	@RequestMapping("/orderDirectInsert.do")
	public String orderDirectInsert(CartVO cartVO, HttpSession session) throws ServletException, IOException {
		String url = "redirect:orderList.do";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		cartVO.setId(loginUser.getId());
		cartList.add(cartVO);
		int maxOseq = orderService.insertDirectOrder(cartList, loginUser.getId());
		url = "redirect:orderList.do?oseq=" + maxOseq;

		return url;
	}

	@RequestMapping("/orderAll.do")
	public String orderAll(HttpSession session, Model model) throws ServletException, IOException {
		String url = "mypage/mypage";

		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		model.addAllAttributes(orderService.getOrderAllById(loginUser.getId()));
		return url;
	}
}
