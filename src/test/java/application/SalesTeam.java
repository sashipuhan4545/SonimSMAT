package application;


import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.Optional;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

import com.graphics.gui.JsonFileReaderAndWriter;


import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.testng.SkipException;
import org.testng.TestNG;


import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;



import com.xp5S.util.BaseUtil;
import com.xp5S.util.CommonConfig;
import com.xp5S.util.appiumService;

import AllGMSTC.EmailAttachmentSender;

import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;



public class SalesTeam  extends CommonConfig {

	//ObservableList<String> items=FXCollections.observableArrayList("Sanity Test","MultiMedia","Messaging","Connectivity","GMS","Browser","Settings","Tools","Contacts","Call","ScoutApps","Performance","DeviceFunctionality");
	//ObservableList<String> items=FXCollections.observableArrayList("Sanity Test","CallPerformance");
	ObservableList<String> items=FXCollections.observableArrayList("Sanity Test","VOLTE-CallPerformance","3G-CallPerformance","Stability","SCOUT"/*,"Stability_AT&T-15595"*/);

	//ObservableList<String> items=FXCollections.observableArrayList("Quick Sanity","Performance");

	//ObservableList<String> Sanity = FXCollections.observableArrayList("Sanity");

	ObservableList<String> Sanity = FXCollections.observableArrayList("Device","Dev-Sanity");

	ObservableList<String> connectivity = FXCollections.observableArrayList("Bluetooth","Wi-Fi","Mobile Data");
	ObservableList<String> multiMedia = FXCollections.observableArrayList("Photos","Music","FM","All");
	ObservableList<String> Messaging = FXCollections.observableArrayList("SMS","MMS","Email","All");
	ObservableList<String> GMS = FXCollections.observableArrayList("YouTube","Chrome","Google Apps","All");
	ObservableList<String> browser = FXCollections.observableArrayList("Native Browser");
	ObservableList<String> Settings = FXCollections.observableArrayList("Settings","Programmable Key","Power ON/OFF","All");
	ObservableList<String> Tools = FXCollections.observableArrayList("Calculator","Calendar","Clock","SoundRecorder","Downloads","FileManager","All");
	ObservableList<String> Contacts = FXCollections.observableArrayList("PhoneContacts");
	ObservableList<String> call = FXCollections.observableArrayList("PhoneDialer");
	ObservableList<String> ScoutApps = FXCollections.observableArrayList("AppUpdater","SafeGuard","SonimCare","SonimWarranty","ContactTransfer","All");
	//ObservableList<String> performance = FXCollections.observableArrayList("CallPerformance","Stability","Browser","All");
	ObservableList<String> CallPerformance = FXCollections.observableArrayList("MO-Call","MT-Call","MO-MT-Call");
	ObservableList<String> stability = FXCollections.observableArrayList("Device Stability","ATT Stability");

	ObservableList<String> devicefunctionality = FXCollections.observableArrayList("DeviceFunctionality-All","DeviceFunctionality_WithOut-SCOUT","DeviceFunctionality_WithOut-SCOUT-Performance");




	public static AndroidDebugBridge debugBridge;
	public int i;
	public String GLOBAL_ONTESTSTART;

	public String GLOBAL_ONTESTSUCESS;

	public String GLOBAL_ONTESTFAILURE;
	public String GLOBAL_ONTESTSKIPPED;
	public static String PRIMARYDEVMDN;
	public static String REFERENCEDEVMDN;

	//public static String EMAILID;

	//Here call duration defualt value is 1 minute
	public static int CALLDURATION=1;
	//Here call gap defualt value is 1 minute
	public static int CALLGAP=5;

	public static int NUMOFCALLS;
	public String comboBoxItems;
	public Timer onfinish;
	public Timer timer2;
	public static Timer timer3_failure;
	public Timer onskip;
	public static Dialog<Pair<String, String>> dialog;
	public Timer percentageCalculator;

	public Button executeFailedTestCases;







	@FXML
	private TextField DUTMDN;

	@FXML
	private TextField RefMDN;


	@FXML
	public Label loginAsTeam;

	@FXML
	public ProgressIndicator progressIndicator;


	@FXML
	public Label status;


	public String operatorName;

	@FXML
	public AnchorPane anchorPane;

	@FXML
	private Button deviceDetect;

	@FXML
	private Button swapBtn;

	@FXML
	private ComboBox<String> combobox;

	@FXML
	private ListView<String> listView;

	@FXML 
	private TextField DUTID;

	@FXML
	private TextField RefID;

	@FXML
	private TextField DUTBuildNo;
	@FXML
	private TextField RefDUTBuildNo;

	@FXML
	private TextField product_DUT;

	@FXML
	private TextField operator_DUT;

	@FXML
	private TextField product_Ref;

	@FXML
	private TextField operator_Ref;

	@FXML
	private Button start;

	@FXML
	private Button stopButton;

	@FXML
	private Button ResetBtn;

	@FXML
	public TextField currentTestCases;

	/*@FXML
	private MenuItem file_Exit;*/

	@FXML
	private AnchorPane secondAnchorPane;


	@FXML
	public TextArea ExecutedTC=new TextArea();

	@FXML
	TextArea failedTC=new TextArea();

	@FXML
	TextField IMEI_DUT;

	@FXML
	TextField IMEI_REF;


	public  Timer currentTcTimer;
	public  Timer timer1;


	public ProgressIndicator pi;
	public  ProgressIndicator loadingScreen;



