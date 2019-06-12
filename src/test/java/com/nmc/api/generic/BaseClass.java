package com.nmc.api.generic;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.nmc.api.utilities.ReadConfigFile;

/*
 * setUP
 * Screen shot
 * close driver(tear down)
 */
public class BaseClass 
{
	ReadConfigFile readConfig = new ReadConfigFile();
	
	public String baseURL= readConfig.getApplicationURL();
	public String userID= readConfig.getUserName();
	public String password = readConfig.getPassword();
	
	public static WebDriver driver;
	public static Logger logger; 
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String br)		//all the prerequsite include in this.( br = bowser)
	{
		//configuration we have to do for the base class
		logger = Logger.getLogger("NMC-API-Testing"); //name of the project
		PropertyConfigurator.configure("log4j.properties");
		
		if(br.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
			driver = new ChromeDriver(); //Instantiation(i.e create chrome driver object)
		}
		else if(br.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			driver = new FirefoxDriver(); //Instantiation(i.e create firefox driver object)
		}
		else if(br.equalsIgnoreCase("ie"))
		{
			System.setProperty("webdriver.ie.driver", readConfig.getIEPath());
			driver = new InternetExplorerDriver();
		}
		
		driver.get(baseURL);
		logger.info("URL is opened where page title is : "+driver.getTitle());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@AfterClass
//	public void tearDown()
//	{
//		driver.quit();
//	}

}
