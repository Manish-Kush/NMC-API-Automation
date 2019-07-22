package com.nmc.api.generic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CopyDataFromSuccessAndWriteIntoExcel
{
	public void test() throws IOException 
	{
		NameAndPhoneNumAndOldRegProvider dataProvider = new NameAndPhoneNumAndOldRegProvider();
		String xlDataPath = "./src/test/java/com/nmc/api/testdata/regData.xlsx";
		
		 File file = new File(xlDataPath);
		 FileInputStream fis = new FileInputStream(file);
		 
		 XSSFWorkbook wb = new XSSFWorkbook(fis);
		 
		// XSSFSheet sh = wb.getSheetAt(0);
		 XSSFSheet sh = wb.getSheet("RegSheet");
		 
		 sh.createRow(0).createCell(0).setCellValue("helloworld1");
		 sh.getRow(0).createCell(1).setCellValue("test1");
		 sh.createRow(1).createCell(0).setCellValue(false);
//		for(int i=0; i<4; i++) 
//		{
//		 sh.getRow(2).createCell(8+i).setCellValue(dataProvider.patientName());//patient first middle and Last name
//		}
//		
//		 sh.getRow(2).createCell(11).setCellValue(dataProvider.PatientPhoneNumber(5));//patient phone Number
//		 sh.getRow(2).createCell(37).setCellValue(dataProvider.PatientPhoneNumber(6));//Old Reg Number
		
		 
		 FileOutputStream fos = new FileOutputStream(file);
		 
		 wb.write(fos);
		 
		fos.close();
		
		System.out.println("Written sucessfully");
	}

	
	public static void main(String[] args) throws IOException
	{
		CopyDataFromSuccessAndWriteIntoExcel obj = new CopyDataFromSuccessAndWriteIntoExcel();
		obj.test();
//		WriteIntoExcelFile writeExel = new WriteIntoExcelFile();
//		writeExel.writeUniqueFieldsIntoExcel();
	}
}
