package com.board.view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.board.dto.BoardVO;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class PdfView extends AbstractPdfView{

	private String fontPath="c:\\windows\\Fonts\\malgun.ttf";
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
									Document document, 
									PdfWriter writer, 
									HttpServletRequest request,
									HttpServletResponse response) throws Exception {
		
		
		List<BoardVO> boardList = (List<BoardVO>)model.get("boardList");
		
		BaseFont bfKor=BaseFont.createFont(fontPath,
				   BaseFont.IDENTITY_H,
				   BaseFont.EMBEDDED);
		Font font=new Font(bfKor);
		font.setSize(24f);
		Paragraph title=new Paragraph("게시판 리스트",font);	
		title.setAlignment(Element.ALIGN_CENTER);
		document.add(title);
		
		Table table=new Table(5,boardList.size()+1);
		table.setPadding(5);
		float[] columnWidths=new float[]{15f,40f,20f,30f,15f};
		table.setWidths(columnWidths);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);	
		font.setSize(12f);		
		
		Cell cell=new Cell(new Paragraph("글번호",font));
		cell.setHeader(true);
		
		table.addCell(cell);
		
		cell=new Cell(new Paragraph("제  목",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("작성자",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("등록일",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("조회수",font));
		table.addCell(cell);
		
		table.endHeaders();
		
		for(BoardVO board:boardList){
			cell=new Cell(new Paragraph(board.getBno()+"",font));
			table.addCell(cell);
			
			cell=new Cell(new Paragraph(board.getTitle(),font));
			table.addCell(cell);
			
			cell=new Cell(new Paragraph(board.getWriter(),font));
			table.addCell(cell);
			
			String dateStr=new SimpleDateFormat("yyyy-MM-dd")
					.format(board.getRegDate());
			
			cell=new Cell(new Paragraph(dateStr,font));
			table.addCell(cell);
			cell=new Cell(new Paragraph(board.getViewcnt()+"",font));
			table.addCell(cell);
		}
		
		document.add(table);
	}
	

}








