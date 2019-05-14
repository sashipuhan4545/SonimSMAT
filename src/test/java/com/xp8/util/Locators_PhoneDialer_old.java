package com.xp8.util;

import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_PhoneDialer_old {

private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_PhoneDialer_old(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	
   @AndroidFindBy(xpath="//android.support.v4.view.ViewPager[@resource-id='com.android.dialer:id/lists_pager']")
   public static WebElement phoneFrame;
	
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='No one is on your speed dial yet']")
   public static WebElement favoriteEmptyPage;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']")
   public static WebElement contact;
   
   @AndroidFindBy(xpath="//android.widget.ImageView[@index='1']")
   public static WebElement selectContact;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='Your call log is empty']")
   public static WebElement callLogEmptyPage;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']/../..//android.widget.TextView[@text='You don't have any contacts yet']")
   public static WebElement contactsEmptyPage;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='0']")
   public static WebElement favoritePage;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='1']")
   public static WebElement callLogPage;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.RelativeLayout[@index='2']")
   public static WebElement contactPage;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='MAKE A CALL']")
   public static WebElement makeACallOption;
   
   @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.dialer:id/digits']")
   public static WebElement dialpadEditFld;
   
   @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/dialpad_floating_action_button_container']")
   public static WebElement callBtn;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_end_call_action_button']")
   public static WebElement endCallBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/callStateLabel']")
   public static WebElement callingViaOpt;

   @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']")
   public static WebElement callBtnInCallLog;

   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/floating_action_button']")
   public static WebElement dialpadBtn;

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
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.contacts:id/editors']/../..//android.widget.EditText[@text='Name']")
   public static WebElement nameEditFld;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/menu_save']")
   public static WebElement saveOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/large_title']")
   public static WebElement savedContact;

   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.packageinstaller:id/permission_message']")
   public static WebElement permissionPopUp;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='ALLOW']")
   public static WebElement allowBtn;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='More options']")
   public static WebElement ContactsMoreOptions;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
   public static WebElement contactsSettingsOPt;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='Default account for new contacts']")
   public static WebElement contactsDefaultAccountSettings;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='PHONE']")
   public static WebElement contactsDefaultPhone;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Add to a contact']")
   public static WebElement addToContactOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='AddCallLog']")
   public static WebElement addedContactCallLog;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='AddDialpad']")
   public static WebElement addedContactDialpad;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Send a message']")
   public static WebElement sendMessageInCallLog;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Send SMS']")
   public static WebElement sendMessageInDialpad;
   
   @AndroidFindBy(xpath="//android.widget.EditText[@text='Type an SMS message']")
   public static WebElement messageEditFld;
   
   @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
   public static WebElement sendMessageBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Now • SMS']")
   public static WebElement messageSentNowOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Call details']")
   public static WebElement callDetailsOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Outgoing call']")
   public static WebElement outgoingCallText;
   
   @AndroidFindBy(xpath="//android.view.View[@resource-id='com.android.dialer:id/call_type_icon']")
   public static WebElement callTypeArrowIcon;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Block number']")
   public static WebElement blockNumberOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='BLOCK']")
   public static WebElement blockBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Unblock number']")
   public static WebElement unblockNumberOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='UNBLOCK']")
   public static WebElement unblockBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Copy number']")
   public static WebElement copyNumberOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Edit number before call']")
   public static WebElement editBeforeCallOpt;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/deleteButton']")
   public static WebElement clearOptInDialpad;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.TextView[@resource-id='com.android.dialer:id/name']")
   public static WebElement editedNumber;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/call_detail_delete_menu_item']")
   public static WebElement deleteOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD A FAVORITE']")
   public static WebElement addAFavorite;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/menu_star']") 
   public static WebElement favoriteIcon;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/contact_tile_secondary_button']")
   public static WebElement settingInFavorite;
  
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageView[@resource-id='com.android.dialer:id/contact_star_icon']")
   public static WebElement favoriteIconInFavoritePage;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Clear frequents']")
   public static WebElement clearFrequentsOpt;

   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/dialtacts_options_menu_button']")
   public static WebElement settingsIcon;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
   public static WebElement okBtn;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Import/export']")
   public static WebElement importOrExportOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Import from .vcf file']")
   public static WebElement importFromVCFFile;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Import from SIM card']")
   public static WebElement importFromSIMCard;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Export to SIM card']")
   public static WebElement exportToSIMCard;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Export to .vcf file']")
   public static WebElement exportToVCFFile;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Share all contacts']")
   public static WebElement shareAllContacts;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Ok']")
   public static WebElement okOptInImportExport;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='PHONE']")
   public static WebElement phoneOptInImport;
   
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Downloads\")")
   public static WebElement downloadsOpt;
   
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
   public static AndroidElement saveBtn;
   
   @AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='2']/../..//android.widget.TextView[@text='contacts.vcf']")
   public static WebElement exportedContact;
   
   @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
   public static AndroidElement searchContactsFld;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchedContactOne;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchedContactTwo;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='3']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchedContactThree;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='3']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchedContactFour;
   
   @AndroidFindBy(xpath="//android.widget.FrameLayout[@index='0']/../..//android.widget.Button[@text='0 selected']")
   public static WebElement selectOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Select all']")
   public static WebElement selectAllOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Messages']")
   public static WebElement messagesOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='JUST ONCE']")
   public static WebElement justOnceOpt;
   
   @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.google.android.apps.messaging:id/send_message_button_icon']")
   public static WebElement mmsOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Sending']")
   public static WebElement sendingTextOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
   public static WebElement settingsOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Display options']")
   public static WebElement displayOptions;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Sort by']")
   public static WebElement sortByOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Name format']")
   public static WebElement nameFormatOpt;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name']")
   public static WebElement firstNameOpt;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name']")
   public static WebElement lastNameOpt;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name first']")
   public static WebElement firstNameFirstOpt;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name first']")
   public static WebElement lastNameFirstOpt;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']/../..//android.widget.TextView[@text='Abc Auto']")
   public static WebElement firstNameContact;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']/../..//android.widget.TextView[@text='Test A']")
   public static WebElement lastNameAndFirstNameFirstContact;
   
   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='0']/../..//android.widget.TextView[@text='A, Test']")
   public static WebElement lastNameFirstContact;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Sounds and vibration']")
   public static WebElement soundsAndVibration;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Phone ringtone']")
   public static WebElement phoneRingtoneOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='android:id/summary']")
   public static WebElement ringtoneSelected;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@index='0']")
   public static WebElement ringtoneOpt;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='1']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
   public static WebElement alsoVibrateCheckbox;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Also vibrate for calls']")
   public static WebElement alsoVibrateForCallsOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Dialpad tones']")
   public static WebElement dialpadTonesOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Call end tone']")
   public static WebElement callEndToneOpt;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='2']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
   public static WebElement dialpadToneCheckbox;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Dialpad tone length']")
   public static WebElement dialPadTonelengthOpt;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@index='4']/../..//android.widget.LinearLayout[@resource-id='android:id/widget_frame']/../..//android.widget.CheckBox[@checked='false']")
   public static WebElement callEndToneCheckbox;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Normal']")
   public static WebElement dialpadToneLengthNormal;
   
   @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Long']")
   public static WebElement dialpadToneLengthLong;
   
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

   @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
   public static WebElement moreSettings;
   
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
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Delete']")
   public static WebElement delete;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
   public static WebElement deleteBtn;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.dialer:id/select_contact']")
   public static WebElement selectContactInSpeedDialSettings;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@content-desc='Search']")
   public static WebElement searchOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='KEEP EDITING']")
   public static WebElement keepEditingOpt;
   
   @AndroidFindBy(xpath="//android.widget.Button[@text='DISCARD']")
   public static WebElement discardBtn;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='Create new contact']")
   public static WebElement createNewContactInContactPage;
   
   @AndroidFindBy(xpath="//android.widget.EditText[@text='Phone']")
   public static WebElement phoneNumberEditFld;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Call History']")
   public static WebElement callHistoryOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='ALL']")
   public static WebElement allOptInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='MISSED']")
   public static WebElement missedOptInCallHistory;

   @AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options']")
   public static WebElement moreOptionsInCallHistory;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='Search call log']")
   public static WebElement searchCallLogOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Clear call history']")
   public static WebElement clearCallHistoryOpt;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Today']")
   public static WebElement todayOptInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Older']")
   public static WebElement olderOptInCallHistory;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='No call log']")
   public static WebElement noCallLogInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='You have no missed calls.']")
   public static WebElement noMissedCallsInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='CLEAR']")
   public static WebElement clearOptInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='NEXT >']")
   public static WebElement nextOptInMessaging;
   
   @AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
   public static WebElement messageEditFldReskin;
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_sms']")
   public static WebElement sendMessageReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
   public static WebElement messageSentNowReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Messaging']")
   public static WebElement messagingOptReskin;
   
   @AndroidFindBy(xpath="//android.widget.Spinner[@resource-id='com.android.dialer:id/filter_status_spinner']")
   public static WebElement logTypeSelectReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='All calls']")
   public static WebElement allCallsReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Incoming calls only']")
   public static WebElement incomingCallsReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Outgoing calls only']")
   public static WebElement outgoingCallsReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Missed calls only']")
   public static WebElement missedCallsReskin;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Your call log is empty']")
   public static WebElement noCallsInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='You have no incoming calls.']")
   public static WebElement noIncomingCallsInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='You have no outgoing calls.']")
   public static WebElement noOutgoingCallsInCallHistory;
   
   @AndroidFindBy(xpath="//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view']")
   public static WebElement callLogInHistory;
   
   @AndroidFindBy(xpath="//android.support.v7.widget.RecyclerView[@resource-id='com.android.dialer:id/recycler_view']")
   public static WebElement callLogFrame; 
   
   @AndroidFindBy(xpath="//android.widget.ImageButton[@resource-id='com.android.mms:id/send_button_mms']")
   public static WebElement mmsOptReskin;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='Sending…']")
   public static WebElement sendingTextReskin;
   
   @AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='To']")
   public static WebElement toFldReskin;
   
   @AndroidFindBy(xpath="//android.widget.ListView[@resource-id='com.android.mms:id/history']")
   public static WebElement toFldContactSelect;
   
   @AndroidFindBy(xpath="//android.widget.FrameLayout[@resource-id='com.android.dialer:id/four']")
   public static WebElement dialpadDigitFour;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@text='Turn off airplane mode to make a call.']")
   public static AndroidElement turnOff_Airplane_PopUp;
   
   @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/duration']")
   public static WebElement durationInSec;

   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchFldOne;

   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='2']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchFldTwo;

   @AndroidFindBy(xpath="//android.view.ViewGroup[@index='3']/../..//android.widget.TextView[@text='AddCallLog']")
   public static WebElement matchFldThree;

   @AndroidFindBy(xpath="//android.widget.TextView[@text='ALL']")
   public static WebElement contactsPageAll;
   
   
   
}

