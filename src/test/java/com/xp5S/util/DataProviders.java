package com.xp5S.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.aosp.Utils.ExcelUtils;

public class DataProviders {
	
	  @DataProvider(name ="SonimCareTest")
	    public static Object[][] SonimCareTest(Method m) throws Exception{
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
	  
	  @DataProvider(name ="Sonim_Warranty_Data_Provider")
	    public static Object[][] WarrantyRegestraion(Method m) throws Exception{
		  System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);
}
	  
	  @DataProvider(name ="AppUpdaterTest")
	    public static Object[][] AppUpdaterTest(Method m) throws Exception{
		 System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);
}
	  
	  @DataProvider(name ="Safe_Guard_Data_Provider")
	    public static Object[][] Safe_Guard_Data_Provider(Method m) throws Exception{
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
	  
	  @DataProvider(name ="SanityTest")
	    public static Object[][] SanitySuiteDataProvider(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);
}	  	  
	  
	  @DataProvider(name ="CalculatorTest")
	    public static Object[][] CalculatorTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);	  
}
	  
	  @DataProvider(name ="FMTest")
	    public static Object[][] FMTest(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);	  
}	  
	  
	  
	  @DataProvider(name ="SoundRec")
	    public static Object[][] SoundRec(Method m) throws Exception{
		System.out.println(m.getName());
		ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			
			String testcase = m.getName();
			
			return ExcelUtils.getData(testcase, excel);	  
}	  
	
	 
	  
	  @DataProvider(name = "ScoutSanity_Data_Provider")
	  public static Object[][] ScoutSanity_Data_Provider(Method m) throws Exception{
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

			String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "Contacts");
	  }
	  
	  @DataProvider(name = "DeviceStability")
	  public static Object[][] DeviceStability(Method m) throws Exception{
			System.out.println(m.getName());                                                       
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

			String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "XP3_DeviceStability");
	  }
	  
	  
	  
	 /* @DataProvider(name = "XP3_Device_Sanity")
	  public static Object[][] XP3_Device_Sanity(Method m) throws Exception{
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);

			String testcase = m.getName();
	   return ExcelUtils.getData_aosp(testcase, excel, "XP3_Device_Sanity");
	  }*/
	  @DataProvider(name ="XP5S_ContactTransfer")
		public static Object[][] XP5S_ContactTransfer(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP5S_ContactTransfer");		
		}
		
		@DataProvider(name ="XP5S_WarrantyRegistration")
		public static Object[][] XP5S_WarrantyRegistration(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP5S_WarrantyRegistration");		
		}
		
		@DataProvider(name ="XP3_ContactTransfer")
		public static Object[][] XP3_ContactTransfer(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP3_ContactTransfer");		
		}
		
		@DataProvider(name ="XP3_WarrantyRegistration")
		public static Object[][] XP3_WarrantyRegistration(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP3_WarrantyRegistration");		
		}
	  
		@DataProvider(name ="XP3_Safeguard")
		public static Object[][] XP3_Safeguard(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP3_Safeguard");		
		}
		
		@DataProvider(name ="XP5S_Safeguard")
		public static Object[][] XP5S_Safeguard(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP5S_Safeguard");		
		}
		
		@DataProvider(name ="XP3_SonimCare")
		public static Object[][] XP3_SonimCare(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP3_SonimCare");		
		}
		
		@DataProvider(name ="XP5S_SonimCare")
		public static Object[][] XP5S_SonimCare(Method m) throws Exception {
			
			System.out.println(m.getName());
			ExcelReader excel = new ExcelReader(ExcelConstants.XP5S_XL_PATH);
			String testcase = m.getName();
			return ExcelUtils.getData_aosp(testcase, excel,"XP5S_SonimCare");		
		}
		//Added comment to test
	  
	  
}	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  	  

