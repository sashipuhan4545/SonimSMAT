package com.xp8.util;




import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_XP8_CallHistory {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_XP8_CallHistory(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
}
	@AndroidFindBy(xpath="//android.support.v4.view.ViewPager[@resource-id='com.android.dialer:id/lists_pager']")
	public static AndroidElement phoneFrame;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialtacts_options_menu_button']")
	public static AndroidElement settingsIcon;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call history']")
	//@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@index='0']")
	public static AndroidElement callHistoryOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement callLogWithSymbols;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement contactDetails;
	
	@AndroidFindBy(xpath="//android.widget.QuickContactBadge[@resource-id='com.android.dialer:id/quick_contact_photo']")
	public static AndroidElement contactLogo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear frequents']")
	public static AndroidElement clearFrequentsOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static AndroidElement okBtn;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static AndroidElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
	public static AndroidElement searchCallLogOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static AndroidElement clearCallHistoryOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.contacts:id/selection_menu']")
	public static AndroidElement selectOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select all']")
	public static AndroidElement selectAllOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Unselect all']")
	public static AndroidElement UnselectAllOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
	public static AndroidElement clearOptInCallHistory;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static AndroidElement contactsSettingsOPt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement contactsDefaultAccountSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static AndroidElement contactsDefaultPhone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='ALL']")
	public static AndroidElement contactsPageAll;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']")
	public static AndroidElement contact;

	@AndroidFindBy(xpath="//android.widget.ImageView[@index='1']")
	public static AndroidElement selectContact;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement moreSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static AndroidElement delete_option;

	@AndroidFindBy(xpath="//android.widget.Button[@text='0 selected']")
	public static AndroidElement zero_selected;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call history\")")
	public static AndroidElement call_history;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement Ok_option;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='No one is on your speed dial yet']")
	public static AndroidElement favoriteEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='Your call history is empty']")
	public static AndroidElement callLogEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='You don't have any contacts yet']")
	public static AndroidElement contactsEmptyPage;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Speed dial tab.']")
	public static AndroidElement FrequentPage;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Call History tab.']")
	public static AndroidElement callLogPage;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Contacts tab.']")
	public static AndroidElement contactPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
	public static AndroidElement makeACallOption;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static AndroidElement dialpadEditFld;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	public static AndroidElement callBtn;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/incall_end_call']")
	public static AndroidElement endCallBtn;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0' and @resource-id='com.android.dialer:id/primary_action_view']")
	public static AndroidElement firstDialedNumber;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0' and @resource-id='com.android.dialer:id/call_log_row']")
	public static AndroidElement secondDialedNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A CONTACT']")
	public static AndroidElement addAContactOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
	public static AndroidElement createNewContactOpt;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.contacts:id/account_selector_container']")
	public static AndroidElement savingToOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call details']")
	public static AndroidElement callDetailsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_duration']")
	public static AndroidElement durationInSec;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']")
	public static AndroidElement callBtnInCallLog;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement recentCallLogNumber;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/message']")
	public static AndroidElement dialpad_PermissionPopUp;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static AndroidElement permissionPopUp;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.android.packageinstaller:id/do_not_ask_checkbox\")")
	public static AndroidElement dontAskAgain;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static AndroidElement allowBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_deny_button']")
	public static AndroidElement denyBtn;
	 
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").checked(\"true\")")
	public static AndroidElement checkboxvalidation;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement nameEditFld;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.contacts:id/editor_menu_save_button']")
	public static AndroidElement saveContactOpt;	

	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static AndroidElement saveOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement savedContact;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']/../..//android.widget.QuickContactBadge[@index='0']")
	public static AndroidElement dialpadContactDetials;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
	public static AndroidElement dialpadBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement savedContactNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Primary Number\")")
	public static AndroidElement contact_one;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reference Number\")")
	public static AndroidElement contact_two;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Remove from favorites']")
	public static AndroidElement validate_fav_add_star_enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_star\")")
	public static AndroidElement fav_add_star_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static AndroidElement search_bar;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_name_textview\")")
	public static AndroidElement searched_contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Send a message\")")
	public static AndroidElement Send_message;

	@AndroidFindBy(id="com.android.mms:id/embedded_text_editor")
	public static AndroidElement messageEditFld;
	
	@AndroidFindBy(id="com.android.mms:id/send_button_sms")
	public static AndroidElement sendMessageOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
	public static AndroidElement messageSentNow;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	public static AndroidElement searchContactsFld;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add to a contact\")")
	public static AndroidElement Add_to_contact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contact\")")
	public static AndroidElement Contact_three;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement contact_more_option;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Link\")")
	public static AndroidElement Link;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement contact_linked_1;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static AndroidElement contact_linked_2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_edit\")")
	public static AndroidElement edit_contact;
	
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

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Change photo']")
	public static AndroidElement added_image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove photo\")")
	public static AndroidElement Remove_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.contacts:id/editor_menu_save_button\")")
	public static AndroidElement editor_menu_save_button;	
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc='Add contact photo']")
	public static AndroidElement contact_image_view_without_image;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public static AndroidElement cancelChangePhoto;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/preview_thumb\")")
	public static AndroidElement captured_image_view_in_camera;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Choose photo\")")
	public static AndroidElement Choose_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
	public static AndroidElement Camera;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static AndroidElement captured_image;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MISSED\")")
	public static AndroidElement missedCallOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").content-desc(\"Speed dial\")")
	public static AndroidElement speedDailPage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_data_view\")")
	public static AndroidElement dailedContactDetails;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Block number\")")
	public static AndroidElement blockNumberopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement blockNumberBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call screening']")
	public static AndroidElement callScreeningOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage black list']")
	public static AndroidElement manageBlackListOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static AndroidElement blockedNumbers;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement phoneSettingsOpt;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static AndroidElement deleteBlockedNumber;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.android.dialer:id/unblock_action']")
	public static AndroidElement unblockNumberOpt;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='UNBLOCK']")
	public static AndroidElement unblockNumber;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Copy number']")
	public static AndroidElement copyNumberInCallDetails;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit number before call']")
	public static AndroidElement editNumberBeforeCallOpt;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static AndroidElement keypadDailedNumber;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Missed call\")")
	public static AndroidElement missedCall;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Outgoing call\")")
	public static AndroidElement outgoingCall;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Incoming call\")")
	public static AndroidElement incomingCall;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='1']/../..//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement callMobile_ContactDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='2']/../..//android.widget.RelativeLayout[@index='2']")
	public static AndroidElement emailHome_ContactDetails;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@index='1']/../..//android.widget.RelativeLayout[@index='4']")
	public static AndroidElement viewAddress_ContactDetails;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static AndroidElement Search_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.contacts:id/search_view\")")
	public static AndroidElement findContacts;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/cliv_name_textview']")
	public static AndroidElement longpress_FirstContact;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='System']")
	public static AndroidElement SystemOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Date & time']")
	public static AndroidElement date_time_Opt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1:00 PM\")")
	public static AndroidElement timeFormatSetting12Hrs;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"13:00\")")
	public static AndroidElement timeFormatInSetting24hrs;
		
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_time']")
	public static AndroidElement call_Time;
	
	@FindBy(xpath="//android.widget.TextView[@text='No recent items']")
	public static AndroidElement no_Recent_Items;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR ALL']")
	public static AndroidElement clear_all;
	
	@AndroidFindBy(id="com.android.systemui:id/task_view_bar")
	public static AndroidElement recent_task_view_bar;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"PHONE\")")
	public static AndroidElement default_Account_For_New_Contact;
	
	
}