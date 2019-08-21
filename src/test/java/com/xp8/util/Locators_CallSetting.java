package com.xp8.util;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_CallSetting {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_CallSetting(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver = aDriver;

	}
	//--------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/title\").text(\"Screen lock\")")
	public static AndroidElement lockscreenlock;
	
    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/title\").text(\"None\")")
	public static AndroidElement locknone2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/title\").text(\"Airplane mode\")")
	public static AndroidElement airplanemode;
	
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement airplanemodeCheckboxoff;
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement airplanemodeCheckboxon;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='None']")
	public static AndroidElement locknone;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='Swipe']")
	public static AndroidElement lockswipe;
	//------------------------------------------------------------------------------------------------------------------//
	// @FindBy(how=How.XPATH, using
	// =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/empty_list_view\")")
	public static AndroidElement UidaiContact;
	
	 @AndroidFindBy(id="com.android.dialer:id/dialtacts_options_menu_button")
	 public static AndroidElement settingsIcon;
	//@AndroidFindBy(xpath="//*[@resource-id ='com.android.dialer:id/dialtacts_options_menu_button' or @resource-id='com.google.android.dialer:id/three_dot_menu_or_clear_icon_view']")
	/*@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement settingsIcon2;*/
	 
	@AndroidFindBy(id="com.google.android.dialer:id/main_options_menu_button")
	public static AndroidElement vzwsettingsIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/start\").text(\"Start Messaging\")")
	public static AndroidElement vzw_Start_msg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/skip_provisioning_tv\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement vzw_msg_skipprovisioing;
	
	@AndroidFindBy(xpath="//*[@resource-id='com.verizon.messaging.vzmsgs:id/date']")
	public static AndroidElement firstSMS_InList_O;

	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/select_All_conv\")")
	public static AndroidElement ALL_Msg;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/delete_btn\")")
	public static AndroidElement delete_Btn;

	
	

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Settings\")")
	public static AndroidElement settingsOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Display options']")
	public static AndroidElement displayOptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sort by']")
	public static AndroidElement sortByOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Name format']")
	public static AndroidElement nameFormatOpt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='First name']")
	public static AndroidElement firstNameOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Abc Automation']")
	public static AndroidElement contact1;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Cde Test']")
	public static AndroidElement contact2;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Test, Cde']")
	public static AndroidElement lnfcontact2;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Last name'or @text='Surname']")
	public static AndroidElement lastNameOpt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='First name first']")
	public static AndroidElement firstNameFirstOpt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Last name first' or @text='Surname first']")
	public static AndroidElement lastNameFirstOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static AndroidElement delete_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")
	public static AndroidElement deletefirstcontact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"android:id/parentPanel\")")
	public static AndroidElement blockcallandtextscreen;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='0 selected']")
	public static AndroidElement zero_selected;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select all']")
	public static AndroidElement selectAllOpt;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.EditText[@resource-id='com.android.server.telecom:id/add_blocked_number']")
	public static AndroidElement blackcontactList;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@resource-id='com.android.server.telecom:id/add_blocked_number']")
	public static AndroidElement firstblackno;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.EditText[@resource-id='com.android.phone:id/add_white_number']")
	public static AndroidElement whitecontactList;

	@AndroidFindBy(xpath = "//*[@content-desc='Unblock'or @text='Remove']")
	public static AndroidElement unBlock_Icon;

	@AndroidFindBy(xpath = "//*[@text='UNBLOCK' or @text='REMOVE']")
	public static AndroidElement remove_Confirm;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static AndroidElement Ok_option;

	/*
	 * @AndroidFindBy(xpath="//android.widget.Button[@text='OK']") public static
	 * AndroidElement okBtn;
	 */

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement okBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement nameEditFld;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Normal']")
	public static AndroidElement dialpadToneLengthNormal;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Long']")
	public static AndroidElement dialpadToneLengthLong;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Last name\")")
	public static AndroidElement EditFld;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Dialpad tone length']")
	public static AndroidElement dialPadTonelengthOpt;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static AndroidElement phoneNumberEditFld;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.contacts:id/editor_menu_save_button']")
	public static AndroidElement saveOpt;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement callLogPage;
    
	
	@AndroidFindBy(uiAutomator = "UiSelector().description(\"Contacts tab.\")")
	public static AndroidElement contactPage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/bottom_nav_item_text\").text(\"Contacts\")")
	public static AndroidElement vzwcontactpage;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement moreSettings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='AddCallLog']")
	public static AndroidElement addedContactCallLog;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement deleteBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']")
	public static AndroidElement savedContact;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static AndroidElement permissionPopUp;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ALLOW']")
	public static AndroidElement allowBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Create new contact']")
	public static AndroidElement createNewContactOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CREATE NEW CONTACT' or @text='Create new contact']")
	public static AndroidElement createNewContactInContactPage;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement contactsSettingsOPt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement contactsDefaultAccountSettings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PHONE']")
	public static AndroidElement contactsDefaultPhone;

	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Call History tab.') or contains(@text,'Recent')]")
	public static AndroidElement call_History;
