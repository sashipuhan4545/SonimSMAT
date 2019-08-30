package com.xp8.util;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Phone_Contacts {
	

	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Phone_Contacts(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contacts\")")
	public static AndroidElement contacts;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Show roots']")
	public static AndroidElement MoreOptions;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement contactsSettingsOPt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement contactsDefaultAccountSettings;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Import']")
	public static AndroidElement contactsImportSettings;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Export']")
	public static AndroidElement contactsIExportSettings;
	
	//@AndroidFindBy(xpath="//*[@text='.vcf file']")
	@AndroidFindBy(xpath="//*[contains(@text,'.vcf file')]")
	public static AndroidElement vcfFile;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"PHONE\")")
	public static AndroidElement PHONE_RadioBtn;
	
	@AndroidFindBy(xpath="//*[@text='SAVE']")
	public static AndroidElement Save_Btn;
	
	@AndroidFindBy(xpath="//*[@text='Downloads']")
	public static AndroidElement DownloadsOption;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='3']/../..//android.widget.TextView[@text='i476.vcf']")
	public static AndroidElement VcfFileOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/selection_menu\")")
	public static AndroidElement Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement ALL_Selection_menu;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1\")")
	public static AndroidElement one_Selection_menu;

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
	 
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"110 contact\")")
	public static AndroidElement contactTottalNo;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='110 contacts']"))
	public static AndroidElement ContactOptn110;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='2 contacts']"))
	public static AndroidElement ContactOptn2;
	
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement AccountsPhoneOPt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().description(\"PHONE PHONE\")")
	public static AndroidElement AccountsPhoneOPt1;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"IMPORT\")")
	public static AndroidElement ImportBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.contacts:id/account_name\")")
	public static AndroidElement DropDown;
	
//	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.contacts:id/account_type_icon']")
//	public static AndroidElement DropDown;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SIM card\")")
	public static AndroidElement SIM_card;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static AndroidElement permissionPopUp;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static AndroidElement allowBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"i476.vcf\")")
	public static AndroidElement i476_vcf; 

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SIM\")")
	public static AndroidElement AccountSIMOPt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resource-id(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement AddcontactBtn1;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.contacts:id/floating_action_button']")
	public static AndroidElement AddcontactBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement nameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone\")")
	public static AndroidElement phoneField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement SaveBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement FirstnameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Last name\")")
	public static AndroidElement LaststnameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Surname\")")
	public static AndroidElement SurnameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Last name\")")
	public static AndroidElement LastnameField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Email\")")
	public static AndroidElement EmailField;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SIM card\")")
	public static AndroidElement ExportSIMcard;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"2 contacts\")")
	public static AndroidElement ContactOptnto;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(5)")
	public static AndroidElement Contact1;
	
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resource-id(\"com.android.contacts:id/menu_edit\")")
//	public static AndroidElement ContactEditBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
	public static AndroidElement FirstnameEdit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
	public static AndroidElement LastnameEdit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
	public static AndroidElement PhoneEdit;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(4)")
	public static AndroidElement EmailEdit;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc=\"Edit\"]")
	public static AndroidElement ContactEditBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").instance(0)")
	public static AndroidElement More_Options;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Set ringtone\")")
	public static AndroidElement SetRingtoneOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Alarm\")")
	public static AndroidElement RingtoneOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"TestEdit1\")")
	public static AndroidElement EditContact;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(5)")
	public static AndroidElement Contactfirst;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(0)")
	public static AndroidElement menu_star;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(3)")
	public static AndroidElement SMSOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resource-id(\"com.android.contacts:id/icon_alternate\")")
	public static AndroidElement SMSOpt2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.contacts:id/icon_alternate\")")
	public static AndroidElement SMSOpt3;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc=\"Text 96069 56766\"]")
	public static AndroidElement SMSOpt4;

	
//	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.mms:id/embedded_text_editor\")")
//	public static AndroidElement SMS_editor;
	
