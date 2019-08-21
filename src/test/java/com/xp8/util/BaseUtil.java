package com.xp8.util;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.library.AndroidWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.ImmutableMap;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xp5S.util.CommonConfig;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

public class BaseUtil extends CommonConfig
{
	//public static AndroidDriver AndroidDriverWait;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	
	
	public void send_Adb_Command() {
		
		List<String> removePicsArgs = Arrays.asList("-a", "android.intent.action.VIEW https://www.facebook.com/");
		Map<String, Object> removePicsCmd = ImmutableMap.of("command", "am start", "args", removePicsArgs);
		aDriver.executeScript("mobile: shell", removePicsCmd);
		
		
		
	}
	

	
	public void clearRecentApps() throws InterruptedException {		 		 	
	 			
	 	try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			minWait();
			boolean check1=true;
			
			 	if(isElementExist(Locators_BaseUtil.no_Recent_Items)) {
			 		APP_LOGS.info("No Recent Applications Present");
			 		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			 		minWait();
			 	}else {
			 		while(check1) {
			 			if(isElementExist(Locators_BaseUtil.clear_all)) {
							check1=false;
							Locators_BaseUtil.clear_all.click();
						 	break;
						}
					   	aDriver.swipe(600, 300, 600, 1400, 250);
						minWait();						 					
			 		}
			 	}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in clearRecentApps method");
			e.printStackTrace();
		}		 					 	
	}	
	
	public void clearRecentApps_O() throws InterruptedException, IOException {		
		// For Android O.
		try {
			customWait(3000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(3000);
			if(isElementExist(Locators_BaseUtil.no_Recent_Items)) {
				APP_LOGS.info("No Recent Applications Present");
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				minWait();
			}else {
				while(true) {
					if(isElementExist(Locators_BaseUtil.clear_all)) {					
						Locators_BaseUtil.clear_all.click();
						break;
					}
					aDriver.swipe(600, 300, 600, 1400, 250);
					minWait();						 					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		 					 	
	}
	
	
	
	
	
	public void clearAllow() throws InterruptedException {
		
		try {
			minWait();
			while(isElementExist(Locators_BaseUtil.allow_PopUp)) {
				clickBtn(Locators_BaseUtil.allow_PopUp);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void clearPermissions() throws InterruptedException, FileNotFoundException, IOException, ParseException {
		
		try {
			while(true) {
			aDriver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.android.packageinstaller:id/permission_allow_button\")").click();
			}
		} catch (Exception e) {
			
		}
		 
	}
/*
	public  void executeShellCommands(int input,String URL,String pkgName,String activityName ) throws IOException {
		
		switch(input) {
		  
			case 1:
				String cmd1 = "adb shell am start -a android.intent.action.VIEW -d "+URL+"";
				Process p1 = Runtime.getRuntime().exec(cmd1);
				break;
		  
			case 2:
				String  cmd2 = "adb shell am start -n "+pkgName+"/"+activityName+"";
				Process p2 = Runtime.getRuntime().exec(cmd2); 
				break;     
		}		
	}
	  */
	public boolean ifAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}


	public void executeJS(String code) {
		
		try {
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript(code, "");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void clickOnElementUsingJS(WebElement e) {
		
		try {
			JavascriptExecutor executor = (JavascriptExecutor) getDriver();
			executor.executeScript("arguments[0].click();", e);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	}

	public void clickBtn(WebElement e) {
		
			e.click();
		
	}
	
	public void clickBtn(AndroidElement e) {
		
			e.click();
			
		
	}
	
	
//This method is used to retun an androidelement based on the match found	
	public AndroidElement multi_Loc_Strategy(AndroidElement e1,AndroidElement e2,AndroidElement e3,AndroidElement e4,AndroidElement e5,int xCordinate,int yCordinate) {
		/*
		 * It takes 5 locators and choose which is Present
		 */
		AndroidElement isElementPresentOntheDeviceScreen = null;
		
		if (isElementExist(e1)) {
			System.out.println("Locator 1 is found");
		isElementPresentOntheDeviceScreen=e1;
		}else if (isElementExist(e2)) {
			System.out.println("Locator 2 is found");
		isElementPresentOntheDeviceScreen=e2;
		}else if (isElementExist(e3)) {
			System.out.println("Locator 3 is found");
		isElementPresentOntheDeviceScreen=e3;
		}else if (isElementExist(e4)) {
			System.out.println("Locator 4 is found");
		isElementPresentOntheDeviceScreen=e4;

		}else if (isElementExist(e5)) {
			System.out.println("Locator 5 is found");
		isElementPresentOntheDeviceScreen=e5;

		}else {
			/*System.out.println("No locator using Coordinates");
		TouchAction tap=new TouchAction(aDriver);
		tap.tap(xCordinate, yCordinate).perform();*/

		}

		return isElementPresentOntheDeviceScreen;



		}
	
	public boolean longpress(int keyCode){
		try {
			aDriver.longPressKeyCode(keyCode);
			return true;
		} catch (Exception s) {
			return false;
		}
	}
	
	public void launchAppThroughABD(String adbCommand) throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec(adbCommand);
		minWait();		
	}
	
	public void launchApp(String pkgName, String actName){
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/properties/objects.properties"));

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		String pkgN = properties.getProperty(pkgName);
		String actN = properties.getProperty(actName);
			
		//aDriver.startActivity(pkgN, actN);
	}

	public void selectCheckbox(WebElement e) {
		if (!e.isSelected()) {
			e.click();
		}
	}
	
	public void selectCheckbox(AndroidElement e) {
		if (!e.isSelected()) {
			e.click();
		}
	}

	public void deSelectCheckbox(WebElement e) {
		if (e.isSelected()) {
			e.click();
		}
	}
	
	public void deSelectCheckbox(AndroidElement e) {
		if (e.isSelected()) {
			e.click();
		}
	}	

	public boolean enterTextToInputField(WebElement e, String text) throws InterruptedException {
		if (e == null) {
			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(300);
				e.clear();
				e.click();
				Thread.sleep(300);
				e.sendKeys(text);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "+ e);
				return false;
			}
		}
	}
	
	public boolean enterTextToInputField(AndroidElement e, String text) throws InterruptedException {
		if (e == null) {
			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(300);
				e.clear();
				e.click();
				Thread.sleep(300);
				e.sendKeys(text);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAIL: Element not found or displayed: Object Locator : "+ e);
				return false;
			}
		}
	}
	
	public boolean clearInputField(WebElement e) throws InterruptedException {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(500);
				e.clear();
				Thread.sleep(500);
				APP_LOGS.info("PASS: Element found and cleared the value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}
	
	public boolean clearInputField(AndroidElement e) throws InterruptedException {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(500);
				e.clear();
				Thread.sleep(500);
				APP_LOGS.info("PASS: Element found and cleared the value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}
	

	public boolean enterKeysToInputField(WebElement e, Keys operation) {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				e.clear();
				e.sendKeys(operation);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}
	
	public boolean enterKeysToInputField(AndroidElement e, Keys operation) {
		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				e.clear();
				e.sendKeys(operation);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : "
						+ e);
				return false;
			}
		}
	}

	public boolean hoverOverElement(WebElement el) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(el).build().perform();
		return false;
	}

	public boolean hoverOverElement(AndroidElement el) {
		Actions actions = new Actions(getDriver());
		actions.moveToElement(el).build().perform();
		return false;
	}

	public String getCurrentTimeStamp() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return format.format(new Date()).toString();
	}

	public void selectDropDownByValue(WebElement e, String value) {

		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}
	
	public void selectDropDownByValue(AndroidElement e, String value) {

		Select dropdown = new Select(e);
		dropdown.selectByValue(value);
	}

	public void selectDropDownByText(WebElement e, String text) {

		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(text);
	}
	
	public void selectDropDownByText(AndroidElement e, String text) {

		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(text);
	}
	
	public void selectDropDownsByText(WebElement e, String text) {
		Select dropdown = new Select(e);
		if (dropdown.isMultiple()) 
		{
			dropdown.deselectAll();
			dropdown.selectByVisibleText(text);
		
		}
		else 
		{
			dropdown.selectByVisibleText(text);
		}	
	}
	
	public void selectDropDownsByText(AndroidElement e, String text) {
		Select dropdown = new Select(e);
		if (dropdown.isMultiple()) 
		{
			dropdown.deselectAll();
			dropdown.selectByVisibleText(text);
		
		}
		else 
		{
			dropdown.selectByVisibleText(text);
		}	
	}
	
	
	public void selectDropDownValueByIndex(WebElement e, int index) {
		Select dropdown = new Select(e);
		dropdown.selectByIndex(index);
	}
	
	public void selectDropDownValueByIndex(AndroidElement e, int index) {
		Select dropdown = new Select(e);
		dropdown.selectByIndex(index);
	}

	public String getSelectedDropDownOptionText(WebElement e) {
		Select dropdown = new Select(e);
		return dropdown.getFirstSelectedOption().getText();
	}
	
	public String getSelectedDropDownOptionText(AndroidElement e) {
		Select dropdown = new Select(e);
		return dropdown.getFirstSelectedOption().getText();
	}

	public boolean isElementPresent(String csslocator) {
		if (getDriver().findElements(By.cssSelector(csslocator)).size() == 0)
			return false;
		else
			return true;
	}
	
	public boolean isAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;

		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public Alert getAlertbox() {

		try {
			return getDriver().switchTo().alert();

		} catch (NoAlertPresentException ex) {
			return null;
		}
	}

	public void waituntilnewWebElement(WebElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));
	}
	
	public void waituntilnew(AndroidElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));

	}

	public void waituntilnew(WebElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));

	}
	public void waituntilnewElementtobeClickable(WebElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	public void waituntilnewElementtobeClickable(AndroidElement e, int timeinSeconds) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(e));
	}


	public void dragAndDrop(WebElement source, WebElement target) {
		(new Actions(getDriver())).dragAndDrop(source, target).perform();
	}
	
	public void dragAndDrop(AndroidElement source, AndroidElement target) {
		(new Actions(getDriver())).dragAndDrop(source, target).perform();
	}
	

	public boolean isElementExist(WebElement e) {
		boolean isPresent = false;
		
			isPresent = e.isDisplayed();
			if(isPresent) {
				isPresent= true;
			}else {
				isPresent=false;
			}
			return isPresent;
		
	}
	
	public boolean isElementExist(AndroidElement e) {
		boolean isPresent = false;
		try {
			
			try {
				isPresent = e.isDisplayed();
			} catch (Exception nse) {
				isPresent = false;
			
			}
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return isPresent;
	}

	public boolean isElementNull(WebElement e) {
		boolean isNull = false;
		if (e.getText() == null) {
			isNull = true;
		}
		return isNull;
	}

	public boolean isElementNull(AndroidElement e) {
		boolean isNull = false;
		
		try {
			
			if (e.getText() == null) {
				isNull = true;
			}
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return isNull;
	}
	
	public void maxWait() throws InterruptedException{
		Thread.sleep(12000);

	}
	public void minWait() throws InterruptedException{
		Thread.sleep(1000);
	}

	public static void customWait(long secs) throws InterruptedException{
		Thread.sleep(secs) ;
	}

	public void softAssert_true(boolean check, String message){
		  SoftAssert softAssert = new SoftAssert();
		  softAssert.assertTrue(check, message); 
		 }
		 

	public void softAssert_false(){
		SoftAssert softAssertFail = new SoftAssert();
		softAssertFail.fail();
	}		 
		 
	public void softAssert_equals(String actual, String expected){
	   SoftAssert softAssertFail = new SoftAssert();
	   softAssertFail.assertEquals(actual, expected);
	}
		 
		
	public void scrollAlittle() throws InterruptedException{
	   JavascriptExecutor jse = (JavascriptExecutor)aDriver;
	   jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	   minWait();
	}

			
	public void scrollUp() throws InterruptedException{
	   try {
		JavascriptExecutor jse = (JavascriptExecutor)aDriver;
		   jse.executeScript("window.scrollBy(0,-250)", "");
		   minWait();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	}

			
	public void scrollToElement(WebElement e) throws InterruptedException{
	   try {
		minWait();
		   WebElement element = e;
		   ((JavascriptExecutor) aDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		   minWait();
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
	}
		
	public void scrollToElement(AndroidElement e) throws InterruptedException{
		
	   try {
		minWait();
		   WebElement element = e;
		   ((JavascriptExecutor) aDriver).executeScript("arguments[0].scrollIntoView(true);", element);
		   minWait();
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
	}
	
	public void javaScriptScroll(){
	   JavascriptExecutor jse = (JavascriptExecutor)aDriver;
	   jse.executeScript("window.scrollBy(0,250)", "");
	}
	
	
	public static void startAdbLog(String fileName) throws AWTException, InterruptedException, FileNotFoundException, IOException, ParseException {
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();

		 // String path = System.getProperty("user.dir");
	      customWait(1000);
		  try {
		   Runtime.getRuntime().exec("adb -s "+Uid+" logcat -c");
		   Thread.sleep(1000);
		   //Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -v time>"+path+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt\"");
		   Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -v time > src/test/resources/adbLogs/"+fileName+".txt\"");
		   Thread.sleep(2000);
		  }
		  catch(Exception e) {		 
		   e.printStackTrace(); 
		  }
	}
	
	public String startRIL_Log() throws AWTException, InterruptedException, IOException, ParseException {

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Thread.sleep(1000);
		String fileName = UUID.randomUUID().toString().replaceAll("-","");
		System.out.println(fileName);
		String path = "src/test/resources/adbLogs/"+fileName+".txt";
		try {
			Runtime.getRuntime().exec("adb -s "+Uid+" logcat -c");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -b all -v threadtime>\""+path);
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	/*		
	public static void startAdbLog(String fileName) throws AWTException, InterruptedException {
	   try {
		   APP_LOGS.info("Adb log started sucessfully ");
		   Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>C:\\Users\\chandan.a\\eclipse-workspace\\2_XP8800\\src\\test\\resources\\adbLogs\\"+fileName+".txt \"");
		   //APP_LOGS.info("Adb log Saved sucessfully");
		   Thread.sleep(2000);
	   }
	   catch(Exception e) {
		   System.out.println("Something goes Wrong!!!");   e.printStackTrace();  
	   }
	}*/
			
	public void stopAdbLog(String TCName) throws InterruptedException, AWTException, IOException {
	   try {
		Thread.sleep(1000);
		   Runtime.getRuntime().exec("taskkill /IM cmd.exe");
		   /*Robot robot = new Robot();
			           robot.keyPress(KeyEvent.VK_CONTROL);
			            robot.keyPress(KeyEvent.VK_C);
			             robot.keyRelease(KeyEvent.VK_CONTROL);
			               robot.keyRelease(KeyEvent.VK_C);*/
		   APP_LOGS.info(TCName+" : Adb logs stopped succesfully. ");
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	}	
			
	public static String captureScreenshot(String methodName) throws IOException{
	
	   Calendar cal = new GregorianCalendar();
	   int month = cal.get(Calendar.MONTH);
	   int year = cal.get(Calendar.YEAR);
	   int sec = cal.get(Calendar.SECOND);
	   int min = cal.get(Calendar.MINUTE);
	   int date = cal.get(Calendar.DATE);
	   int day = cal.get(Calendar.HOUR_OF_DAY);
	   String mailscreenshotpath = null;

	   File scrFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
	   try {
		   mailscreenshotpath = System.getProperty("user.dir")+"\\src\\test\\resources\\screenshots\\"+methodName+"_"+year+"_"+date+"_"+(month+1)+"_"+day+"_"+min+"_" +sec+".jpg";
		   FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
	   } catch (Exception e) {
		   
		   e.printStackTrace();
	   }
	   return mailscreenshotpath;
	   
	   
	}
	
	
	
	public static String saveReportWithDateAndTime(String path) throws IOException {

		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String mailscreenshotpath = null;

		
		try {
		 mailscreenshotpath =path+"_" + year + "_" + date + "_" + (month + 1) + "_" + day + "_" + min + "_" +sec+ ".html";
			FileUtils.copyFileToDirectory(new File(mailscreenshotpath), new File("src/test/resources/extentreport"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
		}
		return mailscreenshotpath;
	}
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void minWait_SonimCare() throws InterruptedException{
		Thread.sleep(2500);
	}
	 /*             
	public boolean searchString(String searchstring, String fileName) {
		
	   boolean check = false;
	   boolean check1=false;
	   boolean check2=false;
	   try { 
		   BufferedReader bf = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\adbLogs\\"+fileName+".txt"));
		   int linecount = 0;
		   String line;
		   APP_LOGS.info("Searching for " + searchstring + " in file...");
		   while (( line = bf.readLine()) != null){
			   boolean indexfound = line.contains(searchstring);
			   if (indexfound) {
				   check=true;
				   APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
				   break;
			   }  
			   else {
				   // APP_LOGS.info("Word "+searchstring+" was not found at position " + indexfound + " on line " + linecount);
				   check=false;
				   linecount++;
			   }
		   }
		   bf.close();
	   }
	   catch (IOException e) {
		   APP_LOGS.info("IO Error Occurred: " + e.toString());
	   }
	   return check;
   }
   */
	
	public boolean searchString(String searchstring, String fileName) {

		  boolean check = false;
		  try {	 
			
		   BufferedReader bf = new BufferedReader(new FileReader("src/test/resources/adbLogs/"+fileName+".txt"));
			  //System.out.println(System.getProperty("user.dir"));
		  //BufferedReader bf = new BufferedReader(new FileReader("C:\\Users\\sashi.p\\git\\SMAT\\XP5S_SCOUT\\src\\test\\resources\\adbLogs\\XP8_Sanity_039.txt"));
		   int linecount = 0;
		   String line;
		   while (( line = bf.readLine()) != null){

		    boolean indexfound = line.contains(searchstring);
		    if (indexfound) {
		     check=true;
		     APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
		     break;
		    }  
		    else {
		     check=false;
		     linecount++;
		    }
		   }
		   bf.close();
		  }
		  catch (Exception e) {
		   APP_LOGS.info("IO Error Occurred: " + e.toString());
		  }
		  return check;
		 }
   public void recordVideo(String TC_Name) throws InterruptedException, IOException, ParseException{
	   try {
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		   minWait();
		   Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" shell screenrecord mnt/sdcard/"+TC_Name+".mp4 \"");
		   customWait(3000);
	} catch (Exception e) {
		
		e.printStackTrace();
	}
   }

   
   public void acceptGoogleSecurity() throws InterruptedException {
	   if(isElementExist(Locators_SonimCare.AcceptBtn)) {
		   minWait();
		   Locators_SonimCare.AcceptBtn.click();		   
	   }
   }
   
   
	public static void deleteDirectory() throws IOException, InterruptedException, ParseException
	{
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
	System.out.println(Uid);
	customWait(2000);	
	Runtime.getRuntime().exec("adb -s "+Uid+" shell rm -r /storage/emulated/0/FilledContent");  
			 System.out.println("Deleted Memory");
			 customWait(6000);
			 
			 Process p1 = Runtime.getRuntime().exec("adb -s "+Uid+" uninstall fillememory.myapplication");
			 customWait(2000);	
	}
  
   public static String image(AndroidDriver<AndroidElement> driver) {
	   
	   File targetFile = null;
	   try {
		   File scrFile = driver.getScreenshotAs(OutputType.FILE);
		   String fileName = UUID.randomUUID().toString();
		   targetFile = new File(System.getProperty("user.dir")+"//toastScreenshots//"+fileName+".png");
		   FileUtils.copyFile(scrFile, targetFile);		   
		   System.out.println(targetFile.toString());
		
	   } catch (Exception e) {
		   e.printStackTrace();
	   }	   
	   return targetFile.toString();	   
   }
   
   public static String OCR(String imagePath) {
	   
	   String result = null;
	   File imageFile = new File(imagePath);
	   ITesseract instance = new Tesseract();
	   instance.setDatapath(System.getProperty("user.dir")+"\\tessdata");
	   try {
		   result = instance.doOCR(imageFile);
	   } catch (TesseractException e) {
		   System.out.println(e.getMessage());
	   }
	   return result;
   }
   
   public String Verify(AndroidDriver<AndroidElement> driver) {
	   
	   String result = null;
	   File scrFile = driver.getScreenshotAs(OutputType.FILE);
	   ITesseract instance = new Tesseract();
	   try {
		   result = instance.doOCR(scrFile);
	   } catch (Exception e) {
		System.out.println(e.getMessage());
	   }
	   return result;
   }
   
   
   
   static String scrShotDir = "screenshots";
   File scrFile;
   static File scrShotDirPath = new java.io.File(System.getProperty("user.dir")+"//"+ scrShotDir+ "//");
   String destFile;
// static AndroidDriver driver = null;
  
  
	public String readToastMessage() throws TesseractException {
		
		   String imgName = takeScreenShot();
		   System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");

		   
		   String result = null;
		   File imageFile = new File(scrShotDirPath, imgName);
		   System.out.println("Image name is :" + imageFile.toString());
		   ITesseract instance = new Tesseract();
//		   ITesseract instance1 = new Tesseract1();
	//	   ITesseract instance = Tesseract.getInstance();
		   File tessDataFolder = LoadLibs.extractTessResources(System.getProperty("user.dir")+"\\tessdata"); // Extracts
		                    // Tessdata
		                    // folder
		                    // from
		                    // referenced
		                    // tess4j
		                    // jar
		                    // for
		                    // language
		                    // support
		   instance.setDatapath(tessDataFolder.getAbsolutePath()); // sets tessData
		                 // path
		
		   result = instance.doOCR(imageFile);
	//	   System.out.println(result);
		   return result;
	  }
	
	  /**
	   * Takes screenshot of active screen
	   * 
	   * @return ImageFileName
	   */
	  public String takeScreenShot() {
		  
		   scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE); 
		   
		   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		   
		   new File(scrShotDir).mkdirs(); // Create folder under project with name
		           // "screenshots" if doesn't exist
		   destFile = dateFormat.format(new Date()) + ".png"; // Set file name
	                // using current
	                // date time.
		   try {
			   FileUtils.copyFile(scrFile, new File(scrShotDir + "/" + destFile)); // Copy
		                     // paste
		                     // file
		                     // at
		                     // destination
		                     // folder
		                     // location
		   } catch (IOException e) {
			   APP_LOGS.info("Image not transfered to screenshot folder");
			   e.printStackTrace();
		   }
		  return destFile;
	 }
	  public boolean scrollTo(String phoneNum) {
			/*
			  Method used to select an element on the page by scrolling the Scroll View/List View
			 */

			boolean check = false;
			try {  
				String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
				String textElement = ".scrollIntoView(new UiSelector().text(\""+ phoneNum +"\"))";
				aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
				APP_LOGS.info("Searched application is found sucessfully : ");
				check = true;
				return check;
			}
			catch(Exception e) {
				return check;
			}
			}
	  
	  public void scrollToElements(AndroidElement e) throws InterruptedException {
		  
		   try {
			minWait();
			 /*  WebElement element = e;
			   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);*/
			   
			   for(int i=1; i<=4;i++)
			   {
				   if(isElementExist(e)) {
					   
					   System.out.println("------------------------");
					   customWait(2000);
					   break;
				   }
				   else {
					   System.out.println("+++++++++++++++++++++++++");

					   scroll();
					   continue;
				   }
			   }
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
	   }
	  
	  
	 /* public void scrollForPTT(AndroidElement e,AndroidElement e1) throws InterruptedException {
		  
		   try {
			minWait();
			   WebElement element = e;
			   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",element);
			   
			   for(int i=1; i<=4;i++)
			   {
				   
				   customWait(4000);
				   if(isElementExist(e) || isElementExist(e1)) {
					   
					   System.out.println("Coming out of for statement");
					 
					   break;
				   }
				   else {
					   System.out.println("Scrolling now ");

					   scroll();
					   continue;
				   }
			   }
		} catch (Exception e3) {
			
			e3.printStackTrace();
		}
	   }
				*/
	  
	  public void scroll() {
			try {
				
				System.out.println("2222222222222222222222222222");
				org.openqa.selenium.Dimension size =aDriver.manage().window().getSize();
				System.out.println(size);
				int x=size.getWidth()/2;
				int starty=(int)(size.getHeight()*0.60);
				int endy=(int)(size.getHeight()*0.10);
			   aDriver.swipe(x, starty, x, endy, 2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   

		// Navigate or launch any application.
		// This method doesnt not launch PTT application.
		public void launch_an_app(String appName) throws InterruptedException {
			
			try {
				customWait(1000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
				APP_LOGS.info("HOme PAge");
				customWait(2000);
				Locators_BaseUtil.AppListIcon.click();
				customWait(1000);
				switch (appName) {
				
				case "browser":
					scrollToElements(Locators_BaseUtil.browser_App);
						clickBtn(Locators_BaseUtil.browser_App);
						APP_LOGS.info("Clicked on Browser successfully.");
				
					break;

				case "contacts":
					scrollToElements(Locators_BaseUtil.contacts);
					clickBtn(Locators_BaseUtil.contacts);
					APP_LOGS.info("Clicked on Contact successfully.");
					break;

				case "phone":
						
					System.out.println("1111111111111111111111");
					scrollToElements(Locators_BaseUtil.phone_DailerApp);
					clickBtn(Locators_BaseUtil.phone_DailerApp);
					APP_LOGS.info("Clicked on Phone successfully.");
					break;

				case "scout":
					scrollToElements(Locators_BaseUtil.scoutApp_icon);
					clickBtn(Locators_BaseUtil.scoutApp_icon);
					APP_LOGS.info("Clicked on ScoutApps successfully.");
					break;

				case "music":
					scrollToElements(Locators_BaseUtil.music_icon);
					minWait();
					clickBtn(Locators_BaseUtil.music_icon);
					APP_LOGS.info("Clicked on Music successfully.");
					break;

				case "fm":
					scrollToElements(Locators_BaseUtil.FM_App);
					minWait();
					clickBtn(Locators_BaseUtil.FM_App);
					APP_LOGS.info("Clicked on FM Radio successfully.");
					break;

				case "fileExplorer":
					scrollToElements(Locators_BaseUtil.FileExplorer_App);
					minWait();
					clickBtn(Locators_BaseUtil.FileExplorer_App);
					APP_LOGS.info("Clicked on FileExplorer_App successfully.");
					break;

				case "backUpReset":
					scrollToElements(Locators_BaseUtil.backUP_reset);
					minWait();
					clickBtn(Locators_BaseUtil.backUP_reset);
					APP_LOGS.info("Clicked on backUP_reset successfully.");
					break;

				case "downloads":
					scrollToElements(Locators_BaseUtil.downloads_icon);
					minWait();
					clickBtn(Locators_BaseUtil.downloads_icon);
					APP_LOGS.info("Clicked on downloads_icon successfully.");
					break;

				case "settings":
					scrollToElements(Locators_BaseUtil.settings);
					clickBtn(Locators_BaseUtil.settings);
					APP_LOGS.info("Clicked on settings successfully.");
					break;

				case "camera":
					scrollToElements(Locators_BaseUtil.cameraApp);
					clickBtn(Locators_BaseUtil.cameraApp);
					APP_LOGS.info("Clicked on cameraApp successfully.");
					break;

				case "calendar":
					scrollToElements(Locators_BaseUtil.CalenderApp);
					clickBtn(Locators_BaseUtil.CalenderApp);
					APP_LOGS.info("Clicked on CalendarApp successfully.");
					break;

				case "clock":
					scrollToElements(Locators_BaseUtil.clock_Icon);
					minWait();
					clickBtn(Locators_BaseUtil.clock_Icon);
					APP_LOGS.info("Clicked on clock_Icon successfully.");
					break;

				case "calculator":
					scrollToElements(Locators_BaseUtil.calciApp);
					minWait();
					clickBtn(Locators_BaseUtil.calciApp);
					APP_LOGS.info("Clicked on Calculator successfully.");
					break;

				case "soundRecorder":
					scrollToElements(Locators_BaseUtil.RecorderApp);
					minWait();
					clickBtn(Locators_BaseUtil.RecorderApp);
					APP_LOGS.info("Clicked on RecorderApp successfully.");
					break;

				case "gallery":
					scrollToElements(Locators_BaseUtil.gallery_icon);
					clickBtn(Locators_BaseUtil.gallery_icon);
					APP_LOGS.info("Clicked on gallery_icon successfully.");
					break;

				case "messaging":
					scrollToElements(Locators_BaseUtil.messaging_icon);
					if(isElementExist(Locators_BaseUtil.messaging_icon)) {
						clickBtn(Locators_BaseUtil.messaging_icon);
					}
					else {
						clickBtn(Locators_BaseUtil.message_icon);
					}

					APP_LOGS.info("Clicked on messaging_icon successfully.");
					break;
					
				case "fillmemory":
					scrollToElements(Locators_BaseUtil.fillmemoryIcon);
					clickBtn(Locators_BaseUtil.fillmemoryIcon);
					
					APP_LOGS.info("Clicked on FillMemory_icon successfully.");
					break;
					
				case "gmail":
					scrollToElements(Locators_BaseUtil.gmail);

					clickBtn(Locators_BaseUtil.gmail);

					APP_LOGS.info("Clicked on Gmail_Icon successfully.");
					break;
					
				case "PTT":
					
				  
					
					
				case "chrome":
					scrollToElements(Locators_BaseUtil.chrome);
					clickBtn(Locators_BaseUtil.chrome);
					

					
				}
			}catch (org.openqa.selenium.NoSuchElementException e) {
				test.log(LogStatus.ERROR, "Error in locators->LaunchApp()");
			}catch (Exception e) {
				test.log(LogStatus.ERROR, "Exeption in ->LaunchApp()");

			}

		}



		public void enableSwitch(WebElement enablebtn, WebElement disablebtn, WebElement switchwidget) throws InterruptedException {
			/*
			 * Enable Switch widget or toggle button
			 */
			try {
			minWait();
			if(isElementExist(switchwidget)) {
				if (!isElementExist(enablebtn)) {
					minWait();
					clickBtn(disablebtn);
					APP_LOGS.info("Switch is Enabled");                     
					minWait();
				}
			}
			else {
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
		}
		
		public void disableSwitch(WebElement disablebtn, WebElement enablebtn, WebElement switchwidget) throws InterruptedException {
			/*
			 * Enable Switch widget or toggle button
			 */
			try {
			minWait();
			if(isElementExist(switchwidget)) {
				if (!isElementExist(disablebtn)) {
					minWait();
					clickBtn(enablebtn);
					APP_LOGS.info("Switch is Disabled");                     
					minWait();
				}
			}
			else {
				test.log(LogStatus.ERROR, "Toggle button is enabled");
				Assert.fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
		}
		
		
		public void enableFeature(WebElement enablebtn, WebElement disablebtn, WebElement switchtitle) throws InterruptedException {
			/*
			 * This Method is to Enable Settings Feature"
			 */
			try {
				minWait();
				if(isElementExist(switchtitle)) {
					if (!isElementExist(enablebtn)) {
						minWait();
						clickBtn(disablebtn);
						APP_LOGS.info("Feature is Enabled");                     
						minWait();
					}
				}
				else {
					test.log(LogStatus.ERROR, "Toggle button is disabled");
					Assert.fail();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "No Such Element Found");
				Assert.fail();
			}
		}
		
		public void disableFeature(WebElement enablebtn, WebElement disablebtn) throws InterruptedException {
			/*
			 * This Method is to Disable Settings Feature"
			 */
			try {
				minWait();
				if (!isElementExist(disablebtn)) {
					minWait();
					clickBtn(enablebtn);
					APP_LOGS.info("Featureis disabled");
					minWait();
				}
			} catch (Exception e) {
				e.printStackTrace();
				test.log(LogStatus.ERROR, "Disable toggle : No Such Element Found");
				Assert.fail();
			}
		}
		
		public void checkSimCardAvailability() throws IOException, InterruptedException, ParseException {
			/*
			 * Check SIM card is inserted using Adb shell command
			 */

			try {
				String XP8SIMcard = JsonFileReaderAndWriter.PrimaryDevicesimAvailability();
				minWait();
				if(!XP8SIMcard.equals("")) {
					APP_LOGS.info("SIM Card is Available "+ XP8SIMcard);
				}
				else {
					test.log(LogStatus.SKIP, "SIM card is not inserted");
					Assert.fail();
				}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		/* public static String fetchDeviceProperty(String cmd) {

			  //  String cmd = "adb shell getprop gsm.version.baseband";
			  String value = null;
			  try {

			   Process child = Runtime.getRuntime().exec(cmd);
			   InputStream lsOut = child.getInputStream();
			   InputStreamReader r = new InputStream(lsOut);
			   BufferedReader in = new BufferedReader(r);
			   value=in.readLine();



			  } catch (IOException e) {
			   
			   e.printStackTrace();
			  }
			  return value;

			 }*/
		
		public boolean scrollToText(String text) {
			/*
			Method used to select an element on the page by scrolling the Scroll View/List View
			*/
			
			boolean check = false;
			try {		
				String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
				String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
				aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
				APP_LOGS.info("Searched application is found sucessfully : ");
				check = true;
				return check;
		}
			catch(Exception e) {
				return check;
				}
			}
		
		
		
		public boolean scrollAppList(String text) {
			/*
			Method used to open an application from Application List
			*/
			
			boolean check = false;
			try {		
				
				String scrollable = "new UiScrollable(new UiSelector().resourceId(\"com.android.launcher3:id/apps_list_view\")).scrollForward()";
				//String textElement = "//android.widget.TextView[@text(\'"+ text +"\''))";
				//WebElement applicationLocator = aDriver.findElementByXPath(textElement);
				while (!isTextPresent(text)) {
					aDriver.findElementByAndroidUIAutomator(scrollable);
					System.out.println((!isTextPresent(text)));
				}
				System.out.println((isTextPresent(text)));
				navigateUsingText(text);
				APP_LOGS.info("Searched application is found sucessfully : ");
				check = true;
				return check;
		}
			catch(Exception e) {
				return check;
				}
			}
		
		public boolean isTextPresent(String text) {
			/*
			Method used to find a text in the displayed screen (element of type android.widget.TextView)
			*/
			boolean check = false;
			WebElement textLocator;
			String textElement = "//android.widget.TextView[@text=\'"+ text +"\']";
			System.out.println(textElement);
			try {
					aDriver.findElementByXPath(textElement);
					check = true;
		}
			catch(Exception e){
				check = false;		
			}
			return check;
			
		}
		
		public boolean navigateUsingText(String text) {
			/*
			Method used to select an element on the page using the text attribute value
			*/
			boolean check = false;	
			try{
				
				String template = "new UiSelector().className(\"android.widget.TextView\")";
				String textElement = ".text(\""+ text+ "\")";
				aDriver.findElementByAndroidUIAutomator(template+textElement).click();
		
				check = true;
				return check;
		}
			catch(Exception e1) {
				return check;
			}
			}
		
		public boolean enterTextUsingTextSelector(String selector, String text) throws InterruptedException {
			/*
			Method used to enter text into a field with a given text
			*/
			boolean check = false;	
			try{
				
				String template = "new UiSelector().className(\"android.widget.EditText\")";
				String textElement = ".text(\""+ selector + "\")";
				WebElement element = aDriver.findElementByAndroidUIAutomator(template+textElement);
				element.clear();
				element.click();
				customWait(500);
				element.sendKeys(text);
				check = true;
				buttonSelectText("OK");
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return check;
				}
			catch(Exception e1) {
				APP_LOGS.error("FAILURE: Element not found or displayed: Object Locator : "+ selector);
				return check;
				}
		}
		
		
	
			
			
			public boolean buttonSelectText(String text){
				/*
				Method used to select a button using the text
				*/
				boolean check = false;	
				try{
					
					String template = "new UiSelector().className(\"android.widget.Button\")";
					String textElement = ".text(\""+ text+ "\")";
					aDriver.findElementByAndroidUIAutomator(template+textElement).click();
			
					check = true;
					return check;
			}
				catch(Exception e1) {
					return check;
				}
				}
			
			
			public boolean clickBtnWithText(String text) {
				boolean check = false;
				try {
					String clickable = "//android.widget.Button[@text=\'";
					String textElement = text+"\']";
					System.out.println(clickable+textElement);
					aDriver.findElementByXPath(clickable+textElement).click();
					//aDriver.
					//aDriver.findElementByAndroidUIAutomator("new UiSelector().childSelector()(new UiSelector().childSelector(new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")))").click();
					
					check = true;
					return check;
				}
				catch(Exception e) {
					return check;
					}
			}
			
			public boolean clickText(String text) {
				boolean check = false;
				try {
					String clickable = "//android.widget.TextView[@text=\'";
					String textElement = text+"\']";
					System.out.println(clickable+textElement);
					aDriver.findElementByXPath(clickable+textElement).click();
					//aDriver.
					//aDriver.findElementByAndroidUIAutomator("new UiSelector().childSelector()(new UiSelector().childSelector(new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")))").click();
					
					check = true;
					return check;
				}
				catch(Exception e) {
					return check;
					}
			}
			
			public boolean isElementExistWithText(String text) {
				boolean isPresent = false;
				String element = "//android.widget.TextView[@text=\'";
				String elementtext = (text+"\']");
				try {
				WebElement webelement = aDriver.findElementByXPath(element+elementtext);
				//	isPresent = e.isDisplayed();
					isPresent = true;
					
				} catch (Exception s) {
					isPresent = false;			
				
				
			}
				return isPresent;
			}
			
			public boolean scrollText(String text) {
				/*
				Method used to scroll to an element in the scrollable view
				*/
				
				boolean check = false;
				try {		
					String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
					String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
					aDriver.findElementByAndroidUIAutomator(scrollable+textElement);
					APP_LOGS.info("Searched application is found sucessfully : ");
					check = true;
					return check;
			}
				catch(Exception e) {
					return check;
					}
				}
			
			public void OCRScreencapture(String fileName) throws InterruptedException {
				//Capture required Screen shots for validation

				OCR.Read_File.takeScreenShotForOcr(fileName);
				Thread.sleep(2000);
				OCR.my_main.validate_Using_OCR(fileName+".png");
			}
			
			//This method will provide the user a searched  string from the adb log text file  
				public boolean searchStringOCR(String searchstring, String fileName) {

					boolean check = false;
					boolean check1=false;
					boolean check2=false;

					try {

						BufferedReader bf = new BufferedReader(new FileReader("src/test/resources/OCR_FILES/"+fileName+".txt"));
						int linecount = 0;
						String line;
						APP_LOGS.info("Searching for " + searchstring + " in file...");

						while (( line = bf.readLine()) != null){

							boolean indexfound = line.contains(searchstring);

							if (indexfound) {
								check=true;
								APP_LOGS.info("Word "+searchstring+" was found at position " + indexfound + " on line " + linecount);
								break;
							}
							else {
								// APP_LOGS.info("Word "+searchstring+" was not found at position " + indexfound + " on line " + linecount);
								check=false;
								linecount++;
							}
						}
						bf.close();
					}
					catch (IOException e) {
						APP_LOGS.info("IO Error Occurred: " + e.toString());
					}
					return check;
				}
				
				
				public String fetchOperatorName() throws FileNotFoundException, IOException, ParseException {
//					product_DUT.setText("XP5800");

					if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {



						return("ATT"); 
					}

					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

						return("Rogers"); 

					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

						return("Bell"); 

					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

						return("Telus"); 

					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {


						return("Sprint"); 
					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {


						return("SL"); 
					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-32.")) {

						return("ACG"); 

					}
					else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34.")) {

						return("USC"); 

					}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

						return("Verizon"); 

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00")) {

						return("MainLine"); 
					}
					return "GEN";

				}
				
				
				public static String getCurrentDate() {
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					System.out.println(dateFormat.format(date));
					return dateFormat.format(date);
				}
				
				public static String getCurrentTime() {
					
					  Date date = new Date();
					    String strDateFormat = "hh-mm-ss";
					    DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
					    String formattedDate= dateFormat.format(date);
					    System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
					return formattedDate;
					
				}
				
			
}