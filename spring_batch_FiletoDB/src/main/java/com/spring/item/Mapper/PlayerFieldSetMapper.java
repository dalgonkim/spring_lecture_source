package com.spring.item.Mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.spring.dto.PlayerVO;

public class PlayerFieldSetMapper implements FieldSetMapper<PlayerVO>{

	@Override
	public PlayerVO mapFieldSet(FieldSet fs) throws BindException {
		
		if(fs==null){
			return null;
		}
		
		PlayerVO player=new PlayerVO();
		player.setId(fs.readString("id"));
		player.setFirstname(fs.readString("firstName"));
		player.setLastName(fs.readString("lastName"));
		player.setPosition(fs.readString("position"));
		player.setBirthYear(fs.readInt("birthYear"));
		player.setDebutYear(fs.readInt("debutYear"));
		
		return player;
	}
	

}







