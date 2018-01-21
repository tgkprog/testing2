package org.s2n.ddt.appHelpers1.serialNo;



/*
 * @Author Suprabha Behera
 * This file would contain all the error code constants related
 * and specified by Business.
 */
public final class GRXBusinessConstants {

	/* Constructor */
	private GRXBusinessConstants(){}


	/****** Error Codes start ******/

	/* Business Error Codes */
	public static final String BIZ_001 = "BIZ_001";
	public static final String BIZ_002 = "BIZ_002";

	/* SQL Error Codes */
	public static final String SQL_001 = "SQL_001";
	public static final String SQL_002 = "SQL_002";

	/* Adapter Error Codes */
	public static final String ADP_001 = "ADP_001";
	public static final String ADP_002 = "ADP_002";


	public static final int ZERO = 0;

	/* Cache Error Codes */
	//"Exception while reading object : get : GenevaCacheManager"
	public static final String GRXCACHE0001 = "GRXCACHE0001";
	//"The VO object should be null"
	public static final String GRXCACHE0002 = "GRXCACHE0002";
	//"The VO object is not null"
	public static final String GRXCACHE0003 = "GRXCACHE0003";
	//"Exception in initialization : get GenevaCacheManager : ClassNotFound"
	public static final String GRXCACHE0004 = "GRXCACHE0004";
	//"Exception in initialization : get GenevaCacheManager : Illegal Argument"
	public static final String GRXCACHE0005 = "GRXCACHE0005";
	//	"Exception in initialization : get GenevaCacheManager : Io"
	public static final String GRXCACHE0006 = "GRXCACHE0006";
	//"Exception in initialization : put GenevaCacheManager"
	public static final String GRXCACHE0007 = "GRXCACHE0007";
	//"Exception in intialization : GenevaCacheManager Constructor"
	public static final String GRXCACHE0008 = "GRXCACHE0008";
	//Exception in put session GRXAccessObject
	public static final String GENCACHE0009 = "GENCACHE0009";
	//Exception in get session GRXAccessObject
	public static final String GENCACHE0010 = "GENCACHE0010";
	//Exception while fetching data from Cache
	public static final String GRXCACHE0011 = "GRXCACHE0011";
	// Exception occured since the passed object is not Serializable
	public static final String GRXCACHE0012 = "GRXCACHE0012";
	// Exception occured since the passed object is not Serializable
	public static final String GRXCACHE0013 = "GRXCACHE0013";
	// Exception occured since the passed object is not Serializable
	public static final String GRXCACHE0014 = "GRXCACHE0014";
	/*Constants for Queue*/
	//"Exception in initialization : GRXQUEUEGenevaImpl constructor"
	public static final String GRXQUEUE0001 = "GRXQUEUE0001";

	/*Constants for Asset*/
	//"Exception in initialization : GRXAssetGenevaImpl constructor"
	public static final String GRXASSET0004 = "GRXASSET0004";
	//"Cannot find the required file : GRXAssetGenevaImpl:: putFile:: "
	public static final String GRXASSET0005 = "GRXASSET0005";
	//"Exception in GRXAssetGenevaImpl:: putFile"
	public static final String GRXASSET0006 = "GRXASSET0006";
	//"Exception while fetching File from Geneva asset: GRXAssetGenevaImpl:: getFile"
	public static final String GRXASSET0007 = "GRXASSET0007";
	//"Exception in GRXAssetGenevaImpl:: getFile"
	public static final String GRXASSET0008 = "GRXASSET0008";
	//"Exception in GRXAssetGenevaImpl:: remove"
	public static final String GRXASSET0009 = "GRXASSET0009";
	//"Cannot find the required file to download "
	public static final String GRXASSET0010 = "GRXASSET0010";
	//"Exception while closing Input Stream "
	public static final String GRXASSET0011 = "GRXASSET0011";



	/*Constants for Authentication*/

