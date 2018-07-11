package com.spring.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.spring.dao.OrderDAOImpl;
import com.spring.dto.OrderVO;

public class OrderWriter implements ItemWriter<OrderVO>{
	
	private OrderDAOImpl dao;
	public void setDao(OrderDAOImpl dao){
		this.dao=dao;
	}
	
	@Override
	public void write(List<? extends OrderVO> orders) throws Exception {
		
		for(OrderVO order:orders){
			dao.save(order);
		}
		
		System.out.println("Orders Writed To DB : "+orders+"\n");
		
	}

}







