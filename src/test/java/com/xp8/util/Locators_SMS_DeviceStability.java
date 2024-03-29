package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_SMS_DeviceStability {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_SMS_DeviceStability(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
//-------------------------------------------------------------------------------------------------------------------------//
	//Multi_Locators for recepient field
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement TO_Field_att;
	
	@AndroidFindBy(id="com.android.mms:id/recipients_editor")
	public static AndroidElement TO_Fieldid_att;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"To\")")
	public static AndroidElement TO_Field_Text1_att;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field_Txt1_att;
	
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement TO_Field_indx_att ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/skip_provisioning_tv\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement vzw_msg_skipprovisioing;
	//--------------------------------------------------------------------------------------------------------------------//
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")
	public static AndroidElement TO_Field_vzw;


	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.google.android.apps.messaging:id/camera_capture_button']")
	public static WebElement cameraIcon1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Just now\")")
	public static AndroidElement now_Text1; //To validate after sending SMS.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_timestamp\")")
	public static AndroidElement message_Summary;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending�']")
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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static WebElement one_Selection_menu;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static AndroidElement MoreOptnsIcn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete']"))
	public static AndroidElement deleteContactOptn1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Your contacts list is empty']")
	public static AndroidElement no_Contacts;

	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	public static AndroidElement OKBtn1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement Contacts_Name;

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
	public static WebElement Search_ConatctIcon;

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

	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".

	@AndroidFindBy(xpath="//android.widget.ImageView[@resourceId='com.android.chrome:id/menu_badge']")
	public static AndroidElement updateMoreOptions;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Call History']"))
	public static AndroidElement callHistoryImage;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call History']"))
	public static WebElement callHistory;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call history']"))
	public static WebElement callHistory_O;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/../..//android.widget.ImageView[@resource-id='com.android.dialer:id/icon']"))
	public static AndroidElement callHistry_notification;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']"))
	public static AndroidElement  contactTest;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Test1']/../..//android.widget.ImageView[@content-desc='Call Test1']"))
	public static AndroidElement Call_Contact;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Airplane;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Airplane mode']/../..//android.widget.Switch[@text='OFF']"))
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

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement MoreOptions1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Refresh']")
	public static AndroidElement RefreshOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Wi-Fi']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement connectedWIFI1;

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


	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS Thread in INBOX.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
	public static AndroidElement add_NewSMS1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement new_Message_Icon;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS;
	//---------------------------------------------------------------------------------------------------------------//
	//Multi locator for ATT starting new conversation

	@AndroidFindBy(id="com.android.mms:id/create")
	public static AndroidElement add_NewSMS_ATT;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS_ATT_cls;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS_ATT_id;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='0']")
	public static AndroidElement  add_NewSMS_ATT_indx ;

	@AndroidFindBy(xpath="//*[@resource-id='com.android.mms:id/create']")
	public static AndroidElement add_NewSMS_ATT_xpath;
	//------------------------------------------------------------------------------------------------------------------//
	//Multi locator for VZW starting new conversation

	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='2']")
	public static AndroidElement  add_NewSMS_vzw ;

	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
	public static AndroidElement add_NewSMS_vzw_id;


	//--------------------------------------------------------//----------------------------------------------------
	//Multi locator for SL Operator starting new conversation

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement add_NewSMS_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Start chat\")")
	public static AndroidElement add_NewSMS_sl_text;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").description(\"Start chat\")")
	public static AndroidElement add_NewSMS_sl_uides;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='2']")
	public static AndroidElement  add_NewSMS_sl_index ;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Start chat']")
	public static AndroidElement add_NewSMS_sl_description;


	//----------------------------------------//------------------------------------------------------------------
	//Multi Locator for SL Operator type name,phoneno,email
	@AndroidFindBy(id="com.google.android.apps.messaging:id/recipient_text_view")
	public static AndroidElement typeno_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement toField_NewMessage_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Type a name, phone number, or email\")")
	public static AndroidElement tofield_text_sl;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement  typeno_sl_indx ;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter_sl;
	//-------------------------------------------------------------------------------------------//
	/*	//Multi Locatos for SL Selecting no to send message

	@AndroidFindBy(id="//com.google.android.apps.messaging:id/contact_picker_create_group")
	public static AndroidElement cntpicker_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement cntpickercls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Type a name, phone number, or email\")")
	public static AndroidElement tofield_text_sl;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']")
	public static AndroidElement  cntpicker_sl_indx ;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter_sl;

	 */

	//----------------------------------------------------------------------------------------------------------//
	//Multi Locators for SL typing message

	@AndroidFindBy(id="com.google.android.apps.messaging:id/compose_message_text")
	public static AndroidElement typemsg_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")")
	public static AndroidElement typemsgcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Text message\")")
	public static AndroidElement typemsg_text_sl;

	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement  typemsg_sl_indx ;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Text message']")
	public static AndroidElement typemsg_xpath_sl;
	//-------------------------------------------------------------------------------------------------------------//
	//Multi Locators for SL sending message

	@AndroidFindBy(id="com.google.android.apps.messaging:id/send_message_button_icon")
	public static AndroidElement sendmsg_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement sendmsgcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/message_indicator\")")
	public static AndroidElement sendmsgtxt_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SMS\")")
	public static AndroidElement sendmsg_text_sl;


	@AndroidFindBy(xpath="//android.widget.LinearLayout[@text='SMS']")
	public static AndroidElement sendmsg_sl_indx ;

	//-----------------------------------------------------------------------------------------------------------//
	//Multi Locators for validating sending message
	@AndroidFindBy(id="com.google.android.apps.messaging:id/message_status")
	public static AndroidElement valsendmsg_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/message_status\")")
	public static AndroidElement valsendmsgcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").description(\"Now, SMS\")")
	public static AndroidElement valsendmsgdes_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now � SMS\")")
	public static AndroidElement valsendmsg_text_sl;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement valsendmsg_sl_indx ;

	//-------------------------------------------------------------------------------------------------------------//
	//Multi Locators for validating not send msg
	@AndroidFindBy(id="com.google.android.apps.messaging:id/message_status")
	public static AndroidElement valnotsendmsg_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/message_status\")")
	public static AndroidElement valnotsendmsgcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").description(\"Not sent. Tap to try again.\")")
	public static AndroidElement valnotsendmsgdes_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Not sent. Tap to try again.\")")
	public static AndroidElement valnotsendmsg_text_sl;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement valnotsendmsg_sl_indx ;
	//-----------------------------------------------------------------------------------------------//

	//Multi Locators for selecting first sms in list
	@AndroidFindBy(id="com.google.android.apps.messaging:id/swipeableContent")
	public static AndroidElement firstsmsinlist_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_name\")")
	public static AndroidElement firstsmsinlistcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_snippet\")")
	public static AndroidElement firstsmsinlistdes_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/conversation_icon\")")
	public static AndroidElement firstsmsinlist_text_sl;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']")
	public static AndroidElement firstsmsinlist_sl_indx ;

	//------------------------------------------------------------------------------------------//
	//Multi Locators for selecting more option in sms

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").description(\"More options\")")
	public static AndroidElement moreoptions_des_sl;

	@AndroidFindBy(xpath="//android.widget.ImageView[@index='1']")
	public static AndroidElement moreopt_sl_indx ;
	//----------------------------------------------------------------------------------------------//
	//Multi Locators for selecting delete option in sms

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.messaging:id/title\").text(\"Delete\")")
	public static AndroidElement deletecls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete\")")
	public static AndroidElement deletetxt_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.messaging:id/title\")")
	public static AndroidElement delete_id_sl;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement delete_sl_indx ;
	//--------------------------------------------------------------------------------------------//
	//Multi Locators for confirm delete option in sms
	@AndroidFindBy(id="android:id/button1")
	public static AndroidElement cnfrmdelete_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement cnfrmdeletecls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement cnfrmdeletetxt_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/button1\")")
	public static AndroidElement cnfrmdelete_id_sl;

	@AndroidFindBy(xpath="//android.widget.Button[@index='1']")
	public static AndroidElement cnfrmdelete_sl_indx ;


	//--------------------------------------------------------------------------------------------//
	//Multi Locators for validating no of characters
	@AndroidFindBy(id="com.google.android.apps.messaging:id/char_counter")
	public static AndroidElement validatingchrsid_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/char_counter\")")
	public static AndroidElement validatingchrscls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"146 / 3\")")
	public static AndroidElement validatingchrs_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.messaging:id/char_counter\")")
	public static AndroidElement validatingchrs_id_sl;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement validatingchrs_sl_indx;
	//--------------------------------------------------------------------------------------------------------//   
	//Multi Locators for validating received messages
	@AndroidFindBy(id="com.google.android.apps.messaging:id/conversation_timestamp")
	public static AndroidElement validatingreceivedid_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_timestamp\")")
	public static AndroidElement validatingreceivedcls_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Just now\")")
	public static AndroidElement validatingreceived_sl;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.messaging:id/conversation_timestamp\")")
	public static AndroidElement validatingreceived_id_sl;

	@AndroidFindBy(xpath="//android.widget.TextView[@index='0']")
	public static AndroidElement validatingreceived_sl_indx;
	//--------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement messageField1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement contactPicker;



	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")	
	public static AndroidElement TO_Field1;

	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter;
