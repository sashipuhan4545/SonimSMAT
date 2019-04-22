package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Dailer {

private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_Dailer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Dismiss Phone.']"))
	public static WebElement DismissIcon;

//	 @FindBy(how=How.XPATH, using ="//android.widget.HorizontalScrollView[contains(@resource-id,'com.android.dialer:id/lists_pager_header')]")
//	 public static WebElement DailerPage;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.HorizontalScrollView\").resource-id(\"com.android.dialer:id/lists_pager_header\")")
	 public static WebElement DailerPage;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.TextView\").text(\"Search contacts\")")
	 public static WebElement searchField;
	 
	 @AndroidFindBy(uiAutomator= "new UiSelector().className(\"android.widget.FrameLayout\").resource-id(\"com.android.dialer:id/search_view_container\")")
	 public static WebElement searchFieldTab;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.dialer:id/search_view')]")
	 public static WebElement searchTxtEnter;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.TextViewt[contains(@text,'Use touch tone keypad)]")
	 public static WebElement TouchToneKeypad;
	 
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.contacts:id/floating_action_button')]")
	 public static WebElement addContactBtn;
	
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='dial pad']"))
	 public static WebElement dailerPad;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Speed dial']"))
	 public static WebElement speedDail;
	 
	 @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id,'ADD A FAVORITE']")
	 public static WebElement addFavorite;
	 
	 @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id,'MAKE A CALL']")
	 public static WebElement makeCall;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Call History']"))
	 public static WebElement callHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@resource-id='com.android.dialer:id/digits']"))
	 public static WebElement enterNumFiled;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Contacts']"))
	 public static WebElement contactsIcon;
	 
	 @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Create new contact']")
	 public static WebElement addContactsIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/empty_list_view']"))
	 public static WebElement UidaiContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.contacts:id/selection_menu']"))
	 public static WebElement Selection_menu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Select all']"))
	 public static WebElement ALL_Selection_menu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='ALL']"))
	 public static WebElement ALL_page;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='dial']"))
	 public static WebElement dailIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[[@resource-id='com.android.dialer:id/callStateLabel']"))
	 public static WebElement callStateLabel;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@resource-id='com.android.dialer:id/primary_action_button']"))
	 public static WebElement CallIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/name']"))
	 public static WebElement dailedNum;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='End Call']"))
	 public static WebElement endCallIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/contact_tile_name']"))
	 public static WebElement contactTitle;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.dialer:id/name']"))
	 public static WebElement contactNameTitle;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Create new contact']"))
	 public static WebElement createNewContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Add to a contact']"))
	 public static WebElement AddtoContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.contacts:id/right_button']"))
	 public static WebElement OkBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='OK']"))
	 public static WebElement OkBtnForClr;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Ok']"))
	 public static WebElement OkBtnFrExport;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@text='John']"))
	 public static WebElement NameFieldJohn;
	 
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	 public static WebElement NameField;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='android:id/text1']"))
	 public static WebElement text1;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.CheckedTextView[@text='Work']"))
	 public static WebElement Work;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ListView[@index='1']/android.widget.LinearLayout[@index='0']"))
	 public static WebElement phoneDefault;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Save']"))
	 public static WebElement saveIcn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Automation']"))
	 public static WebElement saveContactName;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='android:id/button1']"))
	 public static WebElement saveExport;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Edit']"))
	 public static WebElement editContactIcon;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	 public static WebElement MoreOptnsIcn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='More options']"))
	 public static WebElement MoreOptnCallHistry;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call History']"))
	 public static WebElement CallHistoryOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Settings']"))
	 public static WebElement settingsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Import/export']"))
	 public static WebElement ImptExprtOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='XP8800']"))
	 public static WebElement Xp8800Optn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Downloads']"))
	 public static WebElement DownloadsOptn;
 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Clear frequents']"))
	 public static WebElement clrFrquentsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Spinner[@resource-id='com.android.dialer:id/filter_status_spinner']"))
	 public static WebElement AllCallTypOptns;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Outgoing calls only']"))
	 public static WebElement outgoingCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Outgoing call']"))
	 public static WebElement outgoingCallTxt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='All calls']"))
	 public static WebElement AllCall;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Incoming calls only']"))
	 public static WebElement incomingCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Missed calls only']"))
	 public static WebElement missedCalls;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Search call log']"))
	 public static WebElement searchCallLogs;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.EditText[@text='Search call log']"))
	 public static WebElement editsearchCallLog;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Clear call history']"))
	 public static WebElement clearCallHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.contacts:id/call_log_list_item']"))
	 public static WebElement firstEntry;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/primary_action_view']"))
	 public static WebElement firstEntryCallHistory;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@resource-id='com.android.dialer:id/details_action']"))
	 public static WebElement callDetailsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call details']"))
	 public static WebElement CallDetailTxt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.contacts:id/selection_menu']"))
	 public static WebElement selectionMenu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.contacts:id/popup_list_title']"))
	 public static WebElement oneselectionMenu;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Select all']"))
	 public static WebElement selectAllList;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='CLEAR']"))
	 public static WebElement clearBtn;

	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='No call log']"))
	 public static WebElement callLogEmpty;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='ALLOW']"))
	 public static WebElement AllowOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']"))
	 public static WebElement AllowOptnImprt;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Block number']"))
	 public static WebElement blockNumOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Unblock number']"))
	 public static WebElement unblockNumOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='BLOCK']"))
	 public static WebElement blockBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='UNBLOCK']"))
	 public static WebElement unblockBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Call screening']"))
	 public static WebElement callScreeningOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Block black list']"))
	 public static WebElement blockList;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Export to .vcf file']"))
	 public static WebElement exportVcf;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Export to SIM card']"))
	 public static WebElement exportSim;
	 
//	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Downloads']"))
//	 public static WebElement downloadsOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='storage']"))
	 public static WebElement storageOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Download']"))
	 public static WebElement downloadOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Import from .vcf file']"))
	 public static WebElement importVcf;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Show roots']"))
	 public static WebElement menuStorageOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@resource-id='com.android.documentsui:id/nameplate']"))
	 public static WebElement nameVcfCard;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation1']"))
	 public static WebElement Automation1Contact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation2']"))
	 public static WebElement Automation2Contact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Automation1']"))
	 public static WebElement AutomationContact;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Delete']"))
	 public static WebElement deleteContactOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.Button[@text='DELETE']"))
	 public static WebElement deleteConfiemBtn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.ImageView[@content-desc='Unlock']"))
	 public static WebElement UnlockOptn;
	 
	 @FindBy(how=How.XPATH, using =("//android.widget.TextView[@resource-id='com.android.systemui:id/keyguard_carrier_text']"))
	 public static WebElement destinationDrop;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android.widget.EditText']")
	 public static WebElement Phone;
	 
	 @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='android:id/icon']")
	 public static WebElement PhoneIcon;
	 
	 @AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	 public static WebElement PhoneNumField;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']")
	 public static WebElement PhoneIconApp;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Return to call in progress']")
	 public static WebElement PhoneReturn_to_call;
	
}

