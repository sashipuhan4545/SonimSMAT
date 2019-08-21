package com.xp8.util;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_PhoneDialer {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_PhoneDialer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@AndroidFindBy(xpath="//android.support.v4.view.ViewPager[@resource-id='com.android.dialer:id/lists_pager']")
	public static WebElement phoneFrame;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear frequents']")
	public static WebElement clearFrequentsOpt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialtacts_options_menu_button']")
	public static WebElement settingsIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
	public static WebElement okBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")
	public static AndroidElement deletefirstcontact;
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	public static WebElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
	public static WebElement searchCallLogOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	public static WebElement clearCallHistoryOpt;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/../..//android.widget.Button[@text='0 selected']")
	public static WebElement selectOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Select all']")
	public static WebElement selectAllOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
	public static WebElement clearOptInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Call history']")
	public static WebElement callHistoryOpt;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static WebElement Ok_option;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='No one is on your speed dial yet']")
	public static WebElement favoriteEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='Your call log is empty']")
	public static WebElement callLogEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='You don't have any contacts yet']")
	public static WebElement contactsEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='0']")
	public static WebElement FrequentPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageView[@content-desc='Call History tab.']")
	public static WebElement callLogPage;

	//@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Call History tab.']")
	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Call History tab.'or@content-desc='Call History tab. 1 unread item.'or@content-desc='Call History tab. 2 unread items.']")
	public static WebElement callLogPage1;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Create new contact']")
	public static AndroidElement createnewconttxt_dialpad;
	
	@AndroidFindBy(id="com.android.dialer:id/icon")
	public static AndroidElement Callhistorytab;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement dailedFirstNum;
	
	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageView[@content-desc='Contacts tab.']")
	public static WebElement contactPage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
	public static WebElement makeACallOption;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static WebElement dialpadEditFld;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	public static WebElement callBtn;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialpad_floating_action_button']")
	public static WebElement sprintcallBtn;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/incall_end_call']")
	public static WebElement endCallBtn;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='1' and @resource-id='com.android.dialer:id/call_log_row']")
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

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_duration']")
	public static WebElement durationInSec;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.dialer:id/primary_action_button\")")
	public static WebElement callBtnInCallLog;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.dialer:id/name\")")
	public static WebElement recentCallLogNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
	public static WebElement permissionPopUp;

	@AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
	public static WebElement allowBtn;

	/*	@AndroidFindBy(xpath="//android.widget.EditText[@text='First name']")
	public static WebElement nameEditFld;
	 */
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement nameEditFld;

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
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.dialer:id/recycler_view\")")
	public static WebElement after_unlink;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Send a message\")")
	public static WebElement Send_message;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"View linked contacts\")")
	public static WebElement view_linked_cnts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNLINK\")")
	public static WebElement unlink_txt;

	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static WebElement messageEditFld;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")")
	public static WebElement sprintmessageEditFld;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.mms:id/send_button_sms\").description(\"Send\")")
	public static WebElement sendMessage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static WebElement sprintsendMessage;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNBLOCK\")")
	public static WebElement unblocktxt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
	public static WebElement messageSentNow;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Now • SMS']")
	public static WebElement sprintmessageSentNow;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Start Messaging\")")
	public static AndroidElement vzwstartmsg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"SKIP PROVISIONING\")")
	public static AndroidElement vzwskip;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	public static AndroidElement searchContactsFld;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Add to a contact\")")
	public static AndroidElement Add_to_contact;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Contact\")")
	public static AndroidElement Contact_three;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement contact_more_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Contacts tab.\")")
	public static WebElement contactstab;

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
	public static AndroidElement photo_reviewdone;

	/*@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"org.codeaurora.snapcam:id/done_button\")")
	public static AndroidElement photo_add_Btn;
	 */
	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Review done']")
	public static WebElement photo_add_Btn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Done\")")
	public static AndroidElement done_btn;

	@AndroidFindBy(xpath="//android.view.View[@content-desc='Change contact photo']")
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

	@AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
	public static WebElement CancelOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='DISCARD']")
	public static WebElement discardBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Block number']")
	public static WebElement blockNumberOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='BLOCK']")
	public static WebElement blockBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Unblock number']")
	public static WebElement unblockNumberOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='UNBLOCK']")
	public static WebElement unblockBtn;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
	public static WebElement Settings;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Call screening\")")
	public static AndroidElement Call_screening;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Manage black list\")")
	public static AndroidElement Manage_black_list;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"ADD A NUMBER\")")
	public static AndroidElement add_a_number_to_block;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.server.telecom:id/add_blocked_number\")")
	public static AndroidElement phone_number_fld_to_block;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.server.telecom:id/blocked_number\")")
	public static WebElement blocked_number;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.server.telecom:id/delete_blocked_number\")")
	public static AndroidElement delete_unblock_number_option;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"UNBLOCK\")")
	public static AndroidElement unblock_btn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ListView\").resourceId(\"android:id/list\")")
	public static AndroidElement blocked_no_list;
	
	
}