//------------------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(xpath = "//*[contains(@content-desc,'Start chat') or contains(@text,'Start new conversation')]")
	public static AndroidElement Bell_newconversation;
//-----------------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").textContains(\"Messages\")")
	public static AndroidElement Bell_Messages;
//-------------------------------------------------------------------------------------------------------------------------//
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
//-----------------------------------------------------------------------------------------------------------------------//

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
//-----------------------------------------------------------------------------------------------------------------//
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
//---------------------------------------------------------------------------------------------------------//
	
	@AndroidFindBy(xpath = "//*[@text='Last name' or @text='Surname']")
	public static AndroidElement lastnameEditFld;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/contact_name' or@resource-id='com.google.android.dialer:id/contact_name']")
	public static AndroidElement createSecondcontactInContactPage;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone ringtone']")
	public static AndroidElement phoneRingtoneOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Other sounds and vibrations']")
	public static AndroidElement othersoundsandvibrationOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Default notification sound']")
	public static AndroidElement defaultnotificationsoundOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Default alarm sound']")
	public static AndroidElement defaultalarmsoundOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/summary']")
	public static AndroidElement ringtoneSelected;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@index='0']")
	public static AndroidElement ringtoneOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sounds and vibration']")
	public static AndroidElement soundsAndVibration;
	//--------------------------------------------------------verizon sounds and vibration-------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Advanced\")")
	public static AndroidElement vzw_advanced;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Keypad sounds']")
	public static AndroidElement keypadsounds;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement keypadsoundsCheckbox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Dial pad tones']")
	public static AndroidElement dialpadtones;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement dialpadtonesCheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Screen locking sounds']")
	public static AndroidElement screenlockingsounds;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement screenlockingsoundsCheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Touch sounds']")
	public static AndroidElement touchsounds;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement touchsoundsCheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Vibrate on tap']")
	public static AndroidElement vibrateontap;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement vibrateontapCheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Power on sounds']")
	public static AndroidElement poweronsounds;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement poweronsoundsCheckbox;
//-----------------------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement alsoVibrateCheckbox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Also vibrate for calls']")
	public static AndroidElement alsoVibrateForCallsOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Dial pad tones' or@text='Keypad tones']")
	public static AndroidElement dialpadTonesOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call end tone']")
	public static AndroidElement callEndToneOpt;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']")
	public static AndroidElement dialpadToneCheckbox;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.CheckBox[@resource-id='android:id/checkbox']")
	public static AndroidElement callEndToneCheckbox;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Quick responses']")
	public static AndroidElement quickResponseOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/title']")
	public static AndroidElement quickResponseText;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. What's up?\")")
	public static AndroidElement quickResponseTextOne;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"I'll call you right back.\")")
	public static AndroidElement quickResponseTextTwo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"I'll call you later.\")")
	public static AndroidElement quickResponseTextThree;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Can't talk now. Call me later?\")")
	public static AndroidElement quickResponseTextFour;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='android:id/edit']")
	public static AndroidElement quickResponseEditField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Restore defaults']")
	public static AndroidElement restoreDefaultsInQuickResponse;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
	public static AndroidElement cancelBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Speed dial settings']")
	public static AndroidElement speedDialSettingsOpt;
//-------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calls']")
	public static AndroidElement callssettingopt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Calling accounts']")
	public static AndroidElement callingaccount;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Fixed Dialing Numbers']")
	public static AndroidElement fdn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call forwarding']")
	public static AndroidElement callforwarding;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Additional settings']")
	public static AndroidElement additionalsettings;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call barring']")
	public static AndroidElement callbarring;
	
