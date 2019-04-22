package com.xp3.Utils;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Contact_Transfer {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Contact_Transfer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	//Added comment to test
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allowButton; 
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;
	
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
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;
	
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
	
	/*@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SouthernLINC\")")
	public static AndroidElement mdbContact;
	*/
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.sonim.scout.contact:id/view_parent']/../..//android.widget.TextView[@text='SouthernLINC']")
	public static AndroidElement mdbContact;
	/*@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.sonim.scout.contact:id/search_bar_bottom_lyt']/../..//android.widget.TextView[@text='SouthernLINC']")
	public static AndroidElement mdbContact;*/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"My Notifications\")")
	public static AndroidElement myNotification;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select\")")
	public static AndroidElement select_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelesctor().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select all\")")
	public static AndroidElement Select_all_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static AndroidElement delete_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement OK_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;
	
/*	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"OK\")")
	public static AndroidElement selectAll_checkbox;*/

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.sonim.scout.contact:id/search_bar_bottom_lyt']/../..//android.widget.Button[@text='OK(5)']")
	public static AndroidElement selectAll_checkbox;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@checked='true']")
	public static AndroidElement selectAll_check;
}
