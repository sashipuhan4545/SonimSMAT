package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Contacts {
	

	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Contacts(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contacts\")")
	public static AndroidElement contacts;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Show roots']")
	public static AndroidElement MoreOptions;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement contactsSettingsOPt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement contactsDefaultAccountSettings;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Import']")
	public static AndroidElement contactsImportSettings;
	
	//@AndroidFindBy(xpath="//*[@text='.vcf file']")
	@AndroidFindBy(xpath="//*[contains(@text,'.vcf file')]")
	public static AndroidElement vcfFile;
	
	@AndroidFindBy(xpath="//*[@text='PHONE']")
	public static AndroidElement PHONE_RadioBtn;
	
	@AndroidFindBy(xpath="//*[@text='SAVE']")
	public static AndroidElement Save_Btn;
	
	@AndroidFindBy(xpath="//*[@text='Downloads']")
	public static AndroidElement DownloadsOption;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='3']/../..//android.widget.TextView[@text='i476.vcf']")
	public static AndroidElement VcfFileOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static WebElement one_Selection_menu;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement MoreOptnsIcn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete']"))
	public static AndroidElement deleteContactOptn1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your contacts list is empty']")
	public static AndroidElement no_Contacts;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	public static AndroidElement OKBtn1;
	 
	 

	
}
