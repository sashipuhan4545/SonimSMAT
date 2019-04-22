package com.xp5S.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Calculator {

private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Calculator(AndroidDriver<AndroidElement> aDriver) {
		  this.aDriver=aDriver;
		  
		 }
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.calculator2:id/panelswitch\")")
	   public static WebElement calc_Dialog_Pad;

//	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='0']/android.support.v4.view.ViewPager[@index='1']"))
//	 public static WebElement calc_Dialog_Pad;
//	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DEL\")")
	 public static WebElement calc_Delete_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
	public static WebElement calc_Edit_text_field;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Advanced panel\")")
	public static WebElement advanced_Panel;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='^']")
	public static WebElement power;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='=']")
	public static WebElement equal;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='π']")
	public static WebElement pie;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='e']")
	public static WebElement exponential;
}
