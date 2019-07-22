package com.nmc.api.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.nmc.api.generic.BaseClass;
import com.nmc.api.generic.WriteIntoExcelFile;
import com.nmc.api.utilities.CommonActionUtils;
import com.nmc.api.utilities.ReadConfigFile;
import com.nmc.api.utilities.XLUtils;

public class RegistrationScreen extends BaseClass
{
	 ReadConfigFile readConfigFile;
	 String webElementName;
	 Robot robot;
	 Alert alert;
	 WriteIntoExcelFile writeExel;
	 
	public RegistrationScreen() throws AWTException
	{
		readConfigFile = new ReadConfigFile();
		robot = new Robot();
		writeExel = new WriteIntoExcelFile();
	}
	
	/*
	 * This method will read the locator from .xlsx file 
	 * @return "Identifier" value
	 */
	public  String getLocator(int row, int column) throws IOException
	{
		String locator = XLUtils.getCellData(readConfigFile.getExcelLocatorPath(), readConfigFile.getExcelLocatorSheetName(), row, column);
		webElementName=XLUtils.getCellData(readConfigFile.getExcelLocatorPath(), readConfigFile.getExcelLocatorSheetName(), row, column-2);
		return locator;	
	}
	
	public String getData(int row, int column) throws IOException
	{
		return XLUtils.getCellData(readConfigFile.getExcelRegistrationDataPath(),readConfigFile.getExcelRegistrationDataSheetName(), row, column);
	}
	/*
	 * Data Which will be stored in the RegInfo excel file will used to modify or validate the data for which patient is registered
	 */
	public String getRegInfoData(int row, int column) throws IOException
	{
		return XLUtils.getCellData(readConfigFile.getExcelRegInfoDataPath(),readConfigFile.getExcelRegInfoDataSheetName(), row, column);
	}
	
	public void runWithNewUniqueData(String decision) throws IOException
	{
		if(decision.equals("Yes"))
		{
			writeExel.writeUniqueFieldsIntoExcel();//Write unique name, phone number & old reg number into excel file

		}else System.out.println("Registering with old data\n");
	}
	
