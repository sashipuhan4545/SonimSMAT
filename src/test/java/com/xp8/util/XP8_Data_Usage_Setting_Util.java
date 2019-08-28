package com.xp8.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.json.simple.parser.ParseException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.graphics.gui.JsonFileReaderAndWriter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import application.AllQA;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;

public class XP8_Data_Usage_Setting_Util extends BaseUtil {

	private static final String String = null;
	public static ExtentReports extent;
	public static ExtentTest test;

	public Process p;
	public boolean check = false;
	public boolean check1 = false;

	boolean value = false;
	public String p_Id = ""; // Primary Device Serial Number.
	public String r_Id = ""; // Reference Device Serial Number.
	public String p_b_No = ""; // Primary Device Build Number.
	public String r_b_No = ""; // Reference Device Build Number.
	public String pryNum = AllQA.PRIMARYDEVMDN; // Reference Device MDN.
	public String refNum = AllQA.REFERENCEDEVMDN; // Reference Device MDN.

	public void fetch_Devices_Details()
			throws InterruptedException, FileNotFoundException, IOException, ParseException {

		minWait();
		p_Id = JsonFileReaderAndWriter.primaryDevIdReader();
		r_Id = JsonFileReaderAndWriter.ReadRefDeviceId();
		p_b_No = JsonFileReaderAndWriter.primaryDevFirmwareReader();
		r_b_No = JsonFileReaderAndWriter.ReadRefDeviceFirmWare();
	}

	public void navigate_To_DataUsageSetting() {
		/*
		 * Navigate to data usage setting window
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.Network_Internet)) {
				clickBtn(Locators_DataUsageSetting.Network_Internet);
				clickBtn(Locators_DataUsageSetting.Data_usage);
			} else {
				clearRecentApps();
				launch_an_app("settings");
				navigate_To_DataUsageSetting();
			}
			APP_LOGS.info("Navigated to  data usage setting successfully.");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->navigate_To_DataUsageSetting");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->navigate_To_DataUsageSetting");
		}
	}

	public boolean enable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean enabled = false;
		try {
			scrollTo("Mobile network");
			if (Locators_DataUsageSetting.checkMobilenetwork.isEnabled()) {
				scrollTo("Airplane mode");
				clickBtn(Locators_DataUsageSetting.Airplanemode);
				enabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_AirplaneMode()");
		}
		return enabled;
	}

	public boolean disable_AirplaneMode() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			scrollTo("Mobile network");
			if (!Locators_DataUsageSetting.checkMobilenetwork.isEnabled()) {
				scrollTo("Airplane mode");
				clickBtn(Locators_DataUsageSetting.Airplanemode);
			}
			disabled = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> disable_AirplaneMode() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> disable_AirplaneMode()");
		}
		return disabled;
	}

	public void clickOn_DataUsage_SubModules(String subMenu) {
		/*
		 * Navigate to the sub menus of data usage setting window
		 */
		try {
			switch (subMenu) {
			case "data saver":
				scrollToElements(Locators_DataUsageSetting.Data_saver);
				clickBtn(Locators_DataUsageSetting.Data_saver);
				APP_LOGS.info("clicked on Data saver successfully.");
				break;
			case "mobile data usage":
				scrollToElements(Locators_DataUsageSetting.Mobile_data_usage);
				clickBtn(Locators_DataUsageSetting.Mobile_data_usage);
				APP_LOGS.info("clicked on Mobile data usage successfully.");
				break;
			case "billing cycle":
				scrollToElements(Locators_DataUsageSetting.Billing_cycle);
				clickBtn(Locators_DataUsageSetting.Billing_cycle);
				APP_LOGS.info("clicked on Billing cycle successfully.");
				break;
			case "wifi data usage":
				scrollToElements(Locators_DataUsageSetting.Wi_fi_data_usage);
				clickBtn(Locators_DataUsageSetting.Wi_fi_data_usage);
				APP_LOGS.info("clicked on Wifi data usage successfully.");
				break;
			case "network restrictions":
				scrollToElements(Locators_DataUsageSetting.Network_restrictions);
				clickBtn(Locators_DataUsageSetting.Network_restrictions);
				APP_LOGS.info("clicked on Network restrictions successfully.");
				break;
			case "mobile networks":
				clickBtn(Locators_DataUsageSetting.MoreOptions);
				minWait();
				clickBtn(Locators_DataUsageSetting.Mobile_networks);
				minWait();
				APP_LOGS.info("Clicked on mobile networks  successfully.");
				break;
			case "set data usage warning":
				scrollToElements(Locators_DataUsageSetting.set_data_usage_warning);
				clickBtn(Locators_DataUsageSetting.set_data_usage_warning);
				APP_LOGS.info("Clicked on set data usage warning successfully.");
				break;
			default:
				APP_LOGS.info("Invalid submodule");
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_DataUsage_SubModules()");

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_DataUsage_SubModules()");
		}
	}

	public void validate_DataUsageSetting(SoftAssert sa) {
		/*
		 * Validate data usage setting is displayed under setting window
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.Data_usage)) {
				APP_LOGS.info("DataUsage  Setting window is displayed successfully");
				sa.assertTrue(true, "DataUsage  Setting window is displayed successfully");
				test.log(LogStatus.PASS, "DataUsage  Setting window is displayed successfully");
			} else {
				APP_LOGS.info("DataUsage setting is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "DataUsage setting is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_DataUsageSetting()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_DataUsageSetting()");
		}
	}

	public void validate_DataUsageSetting_AirplaneMode(SoftAssert sa) {
		/*
		 * Validate data usage setting is displayed under setting window
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.Data_usage)) {
				APP_LOGS.info("Navigated to data usage setting successfully when airplane mode is enabled");
				sa.assertTrue(true, "Navigated to data usage setting successfully when airplane mode is enabled");
				test.log(LogStatus.PASS, "Navigated to data usage setting successfully when airplane mode is enabled");
			} else {
				APP_LOGS.info("Data usage setting navigation fails when airplane mode is enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Data usage setting navigation fails when airplane mode is enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> validate_DataUsageSetting_AirplaneMode()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> validate_DataUsageSetting_AirplaneMode()");
		}
	}

	public void verify_DataSaver(SoftAssert sa) {
		/*
		 * Verify data saver displayed under data usage setting window
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.Data_saver)) {
				APP_LOGS.info("\"Data Saver\" option is displayed under \"Data usage\" successfully");
				sa.assertTrue(true, "\"Data saver\" option is displayed under \"Data usage\" successfully");
				test.log(LogStatus.PASS, "\"Data Saver\" option is displayed under \"Data usage\" successfully");
			} else {
				APP_LOGS.info("Data saver option is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver option is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_DataSaver()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_DataSaver()");
			e.printStackTrace();
		}
	}

	public void clickOn_DataSaver() {
		/*
		 * Clicking on Data saver option
		 */
		try {
			scrollToElements(Locators_DataUsageSetting.Data_saver);
			clickBtn(Locators_DataUsageSetting.Data_saver);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_DataSaver()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_DataSaver()");
			e.printStackTrace();
		}
	}

	public void verify_DataSaver_IsDisabled_ByDefault(SoftAssert sa) {
		/*
		 * Validate data saver option is disabled by default
		 */
		try {
			if (Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("off")) {
				APP_LOGS.info("Data saver option is disabled by default verified successfully");
				sa.assertTrue(true, "Data saver option is disabled by default verified successfully");
				test.log(LogStatus.PASS, "Data saver option is disabled by default verified successfully");
			} else {
				APP_LOGS.info("Data saver option is not disabled by default");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver option is not disabled by default");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_DataSaver_IsDisabled_ByDefault()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_DataSaver_IsDisabled_ByDefault()");
			e.printStackTrace();
		}
	}

	public boolean enable_DataSaver() {
		/*
		 * Enable the data saver
		 */
		boolean enabled = false;
		try {
			if (Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DataUsageSetting.datasaver_switch);
			}
			enabled = Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("on");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> enable_DataSaver()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> enable_DataSaver()");
		}
		return enabled;
	}

