package com.aosp.Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Stability {
	
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Stability(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static AndroidElement contacts;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static AndroidElement Addcontacts;
	
//	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/primary_action_view' and @indwx='0']")
	@FindBy(how=How.XPATH, using ="//android.widget.LinearLayout[contains(@resource-id,'com.android.dialer:id/primary_action_view')]/../..//android.widget.TextView[@text='Test1']")
	public static AndroidElement first_No_In_CallLog;

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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement contact_FindContacts;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select']")
	public static AndroidElement Contact_Select;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Select All']")
	public static AndroidElement Contact_SelectAll;
	
	@AndroidFindBy(xpath = "//android.widget.ListView[@resource-id='com.android.dialer:id/call_history_list']")
	public static AndroidElement   call_Historypage;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Delete']")
	public static AndroidElement contact_delete;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement contact_DeleteBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title']")
	public static AndroidElement contact_Test;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.contacts:id/header_title\").text(\"Testing\")")
	public static AndroidElement contact_Testing;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Testing']")
	public static AndroidElement contactSelect_Testing;


	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static AndroidElement contact_addContactsOptn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement phone_RecentCallName;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement phone_RecentCall;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Sonim\")")
	public static AndroidElement contact_emailid;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='IDC Sonim']")
	public static AndroidElement contact_addressText;

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
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static WebElement videoCaptureIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/video_thumbnail\")")
	public static AndroidElement video_Attachment;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").text(\"com.borqs.camera:id/camera_switcher\")")
	public static AndroidElement CamerIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement photocameraIcon;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").textContains(\"Delete\")")
	public static AndroidElement Delete;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select album\")")
	public static AndroidElement SelectAlbumOptn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select all\")")
	public static AndroidElement SelectallOptn;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Deselect all\")")
	public static AndroidElement DeselectallOptn;			

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static MobileElement OKBtn;
	
	@FindBy(how=How.XPATH, using ="//android.view.ViewGroup[contains(@resource-id,'android:id/action_bar')]/../..//android.widget.TextView[@text='Camera']")
	public static AndroidElement Gallery_Camera;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static MobileElement videoDeleteOption;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Switch to Camera\")")
	public static AndroidElement SwitchToCameraOptn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement videocameraIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Details\")")
	public static MobileElement video_DetailsOption;
	
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
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@index,'2')]/../..//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Network & Internet']")
	public static AndroidElement NetworkInternet;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/content_frame\")")
    public static MobileElement SettingsList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.android.settings:id/main_content\")")
    public static MobileElement wifiList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;
	
	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@content-desc,'Webpage not available')]")
	public static WebElement networkNotAvailable;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Security warning')]")
	public static WebElement networkNotSecured;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"VIEW CERTIFICATE\")")
	public static AndroidElement ViewCertificate;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;
	
//	@AndroidFindBy(xpath="//android.widget.TextView[@text='Outgoing call']")
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Outgoing call')]")
	public static WebElement outgoingCall;
	
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
	
	
	
}
