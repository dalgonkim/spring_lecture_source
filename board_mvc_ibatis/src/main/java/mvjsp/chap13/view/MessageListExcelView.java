package mvjsp.chap13.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvjsp.chap13.model.Message;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class MessageListExcelView extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"messageList.xls\";");

		HSSFSheet sheet = createFirstSheet(workbook); //sheet 생성
		createColumnLabel(sheet);	  //Label 작성 ( table header)

		List<Message> messageList = (List<Message>) model.get("messageList");
		int rowNum = 1;
		for (Message message : messageList) {
			createmessageRow(sheet, message,rowNum++); //실제 data 입력
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "방명록리스트");
		sheet.setColumnWidth(0, 256 * 5);
		sheet.setColumnWidth(1, 256 * 20);
		sheet.setColumnWidth(2, 256 * 10);
		sheet.setColumnWidth(3, 256 * 20);
		sheet.setColumnWidth(4, 256 * 30);
		
		return sheet;
	}

	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순번");
		cell = firstRow.createCell(1);
		cell.setCellValue("아이디");
		cell = firstRow.createCell(2);
		cell.setCellValue("이름");
		cell = firstRow.createCell(3);
		cell.setCellValue("패스워드");
		cell = firstRow.createCell(4);
		cell.setCellValue("내용");
	}

	private void createmessageRow(HSSFSheet sheet, Message message,
			int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rowNum);
		cell = row.createCell(1);
		cell.setCellValue(message.getMessage_id());
		cell = row.createCell(2);
		cell.setCellValue(message.getGuest_name());
		cell = row.createCell(3);
		cell.setCellValue(message.getPassword());
		cell = row.createCell(4);
		cell.setCellValue(message.getMessage());
		

	}

}
