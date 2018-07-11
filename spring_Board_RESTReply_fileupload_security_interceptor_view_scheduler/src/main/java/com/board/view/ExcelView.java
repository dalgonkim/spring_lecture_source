package com.board.view;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.board.dto.BoardVO;

public class ExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
							    	  HSSFWorkbook book, 
							    	  HttpServletRequest request,
							    	  HttpServletResponse response) throws Exception {
		
		String fileName=URLEncoder.encode("게시판리스트.xls","utf-8");
		response.setHeader("Content-Disposition", 
				"attachment;filename=\""+fileName+"\";");
		
		
		HSSFSheet sheet=createFirstSheet(book); //sheet 생성
		
		createColumnLabel(sheet);  //라벨 생성
		
		List<BoardVO> boardList=(List<BoardVO>)model.get("boardList");
		
		int rowNum=1;
		for(BoardVO board:boardList){
			createRow(sheet,board,rowNum++);  //data row 생성 
		}		
	}
	
	private HSSFSheet createFirstSheet(HSSFWorkbook book){
		HSSFSheet sheet=book.createSheet();
		book.setSheetName(0, "게시판 리스트");
		
		sheet.setColumnWidth(0, 256*10);
		sheet.setColumnWidth(1, 256*30);
		sheet.setColumnWidth(2, 256*15);
		sheet.setColumnWidth(3, 256*10);
		sheet.setColumnWidth(4, 256*7);
		
		return sheet;
	}
	
	private void createColumnLabel(HSSFSheet sheet){
		HSSFRow firstRow=sheet.createRow(0);
		HSSFCell cell=firstRow.createCell(0);
		cell.setCellValue("글번호");
		
		cell=firstRow.createCell(1);
		cell.setCellValue("제  목");
		cell=firstRow.createCell(2);
		cell.setCellValue("작성자");
		cell=firstRow.createCell(3);
		cell.setCellValue("등록일");		
		cell=firstRow.createCell(4);
		cell.setCellValue("조회수");
	}
	
	private void createRow(HSSFSheet sheet,BoardVO board,int rowNum){
		HSSFRow row=sheet.createRow(rowNum);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue(board.getBno());
		
		cell=row.createCell(1);
		cell.setCellValue(board.getTitle());
		cell=row.createCell(2);
		cell.setCellValue(board.getWriter());
		
		String dateStr=new SimpleDateFormat("yyyy-MM-dd")
							.format(board.getRegDate());		
		cell=row.createCell(3);
		cell.setCellValue(dateStr);
		cell=row.createCell(4);
		cell.setCellValue(board.getViewcnt());
		
	}
}









