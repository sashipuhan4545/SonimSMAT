package com.xp3.Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_DeviceStability {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_DeviceStability(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement contact_DeleteBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title']")
	public static AndroidElement contact_Test;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.contacts:id/header_title\").text(\"Testing\")")
	public static AndroidElement contact_Testing;
	
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
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static AndroidElement zero_Characters_Firstcontact;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete']")
	public static AndroidElement contact_delete;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sonim\")")
	public static AndroidElement contact_emailid;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IDC Sonim']")
	public static AndroidElement contact_addressText;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement MobileNetwrks;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data usage']")
	public static AndroidElement dataUsageOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement cellular_DataON;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement cellular_DataOFF;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/error\")")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"New message\")")
	public static AndroidElement new_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement toField_NewMessage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	public static AndroidElement zero_Characters_FirstPage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	public static AndroidElement image_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement photoCaptureIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK;
	
	@FindBy(how=How.XPATH, using ="//android.widget.LinearLayout[contains(@resource-id,'com.android.dialer:id/primary_action_view')]/../..//android.widget.TextView[@text='Test1']")
	public static AndroidElement first_No_In_CallLog;
	
	@FindBy(how=How.XPATH, using ="//android.widget.LinearLayout[contains(@resource-id,'com.android.dialer:id/primary_action_view')]/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement first_No_In_CallLog1;
	
	@FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@resource-id,'com.android.dialer:id/call_detail')]/../..//android.widget.LinearLayout[@resource-id='com.android.dialer:id/caller_information']")
	public static AndroidElement first_No_Info;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;
		
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.contacts:id/content']/../..//android.widget.EditText[@resource-id='com.android.contacts:id/search_view']"))
	public static AndroidElement find_Contacts;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Network & Internet']")
	public static AndroidElement NetworkInternet;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/../..//android.widget.CheckBox[@text='Don't ask again']"))
	public static AndroidElement dontAsk_Again;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;  // This Locator is in Messaging.
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.packageinstaller:id/button_group']/../..//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']"))
	public static AndroidElement allow_Permission1;  // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;
	
	@FindBy(how=How.XPATH, using =("//android.widget.Switch[@resource-id='com.android.settings:id/switch_bar']/../..//android.widget.Switch[@text='On']"))
	public static AndroidElement enabled_wifi;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;
	
	@FindBy(how=How.XPATH, using =("//android.widget.Switch[@resource-id='com.android.settings:id/switch_bar']/../..//android.widget.Switch[@text='Off']"))
	public static AndroidElement disabled_wifi;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;
	
	@FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@index,'2')]/../..//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/main_content\")")
    public static MobileElement wifiList;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='1234567890sonim']")
	public static AndroidElement wifi_Dellas;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='dlink-F092-media']")
	public static AndroidElement wifi_Cannada;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/content_frame\")")
    public static MobileElement SettingsList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[contains(@content-desc,'Call Mobile')]/../..//android.widget.ImageView[@resource-id='com.android.contacts:id/icon']"))
//	@FindBy(how=How.XPATH, using ="//android.widget.RelativeLayout[contains(@content-desc,'Call Mobile')]")
	public static WebElement callNum;
	
	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@content-desc,'Webpage not available')]")
	public static WebElement networkNotAvailable;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static WebElement videoCaptureIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkAndInternet;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data_Oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data_Oreo;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement MobileNetwrks_oreo;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'BLOCK')]")
	public static WebElement BlockBtn;
	
	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@text,'Web Page Blocked')]")
	public static WebElement WebPageBlocked;

	@AndroidFindBy(xpath = "//android.view.View[@text='Webpage not available']")
	public static AndroidElement webpageNotavailable;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"No network connection\")")
	public static AndroidElement noNetworkTitle;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement cancelButton; 
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Google Search')]")
	public static MobileElement SearchIcon_Google;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@content-desc,'Search')]")
	public static MobileElement Searchenter_Google;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Go to URL']")
	public static AndroidElement URLOption;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.browser:id/url\")")
	public static AndroidElement UrlTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
	public static AndroidElement turnOff_Airplane_PopUp;
	
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public static AndroidElement CANCEL;
	
	@AndroidFindBy(xpath="//*[@content-desc='End Call' or @content-desc='End call']")
	public static AndroidElement end_Call;
	
	@AndroidFindBy(xpath="//*[@text='XP3800' or@text='XP5800' or @text='XP8800' or @text='KWSA50K' or @text='KWSA80K']")
	public static AndroidElement BT_Devices;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"When Bluetooth is turned on\")")
	public static AndroidElement BT_Disable_Text;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Pair new device\")")
	public static AndroidElement BT_Pair_new;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='android:id/title_template']/../..//android.widget.TextView[@text='Discard']"))
	public static AndroidElement discard_msg;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;
	
	@AndroidFindBy(xpath="//*[@text='Connected']")
	public static AndroidElement wifi_Connected;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/name\")")
	public static AndroidElement dialerText;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Homepage']")
	public static AndroidElement HomepageOption;
	
	@FindBy(how=How.XPATH, using =("//android.widget.ListView[@index='0']/../..//android.view.View[@text='portal.uscellular']"))
	public static AndroidElement Homepage;
	
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@resource-id='android:id/content']/../..//android.widget.VideoView[@resource-id='com.android.gallery3d:id/surface_view']"))
	public static AndroidElement VideoView;
	
	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@resource-id='android:id/decor_content_parent']/../..//android.view.View[@resource-id='com.android.gallery3d:id/gl_root_view']"))
	public static AndroidElement VideoView1;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Details']")
	public static AndroidElement DetailsOption;
	
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@resource-id='android:id/customPanel']/../..//android.widget.TextView[contains(@text,'Duration : 00')]"))	
	public static AndroidElement duration_Video;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[contains(@text,'No recording')]"))	
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
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allowButton; 
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.soundrecorder:id/rename_edit_text')]")
	public static AndroidElement recordingName;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Record audio\")")
	public static AndroidElement recordAudio;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ScrollView\").resourceId(\"com.android.mms:id/multi_attachment_editor_scroll_view\")")
	public static AndroidElement attachRecordAudio;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement contact_FindContacts;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static AndroidElement contacts;
	
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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement videocameraIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;
	
	


}
