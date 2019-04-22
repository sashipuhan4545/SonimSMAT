package com.aosp.Utils;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AOSP_Locators_Sanity {


	private static AndroidDriver<AndroidElement> aDriver;

	public AOSP_Locators_Sanity(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static AndroidElement recentApp;

	@AndroidFindBy(xpath="//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement AiroplaneSwitch;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Date & time']")
	public static AndroidElement date_time;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement settingsIcon;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	 public static AndroidElement wifi_show_Pswd;

//	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
//	public static AndroidElement SetDate;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']//android.widget.TextView[@index='1']")
	public static AndroidElement Summary;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"LTE\")")
	 public static AndroidElement LTE;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Set date']")
	public static AndroidElement setDate;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Set time']")
	public static AndroidElement settime;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select time zone']")
	public static AndroidElement settimeZone;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Automatic date & time']")
	public static AndroidElement Autodate_time;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Automatic time zone']")
	public static AndroidElement Autotimezone;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Airplane mode']")
	public static AndroidElement Flightmode;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More']")
	public static AndroidElement moreOptn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public static AndroidElement okBtn;

	//	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	//    public static AndroidElement SetTimezone;

/*	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About phone']")
	public static AndroidElement AboutPhone;*/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"About phone\")")
	  public static AndroidElement AboutPhone;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery level']")
	public static WebElement BatteryLevel;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sound']")
	public static AndroidElement Sound;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Status']")
	public static AndroidElement status;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SIM status']")
	public static AndroidElement sim_status;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular network type']")
	public static AndroidElement cellularNw;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Quick Settings']")
	public static AndroidElement quickSettings;

	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement enable;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement bluetooth;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static AndroidElement contacts;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static AndroidElement Addcontacts;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static AndroidElement contactName;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static AndroidElement contact_Phone;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Email']")
	public static AndroidElement contact_Email;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More fields']")
	public static AndroidElement contact_MorefieldsOptn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Address']")
	public static AndroidElement contact_Address;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static AndroidElement contact_Save;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title' and @text='Test']")
	public static AndroidElement contact_Test;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Testing']")
	public static AndroidElement contactSelect_Testing;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement toField_NewMessage;
	
	/*@AndroidFindBy(xpath = "//android.widget.TextView[@text='Testing']")
	public static AndroidElement contact_Testing;*/

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement contact_nameText;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	 @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sonim\")")
	 public static AndroidElement contact_emailid;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim&gmail.com']")
	public static AndroidElement contact_emailid_;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='YYYYY ZZZZZ']")
	public static AndroidElement contact_addressText;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete']")
	public static AndroidElement contact_delete;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement contact_DeleteBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
	public static AndroidElement phone_DailerApp;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/dialer_digits']")
	public static AndroidElement DailNum;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Speaker']")
	public static AndroidElement phone_Speaker;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Muted']")
	public static AndroidElement phone_Mute;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.android.dialer:id/callNameLayout']")
	public static AndroidElement phone_Volume;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Clear all call log']")
	public static AndroidElement phone_DeleteCallLog;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CLEAR']")
	public static AndroidElement phone_ClearOptn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='(5) 0 min. ago']")
	public static AndroidElement phone_UpdateCall;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement phone_RecentCallName;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement phone_RecentCall;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.contacts:id/search_view']")
	public static AndroidElement contact_find;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static AndroidElement contact_addContactsOptn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Testing']")
	public static AndroidElement contact_Testing;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Messaging']")
	public static AndroidElement messagingApp;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='New message']")
	public static AndroidElement New_msgOptn;

	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@text='Type name/number']")
	public static AndroidElement Type_NumField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Type message']")
	public static AndroidElement typeMsg_Field;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='145 / 2']")
	public static AndroidElement single_PageCounterMsg;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Send']")
	public static AndroidElement msg_send;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.mms:id/from']")
	public static AndroidElement Msg_sent_Num;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select']")
	public static AndroidElement Contact_Select;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select All']")
	public static AndroidElement Contact_SelectAll;
	
	@AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.android.dialer:id/call_history_list']")
	public static AndroidElement   call_Historypage;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete selected thread']")
	public static AndroidElement Msg_Delete;

	@AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.android.mms:id/history']")
	public static AndroidElement Msg_Contact;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular data']")
	public static AndroidElement cellular_Data;

	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement On_Toggle_data;
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='com.android.deskclock:id/onoff']")
	public static AndroidElement On_Toggle_clock;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Browser']")
	public static AndroidElement browser_App;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.browser:id/url')]")
	public static AndroidElement DefaultUrlTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@content-desc,'Search')]")
	public static AndroidElement googleSearch;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data usage']")
	public static AndroidElement dataUsageOptn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Use 24-hour format']")
	public static AndroidElement Format_optn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No network connection']")
	public static AndroidElement NoNewtwrkErroeMsg;

	@AndroidFindBy(xpath = "//android.widget.Image[@content-desc='Google']")
	public static AndroidElement googleIcon;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.android.browser:id/webview_wrapper']")
	public static AndroidElement googleWebView;
	
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public static AndroidElement off_Toggle_data;
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"android:id/checkbox\")")
	public static AndroidElement off_checkbox_Roaming;

	@AndroidFindBy(xpath = "//android.widget.CheckBox[@resource-id='android:id/checkbox']")
	public static AndroidElement off_checkbox_data;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data roaming']")
	public static AndroidElement data_Roaming;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='International data roaming']")
	public static AndroidElement international_data_Roaming;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Roaming']")
	public static AndroidElement data_Roaming_Sprint;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular networks']")
	public static AndroidElement cellular_newtworksOptn;//Roaming

