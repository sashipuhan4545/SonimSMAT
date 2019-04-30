package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class XP8_Locators_ContactTransfer {

	private static AndroidDriver<AndroidElement> aDriver;
	
	public XP8_Locators_ContactTransfer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Scout.']")
	public static WebElement DismissIconCare;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static WebElement AppListIcon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Contact Transfer']")
	public static AndroidElement contactTransfer;	

	@AndroidFindBy(xpath ="//android.widget.Button[@text='ACCEPT']")
	public static WebElement AcceptBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferApp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via Bluetooth\")")
	public static WebElement BluetoothOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via MDB\")")
	public static WebElement MDBOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via VCF\")")
	public static WebElement VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via CSV\")")
	public static WebElement CSVOptn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Manager']")
	public static AndroidElement FileManager_App;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Download\")")
	public static WebElement Download_folder;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;
	
	@AndroidFindBy(xpath = "//android.support.v7.widget.LinearLayoutCompat[@resource-id='com.sonim.scout.contact:id/search_field']/../..//android.widget.CheckBox[@resource-id='com.sonim.scout.contact:id/select_all_check']")
	public static AndroidElement select_all_checkbox;
	
/*	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.sonim.scout.contact:id/select_all_check\")")
	public static AndroidElement select_all_checkbox;
	*/
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.sonim.scout.contact:id/btn_ok\")")
	public static AndroidElement ok_btn;
	
/*	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messaging_Title;
	*/
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.sonim.scout.callscreening:id/fab_new_mesage\")")
	public static AndroidElement message_compose;
	
	@FindBy(xpath="//android.widget.TextView[@text='No recent items']")
	public static AndroidElement no_Recent_Items;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.google.android.googlequicksearchbox:id/default_search_widget\")")
	public static AndroidElement google_search_bar;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
	public static AndroidElement connectedDevices_oreo;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement Bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contact Transfer wants to turn on Bluetooth\")")
	public static AndroidElement enable_BT_popup;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Searching…\")")
	public static AndroidElement searchingBT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Scan\")")
	public static AndroidElement scan_Button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DENY\")")
	public static AndroidElement deny_Button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Bluetooth will need to be enabled to use the BT Transfer feature.\")")
	public static AndroidElement deny_popup;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;
	
	
}
