package com.nonage.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nonage.dto.OrderVO;
import com.nonage.service.OrderService;

@Controller
@RequestMapping("/admin")
public class AdminOrderController {

	@Autowired
	OrderService orderService;

	@RequestMapping("/orderList.do")
	public String adminOrderList(@RequestParam(value = "key", defaultValue = "") String key, Model model)
			throws ServletException, IOException {

		String url = "admin/order/orderList";
		ArrayList<OrderVO> orderList = orderService.getOrderList(key);
		model.addAttribute("orderList", orderList);

		return url;
	}

	@RequestMapping("/orderSearch.do")
	public String adminOrderSearch(@RequestParam(value = "key", defaultValue = "") String key, Model model) throws ServletException, IOException {
		
		String url = "admin/order/orderList";
		ArrayList<OrderVO> orderList = orderService.getOrderList(key);		
		model.addAttribute("orderList", orderList);

		return url;
	}
	
	@RequestMapping("/orderSave.do")
	public String adminOrderSave(@RequestParam("result")String[] resultArr)
			throws ServletException, IOException {

		String url = "redirect:orderList.do";
		orderService.insertAdminOrder(resultArr);		
		return url;
	}
}
