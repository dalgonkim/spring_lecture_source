package com.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.dto.PlayerVO;

public class PlayerDAOImpl {

	private BasicDataSource dataSource;

	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void save(final PlayerVO player) throws Exception {
		Connection conn = dataSource.getConnection();

		String sql = "insert into player(player_id,first_name,last_name," + "position,birth_year,debut_year)"
				+ "values(?,?,?,?,?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, player.getId());
			pstmt.setString(2, player.getFirstname());
			pstmt.setString(3, player.getLastName());
			pstmt.setString(4, player.getPosition());
			pstmt.setInt(5, player.getBirthYear());
			pstmt.setInt(6, player.getDebutYear());

			pstmt.executeUpdate();
		} finally {
			pstmt.close();
			conn.close();
		}

	}
}
