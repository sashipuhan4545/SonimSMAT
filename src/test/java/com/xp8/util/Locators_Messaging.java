package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Messaging {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Messaging(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
		

	}
	//---------------message ------------
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement Messaging;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messages;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;
	//clear permission-------------
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement StartMessaging;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"NEXT\")")
	public static AndroidElement NEXT_Msg;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement skipProvisioning;
	
	//----------------adding new sms-----------------
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement add_NewSMS;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='1']")
	public static AndroidElement add_NewSMS11;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
	public static AndroidElement add_NewSMS1;
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
	@AndroidFindBy(id="com.android.dialer:id/custom_sms_input")
	public static AndroidElement msg_popup;
	//-----------------enter phno-------------
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field_phno1;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='To']")
	public static AndroidElement To;
	@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement TO_Field_phno2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement TO_Field_phno3;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")	
	public static AndroidElement TO_Field_O;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/add_attachment_first\")")	
	public static AndroidElement attach;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")	
	public static AndroidElement shutter;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")	
	public static AndroidElement done;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"Review done\")")
	public static AndroidElement done1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").text(\"ALLOW\")")	
	public static AndroidElement allow;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"VIEW\")")	
	public static AndroidElement view;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Capture picture\")")
	public static AndroidElement picture;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sonim@.com\")")
	public static AndroidElement delete_msg;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement TO_Field_O1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").text(\"Type a name, phone number, or email\")")
	public static AndroidElement To_Field_B;
	@AndroidFindBy(id="com.google.android.apps.messaging:id/recipient_text_view")
	public static AndroidElement To_Field_B1;
	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@index='0']")
	public static AndroidElement To_Field_B2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_title\")")
	public static AndroidElement Multiple_Recipients;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.launcher3:id/bubble_text\")")
	public static AndroidElement app_info;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Test1, Test2\")")
	public static AndroidElement Multiple_Recipients1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement Multiple_Recipients2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"New conversation\")")
	public static AndroidElement New_Conversation;
	
	//---------text field
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement type_text1;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
	public static AndroidElement type_text2;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='5']")
	public static AndroidElement type_text3;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type a messageâ€¦\")")
	public static AndroidElement messageField_O;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REPLY\")")
	public static AndroidElement Reply;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/compose_embedded_text_editor\")")
	public static AndroidElement messageField_O1;
	@AndroidFindBy(xpath="//android.widget.EditText[@index='5']")
	public static AndroidElement messageField_O2;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").index(0)")
	public static AndroidElement Text_Field_B;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Write your own\")")
	public static AndroidElement write_Your_Own;

	
//------------send btn
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/send_button_sms\")")
	public static AndroidElement send_Icon;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SEND\")")
	public static AndroidElement SEND;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Send']")
	public static AndroidElement send_Icon3;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='6']")
	public static AndroidElement send_Icon2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement send_SMS; 
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement send_Icon_O;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_container\")")
	public static AndroidElement send_Icon_B;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@content-desc='Send SMS']")
	public static AndroidElement send_Icon_B1;
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']")
	public static AndroidElement send_Icon_B2;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_MMS_Icon;
	
//----------------validate msg-------------
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement Phno;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement Phno_A;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Just now']")
	public static AndroidElement now_Text11;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Just now\")")
	public static AndroidElement now_Text1;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now . SMS\")")
	public static AndroidElement justnow_Text; 
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/from\")")
	public static AndroidElement deleteall;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending...]")
	public static AndroidElement sending_Txt;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date_view\")")
	public static AndroidElement sended_SMS; 
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delivered\")")
	public static AndroidElement Delivered;
	@AndroidFindBy(id ="com.verizon.messaging.vzmsgs:id/status")
	public static AndroidElement Delivered1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").descriptionContains(\"Now\")")
	public static AndroidElement now_sms3;
	@AndroidFindBy(xpath ="//android.widget.TextView[@content-desc='Now, SMS']")
	public static AndroidElement now_sms2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Now\")")

