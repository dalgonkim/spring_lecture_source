package mvjsp.chap13.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvjsp.chap13.model.Member;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class MemberListExcelView extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setHeader("Content-Disposition", "attachment; filename=\"memberList.xls\";");

		HSSFSheet sheet = createFirstSheet(workbook); //sheet 생성
		createColumnLabel(sheet);	  //Label 작성 ( table header)

		List<Member> memberList = (List<Member>) model.get("memberList");
		int rowNum = 1;
		for (Member rank : memberList) {
			createMemberRow(sheet, rank, rowNum++); //실제 data 입력
		}
	}

	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "회원리스트");
		sheet.setColumnWidth(0, 256 * 10);
		sheet.setColumnWidth(1, 256 * 20);
		sheet.setColumnWidth(2, 256 * 20);
		return sheet;
	}

	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순번");
		cell = firstRow.createCell(1);
		cell.setCellValue("아이디");
		cell = firstRow.createCell(2);
		cell.setCellValue("패스워드");
	}

	private void createMemberRow(HSSFSheet sheet, Member member,
			int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rowNum);
		cell = row.createCell(1);
		cell.setCellValue(member.getId());
		cell = row.createCell(2);
		cell.setCellValue(member.getPwd());

	}

}
