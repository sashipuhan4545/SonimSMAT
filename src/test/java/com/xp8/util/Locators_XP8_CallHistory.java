package com.xp8.util;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_XP8_CallHistory {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_XP8_CallHistory(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
}
	@AndroidFindBy(xpath="//android.support.v4.view.ViewPager[@resource-id='com.android.dialer:id/lists_pager']")
	public static WebElement phoneFrame;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialtacts_options_menu_button']")
	public static WebElement settingsIcon;

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Call History']")
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@index='0']")
	public static WebElement callHistoryOpt;
	
	@AndroidFindBy(xpath="//android.view.View[@resource-id='com.android.dialer:id/call_type_icons']")
	public static WebElement callLogWithSymbols;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static WebElement contactDetails;
	
	@AndroidFindBy(xpath="//android.widget.QuickContactBadge[@resource-id='com.android.dialer:id/quick_contact_photo']")
	public static WebElement contactLogo;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear frequents']")
	public static WebElement clearFrequentsOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static WebElement okBtn;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static WebElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
	public static WebElement searchCallLogOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static WebElement clearCallHistoryOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.contacts:id/selection_menu']")
	public static WebElement selectOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select all']")
	public static WebElement selectAllOpt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Unselect all']")
	public static WebElement UnselectAllOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
	public static WebElement clearOptInCallHistory;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static WebElement ContactsMoreOptions;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static WebElement contactsSettingsOPt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
	public static WebElement contactsDefaultAccountSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
	public static WebElement contactsDefaultPhone;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='ALL']")
	public static WebElement contactsPageAll;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']")
	public static WebElement contact;

	@AndroidFindBy(xpath="//android.widget.ImageView[@index='1']")
	public static WebElement selectContact;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement moreSettings;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static WebElement delete;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static WebElement delete_option;

	@AndroidFindBy(xpath="//android.widget.Button[@text='0 selected']")
	public static WebElement zero_selected;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call history\")")
	public static WebElement call_history;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static WebElement Ok_option;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='No one is on your speed dial yet']")
	public static WebElement favoriteEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='Your call log is empty']")
	public static WebElement callLogEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='You don't have any contacts yet']")
	public static WebElement contactsEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='0']")
	public static WebElement FrequentPage;

	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']/../..//android.widget.RelativeLayout[@index='1']")
	public static WebElement callLogPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='2']")
	public static WebElement contactPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
	public static WebElement makeACallOption;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static WebElement dialpadEditFld;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	public static WebElement callBtn;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/incall_end_call']")
	public static WebElement endCallBtn;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0' and @resource-id='com.android.dialer:id/primary_action_view']")
	public static WebElement firstDialedNumber;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0' and @resource-id='com.android.dialer:id/call_log_row']")
	public static WebElement secondDialedNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A CONTACT']")
	public static WebElement addAContactOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
	public static WebElement createNewContactOpt;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.contacts:id/account_selector_container']")
	public static WebElement savingToOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call details']")
	public static WebElement callDetailsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/duration']")
	public static WebElement durationInSec;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']")
	public static WebElement callBtnInCallLog;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static WebElement recentCallLogNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static WebElement permissionPopUp;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").resourceId(\"com.android.packageinstaller:id/do_not_ask_checkbox\")")
	public static WebElement dontAskAgain;
	
	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static WebElement allowBtn;

	@AndroidFindBy(xpath="//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_deny_button']")
	public static WebElement denyBtn;
	 
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckBox\").checked(\"true\")")
	public static WebElement checkboxvalidation;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Name\")")
	public static AndroidElement nameEditFld;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/menu_save']")
	public static WebElement saveContactOpt;	

	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static WebElement saveOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']")
	public static WebElement savedContact;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
	public static WebElement dialpadBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static WebElement savedContactNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Automation\")")
	public static WebElement contact_one;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Test\")")
	public static WebElement contact_two;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_star\")")
	public static WebElement fav_add_star_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static WebElement search_bar;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_name_textview\")")
	public static WebElement searched_contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Send a message\")")
	public static WebElement Send_message;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Text message']")
	public static WebElement messageEditFld;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SMS\")")
	public static WebElement sendMessage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Now • SMS']")
	public static WebElement messageSentNow;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	public static AndroidElement searchContactsFld;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add to a contact\")")
	public static AndroidElement Add_to_contact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contact\")")
	public static AndroidElement Contact_three;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement contact_more_option;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Link\")")
	public static AndroidElement Link;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
	public static WebElement contact_linked_1;
	
	@AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='1']")
	public static WebElement contact_linked_2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_edit\")")
	public static AndroidElement edit_contact;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.contacts:id/photo_icon\")")
	public static AndroidElement camera_image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Take photo\")")
	public static AndroidElement Take_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement photo_capture_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")
	public static AndroidElement photo_add_Btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Take photo\")")
	public static AndroidElement done_btn;

	@AndroidFindBy(xpath="//com.android.contacts[@content-desc='Change contact photo']")
	public static WebElement added_image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Remove photo\")")
	public static AndroidElement Remove_photo;
	
	@AndroidFindBy(xpath="//android.view.View[@content-desc='Add contact photo']")
	public static WebElement contact_image_view_without_image;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/preview_thumb\")")
	public static AndroidElement captured_image_view_in_camera;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Choose photo\")")
	public static AndroidElement Choose_photo;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
	public static AndroidElement Camera;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']")
	public static WebElement captured_image;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MISSED\")")
	public static AndroidElement missedCallOpt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").content-desc(\"Speed dial\")")
	public static WebElement speedDailPage;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_data_view\")")
	public static WebElement dailedContactDetails;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Block number\")")
	public static WebElement blockNumberopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static WebElement blockNumberBtn;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call screening']")
	public static WebElement callScreeningOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage black list']")
	public static WebElement manageBlackListOpt;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static WebElement blockedNumbers;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static WebElement phoneSettingsOpt;
	
	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static WebElement deleteBlockedNumber;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='UNBLOCK']")
	public static WebElement unblockNumber;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/call_detail_action_copy']")
	public static WebElement copyNumberInCallDetails;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.dialer:id/call_detail_action_edit_before_call']")
	public static WebElement editNumberBeforeCallOpt;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static WebElement keypadDailedNumber;
}