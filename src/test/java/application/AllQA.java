package application;


import java.awt.Desktop;
//
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.Optional;

import java.util.Timer;
import java.util.TimerTask;


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
import com.aosp.Utils.XP5S_Data_Utils;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.CommonConfig;
import com.xp5S.util.appiumService;

import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.Pair;


public class AllQA  extends CommonConfig {

	//ObservableList<String> items=FXCollections.observableArrayList("Sanity Test","MultiMedia","Messaging","Connectivity","GMS","Browser","Settings","Tools","Contacts","Call","ScoutApps","Performance","DeviceFunctionality");
	ObservableList<String> items=FXCollections.observableArrayList("CSFB + VoLTE","RebootScenario","New Sanity",/*"PTT"*//*"Sanity Test",*/"DataAndConnectivity","VOLTE-CallPerformance","3G-CallPerformance","Stability","Interaction","Messaging","Call","Interruption"/*,"SCOUT","Call","Stability_AT&T-15595"*/);

	//ObservableList<String> items=FXCollections.observableArrayList("Quick Sanity","Performance");
	//	ObservableList<String> Sanity = FXCollections.observableArrayList("Sanity");

	//ObservableList<String> Sanity = FXCollections.observableArrayList("Device","DEV-QA-Sanity");
	ObservableList<String> New_Sanity = FXCollections.observableArrayList("Device Sanity");

	ObservableList<String> Sanity = FXCollections.observableArrayList("Device");	
	ObservableList<String> Messaging= FXCollections.observableArrayList("Messaging");
	ObservableList<String> reboot= FXCollections.observableArrayList("RebootScenario");



	ObservableList<String> connectivity = FXCollections.observableArrayList("Bluetooth","Wi-Fi","Mobile Data");
	ObservableList<String> multiMedia = FXCollections.observableArrayList("Photos","Music","FM","All");
	//ObservableList<String> Messaging = FXCollections.observableArrayList("SMS","Email","All");
	ObservableList<String> GMS = FXCollections.observableArrayList("YouTube","Chrome","Google Apps","All");
	ObservableList<String> browser = FXCollections.observableArrayList("Native Browser");
	ObservableList<String> Settings = FXCollections.observableArrayList("Settings","Programmable Key","Power ON/OFF","All");
	ObservableList<String> Tools = FXCollections.observableArrayList("Calculator","Calendar","Clock","SoundRecorder","Downloads","FileManager","All");
	ObservableList<String> Contacts = FXCollections.observableArrayList("PhoneContacts");
	ObservableList<String> call = FXCollections.observableArrayList("PhoneDialer","CallHistory","CallSettings","Phone Contacts");
	//ObservableList<String> ScoutApps = FXCollections.observableArrayList("AppUpdater","SafeGuard","SonimCare","SonimWarranty","ContactTransfer","All");
	ObservableList<String> ScoutApps = FXCollections.observableArrayList("SafeGuard","SonimCare","ContactTransfer","WarrantyRegistration");

	//ObservableList<String> performance = FXCollections.observableArrayList("CallPerformance","Stability","Browser","All");
	ObservableList<String> CallPerformance = FXCollections.observableArrayList("MO-Call","MT-Call","MO-MT-Call");
	ObservableList<String> stability = FXCollections.observableArrayList("Contacts Stability",/*"Multitasking",*/"Wifi","Network Stability","Browser Stability","Device Stability","SMS"/*,*/);
	ObservableList<String> ATTStablity = FXCollections.observableArrayList("Telephony","Email");
	ObservableList<String> ptt = FXCollections.observableArrayList("CSFB + VoLTE");
	ObservableList<String> IOT = FXCollections.observableArrayList("Interaction Cases");
	ObservableList<String> interruption = FXCollections.observableArrayList("Interruption Cases");

	ObservableList<String> dataconnectivity = FXCollections.observableArrayList("DataUsageSettings","Wi-fi","DataSettings");




	ObservableList<String> devicefunctionality = FXCollections.observableArrayList("DeviceFunctionality-All","DeviceFunctionality_WithOut-SCOUT","DeviceFunctionality_WithOut-SCOUT-Performance");



	public static AndroidDebugBridge debugBridge;
	public int i;
	public static String GLOBAL_ONTESTSTART;
	public static String GLOBAL_ONTESTSUCESS;
	public static String GLOBAL_ONTESTFAILURE;
	public String GLOBAL_ONTESTSKIPPED;
	public static String PRIMARYDEVMDN;
	public static String REFERENCEDEVMDN;
	public static String EMAILID;
	public static String SSID;
	public static String WIFIPASSWORD;
	public static int NUMOFCALLS;
	public static int NUMOFSTEPS;

	
	//Here call duration defualt value is 1 minute
	public static int CALLDURATION=1;
	//Here call gap defualt value is 1 minute
	public static int CALLGAP=5;


	public static String comboBoxItems;
	public Timer onfinish;
	public Timer timer2;
	public static Timer timer3_failure;
	public Timer onskip;
	public static Dialog<Pair<String, String>> dialog_new;
	public ProgressIndicator pi;
	public ProgressIndicator loadingScreen;
	public Timer percentageCalculator;

	public static String EMAILREPORT;

	//	public Button executeFailedTestCases;
	public static String NUM_OF_CALL_ITER="";
	public static String NUM_OF_CALL_ITER_UPDATE="";
	public static String NUM_OF_CALL_ITER_INCREMENTOR="";
	public static double CALL_COUNT;


  

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
	public Timer call_Iter;


	@FXML
	TextArea testCaseDisplay;




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