//------------------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Voicemail']")
	public static AndroidElement voiceMailOpt;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='(not set)']")
	public static AndroidElement notSetInSpeedDialSettings;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/edit_container']")
	public static AndroidElement editFldInSpeedDialSettings;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='Abc Automation']")
	public static AndroidElement addedContactInSpeedDialSettings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Replace']")
	public static AndroidElement replaceOptInSpeedDialSettings;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.dialer:id/select_contact']")
	public static AndroidElement selectContactInSpeedDialSettings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Search contacts']")
	public static AndroidElement searchOpt;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='KEEP EDITING']")
	public static AndroidElement keepEditingOpt;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DISCARD']")
	public static AndroidElement discardBtn;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='76543 21894']")
	public static AndroidElement firstNameContact;

	/*
	 * @AndroidFindBy(
	 * uiAutomator="UiSelector().resourceId(\"com.android.dialer:id/name\").text(\"Cde Test\")"
	 * ) public static AndroidElement First_Name_Contact;
	 */

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='Cde Test']")
	public static AndroidElement speeddialsettingfirstcontact;
	
	/*@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement speeddialsettingfirstcontactpost;*/
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@text='Abc Automation']")
	public static AndroidElement speeddialsettingfirstcontactpost;
	
	/*@AndroidFindBy(xpath = "//*[contains(@text,'Delivered') or contains(@text,'Sent') or contains(@text,'now') or contains(@text,'Just now')or contains(@text,'Sending')]")
	public static AndroidElement Delivered;*/
	
	/*@AndroidFindBy(xpath = "//*[contains(@text,'+91')]")
	public static AndroidElement speeddialsettingfirstcontactpost;*/

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.TextView[@text='Screening incoming calls']")
	public static AndroidElement callscreeningpage;

	@AndroidFindBy(uiAutomator = "UiSelector().description(\"Navigate up\")")
	public static AndroidElement backKey;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call screening']")
	public static AndroidElement Callscreening;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Blocked numbers']")
	public static AndroidElement vzwblockedno;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage black list']")
	public static AndroidElement Manageblacklist;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage white list']")
	public static AndroidElement Managewhitelist;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD']")
	public static AndroidElement addBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.phone:id/add_white']")
	public static AndroidElement addanumberwhite;

	/*@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/add_blocked']")
	public static AndroidElement addanumberblack;*/
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"ADD A NUMBER\")")
	public static AndroidElement addanumberblack;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Battery is full\")")
	public static AndroidElement batteryfulltxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"OK\")")
	public static AndroidElement batteryokbtn;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone number']")
	public static AndroidElement Phonenumber;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Block number']")
	public static AndroidElement blockNumberOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Block/report spam']")
	public static AndroidElement vzwblockNumberOpt;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='BLOCK'or@text='Block']")
	public static AndroidElement blockBtn;

	/*@AndroidFindBy(uiAutomator="new UiSelector().text(\"ADD A NUMBER\")")
	public static AndroidElement addanoblacklist;*/
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Blocked numbers\")")
	public static AndroidElement addanoblacklist;
	/*
	 * @AndroidFindBy(xpath="//android.widget.TextView[@text='Unblock number']")
	 * public static AndroidElement unblockNumberOpt;
	 */

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unblock number\")")
	public static AndroidElement unblockNumberOpt;
	
	/*@AndroidFindBy(xpath = "//android.widget.TextView[@text='Unblock number'])")
	public static AndroidElement vzwunblockNumberOpt;*/
	
	/*@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Unblock number\")")
	public static AndroidElement vzwunblockNumberOpt;*/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unblock number\")")
	public static AndroidElement vzwunblockNumberOpt2;
	
	/*@AndroidFindBy(xpath = "//*[@text='Unblock number']")
	public static AndroidElement vzwunblockNumberOpt;*/
	
	@AndroidFindBy(id="com.google.android.dialer:id/unblock_action")
	public static AndroidElement vzwunblockNumberOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"You won't receive calls or texts from blocked numbers.\")")
	public static AndroidElement blockednopage;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='UNBLOCK']")
	public static AndroidElement unblockBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call details']")
	public static AndroidElement callDetailsOpt;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static AndroidElement deleteblockedno;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(\"1\")")
	public static AndroidElement dialer1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").index(\"0\")")
	public static AndroidElement dialer2;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/call_location_and_date']")
	public static AndroidElement Blocked;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.google.android.dialer:id/call_location_and_date']")
	public static AndroidElement vzwBlocked;

	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement dailedFirstNum;
	
	/*@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='com.google.android.dialer:id/primary_action_view']")
	public static AndroidElement vzwdailedFirstNum;*/
	
	@AndroidFindBy(id="com.google.android.dialer:id/primary_action_view")
	public static AndroidElement vzwdailedFirstNum;
	
	@AndroidFindBy(id="com.android.dialer:id/primary_action_view")
	public static AndroidElement sprintdailedFirstNum;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@text='Test Automation']")
	public static AndroidElement dailedFirsttext;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static AndroidElement blocklistfirstno;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Search call log']")
	public static AndroidElement searchCallLogOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Clear call history']")
	public static AndroidElement clearCallHistoryOpt;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.Button[@text='0 selected']")
	public static AndroidElement selectOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CLEAR']")
	public static AndroidElement clearOptInCallHistory;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call history']")
	public static AndroidElement callHistoryOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='MAKE A CALL']")
	public static AndroidElement makeACallOption;

	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static AndroidElement dialpadEditFld;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	public static AndroidElement callBtn;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='End call']")
	public static AndroidElement Endcall;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Unblock number']")
	public static AndroidElement unblocknotxt;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Call History tab.'or@content-desc='Call History tab. 1 unread item.'or@content-desc='Call History tab. 2 unread items.']")
	public static AndroidElement Callhistorytab;
