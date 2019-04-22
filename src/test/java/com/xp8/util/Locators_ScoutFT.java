package com.xp8.util;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_ScoutFT {
	
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_ScoutFT(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement setUpTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement supportTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
	public static WebElement BLEconnect;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement SonimSetUpWizard;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SafeGuard']")
	public static WebElement Safeguard;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
	public static WebElement AppUpdater;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat']")
	public static WebElement Chat;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CHAT NOW']")
	public static WebElement Chat_Now;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Save Chat']")
	public static WebElement Chat_Save;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Save Chat\")")
	public static WebElement Chat_Save_btn;	

	@AndroidFindBy(xpath = "//android.widget.Button[@text='End Chat']")
	public static WebElement Chat_End;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Chat']")
	public static WebElement Chat_Title;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='cyberswift.com.sonim:id/et_firstName']")
	public static WebElement Chat_frstname;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='cyberswift.com.sonim:id/et_lastName']")
	public static WebElement Chat_Lastname;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='cyberswift.com.sonim:id/et_email']")
	public static WebElement Chat_Email;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Remote Support']")
	public static WebElement RemoteControl;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Remote Control']")
	public static WebElement RemoteControl_pge;

	@AndroidFindBy(xpath = "//android.view.View[@resource-id='device_name']")
	public static WebElement RemoteControl_DeviceID;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimCare']")
	public static WebElement SonimCare;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Warranty Registration']")
	public static WebElement WarrantyReg;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Device Information']")
	public static WebElement DeviceInfo_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Device Info']")
	public static WebElement DeviceInfo;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IMEI']")
	public static WebElement DeviceInfo_IMEI1;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IMEI2']")
	public static WebElement DeviceInfo_IMEI2;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Carrier']")
	public static WebElement DeviceInfo_Carrier;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Baseband version']")
	public static WebElement DeviceInfo_Baseband;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Model']")
	public static WebElement DeviceInfo_Model;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Build number']")
	public static WebElement DeviceInfo_Build;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Firmware']")
	public static WebElement DeviceInfo_Firmware;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Sonim Support']")
	public static WebElement ContactSonimSupport;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Programmable Keys']")
	public static WebElement programmableKey;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement Ok_Btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Camera']")
	public static WebElement camera_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	public static WebElement summaryText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Select PTT Key app')]")
	public static WebElement SelectPTTKey;

	
	
	
	//ScoutBattery Links page
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery Level')]")
	public static WebElement batteryLevel;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery Health']")
	public static WebElement batteryHealth;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery Status')]")
	public static WebElement batteryStatus;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery Scale')]")
	public static WebElement batteryScale;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery Electricity')]")
	public static WebElement batteryElectricity;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery  Temperature')]")
	public static WebElement batteryTemperature;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Battery  Voltage')]")
	public static WebElement batteryVoltage;
	
	
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"SAVE\")")
	public static AndroidElement saveText;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/cameraPass']")
	public static WebElement cameraPass_Confirmation_dialog;
	
	@AndroidFindBy(xpath="//*[@text='Chrome']/../..//*[@resource-id='com.sonim.safeguard:id/check_box']")
	public static AndroidElement chrome_CheckBox;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='NO']")
	 public static WebElement No_btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No Application']")
	public static WebElement no_Application_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Device Help']")
	public static WebElement deviceHelp_app;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Chat']")
	public static WebElement SonimChat_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement Scout_page;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement SWR_Support_Tab;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.sonim.safeguard:id/switch_widget\")")
	public static WebElement safeguardStatus;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Registration\")")
	public static WebElement SWR_String_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contacting Server. Please wait�\")")
	public static WebElement SWR_Contacting_server_progress_dialog;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Not Registered\")")
	public static WebElement SWR_Not_Registered;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Registered User\")")
	public static WebElement SWR_Registered;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement SWR_OK_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/now\")")
	public static WebElement SWR_Now;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/submit\")")
	public static WebElement SWR_Submit;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/resetButton\")")
	public static WebElement SWR_Reset;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.sonimtech.warrantyregapp:id/submit\").text(\"Remove all\")")
	public static WebElement SWR_Remove_All;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Register your device\")")
	public static WebElement SWR_String_Register_Your_Device;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unable to fetch registration information from server\")")
	public static WebElement SWR_UnableTo_Fetch_RegistrationInforamtionFromServer;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please contact Sonim at support@sonimtech.com to register your handset with scanned copy of invoice\")")
	public static WebElement SWR_Please_contact_Sonim_At;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/autocomplete_country\")")
	public static WebElement SWR_Country;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"No internet Connection. Please check your connection settings and try again\")")
	public static WebElement SWR_No_internet_Connection;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Take a Picture\")")
	public static WebElement SWR_Take_a_Pic;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement SWR_PhotoCapture;
	
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static WebElement SWR_camerabtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/choose_file_textview\")")
	public static WebElement SWR_invoice;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter first name\")")
	public static WebElement SWR_Enter_First_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter last name\")")
	public static WebElement SWR_Please_Enter_Last_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter company name\")")
	public static WebElement SWR_Enter_Company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter company\")")
	public static WebElement SWR_Please_enter_Company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter last name\")")
	public static WebElement SWR_Enter_Last_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter address1\")")
	public static WebElement SWR_Please_enter_Address_1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter address 1\")")
	public static WebElement SWR_Enter_Address_1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter city\")")
	public static WebElement SWR_Please_enter_City;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter city\")")
	public static WebElement SWR_enter_City;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter state\")")
	public static WebElement SWR_Please_Enter_State;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter state name\")")
	public static WebElement SWR_Enter_State_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter zipcode\")")
	public static WebElement SWR_Please_enter_zipcode;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter zip code\")")
	public static WebElement SWR_Enter_zip_Code;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter emailid\")")
	public static WebElement SWR_Please_enter_emailid;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter email id\")")
	public static WebElement SWR_Enter_Email_Id;


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter phone number\")")
	public static WebElement SWR_Please_enter_phone_number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter phone number\")")
	public static WebElement SWR_Enter_Phone_Number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter purchase date\")")
	public static WebElement SWR_Please_Enter_purchase_date;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/purchaseddate\")")
	public static WebElement SWR_Purchased_On;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(0)")
	public static WebElement SWR_Date;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(1)")
	public static WebElement SWR_Month;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(2)")
	public static WebElement SWR_Year;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"@\")")
	public static WebElement SWR_At_the_Rate_Symbol;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CHOOSE\")")
	public static WebElement SWR_ChooseBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Dealer Name\")")
	public static WebElement SWR_DelearName;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='Register']")
	public static WebElement DevInf_Register;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Explorer']")
	public static AndroidElement FileExplorer_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='XP5800']")
	public static AndroidElement Device_StorageName;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public static WebElement Allow_BT_Btn;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static WebElement AllowBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonimtech.easycontactsshare:id/textSeparator']")
	public static WebElement ImprtSummry_Text;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='DONE']")
	public static WebElement DoneOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectMdbFile\")")
	public static WebElement Title_MBDOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectVcfFile\")")
	public static WebElement Title_VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectCsvFile\")")
	public static WebElement Title_CSVOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnBtImportContacts\")")
	public static WebElement Title_BTOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/textSeparator\")")
	public static WebElement Title_ImportSummary;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via Bluetooth\")")
	public static WebElement BluetoothOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via MDB\")")
	public static WebElement MDBOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via VCF\")")
	public static WebElement VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via CSV\")")
	public static WebElement CSVOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Download']")
	public static WebElement DownloadOptn;

	@AndroidFindBy(id="com.sonim.safeguard:id/switch_widget")
	public static WebElement SG_toggle_btn;

	@AndroidFindBy(id="com.sonim.safeguard:id/dialog_edit_text")
	public static WebElement SG_Enter_Pin;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static WebElement SG_OK_Btn_for_Pin;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Applications']")
	public static WebElement SG_Applications_String;

	@AndroidFindAll(@AndroidFindBy(xpath="//android.widget.RelativeLayout"))
	public static List<WebElement> SG_All_Apps_List;

	@AndroidFindBy(id="android:id/list")
	public static WebElement SG_All_Apps_ListView;

	//Locators for Features
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Features']")
	public static WebElement SG_Features_String;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Call settings']")
	public static WebElement SG_Call_Settings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call blocking']")
	public static WebElement SG_Call_Blocking;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Location']")
	public static WebElement SG_Location;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Location consent']")
	public static WebElement SG_Location_consent;

	@AndroidFindBy(xpath="//android.widget.Button[@text='AGREE']")
	public static WebElement SG_Location_Agree;

	@AndroidFindBy(id="com.android.settings:id/switch_widget")
	public static WebElement SG_Location_Off_On_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add Contact']")
	public static WebElement SG_Add_Contact_String;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.contacts:id/floating_action_button']")
	public static WebElement SG_ADD_CONTACT_String;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
	public static WebElement SG_Find_Contact_In_Search_Box;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Blocked numbers']")
	public static WebElement SG_Blocked_Number;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Me']")
	public static WebElement SG_ME;

	@AndroidFindBy(id="com.android.dialer:id/dialer_digits")
	public static WebElement SG_dialer_digit;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Please input number or name']")
	public static WebElement SG_Please_input_number;

	@AndroidFindBy(id="com.android.dialer:id/primary_action_view")
	public static WebElement SG_dialer_History_List;

	@AndroidFindBy(id="android:id/button1")
	public static WebElement SG_clear_button;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear all call log']")
	public static WebElement SG_Clear_Call_Log;



	@AndroidFindBy(id="com.android.dialer:id/cliv_name_textview")
	public static WebElement SG_Create_New_Contact;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth']")
	public static WebElement SG_Bluetooth;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Factory reset']")
	public static WebElement SG_Factory_reset;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
	public static WebElement SG_Settings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add new contact']")
	public static WebElement SG_Add_new_Contact;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use USB to']")
	public static WebElement SG_Use_USB_to;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static WebElement SG_Native_Settings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB for file transfer']")
	public static WebElement SG_Use_USB_toIcon;


	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']"))
	public static WebElement SG_USB;








	//Locators for Change PIN
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change PIN']")
	public static WebElement SG_Change_Pin_String;

	@AndroidFindBy(id="com.sonim.safeguard:id/old_pinET")
	public static WebElement SG_Old_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/new_pinET")
	public static WebElement SG_New_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/reenter_pinET")
	public static WebElement SG_Reenter_Pin;

	@AndroidFindBy(id="com.sonim.safeguard:id/okButton")
	public static WebElement SG_OK_Btn_For_Change_Pin;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Activation']")
	public static WebElement SG_Activation_Text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Input PIN']")
	public static WebElement SG_Input_PIN;

	//Locators for Application Pintimeout
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Application PIN Timeout']")
	public static WebElement SG_App_Pin_TimeOut_String;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='30 Seconds']")
	public static WebElement SG_App_Pin_TimeOut_30_Sec;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1 Minute']")
	public static WebElement SG_App_Pin_TimeOut_1_Min;

	//Locators for Version
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
	public static WebElement SG_Version_String;


	//Locators for Help
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public static WebElement SG_Help_String;

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

	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Sashi\")") 
	public static WebElement scrolling;


	@AndroidFindBy(xpath="//android.widget.ListView[contains(@resource-id,'android:id/list') and @index='2']")
	public static List<WebElement> listView ;


	@AndroidFindBy(className="android.widget.RelativeLayout")
	public static List<WebElement> relative;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='WiFi Restriction']")
	public static WebElement SG_Wifi_Restriction;


	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonim.safeguard:id/app_name\").text(\"Phone\")")
	public static WebElement SG_Phone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select All']")
	public static WebElement SG_Select_All_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='OK']")
	public static WebElement SG_OK_Btn_for_PinApp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SAVE']")
	public static WebElement SG_Save_Btn;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement SG_More_Optns;

	@AndroidFindBy(xpath="//android:id/search_src_text")
	public static WebElement SG_Scout;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Emergency number']")
	public static WebElement SG_Emergency_No;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB for file transfer']")
	public static WebElement SG_Notification_USB;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Enter PIN']")
	public static WebElement SG_Locked_Screen_Validation;

	@AndroidFindBy(id="com.sonim.safeguard:id/editText")
	public static WebElement SG_Safe_Guard_Locked_Screen_Edit_Box;


	@AndroidFindBy(id="com.sonim.safeguard:id/editText")
	public static WebElement SG_Locked_Screen_Edit_Box;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Unselect All']")
	public static WebElement SG_Un_Select_All_Btn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Safe Guard']")
	public static WebElement SG_Lock_icon_Text;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='OK']")
	public static WebElement SG_Lock_Screen_OK_Btn;

	@AndroidFindBy(id="android:id/alertTitle")
	public static WebElement SG_Bluetooth_Error_Msg;





	//Locators used for all device apps

	 @AndroidFindBy(id="com.caf.fmradio:id/btn_onoff")
	public static WebElement SG_FM;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Messages']")
	public static WebElement SG_Message;

	@AndroidFindBy(id="org.codeaurora.snapcam:id/shutter_button")
	public static WebElement SG_Camera;

	  @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
	public static WebElement SG_Sound_Recoder;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement SG_SetUpwizard;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
	public static WebElement SG_Contact;

	  @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='dial pad']")
	public static WebElement SG_RecentCalls;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Please insert Storage card before taking backup']")
	public static WebElement SG_BackUp_restore;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim BLE Connect']")
	public static WebElement SG_BLE_Connect;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Albums']")
	public static WebElement SG_Album;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
	public static WebElement SG_Security;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='More']")
	public static WebElement SG_More;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tethering & portable hotspot']")
	public static WebElement SG_Teathering;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='My Notifications']")
	public static WebElement SG_MyNotification;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Wi-Fi']")
	public static WebElement SG_WIFI;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/search_src_text']")
	public static WebElement SG_search;

	@FindBy(id= "android:id/content")
	public static List<WebElement> notificationPanel;

	@AndroidFindBy(id="com.android.calculator2:id/simple_pad_circle")
	public static WebElement SG_Calc;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Agenda']")
	public static WebElement SG_Calender;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Downloads']")
	public static WebElement SG_Downlaod;

	@AndroidFindBy(className="android.widget.FrameLayout")
	public static AndroidElement SG_scroll;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SG_SetUp_String;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SafeGuard']")
	public static WebElement SG_Safe_Guard_String;

	 @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
	public static WebElement SG_Find_Contacts;

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

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Self-diagnosis Test']")
	public static WebElement selfDiagnosisTest;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Self Diagnosis']")
	public static WebElement selfDiagnosisTitle;
	
	//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/startButton']"
	//@AndroidFindBy(ui="")
	//public static WebElement scanForMoreNetworksBtn;

	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"SCAN FOR MORE NETWORKS\")")
    public static WebElement scanForMoreNetworksBtn;
	
	
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.sonim.sonimcustomercare:id/btn_pass']")
	public static WebElement YES_btn_wifiScanResult;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continue_btn']")
	public static WebElement continueBtn_Bat_scan;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/continueTV']")
	public static WebElement continueBtnFeatureScreen;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/contineView']")
	public static WebElement continueBtn_welcomeScreen_subApp;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/headsetViewContinue']")
	public static WebElement continueBtn_selfDiagnosis;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/camera_Conformation_view']")
	public static WebElement cameraCOnfirmation_text;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static WebElement cameraIcon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/preview_btn_done']")
	public static WebElement acceptCameraImagePreview;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
	public static WebElement OK_btn_resultConfirmation_dialog;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/preview_btn_done']")
	public static WebElement btn_resultConfirmation_dialog;
	
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryStatusLabel']")
	 public static WebElement batteryStatusLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheStatusView']")
	 public static WebElement batteryStatusValue;
		
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryElectricityLabel']")
	 public static WebElement batteryElectricityLabel;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryheElectricityView']")
	 public static WebElement batteryElectricityValue;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Cached data']/../android.widget.TextView[2]")
	 public static WebElement cachedMemory_0;
	 
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
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryHeading']")
	 public static WebElement battery_Info;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryhealthLabel']")
	 public static WebElement battery_health;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batteryvoltageLabel']")
	 public static WebElement battery_voltage;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.sonimcustomercare:id/batterytempLabel']")
	 public static WebElement battery_temperature;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery Diagnosis']")
	 public static WebElement batteryDiagnosis;
	 
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"CHECK FOR UPDATES\")")
	    public static WebElement checkForUpdateBtn;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
	    public static WebElement appUpdaterAlert;
		
		@AndroidFindBy(uiAutomator= "new UiSelector()..className(\"android.widget.TextView\").text(\"Downloading updates...\")")
	    public static WebElement downloadingProgressBar;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Checking for updates...\")")
	    public static WebElement checkingForUpdatesBar;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Installing updates...\")")
	    public static WebElement installingUpdatesBar;
		

		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	    public static WebElement homeSoftButton;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"About\")")
	    public static WebElement aboutBtn;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonimtech.sonimupdater:id/txt_last_updated']")
		 public static WebElement lastCheckedText;	
		
		@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/message']")
		 public static WebElement appUpdaterVersion;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonimtech.sonimupdater:id/txt_status']")
	    public static WebElement uptoDateText;
		
		/*@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.sonimtech.sonimupdater:id/txt_status']")
	    public static WebElement uptoDateText;*/
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	    public static WebElement uptoDateScreen_OkBtn;
		

		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apps']")
	    public static WebElement nativeSettings_apps;
		
		@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apps']")
	    public static WebElement apps_homescreen;
		
		@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.android.settings:id/app_snippet']")
	    public static WebElement appInfo_appTitle;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	    public static WebElement settingsIcon;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
	    public static WebElement appUpdater_app;
		
		@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"App Updater\")")
	    public static WebElement appUpdaterTitle;
		
		@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
		public static WebElement AppListIcon;
		
		@AndroidFindBy(xpath ="//android.widget.EditText[@text='  Search Apps']")
		public static WebElement AppSearch;

		
	

}


