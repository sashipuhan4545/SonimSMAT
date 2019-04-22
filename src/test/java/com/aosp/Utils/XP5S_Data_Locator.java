package com.aosp.Utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class XP5S_Data_Locator {
	
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public XP5S_Data_Locator(AndroidDriver<AndroidElement> aDriver) {
		  this.aDriver=aDriver;		  
	}
	
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"About phone\")")
	 public static AndroidElement About_Phone;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"More\")")
	 public static AndroidElement More;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\").textContains(\"ON\")")
	 public static AndroidElement airplanemodeenabledbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\").textContains(\"OFF\")")
	 public static AndroidElement airplanemodedisableddbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/widget_frame\")")
	 public static AndroidElement airplanemodewidget;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Status\")")
	 public static AndroidElement Sim_status;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/list_container\")")
	 public static AndroidElement Sim_status_Scroll;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"SIM status\")")
	 public static AndroidElement SIMSTATUS;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular network type']/..//android.widget.TextView[@resource-id='android:id/summary']")
	 public static AndroidElement cellularNetworktype_summary;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/content_frame\")")
	 public static MobileElement scrollingInSettingFrame;
	
	//Below code is for experiment
	
	
	

	
	
		
	
	
	

}