/*	@AndroidFindBy(xpath = "//*[@text='Last name' or @text='Surname']")*/

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Speed dial tab.']")
	public static AndroidElement Speeddialtab;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Recent']")
	public static AndroidElement vzw_recent;
	
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"android:id/title\").text(\"Call history\")")
	public static AndroidElement vzw_callhistory;
	
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.server.telecom:id/manage_blocked_ui\")") 
	 public static AndroidElement blacklistnoscreen;
	 

	/*@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.LinearLayout[@resource-id='com.android.server.telecom:id/manage_blocked_ui']")
	public static AndroidElement blacklistnoscreen;
*/
	/*
	 * @AndroidFindBy(
	 * uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Blocked numbers\")"
	 * ) public static AndroidElement blacklistnoscreen;
	 */
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/add_blocked']")
	public static AndroidElement addingnotoblock;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@resource-id='com.android.launcher3:id/drag_layer']")
	public static AndroidElement Appslist;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.google.android.googlequicksearchbox:id/greeting_text_container']")
	public static AndroidElement googlequicksearch;
	
	// removing number in white list
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.phone:id/remove_white_number']")
	public static AndroidElement removenoBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().description(\"Remove\")")
	public static AndroidElement removeBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='REMOVE']")
	public static AndroidElement removetxt;
	// removing number in black list
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.phone:id/delete_blocked_number']")
	public static AndroidElement deleteblockBtn;

