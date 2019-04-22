package com.xp5S.util;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_ProgrammableKey {
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_ProgrammableKey(AndroidDriver<AndroidElement> aDriver) {
		  this.aDriver=aDriver;
		  
		 }
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    public static WebElement settingsIcon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	 public static WebElement recentApp;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Programmable Key']")
    public static WebElement programmableKey;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
    public static WebElement summaryText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Application']")
    public static WebElement no_Application_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
    public static WebElement app_Updater_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Appium Settings']")
    public static WebElement appium_Settings_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='AT&T EPTT']")
    public static WebElement att_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup and Restore']")
    public static WebElement backUp_restore_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Browser']")
    public static WebElement browser_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bug Reporter Tool']")
    public static WebElement bugReporterTool_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calculator']")
    public static WebElement calculator_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calendar']")
    public static WebElement calendar_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Camera']")
    public static WebElement camera_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clock']")
    public static WebElement clock_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
    public static WebElement contactTransfer_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
    public static WebElement contacts_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
    public static WebElement downloads_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Explorer']")
    public static WebElement fileExplorer_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
    public static WebElement fmRadio_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Gallery']")
    public static WebElement gallery_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
    public static WebElement messaging_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
    public static WebElement music_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
    public static WebElement phone_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    public static WebElement settings_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
    public static WebElement sonimBLE_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Scout']")
    public static WebElement sonimScout_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Warranty Registration']")
    public static WebElement sonimScoutWarranty_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
    public static WebElement sonimCare_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
    public static WebElement setUpWizard_app;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sound Recorder']")
    public static WebElement soundRecorder_app;	
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.android.calculator2:id/simple_pad_circle']")
    public static WebElement calc_Page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/action_bar_title']")
    public static WebElement calendar_Page;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
    public static WebElement camera_Page;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.android.deskclock:id/main_clock_frame']")
    public static WebElement clock_Page;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='com.sonimtech.easycontactsshare:id/toolbar']")
    public static WebElement contact_transfer_page;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Find contacts']")
    public static WebElement contacts_native_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
    public static WebElement downloads_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
    public static WebElement appUpdates_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Explorer']")
    public static WebElement fileExplorer_page;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.caf.fmradio:id/nav_circle']")
    public static WebElement fmRadio_page;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='android:id/action_bar']")
    public static WebElement gallery_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
    public static WebElement messaging_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Artists']")
    public static WebElement music_page;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/dialer_digits']")
    public static WebElement phone_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    public static WebElement setting_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
    public static WebElement sonimBLE_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
    public static WebElement sonimSetUp_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.scout:id/tab_title']")
    public static WebElement sonimScout_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Warranty Registration']")
    public static WebElement warrantyReg_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SONIMCARE']")
    public static WebElement sonimCare_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
    public static WebElement setUpWizard_page;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sound Recorder']")
    public static WebElement soundRecorder_page;
	
	
	

	
	
	

}
