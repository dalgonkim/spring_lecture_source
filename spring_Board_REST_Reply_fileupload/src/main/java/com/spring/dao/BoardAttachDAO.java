package com.spring.dao;

import java.sql.SQLException;
import java.util.List;

import com.spring.dto.BoardAttachVO;

public interface BoardAttachDAO {
	
	
	public void insertAttach(BoardAttachVO attach) throws SQLException;

	public void deleteAttach(String uuid) throws SQLException;

	public List<BoardAttachVO> selectAttachesByBno(int bno)throws SQLException;

	public void deleteAllAttach(int bno)throws SQLException;

}



