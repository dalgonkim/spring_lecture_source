package com.nonage.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.nonage.dao.QnaDAO;
import com.nonage.dto.QnaVO;

public class QnaService {

	
	QnaDAO qnaDAO;
	
	public void setQnaDAO(QnaDAO qnaDAO){
		this.qnaDAO=qnaDAO;
	}
	
	public ArrayList<QnaVO> getQnaList(){

		ArrayList<QnaVO> qnaList = null;
		try {
			qnaList = qnaDAO.listAllQna();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaList;
	}
	
	public QnaVO getQnaDetail(String qseq){
		QnaVO qnaVO = null;
		try {
			qnaVO = qnaDAO.getQna(Integer.parseInt(qseq));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qnaVO;
	}
	
	public void updateQnaVO(QnaVO qnaVO){
		try {
			qnaDAO.updateQna(qnaVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}











