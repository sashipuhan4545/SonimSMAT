package com.aosp.Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_DevSanity {
	
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_DevSanity(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Draft\")")
	public static AndroidElement draft_Text; //To validate after saving SMS.
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settings;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement summaryWIFI;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement Bluetooth;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Available devices\")")
	public static AndroidElement feature_text;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/empty']")
	public static AndroidElement BluetoothOff;
	
	@AndroidFindBy(xpath="//*[@text='XP5800' or @text='XP8800' or @text='KWSA50K' or @text='KWSA80K']")
	 public static AndroidElement BT_Devices;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_wifi;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_wifi;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"FORGET\")")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1234567890sonim']")
	public static AndroidElement wifi_Dellas;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='dlink-F092-media']")
	public static AndroidElement wifi_Cannada;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='No network connection']")
	public static AndroidElement NoNewtwrkErroeMsg;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"About phone\")")
	public static AndroidElement AboutPhone;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Status']")
	public static AndroidElement status;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Charging over USB\")")
	public static AndroidElement Charging_OverUSB;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Full\")")
	public static AndroidElement Full_OverUSB;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/content_frame\")")
	public static MobileElement settings_frame;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"SD card\")")
	public static AndroidElement SDcard;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Properties\")")
	public static AndroidElement SD_PropertiesSettingsOptn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"GB\")")
	public static AndroidElement SDcardSize;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='New message']")
	public static AndroidElement New_msgOptn;

	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@text='Type name/number']")
	public static AndroidElement Type_NumField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Type message']")
	public static AndroidElement typeMsg_Field;
	
	@AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.android.mms:id/history']")
	public static AndroidElement Msg_Contact;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Send']")
	public static AndroidElement msg_send;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.mms:id/from']")
	public static AndroidElement Msg_sent_Num;
	