//-----------------------------------------------------------------------------------------------------------------------//
	//Multi locators for type message field
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement type_Message_att;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type message\")")
	public static AndroidElement type_Messagetxt_att;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement typemessageField_att;
	
    @AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement type_Message_indx_att;
    
    @AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	public static AndroidElement type_Messageid_att;
	
	
	//-----------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement type_Message;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement type_Message_enter;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now � SMS\")")
	public static AndroidElement justnow_Text; //To validate after sending SMS.

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending�']")
	public static AndroidElement sending_Txt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Attach']")
	public static AndroidElement attach_icon;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Attach']")
	public static AndroidElement attach_icon_O;

	@AndroidFindBy(xpath="//*[@text='Delete']")
	public static AndroidElement delete_moreOption;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;

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

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement capturePicture;

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

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
	public static AndroidElement send_Icon;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
	public static AndroidElement send_SMS;

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

	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/date']")
	public static AndroidElement firstSMS_InList_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
	public static AndroidElement moreOption_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/select_All_conv\")")
	public static AndroidElement ALL_Msg;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/delete_btn\")")
	public static AndroidElement delete_Btn;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/composeFab\")")
	public static AndroidElement add_NewSMS_O;
	
	/*@AndroidFindBy(xpath="//android.widget.ImageButton[864,1704][1032,1872]�]")
	public static AndroidElement add_NewSMS_O;*/

	/*@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
	public static AndroidElement add_NewSMS_O;*/
	
	/*@AndroidFindBy(xpath="//android.widget.ImageButton[@index='2']//android.widget.ImageButton[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
	public static AndroidElement add_NewSMS_O;*/
	
	@AndroidFindBy(id="com.android.mms:id/create")
	public static AndroidElement createSMS;

	@AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.verizon.messaging.vzmsgs:id/contacts_list_view']/../..//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement contact_Select;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")	
	public static AndroidElement TO_Field_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type a message�\")")
	public static AndroidElement messageField_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Conversations\")")
	public static AndroidElement Conversations;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement send_Icon_O;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete Messages\")")
	public static AndroidElement delete_Messages;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement Delete;

	@AndroidFindBy(xpath="//*[contains(@text,'Delivered') or contains(@text,'Sent') or contains(@text,'now') or contains(@text,'Just now')or contains(@text,'Sending')]")
	public static AndroidElement Delivered;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.verizon.messaging.vzmsgs:id/main\")")
	public static AndroidElement firstsmsvzw;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement StartMessaging;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement skipProvisioning;