	// if State/Province is blank
	public static final String GRXAUTH001 = "GRX-50021";
	// user Id is blank
	public static final String GRXAUTH002 = "GRX-50018";
	// password is blank
	public static final String GRXAUTH003 = "GRX-50019";
	// ship to is blank
	public static final String GRXAUTH004 = "GRX-50020";
	// Invalid time zone
	public static final String GRXAUTH005 = "GRX-50022";
	// The Ship- To account entered is no longer active
	public static final String GRXAUTH006 = "GRX-50026";
	//Your Session has expired please login again
	public static final String GRXAUTH007 = "GRX-50035";
	//Invalid user name password
	public static final String GRXAUTH008 = "GRX-50036";
	//Shipto is not valid
	public static final String GRXAUTH009 = "GRX-50025";

	//Soldto is blank
	public static final String GRXAUTH010 = "GRX-50023";

	//Time zone is invalid
	public static final String GRXAUTH011 = "GRX-50022";

	//Lang code is invalid
    public static final String GRXAUTH012 = "GRX-50028";

    //canAccessWebService privilege error
    public static final String GRXAUTH013 = "GRX-50027";

    //Authorization failure error
    public static final String GRXAUTH014 = "GRX-50205";

    //Time zone is blank
    public static final String GRXAUTH015 = "GRX-50030";

    //Language code is blank
    public static final String GRXAUTH016 = "GRX-50031";

    //API client certificate not provided or not valid
    public static final String GRXAUTH017 = "GRX-50038";

    //Redirect to a different domain
    public static final String GRXAUTH018 = "GRX-50039";

// The cancellation request cannot be processed due to an existing repair against this unit. Please direct the customer  to Apple to request a refund. 
	public static final String GRX20058 = "GRX-20058";
	
	// Return date is mandatory. Please enter return date in %s format.
	public static final String GRX20004 = "GRX-20004";
	
	// entered date is less than to date
	public static final String GRX50056 = "GRX-50056";
	
	// The device ID you entered is not valid.Please enter a valid serial number,IMEI or MEID.If the issue persists, please direct the customer to Apple to request a refund.
	public static final String GRX30009 = "GRX-30009";
	
	// Serial number lookup is unavailable.
	public static final String GRX40003 = "GRX-40003";
	
	
	public static final String GRX_SNV_002 = "GRX_SNV_002";

	// if Return date is future date
	public static final String GRX20005 = "GRX-20005";
	
	// if Return date cannot be a future date. Please enter the correct dateReturn date cannot be a future date. Please enter the correct date
	public static final String GRX20006 = "GRX-20006";
	
	// The AppleCare Plan for this primary device has already been cancelled.
	public static final String GRX20018 = "GRX-20018";
	
/**The AppleCare Plan was purchased using a different account number. 
	Please login using the account number associated with this order.  
	If the issue persists, please direct the customer to Apple to request a refund. 
	 * 
	 */
	public static final String GRX20019 = "GRX-20019";
	
	/**
	 * The AppleCare Plan was purchased using a different account number.  Please login using the account number associated with this order. 
	 *  If the issue persists, please direct the customer to Apple to request a refund. 
	 */
	public static final String GRX20014 = "GRX-20014";
	
	// Please enter a serial number, IMEI or MEID.
	public static final String GRX20043 = "GRX-20043";
	
	// The AppleCare Cancellation Date provided is outside of the Order Delay Window.
	public static final String GRX20057 = "GRX-20057";
	
	/**
	 * The cancellation request is outside the Cancellation Window and is no longer eligible for a full refund. 
	 * Please direct the customer to Apple to request a pro-rated refund.
	 */
	public static final String GRX20007 = "GRX-20007";
	
	// The purchase order number provided for this AppleCare Plan  does not match Apple’s records
	public static final String GRX20016 = "GRX-20016";
	
	/**
	 * Multiple orders were found for the serial number detered.  Please resubmit with the serial number and the original purchase order number.
	 *  If the issue persists, please direct the customer  to Apple to request a refund.
	 */
	public static final String GRX20011 = "GRX-20011";
	
	/**
	 * No open AppleCare Plan found for this device.  If purchased elsewhere, please have the customer contact Apple.
	 */
	public static final String GRX20012 = "GRX-20012";
	public static final String GRX20013 = "GRX-20013";
	
	// Please enter return date in  MM/DD/YY format
	public static final String GRX50002 = "GRX-50002";
	