	public boolean disable_DataSaver() {
		/*
		 * Enable the data saver
		 */
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.datasaver_switch);
			}
			disabled = Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("off");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> disable_DataSaver()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> disable_DataSaver()");
		}
		return disabled;
	}

	public void verify_DataSaver_IsEnabled(boolean res, SoftAssert sa) {
		/*
		 * Validate user can able to turn on data saver option under data usage setting
		 * window
		 */
		try {
			if (res) {
				APP_LOGS.info("Data saver is enabled successfully");
				sa.assertTrue(true, "Data saver  is enabled successfully");
				test.log(LogStatus.PASS, "Data saver  is enabled successfully");
			} else {
				APP_LOGS.info("Data saver is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_DataSaver_IsEnabled()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_DataSaver_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_IsDataSaverEnabled_NotificationBar(SoftAssert sa) {
		try {
			wait(Locators_DataUsageSetting.Datasaver_symbol,20);
			boolean res = Locators_DataUsageSetting.Datasaver_symbol.getText().equalsIgnoreCase("on");
			if (res) {
				APP_LOGS.info("Data saver enabled is verified in notification window");
				sa.assertTrue(true, "Data saver enabled is verified in notification window");
				test.log(LogStatus.PASS, "Data saver enabled is verified in notification window");
			} else {
				APP_LOGS.info("Data saver icon is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver icon is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_IsDataSaverEnabled_NotificationBar()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_IsDataSaverEnabled_NotificationBar()");
			e.printStackTrace();
		}
	}

	public void clickOn_UnrestrictedData() {
		try {
			clickBtn(Locators_DataUsageSetting.Unrestricted_data);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_UnrestrictedData()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_UnrestrictedData()");
		}
	}

	public void tapOn_ShowSystem() {
		try {
			new WebDriverWait(aDriver, 10).until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.Show_system))
					.click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> tapOn_ShowSystem()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> tapOn_ShowSystem()");
		}
	}

	public void clickBackButton(int number) {
		/*
		 * clicks on back button with iteration as user input
		 */
		try {
			for (int i = 0; i < number; i++) {
				minWait();
				aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
				minWait();
			}
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption occurs in clickBackButton() method");
			e.printStackTrace();
		}
	}

	public boolean enable_UnrestrictedData_App1() {
		boolean enabled = false;
		try {
			if (Locators_DataUsageSetting.Unrestricted_data_app_1.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DataUsageSetting.Unrestricted_data_app_1);
			}
			enabled = Locators_DataUsageSetting.Unrestricted_data_app_1.getText().equalsIgnoreCase("on");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> enable_UnrestrictedData_App1()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> enable_UnrestrictedData_App1()");
		}
		return enabled;
	}

	public void verify_UnrestrictedData_IsEnabled(boolean res, SoftAssert sa) {
		try {

			if (res) {
				APP_LOGS.info("Unrestricted data enabled successfully");
				sa.assertTrue(true, "Unrestricted data enabled successfully");
				test.log(LogStatus.PASS, "Unrestricted data enabled successfully");
			} else {
				APP_LOGS.info("Unrestricted data is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Unrestricted data is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_UnrestrictedData_IsEnabled()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_UnrestrictedData_IsEnabled()");
			e.printStackTrace();
		}

	}

	public void open_ResetOption() {
		try {
			scrollToElements(Locators_DataUsageSetting.system);
			clickBtn(Locators_DataUsageSetting.system);
			clickBtn(Locators_DataUsageSetting.Reset_options);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> open_ResetOption()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> open_ResetOption()");
			e.printStackTrace();
		}

	}

	public void RESET_SETTINGS(String reset) throws InterruptedException {
		try {
			switch (reset) {
			case "Reset Wi-Fi, mobile & Bluetooth":
				clickBtn(Locators_DataUsageSetting.Reset_Wifi_mobile_Bluetooth);
				clickBtn(Locators_DataUsageSetting.RESET_SETTINGS);
				new WebDriverWait(aDriver, 10)
						.until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.RESET_SETTINGS)).click();
				APP_LOGS.info("Reset Wi-Fi,mobile & Bluetooth Reset settings done successfully");
				break;

			case "Reset app preferences":
				clickBtn(Locators_DataUsageSetting.Reset_app_preferences);
				clickBtn(Locators_DataUsageSetting.RESET_APPS);
				APP_LOGS.info("Reset app preferences Reset setting done successfulyy");

				break;

			case "Erase all data":
				clickBtn(Locators_DataUsageSetting.erase_all_data_factoryreset);
				clickBtn(Locators_DataUsageSetting.RESET_PHONE);
				APP_LOGS.info("Phone Reset done successfully");
				break;
			default:
				APP_LOGS.info("Invalid selection");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> RESET_SETTINGS");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> RESET_SETTINGS()");
		}
	}

	public void clickOn_Wifi() throws InterruptedException {
		try {
			if (isElementExist(Locators_DataUsageSetting.Wi_Fi)) {
				clickBtn(Locators_DataUsageSetting.Wi_Fi);
			} else {
				scrollToElements(Locators_DataUsageSetting.Wi_Fi);
				clickBtn(multi_Loc_Strategy(Locators_DataUsageSetting.Wi_Fi, Locators_DataUsageSetting.wi_Fi_x2, null,
						null, null, 216, 288));
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Wifi()");
		}
	}

	public boolean wait(AndroidElement element, int waitTime) {
		boolean availability = false;
		try {
			new WebDriverWait(aDriver, waitTime).until(ExpectedConditions.visibilityOf(element));
			availability = true;
		} catch (NoSuchElementException e) {
			availability = false;
		}
		return availability;
	}

	public void enable_Wifi_ADBcommand() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell svc wifi enable");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void disable_Wifi_ADBcommand() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell svc wifi disable");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void enable_data_ADBcommand() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell svc enable enable");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void disable_data_ADBcommand() {
		try {
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell svc data disable");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public boolean enable_Wifi() {
		boolean enabled = false;
		try {
			minWait();
			if (isElementExist(Locators_DataUsageSetting.wifiConnectionSate)) {
				if (Locators_DataUsageSetting.wifiConnectionSate.getText().equalsIgnoreCase("off")) {
					clickBtn(Locators_DataUsageSetting.wifiConnectionSate);
				}
				enabled = (Locators_DataUsageSetting.wifiConnectionSate.getText().equalsIgnoreCase("on")) ? true
						: false;

			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => enable_Wifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enable_Wifi()");
			e.printStackTrace();
		}
		return enabled;
	}

	public void select_Wifi_SSID(String ssid) {
		/*
		 * Select IDC wifi which is available
		 */
		try {
				boolean checkSSID = scrollToText(ssid);
				while (checkSSID == false) {
					checkSSID = scrollToText(ssid);
				}
			if(checkSSID) {
				APP_LOGS.info("Wifi network displayed");
				System.out.println("Wifi network displayed");
			}
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR, "Error in the locators => select_Wifi_SSID()");
			ne.printStackTrace();
		} catch (Exception en) {
			test.log(LogStatus.ERROR, "Exception in functionality=> select_Wifi_SSID()");
			en.printStackTrace();
		}

	}

	public void forget_Wifi_SSID() {
		try {
			if (wait(Locators_DataUsageSetting.FORGET,10)) {
				clickBtn(Locators_DataUsageSetting.FORGET);
			}
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR, "Error in the locators => forget_Wifi_SSID()");
			ne.printStackTrace();
		} catch (Exception en) {
			test.log(LogStatus.ERROR, "Exception in functionality=> forget_Wifi_SSID()");
			en.printStackTrace();
		}

	}

	public void enter_WifiPwd(String pwd) throws InterruptedException, IOException {
		/*
		 * enter Password for SSID
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.wifi_enterPwd_index)) {
				enterTextToInputField(Locators_DataUsageSetting.wifi_enterPwd_index, pwd);
				clickBtn(Locators_DataUsageSetting.CONNECT_t1);
			}
		} catch (NoSuchElementException ne) {
			test.log(LogStatus.ERROR, "Error in the locators => enter_WifiPwd()");
			ne.printStackTrace();
		} catch (Exception en) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enter_WifiPwd()");
			en.printStackTrace();
		}
	}
	public void skip_NtwrkRst_Verify_DS_Disabled(SoftAssert sa) {
		/*
		 * reset network and validate data saver must be turned off
		 */

		try {
				APP_LOGS.info("'Perform network reset and Verify data saver is disabled' -> Not applicable for SPRINT Operator");
				test.log(LogStatus.INFO, "'Perform network reset and Verify data saver is disabled' -> Not applicable for SPRINT Operator");
				test.log(LogStatus.SKIP, "'Perform network reset and Verify data saver is disabled' -> Not applicable for SPRINT Operator");

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> skip_NtwrkRst_Verify_DS_Disabled()");
			e.printStackTrace();
		}
	}
	public void validate_Perform_Network_RESET_DataSaver_Off(SoftAssert sa) {
		/*
		 * reset network and validate data saver must be turned off
		 */

		try {
			wait(Locators_DataUsageSetting.datasaver_switch,20);
			if (Locators_DataUsageSetting.datasaver_switch.getText().equalsIgnoreCase("off")) {
				APP_LOGS.info("After Network resettings Data saver is disabled successfully");
				sa.assertTrue(true, "After Network resettings Data saver is disabled successfully");
				test.log(LogStatus.PASS, "After Network resettings Data saver is disabled successfully");
			} else {
				APP_LOGS.info("After Network resettings Data saver is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "After Network resettings Data saver is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> validate_Perform_Network_RESET_DataSaver_Off()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> validate_Perform_Network_RESET_DataSaver_Off()");
			e.printStackTrace();
		}
	}
	public void validate_Perform_Network_RESET_DataSaver_Off_SP(SoftAssert sa) {
		/*
		 * reset network and validate data saver must be turned off
		 */

		try {
			wait(Locators_DataUsageSetting.Datasaver_symbol,20);
			if (Locators_DataUsageSetting.Datasaver_symbol.getText().equalsIgnoreCase("off")) {
				APP_LOGS.info("After Network resettings Data saver is disabled successfully");
				sa.assertTrue(true, "After Network resettings Data saver is disabled successfully");
				test.log(LogStatus.PASS, "After Network resettings Data saver is disabled successfully");
			} else {
				APP_LOGS.info("After Network resettings Data saver is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "After Network resettings Data saver is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> validate_Perform_Network_RESET_DataSaver_Off_SP()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> validate_Perform_Network_RESET_DataSaver_Off_SP()");
			e.printStackTrace();
		}
	}



	public void verify_DataUsageGraph(SoftAssert sa) {
		/*
		 * Validate data usage graph
		 */
		try {
			wait(Locators_DataUsageSetting.Data_usage_graph, 20);
			if (isElementExist(Locators_DataUsageSetting.Data_usage_graph)
					&& isElementExist(Locators_DataUsageSetting.appList_app_1_title)
					&& isElementExist(Locators_DataUsageSetting.appList_app_1_dataused)) {
				APP_LOGS.info("Data usage graph is displayed successfully");
				sa.assertTrue(true, "Data usage graph is displayed successfully");
				test.log(LogStatus.PASS, "Data usage graph is displayed successfully");
			} else {
				APP_LOGS.info("DataUsage Graph is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, "DataUsage Graph is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_DataUsageGraph()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_DataUsageGraph()");
		}
	}

	public void verify_DataUsageAppListWithAppData(SoftAssert sa) {
		/*
		 * Validate data usage application list with amount of data used are displayed
		 */
		try {
			wait(Locators_DataUsageSetting.appList_app_1_title, 20);
			if (isElementExist(Locators_DataUsageSetting.appList_app_1_title)
					&& isElementExist(Locators_DataUsageSetting.appList_app_1_dataused)) {
				APP_LOGS.info(" Mobile data used applications are displayed successfully");
				sa.assertTrue(true, " Mobile data used applications are displayed successfully");
				test.log(LogStatus.PASS, " Mobile data used applications are displayed successfully");
			} else {
				APP_LOGS.info(" Mobile data used applications are not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, " Mobile data used applications are not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> verify_DataUsageAppListWithAppData()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> verify_DataUsageAppListWithAppData()");
		}
	}

	public void clickOn_QuickSettings() {
		/*
		 * Get settings option from notification window
		 */
		try {
			minWait();
			clickBtn(Locators_DataUsageSetting.openSettings);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_QuickSettings()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_QuickSettings()");
		}
	}

	public void clickOn_RSSIIcon() {
		/*
		 * Get settings option from notification window
		 */
		try {
			clickBtn(Locators_DataUsageSetting.openSettings);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_RSSIIcon()");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_RSSIIcon()");
		}

	}

	public void dragNotification_Twice() {
		/*
		 * Get settings option from notification window
		 */
		try {
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 10 10 10 1000");minWait();
			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Did not get Notification bar");
			e.printStackTrace();
		}
	}

	public void swipe_Left() {
		/*
		 * Get settings option from notification window
		 */
		try {
			 Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 800 400 400 400");
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Swipe failed");
			System.out.println("Swipe failed");
			e.printStackTrace();
		}
	}

	public void clickOn_Edit() {
		try {
			clickBtn(Locators_DataUsageSetting.pencil);
			System.out.println("Pencil");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> clickOn_Edit()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_Edit()");
			e.printStackTrace();
		}
	}

	public void reset_Icons() {
		try {
			clickBtn(Locators_DataUsageSetting.MoreOptions);
			minWait();
			System.out.println("More options");
			clickBtn(Locators_DataUsageSetting.Reset);
			System.out.println("Reset");

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> reset_Icons()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> reset_Icons()");
			e.printStackTrace();
		}
	}

	public void drag_Drop(AndroidElement source, AndroidElement target) {
		TouchAction action = new TouchAction(aDriver);
		action.longPress(source, 10000).moveTo(target).release().perform();	
	}

	public void move_DataSaverIcon() {
		try {
			scrollToElements(Locators_DataUsageSetting.Data_Saver_icon);
	//		clickBtn(Locators_DataUsageSetting.Data_Saver_icon);
			drag_Drop(Locators_DataUsageSetting.Data_Saver_icon, Locators_DataUsageSetting.Cast_icon);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> move_DataSaverIcon()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> move_DataSaverIcon()");
			e.printStackTrace();
		}
	}

	public String extract_Numerics(String stringValue) {
		String numericValue = "";
		for (int i = 0; i < stringValue.length(); i++) {
			if (stringValue.charAt(i) >= '0' && stringValue.charAt(i) <= '9')
				numericValue = numericValue + stringValue.charAt(i);
		}
		return numericValue;
	}

	public String add_MB(String oldMBValue, int value) {
		int in = Integer.parseInt(oldMBValue) + value;
		return String.valueOf(in);
	}

	public void getNotificationWindow() {
		/*
		 * Get notification window to turn on data
		 */
		try {
			minWait();
			Runtime r = Runtime.getRuntime();
			r.exec("adb -s" + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
			r.exec("adb -s" + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
			r.exec("adb -s" + p_Id + " shell input swipe 800 400 400 400");
			System.out.println("performed swipe action");
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.ERROR, "Did not get notification bar");
		}
	}
	public void clickon_ConnectedDevices(){

		try {
				scrollToElements(Locators_DataUsageSetting.Connected_devices);
				if(isElementExist(Locators_DataUsageSetting.Connected_devices)) {
					clickBtn(Locators_DataUsageSetting.Connected_devices);
				}else {
					scrollToText("Connected devices");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickon_ConnectedDevices()");
			e.printStackTrace();
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickon_ConnectedDevices()");
			e.printStackTrace();
		}
	}
	public boolean disable_MobileData() {
		/*
		 * disable data from data usage window
		 */
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.mobile_data_State.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.mobile_data_State);
				if(wait(Locators_DataUsageSetting.OK,10)) {
					clickBtn(Locators_DataUsageSetting.OK);
				}
			}
			disabled = true;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_MobileData() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_MobileData()");
			e.printStackTrace();
		}
		return disabled;
	}

	public boolean enable_MobileData() {
		/*
		 * Enable mobile data from data usage window
		 */
		boolean enabled = false;
		try {
			if (Locators_DataUsageSetting.mobile_data_State.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DataUsageSetting.mobile_data_State);
			}
			enabled = Locators_DataUsageSetting.mobile_data_State.getText().equalsIgnoreCase("on");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_MobileData() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_MobileData()");

		}
		return enabled;
	}

	public boolean search_MobileDataIcon() throws InterruptedException, IOException {
		/*
		 * Search mobile data icon
		 */
		boolean enabled = false;
		try {
			minWait();
			enabled = (isElementExist(Locators_DataUsageSetting.mobileDataState_symbol)) ? true : false;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->search_MobileDataIcon() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->search_MobileDataIcon()");
		}
		System.out.println("Mobile data availability = " + enabled);
		return enabled;
	}

	public boolean search_DataSaverIcon() throws InterruptedException, IOException {
		/*
		 * Enable data from notification window
		 */
		boolean enabled = false;
		try {
			
			enabled =wait(Locators_DataUsageSetting.Datasaver_symbol,5);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> search_DataSaverIcon() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> search_DataSaverIcon()");
			e.printStackTrace();

		}
		System.out.println("Data saver availability = " + enabled);
		return enabled;
	}

	public boolean enable_MobileData_FromNotificationWindow() throws InterruptedException, IOException {
		/*
		 * Enable data from notification window
		 */
		boolean enabled = false;
		try {
			minWait();
			if (Locators_DataUsageSetting.mobileDataState_symbol.getText().equalsIgnoreCase("off")) {
				System.out.println("Mobile data state = " + Locators_DataUsageSetting.mobileDataState_symbol.getText());
				clickBtn(Locators_DataUsageSetting.mobileDataState_symbol);
				minWait();
				enabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_MobileData_FromNotificationWindow() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->enable_MobileData_FromNotificationWindow()");
		}
		System.out.println("Mobile data state = " + Locators_DataUsageSetting.mobileDataState_symbol.getText());
		return enabled;
	}

	public void tap_TURNOFF() throws IOException {
		Runtime.getRuntime().exec("adb -s" + p_Id + " shell input tap 846 1286");
	}

	public void clickOn_CANCEL() throws IOException {
		Runtime.getRuntime().exec("adb shell input tap 578 1283");
	}

	public boolean disable_MobileData_FromNotificationWindow() {
		/*
		 * disable data from notification window
		 */
		boolean disabled = false;
		try {
			minWait();
			if (Locators_DataUsageSetting.mobileDataState_symbol.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.mobileDataState_symbol);
				wait(Locators_DataUsageSetting.TURN_OFF, 10);
				if (isElementExist(Locators_DataUsageSetting.TURN_OFF)) {
					clickBtn(Locators_DataUsageSetting.TURN_OFF);
				} else {
					tap_TURNOFF();
				}
			}
			disabled = true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_MobileData_FromNotificationWindow() ");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->disable_MobileData_FromNotificationWindow()");
			e.printStackTrace();
		}
		System.out.println("disabled = " + disabled);
		return disabled;
	}

	public WebElement webDriverWait(AndroidElement ele, int seconds) {
		WebElement elementFound;
		elementFound = new WebDriverWait(aDriver, seconds).until(ExpectedConditions.visibilityOf(ele));

		return elementFound;
	}
	public void validate_MobileData_IsEnabled_When_DSEnabled(boolean res,SoftAssert sa) throws InterruptedException {
		/*
		 * Validate enable disable cellular data
		 */
		try {
			if (res) {
				APP_LOGS.info("Data saver is enabled and Mobile data is enabled successfully");
				sa.assertTrue(true, "Data saver is enabled and Mobile data is enabled successfully" );
				test.log(LogStatus.PASS, "Data saver is enabled and Mobile data is enabled successfully");
			} else {
				APP_LOGS.info("Mobile data is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_MobileData_IsEnabled_When_DSEnabled() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> validate_MobileData_IsEnabled_When_DSEnabled()");
			e.printStackTrace();
		}

	}
	public void validate_MobileData_IsDisabled_When_DSEnabled(boolean res,SoftAssert sa) throws InterruptedException {
		/*
		 * Validate enable disable cellular data
		 */
		try {
			if (res) {
				APP_LOGS.info("Data saver is enabled and Mobile data is disabled successfully");
				sa.assertTrue(true, "Data saver is enabled and Mobile data is disabled successfully" );
				test.log(LogStatus.PASS, "Data saver is enabled and Mobile data is disabled successfully");
			} else {
				APP_LOGS.info("Mobile data is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_MobileData_IsDisabled_When_DSEnabled() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> validate_MobileData_IsDisabled_When_DSEnabled()");
			e.printStackTrace();
		}

	}
	public void validate_MobileData_IsEnabled(boolean res, String loc, SoftAssert sa) throws InterruptedException {
		/*
		 * Validate enable disable cellular data
		 */
		try {
			if (res) {
				APP_LOGS.info("Mobile data is enabled successfully from  " + loc);
				sa.assertTrue(true, "Mobile data is enabled successfully from " + loc);
				test.log(LogStatus.PASS, "Mobile data is enabled successfully from " + loc);
			} else {
				APP_LOGS.info("Mobile data is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_MobileData_IsEnabled() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->validate_MobileData_IsEnabled()");
			e.printStackTrace();
		}

	}

	public void validate_MobileData_IsDisabled(boolean res, String loc, SoftAssert sa) throws InterruptedException {
		/*
		 * Validate enable disable cellular data
		 */
		try {
			if (res) {
				APP_LOGS.info("Mobile data is disabled successfully from " + loc);
				sa.assertTrue(true, "Mobile data is disabled successfully from " + loc);
				test.log(LogStatus.PASS, "Mobile data is disabled successfully from " + loc);
			} else {
				APP_LOGS.info("Mobile data is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_MobileData_IsDisabled() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> validate_MobileData_IsDisabled()");
			e.printStackTrace();
		}

	}

	public void verify_MobileData_IsEnabled_AftLimitReached(SoftAssert sa) throws InterruptedException {
		/*
		 * Validate enable disable cellular data
		 */
		try {
			if (Locators_DataUsageSetting.mobile_data_State.getText().equalsIgnoreCase("on")) {
				APP_LOGS.info("Mobile data is enabled successfully after reaching data limit  ");
				sa.assertTrue(true, "Mobile data is enabled successfully after reaching data limit  ");
				test.log(LogStatus.PASS, "Mobile data is enabled successfully after reaching data limit  ");
			} else {
				APP_LOGS.info("Mobile data is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_MobileData_IsEnabled_AftLimitReached() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> verify_MobileData_IsEnabled_AftLimitReached()");
			e.printStackTrace();
		}

	}

	public String setDataWarning_From_DUM(String dataSize, String gbOrMb) throws InterruptedException {
		String data = null;
		try {
			enterTextToInputField(Locators_DataUsageSetting.setdata, dataSize);
			set_GB_MB(gbOrMb);
			clickBtn(Locators_DataUsageSetting.SET);
			data = Locators_DataUsageSetting.fixed_DataWarning_DataLimit.getText();

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->setDataWarning_From_DUM() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->setDataWarning_From_DUM()");

		}

		return data;
	}

	public void set_DataWarning(String dataSize, String gbOrMb) throws InterruptedException {
		try {
			clickBtn(Locators_DataUsageSetting.Data_warning);
			enterTextToInputField(Locators_DataUsageSetting.setdata, dataSize);
			if (isElementExist(Locators_DataUsageSetting.GByteMByte)) {
				set_GB_MB(gbOrMb);
			}
			if (wait(Locators_DataUsageSetting.SET, 10)) {
				clickBtn(Locators_DataUsageSetting.SET);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> set_DataWarning() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> set_DataWarning()");
			e.printStackTrace();
		}

	}

	public void set_DataLimit(String dataSize, String gbOrMb) throws InterruptedException {
		try {

			clickBtn(Locators_DataUsageSetting.Data_limit);
			enterTextToInputField(Locators_DataUsageSetting.setdata, dataSize);
			set_GB_MB(gbOrMb);
			clickBtn(Locators_DataUsageSetting.SET);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> set_DataLimit() ");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> set_DataLimit()");
			e.printStackTrace();
		}

	}

	public void clickOn_BackBtn() {
		try {
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_BACK);
			minWait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void validate_SetDataWarning(String excelData, SoftAssert sa, String unit) {
		try {
			wait(Locators_DataUsageSetting.fixed_DataWarning_DataLimit, 10);

			String actualString = Locators_DataUsageSetting.fixed_DataWarning_DataLimit.getText();
			;

			// String arr[] = actualString.split("/", 2);
			String fixedWarning = actualString;
			int expected = Integer.parseInt(excelData.substring(0, 3)); // value taken from excel sheet
			int actual = Integer.parseInt(fixedWarning.substring(0, 3)); // value coming from mobile
			System.out.println("Excel value = " + expected + "    Mobile value = " + actual);

			if (actual == expected) {
				APP_LOGS.info("Data warning fixed successfully at " + unit + " rates");
				sa.assertTrue(true, "Data warning fixed successfully  at " + unit + " rates");
				test.log(LogStatus.PASS, "Data warning fixed successfully  at " + unit + " rates");

			} else {
				APP_LOGS.info("Data warning is not fixed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data warnig is not fixed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SetDataWarning()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_SetDataWarning()");
			e.printStackTrace();
		}
	}

	public void validate_SetDataLimit(String excelData, SoftAssert sa, String unit) {
		try {

			String actualString = Locators_DataUsageSetting.fixed_DataWarning_DataLimit.getText();
			;

			String arr[] = actualString.split("/", 2);
			String fixedLimit = arr[1].trim();
			System.out.println("Input excel data -> expected value = " + excelData.substring(0, 3));
			System.out.println("Output data taken from mobile -> actual value = " + fixedLimit.substring(0, 3));
			int expected = Integer.parseInt(excelData.substring(0, 3)); // value taken from excel sheet
			int actual = Integer.parseInt(fixedLimit.substring(0, 3)); // value coming from mobile

			System.out.println("Excel value = " + expected + "    mobile value = " + actual);

			if (actual == expected) {
				APP_LOGS.info("Data limit fixed at " + unit + " rates");
				sa.assertTrue(true, "Data limit fixed at " + unit + " rates");
				test.log(LogStatus.PASS, "Data limit fixed at " + unit + " rates");

			} else {
				APP_LOGS.info("Data limit is not fixed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data limit is not fixed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_SetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->validate_SetDataLimit()");
			e.printStackTrace();
		}
	}

	/*
	 * public void validate_SetDataLimit_From_NotificationWindow(String data, String
	 * gbOrMb, SoftAssert sa) { try { clickOn_QuickSettingsMenu();
	 * clickBtn(Locators_DataUsageSetting.Network_Internet);
	 * clickBtn(Locators_DataUsageSetting.Data_usage);
	 * clickBtn(Locators_DataUsageSetting.Billing_cycle); enable_SetDataLimit();
	 * setDataLimit(data, gbOrMb);
	 * 
	 * String arr[] = actualString.split("/", 2); String fixedLimit = arr[1].trim();
	 * int expected = Integer.parseInt(data.substring(0, 3)); // value taken from
	 * excel sheet int actual = Integer.parseInt(fixedLimit.substring(0, 3)); //
	 * value coming from mobile
	 * 
	 * System.out.println("Excel value = " + expected + "    mobile value = " +
	 * actual);
	 * 
	 * if (actual == expected) { APP_LOGS.info("Data limit fixed = " + actual +
	 * gbOrMb); sa.assertTrue(true, "Data limit fixed = " + actual + gbOrMb);
	 * test.log(LogStatus.PASS, "Data limit fixed " + actual + gbOrMb);
	 * 
	 * } else { APP_LOGS.info("Data limit  cannot be fixed"); sa.fail();
	 * test.log(LogStatus.FAIL, "Data limit cannot be fixed"); } } catch
	 * (org.openqa.selenium.NoSuchElementException e) {
	 * 
	 * test.log(LogStatus.ERROR, "Error in the locators->validateSetDataLimit()");
	 * e.printStackTrace();
	 * 
	 * } catch (Exception e) { test.log(LogStatus.ERROR,
	 * "Exeption in ->validateSetDataLimit()"); e.printStackTrace(); } }
	 * 
	 */ public void set_DataUsageCycle(String dataSize) throws InterruptedException {
		try {
			clickBtn(Locators_DataUsageSetting.Billing_cycle);
			scrollToElement(Locators_DataUsageSetting.setdata);
			System.out.println(isElementExist(Locators_DataUsageSetting.setdata));
			minWait();
			clickBtn(Locators_DataUsageSetting.SET);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> set_DataUsageCycle()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> set_DataUsageCycle()");
			e.printStackTrace();
		}
	}

	public void clickOn_GooglePlayStore() {
		try {
			scrollToElements(Locators_DataUsageSetting.Google_Play_store);
			clickBtn(Locators_DataUsageSetting.Google_Play_store);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_GooglePlayStore()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_GooglePlayStore()");
			e.printStackTrace();
		}
	}

	public void clickOn_YouTube() {
		try {
			scrollTo("YouTube");
			scrollToElements(Locators_DataUsageSetting.You_Tube);
			clickBtn(Locators_DataUsageSetting.You_Tube);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_YouTube()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in -> clickOn_YouTube()");
			e.printStackTrace();
		}
	}

	public void clickOn_NetworkAndInternet() {
		try {
		
			scrollToElements(Locators_DataUsageSetting.NetworkAndInternet_tc1);
			if(isElementExist(Locators_DataUsageSetting.NetworkAndInternet_tc1)) {
			clickBtn(multi_Loc_Strategy(Locators_DataUsageSetting.NetworkAndInternet_tc1,
					Locators_DataUsageSetting.NetworkAndInternet_tc2, Locators_DataUsageSetting.NetworkAndInternet_x3,
					null, null, 414, 1004));
			}else {
				boolean ni=scrollToText("Network & Internet");
				while(ni==false) {
					ni=scrollToText("Network & Internet");
				}
			}
			minWait();
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => clickOn_NetworkAndInternet()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> clickOn_NetworkAndInternet()");
			e.printStackTrace();
		}
	}

	public String removeSpl(String input) {
		String out = "";
		if (input.contains(".")) {
			System.out.println(input.substring(0, input.length() - 2));
			out = input.substring(0, input.length() - 2);
		} else {
			out = input;
		}
		return out;
	}

	public String change_DataUsageCycle(String dateValue) {
		String fixedDate = null;
		try {
			clickBtn(Locators_DataUsageSetting.numberPicker);
			enterTextToInputField(Locators_DataUsageSetting.numberPicker, dateValue);
			clickBtn(Locators_DataUsageSetting.SET);
			fixedDate = Locators_DataUsageSetting.monthlyDate.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->change_DataUsageCycle()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->change_DataUsageCycle()");
			e.printStackTrace();
		}
		return fixedDate;
	}

	public void validate_DataUsageCycle(String res, SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (Locators_DataUsageSetting.monthlyDate.getText().equalsIgnoreCase(res)) {
				APP_LOGS.info("Data usage cycle changed successfully");
				sa.assertTrue(true, "Data usage cycle changed successfully");
				test.log(LogStatus.PASS, "Data usage cycle changed successfully");
			} else {
				APP_LOGS.info("Failed -> Data usage cycle is not changet");
				sa.fail();
				test.log(LogStatus.FAIL, "Failed -> Data usage cycle is not changet");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_DataUsageCycle()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_DataUsageCycle()");
			e.printStackTrace();
		}

	}

	public void clickOn_DataUsage() {
		try {
			scrollToElements(Locators_DataUsageSetting.Data_usage);
			clickBtn(Locators_DataUsageSetting.Data_usage);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->clickOn_DataUsage()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in ->clickOn_DataUsage()");
			e.printStackTrace();
		}

	}

	public void verify_ProgressBar_IsDisplayed(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (Locators_DataUsageSetting.dataUsageProgressBar.isDisplayed()) {
				APP_LOGS.info("Data usage progress bar is displayed successfully");
				sa.assertTrue(true, "Data usage progress bar is displayed successfully");
				test.log(LogStatus.PASS, "Data usage progress bar is displayed successfully");
			} else {
				APP_LOGS.info("Data usage progress bar is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data usage progress bar is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_ProgressBar_IsDisplayed()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_ProgressBar_IsDisplayed()");
			e.printStackTrace();
		}

	}

	public void verify_DataWarningAndDataLimit_AreDisplayed(String warning, String limit, SoftAssert sa)
			throws InterruptedException, IOException {
		try {
			boolean dataWarning = false, dataLimit = false;
			String dataWarningAndDataLimit = Locators_DataUsageSetting.fixed_DataWarning_DataLimit.getText();
			dataWarning = dataWarningAndDataLimit.contains(warning);
			dataLimit = dataWarningAndDataLimit.contains(limit);
			if (dataWarning && dataLimit) {

				String text = Locators_DataUsageSetting.fixed_DataWarning_DataLimit.getText();
				APP_LOGS.info("Data warning and Data limit values are displayed => " + text);
				sa.assertTrue(true, "Data warning and Data limit values are displayed => " + text);
				test.log(LogStatus.PASS, "Data warning and Data limit values are displayed => " + text);
			} else {
				APP_LOGS.info("Data warning and Data Limit are not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data warning and Data Limit are not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_DataWarningAndDataLimit_AreDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_DataWarningAndDataLimit_AreDisplayed()");
			e.printStackTrace();
		}

	}

	public void verify_DateCycle_IsDisplayed(String recycleDate, SoftAssert sa)
			throws InterruptedException, IOException {
		try {
			boolean dateValue = false;
			String text = Locators_DataUsageSetting.dateCycle.getText();
			dateValue = text.contains(recycleDate);
			text = text.substring(0, 8);
			if (dateValue) {
				APP_LOGS.info("Date cycle range displayed successfully => " + text);
				sa.assertTrue(true, "Date cycle range displayed successfully => " + text);
				test.log(LogStatus.PASS, "Date cycle range displayed successfully => " + text);
			} else {
				APP_LOGS.info("Date cycle range is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Date cycle range is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_DateCycle_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_DateCycle_IsDisplayed()");
			e.printStackTrace();
		}

	}

	public void verify_DataUsageValue_IsDisplayed(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (Locators_DataUsageSetting.dataUsed.isDisplayed()) {
				String text = Locators_DataUsageSetting.dataUsed.getText();
				APP_LOGS.info("Amount of data used displayed successfully =>" + text);
				sa.assertTrue(true, "Amount of data used displayed successfully =>" + text);
				test.log(LogStatus.PASS, "Amount of data used displayed successfully =>" + text);
			} else {
				APP_LOGS.info("Amount of data used is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Amount of data used is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_DataUsageValue_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_DataUsageValue_IsDisplayed()");
			e.printStackTrace();
		}

	}

	public void verify_AvailabilityOfBackgroundData(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (isElementExist(Locators_DataUsageSetting.background_data)) {
				APP_LOGS.info("Background data is displayed successfully");
				sa.assertTrue(true, "Background data is displayed successfully");
				test.log(LogStatus.PASS, "Background data is displayed successfully");
			} else {
				APP_LOGS.info("Background data option is not available");
				test.log(LogStatus.ERROR, "Background data option is not available");
				sa.fail();
				test.log(LogStatus.FAIL, "Background data option is not available");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_AvailabilityOfBackgroundData()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_AvailabilityOfBackgroundData()");
			e.printStackTrace();
		}

	}

	public void verify_AppDataUsageDetails(SoftAssert sa) throws InterruptedException {
		/*
		 * Validates Data usage cycle, Total, Foreground, Background, Background data
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.total) && isElementExist(Locators_DataUsageSetting.foreground)
					&& isElementExist(Locators_DataUsageSetting.background)
					) {
				String s = "App data usage details displayed successfully";
				APP_LOGS.info(s);
				sa.assertTrue(true, s);
				test.log(LogStatus.PASS, s);
			} else {
				APP_LOGS.info("App data usage details is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL,"App data usage details is not displayed ");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_AppDataUsageDetails()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> verify_AppDataUsageDetails()");
			e.printStackTrace();
		}
	}

	public void verify_TFB_IsDisplayed(SoftAssert sa) throws InterruptedException {
		/*
		 * Validates Total, Foreground, Background data consumption
		 */

		try {
			if (isElementExist(Locators_DataUsageSetting.total) && isElementExist(Locators_DataUsageSetting.foreground)
					&& isElementExist(Locators_DataUsageSetting.background)) {
				String totalData = Locators_DataUsageSetting.total.getText(),
						foregroundData = Locators_DataUsageSetting.foreground.getText(),
						backgroundData = Locators_DataUsageSetting.background.getText();
				APP_LOGS.info(
						"1.Total data consumption  2.Foreground data consumption  3.Background data consumption are displayed under 'App data usage'");
				sa.assertTrue(true,
						"1.Total data consumption 2.Foreground data consumption 3.Background data consumption are displayed under 'App data usage'");
				test.log(LogStatus.PASS,
						"1.Total data consumption 2.Foreground data consumption 3.Background data consumption are displayed under 'App data usage'");
			} else {
				APP_LOGS.info(
						"1.Total data consumption 2.Foreground data consumption 3.Background data consumption are not displayed");
				sa.fail();
				test.log(LogStatus.FAIL,
						"1.Total data consumption 2.Foreground data consumption 3.Background data consumption are not displayed");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_TFB_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> verify_TFB_IsDisplayed()");
			e.printStackTrace();
		}
	}

	public void validate_Wifi_Data_Usage(SoftAssert sa) {
		/*
		 * Validation for wifi data usage window(usage cycle used data usage graph
		 * application list)
		 */
		try {
			if (isElementExist(Locators_DataUsageSetting.dateCycleRange)
					&& isElementExist(Locators_DataUsageSetting.wifi_data_used)
					&& isElementExist(Locators_DataUsageSetting.Data_usage_graph)
					&& isElementExist(Locators_DataUsageSetting.app_Icon)) {
				System.out.println(Locators_DataUsageSetting.dateCycleRange.getText());
				APP_LOGS.info(
						"1.Date cycle range, 2.Wifi data used, 3.Wifi data usage graph 4.Wifi used apps are displayed under Wi-fi data usage window");
				sa.assertTrue(true,
						"1.Date cycle range, 2.Wifi data used, 3.Wifi data usage graph 4.Wifi used apps are displayed under Wi-fi data usage window");
				test.log(LogStatus.PASS,
						"1.Date cycle range, 2.Wifi data used, 3.Wifi data usage graph 4.Wifi used apps are displayed under Wi-fi data usage window");
			} else {

				APP_LOGS.info(
						"1.Date cycle range, 2.Wifi data used, 3.Wifi data usage graph 4.Wifi used apps are not displayed under Wi-fi data usage window");
				sa.fail();
				test.log(LogStatus.FAIL,
						"1.Date cycle range, 2.Wifi data used, 3.Wifi data usage graph 4.Wifi used apps are not displayed under Wi-fi data usage window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_Wifi_Data_Usage()");
			e.printStackTrace();

		} catch (Exception e) {

			test.log(LogStatus.ERROR, "Exception in functionality-> validate_Wifi_Data_Usage()");
		}
	}

	public void verify_WifiDataUsed_AppList_IsDisplayed(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.appList_app_1)
					&& isElementExist(Locators_DataUsageSetting.appList_app_2)) {
				APP_LOGS.info("Wifi data used \"Applist\" displayed under \"Wifi data usage\" window successfully");
				sa.assertTrue(true,
						"Wifi data used \"Applist\" displayed under \"Wifi data usage\" window successfully");
				test.log(LogStatus.PASS,
						"Wifi data used \"Applist\" displayed under \"Wifi data usage\" window successfully");
			} else {
				APP_LOGS.info("Applist is not displayed under Wifi data usage window");
				sa.fail();
				test.log(LogStatus.FAIL, "Applist is not displayed under Wifi data usage window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_WifiDataUsed_AppList_IsDisplayed()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> verify_WifiDataUsed_AppList_IsDisplayed()");
		}
	}

	public void open_AppSettings() {
		try {
			scrollToElements(Locators_DataUsageSetting.appSettings);
			clickBtn(Locators_DataUsageSetting.appSettings);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> open_AppSettings()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> open_AppSettings()");
		}
	}

	public void open_AppInfo() {
		try {

			clickBtn(Locators_DataUsageSetting.beforeAppInfo);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> open_AppInfo()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> open_AppInfo()");
			e.printStackTrace();
		}
	}

	public void validate_AppSettings(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.settings)) {
				APP_LOGS.info("App settings is launched successfully");
				sa.assertTrue(true, "App settings is launched successfully");
				test.log(LogStatus.PASS, "App settings is launched successfully");
			} else {
				APP_LOGS.info("App settings is not launched");
				sa.fail();
				test.log(LogStatus.FAIL, "App settings is not launched");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->validate_AppSettings()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_AppSettings()");
			e.printStackTrace();
		}
	}

	public void validate_AppInfo(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.appInfo)) {
				APP_LOGS.info("App info is displayed successfully");
				sa.assertTrue(true, "App info is displayed successfully");
				test.log(LogStatus.PASS, "App info is displayed successfully");
			} else {
				APP_LOGS.info("App info is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "App info is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_AppInfo()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_AppInfo()");
			e.printStackTrace();
		}
	}

	public void verify_WifiDataUsage_Graph(SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.Data_usage_graph)) {
				APP_LOGS.info("Wifi data usage graph displayed under Wifi data usage window successfully ");
				sa.assertTrue(true, "Wifi data usage graph displayed under Wifi data usage window successfully ");
				test.log(LogStatus.PASS, "Wifi data usage graph displayed under Wifi data usage window successfully ");
			} else {
				APP_LOGS.info("Wifi data usage graph is not displayed under Wifi data usage window");
				sa.fail();
				test.log(LogStatus.FAIL, "Wifi data usage graph is not displayed under Wifi data usage window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_WifiDataUsage_Graph()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_WifiDataUsage_Graph()");
		}
	}

	public void validate_WifiDataUsage_Graph_AsPerCycle(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.Data_usage_graph)) {

				String fixedDate = Locators_DataUsageSetting.dateCycleRange.getText();
				String expectedStart = Locators_DataUsageSetting.dateCycleRange.getText().substring(0, 6).trim();
				String expectedEnd = Locators_DataUsageSetting.dateCycleRange.getText().substring(9).trim();

				String actualStart = Locators_DataUsageSetting.start_Cycle_Date.getText().trim(),
						actualEnd = Locators_DataUsageSetting.end_Cycle_Date.getText().trim();

				if (actualStart.equalsIgnoreCase(expectedStart) && actualEnd.equalsIgnoreCase(expectedEnd)) {
					APP_LOGS.info("Data usage graph displayed as per the cycle provided ");
					sa.assertTrue(true, "Data usage graph displayed as per the cycle provided ");
					test.log(LogStatus.PASS, "Data usage graph displayed as per the cycle provided ");
				}
			} else {
				APP_LOGS.info("Data usage graph is not displayed as per the cycle");
				sa.fail();
				test.log(LogStatus.FAIL, "Data usage graph is not displayed as per the cycle");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_WifiDataUsage_Graph_AsPerCycle()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_WifiDataUsage_Graph_AsPerCycle()");
			e.printStackTrace();
		}
	}

	public void verify_MeteredNetworksInfo_IsDisplayed(String meteredNetworksInfo, SoftAssert sa) {

		try {
			new WebDriverWait(aDriver, 10)
					.until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.info_metered_networks));
			String actual = Locators_DataUsageSetting.info_metered_networks.getText();
			if (actual.equalsIgnoreCase(meteredNetworksInfo)) {
				APP_LOGS.info("Metered networks information displayed successfully");
				sa.assertTrue(true, "Metered networks information displayed successfully");
				test.log(LogStatus.PASS, "Metered networks information displayed successfully ");
			} else {
				APP_LOGS.info("Metered networks information is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Metered networks information is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_MeteredNetworksInfo_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_MeteredNetworksInfo_IsDisplayed()");
			e.printStackTrace();
		}
	}

	public void verify_ListOfMeteredNetworks_IsDisplayed(SoftAssert sa) {
		try {
			// Enable wifi network
			if (isElementExist(Locators_DataUsageSetting.list_metered_wifi_networks)) {
				APP_LOGS.info("List of Metered networks  displayed successfully");
				sa.assertTrue(true, "List of Metered networks  displayed successfully");
				test.log(LogStatus.PASS, "List of Metered networks  displayed successfully");
			} else {

				APP_LOGS.info("List of Metered networks is not  displayed successfully");
				sa.fail();
				test.log(LogStatus.FAIL, "List of Metered networks is not  displayed successfully");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_ListOfMeteredNetworks_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_ListOfMeteredNetworks_IsDisplayed()");
			e.printStackTrace();
		}
	}

	public void validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks(String enableOrDisable,
			SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.list_metered_wifi_networks)) {
				clickBtn(Locators_DataUsageSetting.list_wifi_networks_1);
				minWait();
				if (isElementExist(Locators_DataUsageSetting.automatic_metered_notMetered)) {
					switch (enableOrDisable) {
					case "automatic":
						clickBtn(Locators_DataUsageSetting.automatic);
						String actual1 = Locators_DataUsageSetting.enabled_wifi_networks.getText();
						if (actual1.equals("Automatic")) {
							APP_LOGS.info("Wifi Metered network \"Automatic\" enabled successfully");
							sa.assertTrue(true, "Wifi Metered network \"Automatic\" enabled successfully");
							test.log(LogStatus.PASS, "Wifi Metered network \"Automatic\" enabled successfully");
						}
						break;
					case "metered":
						clickBtn(Locators_DataUsageSetting.metered);
						String actual2 = Locators_DataUsageSetting.enabled_wifi_networks.getText();
						if (actual2.equals("Metered")) {
							APP_LOGS.info("Wifi Metered network \"Metered\" enabled successfully");
							sa.assertTrue(true, "Wifi Metered network  \"Metered\" enabled successfully");
							test.log(LogStatus.PASS, "Wifi Metered network  \"Metered\" enabled successfully");
						}
						break;
					case "non metered":
						clickBtn(Locators_DataUsageSetting.non_metered);
						String actual3 = Locators_DataUsageSetting.enabled_wifi_networks.getText();
						if (actual3.equals("Not metered")) {
							APP_LOGS.info("Wifi Metered network  \"Non metered\" enabled successfully");
							sa.assertTrue(true, "Wifi Metered network  \"Non metered\" enabled successfully");
							test.log(LogStatus.PASS, "Wifi Metered network  \"Non metered\" enabled successfully");
						}
						break;

					default:
						APP_LOGS.info("Invalid selection");
						sa.fail();
						test.log(LogStatus.FAIL, "Invalid selection");
						break;
					}
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,
					"Error in the locators->validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR,
					"Exception in validate_NetworksRestrictions_Enable_Disable_Metered_Wifi_Networks()");
			e.printStackTrace();
		}
	}

	public void validate_MobileNetworks(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.Mobile_networks)) {
				APP_LOGS.info("Mobile networks option  under data usage setting displayed successfully");
				sa.assertTrue(true, "Mobile networks option  under data usage setting displayed successfully");
				test.log(LogStatus.PASS, "Mobile networks option  under data usage setting displayed successfully");
			} else {

				APP_LOGS.info("Mobile networks option  under data usage setting is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile networks option  under data usage setting is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_MobileNetworks()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_MobileNetworks()");
			e.printStackTrace();
		}
	}

	public void tapOn_MoreOptions() {
		try {
			clickBtn(Locators_DataUsageSetting.MoreOptions);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> tapOn_MoreOptions()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> tapOn_MoreOptions()");
			e.printStackTrace();
		}
	}

	public void tapOn_MobileNetworks() {
		try {
			clickBtn(Locators_DataUsageSetting.Mobile_networks);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> tapOn_MobileNetworks()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> tapOn_MobileNetworks()");
			e.printStackTrace();
		}
	}

	public void validate_MobileNetworkSetting(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.mobile_network_settings)) {
				APP_LOGS.info("Mobile networks setting window opened successfully");
				sa.assertTrue(true, "Mobile networks setting window opened successfully");
				test.log(LogStatus.PASS, "Mobile networks setting window opened successfully");
			} else {

				APP_LOGS.info("Mobile networks setting window is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile networks setting window is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> validate_MobileNetworkSetting()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_MobileNetworkSetting()");
			e.printStackTrace();
		}
	}

	public void validate_BillingCycle(SoftAssert sa) {
		try {
			if (isElementExist(Locators_DataUsageSetting.Billing_cycle)
					&& isElementExist(Locators_DataUsageSetting.setdataWarning)
					&& isElementExist(Locators_DataUsageSetting.Data_warning)
					&& isElementExist(Locators_DataUsageSetting.setdataLimit)
					&& isElementExist(Locators_DataUsageSetting.Data_limit)) {
				APP_LOGS.info(
						"1.Billing cycle, 2.Set data warning, 3.Data warning, 4.Set data limit, 5.Data limit are displayed under \"Billing cycle\"");
				sa.assertTrue(true,
						"1.Billing cycle, 2.Set data warning, 3.Data warning, 4.Set data limit, 5.Data limit are displayed under \"Billing cycle\"");
				test.log(LogStatus.PASS,
						"1.Billing cycle, 2.Set data warning, 3.Data warning, 4.Set data limit, 5.Data limit are displayed under \"Billing cycle\"");
			} else {

				APP_LOGS.info(
						"1.Billing cycle, 2.Set data warning, 3.Data warning, 4.Set data limit, 5.Data limit are not displayed under \"Billing cycle\"");
				sa.fail();
				test.log(LogStatus.FAIL,
						"1.Billing cycle, 2.Set data warning, 3.Data warning, 4.Set data limit, 5.Data limit are not displayed under \"Billing cycle\"");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_BillingCycle()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_BillingCycle()");
			e.printStackTrace();
		}
	}
	public void validate_CancelSetDataLimit(SoftAssert sa) {
		try {
				if (isElementExist(Locators_DataUsageSetting.Billing_cycle))
				{
					APP_LOGS.info("After cancelling Set data limit billing cycle is displayed successfully");
					sa.assertTrue(true,"After cancelling Set data limit billing cycle is displayed successfully");
					test.log(LogStatus.PASS,"After cancelling Set data limit billing cycle is displayed successfully");
				} else 
				{
					APP_LOGS.info("After cancelling set data limit billing cycle is not displayed");
					sa.fail();
					test.log(LogStatus.FAIL,"After cancelling set data limit billing cycle is not displayed");
				}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> validate_CancelSetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_CancelSetDataLimit()");
			e.printStackTrace();
		}
	}

	public void set_GB_MB(String gbOrMb) throws InterruptedException {
		minWait();
		clickBtn(Locators_DataUsageSetting.GByteMByte);

		switch (gbOrMb) {
		case "gb":
			clickBtn(Locators_DataUsageSetting.GByte);
			break;
		case "mb":
			clickBtn(Locators_DataUsageSetting.MByte);
			break;
		default:
			System.out.println("Wrong choice");
			test.log(LogStatus.WARNING, "Invalid Selection Please give GB or MB ");
		}

	}

	public void clickOn_DataUsageBar() {
		try {
			scrollToElements(Locators_DataUsageSetting.data_usage_bar);
			clickBtn(Locators_DataUsageSetting.data_usage_bar);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_DataUsageBar()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> clickOn_DataUsageBar()");
			e.printStackTrace();
		}
	}

	public String fetch_DateCycleValue() {
		String bfr = "";
		try {
			bfr = Locators_DataUsageSetting.monthlyDate.getText();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> fetch_DateCycleValue()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> fetch_DateCycleValue()");
			e.printStackTrace();
		}
		return bfr;
	}

	public void changeDate(String dateValue) {

		try {
			clickBtn(Locators_DataUsageSetting.numberPicker);
			enterTextToInputField(Locators_DataUsageSetting.numberPicker, dateValue);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> changeDate()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> changeDate()");
			e.printStackTrace();
		}

	}

	public void clickOn_DataUsageMenu() {

		try {
			clickBtn(Locators_DataUsageSetting.data_usage_bar);

		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_DataUsageMenu()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> clickOn_DataUsageMenu()");
			e.printStackTrace();
		}

	}

	public void verify_DataUsageMenu(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (isElementExist(Locators_DataUsageSetting.data_usage_bar)) {
				APP_LOGS.info("Data usage menu displayed successfully");
				sa.assertTrue(true, "Data usage menu displayed successfully");
				test.log(LogStatus.PASS, "Data usage menu displayed successfully");
			} else {
				APP_LOGS.info("Data usage menu is not displayed");
				test.log(LogStatus.ERROR, "Data usage menu is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data usage menu is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_DataUsageMenu()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_DataUsageMenu()");
			e.printStackTrace();
		}

	}

	public void verify_SetDataWarning_IsDisplayed(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (isElementExist(Locators_DataUsageSetting.setdata)) {
				APP_LOGS.info("Set data warning field is displayed successfully");
				sa.assertTrue(true, "Set data warning field is displayed successfully");
				test.log(LogStatus.PASS, "Set data warning field is displayed successfully");
			} else {
				APP_LOGS.info("Set data warning field is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Set data warning field is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_SetDataWarning_IsDisplayed()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_SetDataWarning_IsDisplayed()");
			e.printStackTrace();
		}

	}

	public void clickOn_BillingCycle() {
		try {
			clickBtn(Locators_DataUsageSetting.Billing_cycle);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_BillingCycle()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> clickOn_BillingCycle()");
			e.printStackTrace();
		}

	}

	public void verify_DateUsageCycleResetField(SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (isElementExist(Locators_DataUsageSetting.numberPicker)) {
				APP_LOGS.info("Date Usage Cycle Reset field displayed successfully");
				sa.assertTrue(true, "Date Usage Cycle Reset field displayed successfully");
				test.log(LogStatus.PASS, "Date Usage Cycle Reset field displayed successfully");
			} else {
				APP_LOGS.info("Data usage cycle reset field in not displayed");
				test.log(LogStatus.ERROR, "Data usage cycle reset field in not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Data usage cycle reset field in not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_DateUsageCycleResetField()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_DateUsageCycleResetField()");
			e.printStackTrace();
		}

	}

	public void validate_DateSetting_WithoutModifyingDateCycle(String bfrModification, String aftModification,
			SoftAssert sa) throws InterruptedException, IOException {
		try {
			if (aftModification.equals(bfrModification)) {
				APP_LOGS.info("Previous Date setting value has been retained successfully");
				sa.assertTrue(true, "Previous Date setting value has been retained successfully");
				test.log(LogStatus.PASS, "Previous Date setting value has been retained successfully");
			} else {
				APP_LOGS.info("Previous date setting value is not retained");
				test.log(LogStatus.ERROR, "Previous date setting value is not retained");
				sa.fail();
				test.log(LogStatus.FAIL, "Previous date setting value is not retained");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_DateSetting_WithoutModifyingDateCycle()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->validate_DateSetting_WithoutModifyingDateCycle()");
			e.printStackTrace();
		}

	}
	public void validate_BillingCycle_Without_Modifying_SetDataLimit(SoftAssert sa) {

		try {

			if (isElementExist(Locators_DataUsageSetting.Billing_cycle)) {
				APP_LOGS.info("Billing cycle displayed successfully");
				sa.assertTrue(true,"Billing cycle displayed successfully");
				test.log(LogStatus.PASS,"Billing cycle displayed successfully");
			} else {

				APP_LOGS.info("Billing cycle is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Billing cycle is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,
					"Error in the locators->validate_BillingCycle_Without_Modifying_SetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> validate_BillingCycle_Without_Modifying_SetDataLimit()");
			e.printStackTrace();
		}
	}

	public void validate_DataUsage_Without_Modifying_SetDataUsageWarning(SoftAssert sa) {

		try {

			if (isElementExist(Locators_DataUsageSetting.Data_usage)) {
				APP_LOGS.info("Data Usage window displayed  successfully wihtout modifying data warning limit");
				sa.assertTrue(true,"Data Usage window displayed  successfully wihtout modifying data warning limit");
				test.log(LogStatus.PASS,"Data Usage window displayed  successfully wihtout modifying data warning limit");
			} else {

				APP_LOGS.info("Data Usage window is not displayed ");
				sa.fail();
				test.log(LogStatus.FAIL, "Data Usage winodw is not displayed ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR,
					"Error in the locators->validate_DataUsage_Without_Modifying_SetDataUsageWarning()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in validate_DataUsage_Without_Modifying_SetDataUsageWarning()");
			e.printStackTrace();
		}
	}

	public void clickOn_SetDataLimit() {
		/*
		 * click on set data limit dont do any modification
		 */
		try {
			clickBtn(Locators_DataUsageSetting.Data_limit);
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_SetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> clickOn_SetDataLimit()");
		}
	}

	public boolean enable_SetDataWarning() {
		/*
		 * Enable set data warning
		 */
		boolean enabled = false;
		try {
			/*
			 * minWait(); if
			 * (isElementExist(Locators_DataUsageSetting.enabled_setdatawarning)) { enabled
			 * = true; } else if
			 * (isElementExist(Locators_DataUsageSetting.disabled_setdatawarning)) {
			 * clickBtn(Locators_DataUsageSetting.disabled_setdatawarning); minWait(); if
			 * (isElementExist(Locators_DataUsageSetting.enabled_setdatawarning)) enabled =
			 * true; }
			 * 
			 */
			if (Locators_DataUsageSetting.Data_warning.isEnabled()) {
				enabled = true;
			} else {
				clickBtn(Locators_DataUsageSetting.setdataWarning);
				enabled = Locators_DataUsageSetting.Data_warning.isEnabled();
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->enable_SetDataWarning()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception error in enable_SetDataWarning()");
		}
		System.out.println("Data warning is enabled ? " + enabled);
		return enabled;
	}

	public boolean disable_SetDataWarning() {
		/*
		 * Disable set data warning
		 */
		boolean disabled = false;
		try {
			/*
			 * minWait();
			 * if(isElementExist(Locators_DataUsageSetting.disabled_setdatawarning)) {
			 * disabled = true; }else
			 * if(isElementExist(Locators_DataUsageSetting.enabled_setdatawarning)) {
			 * clickBtn(Locators_DataUsageSetting.enabled_setdatawarning); minWait(); if
			 * (isElementExist(Locators_DataUsageSetting.disabled_setdatawarning)) {
			 * disabled = true; } else { disabled = false; }}
			 */
			if (Locators_DataUsageSetting.Data_warning.isEnabled() == false) {
				disabled = true;
			} else if (Locators_DataUsageSetting.Data_warning.isEnabled() == true) {
				clickBtn(Locators_DataUsageSetting.setdataWarning);
				if (Locators_DataUsageSetting.Data_warning.isEnabled() == false) {
					disabled = true;
				}
			}
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->disable_SetDataWarning()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality -> disable_SetDataWarning()");
			e.printStackTrace();
		}
		System.out.println("Data warning is disabled ? " + disabled);
		return disabled;
	}

	public boolean enable_SetDataLimit() {
		/*
		 * Enable set data limit
		 */
		boolean enabled = false;
		try {
			/*
			 * if(isElementExist(Locators_DataUsageSetting.disabled_setdataLimit)) {
			 * clickBtn(Locators_DataUsageSetting.disabled_setdataLimit);
			 * wait(Locators_DataUsageSetting.OK,10);
			 * clickBtn(Locators_DataUsageSetting.OK); if
			 * (isElementExist(Locators_DataUsageSetting.enabled_setdataLimit)) enabled =
			 * true; else enabled = false; }else
			 * if(isElementExist(Locators_DataUsageSetting.enabled_setdataLimit)) { enabled
			 * = true; }
			 */
			if (Locators_DataUsageSetting.Data_limit.isEnabled() == true) {
				enabled = true;
			} else if (Locators_DataUsageSetting.Data_limit.isEnabled() == false) {
				clickBtn(Locators_DataUsageSetting.setdataLimit);
				minWait();
				clickBtn(Locators_DataUsageSetting.OK);
				enabled = Locators_DataUsageSetting.Data_limit.isEnabled();
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->enable_SetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->enable_SetDataLimit()");
		}
		System.out.println("Data limit is enabled ? " + enabled);
		return enabled;
	}

	public boolean disable_SetDataLimit() {
		/*
		 * Disable set data Limit
		 */
		boolean disabled = false;
		try {
			/*
			 * if (isElementExist(Locators_DataUsageSetting.enabled_setdataLimit)) {
			 * clickBtn(Locators_DataUsageSetting.enabled_setdataLimit); disabled =
			 * (isElementExist(Locators_DataUsageSetting.disabled_setdataLimit)) ? true :
			 * false; } else if
			 * (isElementExist(Locators_DataUsageSetting.disabled_setdataLimit)) { disabled
			 * = true; }
			 */
			if (Locators_DataUsageSetting.Data_limit.isEnabled() == false) {
				disabled = true;
			} else if (Locators_DataUsageSetting.Data_limit.isEnabled() == true) {
				clickBtn(Locators_DataUsageSetting.setdataLimit);
				if (Locators_DataUsageSetting.Data_limit.isEnabled() == false) {
					disabled = true;
				}
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->disable_SetDataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->disable_SetDataLimit()");
		}
		System.out.println("Data limit is disabled ? " + disabled);
		return disabled;
	}

	public void verify_SetDataWarning_IsEnabled(boolean res, SoftAssert sa) {
		try {
			if (res) {
				APP_LOGS.info("Set Data Warning enabled successfully");
				sa.assertTrue(true, "Set Data Warning enabled successfully");
				test.log(LogStatus.PASS, "Set Data Warning enabled successfully");

			} else {
				APP_LOGS.info("Set Data Warning is not enabled ");
				sa.fail();
				test.log(LogStatus.FAIL, "Set Data Warning is not enabled ");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->verify_SetDataWarning_IsEnabled()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_SetDataWarning_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_SetDataWarning_IsDisabled(boolean res, SoftAssert sa) {
		try {
			if (res) {
				APP_LOGS.info("Set Data Warning disabled successfully");
				sa.assertTrue(true, "Set Data Warning disabled successfully");
				test.log(LogStatus.PASS, "Set Data Warning disabled successfully");
			} else {
				APP_LOGS.info("Set Data Warning is not disabled ");
				sa.fail();
				test.log(LogStatus.FAIL, "Set Data Warning is not disabled ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_SetDataWarning_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_SetDataWarning_IsDisabled()");
			e.printStackTrace();
		}
	}

	public void verify_SetDataLimit_IsEnabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("Set Data Limit is enabled successfully");
				sa.assertTrue(true, "Set Data Limit is enabled successfully");
				test.log(LogStatus.PASS, "Set Data Limit is enabled successfully");
			} else {
				APP_LOGS.info("Set data limit is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Set data limit is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_SetDataLimit_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_SetDataLimit_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_SetDataLimit_IsDisabled(boolean res, SoftAssert sa) throws InterruptedException {
		try {
			if (res) {
				APP_LOGS.info("Set Data Limit is disabled successfully");
				sa.assertTrue(true, "Set Data Limit is disabled successfully");
				test.log(LogStatus.PASS, "Set Data Limit is disabled successfully");

			} else {
				APP_LOGS.info("Set data limit is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Set data limit is not disabled");

			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_SetDataLimit_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in  functionality-> verify_SetDataLimit_IsDisabled()");
			e.printStackTrace();
		}
	}

	public void validate_MobileDataUsage(SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.Mobile_data_usage)) {
				APP_LOGS.info("Mobile data usage displayed  successfully under data usage setting");
				sa.assertTrue(true, "Mobile data usage displayed  successfully under data usage setting");
				test.log(LogStatus.PASS, "Mobile data usage displayed  successfully under data usage setting");
			} else {

				APP_LOGS.info("Mobile data usage is not displayed under data usage setting");
				sa.fail();
				test.log(LogStatus.FAIL, "Mobile data usage is not displayed  under data usage setting");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_MobileDataUsage()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality ->  validate_MobileDataUsage()");
			e.printStackTrace();
		}
	}

	public void validate_MobileDataUsage_AppSetting(SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.settings)) {
				APP_LOGS.info("Navigated to \'App Settings\' succesfully from \'App data Usage\'");
				sa.assertTrue(true, "Navigated to \'App Settings\' succesfully from \'App data Usage\'");
				test.log(LogStatus.PASS, "Navigated to \'App Settings\' succesfully from \'App data Usage\'");
			} else {

				APP_LOGS.info("Cannot navigated to \'App Settings\' from \'App data Usage\'");
				sa.fail();
				test.log(LogStatus.FAIL, "Cannot navigated to \'App Settings\' from \'App data Usage\'");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->validate_MobileDataUsage_AppSetting()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality -> validate_MobileDataUsage_AppSetting()");
			e.printStackTrace();
		}
	}
	public boolean enable_RestrictBackgroundData() {
		boolean enabled = false;
		try {
			if(isElementExist(Locators_DataUsageSetting.Restrict_background_data)) {
				clickBtn(Locators_DataUsageSetting.Restrict_background_data);minWait();
				clickBtn(Locators_DataUsageSetting.Never);
			}
			enabled=true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Error in the locators-> enable_RestrictBackgroundData()");
			e.printStackTrace();

		} catch (Exception e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Exeption in functionality-> enable_RestrictBackgroundData()");
			e.printStackTrace();
		}
		return enabled;
	}
	public boolean disable_RestrictBackgroundData() {
		boolean disabled = false;
		try {
			if(isElementExist(Locators_DataUsageSetting.Restrict_background_data)) {
				clickBtn(Locators_DataUsageSetting.Restrict_background_data);minWait();
				clickBtn(Locators_DataUsageSetting.Always);
			}
			disabled=true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> disable_RestrictBackgroundData()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> disable_RestrictBackgroundData()");
			e.printStackTrace();
		}
		return disabled;
	}
	public boolean enable_BackgroundData() {
		boolean enabled = false;
		try {
			minWait();
			if (isElementExist(Locators_DataUsageSetting.enabled_background_data)) {
				enabled = true;
			} else if (isElementExist(Locators_DataUsageSetting.disabled_background_data)) {
				clickBtn(Locators_DataUsageSetting.disabled_background_data);
				if (isElementExist(Locators_DataUsageSetting.enabled_background_data))
					enabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Error in the locators->enable_BackgroundData()");
			e.printStackTrace();

		} catch (Exception e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Exeption in functionality->enable_BackgroundData()");
			e.printStackTrace();
		}
		return enabled;
	}

	public boolean disable_BackgroundData() {
		boolean disabled = false;
		try {
			minWait();
			if (isElementExist(Locators_DataUsageSetting.disabled_background_data)) {
				disabled = true;
			} else if (isElementExist(Locators_DataUsageSetting.enabled_background_data)) {
				clickBtn(Locators_DataUsageSetting.enabled_background_data);
				if (isElementExist(Locators_DataUsageSetting.disabled_background_data))
					disabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			disabled = false;
			test.log(LogStatus.ERROR, "Error in the locators->disable_BackgroundData()");
			e.printStackTrace();

		} catch (Exception e) {
			disabled = false;
			test.log(LogStatus.ERROR, "Exeption in functionality->disable_BackgroundData()");
			e.printStackTrace();
		}
		return disabled;
	}

	public void verify_BackgroundData_IsEnabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("Background data is enabled Successfully");
				sa.assertTrue(true, "Background data is enabled Successfully");
				test.log(LogStatus.PASS, "Background data is enabled Successfully");
			} else {

				APP_LOGS.info("Background data is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Background data is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_BackgroundData_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_BackgroundData_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_BackgroundData_IsDisabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("Background data is disabled Successfully");
				sa.assertTrue(true, "Background data is disabled Successfully");
				test.log(LogStatus.PASS, "Background data is disabled Successfully");
			} else {
				APP_LOGS.info("Background data is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Background data is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_BackgroundData_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> verify_BackgroundData_IsDisabled()");
			e.printStackTrace();
		}
	}

	public boolean enable_UnrestrictedDataUsage() {
		boolean enabled = false;
		try {
			minWait();
			scrollToElements(Locators_DataUsageSetting.Unrestricted_data);
			if (isElementExist(Locators_DataUsageSetting.enabled_unrestricted_data_usage)) {
				enabled = true;
			} else if (isElementExist(Locators_DataUsageSetting.disabled_unrestricted_data_usage)) {
				clickBtn(Locators_DataUsageSetting.disabled_unrestricted_data_usage);
				if (isElementExist(Locators_DataUsageSetting.enabled_unrestricted_data_usage))
					enabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Error in the locators-> enable_UnrestrictedDataUsage()");
			e.printStackTrace();

		} catch (Exception e) {
			enabled = false;
			test.log(LogStatus.ERROR, "Exeption in functionality-> enable_UnrestrictedDataUsage()");
			e.printStackTrace();
		}
		return enabled;
	}

	public boolean disable_UnrestrictedDataUsage() {
		boolean disabled = false;
		try {
			minWait();
			if (isElementExist(Locators_DataUsageSetting.disabled_unrestricted_data_usage)) {
				disabled = true;
			} else if (isElementExist(Locators_DataUsageSetting.enabled_unrestricted_data_usage)) {
				clickBtn(Locators_DataUsageSetting.enabled_unrestricted_data_usage);
				if (isElementExist(Locators_DataUsageSetting.disabled_unrestricted_data_usage))
					disabled = true;
			} else {
				test.log(LogStatus.ERROR, "Disable Unrestricted data usage is not visible");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			disabled = false;
			test.log(LogStatus.ERROR, "Error in the locators->  disable_UnrestrictedDataUsage()");
			e.printStackTrace();

		} catch (Exception e) {
			disabled = false;
			test.log(LogStatus.ERROR, "Exeption in functionality->  disable_UnrestrictedDataUsage()");
			e.printStackTrace();
		}
		return disabled;
	}

	public void verify_UDU_YouTube_IsEnabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("YouTube unrestricted data usage is enabled successfully");
				sa.assertTrue(true, "YouTube unrestricted data usage is enabled successfully");
				test.log(LogStatus.PASS, "YouTube unrestricted data usage is enabled successfully");
			} else {
				APP_LOGS.info("YouTube unrestricted data usage is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "YouTube unrestricted data usage is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_UDU_YouTube_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UDU_YouTube_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_UDU_GooglePlayStore_IsEnabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("Unrestricted data usage for Google play store is enabled successfully");
				sa.assertTrue(true, "Unrestricted data usage for Google play store is enabled successfully");
				test.log(LogStatus.PASS, "Unrestricted data usage for Google play store is enabled successfully");
			} else {
				APP_LOGS.info("Unrestricted data usage for Google play store is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Unrestricted data usage for Google play store is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_UDU_GooglePlayStore_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in -> verify_UDU_GooglePlayStore_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_YouTube_IsEnabled_InDataSaver(SoftAssert sa) {
		try {
			scrollTo("YouTube");
			scrollToElements(Locators_DataUsageSetting.You_Tube);
			if (Locators_DataUsageSetting.You_Tube_Switch.getText().equalsIgnoreCase("on")) {
				APP_LOGS.info("YouTube unrestricted data usage is enabled successfully in Data saver window");
				sa.assertTrue(true, "YouTube unrestricted data usage is enabled successfully in Data saver window");
				test.log(LogStatus.PASS,
						"YouTube unrestricted data usage is enabled successfully in Data saver window");
			} else if (Locators_DataUsageSetting.You_Tube_Switch.getText().equalsIgnoreCase("off")) {
				APP_LOGS.info("YouTube unrestricted data usage is enabled not in Data saver window");
				sa.fail();
				test.log(LogStatus.FAIL, "YouTube unrestricted data usage is enabled not in Data saver window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_YouTube_IsEnabled_InDataSaver()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_YouTube_IsEnabled_InDataSaver()");
			e.printStackTrace();
		}
	}

	public void verify_GooglePlaystore_IsEnabled_InDataSaver(SoftAssert sa) {
		try {
			scrollToElements(Locators_DataUsageSetting.Google_Play_store);
			if (Locators_DataUsageSetting.googlePlayStore_Switch.getText().equalsIgnoreCase("on")) {
				APP_LOGS.info(
						"Unrestricted data usage for Google play store is enabled in data saver window verified successfully");
				sa.assertTrue(true,
						"Unrestricted data usage for Google play store is enabled in data saver window verified successfully");
				test.log(LogStatus.PASS,
						"Unrestricted data usage for Google play store is enabled in data saver window verified successfully");
			} else {
				APP_LOGS.info("Unrestricted data usage for Google play store is not enabled in data saver window");
				sa.fail();
				test.log(LogStatus.FAIL,
						"Unrestricted data usage for Google play store is not enabled in data saver window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_GooglePlaystore_IsEnabled_InDataSaver()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_GooglePlaystore_IsEnabled_InDataSaver()");
			e.printStackTrace();
		}
	}

	public boolean disable_UDU_GooglePlayStore() {
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.googlePlayStore_Switch.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.googlePlayStore_Switch);
				disabled = true;
			} else if (Locators_DataUsageSetting.googlePlayStore_Switch.getText().equalsIgnoreCase("off")) {
				disabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> disable_UDU_GooglePlayStore()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> disable_UDU_GooglePlayStore()");
			e.printStackTrace();
		}
		return disabled;
	}

	public boolean disable_UDU_YouTube() {
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.You_Tube_Switch.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.You_Tube_Switch);
				disabled = true;
			} else if (Locators_DataUsageSetting.You_Tube_Switch.getText().equalsIgnoreCase("off")) {
				disabled = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> disable_UDU_YouTube()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> disable_UDU_YouTube()");
			e.printStackTrace();
		}
		return disabled;
	}

	public void verify_UDU_GooglePlayStore_IsDisabled(boolean res, SoftAssert sa) {
		try {
			if (res) {
				APP_LOGS.info("Google play store unrestricted data usage is disabled from data saver window");
				sa.assertTrue(true, "Google play store unrestricted data usage is disabled from data saver window");
				test.log(LogStatus.PASS,
						"Google play store unrestricted data usage is disabled from data saver window");
			} else {
				APP_LOGS.info("Google play store is can not disabled from data saver window");
				sa.fail();
				test.log(LogStatus.FAIL, "Google play store is can not disabled from data saver window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_UDU_GooglePlayStore_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UDU_GooglePlayStore_IsDisabled()");
			e.printStackTrace();
		}
	}

	public void verify_UDU_YouTube_IsDisabled(boolean res, SoftAssert sa) {
		try {
			if (res) {
				APP_LOGS.info("YouTube unrestricted data usage is disabled successfully in Data saver window");
				sa.assertTrue(true, "YouTube unrestricted data usage is disabled successfully in Data saver window");
				test.log(LogStatus.PASS,
						"YouTube unrestricted data usage is disabled successfully in Data saver window");
			} else {
				APP_LOGS.info("YouTube unrestricted data usage is not disabeld in Data saver window");
				sa.fail();
				test.log(LogStatus.FAIL, "YouTube unrestricted data usage is not disabeld in Data saver window");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_UDU_YouTube_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UDU_YouTube_IsDisabled()");
			e.printStackTrace();
		}
	}

	public void verify_UnrestrictedDataUsage_IsEnabled(boolean enabled, SoftAssert sa) {

		try {
			if (enabled) {
				APP_LOGS.info("Unrestricted data usage is enabled Successfully");
				sa.assertTrue(true, "Unrestricted data usage is enabled Successfully");
				test.log(LogStatus.PASS, "Unrestricted data usage is enabled Successfully");
			} else {

				APP_LOGS.info("Unrestricted data usage is not enabled ");
				sa.fail();
				test.log(LogStatus.FAIL, "Unrestricted data usage is not enabled ");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_UnrestrictedDataUsage_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UnrestrictedDataUsage_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_UnrestrictedDataUsage_IsDisabled(boolean res, SoftAssert sa) {

		try {
			if (res) {
				APP_LOGS.info("Unrestricted data usage is disabled Successfully");
				sa.assertTrue(true, "Unrestricted data usage is disabled Successfully");
				test.log(LogStatus.PASS, "Unrestricted data usage is disabled Successfully");
			} else {
				APP_LOGS.info("Unrestricted data usage is not disabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Unrestricted data usage is not disabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_UnrestrictedDataUsage_IsDisabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UnrestrictedDataUsage_IsDisabled()");
			e.printStackTrace();
		}
	}

	public void verify_UnrestrictedDataUsage_IsPresent(boolean res, SoftAssert sa) {

		try {
			if (res && isElementExist(Locators_DataUsageSetting.unrestricted_data_usage)) {
				APP_LOGS.info("Unrestricted data usage is displayed successfully");
				sa.assertTrue(true, "Unrestricted data usage is displayed successfully");
				test.log(LogStatus.PASS, "Unrestricted data usage is displayed successfully");
			} else {

				APP_LOGS.info("Unrestricted data usage is not displayed");
				sa.fail();
				test.log(LogStatus.FAIL, "Unrestricted data usage is not displayed");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> verify_UnrestrictedDataUsage_IsPresent()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_UnrestrictedDataUsage_IsPresent()");
			e.printStackTrace();
		}

	}

	public boolean verify_SystemApps(SoftAssert sa) {
		boolean systemApps = false;
		try {
			if (isElementExist(Locators_DataUsageSetting.sa1_android_easter_egg)
					&& isElementExist(Locators_DataUsageSetting.sa2_android_services_library)
					&& isElementExist(Locators_DataUsageSetting.sa3_android_setup)
					&& isElementExist(Locators_DataUsageSetting.sa4_android_shared_library)) {
				systemApps = true;
				APP_LOGS.info(
						"All apps are displayed under \"Unrestricted data applist\" when \"Show system\" is enabled");
				sa.assertTrue(true,
						"All apps are displayed under \"Unrestricted data applist\" when \"Show system\" is enabled");
				test.log(LogStatus.PASS,
						"All apps are displayed under \"Unrestricted data applist\" when \"Show system\" is enabled");
			} else {
				systemApps = false;
				APP_LOGS.info(
						"\"System Apps\" are not displayed under \"Unrestricted data applist\" when \"Show system\" is enabled");
				sa.fail();
				test.log(LogStatus.FAIL,
						"\"System Apps\" are not displayed under \"Unrestricted data applist\" when \"Show system\" is enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_SystemApps()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_SystemApps()");
			e.printStackTrace();
		}
		return systemApps;
	}

	public void verify_DefaultDownloadedApps(SoftAssert sa) {
		try {
			if ((isElementExist(Locators_DataUsageSetting.sa1_android_easter_egg)
					&& isElementExist(Locators_DataUsageSetting.sa2_android_services_library)
					&& isElementExist(Locators_DataUsageSetting.sa3_android_setup)
					&& isElementExist(Locators_DataUsageSetting.sa4_android_shared_library)) == false) {
				APP_LOGS.info(
						"Only Default and Downloaded apps displayed under Unrestricted data app list when show system is hidden");
				sa.assertTrue(true,
						"Only Default and Downloaded apps displayed under Unrestricted data app list when show system is hidden");
				test.log(LogStatus.PASS,
						"Only Default and Downloaded apps displayed under Unrestricted data app list when show system is hidden");
			} else {
				APP_LOGS.info("\"System apps\" are also displayed under \"Unrestricted data app list\"");
				sa.fail();
				test.log(LogStatus.FAIL, "\"System apps\" are also displayed under \"Unrestricted data app list\"");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_SystemApps()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_SystemApps()");
			e.printStackTrace();
		}
	}

	public void verify_ShowSystem(SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.Show_system)) {
				APP_LOGS.info("\"Show system\" option present under \"Unrestricted data\" window");
				sa.assertTrue(true, "\"Show system\" option present under \"Unrestricted data\" window");
				test.log(LogStatus.PASS, "\"Show system\" option present under \"Unrestricted data\" window");
			} else {
				APP_LOGS.info("\"Show system\" is not displayed under\"Unrestricted data\"");
				sa.fail();
				test.log(LogStatus.FAIL, "\"Show system\" is not displayed under \"Urestricted data\"");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_ShowSystem()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_ShowSystem()");
			e.printStackTrace();
		}

	}

	public void verify_HideSystem(SoftAssert sa) {

		try {
			if (isElementExist(Locators_DataUsageSetting.Hide_system)) {
				APP_LOGS.info("\"Hide system\" option present under \"Unrestricted data\" window");
				sa.assertTrue(true, "\"Hide system\" option present under \"Unrestricted data\" window");
				test.log(LogStatus.PASS, "\"Hide system\" option present under \"Unrestricted data\" window");
			} else {
				APP_LOGS.info("\"Hide system\" is not displayed under\"Unrestricted data\"");
				sa.fail();
				test.log(LogStatus.FAIL, "\"Hide system\" is not displayed under \"Urestricted data\"");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_HideSystem()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_HideSystem()");
			e.printStackTrace();
		}
	}

	public void verify_DataLimit_IsGreyedOut(SoftAssert sa) {

		try {
			if (!Locators_DataUsageSetting.Data_limit.isEnabled()) {
				APP_LOGS.info("Set data limit is disabled and Data limit is displayed in grey colour");
				sa.assertTrue(true, "Set data limit is disabled and Data limit is displayed in grey colour");
				test.log(LogStatus.PASS, "Set data limit is disabled and Data limit is displayed in grey colour");
			} else {
				APP_LOGS.info("Data Limit is not displayed in grey color");
				sa.fail();
				test.log(LogStatus.FAIL, "Data Limit is not displayed in grey color");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_DataLimit_IsGreyedOut()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_DataLimit_IsGreyedOut()");
			e.printStackTrace();
		}
	}

	public void verify_DataWarning_IsGreyedOut(SoftAssert sa) {
		try {

			if (!Locators_DataUsageSetting.Data_warning.isEnabled()) {
				APP_LOGS.info("Set data warning is disabled and Data warning is displayed is grey colour");
				sa.assertTrue(true, "Set data warning is disabled and Data warning is displayed is grey colour");
				test.log(LogStatus.PASS, "Set data warning is disabled and Data warning is displayed is grey colour");
			} else {
				APP_LOGS.info("Data warning  is not displayed in grey color");
				sa.fail();
				test.log(LogStatus.FAIL, "Data warning  is not displayed in grey color");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_DataWarning_IsGreyedOut()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_DataWarning_IsGreyedOut()");
			e.printStackTrace();
		}
	}

	public void verify_GreyColor_DataWarning(String actualColor, SoftAssert sa) {
		try {

			if (actualColor.equals("#B9B9B9")) {
				APP_LOGS.info("Data warning option is displayed in grey color, color code = " + actualColor);
				sa.assertTrue(true, "Data warning option is displayed in grey color, color code = " + actualColor);
				test.log(LogStatus.PASS, "Data warning option is displayed in grey color, color code = " + actualColor);
			} else {
				APP_LOGS.info("Data warning option is not displayed in grey color");
				sa.fail();
				test.log(LogStatus.FAIL, "Data warning option is not displayed in grey color");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_GreyColor_DataWarning()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_GreyColor_DataWarning()");
			e.printStackTrace();
		}
	}

	public void verify_GreyColor_DataLimit(String actualColor, SoftAssert sa) {

		try {

			if (actualColor.equals("#FAFAFA")) {
				APP_LOGS.info("Data Limit option is displayed in grey color, color code = " + actualColor);
				sa.assertTrue(true, "Data Limit option is displayed in grey color, color code = " + actualColor);
				test.log(LogStatus.PASS, "Data Limit option is displayed in grey color, color code = " + actualColor);
			} else {
				APP_LOGS.info("Data Limit option is not displayed in grey color");
				sa.fail();
				test.log(LogStatus.FAIL, "Data Limit option is not displayed in grey color");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->verify_GreyColor_DataLimit()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->verify_GreyColor_DataLimit()");
			e.printStackTrace();
		}
	}

	public String getColorValue(AndroidElement elem) {
		int decimal = 0;
		String hexaDecimal = null;
		try {
			org.openqa.selenium.Point point = elem.getCenter();
			int centerx = point.getX();
			int centerY = point.getY();

			File scrFile = ((TakesScreenshot) aDriver).getScreenshotAs(OutputType.FILE);
			BufferedImage image = ImageIO.read(scrFile);

			int clr = image.getRGB(centerx, centerY);

			decimal = colorDecimalvalue(pixelARGB(clr));
			hexaDecimal = colorHexaDecimalValue(decimal, elem);
			System.out.println(elem.getText() + " Hexa decimal value = " + hexaDecimal);
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators->getColorValue()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->getColorValue()");
			e.printStackTrace();
		}
		return hexaDecimal;
	}

	public int[] pixelARGB(int pixel) {

		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
		int arr[] = { alpha, red, green, blue };
		return arr;
	}

	public int colorDecimalvalue(int arr[]) {
		int decimalValue = (arr[1] * 65536) + (arr[2] * 256) + (arr[3] * 1);
		return decimalValue;
	}

	public String colorHexaDecimalValue(int decimal, AndroidElement elem) {
		int rem;
		String hex = "";
		char hexchars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		while (decimal > 0) {
			rem = decimal % 16;
			hex = hexchars[rem] + hex;
			decimal = decimal / 16;
		}
		return "#" + hex;
	}

	public String readFile(String filename) throws IOException {
		String content = null;
		try {
			content = new String(Files.readAllBytes(Paths.get(filename)));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	public void clickOn_Search() {
		try {
			if (isElementExist(Locators_DataUsageSetting.NOT_NOW_txt)) {
				clickBtn(Locators_DataUsageSetting.NOT_NOW_txt);
			}
			if (isElementExist(Locators_DataUsageSetting.NOT_NOW_id)) {
				clickBtn(Locators_DataUsageSetting.NOT_NOW_id);
			}

			clickBtn(Locators_DataUsageSetting.Search);

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->clickOn_Search()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->clickOn_Search()");
			e.printStackTrace();
		}

	}

	public void search_Video(String videoName) {
		try {
			enterTextToInputField(Locators_DataUsageSetting.setdata, videoName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->search_Video()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality ->search_Video()");
			e.printStackTrace();
		}

	}
	public boolean scrollToTextContains(String text) {
		/*
		  Method used to select an element on the page by scrolling the Scroll View/List View
		 */
		boolean check = false;
		try {  
			String scrollable = "new UiScrollable(new UiSelector().scrollable(true))";
			String textElement = ".scrollIntoView(new UiSelector().textContains(\""+ text +"\"))";
			aDriver.findElementByAndroidUIAutomator(scrollable+textElement).click();
			APP_LOGS.info("Searched application is found sucessfully : ");
			check = true;
			return check;
		}
		catch(NoSuchElementException e) {
			return check;
		}
	}
	public void launchYoutube() {
		try {
			customWait(1000);
			aDriver.pressKeyCode(AndroidKeyCode.KEYCODE_HOME);
			APP_LOGS.info("HOme PAge");
			customWait(2000);
			Locators_BaseUtil.AppListIcon.click();
			customWait(1000);		
			scrollToElements(Locators_DataUsageSetting.You_Tube);
			clickBtn(Locators_DataUsageSetting.You_Tube);
			APP_LOGS.info("Clicked on Youtube successfully.");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->launchYoutube()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality->launchYoutube()");
			e.printStackTrace();
		}	
	}
	public void play_Video() {
		try {
			if (wait(Locators_DataUsageSetting.sonimvideo, 120)==true) {
				if(isElementExist(Locators_DataUsageSetting.sonimvideo)) {
					   clickBtn(Locators_DataUsageSetting.sonimvideo);
				  }else{
					   scrollToElements(Locators_DataUsageSetting.sonimvideo);
					   if(isElementExist(Locators_DataUsageSetting.sonimvideo)) {
						  clickBtn(Locators_DataUsageSetting.sonimvideo);
					   }else {
						  boolean sonimVideo=scrollToTextContains("Sonim XP8.");
						  while(sonimVideo==false) {
							sonimVideo=scrollToTextContains("Sonim XP8.");
						}
					}
				}
				System.out.println("locator");
			} else {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 348 1635");
				System.out.println("adb command");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> play_Video()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> play_Video()");
			e.printStackTrace();
		}

	}

	public void clickOn_RESUME() {
		try {
			wait(Locators_DataUsageSetting.RESUME, 6000);
			clickBtn(Locators_DataUsageSetting.RESUME);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators-> clickOn_RESUME()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> clickOn_RESUME()");
			e.printStackTrace();
		}
	}

	public void drag_NotificationBar() {
		/*
		 * Get settings option from notification window
		 */
		try {
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.HOME);
			minWait();

			Runtime.getRuntime().exec("adb -s " + p_Id + " shell input swipe 10 10 10 1000");
			minWait();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Did not get Notification bar");
			e.printStackTrace();
		}
	}

	public boolean checkGoogleAccountConfiguration() {
		boolean accountConfigured = false;
		try {
			accountConfigured = (isElementExist(Locators_DataUsageSetting.dusTestMail)) ? true : false;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->checkGoogleAccountConfiguration()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception infunctionality ->checkGoogleAccountConfiguration()");
			e.printStackTrace();
		}
		return accountConfigured;
	}

	public void configureGoogleAccount(String emailId, String pwd) {
		try {
			String firstname = "Xp8 data usage setting", lastname = "Sonim",
					email = "xp8datausagesettingsonim@gmail.com", password = "xp8dus@sonimtech";
			clickBtn(Locators_DataUsageSetting.Add_account);
		//	clickBtn(Locators_DataUsageSetting.Google);
			new WebDriverWait(aDriver, 60)
					.until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.EmailOrPhone)).sendKeys(emailId);
			clickBtn(Locators_DataUsageSetting.Next);
			new WebDriverWait(aDriver, 60).until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.setdata))
					.sendKeys(pwd);
			clickBtn(Locators_DataUsageSetting.Next);
			new WebDriverWait(aDriver, 60).until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.Iagree))
					.click();
			new WebDriverWait(aDriver, 60).until(ExpectedConditions.visibilityOf(Locators_DataUsageSetting.MORE))
					.click();
			clickBtn(Locators_DataUsageSetting.ACCEPT);
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators->configureGoogleAccount()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in ->configureGoogleAccount()");
			e.printStackTrace();
		}

	}

	public boolean disableWifi() {
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.wifiConnectionSate.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.wifiConnectionSate);
				disabled = (Locators_DataUsageSetting.wifiConnectionSate.getText().equalsIgnoreCase("off")) ? true
						: false;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> disableWifi()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> disableWifi()");
		}
		return disabled;
	}

	public boolean enable_Donotdistrub() {
		boolean enabled = false;
		try {
			if (Locators_DataUsageSetting.Donotdistrub_symbol.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DataUsageSetting.Donotdistrub_symbol);
			}
			enabled = true;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> enable_Donotdistrub()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> enable_Donotdistrub()");
		}
		return enabled;
	}

	public boolean disable_Donotdistrub() {
		boolean disabled = false;
		try {
			if (Locators_DataUsageSetting.Donotdistrub_symbol.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.Donotdistrub_symbol);
				if (Locators_DataUsageSetting.Donotdistrub_switch.getText().equalsIgnoreCase("on")) {
					clickBtn(Locators_DataUsageSetting.Donotdistrub_switch);
				}
			}
			disabled = true;

		} catch (org.openqa.selenium.NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators -> disable_Donotdistrub()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exeption in functionality-> disable_Donotdistrub()");
		}
		return disabled;
	}

	public void disable_BT() {
		try {
			if (Locators_DataUsageSetting.BT_switch.getText().equalsIgnoreCase("on")) {
				clickBtn(Locators_DataUsageSetting.BT_switch);
			}

		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => disable_BT()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> disable_BT()");
			e.printStackTrace();
		}

	}

	public void verify_Donotdistrub_IsEnabled(boolean res, SoftAssert sa) {

		try {

			if (res) {
				APP_LOGS.info("Data saver is enabled and Do not distrub mode is enabled successfully ");
				sa.assertTrue(true, "Data saver is enabled and Do not distrub mode is enabled successfully ");
				test.log(LogStatus.PASS, "Data saver is enabled and Do not distrub mode is enabled successfully ");
			} else {
				APP_LOGS.info("Data saver is enabled but Do not distrub mode is not enabled ");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver is enabled but Do not distrub mode is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_Donotdistrub_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception infunctionality -> verify_Donotdistrub_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void verify_Airplane_IsEnabled(boolean res, SoftAssert sa) {

		try {

			if (res) {
				APP_LOGS.info("Data saver is enabled and Airplane mode is enabled successfully ");
				sa.assertTrue(true, "Data saver is enabled and Airplane mode is enabled successfully ");
				test.log(LogStatus.PASS, "Data saver is enabled and Airplane mode is enabled successfully ");
			} else {
				APP_LOGS.info("Data saver is enabled but Airplane mode is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver is enabled but Airplane mode is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_Airplane_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_Airplane_IsEnabled()");
			e.printStackTrace();
		}
	}
	public void verify_PostConditionExcuted(SoftAssert sa) {

		try {
				APP_LOGS.info("Precondition executed");
				sa.assertTrue(true, "Precondition executed");
				test.log(LogStatus.PASS, "Precondition executed");
			} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality -> verify_PostConditionExcuted()");
			e.printStackTrace();
		}
	}
	public boolean enable_BT() {
		boolean enabled = false;
		try {
			if (Locators_DataUsageSetting.BT_switch.getText().equalsIgnoreCase("off")) {
				clickBtn(Locators_DataUsageSetting.BT_switch);
			}
			enabled = Locators_DataUsageSetting.BT_switch.getText().equalsIgnoreCase("on");
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => enable_BT()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> enable_BT()");
			e.printStackTrace();
		}
		return enabled;
	}

	public void verify_Bluetooth_IsEnabled(boolean res, SoftAssert sa) {

		try {

			if (res) {
				APP_LOGS.info("Data saver is enabled and Bluetooth is enabled successfully ");
				sa.assertTrue(true, "Data saver is enabled and Bluetooth is enabled successfully ");
				test.log(LogStatus.PASS, "Data saver is enabled and Bluetooth is enabled successfully ");
			} else {
				APP_LOGS.info("Data saver is enabled but Bluetooth is not enabled");
				sa.fail();
				test.log(LogStatus.FAIL, "Data saver is enabled but Bluetooth is not enabled");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> verify_Bluetooth_IsEnabled()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> verify_Bluetooth_IsEnabled()");
			e.printStackTrace();
		}
	}

	public void preCond_setSleepTime(){
		try {
			Runtime.getRuntime().exec("adb -s "+p_Id+" shell settings put system screen_off_timeout 1800000");
		}catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> preCond_setSleepTime()");
			e.printStackTrace();
		}
	}
	public void preCond_enableData() throws InterruptedException {
		disable_Wifi_ADBcommand();
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();
		clickOn_MobileNetwork();
		enable_MobileData();
	}

	public void preCond_enableWifi(String ssid, String pwd) throws InterruptedException, IOException {
		launch_an_app("settings");
		clickOn_NetworkAndInternet();
		disable_AirplaneMode();
		clickOn_MobileNetwork();
		disable_MobileData();
		clickOn_BackBtn();
		clickOn_Wifi();
		enable_Wifi();
		select_Wifi_SSID(ssid);
		forget_Wifi_SSID();
		select_Wifi_SSID(ssid);
		enter_WifiPwd(pwd);
	}

	public void clickOn_MobileNetwork() {
		try {
			scrollTo("Mobile network");
			scrollToElements(Locators_DataUsageSetting.MobileNetwork_tc1);
			AndroidElement element = multi_Loc_Strategy(Locators_DataUsageSetting.MobileNetwork_tc1, null, null, null,
					null, 371, 546);
			clickBtn(element);
		} catch (NoSuchElementException e) {
			test.log(LogStatus.ERROR, "Error in the locators => clickOn_MobileNetwork()");
			e.printStackTrace();
		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality=> clickOn_MobileNetwork()");
			e.printStackTrace();
		}
	}

	public void preCond_browseData(String videoName, int duration) {
		try {
			clearRecentApps();
			launchYoutube();
			if (isElementExist(Locators_DataUsageSetting.NOT_NOW_txt)) {
				clickBtn(Locators_DataUsageSetting.NOT_NOW_txt);
			}
			if (isElementExist(Locators_DataUsageSetting.NOT_NOW_id)) {
				clickBtn(Locators_DataUsageSetting.NOT_NOW_id);
			}
			clickBtn(Locators_DataUsageSetting.Search);
			enterTextToInputField(Locators_DataUsageSetting.setdata, videoName);
			minWait();
			aDriver.pressKeyCode(AndroidKeyCode.ENTER);
			minWait();
			if (wait(Locators_DataUsageSetting.sonimvideo, 10)) {
				clickBtn(Locators_DataUsageSetting.sonimvideo);
				System.out.println("locator");
			} else {
				Runtime.getRuntime().exec("adb -s " + p_Id + " shell input tap 500 1090");
				System.out.println("adb command");
			}
			wait(Locators_DataUsageSetting.Data_limit,duration);
			System.out.println("Video played for -> "+duration+" seconds");
		} catch (org.openqa.selenium.NoSuchElementException e) {

			test.log(LogStatus.ERROR, "Error in the locators-> preCond_browseData()");
			e.printStackTrace();

		} catch (Exception e) {
			test.log(LogStatus.ERROR, "Exception in functionality-> preCond_browseData()");
			e.printStackTrace();
		}

	}

}