//	@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
//	public static AndroidElement SMS_editor;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resource-id(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement SMS_editor;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement messageEditFld;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")")
	public static AndroidElement messageEditFld2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/compose_embedded_text_editor\")")
	public static AndroidElement messageEditFld3;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/send_button_sms\")")
	public static AndroidElement sendMessageOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement sendMessageOpt2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/message_indicator\")")
	public static AndroidElement sendMessageOpt3;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Send\"]")
	public static AndroidElement sendMessageOpt5;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement sendMessageOpt4;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
	public static AndroidElement messageSentNow;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Now � SMS\")")
	public static AndroidElement messageSentNow2;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sent\")")
	public static AndroidElement messageSentNow3;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(6)")
	public static AndroidElement messageSentNow4;
	
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").instance(3)")
//	public static AndroidElement SendBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").content-desc(\"Send\")")
	public static AndroidElement SendBtn;
	
//	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Send\"]")
//	public static AndroidElement SendBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.mms:id/date_view\")")
	public static AndroidElement RecSMS;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Blocked numbers\")")
	public static AndroidElement BlocknumOpt;
		
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"ADD A NUMBER\")")
	public static AndroidElement AddBlocknumOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Phone number\")")
	public static AndroidElement EditboxOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"BLOCK\")")
	public static AndroidElement BlockOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.server.telecom:id/blocked_number\")")
	public static AndroidElement BlockNum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.server.telecom:id/delete_blocked_number\")")
	public static AndroidElement DelBlockNum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNBLOCK\")")
	public static AndroidElement UnBlockOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test1\")")
	public static AndroidElement FirstName;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test2\")")
	public static AndroidElement SecondName;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Link\")")
	public static AndroidElement LinkOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Name format\")")
	public static AndroidElement NameFormatOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Last name first\")")
	public static AndroidElement Lastnamefirst;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Surname first\")")
	public static AndroidElement Surnamefirst;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"First name first\")")
	public static AndroidElement Firstnamefirst;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test, Sonim\")")
	public static AndroidElement Change_Lastname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Test\")")
	public static AndroidElement Change_Firststname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sort by\")")
	public static AndroidElement SortbyOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Last name\")")
	public static AndroidElement SortbyLastnameOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Surname\")")
	public static AndroidElement SortbySurnameOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"First name\")")
	public static AndroidElement SortbyFirstnameOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"T\")")
	public static AndroidElement AfterChange_SortbyLastname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"S\")")
	public static AndroidElement AfterChange_SortbyFirstname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"My info\")")
	public static AndroidElement MyinfoOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Set up your profile\")")
	public static AndroidElement SetupProfile;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.contacts:id/photo_icon\")")
	public static AndroidElement camera_image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Take photo\")")
	public static AndroidElement Take_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Take new photo\")")
	public static AndroidElement Take_new_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement photo_capture_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")
	public static AndroidElement photo_add_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Done\")")
	public static AndroidElement done_btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove photo\")")
	public static AndroidElement Remove_photo;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public static AndroidElement cancelChangePhoto;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/editor_menu_save_button\")")
	public static AndroidElement editor_menu_save_button;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Display\")")
	public static AndroidElement Settings_Display ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Advanced\")")
	public static AndroidElement Display_Advanced ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").instance(11)")
	public static AndroidElement Sleep_min ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Sleep\")")
	public static AndroidElement SleepOpt ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 minutes\")")
	public static AndroidElement SleepOpt30min ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"30 seconds\")")
	public static AndroidElement SleepOpt30sec ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"More fields\")")
	public static AndroidElement Morefields ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Company\")")
	public static AndroidElement Companyfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"SIP\")")
	public static AndroidElement SIPfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Address\")")
	public static AndroidElement Addressfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"IM\")")
	public static AndroidElement IMfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Website\")")
	public static AndroidElement Websitefield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Notes\")")
	public static AndroidElement Notesfield ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text1;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now • SMS\")")
	public static AndroidElement justnow_Text;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text1;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending…']")
	public static AndroidElement sending_Txt;
	
	
	
}
