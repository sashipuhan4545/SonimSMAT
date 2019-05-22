package com.xp8.util;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_CallSetting {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_CallSetting(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}

	//	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/empty_list_view\")")
	public static AndroidElement UidaiContact;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialtacts_options_menu_button']")
	public static AndroidElement settingsIcon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement settingsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Display options']")
	public static AndroidElement displayOptions;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sort by']")
	public static AndroidElement sortByOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Name format']")
	public static AndroidElement nameFormatOpt;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name']")
	public static AndroidElement firstNameOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Abc Automation']")
	public static AndroidElement contact1;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Cde Test']")
	public static AndroidElement contact2;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Test, Cde']")
	public static AndroidElement lnfcontact2;


	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name']")
	public static AndroidElement lastNameOpt;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name first']")
	public static AndroidElement firstNameFirstOpt;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name first']")
	public static AndroidElement lastNameFirstOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static WebElement delete_option;

	@AndroidFindBy(xpath="//android.widget.Button[@text='0 selected']")
	public static WebElement zero_selected;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select all']")
	public static WebElement selectAllOpt;

	@AndroidFindBy(xpath="//android.widget.ListView[@index='2']/../..//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static WebElement contactList;
	
	@AndroidFindBy(xpath="//*[@content-desc='Unblock'or @text='Remove']")
	public static AndroidElement unBlock_Icon;
	
	@AndroidFindBy(xpath="//*[@text='UNBLOCK' or @text='REMOVE']")
	public static AndroidElement remove_Confirm;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static WebElement Ok_option;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static WebElement okBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement nameEditFld;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Normal']")
	public static WebElement dialpadToneLengthNormal;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Long']")
	public static WebElement dialpadToneLengthLong;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Last name\")")
	public static AndroidElement EditFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Dialpad tone length']")
	public static WebElement dialPadTonelengthOpt;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
	public static WebElement phoneNumberEditFld;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.contacts:id/editor_menu_save_button']")
	public static WebElement saveOpt;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='1']")
	public static WebElement callLogPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='2']")
	public static WebElement contactPage;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement moreSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AddCallLog']")
	public static WebElement addedContactCallLog;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static WebElement delete;

	@AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
	public static WebElement deleteBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']")
	public static WebElement savedContact;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static WebElement permissionPopUp;

	@AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
	public static WebElement allowBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
	public static WebElement createNewContactOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CREATE NEW CONTACT']")
	public static WebElement createNewContactInContactPage;


	@AndroidFindBy(xpath="//android.widget.EditText[@text='Last name']")
	public static WebElement lastnameEditFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/contact_name']")
	public static WebElement createSecondcontactInContactPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Phone ringtone']")
	public static WebElement phoneRingtoneOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
	public static WebElement ringtoneSelected;

	@AndroidFindBy(xpath="//android.widget.CheckedTextView[@index='0']")
	public static WebElement ringtoneOpt;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sounds and vibration']")
	public static WebElement soundsAndVibration;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
	public static WebElement alsoVibrateCheckbox;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Also vibrate for calls']")
	public static WebElement alsoVibrateForCallsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Dial pad tones']")
	public static WebElement dialpadTonesOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call end tone']")
	public static WebElement callEndToneOpt;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='2']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
	public static WebElement dialpadToneCheckbox;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
	public static WebElement callEndToneCheckbox;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Quick responses']")
	public static WebElement quickResponseOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/title']")
	public static WebElement quickResponseText;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. What's up?\")")
	public static WebElement quickResponseTextOne;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"I'll call you right back.\")")
	public static WebElement quickResponseTextTwo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"I'll call you later.\")")
	public static WebElement quickResponseTextThree;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. Call me later?\")")
	public static WebElement quickResponseTextFour;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/edit']")
	public static WebElement quickResponseEditField;


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Restore defaults']")
	public static WebElement restoreDefaultsInQuickResponse;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public static WebElement cancelBtn;
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Speed dial settings']")
	public static WebElement speedDialSettingsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Voicemail']")
	public static WebElement voiceMailOpt;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='(not set)']")
	public static WebElement notSetInSpeedDialSettings;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/edit_container']")
	public static WebElement editFldInSpeedDialSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='9686525252']")
	public static WebElement addedContactInSpeedDialSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Replace']")
	public static WebElement replaceOptInSpeedDialSettings;


	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/select_contact']")
	public static WebElement selectContactInSpeedDialSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Search']")
	public static WebElement searchOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='KEEP EDITING']")
	public static WebElement keepEditingOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='DISCARD']")
	public static WebElement discardBtn;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']/../..//android.widget.TextView[@text='Abc Auto']")
	public static WebElement firstNameContact;
	
   @AndroidFindBy(uiAutomator="UiSelector().description(\"Navigate up\")")
   public static AndroidElement backKey;
	
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Call screening']")
	public static WebElement Callscreening;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Manage black list']")
  	public static WebElement Manageblacklist;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Manage white list']")
 	public static WebElement Managewhitelist;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='ADD']")
	public static WebElement addBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A NUMBER']")
 	public static WebElement addanumber;
   
   @AndroidFindBy(xpath="//android.widget.EditText[@text='Phone number']")
	public static WebElement Phonenumber;
  
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Block number']")
   public static WebElement blockNumberOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='BLOCK']")
   public static WebElement blockBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Unblock number']")
   public static WebElement unblockNumberOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='UNBLOCK']")
   public static WebElement unblockBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Call details']")
   public static WebElement callDetailsOpt;
   
   @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static WebElement deleteblockedno;
   
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").index(\"1\")")
	public static AndroidElement dialer1;
   
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").index(\"0\")")
	public static AndroidElement dialer2;
   
 
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/call_location_and_date']")
	public static AndroidElement Blocked;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement dailedFirstNum;
	

    @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static WebElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
	public static WebElement searchCallLogOpt;
	   
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static WebElement clearCallHistoryOpt;
	
	 @AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/../..//android.widget.Button[@text='0 selected']")
	   public static WebElement selectOpt;
	   
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
	   public static WebElement clearOptInCallHistory;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Call history']")
	   public static WebElement callHistoryOpt;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
	   public static WebElement makeACallOption;
	   
	   
     @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	  public static WebElement dialpadEditFld;
     
	  @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	  public static WebElement callBtn;
	   
	  @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='End call']")
		public static WebElement Endcall;
    
	 @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Call History tab.']")
		public static WebElement Callhistorytab;

	 @AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/../..//android.widget.LinearLayout[@index='0']")
	   public static WebElement addingnotoblock;
	 
	 @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Apps list']")
		public static WebElement Appslist;
	 
	 @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.google.android.googlequicksearchbox:id/opa_search_plate']")
	  public static WebElement googlequicksearch;
	 
     //removing number in white list
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.phone:id/remove_white_number']")
		public static WebElement removenoBtn;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='REMOVE']")
	   public static WebElement removetxt;
	//removing number in black list
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.phone:id/delete_blocked_number']")
		public static WebElement deleteblockBtn; 
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='UNBLOCK']")
	   public static WebElement unblocktxt;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	   public static WebElement canceltxt;
	 
	 //screening incoming call checkbox
	 @AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='android:id/checkbox']")
		public static WebElement screeningincomcallcheckbox;
	 
	/* @AndroidFindBy(xpath="//android.widget.TextView[@text='Screening incoming calls']/../..//android.widget.Switch[@text='ON']")
		public static AndroidElement screeningincomcallcheckbox_Enabled;

		@AndroidFindBy(xpath="//android.widget.TextView[@text='Screening incoming calls']/../..//android.widget.Switch[@text='OFF']")
		public static AndroidElement screeningincomcallcheckbox_Disabled;*/
	 
	 @AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='Screening incoming call Setting']")
	   public static WebElement screeningincomingcallsetting;
	 
	 @AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/../..//android.widget.CheckedTextView[@text='Allow only contacts']")
	   public static WebElement ScreeningincomingcallsettingOpt;
	 
	 @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Block black list']")
		public static WebElement blockblacklistopt;
	 
	 @AndroidFindBy(xpath="//android.widget.CheckedTextView[@resource-id='Allow white list']")
		public static WebElement allowwhitelistopt;
	 
	 @AndroidFindBy(xpath="//android.widget.CheckedTextView[@resource-id='Allow only contacts']")
		public static WebElement allowonlycontactsopt;
	 
	 @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Open navigation drawer']")
		public static WebElement opennavigationdrawer;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
		public static WebElement Settings;
	 
	 //Blocked numbers in phone contact settings
	 @AndroidFindBy(xpath="//android.widget.RelativeLayout[@text='Blocked numbers']")
		public static WebElement Blockedno;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A NUMBER']")
		public static WebElement addno;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
		public static WebElement firstblockedno;
	 
	 @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
		public static AndroidElement MessagePlus;
		
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
		
		@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
		public static AndroidElement add_NewSMS_O;
		
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
		

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
		public static AndroidElement StartMessaging;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
		public static AndroidElement skipProvisioning;
		
		@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
		public static AndroidElement app_List;

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

		@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
		public static AndroidElement add_NewSMS1;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
		public static AndroidElement toField_NewMessage;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
		public static AndroidElement messageField1;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
		public static AndroidElement contactPicker;

		@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
		public static AndroidElement TO_Field;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")	
		public static AndroidElement TO_Field1;
		
		@AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
		public static AndroidElement TO_Field_enter;

		@AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
		public static AndroidElement messageField;

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
		public static AndroidElement type_Message;
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
		public static AndroidElement type_Message_enter;

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
		public static AndroidElement send;

		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
		public static AndroidElement now_Text; //To validate after sending SMS.
		
		@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
		public static AndroidElement moreOption_O;
		
		@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete thread']")
		public static AndroidElement delete_Thread;
		
		@AndroidFindBy(xpath="//*[@content-desc='More options']")
		public static AndroidElement moreOptions;// This Locator is for tagName "android.widget.ImageButton".
		
		@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
		public static WebElement dialerfirstno;




}



