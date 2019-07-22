package com.nmc.api.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nmc.api.generic.BaseClass;
import com.nmc.api.modules.AllRegistrationDataModification;
import com.nmc.api.modules.RegistrationScreen;

public class TestRegistrationPage extends BaseClass
{
	RegistrationScreen regScreen;
	AllRegistrationDataModification regDataMod;

	public TestRegistrationPage() throws AWTException 
	{
		regScreen = new RegistrationScreen();
		regDataMod = new AllRegistrationDataModification();
	}
	
	@Test(priority = 1)
	public void testLogin() throws IOException
	{
		Assert.assertEquals(regScreen.login(),true ,"login is unsuccesfull. Please check the entered credentials");
	}

	@Test(priority = 2, invocationCount = 50)
	public void testOpRegistration() throws IOException, InterruptedException
	{
		//if Yes is passed then it will register patient with new data other wise it will take existing data from excel sheet.
		Assert.assertEquals(regScreen.opRegistration("Yes"),true, "OP Registration was unsuccessful.!!");
	}
//	
//	@Test(priority = 3, invocationCount = 5)
//	public void testIpRegistration() throws IOException, InterruptedException
//	{
//		Assert.assertEquals(regScreen.ipRegistration("Yes"), true, "IP Registration was unsuccessful.!!");
//	}
//
//	@Test(priority = 4, invocationCount = 1)
//	public void testOspRegistration() throws IOException, InterruptedException
//	{		
//		Assert.assertEquals(regScreen.ospRegistration("Yes"),true, "OSP Registration was unsuccessful.!!");
//	}
//	
//	@Test(priority = 5, invocationCount = 1)
//	public void testPreRegistration() throws IOException, InterruptedException
//	{
//		Assert.assertEquals(regScreen.preRegistration("Yes"),true, "Pre Registration was unsuccessful.!!");
//	}
	
//	@Test(priority = 6)
//	public void testEditPatientDetails() throws IOException, InterruptedException
//	{
//		//Assert.assertEquals(regDataMod.editPatientDetails(),true ,"Editing Patient details got failed.!!");
//		//Assert.assertEquals(regDataMod.finalizeBillForOP(),true ,"Finalizing of the bill got failed.!!");
//		Assert.assertEquals(regDataMod.finalizeBillForIP(),true ,"Finalizing of the bill got failed.!!");
//
//	}
	
}
