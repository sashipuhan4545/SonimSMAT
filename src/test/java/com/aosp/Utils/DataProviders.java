package com.aosp.Utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.xp5S.util.ExcelConstants;
import com.xp5S.util.ExcelReader;




public class DataProviders {

	@DataProvider(name = "XP5S_Settings_aosp_trial")
	public static Object[][] XP5S_Settings_aosp_trial(Method m) throws Exception {
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "settings");
	}

	// DataProvider For SMS.
	@DataProvider(name ="SMS")
	public static Object[][] SMS(Method m) throws Exception{

		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
		String testcase = m.getName();
		return ExcelUtils.getData_aosp(testcase, excel, "SMS");
	}


	  @DataProvider(name = "XP5S_aosp_Data")
	  public static Object[][] XP5S_aosp_Data(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "Aosp_Data_TestData");
	  }	  
	  
	  @DataProvider(name = "XP5_AOSP_Sanity")
	  public static Object[][] XP5_AOSP_Sanity(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "Sanity");
	  }
	  
	  
	  @DataProvider(name = "XP5S_Stability")
	  public static Object[][] XP5S_Stability(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "Stability");
	  }
	  
	  @DataProvider(name = "XP5S_Contacts")
	  public static Object[][] XP5S_Contacts(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "Contacts");
	  }
	  
	  @DataProvider(name = "ScoutSanity_Data_Provider")
	  public static Object[][] ScoutSanity_Data_Provider(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "SCOUT_Sanity");
	  }
	  
	  @DataProvider(name = "XP5_Dev_Sanity")
	  public static Object[][] XP5_Dev_Sanity(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "DevSanity");
	  }
	  
	  
	  
	  @DataProvider(name = "XP3_QuickSanity")
	  public static Object[][] XP3_QuickSanity(Method m) throws Exception {
	   System.out.println(m.getName());
	   ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
	   String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "XP3_Sanity");
	  }
	  
	  
	  @DataProvider(name = "XP3_Device_Sanity")
	  public static Object[][] XP3_Device_Sanity(Method m) throws Exception{
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

			String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "XP3_Device_Sanity");
	  }
	  
	 
	  

}
