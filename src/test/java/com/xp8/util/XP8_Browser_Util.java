package com.xp8.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidKeyCode;



public class XP8_Browser_Util extends BaseUtil  {
	
	public boolean check = false;
	public SoftAssert softAssert;
	
	public void End() {
		aDriver.quit();
	}

	public void launchBrowser() throws InterruptedException {
		//launchApp("BROWSER_PACKAGE","BROWSER__ACTIVITY");
		minWait();
		Locators_Browser.BrowserIcon.click();
		APP_LOGS.info("Chrome Browser Launched succesfully. ");
		minWait();
	}
	

	public void launchSettings() throws InterruptedException {
		launchApp("SETTINGS_PACKAGE","SETTINGS_ACTIVITY");
		APP_LOGS.info("Settings Launched succesfully. ");
		minWait();
	}
	
	public void validateChromeBrowserLaunch() throws InterruptedException{
		try {
		if(isElementExist(Locators_Browser.AccptBtn)){
		Locators_Browser.AccptBtn.click();
		}
		customWait(4000);
		if(isElementExist(Locators_Browser.NextIcon)){
			Locators_Browser.NextIcon.click();
		}
			customWait(4000);
			if(isElementExist(Locators_Browser.NothanksBtn)){
				Locators_Browser.NothanksBtn.click();
			}
			customWait(4000);
		
		if(isElementExist(Locators_Browser.DefaultUrlTxt)){
			check = true;
			APP_LOGS.info(" Default Url AT&T. Chrome Browser Launch is sucessful.");
			Assert.assertTrue(check);
		}else{
			APP_LOGS.info("Default Url AT&T. Chrome Browser is not displayed.Chrome Browser Launch is unsuccesful.");
			Assert.fail();	
		}
		}
		catch (org.openqa.selenium.NoSuchElementException e) {
		e.printStackTrace();
		}
	}  
	 public void enterUrl(String newurl) throws InterruptedException {
		 try {
		 minWait();
		 clickBtn(Locators_Browser.DefaultUrlTxt);
		 enterTextToInputField(Locators_Browser.DefaultUrlTxt, newurl);
		 APP_LOGS.info(" URl is entered is sucessful.");
		 minWait();
		 minWait();	 
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		 minWait();
		 APP_LOGS.info(" Search click is sucessful."); 
		 customWait(4000);
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	 }
	 
	 
	 public void enterUrlNewTab() throws InterruptedException {
		 try {
		 minWait();
		 clickBtn(Locators_Browser.UrlbarNewTb);
		 minWait();
		 enterTextToInputField(Locators_Browser.Urlbar,"amazon.in");
		 APP_LOGS.info(" URl is entered is sucessful.");
		 minWait();
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		 minWait();
		 APP_LOGS.info(" Search click is sucessful.");
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	 }
	 
	 public void setSpanishLanguage() throws InterruptedException {
		 try {
			 launchSettings();minWait();
			 Locators_Browser.SearchSettgIcn.click();
			 minWait();
				enterTextToInputField(Locators_Browser.SearchsettgField, "Languages");
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
				minWait();
				Locators_Browser.SelectLanguages.click();
				minWait();
				Locators_Browser.SelectLanguages.click();
				minWait();
				Locators_Browser.AddLanguage.click();
				minWait();
				Locators_Browser.SelectSpanish.click();
				minWait();
				Locators_Browser.IconMenuSettg.click();
				minWait();
				Locators_Browser.RemoveOptn.click();
				minWait();
				Locators_Browser.SelectEnglishUS.click();
				minWait();
				Locators_Browser.DeleteIcon.click();
				minWait();
				Locators_Browser.OkOptn.click();
		 } 	
		catch (org.openqa.selenium.NoSuchElementException e) {
		e.printStackTrace();
		}
	 }
	 
	 public void setEngLanguage() throws InterruptedException {
		 try {
			 launchSettings();
			 minWait();
			 Locators_Browser.SearchSettgIcn.click();
			 minWait();
			 enterTextToInputField(Locators_Browser.SearchsettgField, "Languages");
			 minWait();
			 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
			 minWait();
			 Locators_Browser.SelectLanguages.click();
			 minWait();
			 Locators_Browser.AddLanguage.click();
			 minWait();
			 Locators_Browser.SelectSpanish.click();
			 minWait();
			 Locators_Browser.IconMenuSettg.click();
			 minWait();
			 Locators_Browser.RemoveOptn.click();
			 minWait();
			 Locators_Browser.SelectEnglishUS.click();
			 minWait();
			 Locators_Browser.DeleteIcon.click();
			 minWait();
			 Locators_Browser.OkOptn.click();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	 }
	 
	 
	 public void initialCall() throws InterruptedException, IOException {customWait(5000);
			Runtime.getRuntime().exec("adb -s c1edac9c shell am start -a android.intent.action.CALL -d tel:555-5555");
			customWait(5000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENDCALL);
		}
	 
	 public void validateCallInteraction() {		
			searchString("startService:1357","XP8_BRWSR_011");
		}
		 
	 
	 public void validateUrlEntered() throws InterruptedException
	 {
		 try {
			 minWait();
		  if(isElementExist(Locators_Browser.TimesofInd)){
			  check = true;
				APP_LOGS.info("Url entered sucessfully.");
				Assert.assertTrue(check);
			  } 
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	  public void refreshUrl() throws InterruptedException {
		  try {
			  customWait(5000);
			  minWait();
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.RefrshIcon.click();
		 APP_LOGS.info("Refresh Icon is Selected sucessfully");
		 }
		  catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	  }
	 
	  public void validaterefresh() throws InterruptedException {
			searchString("RemoteReportsRefreshChimeraServic","XP8_BRWSR_002");
			customWait(5000);
	  }
	  
	  public void selectIncognitotab() throws InterruptedException {
		  try {
				 aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")").click();
				 minWait();
				 Locators_Browser.MenuChrome.click();
				 minWait();
				 Locators_Browser.NewIncgTabOptn.click();
				 minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	  }
	  
	  public void selectHistoryPg() throws InterruptedException {
		  try {
		 customWait(5000);
		  Locators_Browser.MenuChrome.click();
				 APP_LOGS.info("Chrome Menu is Selected sucessfully");
				 minWait();
				 Locators_Browser.HistoryOptn.click();
				 APP_LOGS.info("History Option is Selected sucessfully");
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	  }
	  
	  public void validateselectPagesite() throws InterruptedException {
		  try {
		  boolean check1 = false;
		  Locators_Browser.EbayHistry.click();
		  minWait();
		  String getHistryNameText = Locators_Browser.Urlbar.getText();
		  System.out.println(getHistryNameText);
		  if(getHistryNameText.equalsIgnoreCase("https://m.ebay.com/?_mwBanner=1")){
			  check=true;
			  APP_LOGS.info("History Page is Launched succesfully.");
			  Assert.assertTrue(check);	
		  }else{
			  APP_LOGS.info(getHistryNameText+": "+check1);
			  APP_LOGS.info("History Page is not Launched succesfully.");	
			  Assert.fail();
		  }
		  customWait(5000);
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	  }
	  
	  
	  public void validateHistoryPage() throws InterruptedException {
		  boolean check1 = false;
		  boolean check2 = false;
		  minWait();
		  try {			  
			  String getHistryNameText1 = Locators_Browser.AmazonHistry.getText();
			  System.out.println(getHistryNameText1);
			  String getHistryNameText2 = Locators_Browser.FBHistry.getText();
			  System.out.println(getHistryNameText2);
			  if(getHistryNameText1.equalsIgnoreCase("Online Shopping: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")){
				  check1=true;
				  APP_LOGS.info("History of Sites is displayed succesfully.");		
			  }
			  if(getHistryNameText2.equalsIgnoreCase("m.facebook.com")){
				  check2 = true;
				  APP_LOGS.info("Second History: "+getHistryNameText2);
			  }
			  if((check1)&&(check2)){
				  check=true;
				  APP_LOGS.info("History of Sites is displayed succesfully.");
				  Assert.assertTrue(check);			
			  }
			  else{
				  APP_LOGS.info(getHistryNameText1+": "+check1+"\n"+getHistryNameText2+": "+check2);
				  APP_LOGS.info("History of Sites is not displayed succesfully.");	
				  Assert.fail();		
			  }
			  Locators_Browser.HistryCancelIcon.click();
		  }
			  catch (org.openqa.selenium.NoSuchElementException e) {
					APP_LOGS.info("Element not Found");
					e.printStackTrace();
				} 
		  
	  }
	 
	 public void selectNewTab() throws InterruptedException {
		 try {
		 minWait();
		 //aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")").click();
		 Locators_Browser.MenuChrome.click();
		 minWait();
		 Locators_Browser.NewTabOptn.click();
		 minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
			 
	 }
	 
	 public void ValidateNewTabsOpen() throws InterruptedException {
		 try {
		 if(isElementExist(Locators_Browser.TwoTabopen)){
			  check = true;
				APP_LOGS.info("Selected 2 tabs sucessfully.");
				Assert.assertTrue(check);
			  } 
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				Assert.fail();
				e.printStackTrace();
			} 
	 }
	 
	 public void validateNewTabSelected() throws InterruptedException {
			searchString("RemoteReportsRefreshChimeraServic","XP8_BRWSR_003");
			customWait(5000);
	  }
	 
	 
	 public void selectIncognitoNewTab() throws InterruptedException {
		 try {minWait();
		 //aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")").click();
		 Locators_Browser.MenuChrome.click();
		 minWait();
		 Locators_Browser.NewIncgTabOptn.click();
		 minWait();
			 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void copyTxtLink() throws InterruptedException {
		 try {minWait();
		 String getTxtLink= Locators_Browser.TxtLnkHistry.getText();
			System.out.println(getTxtLink);
			minWait();
			Locators_Browser.HistryCancelIcon.click();
		 }  catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			}  
	 }
	 
	 public void enterUrlIncgnbar() throws InterruptedException {
		 try {
		 Locators_Browser.Urlbar.click();
		 minWait();
		 enterTextToInputField(Locators_Browser.Urlbar,"mobile.twitter.com");
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_ENTER);
		 minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	 }
	
		 
	 public void deleteAllTabs() throws InterruptedException {
		 try {
			 minWait();
			 minWait();
			 aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")").click();
			 Locators_Browser.MenuChrome.click();
			 minWait();
			 if(isElementExist(Locators_Browser.CloseAllOptn)){ minWait();
			 Locators_Browser.CloseAllOptn.click();
			 minWait();
			 }
			 else { minWait();
			 Locators_Browser.CloseAllIncgOptn.click();
			 }
			 minWait();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 public void deleteAllIncognitoTabs() throws InterruptedException {
		 try {
		 minWait();
		 minWait();
		// aDriver.findElementByAndroidUIAutomator("UiSelector().resourceId(\"com.android.chrome:id/tab_switcher_button\")").click();
		 Locators_Browser.MenuChrome.click();
		 minWait();
		 if(isElementExist(Locators_Browser.CloseAllOptn)){ minWait();
		 Locators_Browser.CloseAllOptn.click();
		 minWait();
		 }
		 else { minWait();
		 Locators_Browser.CloseAllIncgOptn.click();
		 }
		 minWait();
			}
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
	 }
	 
	 public void clearHistry() throws InterruptedException {
		 try {
		 minWait();
		 Locators_Browser.ClearBrowsngDataOptn.click();
		 APP_LOGS.info("Clear Browsing Data.. is Clicked sucessfully");
		 minWait();
		 Locators_Browser.ClrDataDropdown.click();
		 APP_LOGS.info("Clear Data option is Clicked sucessfully");
		 minWait();
		 Locators_Browser.begningTimeOptn.click();
		 APP_LOGS.info("From Begning of time Option is Selected sucessfully");
		 minWait();
		 Locators_Browser.ClrDataBtn.click();
		 APP_LOGS.info("History Clear Data Button is Selected sucessfully");
		 if(isElementExist(Locators_Browser.HistryClear)) {
			    Locators_Browser.HistryClear.click();
			    minWait();
			   } 
			   customWait(2000);
			//   Locators_Browser.HistryCancelIcon.click();
		 }
			catch (org.openqa.selenium.NoSuchElementException e) {
			e.printStackTrace();
			}
		 

	 }
	 
	 public void validateHistryClear() throws InterruptedException {
		 try {
		 Locators_Browser.MenuChrome.click();
		 minWait();
		 Locators_Browser.HistoryOptn.click();
		 APP_LOGS.info("History Option is Selected sucessfully");
		 minWait();
		 minWait();
		 if(isElementExist(Locators_Browser.NoHstryTxt)){
			 check=true;
			 APP_LOGS.info("\"History cleared Data page is displayed succesfully.");
			 Assert.assertTrue(check);			
		 }
		 else{
			 APP_LOGS.info("History cleared Data page is not displayed succesfully.");	
			 Assert.fail();		
		 }
		 Locators_Browser.HistryCancelIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void downloadPic() throws InterruptedException {
		
			 minWait();
		 Locators_Browser.ImagePic.click();
	 }
	 
	 public void BrowseLandscapemode() throws InterruptedException {
		 minWait();
		 aDriver.rotate(ScreenOrientation.LANDSCAPE);
		 APP_LOGS.info("Browser Turn into LAndscape mode succesfully.");	
		 minWait();
	 }
	 
	 public void BackPotraitmode() throws InterruptedException {
		 minWait();
		 aDriver.rotate(ScreenOrientation.PORTRAIT);
		 APP_LOGS.info("Browser Turn into Potrait mode succesfully.");	
	 }
	 
	 public void selectBookmarks() throws InterruptedException {
		 try {
		 customWait(5000);
		Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 customWait(5000);
		 Locators_Browser.BookmarkIcon.click();
		 APP_LOGS.info("Bookmarks Page is Selected sucessfully");
		 minWait();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
		 
	 }
	 
	 public void deselectBookmarks() throws InterruptedException {
		 try {
		 //customWait(5000);
		Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 minWait();
		 Locators_Browser.BookmarksOptn.click();
		 customWait(5000);
		 Locators_Browser.OptnBkmrkIcon.click();
		 minWait();
		 Locators_Browser.DeleteBkmrkOptn.click();
		 minWait();
			 APP_LOGS.info("Bookmarks Page is Deselected sucessfully");
			 APP_LOGS.info("Delete Bookmarks Page is Selected sucessfully");
			 minWait();
			 Locators_Browser.HistryCancelIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateBookmarks() throws InterruptedException {
		 try {
		 minWait();
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.BookmarksOptn.click();
		 APP_LOGS.info("Bookmarks option is Selected sucessfully");
		 minWait();
		 Locators_Browser.MobileBookmarks.click();
		 minWait();
		 if(isElementExist(Locators_Browser.BookmarkNytimes)){
			 check=true;
			 APP_LOGS.info("NYTimes Page Bookmarks is displayed succesfully.");
			 Assert.assertTrue(check);			
		 }
		 else{
			 APP_LOGS.info("NYTimes Page Bookmarks is not displayed succesfully.");	
			 Assert.fail();		
		 }Locators_Browser.HistryCancelIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
		 
	 }
	 
	 public void selectRecentTabs() throws InterruptedException {
		 try {
		 customWait(5000);
		Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.RecentTabsOptn.click();
		 APP_LOGS.info("Bookmarks Page is Selected sucessfully");
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
		
	 }
	 
	 public void validateRecentTab() throws InterruptedException {
		 try {
		 minWait();
		 Locators_Browser.ShowFullHistory.click();
		 String getText= Locators_Browser.RecentTabsCNN.getText();
		 if(getText.contains("CNN")){
			 check=true;
			 APP_LOGS.info("CNN Url Recently Closed is displayed succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("NYTimes Page Bookmarks is not displayed succesfully.");	
			 Assert.fail();		
		 }Locators_Browser.HistryCancelIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
		 
	 }
	 
	 public void validateHistorySearch() throws InterruptedException {
		 try {
		 Locators_Browser.HistrySearchIcon.click();
		 minWait();
		 enterTextToInputField(Locators_Browser.SearchField,"https://m.facebook.com/?refsrc=https%3A%2F%2Fm.facebook.com" );
		 if(isElementExist(Locators_Browser.FBHistry)){
			 check=true;
			 APP_LOGS.info("History Search is displayed succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("History Search is not displayed succesfully.");	
			 Assert.fail();		
		 }Locators_Browser.GobackIcon.click();
		 minWait();
		 Locators_Browser.HistryCancelIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void selectReqDesktpSite() throws InterruptedException {
		 try {
		 customWait(5000);
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 //customWait(2000);
		 org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
		 System.out.println(size);
		 int x=size.getWidth()/2;
		 int starty=(int)(size.getHeight()*0.60);
		 int endy=(int)(size.getHeight()*0.10);
		 aDriver.swipe(x, starty, x, endy, 2000);
		 minWait();
//		 aDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.android.settings:id/content_parent\")).scrollIntoView(new UiSelector().text(\"Programmable Keys\"))");

		 Locators_Browser.RqstDsktpOptn.click();
		 APP_LOGS.info("Request Desktop Site  is enabled sucessfully");
		 minWait();
		 customWait(2000);	 
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateReqDesktopSiteEnabled() throws InterruptedException {
		 try {
			 minWait();
			 minWait();
			 String getCurrentText = Locators_Browser.Urlbar.getText();
			 System.out.println(getCurrentText);
			 if(getCurrentText.equalsIgnoreCase("www.speedtest.net")){
				 check = true;
				 APP_LOGS.info("Request Desktop Site search is displayed succesfully.");
				 Assert.assertTrue(check);						
			 }else{
				 APP_LOGS.info("Request Desktop Site search is not displayed succesfully.");	
				 Assert.fail();				
			 }
		 } catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void selectAddtoHomeScreen() throws InterruptedException {
		 try {
		 //customWait(5000);
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.AddToHomeScrnOptn.click();
		 minWait();
		 Locators_Browser.AddOptn.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateAddtoHomeScreen() throws InterruptedException {	 
		 searchString(" Add to Home screen","XP8_BRWSR_020");
	 }
	 
	 public void selectFindinPage() throws InterruptedException {
		 try {
		 //customWait(5000);
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.FindInPageOptn.click();
		 minWait();
		 enterTextToInputField(Locators_Browser.FndPageUrlField,"Times of India");
		 minWait();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 public void validateFindinPage() {
		 try {
		 if(isElementExist(Locators_Browser.TimesofIndiaBlogs)){
			 check=true;
			 APP_LOGS.info("Found in Page succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("Found in Page is not succesfully.");	
			 Assert.fail();		
		 }Locators_Browser.CloseIcon.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void disableWiFi() throws InterruptedException {
		 try {
			 aDriver.pressKeyCode(AndroidKeyCode.HOME);
			 minWait();
			 minWait();
			 aDriver.pressKeyCode(AndroidKeyCode.HOME);
			 minWait();
			 minWait();
			 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			 minWait();
			 aDriver.openNotifications();
			 minWait();
			 Locators_Browser.wifiOn.click();
			 aDriver.pressKeyCode(AndroidKeyCode.HOME);
			 customWait(5000);
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
			 APP_LOGS.info("Element not Found");
			 e.printStackTrace();
		 } 
	 }
	 
	 public void enableWiFi() throws InterruptedException {
		 try {
		 minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_NOTIFICATION);
			minWait();
			aDriver.openNotifications();
			minWait();
			Locators_Browser.enableWifi.click();
			minWait();
			Locators_Browser.wifiOn.click();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateWiFiDisable() {
		 try {
		
		 if(isElementExist( Locators_Browser.offlineTxt)){
			 check=true;
			 APP_LOGS.info("WiFi is Disabled succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("WiFi is  not Disabled succesfully.");	
			 Assert.fail();		
		 }
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void scroll() {
		 org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
		 System.out.println(size);
		 int x=size.getWidth()/2;
		 int starty=(int)(size.getHeight()*0.60);
		 int endy=(int)(size.getHeight()*0.10);
		 aDriver.swipe(x, starty, x, endy, 2000);
		
	 }
	 public void selectSettings() throws InterruptedException {
		 try {
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SiteSettgs.click();
		 minWait();
		 Locators_Browser.CookiesOptn.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateCookiesDisabled() throws InterruptedException {
		 try {
		 minWait();
		 Locators_Browser.OnCookies.click();
		 if(isElementExist( Locators_Browser.BlockedCookies)){
			 check=true;
			 APP_LOGS.info("Cookies is Disabled succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("Cookies is not Disabled succesfully.");	
			 Assert.fail();		
		 }minWait();
		 Locators_Browser.OffCookies.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 
	
	 public void changeSearchEngine() throws InterruptedException 
	 { 
		 try {
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 Locators_Browser.SearchEng.click();
		 minWait();
		 Locators_Browser.Yahooo.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 minWait();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 	
	 }
	 
	 public void validateSearchEngChanged(String summaryText) throws InterruptedException {
		 try {
		 minWait();
			String getSummaryText = aDriver.findElement(By.xpath("//android.widget.TextView[@text='" + summaryText + "']"))
					.getText();
			System.out.println(getSummaryText);
			if (getSummaryText.equalsIgnoreCase(getSummaryText)) {
				APP_LOGS.info("Search Engine is: " + getSummaryText);
				check= true;
				minWait();
			} else {
				APP_LOGS.info("Search Engine is not Changed: " + getSummaryText);
			} 
			 Locators_Browser.NavigateUpSrchEng.click();
			 minWait();
			 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 Locators_Browser.SearchEng.click();
		 minWait();
		 Locators_Browser.Google.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void disableHomepage() throws InterruptedException 
	 { 
		 try {
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 Locators_Browser.Homepage.click();
		 minWait();
		 Locators_Browser.OnHomepge.click();
		 minWait();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validatediabledHomepage() throws InterruptedException {
		 try {
		 if(isElementExist( Locators_Browser.HompageScreen)){
			 check=true;
			 APP_LOGS.info("HomePage is Disabled succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("HomePage is not Disabled succesfully.");	
			 Assert.fail();		
		 } Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 Locators_Browser.Homepage.click();
		 minWait();
		 Locators_Browser.OffHomepge.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void editHomepageOpen(String newUrl) throws InterruptedException 
	 { 
		 try {
		 Locators_Browser.MenuChrome.click();
		 APP_LOGS.info("Chrome Menu is Selected sucessfully");
		 minWait();
		 scroll();
		 minWait();
		 Locators_Browser.SettgsOptn.click();
		 minWait();
		 Locators_Browser.Homepage.click();
		 minWait();
		 Locators_Browser.OpnPage.click();
		 minWait();
		 enterTextToInputField(Locators_Browser.OpnPgeField,newUrl);
		 minWait();
		 Locators_Browser.SaveOptn.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 minWait();
		 Locators_Browser.NavigateUpSrchEng.click();
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void validateEditHomePageOpn() throws InterruptedException {
		 try {
		 minWait();
		 minWait();
		 String getHistryNameText1 = Locators_Browser.Urlbar.getText();	 
		  System.out.println(getHistryNameText1);
		 if(getHistryNameText1.equalsIgnoreCase("https://mobile.nytimes.com/?referer=")){
			 check=true;
			 APP_LOGS.info("Search Engine is changed succesfully.");
			 Assert.assertTrue(check);			
		 }else{
			 APP_LOGS.info("Search Engine is not changed succesfully.");	
			 Assert.fail();		
		 }
		 }
		 catch (org.openqa.selenium.NoSuchElementException e) {
				APP_LOGS.info("Element not Found");
				e.printStackTrace();
			} 
	 }
	 
	 public void logout() throws InterruptedException {
		 minWait();
		 aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		 APP_LOGS.info("Home Button is Selected sucessfully");
		 minWait();
	 }
	 
	
	public void stopAdb() throws InterruptedException, IOException {
		  customWait(4000);
		  Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		  APP_LOGS.info("Adb logs stopped succesfully. ");
		  customWait(2000);
		 }
	
}