	/**
	 * An AppleCare Plan is pdeding for this device. 
	 * Please verify order details in the 360 LookUp and contact AppleCare Connect Support if more information is needed.
	 */
	public static final String GRX20024 = "GRX-20024";
	
	// There is no valid Secondary device associated with the provided Primary device
	public static final String GRX20023 = "GRX-20023";
	public static final String GRX20025 = "GRX-20025";
	
	// There is not an AppleCare Plan associated with this serial number %s.
	public static final String GRX20008 = "GRX-20008";
	public static final String GRX20208 = "GRX-20208";
	public static final String GRX20209 = "GRX-20209";
	public static final String GRX20210 = "GRX-20210";
	public static final String GRX20211 = "GRX-20211";
	
	/**
	 * System exceptions
	 */
	public static final String GRX10001 = "GRX-10001";
	/** Error code used for DB  transaction failure for GRX DB lookup for OrderLookup**/
	public static final String GRX10002 = "GRX-10002";

	public static final String SERIAL_NUMBER_FACTORY_EXCEPTION =  "Entered number is not a serail number check if it is IMEI/MEID number.";
	public static final String INVALID_NUMBER = "Invalid Number";

	public static final String DEFAULT_LOCALE = "en";
	public static final String PMC_READ_RULE_ERROR = "Unable to compile \"PMC.drl\".";

	public static final String GRXZIN001 = "GRX_ZIN_001";
   
	// Invalid Reseller admin settings
	public static final String GRX50200 = "GRX-50200";
	
	// Invalid Manual PO number length
	public static final String GRX50201 = "GRX-50201";
	
	// Invalid prefix length
	public static final String GRX50202 = "GRX-50202";
	
	// Invalid Duplicate PO/Consolidated invoice settings
	public static final String GRX50203 = "GRX-50203";

	
	public static final String GRXUTIL0001 = "GRXUTIL0001";
	public static final String GRXUTIL0002 = "GRXUTIL0002";
	public static final String GRXCOV001 = "GRXCOV001";
	public static final String GRXCOV002 = "GRXCOV002";
	public static final String GRXCOV003 = "GRXCOV003";
	public static final String GRXCOV004 = "GRXCOV004";
	public static final String GRXCOV005 = "GRXCOV005";
	public static final String GRXCOV006 = "GRXCOV006";
	public static final String GRXCODV001 = "GRXCODV001";
	public static final String GRXCODV002 = "GRXCODV002";

	// if address is invalid
	public static final String GRX50010 = "GRX-50010";
	public static final String GRXPMC003 = "GRX_PMC_003";
	
	//  The secondary serial number %s is not linked to the primary serial number %s provided.
	public static final String GRX20009 = "GRX-20009";
	public static final String GRXORD061 = "GRX_ORD_061";
	
	// Applecare Sales Date cannot be a future date.
	public static final String GRX20054 = "GRX-20054";
	
	/**
	 * Email address is required to deliver the electronic Proof of Coverage document to the customer
	 */
	public static final String GRX50048 = "GRX-50048";
	//public static final String GRX50076 = "GRX-50076";
	
	// Sending EMail operation failed.
	public static final String GRX50049 = "GRX-50049";
	
	/**
	 * The purchase order number cannot have special characters.  Please double check your information and retry.
	 */
	public static final String GRX50077 = "GRX-50077";
	
	// The purchase order number entered is invalid. Please double check your information and retry.
	public static final String GRX50079 = "GRX-50079";
	
	// Please enter either a device ID or Purchase order number or  Customer Email address to proceed.
	public static final String GRX50074 = "GRX-50074";
	
	/**
	 * Please enter either a Device ID or Purchase Order Number
	   or Customer Email address as the search criteria
	 */
	public static final String GRX50073 = "GRX-50073";
	
	// Invalid email format.
	public static final String GRX50005 = "GRX-50005";

	// AppleCare is not currently available in your location.
	public static final String GRX30004 = "GRX-30004";
	
	// The device ID you entered is not valid. Please enter a valid serial number, IMEI or MEID. 
	public static final String GRX30020 = "GRX-30020";
	
	/**
	 * This device is not yet registered with Apple.  
	 * Please enter the date the customer purchased the device.
	 */
	public static final String GRX30021 = "GRX-30021";
	
	
	public static final String GRX20500 = "GRX-20500";
	