	public void getDataFromRegSuccuss() throws IOException
	{
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(244, 3), driver),0);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(245, 3), driver),1);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(246, 3), driver),2);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(247, 3), driver),3);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(248, 3), driver),4);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(249, 3), driver),5);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(250, 3), driver),6);
		writeExel.writeRegistrationIntoExcel(CommonActionUtils.getTextByXpath(getLocator(251, 3), driver),7);
		
		String[] regInfoDataOtherThenSuccesScr = new String[100];
	}
	
	/**
	 * @return asserted boolean value
	 * @throws IOException
	 */
	//testCase_1
	public  boolean login()

	{
		try
		{
			CommonActionUtils.sendKeysByName(getLocator(2, 3), readConfigFile.getUserName(),driver);
			BaseClass.logger.info("user name entered succesfully. and element name is "+webElementName);
			
			CommonActionUtils.sendKeysByName(getLocator(3, 3),readConfigFile.getPassword(),driver);
			BaseClass.logger.info("login password entered succesfully and element name is "+webElementName);
			
			CommonActionUtils.clickByName(getLocator(4, 3), driver);
			BaseClass.logger.info("succefully clicked on submit button and element name is "+webElementName);
			
			
				if(CommonActionUtils.getTextById("pageuser", driver).equals(readConfigFile.getUserName()))
				{
					BaseClass.logger.info("login was succesful!!");
					return true;
				}
				else
				{
					BaseClass.logger.info("login was Unsucceful.");
					return false;
				}
		}
		catch(Exception e)
		{
			BaseClass.logger.warn("login got failed.");
			e.printStackTrace();
			return false;
		}
	}
	//*******************OP Registration starts**************************************//
	/*
	 * this method will do the complete OP Registration if call in testRegistrationPage class
	 */
	public boolean opRegistration(String decision) throws IOException, InterruptedException
		{
			runWithNewUniqueData(decision);//run code with unique data (NAme, MOb No, Old Reg No.)
		
			CommonActionUtils.selectDropdownByName(getLocator(201,3), getData(2, 47), driver);
			BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+webElementName);
			
			CommonActionUtils.clickByXPath(getLocator(204,3), driver);
			BaseClass.logger.info("successfully clicked on Registration dropdown where element name is "+webElementName);
			
			CommonActionUtils.clickByHyperLink(getLocator(206,3), driver);
			BaseClass.logger.info("successfully clicked on OP Registration hyper link where element name is "+webElementName);
		
			CommonActionUtils.clickOnRadioButtonById(getLocator(7, 3), driver);
			BaseClass.logger.info("successfully clicked on New Registration where element name is "+webElementName);
			
			//-----------Medico Legal Case (MLC) start-------------------------//
			
			CommonActionUtils.clickByName(getLocator(10, 3), driver);
			BaseClass.logger.info("successfully clicked on Medico legal Case checkbox where element name is "+webElementName);
			
			CommonActionUtils.selectDropdownByXpath(getLocator(12,3),getData(2,1) , driver);
			BaseClass.logger.info("successfully selected mlc Document.");
			
			
			CommonActionUtils.sendKeysByXpath(getLocator(13,3), getData(2,2), driver);
			BaseClass.logger.info("successfully entered mlc Type value.");
			
			CommonActionUtils.sendKeysByXpath(getLocator(14,3), getData(2,3), driver);
			BaseClass.logger.info("successfully entered Accident Place name .");
			
			CommonActionUtils.sendKeysByXpath(getLocator(15, 3), getData(2,4), driver);
			BaseClass.logger.info("successfully entered police station name.");
			
			CommonActionUtils.sendKeysByXpath(getLocator(16, 3),getData(2, 5) , driver);
			BaseClass.logger.info("successfully entered mlc remarks.");
			
			CommonActionUtils.sendKeysByXpath(getLocator(17,3),getData(2,6), driver);
			BaseClass.logger.info("successfully entered cetificate status.");
			
			CommonActionUtils.clickById(getLocator(18, 3), driver);
			BaseClass.logger.info("successfully click on mlc OK Button");
			
			//-----------Medico Legal Case (MLC) end-------------------------//
			
			//-----------Basic Information start ----------------------------//
			CommonActionUtils.selectDropdownById(getLocator(24, 3), getData(2, 7), driver);
			BaseClass.logger.info("succesfully selected salutation."); 
			
			CommonActionUtils.sendKeysById(getLocator(25, 3),getData(2, 8),driver);
			BaseClass.logger.info("succesfully entered first name.");
			
			CommonActionUtils.sendKeysById(getLocator(26, 3),getData(2, 9),driver);
			BaseClass.logger.info("succesfully entered middle name.");
			
			CommonActionUtils.sendKeysById(getLocator(27, 3),getData(2, 10),driver);
			BaseClass.logger.info("succesfully entered last name.");
			
			CommonActionUtils.sendKeysById(getLocator(28, 3),getData(2, 11),driver);
			BaseClass.logger.info("succesfully entered patient mobile number .");
			
			CommonActionUtils.sendKeysById(getLocator(31, 3),getData(2, 12),driver);
			BaseClass.logger.info("succesfully entered DOB Day.");
			
			CommonActionUtils.sendKeysById(getLocator(32, 3),getData(2, 13),driver);
			BaseClass.logger.info("succesfully entered DOB Month.");
			
			CommonActionUtils.sendKeysById(getLocator(33, 3),getData(2, 14),driver);
			BaseClass.logger.info("succesfully entered DOB Year.");
			
			CommonActionUtils.sendKeysById(getLocator(29, 3),getData(2, 15),driver);
			BaseClass.logger.info("succesfully entered patient Addln phone number.");
			
			CommonActionUtils.sendKeysById(getLocator(30, 3),getData(2, 18),driver);
			BaseClass.logger.info("succesfully entered patient contact number.");
			
			CommonActionUtils.sendKeysById(getLocator(37, 3),getData(2, 16),driver);
			BaseClass.logger.info("succesfully entered next of the kin name.");
			
			CommonActionUtils.sendKeysById(getLocator(38, 3),getData(2, 17),driver);
			BaseClass.logger.info("succesfully entered patient relation.");
			
			//-----------Basic Information end ----------------------------//
			
			//----------- Visit Information start ----------------------------//
			BaseClass.logger.info("*******Entering Visit Information start********");
			CommonActionUtils.selectDropdownById(getLocator(139, 3),getData(2, 92) , driver);
			BaseClass.logger.info("succesfully selected Visit Type.");
			
			CommonActionUtils.selectDropdownById(getLocator(140, 3), getData(2,93), driver);
			BaseClass.logger.info("succesfully selected the consultation Type.");
			
			CommonActionUtils.sendKeysByXpath(getLocator(144, 3), getData(2, 94), driver);
			BaseClass.logger.info("succesfully entered consultation remark.");
			
			BaseClass.logger.info("******* Visit Information ends********");
			//----------- Visit Information end ----------------------------//
			
			//========================================Dhanush Code===========================
					
			//Adding Patient Addational Information
			  BaseClass.logger.info("\n***Adding Patient Addational Information***");

			  CommonActionUtils.sendKeysById(getLocator(44, 3), getData(2, 25),driver);
			  BaseClass.logger.info("Address of the patient entered successfully.WebElement name is : "+webElementName);
			  CommonActionUtils.sendKeysById(getLocator(45, 3), getData(2, 26),driver); 
			  BaseClass.logger.info("Area name entered successfully.WebElement name is : "+webElementName);
			  CommonActionUtils.selectDropdownByName(getLocator(71, 3), getData(2, 28), driver);
			  BaseClass.logger.info("Nationality of the patient entered successfully.WebElement name is : "+webElementName);
			  CommonActionUtils.selectDropdownByName(getLocator(50, 3), getData(2, 29), driver);
			  BaseClass.logger.info("patient consent selected successfully.WebElement name is : "+webElementName);
			  CommonActionUtils.selectDropdownByName(getLocator(53, 3), getData(2, 32), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByXpath(getLocator(52, 3), getData(2, 26),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(54, 3), getData(2, 33),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(55, 3), getData(2, 34),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(56, 3), getData(2, 35),driver); 
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.clickByName(getLocator(57, 3), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(59, 3), getData(2, 36),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.selectDropdownByName(getLocator(60, 3), getData(2, 38), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(61, 3), getData(2, 40),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByXpath(getLocator(62, 3), getData(2, 42),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByXpath(getLocator(63, 3),getData(2, 44),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(64, 3), getData(2, 46),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByName(getLocator(65, 3), getData(2, 37),driver);
			  BaseClass.logger.info(webElementName+" Is entered");//old reg no
			  CommonActionUtils.selectDropdownById(getLocator(66, 3), getData(2, 39), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  /**
			   * Writing unique data for old Registration number
			   */
			  Thread.sleep(1000);
			     try
					{
						alert = driver.switchTo().alert(); 
						System.out.println(alert.getText());
						alert.accept();
					}
					catch (NoAlertPresentException e) {
						
						System.out.println("Old Reg number is unique)");
					}
			  
			  CommonActionUtils.sendKeysByXpath(getLocator(67, 3), getData(2, 41),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByXpath(getLocator(68, 3), getData(2, 43), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysByXpath(getLocator(69, 3), getData(2, 45),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.clickById(getLocator(70, 3), driver);
			  BaseClass.logger.info("clicked on "+webElementName);
			  
			  //Adding Patient Sponsor Information
			  BaseClass.logger.info("\n***Adding Patient Sponsor Information***");
			  CommonActionUtils.selectDropdownById(getLocator(74, 3), getData(2, 49), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.selectDropdownById(getLocator(75, 3), getData(2, 50), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  
			  //Adding Patient Primary Sponsor
			  BaseClass.logger.info("\n***Adding Patient Primary Sponsor***");
			  CommonActionUtils.selectDropdownById(getLocator(78, 3), getData(2, 52), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.selectDropdownById(getLocator(79, 3), getData(2, 53), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			 
			  CommonActionUtils.selectDropdownById(getLocator(81, 3), getData(2, 54), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.selectDropdownById(getLocator(82, 3), getData(2, 55), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(84, 3), getData(2, 56), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(85, 3), getData(2, 57),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(87, 3),getData(2, 58) ,driver );
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(89, 3), getData(2, 59),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(90, 3), getData(2, 60), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(91, 3), getData(2, 61), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.sendKeysById(getLocator(92, 3), getData(2, 62),driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  CommonActionUtils.selectDropdownById(getLocator(93, 3), getData(2, 63), driver);
			  BaseClass.logger.info(webElementName+" Is entered");
			  
			  CommonActionUtils.sendKeysById(getLocator(80, 3), getData(2, 77),driver);
			     BaseClass.logger.info(webElementName+" Is entered");
			  
			  //Adding Patient Secondary Sponsor
			    BaseClass.logger.info("\n***Adding Patient Secondary Sponsor***");
			    CommonActionUtils.selectDropdownById(getLocator(108, 3), getData(2, 65), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.selectDropdownById(getLocator(109, 3), getData(2, 66), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    
			    CommonActionUtils.sendKeysById(getLocator(110, 3), "0", driver);//hard code sec approval amt
			    
			    CommonActionUtils.selectDropdownById(getLocator(111, 3), getData(2, 67), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.selectDropdownById(getLocator(112, 3), getData(2, 68), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(114, 3),getData(2, 69), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(115, 3), getData(2, 70), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(117, 3), getData(2, 71), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(119, 3), getData(2, 72), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(120, 3), getData(2, 73), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(121, 3), getData(2, 74), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(122, 3), getData(2, 75), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.selectDropdownById(getLocator(123, 3), getData(2, 76), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			 
			    CommonActionUtils.sendKeysById(getLocator(110, 3), getData(2, 77),driver);
				BaseClass.logger.info(webElementName+" Is entered");
			    
			    //Adding Payment Information
			    BaseClass.logger.info("\n***Adding Payment Information***");
			    //CommonActionUtils.selectDropdownById(getLocater(132, 3), getData(2, 78), driver);// this step should come before choosing insurance
			    //CommonActionUtils.selectDropdownById(getLocater(133, 3), getData(2, 79), driver);// this step should come before choosing insurance

			    //Adding Payment Admission Information 
			    BaseClass.logger.info("\n***Adding Payment Admission Information***");
			    CommonActionUtils.selectDropdownById(getLocator(136, 3), getData(2, 81), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.sendKeysById(getLocator(146, 3), getData(2, 87), driver);
			    CommonActionUtils.sendKeysById(getLocator(138, 3), getData(2, 82), driver);
			    Thread.sleep(1000);
			    BaseClass.logger.info(webElementName+" Is entered");
			    
			    
			    robot.mouseMove(400,680);
			    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

			    CommonActionUtils.sendKeysById(getLocator(145, 3),getData(2, 86), driver);
			    Thread.sleep(2000);
			    robot.keyPress(KeyEvent.VK_ENTER);
			    
			    
			    robot.keyPress(KeyEvent.VK_F8);
			    robot.keyRelease(KeyEvent.VK_F8);
			    BaseClass.logger.info(webElementName+" Is entered");
			    CommonActionUtils.selectDropdownByName(getLocator(137, 3), getData(2, 88), driver);
			    BaseClass.logger.info(webElementName+" Is entered");
	
			//==================================================Dhanush Code===============
			    CommonActionUtils.clickById(getLocator(197, 3), driver);
			    BaseClass.logger.info("Clicked on register "+webElementName);
			    
			   // Thread.sleep(9000);
			    
				try
				{
					alert = driver.switchTo().alert(); 
					System.out.println(alert.getText());
					alert.accept();
				}
				catch (NoAlertPresentException e) {
					
					System.out.println("This patient phone number is not exists in data base (it is unique)");
				}
				
				/*
				 * 
				 */
				//BaseClass.logger.info("Clicked on register "+webElementName);
				try
				{
				String x = CommonActionUtils.getTextByXpath(getLocator(207, 3), driver);
				
				getDataFromRegSuccuss();// copying basic data from registration success screen
				
				System.out.println("Text printed after patient got registered is : "+x);
				BaseClass.logger.info("Patient is registered successfuly "+webElementName);
				CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);

				return true;
				
				} 
				catch (NoSuchElementException e)
				{
					CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);
					return false;
				}
		
			}
	//*******************OP Registration ends**************************************//
	
	//*******************IP Registration starts************************************//
	public boolean ipRegistration(String decision) throws IOException, InterruptedException
	 {
	   runWithNewUniqueData(decision);
	   
		CommonActionUtils.selectDropdownByName(getLocator(201,3), getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+webElementName);
	   CommonActionUtils.clickByXPath(getLocator(204, 3), driver);
	   BaseClass.logger.info("Clicked on "+webElementName);
	   CommonActionUtils.clickByXPath(getLocator(169, 3), driver);
	   BaseClass.logger.info("Clicked on "+webElementName);
	  
	   CommonActionUtils.clickOnRadioButtonById(getLocator(7, 3), driver);
	   BaseClass.logger.info("successfully clicked on New Registration where element name is "+webElementName);
	   
	   //-----------Medico Legal Case (MLC) start-------------------------//
	   
	   CommonActionUtils.clickByName(getLocator(10, 3), driver);
	   BaseClass.logger.info("successfully clicked on Medico legal Case checkbox where element name is "+webElementName);
	   
	   System.out.println("child window : "+driver.getWindowHandle());
	   
	   CommonActionUtils.selectDropdownByXpath(getLocator(12,3),getData(2,1) , driver);
	   BaseClass.logger.info("successfully selected mlc Document.");
	   
	   CommonActionUtils.sendKeysByXpath(getLocator(13,3), getData(2,2), driver);
	   BaseClass.logger.info("successfully entered mlc Type value.");
	   
	   CommonActionUtils.sendKeysByXpath(getLocator(14,3), getData(2,3), driver);
	   BaseClass.logger.info("successfully entered Accident Place name .");
	   
	   CommonActionUtils.sendKeysByXpath(getLocator(15, 3), getData(2,4), driver);
	   BaseClass.logger.info("successfully entered police station name.");
	   
	   CommonActionUtils.sendKeysByXpath(getLocator(16, 3),getData(2, 5) , driver);
	   BaseClass.logger.info("successfully entered mlc remarks.");
	   
	   CommonActionUtils.sendKeysByXpath(getLocator(17,3),getData(2,6), driver);
	   BaseClass.logger.info("successfully entered cetificate status.");
	   
	   CommonActionUtils.clickById(getLocator(18, 3), driver);
	   BaseClass.logger.info("successfully click on mlc OK Button");
	   
	   //-----------Medico Legal Case (MLC) end-------------------------//
	   
	   //-----------Basic Information start ----------------------------//
	   CommonActionUtils.selectDropdownById(getLocator(24, 3), getData(2, 7), driver);
	   BaseClass.logger.info("succesfully selected salutation."); 
	   
	   CommonActionUtils.sendKeysById(getLocator(25, 3),getData(2, 8),driver);
	   BaseClass.logger.info("succesfully entered first name.");
	   
	   CommonActionUtils.sendKeysById(getLocator(26, 3),getData(2, 9),driver);
	   BaseClass.logger.info("succesfully entered middle name.");
	   
	   CommonActionUtils.sendKeysById(getLocator(27, 3),getData(2, 10),driver);
	   BaseClass.logger.info("succesfully entered last name.");
	   
	   CommonActionUtils.sendKeysById(getLocator(28, 3),getData(2, 11),driver);
	   BaseClass.logger.info("succesfully entered patient mobile number .");
	   
	   CommonActionUtils.sendKeysById(getLocator(31, 3),getData(2, 12),driver);
	   BaseClass.logger.info("succesfully entered DOB Day.");
	   
	   CommonActionUtils.sendKeysById(getLocator(32, 3),getData(2, 13),driver);
	   BaseClass.logger.info("succesfully entered DOB Month.");
	   
	   CommonActionUtils.sendKeysById(getLocator(33, 3),getData(2, 14),driver);
	   BaseClass.logger.info("succesfully entered DOB Year.");
	   
	   CommonActionUtils.sendKeysById(getLocator(29, 3),getData(2, 15),driver);
	   BaseClass.logger.info("succesfully entered patient Addln phone number.");
	   
	   CommonActionUtils.sendKeysById(getLocator(30, 3),getData(2, 18),driver);
	   BaseClass.logger.info("succesfully entered patient contact number.");
	   
	   CommonActionUtils.sendKeysById(getLocator(37, 3),getData(2, 16),driver);
	   BaseClass.logger.info("succesfully entered next of the kin name.");
	   
	   CommonActionUtils.sendKeysById(getLocator(38, 3),getData(2, 17),driver);
	   BaseClass.logger.info("succesfully entered patient relation.");
	   
	   //-----------Basic Information end ----------------------------//
	  
	  
	  //Adding Patient Addational Information
	  BaseClass.logger.info("\n***Adding Patient Addational Information***");
	//  CommonActionUtils.clickByXPath(getLocator(204, 3), driver);
	//  BaseClass.logger.info("Clicked on "+webElementName);
	//  CommonActionUtils.clickByXPath(getLocator(169, 3), driver);
	//  BaseClass.logger.info("Clicked on "+webElementName);
	  CommonActionUtils.sendKeysById(getLocator(44, 3), "Address",driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(45, 3), getData(2, 26),driver); 
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownByName(getLocator(71, 3), getData(2, 28), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownByName(getLocator(50, 3), getData(2, 29), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownByName(getLocator(53, 3), getData(2, 32), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(52, 3), getData(2, 26),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(54, 3), getData(2, 33),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(55, 3), getData(2, 34),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(56, 3), getData(2, 35),driver); 
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.clickByName(getLocator(57, 3), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(59, 3), getData(2, 36),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownByName(getLocator(60, 3), getData(2, 38), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(61, 3), getData(2, 40),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(62, 3), getData(2, 42),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(63, 3),getData(2, 44),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(64, 3), getData(2, 46),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByName(getLocator(65, 3), getData(2, 37),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(66, 3), getData(2, 39), driver);
	      Thread.sleep(8000);
	      //if Old Reg already exist in data base it will give Alert
	      try
	   {
	    alert = driver.switchTo().alert(); 
	    System.out.println(alert.getText());
	    alert.accept();
	   }
	   catch (NoAlertPresentException e) {
	    
	    System.out.println("Old Reg number is unique)");
	   }
	      BaseClass.logger.info("clicked on "+webElementName);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(67, 3), getData(2, 41),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(68, 3), getData(2, 43), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysByXpath(getLocator(69, 3), getData(2, 45),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.clickById(getLocator(70, 3), driver);
	      
	      //Adding Patient Sponsor Information
	      BaseClass.logger.info("\n***Adding Patient Sponsor Information***");
	      CommonActionUtils.selectDropdownById(getLocator(74, 3), getData(2, 49), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(75, 3), getData(2, 50), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      
	      //Adding Patient Primary Sponsor
	      BaseClass.logger.info("\n***Adding Patient Primary Sponsor***");
	      CommonActionUtils.selectDropdownById(getLocator(78, 3), getData(2, 52), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(79, 3), getData(2, 53), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(81, 3), getData(2, 54), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(82, 3), getData(2, 55), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(84, 3), getData(2, 56), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(85, 3), getData(2, 57),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(87, 3),getData(2, 58) ,driver );
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(89, 3), getData(2, 59),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(90, 3), getData(2, 60), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(91, 3), getData(2, 61), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.sendKeysById(getLocator(92, 3), getData(2, 62),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      CommonActionUtils.selectDropdownById(getLocator(93, 3), getData(2, 63), driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      
	      CommonActionUtils.sendKeysById(getLocator(80, 3), getData(2, 77),driver);
	      BaseClass.logger.info(webElementName+" Is entered");
	      
	      //Adding Patient Secondary Sponsor
	        BaseClass.logger.info("\n***Adding Patient Secondary Sponsor***");
	        CommonActionUtils.selectDropdownById(getLocator(108, 3), getData(2, 65), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(109, 3), getData(2, 66), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(111, 3), getData(2, 67), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(112, 3), getData(2, 68), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(114, 3),getData(2, 69), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(115, 3), getData(2, 70), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(117, 3), getData(2, 71), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(119, 3), getData(2, 72), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(120, 3), getData(2, 73), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(121, 3), getData(2, 74), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(122, 3), getData(2, 75), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(123, 3), getData(2, 76), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	     
	        CommonActionUtils.sendKeysById(getLocator(110, 3), getData(2, 77),driver);
	     BaseClass.logger.info(webElementName+" Is entered");
	        
	        //Adding Payment Information
	        BaseClass.logger.info("\n***Adding Payment Information***");
	        //CommonActionUtils.selectDropdownById(getLocater(132, 3), getData(2, 78), driver);// this step should come before choosing insurance
	        //CommonActionUtils.selectDropdownById(getLocater(133, 3), getData(2, 79), driver);// this step should come before choosing insurance

	    //Adding Payment Admission Information 
	    BaseClass.logger.info("\n***Adding Payment Admission Information***");
	    CommonActionUtils.selectDropdownById(getLocator(136, 3), getData(2, 81), driver);
	    BaseClass.logger.info(webElementName+" Is entered");
	    CommonActionUtils.sendKeysById(getLocator(146, 3), getData(2, 87), driver);
	    CommonActionUtils.sendKeysById(getLocator(138, 3), getData(2, 82), driver);
	    Thread.sleep(1000);
	    BaseClass.logger.info(webElementName+" Is entered");
	    
	    
	    robot.mouseMove(400,680);
	    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	    //CommonActionUtils.clickById(getLocator(161, 3), driver);
	    
	    CommonActionUtils.selectDropdownById(getLocator(161, 3), getData(2, 83), driver);
	    BaseClass.logger.info(webElementName+" Is entered");
	    CommonActionUtils.selectDropdownById(getLocator(162, 3), getData(2, 84), driver);
	    BaseClass.logger.info(webElementName+" Is entered");
	    try
	    {
	    CommonActionUtils.selectDropdownById(getLocator(163, 3), getData(2, 85), driver);
	    }
	    catch (NoSuchElementException e) {
	     CommonActionUtils.selectDropdownByIdUsingIndex(getLocator(163, 3), 1, driver);
	    }
	    BaseClass.logger.info(webElementName+" Is entered");
	    CommonActionUtils.sendKeysById(getLocator(145, 3), getData(2, 86), driver);
	    Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    
	    
	    robot.keyPress(KeyEvent.VK_F8);
	    robot.keyRelease(KeyEvent.VK_F8);
	    BaseClass.logger.info(webElementName+" Is entered");
	    CommonActionUtils.selectDropdownByName(getLocator(137, 3), getData(2, 88), driver);
	    BaseClass.logger.info(webElementName+" Is entered");

	    
	    CommonActionUtils.clickById(getLocator(197, 3), driver);
	    
	    try
	    {
	     alert = driver.switchTo().alert(); 
	     System.out.println(alert.getText());
	     alert.accept();
	    }
	    catch (NoAlertPresentException e) {
	     
	     System.out.println("This patient phone number is not exists in data base (it is unique)");
	    }
	    BaseClass.logger.info("Clicked on register "+webElementName);
	    
	    try
	    {
	    String x = CommonActionUtils.getTextByXpath(getLocator(207, 3), driver);
	    System.out.println(x);
	    BaseClass.logger.info("Patient is registered successfuly "+webElementName);
	    CommonActionUtils.goToTheUrl("http://10.8.55.122/instanmc/home.do", driver);
	    return true;
	    
	    } 
	    catch (NoSuchElementException e) {
	     CommonActionUtils.goToTheUrl("http://10.8.55.122/instanmc/home.do", driver);
	     return false;
	    } 
	 }

	//*******************IP Registration ends************************************//

	//*******************OSP Registration starts**************************************//
/*
 * If this method is called in any class then complete OSP Registration will be done.
 * 
 */
	public boolean ospRegistration(String decision) throws IOException, InterruptedException
	 {
		runWithNewUniqueData(decision);//run code with unique data (NAme, MOb No, Old Reg No.)
		
		CommonActionUtils.selectDropdownByName(getLocator(201,3), getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+webElementName);
	  CommonActionUtils.clickByXPath(getLocator(204, 3), driver);
	  BaseClass.logger.info("successfully clicked on registration dropdown. WebElement name is : "+webElementName);
	  CommonActionUtils.clickByHyperLink(getLocator(187, 3), driver);
	  BaseClass.logger.info("successfully clicked on OSP Registration link. WebElement name is : "+webElementName);
	  
	  CommonActionUtils.clickOnRadioButtonById(getLocator(7, 3), driver);
	  BaseClass.logger.info("successfully clicked on New Registration radio button where element name is "+webElementName);
	  
	  //-----------Medico Legal Case (MLC) start-------------------------//
	  
	  CommonActionUtils.clickByName(getLocator(10, 3), driver);
	  BaseClass.logger.info("successfully clicked on Medico legal Case checkbox where element name is "+webElementName);
	  
	  System.out.println("child window : "+driver.getWindowHandle());
	  
	  CommonActionUtils.selectDropdownByXpath(getLocator(12,3),getData(2,1) , driver);
	  BaseClass.logger.info("successfully selected mlc Document.");
	  
	  CommonActionUtils.sendKeysByXpath(getLocator(13,3), getData(2,2), driver);
	  BaseClass.logger.info("successfully entered mlc Type value.");
	  
	  CommonActionUtils.sendKeysByXpath(getLocator(14,3), getData(2,3), driver);
	  BaseClass.logger.info("successfully entered Accident Place name .");
	  
	  CommonActionUtils.sendKeysByXpath(getLocator(15, 3), getData(2,4), driver);
	  BaseClass.logger.info("successfully entered police station name.");
	  
	  CommonActionUtils.sendKeysByXpath(getLocator(16, 3),getData(2, 5) , driver);
	  BaseClass.logger.info("successfully entered mlc remarks.");
	  
	  CommonActionUtils.sendKeysByXpath(getLocator(17,3),getData(2,6), driver);
	  BaseClass.logger.info("successfully entered cetificate status.");
	  
	  CommonActionUtils.clickById(getLocator(18, 3), driver);
	  BaseClass.logger.info("successfully click on mlc OK Button");
	  
	  //-----------Medico Legal Case (MLC) end-------------------------//
	  
	  //-----------Basic Information start ----------------------------//
	  CommonActionUtils.selectDropdownById(getLocator(24, 3), getData(2, 7), driver);
	  BaseClass.logger.info("succesfully selected salutation."); 
	  
	  CommonActionUtils.sendKeysById(getLocator(25, 3),getData(2, 8),driver);
	  BaseClass.logger.info("succesfully entered first name.");
	  
	  CommonActionUtils.sendKeysById(getLocator(26, 3),getData(2, 9),driver);
	  BaseClass.logger.info("succesfully entered middle name.");
	  
	  CommonActionUtils.sendKeysById(getLocator(27, 3),getData(2, 10),driver);
	  BaseClass.logger.info("succesfully entered last name.");
	  
	  CommonActionUtils.sendKeysById(getLocator(28, 3),getData(2, 11),driver);
	  BaseClass.logger.info("succesfully entered patient mobile number .");
	  
	  CommonActionUtils.sendKeysById(getLocator(31, 3),getData(2, 12),driver);
	  BaseClass.logger.info("succesfully entered DOB Day.");
	  
	  CommonActionUtils.sendKeysById(getLocator(32, 3),getData(2, 13),driver);
	  BaseClass.logger.info("succesfully entered DOB Month.");
	  
	  CommonActionUtils.sendKeysById(getLocator(33, 3),getData(2, 14),driver);
	  BaseClass.logger.info("succesfully entered DOB Year.");
	  
	  CommonActionUtils.sendKeysById(getLocator(29, 3),getData(2, 15),driver);
	  BaseClass.logger.info("succesfully entered patient Addln phone number.");
	  
	  CommonActionUtils.sendKeysById(getLocator(30, 3),getData(2, 18),driver);
	  BaseClass.logger.info("succesfully entered patient contact number.");
	  
	  CommonActionUtils.sendKeysById(getLocator(37, 3),getData(2, 16),driver);
	  BaseClass.logger.info("succesfully entered next of the kin name.");
	  
	  CommonActionUtils.sendKeysById(getLocator(38, 3),getData(2, 17),driver);
	  BaseClass.logger.info("succesfully entered patient relation.");
	  
	  //-----------Basic Information end ----------------------------//
	  
	  
	  //Adding Patient Addational Information
	    BaseClass.logger.info("\n***Adding Patient Addational Information***");
//	    CommonActionUtils.clickByXPath(getLocator(204, 3), driver);
//	    BaseClass.logger.info("Clicked on "+webElementName);
//	    CommonActionUtils.clickByXPath(getLocator(169, 3), driver);
//	    BaseClass.logger.info("Clicked on "+webElementName);
	    CommonActionUtils.sendKeysById(getLocator(44, 3), "Address",driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(45, 3), getData(2, 26),driver); 
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownByName(getLocator(71, 3), getData(2, 28), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownByName(getLocator(50, 3), getData(2, 29), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownByName(getLocator(53, 3), getData(2, 32), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(52, 3), getData(2, 26),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(54, 3), getData(2, 33),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(55, 3), getData(2, 34),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(56, 3), getData(2, 35),driver); 
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.clickByName(getLocator(57, 3), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(59, 3), getData(2, 36),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownByName(getLocator(60, 3), getData(2, 38), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(61, 3), getData(2, 40),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(62, 3), getData(2, 42),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(63, 3),getData(2, 44),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(64, 3), getData(2, 46),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        

	        CommonActionUtils.sendKeysByName(getLocator(65, 3), getData(2, 37),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(66, 3), getData(2, 39), driver);
	        BaseClass.logger.info("successfull entered patient religion. Webelment name is : "+webElementName);
	        Thread.sleep(4000);
	        //if Old Reg already exist in data base it will give Alert
	        try
	     {
	      alert = driver.switchTo().alert(); 
	      System.out.println(alert.getText());
	      alert.accept();
	     }
	     catch (NoAlertPresentException e) {
	      
	      System.out.println("Old Reg number is unique)");
	     }
	        BaseClass.logger.info("clicked on "+webElementName);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(67, 3), getData(2, 41),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(68, 3), getData(2, 43), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysByXpath(getLocator(69, 3), getData(2, 45),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.clickById(getLocator(70, 3), driver);
	        
	        //Adding Patient Sponsor Information
	        BaseClass.logger.info("\n***Adding Patient Sponsor Information***");
	        CommonActionUtils.selectDropdownById(getLocator(74, 3), getData(2, 49), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(75, 3), getData(2, 50), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        
	        //Adding Patient Primary Sponsor
	        BaseClass.logger.info("\n***Adding Patient Primary Sponsor***");
	        CommonActionUtils.selectDropdownById(getLocator(78, 3), getData(2, 52), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(79, 3), getData(2, 53), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(81, 3), getData(2, 54), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(82, 3), getData(2, 55), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(84, 3), getData(2, 56), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(85, 3), getData(2, 57),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(87, 3),getData(2, 58) ,driver );
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(89, 3), getData(2, 59),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(90, 3), getData(2, 60), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(91, 3), getData(2, 61), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.sendKeysById(getLocator(92, 3), getData(2, 62),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        CommonActionUtils.selectDropdownById(getLocator(93, 3), getData(2, 63), driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        
	        CommonActionUtils.sendKeysById(getLocator(80, 3), getData(2, 77),driver);
	        BaseClass.logger.info(webElementName+" Is entered");
	        
	        //Adding Patient Secondary Sponsor
	          BaseClass.logger.info("\n***Adding Patient Secondary Sponsor***");
	          CommonActionUtils.selectDropdownById(getLocator(108, 3), getData(2, 65), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.selectDropdownById(getLocator(109, 3), getData(2, 66), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.selectDropdownById(getLocator(111, 3), getData(2, 67), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.selectDropdownById(getLocator(112, 3), getData(2, 68), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(114, 3),getData(2, 69), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(115, 3), getData(2, 70), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(117, 3), getData(2, 71), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(119, 3), getData(2, 72), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(120, 3), getData(2, 73), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(121, 3), getData(2, 74), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.sendKeysById(getLocator(122, 3), getData(2, 75), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          CommonActionUtils.selectDropdownById(getLocator(123, 3), getData(2, 76), driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	       
	          CommonActionUtils.sendKeysById(getLocator(110, 3), getData(2, 77),driver);
	          BaseClass.logger.info(webElementName+" Is entered");
	          
	     BaseClass.logger.info("\n***Adding Payment Visit Information***");
	     CommonActionUtils.selectDropdownById(getLocator(136, 3), getData(2, 81), driver);
	     BaseClass.logger.info(webElementName+" Is entered");
	     
	     CommonActionUtils.sendKeysById(getLocator(138, 3), getData(2, 82), driver);
	     Thread.sleep(1000);
	     BaseClass.logger.info(webElementName+" Is entered");
	          
	     robot.mouseMove(400,680);
	     robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	     robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	  
	     CommonActionUtils.sendKeysById(getLocator(145, 3), getData(2, 86), driver);
	     Thread.sleep(1000);
	     robot.keyPress(KeyEvent.VK_ENTER);
	     Thread.sleep(1000);
	     robot.keyRelease(KeyEvent.VK_ENTER);
	     BaseClass.logger.info("successfully entered reffered By doctor. WebElement name is : "+webElementName);
	          
	     //System.out.println("Title before click "+driver.getTitle());
	     CommonActionUtils.clickById(getLocator(173, 3), driver);
	     BaseClass.logger.info("successfully clicked on Add icon. WebElement name is : "+webElementName);
	     //System.out.println("Title after click "+driver.getTitle());
	     
	     CommonActionUtils.sendKeysById(getLocator(182, 3), getData(2, 104), driver);
	     BaseClass.logger.info("Year Of Onset entered successfully. WebElement name is : "+webElementName);
	     
		CommonActionUtils.sendKeysById(getLocator(184, 3), getData(2, 34),driver);
		 BaseClass.logger.info("Remark entered successfully. WebElement name is : "+webElementName);
		 
		 CommonActionUtils.selectDropdownById(getLocator(224, 3), getData(2, 103), driver);
		 BaseClass.logger.info("Diagnossis Status selected successfully. Webelement name is : "+webElementName);
	     
	     CommonActionUtils.selectDropdownById(getLocator(176, 3),getData(2, 100), driver);
	     BaseClass.logger.info("successfully selected Diagnostic Type. WebElement name is : "+webElementName);
	     
	     CommonActionUtils.sendKeysById(getLocator(177, 3), getData(2, 102), driver);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        BaseClass.logger.info("successfully entered and selected primary Diagnostic Code. WebElement name is : "+webElementName);
	        
	      
	      try {  
	    	  Thread.sleep(4000);
	        alert = driver.switchTo().alert();
	        if(alert.getText().equalsIgnoreCase("Primary ICD is Invalid, it can be marked as Secondary. Choose a different Primary ICD to proceed"))
	        {
	         BaseClass.logger.error("Given Primery ICD is Invalid. please change icd in excel file.");
	        }
	        System.out.println("alert msg : "+alert.getText());
	        alert.accept();
	        Thread.sleep(4000);
	        
	       }
	   
	      catch(NoAlertPresentException e)
	      {

	       System.out.println("No alert");
	       CommonActionUtils.clearElementById(getLocator(177, 3), driver);
	       System.out.println("code got cleared...");
	       Thread.sleep(8000);
	       robot.keyPress(KeyEvent.VK_SPACE);
	       BaseClass.logger.warn("Given diagnosis code in excel sheet have no match. So selecting value at index(1)");
	       robot.keyRelease(KeyEvent.VK_SPACE);
	       System.out.println("waiting for 8 sec");
	       Thread.sleep(8000);
	       System.out.println("presssing the enter key....");
	       robot.keyPress(KeyEvent.VK_ENTER);
	       Thread.sleep(500);
	       robot.keyRelease(KeyEvent.VK_ENTER); 
	       System.out.println("enter key got released...");
	      }
	      
	      CommonActionUtils.sendKeysById(getLocator(177, 3), getData(2, 105), driver);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        BaseClass.logger.info("successfully entered and selected secondry Diagnostic Code. WebElement name is : "+webElementName);

	        try {  
		    	  Thread.sleep(4000);
		        alert = driver.switchTo().alert();
		        System.out.println("alert msg : "+alert.getText());
		        alert.accept();
		        Thread.sleep(4000);
		        
		       }
		   
		      catch(NoAlertPresentException e)
		      {

		       System.out.println("No alert");
		      }
	        
	      CommonActionUtils.clickById(getLocator(186, 3), driver);
	      BaseClass.logger.info("successfully clicked on Diagnosis cancle button. WebElement name is : "+webElementName);
	  
	      CommonActionUtils.clickById(getLocator(197, 3), driver);
	      BaseClass.logger.info("successfully clicked on register button. WebElement name is : "+webElementName);

	      try
			{
				alert = driver.switchTo().alert(); 
				System.out.println(alert.getText());
				alert.accept();
			}
			catch (NoAlertPresentException e) {
				
				System.out.println("This patient phone number is not exists in data base (it is unique)");
			}
			//BaseClass.logger.info("Clicked on register "+webElementName);
			try
			{
			String x = CommonActionUtils.getTextByXpath(getLocator(207, 3), driver);
			System.out.println("Text printed after patient got registered is : "+x);
			BaseClass.logger.info("Patient is registered successfuly "+webElementName);
			CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);

			return true;
			
			} 
			catch (NoSuchElementException e)
			{
				CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);
				return false;
			}
	  }

	//*******************OSP Registration ends**************************************//

	//*******************Pre Registration starts************************************//
	public boolean preRegistration(String decision) throws IOException, InterruptedException
	{
		runWithNewUniqueData(decision);//run code with unique data (NAme, MOb No, Old Reg No.)
		
		CommonActionUtils.selectDropdownByName(getLocator(201,3), getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+webElementName);
		
		CommonActionUtils.clickByXPath(getLocator(204,3), driver);
		BaseClass.logger.info("successfully clicked on Registration dropdown where element name is "+webElementName);
		
		CommonActionUtils.clickByXPath(getLocator(208, 3),driver);
		BaseClass.logger.info("successfully clicked on Pre-Registration link.WebElement name is : "+webElementName);
	
		//-----------Basic Information start ----------------------------//
		CommonActionUtils.selectDropdownById(getLocator(24, 3), getData(2, 7), driver);
		BaseClass.logger.info("succesfully selected salutation."); 
		
		CommonActionUtils.sendKeysById(getLocator(25, 3),getData(2, 8),driver);
		BaseClass.logger.info("succesfully entered first name.");
		
		CommonActionUtils.sendKeysById(getLocator(26, 3),getData(2, 9),driver);
		BaseClass.logger.info("succesfully entered middle name.");
		
		CommonActionUtils.sendKeysById(getLocator(27, 3),getData(2, 10),driver);
		BaseClass.logger.info("succesfully entered last name.");
		
		CommonActionUtils.sendKeysById(getLocator(28, 3),getData(2, 11),driver);
		BaseClass.logger.info("succesfully entered patient mobile number .");
		
		CommonActionUtils.sendKeysById(getLocator(31, 3),getData(2, 12),driver);
		BaseClass.logger.info("succesfully entered DOB Day.");
		
		CommonActionUtils.sendKeysById(getLocator(32, 3),getData(2, 13),driver);
		BaseClass.logger.info("succesfully entered DOB Month.");
		
		CommonActionUtils.sendKeysById(getLocator(33, 3),getData(2, 14),driver);
		BaseClass.logger.info("succesfully entered DOB Year.");
		
		CommonActionUtils.sendKeysById(getLocator(29, 3),getData(2, 15),driver);
		BaseClass.logger.info("succesfully entered patient Addln phone number.");
		
		CommonActionUtils.sendKeysById(getLocator(215, 3),getData(2, 18),driver);
		BaseClass.logger.info("succesfully entered patient contact number.");
		
		CommonActionUtils.sendKeysById(getLocator(37, 3),getData(2, 16),driver);
		BaseClass.logger.info("succesfully entered next of the kin name.");
		
		CommonActionUtils.sendKeysById(getLocator(216, 3),getData(2, 17),driver);
		BaseClass.logger.info("succesfully entered patient relation.");
	
		//From line 754 to 773 is important part of the code don't jumble any lines lines
        CommonActionUtils.sendKeysByName(getLocator(65, 3), getData(2, 37),driver);
        BaseClass.logger.info("successfully entered old Registration number. WebElement name is : "+webElementName);
		
		//-----------Basic Information ends ----------------------------//
			
		//-----------Additional Patient Information starts--------------//	
		BaseClass.logger.info("\n***Adding Patient Addational Information***");
		CommonActionUtils.sendKeysById(getLocator(44, 3), getData(2, 25),driver);
		/**
		 * Writing unique data for old Registration number
		 */
		Thread.sleep(5000);
			try{
					alert = driver.switchTo().alert(); 
					System.out.println(alert.getText());
					alert.accept();
				}
			catch (NoAlertPresentException e) {
					System.out.println("Old Reg number is unique)");
				}
		BaseClass.logger.info("Address of the patient entered successfully.WebElement name is : "+webElementName);
		CommonActionUtils.sendKeysById(getLocator(45, 3), getData(2, 26),driver); 
		BaseClass.logger.info("Area name entered successfully.WebElement name is : "+webElementName);
		CommonActionUtils.selectDropdownByName(getLocator(71, 3), getData(2, 28), driver);
		BaseClass.logger.info("Nationality of the patient entered successfully.WebElement name is : "+webElementName);
		CommonActionUtils.selectDropdownByName(getLocator(50, 3), getData(2, 29), driver);
		BaseClass.logger.info("patient consent collected for patient is successfully.WebElement name is : "+webElementName);
        CommonActionUtils.selectDropdownById(getLocator(66, 3), getData(2, 39), driver);
        BaseClass.logger.info("Religion entered successfully. WebElement name is : "+webElementName);
	
        
        CommonActionUtils.selectDropdownByName(getLocator(60, 3), getData(2, 38), driver);
        BaseClass.logger.info("Blood Group entered successfully. WebElement name is : "+webElementName);
        CommonActionUtils.sendKeysById(getLocator(61, 3), getData(2, 40),driver);
        BaseClass.logger.info("Occupation entered successfully. WebElement name is : "+webElementName);
        CommonActionUtils.sendKeysByXpath(getLocator(62, 3), getData(2, 42),driver);
        BaseClass.logger.info("Pass Port No entered successfully. WebElement name is : "+webElementName);
        CommonActionUtils.sendKeysByXpath(getLocator(63, 3),getData(2, 44),driver);
        BaseClass.logger.info("Mother Name entered successfully. WebElement name is : "+webElementName);
        CommonActionUtils.sendKeysByName(getLocator(64, 3), getData(2, 46),driver);
        BaseClass.logger.info("Family ID entered successfully. WebElement name is : "+webElementName);
        
		 CommonActionUtils.sendKeysByXpath(getLocator(67, 3), getData(2, 41),driver);
	       BaseClass.logger.info("Post Box No entered successfully. WebElement name is : "+webElementName);
		 CommonActionUtils.sendKeysByXpath(getLocator(68, 3), getData(2, 43), driver);
	       BaseClass.logger.info("Driving License No entered successfully. WebElement name is : "+webElementName);
		 CommonActionUtils.sendKeysByXpath(getLocator(69, 3), getData(2, 45),driver);
	       BaseClass.logger.info("Father Name No entered successfully. WebElement name is : "+webElementName);
	       
	    CommonActionUtils.sendKeysByName(getLocator(59, 3), getData(2, 36),driver);
	       BaseClass.logger.info("Next of Kin Address entered successfully. WebElement name is : "+webElementName);
		CommonActionUtils.sendKeysByName(getLocator(56, 3), getData(2, 35),driver); 
	       BaseClass.logger.info("Emirate ID entered successfully. WebElement name is : "+webElementName);
		CommonActionUtils.sendKeysById(getLocator(54, 3), getData(2, 33),driver);
	       BaseClass.logger.info("ID Type entered successfully. WebElement name is : "+webElementName);
		CommonActionUtils.sendKeysById(getLocator(55, 3), getData(2, 34),driver);
	       BaseClass.logger.info("Remark entered successfully. WebElement name is : "+webElementName);
		CommonActionUtils.sendKeysByXpath(getLocator(52, 3),getData(2, 31), driver);
		   BaseClass.logger.info(" Refferal Remark entered successfully. WebElement name is : "+webElementName);
		  /**
		   * Since refferal source is dependent on center so if data given in Excel is not matching the it will select value by 1st index 
		   */
		try{
			CommonActionUtils.selectDropdownById(getLocator(51, 3),getData(2, 30), driver);
			BaseClass.logger.info(" Refferal Source entered successfully. WebElement name is : "+webElementName);
		  }
		catch(NoSuchElementException e)
		{
			BaseClass.logger.info("given refferal source is not present in drop down so selecting refferral source by index(1).");
			CommonActionUtils.selectDropdownByIdUsingIndex(getLocator(51, 3),1, driver);
		}
		
		CommonActionUtils.clickById(getLocator(192, 3), driver);
		BaseClass.logger.info("Successfully clicked on SMS For Vaccination Reminder. WebElement name is : "+webElementName);
		
		CommonActionUtils.clickByName(getLocator(218, 3), driver);
		BaseClass.logger.info("successfully clicked on Register button. WebElement name is : "+webElementName);
		/*
		 * If data is not present in data base then this message will be printed.
		 */
		try
		{
			alert = driver.switchTo().alert(); 
			System.out.println(alert.getText());
			alert.accept();
		}
		catch (NoAlertPresentException e)
		{
			System.out.println("This patient phone number is not exists in data base (it is unique)");
		}
		
		/*
		 * After successfull login of the patient we need to validate the page for successful registration
		 */
		
		try
		{
		String x = CommonActionUtils.getTextByXpath(getLocator(219, 3), driver);//page header locator for success screeen
		System.out.println("Text printed after patient got registered is : "+x);
		BaseClass.logger.info("Patient is registered successfuly. WebElement name is : "+webElementName);
		CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);//navigating back to home page

		return true;
		
		} 
		catch (NoSuchElementException e)
		{
			CommonActionUtils.goToTheUrl(readConfigFile.getHomePageUrl(), driver);//navigating back to home page
			return false;
		}
	}
	//*******************Pre Registration ends************************************//
	//*******************New Born Registration starts*****************************//
	public boolean birthRegistration() throws IOException
	  {
	   CommonActionUtils.clickByXPath(getLocator(260, 3), driver);
	      BaseClass.logger.info("successfully clicked on "+webElementName);
	      CommonActionUtils.clickByHyperLink(getLocator(261, 3), driver);
	      BaseClass.logger.info("successfully clicked on "+webElementName);
	      CommonActionUtils.sendKeysByXpath(getLocator(262, 3), getRegInfoData(XLUtils.getRowCount(readConfigFile.getExcelRegInfoDataPath(), readConfigFile.getExcelRegInfoDataSheetName()), 1),driver);
	      
	      BaseClass.logger.info("successfully entered "+webElementName);
	      
	      CommonActionUtils.clickByXPath(getLocator(263, 3), driver);
	      
	      CommonActionUtils.clickByHyperLink(getLocator(272, 3), driver);
	      BaseClass.logger.info("successfully clicked on "+webElementName);
	      
	      CommonActionUtils.sendKeysById(getLocator(273, 3), getData(2, 10), driver);
	      BaseClass.logger.info("successfully entered "+webElementName);
	      
	      CommonActionUtils.clearElementById(getLocator(278, 3), driver);
	      
	      CommonActionUtils.sendKeysById(getLocator(278, 3), getData(2, 119), driver);
	      BaseClass.logger.info("successfully entered "+webElementName);
	      
	      CommonActionUtils.selectDropdownById(getLocator(276, 3), getData(2, 118), driver);
	      BaseClass.logger.info("successfully entered "+webElementName);
	      
	      CommonActionUtils.selectDropdownById(getLocator(277, 3), getData(2, 28), driver);
	      BaseClass.logger.info("successfully entered "+webElementName);

	      CommonActionUtils.clickById(getLocator(287, 3), driver);
	      BaseClass.logger.info("successfully clicked on "+webElementName);
	      
	      try
	   {
	   String x = CommonActionUtils.getTextByXpath(getLocator(207, 3), driver);
	   System.out.println(x);
	   BaseClass.logger.info("Patient is registered successfuly "+webElementName);
	   getDataFromRegSuccuss();
	   CommonActionUtils.goToTheUrl("http://10.8.55.122/instanmc/home.do", driver);// return to home page
	   return true;
	   
	   } 
	   catch (NoSuchElementException e) {
	    e.printStackTrace();
	    CommonActionUtils.goToTheUrl("http://10.8.55.122/instanmc/home.do", driver);
	    return false;
	   }

	  }
	  
	//*******************New Born Registration ends*****************************//

}
