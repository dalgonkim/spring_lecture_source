package com.spring.item.Mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.spring.dto.OrderVO;

public class OrderFieldSetMapper implements FieldSetMapper<OrderVO>{

	@Override
	public OrderVO mapFieldSet(FieldSet fs) throws BindException {
		
		if(fs==null){		
			return null;
		}
		
		OrderVO order=new OrderVO();
		order.setIsbn(fs.readString("isbn"));
		order.setQuantity(fs.readInt("quantity"));
		order.setPrice(fs.readDouble("price"));
		order.setCustomer(fs.readString("customer"));
		
		return order;
		
	}
	

}







