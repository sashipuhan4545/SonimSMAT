package com.xp8.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.aosp.Utils.ExcelUtils;
import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;

public class DataProviders {

	@DataProvider(name ="LaunchSettingsApp")
	public static Object[][] LaunchSettingsApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="LaunchCalculatorApp")
	public static Object[][] LaunchCalculatorApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchContactsApp")
	public static Object[][] LaunchContactsApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

		String testcase = m.getName();

		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchSoundRecorderApp")
	public static Object[][] LaunchSoundRecorderApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="LaunchFMRadioApp")
	public static Object[][] LaunchFMRadioApp(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="ProgrammableKeyTest")
	public static Object[][] ProgrammableKeyTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="DailerTest")
	public static Object[][] DailerTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}


	@DataProvider(name ="XP8_SonimCareTest")
	public static Object[][] SonimCareTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="SafeguardTest")
	public static Object[][] SafeguardTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="SonimWarrantyReg")
	public static Object[][] SonimWarrantyReg(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}

	@DataProvider(name ="ContactTransfer")
	public static Object[][] ContactTransfer(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}



	@DataProvider(name ="XP8_Browser")
	public static Object[][] XP8_Browser(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel);
	}	

	@DataProvider(name = "XP8_Stability")
	public static Object[][] XP8_Stability(Method m) throws Exception {
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "XP8_Stability");
	}
	
	
	@DataProvider(name = "XP8_ATTStability")
	public static Object[][] XP8_ATTStability(Method m) throws Exception {
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "XP8_ATTStability");
	}

	@DataProvider(name ="XP8SanityTest")
	public static Object[][] XP8SanityTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "XP8_Device_Sanity");
	}

	@DataProvider(name ="XP8_ScoutFTTest")
	public static Object[][] XP8_ScoutFTTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "SCOUT_Sanity");
	}
	
	@DataProvider(name ="XP8DevSanity")
	 public static Object[][] XP8DevSanity(Method m) throws Exception{
	  System.out.println(m.getName());
	  ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	  String testcase = m.getName();
	  return ExcelUtils.getData_aosp(testcase, excel,"XP8_Dev_Sanity");
	 }
	
	@DataProvider(name ="XP8_PhoneDialer")
	 public static Object[][] XP8_PhoneDialer(Method m) throws Exception{
	  System.out.println(m.getName());
	  ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	  String testcase = m.getName();
	  return ExcelUtils.getData_aosp(testcase, excel,"XP8_PhoneDialer_new");
	 }
	
	@DataProvider(name = "Stability")
	public static Object[][] Stability(Method m) throws Exception {
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "XP8_Browser_Stability");
	}
	
	
	@DataProvider(name ="XP5S_ContactTransfer")
	public static Object[][] XP5S_ContactTransfer(Method m) throws Exception {
		
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel,"Contact_Transfer");		
	}
	
	@DataProvider(name ="XP8_Contacts")
	public static Object[][] XP8_Contacts(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData(testcase, excel,"XP8_Contacts");
	}
  
	@DataProvider(name ="XP8_ContactTransfer")
	public static Object[][] XP8_ContactTransfer(Method m) throws Exception {
		
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel,"XP8_ContactTransfer");		
	}
	
	@DataProvider(name ="XP8_SafeGuard")
	public static Object[][] XP8_SafeGuard(Method m) throws Exception {
		
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel,"XP8_SafeGuard");		
	}
	
	@DataProvider(name ="XP8_CallHistory")
	public static Object[][] XP8_CallHistory(Method m) throws Exception {
		
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel,"XP8_CallHistory");		
	}	
	


}
