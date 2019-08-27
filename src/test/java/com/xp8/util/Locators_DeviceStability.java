package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_DeviceStability {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_DeviceStability(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;


	//	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.google.android.apps.messaging:id/camera_capture_button']")
	public static WebElement cameraIcon1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"now\")")
	public static AndroidElement now;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Just now\")")
	public static AndroidElement now_Text1; //To validate after sending SMS.
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")	
	public static AndroidElement TO_Field1;
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement messageField;
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_timestamp\")")
	public static AndroidElement message_Summary;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SendingÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¦']")
	public static AndroidElement sending_Txt1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/char_counter\")")
	public static AndroidElement zero_Characters_FirstPage2;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Add an attachment']")
	public static AndroidElement attach_icon1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/empty_list_view\")")
	public static AndroidElement UidaiContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"ALL\")")
	public static AndroidElement ALL_page;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement longpress_FirstContact;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement longpress_FirstContact_O;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement AddtoContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"0 selected\")")
	public static AndroidElement Selection_menu1;

	@AndroidFindBy(xpath  = "//android.widget.Button[@index='1']")
	public static AndroidElement Selection_menu2;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/popup_list_title\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu1;
	@AndroidFindBy(xpath  = "//android.widget.Button[@index='0']")
	public static AndroidElement ALL_Selection_menu2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static WebElement one_Selection_menu;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement MoreOptnsIcn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete']"))
	public static AndroidElement deleteContactOptn1;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@index='3']"))
	public static AndroidElement deleteContactOptn3;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static AndroidElement deleteContactOptn2;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your contacts list is empty']")
	public static AndroidElement no_Contacts;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtnopt1;
	@AndroidFindBy(xpath = "//android.widget.Button[@index='1']")
	public static AndroidElement OKBtnopt3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement OKBtnopt2;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	public static AndroidElement OKBtn1;
	@AndroidFindBy(xpath ="//android.widget.TextView[@index='5']")
	public static AndroidElement OKBtn3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/btn_ok\")")
	public static AndroidElement OKBtn2;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement Contacts_Name;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement test1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement Contacts_Phone;


	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android:id/icon']")
	public static AndroidElement Choose_Phone;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement Contacts_Email;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_save\")")
	public static AndroidElement Save_ConatctIcon;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/editor_menu_save_button\")")
	public static AndroidElement Save_ConatctIcon1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement  Save_ConatctIcon2;
     @AndroidFindBy(xpath = "//android.widget.Button[@index='0']")
     
     public static AndroidElement Save_ConatctIcon3;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/large_title\")")
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']"))
	public static WebElement ContactTitle;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='More fields']")
	public static AndroidElement moreFields_NewContact;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Address']")
	public static AndroidElement address_NewContact;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='SIM Card']")
	public static AndroidElement savingTo_SIM;


	@AndroidFindBy(xpath = "//*[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonimtech@gmail.com\")")
	public static AndroidElement contact_Emailid;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static AndroidElement Search_ConatctIcon;
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Search contacts']")
	public static AndroidElement Search_ConatctIcon1;
	@AndroidFindBy(xpath="//android.widget.TextView[@index='2']")
	public static AndroidElement Search_ConatctIcon2;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Find contacts\")")
	public static AndroidElement findContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Search contacts\")")
	public static AndroidElement findContacts_O;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement SavedContact;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement AllowOptn;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settings_Contact;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement defaultAccount_NewContacts;

	@AndroidFindBy(xpath="//*[@text='PHONE']")
	public static AndroidElement PHONE_RadioBtn;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"More options\")")
	public static AndroidElement morecal;
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".
	@AndroidFindBy(xpath="//*[@index='3']")
	public static AndroidElement moreOptions1;
	@AndroidFindBy(xpath="//android.widget.ImageView[@resourceId='com.android.chrome:id/menu_badge']")
	public static AndroidElement updateMoreOptions;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Call History']"))
	public static AndroidElement callHistoryImage;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call History']"))
	public static WebElement callHistory;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call history']"))
	public static AndroidElement callHistory_O;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@index='0']"))
	public static AndroidElement callHistory_O1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/title\")")
	public static AndroidElement callHistory_O2;


	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/../..//android.widget.ImageView[@resource-id='com.android.dialer:id/icon']"))
	public static AndroidElement callHistry_notification;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']"))
	public static AndroidElement  contactTest;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']/../..//android.widget.ImageView[@content-desc='Call Test1']"))
	public static AndroidElement Call_Contact;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']/../..//android.widget.ImageView[@index='3']"))
	public static AndroidElement Call_Contact1;
	@FindBy(xpath = "//android.widget.ImageView[@content-desc='Call Test3']")
	public static AndroidElement Call_Contact4;
	@FindBy(id =("com.android.dialer:id/primary_action_button"))
	public static AndroidElement Call_Contact2;
	@FindBy(xpath = "//android.widget.ImageView[@index='1']")
	public static AndroidElement Call_Contact3;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Airplane;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"android:id/switch_widget\")")
	public static AndroidElement switch_widget;	

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Wi-Fi']")
	public static AndroidElement wifi;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"On\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"Off\")")
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
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(1)")
	public static AndroidElement wifi_enterPwd_index; // index

	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.settings:id/forget_button\")")
	public static AndroidElement wifi_IDC_ForgetBtn1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(\"0\")")
	public static AndroidElement wifi_IDC_ForgetBtn2;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Connected']")
	public static AndroidElement wifi_IDC_Connected;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/alertTitle\")")
	public static AndroidElement SSIDTxt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement MoreOptions;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='2']")
	public static AndroidElement MoreOptions2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/dialtacts_options_menu_button\")")
	public static AndroidElement MoreOptions11;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement MoreOptions1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh']")
	public static AndroidElement RefreshOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
	public static AndroidElement connectedwifi;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi-Fi\")")
	public static AndroidElement WiFi_feature;

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@content-desc,'Webpage not available')]")
	public static WebElement networkNotAvailable;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ACCEPT & CONTINUE')]")
	public static WebElement AccptBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NEXT')]")
	public static WebElement NextIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NO THANKS')]")
	public static WebElement NothanksBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/swipeableContent\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement firstSMS_InList;
	
	@AndroidFindBy(id="com.google.android.apps.messaging:id/swipeableContainer")
	public static AndroidElement firstSMS_InList11;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement delete_Confirm_s;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement delete_Confirm_s1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement delete_Confirm1;
	@AndroidFindBy(xpath="//android.widget.Button[@index='1']")
	public static AndroidElement delete_Confirm2;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement new_Message_Icon;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/create']")
	public static AndroidElement add_NewSMS;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='1']")
	public static AndroidElement add_NewSMS11;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
	public static AndroidElement add_NewSMS1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement toField_NewMessage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement messageField1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement contactPicker;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field_phno1;
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement TO_Field_phno2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement TO_Field_phno3;

	

	
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter;

	
	@AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	public static AndroidElement type_text1;
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement type_text2;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='5']")
	public static AndroidElement type_text3;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement type_Message;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement type_Message_enter;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Just now']")
	public static AndroidElement now_Text11;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now . SMS\")")
	public static AndroidElement justnow_Text; //To validate after sending SMS.

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending...']")
	public static AndroidElement sending_Txt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Attach']")
	public static AndroidElement attach_icon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Attach']")
	public static AndroidElement attach_icon_O;
	
	@AndroidFindBy(xpath="//*[@text='Delete']")
	public static AndroidElement delete_moreOption;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete_moreOption1;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;
	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement delete_Thread2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/title\")")
	public static AndroidElement delete_Thread1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement captureImage_SMS;
	
	@AndroidFindBy(xpath="//*[@index='0' and @resource-id='com.google.android.apps.messaging:id/swipeableContainer']")
	public static AndroidElement firstSMS_InList1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement send_Icon1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='0']")
	public static AndroidElement zero_Characters_FirstPage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/text_counter\")")
	public static AndroidElement zero_Characters_FirstPage1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/text_counter\")")
	public static AndroidElement zero_Characters_FirstPage_O;
	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='1']")
	public static AndroidElement zero_Characters_FirstPage_s;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement capturePicture3;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Shutter']")
	public static AndroidElement capturePicture;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@index='0']")
	public static AndroidElement capturePicture2;
	
	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='1']/../..//android.widget.TextView[@text='Camera']")
	public static AndroidElement capturePicture1;
	

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='5']/../..//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static AndroidElement capturePicture_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_shutter_icon\")")
	public static AndroidElement captureVideo_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_send_icon\")")
	public static AndroidElement send_O;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='org.codeaurora.snapcam:id/shutter_button']")
	public static WebElement cameraIcon;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Camera']")
	public static AndroidElement cameraIcon_O;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Review done']")
	public static WebElement OK_btn_resultConfirmation_dialog;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/image_content\")")
	//			@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.mms:id/image_attachment_view']/../..//android.widget.ImageView[@resource-id='com.android.mms:id/image_content']"))
	public static AndroidElement image_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.mms:id/video_thumbnail\")")
	public static AndroidElement video_Attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture video\")")
	public static AndroidElement captureVideo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/video_capture\")")
	public static WebElement videoCaptureIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static WebElement videoStopIcon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/video_pause\")")
	public static WebElement videopauseIcon;

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

