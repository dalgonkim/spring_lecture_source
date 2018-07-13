package com.nonage.convert;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.nonage.dto.ProductVO;

public class ProductListPdfView extends AbstractPdfView{
	
	private String fontPath="c:\\windows\\Fonts\\malgun.ttf";

	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
			Document document, PdfWriter writer, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		
		List<ProductVO> productList=(List<ProductVO>)model.get("productList");
		 
		Table table = new Table(6, productList.size() + 1); //Table 선언(열개수, 행수)
		table.setPadding(5); //내부여백
		float[] columnWidths = new float[] {15f, 40f, 20f, 20f,30f,25f};
		table.setWidths(columnWidths);
		//글꼴설정.
		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);
		
		Font font = new Font(bfKorean);
		Cell cell=new Cell(new Paragraph("번호",font));		
		cell.setHeader(true); //표헤더시작
		table.addCell(cell);
		cell=new Cell(new Paragraph("상품명",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("원가",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("판매가",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("등록일",font));
		table.addCell(cell);
		cell=new Cell(new Paragraph("사용유무",font));
		table.addCell(cell);
		table.endHeaders(); //표헤더 종료.
		
		for (ProductVO product : productList) {
			table.addCell(""+product.getPseq());
			cell=new Cell(new Paragraph(product.getName(),font));			
			table.addCell(cell);
			cell=new Cell(new Paragraph(""+product.getPrice1(),font));			
			table.addCell(cell);
			cell=new Cell(new Paragraph(""+product.getPrice2(),font));
			table.addCell(cell);
			cell=new Cell(new Paragraph(new SimpleDateFormat("yyyy-MM-dd")
				.format(product.getIndate()),font));			
			table.addCell(cell);
			cell=new Cell(new Paragraph(product.getUseyn(),font));
			table.addCell(cell);
			
		}
		document.add(table);
	}
	
	@Autowired(required=false)
	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}