//	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular network settings']")
//	public static AndroidElement cellular_newtworkSettgOptn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FM Radio']")
	public static AndroidElement FM_App;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Applications']")
	public static AndroidElement Device_App;

	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Radio On')]")
	public static AndroidElement OffFMImsg;


	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/prog_frequency_tv')]")
	public static AndroidElement ChannelTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/freq')]")
	public static AndroidElement ScanedsChannelTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'YES')]")
	public static AndroidElement YesOpt;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Sonim mobiles']")
	public static AndroidElement Browsed_Sonim;

	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Homepage')]")
	public static AndroidElement HomePage_Browser;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='start.att.net']")
	public static AndroidElement Hompage_ATT;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile networks']")
	public static AndroidElement MobileNetwrks;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Access point names']")
	public static AndroidElement APN_Optn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Search the Web']")
	public static AndroidElement SearchResult;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='ref=navm_hdr_logo']")
	public static AndroidElement amazonLogo;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='eBay']")
	public static AndroidElement ebay;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Yahoo']")
	public static AndroidElement yahoo;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='facebook']")
	public static AndroidElement facebook;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile networks']")
	public static AndroidElement mobile_Networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_Networks;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Access point names']")
	public static AndroidElement access_Point_Names;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New APN']")
	public static AndroidElement new_APN;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static AndroidElement OK_Button;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/edit']")
	public static AndroidElement textField_PopUp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Name']")
	public static AndroidElement apn_Name;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='APN']")
	public static AndroidElement apn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='APN type']")
	public static AndroidElement apn_Type;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static AndroidElement save;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Jio']")
	public static AndroidElement added_APN;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Edit']")
	public static AndroidElement edit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Jio']")
	public static AndroidElement Added_Apn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='AIRTEL']")
	public static AndroidElement Airtel_Apn;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete APN']")
	public static AndroidElement delete_APN; 

	@AndroidFindBy(xpath="//android.widget.TextView[@text='More']")
	public static AndroidElement more_Button;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tethering & portable hotspot']")
	public static AndroidElement tethering_Portable_Hotspot;

	@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF']")
	public static AndroidElement standard_Data_Off;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB tethering']")
	public static AndroidElement usb_Tethering;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Portable Wi-Fi hotspot']")
	public static AndroidElement portable_WiFi_hotspot;

	@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF']")
	public static AndroidElement portable_WiFi_hotspot_Off;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth tethering']")
	public static AndroidElement bluetooth_Tethering_Off;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Entitlement Error']")
	public static AndroidElement entitlement_Error;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='UM.JT.DWM']")
	public static AndroidElement enteredNum;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view']")
	public static AndroidElement dailedNum;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Tools']")
	public static AndroidElement ToolsOptn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Calculator']")
	public static AndroidElement calciApp;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
	public static AndroidElement clockApp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
	public static AndroidElement calc_Edit_text_field;	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Calendar']")
	public static AndroidElement CalenderApp;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
	public static AndroidElement Calender_NewEvent;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Event name']")
	public static AndroidElement Calender_Eventname;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Location']")
	public static AndroidElement Calender_Location;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Description']")
	public static AndroidElement Calender_Description;

	@AndroidFindBy(xpath="//android.widget.Spinner[@content-desc='Repetition']")
	public static AndroidElement Calender_SpinnerRepitition;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='One-time event']")
	public static AndroidElement Calender_onetime_event;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement RecorderApp;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='SoundRecorder']")
	public static AndroidElement SoundRecorderoptn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='DCIM']")
	public static AndroidElement DCIM_optn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='File Explorer']")
	public static AndroidElement FileExplorer_App;


	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='2']"))
	public static AndroidElement allowBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Birthday']")
	public static AndroidElement Notification_Calender;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete events']")
	public static AndroidElement Deleteevents_Calender;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.soundrecorder:id/rename_edit_text')]")
	public static AndroidElement recordingName;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/list_item_title')]"))
	 public static WebElement saveBtnUnderRecList;


	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	public static AndroidElement DiscardBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement saveBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The name already exists.')]")
	public static AndroidElement NameExist;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SampleSoundRecord.amr')]")
	public static AndroidElement SampleSoundRec;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement ListallowBtn;

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@content-desc,'SDownload free Abstract wallpapers for mobile phone. Abstract')]")
	public static AndroidElement download_Image;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AT&T Firefly']")
	public static AndroidElement defaultPhone_ringtone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Wave']")
	public static AndroidElement defaultNotification_ringtone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Languages & input']")
	public static AndroidElement defaultLaunguageOptn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='English (United States)']")
	public static AndroidElement defaultLaunguage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Network operators']")
	public static AndroidElement Network_Operators;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preferred network type']")
	public static AndroidElement PreferredNwType_Operators;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Reset to default']")
	public static AndroidElement RestoreDefault;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	public static AndroidElement Txt_Apn;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id='com.android.settings:id/apn_radiobutton']")
	public static AndroidElement SelectedApn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
	public static AndroidElement Security;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Screen lock']")
	public static AndroidElement Screen_Lock;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='PIN']")
	public static AndroidElement PIN;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password_entry']")
	public static AndroidElement setPsswd;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/edit']")
	public static AndroidElement setPsswd_SIM;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONTINUE']")
	public static AndroidElement continueBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Add']")
	public static AndroidElement Add_Btn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement txt_ScreenLockSet;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Press and hold ￼ key']")
	public static AndroidElement Press_hold_Star;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='YES, REMOVE']")
	public static AndroidElement yes_RemoveBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.deskclock:id/digital_clock']")
	public static AndroidElement Current_Device_Time;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.deskclock:id/date']")
	public static AndroidElement Current_Device_Date;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Clock']")
	public static AndroidElement clock_Icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Alarm']")
	public static AndroidElement Alarm_Icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Timer']")
	public static AndroidElement Timer_Icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Stopwatch']")
	public static AndroidElement Stopwatch_Icon;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.deskclock:id/timer_time_text_view']")
	public static AndroidElement Timer_Timing;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='START']")
	public static AndroidElement Timer_Start;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id='com.android.deskclock:id/stopwatch_time_text']")
	public static AndroidElement STopwatch_Timing;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='To see available networks, turn Wi-Fi on.']")
	public static AndroidElement wifi_notavailable;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Off']")
	public static AndroidElement wifi_Off;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='When Bluetooth is turned on, your device can communicate with other nearby Bluetooth devices.']")
	public static AndroidElement bluetooth_notavailable;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@text='com.android.settings:id/switch_tile']")
	public static AndroidElement wifi_switch;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Available devices']")
	public static AndroidElement bluetooth_Availabledevices;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']//android.widget.ImageButton[@index='0']")
	public static AndroidElement volume_Up_Icon;
	
	@AndroidFindBy(xpath="//android.widget.SeekBar[@resource-id='com.android.systemui:id/volume_row_slider]")
	public static AndroidElement volume_Slider;
	
	@AndroidFindBy(xpath="//android.widget.GridView[@resource-id='com.sonim.borqs.launcher:id/content']")
	public static AndroidElement homePge;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']//android.widget.ImageButton[@index='0']")
	public static AndroidElement volume_Down_Icon;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='My Notifications']")
	public static AndroidElement menu_Notification;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Camera']")
	public static AndroidElement cameraApp;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
	public static AndroidElement camera_Icon;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']")
	public static AndroidElement pic_capture;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Storage']")
	public static AndroidElement Storage_Optn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Internal shared storage']")
	public static AndroidElement internalStorageTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	public static AndroidElement internalStorage_Value_Txt;
	
	@AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='android:id/progress']")
	public static AndroidElement Available_Progress_Bar;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement Available_Value_Txt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='AT&T Software Update']")
	public static AndroidElement Software_Update_ATT;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Software Update']")
	public static AndroidElement Software_Update;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1. Check for Updates']")
	public static AndroidElement check_Updates;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Check for Updates']")
	public static AndroidElement check_for_Updates;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.thundersoft.zdm:id/alert_activity_content']")
	public static AndroidElement Up_To_Software;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.ts.zdm:id/txtMssage']")
	public static AndroidElement Up_To_Software_SL;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static AndroidElement Up_To_Software_OKBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.ts.zdm:id/txtMssage']")
	public static AndroidElement No_Updates_Available;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Set up SIM card lock']")
	public static AndroidElement Set_SIM_Lock_Optn;
	
	@AndroidFindBy(id="android:id/switch_widget")
	public static AndroidElement toggle_btn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network type']")
	public static AndroidElement cellularNwType;
	

	/*
	 * ===================================================================================================================================================================
	 * 																	AOSP RELATED LOCATORS
	 * ===================================================================================================================================================================
	
	*/
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"USB debugging connected\")")
	public static AndroidElement USB_debugging_connected;	
	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='AT&T EPTT']")
	public static AndroidElement PTT_ATT;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Scout']")
	public static AndroidElement scoutApp_icon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Applications']")
	public static AndroidElement applications_icon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
	public static AndroidElement music_icon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup and Restore']")
	public static AndroidElement backUP_reset;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Downloads']")
	public static AndroidElement downloads_icon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Gallery']")
	public static AndroidElement gallery_icon;
	
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
	
	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.borqs.camera:id/gallery\")")
	public static AndroidElement gallery_icon_in_CameraScreen;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"ext-element-168\")")
	public static AndroidElement reconnecting_message_PTT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Artists\")")
	public static AndroidElement artists_music_player;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"ext-element-141\")")
	public static AndroidElement confirmDialog_ATT_PTT;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Battery']")
	public static AndroidElement battery;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/content_frame\")")
	public static MobileElement settings_frame;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sound Recorder\")")
	public static AndroidElement soundRecorder;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement video_capture_button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Build number\")")
	public static AndroidElement build;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='4']//android.widget.TextView[@index='1']")
	public static AndroidElement build_Summary;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.music:id/line1\").textContains(\"Music\")")
	public static AndroidElement music;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.music:id/play_indicator\")")
	public static AndroidElement musicIndicator;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.browser:id/tab_switcher\")")
	public static AndroidElement browser_tabSwitcher;
	
	//@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Download\")")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Download']")
	public static AndroidElement download_folder;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Alarms\")")
	public static AndroidElement alarms_folder;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Copy to�\")")
	public static AndroidElement copyTo_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"XP5800\")")
	public static AndroidElement xp5800;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\").textContains(\"COPY HERE\")")
	public static AndroidElement copy_here;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Test_File.txt\")")
	public static AndroidElement test_file_txt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']/..//android.widget.LinearLayout[@index='0']")
	public static AndroidElement first_File_In_FileManager;
	
	@AndroidFindBy(uiAutomator = "//android.view.View[@content-desc='Test']")
	public static AndroidElement file_content;
}
