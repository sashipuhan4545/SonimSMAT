package com.xp8.util;

//import org.openqa.selenium.AndroidElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Stability {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Stability(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}

	//	 @AndroidFindBy(xpath =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FillMemory']")
	public static AndroidElement fillmemoryIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"android:id/parentPanel\")")
	public static AndroidElement memory_Panel;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/empty_list_view\")")
	public static AndroidElement UidaiContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"ALL\")")
	public static AndroidElement ALL_page;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement longpress_FirstContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement AddtoContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static AndroidElement one_Selection_menu;

	@AndroidFindBy(xpath =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement MoreOptnsIcnByXpath;

	@AndroidFindBy(xpath =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;

	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='0']/../..//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;

	/*@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement OK;
	*/
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement Contacts_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement Contacts_Phone;

	@AndroidFindBy(xpath="//*[@text='Delete']")
	public static AndroidElement delete_moreOption;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android:id/icon']")
	public static AndroidElement Choose_Phone;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement Contacts_Email;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_save\")")
	public static AndroidElement Save_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/large_title\")")
	public static AndroidElement ContactTitle;

	@AndroidFindBy(xpath = "//*[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonimtech@gmail.com\")")
	public static AndroidElement contact_Emailid;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static AndroidElement Search_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement findContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement SavedContact;

	@AndroidFindBy(xpath =("//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement AllowOptn;

	@AndroidFindBy(xpath =("//android.widget.ImageView[@content-desc='Call History']"))
	public static AndroidElement callHistoryImage;

	@AndroidFindBy(xpath =("//android.widget.TextView[@text='Call History']"))
	public static AndroidElement callHistory;

	@AndroidFindBy(xpath =("//android.widget.RelativeLayout[@index='1']/../..//android.widget.ImageView[@resource-id='com.android.dialer:id/icon']"))
	public static AndroidElement callHistry_notification;

	@AndroidFindBy(xpath =("//android.widget.TextView[@text='Test1']"))
	public static AndroidElement  contactTest;

	@AndroidFindBy(xpath =("//android.widget.TextView[@text='Test1']/../..//android.widget.ImageView[@content-desc='Call Test1']"))
	public static AndroidElement Call_Contact;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement enabled_Airplane;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement disabled_Airplane;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;

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

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement MoreOptions;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh']")
	public static AndroidElement RefreshOptn;

	@AndroidFindBy(xpath =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet\")")
	public static AndroidElement networkNotAvailable;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Web Page Blocked\")")
	public static AndroidElement WebPageBlocked;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"This site can’t be reached\")")
	public static AndroidElement siteNotloaded;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"404\")")
	public static AndroidElement site404Error;
	
	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'BLOCK')]")
	public static AndroidElement BlockBtn;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'ACCEPT & CONTINUE')]")
	public static AndroidElement AccptBtn;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'NEXT')]")
	public static AndroidElement NextIcon;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'NO THANKS')]")
	public static AndroidElement NothanksBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/swipeableContent\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement firstSMS_InList;


	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement new_Message_Icon;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/create']")
	public static AndroidElement add_NewSMS;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement toField_NewMessage;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field;
	
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement messageField;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement type_Message_enter;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now ï¿½ SMS\")")
	public static AndroidElement justnow_Text; //To validate after sending SMS.

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sendingï¿½']")
	public static AndroidElement sending_Txt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Attach']")
	public static AndroidElement attach_icon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement captureImage_SMS;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement zero_Characters_FirstPage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/text_counter\")")
	public static AndroidElement zero_Characters_FirstPage1;

	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement pictureattach;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Pictures\")")
	public static AndroidElement Pictures;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Videos\")")
	public static AndroidElement videos;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Audio\")")
	public static AndroidElement Audio;
	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='External audio']")
	public static AndroidElement External_Txt;

	@AndroidFindBy(xpath =("//android.widget.RelativeLayout/../..//android.widget.TextView[@text='RecordingSample']"))
	public static AndroidElement selectAudio;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static AndroidElement cameraIcon;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Review done']")
	public static AndroidElement OK_btn_resultConfirmation_dialog;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	//			@AndroidFindBy(xpath =("//android.widget.LinearLayout[@resource-id='com.android.mms:id/image_attachment_view']/../..//android.widget.ImageView[@resource-id='com.android.mms:id/image_content']"))
	public static AndroidElement image_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/video_thumbnail\")")
	public static AndroidElement video_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static AndroidElement videoCaptureIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static AndroidElement videoStopIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_pause\")")
	public static AndroidElement videopauseIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Attach others\")")
	public static AndroidElement attachOthers;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/delete\")")
	public static AndroidElement delete_Icon_SMS; //To validate after sending SMS.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/action_delete\")")
	public static AndroidElement delete_Icon; 

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"NEXT\")")
	public static AndroidElement NEXT_Msg;  // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;  // This Locator is in Messaging.

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
	public static AndroidElement send_Icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
	public static AndroidElement send_SMS;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_MMS_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text;

	@AndroidFindBy(xpath =("//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/cliv_name_textview']"))
	public static AndroidElement Phonebook_firstContact;

	@AndroidFindBy(xpath =("//android.widget.ImageView[@content-desc='Speed dial']"))
	public static AndroidElement speedDail;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static AndroidElement Search_ConatctDailer;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter percentage of memory to fill']")
	public static AndroidElement enterFill_Memory;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Start Filling']")
	public static AndroidElement startFilling;

	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//android.widget.TextView[@text='FilledContent']"))
	public static AndroidElement filedContact_File;

	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//andrandroid.widget.ImageButton[@resoucre-id='com.cyanogenmod.filemanager:id/navigation_view_item_check']"))
	public static AndroidElement checkbox;

	@AndroidFindBy(xpath =("//android.widget.RelativeLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_actionbar']/../..//android.widget.ImageButton[@resource-id='com.cyanogenmod.filemanager:id/ab_search']"))
	public static AndroidElement SearchOptn;

	@AndroidFindBy(xpath ="//android.widget.EditText[contains(@text,'Type your search')]")
	public static AndroidElement SearchTxt;

	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='1']/../..//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement Allow_FileExplorer;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='add new contact']")
	public static AndroidElement add_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name']")
	public static AndroidElement name_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static AndroidElement phone_NewContact;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Saving to']")
	public static AndroidElement contact_SavingTo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement savingTo_Phone;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Save']")
	public static AndroidElement save_Icon;
	
	@AndroidFindBy(xpath =("//android.view.View[@index='0']/../..//android.view.View[@content-desc=' me, Email_Open,']"))
	public static AndroidElement receive_mail;
	
	@AndroidFindBy(xpath =("//android.widget.FrameLayout[@index='2']/../..//android.widget.TextView[@text='GOT IT']"))
	public static AndroidElement gmail_gotIt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Add an email address\")")
	public static AndroidElement add_email_address;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@text='Google']"))
	public static AndroidElement googleAccount;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@text='Google']"))
	public static AndroidElement enetrEmailId;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement add_Account;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[contains(@text,'@gmail.com')]"))
	public static AndroidElement connectedAccount;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"identifierId\")")
	public static AndroidElement email_googleAcnt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement next;

	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement password_googleAcnt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement i_agree;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"MORE\")")
	public static AndroidElement MORE;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/user_id\")")
	public static AndroidElement googleAccount_email;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.gm:id/dismiss_icon\")")
	public static AndroidElement dismiss_icon_Gmail;
	
	@AndroidFindBy(xpath="//*[@text='ACCEPT' or @text='AGREE']")
	public static AndroidElement ACCEPTorAGREE;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"TAKE ME TO GMAIL\")")
	public static AndroidElement TAKE_ME_TO_GMAIL;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.gm:id/compose_button\")")
	public static AndroidElement compose_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.gm:id/to\")")
	public static AndroidElement To_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.gm:id/subject\")")
	public static AndroidElement subject_gmail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Compose email\")")
	public static AndroidElement compose_Mail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/send\")")
	public static AndroidElement send_gmail;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'this conversation')]")
	public static AndroidElement select_Mail;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Email_with_Attachment')]")
	public static AndroidElement click_Mail;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Email_without_Attachment')]")
	public static AndroidElement clickwithout_Mail;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Email_Open')]")
	public static AndroidElement receive_Mail;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/delete\")")
	public static AndroidElement delete_gmail;

	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='1']/../..//android.widget.ImageView[@content-desc='More options']"))
	public static AndroidElement moreOptions_Mail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Forward\")")
	public static AndroidElement forward_Mail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP\")")
	public static AndroidElement skip_Mail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Skip\")")
	public static AndroidElement skip_;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Open navigation drawer')]")
	public static AndroidElement navigate_OptionMail;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='8']/../..//android.widget.TextView[@text='Sent']"))
	public static AndroidElement sent_MailOptn;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='8']/../..//android.widget.TextView[@text='Drafts']"))
	public static AndroidElement draft_MailOptn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.gm:id/add_attachment\")")
	public static AndroidElement attach_gmail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Attach file\")")
	public static AndroidElement attach_File;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='0']/../..//*[contains(@text,'ATT_Stability_Email')]"))
	public static AndroidElement File;
	
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.chrome:id/menu_badge']")
	public static AndroidElement moreUpdateOptions;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove account\")")
	public static AndroidElement remove_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REMOVE ACCOUNT\")")
	public static AndroidElement REMOVE_ACCOUNT;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/listButton']")
	public static AndroidElement listButton_SoundRec;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/recordButton']")
	public static AndroidElement recordButton;	

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.soundrecorder:id/stopButton']")
	public static AndroidElement stopButton;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.soundrecorder:id/rename_edit_text\")")
	public static AndroidElement rename_Edit_Text;

	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static AndroidElement save_SoundRec;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.soundrecorder:id/list_item_title\")")
	public static AndroidElement file_list_SoundRec;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Share']")
	public static AndroidElement share_File;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Gmail\")")
	public static AndroidElement gmail_Share;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='4']/../..//android.widget.ImageButton[@content-desc='Save']"))
	public static AndroidElement saveOptions;// This Locator is for download 
	
	@AndroidFindBy(xpath =("//android.support.v7.widget.RecyclerView[@resource-id='com.google.android.gm:id/thread_list_view']/../..//android.view.View[@index='3']"))
	public static AndroidElement drafts_Audio;
	
	@AndroidFindBy(xpath =("//android.support.v7.widget.RecyclerView[@resource-id='com.google.android.gm:id/thread_list_view']/../..//android.view.View[@index='1']"))
	public static AndroidElement drafts_Video;
	
	@AndroidFindBy(xpath =("//android.support.v7.widget.RecyclerView[@resource-id='com.google.android.gm:id/thread_list_view']/../..//android.view.View[@index='2']"))
	public static AndroidElement drafts_Image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Recording list\")")
	public static AndroidElement recording_ListTitle;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.soundrecorder:id/listButton\")")
	public static AndroidElement recording_List;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.systemui:id/clock_view\")")
	public static AndroidElement Clock_lock_Screen;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement cellularData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement cellularData_OffState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement mobileData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement mobileData_OffState;
	
	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;
	
	@AndroidFindBy(xpath="//*[@text='IDCSONWAP']/..//*[@text='Connected']")
	public static AndroidElement wifi_ConnectedState;
	
	@AndroidFindBy(uiAutomator = "new uiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\").textContains(\"Connected\")")
	public static AndroidElement wifi_ConnectedStateByUiAutomator;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement switch_OffState;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement switch_OnState;

	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.settings:id/password\")")
	public static AndroidElement wifiPasswordTextBox;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
	public static AndroidElement connect;
	
	@AndroidFindBy(xpath="//*[@text='Battery is full' or @text='Sonim AppKey Policy']")
	public static AndroidElement batteryFullorAppKey;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_delete')]")
	 public static AndroidElement DeleteIcon;
	
	@AndroidFindBy(xpath =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static AndroidElement DeleteBtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
	public static AndroidElement ACCEPTCONTINUE; //This Locator is in Chrome Breowser.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT;  //This Locator is in Chrome Breowser.	

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.chrome:id/negative_button\")")
	public static AndroidElement NO_THANKS;  //This L
	
	@AndroidFindBy(xpath="//android.widget.Switch[@text='OFF']")
	public static AndroidElement switch_Off_State;
	
	@AndroidFindBy(xpath ="//android.widget.ImageButton[contains(@resource-id,'com.android.chrome:id/menu_button')]")
	public static AndroidElement MenuChrome;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Settings')]")
	public static AndroidElement SettgsOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Home page')]")
	public static AndroidElement Homepage;

	@AndroidFindBy(xpath ="//android.widget.Switch[contains(@text,'ON')]")
	public static AndroidElement OnHomepge;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement SaveOptn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Save\")")
	public static AndroidElement SaveOptns;
	
	@AndroidFindBy(xpath =("//android.widget.ImageButton[@content-desc='Navigate up']"))
	public static AndroidElement NavigateUpSrchEng;
		
	@AndroidFindBy(xpath =("//android.widget.ImageButton[@content-desc='Go back']"))
	public static AndroidElement NavigateBack;	
	
	@AndroidFindBy(xpath ="//android.widget.TextView[contains(@text,'Open this page')]")
	public static AndroidElement OpnPage;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.chrome:id/homepage_url_edit\")")
	public static AndroidElement OpnPgeField;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='www.google.co.in']")
	public static AndroidElement googleCoIn_Text;
	
	@AndroidFindBy(xpath =("//android.widget.ImageButton[@content-desc='Bookmark this page']"))
	public static AndroidElement BookmarkIcon;
	
	@AndroidFindBy(xpath =("//android.widget.ImageButton[@content-desc='Edit bookmark']"))
	public static AndroidElement editBookmark;
	
	@AndroidFindBy(xpath =("//android.widget.TextView[@content-desc='Bookmarks']"))
	public static AndroidElement Bookmarksoptn;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")
	public static AndroidElement urlBar_Chrome;

	@AndroidFindBy(xpath="//*[@text='Mobile bookmarks']")
	public static AndroidElement MobileBookMark;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@resource-id='com.android.chrome:id/more']")
	public static AndroidElement BookmarkmoreOptions;// This Locator is for tagName "android.widget.ImageButton".

	@AndroidFindBy(xpath="//*[@content-desc='Close dialog']") 
	public static AndroidElement closeIcon;
	
	@AndroidFindBy(xpath="//android.widget.ProgressBar[@index='5']")
	public static AndroidElement progressBar_chrome;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.chrome:id/toolbar_shadow\")")
	public static AndroidElement toolbarSlider;
	
	@AndroidFindBy(xpath="//*[@content-desc='Close']") 
	public static AndroidElement close;
	
	@AndroidFindBy(xpath="//*[@text='att.net']") 
	public static AndroidElement attnet;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Google')")
	public static AndroidElement GooglePage;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Home - Welcome to att.net')")
	public static AndroidElement AttHomePage;// This Locator is for tagName "android.widget.ImageButton".
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Sonim Cloud')")
	public static AndroidElement SonimCloudPage;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'www.ebay.com')")
	public static AndroidElement ebayPage;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Yahoo')")
	public static AndroidElement yahooPage;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Amazon.com')")
	public static AndroidElement amazonPage;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.chrome:id/bookmark_row']/../..//*[(@text='https://www.amazon.com')")
	public static AndroidElement amazonPage1;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'Home - YouTube')")
	public static AndroidElement youtubePage;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.chrome:id/bookmark_row']/../..//android.widget.TextView[contains(@text,'www.nytimescom - Google Search')")
	public static AndroidElement nyTimesPage;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'CLEAR BROWSING DATAï¿½')]")
	public static AndroidElement ClearBrowsngDataOptn;

	@AndroidFindBy(xpath ="//android.widget.Spinner[contains(@resource-id,'com.android.chrome:id/spinner')]")
	public static AndroidElement ClrDataDropdown;

	@AndroidFindBy(xpath ="//android.widget.CheckedTextView[contains(@text,'All time')]")
	public static AndroidElement begningTimeOptn;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'CLEAR DATA')]")
	public static AndroidElement ClrDataBtn;

	@AndroidFindBy(xpath =("//android.widget.TextView[@content-desc='Search']"))
	public static AndroidElement HistrySearchIcon;

	@AndroidFindBy(xpath ="//android.widget.EditText[contains(@text,'Search your history')]")
	public static AndroidElement SearchField;

	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'CLEAR')]")
	public static AndroidElement HistryClear;
	
	@AndroidFindBy(xpath="//*[@text='SIGN UP']") 
	public static AndroidElement SignUpLink;
	
	@AndroidFindBy(xpath="//*[@text='Sign-up']") 
	public static AndroidElement SignUpPage;
	
	/////Messaging/////////////////////
	
	@AndroidFindBy(xpath="//*[@text='NOT NOW']") 
	public static AndroidElement notNow;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static AndroidElement photoView;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@text='No photos']")
	public static AndroidElement photoViewempty;
	
