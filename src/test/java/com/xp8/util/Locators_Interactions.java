package com.xp8.util;


import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Interactions {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Interactions(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
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

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").textContains(\"Type a name\")")	
	public static AndroidElement TO_Field1;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.messaging:id/contact_picker_create_group\")")
	public static AndroidElement contactPicker;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now\")")
	public static AndroidElement now_Text; //To validate after sending SMS.

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Now • SMS\")")
	public static AndroidElement justnow_Text; //To validate after sending SMS.

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sending…']")
	public static AndroidElement sending_Txt;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"REPLY\")")
	public static AndroidElement reply_txt;


	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.systemui:id/remote_input_text\").text(\"Type message\")")
	public static AndroidElement reply_typemsg;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.systemui:id/remote_input_send\")")
	public static AndroidElement reply_send;

	@AndroidFindBy(xpath = "//android.widget.RelativeLayout[@index='1']/../..//android.widget.LinearLayout[@index='1']")
	public static AndroidElement smsfirstmsg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.mms:id/text_view_buttom\").text(\"https://www.google.com/\")")
	public static AndroidElement  chromeshare_msg;

	//@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.mms:id/text_view_buttom\").text(\"Hi\")")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Hi' or @text='Automation Test']")
	public static AndroidElement msg1;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	public static AndroidElement ContactsMoreOptions;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
	public static AndroidElement contactsSettingsOPt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Default account for new contacts']")
	public static AndroidElement contactsDefaultAccountSettings;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PHONE']")
	public static AndroidElement contactsDefaultPhone;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.contacts:id/floating_action_button\")")
	public static AndroidElement addnewcontact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"First name\")")
	public static AndroidElement nameEditFld;

	@AndroidFindBy(xpath = "//*[@text='Last name' or @text='Surname']")
	public static AndroidElement lastnameEditFld;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static AndroidElement phoneNumberEditFld;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.contacts:id/editor_menu_save_button']")
	public static AndroidElement saveOpt;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='CREATE NEW CONTACT' or @text='Create new contact']")
	public static AndroidElement createNewContactInContactPage;

	@AndroidFindBy(uiAutomator = "UiSelector().description(\"Contacts tab.\")")
	public static AndroidElement contactPage;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.contacts:id/cliv_name_textview\").text(\"Sonim Automation\")")
	public static AndroidElement validate_savedcontact;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/button1\").text(\"DELETE\")")
	public static AndroidElement delete_txt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/cliv_name_textview\")")
	public static AndroidElement deletefirstcontact;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.contacts:id/menu_delete\")")
	public static AndroidElement delete_option;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='0 selected']")
	public static AndroidElement zero_selected;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Select all']")
	public static AndroidElement selectAllOpt;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Ok\")")
	public static AndroidElement Ok_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Location\")")
	public static AndroidElement location;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement okBtn;
	
	@AndroidFindBy(xpath = "//*[@content-desc='Bluetooth off.' or @content-desc='Bluetooth on.,Not connected.']")
	public static AndroidElement bt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile data\")")
	public static AndroidElement data;
	
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@text='On' or @text='Off']")
	public static AndroidElement wifi;
	
	@AndroidFindBy(uiAutomator = "UiSelector().description(\"Open settings.\")")
	public static AndroidElement settingsnotification ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement locationswitch;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Alarm\")")
	public static AndroidElement alarm;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/fab\").description(\"Add alarm\")")
	public static AndroidElement createalarm;
	
	//@AndroidFindBy(xpath="//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@description='2']")
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RadialTimePickerView$RadialPickerTouchHelper\").description(\"2\")")
	public static AndroidElement  twohrsalarm ;
	
	//@AndroidFindBy(xpath="//android.widget.RadialTimePickerView$RadialPickerTouchHelper[@description='9']")
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.RadialTimePickerView$RadialPickerTouchHelper\").description(\"40\")")
	public static AndroidElement  fortyminshrsalarm ;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static AndroidElement okalarm;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/digital_clock\")")
	public static AndroidElement alarmenabled;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/delete\").text(\"Delete\")")
	public static AndroidElement alarmdelete;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Timer\")")
	public static AndroidElement timer;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/timer_setup_digit_1\").text(\"1\")")
	public static AndroidElement settimer;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/fab\")")
	public static AndroidElement starttimer;
	
	/*@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.deskclock:id/timer_time_text\")")
	public static AndroidElement ontimer;*/
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Stop\"]")
	public static AndroidElement ontimer;
	
	//@AndroidFindBy(xpath="//android.widget.Button[@content-desc=\"Delete\"]")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Delete\")")
     public static AndroidElement  deletetimer ;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Start\"]")
	public static AndroidElement  startingtimer ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.calendar:id/right_arrow\")")
	public static AndroidElement calendarfirststep;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.calendar:id/done_button\").text(\"Got it\")")
	public static AndroidElement calendargotit;
	
	//--------------------------------------TO CREATE GOOGLE ACCOUNT------------------------------------------------//
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add account\")")
	public static AndroidElement addaccount;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Google\")")
	public static AndroidElement google;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Spinner\").text(\"Create account\")")
	public static AndroidElement createaccount;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.MenuItem\").text(\"For myself\")")
	public static AndroidElement formyself;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"firstName\")")
	public static AndroidElement firstname;
	
	@AndroidFindBy(xpath="//android.view.View[@index='2']")
	public static AndroidElement lastname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement next;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"phoneNumberId\")")
	public static AndroidElement phoneno;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Month\")")
	public static AndroidElement month;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"day\")")
	public static AndroidElement day;
	
	@AndroidFindBy(xpath="//android.view.View[@index='2']")
	public static AndroidElement year;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Spinner\").text(\"Gender\")")
	public static AndroidElement gender;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"Male\")")
	public static AndroidElement malegender;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").resourceId(\"selectionc0\")")
	public static AndroidElement choosemail;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"passwd\")")
	public static AndroidElement createpassword;
	
	@AndroidFindBy(xpath="//android.view.View[@index='2']")
	public static AndroidElement cnfrmpassword;
	
	@AndroidFindBy(xpath="//android.widget.EditText[@index='0']")
	public static AndroidElement wlcmpassword;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Yes, I’m in\")")
	public static AndroidElement yesiamin;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"I agree\")")
	public static AndroidElement agree;
	
	@AndroidFindBy(uiAutomator="new UiSelector().text(\"Sign in with your Google Account. Learn more\")")
	public static AndroidElement sign;
	