//	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
//	public static AndroidElement send_Icon;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/send_button_sms\")")
	public static AndroidElement send_Icon;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Send']")
	public static AndroidElement send_Icon3;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='6']")
	public static AndroidElement send_Icon2;
	
//	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
//	public static AndroidElement send_SMS;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement send_SMS; 
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date_view\")")
	public static AndroidElement sended_SMS; 

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_MMS_Icon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text;

	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/cliv_name_textview']"))
	public static AndroidElement Phonebook_firstContact;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Speed dial']"))
	public static WebElement speedDail;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static WebElement Search_ConatctDailer;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Enter percentage of memory to fill']")
	public static AndroidElement enterFill_Memory;

	@AndroidFindBy(xpath="//android.widget.Button[@text='Start Filling']")
	public static AndroidElement startFilling;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//android.widget.TextView[@text='FilledContent']"))
	public static AndroidElement filedContact_File;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_view_details_item']/../..//andrandroid.widget.ImageButton[@resoucre-id='com.cyanogenmod.filemanager:id/navigation_view_item_check']"))
	public static AndroidElement checkbox;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.cyanogenmod.filemanager:id/navigation_actionbar']/../..//android.widget.ImageButton[@resource-id='com.cyanogenmod.filemanager:id/ab_search']"))
	public static WebElement SearchOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Type your search')]")
	public static WebElement SearchTxt;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/../..//android.widget.Button[@text='ALLOW']"))
	public static AndroidElement Allow_FileExplorer;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='add new contact']")
	public static AndroidElement add_NewContact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Name\")")
	public static AndroidElement name_NewContact;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static AndroidElement phone_NewContact;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"More fields\")")
	public static AndroidElement more_Fields;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement add_ContactIcon_O;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Saving to']")
	public static AndroidElement contact_SavingTo;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement savingTo_Phone;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static AndroidElement save_Icon;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.chrome:id/url_bar']")
	public static AndroidElement urlBar_Chrome;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.chrome:id/search_box_text']")
	public static AndroidElement urlBar_Chrome_O;
	
	@AndroidFindBy(xpath="//android.view.View[@text='Google offered in:']")
	public static AndroidElement google_Logo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='www.google.co.in']")
	public static AndroidElement googleCoIn_Text;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement cellularData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement cellularData_OffState;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement mobileData_OnState;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Standard data']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement mobileData_OffState;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Email']")
	public static AndroidElement email_NewContact;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/from']")
	public static AndroidElement firstSMS_InList_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
	public static AndroidElement moreOption_O;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/select_All_conv\")")
	public static AndroidElement ALL_Msg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/delete_btn\")")
	public static AndroidElement delete_Btn;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='5']")
	public static AndroidElement add_NewSMS_O1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.verizon.messaging.vzmsgs:id/composeFab\")")
	public static AndroidElement add_NewSMS_O;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='Start chat']")
	public static AndroidElement add_NewSMS_s;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement add_NewSMS_s1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").descriptionContain(\"Start chat\")")
	public static AndroidElement add_NewSMS_s2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").index(\"4\")")
	public static AndroidElement add_NewSMS_s3;
	@AndroidFindBy(id="com.android.mms:id/create")
	public static AndroidElement createSMS;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.verizon.messaging.vzmsgs:id/contacts_list_view']/../..//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement contact_Select;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")	
	public static AndroidElement TO_Field_O;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement TO_Field_O1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type a messageÃƒÂ¢Ã¢â€šÂ¬Ã‚Â¦\")")
	public static AndroidElement messageField_O;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/compose_embedded_text_editor\")")
	public static AndroidElement messageField_O1;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='5']")
	public static AndroidElement messageField_O2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Conversations\")")
	public static AndroidElement Conversations;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement send_Icon_O;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_container\")")
	public static AndroidElement send_Icon_B;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc='Send SMS']")
	public static AndroidElement send_Icon_B1;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']")
	public static AndroidElement send_Icon_B2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete Messages\")")
	public static AndroidElement delete_Messages;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement Delete;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delivered\")")
	public static AndroidElement Delivered;
	@AndroidFindBy(id ="com.verizon.messaging.vzmsgs:id/status")
	public static AndroidElement Delivered1;
	
	@AndroidFindBy(id ="com.google.android.apps.messaging:id/message_status")
	public static AndroidElement now_sms;
	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").descriptionContains(\"Now\")")
	public static AndroidElement now_sms3;
	@AndroidFindBy(xpath ="//android.widget.TextView[@content-desc='Now, SMS']")
	public static AndroidElement now_sms2;
	
	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Now . SMS']")
	public static AndroidElement now_sms1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement StartMessaging;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement skipProvisioning;
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement Messageing;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.chrome:id/tab_switcher_button\")")
	public static AndroidElement switcherButton_Chrome;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.chrome:id/menu_button\")")
	public static AndroidElement menuButton_Chrome;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.chrome:id/menu_item_text\")")
	public static AndroidElement closeAllTabs_Chrome;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/add_attachment_first' or @resource-id='com.google.android.apps.messaging:id/attach_media_button']")
	public static AndroidElement attachment;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture_MMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")
	public static AndroidElement done_1;
	
	@AndroidFindBy(xpath="//*[@text='Camera']")
	public static AndroidElement capture_pictures_or_video;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.messaging:id/camera_capture_button\")")
	public static AndroidElement take_picture_MMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/btnAttach\")")
	public static AndroidElement button_Attach_SMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/btnQuickCamera\")")
	public static AndroidElement camera_MMS;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_shutter_icon\")")
	public static AndroidElement cameraCapture_Icon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_send_icon\")")
	public static AndroidElement send_Cam_SMS;	
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.verizon.messaging.vzmsgs:id/kanvas_modes_grid']/../..//android.widget.TextView[@text='PHOTOS']")
	public static AndroidElement OptionPhoto_Icon;
	
	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.verizon.messaging.vzmsgs:id/kanvas_modes_grid']/../..//android.widget.TextView[@text='VIDEO']")
	public static AndroidElement OptionVideo_Icon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/kanvas_shutter_icon\")")
	public static AndroidElement cameraVideo_Icon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/unread_conv_messages\")")
	public static AndroidElement unread_Conv_Messages;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"1 Unread Message\")")
	public static AndroidElement unread_Conv_Messages1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[contains(@text,'@gmail.com')]"))
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
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove account\")")
	public static AndroidElement remove_Account;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REMOVE ACCOUNT\")")
	public static AndroidElement REMOVE_ACCOUNT;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement add_Account;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Skip\")")
	public static AndroidElement skip_;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Store\")")
	public static AndroidElement PlayStore;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/search_box_idle_text\")")
	public static AndroidElement google_Play;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search Google Play\")")
