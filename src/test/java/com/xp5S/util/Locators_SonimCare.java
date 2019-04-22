package com.xp5S.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class Locators_SonimCare{
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_SonimCare(AndroidDriver<AndroidElement> aDriver) {
		  this.aDriver=aDriver;
		  
		 }
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	 public static WebElement supportTab;

	
	@AndroidFindBy(id = "com.android.contacts:id/floating_action_button")
    public static WebElement addContactFloatingIcon;
	
	@AndroidFindBy(id = "com.android.contacts:id/text")
    public static WebElement newContactWillbeSaveToPhoneBook;
	
	@AndroidFindBy(id = "com.android.contacts:id/right_button")
    public static WebElement OKBTN;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static WebElement contactName;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_save")
	public static WebElement contactname;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_save")
	 public static WebElement ok;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SIM Card']")
	 public static WebElement simCartd;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	 public static WebElement name;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More fields']")
	 public static WebElement moreFiled;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_search")
	 public static WebElement moreoption;
	
	@AndroidFindBy(id = "com.android.contacts:id/search_view")
	 public static WebElement searchContact;
	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Self-diagnosis Test']")
	 public static WebElement selfDiagnosisTest;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery Diagnosis']")
	 public static WebElement batteryDiagnosis;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Diagnosis Report']")
	 public static WebElement diagnosisReport; 
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Videos']")
	 public static WebElement videos; 
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Sashi\")") 
	 public static WebElement scrolling;	 
	 
	 public static WebElement scroll()
	 {	  
	  return scrolling;	
	 }
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.contacts:id/add_account_button']")
	 public static WebElement addContactBtn;
	 
	 @AndroidFindBy(uiAutomator="new UiSelector().text(\"Name\")")
	 public static WebElement contactNameField;
	 
	 public static WebElement contactNameFieldText()
	 {
		 
		 return contactNameField;
	 }
	
	
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
	 public static WebElement careApp;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/toolbar_title']")
	 public static WebElement sonimCareTitle;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/resultview']")
	 public static WebElement wifiScanResult;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Self Diagnosis']")
	 public static WebElement selfDiagnosisTitle;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/contineView']")
	 public static WebElement continueBtn_welcomeScreen_subApp;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/headsetViewContinue']")
	 public static WebElement continueBtn_selfDiagnosis;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='WI-FI']")
	 public static WebElement wifiFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BLUETOOTH']")
	 public static WebElement bluetoothFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BACK CAMERA']")
	 public static WebElement backCameraFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='FLASHLIGHT']")
	 public static WebElement flashLightFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='DISPLAY']")
	 public static WebElement displayFeature;
	 

	 
	 @AndroidFindBy(uiAutomator="new UiSelector().text(\"KEYPAD\")")
	 public static WebElement keyPadFeature;
	 
	 public static WebElement keyPadFeaturetext()
	 {
		 
		 return keyPadFeature;
	 }
	 	
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='BATTERY']")
	 public static WebElement batteryFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='VIBRATION']")
	 public static WebElement vibrationFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='EARPIECE']")
	 public static WebElement earpieceFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='HEADSET']")
	 public static WebElement headsetFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='SPEAKER']")
	 public static WebElement speakerFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='ACCELERATOR SENSOR']")
	 public static WebElement acceleratorSensorFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='GPS']")
	 public static WebElement gpsFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='NOTIFICATION LED']")
	 public static WebElement notificationLEDFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='STORAGE INFO']")
	 public static WebElement storageInfoLEDFeature;
	 
	 @AndroidFindBy(xpath = "//android.widget.Button[@text='CLEAR CACHE MEMORY']")
	 public static WebElement clearCacheMemoryFeature;
	 
	 
	 
	 @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"SPEAKER\")") 
	 public static WebElement scrollTillSpeaker;	 
	 
	 public static WebElement scrollSpeaker()
	 {	  
	  return scrollTillSpeaker;	
	 }
	
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Remove all']")
	 public static WebElement SG_remove_All_btn;
	
	 @AndroidFindAll(@AndroidFindBy(xpath ="//android.widget.GridView[@resouce-id='com.sonim.sonimcustomercare:id/gridListview']/android.widget.LinearLayout"))
	public static List<WebElement> features;	
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continueTV']")
	 public static WebElement continueBtnFeatureScreen;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continue_btn']")
	 public static WebElement continueBtn_Bat_scan;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/startButton']")
	 public static WebElement scanForMoreNetworksBtn;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_pass']")
	 public static WebElement YES_btn_wifiScanResult;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_fail']")
	 public static WebElement NO_btn_wifiScanResult;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	 public static WebElement OK_btn_resultConfirmation_dialog;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	 public static WebElement positiveTestResult_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/camera_Conformation_view']")
	 public static WebElement cameraCOnfirmation_text;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/cameraPass']")
	 public static WebElement YES_btn_CameraResult;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/cameraFail']")
	 public static WebElement NO_btn_CameraResult;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryStatusLabel']")
	 public static WebElement batteryStatusLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheStatusView']")
	 public static WebElement batteryStatusValue;
		
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryElectricityLabel']")
	 public static WebElement batteryElectricityLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheElectricityView']")
	 public static WebElement batteryElectricityValue;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Internal shared storage']")
	 public static WebElement internalStorageTitle;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	 public static WebElement usedMemoryText;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	 public static WebElement apps_storage_heading;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	 public static WebElement cachedMemory_0;
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	 public static WebElement recentApp;	
	 
	 @AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/app_thumbnail_image\")")
	 public static WebElement SWR_RecentApp;
	
	 @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Cached data\")") 
	 public static WebElement scrollToCacheMemory;	 
	 
	 public static WebElement scrollToCacheMemory()
	 {	  
	  return scrollToCacheMemory;	
	 }
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
	 public static WebElement cameraIcon;
	 
	 @AndroidFindBy(xpath="//android.widget.ListView[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='0']/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_name_lable']")
	 public static WebElement bluetoothNameLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.ListView[@index='1']/android.widget.LinearLayout[@index='0']/android.widget.LinearLayout[@index='1']/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_address_lable']")
	 public static WebElement bluetoothAddressLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_conformation_view']")
	 public static WebElement bluetoothConfirmationText;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/tv_bluetooth_info']")
	 public static WebElement bt_TestResult;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryHeading']")
	 public static WebElement battery_Info;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthLabel']")
	 public static WebElement battery_health;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryvoltageLabel']")
	 public static WebElement battery_voltage;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterytempLabel']")
	 public static WebElement battery_temperature;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/line1']")
	 public static WebElement battery_service_History;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/line2']")
	 public static WebElement battery_view_detailedReport;
	 
	 @AndroidFindBy(xpath="//android.widget.RelativeLayout[@resource-id='com.sonim.sonimcustomercare:id/historylayout']")
	 public static WebElement battery_history_Layout;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Battery Diagnosis']")
	 public static WebElement battery_diagnosis_title;
	 
	 @AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='com.sonim.sonimcustomercare:id/progressBar']")
	 public static WebElement battery_progress_Bar;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterylevelview']")
	 public static WebElement battery_progress_Indicator;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthview']")
	 public static WebElement battery_health_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryvoltageview']")
	 public static WebElement battery_voltage_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterytempview']")
	 public static WebElement battery_temperature_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/calendar_month_year_textview']")
	 public static WebElement battery_Month_Year_view;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Total no.of Charges']")
	 public static WebElement battery_Total_No_Charges;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Max temp recorded']")
	 public static WebElement battery_Max_temp_recorded;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Total Charge Duration']")
	 public static WebElement battery_total_charge_duration;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/chargecycletextview']")
	 public static WebElement battery_Total_No_Charges_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/maxTempRecordedTV']")
	 public static WebElement battery_Max_temp_recorded_value;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/chargeduartionTextview']")
	 public static WebElement battery_total_charge_duration_value;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@content-desc='SUBSCRIBE']")
	 public static WebElement youtube_Page;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Browser']")
	 public static WebElement browser_select;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='ALWAYS']")
	 public static WebElement always_Btn;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/rssilable']")
	 public static WebElement RSSI_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_statelable']")
	 public static WebElement wifi_State_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_ConnectedLable']")
	 public static WebElement connectedTo_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/SupplicantLable']")
	 public static WebElement status_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bssilable']")
	 public static WebElement bssID_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ssidlable']")
	 public static WebElement ssID_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ipaddresslable']")
	 public static WebElement ip_address_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/macaddresslable']")
	 public static WebElement mac_address_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/linkspeedlable']")
	 public static WebElement link_speed_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/alertTitle']")
	 public static WebElement alert;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryLevelLabel']")
	 public static WebElement battery_level_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthLabel']")
	 public static WebElement battery_health_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryStatusLabel']")
	 public static WebElement battery_status_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryscaleLabel']")
	 public static WebElement battery_scale_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryElectricityLabel']")
	 public static WebElement battery_electricity_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryTemperatureLabel']")
	 public static WebElement battery_temperature_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryVoltageLabel']")
	 public static WebElement battery_voltage_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_model_textview']")
	 public static WebElement model_link;
	 
	 @FindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_imei_textview']")
	 public static WebElement imei_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_vesion_textview']")
	 public static WebElement androidVersion_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_build_textview']")
	 public static WebElement build_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/phone_number_textview']")
	 public static WebElement phoneNumber_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_info_textview']")
	 public static WebElement network_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_type_textview']")
	 public static WebElement network_type_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/signal_strength_textview']")
	 public static WebElement signal_strength_link;	 
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/roaming_textview']")
	 public static WebElement on_roaming_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/data_textview']")
	 public static WebElement data_link;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_model_textValue']")
	 public static WebElement model_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/device_imei_textvalue']")
	 public static WebElement imei_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_vesion_textvalue']")
	 public static WebElement android_version_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/os_build_textvalue']")
	 public static WebElement build_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/phone_number_textvalue']")
	 public static WebElement phone_number_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_info_textvalue']")
	 public static WebElement network_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/network_type_textvalue']")
	 public static WebElement network_type_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/signal_strength_textvalue']")
	 public static WebElement signal_strength_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/roaming_textvalue']")
	 public static WebElement on_roaming_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/data_textvalue']")
	 public static WebElement data_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_state_view']")
	 public static WebElement wifi_state_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/wifi_ConnectedView']")
	 public static WebElement connected_to_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/SupplicantView']")
	 public static WebElement status_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/rssiview']")
	 public static WebElement rssi_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bssiview']")
	 public static WebElement bssid_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ssidview']")
	 public static WebElement ssid_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/ipaddressview']")
	 public static WebElement ip_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/macaddressview']")
	 public static WebElement mac_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/linkspeedview']")
	 public static WebElement link_speed_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryLevelView']")
	 public static WebElement battery_level_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthView']")
	 public static WebElement battery_health_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheStatusView']")
	 public static WebElement battery_status_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryscaleView']")
	 public static WebElement battery_scale_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheElectricityView']")
	 public static WebElement battery_electricity_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheTemperatureView']")
	 public static WebElement battery_temperature_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheVoltageView']")
	 public static WebElement battery_voltage_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.LinearLayout[0]/android.widget.LinearLayout[0]/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_name']")
	 public static WebElement bluetooth_name_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.LinearLayout[0]/android.widget.LinearLayout[1]/android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/bluetooth_name']")
	 public static WebElement bluetooth_address_value_text;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
	 public static WebElement self_diagnosis_alert_msg;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	 public static WebElement sonim_scout_AppLauncher;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='SEARCH BLUETOOTH']")
	 public static WebElement searchForBluetooth;
	 
	 
	 

	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
	
	 
	}