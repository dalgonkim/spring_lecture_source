package com.spring.view;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.spring.dto.MemberVO;

public class ExcelView extends AbstractExcelView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
							    	  HSSFWorkbook book, 
							    	  HttpServletRequest request,
							    	  HttpServletResponse response) throws Exception {
		
		String fileName=URLEncoder.encode("회원리스트.xls","utf-8");
		response.setHeader("Content-Disposition", 
				"attachment;filename=\""+fileName+"\";");
		
		
		HSSFSheet sheet=createFirstSheet(book); //sheet 생성
		
		createColumnLabel(sheet);  //라벨 생성
		
		List<MemberVO> memberList=(List<MemberVO>)model.get("memberList");
		
		int rowNum=1;
		for(MemberVO member:memberList){
			createRow(sheet,member,rowNum++);  //data row 생성 
		}		
	}
	
	private HSSFSheet createFirstSheet(HSSFWorkbook book){
		HSSFSheet sheet=book.createSheet();
		book.setSheetName(0, "회원리스트");
		
		sheet.setColumnWidth(0, 256*15);
		sheet.setColumnWidth(1, 256*15);
		sheet.setColumnWidth(2, 256*10);
		sheet.setColumnWidth(3, 256*30);
		
		return sheet;
	}
	
	private void createColumnLabel(HSSFSheet sheet){
		HSSFRow firstRow=sheet.createRow(0);
		HSSFCell cell=firstRow.createCell(0);
		cell.setCellValue("아이디");
		
		cell=firstRow.createCell(1);
		cell.setCellValue("패스워드");
		cell=firstRow.createCell(2);
		cell.setCellValue("이  름");
		cell=firstRow.createCell(3);
		cell.setCellValue("email");		
	}
	
	private void createRow(HSSFSheet sheet,MemberVO member,int rowNum){
		HSSFRow row=sheet.createRow(rowNum);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue(member.getUserid());
		
		cell=row.createCell(1);
		cell.setCellValue(member.getUserpwd());
		cell=row.createCell(2);
		cell.setCellValue(member.getName());
		cell=row.createCell(3);
		cell.setCellValue(member.getEmail());
		
	}
}