//	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/../..//android.widget.EditText[@text='Search Google Play']"))
	public static AndroidElement search_PlayStore;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@resource-id='com.android.vending:id/li_title']"))
	public static AndroidElement firstApp_Suggetion;

	@AndroidFindBy(xpath="//*[contains(@content-desc,'Installed')]")
	public static AndroidElement installed_Playstore;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").resourceId(\"com.android.vending:id/li_label\")")
	public static AndroidElement installed_Playstore1;	
	
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.vending:id/cover_photo\")")
	public static AndroidElement account_Page;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"INSTALL\")")
	public static AndroidElement INSTALL;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"UNINSTALL\")")
	public static AndroidElement UNINSTALL;
	
//	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"UNINSTALL\")")
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.android.vending:id/flat_card_apps_mdp']/../..//android.widget.Button[@text='UNINSTALL']"))
	public static AndroidElement UNINSTALL1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT\")")
	public static AndroidElement ACCEPT;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.launcher3:id/icon\")")
	public static AndroidElement apkExtractor;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Search Apps\")")
	public static AndroidElement searchApps;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
	public static AndroidElement ACCEPTCONTINUE; //This Locator is in Chrome Breowser.
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Accept & continue\")")
	public static AndroidElement ACCEPTandCONTINUE;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT;  //This Locator is in Chrome Breowser.	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"NO THANKS\")")
	public static AndroidElement NO_THANKS;  //This L
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"No\")")
	public static AndroidElement No_ThanKS;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"No, thanks\")")
	public static AndroidElement No_thanKS;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Cancel\")")
	public static AndroidElement Cancel; 
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='OK']")
	public static AndroidElement alert_OKBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[@text='Hulu Live TV Guide']"))
	public static AndroidElement HuluApp;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: Hulu Live TV Guide']")
	public static AndroidElement HuluApp1;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: Netflix Stream Team']")
	public static AndroidElement NetFlix;

	@AndroidFindBy(xpath="//*[@content-desc='App: Avis India']")
	public static AndroidElement Avis;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: AirportShuttles.com Rides']")
	public static AndroidElement AirportShuttle;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: UPSC IAS All in One - Study for 2019 Prelims Mains']")
	public static AndroidElement UPSC;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: Yellow Cabs']")
	public static AndroidElement yellowCabs;
	
	@AndroidFindBy(xpath="//*[@text='Battery is full' or @text='Sonim AppKey Policy']")
	public static AndroidElement batteryFullorAppKey;
	
	@AndroidFindBy(xpath="//*[@content-desc='App: DHL VoE']")
	public static AndroidElement DHL;

	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.utilityapp:id/textOk\")")
	public static AndroidElement OK1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement chrome;
	
	@AndroidFindBy(xpath="//*[@text='XP3800' or@text='XP5800' or @text='XP8800' or @text='KWSA50K' or @text='KWSA80K']")
	public static AndroidElement BT_Devices;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"When Bluetooth is turned on\")")
	public static AndroidElement BT_Disable_Text;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add call\")")
	public static AndroidElement add_Call;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/digits' or @resource-id='com.google.android.dialer:id/digits']")
	public static AndroidElement enterNumFiled;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='dial']")
	public static AndroidElement dail;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.android.dialer:id/floating_action_button' or @resource-id='com.google.android.dialer:id/fab']")
	public static AndroidElement dailerPad;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
	public static AndroidElement turnOff_Airplane_PopUp;
	
	@AndroidFindBy(xpath="//*[@text='CANCEL']")
	public static AndroidElement CANCEL;
	
	@AndroidFindBy(xpath="//*[@content-desc='End Call' or @content-desc='End call']")
	public static AndroidElement end_Call;
	//-------------------edit---------------
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Google Search\")")
	public static AndroidElement googlesearch;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement msg1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.mms:id/selection_menu\")")
	public static AndroidElement select;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.mms:id/popup_list_titles\")")
	public static AndroidElement select1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"1 selected\")")
	public static AndroidElement oneselected1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"1 selected\")")
	public static TouchAction oneselected;
	@AndroidFindBy(xpath="//android.widget.TextView[@index='0' or @text='1 selected']")
	public static AndroidElement selectfirst;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement selectall;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Deselect all\")")
	public static AndroidElement deselecte;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/delete\")")
	public static AndroidElement deletesms;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement deleteopt;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Type message\")")
	public static AndroidElement typmsgtextfield;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement typemsgtextfield1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='5']")
	public static AndroidElement typemsgtextfield2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"To\")")
	public static AndroidElement phnotextfield;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement phnotextfield1;
	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement phnotextfield2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement name_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='4']")
	public static AndroidElement name_NewContact2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement phone_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='8']")
	public static AndroidElement phone_NewContact2;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement email_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='11']")
	public static AndroidElement email_NewContact2;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"No Internet\")")
	public static AndroidElement noInternet;
	@AndroidFindBy(xpath="//android.view.View[contains(text()='Results will be available')]")
	public static AndroidElement YouAre_offline_lnk2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Offline\")")
	public static AndroidElement YouAre_offline_lnk3;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"Sign in\")")
	public static AndroidElement googlesign;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").text(\"IMAGES\")")
	public static AndroidElement googleimg;
	@AndroidFindBy(id="com.android.mms:id/history")
	public static AndroidElement msg_Screen;