//	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Now • SMS']")
	public static AndroidElement now_sms1;
	@AndroidFindBy(id ="com.google.android.apps.messaging:id/message_status")
	public static AndroidElement now_sms;
	
	
	//////--------------------ooooookkkkkkkkkkkkkkk----------------
	
	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	public static AndroidElement OKBtnopt1;
	@AndroidFindBy(xpath = "//android.widget.Button[@index='1']")
	public static AndroidElement OKBtnopt3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement OKBtbtn2;
	
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	public static AndroidElement OKBtn1;
	@AndroidFindBy(xpath ="//android.widget.TextView[@index='5']")
	public static AndroidElement OKBtn3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/btn_ok\")")
	public static AndroidElement OKBtn2;

	
	//----------validate special character-----------
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")")
	public static AndroidElement special_char; 
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement special_char_A;
	//---------------------group chat--------
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"Call\")")
	public static AndroidElement call;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add to Contact\")")
	public static AndroidElement add_contact;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Demo1\")")
	public static AndroidElement demo1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Create new contact\")")
	public static AndroidElement create_contact;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement group_coversation;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Start group conversation\")")
	public static AndroidElement group_coversation1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/recipients_picker\")")
	public static AndroidElement group_sms;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").index(2)")
	public static AndroidElement group_sms1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.messaging:id/action_add_more_people\")")
	public static AndroidElement add_people;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").index(6)")
	public static AndroidElement add_people1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.contacts:id/account_expander_icon\")")
	public static AndroidElement saving_to_phone;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"Block\")")
	public static AndroidElement block_contact;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Blocked contacts\")")
	public static AndroidElement block_contact_opt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/title\")")
	public static AndroidElement block_contact_opt1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement block_contact_opt2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"+91 95135 48679\")")
	public static AndroidElement block_contact_Phno;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.server.telecom:id/blocked_number\")")
	public static AndroidElement block_contact_Phno1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.server.telecom:id/delete_blocked_number\")")
	public static AndroidElement unblock_contact1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").index(1)")
	public static AndroidElement unblock_contact2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"Unblock\")")
	public static AndroidElement unblock_contact3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement unblock_confirm;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").index(1)")
	public static AndroidElement unblock_confirm1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNBLOCK\")")
	public static AndroidElement unblock_confirm2;
	//-------------------create contact----------------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement AddtoContact;
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android:id/icon']")
	public static AndroidElement Choose_Phone;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PHONE']")
	public static AndroidElement Choose_Phone_A;
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SIM Card']")
	public static AndroidElement Choose_sim_A;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement Contacts_Name;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").textContains(\"Name\")")
	public static AndroidElement name_NewContact;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement name_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='4']")
	public static AndroidElement name_NewContact2;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static AndroidElement phone_NewContact;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement phone_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='8']")
	public static AndroidElement phone_NewContact2;
	@AndroidFindBy(xpath="//android.widget.EditText[@text='Email']")
	public static AndroidElement email_NewContact;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement email_NewContact1;
	@AndroidFindBy(xpath = "//android.widget.EditText[@index='11']")
	public static AndroidElement email_NewContact2;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/editor_menu_save_button\")")
	public static AndroidElement Save_ConatctIcon1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement  Save_ConatctIcon2;
     @AndroidFindBy(xpath = "//android.widget.Button[@index='0']")
     
     public static AndroidElement Save_ConatctIcon3;
 	@FindBy(how=How.XPATH, using =("//android.widget.Button[@text='ALLOW']"))
 	public static AndroidElement AllowOptn;
 	@AndroidFindBy(xpath = "//*[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement contact_phnNum;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonimtech@gmail.com\")")
	public static AndroidElement contact_Emailid;
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']"))
	public static AndroidElement ContactTitle;
	//---------------------Send sms through contact------------------
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement Test;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(0)")
	public static AndroidElement Test1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.android.contacts:id/pick_contact_check\")")
	public static AndroidElement check_box;
	
	//group chat----------------------
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(2)")
	public static AndroidElement Test_1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(3)")
	public static AndroidElement Test_2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/action_confirm_participants\")")
	public static AndroidElement confirm;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test4\")")
	public static AndroidElement Test4;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").index(0)")
	public static AndroidElement clear;
	
	//---------------forward sms-----------
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/conversation_name\")")
	public static AndroidElement Receive_sms;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement Receive_sms1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement Receive_sms_A;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").descriptionContains(\"Testing\")")
	public static AndroidElement forward_Text;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Testing\")")
	public static AndroidElement forward_Text_A;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").index(0)")
	public static AndroidElement forward_Text_A1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim@.com\")")
	public static AndroidElement forward_Text1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(1)")
	public static AndroidElement forward_Text2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"More options\")")
	public static AndroidElement more_option;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").descriptionContains(\"More options\")")
	public static AndroidElement more_option_a;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").descriptionContains(\"Test\")")
	public static AndroidElement details;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").text(\"Details\")")
	public static AndroidElement details1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement forward_opt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/title\")")
	public static AndroidElement forward_opt1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/forward\")")
	public static AndroidElement forward_opt2;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Forward\")")
