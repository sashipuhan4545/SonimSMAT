//Comment 1
//Comment 2
//Comment 3

//Comment 5
//Comment 4


package com.xp5S.util;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Properties;

import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.grid.common.exception.RemoteException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.android.library.AndroidWebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.aosp.Utils.Locators_DevSanity;
import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class BaseUtil extends CommonConfig {



	public static ExtentReports extent;

	public static ExtentTest test;
	public static Robot robot;

	public boolean check = false;


	public boolean ifAlertPresent() {
		try {
			getDriver().switchTo().alert();
			return true;
		} catch (NoAlertPresentException ex) {
			return false;
		}
	}

	public void executeJS(String code) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript(code, "");
	}

	public void clickOnElementUsingJS(WebElement e) {
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", e);
	}

	public boolean clickBtn(WebElement e) {
		try {
			e.click();
			return true;

		} catch (NoSuchElementException s) {
			return false;
		}

	}

	public void selectCheckbox(WebElement e) {
		if (!e.isSelected()) {
			e.click();
		}

	}

	public void deSelectCheckbox(WebElement e) {
		if (e.isSelected()) {
			e.click();
		}
	}

	public void launchApp(String pkgName, String actName) throws InterruptedException {
		customWait(4000);
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

		aDriver.startActivity(pkgN, actN);
		customWait(3000);
	}

	public boolean enterTextToInputField(WebElement e, String text) throws InterruptedException {

		if (e == null) {

			return false;
		} else {
			if (e.isDisplayed()) {
				Thread.sleep(500);
				e.clear();
				Thread.sleep(500);
				e.sendKeys(text);
				APP_LOGS.info("PASS: Element found and entered value successfully");
				return true;
			} else {
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : " + e);
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
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : " + e);
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
				APP_LOGS.info("FAILURE: Element not found or displayed: Object Locator : " + e);
				return false;
			}

		}

	}

	public boolean hoverOverElement(WebElement el) {
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

	public void selectDropDownByText(WebElement e, String text) {

		Select dropdown = new Select(e);
		dropdown.selectByVisibleText(text);
	}

	public void selectDropDownsByText(WebElement e, String text) {
		Select dropdown = new Select(e);
		if (dropdown.isMultiple()) {
			dropdown.deselectAll();
			dropdown.selectByVisibleText(text);

		} else {
			dropdown.selectByVisibleText(text);
		}

	}

	public void selectDropDownValueByIndex(WebElement e, int index) {

		Select dropdown = new Select(e);
		dropdown.selectByIndex(index);
	}

	public String getSelectedDropDownOptionText(WebElement e) {
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

	public static void waituntilnew(WebElement e, int timeinSeconds) {

		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.visibilityOf(e));

	}



	public void waitUntilElementclickable(WebElement e) {
		WebDriverWait wait = new WebDriverWait(aDriver, 30);
		WebElement elementClickable = wait.until(ExpectedConditions.elementToBeClickable(e));
		// WebElement elementClickable =
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(e));
	}

	public void waituntilnewElementtobeClickable(WebElement e, int timeinSeconds) {

		WebDriverWait wait = new WebDriverWait(getDriver(), timeinSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(e));

	}

	public void dragAndDrop(WebElement source, WebElement target) {
		(new Actions(getDriver())).dragAndDrop(source, target).perform();
	}

	public boolean isElementExist(WebElement e) {
		boolean isPresent = false;
		try {
			isPresent = e.isDisplayed();
		} catch (NoSuchElementException s) {
			isPresent = false;
		} catch (NullPointerException npe) {
			isPresent = false;
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

	public void maxWait() throws InterruptedException {
		Thread.sleep(12000);

	}




	public static void minWait() throws InterruptedException{

		Thread.sleep(1000);

	}

	public void minWait_SonimCare() throws InterruptedException {
		Thread.sleep(2500);
	}

	public static void customWait(long secs) throws InterruptedException {
		Thread.sleep(secs);
	}

	/*
	 * //This Method will clear the recentApp from the android mobile public void
	 * clearRecentApps() throws InterruptedException { customWait(1000);
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME); customWait(4000); try {
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH); customWait(2000);
	 * APP_LOGS.info("Pressed recent button sucessfully"); customWait(2000);
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU); customWait(2000);
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN); customWait(2000);
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER); customWait(2000);
	 * for (int iter = 0; iter <= 24; iter++) { //if
	 * (isElementExist(Locators_SonimCare.recentApp)) { if
	 * (isElementExist(Locators_SonimCare.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; }else if
	 * (isElementExist(Locators_SoundRec.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; } else if
	 * (isElementExist(Locators_FM.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; } else if
	 * (isElementExist(Locators_Safeguard.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; } else if
	 * (isElementExist(Locators_ContactTransfer.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; } else if
	 * (isElementExist(Locators_Warranty_Reg.recentApp)) {
	 * APP_LOGS.info("Recent Apps displayed sucessfully");
	 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR); customWait(1000);
	 * APP_LOGS.info("Clearing recent APPS: " + iter); continue; } else {
	 * APP_LOGS.info("No recent apps are displayed"); break; }
	 * 
	 * }
	 * 
	 * } catch (NoSuchElementException e) { e.printStackTrace(); } }
	 * 
	 */
	public void clearApp() throws InterruptedException {

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(2000);
			// APP_LOGS.info("Pressed Home button successfully");

			for (int iter = 0; iter <= 24; iter++) {
				if (isElementExist(Locators_SonimCare.SWR_RecentApp)) {
					APP_LOGS.info("Recent Apps displayed sucessfully");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					customWait(1000);
					APP_LOGS.info("Clearing recent APPS: " + iter);
					continue;
				} else {
					// APP_LOGS.info("No recent apps are displayed");
					break;
				}

			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void clearApp_SG() throws InterruptedException {

		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(4000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(2000);
			// APP_LOGS.info("Pressed Home button successfully");

			for (int iter = 0; iter <= 24; iter++) {
				if (isElementExist(Locators_Safeguard.SG_recentApp)) {

					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					customWait(1000);

					continue;
				} else {
					// APP_LOGS.info("No recent apps are displayed");
					break;
				}

			}

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	// This method will provide the user a searched string from the adb log text
	// file
	public boolean searchString(String searchstring, String fileName) {

		boolean check = false;
		boolean check1 = false;
		boolean check2 = false;

		try {

			BufferedReader bf = new BufferedReader(new FileReader("src/test/resources/adbLogs/" + fileName + ".txt"));
			int linecount = 0;
			String line;
			APP_LOGS.info("Searching for " + searchstring + " in file...");

			while ((line = bf.readLine()) != null) {

				boolean indexfound = line.contains(searchstring);

				if (indexfound) {
					check = true;
					APP_LOGS.info(
							"Word " + searchstring + " was found at position " + indexfound + " on line " + linecount);
					break;
				} else {
					// APP_LOGS.info("Word "+searchstring+" was not found at position " + indexfound
					// + " on line " + linecount);
					check = false;
					linecount++;
				}
			}
			bf.close();
		} catch (IOException e) {
			APP_LOGS.info("IO Error Occurred: " + e.toString());
		}
		return check;
	}

	/*	public static boolean searchString(String fileName,String phrase) throws IOException{
		boolean found=false;
		Scanner fileScanner = new Scanner(new File("src/test/resources/adbLogs/" + fileName + ".txt"));
	//	Scanner fileScanner = new Scanner(new File("C:\\Users\\sashi.p\\Desktop\\EXP\\sashi.txt"));

		int lineID = 0;
		//List lineNumbers = new ArrayList();
		Pattern pattern =  Pattern.compile(phrase,Pattern.CASE_INSENSITIVE);
		Matcher matcher = null;
		while(fileScanner.hasNextLine())
		{

			String line = fileScanner.nextLine();
			lineID++;
			matcher = pattern.matcher(line);
			if(matcher.find()){
				//lineNumbers.add(lineID);
				found=true;

			}


		}
		return found;
	}


	 */





	// This method will take adb log
	public static void startAdbLog(String fileName) throws AWTException, InterruptedException, FileNotFoundException, IOException, ParseException {

		//This method will take adb log


		customWait(2000);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec("adb -s "+Uid+" logcat -c");

			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -v time>src/test/resources/adbLogs/"+fileName+".txt \"");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Something goes Wrong!!!");   e.printStackTrace();  
		}


		/*customWait(1000);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader(); 
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			// Runtime.getRuntime().exec("adb logcat -c");
			  Thread.sleep(1000);
			Runtime.getRuntime().exec("cmd /C \"adb -s \""+Uid+"\" logcat -v time>src/test/resources/adbLogs/" + fileName + ".txt \"");
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Something goes Wrong!!!");
			e.printStackTrace();
		}*/


	}

	// This method will stop adb log

	public static void stopAdbLog() throws InterruptedException, IOException, ParseException {
		Thread.sleep(1000);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		Runtime.getRuntime().exec("taskkill /IM cmd.exe");

		/*
		 * robot.keyPress(KeyEvent.VK_CONTROL); robot.keyPress(KeyEvent.VK_C);
		 * robot.keyRelease(KeyEvent.VK_CONTROL); robot.keyRelease(KeyEvent.VK_C);
		 */
		APP_LOGS.info("Adb log stopped sucessfully ");
	}

	// This methods are used for verifying toast message

	public static String OCR(String ImagePath) {

		String result = null;
		File imageFile = new File(ImagePath);
		ITesseract instance = new Tesseract();

		try {
			result = instance.doOCR(imageFile);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			APP_LOGS.info("Not able to do");
		}

		return result;

	}

	//This method will take adb log
	public static void startLog(String fileName) throws AWTException, InterruptedException, FileNotFoundException, IOException, ParseException {

		customWait(2000);
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		try {
			APP_LOGS.info("Adb log started sucessfully ");
			Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" logcat -b all -v threadtime>src/test/resources/adbLogs/"+fileName+".txt \"");
			Thread.sleep(2000);
		}
		catch(Exception e) {
			System.out.println("Something goes Wrong!!!");   e.printStackTrace();  
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

	public String startRIL_Log_Call_Perfomrance_MT() throws AWTException, InterruptedException, IOException, ParseException {

		String Uid=JsonFileReaderAndWriter.ReadRefDeviceId();
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



	public static String captureScreenshot(String methodName) throws IOException {

		Calendar cal = new GregorianCalendar();
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int sec = cal.get(Calendar.SECOND);
		int min = cal.get(Calendar.MINUTE);
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.HOUR_OF_DAY);
		String mailscreenshotpath = null;

		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			mailscreenshotpath = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + methodName
					+ "_" + year + "_" + date + "_" + (month + 1) + "_" + day + "_" + min + "_" + sec + ".jpg";
			FileUtils.copyFile(scrFile, new File(mailscreenshotpath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailscreenshotpath;
	}

	/*
	 * public void get() throws TesseractException, IOException {
	 * 
	 * String test=OCR(image(driver)); }
	 * 
	 */

	public void softAssert_true(boolean check, String message) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(check, message);
		softAssert.assertAll();
	}

	public void softAssert_false() {
		SoftAssert softAssertFail = new SoftAssert();
		softAssertFail.fail();
		softAssertFail.assertAll();
	}

	public void softAssert_equals(String actual, String expected) {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(actual, expected);
		softAssert.assertAll();
	}

	public void clearRecentApp(WebElement app) throws InterruptedException {
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(4000);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(2000);
			APP_LOGS.info("Pressed recent button sucessfully");
			customWait(2000);
			for (int iter = 0; iter <= 24; iter++) {
				// if (isElementExist(Locators_SonimCare.recentApp)) {
				if (isElementExist(app)) {
					APP_LOGS.info("Recent Apps displayed sucessfully");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					customWait(1000);
					APP_LOGS.info("Clearing recent APPS: " + iter);
					continue;
				} else {
					APP_LOGS.info("No recent apps are displayed");
					break;
				}

			}

		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}

	public boolean longpress(int keyCode) {
		try {
			aDriver.longPressKeyCode(keyCode);
			return true;
		} catch (NoSuchElementException s) {
			return false;
		}
	}

	public void recordVideo(String TC_Name) throws InterruptedException, IOException, ParseException {
		String Uid=JsonFileReaderAndWriter.primaryDevIdReader();
		minWait();
		Runtime.getRuntime().exec("cmd /C \"adb -s "+Uid+" shell screenrecord mnt/sdcard/" + TC_Name + ".mp4 \"");
		customWait(3000);
	}

	public static String fetchDeviceProperty(String cmd) {

		// String cmd = "adb shell getprop gsm.version.baseband";
		String value = null;
		try {

			Process child = Runtime.getRuntime().exec(cmd);
			InputStream lsOut = child.getInputStream();
			InputStreamReader r = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(r);
			value = in.readLine();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;

	}

	public static void openReportPath(String path) throws IOException {

		Desktop desktop = Desktop.getDesktop();
		File dirToOpen = null;
		try {
			dirToOpen = new File(path);
			desktop.open(dirToOpen);
		} catch (IllegalArgumentException iae) {
			System.out.println("File Not Found");
		}

	}

	public void launchAppThroughABD(String adbCommand) throws InterruptedException, IOException {
		minWait();
		Runtime.getRuntime().exec(adbCommand);
		minWait();
	}

	public void clearRecentApps() throws InterruptedException {
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			customWait(2500);

			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);
			customWait(1500);
			if (!isElementExist(Locators_BaseUtil.noRecentApp)) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
				minWait();
				/*
				 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN); minWait();
				 * aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
				 */
				aDriver.findElementByAndroidUIAutomator(
						"new UiSelector().className(\"com.android.internal.widget.AccessibleTextView\").text(\"Remove all\")")
				.click();
				customWait(2000);
			}
		} catch (Exception e) {
			while (true) {
				aDriver.pressKeyCode(AndroidKeyCode.BACK);

				customWait(2000);
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_APP_SWITCH);

				customWait(1000);
				if (isElementExist(Locators_BaseUtil.recentApp)) {
					APP_LOGS.info("Recent Apps displayed sucessfully");
					aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_CLEAR);
					customWait(1000);
				} else {
					APP_LOGS.info("No recent apps are displayed");
					break;
				}
			}
		}
	}




	// This Method is used to scroll to any element
	public MobileElement ScrollToElement(MobileElement element, String str) {

		return ((AndroidElement) element).findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector())" + ".scrollIntoView(new UiSelector().text(\"" + str + "\"))");

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
		catch(NoSuchElementException e) {
			return check;
		}
	}


	public void navigateToSettingsFeature(WebElement option,String name) throws InterruptedException, IOException {
		/*
		 * select particular setting features
		 */
		SoftAssert Sa = new SoftAssert();
		try { 
			minWait();
			ScrollToElement(Locators_BaseUtil.SettingsList, name);
			minWait();
			String getName= option.getText();
			minWait();
			APP_LOGS.info("Settings Feature is displayed sucessfully " + getName);
			minWait();
			clickBtn(option);
			APP_LOGS.info("Settings Feature is selected sucessfully  " +  getName);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			APP_LOGS.info("No Element found.");
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Sa.fail();
		}
		Sa.assertAll();
	}


	public void setSleepMax() throws InterruptedException {
		/*
		 * Set sleep mode to maximum
		 */

		try {
			clickBtn(Locators_DevSanity.Sleep_Optn);
			minWait();
			for(int i=1; i<=8; i++) {
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			clickBtn(Locators_DevSanity.eightHrsSleep_Optn);
			test.log(LogStatus.INFO, " Sleep Mode is set to maximum");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			test.log(LogStatus.ERROR, " No such Element Found");
		}		
	}


	// Validates the presence of element
	public boolean validate_presenceOfElement(WebElement element, String elementName) throws InterruptedException {
		minWait();
		try {
			if (isElementExist(element)) {
				check = true;
				APP_LOGS.info(elementName + " is found succesfully");

			} else {
				APP_LOGS.info(elementName + " is not found");
				check = false;
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
		return check;
	}



	// To Navigate or launch any application
	public void launch_any_PTT_app(String pttApp_name) throws InterruptedException {
		minWait();
		switch (pttApp_name) {
		case "att":
			clickBtn(Locators_BaseUtil.ATT_PTT);
			APP_LOGS.info("Clicked on ATT_PTT successfully.");
			break;
		case "sprint":
			clickBtn(Locators_BaseUtil.sprint_PTT);
			APP_LOGS.info("Clicked on ATT_PTT successfully.");
			break;
		case "telus":
			clickBtn(Locators_BaseUtil.telusLink_PTT);
			APP_LOGS.info("Clicked on telusLink_PTT successfully.");
			break;
		case "rogers":
			clickBtn(Locators_BaseUtil.rogers_PTT);
			APP_LOGS.info("Clicked on rogers_PTT successfully.");
			break;
		}

	}

	// Launch and validate PTT (ATT)
	// Pre-condition: PTT should be assigned to programmable key and should be
	// configured.
	// This programm may not work with other PTT apps as "Reconnecting to server"
	// message may not be displayed. Needs Workaround.
	public void validate_PTT_Screen(WebElement ptt_element) throws InterruptedException, IOException, ParseException {

		String Uid=JsonFileReaderAndWriter.primaryDevIdReader(); 
		Runtime.getRuntime().exec("adb -s "+Uid+" shell input keyevent --longpress KEYCODE_PTT");
		minWait();
		if (validate_presenceOfElement(ptt_element, "Reconnecting message")) {
			APP_LOGS.info("PTT_application is launched successfully.");
			Assert.assertTrue(true);
		} else {
			APP_LOGS.info("PTT_application is not launched.");
			Assert.fail();
		}

	}







	//This method is used to navigate to notification screen only
	public void navigate_to_NotificationScreen() throws InterruptedException{
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_MENU);
			APP_LOGS.info("Pressed MENU button");
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_CENTER);
			APP_LOGS.info("You are in Notification screen");
			minWait();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
	}






	// Validates the presence of text of the element.
	public void validate_Displayed_text(WebElement element, String textToFind) throws InterruptedException{
		minWait();
		try {
			String text = element.getText();
			if(text.equalsIgnoreCase(textToFind)){
				check = true;
				APP_LOGS.info("Searched text is found succesfully");
				APP_LOGS.info("Searched string: "+textToFind);
				APP_LOGS.info("Found String: "+text);
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Mismatch in string comparison");
				Assert.fail();
			}

		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}


	}

	// Validates the presence of element 
	public void validate_presenceOfElement(WebElement element) throws InterruptedException{
		minWait();
		try {
			if(isElementExist(element)){
				check = true;
				APP_LOGS.info("Element is found succesfully");
				Assert.assertTrue(check);
			}else{
				APP_LOGS.info("Element is not found");
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
			// TODO: handle exception
		}
	}


	// Navigate or launch any application.
	// This method doesnt not launch PTT application.
	public void launch_an_app(String appName) throws InterruptedException {
		minWait();
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
		customWait(1000);
		aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
		APP_LOGS.info("HOme PAge");
		customWait(1000);
		switch (appName) {
		case "browser":
			if(isElementExist(Locators_BaseUtil.browser_App)) {
				clickBtn(Locators_BaseUtil.browser_App);
				APP_LOGS.info("Clicked on Browser successfully.");
			}
			else {
				clickBtn(Locators_BaseUtil.applications_icon);
				APP_LOGS.info("Clicked on applications_icon successfully.");
				customWait(1000);
				clickBtn(Locators_BaseUtil.browser_App);
				APP_LOGS.info("Clicked on Browser successfully.");
			}
			break;

		case "contacts":
			clickBtn(Locators_BaseUtil.contacts);
			APP_LOGS.info("Clicked on Contact successfully.");
			break;

		case "phone":
			clickBtn(Locators_BaseUtil.phone_DailerApp);
			APP_LOGS.info("Clicked on Phone successfully.");
			break;

		case "scout":
			clickBtn(Locators_BaseUtil.scoutApp_icon);
			APP_LOGS.info("Clicked on ScoutApps successfully.");
			break;

		case "music":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.music_icon);
			APP_LOGS.info("Clicked on Music successfully.");
			break;

		case "fm":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.FM_App);
			APP_LOGS.info("Clicked on FM Radio successfully.");
			break;

		case "fileExplorer":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			if(isElementExist(Locators_BaseUtil.FileExplorer_App)) {
				clickBtn(Locators_BaseUtil.FileExplorer_App);
				APP_LOGS.info("Clicked on FileExplorer_App successfully.");
			}
			else {
				clickBtn(Locators_BaseUtil.Files_App);
				APP_LOGS.info("Clicked on FileExplorer_App successfully.");
			}


			break;

		case "backUpReset":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.backUP_reset);
			APP_LOGS.info("Clicked on backUP_reset successfully.");
			break;

		case "downloads":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.downloads_icon);
			APP_LOGS.info("Clicked on downloads_icon successfully.");
			break;

		case "settings":
			clickBtn(Locators_BaseUtil.settings);
			APP_LOGS.info("Clicked on settings successfully.");
			break;

		case "camera":
			clickBtn(Locators_BaseUtil.cameraApp);
			APP_LOGS.info("Clicked on cameraApp successfully.");
			break;

		case "calendar":


			if(isElementExist(Locators_BaseUtil.CalenderApp)) {
				clickBtn(Locators_BaseUtil.CalenderApp);
				APP_LOGS.info("Clicked on CalendarApp successfully.");
			}
			else {
				clickBtn(Locators_BaseUtil.applications_icon);
				APP_LOGS.info("Clicked on applications_icon successfully.");
				customWait(1000);
				clickBtn(Locators_BaseUtil.CalenderApp);
				APP_LOGS.info("Clicked on Browser successfully.");
			}
			break;

		case "clock":
			clickBtn(Locators_BaseUtil.ToolsOptn);
			APP_LOGS.info("Clicked on Tools_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.clock_Icon);
			APP_LOGS.info("Clicked on clock_Icon successfully.");
			break;

		case "calculator":
			clickBtn(Locators_BaseUtil.ToolsOptn);
			APP_LOGS.info("Clicked on Tools_icon successfully.");
			minWait();
			clickBtn(Locators_BaseUtil.calciApp);
			APP_LOGS.info("Clicked on Calculator successfully.");
			break;

		case "soundRecorder":
			clickBtn(Locators_BaseUtil.ToolsOptn);
			APP_LOGS.info("Clicked on Tools_icon successfully.");
			minWait();
			if(isElementExist(Locators_BaseUtil.RecorderApp)) {
				clickBtn(Locators_BaseUtil.RecorderApp);
			}

			else {
				clickBtn(Locators_BaseUtil.RecorderAppSB);
			}
			APP_LOGS.info("Clicked on RecorderApp successfully.");
			break;

		case "gallery":
			clickBtn(Locators_BaseUtil.gallery_icon);
			APP_LOGS.info("Clicked on gallery_icon successfully.");
			break;

		case "messaging":
			clickBtn(Locators_BaseUtil.messaging_icon);
			APP_LOGS.info("Clicked on messaging_icon successfully.");
			break;

		case "fillmemory":
			clickBtn(Locators_BaseUtil.applications_icon);
			APP_LOGS.info("Clicked on applications_icon successfully.");
			minWait();
			for(int i=0;i<3;i++){
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_DPAD_DOWN);
			}
			if(isElementExist(Locators_BaseUtil.fillmemoryIcon)) {
				clickBtn(Locators_BaseUtil.fillmemoryIcon);
				APP_LOGS.info("Clicked on FileExplorer_App successfully.");
			}	

		}

	}




	public void checkSimCardAvailability() throws IOException, InterruptedException, ParseException {
		/*
		 * Check SIM card is inserted using Adb shell command
		 */

		String XP5SSIMcard =JsonFileReaderAndWriter.PrimaryDevicesimAvailability();
		minWait();
		if(!XP5SSIMcard.equals("")) {
			APP_LOGS.info("SIM Card is Available "+ XP5SSIMcard);
		}
		else {
			test.log(LogStatus.ERROR, "SIM card is not inserted");
			Assert.fail();
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
		} catch (NoSuchElementException e) {
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
				Assert.fail();
			}
		} catch (NoSuchElementException e) {
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

		} catch (NoSuchElementException e) {
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
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "No Such Element Found");
			Assert.fail();
		}
	}


	public boolean scrollWithText(String text) {
		/*
		Method used to select an element on the page by scrolling the Scroll View
		 */

		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\"))";
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}


	public boolean scrollListWithText(String text) {
		/*
		Method used to select an element on the page by scrolling the List View
		 */


		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().className(\"android.widget.ListView\"))";			
			String textElement = ".scrollIntoView(new UiSelector().text(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean navigateUsingText(String text) {


		/*
		Method used to select an element on the page using the text attribute value
		 */
		try{
			boolean check = false;	
			System.out.println("Navaigation");
			String template = "new UiSelector().className(\"android.widget.TextView\")";
			String textElement = ".text(\""+ text+ "\")";
			aDriver.findElementByAndroidUIAutomator(template+textElement).click();
			System.out.println("Navaigation3");
			check = true;
			return check;
		}
		catch(NoSuchElementException e1) {
			return check;
		}
	}


	public boolean scrollWithUIAutomatorElement(WebElement e) {
		/*
		Method used to select an element on the page by scrolling using the UIAutomator Element ---still in works
		 */

		boolean check = false;
		try {		
			String scrollable = "new UiScrollable(new UiSelector().className(\"android.widget.ScrollView\"))";
			String text = Locators_ScoutSanity.device_Information.getText();
			System.out.println(text);
			//aDriver.findElementByAndroidUIAutomator(UIScroll+elementType).click();
			check = true;
			return check;
		}
		catch(NoSuchElementException e2) {
			return check;
		}
	}


	//public String getSummaryText(WebElement e) {

	public boolean scrollText(String text) {
		/*
		Method used to select an element on the page by scrolling the Scroll View
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
		catch(NoSuchElementException e) {
			return check;
		}
	}

	public boolean clickBtnWithText(String text) {
		boolean check = false;
		try {
			String clickable = "//android.widget.LinearLayout/android.widget.Button[@text=\'";
			String textElement = text+"\']";
			System.out.println(clickable+textElement);
			aDriver.findElementByXPath("//android.widget.LinearLayout/android.widget.TextView[@text='Camera']").click();
			//aDriver.
			//aDriver.findElementByAndroidUIAutomator("new UiSelector().childSelector()(new UiSelector().childSelector(new UiSelector().className(\"android.widget.TextView\").text(\"Clock\")))").click();

			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
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

		} catch (NoSuchElementException s) {
			isPresent = false;			
		} catch (NullPointerException npe) {
			isPresent = false;
		}
		return isPresent;
	}

	public boolean checkBoxClick(WebElement element) {
		boolean check = false;
		try {
			element.click();
			//	isPresent = e.isDisplayed();
			check = true;

		} catch (NoSuchElementException s) {
			check = false;			
		} catch (NullPointerException npe) {
			check = false;
		}
		return check;
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
		catch(NoSuchElementException e){
			check = false;		
		}
		return check;

	}

	public void launchSettings() throws IOException, InterruptedException {
		/*
		 * Launch Settings Application
		 */
		minWait();
		customWait(500);
		Runtime.getRuntime().exec("adb shell am start -n com.android.settings/com.android.settings.Settings");
		minWait();
		APP_LOGS.info("Launched Settings sucessfully");
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

















}