	/**
	 * Proof Of Coverage language is not valid for the country.
	 *  Please enter a valid Proof of Coverage language for the country.
	 */
	public static final String GRX20037 = "GRX-20037";
	
	// POC Language is mandatory.
	public static final String GRX20038 = "GRX-20038";
	public static final String GRX50161 = "GRX-50161";
	public static final String GRX50162 = "GRX-50162";

	

    // There are no records matching the search criteria.
	public static final String GRX50052 = "GRX-50052";
	
	// There are no records matching the search criteria.
	public static final String GRX50100 = "GRX-50100";
	
	// Please enter the search criteria
	public static final String GRX50053 = "GRX-50053";
	
	// Invalid input data for sold to on/off.
	public static final String GRX50101 = "GRX-50101";
	public static final String GRX50102 = "GRX-50102";
	
	// Please specify minimum 3 characters for searching.
	public static final String GRX50103 = "GRX-50103";
	
	// Please specify sold to code.
	public static final String GRX50104 = "GRX-50104";
	
	// You do not have enough privileges to view reselleraccount profile.
	public static final String GRX50105 = "GRX-50105";
	
	//Invalid sold to code
	public static final String GRX50106 = "GRX-50106";
	
	// You do not have enough privileges to modify install base in Sold to details 
	public static final String GRX50107 = "GRX-50107";
	
	// Please specify minimum 3 characters for searching.
	public static final String GRX50108 = "GRX-50108";
	
	// You do not have enough privileges to modify POC type in ship to details.
	public static final String GRX50109 = "GRX-50109";
	
	/**
	 * You do not have enough privileges to modify agreement bar code in ship to details.
	 */
	public static final String GRX50110 = "GRX-50110";
	
	// Please specify minimum 3 characters for searching.
	public static final String GRX50111 = "GRX-50111";
	
	/**
	 * You do not have access to modify can create/ can cancel/bulk sales for this ship to account.
	 */
	public static final String GRX50112 = "GRX-50112";
	
	// You do not have access to sold to / ship to
	public static final String GRX50113 = "GRX-50113";
	
	// You do not have enough privileges to modify can create/can cancel/bulk order in Sold to details.
	public static final String GRX50114 = "GRX-50114";
	
	// Sales Org Code is mandatory
	public static final String GRX50150 = "GRX-50150";
	
	// Part Number is mandatory
	public static final String GRX50151 = "GRX-50151";
//	Added By Amit @Desc if date of purchase entered is outside the expected range
	public static final String GRX50250 = "GRX-50250";

	// An AppleCare Plan is not available for this device. 
	public static final String GRX30002 = "GRX-30002";
	
	// Please enter valid date
	public static final String GRX50055 = "GRX-50055";

	// Duplicate device ID submitted.  Please review and correct.
	public static final String GRX30019 = "GRX-30019";
	
	
	public static final String GRX20056 = "GRX-20056";
	
	// Applecare Sales date is mandatory. Please enter return date in (0) format.
	public static final String GRX20010 = "GRX-20010";
	
	/**
	 *  The order cannot be processed at this time as there is an issue with the account. 
	 *   Please consult with your management team.
	 */
	public static final String GRX20044 = "GRX-20044";
	
	// The device ID you entered is not valid. Please enter a valid serial number, IMEI or MEID. 
	public static final String GRX40005 = "GRX-40005";
	
	//You do not have the privilege to set the AppleCare Cancellation Date in the past.
	public static final String GRX20064 = "GRX-20064";

	/**
	 * File format is invalid. Please upload a bulk order CSV file 
	 * using the template located on the bulk order page. 
	 */
	public static final String GRX50069 = "GRX-50069";
	
	//Hardware date of purchase cannot be in the future.
	public static final String GRX30005 = "GRX-30005";
	
	// You have not been assigned to a Role that allows you to perform this operation.
	public static final String GRX50001 = "GRX-50001";
	
	// System error, please try again.
	public static final String GRX50072 = "GRX-50072";
	
	// Please upload a bulk order CSV file using the template located on the bulk order page. 
	public static final String GRX50066 = "GRX-50066";
	
