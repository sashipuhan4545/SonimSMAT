package com.xp5S.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class XP5S_Locators_SafeGuard {

	private static AndroidDriver<AndroidElement> aDriver;

	public XP5S_Locators_SafeGuard(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	//Added comment to test
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement setUpTab;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SonimSetupWizard']")
	public static WebElement SonimSetUpWizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SafeGuard']")
	public static WebElement Safeguard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.sonim.safeguard:id/switch_widget\")")
	public static AndroidElement Activation_switch;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
	public static WebElement SG_Version;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public static WebElement SG_Help;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']/../..//android.widget.TextView[@resource-id='android:id/summary']")
	public static WebElement Version;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.safeguard:id/tv_help_description\")")
	public static AndroidElement Help_description;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Applications\")")
	public static AndroidElement Applications;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Features\")")
	public static AndroidElement Features;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Change PIN\")")
	public static AndroidElement ChangePIN;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Application PIN Timeout\")")
	public static AndroidElement ApplicationPINTimeout;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter PIN here\")")
	public static AndroidElement enterPINHereOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement Ok_Button;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select All\")")
	public static AndroidElement select_All;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Save\")")
	public static AndroidElement save;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Enter PIN\")")
	public static AndroidElement Enter_PIN_string;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.safeguard:id/editText\")")
	public static AndroidElement enterPIN_TextFld;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.browser:id/url')]")
	public static AndroidElement DefaultUrlTxt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement contact_FindContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search in number or name\")")
	public static AndroidElement phone_find;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SetUp_Scout;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.borqs.camera:id/zoomoutButton\")")
	public static AndroidElement Camera_view;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
	public static AndroidElement Calender_NewEvent;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Albums\")")
	public static AndroidElement GalleryAlbums;

	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	public static WebElement clock_image;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.android.calculator2:id/simple_pad_circle\")")
	public static AndroidElement calculator_image;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement sound_Recorder;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Artists\")")
	public static AndroidElement artists_music_player;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.caf.fmradio:id/nav_circle\")")
	public static AndroidElement fm_image;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"File Explorer\")")
	public static AndroidElement fileExplorer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup and Restore']")
	public static AndroidElement backUP_reset;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Downloads\")")
	public static AndroidElement Downloads;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please insert Storage card before taking backup\")")
	public static AndroidElement backup_error_text;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static WebElement addContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Unselect All\")")
	public static AndroidElement unselect_All;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/search_src_text\")")
	public static AndroidElement search_field;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.sonim.safeguard:id/check_box\")")
	public static AndroidElement checkbox;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkAndInternet;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Hotspot & tethering\")")
	public static AndroidElement hotspotAndTethering;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
	public static AndroidElement connectedDevices;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement Bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement toggle_switch;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"USB\")")
	public static AndroidElement usb;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Use USB to\")")
	public static AndroidElement Use_USB_to_string;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Security & location\")")
	public static AndroidElement security_Location;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Location\")")
	public static AndroidElement Location;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"AGREE\")")
	public static AndroidElement Agree_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"System\")")
	public static AndroidElement systemInsettingsList;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reset options\")")
	public static AndroidElement Reset_options;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Erase all data (factory reset)\")")
	public static AndroidElement erase_all_data_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Call settings\")")
	public static AndroidElement call_settings;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call blocking\")")
	public static AndroidElement Call_blocking;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Blocked numbers\")")
	public static AndroidElement Blocked_numbers_String;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static AndroidElement contactName;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static AndroidElement contact_Phone;
	
	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static WebElement addContactOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Automation\")")
	public static AndroidElement added_contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Edit\")")
	public static AndroidElement edit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Edit contact\")")
	public static AndroidElement edit_contact_string;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static AndroidElement delete;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.safeguard:id/old_pinET\")")
	public static AndroidElement old_PIN;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.safeguard:id/new_pinET\")")
	public static AndroidElement new_PIN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.safeguard:id/reenter_pinET\")")
	public static AndroidElement reEnter_PIN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Immediate\")")
	public static AndroidElement Immediate_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 Seconds\")")
	public static AndroidElement thirty_Seconds_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"1 Minute\")")
	public static AndroidElement one_Minute_option;
	
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
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement MobileNetwrks_oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data_Oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data_Oreo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement Wifi;	
	
}
