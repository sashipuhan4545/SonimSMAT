package com.graphics.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestResult;
import org.testng.TestNG;

import com.mongodb.util.JSON;



public class JsonFileReaderAndWriter {

	public static String JSonFileReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/config_file.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceId = (String) jsonObject.get("DeviceId");
		return deviceId;


	}


	public static String ReadMobileNumFrmJson() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/RefMobileNumber.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String mobileNum = (String) jsonObject.get("RefMobileNum");
		return mobileNum;


	}

	public static void JsonFileWriter(String priDeviceId) {

		JSONObject obj=new JSONObject();
		obj.put("DeviceId", priDeviceId);



		try (FileWriter file = new FileWriter("src/test/resources/drivers/config_file.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void RefMobileNumber(String RefMobNum) {

		JSONObject obj=new JSONObject();
		obj.put("RefMobileNum", RefMobNum);


		try (FileWriter file = new FileWriter("src/test/resources/drivers/RefMobileNumber.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//This Method is used to write the deviceId into Json file
	public static void WriteRefDevIDtoJson(String RefDevId,String Reffirmware,String RefModelNum,String RefOperatorName,String chkSimAvailability) throws IOException {

		
		JSONObject obj=new JSONObject();
		obj.put("primaryDevId", RefDevId);
		obj.put("firmwareVersion", Reffirmware);
		obj.put("prideviceModel", RefModelNum);
		obj.put("operatorName", RefOperatorName);
		obj.put("simAvailability", chkSimAvailability);
		


		try (FileWriter file = new FileWriter("src/test/resources/drivers/RefDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void WriteNullToTemp(String value) throws IOException {

		
		JSONObject obj=new JSONObject();
		


		try (FileWriter file = new FileWriter("src/test/resources/drivers/temp.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static String RefDevOperatorName() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String RefDevOperatorName = (String) jsonObject.get("operatorName");
		return RefDevOperatorName;


	}

	//This Method is used to read the deviceId into Json file

	public static String ReadRefDeviceId() throws FileNotFoundException, IOException, ParseException {
		

     
		JSONParser parser = new JSONParser();
	
	
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		
		JSONObject jsonObject =  (JSONObject) obj;
		 String mobileNum = (String) jsonObject.get("primaryDevId");
		 return mobileNum;
		
      


	}

	public static String RefDeviceModelNum() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String RefDeviceModelNum = (String) jsonObject.get("prideviceModel");
		return RefDeviceModelNum;


	}
	public static String ReadRefDeviceFirmWare() throws FileNotFoundException, IOException, ParseException {

		
	
		JSONParser parser = new JSONParser();
		
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		
		JSONObject jsonObject =  (JSONObject) obj;
		 String mobileNum = (String) jsonObject.get("firmwareVersion");
	 	return mobileNum;
		
		


	}



	public static void TestCaseNameWriter(String methodName) {

		JSONObject obj=new JSONObject();
		obj.put("TestCaseName", methodName);

		try (FileWriter file = new FileWriter("src/test/resources/drivers/TestCaseName.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}



	public static String TestCaseNameReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/TestCaseName.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String testcaseName = (String) jsonObject.get("TestCaseName");
		return testcaseName;
	}

	public static void deviceModelWriter(String model) {

		JSONObject obj=new JSONObject();
		obj.put("model", model);

		try (FileWriter file = new FileWriter("src/test/resources/drivers/DeviceModel.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	public static String deviceModelreader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/DeviceModel.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("model");
		return deviceModel;
	}

	//This is used for Two device commnication

	public static void primaryDevIdWriter(String primaryDeviceId,String priDevModel,String operatorName,String firmwareVersion,String chkSimAvailability) throws IOException {


		
		JSONObject obj=new JSONObject();

		obj.put("primaryDevId", primaryDeviceId);
		obj.put("prideviceModel", priDevModel);
		obj.put("operatorName", operatorName);
		obj.put("firmwareVersion", firmwareVersion);
		obj.put("simAvailability", chkSimAvailability);
		


		try (FileWriter file = new FileWriter("src/test/resources/drivers/primaryDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String PrimaryDevicesimAvailability() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("simAvailability");
		return deviceModel;
	}
	

	public static void OnlyPrimaryDeviceIdWriter(String OnlyprimaryDeviceId) {

		JSONObject obj=new JSONObject();
		obj.put("primaryDevId", OnlyprimaryDeviceId);

		try (FileWriter file = new FileWriter("src/test/resources/drivers/primaryDeviceId.json")) {

			file.write(obj.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static String primaryDevIdReader() throws FileNotFoundException, IOException, ParseException{
		
		
      
		JSONParser parser = new JSONParser();
		
		
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
	
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel= (String) jsonObject.get("primaryDevId");
		System.out.println(deviceModel);
		return deviceModel;
	}
	public static String primaryDevModelReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("prideviceModel");
		return deviceModel;
	}
	public static String primaryDevOperatorReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("operatorName");
		return deviceModel;
	}

	public static String primaryDevFirmwareReader() throws FileNotFoundException, IOException, org.json.simple.parser.ParseException {


		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
		JSONObject jsonObject =  (JSONObject) obj;
		String deviceModel = (String) jsonObject.get("firmwareVersion");
		return deviceModel;
	}
	
	
	
 public void swap() throws FileNotFoundException, IOException, ParseException {

	
		JSONParser parser = new JSONParser();
		
		Object ref = parser.parse(new FileReader("src/test/resources/drivers/RefDeviceId.json"));
		Object pri = parser.parse(new FileReader("src/test/resources/drivers/primaryDeviceId.json"));
	
	
		

		JSONObject jsonObject3 =  (JSONObject)ref ;
		try (FileWriter file = new FileWriter("src/test/resources/drivers/primaryDeviceId.json")) {

			file.write(jsonObject3.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JSONObject jsonObject1 =  (JSONObject) pri;
		try (FileWriter file = new FileWriter("src/test/resources/drivers/RefDeviceId.json")) {

			file.write(jsonObject1.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}


 }

}





























