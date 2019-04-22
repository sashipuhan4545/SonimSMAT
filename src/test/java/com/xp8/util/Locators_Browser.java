package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Browser {

	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Browser(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ACCEPT & CONTINUE')]")
	public static WebElement AccptBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='1']/android.widget.ImageView[@index='2']"))
	public static WebElement DismissIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Chrome')]")
	public static WebElement BrowserIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NEXT')]")
	public static WebElement NextIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'NO THANKS')]")
	public static WebElement NothanksBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/url_bar')]")
	public static WebElement DefaultUrlTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/url_bar')]")
	public static WebElement Urlbar;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.chrome:id/search_box_text')]")
	public static WebElement UrlbarNewTb;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Bookmark this page']"))
	public static WebElement BookmarkIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Edit bookmark']"))
	public static WebElement BookmarkEditIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'android:id/summary')]")
	public static WebElement summaryTextSearch;

	//After tapping on Url tab



	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.view.View[@index='0']"))
	public static WebElement TimesofInd;

	//    @FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='2']/android.widget.ImageButton[@index='0']"))
	//    public static WebElement Tabs;

	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.chrome:id/tab_switcher_button')]")
	public static WebElement TabBtn;

	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.chrome:id/menu_button')]")
	public static WebElement MenuChrome;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'New tab')]")
	public static WebElement NewTabOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'New incognito tab')]")
	public static WebElement NewIncgTabOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='2 open tabs']"))
	public static WebElement TwoTabopen;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'History')]")
	public static WebElement HistoryOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Bookmarks')]")
	public static WebElement BookmarksOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Desktop site')]")
	public static WebElement RqstDsktpOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Add to Home screen')]")
	public static WebElement AddToHomeScrnOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Find in page')]")
	public static WebElement FindInPageOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recent tabs')]")
	public static WebElement RecentTabsOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Settings')]")
	public static WebElement SettgsOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.ImageButton[@index='4']"))
	public static WebElement RefrshIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Close all tabs')]")
	public static WebElement CloseAllOptn;


	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Close incognito tabs')]")
	public static WebElement CloseAllIncgOptn;

	//History page
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Home - YouTube')]")
	public static WebElement YoutubeHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in')]")
	public static WebElement AmazonHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Electronics, Cars, Fashion, Collectibles, Coupons and More | eBay')]")
	public static WebElement EbayHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'m.facebook.com')]")
	public static WebElement FBHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'CLEAR')]")
	public static WebElement HistryClear;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.chrome:id/close_menu_id')]")
	public static WebElement HistryCancelIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Go back']"))
	public static WebElement GobackIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'No history here')]")
	public static WebElement NoHstryTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'mobile.twitter.com')]")
	public static WebElement TxtLnkHistry;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Copy link')]")
	public static WebElement CopyLinkOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Open in incognito tab')]")
	public static WebElement OpnIncgntOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageView[@index='4']"))
	public static WebElement HistryMenuIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'CLEAR BROWSING DATA…')]")
	public static WebElement ClearBrowsngDataOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@resource-id,'com.android.chrome:id/spinner')]")
	public static WebElement ClrDataDropdown;

	@FindBy(how=How.XPATH, using ="//android.widget.CheckedTextView[contains(@text,'All time')]")
	public static WebElement begningTimeOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'CLEAR DATA')]")
	public static WebElement ClrDataBtn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Search']"))
	public static WebElement HistrySearchIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Search your history')]")
	public static WebElement SearchField;



	//Incognito tab Page

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@resource-id,'com.android.chrome:id/refine_view_id')]")
	public static WebElement LinkCopyIcon;

	@FindBy(how=How.XPATH, using =("//android.widget.FrameLayout[@index='2']/android.widget.EditText[@index='0']"))
	public static WebElement IncogntUrl;

	//FB Page
	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.view.View[@index='3']"))
	public static WebElement ImageTxt;

	@FindBy(how=How.XPATH, using =("//android.view.View[@index='0']/android.widget.Image[@index='1']"))
	public static WebElement ImagePic;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Download image')]")
	public static WebElement DwnldImg;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'UPDATE PERMISSIONS')]")
	public static WebElement UpdtePermssnTxt;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ALLOW')]")
	public static WebElement AllwBtn;

	//Settings page
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.settings:id/search')]")
	public static WebElement SearchSettgIcn;

	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Search…')]")
	public static WebElement SearchsettgField;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Languages')]")
	public static WebElement SelectLanguages;

	@FindBy(how=How.XPATH, using =("//android.support.v7.widget.RecyclerView[@index='2']/android.widget.LinearLayout[@index='0']"))
	public static WebElement Languageselect;


	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Add a language')]")
	public static WebElement AddLanguage;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Español (Estados Unidos)')]")
	public static WebElement SelectSpanish;

	@FindBy(how=How.XPATH, using ="//android.widget.CheckBox[contains(@text,'English (United States)')]")
	public static WebElement SelectEnglishUS;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='More options']"))
	public static WebElement IconMenuSettg;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Remove')]")
	public static WebElement RemoveOptn;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Remove']"))
	public static WebElement DeleteIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'OK')]")
	public static WebElement OkOptn;

	//Bookmarks page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The New York Times - Breaking News, World News & Multimedia')]")
	public static WebElement BookmarkNytimes;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Mobile bookmarks']")
	public static WebElement MobileBookmarks;

	@FindBy(how=How.XPATH, using =("//android.widget.TextView[@content-desc='Delete bookmarks']"))
	public static WebElement DeleteBookmarks;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Options']"))
	public static WebElement OptnBkmrkIcon;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Delete')]")
	public static WebElement DeleteBkmrkOptn;

	//Recent Tabs Page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.chrome:id/title')]")
	public static WebElement RecentTabsCNN;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Show full history')]")
	public static WebElement ShowFullHistory;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'On')]")
	public static WebElement wifiOn;

	@FindBy(how=How.XPATH, using =("//android.view.View[@content-desc='You are offline']"))
	public static WebElement offlineTxt;

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.ImageView[@index='0']"))
	public static WebElement enableWifi;

	//Find in page

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Close']"))
	public static WebElement CloseIcon;

	@FindBy(how=How.XPATH, using =("//android.view.View[@text='Did you mean:']"))
	public static WebElement TimesofIndiaBlogs;



	//Add to HomeScreen Popup

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ADD')]")
	public static WebElement AddOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'https://Times of India.com/ - Google Search')]")
	public static WebElement HomeScreenAdded;

	//Find in page 
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Find in page')]")
	public static WebElement FndPageUrlField;


	//Settings Page

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Site settings')]")
	public static WebElement SiteSettgs;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Cookies')]")
	public static WebElement CookiesOptn;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'ON')]")
	public static WebElement OnCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Blocked')]")
	public static WebElement BlockedCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'OFF')]")
	public static WebElement OffCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'OFF')]")
	public static WebElement HelpCookies;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Clear browsing data')]")
	public static WebElement ClrBrwsingData;

	//search engine

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Search engine')]")
	public static WebElement SearchEng;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Yahoo!')]")
	public static WebElement Yahooo;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Google')]")
	public static WebElement Google;

	@FindBy(how=How.XPATH, using =("//android.widget.ImageButton[@content-desc='Navigate up']"))
	public static WebElement NavigateUpSrchEng;

	@FindBy(how=How.XPATH, using ="//android.view.View[contains(@text,'Yahoo!')]")
	public static WebElement Yahooologo;

	// 
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Home page')]")
	public static WebElement Homepage;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'ON')]")
	public static WebElement OnHomepge;

	@FindBy(how=How.XPATH, using ="//android.widget.FrameLayout[contains(@resource-id,'com.android.chrome:id/compositor_view_holder')]")
	public static WebElement HompageScreen;

	@FindBy(how=How.XPATH, using ="//android.widget.Switch[contains(@text,'OFF')]")
	public static WebElement OffHomepge;

	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Open this page')]")
	public static WebElement OpnPage;

	@FindBy(how=How.XPATH, using =("//android.widget.EditText[@content-desc='Open this page']"))
	public static WebElement OpnPgeField;

	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'Save')]")
	public static WebElement SaveOptn;



}