				public void deviceConnected(IDevice device) {

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



					}else if (i==1) {

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



						} catch (TimeoutException | AdbCommandRejectedException | ShellCommandUnresponsiveException	| IOException | ParseException e) {

							//	Platform.runLater(()->fingerPrintAlertBox("Please enable the USB debugging/Ack the RSA key"));  
						}

					}
					i++;

				}


				public void deviceDisconnected(IDevice device) {

					deviceDetect.setStyle("-fx-background-color: #ff0000"); 
					/*Thread t=new Thread(new Runnable() {
						public void run() {

							Platform.runLater(()->AlertDialog("Device disconnected .Please restart the tool and try again..."));

						}
					});
					t.start();*/

				}
			

			});
			
	//Here i m terminating the adb connection once device is detected		
		debugBridge.terminate();

		}catch (java.lang.IllegalStateException e)	{

			e.printStackTrace();

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

					operator_DUT.setText("ROGERS"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

					operator_DUT.setText("BELL"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

					operator_DUT.setText("TELUS"); 

				}
				else if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {


					operator_DUT.setText("SPRINT"); 
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

					operator_DUT.setText("VERIZON"); 

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00")) {

					operator_DUT.setText("MAINLINE"); 
				}



			}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("8A")) {

				product_DUT.setText("XP8800");

				if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {


					operator_DUT.setText("ATT"); 

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18.")) {

					operator_DUT.setText("ROGERS");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {

					operator_DUT.setText("TELUS");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

					operator_DUT.setText("BELL");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.")) {

					operator_DUT.setText("SL");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) {

					operator_DUT.setText("SPRINT");

				}
				else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-30.")) {

					operator_DUT.setText("JVCK");

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-15.")) {

					operator_DUT.setText("VERIZON");

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-00.")) {

					operator_DUT.setText("MAINLINE");

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-31.")) {

					operator_DUT.setText("EU");
				}


			}
			else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A.")) {

				product_DUT.setText("XP3800");

				if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) {


					operator_DUT.setText("ATT"); 

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-34")) {

					operator_DUT.setText("USC"); 

				}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
					operator_DUT.setText("SPRINT"); 

				}else {

					operator_DUT.setText("GEN"); 
				}


			}
			else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-30.") ) {


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


					operator_Ref.setText("TELUS");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					operator_Ref.setText("SPRINT");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-26.")) {

					operator_Ref.setText("SL");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-18.")) {


					operator_Ref.setText("ROGERS");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-11.")) {


					operator_Ref.setText("BELL");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-32.")) {


					operator_Ref.setText("ACG");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-34.")) {


					operator_Ref.setText("USC");  
				}else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-15.")) {


					operator_Ref.setText("VERIZON");  
				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-00")) {

					operator_Ref.setText("MAINLINE"); 

				}

			}
			else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("8A")){
				product_Ref.setText("XP8800");

				if(JsonFileReaderAndWriter.RefDevOperatorName().contains("ATT")) {


					operator_Ref.setText("ATT");  
				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-18.")) {

					operator_Ref.setText("ROGERS"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-12.")) {

					operator_Ref.setText("TELUS"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-11.")) {

					operator_Ref.setText("BELL"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-26.")) {

					operator_Ref.setText("SL"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					operator_Ref.setText("SPRINT"); 


				}
				else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

					operator_Ref.setText("JVCK"); 


				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-15.")) {

					operator_Ref.setText("VERIZON"); 


				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-00.")) {

					operator_Ref.setText("MAINLINE"); 


				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-31.")) {

					operator_Ref.setText("EU");
				}

			}
			else if (JsonFileReaderAndWriter.RefDeviceModelNum().contains("xp3800") || JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("3A.")) {

				product_Ref.setText("XP3800");


				if(JsonFileReaderAndWriter.RefDevOperatorName().contains("ATT")) {


					operator_Ref.setText("ATT");  
				}else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-34")) {
					operator_Ref.setText("USC"); 
				}else {
					operator_Ref.setText("GEN"); 
				}


			}
			else if (JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

				product_Ref.setText("XP8800");

				if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-30.")) {

					/*operatorName=JsonFileReaderAndWriter.RefDevOperatorName();
				String[] split=operatorName.split("-");*/
					operator_Ref.setText("JVCK");  
				}
				else if(JsonFileReaderAndWriter.ReadRefDeviceFirmWare().contains("-29.")) {

					operator_Ref.setText("SPRINT"); 
				}else {
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
			alert.setContentText("1.Please check the both device connection and online \n"
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

	// End Of Detect Device method.


	@FXML
	public void initialize() {

		System.out.println("Initialise ");

		try {

			combobox.setItems(items);
			progressIndicator.getStylesheets().add(getClass().getResource("").toExternalForm());
			progressIndicator.getStyleClass().add("ProgressIndicator");

			listView.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			listView.getStyleClass().add("ListViewItems");

			//	listView.setPlaceholder(new Label("No Content In List"));

			start.setDisable(true);
			combobox.setDisable(true);
			stopButton.setDisable(true);
			Platform.runLater(() ->loginAsTeam.setText(MainController.TEAM_BASED_LOGIN));

			comboBoxItems=(String) combobox.getSelectionModel().getSelectedItem();

			if(comboBoxItems=="Sanity")
			{
				listView.setItems(Sanity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}else if (comboBoxItems=="New Sanity") {
			
				listView.setItems(New_Sanity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			//Added as per Raj comments 03-12-2018
			}else if (comboBoxItems=="Stability") {

				listView.setItems(stability);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}

			else if (comboBoxItems=="Stability_Non-AT&T") {
				listView.setItems(stability);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}else if (comboBoxItems=="Stability_AT&T-15595") {

				listView.setItems(ATTStablity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}else if (comboBoxItems=="3G-CallPerformance" || comboBoxItems=="VOLTE-CallPerformance") {

				listView.setItems(CallPerformance);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}
			else if (comboBoxItems=="MultiMedia") {

				listView.setItems(multiMedia);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="Messaging") {
				listView.setItems(Messaging);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}
			else if (comboBoxItems=="Connectivity") {

				listView.setItems(connectivity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="GMS") {

				listView.setItems(GMS);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="Browser") {

				listView.setItems(browser);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}
			else if (comboBoxItems=="Settings") {
				listView.setItems(Settings);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="Tools") {

				listView.setItems(Tools);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="Contacts") {

				listView.setItems(Contacts);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="Call") {

				listView.setItems(call);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="SCOUT") {

				listView.setItems(ScoutApps);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}
			else if (comboBoxItems=="DeviceFunctionality") {

				listView.setItems(devicefunctionality);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			}else if (comboBoxItems=="CSFB + VoLTE") { 

				listView.setItems(ptt);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

			}else if (comboBoxItems=="Interaction") {
				
				listView.setItems(IOT);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
				
			}else if (comboBoxItems=="DataAndConnectivity") {
				
				listView.setItems(dataconnectivity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}else if (comboBoxItems=="Wi-fi") {
				
				listView.setItems(dataconnectivity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}else if (comboBoxItems=="DataSettings") {
				
				listView.setItems(dataconnectivity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}else if (comboBoxItems=="DataUsageSettings") {
				
				listView.setItems(dataconnectivity);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}else if (comboBoxItems=="Interruption") {
				
				listView.setItems(interruption);
				listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
				
			}else if (comboBoxItems=="RebootScenario") {
				
				listView.setItems(reboot);
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


			//Here at every run reseting the global variable values to its defualt value
			//resetAllValues(); 
			ProgressIndicator(); 
			progressAction();
			fetchExecutionStatus();
			saveReportAndLogInLocalSystem();


			if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {

				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();

				for (String itemvalue : topics) {



					switch (itemvalue) {



					case "SonimCare":



						Thread SC=new Thread(new Runnable() {
							
							public void run() {
								
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_SonimCare_O.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SC.start();
						break;


					case "SafeGuard":

						Thread SG=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_SafeGuard_O.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	
							}
						});
						SG.start();
						break;

					case "WarrantyRegistration":

						Thread WR=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP5_WarrantyRegistration_O.xml");
								System.out.println("XP5 :");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						WR.start();
						break;


					case "ApkDownload":

						Thread APkD=new Thread(new Runnable() {
							public void run() {

								anchorPane.setDisable(false);
								pi.setVisible(false);
								currentTcTimer.cancel();
								Platform.runLater(()->AlertDialog("APK Downloader is not applicable for XP5 "));
								ResetBtn.setDisable(false);


							}
						});
						APkD.start();
						break;


					case "SMS":

						Thread sms=new Thread(new Runnable() {
							public void run() {

								anchorPane.setDisable(false);
								pi.setVisible(false);
								currentTcTimer.cancel();
								Platform.runLater(()->AlertDialog("SMS is not applicable for XP5 "));
								ResetBtn.setDisable(false);




							}
						});
						sms.start();
						break;

						/*case "DEV-QA-Sanity":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread stability=new Thread(new Runnable() {


								public void run() {

									System.out.println("XP5 Stability");
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5_Dev_Sanity.xml");
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
									suitefiles.add("src/test/resources/drivers/XP5_Dev_Sanity_O.xml");

									runner.setTestSuites(suitefiles);
									runner.run();
								}
							});
							stability.start();	

						}

						break;

						 */

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
									suitefiles.add("src/test/resources/drivers/XP8_New_Sanity.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	


								}
							});
							DeviceSanity.start();


						}

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
								suitefiles.add("src/test/resources/drivers/XP5_ContactTransfer_O.xml");
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

					case "Browser Stability":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
							/*
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
							stability.start();	*/

						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {

							Thread stability=new Thread(new Runnable() {


								public void run() {

									System.out.println("XP5 Stability");
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP5_Browser_Stability_O.xml");

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

					/*
					 * Here listView ,combox,resetbutton will be disabled once user select a TC and execute it.
					 */                 
					listView.setDisable(true);
					combobox.setDisable(true);
					ResetBtn.setDisable(true);
					start.setDisable(true);

					stopButton.setDisable(false);

				}





			}
			else if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("80k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("8A")) {




				System.out.println("Executing on XP8 device"); 
				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();
				//start.setDisable(true);
				for (String itemvalue : topics) {


					switch (itemvalue) {
					
					
					case "RebootScenario":
						Thread RS=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Reboot.xml");
								System.out.println("XP8 messaging ..............");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						RS.start();
						break;
						
					
					
					
					case "Messaging":
						Thread msg=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Messaging.xml");
								System.out.println("XP8 messaging ..............");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						msg.start();
						break;
						
					case "DataSettings":
						Thread data=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_DataSettings.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						data.start();
						break;

					
					
					
					
					case "DataUsageSettings":
						Thread usage=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_DataUsageSettings.xml");
								System.out.println("XP8 DataUsagesettings..........");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						usage.start();
						break;

						
					case "Interruption Cases":

						Thread interruption=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Interruption.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						interruption.start();
						break;

					case "Phone Contacts":

						Thread PC=new Thread(new Runnable() {
							public void run() {
								System.out.println("XP8 phone contacts --------------------------");

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_PC.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						PC.start();
						break;

					
					
					case "Interaction Cases":

						Thread inter=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Interaction.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						inter.start();
						break;



					case "Contacts Stability":

						Thread C_S=new Thread(new Runnable() {
							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Contact_Stability.xml");
								
								System.out.println("XP8 Contact stability");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						C_S.start();
						break;


					case "CallSettings":

						Thread CS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_CallSettings_O.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						CS.start();
						break;

					case "CSFB + VoLTE":

						Thread ptt=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_KodiakPTT.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						ptt.start();
						break;



					case "CallHistory":

						Thread CallHis=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_CallHistory_O.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						CallHis.start();
						break;
						
						
						
					case "Wi-fi":

						Thread wifi=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Wi-Fi.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						wifi.start();
						break;


					case "Wifi":

						Thread wf=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_WiFi_Stability_O.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						wf.start();
						break;


					case "SMS":

						Thread SMS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_SMS_Stability_O.xml");
								System.out.println("XP8 SMS stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SMS.start();
						break;

					case "Network Stability":

						Thread NetworkStability=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Connectivity_Stability_O.xml");
								System.out.println("XP8 Network Connectivity stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();

							}
						});
						NetworkStability.start();
						break;

					case "ContactTransfer":

						Thread SCOUT=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Contact_Transfer_O.xml");

								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SCOUT.start();
						break;


					case "ApkDownload":

						Thread APkD=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_ApkDownload_O.xml");

								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						APkD.start();
						break;

					case "Contacts":

						Thread Contacts=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Contacts_O.xml");

								runner.setTestSuites(suitefiles);
								runner.run();	
							}
						});
						Contacts.start();
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

						/*	case "DEV-QA-Sanity":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Dev_Sanity.xml");
									System.out.println("Android N XP8");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							DeviceSanity.start();

						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {

									System.out.println("-------------->kkkkkkkkkkkkkkkkkkkkkkkkkk");
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Dev_Sanity_O.xml");
									System.out.println("Android O XP8");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							DeviceSanity.start();

						}

						break;
						 */
					case "Telephony":
						Thread attStability=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Telephony.xml");
								System.out.println("Starting ATT stability tc");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						attStability.start();
						break;



						/*
					case "Email":
						Thread email=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_Email.xml");
								System.out.println("Starting ATT stability tc");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						email.start();
						break;		*/
						
					case "Device Sanity":
						Thread DS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_New_Sanity.xml");
								System.out.println("New Device Sanity Started");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						DS.start();
						break;

					case "Device":


						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

							Thread DeviceSanity=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Sanity.xml");
									System.out.println("Android N XP8");
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
									suitefiles.add("src/test/resources/drivers/XP8_New_Sanity.xml");
									System.out.println("Android O XP8");
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
					case "Browser Stability":

						if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
							/*Thread stability=new Thread(new Runnable() {


								public void run() {


									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Stability.xml");

									runner.setTestSuites(suitefiles);
									runner.run();
								}
							});
							stability.start();	*/
						}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


							Thread stability=new Thread(new Runnable() {
								public void run() {
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Browser_Stability_O.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run();	

								}
							});
							stability.start();

						}


						break;


					case "SafeGuard":
						Thread SG=new Thread(new Runnable() {


							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP8_SafeGuard.xml");

								runner.setTestSuites(suitefiles);
								runner.run();
							}
						});
						SG.start();	
						break;		



					}

					/*
					 * Here listView ,combox,resetbutton will be disabled once user select a TC and execute it.
					 */
					listView.setDisable(true);
					combobox.setDisable(true);
					ResetBtn.setDisable(true);					
					start.setDisable(true);
					//	swapBtn.setDisable(true);
					stopButton.setDisable(false);



				}

			}else if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A")) {

				ObservableList<String> topics;

				topics = listView.getSelectionModel().getSelectedItems();

				for (String itemvalue : topics) {



					switch (itemvalue) {


					case "Multitasking":

						Thread Ml=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_MultiTasking_Stability.xml");
								System.out.println("XP3 Network Stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						Ml.start();
						break;


					case "Wifi":

						Thread wf=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_Wifi_Stability.xml");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						wf.start();
						break;


					case "SafeGuard":

						Thread SG=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_SafeGuard_O.xml");
								System.out.println("XP3 Network Stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SG.start();
						break;



					case "SonimCare":

						Thread SC=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_SonimCare_O.xml");
								System.out.println("XP3 Network Stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SC.start();
						break;



					case "Network Stability":

						Thread NS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_Network_Stability_O.xml");
								System.out.println("XP3 Network Stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						NS.start();
						break;


					case "WarrantyRegistration":

						Thread WR=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_WarrantyRegistration_O.xml");
								System.out.println("XP3 Warranty Reg Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						WR.start();
						break;


					case "ContactTransfer":

						Thread CT=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_ContactTransfer_O.xml");
								System.out.println("XP3 SMS stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						CT.start();
						break;




					case "ApkDownload":

						Thread APkD=new Thread(new Runnable() {
							public void run() {

								anchorPane.setDisable(false);
								pi.setVisible(false);
								currentTcTimer.cancel();
								Platform.runLater(()->AlertDialog("APK Downloader is not applicable for XP3 "));
								ResetBtn.setDisable(false);


							}
						});
						APkD.start();
						break;

					case "SMS":

						Thread SMS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_SMS_Stability.xml");
								System.out.println("XP3 SMS stability Test Started ----------------");
								runner.setTestSuites(suitefiles);
								runner.run();	


							}
						});
						SMS.start();
						break;

					case "Device":

						Thread DS=new Thread(new Runnable() {
							public void run() {
								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_Sanity.xml");
								System.out.println("XP3 device sanity Test Started ----------------");
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

						/*case "DEV-QA-Sanity":

						Thread NotImplemented=new Thread(new Runnable() {
							public void run() {


								timer1.cancel();
								timer2.cancel();
								timer3_failure.cancel();
								onfinish.cancel();
								onskip.cancel();
								percentageCalculator.cancel();
								//	percentageCalculator_Failed.cancel();
								stopButton.setDisable(true);
								ResetBtn.setDisable(false);

								Platform.runLater(()->AlertDialog("'DevQA Sanity is not supported for XP300 devices'"));

								anchorPane.setDisable(false);
								pi.setVisible(false);






							}
						});
						NotImplemented.start();
						break;*/

					case "Device Stability":
						Thread stability=new Thread(new Runnable() {


							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3 Device_Stability.xml");

								runner.setTestSuites(suitefiles);
								runner.run();

							}
						});
						stability.start();	

						break;

					case "Browser Stability":
						Thread Bstability=new Thread(new Runnable() {


							public void run() {

								TestNG runner=new TestNG();
								List<String> suitefiles=new ArrayList<String>();
								suitefiles.add("src/test/resources/drivers/XP3_Browser_Stability.xml");

								runner.setTestSuites(suitefiles);
								runner.run();

							}
						});
						Bstability.start();		
						break;

					case "ATT Stability":
						Thread attStability=new Thread(new Runnable() {
							public void run() {


								timer1.cancel();
								timer2.cancel();
								timer3_failure.cancel();
								onfinish.cancel();
								onskip.cancel();
								percentageCalculator.cancel();
								//percentageCalculator_Failed.cancel();
								stopButton.setDisable(true);
								ResetBtn.setDisable(false);
								Platform.runLater(()->AlertDialog("'ATT Stability' is not supported for XP300 devices'"));

								anchorPane.setDisable(false);
								pi.setVisible(false);


							}
						});
						attStability.start();
						break;

					case "Telephony":

						Thread telephony=new Thread(new Runnable() {
							public void run() {
								timer1.cancel();
								timer2.cancel();
								timer3_failure.cancel();
								onfinish.cancel();
								onskip.cancel();
								percentageCalculator.cancel();
								//	percentageCalculator_Failed.cancel();
								stopButton.setDisable(true);
								ResetBtn.setDisable(false);

								Platform.runLater(()->AlertDialog("'TelePhony is not supported for XP300 devices'"));

								anchorPane.setDisable(false);
								pi.setVisible(false);

							}
						});
						telephony.start();
						break;

					case "Email":

						Thread email=new Thread(new Runnable() {
							public void run() {


								timer1.cancel();
								timer2.cancel();
								timer3_failure.cancel();
								onfinish.cancel();
								onskip.cancel();
								percentageCalculator.cancel();
								//percentageCalculator_Failed.cancel();
								stopButton.setDisable(true);
								ResetBtn.setDisable(false);

								Platform.runLater(()->AlertDialog("'Email is not supported for XP300 devices'"));

								anchorPane.setDisable(false);
								pi.setVisible(false);

							}
						});
						email.start();
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




			File Dir = new File(System.getProperty("user.home") +File.separator +"Desktop"+File.separator+"SMAT-REPORTS-LOGS");

			System.out.println("++++++++++++++++"+Dir);
			if(!Dir.exists()) {
				Dir.mkdir();

			}


			Timer storereport= new Timer(); 
			storereport.schedule( new TimerTask() 
			{ 
				public void run() { 


					System.out.println("Fetching report at every 3 mins");
					File fetchhtmlreport=new File("src/test/resources/extentreport");
					File fetchadblogs=new File("src/test/resources/adbLogs");



					try {
						FileUtils.copyDirectory(fetchhtmlreport,Dir);
						FileUtils.copyDirectory(fetchadblogs,Dir);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} 
			}, 0, 18000*(10*1));


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
	public void stop(ActionEvent event) throws InterruptedException, IOException, ParseException {



		try {

			//Here once user press the stop button it will cancel the onfinish timer so that it will not display anythink on status text area
			onfinish.cancel();
			loadingScreen();

			Platform.runLater(()->currentTestCases.clear());
			ResetBtn.setDisable(false);
			stopButton.setDisable(true);
			Thread clean=new Thread(new Runnable() {

				@Override
				public void run() {

					cleanUp();

				}
			});
			clean.start();
			throw new SkipException("Here skipping all the test cases as user has pressed the stop button");


		} catch (Exception e) {

			e.printStackTrace();
			throw new RuntimeException("Here skipping all the test cases as user has pressed the stop button");

		}




	}

	@FXML
	public void reset(ActionEvent event) {

		try {

		
			
			
            CommonConfig.COMMAND_TIMEOUT=100;
			NUM_OF_CALL_ITER_UPDATE="";
			NUM_OF_CALL_ITER="";
			NUM_OF_CALL_ITER_INCREMENTOR="";
			
			combobox.getSelectionModel().clearSelection();
			listView.refresh();
			ObservableList<String> empty = FXCollections.observableArrayList("");
			listView.setItems(empty);


			GLOBAL_ONTESTFAILURE="";
			GLOBAL_ONTESTSTART="";
			GLOBAL_ONTESTSUCESS="";

			TOTAL_NUM_OF_TESTCASES=0;
			INCREMENT_VLAUE=0;
			TESTCASES_INCREMENTER=1;
			progressIndicator.setProgress(0);
			testCaseDisplay.clear();
			TNGListner.Listner.onFinish="";
			TNGListner.Listner.onTestSkipped="";
			TNGListner.Listner.onTestSucess="";
			TNGListner.Listner.onTestStart="";
			TNGListner.Listner.onTestFailure="";
			Platform.runLater(()->status.setText(" "));
			Platform.runLater(()->currentTestCases.setText(""));
			Platform.runLater(()->ExecutedTC.clear());
			Platform.runLater(()->failedTC.clear());
			combobox.setDisable(false);
			listView.setDisable(false);

			start.setDisable(true);
			
			
			
			onskip.cancel();
			timer1.cancel();
		    timer2.cancel();
		    timer3_failure.cancel();
		    onfinish.cancel();
            percentageCalculator.cancel();
		    call_Iter.cancel();
		    CALL_COUNT=0;
			NUM_OF_CALL_ITER_UPDATE=" ";
			NUM_OF_CALL_ITER=" ";
			NUM_OF_CALL_ITER_INCREMENTOR=" ";
			
			/*    onskip.cancel();
			timer1.cancel();
		    timer2.cancel();
		    timer3_failure.cancel();
		    onfinish.cancel();

		    percentageCalculator.cancel();
		    call_Iter.cancel();




			CommonConfig.COMMAND_TIMEOUT=100;
        	combobox.getSelectionModel().clearSelection();

			listView.refresh();

			ObservableList<String> empty = FXCollections.observableArrayList("");
			Platform.runLater(()->listView.setItems(empty));
			CALL_COUNT=0;
			NUM_OF_CALL_ITER_UPDATE=" ";
			NUM_OF_CALL_ITER=" ";
			NUM_OF_CALL_ITER_INCREMENTOR=" ";

			GLOBAL_ONTESTFAILURE="";
			GLOBAL_ONTESTSTART="";
			GLOBAL_ONTESTSUCESS="";

			TOTAL_NUM_OF_TESTCASES=0;
			INCREMENT_VLAUE=0;
			TESTCASES_INCREMENTER=1;

			TNGListner.Listner.onFinish="";
			TNGListner.Listner.onTestSkipped="";
			TNGListner.Listner.onTestSucess="";
			TNGListner.Listner.onTestStart="";
			TNGListner.Listner.onTestFailure="";
			progressIndicator.setProgress(0);
			Platform.runLater(()->status.setText(" "));
			Platform.runLater(()->currentTestCases.setText(""));
			Platform.runLater(()->ExecutedTC.clear());
			Platform.runLater(()->failedTC.clear());
			combobox.setDisable(false);
			listView.setDisable(false);


			start.setDisable(true);

			testCaseDisplay.clear();

			//	pi.setVisible(false);
			 */


		} catch(Exception e) {
			e.printStackTrace();

			System.out.println("Exeption in reset() Method.....");


		}



	}

	@FXML
	public void savereportAndlog(ActionEvent event) throws FileNotFoundException, IOException, ParseException {


		//	Date date=new Date();
		//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
		/* Calendar cal = new GregorianCalendar();
		   int month = cal.get(Calendar.MONTH);
		   int year = cal.get(Calendar.YEAR);
		   int sec = cal.get(Calendar.SECOND);
		   int min = cal.get(Calendar.MINUTE);
		   int date = cal.get(Calendar.DATE);
		   int day = cal.get(Calendar.HOUR_OF_DAY);*/
		File Dir = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
		if(!Dir.exists()) {
			Dir.mkdir();

		}

		if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35.")) {

			ObservableList<String> topics;

			topics = listView.getSelectionModel().getSelectedItems();
			for (String itemvalue : topics) {

				switch (itemvalue) {

				case "SonimCare":


					File SC = new File("src/test/resources/extentreport/XP5S_SCOUT_SonimCare_TestReport.html");
					File Sonimest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String SCPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(SC, Sonimest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(SC.exists()) {

						try {
							BaseUtil.openReportPath(SCPath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;




				case "SafeGuard":



					File WR = new File("src/test/resources/extentreport/XP5S_SCOUT_SafeGuard_TestReport.html");
					File WRDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String WRtPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(WR, WRDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(WR.exists()) {

						try {
							BaseUtil.openReportPath(WRtPath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;


				case "WarrantyRegistration":

					File SG = new File("src/test/resources/extentreport/XP5S_SCOUT_Warranty_Registration_TestReport.html");
					File SGDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String SGPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(SG, SGDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(SG.exists()) {

						try {
							BaseUtil.openReportPath(SGPath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

				case "ContactTransfer":

					File scout = new File("src/test/resources/extentreport/XP5S_SCOUT_Contact_Transfer_TestReport.html");
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

				case "ApkDownload":

					File Apk = new File("src/test/resources/extentreport/XP5S_SCOUT_Contact_Transfer_TestReport.html");
					File ApkDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String ApkPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Apk, ApkDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Apk.exists()) {

						try {
							BaseUtil.openReportPath(ApkPath);
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



					/*	case "DEV-QA-Sanity":

					File DS = new File("src/test/resources/extentreport/XP5S_Dev_Sanity_O_TestReport.html");
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

						File sanitytest = new File("src/test/resources/extentreport/XP5S_Device_QA_Sanity_Oreo_TestReport.html");
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


						File sanitytest = new File("src/test/resources/extentreport/XP5S_Device_QA_Sanity_Oreo_TestReport.html");
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


					break;*/

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

				case "Browser Stability":	

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

						/*
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

						}*/

					}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {


						File XP5stability = new File("src/test/resources/extentreport/XP5S_Browser_Stability_Orio_TestReport.html");
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
				
				
				
				case "CSFB + VoLTE":
					
					
					File csfb = new File("src/test/resources/extentreport/CSFB_VoLTE.html");
					File csfb_src = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String csfv_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(csfb, csfb_src);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(csfb.exists()) {

						try {
							BaseUtil.openReportPath(csfv_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;
				
				
				case "RebootScenario":

					File RebootSc = new File("src/test/resources/extentreport/XP8_RebootScenario.html");
					File reboot = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String rboot_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(RebootSc, reboot);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(RebootSc.exists()) {

						try {
							BaseUtil.openReportPath(rboot_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;
				
				
				case "Wi-fi":

					File data_wifi = new File("src/test/resources/extentreport/XP8_Wifi_TestReport.html");
					File datausage_wifi = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String wifi_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(data_wifi, datausage_wifi);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(data_wifi.exists()) {

						try {
							BaseUtil.openReportPath(wifi_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;
				case "DataSettings":

					File datasetting = new File("src/test/resources/extentreport/XP8_Data_Setting_Test.html");
					File data = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String data_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(datasetting, data);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(datasetting.exists()) {

						try {
							BaseUtil.openReportPath(data_path);
						} catch (IOException e) {

							e.printStackTrace();
						}
					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

				
				case "DataUsageSettings":

					File datausagesetting = new File("src/test/resources/extentreport/XP8_Data_Usage_Setting_TestReport.html");
					File datausage = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String usage_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(datausagesetting, datausage);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(datausagesetting.exists()) {

						try {
							BaseUtil.openReportPath(usage_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;


				case "Contacts Stability":

					File ContactStability = new File("src/test/resources/extentreport/XP8_Contact_Stability.html");
					File ContStability = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String contact_path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(ContactStability, ContStability);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(ContactStability.exists()) {

						try {
							BaseUtil.openReportPath(contact_path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;



				case "CallSettings":

					//	File CallSettings = new File("src/test/resources/extentreport/XP8_Call_Settings_TestReport.html");
					File CallSettings = new File("src/test/resources/extentreport/XP8_Call_Settings_TestReport.html");
					File Call = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String callsettingspath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(CallSettings, Call);

					} catch (IOException e) {

						e.printStackTrace();
					}
					if(CallSettings.exists()) {

						try {
							BaseUtil.openReportPath(callsettingspath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;




				case "PhoneDialer":

					File Phone_Dialer = new File("src/test/resources/extentreport/XP8_PhoneDialer_TestReport.html");
					File PD = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String PDPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Phone_Dialer, PD);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Phone_Dialer.exists()) {

						try {
							BaseUtil.openReportPath(PDPath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;





				case "CallHistory":

					File CallHistory = new File("src/test/resources/extentreport/XP8_CallHistory_TestReport.html");
					File CH = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String CAHPh=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(CallHistory, CH);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(CallHistory.exists()) {

						try {
							BaseUtil.openReportPath(CAHPh);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;



				case "SafeGuard":

					File SG = new File("src/test/resources/extentreport/XP8_SCOUT_SafeGuard_TestReport.html");
					File SG_DE = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(SG, SG_DE);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(SG.exists()) {

						try {
							BaseUtil.openReportPath(path);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;


				case "ApkDownload":

					File PhoneDialer1 = new File("src/test/resources/extentreport/XP8_APK_Download_Stability_Orio_TestReport.html");
					File PhoneDialer_dest1 = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String PhoneDialer_path1=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(PhoneDialer1, PhoneDialer_dest1);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(PhoneDialer1.exists()) {

						try {
							BaseUtil.openReportPath(PhoneDialer_path1);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;



				case "ContactTransfer":

					File PhoneDialer = new File("src/test/resources/extentreport/XP8_SCOUT_Contact_Transfer_TestReport.html");
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




					/*
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

					 */


					/*	case "DEV-QA-Sanity":

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

						File sanitytest = new File("src/test/resources/extentreport/XP8_Dev_SanityTest.html");
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

						File sanitytest = new File("src/test/resources/extentreport/XP8_Dev_Sanity_Test_O.html");
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

					break;*/

				case "Wifi":

					File Wifi = new File("src/test/resources/extentreport/XP8_WiFi_Stability_Orio_TestReport.html");
					File Wifiest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String Wifiath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Wifi, Wifiest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Wifi.exists()) {

						try {
							BaseUtil.openReportPath(Wifiath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;

				case "SMS":

					File smsstability = new File("src/test/resources/extentreport/XP8_SMS_Stability_Orio_TestReport.html");
					File smsdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String smspath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(smsstability, smsdest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(smsstability.exists()) {

						try {
							BaseUtil.openReportPath(smspath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;


				case "Network Stability":

					File Connectivitystability = new File("src/test/resources/extentreport/XP8_NetworkConnectivity_Stability_Orio_TestReport.html");
					File connectivitydest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String connectivitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Connectivitystability, connectivitydest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Connectivitystability.exists()) {

						try {
							BaseUtil.openReportPath(connectivitypath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

				case "Contacts":

					File Contacts = new File("src/test/resources/extentreport/XP8_CallModule_Contacts_TestReport.html");
					File contactsdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String contactspath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Contacts, contactsdest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Contacts.exists()) {

						try {
							BaseUtil.openReportPath(contactspath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;
					
				case "Messaging":

					File Messaging = new File("src/test/resources/extentreport/XP8_Messaging_TestReport.html");
					File msgdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String msgpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Messaging, msgdest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Messaging.exists()) {

						try {
							BaseUtil.openReportPath(msgpath);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;

			

				case "Telephony":

					File attstability = new File("src/test/resources/extentreport/XP8_ATT_Stability_Telephony_TestReport.html");
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


				case "Email":

					File email = new File("src/test/resources/extentreport/XP8_ATT_Stability_Email_TestReport.html");
					File emaildest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String emailpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(email, emaildest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(email.exists()) {

						try {
							BaseUtil.openReportPath(emailpath);
						} catch (IOException e) {

							e.printStackTrace();
						}



					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");
					}

					break;	
					
				case "Device Sanity":

					File DeviceNewSanity = new File("src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html");
					File DeviceNewSanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String DeviceNewSanityPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(DeviceNewSanity, DeviceNewSanityDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(DeviceNewSanity.exists()) {

						try {
							BaseUtil.openReportPath(DeviceNewSanityPath);
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

						File Stabilitytest = new File("src/test/resources/extentreport/XP8_Device_Stability_Orio_TestReport.html");
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
					
				case "Interaction Cases":

					File inter = new File("src/test/resources/extentreport/XP8_Interactions_TestReport.html");
					File interDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String interpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(inter, interDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(inter.exists()) {

						try {
							BaseUtil.openReportPath(interpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;	
					
					
					
					
					
					
				case "Interruption Cases":

					File interruption = new File("src/test/resources/extentreport/XP8_Interruption_TestReport.html");
					File interrupDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String interepath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(interruption, interrupDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(interruption.exists()) {

						try {
							BaseUtil.openReportPath(interepath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;	
					
				case "Phone Contacts":

					File PC = new File("src/test/resources/extentreport/XP8_Phone_Contacts.html");
					File PCDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String PCpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(PC, PCDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(PC.exists()) {

						try {
							BaseUtil.openReportPath(PCpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}
					break;	
					
					
					

				case "Browser Stability":	

					if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {

						/*File stability = new File("src/test/resources/extentreport/XP8_DeviceStability_TestReport.html");
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

						}*/
					}
					else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {

						File Stabilitytest = new File("src/test/resources/extentreport/XP8_Stability_Orio_TestReport.html");
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


				case "Multitasking":

					File multitasking = new File("src/test/resources/extentreport/XP3_MultiTasking_Stability_TestReport.html");
					File multiiest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String mulpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(multitasking, multiiest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(multitasking.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(mulpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;





				case "Wifi":

					File Wifi = new File("src/test/resources/extentreport/XP3_WiFi_Stability_TestReport.html");
					File Wifiest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String Wifiath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(Wifi, Wifiest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(Wifi.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(Wifiath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;





				case "SafeGuard":

					File SG = new File("src/test/resources/extentreport/XP3_SCOUT_Safeguard_TestReport.html");
					File SGest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String SGpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(SG, SGest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(SG.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(SGpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;




				case "SonimCare":



					File NsonimCareS = new File("src/test/resources/extentreport/XP3_SCOUT_SonimCare_TestReport.html");
					File SCest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String SCpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(NsonimCareS, SCest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(NsonimCareS.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(SCpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;


				case "Network Stability":

					File NS = new File("src/test/resources/extentreport/XP3_NetworkConnectivity_Stability_TestReport.html");
					File NSest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String NSpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(NS, NSest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(NS.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(NSpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;





				case "WarrantyRegistration":

					File WR = new File("src/test/resources/extentreport/XP3_SCOUT_Warranty_Registration_TestReport.html");
					File WRDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String WRpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(WR, WRDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(WR.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(WRpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;



				case "ContactTransfer":

					File CT = new File("src/test/resources/extentreport/XP3_SCOUT_Contact_Transfer_TestReport.html");
					File CTDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String CTpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(CT, CTDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(CT.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(CTpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;


				case "SMS":

					File sms = new File("src/test/resources/extentreport/XP3_SMS_Stability_TestReport.html");
					File smsDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String Smspath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(sms, smsDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(sms.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(Smspath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;

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



				case "Device Stability":

					File DS = new File("src/test/resources/extentreport/XP3_Device_Stability_TestReport.html");
					File DSDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String DSpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(DS, DSDest);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(DS.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(DSpath);
						} catch (IOException e) {

							e.printStackTrace();
						}

					}
					else {
						executionReportDoesnotExist("Test Report is not generated yet");

					}


					break;	


				case "Browser Stability":

					File DS1 = new File("src/test/resources/extentreport/XP3_Browser_Stability_TestReport.html");
					File DSDest1 = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
					String DSpath1=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
					try {
						FileUtils.copyFileToDirectory(DS1, DSDest1);
					} catch (IOException e) {

						e.printStackTrace();
					}
					if(DS1.exists()) {

						try {
							com.xp3.Utils.BaseUtil.openReportPath(DSpath1);
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
			loadingScreen.autosize();
			secondAnchorPane.getChildren().add(loadingScreen);
			anchorPane.setDisable(true);




			/*ImageView imageView = new ImageView();
			secondAnchorPane.getChildren().add(imageView);
            imageView.setImage(new Image(this.getClass().getResource("/application/loading.gif").toExternalForm()));
            anchorPane.setDisable(true);*/


		} catch (Exception e) {

			System.out.println("Problem in loadingScreen()");

			e.printStackTrace();
		}
	}




	public void ProgressIndicator() {

		try {

			pi = new ProgressIndicator();
			//	pi.getStylesheets().add(getClass().getResource("/application/newLogo.png").toExternalForm());
			/*pi.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
		dialog_new = new Dialog<>();
		dialog_new.setTitle("S-MAT");

		dialog_new.initStyle(StageStyle.DECORATED);
		//	dialog.getDialogPane().lookupButton(ButtonType.CLOSE).setDisable(true);


		dialog_new.setHeaderText("Please enter MDN's including country Code [Excluding prefix '+']");

		//MainController.secondaryStage1.setAlwaysOnTop(true);
		dialog_new.initOwner(MainController.secondaryStage1);



		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog_new.getDialogPane().getButtonTypes().addAll(loginButtonType);
		//dialog.initStyle(StageStyle.UNDECORATED);
		DialogPane dialogPane = dialog_new.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dialogPane.getStyleClass().add("MDN_Popup");

		dialog_new.getDialogPane().getScene().getWindow().setOnCloseRequest(e->{

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

		final TextField ssid=new TextField();
		ssid.setPrefWidth(200);
		ssid.setPromptText("Enter SSID :");
		grid.add(new Label("Wi-Fi SSID:"), 0, 3);
		grid.add(ssid, 1, 3);

		final TextField wifipassword=new TextField();
		wifipassword.setPrefWidth(200);
		wifipassword.setPromptText("Enter Wi-Fi Password :");
		grid.add(new Label("Wi-Fi Password:"), 0, 4);
		grid.add(wifipassword, 1, 4);



		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog_new.getDialogPane().lookupButton(loginButtonType);
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

		dialog_new.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog_new.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(), password.getText());
			}
			return null;
		});


		Optional<Pair<String, String>> result = dialog_new.showAndWait();
		result.ifPresent(Dut_Ref_Dut_email -> {

			PRIMARYDEVMDN="+"+Dut_Ref_Dut_email.getKey();
			REFERENCEDEVMDN="+"+Dut_Ref_Dut_email.getValue();
			EMAILID=email.getText().trim();
			SSID=ssid.getText().trim();
			WIFIPASSWORD=wifipassword.getText().trim();

			System.out.println(PRIMARYDEVMDN +"-"+REFERENCEDEVMDN +"Email id is :"+EMAILID +"--"+SSID+"--"+WIFIPASSWORD);



			if(result.isPresent() && (PRIMARYDEVMDN.length()>10 && PRIMARYDEVMDN.length()<=13) && (REFERENCEDEVMDN.length()>10 && REFERENCEDEVMDN.length()<=13)) {

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


	public static void fingerPrintAlertBox(String value){

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("S-MAT");
		alert.initStyle(StageStyle.UNDECORATED);
		alert.setContentText(value);
		Optional<ButtonType> action = alert.showAndWait();
		if(action.get()==ButtonType.OK) {

			System.exit(0);


		}

	}


	//This method is used to fetch the onskipped value presrnt in the listner	

	public void itemSelectedListner(){

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@SuppressWarnings("unchecked")
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


				if(listView.getItems().contains(newValue)) {


					System.out.println("Inside itemSelectedListner() Method");



					try {

						if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800") || JsonFileReaderAndWriter.primaryDevModelReader().contains("50k") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-35."))
						{


							if(newValue.contains("ContactTransfer")) {

								EMAILREPORT="src/test/resources/extentreport/XP5S_SCOUT_Contact_Transfer_TestReport.html";

								/*	try {
									testCaseDisplay.setItems(GetMethods.TestCasesMethodName("XP5S_TC_", XP5S_SCOUT_Contact_Transfer_Test.class));
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/


							}else if (newValue.contains("PhoneContacts")) {
								EMAILREPORT="src/test/resources/extentreport/XP5S_Contacts_TestReport.html";

								/*	try {
									testCaseDisplay.setItems(GetMethods.TestCasesMethodName("XP5S", XP5S_Contacts_Test.class));
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/

							}else if (newValue.contains("Device")) {

								if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP5S_Device_QA_Sanity_TestReport.html";

								}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP5S_Dev_Sanity_O_TestReport.html";

								}

							}else if (newValue.contains("MO-MT-Call")){
								EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";
							}else if (newValue.contains("MO-Call")) {
								EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";
							}else if (newValue.contains("MT-Call")) {

								EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";


							}else if (newValue.contains("Device Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP5S_Stability_TestReport.html";
							}else if (newValue.contains("Browser Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP5S_Browser_Stability_Orio_TestReport.html";
							}else if (newValue.contains("WarrantyRegistration")) {

								EMAILREPORT="src/test/resources/extentreport/XP5S_SCOUT_Warranty_Registration_TestReport.html";

							}else if (newValue.contains("SafeGuard")) {

								EMAILREPORT="src/test/resources/extentreport/XP5S_SCOUT_SafeGuard_TestReport.html";
							}else if (newValue.contains("SonimCare")) {

								EMAILREPORT="src/test/resources/extentreport/XP5S_SCOUT_SonimCare_TestReport.html";

							}


						}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("8A")||JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800") ||  JsonFileReaderAndWriter.primaryDevModelReader().contains("80k")) {

							if(newValue.contains("DEV-QA-Sanity")) {

								/*if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP8_Dev_SanityTest.html";
								}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP8_Dev_Sanity_Test_O.html";

									try {
										testCaseDisplay.setItems(GetMethods.TestCasesMethodName("XP8_",XP8_Dev_Sanity_Test_O.class));
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
								 */

							}else if (newValue.contains("Telephony")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_ATT_Stability_Telephony_TestReport.html";

								/*try {
									testCaseDisplay.setItems(GetMethods.TestCasesMethodName("_Stability_",ATT_Stability_Telephony_Test.class));
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/

							}else if (newValue.contains("Email")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_ATT_Stability_Email_TestReport.html";

							}else if (newValue.contains("Device")) {

								if(JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-7.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP8_Device_QA_Sanity_Test.html";

								}else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-8.1")) {
									EMAILREPORT="src/test/resources/extentreport/XP8_Device_QA_Sanity_Test_O.html";

									/*try {
										testCaseDisplay.setItems(GetMethods.TestCasesMethodName("XP8_DeviceSanity",XP8_Device_QA_Sanity_Test_O.class));
									} catch (ClassNotFoundException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}*/

								}
							}else if (newValue.contains("MO-MT-Call")) {

								EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";

							}else if (newValue.contains("MO-Call")) {

								EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";

							}else if (newValue.contains("MT-Call")) {

								EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";
							}
							else if (newValue.contains("Device Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_DeviceStability_TestReport.html";  
							}else if (newValue.contains("Browser Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_Browser_Stability_Orio_TestReport.html";
							}if(newValue.contains("ContactTransfer")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_SCOUT_Contact_Transfer_TestReport.html";
								/*
								try {
									testCaseDisplay.setItems(GetMethods.TestCasesMethodName("XP8_", XP8_ContactTransfer_Test.class));
								} catch (ClassNotFoundException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}*/

							}if(newValue.contains("ApkDownload")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_APK_Download_Stability_Orio_TestReport.html";
							}else if (newValue.contains("SafeGuard")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_SCOUT_SafeGuard_TestReport.html";
							}else if (newValue.contains("CallHistory")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_CallHistory_TestReport.html";


							}else if (newValue.contains("PhoneDialer")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_PhoneDialer_TestReport.html";


							}else if (newValue.contains("Callsettings")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_Call_Settings_TestReport.html";

							}else if (newValue.contains("CallHistory")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_CallHistory_TestReport.html";

							}else if (newValue.contains("SMS")) {

								EMAILREPORT="src/test/resources/extentreport/XP8_SMS_Stability_Orio_TestReport.html";



							}else if (newValue.contains("Contacts Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP8_Contact_Stability.html";

							}else if (newValue.contains("RebootScenario")) {
								
								EMAILREPORT="src/test/resources/extentreport/XP8_RebootScenario.html";

								
							}

						}else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp3800") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("3A")) {

							if(newValue.equals("Device")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_Sanity.html";


							}else if (newValue.contains("MO-MT-Call")) {
								System.out.println("000000000000MO-MT");
								EMAILREPORT="src/test/resources/extentreport/MO_MT_CallPerformanceReport.html";


							}else if (newValue.contains("MT-Call")) {
								System.out.println("000000000000MT");

								EMAILREPORT="src/test/resources/extentreport/MT_CallPerformance_Report.html";
							}else if (newValue.contains("MO-Call")) {
								System.out.println("000000000000");
								EMAILREPORT="src/test/resources/extentreport/MO_CallPerformance_Report.html";

							}else if (newValue.contains("Device Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP3_Device_Stability_TestReport.html";	
							}else if (newValue.contains("Browser Stability")) {
								EMAILREPORT="src/test/resources/extentreport/XP3_Browser_Stability_TestReport.html";
							}else if (newValue.contains("SMS")) {
								EMAILREPORT="src/test/resources/extentreport/XP3_SMS_Stability_TestReport.html";

							}else if (newValue.contains("ContactTransfer")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_SCOUT_Contact_Transfer_TestReport.html";

							}else if (newValue.contains("WarrantyRegistration")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_SCOUT_Warranty_Registration_TestReport.html";

							}else if (newValue.contains("Network Stability")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_NetworkConnectivity_Stability_TestReport.html";


							}else if (newValue.contains("SonimCare")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_SCOUT_SonimCare_TestReport.html";	
							}else if(newValue.contains("Wifi")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_WiFi_Stability_TestReport.html";	


							}else if(newValue.contains("Multitask")) {

								EMAILREPORT="src/test/resources/extentreport/XP3_MultiTasking_Stability_TestReport.html";	

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
						System.out.println(EMAILREPORT);

					} catch (IOException | ParseException  e) {

						e.printStackTrace();
					}



				}


			}


		});
	}




	//THis item listner will get the selected item from listview .	
	public void itemListner2() {


		try {

			listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


					if(newValue.equals("MT-Call")) {

						System.out.println("HEY WHATS=");
						CommonConfig.CALL_MODULE="MT-Call";

					}else if (newValue.equals("Contacts Stability") ||newValue.equals("Browser Stability") || newValue.equals("Device Stability") || newValue.equals("Network Stability")|| newValue.equals("ApkDownload") || newValue.equals("SMS") ||newValue.equals("Wifi") || newValue.equals("Multitasking")) {
						CommonConfig.CALL_MODULE="";
						System.out.println("Here we are reseting the Call Module to null");
						iteration_Input_Dialog();
						
					}else if (newValue.equals("Device Sanity") || newValue.equals("Interaction Cases")) {
						
						CommonConfig.CALL_MODULE="";
						System.out.println("Here we are reseting the Call Module to null");
						testCaseDisplay.setStyle("-fx-text-inner-color: green;");
						testCaseDisplay.setText("Please follow the below preconditions :\n\n1. Side Connector should be connected \n2. Head phone should be connected \n3. Connect the Device to the Wi-fi network");
						
						
					}else if (newValue.equals("RebootScenario")) {
						
						iteration_Reboot();
						
					}else if (newValue.equals("CSFB + VoLTE")) {
						
						iteration_Input_Dialog();
						
					}else {
						System.out.println("Here we are reseting the Call Module to null");
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
					
					//System.out.println("11111111111111111111111111");


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
					
					//System.out.println("22222222222222222222");

					if(TNGListner.Listner.onTestSucess.equals("")) {

					}
					else if (TNGListner.Listner.onTestSucess.equalsIgnoreCase(GLOBAL_ONTESTSUCESS)) {


					}
					else 
					{
						System.out.println("Tiemr 2---->Sucess of Test cases");
						Platform.runLater(()->ExecutedTC.appendText(TNGListner.Listner.onTestSucess));
						Platform.runLater(()->ExecutedTC.setStyle("-fx-text-inner-color: blue;"));	
						GLOBAL_ONTESTSUCESS=TNGListner.Listner.onTestSucess;
					}
				} 
			}, 0, 10*(10*1));



			timer3_failure = new Timer(); 
			timer3_failure.schedule( new TimerTask() 
			{ 
				public void run() { 
					
					//System.out.println("33333333333333333333333333");


					if(TNGListner.Listner.onTestFailure.equals("")) {

					}
					else if (TNGListner.Listner.onTestFailure.equalsIgnoreCase(GLOBAL_ONTESTFAILURE)) {

					}
					else 
					{

						System.out.println("Timer_failure got failed ");

						Platform.runLater(()->failedTC.appendText(TNGListner.Listner.onTestFailure));

						GLOBAL_ONTESTFAILURE=TNGListner.Listner.onTestFailure;


					}
				} 
			}, 0, 10*(100*1));


			onfinish=new Timer();
			onfinish.schedule(new TimerTask() {
				public void run() {

					//System.out.print("555555555555555555555555555555");

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

					//System.out.print("666666666666666666666666");
					
					if(TNGListner.Listner.onTestSkipped.equals("TCSKIPPED")) {	


						Platform.runLater(()->status.setText("All test cases are skipped."));
						onskip.cancel();
						onfinish.cancel();
						timer1.cancel();
						timer2.cancel();
						timer3_failure.cancel();
						stopButton.setDisable(true);
						ResetBtn.setDisable(false);

						if(NUM_OF_CALL_ITER_UPDATE.equals("NOT IMS")){

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							Platform.runLater(()->status.setText("SIM IS NOT IMS REGISTRED/DEVICE IS NOT VOLTE SUPPORTED SKIPPING ALL THE TESTCASES"));
						}



					}

				}
			},  0, 10*(100*1)); 


			percentageCalculator = new Timer(); 
			percentageCalculator.schedule( new TimerTask() 
			{ 
				public void run() { 

					if(appiumService.INCREMENT_VLAUE!=0.0) {

						Platform.runLater(()->progressIndicator.setProgress(INCREMENT_VLAUE));

					}

				} 
			}, 0, 10*(10*1));


			call_Iter=new Timer();
			call_Iter.schedule(new TimerTask() {

				@Override
				public void run() {

					if(NUM_OF_CALL_ITER.equals("")) {

					}
					else if (NUM_OF_CALL_ITER.equalsIgnoreCase(NUM_OF_CALL_ITER_INCREMENTOR)) {

					}
					else 
					{

						Platform.runLater(()->testCaseDisplay.appendText(NUM_OF_CALL_ITER));
						Platform.runLater(()->testCaseDisplay.setStyle("-fx-text-inner-color: blue;"));
						NUM_OF_CALL_ITER_INCREMENTOR=NUM_OF_CALL_ITER;




						double percentagePerCase = (CALL_COUNT/NUMOFCALLS);


						DecimalFormat df = new DecimalFormat("#.##");
						df.setRoundingMode(RoundingMode.FLOOR);
						String num = df.format(percentagePerCase);
						System.out.println("CAll count :"+NUMOFCALLS);
						double value=Double.parseDouble(num);
						System.out.println("CAll COUNT is "+value);
						Platform.runLater(()->progressIndicator.setProgress(value));




					}

				}
			}, 0, 20*(10*1));





		}catch (Exception e) {

			System.out.println("Some error occurs in Timers ");
		}

	}




	//This method is used for entering numbers of calls	
	public void inputDialogBoxfornumOfCalls() {


		dialog_new = new Dialog<>();
		dialog_new.setTitle("S-MAT");
		dialog_new.setHeaderText("Please Enter the Call details below :");
		dialog_new.initOwner(MainController.secondaryStage1);


		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog_new.getDialogPane().getButtonTypes().addAll(loginButtonType);
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


		grid.add(new Label("Please Enter number of calls (1-1000):"), 0, 0);
		grid.add(username, 1, 0);

		grid.add(new Label("Call Duration in Seconds (1-3600):"), 0, 1);
		grid.add(CallDuration, 1, 1);

		grid.add(new Label("Successive Call Gap in Seconds (1-120) :"), 0, 2);
		grid.add(callGaps, 1, 2);



		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog_new.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> {
			loginButton.setDisable(newValue.trim().isEmpty());
		});


		dialog_new.getDialogPane().setContent(grid);

		// Request focus on the username field by default.
		//	Platform.runLater(() -> username.requestFocus());

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog_new.setResultConverter(dialogButton -> {
			if (dialogButton == loginButtonType) {
				return new Pair<>(username.getText(),"");
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog_new.showAndWait();

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
	/*@FXML
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


	}*/


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
			e.printStackTrace();
			System.out.println("Problem ocurs in progressAction Method");
		}

	}






	//This method is used for taking number of iteration from user for stablitity test cases
	public void iteration_Input_Dialog() {

		try {

			TextInputDialog dialog = new TextInputDialog("");
			dialog.initOwner(MainController.secondaryStage1);
			dialog.setTitle("Iteration Dialog:");
			dialog.setHeaderText("Iterations :");
			dialog.setContentText("Please enter number of Iterations(Max Iter Limit 30):");

			// Traditional way to get the response value.
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
				NUMOFCALLS=Integer.parseInt(result.get());
			}

			// The Java 8 way to get the response value (with lambda expression).
			result.ifPresent(name -> System.out.println("Number of Iterations: " + name));


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	//THis method is for reboot scenario

	public void iteration_Reboot() {
		

		try {
			dialog_new = new Dialog<>();
			dialog_new.setTitle("S-MAT");
			dialog_new.initOwner(MainController.secondaryStage1);


			// Set the button types.
			ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
			dialog_new.getDialogPane().getButtonTypes().addAll(loginButtonType);
			//dialog.initStyle(StageStyle.UNDECORATED);

			// Create the username and password labels and fields.
			GridPane grid = new GridPane();
			grid.setHgap(12);
			grid.setVgap(12);
			grid.setPadding(new Insets(20, 150, 10, 10));

			final TextField username = new TextField();
			username.setPromptText("Num of reboots");

			final TextField CallDuration = new TextField();
			CallDuration.setPromptText("Total num of steps");





			grid.add(new Label("Num of time device Reboots(This will reboot the device as per the user inputs)[max=100]:"), 0, 0);
			grid.add(username, 1, 0);

			grid.add(new Label("Total num of iter(Reboot + number of times script will execute )[max=10]"), 0, 1);
			grid.add(CallDuration, 1, 1);





			// Enable/Disable login button depending on whether a username was entered.
			Node loginButton = dialog_new.getDialogPane().lookupButton(loginButtonType);
			loginButton.setDisable(true);

			// Do some validation (using the Java 8 lambda syntax).
			username.textProperty().addListener((observable, oldValue, newValue) -> {
				loginButton.setDisable(newValue.trim().isEmpty());
			});


			dialog_new.getDialogPane().setContent(grid);

			// Request focus on the username field by default.
			//	Platform.runLater(() -> username.requestFocus());

			// Convert the result to a username-password-pair when the login button is clicked.
			dialog_new.setResultConverter(dialogButton -> {
				if (dialogButton == loginButtonType) {
					return new Pair<>(username.getText(),"");
				}
				return null;
			});

			Optional<Pair<String, String>> result = dialog_new.showAndWait();

			result.ifPresent(usernamePassword -> {
				NUMOFCALLS=Integer.parseInt(usernamePassword.getKey());
				NUMOFSTEPS=Integer.parseInt(CallDuration.getText());



				System.out.println("The number of call"+NUMOFCALLS);
				System.out.println("Call Duration is "+NUMOFSTEPS);

				/*if(result.isPresent() && NUMOFCALLS<=1000) {

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

*/
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	
		

	
	
	
	
	
}
}