package mvjsp.chap13.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvjsp.chap13.model.Member;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class MemberListPdfView extends AbstractPdfView{
	private String fontPath = "c:\\windows\\Fonts\\malgun.ttf";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Member> Members = (List<Member>) model.get("memberList");
		
		Table table = new Table(3, Members.size() + 1);
		
		table.setPadding(5);

		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);

		Font font = new Font(bfKorean);
		
		Cell cell = new Cell(new Paragraph("순번", font));
		cell.setHeader(true);
		table.addCell(cell);
		
		cell = new Cell(new Paragraph("아이디", font));
		table.addCell(cell);
		table.endHeaders();
		
		cell = new Cell(new Paragraph("패스워드", font));
		table.addCell(cell);
		table.endHeaders();
		
		int index=0;
		for (Member member : Members) {
			index++;
			table.addCell(""+index);
			cell = new Cell(new Paragraph(member.getId(), font));
			table.addCell(cell);
			cell = new Cell(new Paragraph(member.getPwd(), font));
			table.addCell(cell);
			
		}
		document.add(table);
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}
