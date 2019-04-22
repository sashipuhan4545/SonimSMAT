package com.xp5S.util;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Locators {
	
	// LOGIN PAGE.
	
	@FindBy(how = How.XPATH, using="//input[@id='username']")
	public static WebElement email;
	
	@FindBy(how=How.XPATH, using="//input[@id='password']")
	public static WebElement password;
	
	@FindBy(how=How.XPATH, using="//input[@id='submitbutton']")
	public static WebElement submitBtn;
	
	@FindBy(how=How.XPATH, using="//a[@href='/recoverPassword']")
	public static WebElement forgotPassword;
	
	@FindBy(how=How.XPATH, using="//a[@href='/register']")
	public static WebElement signUpBtn;
	
	
	@FindBy(how=How.XPATH, using="//li[contains(text(),'Email format is invalid')]")
	public static WebElement errorText;
	
	@FindBy(how=How.XPATH, using="//h2[@class='form-login-heading']")
	public static WebElement loginPageHeading;
	
	@FindBy(how=How.XPATH, using="//label[@for='username']")
	public static WebElement emailText;
	
	@FindBy(how=How.XPATH, using="//label[@for='password']")
	public static WebElement passwordText;
	
	
	
	
	// DASHBOARD PAGE
	
	@FindBy(how=How.XPATH, using="//a[@href='#']")
	public static WebElement profileIcon;
	
	@FindBy(how=How.XPATH, using="//a[@href='/logout']")
	public static WebElement logoutIcon;
	
	@FindBy(how=How.XPATH, using="//a[@href='/profile/updatePassword']")
	public static WebElement changePasswordIcon;
	
	@FindBy(how=How.XPATH, using="//a[@href='/profile']")
	public static WebElement settingsIcon;
	
	@FindBy(how=How.XPATH, using="//input[@id='old_password']")
	public static WebElement oldPassword;
	
	@FindBy(how=How.XPATH, using="//input[@id='new_password']")
	public static WebElement newPassword;
	
	@FindBy(how=How.XPATH, using="//input[@id='retype_password']")
	public static WebElement reTypePassword;
	
	@FindBy(how=How.XPATH, using="//li[@class='dropdown username']")
	public static WebElement profileNameDashboard;
	
	@FindBy(how=How.XPATH, using="//input[@id='firstname']")
	public static WebElement firstNameProfile;
	
	@FindBy(how=How.XPATH, using="//input[@id='lastname']")
	public static WebElement lastNameProfile;
	
	
	@FindBy(how=How.XPATH, using="//a[@id='header_title']")
	public static WebElement dashBoardPage;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'Enterprises ')]/..//ul//li[contains(text(),'Approval pending')]")
	public static WebElement approvalPendingTextEnterprises;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'Enterprises ')]/..//ul//li[contains(text(),'Approved')]")
	public static WebElement approvedTextEnterprises;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'Enterprises ')]")
	public static WebElement enterprisesText;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'Enterprises ')]/..//p[contains(text(),'Total Enterprises: ')]")
	public static WebElement totalEnterprisesText;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'DEVICES ')]")
	public static WebElement devicesText;	
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'Device Groups ')]")
	public static WebElement deviceGroupsDashboardText;	
	
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'DEVICES ')]/..//p[contains(text(),'Total Devices: ')]")
	public static WebElement totalDevicesText;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'DEVICES ')]/..//ul//li[contains(text(),'Approval pending')]")
	public static WebElement approvalPendingDevices;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'DEVICES ')]/..//ul//li[contains(text(),'Approved')]")
	public static WebElement approvedTextDevices;
	
	@FindBy(how=How.XPATH, using="//h5[contains(text(), 'DEVICES ')]/..//ul//li[contains(text(),'Delete requested')]")
	public static WebElement deleteRequestedDevices;
	
	
	@FindBy(how=How.XPATH, using="//div[@class='text-center']")
	public static WebElement copyrightText;

	
	
	// MODULES

	
	@FindBy(how=How.XPATH, using="//a[@href='/admin/index']")
	public static WebElement admins;
	
	@FindBy(how=How.XPATH, using="//a[@href='/dashboard/index']")
	public static WebElement dashboard;
	
	@FindBy(how=How.XPATH, using="//a[@href='/device/index']")
	public static WebElement devices;
	
	@FindBy(how=How.XPATH, using="//a[@href='/enterprise']")
	public static WebElement enterprises;
	
	@FindBy(how=How.XPATH, using="//a[@href='/app-updates']")
	public static WebElement appUpdates;
	
	@FindBy(how=How.XPATH, using="//a[@href='/event']")
	public static WebElement events;
	
	@FindBy(how=How.XPATH, using="//a[@href='/vendor']")
	public static WebElement vendorsModule;
	
	@FindBy(how=How.XPATH, using="//a[@href='/provisioning']")
	public static WebElement provisioningModule;
	
	
	
	
	
	// VENDORS
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 gm ap animated fadeIn sel']//a//i")
	public static WebElement settingsIconVendor;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 gm ap animated fadeIn']//a//i")
	public static WebElement firstEntrySettingsIconVendor;
	
	@FindBy(how=How.XPATH, using ="//select[@id='nextcic']")
	public static WebElement cicDropdown;
	
	@FindBy(how=How.XPATH, using ="//i[@class='icon-uniE629']")
	public static WebElement backButton;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Name:')]/following-sibling::span")
	public static WebElement vendorName;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Country:')]/following-sibling::span")
	public static WebElement countryNameVendor;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Code:')]/following-sibling::span")
	public static WebElement codeNameVendor;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'MCC:MNC:')]/following-sibling::span")
	public static WebElement mcc_mncVendor;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Email:')]/following-sibling::span")
	public static WebElement emailIDVendor;
	
	
	
	
	
	
	
	
	
	// DEVICES
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add Device']")
	public static WebElement addDevicesIcon;
	
	@FindBy(how=How.XPATH, using ="//select[@id='company']")
	public static WebElement companyNames;
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Bulk Upload']")
	public static WebElement bulkUploadBtn;
	
	@FindBy(how=How.XPATH, using ="//input[@id='fileupload']")
	public static WebElement chooseFileBulkUpload;
	
	@FindBy(how=How.XPATH, using ="//a[@data-toggle='tooltip']")
	public static WebElement backBtnBulkUpload;
	
	@FindBy(how=How.XPATH, using ="//div[@class='ca']//b[1]")
	public static WebElement zeroTextBulkUpload;
	
	@FindBy(how=How.XPATH, using ="//div[@class='ca']//b[2]")
	public static WebElement tenTextBulkUpload;
	
	@FindBy(how=How.XPATH, using ="//input[@id='name']")
	public static WebElement nameTextField;
	
	@FindBy(how=How.XPATH, using ="//input[@id='imei']")
	public static WebElement imeiTextField;	
	
	@FindBy(how=How.XPATH, using ="//select[@id='type']")
	public static WebElement deviceType;	
	
	@FindBy(how=How.XPATH, using ="//select[@id='tz_area']")
	public static WebElement timeZoneArea;	
	
	@FindBy(how=How.XPATH, using ="//select[@id='timezone']")
	public static WebElement timeZone;	
	
	@FindBy(how=How.XPATH, using ="//textarea[@id='policy_desc']")
	public static WebElement description;	
	
	@FindBy(how=How.XPATH, using ="//input[@id='submitbutton']")
	public static WebElement addButton;	
	
