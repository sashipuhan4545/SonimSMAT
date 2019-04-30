package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class XP8_Locators_SafeGuard {

private static AndroidDriver<AndroidElement> aDriver;
	
	public XP8_Locators_SafeGuard(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SafeGuard']")
	public static WebElement Safeguard;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Setup']")
	public static WebElement setUpTab;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static WebElement AppListIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.sonim.safeguard:id/switch_widget\")")
	public static AndroidElement Activation_switch;
	
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

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
	public static WebElement SG_Version;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
	public static WebElement SG_Help;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Version']/../..//android.widget.TextView[@resource-id='android:id/summary']")
	public static WebElement Version;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.safeguard:id/tv_help_description\")")
	public static AndroidElement Help_description;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement Ok_Button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select All\")")
	public static AndroidElement select_All;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SAVE\")")
	public static AndroidElement save;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Enter PIN\")")
	public static AndroidElement Enter_PIN_string;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.safeguard:id/editText\")")
	public static AndroidElement enterPIN_TextFld;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.browser:id/url')]")
	public static AndroidElement DefaultUrlTxt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title_Msg;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messaging_Title_CallScreening;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/floating_action_button\")")
	public static AndroidElement phone_dail;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SetUp_Scout;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement Camera_view;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;

	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='New event']")
	public static AndroidElement Calender_NewEvent;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").textContains(\"Albums\")")
	public static AndroidElement PhotosAlbums;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clock']")
	public static WebElement clock_view;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").resourceId(\"com.google.android.calculator:id/pad_numeric\")")
	public static AndroidElement calculator_pannel;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
	public static AndroidElement sound_Recorder;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Artists\")")
	public static AndroidElement artists_music_player;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.caf.fmradio:id/btn_onoff\")")
	public static AndroidElement fm_on_off;

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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement chrome;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Image\").resourceId(\"hplogo\")")
	public static AndroidElement chrome_google_image;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Drive\")")
	public static AndroidElement Drive;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.docs:id/branded_fab\")")
	public static AndroidElement drive_add_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Duo\")")
	public static AndroidElement Duo;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"High quality video calling with Google Duo\")")
	public static AndroidElement Duo_pop_up;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement I_agree_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Give access\")")
	public static AndroidElement Give_access;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.calendar:id/image\")")
	public static AndroidElement calender_image;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.calendar:id/floating_action_button\")")
	public static AndroidElement calender_floating_btn;
	
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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement call_settings;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call blocking\")")
	public static AndroidElement Call_blocking;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Blocked numbers\")")
	public static AndroidElement Blocked_numbers;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='First name']")
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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement Wifi;	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement Contact_Save_button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_edit\")")
	public static AndroidElement Contact_edit_button;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement more_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete\")")
	public static AndroidElement Contact_delete_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement add_contact_floting_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unselect All\")")
	public static AndroidElement unselect_All;
	
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

/*	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search applications\")")
	public static AndroidElement Search_applications;
	*/
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='android:id/search_plate']/../..//android.widget.EditText[@text='Search applications']")
	public static AndroidElement Search_applications;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.sonim.safeguard:id/check_box\")")
	public static AndroidElement messaging_app;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement cancel_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").resourceId(\"com.google.android.calculator:id/pad_numeric\")")
	public static AndroidElement Calculator_number_board;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Sign in\")")
	public static AndroidElement Google_SignIn_page;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Show Calendar List and Settings drawer']")
	public static AndroidElement calender_view;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Google Search\")")
	public static AndroidElement Google_Search;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement add_ContactIcon_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Photos\")")
	public static AndroidElement Photos;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
	public static AndroidElement recordButton;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"File Manager\")")
	public static AndroidElement File_Manager;
	
	@AndroidFindBy(xpath="//*[@content-desc='Open navigation drawer' or @content-desc='More options']")
	public static AndroidElement open_Navigation_Drawer;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")")
	public static AndroidElement Clock;
	
}