//-----------------------------------------------------------------------------------------------------------//
	//Multi Locator for app list
	@AndroidFindBy(id="com.android.launcher3:id/all_apps_handle")
	public static AndroidElement app_List_id;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").description(\"Apps list\")")
	public static AndroidElement app_List_contdesc;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.launcher3:id/all_apps_handle\")")
	public static AndroidElement app_List_resourceid;
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List_desc;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@index='0']")
	public static AndroidElement app_List_index;

//------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement vzwSettings;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"android:id/icon\")")
	public static AndroidElement vzwseeallapps;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Storage\")")
	public static AndroidElement vzwStorage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CLEAR DATA\")")
	public static AndroidElement vzwclrdata;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement vzwok;
		
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
	//Multilocator for launching message app
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messages;


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

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"No Thanks\")")
	public static AndroidElement No_ThanKS;

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
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/start\").text(\"Start Messaging\")")
	public static AndroidElement vzw_Start_msg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/first_number_block\").text(\"� � �\")")
	public static AndroidElement vzw_msg_enterno;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/save_txt\").text(\"Next\")")
	public static AndroidElement vzw_msg_next;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/create_profile\").text(\"YES, I WANT A PUBLIC PROFILE\")")
	public static AndroidElement vzw_msg_txt1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/save_txt\").text(\"Save\")")
	public static AndroidElement vzw_msg_txt2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/closeButton\")")
	public static AndroidElement vzw_msg_txt3;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/conversation_list\")")
	public static AndroidElement vzw_msg_screen;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/positive_button\").text(\"OK\")")
	public static AndroidElement vzw_msg_popup_txt1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.packageinstaller:id/permission_allow_button\").text(\"ALLOW\")")
	public static AndroidElement vzw_msg_allow_txt1;
}
