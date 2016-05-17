package org.hu.export;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hu.annocation.SystemControllerLog;
import org.hu.data.model.User;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class UserExcelView extends AbstractExcelView{
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<User> userList = (List<User>) model.get("userList");
		HSSFSheet sheet = workbook.createSheet();
		sheet.setDefaultColumnWidth(12);
		HSSFCell cell = getCell(sheet,0,0);
		setText(cell,"ID");
		cell = getCell(sheet,0,1);
		setText(cell,"–’√˚");
		cell = getCell(sheet,0,2);
		setText(cell,"√‹¬Î");
		for(int i=0;i<userList.size();i++){
			User user = userList.get(i);
			HSSFRow row = sheet.createRow(i+1);
			row.createCell(0).setCellValue(user.getId());
			row.createCell(1).setCellValue(user.getUsername());
			row.createCell(2).setCellValue(user.getPassword());
		}
		String fileName = "user.xls";
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-disposition", "attachment;filename="+fileName);
		OutputStream out = response.getOutputStream();
		workbook.write(out);
		out.flush();
		out.close();
	}
	
}