//	@FindAll(@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 grid_info']"))
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 prpl']/following-sibling::div[@class='col-md-12 mt']//select")
	public static WebElement pageEntryDropdown;	
	
	@FindBy(how=How.XPATH, using ="//input[@placeholder='Search']")
	public static WebElement searchField;	
	
	@FindBy(how=How.XPATH, using ="//div[@class='modalclose ng-scope']")
	public static WebElement closeIcon;
	
	@FindBy(how=How.XPATH, using ="//div[@class='grid_dname']//span[@class='ng-binding']")
	public static WebElement firstEntryDevice;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 grid_info']")
	public static WebElement firstEntryDeviceGroup;
	
	@FindBy(how=How.XPATH, using ="//select[@id='company']")
	public static WebElement deviceEnterpriseDropDown;
	
	@FindBy(how=How.XPATH, using ="//select[@id='company']")
	public static WebElement deviceGroupDropDown;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Name')]/following-sibling::span")
	public static WebElement deviceGroupNameText;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Enterprise')]/following-sibling::span")
	public static WebElement deviceGroupEnterpriseText;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Name')]/following-sibling::span")
	public static WebElement deviceGroupDescriptionText;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'IMEI')]/following-sibling::span")
	public static WebElement imeiNumber;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Name:')]/following-sibling::span")
	public static WebElement deviceName;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Enterprise:')]/following-sibling::span")
	public static WebElement deviceEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Type:')]/following-sibling::span")
	public static WebElement deviceTypeText;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Device Group:')]/following-sibling::span")
	public static WebElement deviceGroupsText;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Status:')]/following-sibling::span")
	public static WebElement deviceStatus;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Description:')]/following-sibling::span")
	public static WebElement deviceDescription;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-5']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIcondeviceGroup;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-5']//a//i[@class='icon-uniE61D']")
	public static WebElement editIcondeviceGroup;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-5']//a//i[@class='icon-uniE605']")
	public static WebElement deleteDevice;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-5']//a//i[@class='icon-uniE64C']")
	public static WebElement approveIconDevice;
	
	@FindBy(how=How.XPATH, using ="//i[@class='icon-check2']")
	public static WebElement rightMarkIconDevices;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-5']//a//i[@class='icon-uniE61D']")
	public static WebElement editIconInPopDevices;	
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 prpl']//div[@class='mw']")
	public static WebElement noDeviceFoundMsg;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 prpl']//div[@class='mw']")
	public static WebElement noEnterpriseFoundMsg;
	
	@FindAll(@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 grid_info']"))
	public static List<WebElement> displayedDevicesGrid;
	
	@FindBy(how=How.XPATH, using ="//table[@class='table table-advance table-hover']//span[contains(text(),'001080001751529')]/..//following-sibling::*//a[@tooltip='Edit']")
	public static WebElement editIconForOneIMEI;
	
	@FindBy(how=How.XPATH, using ="//table[@class='table table-advance table-hover']//span[contains(text(),'001080001751529')]")
	public static WebElement imeiTextListViewTable;
		
	
	// ADMINS
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add Admin']")
	public static WebElement addAdminIcon;	
	
	@FindBy(how=How.XPATH, using ="//select[@id='admintype']")
	public static WebElement adminType;
	
	@FindBy(how=How.XPATH, using ="//select[@id='linkto']")
	public static WebElement linkTo;
	
	@FindBy(how=How.XPATH, using ="//input[@id='firstname']")
	public static WebElement firstName;
	
	@FindBy(how=How.XPATH, using ="//input[@id='lastname']")
	public static WebElement lastName;
	
	@FindBy(how=How.XPATH, using ="//input[@id='email']")
	public static WebElement emailfield;
	
	@FindBy(how=How.XPATH, using ="//input[@id='msisdn']")
	public static WebElement phoneNumber;
	
	@FindBy(how=How.XPATH, using ="//select[@id='country']")
	public static WebElement country;
	
	@FindBy(how=How.XPATH, using ="//select[@id='language']")
	public static WebElement language;
	
	@FindBy(how=How.XPATH, using ="//select[@id='tz_area']")
	public static WebElement timeZoneAreaAdmin;
	
	@FindBy(how=How.XPATH, using ="//select[@id='timezone']")
	public static WebElement timeZoneAdmin;
	
	@FindBy(how=How.XPATH, using ="//input[@id='submitbutton']")
	public static WebElement addBtnAdmin;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 col-sm-6 mb br']//li")
	public static WebElement fieldValidationError;
	
	@FindBy(how=How.XPATH, using ="//h5[@class='col-lg-6 col-md-6']/text()")
	public static WebElement newAdminTitle;
	
	
	@FindBy(how=How.XPATH, using ="//h5[@class='col-lg-3 col-md-3 ng-binding']")
	public static WebElement adminsPage;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Name:')]/following-sibling::span")
	public static WebElement nameTextAdmins;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Type:')]/following-sibling::span")
	public static WebElement typeTextAdmins;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Phone:')]/following-sibling::span")
	public static WebElement phoneTextAdmins;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Email:')]/following-sibling::span")
	public static WebElement emailTextAdmins;
	
	
	//
	
	
	// ENTERPRISES
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add Enterprise']")
	public static WebElement addEnterprisesbtn;
	
	@FindBy(how=How.XPATH, using ="//select[@id='vendor']")
	public static WebElement vendor;
	
	@FindBy(how=How.XPATH, using ="//input[@id='com_name']")
	public static WebElement compNameEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='com_email']")
	public static WebElement compEmailEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='weburl']")
	public static WebElement webURLEnterprises;
	
	@FindBy(how=How.XPATH, using ="//textarea[@id='address']")
	public static WebElement addressEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='city']")
	public static WebElement cityEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='postcode']")
	public static WebElement pinCodeEnterprises;
	
	@FindBy(how=How.XPATH, using ="//select[@id='com_country']")
	public static WebElement countryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@name='com_msisdn']")
	public static WebElement phoneNumberEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='fname']")
	public static WebElement firstNameEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='lname']")
	public static WebElement lastNameEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='email']")
	public static WebElement emailAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@name='msisdn']")
	public static WebElement phoneNumberAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//select[@id='country']")
	public static WebElement countryAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//select[@id='language']")
	public static WebElement languageAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//select[@id='tz_area']")
	public static WebElement timZoneAreaAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//select[@id='timezone']")
	public static WebElement timeZoneAdminEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='1']")
	public static WebElement agriIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='2']")
	public static WebElement constructionIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='3']")
	public static WebElement educationIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='4']")
	public static WebElement healthcareIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='5']")
	public static WebElement hospitalityIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='6']")
	public static WebElement manufacturingIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='12']")
	public static WebElement maritimeIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='7']")
	public static WebElement gas_Oil_IndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='8']")
	public static WebElement publicSafetyIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='9']")
	public static WebElement securityMgmtIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='10']")
	public static WebElement sportsAdvntrIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='11']")
	public static WebElement transportationAIRIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='13']")
	public static WebElement transportationRAILIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='14']")
	public static WebElement transportationROADIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='15']")
	public static WebElement utilitiesIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'industries[]')and @value='15']")
	public static WebElement wasteMgmtIndustryEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'services[]')and @value='3']")
	public static WebElement appSelfUpdateServicesEnterprises;
		
	@FindBy(how=How.XPATH, using ="//input[contains(@name,'services[]')and @value='2']")
	public static WebElement deviceProvisnServicesEnterprises;
	
	@FindBy(how=How.XPATH, using ="//input[@id='submitbutton']")
	public static WebElement createBtnEnterprises;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12']//a[@tooltip='Grid']")
	public static WebElement gridView;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12']//a[@tooltip='List']")
	public static WebElement listView;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Name:')]/following-sibling::span")
	public static WebElement nameEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Vendor:')]/following-sibling::span")
	public static WebElement vendorEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Phone:')]/following-sibling::span")
	public static WebElement phoneEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Status:')]/following-sibling::span")
	public static WebElement statusEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 grid_info']")
	public static WebElement firstEntry;
	
	
	//"//td[@ng-repeat='device in deviceObject']/.."
	
	
	@FindAll(@FindBy(how=How.XPATH, using ="//td[@ng-repeat='device in deviceObject']/.."))
	public static List<WebElement> firstEntryListView;
		
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-uniE605']")
	public static WebElement deletIconInPopUpEnterprise;
	
	@FindBy(how=How.XPATH, using ="//div[@class='modal-content']//div[@class='modal-footer ng-scope']//button//i[@class='icon-check2']")
	public static WebElement deletConfirmIcon;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-uniE61D']")
	public static WebElement editIconInPopEnterprs;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-uniE621']")
	public static WebElement deviceListIconInPopEnterprs;
	
	
	@FindAll(@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 prpl']//div[@class='col-md-3 col-lg-2 col-sm-3 grid_view ng-scope']"))
	public static List<WebElement> enterprsGridView;
	
	@FindAll(@FindBy(how=How.XPATH, using ="//div[@class='animated fadeIn']//table[@class='table table-advance table-hover']//tr[@ng-repeat='deviceObject in filteredEvents']"))
	public static List<WebElement> enterprsListView;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 gm ap animated fadeIn sel']//a//i[@class='icon-uniE61D']")
	public static WebElement editIconEnterprise;
	
	
	
	// ENTERPRISE ADMIN
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Device Group']")
	public static WebElement deviceGroup;
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add Device Group']")
	public static WebElement AddDeviceGroup;
	
	@FindBy(how=How.XPATH, using ="//input[@id='submitbutton']")
	public static WebElement addDeviceGroupButton;
	
	@FindBy(how=How.XPATH, using ="//img[@src='/img/xp5.png']")
	public static WebElement imageDeviceXP5;
	
	@FindBy(how=How.XPATH, using ="//img[@src='/img/xp6.png']")
	public static WebElement imageDeviceXP6;
	
	@FindBy(how=How.XPATH, using ="//img[@src='/img/xp7.png']")
	public static WebElement imageDeviceXP7;
	
	@FindAll(@FindBy(how=How.XPATH, using ="//img[@src='/img/xp5.png']"))
	public static List<WebElement> allimageDeviceXP5;
	
	@FindBy(how=How.XPATH, using ="//input[@name='name']")
	public static WebElement nameDeviceGroup;
		
	@FindBy(how=How.XPATH, using ="//textarea[@id='description']")
	public static WebElement descriptionDeviceGroup;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 gm ap animated fadeIn']//span[contains(text(),'x1')]/../../../../../..//a[@tooltip='Edit']")
	public static WebElement editIcon;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 prpl']//div[@class='mw']")
	public static WebElement noGroupFoundMsg;
	
	@FindBy(how=How.XPATH, using ="//input[@id='deviceapprove']")
	public static WebElement autoApproveChckbox;
	
	
	// EVENTS
	
	@FindBy(how=How.XPATH, using ="//table[@class='table table-advance table-hover']//tr[@ng-repeat='deviceObject in filteredEvents']")
	public static WebElement eventsList;
	
	@FindBy(how=How.XPATH, using ="//select[@class='form-select ngpageselect ng-valid ng-dirty']")
	public static WebElement numberOfEntriesEvents;
	
	@FindBy(how=How.XPATH, using ="//a[@ng-click='selectPage(1)']")
	public static WebElement firstPageEvents;
	
	@FindBy(how=How.XPATH, using ="//a[@ng-click='selectPage(totalPages)")
	public static WebElement LastpageEvents;
	
	@FindBy(how=How.XPATH, using ="//a[@ng-click='selectPage(page - 1)']")
	public static WebElement navigateBackpageEvents;
	
	@FindBy(how=How.XPATH, using ="//a[@ng-click='selectPage(page + 1)']")
	public static WebElement navigateForwardpageEvents;
	
	@FindBy(how=How.XPATH, using ="//table[@class='table table-advance table-hover']//span[contains(text(),'sonimtest')]")
	public static WebElement eventNameTextListViewTable;	
	
	@FindAll(@FindBy(how=How.XPATH, using ="//tr[@ng-if='!error']//td//span"))
	public static List<WebElement> eventResultsListView;
	
	@FindBy(how=How.XPATH, using ="//table[@class='table table-advance table-hover']//tbody//tr[@class='ng-scope']")
	public static WebElement invalidDateRangeText;
	
	@FindBy(how=How.XPATH, using ="//i[@class='icon-search']")
	public static WebElement searchIconEvents;

	@FindBy(how=How.XPATH, using ="//input[@id='fromDate']")
	public static WebElement fromDateSearchField;
	
	
	//ADMINS
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'Name')]/following-sibling::span")
	public static WebElement adminNameText;
	
	
	// APP UPDATES
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add New']")
	public static WebElement AddNewBtnAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='vendor']")
	public static WebElement vendorDropdownAppUpdate;
	
	@FindBy(how=How.XPATH, using ="//input[@id='display-name']")
	public static WebElement packageDisplayNameAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='name']")
	public static WebElement packageNameAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='access']")
	public static WebElement availabilityAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='app-version']")
	public static WebElement appVersionAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='model']")
	public static WebElement deviceModelAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='transport-service']")
	public static WebElement useTransportAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='must-update-by']")
	public static WebElement mustUpdateByAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='apk-upload']")
	public static WebElement choosApkAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='phone-version']")
	public static WebElement deviceSoftwareVersionAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='restart-after-update']")
	public static WebElement restartAfterUpdateAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='silent-upgrade']")
	public static WebElement silentUpgradeAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='network-operator']//optgroup/option[@value='1']")
	public static WebElement networkOperatorFirstValueBellAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//select[@id='network-operator']//optgroup/option[@value='12']")
	public static WebElement networkOperatorSecondValueBellAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Status')]/following-sibling::span")
	public static WebElement statusTextInPopUpAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Availability:')]/following-sibling::span")
	public static WebElement availabilityTextInPopUpAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Device Software version:')]/following-sibling::span")
	public static WebElement deviceSoftVersionTextInPopUpAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'IMEI:')]/following-sibling::span")
	public static WebElement imeiTextBlackListInPopUpAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-uniE621']")
	public static WebElement blackListedIMEIIconAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//div[@class='mw']")
	public static WebElement errorTExtAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Add']")
	public static WebElement addBlackListedIMEIbtnAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Upload']")
	public static WebElement uploadBtnBlackListedIMEIAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@id='fileupload']")
	public static WebElement blackListedIMEIfileUpload;
	
	
	
	// RESOURCES
	@FindBy(how=How.XPATH, using ="//a[@tooltip='Resources']")
	public static WebElement resourcesBtn;
	
	@FindBy(how=How.XPATH, using ="//a[@tooltip='User Guide']")
	public static WebElement userGuideBtn;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//p//span[contains(text(),'APN')]")
	public static WebElement APNTextInPopUnResources;
	
	@FindBy(how=How.XPATH, using ="//input[@id='name']")
	public static WebElement profileNameTextField;
	
	@FindBy(how=How.XPATH, using ="//select[@id='devicegroup']")
	public static WebElement deviceGroupDropDownProvisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultwifi']")
	public static WebElement defaultWifiDropDownProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb']//select[@id='enable_packages']")
	public static WebElement EnablePackagesProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-3']//a//i[@class='icon-visibility']")
	public static WebElement viewIconProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Profile:')]/following-sibling::span")
	public static WebElement profileTextInPopProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Device Group:')]/following-sibling::span")
	public static WebElement deviceGroupTextInPopProvisioning;	
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'IDCSONWAP1')]")
	public static WebElement defaultWifiTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'sonimAirtel')][1]")
	public static WebElement apnNameTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//a//i[@class='icon-add-circle']")
	public static WebElement addAPNbtninPonUpProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='apn']")
	public static WebElement apnEnterTextFieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='mcc']")
	public static WebElement mccEnterTextFieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='mnc']")
	public static WebElement mncEnterTextFieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='url']")
	public static WebElement urlEnterTextFieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//a[@class='btn btn-info']")
	public static WebElement addMoreBtnProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[0][name]']")
	public static WebElement bookmarkName_1_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[0][url]']")
	public static WebElement bookmarkURL_1_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[1][name]']")
	public static WebElement bookmarkName_2_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[1][url]']")
	public static WebElement bookmarkURL_2_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='vcard']")
	public static WebElement vCardChooseBtn_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='type']")
	public static WebElement downloadTypWallpaper_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='extension']")
	public static WebElement fileExtension_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//a//i[@class='icon-upload']")
	public static WebElement uploadIconinPonUpProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='server']")
	public static WebElement serverTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='user']")
	public static WebElement userTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='password']")
	public static WebElement passwordTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='ssid']")
	public static WebElement ssidTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='wepkey']")
	public static WebElement wifiPasswordTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-3']//a//i[@class='icon-uniE61D']")
	public static WebElement editIconInPopUp_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultapn']")
	public static WebElement defaultAPNdropDownProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-3']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconInPopUp_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'sonimAirtel')]")
	public static WebElement apnSuggestedProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-5 col-md-offset-5']//a//i[@class='icon-uniE605']")
	public static WebElement deleteiconResourcePoUp_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-10 modal-data mb']//a//i[@class='icon-visibility']")
	public static WebElement viewIconInPopUpResource_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-3']//a//i[@class='icon-qrcode']")
	public static WebElement qrCodeInPopUpResource_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//a[@data-original-title='Offline QR Code ']")
	public static WebElement offlineQRCodeBtn_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//a[@data-original-title='Print Offline QR Code ']")
	public static WebElement printOfflineQRCodeBtn_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//a[@class='button-3d btn-theme-header']//i[@class='icon-print']")
	public static WebElement printQRCodeBtn_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='modal-body ng-binding ng-scope']")
	public static WebElement permissionToDeleteResource_Provisioning;
	
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestBookmark')]")
	public static WebElement bookmarkSuggested_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[@value='213']")
	public static WebElement first_bookmarkSuggested_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'AutoTestBookmark')][1]")
	public static WebElement bookmarkTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All Bookmarks:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][1]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement firstbookmarkText_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All Bookmarks:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][2]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement secondbookmarkText_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-5 col-md-offset-4']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconBookMarkProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'sonimBookmarkOne')]")
	public static WebElement bookmarkOneProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'sonimBookmarkOne')]")
	public static WebElement bookmarkTwoProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-10 col-lg-offset-1 col-md-10 col-md-offset-1 col-sm-10 col-sm-offset-1']//form//div[@class='row'][11]//div[@class='col-lg-6 col-md-6 mb br']//select//option[1]")
	public static WebElement bookmarkSuggestionProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'sonimBookmarkOne')][1]")
	public static WebElement bookmarkTextOneProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'sonimBookmarkTwo')][1]")
	public static WebElement bookmarkTextTwoProvisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All Bookmarks:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][1]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement bookmarkTextFirstProvisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All Bookmarks:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][2]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement bookmarkTextSecondProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb']//select[@id='contacts']")
	public static WebElement ContactSuggested_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'AutoTestContact')][1]")
	public static WebElement contactTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-2 col-md-offset-2']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconContactProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestDownload')]")
	public static WebElement downloadsSuggestedProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultwallpaper']")
	public static WebElement defaultDownloadDropDownProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'AutoTestDownload.jpg')][1]")
	public static WebElement downloadWallpaperTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-4 col-md-offset-3']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconWallPaperProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='file']")
	public static WebElement appFileChooseBtn_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestUpload')]")
	public static WebElement uploadSuggestedProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='row datablock']//div[contains(text(),'AutoTestUpload.jpg')][1]")
	public static WebElement uploadWallpaperTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='admin_packages']")
	public static WebElement adminPackagesProvisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultnotificationtone']")
	public static WebElement defaultNotificationTonedropDownProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All Downloads:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][1]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement adminPackTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-4 col-md-offset-4']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconAdminPackagesProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestSound')]")
	public static WebElement AlarmToneSuggestedProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultalarmtone']")
	public static WebElement defaultAlarmToneDropDownProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='alarmtonevolume']")
	public static WebElement defaultAlarmtoneVolumeProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-2 col-md-offset-3']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconSoundProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestSound')]")
	public static WebElement ringToneSuggestedProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='ringtonevolume']")
	public static WebElement defaultRingtoneVolumeProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//select[@id='defaultringtone']")
	public static WebElement defaultRingToneDropDownProfilePage_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-6 col-md-6 mb br']//select//option[contains(text(),'AutoTestVPN')]")
	public static WebElement vpnSuggested_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@id='screenlockpin']")
	public static WebElement screenLockPinTextField_Provisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'All VPN:')]/../../following-sibling::div[@class='row'][1]//div[@class='row datablock'][1]//div[contains(text(),'Name:')]/following-sibling::div[@class='col-md-9'][1]")
	public static WebElement vpnNameTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-7 col-md-offset-6']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconVPNProvisioning;
	
	@FindBy(how=How.XPATH, using ="//*[contains(text(),'Default WiFi:')]/../following-sibling::div[@class='col-md-9']")
	public static WebElement defaultWiFiTextProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-6 col-md-offset-5']//a//i[@class='icon-uniE605']")
	public static WebElement deleteIconWiFiProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-8 col-md-offset-4']//a//i[@class='icon-link2']")
	public static WebElement linkIconEnterprisePopUp;
	
	@FindBy(how=How.XPATH, using ="//select[@id='deviceGroup']")
	public static WebElement deviceGroupSuggestion;
	
	@FindBy(how=How.XPATH, using ="//select[@id='deviceGroup']//option[contains(text(),'DoNotDeleteAutomationGroup')]")
	public static WebElement deviceGroupFirstSuggestion;
	
	@FindBy(how=How.XPATH, using ="//select[@id='deviceGroup']//option[contains(text(),'DoNotDeleteThisGroup')]")
	public static WebElement deviceGroupSecondSuggestion;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-md-12 modal-data mb']//p//span[contains(text(),'Device Groups:')]/following-sibling::span")
	public static WebElement deviceGroupTextInPopUpAppUpdates;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[0][name]']")
	public static WebElement bookmarkNameFirstTextfieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[1][name]']")
	public static WebElement bookmarkNameSecondTextfieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[0][url]']")
	public static WebElement bookmarkURLFirstTextfieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//input[@name='appbrowser[1][url]']")
	public static WebElement bookmarkURLSecondTextfieldProvisioning;
	
	@FindBy(how=How.XPATH, using ="//a[@class='btn btn-info']")
	public static WebElement addMorebtnBookmarkProvisioning;
	
	@FindBy(how=How.XPATH, using ="//div[@class='col-lg-12 col-md-12 col-sm-12 mt mb']//div[@class='row datablock'][1]//div[@class='col-md-3']/following-sibling::div")
	public static WebElement defaultWifi_1_TextProvisioning;
	
	
	//
	
	
	
	
	
	
	
	

	
	
	
}