/*	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static AndroidElement deleteblockBtninContact;*/
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Unblock\")")
	public static AndroidElement deleteblockBtninContact;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement firstcontactinblock;
	
	/*@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Unblock']")
	public static AndroidElement deleteblockBtninContact;*/

	@AndroidFindBy(xpath = "//android.widget.Button[@text='UNBLOCK']")
	public static AndroidElement unblocktxt;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
	public static AndroidElement canceltxt;

	// screening incoming call checkbox
	@AndroidFindBy(xpath = "//android.widget.CheckBox[@resource-id='android:id/checkbox']")
	public static AndroidElement screeningincomcallcheckbox;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.CheckBox[@resource-id='android:id/checkbox']")
	public static AndroidElement screeningoutgoingcallcheckbox;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.ListView[@resource-id='android:id/list'or @resource-id='android:id/list_container']")
	public static AndroidElement callsettingsframe;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Screening outgoing calls']")
	public static AndroidElement screeningoutgoingcall;

	/*
	 * @AndroidFindBy(
	 * xpath="//android.widget.TextView[@text='Screening incoming calls']/../..//android.widget.Switch[@text='ON']"
	 * ) public static AndroidElement screeningincomcallcheckbox_Enabled;
	 * 
	 * @AndroidFindBy(
	 * xpath="//android.widget.TextView[@text='Screening incoming calls']/../..//android.widget.Switch[@text='OFF']"
	 * ) public static AndroidElement screeningincomcallcheckbox_Disabled;
	 */

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/../..//android.widget.TextView[@text='Screening incoming call Setting']")
	public static AndroidElement screeningincomingcallsetting;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']/../..//android.widget.CheckedTextView[@text='Allow only contacts']")
	public static AndroidElement ScreeningincomingcallsettingOpt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Block black list']")
	public static AndroidElement blockblacklistopt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Allow white list']")
	public static AndroidElement allowwhitelistopt;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text='Allow only contacts']")
	public static AndroidElement allowonlycontactsopt;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement opennavigationdrawer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement Settings;

	// Blocked numbers in phone contact settings
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@text='Blocked numbers']")
	public static AndroidElement Blockedno;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD A NUMBER']")
	public static AndroidElement addno;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static AndroidElement firstblockedno;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Messages\")")
	public static AndroidElement messages;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"NEXT\")")
	public static AndroidElement NEXT_Msg; // This Locator is in Messaging.

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.packageinstaller:id/permission_allow_button\")")
	public static AndroidElement allow_Permission; // This Locator is in
													// Messaging.

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
	public static AndroidElement send_Icon;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
	public static AndroidElement send_SMS;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
	public static AndroidElement send_MMS_Icon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Not sent, touch to try again']")
	public static AndroidElement not_Sent_Text;

	@AndroidFindBy(xpath = "//*[@resource-id='com.verizon.messaging.vzmsgs:id/composeFab']")
	public static AndroidElement add_NewSMS_O;

	@AndroidFindBy(xpath = "//android.support.v7.widget.RecyclerView[@resource-id='com.verizon.messaging.vzmsgs:id/contacts_list_view']/../..//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement contact_Select;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.verizon.messaging.vzmsgs:id/curRecip\")")
	public static AndroidElement TO_Field_O;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Type a message…\")")
	public static AndroidElement messageField_O;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Conversations\")")
	public static AndroidElement Conversations;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement send_Icon_O;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete Messages\")")
	public static AndroidElement delete_Messages;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement Delete;

	@AndroidFindBy(xpath = "//*[contains(@text,'Delivered') or contains(@text,'Sent') or contains(@text,'now') or contains(@text,'Just now')or contains(@text,'Sending')]")
	public static AndroidElement Delivered;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement StartMessaging;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement skipProvisioning;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement app_List;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/swipeableContent\")")
	public static AndroidElement first_sms_Thread; // Locator for First SMS
													// Thread in INBOX.
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.apps.messaging:id/conversation_name\")")
	public static AndroidElement bell_first_sms;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/main\")")
	public static AndroidElement VZW_first_sms;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.apps.messaging:id/action_delete\")")
	public static AndroidElement deletebell_first_sms;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\").text(\"Delete\")")
	public static AndroidElement cnfrmdeletebell_first_sms;
	
	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement firstSMS_InList;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@index='0']")
	public static AndroidElement first_sms_Thread1; // Locator for First SMS
													// Thread in INBOX.

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_Confirm;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/start_new_conversation_button\")")
	public static AndroidElement new_Message_Icon;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.mms:id/create']")
	public static AndroidElement add_NewSMS;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/start_new_conversation_button']")
	public static AndroidElement add_NewSMS1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.google.android.apps.messaging:id/recipient_text_view\")")
	public static AndroidElement toField_NewMessage;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement messageField1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement contactPicker;

	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@text='To']")
	public static AndroidElement TO_Field;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")
	public static AndroidElement TO_Field1;

	@AndroidFindBy(xpath = "//android.widget.MultiAutoCompleteTextView[@text='Type a name, phone number or email address']")
	public static AndroidElement TO_Field_enter;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Type message']")
	public static AndroidElement messageField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement type_Message;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Type an SMS message\")")
	public static AndroidElement type_Message_enter;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Send\")")
	public static AndroidElement send;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; // To validate after sending SMS.

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.verizon.messaging.vzmsgs:id/imgGalleryOption\")")
	public static AndroidElement moreOption_O;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete thread']")
	public static AndroidElement delete_Thread;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select all']")
	public static AndroidElement smsselectall;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
	public static AndroidElement smsconfirmdelete;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']/../..//android.widget.LinearLayout[@index='1']")
	public static AndroidElement smsfirstmsg;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='2']/../..//android.widget.TextView[@resource-id='com.android.mms:id/delete']")
	public static AndroidElement smsdeleteall;

	/*
	 * @AndroidFindBy(xpath=
	 * "//android.widget.TextView[@resource-id='com.android.mms:id/delete']")
	 * public static AndroidElement smsdeleteall;
	 */
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.mms:id/expand']")
	public static AndroidElement smsmoreOptions;// This Locator is for tagName
												// "android.widget.ImageButton".

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement dialerfirstno;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@index='0']")
	public static AndroidElement quickresponseedittxt;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']/../..//android.widget.LinearLayout[@index='1']")
	public static AndroidElement quickresponsemsg;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
	public static AndroidElement callericon;

	@AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='2,ABC']")
	public static AndroidElement twoindialpad;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@resource-id='com.android.dialer:id/incall_end_call']")
	public static AndroidElement endcallicon;
	
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

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").description(\"More options\")")
	public static AndroidElement moreoptions_des_sl;
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
	
	

}
