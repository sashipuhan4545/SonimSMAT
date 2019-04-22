package com.xp5S.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_ContactTransfer {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_ContactTransfer(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	//Added comment to test
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static WebElement recentApp1;	

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sonim Scout']")
	public static WebElement sonim_scout_AppLauncher;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contact Transfer']")
	public static WebElement ContactTransferTitle;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Utilities']")
	public static WebElement utilitiesTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/bluetooth_rb\")")
	public static WebElement BtRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/mdb_rb\")")
	public static WebElement MDBRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/vcf_rb\")")
	public static WebElement VCFRadioBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.RadioButton\").resourceId(\"com.sonimtech.easycontactsshare:id/mdb_rb\")")
	public static WebElement CSVRadioBtn;

	@AndroidFindBy(xpath ="//android.widget.ImageView[@content-desc='Dismiss Sonim Warranty Registration.']")
	public static WebElement DismissIconWarranty;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
	public static WebElement CT_MoreOptns;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='About']")
	public static WebElement AbtContactTransfer;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='android:id/message']")
	public static WebElement About_CT_Text;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public static WebElement OKBtn_Abt;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.android.packageinstaller:id/permission_allow_button']")
	public static WebElement AllowBtn;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
	public static WebElement Allow_BT_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via Bluetooth\")")
	public static WebElement BluetoothOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via MDB\")")
	public static WebElement MDBOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via VCF\")")
	public static WebElement VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Via CSV\")")
	public static WebElement CSVOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Download']")
	public static WebElement DownloadOptn;

	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id='com.sonimtech.easycontactsshare:id/btn_ok']")
	public static WebElement OKBtn;

	@AndroidFindBy(xpath ="//android.widget.CheckBox[@resource-id='com.sonim.scout.contact:id/select_all_check']")
	public static WebElement selectAllOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.TextView[@index='0']"))
	public static WebElement ImprtSummry_Text;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='DONE']")
	public static WebElement DoneOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='com.android.systemui:id/title']")
	public static WebElement NotifctnTitle;

	@AndroidFindBy(xpath ="//android.widget.Button[@text='CANCEL']")
	public static WebElement CancelBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btn_ok\")")
	public static WebElement search_Ok__Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/done_btn\")")
	public static WebElement Done_Btn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import MDB Contacts']")
	public static WebElement ImportMBDOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import VCF Contacts']")
	public static WebElement ImportVCFOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import CSV Contacts']")
	public static WebElement ImportCSVOptn;

	@AndroidFindBy(xpath ="//android.widget.TextView[@text='Import via Bluetooth']")
	public static WebElement ImportBTOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/search_src_text\")")
	public static WebElement searchField;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/view_parent\")")
	public static WebElement SearchResult;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.CheckBox[@index='2']"))
	public static WebElement CheckBox_Search;

	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.TextView[@index='2']"))
	public static WebElement UFMI_Search;


	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectMdbFile\")")
	public static WebElement Title_MBDOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectVcfFile\")")
	public static WebElement Title_VCFOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnSelectCsvFile\")")
	public static WebElement Title_CSVOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.easycontactsshare:id/btnBtImportContacts\")")
	public static WebElement Title_BTOptn;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static WebElement recentApp;

	//========================================================================================================================================

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Messaging\")")
	public static AndroidElement messaging_Title;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Imported contacts: 253/253\")")
	public static AndroidElement imported_contacts_text;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Connected devices\")")
	public static AndroidElement connectedDevices_oreo;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Bluetooth']")
	public static AndroidElement Bluetooth;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contact Transfer wants to turn on Bluetooth\")")
	public static AndroidElement enable_BT_popup;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Searching…\")")
	public static AndroidElement searchingBT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Scan\")")
	public static AndroidElement scan_Button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"DENY\")")
	public static AndroidElement deny_Button;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Bluetooth will need to be enabled to use the BT Transfer feature.\")")
	public static AndroidElement deny_popup;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"com.sonim.scout.contact:id/search_src_text\")")
	public static AndroidElement search_field;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"SouthernLINC\")")
	public static AndroidElement mdbContact;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"My Notifications\")")
	public static AndroidElement myNotification;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select\")")
	public static AndroidElement select_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select all\")")
	public static AndroidElement Select_all_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static AndroidElement delete_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement OK_Btn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK(5)\")")
	public static AndroidElement selectAll_checkbox;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;

}
