package com.xp8.util;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Wifi {
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Wifi(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	
	
	
	
	
	
	
		
	//**********************Bluetooth*************************
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Bluetooth\")")
	public static AndroidElement Bluetooth;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"scanning settings\")")
	public static AndroidElement scanningsetting;
	
	//************************ Network & Setting Elements*************************
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement Network_Internet_Lnk;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Airplane mode\")")
	public static AndroidElement Airplanemode;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
	public static AndroidElement Connected_devices;	
	
	@AndroidFindBy(uiAutomator 	= "new UiSelector().className(\"android.widget.TextView\").text(\"Battery\")")
	public static AndroidElement Battery;	
	
	@FindBy(id = "android:id/empty")
	public static AndroidElement BT_ScngOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_bar\")")
	public static AndroidElement BTSwitch;	
	
	
	//***********************Wifi*****************************

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement WiFi_Lnk;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").index(0)")
	public static AndroidElement Wifi_Switch_Btn; 
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\")")
	public static AndroidElement Switch; 
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ProgressBar\").resourceId(\"com.android.settings:id/progress_bar_animation\")")
	public static AndroidElement ScanningProgress; 
	
	@FindBy(xpath = "//android.widget.LinearLayout[@index=1]")
	public static AndroidElement Wifi_ListLnk;
	
	@FindBy(id="com.android.settings:id/password")
	public static AndroidElement Wifi_PasswordTxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(1)")
	public static AndroidElement wifi_enterPwd_index; // index
	
	@FindBy(id="android:id/button1")
	public static AndroidElement Wifi_connectBtn;
	
	@FindBy(id="android:id/summary")
	public static AndroidElement Wif_summaryTxt;
	
	@FindBy(xpath="//android.widget.TextView[@text='Add network']")
	public static AndroidElement Add_network;
	
	@FindBy(xpath="//android.widget.TextView[@text='Saved networks']")
	public static AndroidElement Saved_network;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Enter the SSID\")")
	public static AndroidElement Wifi_ssidTxtBx;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Peer devices\")")
	public static AndroidElement Peer_devices;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Connected\")")
	public static AndroidElement Connected;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"FORGET\")")
	public static AndroidElement FORGET;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"CANCEL\")")
	public static AndroidElement CANCEL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckBox\").text(\"Advanced options\")")
	public static AndroidElement Advanced_options;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Advanced\")")
	public static AndroidElement Advanced;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"preferences\")")
	public static AndroidElement WiFi_preferences;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Install certificates\")")
	public static AndroidElement Install_certificates;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Location\")")
	public static AndroidElement Location;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Security & location\")")
	public static AndroidElement SecurityAndLocation;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Scanning\")")
	public static AndroidElement Scanning;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"System\")")
	public static AndroidElement System;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"About phone\")")
	public static AndroidElement Aboutphone;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Status\")")
	public static AndroidElement Status;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Spinner\")")
	public static AndroidElement Spinner;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"None\")")
	public static AndroidElement None ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Manual\")")
	public static AndroidElement Manual;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Proxy Auto\")")
	public static AndroidElement Proxy_Auto_Config;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Recent\")")
	public static AndroidElement Recent;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Spinner\").resourceId(\"com.android.settings:id/ip_settings\")")
	public static AndroidElement IpsettingSpinner;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"DHCP\")")
	public static AndroidElement DHCP;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Static\")")
	public static AndroidElement Static;
	
	@AndroidFindBy(uiAutomator="new UiSelector().descriptionContains(\"Open settings.\")")
	public static AndroidElement openSettings;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Edit order of settings.\")")
	public static AndroidElement pencil;
	
	@AndroidFindBy(uiAutomator="new UiSelector().description(\"More options\")")
	public static AndroidElement MoreOptions;

	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Reset\")")
	public static AndroidElement Reset;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").descriptionContains(\"Wi-Fi\")")
	public static AndroidElement Wifi_img;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").descriptionContains(\"Connected\")")
	public static AndroidElement Connected_SignalLevel;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Wi-Fi\")")
	public static AndroidElement Wifi_icon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Flashlight\")")
	public static AndroidElement Flashlight_icon;

	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Forget network\")")
	public static AndroidElement Forgetnetwork;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Connect to network\")")
	public static AndroidElement Connect_to_network;


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Cast\")")
	public static AndroidElement Cast_icon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(0)")
	public static AndroidElement Wi_Fi_scanning;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(1)")
	public static AndroidElement Bluetooth_scanning;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"MAC address\").clickable(false)")
	public static AndroidElement MAC_address;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"IP address\").clickable(false)")
	public static AndroidElement IP_address;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"android:id/list\")")
	public static AndroidElement wifi_list;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Spinner\")")
	public static AndroidElement Wifi_securityLst;
	
	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Open' or @text='None']")
	public static AndroidElement WiFi_securityLst_Open;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"WEP\")")
	public static AndroidElement WiFi_securityLst_WEP;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"WPA/WPA2 PSK\")")
	public static AndroidElement WiFi_securityLst_WPA_WPA2PSK;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"802.1x EAP\")")
	public static AndroidElement WiFi_securityLst_802_1x_EAP;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SAVE\")")
	public static AndroidElement WiFi_saveBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please select\")")
	public static AndroidElement WiFi_802_PlzSelectDrpDwn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Do not validate\")")
	public static AndroidElement WiFi_802_dontvalidateLst;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Password\")")
	public static AndroidElement WiFi_802_pswdBx;
	
	@FindBy(id="com.android.settings:id/password")
	public static AndroidElement WiFi_802_pswdTxtBx;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Saved networks\")")
	public static AndroidElement WiFi_savedNtw;
	
	@FindBy(id="com.android.settings:id/show_password")
	public static AndroidElement WiFi_showpswdBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
	public static AndroidElement Wifi_ConnectedSummary;
	
	@FindBy(id="com.android.settings:id/forget_button")
	public static AndroidElement WiFi_FrgtBtn;
	
	@FindBy(id="android:id/switch_widget")
	public static AndroidElement ArplnMode;
	
	@FindBy(xpath = "//android.widget.Switch[@content-desc='Wi-Fi,']/android.widget.FrameLayout[@index=0]")
	public static AndroidElement Wifi_QuickTurnOnBtn;
	
	@FindBy(xpath = "//android.widget.Button[@content-desc='Open Wi-Fi settings.']")
	public static AndroidElement Wifi_QuickTurnSetting;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MORE SETTINGS\")")
	public static AndroidElement WifiQuick_MoresettingBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Security & location\")")
	public static AndroidElement Security_LocationLnk;
	
	@FindBy(id = "android:id/button3")
	public static AndroidElement Wifi_FrgtBtn;
	
	@FindBy(id = "android:id/button2")
	public static AndroidElement Wifi_CnclBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='MAC address']/../..//android.widget.TextView[@index='1']")
	public static AndroidElement Wifi_MacAddress;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IP address']/../..//android.widget.TextView[@index='1']")
	public static AndroidElement Wifi_IpDetails;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Recent\")")
	public static AndroidElement RecentTxt_StrgLoc;
	
	@FindBy(id = "com.google.android.youtube:id/error_message_text")
	public static AndroidElement Wifi_youtubeErrorMsg;
	
	@FindBy(id = "android:id/edit")
	public static AndroidElement Wifi_editOptNotification;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Accessibility\")")
	public static AndroidElement setting_AccesibilityLnk;
	
	@FindBy(xpath="//android.widget.LinearLayout[@chekable='false']/android.widget.TextView[@text='Auto-rotate screen']/android.widget.Switch[@[@chekable='true']")
	public static AndroidElement Accesibility_AutoRotateLnk;
	
	@FindBy(xpath = "//android.widget.Switch[@instance=4]")
	public static AndroidElement Accessibility_AutoRotateBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connect to network\")")
	public static AndroidElement Wifi_ConnectToNtwrkBtn;
	
	@FindBy(xpath = "//android.support.v7.widget.RecyclerView[@index=0]/android.widget.LinearLayout[@enabled='true']/android.widget.RelativeLayout[@enabled='true']/android.widget.TextView[@enabled='true']")
	public static List<AndroidElement> Wifi_WifiDetails;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IP address']/../..//android.widget.TextView[@index='1']")
	public static AndroidElement Abtphone_IpDetails;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").instance(0)")
	public static AndroidElement WifiScanningBtn;
	
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").instance(1)")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth scanning']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement BluetoothScanningBtn;
	
	@FindBy(id = "android:id/button1")
	public static AndroidElement Wifi_scngOKBtn;
	
	@FindBy(id ="img_0_srch_hungama_48394806")
	public static AndroidElement wynk_firstLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Hotspot & tethering\")")
	public static AndroidElement HotspotLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Set up\")")
	public static AndroidElement Hotspot_setUpLnk;
	
	@FindBy(id ="com.android.settings:id/ssid")
	public static AndroidElement Hotspot_ssidTxtBx;
	
	@FindBy(id ="com.android.settings:id/password")
	public static AndroidElement Hotspot_PassTxtBx;
	
	@FindBy(id ="com.android.settings:id/security")
	public static AndroidElement Hotspot_securityDrpDwn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Open\")")
	public static AndroidElement Hotspot_security_Open;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"WPA2 PSK\")")
	public static AndroidElement Hotspot_security_WPA2;
	
	@FindBy(id ="android:id/button1")
	public static AndroidElement Hotspot_saveBtn;
	
	@FindBy(id ="android:id/button2")
	public static AndroidElement Hotspot_cnclBtn;
	
	@FindBy(xpath = "//android.widget.TextView[@instance=6]")
	public static AndroidElement Hotspot_svdNtwrk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile network\")")
	public static AndroidElement MobileNtwrk_Lnk;
	
	@FindBy(xpath = "//android.widget.Switch[@instance=0]")
	public static AndroidElement MobileData_Lnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Wi‑Fi\")")
	public static AndroidElement Wifi_PageLogo;
	

	@FindBy(xpath = "//android.widget.FrameLayout[@index=0]/android.widget.TextView[@index=1]")
	public static AndroidElement Battery_chargingTxt;
	
	@FindBy(id = "com.android.settings:id/wifi_advanced_toggle")
	public static AndroidElement Wifi_AdvOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\" mobile data\")")
	public static AndroidElement dataUsageDetails;
	//***************************youtube*****************************
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"NOT NOW\")")
	public static AndroidElement NOT_NOW_txt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.youtube:id/later_button\")")
	public static AndroidElement NOT_NOW_id;

	@AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Search\")")
	public static AndroidElement Search;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static AndroidElement youtube;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static AndroidElement firstvideo;
	 
	//****************************************************************************************************************************
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Data usage\")")
	public static AndroidElement Data_usage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Wi-Fi data usage\")")
	public static AndroidElement Wi_fi_data_usage;
	
	@FindBy(id="com.android.settings:id/switch_bar")
	public static AndroidElement Wifi_SwitchBar;
	
	@FindBy(xpath="//android.widget.ImageButton[@index=2]")
	public static AndroidElement Wifi_OptBtn;
	
	@FindBy(id = "android:id/title")
	public static AndroidElement Wifi_resetOpt;
	
	@FindBy(id = "com.android.settings:id/initiate_reset_network")
	public static AndroidElement Wifi_resetStngBtn;
	
	@FindBy(id = "com.android.settings:id/execute_reset_network")
	public static AndroidElement Wifi_resetExecuteBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reset?\")")
	public static AndroidElement Wifi_ResetLogoTxt;
	
	@FindBy(id = "com.android.settings:id/switch_bar")
	public static AndroidElement BT_turnOffBtn;
	
	@FindBy(xpath = "//	android.widget.Switch[@instance=1]")
	public static AndroidElement BT_ScngBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement chromeAppLnk;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']/android.widget.RelativeLayout[@index='0']/android.widget.TextView")
	public static AndroidElement autoRotateScreen;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']/android.widget.LinearLayout[@index='1']/android.widget.Switch")
	public static AndroidElement aRSwitch;
	
	@FindBy(xpath = "//android.widget.EditText[@index=0]")
	public static WebElement googleSearchBx;
	
	@FindBy(id = "com.android.chrome:id/url_bar")
	public static AndroidElement chromeUrlTxtBx;
	
	@FindBy(id = "com.android.chrome:id/delete_button")
	public static AndroidElement chromeUrlDelBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Google Search\")")
	public static AndroidElement googleSearchBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").descriptionContains(\"End call\")")
	public static AndroidElement Endcall;
	
	@FindBy(id = "msc")
	public static AndroidElement google_verifyLnk;
	
	@FindBy(id = "navd")
	public static AndroidElement google_navigateIdLnk;
	
	//*****************************************Call Options**************************************
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"key pad\")")
		public static AndroidElement keypad;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\")")
		public static AndroidElement enterNumber;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"dial\")")
		public static AndroidElement call;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index=1]/android.widget.TextView[@index=0]")
	public static AndroidElement dataUsageSummary;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index=5]")
	public static AndroidElement MobileDataUsageBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\" mobile data\")")
	public static AndroidElement mobiledataUsed;
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"used\")")
	public static AndroidElement dataUsed;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi Direct\")")
	public static AndroidElement WifiDirectLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"RENAME DEVICE\")")
	public static AndroidElement WifiDirect_RenameDevices;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\")")
	public static AndroidElement setdata;
	
	@AndroidFindBy(id="android:id/button1")
	public static AndroidElement WifiDirect_OKBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Languages & input\")")
	public static  AndroidElement Languages_inputLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Languages\")")
	public static  AndroidElement LanguagesLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"French\")")
	public static  AndroidElement French;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.settings:id/add_language\")")
	public static  AndroidElement AddlanguagesLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\")")
	public static  AndroidElement Remove_clsname;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"Remove\")")
	public static  AndroidElement Remove;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\")")
	public static  AndroidElement checkbox;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"FranÃ§ais (France)\")")
	public static  AndroidElement France;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='More options']")
	public static  AndroidElement LanguagesettingBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Remove\")")
	public static  AndroidElement removeBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Remove']")
	public static  AndroidElement deleteBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
	public static  AndroidElement wifiradiobtn1;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@index=0]/android.widget.RelativeLayout")
	public static List<AndroidElement>  LanguageSelChkBoxes;
	
	@FindBy(id="android:id/button1")
	public static AndroidElement OkBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"key pad\")")
	public static AndroidElement dialPad;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi information\")")
	public static AndroidElement wifi_information;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi API\")")
	public static AndroidElement wifi_API;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"enableNetwork\")")
	public static AndroidElement enableNetwork;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@instance=3]/	android.widget.LinearLayout")
	public static List<AndroidElement> wifiListWindow;
	
	@FindBy(id="com.google.android.gm:id/welcome_tour_skip")
	public static AndroidElement gmailSkipBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Users & accounts\")")
	public static AndroidElement UsersAndAccounts;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement Addaccount;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").index(2)")
	public static AndroidElement Email_or_phone;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gm:id/og_apd_internal_image_view\")")
	public static AndroidElement gmailMenuBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.gm:id/setup_addresses_add_another\")")
	public static AndroidElement add_EmailAddress;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add another account\")")
	public static AndroidElement gmail_addAccountLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement gmail_googleLnk;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.webkit.WebView\").index(0)")
	public static AndroidElement gmail_googleSignInPage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").index(2)")
	public static AndroidElement emailTxtBx;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"identifierNext\")")
	public static AndroidElement gmailNxtBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").index(0)")
	public static AndroidElement passwordTxtBx;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").index(1)")
	public static AndroidElement acceptBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='I agree']")
	public static AndroidElement I_agree;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='MORE']")
	public static AndroidElement MORE;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='ACCEPT']")
	public static AndroidElement ACCEPT;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='REMOVE ACCOUNT']")
	public static AndroidElement REMOVE_ACCOUNT;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='GOT IT']")
	public static AndroidElement GOT_IT;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='TAKE ME TO GMAIL']")
	public static AndroidElement TAKE_ME_TO_GMAIL;
	
	@AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@index=0]/android.widget.RelativeLayout/	android.widget.TextView[@index=1]")
	public static List<AndroidElement> addedLanguageOpt;
	
	//***********************************************chrome browser******************************************
	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO\")")
	public static AndroidElement NO_THANKS;  //This L
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"No\")")
	public static AndroidElement No_ThanKS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Cancel\")")
	public static AndroidElement Cancel; 
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/terms_accept\")")
	public static AndroidElement Accept_id;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Next\")")
	public static AndroidElement Next_gmailsetup; 
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT;  //This Locator is in Chrome Breowser.	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"NEXT\")")
	public static AndroidElement NEXTBTN;  //This Locator is in Chrome Breowser.	
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='OK']")
	public static AndroidElement alert_OKBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.chrome:id/url_bar\")")
	public static AndroidElement chromeSearch;

	//*****************************************Battery********************
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Charging\")")
	public static AndroidElement Charging;  //This L
	
	@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Full\")")
	public static AndroidElement Full;  //This L
	


}
