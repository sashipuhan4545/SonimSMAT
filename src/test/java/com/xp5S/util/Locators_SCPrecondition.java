package com.xp5S.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Locators_SCPrecondition{
	
	public static WebDriver wdriver;
	
	
public static void launch() {
	System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
	WebDriver wdriver = new ChromeDriver();
	wdriver.get("http://test.sonimcloud.com/");  
 }

}