//	@AndroidFindBy(xpath="//android.widget.TextView[contains(text()='Messages')]")
//	public static AndroidElement messages;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messages;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Type a name, phone number, or email\")")
	public static AndroidElement To_Field_B;
	@AndroidFindBy(id="com.google.android.apps.messaging:id/recipient_text_view")
	public static AndroidElement To_Field_B1;
	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement To_Field_B2;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(0)")
	public static AndroidElement Text_Field_B;
	
	@AndroidFindBy(xpath="//android.widget.Switch[@text='ON']")
	public static AndroidElement mobile_data_State;
	@AndroidFindBy(id="android:id/switch_widget")
	public static AndroidElement mobile_data_State1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Save data and browse faster\")")
	public static AndroidElement google_savedDataOpt;
	@AndroidFindBy(id="com.android.chrome:id/url_bar")
	public static AndroidElement google_urlBar;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Google apps\")")
	public static AndroidElement google_appsBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"Google offered in:\")")
	public static AndroidElement google_offeredInTxt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"Sign in\")")
	public static AndroidElement google_signinBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO THANKS\")")
	public static AndroidElement google_savedDataOpt_OkBtn;

@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_bar\")")
public static AndroidElement wifiConnectionSate;

//@AndroidFindBy(id="com.android.settings:id/switchWidget")
//public static AndroidElement wifiConnectionSate;


