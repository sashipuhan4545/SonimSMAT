package com.aosp.Utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class Locators_Contacts {


	private static AndroidDriver<AndroidElement> aDriver;

	public Locators_Contacts(AndroidDriver<AndroidElement> aDriver) {
		this.aDriver=aDriver;

	}
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Contacts']")
	public static WebElement contactsIcon;

	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.android.systemui:id/app_thumbnail_image']")
	public static WebElement recentApp;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='ADD CONTACT']")
	public static WebElement addContacts;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='IMPORT CONTACTS']")
	public static WebElement importContacts;
	
	@FindBy(how=How.XPATH, using =("//android.widget.LinearLayout[contains(@resource-id,'com.android.contacts:id/search_box_expanded')]/../..//android.widget.EditText[@text='Find contacts']"))
	 public static AndroidElement findContactsoption;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name']")
	public static WebElement nameField;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Phone']")
	public static WebElement phoneField;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Save']")
	public static WebElement saveBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.android.contacts:id/header_title']")
	public static WebElement addedContact;

	@AndroidFindBy(xpath = "//com.android.internal.widget.AccessibleTextView[@text='Add Contact']")
	public static WebElement addContactOpt;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.android.contacts:id/expansion_view']")
	public static WebElement expandFld;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Name prefix']")
	public static WebElement namePrefixFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='First name']")
	public static WebElement firstNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Middle name']")
	public static WebElement middleNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Last name']")
	public static WebElement lastNameFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Name suffix']")
	public static WebElement nameSuffixFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Phonetic last name']")
	public static WebElement phoneticLastName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Phonetic middle name']")
	public static WebElement phoneticMiddleName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Phonetic first name']")
	public static WebElement phoneticFirstName;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Nickname']")
	public static WebElement nickNameFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Company']")
	public static WebElement companyFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Title']")
	public static WebElement titleFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Mobile']")
	public static WebElement numberTypeFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='SIP']")
	public static WebElement sipFld;

	@AndroidFindBy(xpath= "//android.widget.EditText[@text='Email']")
	public static WebElement emailFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='Home']")
	public static WebElement emailTypeFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Address']")
	public static WebElement addressFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='IM']")
	public static WebElement IMFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='AIM']")
	public static WebElement IMTypeFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Website']")
	public static WebElement websiteFld;

	@AndroidFindBy(xpath="//android.widget.EditText[@text='Notes']")
	public static WebElement notesFld;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='More fields']")
	public static WebElement moreFldOpt;

	@AndroidFindBy(xpath="//android.widget.ScrollView[@resource-id='com.android.contacts:id/compact_contact_editor_fragment']")
	public static MobileElement scrollpage;

	@AndroidFindBy(xpath="//android.widget.TextView[@text='No contacts']")
	public static WebElement noContacts;
	
	@AndroidFindBy(xpath="//android.widget.Button[@text='CHANGE PHOTO']")
	public static WebElement changePhotoOpt;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Take photo']")
    public static WebElement takePhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Choose photo']")
    public static WebElement choosePhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.borqs.camera:id/photo_capture']")
    public static WebElement captureOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement imageOkOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Edit']")
    public static WebElement editOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Remove photo']")
    public static WebElement removePhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Take new photo']")
    public static WebElement takeNewPhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Select new photo']")
    public static WebElement selectNewPhotoOpt;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Test']")
    public static WebElement editNameFldMinContacts;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Sonim']")
    public static WebElement editNameAllFldContacs;
    
    @AndroidFindBy(xpath="//android.widget.RelativeLayout[contains(@content-desc,'Call Mobile')]")
    public static WebElement callOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Dialing']")
    public static WebElement dailingOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Send message']")
    public static WebElement sendMessageOpt;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Type message']")
    public static WebElement typeMessageOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Send']")
    public static WebElement sendOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Now']")
    public static WebElement messageNowOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add to favorites']")
    public static WebElement addToFavorites;
    
    @AndroidFindBy(xpath="//android.view.ViewGroup[@index='1']/android.widget.ImageView[@index='2']")
    public static WebElement favoriteContact;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Remove from favorites']")
    public static WebElement removeFavorites;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Share']")
    public static WebElement shareOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Messaging']")
    public static WebElement shareViaMessage;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Bluetooth']")
    public static WebElement shareViaBluetooth;
    
    @AndroidFindBy(xpath="//android.widget.ScrollView[@resource-id='com.android.mms:id/attachment_editor_scroll_view']")
    public static WebElement shareMessagePage;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Send Contact']")
    public static AndroidElement sendContactOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add to contact']")
    public static WebElement addToContactOptDailer;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add to contact']")
    public static WebElement addToContactOptMenuDailer;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Create new contact']")
    public static WebElement createNewContact;

    @AndroidFindBy(xpath="//android.widget.MultiAutoCompleteTextView[@text='Type name/number']")
    public static WebElement typeNameOrNumber;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to SIM']")
    public static WebElement copyToSIMOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Copy to Phone']")
    public static WebElement copyToPhoneOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.contacts:id/search_count_text']")
    public static WebElement matchedContactsFld;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Set ringtone']")
    public static WebElement setRingtoneOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='All calls to voicemail']")
    public static WebElement allCallsToVoicemailOpt;
    
    @AndroidFindBy(xpath="//android.widget.CheckBox[@resource-id='android:id/checkbox']")
    public static WebElement allcallsToVoicemailCheckbox;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Link']")
    public static AndroidElement linkOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='96865 02807']")
    public static WebElement linkedContact;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Unlink']")
    public static AndroidElement unlinkOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='UNLINK']")
    public static WebElement unlinkBtn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Blocked numbers']")
    public static WebElement blockedNumbersOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Add a number']")
    public static WebElement addNumberToBlock;
    
    @AndroidFindBy(xpath="//android.widget.EditText[@text='Phone number']")
    public static WebElement phoneNumberToBlock;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='BLOCK']")
    public static WebElement blockBtn;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='(968) 656-5656']")
    public static WebElement blockedNumber;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Unblock']")
    public static WebElement unblockOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='UNBLOCK']")
    public static WebElement unblockBtn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select']")
    public static WebElement selectOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Select All']")
    public static WebElement selectAllOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@index='1']")
    public static WebElement countField;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Deselect all']")
    public static WebElement deselectAllOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Settings']")
    public static WebElement settingsOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Sort by']")
    public static WebElement sortByOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Name format']")
    public static WebElement nameFormat;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name']")
    public static WebElement sortFirstName;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name']")
    public static WebElement sortLastName;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='First name first']")
    public static WebElement firstNameFirst;
    
    @AndroidFindBy(xpath="//android.widget.CheckedTextView[@text='Last name first']")
    public static WebElement lastNameFirst;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Abc Auto']")
    public static WebElement firstNamefirstContact;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Test A']")
    public static WebElement sortLastNameContact;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Auto, Abc']")
    public static WebElement lastNameFirstContact;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='DELETE']")
    public static WebElement deleteBtn;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Delete']")
    public static WebElement deleteOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Import/export']")
    public static WebElement importExportOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Import from .vcf file']")
    public static WebElement importFromVcfOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Import from SIM card']")
    public static WebElement importFromSIMOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Export to SIM card']")
    public static WebElement exportToSIMopt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Export to .vcf file']")
    public static WebElement exportToVcfOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Share all contacts']")
    public static WebElement shareAllContactsOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='No vCard file found in storage.']")
    public static WebElement noVcardPopUp;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='OK']")
    public static WebElement okOpt;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@resource-id='com.android.dialer:id/cliv_data_view']")
    public static WebElement existingContactInDailer;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='KEEP EDITING']")
    public static WebElement keepEditingOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='DISCARD']")
    public static WebElement discardOpt;
    
    @AndroidFindBy(xpath="//android.widget.Button[@text='CANCEL']")
    public static WebElement cancelOpt;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Cancel']")
    public static WebElement cancelOptInAddContact;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='Discard your changes and quit editing?']")
    public static WebElement discardPromptMessage;
    
    @AndroidFindBy(xpath="//android.widget.TextView[@text='No vCard file found in storage.']")
    public static WebElement noVCFPopUpOpt;
    
    @AndroidFindBy(xpath="//android.widget.RelativeLayout[@index='0']")
    public static WebElement SIMContactToImport;
    
    @AndroidFindBy(xpath="//com.android.internal.widget.AccessibleTextView[@text='Done']")
    public static WebElement doneOpt;
    
    @AndroidFindBy(uiAutomator="new UiSelector().className(\"android.widget.MultiAutoCompleteTextView\").resourceId(\"com.android.mms:id/recipients_editor\")")
	public static AndroidElement toField_NewMessage;
}
