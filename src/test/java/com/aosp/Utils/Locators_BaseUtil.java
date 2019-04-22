package com.aosp.Utils;

import org.openqa.selenium.WebElement;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_BaseUtil {	
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_BaseUtil(AndroidDriver<AndroidElement> aDriver) {
		Locators_BaseUtil.aDriver=aDriver;
	}
	
	//uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Remove all\")"
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Remove all']")
	public static AndroidElement removeAll;
	
	//xpath="//android.widget.RelativeLayout[@resource-id='com.android.systemui:id/recent_item']"
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.systemui:id/recent_item\")")
	public static AndroidElement recentApp;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Your recent screens appear here\")")
	public static AndroidElement noRecentApp;	
	
	 @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/app_thumbnail_image\")")
	 public static WebElement SWR_RecentApp;
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Browser\")")
	 public static AndroidElement browser_App;
	
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
		public static AndroidElement contacts;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
		public static AndroidElement phone_DailerApp;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Scout']")
		public static AndroidElement scoutApp_icon;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Applications']")
		public static AndroidElement applications_icon;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
		public static AndroidElement music_icon;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
		public static AndroidElement FM_App;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Explorer']")
		public static AndroidElement FileExplorer_App;
		

		@AndroidFindBy(xpath = "//android.widget.TextView[@text='FillMemory']")
		public static AndroidElement fillmemoryIcon;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Files']")
		public static AndroidElement Files_App;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup and Restore']")
		public static AndroidElement backUP_reset;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
		public static AndroidElement downloads_icon;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
		public static AndroidElement settings;
		
		@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
		//com.sonim.borqs.launcher:id/icon_text
		public static AndroidElement camera_Icon;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Camera']")
		//com.sonim.borqs.launcher:id/icon_text
		public static AndroidElement cameraApp;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Calendar']")
		public static AndroidElement CalenderApp;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Tools']")
		public static AndroidElement ToolsOptn;

		@AndroidFindBy(xpath="//android.widget.TextView[@text='Calculator']")
		public static AndroidElement calciApp;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
		public static AndroidElement clockApp;
		
		@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Clock']")
		public static AndroidElement clock_Icon;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
		public static AndroidElement RecorderApp;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Recorder']")
		public static AndroidElement RecorderAppSB;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Gallery']")
		public static AndroidElement gallery_icon;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
		public static AndroidElement messaging_icon;
		 
		 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"AT&T EPTT\")")
			public static AndroidElement ATT_PTT;
			
			@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"SDC Plus\")")
			public static AndroidElement sprint_PTT;
			
			@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Push-to-Talk\")")
			public static AndroidElement bell_PTT;
			
			@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"TELUS Link\")")
			public static AndroidElement telusLink_PTT;
			
			@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Nova Talk\")")
			public static AndroidElement rogers_PTT;
			
			@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Linc PTT\")")
			public static AndroidElement sl_PTT;
		
		 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/content_frame\")")
	     public static MobileElement SettingsList;
		 
		 
		
	
}