/*	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement toField_NewMessage;
	*/
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']")
	public static MobileElement MsgList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"DELETE\")")
	public static AndroidElement DELETE;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"No conversations.\")")
	public static AndroidElement noConversations;
		
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.support.v7.widget.RecyclerView\").resourceId(\"com.android.settings:id/list\")")
	public static MobileElement DataUsageList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Status\")")
	public static AndroidElement statusOptn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IMEI information\")")
	public static AndroidElement IMEI_info;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"IMEI\")")
	public static AndroidElement IMEI;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/message\")")
	public static AndroidElement IMEI_Num;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/text1\")")
	public static AndroidElement IMEI_Numdisplay;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.browser:id/url')]")
	public static AndroidElement DefaultUrlTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@content-desc,'Google Search')]")
	public static MobileElement SearchIcon_Google;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@content-desc,'Search')]")
	public static MobileElement Searchenter_Google;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile networks']")
	public static AndroidElement MobileNetwrks;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data usage']")
	public static AndroidElement dataUsageOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement cellular_DataON;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement cellular_DataOFF;
	
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
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static AndroidElement contact_addContactsOptn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static AndroidElement contacts;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select']")
	public static AndroidElement Contact_Select;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select All']")
	public static AndroidElement Contact_SelectAll;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete']")
	public static AndroidElement contact_delete;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement contact_DeleteBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title' and @text='Test']")
	public static AndroidElement contact_Test;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sonim\")")
	public static AndroidElement contact_emailid;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IDC Sonim']")
	public static AndroidElement contact_addressText;
	
	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[contains(@resource-id,'android:id/action_bar')]/../..//android.widget.TextView[@text='Camera']"))
	public static AndroidElement Gallery_Camera;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement photocameraIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Switch to Camera\")")
	public static AndroidElement SwitchToCameraOptn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement videocameraIcon;
		
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select album\")")
	public static AndroidElement SelectAlbumOptn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select all\")")
	public static AndroidElement SelectallOptn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").textContains(\"Delete\")")
	public static AndroidElement Delete;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static MobileElement OKBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='2']"))
	public static WebElement allowBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.soundrecorder:id/rename_edit_text')]")
	public static WebElement recordingName;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/list_item_title')]"))
	 public static WebElement saveBtnUnderRecList;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	 public static WebElement SaveButton;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/prog_frequency_tv')]")
	public static AndroidElement ChannelTxt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Auto Scan\")")
	public static MobileElement AutoScan;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'YES')]")
	 public static WebElement YesOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"All Channels\")")
	public static MobileElement AllChannelsList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Scanning for All Stations\")")
	public static MobileElement ScanningAllChannels;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Screen lock']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement typelock;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Screen lock\")")
	public static AndroidElement ScreenLock;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.settings:id/password_entry\")")
	public static AndroidElement enter_PIN;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.systemui:id/pinEntry\")")
	public static AndroidElement enter_PINLock;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Security\")")
	public static AndroidElement Security_feature;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"PIN\")")
	public static AndroidElement Screenlck_PIN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CONTINUE\")")
	public static AndroidElement PIN_continue;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Press and hold ￼ key\")")
	public static AndroidElement Screenlck_PressHoldStar;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Airplane mode']")
	public static AndroidElement Flightmode;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More']")
	public static AndroidElement moreOptn;
	
	@AndroidFindBy(xpath="//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement AiroplaneSwitch;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Turn off airplane mode to make a call.\")")
	public static AndroidElement airplaneMode_Call;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Press & hold ￼ to unlock.\")")
	public static AndroidElement Screenlck_Star_Device;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Press & hold ￼ for emergency call.\")")
	public static AndroidElement Screenlck_Star_Device_emergencyTXt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"None\")")
	public static AndroidElement Screenlck_None;
	
	@AndroidFindBy(xpath = "//android.view.View[@content-desc='Webpage not available']")
	public static AndroidElement webpageNotavailable;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SetUp_Scout;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement contact_FindContacts;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Please input number or name\")")
	public static AndroidElement phone_find;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
	public static AndroidElement camera_Icon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Albums\")")
	public static AndroidElement GalleryAlbums;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
	public static AndroidElement Calender_NewEvent;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement sound_Recorder;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement settingsIcon;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
	public static AndroidElement music_icon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Artists\")")
	public static AndroidElement artists_music_player;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SIM status']")
	public static AndroidElement sim_status;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network type']")
	public static AndroidElement cellularNwType;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"LTE\")")
	public static AndroidElement LTE;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data roaming']")
	public static AndroidElement data_Roaming;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Data roaming']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_DataRoaming;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Data roaming']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_DataRoaming;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='International data roaming']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_InternationalDataRoaming;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='International data roaming']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_InternationalDataRoaming;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='International data roaming']")
	public static AndroidElement international_data_Roaming;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Roaming']")
	public static AndroidElement data_Roaming_Sprint;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cellular networks']")
	public static AndroidElement cellular_newtworksOptn;//Roaming

	@AndroidFindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public static AndroidElement off_Toggle_data;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile networks']")
	public static AndroidElement mobile_Networks;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_Networks;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular network settings']")
	public static AndroidElement cellular_networks;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preferred network type']")
	public static AndroidElement PreferredNwType_Operators;

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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Quick Settings\")")
	public static AndroidElement quickSettings;

	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement enable;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.music:id/line1\").textContains(\"Music\")")
	public static AndroidElement music;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.music:id/play_indicator\")")
	public static AndroidElement musicIndicator;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select\")")
	public static AndroidElement selectSRList;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement photoCaptureIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"New message\")")
	public static AndroidElement new_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	public static AndroidElement image_Attachment;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Display\")")
	public static AndroidElement display_feature;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sound\")")
	public static AndroidElement sound_feature;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Phone ringtone\")")
	public static AndroidElement Ringtone_feature;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Phone ringtone']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static WebElement Ringtone_Summary;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Sitar Versus Sitar\")")
	public static AndroidElement Ringtone_StarVersus;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Silky Way\")")
	public static AndroidElement Ringtone_SilkyWay;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sleep\")")
	public static AndroidElement Sleep_Optn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Sleep']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static WebElement Sleep_Summary;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"8 hours\")")
	public static AndroidElement eightHrsSleep_Optn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 minutes\")")
	public static AndroidElement thirtyMinSleep_Optn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@resource-id='android:id/content']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static WebElement recording_List;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static WebElement phoneField;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static WebElement saveBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static WebElement addContacts;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static WebElement addContactOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='No contacts']")
	public static WebElement noContacts;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title']")
	public static WebElement addedContact;
	
	@AndroidFindBy(xpath="//android.widget.ScrollView[@resource-id='com.android.contacts:id/compact_contact_editor_fragment']")
	public static MobileElement scrollpage;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='More fields']")
	public static WebElement moreFldOpt;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.contacts:id/expansion_view']")
	public static WebElement expandFld;

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

	@AndroidFindBy(xpath="//android.widget.EditText[@text='IM']")
	public static WebElement IMFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AIM']")
	public static WebElement IMTypeFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Website']")
	public static WebElement websiteFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Notes']")
	public static WebElement notesFld;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CHANGE PHOTO']")
	public static WebElement changePhotoOpt;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Take photo']")
    public static WebElement takePhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
    public static WebElement captureOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement imageOkOpt;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
    public static AndroidElement turnOff_Airplane_PopUp;
	
    @AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
    public static WebElement deleteBtn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete']")
    public static WebElement deleteOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
    public static WebElement newEvent;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Event name']")
    public static WebElement eventName;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Location']")
    public static WebElement locationFld;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Save']")
    public static WebElement saveOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.calendar:id/title']")
    public static WebElement createdEvent;
    
    @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='3']")
    public static WebElement deleteEventsEnabledThree;
    
    @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']")
    public static WebElement deleteEventsEnabled;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Month']")
    public static WebElement monthOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete events']")
    public static WebElement deleteEventsOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select all']")
    public static WebElement selectAllOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Done']")
    public static WebElement doneOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement OkBtn;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Applications']")
    public static WebElement applications;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Calendar']")
    public static WebElement calendar;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Agenda']")
    public static WebElement agendaOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tools']")
    public static WebElement tools;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='App info']")
    public static WebElement appInfoOpt;
    
    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Storage\")")
    public static WebElement storageOpt;
    
    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CLEAR DATA\")")
    public static WebElement clearDataBtn;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
    public static WebElement clock;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add']")
    public static WebElement addOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Time']")
    public static WebElement alarmSetPage;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Today']")
    public static WebElement alarmTodayOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tomorrow']")
    public static WebElement alarmTomorrowOpt;
    
    @AndroidFindBy(xpath="//android.widget.Switch[@text='On']")
    public static WebElement alarmSwitchOn;
    
    @AndroidFindBy(xpath="//android.widget.Switch[@text='Off']")
    public static WebElement alarmSwitchOff;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.deskclock:id/digitalClock']")
    public static WebElement alarmTimeFld;
    
    @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.deskclock:id/time_layout']/../..//android.widget.TextView[@resource-id='com.android.deskclock:id/digital_clock']")
    public static WebElement alarmSetTime;
    
    @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[@resource-id='com.android.deskclock:id/digitalClock']")
    public static WebElement alarmTime;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.sonim.borqs.launcher:id/time_display']")
    public static WebElement homeScreenTime;
    
    @AndroidFindBy(xpath="//android.view.View[@resource-id='com.android.deskclock:id/glow_pad_view']")
    public static WebElement alarmPage;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Location\")")
	public static AndroidElement Location_feature;
    
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"AGREE\")")
	public static AndroidElement location_Agree;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement setUpTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement supportTab;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='App Updater']")
	public static WebElement AppUpdater;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@resource-id='android:id/action_bar']/../..//android.widget.TextView[@text='App Updater']")
	public static WebElement AppUpdater_oreo;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement SonimSetUpWizard;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SafeGuard']")
	public static WebElement Safeguard;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim BLE Connect']")
	public static WebElement BLEconnect;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Error']")
	public static WebElement BLEconnectError;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat']")
	public static WebElement Chat;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Remote Control']")
	public static WebElement RemoteControl;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Care']")
	public static WebElement SonimCare;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Registration\")")
	public static WebElement WarrantyReg;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Device Information']")
	public static WebElement DeviceInfo_App;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Sonim Support']")
	public static WebElement ContactSonimSupport;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
    public static AndroidElement HomeBtn_Wizard;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Chat']")
	public static WebElement Chat_Title;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Remote Support']")
	public static WebElement RemoteSupport;

	@AndroidFindBy(xpath = "//android.view.View[@text='Remote Control']")
	public static WebElement RemoteControl_pge;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/error\")")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.

	 @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New message']")
	    public static WebElement newMessageOpt;
	    
	    @AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	    public static WebElement typeMessageOpt;
	    
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Send']")
	    public static WebElement sendOpt;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
	    public static WebElement messageNowOpt;
	    
	    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
		public static AndroidElement toField_NewMessage;
	    
	    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.mms:id/search_view']")
		public static WebElement searchFld_InMessages;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Declined call']")
	    public static WebElement declinedCall;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Missed call']")
	    public static WebElement missedCall;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Incoming call']")
	    public static WebElement incomingCall;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Outgoing call']")
	    public static WebElement outgoingCall;
	    
	    @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageView[@index='0']")
	    public static WebElement rejectWithSMSTextCallPage;
	    
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Search']")
	    public static WebElement searchOpt;
	    
	    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. What's up?\")")
	   	public static AndroidElement quickResponseSent;
	    
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to SIM']")
	    public static WebElement copyToSIMOpt;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/search_count_text']")
	    public static WebElement matchedContactsFld;
	
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to SIM1']")
	    public static WebElement copyToSIM1Opt;
	
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select']")
	    public static WebElement selectOpt;
	    
	    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select All']")
	    public static WebElement contactsSelectAllOpt;
	    
	    //====================================Oreo=============================================================
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
		public static AndroidElement networkAndInternet;
	    
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"LTE/UMTS/GSM\")")
		public static AndroidElement preferred_network_option_oreo;
	    
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
		public static AndroidElement MobileNetwrks_oreo;
	    
	    @AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile network type']")
		public static AndroidElement mobileNwType;
	    
	    @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile network type']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
		public static AndroidElement networkType_Oreo;
	    
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"System\")")
		public static AndroidElement systemInsettingsList;
	    
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"CDMA/EvDo/GSM/WCDMA\")")
		public static AndroidElement CDMA_Combo_NetwrkTyp;
	    
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"Global\")")
		public static AndroidElement Global_NetwrkTyp;
	    
	    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"GSM/WCDMA preferred\")")
		public static AndroidElement GSM_WCDMA_Network_type;
	    
	    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Turn off Airplane mode to make this call?\")")
		public static AndroidElement airplaneMode_Call_Oreo;
	    
	    @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']"))
		public static AndroidElement enabled_Data_Oreo;
		
		@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']"))
		public static AndroidElement disabled_Data_Oreo;
	    
		@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
		public static AndroidElement first_No_In_CallLog;
		
		@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Clear all call log']")
		public static AndroidElement phone_DeleteCallLog;

		@AndroidFindBy(xpath = "//android.widget.Button[@text='CLEAR']")
		public static AndroidElement phone_ClearOptn;
		
		@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view']")
		public static AndroidElement dailedNum;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/attachment_view\")")
		public static AndroidElement image_Attachment_oreo;
		
		@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
		public static AndroidElement okBtn;

		@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
		public static AndroidElement summaryWIFI_oreo;
		
		@FindBy(how=How.XPATH, using ="//android.widget.Button[@text='Google Search']")
		public static MobileElement SearchIcon_Google_Oreo;
		
		@FindBy(how=How.XPATH, using ="//android.widget.EditText[@text='Search']")
		public static MobileElement Searchenter_Google_Oreo;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Quick toggle\")")
		public static AndroidElement quick_Toggle;
		
		@AndroidFindBy(xpath ="//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
		public static AndroidElement wifi_switch_QuickSet;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
		public static AndroidElement connectedDevices_oreo;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Pair new device\")")
		public static AndroidElement pairNewDeviceOptn;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Charging\")")
		public static AndroidElement Charging_OverUSB_oreo;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"About phone\")")
		public static AndroidElement AboutPhone_feature;
		
		@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
		public static WebElement saveButton;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").resourceId(\"com.android.gallery3d:id/gl_root_view\")")
		public static AndroidElement camera_captured_image;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.borqs.camera:id/gallery\")")
		public static AndroidElement camera_captured_data;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Your recordings']")
		public static AndroidElement yourrecordings;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Audio recordings']")
		public static AndroidElement audiorecordings;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Security & location\")")
		public static AndroidElement securityAndLocation;
		
		@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add']")
		public static WebElement addOpt_oreo;
		
		@AndroidFindBy(xpath = "//android.widget.Button[@text='SWITCH BT ON']")
		public static AndroidElement switchBTOnBtn;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CHECK FOR UPDATES\")")
		public static AndroidElement appUpdater_CheckForUpdates;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"3G\")")
		public static AndroidElement NetwrkType_3G;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").textContains(\"2G\")")
		public static AndroidElement NetwrkType_2G;
		
		@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"LTE (recommended)\")")
		public static AndroidElement LTE_Recommended_Optn;
		
}