	// Please upload the file in the supported CSV format.
	public static final String GRX50067 = "GRX-50067";
	
	//Please upload a bulk order CSV file using the template located on the bulk order page. 
	public static final String GRX50068 = "GRX-50068";
	public static final String GRX50070 = "GRX-50070";
	
	/**
	 * Maximum 1000 records can be placed in single order.  
	 * Please create separate files containing no more than 1000 records and resubmit. 
	 */
	public static final String GRX50071 = "GRX-50071";
	
	//Please enter Apple Care Sales Date in %s format
	public static final String GRX20080 = "GRX-20080";
	
	// Please enter Hardware date of purchase in %s format 
	public static final String GRX20081 = "GRX-20081";
	
	// The Secondary Serial number you entered is not valid. Please enter a valid serial number,
	public static final String GRX20082 = "GRX-20082";
	
	//There are no results for the given search criteria.
	public static final String GRX50082 = "GRX-50082";
	
	/**
	 * You have entered a purchase order number that has already been used.  
	 * Please enter a unique purchase order number
	 */
	public static final String GRX20051 = "GRX-20051";
	
	//You do not have the privilege to perform this activity
	public static final String GRX50051 = "GRX-50051";
	
	// Reseller is not eligible for an install base sale.
	public static final String GRX50206 = "GRX-50206";
	public static final String GRX_PMC_005 = "GRX_PMC_005";
	
	/**
	 * We are experiencing problem in processing your request. 
	 * Please come back later or contact GRX support
	 */
	public static final String GRX16001 = "GRX-16001";
	
	//Address is required to deliver the printed Proof of Coverage document to the customer.
	public static final String GRX50013 = "GRX-50013";
	
	//First name is required. 
	public static final String GRX50011 = "GRX-50011";
	
	//Last name is required. 
	public static final String GRX50012 = "GRX-50012";
	
	// Country field is required.
	public static final String GRX50081 = "GRX-50081";
	
	//The postal code is invalid.
	public static final String GRX20040 = "GRX-20040";
	
	// State/Province is required.
	public static final String GRX50021 = "GRX-50021";
	
	// The state/province code is invalid.
	public static final String GRX50029 = "GRX-50029";
	
	// City is required.
	public static final String GRX50046 = "GRX-50046";
	
	// ZIP/Postal Code is required. 
	public static final String GRX50037 = "GRX-50037";
	
	// Address cannot have special characters. 
	public static final String GRX50015 = "GRX-50015";
	
	//Email address cannot exceed 241 characters.
	public static final String GRX50004 = "GRX-50004";
	
	// Primary phone number is required to deliver the SMS.
	public static final String GRX50083 = "GRX-50083";
	
	// Sending SMS operation failed.
	public static final String GRX50084 = "GRX-50084";
	
	// The address you entered is invalid.
	public static final String GRX50016 = "GRX-50016";
	
	// First Name/Last Name cannot exceed 110 characters
	public static final String GRX50017 = "GRX-50017";
	
	// You do not have the privilege to enter Secondary Device ID.
    public static final String GRX50080 = "GRX-50080";
    
    // Country field is required.
	public static final String GRX80001 = "GRX-80001";
	
	// There are no results for the given search criteria.
	public static final String GRX80002 = "GRX-80002";
	
	// Primary phone number is required to deliver the SMS.
	public static final String GRX80003 = "GRX-80003";
	
	//The city you entered is invalid.
	public static final String GRX50008 = "GRX-50008";
	
	// The postal code you entered is invalid.
	public static final String GRX50047 = "GRX-50047";
	
	// Country you entered is invalid
	public static final String GRX80004 = "GRX-80004";
	
	// PO# is mandatory
	public static final String GRX80006 = "GRX-80006";
	
	public static final String GRX80007 = "GRX-80007";

	public static final String GRX50160 = "GRX-50160";

	/* Constants for Reseller Acnt Mgt */


