package com.spring.item.Mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.dto.PlayerVO;

public class PlayerRowMapper implements RowMapper<PlayerVO>{

	@Override
	public PlayerVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		PlayerVO player = new PlayerVO();
		
		player.setId(rs.getString("player_id"));
		player.setFirstname(rs.getString("first_name"));
		player.setLastName(rs.getString("last_name"));
		player.setPosition(rs.getString("position"));
		player.setBirthYear(rs.getInt("birth_year"));
		player.setDebutYear(rs.getInt("debut_year"));
		
		return player;
	}
}
	
	
	