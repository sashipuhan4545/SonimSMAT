package com.xp8.util;


	import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
	import io.appium.java_client.android.AndroidElement;
	import io.appium.java_client.pagefactory.AndroidFindBy;

	public class Locators_DataUsageSetting {
		private static AndroidDriver<AndroidElement> aDriver;

		public Locators_DataUsageSetting(AndroidDriver<AndroidElement> aDriver) {
			this.aDriver=aDriver;
		}
		@AndroidFindBy(uiAutomator="new UiSelector().descriptionContains(\"Open settings.\")")
		public static AndroidElement openSettings;
		
		//**********************************Airplane mode**********************************
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Mobile network\")")
		public static AndroidElement checkMobilenetwork;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Airplane mode\")")
		public static AndroidElement Airplanemode;
		
		//*************************************Settings window**************************
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
		public static AndroidElement NetworkAndInternet_tc1;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"mobile, data usage, Mobile Hotspot\")")
		public static AndroidElement NetworkAndInternet_tc2;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.android.settings:id/dashboard_tile' and @index='2']")
		public static AndroidElement NetworkAndInternet_x3;
			
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Network & Internet\")")
		public static AndroidElement Network_Internet;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"System\")")
		public static AndroidElement system;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Reset options\")")
		public static AndroidElement Reset_options;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Reset Wi-Fi, mobile & Bluetooth\")")
		public static AndroidElement Reset_Wifi_mobile_Bluetooth;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Reset app preferences\")")
		public static AndroidElement Reset_app_preferences;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Erase all data (factory reset)\")")
		public static AndroidElement erase_all_data_factoryreset;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"RESET SETTINGS\")")
		public static AndroidElement RESET_SETTINGS;
		
		
		//**************************************Network and internet******************
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Data usage\")")
		public static AndroidElement Data_usage;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
		public static AndroidElement Connected_devices;	
		
		//**************************************Data usage settings window******************
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Data saver' or @text='Data Saver']")
		public static AndroidElement Data_saver;
			
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Mobile data usage\")")
		public static AndroidElement Mobile_data_usage;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Billing cycle\").index(0)")
		public static AndroidElement Billing_cycle;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Wi-Fi data usage\")")
		public static AndroidElement Wi_fi_data_usage;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Network restrictions\")")
		public static AndroidElement Network_restrictions;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"RESET APPS\")")
		public static AndroidElement RESET_APPS;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"RESET PHONE\")")
		public static AndroidElement RESET_PHONE;
	
		@AndroidFindBy(uiAutomator="new UiSelector().description(\"More options\")")
		public static AndroidElement MoreOptions;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Mobile networks\")")
		public static AndroidElement Mobile_networks;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Network settings\")")
		public static AndroidElement mobile_network_settings;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/color_bar\")")
		public static AndroidElement dataUsageProgressBar;
	
		
		//************************notification window icons*************************************
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Edit order of settings.\")")
		public static AndroidElement pencil;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Reset\")")
		public static AndroidElement Reset;
		
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Data Saver\")")
		public static AndroidElement Data_Saver_icon;
	
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Cast\")")
		public static AndroidElement Cast_icon;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").descriptionContains(\"Data Saver\")")
		public static AndroidElement Datasaver_symbol;

		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").descriptionContains(\"Do not disturb\")")
		public static AndroidElement Donotdistrub_symbol;
	
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\")")
		public static AndroidElement Donotdistrub_switch;
	
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").descriptionContains(\"Airplane mode\")")
		public static AndroidElement Airplanemode_symbol;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\")")
		public static AndroidElement BT_switch;

		//**********************************Common locators*********************
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"OK\")")
		public static AndroidElement OK;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"CANCEL\")")
		public static AndroidElement CANCEL;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\")")
		public static AndroidElement setdata;
		//***************************Mobile data *******************
		@AndroidFindBy(uiAutomator="new UiSelector().textContains(\" mobile data\")")
		public static AndroidElement mobiledataUsed;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Enable mobile data services such as email,web browsing, AT&T Video Call and push notifications over the cellular network.\")")
		public static AndroidElement Mobile_data;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
		public static AndroidElement mobiledata_Switch;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\")")
		public static AndroidElement datasaver_switch;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Unrestricted data\")")
		public static AndroidElement Unrestricted_data;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@index='0' and @instance='0']")
		public static AndroidElement Unrestricted_data_app_1;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/usage_graph\")")
		public static AndroidElement Data_usage_graph;
		
		//**************************************Billing cycle***************************************
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Set data warning\")")
		public static AndroidElement setdataWarning;

		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Set data limit\")")
		public static WebElement setdataLimit;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='ON' and @instance='0' and @checked='true']")
		public static AndroidElement enabled_setdatawarning;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF' and @instance='0' and @checked='false']")
		public static AndroidElement disabled_setdatawarning;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='ON' and @instance='1' and @checked='true']")
		public static AndroidElement enabled_setdataLimit;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF' and @instance='1' and @checked='false']")
		public static AndroidElement disabled_setdataLimit;

		@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='2']/android.widget.RelativeLayout[@index='0']/android.widget.TextView")
		public static AndroidElement fixed_DataWarning_DataLimit;

		@AndroidFindBy(uiAutomator="new UiSelector().textContains(\" mobile data\")")
		public static AndroidElement dataUsed;

		@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/android.widget.TextView[@index='1' and @resource-id='android:id/summary']")
		public static AndroidElement dateCycle;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Data warning\")")
		public static AndroidElement Data_warning;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Data limit\")")
		public static AndroidElement Data_limit;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Spinner\").index(1)")
		public static AndroidElement GByteMByte;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"GByte\")")
		public static AndroidElement GByte;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"MByte\")")
		public static AndroidElement MByte;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"SET\")")
		public static AndroidElement SET;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.NumberPicker\")")
		public static AndroidElement numberPicker;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/color_bar\")")
		public static AndroidElement set_data_usage_warning;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/icon\")")
		public static AndroidElement app_Icon;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").descriptionContains(\"Mobile data\")")
		public static AndroidElement mobileDataState_symbol;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"TURN\")")
		public static AndroidElement TURN_OFF;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Google Play Store']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
		public static AndroidElement googlePlayStore_Switch;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Android Easter Egg\")")
		public static AndroidElement sa1_android_easter_egg;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Android Services Library\")")
		public static AndroidElement sa2_android_services_library;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Android Setup\")")
		public static AndroidElement sa3_android_setup;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Android Shared Library\")")
		public static AndroidElement sa4_android_shared_library;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Show system\")")
		public static AndroidElement Show_system;

		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Android Accessibility Suite\")")
		public static AndroidElement otherApp1;

		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Hide system\")")
		public static AndroidElement Hide_system;

		//***********************App data usage************************
		
		@AndroidFindBy(xpath = "//android.widget.Spinner[@index='0']/android.widget.TextView")
		public static AndroidElement dateCycleRange;
		
		@AndroidFindBy(uiAutomator="new UiSelector().textContains(\"Monthly on day\")")
		public static AndroidElement monthlyDate;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='3']/android.widget.TextView[@index='1']")
		public static AndroidElement total;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='4']/android.widget.TextView[@index='1']")
		public static AndroidElement foreground;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='5']/android.widget.TextView[@index='1']")
		public static AndroidElement background;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"App settings\")")
		public static AndroidElement appSettings;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Restrict background data\")")
		public static AndroidElement Restrict_background_data;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Never\")")
		public static AndroidElement Never;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Always\")")
		public static AndroidElement Always;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/entity_header_content\")")
		public static AndroidElement beforeAppInfo;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"App info\")")
		public static AndroidElement appInfo;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Settings\")")
		public static AndroidElement settings;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Background data\")")
		public static AndroidElement background_data;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='ON' and @instance='0' and @checked='true']")
		public static AndroidElement enabled_background_data;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF' and @instance='0' and @checked='false']")
		public static AndroidElement disabled_background_data;
	
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Unrestricted data usage\")")
		public static AndroidElement unrestricted_data_usage;
	
		@AndroidFindBy(xpath="//android.widget.Switch[@text='ON' and @instance='1' and @checked='true']")
		public static AndroidElement enabled_unrestricted_data_usage;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF' and @instance='1' and @checked='false']")
		public static AndroidElement disabled_unrestricted_data_usage;
		
		@AndroidFindBy(xpath=
"//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.TextView[@index='0']")
		public static AndroidElement start_Cycle_Date;
		
		@AndroidFindBy(xpath=
"//android.widget.LinearLayout[@index='1']/android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.TextView[@index='1']")
		public static AndroidElement end_Cycle_Date;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").index(1)")
		public static AndroidElement wifi_data_used;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").index(3)")
		public static AndroidElement appList_app_1;
		
		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='3']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@resource-id='android:id/title']")
		public static AndroidElement appList_app_1_title;

		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='4']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@resource-id='android:id/title']")
		public static AndroidElement appList_app_2_title;

		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='3']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@resource-id='android:id/summary']")
		public static AndroidElement appList_app_1_dataused;

		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='4']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@resource-id='android:id/summary']")
		public static AndroidElement appList_app_2_dataused;

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").index(4)")
		public static AndroidElement appList_app_2;

		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.TextView")
		public static AndroidElement metered_wifi_networks;
		
		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.TextView")
		public static AndroidElement list_metered_wifi_networks;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").index(1)")
		public static AndroidElement list_wifi_networks_1;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").index(2)")
		public static AndroidElement list_wifi_networks_2;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ListView\").index(0)")
		public static AndroidElement automatic_metered_notMetered;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Automatic\")")
		public static AndroidElement automatic;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Metered\")")
		public static AndroidElement metered;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Not metered\")")
		public static AndroidElement non_metered;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"Metered networks are treated like mobile networks when background data is restricted. Apps may warn before using these networks for large downloads.\")")
		public static AndroidElement info_metered_networks;
		
		@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']")
		public static AndroidElement enabled_wifi_networks;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/color_bar\")")
		public static AndroidElement data_usage_bar;
		
		@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/label_bar\")")
		public static AndroidElement numericDataValue;
		
		//****************************Wifi *******************************
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi‑Fi']")
		public static AndroidElement Wi_Fi;
		
		@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/android.widget.TextView[@text='Wi‑Fi' and @index='0' @resource-id='android:id/title']")
		public static AndroidElement wi_Fi_x2;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").index(0)")
		public static AndroidElement wifiConnectionSate;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(1)")
		public static AndroidElement wifi_enterPwd_index; // index
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"CONNECT\")")
		public static AndroidElement CONNECT_t1;
				
		@AndroidFindBy(xpath="//android.widget.Switch[@resourceId='com.android.settings:id/switch_bar' and @text='ON']")
		public static AndroidElement enabled_Wifi;
		
		@AndroidFindBy(xpath="//android.widget.Switch[@resourceId='com.android.settings:id/switch_bar' and @text='OFF']")
		public static AndroidElement disabled_Wifi;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"FORGET\")")
		public static AndroidElement FORGET;
		
	
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
		public static AndroidElement wifiradiobtn;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\")")
		public static AndroidElement wifi1;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Belkin 2.4\")")
		public static AndroidElement wifi2;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/password_layout\")")
		public static AndroidElement wifitextfield;
		@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
		public static AndroidElement wifiState;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
		public static AndroidElement wificonnect;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
		public static AndroidElement wifiConnected;
		
		
		//***********************************mobile data********************************
		@AndroidFindBy(xpath="//android.widget.Switch[@instance='0']")
		public static AndroidElement mobile_data_State;

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Mobile\")")
		public static AndroidElement MobileNetwork_tc1;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='YouTube']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
		public static AndroidElement You_Tube_Switch;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"NOT NOW\")")
		public static AndroidElement NOT_NOW_txt;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.youtube:id/later_button\")")
		public static AndroidElement NOT_NOW_id;
	
		@AndroidFindBy(uiAutomator = "new UiSelector().descriptionContains(\"Search\")")
		public static AndroidElement Search;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"sonim xp8\")")
		public static AndroidElement sonimxp8;
		
		@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']")
		public static AndroidElement video;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Mobile data limit\")")
		public static AndroidElement Mobile_data_limit_reached;
		
		@AndroidFindBy(uiAutomator="new UiSelector().text(\"RESUME\")")
		public static AndroidElement RESUME;

		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Chrome\")")
		public static AndroidElement Chrome;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Google Play services\")")
		public static AndroidElement Google_Play_services;
	
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Google Play Store\")")
		public static AndroidElement Google_Play_store;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"xp8datausagesettingsonim@gmail.com\")")
		public static AndroidElement dusTestMail;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Add account\")")
		public static AndroidElement Add_account;
		@AndroidFindBy(xpath="//android.view.View[@index='2']")
		public static AndroidElement EmailOrPhone;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"Next\")")
		public static AndroidElement Next;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"I agree\")")
		public static AndroidElement Iagree;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"MORE\")")
		public static AndroidElement MORE;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
		public static AndroidElement ACCEPT;
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"WiÃƒÂ¢Ã¢â€šÂ¬Ã¢â‚¬ËœFi\")")
		public static AndroidElement WIFI_STATE;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"YouTube\")")
		public static AndroidElement You_Tube;
		

		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").descriptionContains(\"Sonim XP8. First look.\")")
		public static AndroidElement sonimvideo;
		
		
		
	}
