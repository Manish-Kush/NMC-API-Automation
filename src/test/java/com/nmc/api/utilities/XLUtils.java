package com.nmc.api.utilities;
/*
 * This class is used to read the data from .xlsx file
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils 
{
	public static FileInputStream fileInputStream;
	public static FileOutputStream fileOutputStream;
	public static XSSFWorkbook workBook;
	public static XSSFSheet workSheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowCount(String xlFile, String xlSheet) throws IOException
	{
		fileInputStream = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(xlSheet);
		int rowCount = workSheet.getLastRowNum();
		
		workBook.close();
		fileInputStream.close();
		
		return rowCount;
	}
	
	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException
	{
		fileInputStream = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		
		workBook.close();
		fileInputStream.close();
		
		return cellCount;
	}
	
	public static String getCellData(String xlFile, String xlSheet, int rowNum, int column) throws IOException
	{
		fileInputStream = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		cell = row.getCell(column);
		String data;
		
		try
		{
			DataFormatter formatter	=  new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		}
		catch(Exception e)
		{
			data = "";
		}
		workBook.close();
		fileInputStream.close();
		
		return data;
	}
	
	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data) throws IOException
	{
		fileInputStream = new FileInputStream(xlFile);
		workBook = new XSSFWorkbook(fileInputStream);
		workSheet = workBook.getSheet(xlSheet);
		row = workSheet.getRow(rowNum);
		
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		
		fileOutputStream = new FileOutputStream(xlFile);
		workBook.write(fileOutputStream);
		workBook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}
	
}