package com.xp8.util;
import org.openqa.selenium.WebElement;



import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Data_Setting {
	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Data_Setting(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Settings\")")
	public static AndroidElement settings;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network & Internet\")")
	public static AndroidElement networkandinternetopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile network\")")
	public static AndroidElement Mobilenetworkopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Airplane mode\")")
	public static AndroidElement airplanemode;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\")")
	public static AndroidElement airplanemodeswitchonoff;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/switchWidget\")")
	public static AndroidElement wifiswitchonoff;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Mobile data\")")
	public static AndroidElement mobiledata;

//	
//	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\")")
//	public static WebElement mobileswitch;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"ON\")")
//	public static AndroidElement mobileswitchon;
//	
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Switch\").text(\"OFF\")")
//	public static AndroidElement mobileswitchoff;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Wi‑Fi\")")
	public static AndroidElement wifiopt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Advanced\")")
	public static AndroidElement Advancedopt;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Network settings\")")
	public static AndroidElement networksettingbar;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Enhanced 4G LTE services\")")
	public static AndroidElement lteservice;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Access Point Names\")")
	public static AndroidElement APNopt;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Edit access point\")")
	public static AndroidElement editapopt;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Vodafone connect\")")
	public static AndroidElement VodafoneConnect;
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static WebElement APNSettings;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.settings:id/apn_radiobutton\")")
	public static WebElement radiobtn;

	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='New APN']")
	public static WebElement NewAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APNs\")")
	public static AndroidElement apnsframe;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Vodafone connect\")")
	public static AndroidElement vodafoneconnect;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/button1\")")
	public static AndroidElement okbtn;
	//CANCEL
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"CANCEL\")")
	public static AndroidElement apncancelbtn;
	
	
	
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc='More options']")
	public static AndroidElement newAPNSettings;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Reset to default\")")
	public static AndroidElement resettodefaul;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Save\")")
	public static AndroidElement savebtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Discard\")")
	public static AndroidElement discardbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Name\")")
	public static AndroidElement APNname;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"The Name field can’t be empty.\")")
	public static AndroidElement errormsginname;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"The APN can’t be empty.\")")
	public static AndroidElement errormsginapn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN\")")
	public static AndroidElement APNno;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").resourceId(\"android:id/edit\")")
	public static AndroidElement APNinput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim\")")
	public static AndroidElement newapnnum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Sonim1\")")
	public static AndroidElement editapnnum;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Delete APN\")")
	public static AndroidElement deleteapnnum;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Proxy\")")
	public static AndroidElement proxyAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Port\")")
	public static AndroidElement portAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Username\")")
	public static AndroidElement usernameAPN;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Password\")")
	public static AndroidElement PasswordAPN;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Server\")")
	public static AndroidElement ServerAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMSC\")")
	public static AndroidElement MMSCAPN;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMS proxy\")")
	public static AndroidElement MMSproxyAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MMS port\")")
	public static AndroidElement MMSport;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MCC\")")
	public static AndroidElement MCCAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"MNC\")")
	public static AndroidElement MNCAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN protocol\")")
	public static AndroidElement APNprotool;
	

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN roaming protocol\")")
	public static AndroidElement APNroamingprotool;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4\")")
	public static AndroidElement ipv4r;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv6\")")
	public static AndroidElement ipv6r;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4/IPv6\")")
	public static AndroidElement ipv4oripv6r;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4\")")
	public static AndroidElement ipv4p;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv6\")")
	public static AndroidElement ipv6p;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"IPv4/IPv6\")")
	public static AndroidElement ipv4oripv6p;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/text1\")")
	public static AndroidElement protocolid;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"APN type\")")
	public static AndroidElement APNtypeAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Bearer\")")
	public static AndroidElement bearer;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Authentication type\")")
	public static AndroidElement AuthenticationtypeofAPN;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"None\")")
	public static AndroidElement NoneAPNAT;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"PAP\")")
	public static AndroidElement PAPAPNAT;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"CHAP\")")
	public static AndroidElement CHAPAPNAT;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.CheckedTextView\").text(\"PAP or CHAP\")")
	public static AndroidElement PAPorCHAPPNAT;

	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/search_box_text\")")
	public static AndroidElement googletextfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"com.android.chrome:id/url_bar\")")
	public static AndroidElement textfieldinchrome;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Login\")")
//	public static AndroidElement flipcartloginbtn;
//	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"No Internet \")")
	public static WebElement Nointernet;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"m_login_email\")")
	public static WebElement fbloginfield;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Image\").text(\"facebook\")")
	public static AndroidElement facebooktext;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"NO INTERNET\")")
	public static AndroidElement nointernet;
//	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").text(\"Could not load content\")")
//	public static AndroidElement couldnotload;
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/switch_widget\")")
	public static AndroidElement mobiledatarbtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"android:id/title\")")
	public static AndroidElement apnname;
	
}
