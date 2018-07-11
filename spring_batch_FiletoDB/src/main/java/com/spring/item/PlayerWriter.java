package com.spring.item;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import com.spring.dao.PlayerDAOImpl;
import com.spring.dto.PlayerVO;

public class PlayerWriter implements ItemWriter<PlayerVO>{
	
	private PlayerDAOImpl dao;
	public void setDao(PlayerDAOImpl dao){
		this.dao=dao;
	}

	@Override
	public void write(List<? extends PlayerVO> players) throws Exception {
		
		for(PlayerVO player:players){
			dao.save(player);
		}
		
		System.out.println("Players Writed To DB : "+players+"\n");
	}

}




