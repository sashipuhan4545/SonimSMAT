package com.xp5S.util;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;



public class Locators_Safeguard{
	
	private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Safeguard(AndroidDriver<AndroidElement> aDriver) {
		  this.aDriver=aDriver;
		  
		 }
	
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	 public static WebElement recentApp;	
	
	
	
	
	
	
	//SafeGuard locators
	
	
	@AndroidFindBy( id = "com.sonim.safeguard:id/editText")
    public static WebElement SG_Locked_Screen_Entered_Pin;
	
	@AndroidFindBy( xpath = "//android.widget.TextView[@text='SafeGuard']")
    public static WebElement SG_String;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Setup']")
	public static WebElement SG_SetUp_String;
	
	@AndroidFindBy(id="com.sonim.safeguard:id/switch_widget")
	public static WebElement SG_toggle_btn;
	
	@AndroidFindBy(id="com.sonim.safeguard:id/dialog_edit_text")
	public static WebElement SG_Enter_Pin;
	


	@AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
	public static WebElement SG_OK_Btn_for_Pin;
	
	
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	  public static WebElement SG_recentApp;
	
	

	@AndroidFindBy(id="com.sonim.safeguard:id/tv_help_description")
	  public static WebElement SG_Help_text;
	
	@AndroidFindBy(id="android:id/summary")
	 public static WebElement SG_Version_summary_text;
	
     
     @AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
 	public static WebElement SG_Version;
     
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
  	public static WebElement SG_Help;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='SafeGuard']")
  	public static WebElement SG_Safe_Guard_String;
    
    
    
    //Locators for Applications
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Contacts']")
  	public static WebElement SG_Contact_Text;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
  	public static WebElement SG_Find_Contacts;
    
    
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Applications']")
  	public static WebElement SG_Applications_String;
    
    @AndroidFindAll(@AndroidFindBy(xpath="//android.widget.RelativeLayout"))
  	public static List<WebElement> SG_All_Apps_List;
    
    @AndroidFindBy(id="android:id/list")
  	public static WebElement SG_All_Apps_ListView;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select All']")
  	public static WebElement SG_Select_All_Btn;
    

    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Save']")
  	public static WebElement SG_Save_Btn;
    
    @AndroidFindBy(xpath="//android:id/search_src_text")
  	public static WebElement SG_Scout;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Emergency number']")
  	public static WebElement SG_Emergency_No;
    

    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Enter PIN']")
  	public static WebElement SG_Locked_Screen_Validation;
    
    @AndroidFindBy(id="com.sonim.safeguard:id/editText")
  	public static WebElement SG_Safe_Guard_Locked_Screen_Edit_Box;
    
    
    @AndroidFindBy(id="com.sonim.safeguard:id/editText")
  	public static WebElement SG_Locked_Screen_Edit_Box;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Unselect All']")
  	public static WebElement SG_Un_Select_All_Btn;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Safe Guard']")
  	public static WebElement SG_Lock_icon_Text;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='OK']")
  	public static WebElement SG_Lock_Screen_OK_Btn;
    
    @AndroidFindBy(id="android:id/alertTitle")
  	public static WebElement SG_Bluetooth_Error_Msg;
    
    
    
    
    
    //Locators used for all device apps
    
    @AndroidFindBy(id="com.caf.fmradio:id/nav_circle")
  	public static WebElement SG_FM;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Messaging']")
  	public static WebElement SG_Message;
    
    @AndroidFindBy(id="com.borqs.camera:id/camera")
  	public static WebElement SG_Camera;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Sound Recorder']")
  	public static WebElement SG_Sound_Recoder;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='SonimSetupWizard']")
  	public static WebElement SG_SetUpwizard;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
  	public static WebElement SG_Contact;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Recent calls']")
  	public static WebElement SG_RecentCalls;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Please insert Storage card before taking backup']")
  	public static WebElement SG_BackUp_restore;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim BLE Connect']")
  	public static WebElement SG_BLE_Connect;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Albums']")
  	public static WebElement SG_Album;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
  	public static WebElement SG_Security;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='More']")
  	public static WebElement SG_More;
    

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Tethering & portable hotspot']")
  	public static WebElement SG_Teathering;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='My Notifications']")
  	public static WebElement SG_MyNotification;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Wi-Fi']")
   	public static WebElement SG_WIFI;
     
    @AndroidFindBy(xpath="//android.widget.EditText[@resource-id='android:id/search_src_text']")
  	public static WebElement SG_search;
    
    @FindBy(id= "android:id/content")
    public static List<WebElement> notificationPanel;
    
    @AndroidFindBy(id="com.android.calculator2:id/simple_pad_circle")
  	public static WebElement SG_Calc;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Agenda']")
  	public static WebElement SG_Calender;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Downloads']")
  	public static WebElement SG_Downlaod;
    
    @AndroidFindBy(className="android.widget.FrameLayout")
  	public static AndroidElement SG_scroll;
    
    
    
    
    
    
    
    
   



    
    
    
    
    
    
    
    
    
    //Locators for Features
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Features']")
  	public static WebElement SG_Features_String;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Call settings']")
  	public static WebElement SG_Call_Settings;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Call blocking']")
  	public static WebElement SG_Call_Blocking;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Location']")
  	public static WebElement SG_Location;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Location consent']")
  	public static WebElement SG_Location_consent;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='AGREE']")
  	public static WebElement SG_Location_Agree;
    
    @AndroidFindBy(id="com.android.settings:id/switch_widget")
	public static WebElement SG_Location_Off_On_Btn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
  	public static WebElement SG_Add_Contact_String;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='ADD CONTACT']")
  	public static WebElement SG_ADD_CONTACT_String;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Find contacts']")
  	public static WebElement SG_Find_Contact_In_Search_Box;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Blocked numbers']")
  	public static WebElement SG_Blocked_Number;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Blocked numbers']")
  	public static WebElement SG_Blocked_NumberDailer;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[contains(text(),'Block number')]")
  	public static WebElement SG_Block_Number;
    

    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Me']")
  	public static WebElement SG_ME;
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Edit']")
  	public static WebElement SG_Contact_EDit;
    
    @AndroidFindBy(id="com.android.dialer:id/dialer_digits")
  	public static WebElement SG_dialer_digit;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Please input number or name']")
  	public static WebElement SG_Please_input_number;
    
    @AndroidFindBy(id="com.android.dialer:id/primary_action_view")
  	public static WebElement SG_dialer_History_List;
    
    @AndroidFindBy(id="android:id/button1")
  	public static WebElement SG_clear_button;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Clear all call log']")
  	public static WebElement SG_Clear_Call_Log;
    
    
    
    @AndroidFindBy(id="com.android.dialer:id/cliv_name_textview")
  	public static WebElement SG_Create_New_Contact;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth']")
  	public static WebElement SG_Bluetooth;
    

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Factory reset']")
  	public static WebElement SG_Factory_reset;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Security']")
  	public static WebElement SG_Settings;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add new contact']")
  	public static WebElement SG_Add_new_Contact;
    
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Use USB to']")
  	public static WebElement SG_Use_USB_to;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
  	public static WebElement SG_Native_Settings;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='USB for file transfer']")
  	public static WebElement SG_Use_USB_toIcon;
   
    
    @FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']"))
    public static WebElement SG_USB;
   
    
    
    
    
    
    
    
    //Locators for Change PIN
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Change PIN']")
  	public static WebElement SG_Change_Pin_String;
    
    @AndroidFindBy(id="com.sonim.safeguard:id/old_pinET")
  	public static WebElement SG_Old_Pin;
    
    @AndroidFindBy(id="com.sonim.safeguard:id/new_pinET")
  	public static WebElement SG_New_Pin;
    
    @AndroidFindBy(id="com.sonim.safeguard:id/reenter_pinET")
  	public static WebElement SG_Reenter_Pin;
    
    @AndroidFindBy(id="com.sonim.safeguard:id/okButton")
  	public static WebElement SG_OK_Btn_For_Change_Pin;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Activation']")
  	public static WebElement SG_Activation_Text;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Input PIN']")
  	public static WebElement SG_Input_PIN;
    
    
    
    
    
    
    
    //Locators for Application Pintimeout
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Application PIN Timeout']")
  	public static WebElement SG_App_Pin_TimeOut_String;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='30 Seconds']")
  	public static WebElement SG_App_Pin_TimeOut_30_Sec;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='1 Minute']")
  	public static WebElement SG_App_Pin_TimeOut_1_Min;
    
    
    
    //Locators for Version
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Version']")
  	public static WebElement SG_Version_String;
    
    
    //Locators for Help
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Help']")
  	public static WebElement SG_Help_String;
    
    
     
     
	
	
	
	
	
	
	
	
	@AndroidFindBy(id = "com.android.contacts:id/floating_action_button")
    public static WebElement addContactFloatingIcon;
	
	@AndroidFindBy(id = "com.android.contacts:id/text")
    public static WebElement newContactWillbeSaveToPhoneBook;
	
	@AndroidFindBy(id = "com.android.contacts:id/right_button")
    public static WebElement OKBTN;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static WebElement contactName;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_save")
	public static WebElement contactname;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_save")
	 public static WebElement ok;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SIM Card']")
	public static WebElement simCartd;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static WebElement name;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='More fields']")
	public static WebElement moreFiled;
	
	@AndroidFindBy(id = "com.android.contacts:id/menu_search")
	public static WebElement moreoption;
	
	@AndroidFindBy(id = "com.android.contacts:id/search_view")
	public static WebElement searchContact;
	
	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Sashi\")") 
	public static WebElement scrolling;
	
	
	@AndroidFindBy(xpath="//android.widget.ListView[contains(@resource-id,'android:id/list') and @index='2']")
	public static List<WebElement> listView ;
	
	
	@AndroidFindBy(className="android.widget.RelativeLayout")
	public static List<WebElement> relative;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='WiFi Restriction']")
	public static WebElement SG_Wifi_Restriction;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonim.safeguard:id/app_name\").text(\"Phone\")")
	public static WebElement SG_Phone;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}