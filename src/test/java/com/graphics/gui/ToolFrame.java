/*package com.graphics.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.JobAttributes;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileDeleteStrategy;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.formula.functions.T;
import org.bouncycastle.jcajce.provider.symmetric.ARC4.Base;
import org.bouncycastle.util.io.StreamOverflowException;
import org.json.simple.parser.ParseException;
import org.jsoup.Connection.Method;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.TestNGException;
import org.testng.collections.Lists;
import org.testng.internal.annotations.TestAnnotation;


import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.Client;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.TimeoutException;
import com.android.sdklib.devices.Device;
import com.mongodb.util.JSON;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import javassist.tools.framedump;


import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.glass.ui.Window.Level;
import com.xp5S.util.BaseUtil;
import com.xp5S.util.CommonConfig;
import com.xp5S.util.DataProviders;


import TNGListner.BlockingDialog;
import antlr.build.Tool;
import antlr.debug.DebuggingInputBuffer;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.Toolkit;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.RenderedImage;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.Button;
import java.awt.TextArea;
import java.awt.Choice;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import java.awt.Window.Type;
import javax.swing.JProgressBar;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Scrollbar;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;

public class ToolFrame extends CommonConfig{



	public static JFrame frmMobileAutomationTray;
	

	public static	Thread startVideo=null;
	public static String TestNgPath="src/test/resources/drivers/testng.xml";
	public static JComboBox comboBox_2;
	public static JComboBox comboBox_3;
	public static  JComboBox comboBox;
	public static JComboBox comboBox_1;
	public static ProcessBuilder pb;

	public static Button resetAll;
	public static Button button_2;
	public static AndroidDebugBridge debugBridge;
	public static JTextArea deviceId;
	public static JProgressBar progressBar;
	public static Button button_6;
	public static JPanel panel;
	public static Button button;
	public static Button button_3;
	public static JScrollPane scrollPane;
	public static JPanel panel_6;
	public static Button button_7;
	public static Button button_1;
	public static JPanel panel_7;
	public static Thread safeGuardTestCases;
	public static Thread sonimCareTc;
	private static JScrollPane scrollPane_1;
	public static JTextArea logwindow;
	public static String DeviceModel;
	public static Thread SonimWarrantyTc;
	public static Thread ProgrammableKeyTc;
	public static Thread AppUpdaterTc;
	public static  Thread ContactTransferTc;
	public static Thread Sanitytest;
	public static  Thread Calc;
	public static Thread SoundRec;
	public static  Thread FM;
	public static Thread SelectAll;
	public static Thread t=null;

	public static int hours=0;
	public static int minutes=0;
	public static int seconds=0;
	public static String timeString = "";
	private static JTextField textField;
	private static JPanel panel_8;
	private static JLabel lblNewLabel;
	private static final JTree tree = new JTree();
	private static JLabel lblStopTime;
	private static JLabel lblTotalExecutionTime;
	public static JTextField textField_3;
	public static String DeviceTypeForXP8;
	public static Thread XP5_XP8;
	public static JTextField textField_1;
	public static JTextField textField_4;
	public static int i=0;
	public static JLabel RefDeviceId;
	public static JTextArea RefDev;
	public static JTextArea primaryDevBuildNum;
	public static JTextArea ReferenceDevBuildNum;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {


					ToolFrame window = new ToolFrame();
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //Windows Look and feel     

					window.totalTime();
					window.deviceDetect();
					window.startButton();
					window.stopButton();
					window.startTime();
					window.stopTime();
					window.ProgressBard();
					//   window.totalTime();

					window.DeviceTypeSelection(); 
					window.operatorSelection();

					window.loginWindow();
					window.combobox2();
					
					window.primaryDeviceBuildNum();
					window.ReferenceDeviceBuildNum();
					
					window.frmMobileAutomationTray.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


	}

	*//**
	 * Create the application.
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 *//*
	public ToolFrame() throws FileNotFoundException, IOException, ParseException {

		initialize();

	}

	*//**
	 * Initialize the contents of the frame.
	 *//*



	public static void initialize() {

		frmMobileAutomationTray = new JFrame();
	    frmMobileAutomationTray.setDefaultLookAndFeelDecorated(true);
		frmMobileAutomationTray.setForeground(new Color(51, 255, 51));
		frmMobileAutomationTray.setResizable(true);
		frmMobileAutomationTray.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmMobileAutomationTray.getContentPane().setBackground(Color.CYAN);
		frmMobileAutomationTray.setIconImage(Toolkit.getDefaultToolkit().getImage("src/test/resources/images/sm.jpg"));
		frmMobileAutomationTray.getContentPane().setForeground(new Color(51, 204, 255));
		frmMobileAutomationTray.setBackground(Color.GREEN);
		frmMobileAutomationTray.setTitle("S-MAT");

		frmMobileAutomationTray.setBounds(0, 0, 1382, 743);
		frmMobileAutomationTray.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmMobileAutomationTray.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		frmMobileAutomationTray.getContentPane().setLayout(null);
		

		frmMobileAutomationTray.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int value = JOptionPane.showConfirmDialog(null,"<html><b>Do you want to Exit?</b></html>", "WARNING", dialogButton);
				if (value == 0) {
					System.exit(0);
				}
			}

		});

		panel = new JPanel();
		panel.setBounds(22, 50, 196, 360);
		panel.setForeground(new Color(30, 144, 255));
		panel.setBackground(Color.GRAY);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		frmMobileAutomationTray.getContentPane().add(panel);
		panel.setLayout(null);


		//This is logWindow code 

		comboBox_3 = new JComboBox();
		comboBox_3.setEnabled(false);
		comboBox_3.setForeground(Color.BLACK);
		comboBox_3.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(43, 266, 122, 22);
		comboBox_3.setRenderer(new MyComboBoxRenderer("Applications"));
		comboBox_3.setSelectedIndex(-1);
		panel.add(comboBox_3);

		comboBox_3.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox_3 = (JComboBox) e.getSource();

				String items_Apps =String.valueOf(comboBox_3.getSelectedItem());

				switch(items_Apps) {

				case "Safeguard":
					JOptionPane.showMessageDialog(null, "You have selected :" +items_Apps);
					DialogMessage();
					DialogMessage1();

					break;
				case "SonimCare":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "SonimWarranty":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();    
					DialogMessage1();
					break;
				case "Programmable Key":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage(); 
					DialogMessage1();

					break;
				case "App Updater":	
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "Contact Transfer":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "SanityTest":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "FM":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "Calc": 
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "SoundRecorder":	
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "MonkeyRunAllApps":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();
					break;
				case "SelectAll":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "ScoutFunctionalTest":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;	
				case "BELSanityTest":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "SPRSanityTest":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "Browser":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();
					break;	
				case "ContactTransfer":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break;
				case "BELLSanityTest":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();

					break; 	

				case "Dailer":
					JOptionPane.showMessageDialog(null, "You have selected : "+items_Apps);

					DialogMessage();
					DialogMessage1();
					break; 	 	

				}




			}


		});

		deviceId = new JTextArea();
		deviceId.setLineWrap(true);
		deviceId.setBounds(721, 111, 73, 22);
		deviceId.setForeground(new Color(255, 255, 255));
		deviceId.setBackground(Color.WHITE);
		deviceId.setEditable(false);
		frmMobileAutomationTray.getContentPane().add(deviceId);
		//Thread for Printing the output




		panel_6 = new JPanel();
		panel_6.setBackground(Color.GRAY);
		panel_6.setBounds(22, 421, 97, 42);
		panel_6.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmMobileAutomationTray.getContentPane().add(panel_6);
		panel_6.setLayout(null);






		panel_7 = new JPanel();
		panel_7.setBackground(Color.GRAY);
		panel_7.setBounds(126, 421, 92, 42);
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmMobileAutomationTray.getContentPane().add(panel_7);
		panel_7.setLayout(null);



		JLabel lblNewLabel_1 = new JLabel("                                        Sonim Mobile Applications Tool");
		lblNewLabel_1.setBounds(69, 11, 622, 28);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		frmMobileAutomationTray.getContentPane().add(lblNewLabel_1);




		JLabel lblNewLabel_2 = new JLabel("Test Progress Bar :");
		lblNewLabel_2.setBounds(389, 48, 220, 25);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMobileAutomationTray.getContentPane().add(lblNewLabel_2);




		JLabel lblNewLabel_4 = new JLabel("TestCase In Progress :");
		lblNewLabel_4.setBounds(390, 108, 138, 29);
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBackground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMobileAutomationTray.getContentPane().add(lblNewLabel_4);
		

		button_6 = new Button("Report and Logs");
		button_6.setBackground(new Color(59, 89, 182));
		button_6.setForeground(Color.WHITE);
		button_6.setBounds(228, 138, 151, 22);
		button_6.setEnabled(false);
		button_6.addActionListener(new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent eve) {

				Date date=new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss") ;
				File Dir = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");



				if(!Dir.exists()) {
					Dir.mkdir();

				}


			//	DeviceModel=BaseUtil.fetchDeviceProperty("adb shell getprop ro.product.model");

				try {
					
					if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800"))
					{

						String items_Apps = String.valueOf(comboBox_3.getSelectedItem());
						switch (items_Apps) {





						case "BELLSanityTest": 

							File BElTestSuite = new File("src/test/resources/extentreport/XP5S_BELL_Sanity_TestReport.html");
							File BElLog=new File("src/test/resources/adbLogs/BELLSanityAdb.txt");
							File BELDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String BELPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";

							try {
								FileUtils.copyFileToDirectory(BElTestSuite, BELDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(BElLog, BELDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(BElTestSuite.exists() && BElLog.exists()) {


								try {
									BaseUtil.openReportPath(BELPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;




						case "App Updater": 

							File appUpdater = new File("src/test/resources/extentreport/XP5S_ScoutApps_AppUpdater_TestReport.html");
							File appUpdaterLog=new File("src/test/resources/adbLogs/AppUpdater_AdbLog.txt");
							File appUpdaterDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String appUdaterPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";

							try {
								FileUtils.copyFileToDirectory(appUpdater, appUpdaterDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(appUpdaterLog, appUpdaterDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(appUpdater.exists() && appUpdaterLog.exists()) {


								try {
									BaseUtil.openReportPath(appUdaterPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "SonimCare": 	
							File sonimCare = new File("src/test/resources/extentreport/XP5S_SonimCare_TestReport.html");
							File sonimCareLogFile=new File("src/test/resources/adbLogs/SonimCare_AdbLog.txt");
							File sonimCareDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String sonimcarePath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(sonimCare, sonimCareDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(sonimCareLogFile, sonimCareDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(sonimCare.exists() && sonimCareLogFile.exists()) {

								try {
									BaseUtil.openReportPath(sonimcarePath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}						}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "Safeguard":
							File safeGuard = new File("src/test/resources/extentreport/XP5S_ScoutApps_Safeguard_TestReport.html");
							File safeGuardLog=new File("src/test/resources/adbLogs/SafeGuard_AdbLog.txt");
							File safeGuardDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String safeGuardPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(safeGuard, safeGuardDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(safeGuardLog, safeGuardDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(safeGuard.exists() && safeGuardLog.exists()) {

								try {
									BaseUtil.openReportPath(safeGuardPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "Programmable Key":
							File programableKey = new File("src/test/resources/extentreport/XP5S_Scout_ProgrammableKeyTestReport.html");
							File programableKeyLog=new File("src/test/resources/adbLogs/ProgrammableKey_AdbLog.txt");
							File programableKeyDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String prgKey=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(programableKey, programableKeyDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(programableKeyLog, programableKeyDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(programableKey.exists() && programableKeyLog.exists()) {

								try {
									BaseUtil.openReportPath(prgKey);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "SonimWarranty":
							File warrantyReg = new File("src/test/resources/extentreport/XP5S_Scout_SonimWarrantyReg.html");
							File warrantyRegLog=new File("src/test/resources/adbLogs/SonimWarranty_AdbLog.txt");
							File warrantyRegDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String warPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(warrantyReg, warrantyRegDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(warrantyRegLog, warrantyRegDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(warrantyReg.exists() && warrantyRegLog.exists()) {

								try {
									BaseUtil.openReportPath(warPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "Contact Transfer":
							File contactTransfer = new File("src/test/resources/extentreport/XP5S_ScoutApps_ContactTransfer_TestReport.html");
							File contactTransferLog=new File("src/test/resources/adbLogs/ContactTransfer_AdbLog.txt");
							File contactTransferDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String contPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(contactTransfer, contactTransferDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(contactTransferLog, contactTransferDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(contactTransfer.exists() && contactTransferLog.exists()) {

								try {
									BaseUtil.openReportPath(contPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "SanityTest": 
							File sanitytest = new File("src/test/resources/extentreport/XP5S_SanityTestReport.html");
							File sanityAdbLog=new File("src/test/resources/adbLogs/SanityTest_AdbLog.txt");
							File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(sanitytest, sanityDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(sanityAdbLog, sanityDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(sanitytest.exists() && sanityAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(sanitypath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;

						case "Calc":
							File calctest = new File("src/test/resources/extentreport/CalculatorTestReport.html");
							File calcAdbLog=new File("src/test/resources/adbLogs/Calc_AdbLog.txt");
							File calcDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String calPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(calctest, calcDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(calcAdbLog, calcDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(calctest.exists() && calcAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(calPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;

						case "SoundRecorder":
							File SoundRec = new File("src/test/resources/extentreport/SoundRecorderTestReport.html");
							File SoundAdbLog=new File("src/test/resources/adbLogs/SoundRec_Adb.txt");
							File Soundest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String soundRec=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(SoundRec, Soundest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SoundAdbLog, Soundest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(SoundRec.exists() && SoundAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(soundRec);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							


							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "FM":
							File FM = new File("src/test/resources/extentreport/FM_TestReport.html");
							File FMAdbLog=new File("src/test/resources/adbLogs/FM_Adb.txt");
							File FMdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String FMPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(FM, FMdest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(FMAdbLog, FMdest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(FM.exists() && FMAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(FMPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;

						case "SelectAll":

							File Select_All = new File("src/test/resources/extentreport/AllPre_bundle_Apps_TestReport.html");
							File SelectAdbLog_All=new File("src/test/resources/adbLogs/SelectAll_Adb.txt");
							File SelectDest_All = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String SelectPath_All=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(Select_All, SelectDest_All);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SelectAdbLog_All, SelectDest_All);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(Select_All.exists() && SelectAdbLog_All.exists()) {

								try {
									BaseUtil.openReportPath(SelectPath_All);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "ScoutFunctionalTest":

							File FTSanity = new File("src/test/resources/extentreport/XP5S_Scout_Functional_TestReport.html");
							File FTAdbLog=new File("src/test/resources/adbLogs/SelectAll_Adb.txt");
							File FT_Dest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String FTPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(FTSanity, FT_Dest);
							} catch (IOException e) {

								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(FTAdbLog,FT_Dest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(FTSanity.exists() && FTAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(FTPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;

						default:
							File Directory = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							Desktop desktop = Desktop.getDesktop();
							File dirToOpen = null;
							try {

								desktop.open(Directory);
							} catch (IllegalArgumentException | IOException iae) {
								System.out.println("File Not Found");
							}

						}

					}
					else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800"))
					{
						String items_Apps = String.valueOf(comboBox_3.getSelectedItem());
						switch(items_Apps)
						{
						case "Dailer":
							File DailerReport = new File("src/test/resources/extentreport/XP8_PreBundled_DailerTestReport.html");
							File DailerAdbLog=new File("src/test/resources/adbLogs/DailerAdbLog.txt");
							File DailerDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String DailerPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(DailerReport, DailerDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(DailerAdbLog, DailerDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(DailerReport.exists() && DailerAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(DailerPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							


							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet !!!");
							}
							break;

						case "ContactTransfer":
							File ContactTransfer = new File("src/test/resources/extentreport/XP8_ATT_ScoutApps_ContactTransfer_TestReport.html");
							File CTAdbLog=new File("src/test/resources/adbLogs/XP8AdbCT.txt");
							File CTDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String CTPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(ContactTransfer, CTDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(CTAdbLog, CTDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(ContactTransfer.exists() && CTAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(CTPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							


							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet !!!");
							}
							break;



						case "Browser":
							File Browser = new File("src/test/resources/extentreport/XP8_BrowserTest.html");
							File BrowserAdbLog=new File("src/test/resources/adbLogs/XP8AdbBrowser.txt");
							File Brdestest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String Browserpath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(Browser, Brdestest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(BrowserAdbLog, Brdestest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(Browser.exists() && BrowserAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(Browserpath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							


							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet !!!");
							}
							break;

						case "SoundRecorder":
							File SoundRec = new File("src/test/resources/extentreport/XP8_PrebundleApps__SoundRecTest_Report.html");
							File SoundAdbLog=new File("src/test/resources/adbLogs/XP8_SoundRec_Adb.txt");
							File Soundest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String soundRec=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(SoundRec, Soundest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SoundAdbLog, Soundest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(SoundRec.exists() && SoundAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(soundRec);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							


							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet !!!");
							}
							break;

						case "Calc":
							File calctest = new File("src/test/resources/extentreport/XP8_PreBundleApps_CalcTest_Report.html");
							File calcAdbLog=new File("src/test/resources/adbLogs/XP8_Calc_AdbLog.txt");
							File calcDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String calPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(calctest, calcDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(calcAdbLog, calcDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(calctest.exists() && calcAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(calPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet!");
							}
							break;	  

						case "SanityTest": 
							File sanitytest = new File("src/test/resources/extentreport/XP8_SanityTest_ATT.html");
							File sanityAdbLog=new File("src/test/resources/adbLogs/XP8_SanityTest_AdbLog.txt");
							File sanityDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String sanitypath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(sanitytest, sanityDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(sanityAdbLog, sanityDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(sanitytest.exists() && sanityAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(sanitypath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet!");
							}
							break;
						case "FM":
							File FM = new File("src/test/resources/extentreport/XP8_FM_Test.html");
							File FMAdbLog=new File("src/test/resources/adbLogs/XP8_FM_AdbLog.txt");
							File FMdest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String FMPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(FM, FMdest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(FMAdbLog, FMdest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(FM.exists() && FMAdbLog.exists()) {

								try {
									BaseUtil.openReportPath(FMPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "Reports are not generated yet!");
							}
							break;	

						case "SonimCare": 	
							File sonimCare = new File("src/test/resources/extentreport/XP8_ScoutApps_SonimCare_TestReport.html");
							File sonimCareLogFile=new File("src/test/resources/adbLogs/XP8_SonimCare_AdbLog.txt");
							File sonimCareDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String sonimcarePath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(sonimCare, sonimCareDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(sonimCareLogFile, sonimCareDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(sonimCare.exists() && sonimCareLogFile.exists()) {

								try {
									BaseUtil.openReportPath(sonimcarePath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}						}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;	
						case "Programmable Key":
							File programableKey = new File("src/test/resources/extentreport/XP8_ScoutApps_ProgrammableKey_TestReport.html");
							File programableKeyLog=new File("src/test/resources/adbLogs/XP8_ProgrammableKey_AdbLog.txt");
							File programableKeyDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String prgKey=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(programableKey, programableKeyDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(programableKeyLog, programableKeyDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(programableKey.exists() && programableKeyLog.exists()) {

								try {
									BaseUtil.openReportPath(prgKey);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}

							break;	
						case "Safeguard":
							File SG = new File("src/test/resources/extentreport/XP8_ScoutApps_Safeguard_TestReprt.html");
							File SGLog=new File("src/test/resources/adbLogs/XP8AdbSafeGuard.txt");
							File SGDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String SGPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(SG, SGDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SGLog, SGDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(SG.exists() && SGLog.exists()) {

								try {
									BaseUtil.openReportPath(SGPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}			  

							break;	
						case "SonimWarranty":
							File WarrantyReg = new File("src/test/resources/extentreport/XP8_ScoutApss_SonimWarranty_TestReport.html");
							File WarrantyRegLog=new File("src/test/resources/adbLogs/XP8AdbSW.txt");
							File WarrantyRegDest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String WarrantyRegPath=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(WarrantyReg, WarrantyRegDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(WarrantyRegLog, WarrantyRegDest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(WarrantyReg.exists() && WarrantyRegLog.exists()) {

								try {
									BaseUtil.openReportPath(WarrantyRegPath);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}			  
							break;

						case "SelectAll":

							File Select_All = new File("src/test/resources/extentreport/XP8_AllPre_bundle_Apps_TestReport.html");
							File SelectAdbLog_All=new File("src/test/resources/adbLogs/XP8_AllAppAdbLog.txt");
							File SelectDest_All = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String SelectPath_All=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(Select_All, SelectDest_All);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SelectAdbLog_All, SelectDest_All);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(Select_All.exists() && SelectAdbLog_All.exists()) {

								try {
									BaseUtil.openReportPath(SelectPath_All);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}



							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;
						case "BELSanityTest":

							File BEL = new File("src/test/resources/extentreport/XP8_SanityTest_BEL.html");
							File BEL_AdbLog=new File("src/test/resources/adbLogs/XP8AdbLog_BEL.txt");
							File BEL_Dest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String BEL_Path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(BEL, BEL_Dest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(BEL_AdbLog, BEL_Dest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(BEL.exists() && BEL_AdbLog.exists()) {

								try {
									BaseUtil.openReportPath(BEL_Path);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							

							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}

						case "SPRSanityTest":

							File SPR = new File("src/test/resources/extentreport/XP8_SanityTest_SPR.html");
							File SPR_AdbLog=new File("src/test/resources/adbLogs/XP8AdbLog_SPR.txt");
							File SPR_Dest = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							String SPR_Path=System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog";
							try {
								FileUtils.copyFileToDirectory(SPR, SPR_Dest);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								FileUtils.copyFileToDirectory(SPR_AdbLog, SPR_AdbLog);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(SPR.exists() && SPR_AdbLog.exists()) {

								try {
									BaseUtil.openReportPath(SPR_Path);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}							

							}
							else {
								JOptionPane.showMessageDialog(null, "<html><b>Reports are not generated !!!</b></html>");
							}
							break;	

						default:
							File Directory = new File(System.getProperty("user.home") +File.separator +"ExecutionReport_AdbLog");
							Desktop desktop = Desktop.getDesktop();
							File dirToOpen = null;
							try {

								desktop.open(Directory);
							} catch (IllegalArgumentException | IOException iae) {
								System.out.println("File Not Found");
							}	


						}	
					}
					
					
				} catch (HeadlessException | IOException | ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});
		button_6.setForeground(UIManager.getColor("Button.highlight"));
		button_6.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMobileAutomationTray.getContentPane().add(button_6);

		button_7 = new Button("Start Video");
        button_7.setFocusable(true);
		button_7.setBackground(new Color(59, 89, 182));
		button_7.setForeground(Color.WHITE);
		button_7.setBounds(228, 190, 151, 22);
		button_7.setEnabled(false);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				Thread startVideo=new Thread(new Runnable() {
					public void run() {

						pb = new ProcessBuilder("java", "-jar", "src/test/resources/drivers/asm.jar");
						try {
							Process p = pb.start();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	
						button_7.setEnabled(false);	



					}
				});
				startVideo.start();


			}
		});


		button_7.setForeground(Color.BLACK);
		button_7.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMobileAutomationTray.getContentPane().add(button_7);



		JLabel lblPriDeviceId = new JLabel("Pri Device ID:");
		lblPriDeviceId.setBounds(646, 108, 97, 28);
		lblPriDeviceId.setForeground(new Color(0, 0, 0));
		lblPriDeviceId.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPriDeviceId.setBackground(Color.LIGHT_GRAY);
		frmMobileAutomationTray.getContentPane().add(lblPriDeviceId);






		resetAll = new Button("Reset All");
		resetAll.setBackground(new Color(59, 89, 182));
		resetAll.setForeground(Color.WHITE);
		resetAll.setBounds(223, 243, 156, 22);
		resetAll.setEnabled(false);
		resetAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(e.getSource()==resetAll) {
					progressBar.setValue(0);	

					//comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Application Model:", "Scout Apps", "Pre-bundled Apps"}));
					comboBox_2.setRenderer(new MyComboBoxRenderer("Apps Module "));
					comboBox_2.setSelectedIndex(-1);

					comboBox_3.setRenderer(new MyComboBoxRenderer("Applications "));
					comboBox_3.setSelectedIndex(-1);
					comboBox_3.setEnabled(false);

					//comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Applications:"}));



					button.setEnabled(false);
					comboBox_2.setEnabled(true);

					logwindow.setText("");
					textField_1.setText("");
					textField_4.setText("");



				}
				resetAll.setEnabled(false);

			}

		});
		resetAll.setForeground(Color.BLACK);
		resetAll.setFont(new Font("Dialog", Font.BOLD, 12));
		frmMobileAutomationTray.getContentPane().add(resetAll);









		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(new Color(100, 149, 237));
		menuBar.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.setBackground(Color.CYAN);
		frmMobileAutomationTray.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 12));
		mnNewMenu.setBackground(new Color(238, 130, 238));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);

			JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setHorizontalAlignment(SwingConstants.LEFT);
		mnNewMenu.add(mntmNew);

		JMenuItem mntmOpenFile = new JMenuItem("Open File");
		mnNewMenu.add(mntmOpenFile);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnNewMenu.add(mntmClose);
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int value=JOptionPane.showConfirmDialog (null, "<html><b>Do you want to Exit?</b></html>","WARNING",dialogButton);
				if(value==0) {
					
					System.exit(0);
				}				
			}
		});


		mnNewMenu.add(mntmExit);
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setFont(new Font("Dialog", Font.BOLD, 12));
		mnEdit.setForeground(new Color(0, 0, 0));
		menuBar.add(mnEdit);
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Dialog", Font.BOLD, 12));
		mnHelp.setForeground(new Color(0, 0, 0));
		menuBar.add(mnHelp);
		JMenuItem mntmSmatHelp = new JMenuItem("SMAT Help");
		mnHelp.add(mntmSmatHelp);
		mntmSmatHelp.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {

				SmatHelp.main(null);


			}
		});


		JMenuItem mntmAbout = new JMenuItem("About SMAT");
		mnHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				AboutSMAT_Frame.main(null);

			}
		});
	}

	private static void deviceDetect() throws FileNotFoundException, IOException, ParseException	
	{


		button_2 = new Button("ADB Port Detection");
		button_2.setFont(new Font("Dialog", Font.BOLD, 12));
		button_2.setBackground(new Color(59, 89, 182));
		button_2.setForeground(Color.WHITE);
		button_2.setBounds(43, 67, 122, 22);

		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(final ActionEvent e) {

				try {
					executeTC();

				} catch (IOException | ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}


				button_2.setEnabled(false);

				try {
					DeviceSwapModelNum();



				} catch (IOException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				// DisappearingMessage.main(null, "Please wait while detecting the device...");
				BlockingDialog.main(null, "Detecting Device ...",Color.BLUE);

				//-------------------------------timer ----------------------



				//---------------------------------------------------


				AndroidDebugBridge.init(false);
				debugBridge = AndroidDebugBridge.createBridge("src/test/resources/ADBDRIVER/adb.exe", true);


				if (debugBridge == null) {
					System.err.println("Invalid ADB location.");
					System.exit(1);
				}

				AndroidDebugBridge.addDeviceChangeListener(new IDeviceChangeListener() {


					public void deviceChanged(IDevice device, int arg1) {

					}


					public void deviceConnected(IDevice device) {


						try {


							final String baseBandVersion=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
							final String DeviceModel=BaseUtil.fetchDeviceProperty("adb shell getprop ro.product.model");
							final String DeviceTypeForXP8=BaseUtil.fetchDeviceProperty("adb shell getprop ro.product.internaledition");
							final String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");
							//JsonFileReaderAndWriter.deviceModelWriter(DeviceModel);

							if(!button_2.isEnabled()) 
							{
								// DisappearingMessage.dispose();
								BlockingDialog.blocker.setVisible(false);	
								AndroidDebugBridge.terminate();
							}

							button_2.setBackground(Color.GREEN);

							if(i==0) {

								//This code is for primary device ID
								JsonFileReaderAndWriter.primaryDevIdWriter(device.getSerialNumber()
										,device.getName(),
										device.getPropertyCacheOrSync("gsm.version.baseband"),
										device.getPropertyCacheOrSync("ro.build.id"));
								deviceId.setText(" "+device.getSerialNumber());
								deviceId.setForeground(Color.BLACK);
								deviceId.setFont(new Font("Dialog", Font.BOLD, 12));
								deviceId.setBackground(Color.GREEN);
								primaryDevBuildNum.setText(JsonFileReaderAndWriter.primaryDevFirmwareReader());


							}
							else if (i==1) {
								//This code is for Reference device ID	
								JsonFileReaderAndWriter.WriteRefDevIDtoJson(device.getSerialNumber(),device.getPropertyCacheOrSync("ro.build.id"));
								RefDev.setText(" "+device.getSerialNumber());
								RefDev.setForeground(Color.BLACK);
								RefDev.setFont(new Font("Dialog", Font.BOLD, 12));
								RefDev.setBackground(Color.GREEN);
								ReferenceDevBuildNum.setText(JsonFileReaderAndWriter.ReadRefDeviceFirmWare());

								//System.out.println("Reference DUT----"+device.getSerialNumber()+","+device.getName());
							}
							i++;




							if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800")) 
							{
								comboBox.setSelectedIndex(1);
								comboBox.setEnabled(false);

								if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10"))
								{
									comboBox_1.setSelectedIndex(1);;
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("SL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26")) {
									comboBox_1.setSelectedIndex(3);
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("BELL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11.")) {

									comboBox_1.setSelectedIndex(4);
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("SPR") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29")) {
									comboBox_1.setSelectedIndex(2);
									comboBox_1.setEnabled(false);
								}	
								else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12")) {

									comboBox_1.setSelectedIndex(5);
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-18")) {
									comboBox_1.setSelectedIndex(6);
									comboBox_1.setEnabled(false);
								}


								comboBox_2.setEnabled(true);	


							}
							else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800")) 
							{


								comboBox.setSelectedIndex(2);
								comboBox.setEnabled(false);


								if((JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT")) || (JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10.")))
								{
									comboBox_1.setSelectedIndex(1);;
									comboBox_1.setEnabled(false);


								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("SL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26.") ) 
								{
									comboBox_1.setSelectedIndex(3);
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("SPR") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-29.")) 
								{
									comboBox_1.setSelectedIndex(2);
									comboBox_1.setEnabled(false);
								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("BEL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-12.")) {
									comboBox_1.setSelectedIndex(4);
									comboBox_1.setEnabled(false);

								}
								comboBox_2.setEnabled(true);

							}

						} catch (NullPointerException | IOException | ParseException  e) {

							//System.out.println("Some problem with adb connect listner");	

						} catch (TimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (AdbCommandRejectedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ShellCommandUnresponsiveException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}


					public void deviceDisconnected(final IDevice device) {



						button.setEnabled(false);
						button_2.setBackground(Color.RED);
						deviceId.setBackground(Color.RED);
						deviceId.setText("");
						comboBox.setEnabled(false);
						comboBox_1.setEnabled(false);
						comboBox_2.setEnabled(false);
						comboBox_3.setEnabled(false);
						button_2.setEnabled(false);


						if(!(button.isEnabled()) || !(button_1.isEnabled())) {

							final Timer timer = new Timer(); 
							timer.schedule( new TimerTask() 
							{ 
								public void run() { 

									BlockingDialog.blocker.setVisible(false);



								} 
							}, 0, 100*(100*1));



							final Timer timer1 = new Timer(); 
							timer1.schedule( new TimerTask() 
							{ 
								public void run() { 

									try {
										String value=JsonFileReaderAndWriter.JSonFileReader();
										if(!value.equals(device.getSerialNumber())){

											timer1.cancel();	

											JOptionPane.showMessageDialog(null, "Device swap detected,please exit the tool and try again");
											System.exit(0);

											//  AndroidDebugBridge.terminate();



										}

									} catch (IOException | ParseException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}


								} 
							}, 0, 300*(10*1));
							//	BlockingDialog.main(null, "Device disconnected ...",Color.RED);




						}

					}



				});


			}
		});

		panel.add(button_2);

	}







	public static void loginWindow() throws FileNotFoundException, IOException, ParseException
	{
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(390, 138, 404, 191);
		frmMobileAutomationTray.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 404, 191);
		panel_2.add(scrollPane_1);

		logwindow = new JTextArea();
		scrollPane_1.setViewportView(logwindow);
		logwindow.setFont(new Font("Dialog", Font.BOLD, 11));
		logwindow.setForeground(Color.BLACK);
		logwindow.setWrapStyleWord(true);
		logwindow.setEditable(false);

		textField_1 = new JTextField();
		textField_1.setForeground(Color.BLUE);
		textField_1.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(518, 338, 80, 20);
		frmMobileAutomationTray.getContentPane().add(textField_1);

		textField_4 = new JTextField();
		textField_4.setForeground(Color.BLUE);
		textField_4.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(518, 363, 80, 20);
		frmMobileAutomationTray.getContentPane().add(textField_4);

		RefDev = new JTextArea();
		RefDev.setLineWrap(true);
		RefDev.setForeground(Color.WHITE);
		RefDev.setEditable(false);
		RefDev.setBackground(Color.WHITE);
		RefDev.setBounds(721, 16, 73, 22);
		frmMobileAutomationTray.getContentPane().add(RefDev);

		RefDeviceId = new JLabel("Ref Device ID:");
		RefDeviceId.setForeground(Color.BLACK);
		RefDeviceId.setFont(new Font("Dialog", Font.BOLD, 12));
		RefDeviceId.setBackground(Color.LIGHT_GRAY);
		RefDeviceId.setBounds(632, 11, 97, 28);
		frmMobileAutomationTray.getContentPane().add(RefDeviceId);




	}
	
	public static void primaryDeviceBuildNum() {
		
		primaryDevBuildNum = new JTextArea();
		primaryDevBuildNum.setLineWrap(true);
		primaryDevBuildNum.setForeground(Color.WHITE);
		primaryDevBuildNum.setEditable(false);
		primaryDevBuildNum.setBackground(Color.WHITE);
		primaryDevBuildNum.setBounds(952, 111, 229, 22);
		primaryDevBuildNum.setForeground(Color.BLACK);
		primaryDevBuildNum.setFont(new Font("Dialog", Font.BOLD, 12));
		
		frmMobileAutomationTray.getContentPane().add(primaryDevBuildNum);
		
		JLabel lblPrimarydutbuild = new JLabel("Primary Device Build #");
		lblPrimarydutbuild.setForeground(Color.BLACK);
		lblPrimarydutbuild.setFont(new Font("Dialog", Font.BOLD, 12));
		lblPrimarydutbuild.setBackground(Color.LIGHT_GRAY);
		lblPrimarydutbuild.setBounds(820, 108, 137, 28);
		frmMobileAutomationTray.getContentPane().add(lblPrimarydutbuild);
		
	}
	
	public static void ReferenceDeviceBuildNum() {
		
		ReferenceDevBuildNum = new JTextArea();
		ReferenceDevBuildNum.setLineWrap(true);
		ReferenceDevBuildNum.setForeground(Color.WHITE);
		ReferenceDevBuildNum.setEditable(false);
		ReferenceDevBuildNum.setBackground(Color.WHITE);
		ReferenceDevBuildNum.setBounds(952, 16, 229, 22);
		ReferenceDevBuildNum.setForeground(Color.BLACK);
		ReferenceDevBuildNum.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		frmMobileAutomationTray.getContentPane().add(ReferenceDevBuildNum);
		
		JLabel lblReferenceDeviceBuild = new JLabel("Reference Device Build #");
		lblReferenceDeviceBuild.setForeground(Color.BLACK);
		lblReferenceDeviceBuild.setFont(new Font("Dialog", Font.BOLD, 12));
		lblReferenceDeviceBuild.setBackground(Color.LIGHT_GRAY);
		lblReferenceDeviceBuild.setBounds(804, 13, 153, 28);
		frmMobileAutomationTray.getContentPane().add(lblReferenceDeviceBuild);
		
		
	}

	public static void deviceSwapBtn() {

		final Button deviceSwapBtn = new Button("Device Swap Button");
		deviceSwapBtn.setBackground(new Color(59, 89, 182));
		deviceSwapBtn.setForeground(Color.WHITE);
		deviceSwapBtn.setFont(new Font("Dialog", Font.BOLD, 12));
		deviceSwapBtn.setBounds(988, 65, 151, 22);
		frmMobileAutomationTray.getContentPane().add(deviceSwapBtn);
		
		deviceSwapBtn.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==deviceSwapBtn) {
					
					try {
						String priID=JsonFileReaderAndWriter.primaryDevIdReader();
						String secId=JsonFileReaderAndWriter.ReadRefDeviceId();
						System.out.println("Before" +priID +"," +secId);
						String temp=priID;
						priID=secId;
						secId=temp;
						System.out.println("Afetr" +priID +"," +secId);
						
						//JsonFileReaderAndWriter.OnlyPrimaryDeviceIdWriter(priID);
						
						
					} catch (IOException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
				}
				
				
			}
		});

	}

	public static void ProgressBard() {

		progressBar = new JProgressBar(0,100);
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setBounds(390, 72, 404, 28);
		frmMobileAutomationTray.getContentPane().add(progressBar);

	}



	//
	public static void startButton() throws FileNotFoundException, IOException, ParseException {

		button = new Button("Start");
		button.setBackground(new Color(59, 89, 182));
		
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.setBounds(10, 10, 70, 22);
		panel_6.add(button);
		button.setEnabled(false);
		//	executeTC();
	}
	public static  void executeTC() throws FileNotFoundException, IOException, ParseException {

		try {
			

			if(JsonFileReaderAndWriter.primaryDevModelReader().contains("xp5800")) 
			{
				
				button.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {

						String items_Apps = String.valueOf(comboBox_3.getSelectedItem());
						switch(items_Apps) {


						case "BELLSanityTest":

							Thread	BELLSanity =new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"BELLSanityAdb.txt \"");
									} catch (IOException e) {
										e.printStackTrace();
									}

									try {
										TestNG runner=new TestNG();
										List<String> suitefiles=new ArrayList<String>();
										suitefiles.add("src/test/resources/drivers/XP5_BELL_Sanity.xml");

										System.out.println("XP5 :");
										runner.setTestSuites(suitefiles);
										runner.run();
									}
									catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							});
							BELLSanity.start();

							break;

						case "Safeguard":

							safeGuardTestCases =new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SafeGuard_AdbLog.txt \"");
									} catch (IOException e) {
										e.printStackTrace();
									}

									try {
										TestNG runner=new TestNG();
										List<String> suitefiles=new ArrayList<String>();
										suitefiles.add("src/test/resources/drivers/SafeGuardTest_ATT.xml");
										System.out.println("XP5 :");
										runner.setTestSuites(suitefiles);
										runner.run();
									}
									catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							});
							safeGuardTestCases.start();

							break;
						case "SonimCare":

							sonimCareTc=new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SonimCare_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/SonimCareTest_ATT.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							sonimCareTc.start();
							break;
						case "SonimWarranty":
							SonimWarrantyTc=new Thread(new Runnable() {
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SonimWarranty_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/SonimWarrantyRegTest_ATT.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SonimWarrantyTc.start();
							break;
						case "Programmable Key":	    
							ProgrammableKeyTc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"ProgrammableKey_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/ProgrammableKeyTest_ATT.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							ProgrammableKeyTc.start();
							break;

						case "App Updater":

							AppUpdaterTc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"AppUpdater_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/AppUpdaterTest_ATT.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							AppUpdaterTc.start();
							break;

						case "Contact Transfer":
							ContactTransferTc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"ContactTransfer_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/ContactTransferTest_ATT.xml");

									runner.setTestSuites(suitefiles);
									runner.run(); 

								}
							});
							ContactTransferTc.start();
							break;

						case "SanityTest":
							Sanitytest=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SanityTest_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/Sanity_ATT.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Sanitytest.start();
							break;

						case "Calc":
							Calc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"Calc_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/CalculatorTest.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Calc.start();
							break;
						case "SoundRecorder":
							SoundRec=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SoundRec_Adb.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/SoundRecorderTest.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SoundRec.start();
							break;

						case "FM":
							FM=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"FM_Adb.txt \"");


									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/FMTest.xml");
									System.out.println("XP5 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							FM.start();
							break;

						case "SelectAll":
							SelectAll=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SelectAll_Adb.txt \"");

										//Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SanityTest_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}


									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/MasterTestSuite.xml");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SelectAll.start();
							break;

						case "ScoutFunctionalTest":
							Thread FTScoutSanity=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"FTSanity.txt \"");


									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/FTSanity.xml");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							FTScoutSanity.start();
							break;		           


						}
						button_1.setEnabled(true);
						button.setEnabled(false); 
						comboBox_2.setEnabled(false);
						comboBox_3.setEnabled(false);
						button_6.setEnabled(true);
						button_7.setEnabled(true);

					}
				});

			}

			else if (JsonFileReaderAndWriter.primaryDevModelReader().contains("xp8800"))
			{

				button.addActionListener(new ActionListener() {
					public void actionPerformed(final ActionEvent e) {
						String items_Apps = String.valueOf(comboBox_3.getSelectedItem());

						switch(items_Apps) {

						case "Dailer":
							Thread Dailer=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"DailerAdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Dailer.xml");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Dailer.start();
							break;

						case "ContactTransfer":
							Thread CT=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbCT.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_ContactTransfer.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							CT.start();
							break;

						case "Browser":
							Thread Browser=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbBrowser.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Browser.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Browser.start();
							break;

						case "SoundRecorder":
							Thread SoundRec=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_SoundRec_Adb.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_ATT_SoundRecorderTest.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SoundRec.start();
							break;

						case"Calc":
							Thread Calc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_Calc_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_ATT_CalculatorTest.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Calc.start();
							break; 

						case "SanityTest":
							Thread Sanitytest=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_SanityTest_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_Sanity_ATT.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							Sanitytest.start();
							break;   

						case "FM":
							Thread FM=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_FM_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_FM.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							FM.start();
							break;       

						case "SonimCare":
							Thread sonimCareTc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_SonimCare_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_ATT_SonimCareTest.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							sonimCareTc.start();		          
							break; 
						case"Programmable Key":	    
							Thread ProgrammableKeyTc=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_ProgrammableKey_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_ATT_ProgrammableKeyTest.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							ProgrammableKeyTc.start();

							break;
						case "SelectAll":
							Thread SelectAll=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8_AllAppAdbLog.txt \"");

										//Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SanityTest_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}


									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_AllAPP.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SelectAll.start();
							break;	


						case "SonimWarranty":
							Thread SW=new Thread(new Runnable() {

								@Override
								public void run() {
									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbSW.txt \"");

										//Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"SanityTest_AdbLog.txt \"");
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}


									TestNG runner=new TestNG();
									List<String> suitefiles=new ArrayList<String>();
									suitefiles.add("src/test/resources/drivers/XP8_SonimWarranty.xml");
									System.out.println("XP8 :");
									runner.setTestSuites(suitefiles);
									runner.run(); 
								}
							});
							SW.start();
							break;		               

						case "Safeguard":
							safeGuardTestCases =new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbSafeGuard.txt \"");
									} catch (IOException e) {
										e.printStackTrace();
									}

									try {
										TestNG runner=new TestNG();
										List<String> suitefiles=new ArrayList<String>();
										suitefiles.add("src/test/resources/drivers/XP8_SafeGuard.xml");
										System.out.println("XP8 :");
										runner.setTestSuites(suitefiles);
										runner.run();
									}
									catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							});
							safeGuardTestCases.start();
							break;      
						case "SPRSanityTest":
							Thread SprintSanity =new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbLog_SPR.txt \"");
									} catch (IOException e) {
										e.printStackTrace();
									}

									try {
										TestNG runner=new TestNG();
										List<String> suitefiles=new ArrayList<String>();
										suitefiles.add("src/test/resources/drivers/XP8_Sanity_SPR.xml");
										System.out.println("XP8 :");
										runner.setTestSuites(suitefiles);
										runner.run();
									}
									catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							});
							SprintSanity.start();
							break; 

						case "BELSanityTest":
							Thread	BellTestCases =new Thread(new Runnable() {
								public void run() {

									try {
										Runtime.getRuntime().exec("cmd /C \"adb logcat -v time>src/test/resources/adbLogs/"+"XP8AdbLog_BEL.txt \"");
									} catch (IOException e) {
										e.printStackTrace();
									}

									try {
										TestNG runner=new TestNG();
										List<String> suitefiles=new ArrayList<String>();
										suitefiles.add("src/test/resources/drivers/XP8_Sanity_BEL.xml");
										System.out.println("XP8 :");
										runner.setTestSuites(suitefiles);
										runner.run();
									}
									catch (NumberFormatException e) {
										e.printStackTrace();
									}

								}
							});
							BellTestCases.start();
							break;      	        


						}

						button_1.setEnabled(true);
						button_6.setEnabled(true);
						button.setEnabled(false); 
						comboBox_2.setEnabled(false);
						comboBox_3.setEnabled(false);
						button_6.setEnabled(true);




					}	
				});	
			}

		}catch(NullPointerException exception)
		{
			// JOptionPane.showMessageDialog(null, "Please connect the device properly!");
		}


	}

	public static void stopButton() {

		button_1 = new Button("Stop");
		button_1.setBackground(new Color(59, 89, 182));
		
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Dialog", Font.BOLD, 12));
		button_1.setEnabled(false);

		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e){


				resetAll.setEnabled(true);
				//	button_1.setEnabled(false);

				//DisappearDialog.main(null, "Please wait...", 8);
				BlockingDialog.main(null, "Please wait...",Color.BLACK);
				try {
					ForceClose.BGProcess();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}



				Thread stopbtn=new Thread(new Runnable() {

					public void run() {



						getDriver().quit();


					}
				});
				stopbtn.start();

			}

		});

		button_1.setBounds(10, 10, 70, 22);
		panel_7.add(button_1);

	}

	public static void combobox2() throws FileNotFoundException, IOException, ParseException {

		comboBox_2 = new JComboBox();
		// UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.blue));

		comboBox_2.setEnabled(false);
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBox_2.setBounds(43, 217, 122, 22);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Scout Apps", "Pre-bundled Apps"}));
		comboBox_2.setRenderer(new MyComboBoxRenderer("Apps Module"));
		comboBox_2.setSelectedIndex(-1);
		comboBox_2.setForeground(Color.BLACK);
		panel.add(comboBox_2);


	}

	public static void DeviceSwapModelNum() throws FileNotFoundException, IOException, ParseException {
		//final String deviceModel=JsonFileReaderAndWriter.deviceModelreader();
		//final String OperatorName=BaseUtil.fetchDeviceProperty("adb shell getprop ro.product.internaledition");
		//final String baseBandVersion=BaseUtil.fetchDeviceProperty("adb shell getprop gsm.version.baseband");
		//final String BuildNumber=BaseUtil.fetchDeviceProperty("adb shell getprop ro.build.id");



		XP5_XP8=new Thread(new Runnable() {
			public void run() {

				comboBox_2.addActionListener(new ActionListener() {

				
					public void actionPerformed(ActionEvent e) {



						JComboBox comboBox_2 = (JComboBox) e.getSource();	
						String items=String.valueOf(comboBox_2.getSelectedItem());

						switch(items) 
						{


						case "Scout Apps":

							JOptionPane.showMessageDialog(null, "You have selected : Scout Apps");
							



							try {
								
								if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("ATT") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-10")) {

									comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"ScoutFunctionalTest","Safeguard", "SonimCare", "SonimWarranty", "Programmable Key","Contact Transfer"}));
									comboBox_3.setRenderer(new MyComboBoxRenderer("Scout Apps"));
									comboBox_3.setSelectedIndex(-1);


								}
								else if (JsonFileReaderAndWriter.primaryDevOperatorReader().contains("SL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-26"))
								{



									comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"ScoutFunctionalTest","Safeguard", "SonimCare", "App Updater", "Contact Transfer"}));
									comboBox_3.setRenderer(new MyComboBoxRenderer("Scout Apps"));
									comboBox_3.setSelectedIndex(-1);


								}
								else {

									comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"ScoutFunctionalTest","Safeguard", "SonimCare", "SonimWarranty", "Programmable Key", "App Updater", "Contact Transfer"}));
									comboBox_3.setRenderer(new MyComboBoxRenderer("Scout Apps"));
									comboBox_3.setSelectedIndex(-1);
									}
								
								
								
							} catch (IOException | ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							break;
							
						case "Pre-bundled Apps":

							JOptionPane.showMessageDialog(null, "You have selected: Pre-bundled Apps");

							


							try {
								if(JsonFileReaderAndWriter.primaryDevOperatorReader().contains("BEL") || JsonFileReaderAndWriter.primaryDevFirmwareReader().contains("-11"))
								{
									comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Calc","SoundRecorder", "FM","BELLSanityTest","SelectAll"}));

									comboBox_3.setRenderer(new MyComboBoxRenderer("Pre-bundled Apps"));
									comboBox_3.setSelectedIndex(-1);

								}
								else {
									comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Calc","SoundRecorder","FM","SanityTest","SelectAll"}));
									comboBox_3.setRenderer(new MyComboBoxRenderer("Pre-bundled Apps"));
									comboBox_3.setSelectedIndex(-1);

								   }
								
								
							} catch (IOException | ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
						comboBox_3.setEnabled(true);
						comboBox_2.setEnabled(false);

					}
				});

			}
		});
		XP5_XP8.start();


	}

	public static void operatorSelection() {
		comboBox_1 = new JComboBox();
		comboBox_1.setEditable(false);
		comboBox_1.setEnabled(false);
		UIManager.put( "ComboBox.disabledForeground", Color.BLACK);
		comboBox_1.setFont(new Font("Dialog", Font.BOLD, 12));
		comboBox.setForeground(Color.black);

		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {" Operator Type", "        AT&T", "        SPRINT", "        SL", "        BELL", "        TELUS", "        ROGERS"}));

		comboBox_1.setUI(new BasicComboBoxUI() { // make the down arrow invisible
			public JButton createArrowButton() {
				return new JButton() {
					public int getWidth() {
						return 0;
					}



				};
			}
		});

		comboBox_1.setBounds(43, 168, 122, 22);
		panel.add(comboBox_1);


	}

	public static void DialogMessage() {

		String msg = "<html>Please follow the Pre-conditions before test execution:<ul> "
				+ "<li>Launch Appium, Configure IP: 127.0.0.1 & Port: 4723 "
				+ "<li>Connect 'Sonim Sterio Headset'"
				+ "<li>SIM Card should be inserted "
				+"<li>SIM-Lock= None, ScreenLock = None & SleepTime = Maximum"
				+"<li>SCOUT: SWR: Unregistered DUT required, AppUpdater : BLE APK will be downloaded</ul></html>";


		JLabel label = new JLabel(msg);
		label.setFont(new Font("serif", Font.PLAIN, 15));
		JOptionPane.showMessageDialog(frmMobileAutomationTray,label,"INFORMATION",JOptionPane.INFORMATION_MESSAGE);

	}

	public static void DialogMessage1() {

		int dialogButton = JOptionPane.YES_NO_OPTION;
		int value;

		value = JOptionPane.showConfirmDialog(null,"<html><b>Do you want to Proceed?</b></html> ", "WARNING", dialogButton);
		if(value==0) {

			button.setEnabled(true);
		}
		else if (value==1) {
			button.setEnabled(false);

		}


	}

	public static void DeviceTypeSelection() {


		comboBox = new JComboBox();
		comboBox.setEditable(false);

		comboBox.setEnabled(false);

		//UIManager.put( "ComboBox.disabledForeground", Color.BLACK);
		comboBox.setBackground(new Color(59, 89, 182));
		comboBox.setForeground(Color.WHITE);

		comboBox.setFont(new Font("Dialog", Font.BOLD, 12));
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"   Device Type", "        XP5800", "        XP8800"}));
		comboBox.setUI(new BasicComboBoxUI() { // make the down arrow invisible
			public JButton createArrowButton() {
				return new JButton() {
					public int getWidth() {
						return 0;
					}

				};
			}
		});
		comboBox.setBounds(43, 119, 122, 22);
		panel.add(comboBox);


	}

	public static void startTime() {

		lblNewLabel = new JLabel("Execution Start Time :");

		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel.setBounds(390, 340, 138, 14);
		frmMobileAutomationTray.getContentPane().add(lblNewLabel);

	}
	public static void stopTime() {

		lblStopTime = new JLabel("Execution End Time :");
		lblStopTime.setFont(new Font("Dialog", Font.BOLD, 12));
		lblStopTime.setBounds(389, 365, 123, 14);
		frmMobileAutomationTray.getContentPane().add(lblStopTime);


	}
	public static void totalTime() {


		panel_8 = new JPanel();
		panel_8.setBounds(613, 362, 130, 22);
		frmMobileAutomationTray.getContentPane().add(panel_8);
		panel_8.setLayout(null);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(0, 0, 130, 20);
		panel_8.add(textField_3);
		textField_3.setFont(new Font("Dialog", Font.BOLD, 13));
		textField_3.setForeground(Color.BLUE);
		textField_3.setEditable(false);
		textField_3.setColumns(10);

		lblTotalExecutionTime = new JLabel("Total Execution Time :");
		lblTotalExecutionTime.setFont(new Font("Dialog", Font.BOLD, 12));
		lblTotalExecutionTime.setBounds(613, 341, 130, 14);
		frmMobileAutomationTray.getContentPane().add(lblTotalExecutionTime);

	}
}

















*/