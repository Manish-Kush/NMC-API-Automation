package com.nmc.api.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nmc.api.generic.BaseClass;
import com.nmc.api.modules.RegistrationScreen;

public class TestRegistrationPage extends BaseClass
{
	RegistrationScreen regScreen;

	public TestRegistrationPage() throws AWTException 
	{
		regScreen = new RegistrationScreen();
	}
	
	@Test(priority = 1)
	public void testLogin() throws IOException
	{
		Assert.assertEquals(regScreen.login(),true ,"login is unsuccesfull. Please check the entered credentials");
	}

//	@Test(priority = 2, invocationCount = 1)
//	public void testOpRegistration() throws IOException, InterruptedException
//	{
//		Assert.assertEquals(regScreen.opRegistration(),true, "OP Registration was unsuccessful.!!");
//	}

	@Test(priority = 3, invocationCount = 1)
	public void testOspRegistration() throws IOException, InterruptedException
	{
		Assert.assertEquals(regScreen.ospRegistration(),true, "IP Registration was unsuccessful.!!");
	}
	
//	@Test(priority = 4, invocationCount = 1)
//	public void testPreRegistration() throws IOException, InterruptedException
//	{
//		Assert.assertEquals(regScreen.preRegistration(),true, "Pre Registration was unsuccessful.!!");
//	}
	
}
