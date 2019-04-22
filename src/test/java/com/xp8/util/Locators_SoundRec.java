package com.xp8.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Locators_SoundRec {
	
private static AndroidDriver<AndroidElement> aDriver;
	
	public Locators_SoundRec(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;
	}

	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Press Record')]")
	 public static WebElement PressRecordText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Sound Recorder')]")
	 public static WebElement SoundRecorderText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageView[contains(@resource-id,'com.android.systemui:id/dismiss_task')]")
	 public static WebElement DismissIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'CLEAR ALL')]")
	 public static WebElement ClearAllOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'00:00')]")
	 public static WebElement initialTimeText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/recordButton')]")
	 public static WebElement recordBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='1']/android.widget.ImageButton[@index='1']"))
	public static WebElement PauseBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/stopButton')]")
	 public static WebElement stopBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Pause')]")
	 public static WebElement PauseText;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.ImageButton[@index='1']"))
	 public static WebElement PauseBtnList;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recording')]")
	 public static WebElement RecordingText;
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'com.android.soundrecorder:id/listButton')]")
	 public static WebElement listIcon;
	
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='2']"))
	public static WebElement allowBtn;
	

	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static WebElement ListallowBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@resource-id,'com.android.soundrecorder:id/rename_edit_text')]")
	public static WebElement recordingName;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='0']"))
	 public static WebElement DiscardBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	 public static WebElement saveBtn;
	
	@FindBy(how=How.XPATH, using =("//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/list_item_title')]"))
	 public static WebElement saveBtnUnderRecList;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[contains(@resource-id,'com.android.soundrecorder:id/file_list_recording_layout')]"))
	 public static WebElement firstEntry;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[contains(@resource-id,'com.android.soundrecorder:id/file_list_folder_layout')]"))
	 public static WebElement firstEntryFolder;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='0']/android.widget.CheckBox[@index='0']"))
	 public static WebElement CheckBox1;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.CheckBox[@index='0']"))
	 public static WebElement CheckBox2;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='2']/android.widget.CheckBox[@index='0']"))
	 public static WebElement CheckBox3;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='3']/android.widget.CheckBox[@index='0']"))
	 public static WebElement CheckBox4;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='4']/android.widget.CheckBox[@index='0']"))
	 public static WebElement CheckBox5;
	
	@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='0']/android.widget.ImageButton[@index='0']"))
	 public static WebElement backBtn;
	
	//@FindBy(how=How.XPATH, using =("//android.view.ViewGroup[@index='0']/android.widget.ImageButton[@index='0']"))
	// public static WebElement backBtnList;
	
	@FindBy(how=How.XPATH, using ="//android.widget.ImageButton[contains(@resource-id,'android:id/action_mode_close_button')]")
	 public static WebElement CloseBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_delete')]")
	 public static WebElement DeleteIcon;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[@index='0']/android.widget.Button[@index='1']"))
	public static WebElement DeleteBtn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_share')]")
	 public static WebElement ShareIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Spinner[contains(@resource-id,'com.android.soundrecorder:id/selection_spinner')]")
	 public static WebElement SelectDropDown;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Select all')]")
	public static WebElement SelectAllOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Deselect all')]")
	public static WebElement DeSelectAllOpt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'No recording')]")
	public static WebElement NorecordingMsg;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.android.soundrecorder:id/action_edit')]")
	 public static WebElement EditIcon;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'Recording list')]")
	public static WebElement RecordingListText;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SoundRecorder')]")
	 public static WebElement SoundRecoderOpt;
	
	@FindBy(how=How.XPATH, using =("//android.widget.RelativeLayout[@index='1']/android.widget.ImageButton[@index='0']"))
	 public static WebElement SearchOptn;
	
	@FindBy(how=How.XPATH, using ="//android.widget.Button[contains(@text,'ALLOW')]")
	 public static WebElement AllowOpt;
	
	
	
	@FindBy(how=How.XPATH, using ="//android.widget.EditText[contains(@text,'Type your search')]")
	public static WebElement SearchTxt;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SoundRecorder')]")
	 public static WebElement SelectSoundRec;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@resource-id,'com.cyanogenmod.filemanager:id/navigation_view_item_name')]")
	public static WebElement HistorySearch;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'SampleSoundRecord.amr')]")
	public static WebElement SampleSoundRec;
	
	@FindBy(how=How.XPATH, using ="//android.widget.TextView[contains(@text,'The name already exists.')]")
	public static WebElement NameExist;
	
	
	
	
}