//--------------chrome
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.chrome:id/url_bar\")")
	public static AndroidElement chromeSearch;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"NEXT\")")
	public static AndroidElement NEXTBTN;  
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/terms_accept\")")
	public static AndroidElement Accept_id;

@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT_V;  //This Locator is in Chrome Breowser.	

@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO\")")
	public static AndroidElement NO_THANKS_v;  //This L
	

@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Google\")")
	public static AndroidElement No_ThanKS_v;
@AndroidFindBy(xpath = "//android.widget.Image[@text='0']")
public static AndroidElement google_text;

@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Connected\")")
public static AndroidElement wificonnected;
//@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/attach_media_button\")")
//public static AndroidElement camera_sl; 
@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Attach from camera or gallery']")
public static AndroidElement camera_sl;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/shutter_button\")")
public static AndroidElement camera_shutter_sl; 

//******************************************Airplane mode******************************
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Mobile network\")")
public static AndroidElement checkMobilenetwork;


@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
public static AndroidElement Wi_Fi;


@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/android.widget.TextView[@text='WiÃƒÂ¢Ã¢â€šÂ¬Ã¢â‚¬ËœFi' and @index='0' @resource-id='android:id/title']")
public static AndroidElement wi_Fi_x2;
//-----------------------delete------------
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/searchbutton\")")
public static AndroidElement search_btn;
@AndroidFindBy(xpath = "//android.widget.ImageView[@index='2']")
public static AndroidElement search_btn1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/searchEdit\")")
public static AndroidElement search_all_btn;
@AndroidFindBy(xpath = "//android.widget.EditText[@index='0']")
public static AndroidElement search_all_btn1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Search all conversations\")")
public static AndroidElement search_all_btn2;

