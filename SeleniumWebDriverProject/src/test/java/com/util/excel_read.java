package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class excel_read {
	
	
	public static Object[][] test_excelread( String sheetname)
	{
		File file=new File("src\\test\\resources\\Book1.xlsx");
		
		Object [][] obj=null;
		
		
		try {
			InputStream is = new FileInputStream(file);
			XSSFWorkbook workbook=new XSSFWorkbook(is);
			XSSFSheet Sheet1=workbook.getSheet(sheetname);
			
			obj=new Object[Sheet1.getLastRowNum()][];
			for(int i=1;i<=Sheet1.getLastRowNum();i++)
			{
				obj[i-1]=new Object[Sheet1.getRow(i).getPhysicalNumberOfCells()];
				for(int j=0;j<Sheet1.getRow(i).getPhysicalNumberOfCells();j++)
				{
					obj[i-1][j]=Sheet1.getRow(i).getCell(j).getStringCellValue();
				}
			//	Sheet1.getRow(i).createCell(2).setCellValue("valid user");//to write into the excel sheet
			//  Sheet1.getRow(0).createCell(2).setCellValue("status");
				
			//	System.out.println("");
			}
			is.close();
			
		//	OutputStream os=new FileOutputStream(file);
		//	workbook.write(os);
	      	workbook.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return obj;
	}
	

}