	// Order delay window should be between 0 and {x}
	public static final String GRX50115 = "GRX-50115";
	// Install Base Window should be between 0 and {x}
	public static final String GRX50116 = "GRX-50116";
	// Cancellation window should be between 0 and {x}
	public static final String GRX50117 = "GRX-50117";
	// Incorrect PO format value
	public static final String GRX50118 = "GRX-50118";
	// Prefix option length should be limited to 5 characters
	public static final String GRX50119 = "GRX-50119";
	// Increment Option is mandatory
	public static final String GRX50120 = "GRX-50120";
	// Incorrect Increment option value
	public static final String GRX50121 = "GRX-50121";
	// Invalid values for DuplicatePO/Consolidated invoice
	public static final String GRX50122 = "GRX-50122";
	// Invalid PO format for the given DuplicatePO & Consolidated invoice
	// combination
	public static final String GRX50123 = "GRX-50123";
	// Ship To number is mandatory
	public static final String GRX50124 = "GRX-50124";
	// Please specify correct values for POC type
	public static final String GRX50125 = "GRX-50125";
	// Market field text is mandatory
	public static final String GRX50126 = "GRX-50126";
	// Market field text length should be less than {x}
	public static final String GRX50127 = "GRX-50127";
	// You do not have access to manage sold to accounts.
	public static final String GRX50128 = "GRX-50128";
	// You do not have access to manage ship to accounts.
	public static final String GRX50129 = "GRX-50129";
	//No ship to is mapped with this sold to.
	public static final String GRX50130 = "GRX-50130";

	/* Constants for Part Mapping */
	// Part Number is invalid/No details found for this part number
	public static final String GRX50152 = "GRX-50152";
	// ACC Description is mandatory
	public static final String GRX50153 = "GRX-50153";
	// No Sales org is mapped with this part number
	public static final String GRX50154 = "GRX-50154";
	// No sold to is mapped with this sales org
	public static final String GRX50155 = "GRX-50155";
	// No ship to is mapped with this sales org and sold to.
	public static final String GRX50156 = "GRX-50156";
	// Invalid input data for the part
	public static final String GRX50157 = "GRX-50157";
	// You do not have access to manage part inclusions
	public static final String GRX50058 = "GRX-50058";
    public static final String GRX15000 = "GRX-15000";
    public static final String GRX40080 = "GRX-40080";
    public static final String GRX50086 = "GRX-50086";
    public static final String GRX50057 = "GRX-50057";
	public static final String GRX50158 = "GRX-50158";
	public static final String GRX50087 = "GRX-50087";
	public static final String GRX50088 = "GRX-50088";

	//  Adding for Authorization Exception
	public static final String GRX17001 = "GRX-17001";
	//Adding for GRX shutdown afetr 24 hrs
	public static final String GRX17002 = "GRX-17002";
	/** Error code used for  Text and messages  Localization**/
	public static final String TX1001 = "TX-1001";
	public static final String TX1002 = "TX-1002";
	public static final String TX1003 = "TX-1003";
	public static final String TX1004 = "TX-1004";
	public static final String TX1005 = "TX-1005";
	public static final String TX1006 = "TX-1006";
	public static final String TX1007 = "TX-1007";
	public static final String TX1008 = "TX-1008";
	public static final String TX1009 = "TX-1009";
	public static final String TX1010 = "TX-1010";
	public static final String TX1011 = "TX-1011";
	public static final String TX1012 = "TX-1012";
	public static final String TX1013 = "TX-1013";
	public static final String TX1014 = "TX-1014";
	public static final String TX1015 = "TX-1015";
	public static final String TX1016 = "TX-1016";

  public static final String NOT_VALID = "Not Valid";

  public static final String GRX55555 = "GRX-55555";

  public static final String DS_INVALID_INPUT = "DS-5041";
  
  public static final String GRX20101 = "GRX-20101";
  public static final String GRX20102 = "GRX-20102";
  public static final String GRX20103 = "GRX-20103";
  public static final String GRX20104 = "GRX-20104";
  public static final String GRX20105 = "GRX-20105";
  public static final String GRX20106 = "GRX-20106";
  public static final String GRX20107 = "GRX-20107";
  public static final String GRX20108 = "GRX-20108";
  public static final String GRX20109 = "GRX-20109";

  public static final String GRX50208 = "GRX-50208"; //Added for <rdar://problem/15430863>
  public static final String GRX51111 = "GRX-51111";
  public static final String GRX51112 = "GRX-51112";

}