	@FXML
	public void Device_Detection(ActionEvent event) throws FileNotFoundException, IOException, ParseException, InterruptedException {

		try {

			JsonFileReaderAndWriter.primaryDevIdWriter(null, null, null, null,null);
			JsonFileReaderAndWriter.WriteRefDevIDtoJson(null, null, null, null,null);
			AndroidDebugBridge.init(false);
			String SMAT_ADB_PATH=System.getenv("LOCALAPPDATA") +"\\SMAT\\Android\\platform-tools\\adb.exe";
			debugBridge = AndroidDebugBridge.createBridge(SMAT_ADB_PATH, true);

			if (debugBridge == null) {
				System.err.println("Invalid ADB location.");
				System.exit(1);
			}

			AndroidDebugBridge.addDeviceChangeListener(new IDeviceChangeListener() {



				public void deviceChanged(IDevice device, int arg1) 
				{


				}


				public void deviceConnected(IDevice device) 
				{

					if(i==0)
					{


						//This code is for primary device ID
						try {

							DUTID.setText(device.getSerialNumber());

							//	deviceDetect.setStyle("-fx-background-color: #1aff1a");
							deviceDetect.setDisable(true);
							combobox.setDisable(false);
							ResetBtn.setDisable(false);


							JsonFileReaderAndWriter.primaryDevIdWriter(device.getSerialNumber()
									,device.getName(),
									device.getPropertyCacheOrSync("gsm.version.baseband"),
									device.getPropertyCacheOrSync("ro.build.id"),device.getPropertyCacheOrSync("gsm.sim.operator.alpha"));



							IMEI_DUT.setText(BaseUtil.fetchDeviceProperty("adb -s "+JsonFileReaderAndWriter.primaryDevIdReader()+" shell \"service call iphonesubinfo 1 | toybox cut -d \\\"'\\\" -f2 | toybox grep -Eo '[0-9]' | toybox xargs | toybox sed 's/\\ //g'\""));


						} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException
								| IOException | ParseException  e) {

							System.out.println("Problem occurs in Primary device detection where i=0");

							//		Platform.runLater(()->fingerPrintAlertBox("Please enable the USB debugging/Ack the RSA key"));  


						} 


						try {


							DUTBuildNo.setText(JsonFileReaderAndWriter.primaryDevFirmwareReader());

						} catch (IOException | ParseException e) {

							e.printStackTrace();
						}



					}else if (i==1)
					{

						try {



							JsonFileReaderAndWriter.WriteRefDevIDtoJson(device.getSerialNumber(),device.getPropertyCacheOrSync("ro.build.id"),
									device.getName(),device.getPropertyCacheOrSync("gsm.version.baseband"),
									device.getPropertyCacheOrSync("gsm.sim.operator.alpha"));
							RefID.setText(device.getSerialNumber());
							try {
								RefDUTBuildNo.setText(JsonFileReaderAndWriter.ReadRefDeviceFirmWare());


							} catch (ParseException e) {

								e.printStackTrace();
							}

							IMEI_REF.setText(BaseUtil.fetchDeviceProperty("adb -s "+JsonFileReaderAndWriter.ReadRefDeviceId()+" shell \"service call iphonesubinfo 1 | toybox cut -d \\\"'\\\" -f2 | toybox grep -Eo '[0-9]' | toybox xargs | toybox sed 's/\\ //g'\""));



						} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException
								| IOException | ParseException e) {

							//	Platform.runLater(()->fingerPrintAlertBox("Please enable the USB debugging/Ack the RSA key"));  
						}

					}
					i++;





				}


				public void deviceDisconnected(IDevice device) {


					deviceDetect.setStyle("-fx-background-color: #ff0000"); 

					/*	Thread t=new Thread(new Runnable() {
						public void run() {

							Platform.runLater(()->AlertDialog("Device disconnected .Please restart the tool and try again..."));

						}
					});
					t.start();*/



				}


			});





		}
		catch (java.lang.IllegalStateException e) 
		{

			Alert alert= new Alert(AlertType.INFORMATION);
			alert.setTitle("S-MAT");
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setContentText("Please check your ADB connection/Restart the tool...");

			Optional<ButtonType> action = alert.showAndWait();
			if(action.get()==ButtonType.OK) {


			}
		} catch (Exception e) {

			System.out.println("Error occurs in Adb connection");

		}




		//This sleep is given to fetch the value form Json file after writing the device information into Json
		try {

			Thread.sleep(1000);
			if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800")){

				product_DUT.setText("XP5800");

				if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {



					operator_DUT.setText("ATT"); 
				}

				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

					operator_DUT.setText("Rogers"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

					operator_DUT.setText("Bell"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

					operator_DUT.setText("Telus"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {


					operator_DUT.setText("Sprint"); 
				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {


					operator_DUT.setText("SL"); 
				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-32.")) {

					operator_DUT.setText("ACG"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34.")) {

					operator_DUT.setText("USC"); 

				}else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

					operator_DUT.setText("Verizon"); 

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00")) {

					operator_DUT.setText("MainLine"); 
				}

			}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {

				product_DUT.setText("XP8800");

				if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {


					operator_DUT.setText("ATT"); 

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

					operator_DUT.setText("Rogers");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

					operator_DUT.setText("Telus");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

					operator_DUT.setText("Bell");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {

					operator_DUT.setText("SL");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {

					operator_DUT.setText("Sprint");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-30.")) {

					operator_DUT.setText("JVCK");

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

					operator_DUT.setText("Verizon");

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00.")) {

					operator_DUT.setText("MainLine");
				}

			}
			else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A.")) {

				product_DUT.setText("XP3800");

				if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {


					operator_DUT.setText("ATT"); 


				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34")) {

					operator_DUT.setText("USC"); 

				}else {

					operator_DUT.setText("GEN"); 
				}

			}
			else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-30.")) {


				product_DUT.setText("XP5800");
				if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-30.") ) {


					operator_DUT.setText("JVCK"); 

				}
				else {   

					operator_DUT.setText("GEN"); 
				}


			}


			else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {



				product_DUT.setText("XP5800");
				if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {


					operator_DUT.setText("SB"); 

				}
				else {   

					operator_DUT.setText("GEN"); 
				}


			}





			//Reference device selection
			Thread.sleep(1000);

			if(JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp5800"))	{

				product_Ref.setText("XP5800");

				if(JsonFileReaderAndWriter.RefDevOperatorName().contains("ATT")) {

					System.out.println("Executing if part");


					operator_Ref.setText("ATT"); 
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-12.")) {


					operator_Ref.setText("Telus");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					operator_Ref.setText("Sprint");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-26.")) {

					operator_Ref.setText("SL");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-18.")) {


					operator_Ref.setText("Rogers");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-11.")) {


					operator_Ref.setText("Bell");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-32.")) {


					operator_Ref.setText("ACG");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-34.")) {


					operator_Ref.setText("USC");  

				}else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-15.")) {


					operator_Ref.setText("Verizon");  
				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-00")) {

					operator_Ref.setText("MainLine"); 

				}



			}
			else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")){
				product_Ref.setText("XP8800");

				if(JsonFileReaderAndWriter.RefDevOperatorName().contains("ATT")) {


					operator_Ref.setText("ATT");  
				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-18.")) {

					operator_Ref.setText("Rogers"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-12.")) {

					operator_Ref.setText("Telus"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-11.")) {

					operator_Ref.setText("Bell"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-26.")) {

					operator_Ref.setText("SL"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					operator_Ref.setText("Sprint"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

					operator_Ref.setText("JVCK"); 


				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-15.")) {

					operator_Ref.setText("Verizon"); 


				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-00.")) {

					operator_Ref.setText("MainLine"); 


				}

			}
			else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp3800") || JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("3A.")) {

				product_Ref.setText("XP3800");

				if(JsonFileReaderAndWriter.RefDevOperatorName().contains("ATT")) {


					operator_Ref.setText("ATT");  

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34")) {

					operator_DUT.setText("USC"); 

				}else {

					operator_DUT.setText("GEN"); 
				}

			}
			else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

				product_Ref.setText("XP8800");

				if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

					/*operatorName=JsonFileReaderAndWriter.RefDevOperatorName();
				String[] split=operatorName.split("-");*/
					operator_Ref.setText("JVCK");  
				}
				else {

					operator_Ref.setText("GEN"); 
				}
			}
			else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-35.")) {

				product_Ref.setText("XP5800");

				if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-35.")) {

					/*operatorName=JsonFileReaderAndWriter.RefDevOperatorName();
				String[] split=operatorName.split("-");*/
					operator_Ref.setText("SB");  
				}
				else {

					operator_Ref.setText("GEN"); 
				}

			}



			//Here changing the color to green once both the devices are detected 
			deviceDetect.setStyle("-fx-background-color: #1aff1a");  
			showMdnDialogbox(); 
			itemSelectedListner();
			itemListner2();

		}catch(Exception e) {


			e.printStackTrace();

			//Here changing the red once either of  the devices are not detected 

			deviceDetect.setStyle("-fx-background-color:#ff0000");

			Alert alert = new Alert(AlertType.WARNING);
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			dialogPane.getStyleClass().add("DeviceDetection");
			alert.setTitle("S-MAT");
			alert.initStyle(StageStyle.UNDECORATED);
			alert.setHeaderText("Device Detection:");
			alert.setContentText("1.Please check the 2nd device connection \n"
					+ "2.Make sure ADB is enabled & Cross check in command prompt using ADB command [adb devices]\n"
					+ "3.Accept \"Allow USB debugging\" in reference device\n\n"
					+ "Note: SMAT tool will be closed & Launch and Log-in again");
			Optional<ButtonType> action = alert.showAndWait();
			if(action.get()==ButtonType.OK) {

				System.exit(0);
				/*		



				Thread t=new Thread(new Runnable() {

					@Override
					public void run() {



						try {
							Parent parentroot = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
							Scene scene = new Scene(parentroot, 800, 438);
							Stage secondaryStage1 = new Stage();
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							secondaryStage1.resizableProperty().setValue(false);
							secondaryStage1.initStyle(StageStyle.UNDECORATED);
							secondaryStage1.getIcons().add(new Image("/application/newLogo.png"));
							secondaryStage1.setScene(scene);
							secondaryStage1.setTitle("S-MAT");
							secondaryStage1.show();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				t.start();

				 */

			}

		}

	}



	@FXML
	public void initialize(){

		System.out.println("Initialise Sales");


		try {

			combobox.setItems(items);


			progressIndicator.getStylesheets().add(getClass().getResource("").toExternalForm());
			progressIndicator.getStyleClass().add("ProgressIndicator");


			listView.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listView.getStyleClass().add("ListViewItems");


			start.setDisable(true);
			combobox.setDisable(true);
			stopButton.setDisable(true);
			Platform.runLater(() ->loginAsTeam.setText(MainController.TEAM_BASED_LOGIN));



			comboBoxItems=(String) combobox.getSelectionModel().getSelectedItem();

			if(comboBoxItems=="Sanity Test")
			{
				listView.setItems(Sanity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


			}else if (comboBoxItems=="3G-CallPerformance" || comboBoxItems=="VOLTE-CallPerformance") {

				listView.setItems(CallPerformance);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}


			//	itemSelectedListner();



		}catch (Exception e) {
			System.out.println("Some problem occurs in initialise Method");
		}


	}




	@FXML
	public void executeTC(ActionEvent event) throws FileNotFoundException, IOException, ParseException, InterruptedException {

		try {


			ProgressIndicator(); 
			progressAction();
			fetchExecutionStatus();
			saveReportAndLogInLocalSystem();





			if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {

				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();

				for (String itemvalue : topics) {



					switch (itemvalue) {

					case "DEV-QA-Sanity":

						Thread DS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_Dev_Sanity.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						DS.start();
						break;

					case "PhoneContacts":
						Thread PC=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_PhoneContacts.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						PC.start();
						break;

					case "SCOUT":
						Thread SCOUT=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_SCOUT_Sanity.xml");

								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SCOUT.start();
						break;



					case "Device":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5S_Sanity.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	


								}
							});
							DeviceSanity.start();

						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.0")) {

							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5_Device_QA_Sanity_O.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	


								}
							});
							DeviceSanity.start();


						}

						break;

					case "SafeGuard":
						Thread SG=new Thread(new Runnable() {


							public void run() {


								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/SafeGuardTest_ATT.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SG.start();
						break;

					case "AppUpdater":
						Thread AU=new Thread(new Runnable() {


							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/AppUpdaterTest_ATT.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						AU.start();
						break;

					case "SonimCare":
						Thread SC=new Thread(new Runnable() {


							public void run() {


								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/SonimCareTest_ATT.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SC.start();
						break;

					case "SonimWarranty":
						Thread SW=new Thread(new Runnable() {


							public void run() {


								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/SonimWarrantyRegTest_ATT.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SW.start();
						break;

					case "PhoneDialer":
						Thread PhoneDialer=new Thread(new Runnable() {
							public void run() {

								Platform.runLater(()->AlertDialog("Yet to implemented"));



							}
						});
						PhoneDialer.start();
						break;

					case "MO-MT-Call":
						Thread CP=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						CP.start();	
						break;

					case "MO-Call":
						Thread MOCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MO-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MOCALL.start();	
						break;

					case "MT-Call":
						Thread MTCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MT-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MTCALL.start();	
						break;	

					case "ContactTransfer":
						Thread CT=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/ContactTransferTest_ATT.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						CT.start();	
						break;

					case "Device Stability":
						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread stability=new Thread(new Runnable() {


								public void run() {

									System.out.println("XP5 Stability");
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5_Stability.xml");

									runner.setTestSuites(suitefiles);
									runner.run();
								}
							});
							stability.start();	

						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {

							Thread stability=new Thread(new Runnable() {


								public void run() {

									System.out.println("XP5 Stability");
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5_Stability_O.xml");

									runner.setTestSuites(suitefiles);
									runner.run();
								}
							});
							stability.start();	

						}
						break;



					default:
						break;
					}


					listView.setDisable(true);
					combobox.setDisable(true);
					ResetBtn.setDisable(true);
					start.setDisable(true);

					stopButton.setDisable(false);

				}





			}
			else if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {




				System.out.println("Executing on XP8 device"); 
				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();
				//start.setDisable(true);
				for (String itemvalue : topics) {


					switch (itemvalue) {

					case "SCOUT":
						Thread SCOUT=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Scout.xml");

								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SCOUT.start();
						break;

					case "Phone":
						Thread Dev_Sanity=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Dev_Sanity.xml");
								System.out.println("Starting ATT stability tc");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						Dev_Sanity.start();
						break;

					case "PhoneDialer":
						Thread PhoneDialer=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_PhoneDialer.xml");
								System.out.println("Starting ATT stability tc");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						PhoneDialer.start();
						break;

					case "DEV-QA-Sanity":
						Thread DS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Dev_Sanity.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						DS.start();
						break;




					case "ATT Stability":
						Thread attStability=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_ATT_Stability.xml");
								System.out.println("Starting ATT stability tc");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						attStability.start();
						break;


					case "Device":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Sanity.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							DeviceSanity.start();

						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Sanity_O.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							DeviceSanity.start();

						}
						break;



					case "MO-MT-Call":
						Thread CP=new Thread(new Runnable() {


							public void run() {


								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						CP.start();	
						break;

					case "MO-Call":
						Thread MOCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MO-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MOCALL.start();	
						break;

					case "MT-Call":
						Thread MTCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MT-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MTCALL.start();	
						break;

					case "Device Stability":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
							Thread stability=new Thread(new Runnable() {


								public void run() {


									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Stability.xml");

									runner.setTestSuites(suitefiles);
									runner.run();
								}
							});
							stability.start();	
						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


							Thread stability=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Stability_Orio.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							stability.start();
						}

						break;


					}


					listView.setDisable(true);
					combobox.setDisable(true);
					ResetBtn.setDisable(true);					
					start.setDisable(true);
					stopButton.setDisable(false);



				}

			}else if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A")) {

				System.out.println("Executing on XP3 device"); 

				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();

				for (String itemvalue : topics) {



					switch (itemvalue) {

					case "Device":

						Thread DS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_Sanity.xml");
								System.out.println("XP3 for Sales team started :-------------------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						DS.start();
						break;

					case "MO-MT-Call":

						Thread CP=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						CP.start();	
						break;

					case "MO-Call":
						Thread MOCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MO-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MOCALL.start();	
						break;

					case "MT-Call":
						Thread MTCALL=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3-XP5-XP8-MT-Call-Performance.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						MTCALL.start();	

						break;

					case "Dev-Sanity":
						Thread NotI=new Thread(new Runnable() {
							public void run() {

								timer1.cancel();
								timer2.cancel();
								timer3_failure.cancel();
								onfinish.cancel();
								onskip.cancel();
								percentageCalculator.cancel();


								Platform.runLater(()->AlertDialog("'DevQa Sanity' is not supported for XP300 devices'"));

								anchorPane.setDisable(false);
								pi.setVisible(false);




							}
						});
						NotI.start();
						break;	





					}

					listView.setDisable(true);
					combobox.setDisable(true);
					ResetBtn.setDisable(true);					
					start.setDisable(true);
					//	swapBtn.setDisable(true);
					stopButton.setDisable(false);
				}


			}
		}catch (Exception e) {
			e.printStackTrace();
			AlertDialog("Exeption while executing test cases.Please restart the tool");

		}



	}



	public void saveReportAndLogInLocalSystem() {

		try {

			File Dir = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
			if(!Dir.exists()) {
				Dir.mkdir();

			}
			File src=new File("src/test/resources/extentreport");

			FileUtils.copyDirectory(src,Dir);
		}catch(Exception e) {

			System.out.println("Problem occurs in SaveReportAndLogsIn Local System");
		}





	}



	//This method is used to swap the device IDs
	@FXML
	public void swapDevice(ActionEvent event) {

		try {

			String DUT_Device = DUTID.getText();
			String Ref_Device = RefID.getText();
			String swapDUTBuildNo=DUTBuildNo.getText();
			String swapRefBuildNo=RefDUTBuildNo.getText();
			String DUT_product = product_DUT.getText();
			String Ref_product = product_Ref.getText();
			String DUT_operator = operator_DUT.getText();
			String Ref_operator = operator_Ref.getText();
			String DUTmdn=DUTMDN.getText();
			String Refmdn=RefMDN.getText();
			String DUTIMEI=IMEI_DUT.getText();
			String REFIMEI=IMEI_REF.getText();

			String temp="";
			String Build="";
			String product ="";
			String operator="";
			String tempMobile="";
			String tempMDN="";
			String tempIMEI="";

			tempMDN=Refmdn;
			RefMDN.setText(DUTmdn);
			DUTMDN.setText(tempMDN);

			temp = Ref_Device;
			RefID.setText(DUT_Device);		  
			DUTID.setText(temp);

			Build = swapRefBuildNo;
			RefDUTBuildNo.setText(swapDUTBuildNo);		  
			DUTBuildNo.setText(Build);

			product = Ref_product;
			product_Ref.setText(DUT_product);		  
			product_DUT.setText(product);

			operator = Ref_operator;
			operator_Ref.setText(DUT_operator);		  
			operator_DUT.setText(operator);

			tempMobile=REFERENCEDEVMDN;
			REFERENCEDEVMDN=PRIMARYDEVMDN;
			PRIMARYDEVMDN=tempMobile;

			tempIMEI=REFIMEI;

			IMEI_REF.setText(DUTIMEI);
			IMEI_DUT.setText(REFIMEI);

			System.out.println(PRIMARYDEVMDN +"---"+REFERENCEDEVMDN);


			JsonFileReaderAndWriter js=new JsonFileReaderAndWriter();
			js.swap();

		}catch (Exception e) {

			e.printStackTrace();
			System.out.println("Execption in Swap device");
		}
	}


	@FXML
	public void stop() {



		try {
			
			loadingScreen();

			Platform.runLater(()->currentTestCases.clear());
			ResetBtn.setDisable(false);
			stopButton.setDisable(true);
			//Platform.runLater(()->AlertDialog("Test execution is stopped"));

			Thread t=new Thread(new Runnable() {

				@Override
				public void run() {

					cleanUp();

				}
			});
			t.start();
			
		} catch (Exception e) {
			throw new RuntimeException("Here skipping all the test cases as user has pressed the stop button");

		}



	}

	@FXML
	public void reset() {

		try {

			CommonConfig.COMMAND_TIMEOUT=100;
			combobox.getSelectionModel().clearSelection();
			ObservableList<String> empty = FXCollections.observableArrayList("");
			listView.setItems(empty);
			listView.refresh();
			//	AllQA.CALL_MODULE="";
			GLOBAL_ONTESTFAILURE="";
			GLOBAL_ONTESTSTART="";
			GLOBAL_ONTESTSUCESS="";
			//	PROGRESSPERCENTAGE_CONSTANT=0;
			//	PROGRESSPERCENTAGE=0;
			TOTAL_NUM_OF_TESTCASES=0;
			INCREMENT_VLAUE=0;
			TESTCASES_INCREMENTER=1;

			progressIndicator.setProgress(0);
			TNGListner.Listner.onFinish="";
			TNGListner.Listner.onTestSkipped="";
			TNGListner.Listner.onTestSucess="";
			TNGListner.Listner.onTestStart="";
			TNGListner.Listner.onTestFailure="";
			Platform.runLater(()->status.setText(" "));
			Platform.runLater(()->currentTestCases.setText(""));
			Platform.runLater(()->ExecutedTC.clear());
			failedTC.clear();
			combobox.setDisable(false);
			listView.setDisable(false);

			start.setDisable(true);




		} catch(Exception e) {

			System.out.println("Exeption in reset Method.....");


		}



	}

	@FXML
	public void savereportAndlog(ActionEvent event) throws FileNotFoundException, IOException, ParseException {


		//	Date date=new Date();
		//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		File Dir = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
		if(!Dir.exists()) {
			Dir.mkdir();

		}

		if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {

			ObservableList<String> topics;

			topics = listView.getSelectionModel().getSelectedItems();
			for (String itemvalue : topics) {

				switch (itemvalue) {

				case "SCOUT":

					File scout = new File("src/test/resources/extentreport/XP5S_Scout_Sanity_TestReport.html");
					File ScoutDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String scoutPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(scout, ScoutDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(scout.exists()) {

						try {
							BaseUtil.openReportPath(scoutPath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;


				case "PhoneContacts":

					File PC = new File("src/test/resources/extentreport/XP5S_Contacts_TestReport.html");
					File PCDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(PC, PCDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(PC.exists()) {

						try {
							BaseUtil.openReportPath(sanitypath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

				case "DEV-QA-Sanity":

					File DS = new File("src/test/resources/extentreport/XP5S_Dev_Sanity_TestReport.html");
					File DSdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String DSsanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(DS, DSdest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(DS.exists()) {

						try {
							BaseUtil.openReportPath(DSsanitypath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

				case "Device":

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

						File sanitytest = new File("src/test/resources/extentreport/XP5S_Device_QA_Sanity_TestReport.html");
						File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String Sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(sanitytest, sanityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(sanitytest.exists()) {

							try {
								BaseUtil.openReportPath(Sanitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}



						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");
						}





					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File sanitytest = new File("src/test/resources/extentreport/XP5S_Device_QA_Sanity_O_TestReport.html");
						File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String Sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(sanitytest, sanityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(sanitytest.exists()) {

							try {
								BaseUtil.openReportPath(Sanitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}



						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");
						}

					}


					break;

				case "MO-MT-Call":

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {


						File CP = new File("src/test/resources/extentreport/MO_MT_CallPerformanceReport.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File CP = new File("src/test/resources/extentreport/MO_MT_CallPerformanceReport.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}


					}


					break;

				case "MO-Call":

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {


						File CP = new File("src/test/resources/extentreport/MO_CallPerformance_Report.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File CP = new File("src/test/resources/extentreport/MO_CallPerformance_Report.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}


					}


					break;


				case "MT-Call":

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {


						File CP = new File("src/test/resources/extentreport/MT_CallPerformance_Report.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File CP = new File("src/test/resources/extentreport/MT_CallPerformance_Report.html");
						File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(CP, CPDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(CP.exists()) {

							try {
								BaseUtil.openReportPath(CPypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}


					}


					break;



				case "Device Stability":	

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {


						File XP5stability = new File("src/test/resources/extentreport/XP5S_DeviceStability_TestReport.html"); 
						File XP5stabilityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String XP5stabilitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(XP5stability, XP5stabilityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(XP5stability.exists()) {

							try {
								BaseUtil.openReportPath(XP5stabilitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File XP5stability = new File("src/test/resources/extentreport/XP5S_Device_Stability_O_TestReport.html");
						File XP5stabilityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String XP5stabilitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(XP5stability, XP5stabilityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(XP5stability.exists()) {

							try {
								BaseUtil.openReportPath(XP5stabilitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}

					}

					break;

				default:

					executionReportDoesnotExist("Please Select a Module from the Application List and click on 'Save Report and Logs' button ");



				}

			}

		}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") ||  JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {

			ObservableList<String> topics;

			topics = listView.getSelectionModel().getSelectedItems();
			for (String itemvalue : topics) {

				switch (itemvalue) {


				case "PhoneDialer":

					File PhoneDialer = new File("src/test/resources/extentreport/XP8_PhoneDialer_Test.html");
					File PhoneDialer_dest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String PhoneDialer_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(PhoneDialer, PhoneDialer_dest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(PhoneDialer.exists()) {

						try {
							BaseUtil.openReportPath(PhoneDialer_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;




				case "DEV-QA-Sanity":

					File Dev_Sanity = new File("src/test/resources/extentreport/XP8_Dev_SanityTest.html");
					File Dev_Dest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String Dev_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Dev_Sanity, Dev_Dest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Dev_Sanity.exists()) {

						try {
							BaseUtil.openReportPath(Dev_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;



				case "ATT Stability":

					File attstability = new File("src/test/resources/extentreport/XP8_ATT_Stability_TestReport.html");
					File attdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String attpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(attstability, attdest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(attstability.exists()) {

						try {
							BaseUtil.openReportPath(attpath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

				case "Device":


					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

						File sanitytest = new File("src/test/resources/extentreport/XP8_Device_QA_Sanity_Test.html");
						File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(sanitytest, sanityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(sanitytest.exists()) {

							try {
								BaseUtil.openReportPath(sanitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}



						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");
						}

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {

						File sanitytest = new File("src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html");
						File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(sanitytest, sanityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(sanitytest.exists()) {

							try {
								BaseUtil.openReportPath(sanitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}



						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");
						}

					}

					break;


				case "MO-MT-Call":

					File CP = new File("src/test/resources/extentreport/MO_MT_CallPerformanceReport.html");
					File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(CP, CPDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(CP.exists()) {

						try {
							BaseUtil.openReportPath(CPypath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;


				case "MO-Call":

					File MOCall = new File("src/test/resources/extentreport/MO_CallPerformance_Report.html");
					File MOCallDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String MOPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(MOCall, MOCallDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(MOCall.exists()) {

						try {
							BaseUtil.openReportPath(MOPath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;

				case "MT-Call":

					File MTCall = new File("src/test/resources/extentreport/MT_CallPerformance_Report.html");
					File MTCallDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String MTPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(MTCall, MTCallDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(MTCall.exists()) {

						try {
							BaseUtil.openReportPath(MTPath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;	


				case "Device Stability":	

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
						File stability = new File("src/test/resources/extentreport/XP8_DeviceStability_TestReport.html");
						File stabilityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String stabilitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(stability, stabilityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(stability.exists()) {

							try {
								BaseUtil.openReportPath(stabilitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}

						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");

						}
					}
					else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {

						File Stabilitytest = new File("src/test/resources/extentreport/XP8_DeviceStability_Orio_TestReport.html");
						File StabilityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
						String Stabilitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
						try {
							FileUtils.copyFileToDirectory(Stabilitytest, StabilityDest);
						} catch (IOException e) {

							e.printStackTrace();
						}
						if(Stabilitytest.exists()) {

							try {
								BaseUtil.openReportPath(Stabilitypath);
							} catch (IOException e) {

								e.printStackTrace();
							}



						}
						else {
							executionReportDoesnotExist("Test Report is not generated yet");
						}

					}
					break;

				default:

					executionReportDoesnotExist("Please Select a Module from the Application List and click on 'Save Report and Logs' button ");


				}


			}


		}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A")) {

			ObservableList<String> topics;

			topics = listView.getSelectionModel().getSelectedItems();
			for (String itemvalue : topics) {

				switch (itemvalue) {


				case "Device":

					File san = new File("src/test/resources/extentreport/XP3_Device_Sanity.html");
					File sanDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String Sanpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(san, sanDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(san.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(Sanpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}



					break;

				case "MO-MT-Call":

					File CP = new File("src/test/resources/extentreport/MO_MT_CallPerformanceReport.html");
					File CPDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String CPypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(CP, CPDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(CP.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(CPypath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;

				case "MO-Call":

					File MO = new File("src/test/resources/extentreport/MO_CallPerformance_Report.html");
					File MODest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String MOpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(MO, MODest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(MO.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(MOpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;		


				case "MT-Call":

					File MT = new File("src/test/resources/extentreport/MT_CallPerformance_Report.html");
					File MTDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String MTpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(MT, MTDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(MT.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(MTpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;		

				default:
					executionReportDoesnotExist("Please Select a Module from the Application List and click on 'Save Report and Logs' button ");




				}
			}

		}			













	}

	/*@FXML	
	public void file_Exit() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setContentText("Do You Want to Exit?");
		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {

			System.exit(0);
		}
	}*/


	public void loadingScreen() {


		try {


			loadingScreen = new ProgressIndicator();

			Platform.runLater(()->loadingScreen.setStyle("-fx-progress-color: #ff0000;"));

			loadingScreen.setMinWidth(100);
			loadingScreen.setMinHeight(100);
			loadingScreen.setScaleX(2);
			loadingScreen.setScaleY(2);
			loadingScreen.setTranslateY(300);
			loadingScreen.setTranslateX(300);

			secondAnchorPane.getChildren().add(loadingScreen);
			anchorPane.setDisable(true);

		} catch (Exception e) {

			e.printStackTrace();
		}





	}





	public void ProgressIndicator() {

		try {
			System.out.println("Executing progress indicaotr");
			pi = new ProgressIndicator();
			/*	pi.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			pi.getStyleClass().add("LoadingScreen");*/
			Platform.runLater(()->pi.setStyle("-fx-progress-color: #3339FF;"));

			pi.setMinWidth(100);
			pi.setMinHeight(100);
			pi.setScaleX(2);
			pi.setScaleY(2);
			pi.setTranslateY(300);
			pi.setTranslateX(300);

			secondAnchorPane.getChildren().add(pi);
			anchorPane.setDisable(true);



			/* currentTestCases.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

					System.out.println(newValue);

					if(currentTestCases.getText().equals(newValue)) {
						anchorPane.setDisable(false);
						pi.setVisible(false);

						}  
				}
			});*/


			currentTcTimer = new Timer(); 
			currentTcTimer.schedule( new TimerTask() 
			{ 
				public void run() { 

					if(!currentTestCases.getText().equals("")) {

						anchorPane.setDisable(false);
						pi.setVisible(false);
						currentTcTimer.cancel();
					}
				} 
			}, 0, 100*(10*1));

		}catch (Exception e) {
			System.out.println("Exeption occurs in start progressdialog");
		}	


	}

	@FXML
	public void aboutScreen(ActionEvent event) throws IOException {

		Parent child = FXMLLoader.load(getClass().getResource("/application/About.fxml"));
		Scene scene = new Scene(child);
		Stage secondaryStage = new Stage();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondaryStage.getIcons().add(new Image("/application/newLogo.png"));
		secondaryStage.resizableProperty().setValue(Boolean.FALSE);
		secondaryStage.setMaximized(false);
		secondaryStage.initStyle(StageStyle.DECORATED);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("S-MAT");
		secondaryStage.show();


	}

	@FXML
	public void Support(ActionEvent event) throws IOException {

		Parent child = FXMLLoader.load(getClass().getResource("/application/Support.fxml"));
		Scene scene = new Scene(child);
		Stage secondaryStage = new Stage();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondaryStage.getIcons().add(new Image("/application/newLogo.png"));
		secondaryStage.resizableProperty().setValue(Boolean.FALSE);
		secondaryStage.setMaximized(false);
		secondaryStage.initStyle(StageStyle.DECORATED);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("S-MAT");
		secondaryStage.show();


	}


	@FXML
	public void PreconditionPopUp(ActionEvent event) throws IOException {

		Desktop desktop = Desktop.getDesktop();
		File dirToOpen = null;
		try {
			dirToOpen = new File("src/test/resources/DOCS/PC.pdf");
			desktop.open(dirToOpen);
		} catch (Exception ex) {
			AlertDialog("Please check the pdf software is installed in your machine");
		}

		/*Parent child = FXMLLoader.load(getClass().getResource("/application/precondition.fxml"));
		Scene scene = new Scene(child);
		Stage secondaryStage = new Stage();
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		secondaryStage.getIcons().add(new Image("/application/newLogo.png"));
		secondaryStage.resizableProperty().setValue(Boolean.FALSE);
		secondaryStage.setMaximized(false);
		secondaryStage.initStyle(StageStyle.DECORATED);
		secondaryStage.setScene(scene);
		secondaryStage.setTitle("S-MAT");
		secondaryStage.show();*/


	}

	@FXML
	public void userGuide(ActionEvent event) {


		Desktop desktop = Desktop.getDesktop();
		File dirToOpen = null;
		try {
			dirToOpen = new File("src/test/resources/DOCS/UG.pdf");
			desktop.open(dirToOpen);
		} catch (Exception ex) {
			AlertDialog("Please check the pdf software is installed in your machine");
		}

	}

	@FXML
	public void TestExecution(ActionEvent event) {


		Desktop desktop = Desktop.getDesktop();
		File dirToOpen = null;
		try {
			dirToOpen = new File("src/test/resources/DOCS/TE.pdf");
			desktop.open(dirToOpen);
		} catch (Exception ex) {
			AlertDialog("Please check the pdf software is installed in your machine");
		}

	}



	public void executionReportDoesnotExist(String value) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.initOwner(MainController.secondaryStage1);
		alert.setContentText(value);
		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {

		}


	}





	public void showMdnDialogbox() {

		// Create the custom dialog.
		dialog = new Dialog<>();
		dialog.setTitle("S-MAT");

		dialog.initStyle(StageStyle.DECORATED);
		//	dialog.getDialogPane().lookupButton(ButtonType.CLOSE).setDisable(true);


		dialog.setHeaderText("Please enter MDN's including country Code [Excluding prefix '+']");

		//MainController.secondaryStage1.setAlwaysOnTop(true);
		dialog.initOwner(MainController.secondaryStage1);



		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
		//dialog.initStyle(StageStyle.UNDECORATED);
		DialogPane dialogPane = dialog.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogPane.getStyleClass().add("MDN_Popup");

		dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(e->{

			e.consume();



		});




		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(12);
		grid.setVgap(12);
		grid.setPadding(new Insets(20, 150, 10, 10));


		final TextField username = new TextField();
		username.setPromptText("Enter DUT MDN");

		TextField password = new TextField();
		password.setPromptText("Enter Reference MDN");

		grid.add(new Label("DUT MDN:"), 0, 0);
		grid.add(username, 1, 0);

		grid.add(new Label("REF DUT MDN:"), 0, 1);
		grid.add(password, 1, 1);

		final TextField email=new TextField();
		email.setPrefWidth(200);
		email.setPromptText("Enter Email-ID's separated by ',' ");
		grid.add(new Label("EMAIL ID:"), 0, 2);
		grid.add(email, 1, 2);


		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).




		username.textProperty().addListener((observable, oldValue, newValue) -> {


			if (!newValue.matches("\\d*") ) {
				username.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if(newValue.length()>12) {


				username.setText(oldValue);

			}

		});

		password.textProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue.matches("\\d*") ) {
				password.setText(newValue.replaceAll("[^\\d]", ""));
			}
			if(newValue.length()>12) {

				password.setText(oldValue);
			}

			loginButton.setDisable(newValue.trim().isEmpty());


		});

		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});


		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(Dut_Ref_Dut_email -> {

			PRIMARYDEVMDN="+"+Dut_Ref_Dut_email.getKey();
			REFERENCEDEVMDN="+"+Dut_Ref_Dut_email.getValue();
			AllQA.EMAILID=email.getText();
			System.out.println(PRIMARYDEVMDN +"-"+REFERENCEDEVMDN +"Email id is :"+AllQA.EMAILID);



			if(result.isPresent() && (PRIMARYDEVMDN.length()==13 && REFERENCEDEVMDN.length()==13)) {

				if(PRIMARYDEVMDN.equals(REFERENCEDEVMDN)){

					AlertDialog("DUT and Ref MDN's should not be same");
					showMdnDialogbox();



				} else {


					Alert alert1 = new Alert(AlertType.CONFIRMATION);
					alert1.setTitle("S-MAT");

					alert1.initStyle(StageStyle.UNDECORATED);
					alert1.initOwner(MainController.secondaryStage1);
					alert1.setContentText("Please reconfirm DUT:" +PRIMARYDEVMDN+",REF:" +REFERENCEDEVMDN+" and Email-ID :entered is correct & Same MDN's will be used for Test-Execution.");
					ButtonType yesButton = new ButtonType("Yes",ButtonData.YES);
					ButtonType noButton = new ButtonType("No",ButtonData.NO);
					alert1.getButtonTypes().setAll(yesButton, noButton);
					Optional<ButtonType> action = alert1.showAndWait();

					if(action.get()==yesButton) {

						System.out.println("These MDN will be used for test execution");
						DUTMDN.setText(PRIMARYDEVMDN);
						RefMDN.setText(REFERENCEDEVMDN);

					}
					else if (action.get()==noButton) {



						showMdnDialogbox();


					}
				}

			}



			else {
				AlertDialog("MDN's should not be less than 12 digits");
				showMdnDialogbox();


			}




		});


	}


	public void AlertDialog(String numofCallMessage){



		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.initOwner(MainController.secondaryStage1);
		alert.setContentText(numofCallMessage);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogPane.getStyleClass().add("AllTestCaseSkippedPopup");






		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {




		}

	}
	public void ExecutionCompletedPopup(String numofCallMessage){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.initOwner(MainController.secondaryStage1);
		alert.setContentText(numofCallMessage);
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogPane.getStyleClass().add("ExecutionCompletedPopup");


		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {

		}
	}


	public static void fingerPrintAlertBox(){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setContentText("");
		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {

			System.exit(0);


		}

	}


	//This method is used to fetch the onskipped value presrnt in the listner	

	public void itemSelectedListner() {


		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


				if(listView.getItems().contains(newValue)) {

					try {

						if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35."))
						{


							if(newValue.contains("SCOUT")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Scout_Sanity_TestReport.html";
							}else if (newValue.contains("PhoneContacts")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Contacts_TestReport.html";
							}else if (newValue.contains("DEV-QA-Sanity")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Dev_Sanity_TestReport.html";
							}else if (newValue.contains("Device")) {

								if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
									AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Device_QA_Sanity_TestReport.html";

								}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {
									AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Device_QA_Sanity_O_TestReport.html";
								}

							}else if (newValue.contains("MO-MT-Call")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";
							}else if (newValue.contains("MO-Call")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";
							}else if (newValue.contains("MT-Call")) {

								AllQA.EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";

							}else if (newValue.contains("Device Stability")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP5S_Stability_TestReport.html";
							}



						}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") ||  JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {

							if(newValue.contains("DEV-QA-Sanity")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_Dev_SanityTest.html";
							}else if (newValue.contains("Telephony")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_ATT_Stability_Telephony_TestReport.html";
							}else if (newValue.contains("Email")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_ATT_Stability_Email_TestReport.html";
							}else if (newValue.contains("Device")) {

								if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
									AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_Device_QA_Sanity_Test.html";

								}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {
									AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html";

								}


							}else if (newValue.contains("MO-MT-Call")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";
							}else if (newValue.contains("MO-Call")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";
							}else if (newValue.contains("MT-Call")) {

								AllQA.EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";


							}else if (newValue.contains("Device Stability")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP8_DeviceStability_TestReport.html";  
							}

						}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A")) {

							if(newValue.contains("Device")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP3_Sanity.html";

							}else if (newValue.contains("MO-MT-Call")) {
								System.out.println("000000000000MO-MT");
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";


							}else if (newValue.contains("MT-Call")) {
								System.out.println("000000000000MT");

								AllQA.EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";
							}else if (newValue.contains("MO-Call")) {
								System.out.println("000000000000");
								AllQA.EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";



							}else if (newValue.contains("Device Stability")) {
								AllQA.EMAILREPORT="src/test/resources/extentreport/XP3_Device_Stability_TestReport.html";	


							}
						}




						if(listView.getItems().contains("MO-MT-Call") || listView.getItems().contains("MO-Call") || listView.getItems().contains("MT-Call") ) {

							try {

								System.out.println("numofCall");
								inputDialogBoxfornumOfCalls();

								listView.refresh();


							}catch (java.util.NoSuchElementException e) {

							}catch(java.lang.NumberFormatException e){

								AlertDialog("Please enter numbers only");
								inputDialogBoxfornumOfCalls();

							}
						}

						start.setDisable(false);
						System.out.println(AllQA.EMAILREPORT);
					} catch (IOException | ParseException e) {

						e.printStackTrace();
					}



				}


			}


		});	

	}

	//THis item listner will get the selected item from listview and this method basically update the value to the commonConfig file.	
	public void itemListner2() {


		try {


			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

					if(newValue.equals("MT-Call")) {

						System.out.println("Sales Team -> HEY WHATS=");
						CommonConfig.CALL_MODULE="MT-Call";
					}else {
						CommonConfig.CALL_MODULE="";
					}

				}
			});


		} catch (Exception e) {

			e.printStackTrace();
		}

	}





	//THis method is used to fetch exection result like pass/fail from listner class and display in GUI

	public void fetchExecutionStatus() {

		System.out.println("Started All timers");

		try {



			timer1 = new Timer(); 
			timer1.schedule( new TimerTask() 
			{ 
				public void run() { 


					if(TNGListner.Listner.onTestStart.equals("")) {

					}
					else if (TNGListner.Listner.onTestStart.equalsIgnoreCase(GLOBAL_ONTESTSTART)) {

					}
					else 
					{

						Platform.runLater(()->currentTestCases.setText(TNGListner.Listner.onTestStart));
						Platform.runLater(()->currentTestCases.setStyle("-fx-text-inner-color: green;"));
						GLOBAL_ONTESTSTART=TNGListner.Listner.onTestStart;
					}
				} 
			}, 0, 10*(10*1));



			timer2 = new Timer(); 
			timer2.schedule( new TimerTask() 
			{ 
				public void run() { 

					if(TNGListner.Listner.onTestSucess.equals("")) {



					}
					else if (TNGListner.Listner.onTestSucess.equalsIgnoreCase(GLOBAL_ONTESTSUCESS)) {


					}
					else 
					{
						System.out.println("Tiemr 2---->Sucess of Test cases");
						ExecutedTC.appendText(TNGListner.Listner.onTestSucess);
						ExecutedTC.setStyle("-fx-text-inner-color: blue;");
						GLOBAL_ONTESTSUCESS=TNGListner.Listner.onTestSucess;
					}
				} 
			}, 0, 10*(10*1));



			timer3_failure = new Timer(); 
			timer3_failure.schedule( new TimerTask() 
			{ 
				public void run() { 

					if(TNGListner.Listner.onTestFailure.equals("")) {

					}
					else if (TNGListner.Listner.onTestFailure.equalsIgnoreCase(GLOBAL_ONTESTFAILURE)) {

					}
					else 
					{

						System.out.println("Timer_failure got failed ");

						failedTC.appendText(TNGListner.Listner.onTestFailure);
						GLOBAL_ONTESTFAILURE=TNGListner.Listner.onTestFailure;


					}
				} 
			}, 0, 100*(100*1));


			onfinish=new Timer();
			onfinish.schedule(new TimerTask() {
				public void run() {

					if(TNGListner.Listner.onFinish.equals("TCFINISHED")) {



						onfinish.cancel();
						timer1.cancel();
						timer2.cancel();
						timer3_failure.cancel();
						stopButton.setDisable(true);
						ResetBtn.setDisable(false);
						Platform.runLater(()->status.setText("Test Execution completed successfully"));
						Platform.runLater(()->currentTestCases.setText("Test Execution ---> Completed "));



					}

				}
			},  0, 10*(100*1)); 

			onskip=new Timer();
			onskip.schedule(new TimerTask() {
				public void run() {
					if(TNGListner.Listner.onTestSkipped.equals("TCSKIPPED")) {	
						Platform.runLater(() ->status.setText("All test cases are skipped."));
						onskip.cancel();
						onfinish.cancel();
						timer1.cancel();
						timer2.cancel();
						timer3_failure.cancel();
						stopButton.setDisable(true);
						ResetBtn.setDisable(false);
					}

				}
			},  0, 10*(100*1)); 




			percentageCalculator = new Timer(); 
			percentageCalculator.schedule( new TimerTask() 
			{ 
				public void run() { 



					if(appiumService.INCREMENT_VLAUE!=0.0) {

						progressIndicator.setProgress(INCREMENT_VLAUE);

					}

				} 
			}, 0, 10*(10*1));








		}catch (Exception e) {

			System.out.println("Some error occurs in Timers ");
		}

	}


	//This method is used for entering numbers of calls	
	public void inputDialogBoxfornumOfCalls() {


		dialog = new Dialog<>();
		dialog.setTitle("S-MAT");
		dialog.setHeaderText("Please Enter the Call details below :");
		dialog.initOwner(MainController.secondaryStage1);


		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
		//dialog.initStyle(StageStyle.UNDECORATED);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(12);
		grid.setVgap(12);
		grid.setPadding(new Insets(20, 150, 10, 10));

		final TextField username = new TextField();
		username.setPromptText("Enter number of Calls");

		final TextField CallDuration = new TextField();
		CallDuration.setPromptText("Enter In-Call Duration:");


		final TextField callGaps = new TextField();
		callGaps.setPromptText("Enter sucessesive Gaps:");


		grid.add(new Label("Please Enter number of calls(Call Range 1-1000):"), 0, 0);
		grid.add(username, 1, 0);

		grid.add(new Label("Call Duration(In Minutes 1-60):"), 0, 1);
		grid.add(CallDuration, 1, 1);

		grid.add(new Label("Successive Call Gap(In Seconds 1-120) :"), 0, 2);
		grid.add(callGaps, 1, 2);



		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});


		dialog.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		//	Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(),"");
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(usernamePassword -> {
			NUMOFCALLS=Integer.parseInt(usernamePassword.getKey());
			CALLDURATION=Integer.parseInt(CallDuration.getText());
			CALLGAP=Integer.parseInt(callGaps.getText());



			System.out.println("The number of call"+NUMOFCALLS);
			System.out.println("Call Duration is "+CALLDURATION);
			System.out.println("Call Gap is "+CALLGAP);

			if(result.isPresent() && NUMOFCALLS<=1000) {

				//Here updating the values of Appium command timeout based on only call performance
				CommonConfig.COMMAND_TIMEOUT=3600;



				Alert alert = new Alert(AlertType.CONFIRMATION);
				alert.setTitle("S-MAT");
				alert.initStyle(StageStyle.UNDECORATED);
				alert.setContentText("The number of 'Call' and 'Call Duration' are : "+NUMOFCALLS +"  "+CALLDURATION);
				alert.initOwner(MainController.secondaryStage1);
				ButtonType yesButton = new ButtonType("Yes",ButtonData.YES);
				ButtonType noButton = new ButtonType("No",ButtonData.NO);
				alert.getButtonTypes().setAll(yesButton, noButton);
				Optional<ButtonType> action = alert.showAndWait();
				if(action.get()==yesButton && NUMOFCALLS<=1000) {


				}
				else if (action.get()==noButton) {
					inputDialogBoxfornumOfCalls();

				}

			}

			else {

				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("S-MAT");
				alert1.initStyle(StageStyle.UNDECORATED);
				alert1.setContentText("Number of call should be less than 1000!!!");
				Optional<ButtonType> action1 = alert1.showAndWait();
				if(action1.get()==ButtonType.OK) {
					inputDialogBoxfornumOfCalls();


				}



			}


		});

	}




	//THis Method is used to execute only failed Test cases	
	@FXML
	public void ExecuteFailedTestCasesOnly() {

		try {
			executeFailedTestCases.setDisable(true);
			ProgressIndicator(); 
			progressAction();
			fetchExecutionStatus();

			Thread ExecuteFailedTC=new Thread(new Runnable() {

				public void run() {

					TestNG runner=new TestNG();
					List<String> suitefiles=new ArrayList<String>();
					suitefiles.add("test-output/Suite/testng-failed.xml");
					System.out.println("XP5 :");
					runner.setTestSuites(suitefiles);
					runner.run();	
				}
			});

			ExecuteFailedTC.start();

		} catch (Exception e) {

			e.printStackTrace();
		}


	}	







	public void progressAction() {

		try {

			System.out.println("<---------------ProgressAction------->");

			failedTC.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


					if(failedTC.getText().contains("")) {

						System.out.println("Getting some values from ");
						//	executeFailedTestCases.setDisable(false);
						//	executeFailedTestCases.setStyle("-fx-background-color: #FF0000"); 
						failedTC.textProperty().removeListener(this);


					}  	
				}
			});


			status.textProperty().addListener(new ChangeListener<String>() {
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

					if(status.getText().equals("All test cases are skipped.")) {

						anchorPane.setDisable(false);
						loadingScreen.setVisible(false);


					}  	
				}
			});


			/*	progressIndicator.progressProperty().addListener((ov, oldValue, newValue) -> {
     	     Text text = (Text) progressIndicator.lookup(".percentage");
     	     if(text!=null && text.getText().equals("Done")){
     	        text.setText("Finished");
     	        text.autosize();
     	        progressIndicator.setPrefWidth(text.getLayoutBounds().getWidth());
     	     }
     	});*/


		}catch(Exception e) {
			System.out.println("Problem ocurs in progressbar listnber");
		}

	}





}
