package com.xp3.Utils;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Sanity {


	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Sanity(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	//---------------------------------------Locators for XP3 device sanity----------------------------------------------------
	
	@AndroidFindBy(xpath="//*[@text='Preferred network type']/..//*[@resource-id='android:id/summary']")
	public static AndroidElement selectedNetworkType;
	
//	@AndroidFindBy(xpath="//*[@text='Preferred network type']/following-sibling::class")
//	public static AndroidElement selectedNetworkType1;
	
	@AndroidFindBy(xpath="//*[@text='Preferred network type']")
	public static AndroidElement preferredNetworkType;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/dialer_digits']")
	public static AndroidElement dialer;
	
	@AndroidFindBy(xpath="//*[@text='Dialing']")
	public static AndroidElement dialingScreen;
	
	//@AndroidFindBy(xpath="//*[contains(@text, 'I'll call you later.')]")
	//public static AndroidElement endCallmsg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"I'll call you later.\")")
	public static AndroidElement endCallmsg;
	
	@AndroidFindBy(xpath="//*[@resource-id ='com.android.dialer:id/name'][1]")
	public static AndroidElement nameFirstEntry;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id ='com.android.mms:id/embedded_text_editor']")
	public static AndroidElement message_field;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
    public static WebElement typeMessageOpt;
	
	@AndroidFindBy(xpath="//*[@text ='Standard data']/../..//android.widget.Switch[@text = 'ON' or @text= 'OFF']")
	public static AndroidElement mobileDataSwitch;
	
	@AndroidFindBy(xpath="//*[@resource-id ='com.android.packageinstaller:id/permission_message']")
	public static AndroidElement permissionPopUp;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allowButton; 
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add to contact']")
	public static AndroidElement addContactPhone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
    public static WebElement newContactPhone;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Pair new device']")
	public static AndroidElement pairNewDevice;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Go to URL']")
	public static AndroidElement gotoURL;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"LTE\")")
	public static AndroidElement LTEoption;
	
	@AndroidFindBy(xpath = "//*[@text='3G' or @text='CDMA']")
	public static AndroidElement threeGoption;
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='2G' or @text='GSM/UMTS']")
	public static AndroidElement twoGoption;
	
	
	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Automatic']")
	public static AndroidElement automaticOption;
	
	/*@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Sleep']/../..//android.widget.TextView[@resource-id = 'android:id/summary']"))
	public static AndroidElement sleepTimeSummary;
	*/
	//----------------------------------------End of Locators for XP3 device sanity----------------------------------------------
	
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageView[@index='0']")
	public static WebElement rejectWithSMSTextCallPage;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Search']")
	public static WebElement searchOpt;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.mms:id/search_view']")
	public static WebElement searchFld_InMessages;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. What's up?\")")
	public static AndroidElement quickResponseSent;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static AndroidElement recentApp;

	@AndroidFindBy(xpath="//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement AiroplaneSwitch;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Date & time']")
	public static AndroidElement date_time;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement settingsIcon;

	//	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	//	public static AndroidElement SetDate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;


	@AndroidFindBy(xpath = "//*[contains(@text,'data')]/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement enabled_Data;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement enabled_DataSP;

	@AndroidFindBy(xpath = "//*[Contains(@text,'data')]/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement disabled_Data;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement disabled_DataSP;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='2']//android.widget.TextView[@index='1']")
	public static AndroidElement setDateSummary;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='3']//android.widget.TextView[@index='1']")
	public static AndroidElement setTimeSummary;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='4']//android.widget.TextView[@index='1']")
	public static AndroidElement setTimeZoneSummary;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Use 24-hour format']")
	public static AndroidElement use24HourFormatOpt;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='4']//android.widget.Switch[@text='OFF']")
	public static AndroidElement timeFormatDisabled;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"LTE\")")
	public static AndroidElement LTE;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Set date']")
	public static AndroidElement setDate;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Set time']")
	public static AndroidElement settime;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select time zone']")
	public static AndroidElement settimeZone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='USB connected']")
	public static AndroidElement usbConnectedOpt;

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


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"GSM only\")")
	public static AndroidElement GSM_NetwrkTyp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"UMTS/GSM\")")
	//	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@resource-id='android:id/contentPanel']/../..//android.widget.CheckedTextView[@text='UMTS/GSM']"))
	public static AndroidElement UMTS_NetwrkTyp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"LTE\")")
	public static AndroidElement LTE_NetwrkTyp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"WCDMA/GSM\")")
	public static AndroidElement WCDMAGSM_NetwrkTyp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"WCDMA Only\")")
	public static AndroidElement WCDMA_NetwrkTyp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"CDMA\")")
	public static AndroidElement CDMA_NetwrkTyp;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular network type']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement networkType;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"About phone\")")
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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/content_frame\")")
	public static MobileElement SettingsList;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Wi\")")
	public static AndroidElement wifi;
	
	@AndroidFindBy(xpath = "//*[@text= 'Add Contact']")
	public static AndroidElement addContact;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
	public static MobileElement connected_WiFi;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_wifi;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_wifi;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Bluetooth']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Bt;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Bluetooth']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Bt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Bluetooth']/../..//android.widget.Switch"))
	public static AndroidElement Bt_Status;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch"))
	public static AndroidElement Wifi_Status;
	
	@FindBy(how=How.XPATH, using =("//android.widget.ListView//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch"))
	public static AndroidElement airplaneMode_Status;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_FlightMode;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Flightmode;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Quick Settings\")")
	public static AndroidElement quickSettings;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Quick toggle\")")
	public static AndroidElement quickToggle;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text ='Quick toggle' or @text ='Quick Settings']")
	public static AndroidElement quickSettingsPage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.sonim.borqs.launcher:id/homescreen_ring\")")
	public static AndroidElement HomeScreen;

	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement enable;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.contacts:id/content\")")
	public static AndroidElement contacts_find;


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
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static WebElement addContactOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More fields']")
	public static AndroidElement contact_MorefieldsOptn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Address']")
	public static AndroidElement contact_Address;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CHANGE PHOTO']")
	public static WebElement changePhotoOpt;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Take photo']")
    public static WebElement takePhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
    public static WebElement captureOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement imageOkOpt;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static AndroidElement contact_Save;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement contact_FindContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Please input number or name\")")
	public static AndroidElement phone_find;

	//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test\")")
	//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/header_title\")")
	public static AndroidElement contact_Test;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Google Search')]")
	public static MobileElement SearchIcon_Google;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement summaryWIFI;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/empty']")
	public static AndroidElement BluetoothOff;
	
	@AndroidFindBy(xpath="//*[@text='XP5800' or @text='XP8800' or @text='KWSA50K' or @text='KWSA80K']")
	 public static AndroidElement BT_Devices;

	  @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Location\")")
		public static AndroidElement Location_feature;
	    
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"AGREE\")")
		public static AndroidElement location_Agree;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement Bluetooth;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@content-desc,'Search')]")
	public static MobileElement Searchenter_Google;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name prefix']")
	public static WebElement namePrefixFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='First name']")
	public static WebElement firstNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Middle name']")
	public static WebElement middleNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Last name']")
	public static WebElement lastNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name suffix']")
	public static WebElement nameSuffixFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phonetic last name']")
	public static WebElement phoneticLastName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Phonetic middle name']")
	public static WebElement phoneticMiddleName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Phonetic first name']")
	public static WebElement phoneticFirstName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Nickname']")
	public static WebElement nickNameFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Company']")
	public static WebElement companyFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Title']")
	public static WebElement titleFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile']")
	public static WebElement numberTypeFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='SIP']")
	public static WebElement sipFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Email']")
	public static WebElement emailFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Home']")
	public static WebElement emailTypeFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Address']")
	public static WebElement addressFld;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView[@resource-id='com.android.contacts:id/compact_contact_editor_fragment']")
	public static MobileElement scrollpage;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='IM']")
	public static WebElement IMFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AIM']")
	public static WebElement IMTypeFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Website']")
	public static WebElement websiteFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Notes']")
	public static WebElement notesFld;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.contacts:id/expansion_view']")
	public static WebElement expandFld;


	/*@AndroidFindBy(xpath = "//android.widget.TextView[@text='Testing']")
	public static AndroidElement contact_Testing;*/

	/*	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement contact_nameText;*/

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement first_No_In_CallLog;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sonim\")")
	public static AndroidElement contact_emailid;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='YYYYY ZZZZZ']")
	public static AndroidElement contact_addressText;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete']")
	public static AndroidElement contact_delete;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static WebElement addContacts;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.contacts:id/header_title\").text(\"Testing\")")
	public static AndroidElement contact_Testing;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement toField_NewMessage;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/search_count_text']")
	public static WebElement matchedContactsFld;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to SIM']")
	public static WebElement copyToSIMOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title']")
	public static WebElement addedContact;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to SIM1']")
	public static WebElement copyToSIM1Opt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='No contacts']")
	public static WebElement noContacts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Testing']")
	public static AndroidElement contactSelect_Testing;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/error\")")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Draft\")")
	public static AndroidElement draft_Text; //To validate after saving SMS.

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"DELETE\")")
	public static AndroidElement DELETE;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	public static AndroidElement image_Attachment;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/attachment_view\")")
	public static AndroidElement image_Attachment1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement photoCaptureIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"No conversations.\")")
	public static AndroidElement noConversations;

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

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement cellular_DataON;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement cellular_DataOFF;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;
	
	//@AndroidFindBy(xpath = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	//public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No network connection']")
	public static AndroidElement NoNewtwrkErroeMsg;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Connection problem']")
	public static AndroidElement ConnectionPrblm;

	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.webkit.WebView\")")
	public static AndroidElement googleIcon;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.android.browser:id/webview_wrapper']")
	public static AndroidElement googleWebView;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='rogers-fido.mobi']")
	public static WebElement rogersHomePageBrowser;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='https://start.att.net/']")
	public static WebElement attHomePageBrowser;

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
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='web.bwanet.ca']")
	public static WebElement bellURLHomePageBrowser;
	
	@AndroidFindBy(xpath="//android.widget.Button[@content-desc='Search']")
	public static WebElement bellSearchHomePageBrowser;
	

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"About phone\")")
	public static AndroidElement AboutPhone_feature;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Display\")")
	public static AndroidElement display_feature;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sleep\")")
	public static AndroidElement Sleep_Optn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"android:id/contentPanel\")")
	public static MobileElement SleepList;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"8 hours\")")
	public static AndroidElement eightHrsSleep_Optn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;




	/*	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Baseband version\")")
	public static AndroidElement BasebandVersion;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Build number\")")
	public static AndroidElement BuildNumber;*/

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.support.v7.widget.RecyclerView\").resourceId(\"com.android.settings:id/list\")")
	public static MobileElement DataUsageList;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Sonim mobiles']")
	public static AndroidElement Browsed_Sonim;

	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Homepage')]")
	public static AndroidElement HomePage_Browser;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.calculator2:id/panelswitch\")")
	public static WebElement calc_Dialog_Pad;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='start.att.net']")
	public static AndroidElement Hompage_ATT;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
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


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement mobile_Networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_Networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Access Point Names']")
	public static AndroidElement access_Point_Names;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New APN']")
	public static AndroidElement new_APN;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static AndroidElement OK_Button;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public static AndroidElement CANCEL_Button;

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

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Hotspot & tethering']")
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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SD card\")")
	public static AndroidElement SD_card;	

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
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.borqs.launcher:id/network_name']")
	public static AndroidElement operatorName;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No recording']")
	public static AndroidElement noRecordingsOpt;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/list_item_title')]"))
	public static WebElement saveBtnUnderRecList;


	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	public static AndroidElement DiscardBtn;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static WebElement saveButton;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement saveBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The name already exists.')]")
	public static AndroidElement NameExist;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SampleSoundRecord.amr')]")
	public static AndroidElement SampleSoundRec;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recording list')]")
	public static AndroidElement SoundRecList;

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

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Restore']")
	public static AndroidElement Restore;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	public static AndroidElement Txt_Apn;

	@AndroidFindBy(xpath="//android.widget.RadioButton[@resource-id='com.android.settings:id/apn_radiobutton']")
	public static AndroidElement SelectedApn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
	public static AndroidElement Security;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Screen lock']")
	public static AndroidElement Screen_Lock;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Screen lock\")")
	public static AndroidElement ScreenLock;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"None\")")
	public static AndroidElement Screenlck_None;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"YES, REMOVE\")")
	public static AndroidElement Screenlck_removeBtn;

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


	@AndroidFindBy(xpath="//android.widget.TextView[@text='1234567890sonim']")
	public static AndroidElement wifi_Dellas;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='dlink-F092-media']")
	public static AndroidElement wifi_Cannada;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;

	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement disconnectedWIFI;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"FORGET\")")
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

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@resource-id='com.borqs.camera:id/video_module_keys']/..//android.widget.ImageButton[@resource-id='com.borqs.camera:id/camera_switcher']")
	public static AndroidElement switchCamerIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Albums\")")
	public static AndroidElement GalleryAlbums;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']")
	public static AndroidElement pic_capture;


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Baseband version\")")
	public static AndroidElement BasebandVersion;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Device storage\")")
	public static AndroidElement device_Storage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Build number\")")
	public static AndroidElement BuildNumber;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Baseband version']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement BasebandVersionText;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Build number']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement BuildNumberText;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Storage']")
	public static AndroidElement Storage_Optn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Internal shared storage']")
	public static AndroidElement internalStorageTxt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	public static AndroidElement internalStorage_Value_Txt;

	@AndroidFindBy(xpath="//android.widget.ProgressBar[@resource-id='com.android.settings:id/storage_summary_progress']")
	public static AndroidElement Available_Progress_Bar;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Total used of 8.00 GB\")")
	public static AndroidElement total_Used_of_8GB;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement Available_Value_Txt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AT&T Software Update']")
	public static AndroidElement Software_Update_ATT;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Software Update']")
	public static AndroidElement Software_Update;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='System Updates']")
	public static AndroidElement System_Update;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='1. Check for Updates']")
	public static AndroidElement check_Updates;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Check for Updates']")
	public static AndroidElement check_for_Updates;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Check update\")")
	public static AndroidElement checkUpdates;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Download now\")")
	public static AndroidElement downloadUpdates;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Update now\")")
	public static AndroidElement UpdateNow;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.thunderst.update:id/cur_tip\")")
	public static AndroidElement updated;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.thundersoft.zdm:id/alert_activity_m_btn']")
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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Charging\")")
	public static AndroidElement Charging_OverUSB;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Full\")")
	public static AndroidElement Full_OverUSB;	


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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"SoundRecorder\")")
	public static AndroidElement soundRecorder_FE;//FileExplorer.

	@AndroidFindBy(xpath = "//android.widget.GridView[@resource-id='com.sonim.borqs.launcher:id/content']/..//android.widget.TextView[@text='Push-to-Talk']")
	public static AndroidElement bell_PTT;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"TELUS Link\")")
	public static AndroidElement telusLink_PTT;

	@AndroidFindBy(xpath = "//android.widget.TabHost[@resource-id='android:id/tabhost']/..//android.widget.TabWidget[@resource-id='android:id/tabs']")
	public static AndroidElement telusLink_PTT_validate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Nova Talk\")")
	public static AndroidElement rogers_PTT;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Linc PTT\")")
	public static AndroidElement sl_PTT;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Activate\")")
	public static AndroidElement rogers_PTT_validate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Push-to-Talk\")")
	public static AndroidElement bell_PTT_validate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Allow Linc PTT to access this device's location?\")")
	public static AndroidElement sl_PTT_validate;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='KODIAK LICENSE AGREEMENT']")
	public static AndroidElement sprint_PTT_validate;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='End-User-License Agreement']")
	public static AndroidElement ATT_PTT_validate;

	@AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.borqs.camera:id/gallery\")")
	public static AndroidElement gallery_icon_in_CameraScreen;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"ext-element-168\")")
	public static AndroidElement reconnecting_message_PTT;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Artists\")")
	public static AndroidElement artists_music_player;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement CamerIcon;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"Copy to\")")
	public static AndroidElement copyTo_option;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Rename']")
	public static AndroidElement renameOption;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='android:id/text1']")
	public static AndroidElement renameField;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"XP3800\")")
	public static AndroidElement xp3800;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\").textContains(\"COPY\")")
	public static AndroidElement copy_here;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\").textContains(\"test_file\")")
	public static AndroidElement test_file_txt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']/..//android.widget.LinearLayout[@index='0']")
	public static AndroidElement first_File_In_FileManager;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='android:id/content']/..//android.view.View[@content-desc='AUtomation']")
	public static AndroidElement file_content;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SetUp_Scout;

	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Sign in']")
	public static AndroidElement signIn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Email or phone\")")
	public static AndroidElement email_ID_Login;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Enter your password\")")
	public static AndroidElement email_ID_Password;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.browser:id/main_content\")")
	public static AndroidElement scroll_Browser;

	@AndroidFindBy(xpath = "//android.view.View[@index='3']/..//android.widget.Button[@resource-id='identifierNext']")
	public static AndroidElement next_Login;

	@AndroidFindBy(xpath = "//android.view.View[@index='4']/..//android.widget.Button[@resource-id='passwordNext']")
	public static AndroidElement next_Passwrd;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Status\")")
	public static AndroidElement statusOptn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement sound_Recorder;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Declined call']")
	public static WebElement declinedCall;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Missed call']")
	public static WebElement missedCall;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Incoming call']")
	public static WebElement incomingCall;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Outgoing call']")
	public static WebElement outgoingCall;


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//=======================================================
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.support.v7.widget.RecyclerView\").resourceId(\"com.android.settings:id/dashboard_container\")")
	public static MobileElement settingsPageList;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select PTT Key app']")
	public static MobileElement selectPTTKeyOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ListView\").resourceId(\"android:id/list\")")
	public static AndroidElement PTTAppPageList;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Programmable Key']")
	public static MobileElement programmableKeyOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/message\")")
	public static AndroidElement PTTPopUp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.sonim.borqs.launcher:id/home_screen_frame_layout\")")
	public static AndroidElement appsMenuScreen;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Missed Events\")")
	public static AndroidElement missed_Events;

	@AndroidFindBy(xpath="//*[@text='Dialing']")
	public static AndroidElement dialingOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Recent calls\")")
	public static AndroidElement recentCallsScreen;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send message\")")
	public static AndroidElement sendMessageInDialer;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/message\")")
	public static AndroidElement deleteMsgPopUp;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Remove all\")")
	public static AndroidElement removeAllOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.borqs.launcher:id/page_title\")")
	public static AndroidElement missedEventsOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"USB tethering\")")
	public static AndroidElement USBTetheringOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement photocameraIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Switch to Camera\")")
	public static AndroidElement SwitchToCameraOptn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement videocameraIcon;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Applications']")
    public static WebElement applications;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Calendar']")
    public static WebElement calendar;
	
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Month']")
    public static WebElement monthOpt;
    
    @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']")
    public static WebElement deleteEventsEnabled;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete events']")
    public static WebElement deleteEventsOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select all']")
    public static WebElement selectAllOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Done']")
    public static WebElement doneOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement OkBtn;
    
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']//android.widget.TextView[@index='1']")
	public static AndroidElement Summary;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Agenda']")
    public static WebElement agendaOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.calendar:id/title']")
    public static WebElement createdEvent;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
    public static WebElement newEvent;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Event name']")
    public static WebElement eventName;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Location']")
    public static WebElement locationFld;
    
    @AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static WebElement saveOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Time']")
    public static WebElement alarmSetPage;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Today']")
    public static WebElement alarmTodayOpt;
    
    @AndroidFindBy(xpath="//android.widget.Switch[@text='On']")
    public static WebElement alarmSwitchOn;
    
    @AndroidFindBy(xpath="//android.widget.Switch[@text='Off']")
    public static WebElement alarmSwitchOff;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tools']")
    public static WebElement tools;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
    public static WebElement clock;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add']")
    public static WebElement addOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add']")
    public static WebElement addOpt1;
    
    @AndroidFindBy(xpath="//android.view.View[@resource-id='com.android.deskclock:id/glow_pad_view']")
    public static WebElement alarmPage;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.deskclock:id/digitalClock']")
    public static WebElement alarmTimeFld;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tomorrow']")
    public static WebElement alarmTomorrowOpt;
    
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Webpage not available']")
	public static AndroidElement webpageNotavailable;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Silky Way\")")
	public static AndroidElement Ringtone_SilkyWay;
    
    @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Sleep']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static WebElement Sleep_Summary;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 minutes\")")
	public static AndroidElement thirtyMinSleep_Optn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select']")
    public static AndroidElement selectOptInMsg;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete all threads']")
    public static AndroidElement deleteAllThreadsInMsg;
    
    
}