//	public static AndroidElement forward_opt2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(0)")
	public static AndroidElement phno;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement phno1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/name\")")
	public static AndroidElement phno2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").index(1)")
	public static AndroidElement thread_2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").index(2)")
	public static AndroidElement thread_3;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(1)")
	public static AndroidElement thread_A;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(2)")
	public static AndroidElement thread_A1;
	//-----------------callhistory--------------------
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement MoreOptions;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@index='2']")
	public static AndroidElement MoreOptions2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/dialtacts_options_menu_button\")")
	public static AndroidElement MoreOptions11;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement MoreOptions1;
	@AndroidFindBy(xpath="//*[@content-desc='More options']")
	public static AndroidElement moreOptions;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call history']"))
	public static AndroidElement callHistory_O;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@index='0']"))
	public static AndroidElement callHistory_O1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/title\")")
	public static AndroidElement callHistory_O2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/primary_action_view\")")
	public static AndroidElement First_Contact;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(0)")
	public static AndroidElement First_Contact1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/send_message_action\")")
	public static AndroidElement Send_sms_To_First_Contact;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(0)")
	public static AndroidElement Send_sms_To_First_Contact1;
	//---------------view details------------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/title\")")
	public static AndroidElement view_details;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
	public static AndroidElement view_details1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"View details\")")
	public static AndroidElement view_details2;
	//-----------------details,dateand time-----------
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement setting;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"+91 95135 48679\")")
	public static AndroidElement phnotext;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Notifications\")")
	public static AndroidElement Notifications_opt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"On\")")
	public static AndroidElement Notifications_On;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"Off\")")
	public static AndroidElement Notifications_Off;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
//	public static AndroidElement recive_sms;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"91 95135 48679\")")
	public static AndroidElement recive_sms;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement recive_sms_A;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test2\")")
	public static AndroidElement recive_sms1;
	
	//--------------dailpad------------------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.apps.messaging:id/action_ime_dialpad_toggle\")")
	public static AndroidElement dailpad;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").index(2)")
	public static AndroidElement dailpad1;
	//-------------------google------------------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/link_preview_title\")")
	public static AndroidElement google;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"www.google.com\")")
	public static AndroidElement google_text;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Save to bookmark\")")
	public static AndroidElement save_bookmark;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connect\")")
	public static AndroidElement connect;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").descriptionContains(\"8/12/19\")")
	public static AndroidElement date_and_time;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Save data and browse faster\")")
	public static AndroidElement google_savedDataOpt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"NO THANKS\")")
	public static AndroidElement google_savedDataOpt_OkBtn;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.view.View\").textContains(\"No Internet\")")
	public static AndroidElement noInternet;
	@AndroidFindBy(xpath="//android.widget.Switch[@instance='0']")
	public static AndroidElement mobile_data_State;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Mobile network\")")
	public static AndroidElement checkMobilenetwork;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Airplane mode\")")
	public static AndroidElement airoplane_mode;
	
	//----------------------reply----------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/action0\")")
	public static AndroidElement reply;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"REPLY\")")
	public static AndroidElement reply1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").descriptionContains(\"Reply\")")
	public static AndroidElement reply2;
	//--------------vibration----------------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement vibrate_on;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement vibrate_off;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Memory status\")")
	public static AndroidElement memory_status;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Message template\")")
	public static AndroidElement msgtemplate;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/text1\")")
	public static AndroidElement msgtemplate1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(7)")
	public static AndroidElement textmsgsetting;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/message\")")
	public static AndroidElement memory_status_text;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"android:id/checkbox\")")
	public static AndroidElement checked;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Message details\")")
	public static AndroidElement message_details;
	//-------------simcontact-----------
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").index(1)")
	public static AndroidElement sonim1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").index(6)")
	public static AndroidElement test1;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").index(2)")
	public static AndroidElement sonim2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMS\")")
	public static AndroidElement mms;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SMS\")")
	public static AndroidElement sms;
	//delete contact
	//--------------------AT&t-----
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/from\")")
	public static AndroidElement delete_firstmsg_a;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm_a;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Delete thread\")")
	public static AndroidElement delete_thread;
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
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement delete_Confirm_s;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	public static AndroidElement deleteContactOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete']"))
	public static AndroidElement deleteContactOptn1;
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@index='3']"))
	public static AndroidElement deleteContactOptn3;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static AndroidElement deleteContactOptn2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"0 selected\")")
	public static AndroidElement Selection_menu1;

	@AndroidFindBy(xpath  = "//android.widget.Button[@index='1']")
	public static AndroidElement Selection_menu2;


	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement OKBtnopt2;
	@AndroidFindBy(xpath="//*[@text='OK']") 
	public static AndroidElement OK;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/terms_accept\")")
	public static AndroidElement Accept_id;
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement NEXT; 
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"NO THANKS\")")
	public static AndroidElement NO_THANKS;  //This L
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"No Thanks\")")
	public static AndroidElement No_ThanKS;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"Cancel\")")
	public static AndroidElement Cancel;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu1;
	@AndroidFindBy(xpath  = "//android.widget.Button[@index='0']")
	public static AndroidElement ALL_Selection_menu2;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/popup_list_title\")")
	public static AndroidElement ALL_Selection_menu;
}
