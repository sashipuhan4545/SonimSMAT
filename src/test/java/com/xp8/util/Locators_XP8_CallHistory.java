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

	@FindBy(xpath="//android.widget.TextView[@text='No recent items']")
	public static AndroidElement no_Recent_Items;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR ALL']")
	public static AndroidElement clear_all;

	@AndroidFindBy(id="com.android.systemui:id/task_view_bar")
	public static AndroidElement recent_task_view_bar;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"PHONE\")")
	public static AndroidElement default_Account_For_New_Contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.support.v4.view.ViewPager\").resourceId(\"com.android.dialer:id/lists_pager\")")
	public static AndroidElement phoneFrame;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"com.google.android.dialer:id/search_fragment_container\")")
	public static AndroidElement phoneFrameInVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/dialtacts_options_menu_button\")")
	public static AndroidElement settingsIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.dialer:id/three_dot_menu_or_clear_icon_view\")")
	public static AndroidElement settingsIconInVZW;

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Call History']")
	//@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@index='0']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call history\")")
	public static AndroidElement callHistoryOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/name\")")
	public static AndroidElement callLogWithSymbols;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/name\")")
	public static AndroidElement callLogWithSymbolsInVZW;

	//@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/name\")")
	public static AndroidElement contactDetails;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/name\")")
	public static AndroidElement contactDetailsInVZW;

	@AndroidFindBy(xpath="//android.widget.QuickContactBadge[@resource-id='com.android.dialer:id/quick_contact_photo']")
	public static AndroidElement contactLogo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.QuickContactBadge\").instance(0)")
	public static AndroidElement contactLogoIns;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.QuickContactBadge\").resourceId(\"com.google.android.dialer:id/quick_contact_photo\")")
	public static AndroidElement contactLogoInVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"P\")")
	public static AndroidElement contactIconInVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.QuickContactBadge\").instance(2)")
	public static AndroidElement contactIconInVZWInst;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.QuickContactBadge\").instance(1)")
	public static AndroidElement contactIconInVZWInstRef;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear frequents']")
	public static AndroidElement clearFrequentsOpt;

	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static AndroidElement okBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement okBtnUiSelector;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.contacts:id/btn_ok\")")
	public static AndroidElement btn_ok_Contacts;

	//@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"More options\")")
	public static AndroidElement moreOptionsInCallHistory;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
	public static AndroidElement searchCallLogOpt;

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Clear call history\")")
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

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Choose a default account for new contacts:\")")
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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"More options\")")
	public static AndroidElement moreSettingsInUiSelector;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
	public static AndroidElement delete;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.mms:id/delete\")")
	public static AndroidElement SMSDeleteLogo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete\")")
	public static AndroidElement deleteInUiSelector;

	@AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
	public static AndroidElement delete_BUTTON;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement delete_BUTTONInUiSelector;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
	public static AndroidElement deleteOptInVZW;

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

	/*@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='Your call history is empty']")
	public static AndroidElement callLogEmptyPage;*/

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Your call history is empty\")")
	public static AndroidElement callLogEmptyPage;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='You don't have any contacts yet']")
	public static AndroidElement contactsEmptyPage;

	//@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Speed dial tab.']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Speed dial tab.\")")
	public static AndroidElement FrequentPage;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.google.android.dialer:id/speed_dial_tab\")")
	public static AndroidElement FrequentPageVZW;

	//@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Call History tab.']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Call History tab.\")")
	public static AndroidElement callLogPage;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").instance(1)")
	public static AndroidElement callLogPageInst;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.google.android.dialer:id/call_log_tab\")")
	public static AndroidElement recentCallLogPage;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='Contacts tab.']")
	public static AndroidElement contactPageXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").description(\"Contacts tab.\")")
	public static AndroidElement contactPage;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RelativeLayout\").resourceId(\"com.google.android.dialer:id/contacts_tab\")")
	public static AndroidElement contactPageVZW;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
	public static AndroidElement makeACallOption;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	public static AndroidElement dialpadEditFld;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.dialer:id/digits\")")
	public static AndroidElement dialpadEditFldVZW;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
	public static AndroidElement callBtn;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/incall_end_call']")
	public static AndroidElement endCallBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.dialer:id/incall_end_call\")")
	public static AndroidElement endCallBtnVZW;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0' and @resource-id='com.android.dialer:id/primary_action_view']")
	public static AndroidElement firstDialedNumber;

	@AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0' and @resource-id='com.android.dialer:id/call_log_row']")
	public static AndroidElement secondDialedNumber;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A CONTACT']")
	public static AndroidElement addAContactOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
	public static AndroidElement createNewContactOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(24)")
	public static AndroidElement createNewContactOptUiInstance;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Create new contact\")")
	public static AndroidElement createNewContactOptUiSelector;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.contacts:id/account_selector_container']")
	public static AndroidElement savingToOpt;

	//@AndroidFindBy(xpath="//android.widget.TextView[@text='Call details']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call details\")")
	public static AndroidElement callDetailsOpt;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_duration']")
	public static AndroidElement durationInSec;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']")
	public static AndroidElement callBtnInCallLog;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.dialer:id/primary_action_button\")")
	public static AndroidElement callBtnInCallLogVZW;

	@AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	public static AndroidElement recentCallLogNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.dialer:id/primary_action_view\").instance(4)")
	public static AndroidElement recentCallLogNumberInUiSelector;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.google.android.dialer:id/editor_menu_save_button\")")
	public static AndroidElement saveContactOptVZW;

	@AndroidFindBy(xpath="//android.widget.Button[@text='SAVE']")
	public static AndroidElement saveOpt;

	//@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/name\")")
	public static AndroidElement savedContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/name\")")
	public static AndroidElement savedContactVZW;

	@AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']/../..//android.widget.QuickContactBadge[@index='0']")
	public static AndroidElement dialpadContactDetials;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
	public static AndroidElement dialpadBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/floating_action_button\")")
	public static AndroidElement dialpadBtnInUiSelector;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.dialer:id/fab\")")
	public static AndroidElement dialpadBtnInVZW;

	@AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/header']")
	public static AndroidElement savedContactNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Primary Number\")")
	public static AndroidElement contact_one;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reference Number\")")
	public static AndroidElement contact_two;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").description(\"Remove from favorites\")")
	//@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Remove from favorites']")
	public static AndroidElement validate_fav_add_star_enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_star\")")
	public static AndroidElement fav_add_star_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/search_box_start_search\")")
	public static AndroidElement search_bar;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_name_textview\")")
	public static AndroidElement searched_contact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Send a message\")")
	public static AndroidElement Send_message;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.mms:id/embedded_text_editor\")")
	public static AndroidElement messageEditFld;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.apps.messaging:id/compose_message_text\")")
	public static AndroidElement messageEditFldInSprint;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/send_button_sms\")")
	public static AndroidElement sendMessageOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.apps.messaging:id/send_message_button_icon\")")
	public static AndroidElement sendMessageOptInSprint;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement messageSentNow;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Now • SMS\")")
	public static AndroidElement messageSentNowInSprint;


	//=====================Message Plus================//

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.verizon.messaging.vzmsgs:id/skip_provisioning_tv\")")
	public static AndroidElement skipProvisioningVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.verizon.messaging.vzmsgs:id/composeFab\")")
	public static AndroidElement composeNewMsgVZW;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/start\").text(\"Start Messaging\")")
	public static AndroidElement vzw_Start_msg;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/first_number_block\").text(\"• • •\")")
	public static AndroidElement vzw_msg_enterno;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/save_txt\").text(\"Next\")")
	public static AndroidElement vzw_msg_next;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	public static AndroidElement searchContactsFld;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/composebtnSend\")")
	public static AndroidElement vzw_msg_send;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/compose_embedded_text_editor\").text(\"Type a message…\")")
	public static AndroidElement vzw_msg_field;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.verizon.messaging.vzmsgs:id/status\").text(\"Delivered\")")
	public static AndroidElement vzw_msg_delivery;

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

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Missed\")")
	public static AndroidElement missedCallOptVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").content-desc(\"Speed dial\")")
	public static AndroidElement speedDailPage;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.dialer:id/cliv_data_view\")")
	public static AndroidElement dailedContactDetails;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.dialer:id/primary\")")
	public static AndroidElement dailedContactDetailsInVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Block number\")")
	public static AndroidElement blockNumberopt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement blockNumberBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Block/report spam\")")
	public static AndroidElement blockNumberoptVZW;

	//@AndroidFindBy(xpath = "//android.widget.TextView[@text='Call screening']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Call screening\")")
	public static AndroidElement callScreeningOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Blocked numbers\")")
	public static AndroidElement blockedNumbersOptInVZW;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage black list']")
	public static AndroidElement manageBlackListOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.server.telecom:id/blocked_number']")
	public static AndroidElement blockedNumbers;

	//@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement phoneSettingsOpt;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.server.telecom:id/delete_blocked_number']")
	public static AndroidElement deleteBlockedNumber;

	//@AndroidFindBy(xpath = "//android.widget.LinearLayout[@resource-id='com.android.dialer:id/unblock_action']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.android.dialer:id/unblock_action\")")
	public static AndroidElement unblockNumberOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unblock number\")")
	public static AndroidElement unblockNumberOptVZW;

	//@AndroidFindBy(xpath = "//android.widget.Button[@text='UNBLOCK']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNBLOCK\")")
	public static AndroidElement unblockNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"android:id/button1\")")
	public static AndroidElement unblockNumberUiSelector;

	//@AndroidFindBy(xpath = "//android.widget.TextView[@text='Copy number']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Copy number\")")
	public static AndroidElement copyNumberInCallDetails;

	//@AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit number before call']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Edit number before call\")")
	public static AndroidElement editNumberBeforeCallOpt;

	//@AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.dialer:id/digits\")")
	public static AndroidElement keypadDailedNumber;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.google.android.dialer:id/digits\")")
	public static AndroidElement keypadDailedNumberInVZW;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Missed call\")")
	public static AndroidElement missedCall;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Outgoing call\")")
	public static AndroidElement outgoingCall;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Incoming call\")")
	public static AndroidElement incomingCall;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='0']/android.widget.TextView[1]")
	public static AndroidElement callMobile_ContactDetails;

	/*@AndroidFindBy(xpath="//android.widget.TextView[@index='1']/../..//android.widget.RelativeLayout[@index='0']")
	public static AndroidElement callMobile_ContactDetails;*/

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='2']/android.widget.TextView[1]")
	public static AndroidElement emailHome_ContactDetails;

	/*@AndroidFindBy(xpath="//android.widget.TextView[@index='2']/../..//android.widget.RelativeLayout[@index='2']")
	public static AndroidElement emailHome_ContactDetails;*/

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='4']/android.widget.TextView[1]")
	public static AndroidElement viewAddress_ContactDetails;

	/*@AndroidFindBy(xpath="//android.widget.TextView[@index='1']/../..//android.widget.RelativeLayout[@index='4']")
	public static AndroidElement viewAddress_ContactDetails;*/

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_search\")")
	public static AndroidElement Search_ConatctIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.contacts:id/search_view\")")
	public static AndroidElement findContacts;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")
	public static AndroidElement longpress_FirstContact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"System\")")
	public static AndroidElement SystemOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Date & time\")")
	public static AndroidElement date_time_Opt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"1:00 PM\")")
	public static AndroidElement timeFormatSetting12Hrs;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"13:00\")")
	public static AndroidElement timeFormatInSetting24hrs;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.dialer:id/call_time\")")
	public static AndroidElement call_Time;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.google.android.dialer:id/call_time\")")
	public static AndroidElement call_TimeVZW;
	/*
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"CLEAR ALL\")")
	public static AndroidElement clear_all;
	 */
	@AndroidFindBy(uiAutomator = "new UiAutomator().className(\"android.view.View\").package(\"com.android.systemui\")")
	public static AndroidElement recentTaskViewBar;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.contacts:id/large_title\")")
	public static AndroidElement title_gradient;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(10)")
	public static AndroidElement filledValueText;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(10)")
	public static AndroidElement filledValueByXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.dialer:id/call_duration\")")
	public static AndroidElement callDurationData;

	//=====================XP8_Interruption==========================================//
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Alarm\")")
	public static AndroidElement alarm;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(0)")
	public static AndroidElement alarmInstance;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").description(\"Add alarm\")")
	public static AndroidElement createalarm;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").instance(2)")
	public static AndroidElement createalarmInstance;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Add alarm']")
	public static AndroidElement createalarmXpath;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"android:id/toggle_mode\")") //description(\"Switch to text input mode for the time input.\")")
	public static AndroidElement alarmSwitchToText;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Switch to text input mode for the time input.']")
	public static AndroidElement alarmSwitchToTextXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"	android.widget.ImageButton\").instance(0)")
	public static AndroidElement alarmSwitchToTextInstance;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/input_hour\")")
	public static AndroidElement alarmInputHour;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/input_minute\")")
	public static AndroidElement alarmInputMinute;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Spinner\").resourceId(\"android:id/am_pm_spinner\")")
	public static AndroidElement alarmAMPMSpinner;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"AM\")")
	public static AndroidElement alarmAMOpt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.CheckedTextView\").text(\"PM\")")
	public static AndroidElement alarmPMOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Timer\")")
	public static AndroidElement timerOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"1\")")
	public static AndroidElement timerValue1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
	public static AndroidElement timerValue1Ins;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"0\")")
	public static AndroidElement timerValue0;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(9)")
	public static AndroidElement timerValue0Ins;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"Start\")")
	public static AndroidElement timerStartOpt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Start']")
	public static AndroidElement timerStartOptXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.google.android.deskclock:id/timer_time_text\")")
	public static AndroidElement timerTimeText;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(4)")
	public static AndroidElement timerTimeTextIns;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"Stop\")")
	public static AndroidElement timerStopOpt;

	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Stop']")
	public static AndroidElement timerStopOptXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.deskclock:id/alarm\")")
	public static AndroidElement timerSnoozeOrDismissOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.mms:id/create\")")
	public static AndroidElement createButtonInMessage;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.dialer:id/dialpad_floating_action_button\")")
	public static AndroidElement callButtonInDailer;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").packageName(\"com.google.android.youtube\")")
	public static AndroidElement youTubeVideoSelect;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"NO THANKS\")")
	public static AndroidElement NoThanksBtnMusic;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SKIP\")")
	public static AndroidElement SkipBtnMusic;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.music:id/play_button\")")
	public static AndroidElement musicPlayButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.google.android.music:id/play_pause_header\")")
	public static AndroidElement musicPauseStopButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"Play\")")
	public static AndroidElement musicPlayEnabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageButton\").description(\"Pause\")")
	public static AndroidElement musicPauseEnabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please plug in a Headset to use FM Radio\")")
	public static AndroidElement FMHeadSetError;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.caf.fmradio:id/btn_onoff\")")
	public static AndroidElement fmPowerONOFF;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.caf.fmradio:id/prog_frequency_tv\")")
	public static AndroidElement fmFrequency;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"GOT IT\")")
	public static AndroidElement gotIT;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SONIM CDMA\")")
	public static AndroidElement gmailAccountName;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"TAKE ME TO GMAIL\")")
	public static AndroidElement takeMeToGmailOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add another email address\")")
	public static AndroidElement addAnotherEmailAddressopt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement googleAccountOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(9)")
	public static AndroidElement gmailUserName;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement nextBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(11)")
	public static AndroidElement gmailPwd;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement gmailAgreeBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"MORE\")")
	public static AndroidElement gmailMOREBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT\")")
	public static AndroidElement gmailAcceptBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add an email address\")")
	public static AndroidElement addEmailAddress;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.google.android.gm:id/og_apd_internal_image_view\")")
	public static AndroidElement gmailLoggedIn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DISMISS\")")
	public static AndroidElement alarmDismissBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SNOOZE\")")
	public static AndroidElement alarmSnoozeBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"STOP\")")
	public static AndroidElement clockStopBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete Conversation\")")
	public static AndroidElement deleteConversationInVZW;

	//=================Memory Fill============//

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='FillMemory']")
	public static AndroidElement fillmemoryIcon;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Memory Fill\")")
	public static AndroidElement memoryfillIcon;

	@AndroidFindBy(xpath = "//android.widget.TextView[@content-desc='Memory Fill']")
	public static AndroidElement memoryfillIconByXpath;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"INTERNAL\")")
	public static AndroidElement internalSpaceopt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").instance(10)")
	public static AndroidElement filledValue;

	@AndroidFindBy(id="com.rektgames.memoryfill:id/allocate")
	public static AndroidElement fillOpt;

	@AndroidFindBy(id="com.rektgames.memoryfill:id/cancel")
	public static AndroidElement cancelOpt;

	@AndroidFindBy(id="com.rektgames.memoryfill:id/deallocate")
	public static AndroidElement memoryFreeUpBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"UNINSTALL\")")
	public static AndroidElement uninstallBtn;

	@AndroidFindBy(id = "com.android.settings:id/left_button")
	public static AndroidElement uninstallBtnById;

	@AndroidFindBy(id = "android:id/button1")
	public static AndroidElement okBtnById;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"See all\")")
	public static AndroidElement seeAllOptn;

	//================Lock and Airplane Mode=======================//

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

	//============SLEEP=====================//

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

	//=================================Message+===============================//

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Message+\")")
	public static AndroidElement MessagePlus ;

	//===============YouTube==================//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static AndroidElement YouTube ;

	//===========Play Music===================//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Play Music\")")
	public static AndroidElement PlayMusic ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"No music available\")")
	public static AndroidElement noMusicAvailable;

	//================Google Maps==================//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
	public static AndroidElement GoogleMaps ;

	//================File Manager==================//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"File Manager\")")
	public static AndroidElement fileManager;

	//===============Google Photos=============================//
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Photos\")")
	public static AndroidElement googlePhotos;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Confirm\")")
	public static AndroidElement confirmBtn;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"Skip\")")
	public static AndroidElement skipBtn;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Apps list']")
	public static AndroidElement AppListIcon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT & CONTINUE\")")
	public static AndroidElement ACCEPTCONTINUE;
}