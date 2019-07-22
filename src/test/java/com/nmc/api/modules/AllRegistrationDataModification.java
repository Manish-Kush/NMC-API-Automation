package com.nmc.api.modules;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;

import com.nmc.api.generic.BaseClass;
import com.nmc.api.utilities.CommonActionUtils;

public class AllRegistrationDataModification extends BaseClass
{
	RegistrationScreen regScreen;
	String textValue;
	Robot robot;
	Alert alert ;
	
	public AllRegistrationDataModification() throws AWTException
	{
		regScreen = new RegistrationScreen();
		robot = new Robot();

	}


	/****
	 * @return boolean value
	 * @throws IOException
	 */
	public boolean finalizeBillForOP() throws IOException
	{
		CommonActionUtils.selectDropdownByName(regScreen.getLocator(201,3), regScreen.getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+regScreen.webElementName);
		
		CommonActionUtils.clickByXPath(regScreen.getLocator(260, 3), driver);
		BaseClass.logger.info("successfully clicked on billing dropdown. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickByHyperLink(regScreen.getLocator(265, 3), driver);
		BaseClass.logger.info("successfully clicked on search bill link. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.sendKeysById(regScreen.getLocator(9, 3), regScreen.getRegInfoData(1, 0), driver);//taking the Mr.No from regInfo excel file
		BaseClass.logger.info("successfully copyedMR. No. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickById(regScreen.getLocator(236, 3), driver);
		BaseClass.logger.info("successfully clicked on Search Button. Webelement name is : "+regScreen.webElementName);

		//move the mouse to row0 and doing single click to get options
		regScreen.robot.mouseMove(500, 360);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		//move the mouse to row0 and doing single click on second link(Edit Patient )
		regScreen.robot.mouseMove(500,360);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		BaseClass.logger.info("successfully clicked on Search bill link . Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.selectDropdownById(regScreen.getLocator(297, 3),"Finalized", driver);//hard coding finalize status
		BaseClass.logger.info("successfully selected finalized from dropdown. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickByName(regScreen.getLocator(298, 3), driver);
		BaseClass.logger.info("successfully clicked Ok to Dischage checkbox. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickById(regScreen.getLocator(300, 3), driver);
		BaseClass.logger.info("successfully clicked Save Button. Webelement name is : "+regScreen.webElementName);
		return true;
		
	}
	/***
	 * @return	boolean value
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public boolean finalizeBillForIP() throws IOException, InterruptedException
	{
		CommonActionUtils.selectDropdownByName(regScreen.getLocator(201,3), regScreen.getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+regScreen.webElementName);
		
		CommonActionUtils.clickByXPath(regScreen.getLocator(260, 3), driver);
		BaseClass.logger.info("successfully clicked on billing dropdown. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickByHyperLink(regScreen.getLocator(265, 3), driver);
		BaseClass.logger.info("successfully clicked on search bill link. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.sendKeysById(regScreen.getLocator(9, 3), regScreen.getRegInfoData(3, 0), driver);//here we are geting Mr.No
		BaseClass.logger.info("successfully copyedMR. No. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickById(regScreen.getLocator(236, 3), driver);
		BaseClass.logger.info("successfully clicked on Search Button. Webelement name is : "+regScreen.webElementName);

		//move the mouse to row0 and doing single click to get options
		regScreen.robot.mouseMove(500, 360);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		
		String mainWindow = driver.getWindowHandle();
		
		//move the mouse to row0 and doing single click on second link(Edit Patient )
		regScreen.robot.mouseMove(500,360);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		BaseClass.logger.info("successfully clicked on Search bill link . Webelement name is : "+regScreen.webElementName);

		Thread.sleep(1000);
//		regScreen.robot.mouseMove(771,346);
//		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
//		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		((JavascriptExecutor)driver).executeScript("scroll(0,2000)");
		CommonActionUtils.clickByXPath(regScreen.getLocator(295, 3),  driver);
		BaseClass.logger.info("successfully clicked on Bed Details link. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickOnRadioButtonById(regScreen.getLocator(266, 3),  driver);
		BaseClass.logger.info("successfully click on finalizeBedCharges radiobutton. Webelement name is : "+regScreen.webElementName);
		
		System.out.println("test 5");
		Thread.sleep(2000);
		try {
			CommonActionUtils.clickById(regScreen.getLocator(267, 3),  driver);
			BaseClass.logger.info("successfully click on Save button for BedChargefinalize. Webelement name is : "+regScreen.webElementName);
			
			Alert alert = driver.switchTo().alert();
			System.out.println("alert msg is : "+alert.getText());
			alert.accept();
		}
		catch(UnhandledAlertException e)
		{
			System.out.println("catch block.");
			CommonActionUtils.clickById(regScreen.getLocator(267, 3),  driver);
		}

		CommonActionUtils.clickByXPath(regScreen.getLocator(296, 3),  driver);
		BaseClass.logger.info("successfully click on bill link. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.selectDropdownById(regScreen.getLocator(297, 3),"Finalized", driver);//hard coding finalize status
		BaseClass.logger.info("successfully selected finalized from dropdown. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickByName(regScreen.getLocator(298, 3), driver);
		BaseClass.logger.info("successfully clicked Ok to Dischage checkbox. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickById(regScreen.getLocator(300, 3), driver);
		BaseClass.logger.info("successfully clicked Save Button. Webelement name is : "+regScreen.webElementName);
		return true;
		
	}
	//*****************Physical discharge of the patient starts**************************//
	public boolean patientPhysicalDischarge() throws IOException, InterruptedException
	  {
	   // method to close the open bills of ip and op
	   
	   CommonActionUtils.clickByXPath(regScreen.getLocator(204, 3), driver);
	   BaseClass.logger.info("Clicked on "+regScreen.webElementName);
	   
	   CommonActionUtils.clickByHyperLink(regScreen.getLocator(292, 3), driver);
	   BaseClass.logger.info("Clicked on "+regScreen.webElementName);
	   
	   CommonActionUtils.sendKeysById(regScreen.getLocator(293, 3), regScreen.getData(2, 125), driver);
	   Thread.sleep(4000);
	   BaseClass.logger.info("Entered successfuly entered patient visit id.WebElement name is : "+regScreen.webElementName);
	   
	   robot.keyPress(KeyEvent.VK_ENTER);
	   robot.keyRelease(KeyEvent.VK_ENTER);
	   
	   CommonActionUtils.clickByXPath(regScreen.getLocator(294, 3), driver);
	   BaseClass.logger.info("Clicked on "+regScreen.webElementName);
	   
	   CommonActionUtils.selectDropdownById(regScreen.getLocator(303, 3), regScreen.getData(2, 130), driver);
	   BaseClass.logger.info("Selected "+regScreen.webElementName);
	   
	   CommonActionUtils.clearElementById(regScreen.getLocator(304, 3), driver);
	   CommonActionUtils.sendKeysById(regScreen.getLocator(304, 3), regScreen.getData(2, 131), driver);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");

	   CommonActionUtils.clearElementById(regScreen.getLocator(305, 3), driver);
	   CommonActionUtils.sendKeysById(regScreen.getLocator(305, 3), regScreen.getData(2, 132), driver);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");

	   CommonActionUtils.selectDropdownById(regScreen.getLocator(307, 3), regScreen.getData(2, 134), driver);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");
	   
	   CommonActionUtils.clearElementById(regScreen.getLocator(308, 3), driver);
	   CommonActionUtils.sendKeysById(regScreen.getLocator(308, 3), regScreen.getData(2, 131), driver);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");
	   
	   CommonActionUtils.clearElementById(regScreen.getLocator(309, 3), driver);
	   alert = driver.switchTo().alert(); 
	   System.out.println(alert.getText());
	   alert.accept();
	   CommonActionUtils.sendKeysById(regScreen.getLocator(309, 3), regScreen.getData(2, 132), driver);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");
	   
	   CommonActionUtils.sendKeysById(regScreen.getLocator(310, 3), regScreen.getData(2, 135), driver);
	   Thread.sleep(2000);
	   robot.keyPress(KeyEvent.VK_ENTER);
	   robot.keyRelease(KeyEvent.VK_ENTER);
	   BaseClass.logger.info(regScreen.webElementName+" Entered successfuly");
	   
	   CommonActionUtils.clickByName(regScreen.getLocator(311, 3), driver);
	   BaseClass.logger.info("Clicked on "+regScreen.webElementName);  
	  if(CommonActionUtils.getTextByXpath(regScreen.getLocator(302, 3), driver).equals("Discharged"))
	  {
	   return true;
	  }
	  else return false;
	  }
	  

	//*****************Physical discharge of the patient ends**************************//

	//*******************Edit Patient Details starts*************************************//
	public boolean editPatientDetails() throws IOException, InterruptedException
	{
		CommonActionUtils.selectDropdownByName(regScreen.getLocator(201,3), regScreen.getData(2, 47), driver);
		BaseClass.logger.info("successfully change center to NMC Royal and webelement name is "+regScreen.webElementName);
		
		CommonActionUtils.clickByXPath(regScreen.getLocator(231, 3), driver);
		BaseClass.logger.info("successfully click on search Dropdown. WebElement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickByHyperLink(regScreen.getLocator(232, 3), driver);
		BaseClass.logger.info("successfully clicked on Active hyper link. Webelement name is "+regScreen.webElementName);
		
		CommonActionUtils.sendKeysById(regScreen.getLocator(9, 3), regScreen.getRegInfoData(2, 1), driver);
		BaseClass.logger.info("successfully copyedMR. No. Webelement name is : "+regScreen.webElementName);
		
		CommonActionUtils.clickById(regScreen.getLocator(236, 3), driver);
		BaseClass.logger.info("successfully clicked on Search Button. Webelement name is : "+regScreen.webElementName);

		//move the mouse to row0 and doing single click to get options
		regScreen.robot.mouseMove(500, 360);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		
		
		String mainWindow = driver.getWindowHandle();
		
		//move the mouse to row0 and doing single click on second link(Edit Patient )
		regScreen.robot.mouseMove(500,400);
		regScreen.robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		regScreen.robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		BaseClass.logger.info("successfully clicked on Edit patient details link . Webelement name is : "+regScreen.webElementName);
		
		Thread.sleep(2000);//this wait is important for switching to window
		
		Set<String> allTabAddress =driver.getWindowHandles();

		for(String addr : allTabAddress)
		{
			if(!mainWindow.equalsIgnoreCase(addr))
			{
				driver.switchTo().window(addr);
			}
		}
		System.out.println("Title of the page is : "+driver.getTitle());
		//=============== Validation Basic Details starts ==========================
		//*******************Basic Information start********************************//

		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(25, 3),"value", driver);
		System.out.println("textValue 1 : "+textValue);
		boolean a =textValue.equalsIgnoreCase(regScreen.getData(2, 8));
		System.out.println("regValue is : "+regScreen.getData(2, 8));
		BaseClass.logger.info("patient first name copyed succesfully. WebElement name is : "+regScreen.webElementName+" & boolean value is : "+a);
		
		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(26, 3),"value", driver);
		boolean b = textValue.equalsIgnoreCase(regScreen.getData(2, 9));
		BaseClass.logger.info("patient Middle name copyedsuccesfully. WebElement name is : "+regScreen.webElementName+" & boolean value is : "+b);
		
		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(27, 3),"value",driver);
		boolean c = textValue.equalsIgnoreCase(regScreen.getData(2, 10));
		BaseClass.logger.info("patient Last name copyed succesfully. WebElement name is : "+regScreen.webElementName+" & boolean value is : "+c);

//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(28, 3),"value",driver);
//		BaseClass.logger.info("patient Mobile No copyed succesfully. WebElement name is : "+regScreen.webElementName);
//		boolean d = textValue.equalsIgnoreCase(regScreen.getData(2, 11));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(31, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyedDOB Day. WebElement name is : "+regScreen.webElementName);
//		boolean e = textValue.equalsIgnoreCase(regScreen.getData(2, 12));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(32, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed DOB Month. WebElement name is : "+regScreen.webElementName);
//		boolean f = textValue.equalsIgnoreCase(regScreen.getData(2, 13));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(33, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed DOB Year. WebElement name is : "+regScreen.webElementName);
//		boolean g = textValue.equalsIgnoreCase(regScreen.getData(2, 14));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(29, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed patient Addln phone number. WebElement name is : "+regScreen.webElementName);
//		boolean h = textValue.equalsIgnoreCase(regScreen.getData(2, 15));
		
//		textValue=CommonActionUtils.getAttributeValueUsingName(regScreen.getLocator(30, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed patient contact number. WebElement name is : "+regScreen.webElementName);
//		boolean i = textValue.equalsIgnoreCase(regScreen.getData(2, 18));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(37, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed next of the kin name. WebElement name is : "+regScreen.webElementName);
//		boolean j = textValue.equalsIgnoreCase(regScreen.getData(2, 16));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(216, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed patient relation. WebElement name is : "+regScreen.webElementName);
//		boolean k = textValue.equalsIgnoreCase(regScreen.getData(2, 17));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(40, 3),"value",driver);
//		BaseClass.logger.info("succesfully copyed patient case file No. WebElement name is : "+regScreen.webElementName);
//		boolean l = textValue.equalsIgnoreCase(regScreen.getRegInfoData(2, 6));//taking case file no from RegInfoData excel
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(65, 3),"value" ,driver);
//		BaseClass.logger.info("successfully copyed patient old reg No. WebElement name is : "+regScreen.webElementName);//old reg no
//		boolean m = textValue.equalsIgnoreCase(regScreen.getData(2, 37));
		//*******************Basic Information ends********************************//

		//*******************Additional Patient Information start************************//
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(45, 3),"value" ,driver);
//		BaseClass.logger.info("successfully copyed patient Area Name. WebElement name is : "+regScreen.webElementName);//old reg no
//		boolean n = textValue.equalsIgnoreCase(regScreen.getData(2, 26));
//		
//		textValue=CommonActionUtils.getAttributeValueUsingId(regScreen.getLocator(65, 3),"value" ,driver);
//		BaseClass.logger.info("successfully copyed patient city Name. WebElement name is : "+regScreen.webElementName);//old reg no
//		boolean o = textValue.equalsIgnoreCase(regScreen.getData(2, 27));
		
		//*******************Additional Patient Information ends ************************//

		
		//=============== Validation Basic Details ends ============================
		//&&d&&e&&f&&g&&h&&i&&j&&k&&l&&m 
		if(a&&b&&c == true)
		{
			return true;
		}
		return false;

	}
	//*******************Edit Patient Details ends*************************************//
}
