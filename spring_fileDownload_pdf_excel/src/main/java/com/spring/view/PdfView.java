package com.spring.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.spring.dto.MemberVO;

public class PdfView extends AbstractPdfView{

	private String fontPath="c:\\windows\\Fonts\\malgun.ttf";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
									Document document, 
									PdfWriter writer, 
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		
		
		List<MemberVO> memberList=
				(List<MemberVO>)model.get("memberList");
		
		
		Table table=new Table(4,memberList.size()+1);
		table.setPadding(5);
		
		BaseFont bfKor=BaseFont.createFont(fontPath,
										   BaseFont.IDENTITY_H,
										   BaseFont.EMBEDDED);
		Font font=new Font(bfKor);
		
		Cell cell=new Cell(new Paragraph("아이디",font));
		cell.setHeader(true);
		
		table.addCell(cell);
		
		cell=new Cell(new Paragraph("패스워드",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("이메일",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("이  름",font));
		table.addCell(cell);
		
		table.endHeaders();
		
		for(MemberVO member:memberList){
			cell=new Cell(new Paragraph(member.getUserid(),font));
			table.addCell(cell);
			
			cell=new Cell(new Paragraph(member.getUserpwd(),font));
			table.addCell(cell);
			
			cell=new Cell(new Paragraph(member.getEmail(),font));
			table.addCell(cell);
			
			cell=new Cell(new Paragraph(member.getName(),font));
			table.addCell(cell);
		}
		
		document.add(table);
	}
	

}








