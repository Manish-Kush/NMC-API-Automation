package com.nmc.api.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nmc.api.utilities.ReadConfigFile;
import com.nmc.api.utilities.XLUtils;

public class WriteIntoExcelFile
{
	ReadConfigFile readConfigFile;
	public void writeUniqueFieldsIntoExcel() throws IOException
	
	{
		readConfigFile = new ReadConfigFile();
		NameAndPhoneNumAndOldRegProvider dataProvider = new NameAndPhoneNumAndOldRegProvider();
		
		 File file = new File(readConfigFile.getExcelRegistrationDataPath());
		 FileInputStream fis = new FileInputStream(file);
		 
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 
		 XSSFSheet sh = wb.getSheetAt(0);
		for(int i=0; i<4; i++) 
		{
		 sh.getRow(2).createCell(8+i).setCellValue(dataProvider.patientName());//patient first middle and Last name
		}
		
		 sh.getRow(2).createCell(11).setCellValue(dataProvider.PatientPhoneNumber(5));//patient phone Number
		 sh.getRow(2).createCell(37).setCellValue(dataProvider.PatientPhoneNumber(6));//Old Reg Number
		
		 
		 FileOutputStream fos = new FileOutputStream(file);
		 
		 wb.write(fos);
		 
		fos.close();
		
		System.out.println("Written sucessfully");
		}
	
	public void writeRegistrationIntoExcel(String registeredInfo, int columnNum) throws IOException
	{	
		File file = new File(readConfigFile.getExcelRegInfoDataPath());
		FileInputStream fis = new FileInputStream(file);
		 
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		 
		XSSFSheet sh = wb.getSheet(readConfigFile.getExcelRegInfoDataSheetName());
		
		int row = XLUtils.getRowCount(readConfigFile.getExcelRegInfoDataPath(), readConfigFile.getExcelRegInfoDataSheetName());
		
		if(columnNum == 0)
			{
			System.out.println("Entered");
			  sh.createRow(row+1);
			  row++;
			}
		
		
		sh.getRow(row).createCell(columnNum).setCellValue(registeredInfo);//enter reg info into excel
		
		FileOutputStream fos = new FileOutputStream(file);
		wb.write(fos);		 
		fos.close();
		System.out.println("Written new patient data into excel sucessfully");
	}
		
		public static void main(String[] args) throws IOException
		{
			WriteIntoExcelFile writeExel = new WriteIntoExcelFile();
			writeExel.writeUniqueFieldsIntoExcel();
		}
	
}
