package com.dokdo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.dokdo.dao.BoardDAO;
import com.dokdo.dto.BoardVO;
import com.ibatis.sqlmap.client.SqlMapClient;


public class BoardDAOImpl implements BoardDAO {

	
	private SqlMapClient client;
	public void setClient(SqlMapClient client){
		this.client=client;
	}
	
	@Override
	public List<BoardVO> getBoardList() throws SQLException{
		List<BoardVO> BoardList 
			=(List<BoardVO>)client.queryForList("getBoardList",null); 
		return BoardList;
	}

	@Override
	public List<BoardVO> getBoardList(String board_title) throws SQLException{
		List<BoardVO> BoardList 
		=(List<BoardVO>)client.queryForList("getBoardList",board_title); 
	return BoardList;
		
	}

	@Override
	public BoardVO getBoard(int board_id) throws SQLException {
		BoardVO Board=(BoardVO)client.queryForObject("getBoard",board_id);
		return Board;
	}

	@Override
	public int insertBoard(BoardVO Board) throws SQLException {
		int result=client.update("insertBoard",Board);		
		return result;
	}


	@Override
	public int updateBoard(BoardVO Board) throws SQLException {
		return client.update("updateBoard",Board);
	}


	@Override
	public int deleteBoard(int board_id) throws SQLException {
		return client.update("deleteBoard",board_id);
	}

}





