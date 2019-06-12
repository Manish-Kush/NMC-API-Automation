package com.nmc.api.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CommonActionUtils 
{
	//
	public static void clickById(String id, WebDriver driver) {
		driver.findElement(By.id(id)).click();
	}
	public static void clickByXPath(String xPath,WebDriver driver) {
		driver.findElement(By.xpath(xPath)).click();
	}
	public static void clickByName(String name, WebDriver driver) {
		driver.findElement(By.name(name)).click();
	}
	public static void clickByHyperLink(String linkTextName, WebDriver driver) {
		driver.findElement(By.linkText(linkTextName)).click();
	}
	
	
	public static void selectDropdownById(String id, String option, WebDriver driver) {
		Select dd = new Select(driver.findElement(By.id(id)));
		dd.selectByVisibleText(option);
	}
	public static void selectDropdownByXpath(String xpath,String option,WebDriver driver){
		Select dd = new Select(driver.findElement(By.xpath(xpath)));
		dd.selectByVisibleText(option);
	}
	public static void selectDropdownByIdUsingIndex(String id, int index, WebDriver driver) {
		Select dd = new Select(driver.findElement(By.id(id)));
		dd.selectByIndex(index);
	}
	public static void selectDropdownByName(String name,String option,WebDriver driver) {
		Select dd = new Select(driver.findElement(By.name(name)));
		dd.selectByVisibleText(option);
	}
	
	
	public static void sendKeysById(String id,String value,WebDriver driver){
		driver.findElement(By.id(id)).sendKeys(value);
	}
	public static void sendKeysByName(String name, String value ,WebDriver driver){
		driver.findElement(By.name(name)).sendKeys(value);
	}
	public static void sendKeysByXpath(String xpath,String value, WebDriver driver ) {
		driver.findElement(By.xpath(xpath)).sendKeys(value);
	}
	
	
	public static String getTextById(String id, WebDriver driver){
		return driver.findElement(By.id(id)).getText();
	}
	public static String getTextByXpath(String xpath, WebDriver driver){
		return driver.findElement(By.xpath(xpath)).getText();
	}
	
	public static void clickOnRadioButtonById(String radioButtonId,WebDriver driver)
	{
		driver.findElement(By.id(radioButtonId)).click();
	}
	
	
	public static void goToTheUrl(String url, WebDriver driver)
	{
		driver.navigate().to(url);
	}
	public static void clearElementById(String id, WebDriver driver) 
	{
		driver.findElement(By.id(id)).clear();
	}

}
