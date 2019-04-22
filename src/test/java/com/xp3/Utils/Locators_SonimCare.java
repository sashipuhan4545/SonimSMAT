package com.xp3.Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_SonimCare {


	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_SonimCare(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	//Added comment to test
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement supportTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Care']")
	public static WebElement SonimCare;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Self-diagnosis Test\")")
	public static AndroidElement Self_diagnosis_Test;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Battery Diagnosis\")")
	public static AndroidElement Battery_Diagnosis_Test;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Diagnosis Report\")")
	public static AndroidElement Diagnosis_Report;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Videos\")")
	public static AndroidElement Videos;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Online Troubleshooting\")")
	public static AndroidElement Online_Troubleshooting;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Self Diagnosis\")")
	public static AndroidElement Self_diagnosis;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Welcome to Sonim Self Diagnosis Application\")")
	public static AndroidElement Self_diagnosis_welcome_text;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"This will allow you to test various functions of the phone\")")
	public static AndroidElement Self_diagnosis_info_text;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Continue\")")
	public static AndroidElement Continue;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"WI-FI\")")
	public static AndroidElement wi_fi;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"BLUETOOTH\")")
	public static AndroidElement Bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"BACK CAMERA\")")
	public static AndroidElement Back_Camera;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"FLASHLIGHT\")")
	public static AndroidElement Flashlight;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DISPLAY\")")
	public static AndroidElement Display;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"KEYPAD\")")
	public static AndroidElement Keypad;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"BATTERY\")")
	public static AndroidElement Battery;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"VIBRATION\")")
	public static AndroidElement Vibration;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"EARPIECE\")")
	public static AndroidElement Earpiece;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HEADSET\")")
	public static AndroidElement Headset;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SPEAKER\")")
	public static AndroidElement Speaker;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"GPS\")")
	public static AndroidElement GPS;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"NOTIFICATION LED\")")
	public static AndroidElement Notification_LED;

	/*@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"STORAGE \r\n" + 
			" INFO")")
	public static AndroidElement Storage_Info;*/

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='5']/../..//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/gridItemView']")
	public static WebElement storageInfo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CLEAR CACHE MEMORY\")")
	public static AndroidElement Clear_Cache_Memory;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/descriptionTextview\")")
	public static AndroidElement module_welcome_page;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SCAN FOR MORE NETWORKS\")")
	public static AndroidElement Scan_for_more_networks;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test Results\")")
	public static AndroidElement Test_Results;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wifi Scan Results\")")
	public static AndroidElement Wifi_Scan_Results;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/wifiNameView\")")
	public static AndroidElement Wifi_SSIDS;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Is the connected Wi-Fi Access Point info displayed properly and are other networks, if available, found?\")")
	public static AndroidElement Wifi_confirmation_statement;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"YES\")")
	public static AndroidElement yes_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SEARCH BLUETOOTH\")")
	public static AndroidElement Search_Bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/bluetooth_name\")")
	public static AndroidElement bluetooth_name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/bluetooth_address\")")
	public static AndroidElement bluetooth_address;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
	public static AndroidElement Camera;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement capture_btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add Image ?\")")
	public static AndroidElement add_image;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement ok_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.sonim.sonimcustomercare:id/imageView1\")")
	public static AndroidElement captured_image;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement cancel_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"Off\")")
	public static AndroidElement flashlight_off_mode;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"On\")")
	public static AndroidElement flashlight_on_mode;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"To test the functionality of display,press back key and observe change in background colors\")")
	public static AndroidElement display_info_page;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Did the display show all colors full screen?\")")
	public static AndroidElement display_confirmation_msg;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please select the result of Battery test\")")
	public static AndroidElement battery_info;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"START VIBRATE\")")
	public static AndroidElement start_vibrate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"STOP VIBRATE\")")
	public static AndroidElement stop_vibrate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/message\")")
	public static AndroidElement self_diagnosis_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"NO\")")
	public static AndroidElement no_button;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ProgressBar\").resourceId(\"com.android.settings:id/storage_summary_progress\")")
	public static AndroidElement storage_info_progress;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Cached data\")")
	public static AndroidElement cached_data;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Clear cached data?\")")
	public static AndroidElement clear_cached_data;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"0\")")
	public static AndroidElement cleared_cached_data;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Click here for tips to solve the problem \")")
	public static AndroidElement no_option_confirmation_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Welcome to Sonim battery Diagnosis Application\")")
	public static AndroidElement battery_diagnosis_welcome_screen;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"This application collects the battery charging details and send to Sonim Server\")")
	public static AndroidElement battery_diagnosis_info_text;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Battery Information\")")
	public static AndroidElement Battery_information;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ProgressBar\").resourceId(\"com.sonim.sonimcustomercare:id/progressBar\")")
	public static AndroidElement Battery_progress_bar;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.sonim.sonimcustomercare:id/lin_info1\")")
	public static AndroidElement Battery_health;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.sonim.sonimcustomercare:id/lin_info2\")")
	public static AndroidElement Battery_voltage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.sonim.sonimcustomercare:id/lin_info3\")")
	public static AndroidElement Battery_temperature;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.sonim.sonimcustomercare:id/historylayout\")")
	public static AndroidElement Battery_service_history;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/calendar_month_year_textview\")")
	public static AndroidElement month_in_battery_diagnosis ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.GridView\").resourceId(\"com.sonim.sonimcustomercare:id/calendar_gridview\")")
	public static AndroidElement calendar_in_battery_diagnosis ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Total no.of Charges\")")
	public static AndroidElement Total_no_of_charges ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/chargecycletextview\")")
	public static AndroidElement charge_cycle;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Max temp recorded\")")
	public static AndroidElement Max_temp_recorded ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/maxTempRecordedTV\")")
	public static AndroidElement temp_record;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Total Charge Duration\")")
	public static AndroidElement Total_Charge_Duration ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.sonimcustomercare:id/chargeduartionTextview\")")
	public static AndroidElement charge_duration;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ListView\").resourceId(\"com.sonim.sonimcustomercare:id/diagnosisReportListView\")")
	public static AndroidElement diagnosis_report_list;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement cellular_DataON;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement cellular_DataOFF;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkAndInternet;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement MobileNetwrks_oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data_Oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data_Oreo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement OK_Btn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data usage']")
	public static AndroidElement dataUsageOptn;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"FORGET\")")
	public static AndroidElement wifi_IDC_ForgetBtn;

	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement summaryWIFI_oreo;
	
}
