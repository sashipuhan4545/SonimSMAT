package com.xp5S.util;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_FM {
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_FM(AndroidDriver<AndroidElement> aDriver) {
		Locators_FM.aDriver=aDriver;
	}
	
	 @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	 public static WebElement recentApp;	
	 
	 @FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Radio On')]")
	 public static WebElement OffFMImsg;
	 
	 @FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'FM Off')]")
	 public static WebElement Offmsg_Txt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'FM Radio')]")
	 public static WebElement FMRadioTxt;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Loud Speaker')]")
	 public static WebElement FMRadioLoudSpeaker;
	
	@AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='My Notifications']")
	public static WebElement SG_MyNotification;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Wired Headset')]")
	 public static WebElement FMRadioWiredHeadset;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/android.widget.ImageButton[@index='0']"))
	 public static WebElement MenuIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/record_msg_tv')]")
	 public static WebElement recordMsgIcon;
	 
	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	 public static WebElement DismissIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_onoff')]")
	 public static WebElement OnOFFBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='2']"))
	 public static WebElement MuteBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.ImageView[@index='3']"))
	 public static WebElement MonoStereoOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	 public static WebElement PresetBtn1;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/name')]")
	 public static WebElement EditedChannelTxt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TableRow[@index='0']/android.widget.TextView[@index='1']"))
	 public static WebElement Remane_channel;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Add to presets')]")
	 public static WebElement AddPersets;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Presets')]")
	 public static WebElement Persets;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Remove from presets')]")
	 public static WebElement Remove_Persets;
	
		@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Rename')]")
	 public static WebElement Rename_Persets;
		
		@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.caf.fmradio:id/name')]")
		 public static WebElement EditedRenameChannelTxt;
		
		
//	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.ImageView[@index='2']"))
//	 public static WebElement ForwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_forward')]")
	 public static WebElement ForwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/prog_frequency_tv')]")
	 public static WebElement ChannelTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.caf.fmradio:id/freq')]")
	 public static WebElement ScanedsChannelTxt;
	
//	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_back')]")
//	 public static WebElement BackwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.caf.fmradio:id/btn_back')]")
	 public static WebElement BackwrdBtn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Settings')]")
	 public static WebElement SettgOptn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Start Recording')]")
	 public static WebElement StartRecOptn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Stop Recording')]")
	 public static WebElement StopRecOptn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Auto Scan')]")
	 public static WebElement AutoScanOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Scan')]")
	 public static WebElement ScanOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'All Stations')]")
	 public static WebElement AllStationsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Record Duration')]")
	 public static WebElement RecDurtnOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'45 minutes')]")
	 public static WebElement FortyFiveminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'15 minutes')]")
	 public static WebElement FifteenminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'30 minutes')]")
	 public static WebElement ThirtyminsOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'1 Hour')]")
	 public static WebElement onehrOptn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='6']/android.widget.RelativeLayout[@index='0']/android.widget.TextView[@index='1']"))
	 public static WebElement SummryBtn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Sleep')]")
	 public static WebElement SleepOptn;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Cancel Sleep')]")
	 public static WebElement CancelSleepOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'15 minutes')]")
	 public static WebElement fifteenminsSleepOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'candroid:id/alertTitle')]")
	 public static WebElement AlertTitle;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'STOP')]")
	 public static WebElement StopOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Regional band')]")
	 public static WebElement RegionalBandOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'North America (87.5MHz To 108.0MHz In 200 Khz Steps)')]")
	 public static WebElement DefaultRegnlBnd;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Stereo')]")
	 public static WebElement SteroTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Auto Select Enabled')]")
	 public static WebElement AutoSelectTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'5 minutes')]")
	 public static WebElement fiveminxTxt;
	
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Replace')]")
	 public static WebElement ReplaceOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Rename')]")
	 public static WebElement RenameOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.caf.fmradio:id/name')]")
	 public static WebElement EditName;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Tune')]")
	  public static WebElement TuneBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'OK')]")
	 public static WebElement OKOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@resource-id,'com.caf.fmradio:id/save')]")
	 public static WebElement OK_RenameOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Current favourite list will be cleared!')]")
	 public static WebElement AlertMsg;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Delete')]")
	 public static WebElement DeleteOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'Europe')]")
	 public static WebElement EuropeOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Audio Output Mode')]")
	 public static WebElement AudioOutMode;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Alternate Frequency')]")
	 public static WebElement AlternateFreqOpt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.ListView[@index='0']/android.widget.CheckedTextView[@index='1']"))
	 public static WebElement MonoAudioOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Revert to Factory Defaults')]")
	 public static WebElement FactryResetOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.systemui:id/title')]")
	 public static WebElement FMNotifctn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'YES')]")
	 public static WebElement YesOpt;
	
	@FindBy(how=How.XPATH, using ="//com.android.internal.widget.AccessibleTextView[contains(@text,'Radio Off')]")
	 public static WebElement RadioOff_Optn;
	
}