//	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"MOVE TO TRASH\")")
	
	@AndroidFindBy(xpath ="//android.widget.Button[contains(@text,'MOVE TO TRASH')]")
	public static AndroidElement move_To_Trash;
	
	@AndroidFindBy(xpath="//*[contains(@content-desc,'Cancel')]")
	public static AndroidElement cancel_Photos;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.photos:id/move_to_trash\")")
	public static AndroidElement move_To_Trash1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.photos:id/move_to_trash\")")
	public static AndroidElement delete_Icon_photo; //
	
	//---------------------------------------------Stabilization starts from here---------------------------------------------// 
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").instance(0).text(\"ON\")")
	public static AndroidElement mobileDataOption;
	
	@AndroidFindBy(id ="terms_accept")
	public static AndroidElement ACCEPTCONTINUEByID;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"ACCEPT & CONTINUE\")")
	public static AndroidElement ACCEPTCONTINUEByText;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"ACCEPT & CONTINUE\")")
	public static AndroidElement ACCEPTCONTINUEByResourceId;	
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='ACCEPT & CONTINUE']")
	public static AndroidElement ACCEPTCONTINUEByXpath;	
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public static AndroidElement switch_OffStateByXpath;
		
	@AndroidFindBy(id="password")
	public static AndroidElement wifiPasswordTextBoxById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.EditText\").index(\"1\")")
	public static AndroidElement wifiPasswordTextBoxByIndex;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@id='password']")
	public static AndroidElement wifiPasswordTextBoxByXpath;
	
	@AndroidFindBy(id="button1")
	public static AndroidElement connectById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").textContains(\"CONNECT\")")
	public static AndroidElement connectByContains;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONNECT']")
	public static AndroidElement connectByXpath;
	
	@AndroidFindBy(id="next_button")
	public static AndroidElement NEXTById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").textContains(\"NEX\")")
	public static AndroidElement NEXTByContains;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").text(\"NEXT\")")
	public static AndroidElement NEXTByText;
	
	@AndroidFindBy(xpath ="//android.widget.Button[@text='NEXT']")
	public static AndroidElement NEXTByXpath;
	
	@AndroidFindBy(id="com.android.chrome:id/negative_button")
	public static AndroidElement NO_THANKS_By_Id;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").textContains(\"thank\")")
	public static AndroidElement NO_THANKS_By_Contains;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").text(\"No, thanks\")")
	public static AndroidElement NO_THANKS_By_Text;
	
	@AndroidFindBy(xpath ="//android.widget.Button[@text='No, thanks']")
	public static AndroidElement NO_THANKS_By_Xpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").textContains(\"DOWNLOAD WHEN\")")
	public static AndroidElement networkNotAvailableByContains;
	
	@AndroidFindBy(xpath = "//android.view.View[@text='No Internet']")
	public static AndroidElement networkNotAvailableByXpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").textContains(\"Blocked\")")
	public static AndroidElement WebPageBlockedByContains;
	
	@AndroidFindBy(xpath ="//android.view.View[@text='Web Page Blocked']")
	public static AndroidElement WebPageBlockedByXpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").textContains(\"ERR_CONNECTION_RESET\")")
	public static AndroidElement siteNotloadedByContains;
	
	@AndroidFindBy(xpath = "//android.view.View[@text='ERR_CONNECTION_RESET']")
	public static AndroidElement siteNotloadedByXpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").textContains(\"The site you were\")")
	public static AndroidElement site404ErrorByContains;
	
	@AndroidFindBy(xpath = "//android.view.View[@text='404']")
	public static AndroidElement site404ErrorByXpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")")
	public static AndroidElement tabSwitcherButton;
	
	@AndroidFindBy(id="com.android.chrome:id/tab_switcher_button")
	public static AndroidElement tabSwitcherButtonById;
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@id='tab_switcher_button']")
	public static AndroidElement tabSwitcherButtonByXpath;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").descriptionContains(\"open tabs, tap to switch tabs\")")
	public static AndroidElement tabSwitcherButtonByContectDesc;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/menu_button\")")
	public static AndroidElement MoreOptnsIcn;
	
	@AndroidFindBy(id = "menu_button")
	public static AndroidElement MoreOptnsIcnById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").descriptionContains(\"More options\")")
	public static AndroidElement MoreOptnsIcnByContectDesc;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Close all tabs\")")
	public static AndroidElement closeAllBrowserTabs;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.chrome:id/menu_item_text\")")
	public static AndroidElement closeAllBrowserTabsByUiSelectorId;
	
	@AndroidFindBy(id = "menu_item_text")
	public static AndroidElement closeAllBrowserTabsById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"Close all tabs\")")
	public static AndroidElement closeAllBrowserTabsByContectDesc;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@id='menu_item_text']")
	public static AndroidElement closeAllBrowserTabsByXpath;
	
	//Locators for wifi password textfield
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_PsswdBx; // Resource ID
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(1)")
	public static AndroidElement wifi_PsswdBx_index; // index
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").instance(0)")
	public static AndroidElement wifi_PsswdBx_instance; // Instance
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Your connection is not private\")")
	public static AndroidElement connectionNotPrivate;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\")")
	public static AndroidElement Wifi_IDC_UiSelector;
	
	
	
}
