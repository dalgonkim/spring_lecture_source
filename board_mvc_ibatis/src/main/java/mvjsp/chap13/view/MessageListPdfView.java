package mvjsp.chap13.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvjsp.chap13.model.Message;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class MessageListPdfView extends AbstractPdfView{
	private String fontPath = "c:\\windows\\Fonts\\malgun.ttf";

	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		List<Message> messages = (List<Message>) model.get("messageList");
		
		BaseFont bfKorean = BaseFont.createFont(fontPath, BaseFont.IDENTITY_H,
				BaseFont.EMBEDDED);

		Font font = new Font(bfKorean);
		
		String[] colLabel={"순번","아이디","작성자","패스워드","메세지" };
		
		Table table = new Table(colLabel.length, messages.size() + 1);		
		table.setPadding(5);

		Cell cell =new Cell();
		cell.setHeader(true);
		
		for(String label:colLabel){
		cell = new Cell(new Paragraph(label, font));		
		table.addCell(cell);
		}
		
		table.endHeaders();
		
		int index=0;
		for (Message message : messages) {
			index++;
			table.addCell(""+index);
			cell = new Cell(new Paragraph(message.getMessage_id()+"", font));
			table.addCell(cell);
			cell = new Cell(new Paragraph(message.getGuest_name(), font));
			table.addCell(cell);
			cell = new Cell(new Paragraph(message.getPassword(), font));
			table.addCell(cell);
			cell = new Cell(new Paragraph(message.getMessage(), font));
			table.addCell(cell);
			
		}
		document.add(table);
	}

	public void setFontPath(String fontPath) {
		this.fontPath = fontPath;
	}
}
