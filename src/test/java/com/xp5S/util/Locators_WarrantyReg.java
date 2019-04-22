package com.xp5S.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_WarrantyReg {


	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_WarrantyReg(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;		  
	}
	//Added comment to test
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static WebElement recentApp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Registration\")")
	public static WebElement SWR_String_Name;

	/* 	new UiSelector().className(\"android.widget.TextView\").text(\"Support\") */
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement SWR_Support_Tab;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Contacting Server. Please wait…\")")
	public static WebElement SWR_Contacting_server_progress_dialog;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Not Registered\")")
	public static WebElement SWR_Not_Registered;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Registered User\")")
	public static WebElement SWR_Registered;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Device Information\")")
	public static WebElement SWR_String_Device_Information;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Register your device\")")
	public static WebElement SWR_String_Register_Your_Device;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Unable to fetch registration information from server\")")
	public static WebElement SWR_UnableTo_Fetch_RegistrationInforamtionFromServer;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"OK\")")
	public static WebElement SWR_OK_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/now\")")
	public static WebElement SWR_Now;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/submit\")")
	public static WebElement SWR_Submit;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.sonimtech.warrantyregapp:id/submit\").text(\"Remove all\")")
	public static WebElement SWR_Remove_All;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.systemui:id/app_thumbnail_image\")")
	public static WebElement SWR_RecentApp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please contact Sonim at support@sonimtech.com to register your handset with scanned copy of invoice\")")
	public static WebElement SWR_Please_contact_Sonim_At;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/autocomplete_country\")")
	public static WebElement SWR_Country;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"No internet Connection. Please check your connection settings and try again\")")
	public static WebElement SWR_No_internet_Connection;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter first name\")")
	public static WebElement SWR_please_enter_first_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter first name\")")
	public static WebElement SWR_Enter_First_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter last name\")")
	public static WebElement SWR_Please_Enter_Last_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter company name\")")
	public static WebElement SWR_Enter_Company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter company\")")
	public static WebElement SWR_Please_enter_Company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter last name\")")
	public static WebElement SWR_Enter_Last_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter address1\")")
	public static WebElement SWR_Please_enter_Address_1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter address 1\")")
	public static WebElement SWR_Enter_Address_1;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter city\")")
	public static WebElement SWR_Please_enter_City;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter city\")")
	public static WebElement SWR_enter_City;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter state\")")
	public static WebElement SWR_Please_Enter_State;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter state name\")")
	public static WebElement SWR_Enter_State_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter zipcode\")")
	public static WebElement SWR_Please_enter_zipcode;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter zip code\")")
	public static WebElement SWR_Enter_zip_Code;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter emailid\")")
	public static WebElement SWR_Please_enter_emailid;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter email id\")")
	public static WebElement SWR_Enter_Email_Id;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter phone number\")")
	public static WebElement SWR_Please_enter_phone_number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter valid emailid\")")
	public static WebElement SWR_Please_enter_Valid_id;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter phone number\")")
	public static WebElement SWR_Enter_Phone_Number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please enter purchase date\")")
	public static WebElement SWR_Please_Enter_purchase_date;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.sonimtech.warrantyregapp:id/purchaseddate\")")
	public static WebElement SWR_Purchased_On;	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(0)")
	public static WebElement SWR_Date;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(1)")
	public static WebElement SWR_Month;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.NumberPicker\").index(2)")
	public static WebElement SWR_Year;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"@\")")
	public static WebElement SWR_At_the_Rate_Symbol;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CHOOSE\")")
	public static WebElement SWR_ChooseBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Dealer Name\")")
	public static WebElement SWR_DelearName;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Take a Picture\")")
	public static WebElement SWR_Take_a_Pic;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static WebElement SWR_PhotoCapture;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Reset\")")
	public static WebElement SWR_RstBtn;

	//=======================================================================================================================================
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Support']")
	public static WebElement supportTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Registration\")")
	public static WebElement WarrantyReg;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"HOME\")")
	public static AndroidElement HomeBtn_Wizard;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")
	public static AndroidElement url_Field;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Now\")")
	public static AndroidElement Now_Button;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Later\")")
	public static AndroidElement Later_Button;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"Never\")")
	public static AndroidElement Never_Button;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Manual Registration\")")
	public static AndroidElement manual_reg_String;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Are you sure you want to stop Warranty Registration reminders?\")")
	public static AndroidElement never_Optn_Confirmation_Popup;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"YES\")")
	public static AndroidElement yes_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"NO\")")
	public static AndroidElement no_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim Care\")")
	public static AndroidElement sonimcare;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Device Information\")")
	public static AndroidElement deviceInfo;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Register your device\")")
	public static AndroidElement RegisterYourDeviceOptn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter first name\")")
	public static AndroidElement firstNameTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter last name\")")
	public static AndroidElement lastNameTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Country\")")
	public static AndroidElement countryTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Company\")")
	public static AndroidElement companyTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter State\")")
	public static AndroidElement stateTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter city\")")
	public static AndroidElement cityTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter zip code\")")
	public static AndroidElement zipcodeTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter address 1\")")
	public static AndroidElement address1TextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter address 2\")")
	public static AndroidElement address2TextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter phone number\")")
	public static AndroidElement phoneNumberTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter email id\")")
	public static AndroidElement email_idTextField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Select Date\")")
	public static AndroidElement selectPurchasedDateField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please complete all required fields and re-submit\")")
	public static AndroidElement mandatoryFieldPopUp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SUBMIT\")")
	public static AndroidElement submitBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"RESET\")")
	public static AndroidElement resetBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Are you sure, you want clear the form.\")")
	public static AndroidElement ResetConfirmationPopUp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"PERSONAL DETAILS\")")
	public static AndroidElement PersonalDetailsString;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Automation\")")
	public static AndroidElement firstnamefilled_Fld;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Upload Files\")")
	public static AndroidElement uploadFilesString;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.sonim.scout.warranty:id/choose_file_textview\")")
	public static AndroidElement uploadFilesOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Take a Picture\")")
	public static AndroidElement take_a_picture_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Add Image ?\")")
	public static AndroidElement addImagePopUp;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"invoice.jpg\")")
	public static AndroidElement imageAddedOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.borqs.camera:id/photo_capture\")")
	public static AndroidElement captureImageOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Choose a File\")")
	public static AndroidElement chooseAFileOption;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"picture.jpeg\")")
	public static AndroidElement jpegFile;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"picture.jpg\")")
	public static AndroidElement jpgFile;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"picture.png\")")
	public static AndroidElement pngFile;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"sample.doc\")")
	public static AndroidElement docFile;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"sample.pdf\")")
	public static AndroidElement pdfFile;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Please select the following filetypes\")")
	public static AndroidElement invalidFileTypeErrorMsg;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CHOOSE\")")
	public static AndroidElement choose_Btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").text(\"Enter Dealer Name\")")
	public static AndroidElement dealer_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Are you sure, you want to submit the form.\")")
	public static AndroidElement submit_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"User Information\")")
	public static AndroidElement UserInformationString;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/url_titleId_URL\")")
	public static AndroidElement app_version;

	@AndroidFindBy(xpath="//android.widget.ImageView[@content-desc='More options' and @index='0']")
	public static AndroidElement More_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"About\")")
	public static AndroidElement about_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/url_titleId_copy\")")
	public static AndroidElement copyright_option;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/rights\")")
	public static AndroidElement AllRightsReservedString;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/privacy_policy\")")
	public static AndroidElement sonimPrivacyPolicyString;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/url_link\")")
	public static AndroidElement PrivacyPolicylink;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Name\")")
	public static AndroidElement Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Automation Test\")")
	public static AndroidElement Registered_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Company Name\")")
	public static AndroidElement company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim\")")
	public static AndroidElement registered_company_Name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Phone Number\")")
	public static AndroidElement phone_number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"9876543210\")")
	public static AndroidElement registered_phone_number;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Email ID\")")
	public static AndroidElement email_id;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"automationtest@gmail.com\")")
	public static AndroidElement registered_email_id;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Address\")")
	public static AndroidElement address;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"JPNagar, Bangalore, Bangalore, Karnataka, India- 560078\")")
	public static AndroidElement registered_address;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"IMEI\")")
	public static AndroidElement IMEI;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/device_user_id\")")
	public static AndroidElement registered_IMEI;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Model No.\")")
	public static AndroidElement Model_No;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"XP5800\")")
	public static AndroidElement registered_Model_No;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Dealer Name\")")
	public static AndroidElement dealer_name_reg;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Operator\")")
	public static AndroidElement registered_dealer_name;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Activated On\")")
	public static AndroidElement Activated_on;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/activate_time_id\")")
	public static AndroidElement registered_Activated_on;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Purchased On\")")
	public static AndroidElement Purchased_On;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/purchase_time_id\")")
	public static AndroidElement registered_Purchased_on;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Warranty Ends On\")")
	public static AndroidElement warranty_ends_On;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").resourceId(\"com.sonim.scout.warranty:id/expire_time_id\")")
	public static AndroidElement registered_warranty_ends_On;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.ImageView\").resourceId(\"com.android.packageinstaller:id/permission_icon\")")
	public static AndroidElement permission_pop_up;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ALLOW\")")
	public static AndroidElement allow_btn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkAndInternet;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile network']")
	public static AndroidElement MobileNetwrks_oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement enabled_Data_Oreo;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Mobile data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement disabled_Data_Oreo;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Data usage']")
	public static AndroidElement dataUsageOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='ON']"))
	public static AndroidElement cellular_DataON;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@text='Cellular data']/../..//android.widget.Switch[@text='OFF']"))
	public static AndroidElement cellular_DataOFF;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
	public static AndroidElement enabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
	public static AndroidElement disabled;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").resourceId(\"com.android.settings:id/switch_widget\")")
	public static AndroidElement switch_Title;

	@AndroidFindBy(xpath="//android.widget.Button[@text='CONNECT']")
	public static AndroidElement wifi_IDC_ConnectBtn;

	@AndroidFindBy(xpath="//android.widget.EditText[@resource-id='com.android.settings:id/password']")
	public static AndroidElement wifi_IDC_Psswd;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"FORGET\")")
	public static AndroidElement wifi_IDC_ForgetBtn;

	@AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='com.android.settings:id/show_password']")
	public static AndroidElement wifi_show_Pswd;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='IDCSONWAP']")
	public static AndroidElement wifi_IDC;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/../..//android.widget.TextView[@resource-id='android:id/summary']"))
	public static AndroidElement summaryWIFI_oreo;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Apps & notifications\")")
	public static AndroidElement Apps_Notification;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Apps info\")")
	public static AndroidElement Apps_info;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.TextView\").text(\"Storage\")")
	public static AndroidElement storage;

	@AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.Button\").text(\"CLEAR DATA\")")
	public static AndroidElement cleardata;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select\")")
	public static AndroidElement select_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Select all\")")
	public static AndroidElement Select_all_option;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Delete\")")
	public static AndroidElement delete_option;
	
	
	
	
	
}