@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Search all conversations']")
public static AndroidElement search_btn2;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/title\")")
public static AndroidElement First_no;
@AndroidFindBy(xpath = "//android.widget.TextView[@index='0']")
public static AndroidElement First_no1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
public static AndroidElement more_opt;
@AndroidFindBy(xpath = "//android.widget.ImageView[@index='5']")
public static AndroidElement more_opt1;
@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Content Finder']")
public static AndroidElement more_opt2;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/tv_title\")")
public static AndroidElement delete_o;
@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete Messages']")
public static AndroidElement delete_o1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/select_All_conv\")")
public static AndroidElement delete_all_o;
@AndroidFindBy(xpath = "//android.widget.ImageView[@index='2']")
public static AndroidElement delete_all_o1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/delete_btn\")")
public static AndroidElement delete_conf_o;
@AndroidFindBy(xpath = "//android.widget.ImageView[@index='3']")
public static AndroidElement delete_conf_o1;
//------------------vzw--------
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"Content Finder\")")
public static AndroidElement delete_conf_o2;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.verizon.messaging.vzmsgs:id/positive_button\")")
public static AndroidElement delete_Confirm_o1;
@AndroidFindBy(xpath="//android.widget.Button[@index='3']")
public static AndroidElement delete_Confirm_o2;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/from\")")
public static AndroidElement deleteall;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete Conversation\")")
public static AndroidElement delete_conversation;
//-------------belll
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_name\")")
public static AndroidElement delete_firstmsg_b;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/action_delete\")")
public static AndroidElement deletebtn_b;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").descriptionContain(\"Delete\")")
public static AndroidElement deletebtn1_b;
//--------------------AT&t-----
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/from\")")
public static AndroidElement delete_firstmsg_a;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
public static AndroidElement delete_Confirm_a;
//----------------------AT&t-----------
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/title\")")
public static AndroidElement clear_call_history;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Clear call history\")")
public static AndroidElement clear_call_history1;
@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").index(\"0\")")
public static AndroidElement clear_call_history2;
}
