package com.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.board.dto.BoardVO;
import com.board.dto.Criteria;
import com.board.dto.SearchCriteria;

public class BoardDAOImpl implements BoardDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}

	private static List<BoardVO> boardList = new ArrayList<BoardVO>();

	private static final String NAMESPACE = "BoardMapper";

	@Override
	public int selectBoardSeqNextVal() throws Exception {
		int nextval = (Integer) session.selectOne(NAMESPACE + ".selectBoardSeqNextVal");
		return nextval;
	}

	@Override
	public void insertBoard(BoardVO board) throws Exception {
		session.update(NAMESPACE + ".insertBoard", board);

	}

	@Override
	public BoardVO selectBoardByBNO(int bno) throws Exception {
		BoardVO board = (BoardVO) session.selectOne(NAMESPACE + ".selectBoardByBNO", bno);
		return board;
	}

	@Override
	public void updateBorad(BoardVO board) throws Exception {
		session.update(NAMESPACE + ".updateBoard", board);
	}

	@Override
	public void deleteBoard(int bno) throws Exception {
		session.update(NAMESPACE + ".deleteBoard", bno);

	}

	@Override
	public List<BoardVO> selectBoardAll() throws Exception {
		List<BoardVO> boardList = session.selectList(NAMESPACE + ".selectBoardAll");

		this.boardList = boardList;

		return boardList;
	}

	@Override
	public List<BoardVO> selectBoardPage(int page) throws Exception {
		if (page <= 0) {
			page = 1;
		}

		int offset = (page - 1) * 10;
		int limit = 10;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> boardList = session.selectList(NAMESPACE + ".selectBoardAll", null, rowBounds);
		return boardList;
	}

	@Override
	public int countBoard() throws Exception {
		int count = (Integer) session.selectOne(NAMESPACE + ".countBoard");
		return count;
	}

	@Override
	public List<BoardVO> selectBOardCriteria(Criteria cri) throws Exception {
		int page = cri.getPage();
		int perPageNum = cri.getPerPageNum();

		int offset = (page - 1) * perPageNum;
		int limit = perPageNum;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<BoardVO> boardList = session.selectList(NAMESPACE + ".selectBoardAll", null, rowBounds);
		return boardList;
	}

	@Override
	public List<BoardVO> selectSearchBoardList(SearchCriteria cri) throws Exception {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		int totalBoardCount = selectSearchBoardCount(cri);

		if (limit > totalBoardCount) {
			limit = totalBoardCount;
		}
		List<BoardVO> retBoardList =null;
		if (this.boardList.size() > 0) {
			List<BoardVO> boardList = new ArrayList<BoardVO>();
			searchBoardList(boardList, cri);
			 retBoardList=new ArrayList<BoardVO>();
			for (int i = offset; i < offset + limit ; i++) {
				retBoardList.add(boardList.get(i));
			}
		}
		return retBoardList;
	}

	@Override
	public int selectSearchBoardCount(SearchCriteria cri) throws Exception {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		searchBoardList(boardList, cri);

		int listCount = boardList.size();

		return listCount;
	}

	@Override
	public void increaseViewcnt(int bno) throws Exception {
		session.update(NAMESPACE + ".increaseViewCnt", bno);
	}

	@Override
	public void insertAttach(String fullname, int bno) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullname", fullname);
		paramMap.put("bno", bno);

		session.update(NAMESPACE + ".insertAttach", paramMap);

	}

	@Override
	public void deleteAttach(int bno) throws Exception {
		session.update(NAMESPACE + ".deleteAttach", bno);

	}

	@Override
	public List<String> selectAttach(int bno) throws Exception {
		List<String> files = session.selectList(NAMESPACE + ".selectAttach", bno);
		return files;
	}

	@Override
	public void replayAttach(String fullname, int bno) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullname", fullname);
		paramMap.put("bno", bno);

		session.update(NAMESPACE + ".insertAttach", paramMap);

	}

	private void searchBoardList(List<BoardVO> boardList, SearchCriteria cri) {
		for (int i = 0; i < this.boardList.size(); i++) {
			BoardVO board = this.boardList.get(i);
			switch (cri.getSearchType()) {
			case "t":
				if (board.getTitle().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			case "c":
				if (board.getContent().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			case "w":
				if (board.getWriter().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			case "tc":
				if (board.getTitle().contains(cri.getKeyword()) || board.getContent().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			case "cw":
				if (board.getWriter().contains(cri.getKeyword()) || board.getContent().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			case "tcw":
				if (board.getTitle().contains(cri.getKeyword()) || board.getContent().contains(cri.getKeyword())
						|| board.getWriter().contains(cri.getKeyword())) {
					boardList.add(board);
				}
				break;
			default:
				boardList.add(board);
			}

		}
	}

	@Override
	public List<BoardVO> adminSelectSearchBoardList(SearchCriteria cri) throws Exception {
		int offset = cri.getPageStart();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<BoardVO> boardList=
				session.selectList(NAMESPACE+".selectSearchBoardList",
						cri,rowBounds);
		return boardList;
	}

	@Override
	public int adminSelectSearchBoardCount(SearchCriteria cri) throws Exception {
		int listCount=(Integer)session.selectOne(NAMESPACE+".selectSearchBoardCount",cri);
		return listCount;
	}
}










