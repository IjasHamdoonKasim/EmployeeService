package com.sgic.employee.server.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sgic.employee.server.entities.Employee;
import com.sgic.employee.server.repositories.EmployeeRepository;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportEmployeeServiceImpl implements ExportEmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public ByteArrayInputStream exportEmployee(List<Employee> employees) {
		try(Workbook workbook = new XSSFWorkbook()){
			Sheet sheet = workbook.createSheet("Contacts");
			
			Row row = sheet.createRow(0);
			
			// Define header cell style
	        CellStyle headerCellStyle = workbook.createCellStyle();
	        headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
	        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
	        // Creating header cells 
	        Cell cell = row.createCell(0);
	        cell.setCellValue("Employee Id");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(1);
	        cell.setCellValue("Email");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(2);
	        cell.setCellValue("First Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Gender");
	        cell.setCellStyle(headerCellStyle);
	
	        cell = row.createCell(4);
	        cell.setCellValue("Last Name");
	        cell.setCellStyle(headerCellStyle);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Phone Number");
	        cell.setCellStyle(headerCellStyle);
	        
	        // Creating data rows for each contact
	        for(int i = 0; i < employees.size(); i++) {
	        	Row dataRow = sheet.createRow(i + 1);
	        	dataRow.createCell(0).setCellValue(employees.get(i).getEmployeeId());
	        	dataRow.createCell(1).setCellValue(employees.get(i).getEmailId());
	        	dataRow.createCell(2).setCellValue(employees.get(i).getFirstName());
	        	dataRow.createCell(3).setCellValue(employees.get(i).getGender());
	        	dataRow.createCell(4).setCellValue(employees.get(i).getLastName());
	        	dataRow.createCell(5).setCellValue(employees.get(i).getPhoneNo());
	        }
	
	        // Making size of column auto resize to fit with data
	        sheet.autoSizeColumn(0);
	        sheet.autoSizeColumn(1);
	        sheet.autoSizeColumn(2);
	        sheet.autoSizeColumn(3);
	        sheet.autoSizeColumn(4);
	        sheet.autoSizeColumn(5);
	        
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        workbook.write(outputStream);
	        return new ByteArrayInputStream(outputStream.toByteArray());
		} catch (IOException ex) {
			logger.error("Error during export Excel file", ex);
			return null;
		}
	}


}