//-----------------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.packageinstaller:id/permission_allow_button\").text(\"ALLOW\")")
	public static AndroidElement cameraallow;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"org.codeaurora.snapcam:id/shutter_button\")")
	public static AndroidElement camerashutter;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"org.codeaurora.snapcam:id/video_button\")")
	public static AndroidElement videobtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"org.codeaurora.snapcam:id/preview_thumb\")")
	public static AndroidElement previewbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Photos\")")
	public static AndroidElement photos;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.photos:id/first_page\")")
	public static AndroidElement photosbackup;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Keep off\")")
	public static AndroidElement keepoff;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.photos:id/negative_button\")")
	public static AndroidElement photonotnow;

	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.photos:id/collection_title\").text(\"Camera\")")
	public static AndroidElement cameralist;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Camera\")")
	public static AndroidElement camera;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Maps\")")
	public static AndroidElement maps;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SKIP\")")
	public static AndroidElement skip;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"GO\")")
	public static AndroidElement go;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.LinearLayout\").resourceId(\"com.google.android.apps.maps:id/action_bar_root\")")
	public static AndroidElement mapvalidate;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sound Recorder\")")
	public static AndroidElement soundrecorder;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.soundrecorder:id/recordButton\")")
	public static AndroidElement soundrecorderbtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.soundrecorder:id/stopButton\")")
	public static AndroidElement soundrecorderstopbtn;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.soundrecorder:id/list_item_title\")")
	public static AndroidElement soundrecordinglist;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SAVE\")")
	public static AndroidElement soundrecordersave;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Recording audio is not allowed during call.\")")
	public static AndroidElement soundrecorderslist;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"USB debugging connected\")")
	public static AndroidElement usbdebuggingconnected;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Developer options\")")
	public static AndroidElement developeropt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Gaana\")")
	public static AndroidElement gaana;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Home\")")
	public static AndroidElement gaanahome;
	
	//@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/txt_skip\").text(\"SKIP\")")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SKIP\")")
	public static AndroidElement gaanaskip;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/btn_all_done\").text(\"CONTINUE (2)\")")
	public static AndroidElement gaanacontinue2;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/btn_all_done\").text(\"CONTINUE\")")
	public static AndroidElement gaanacontinue;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/txt_do_later\").text(\"I'll do it later\")")
	public static AndroidElement gaanadolater;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/play_icon\")")
	public static AndroidElement gaanasongslt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/header.text\").text(\"Trending Songs\")")
	public static AndroidElement gaanatrendingsongs;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/content\")")
	public static AndroidElement  gaanaframe ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.gaana:id/shuffle_play_button\")")
	public static AndroidElement gaanaplay;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/resolver_list\")")
	public static AndroidElement phncomplt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Gaana\")")
	public static AndroidElement gaananotification;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/text1\").text(\"Phone\")")
	public static AndroidElement phntxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/button_always\").text(\"ALWAYS\")")
	public static AndroidElement phnalways;
	//--------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").instance(0).text(\"ON\")")
	public static AndroidElement mobileDataOption;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement switch_OffState;
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@text='OFF']")
	public static AndroidElement switch_OffStateByXpath;
	
	@AndroidFindBy(xpath="//*[@text='IDCSONWAP']/..//*[@text='Connected']")
	public static AndroidElement wifi_ConnectedState;
	
	@AndroidFindBy(uiAutomator = "new uiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\").textContains(\"Connected\")")
	public static AndroidElement wifi_ConnectedStateByUiAutomator;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IDCSONWAP\")")
	public static AndroidElement Wifi_IDC_UiSelector;
	
	@AndroidFindBy(id="com.android.settings:id/forget_button")
	public static AndroidElement wifi_IDC_ForgetBtn_By_ID;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"FORGET\")")
	public static AndroidElement wifi_IDC_ForgetBtn_By_UiSelector;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.settings:id/forget_button\")")
	public static AndroidElement wifi_IDC_ForgetBtn_By_UiSelectorId;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").textContains(\"ACCEPT\")")
	public static AndroidElement ACCEPTCONTINUE; //This Locator is in Chrome Breowser.

	@AndroidFindBy(id ="terms_accept")
	public static AndroidElement ACCEPTCONTINUEByID;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"ACCEPT & CONTINUE\")")
	public static AndroidElement ACCEPTCONTINUEByText;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"ACCEPT & CONTINUE\")")
	public static AndroidElement ACCEPTCONTINUEByResourceId;	
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='ACCEPT & CONTINUE']")
	public static AndroidElement ACCEPTCONTINUEByXpath;	
	
	@AndroidFindBy(id="password")
	public static AndroidElement wifiPasswordTextBoxById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.EditText\").index(\"1\")")
	public static AndroidElement wifiPasswordTextBoxByIndex;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@id='password']")
	public static AndroidElement wifiPasswordTextBoxByXpath;
	
	@AndroidFindBy(id="button1")
	public static AndroidElement connectById;
	
	@AndroidFindBy(uiAutomator = "new UiSelector.className(\"android.widget.Button\").textContains(\"CONNECT\")")
	public static AndroidElement connectByContains;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='CONNECT']")
	public static AndroidElement connectByXpath;
	

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement switch_OnState;

	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.android.settings:id/password\")")
	public static AndroidElement wifiPasswordTextBox;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CONNECT\")")
	public static AndroidElement connect;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='FORGET']")
	public static AndroidElement wifi_IDC_ForgetBtn;
	
	//------------------------------------------------------------------------------------------------------//
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chrome\")")
	public static AndroidElement chrome;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/terms_accept\")")
	public static AndroidElement chromeaccept;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/next_button\")")
	public static AndroidElement chromenext;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").resourceId(\"com.android.chrome:id/negative_button\")")
	public static AndroidElement chromenothanks;
	
	@AndroidFindBy(xpath ="//android.widget.Image[@text='Google']")
	public static AndroidElement chromegoogle;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CLEAR ALL\")")
	public static AndroidElement clearall;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.caf.fmradio:id/prog_frequency_tv\")")
	public static AndroidElement fmfrequency;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.caf.fmradio:id/btn_onoff\")")
	public static AndroidElement fmon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.caf.fmradio:id/frequency\")")
	public static AndroidElement fmnotification;
	
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement airplanemodeCheckboxoff;
	
	@AndroidFindBy(xpath = "//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@resource-id='android:id/switch_widget']/../..//android.widget.Switch[@text='ON']")
	public static AndroidElement airplanemodeCheckboxon;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ListView\").resourceId(\"com.android.dialer:id/dialpadChooser\")")
	public static AndroidElement dialpadchooser;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Skype\")")
	public static AndroidElement skype;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Let's go\")")
	public static AndroidElement skypeletsgo;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sign in or create\")")
	public static AndroidElement skypesignin;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"i0116\")")
	public static AndroidElement skypeedittxt;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.FrameLayout\").resourceId(\"android:id/content\")")
	public static AndroidElement skypecntframe;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Next\")")
	public static AndroidElement skypenext;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.EditText\").resourceId(\"i0118\")")
	public static AndroidElement skypepassword;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Sign in\")")
	public static AndroidElement skypesign;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Continue\")")
	public static AndroidElement skypecontinue;
	
	//android.widget.Button[@content-desc="New conversation"]
	//@AndroidFindBy(xpath ="//android.widget.Button[@content-desc='New Conversation']")
	//@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").description(\"New Conversation\")")
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"\")")
	public static AndroidElement skypestart;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"People, groups & messages\")")
	public static AndroidElement skypeNEWCHT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Automation\")")
	public static AndroidElement skypecontact;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Say hi\")")
	public static AndroidElement skypesayhi;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='0']/../..//android.widget.ImageView[@index='0']")
	public static AndroidElement skypetouch;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Type a message\")")
	public static AndroidElement skypetypemsg;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Hi\")")
	public static AndroidElement skypemsg;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contacts\")")
	public static AndroidElement skypehomecontact;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Calls\")")
	public static AndroidElement skypehomecall;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Chats\")")
	public static AndroidElement skypehomechats;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"All\")")
	public static AndroidElement skypeall;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='0']/../..//android.view.ViewGroup[@index='1']")
	public static AndroidElement photofirstpic;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.apps.photos:id/share_button\")")
	public static AndroidElement share;
	
	//@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Send\")")
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Sonim Automation']/../..//android.widget.TextView[@text='Send']")
	//@AndroidFindBy(xpath = "//android.view.ViewGroup[@index='1']/../..//android.widget.TextView[@text='Send']")
	public static AndroidElement skypesend;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Done\")")
	public static AndroidElement skypedone;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").description(\"Forward\")")
	public static AndroidElement skypeforward;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").description(\"Send message\")")
	public static AndroidElement skypesendmsg;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"File Manager\")")
	public static AndroidElement filemanager;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").description(\"Audio Call\")")
	public static AndroidElement skypeaudiocall;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Call\")")
	public static AndroidElement skypecall;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").description(\"End Call\")")
	public static AndroidElement skypeaudioendcall;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Not Now\")")
	public static AndroidElement skypenotnow;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"YouTube\")")
	public static AndroidElement youtube;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.google.android.youtube:id/thumbnail_layout\")")
	public static AndroidElement youtubeivideo;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Share\")")
	public static AndroidElement youtubeshare;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Copy link\")")
	public static AndroidElement youtubecopylink;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Copied\")")
	public static AndroidElement youtubedcopied;
	
	//@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"com.android.chrome:id/menu_button\").description(\"More options\")")
	@AndroidFindBy(xpath="//*[@content-desc='Update available. More options' or @content-desc='More options']")
	public static AndroidElement chromemenu;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Share…\")")
	public static AndroidElement chromeshare;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement chromemsg;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.ImageButton\").resourceId(\"com.android.soundrecorder:id/listButton\")")
	public static AndroidElement soundrecorderlist;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.soundrecorder:id/list_item_title\")")
	public static AndroidElement soundrecorderlisttitle;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"1 selected\")")
	public static AndroidElement soundrecorder1selected;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Select all\")")
	public static AndroidElement soundrecorderselectall;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.android.soundrecorder:id/action_delete\")")
	public static AndroidElement soundrecorderdelete;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"DELETE\")")
	public static AndroidElement soundrecorderdeletetxt;

	@AndroidFindBy(uiAutomator = "UiSelector().description(\"More options\")")
	public static AndroidElement msgnotification ;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement msgsettings;
	
	@AndroidFindBy(uiAutomator="new UiSelector().resourceId(\"android:id/checkbox\")")
	public static AndroidElement msgcheckbox;
	
	@AndroidFindBy(xpath = "//android.widget.LinearLayout[@index='1']/../..//android.widget.Switch[@text='OFF']")
	public static AndroidElement vibratecheckbox;
	
	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Restore default settings\")")
	public static AndroidElement msgrestoresettings;
	
	
}
