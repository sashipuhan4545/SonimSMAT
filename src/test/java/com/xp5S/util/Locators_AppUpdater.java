package com.xp5S.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_AppUpdater{
	private static AndroidDriver<AndroidElement> aDriver;
	
		public Locators_AppUpdater(AndroidDriver<AndroidElement> aDriver) {
			this.aDriver=aDriver;		  
		}
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/app_thumbnail_image\")")
	public static AndroidElement SWR_RecentApp;	

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static AndroidElement recentApp;
	 
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static AndroidElement sonim_scout_AppLauncher;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apps']")
    public static AndroidElement nativeSettings_apps;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apps']")
    public static AndroidElement apps_homescreen;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.android.settings:id/app_snippet']")
    public static AndroidElement appInfo_appTitle;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
    public static AndroidElement settingsIcon;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
    public static AndroidElement appUpdater_app;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
    public static AndroidElement appUpdaterTitle;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"CHECK FOR UPDATES\")")
    public static AndroidElement checkForUpdateBtn;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Uninstall updates\")")
    public static AndroidElement uninstall_Updates;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim BLE Connect\")")
    public static AndroidElement sonimBLEconnect_AllApps;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Updater\")")
    public static AndroidElement updater_AllApps;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Unlock\")")
    public static AndroidElement unlock_AllApps;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
    public static AndroidElement appUpdaterAlert;
	
	@AndroidFindBy(uiAutomator= "new UiSelector()..className(\"android.widget.TextView\").text(\"Downloading updates...\")")
    public static AndroidElement downloadingProgressBar;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Checking for updates...\")")
    public static AndroidElement checkingForUpdatesBar;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
    public static AndroidElement cancel_PopUp;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Installing updates...\")")
    public static AndroidElement installingUpdatesBar;	

	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
    public static AndroidElement homeSoftButton;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"About\")")
    public static AndroidElement aboutBtn;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"All applications are up to date.\")")
    public static AndroidElement allAppsUptoDateText;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonimtech.sonimupdater:id/txt_last_updated']")
	public static AndroidElement lastCheckedText;	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	public static AndroidElement appUpdaterVersion;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
    public static AndroidElement uptoDateScreen_OkBtn;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
    public static AndroidElement OkBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.settings:id/switch_widget']")
	public static AndroidElement wifiBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static AndroidElement SetUp_Scout;
	
	
	
	
	
	
}



