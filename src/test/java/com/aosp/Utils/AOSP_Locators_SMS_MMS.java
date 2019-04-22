package com.aosp.Utils;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class AOSP_Locators_SMS_MMS {

	private static AndroidDriver<AndroidElement> aDriver;

	public AOSP_Locators_SMS_MMS(AndroidDriver<AndroidElement> aDriver) {

		this.aDriver=aDriver;

	}	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"New message\")")
	public static AndroidElement new_Message;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Discard\")")
	public static AndroidElement discard;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Type name/number\")")
	public static AndroidElement toField_NewMessage;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Message;

	// ======================================================================================

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS..

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"Forward\"))")
	public static AndroidElement forward;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"Copy text\"))")
	public static AndroidElement copy_Text;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"Paste\"))")
	public static AndroidElement paste_Text;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.FrameLayout\")).scrollIntoView(new UiSelector().text(\"Settings\"))")
	public static AndroidElement settings_SMS; // This Locator is in Menu List

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.TextView\")).scrollIntoView(new UiSelector().text(\"Group notification message\"))")
	public static AndroidElement gruop_Notification_Meassage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Group notification message']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement enable_Group_Notftn_Msg;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Group notification message']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement disable_Group_Notftn_Msg;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings_Text;  // This Locator is in SMS Settings Page.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ADD CONTACT\")")
	public static AndroidElement add_Contact_Contacts;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement find_Contacts;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Add Contact\")")
	public static AndroidElement add_Contact_Contacts_Menu;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement name_add_Contact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement phoneNo_add_Contact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Save\")")
	public static AndroidElement save_add_Contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Add Contact\")" )
	public static AndroidElement add_Contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search\")")
	public static AndroidElement search_Contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Done\")" )
	public static AndroidElement done;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Search\")")
	public static AndroidElement search_SMS;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search messaging\")")
	public static AndroidElement search_messaging;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/from\")")
	public static AndroidElement searched_SMS_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"No conversations.\")")
	public static AndroidElement no_Conversations;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Vibrate']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement offState_Vibrate;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Vibrate']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement onState_Vibrate;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Pop-up Alert']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement offState_PopUp_Alert;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Pop-up Alert']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement onState_PopUp_Alert;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Restore default settings\")")
	public static AndroidElement restoreDefaultSettings;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Message template\")")
	public static AndroidElement messageTemplate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Add\")")
	public static AndroidElement add_Template;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.mms:id/edit_tempsms_editor']")
	public static AndroidElement new_Template_TextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement CANCEL;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Text (SMS) messages settings\")")
	public static AndroidElement textMessagesSettings;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Edit\")")
	public static AndroidElement edit;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Hi_I_am_in_Meeting\")")
	public static AndroidElement template_1;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"Hi_I_am_in_Meeting\"))")
	public static AndroidElement template_2;

	@AndroidFindBy(uiAutomator="new UiScrollable(new UiSelector().className(\"android.widget.ListView\")).scrollIntoView(new UiSelector().text(\"I_am_Driving\"))")
	public static AndroidElement template_3;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"I_am_Driving\")")
	public static AndroidElement template_4;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ListView\").text(\"Where are you?\")")
	public static AndroidElement template_5;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ListView\").text(\"I_am_Driving\")")
	public static AndroidElement template_6;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Music']")
	public static AndroidElement music;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Add to Contacts\")")
	public static AndroidElement addToContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Create new contact\")")
	public static AndroidElement createNewContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static AndroidElement delete_InMenu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Insert contact\")")
	public static AndroidElement insertContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")
	public static AndroidElement searchedContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Insert from Template\")")
	public static AndroidElement insertFromTemplate;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"IMPORT CONTACTS\")")
	public static AndroidElement importContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Import from .vcf file\")")
	public static AndroidElement importFrom_VCF_File;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	public static AndroidElement zero_Characters_FirstPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='135 / 2']")
	public static AndroidElement chars_SecondPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='146 / 3']")
	public static AndroidElement chars_ThirdPage;	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select All\")")
	public static AndroidElement select_All;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select\")")
	public static AndroidElement select;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Paste\")")
	public static AndroidElement paste;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Copy\")")
	public static AndroidElement copy;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Cut\")")
	public static AndroidElement cut;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send message\")")
	public static AndroidElement sendMessage_Dialer;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Draft\")")
	public static AndroidElement draft_Text;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Discard\")")
	public static AndroidElement discard_Popup;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach photos/videos\")")
	public static AndroidElement attachPhotosVideos;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement photoCaptureIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Record audio\")")
	public static AndroidElement recordAudio;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Slideshow\")")
	public static AndroidElement slideShow;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static WebElement videoCaptureIcon;
	
	

}
