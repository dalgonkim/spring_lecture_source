package com.spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.board.dao.BoardDAO;
import com.board.dto.BoardVO;
import com.board.dto.Criteria;
import com.board.dto.SearchCriteria;

public class MockBoardDAOImpl implements BoardDAO {	
	
	private Map<String,BoardVO> db=new HashMap<String,BoardVO>();
	
	private int board_seq=1;
	
	{
		String driverName="oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String username="spring";
		String password="spring";
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		String sql="select * from tbl_board";
		
		try {
			Class.forName(driverName);			
			conn=DriverManager.getConnection(url,username,password);
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				BoardVO board=new BoardVO();
				board.setBno(rs.getInt("bno"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("regDate"));
				board.setTitle(rs.getString("title"));
				board.setViewcnt(rs.getInt("viewcnt"));
				board.setWriter(rs.getString("writer"));
				
				db.put(board.getBno()+"", board);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

	
	@Override
	public void insertBoard(BoardVO board) throws Exception {
		if(db.containsKey(board.getBno()+"")) throw new Exception();
		board.setBno(board_seq);
		board.setViewcnt(0);
		board.setRegDate(new Date());
		
		db.put(board.getBno()+"", board);
		
		board_seq++;		
	}

	@Override
	public BoardVO selectBoardByBNO(int bno) throws Exception {
		BoardVO board=db.get(bno+"");
		Set<String> bnos=db.keySet();
		System.out.println("SET:"+bnos);
		return board;
	}

	@Override
	public void updateBorad(BoardVO board) throws Exception {
		
		BoardVO oldBoard=db.get(board.getBno()+"");
		board.setBno(oldBoard.getBno());
		board.setRegDate(oldBoard.getRegDate());
		board.setWriter(oldBoard.getWriter());
		board.setRegDate(oldBoard.getRegDate());
		
		db.put(board.getBno()+"",board);

	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		db.remove(bno+"");

	}

	@Override
	public List<BoardVO> selectBoardAll() throws Exception {
		List<BoardVO> boardList=new ArrayList<BoardVO>();
		
		Set<String> bnos=db.keySet();
		System.out.println("SET:"+bnos);
		Iterator<String> it=bnos.iterator();	
		
		while(it.hasNext()){
			BoardVO board=db.get(it.next());
			boardList.add(board);
		}		
		
		return boardList;
	}

	@Override
	public List<BoardVO> selectBoardPage(int page) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> selectBOardCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countBoard() throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectSearchBoardCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		// TODO Auto-generated method stub
		
	}

}









