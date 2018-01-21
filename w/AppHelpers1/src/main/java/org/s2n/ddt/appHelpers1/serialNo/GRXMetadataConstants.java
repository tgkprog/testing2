package org.s2n.ddt.appHelpers1.serialNo;

import java.util.regex.Pattern;

public final class GRXMetadataConstants {

	/* Constructor */

	public static final String USER_ROLE = "USER_ROLE";
	public static final String ACCESS_DENIED = "ACCESS_DENIED";
	public static final String INVALID_DEVICE = "INVALID_DEVICE";
	/** Constants for Purchase Mode**/
	public static final String CONCURRENT_SALE = "C";
	public static final String INSTALL_BASE_SALE = "I";
	/** Constants for Delivery preference**/
	public static final String EMAIL_DELIVERY_PREF ="E";
	public static final String PRINT_DELIVERY_PREF ="H";
	/** Constants for Ordering Method**/
	public static final String ZGSE ="ZGSE";
	public static final String ZGSP ="ZGSP";
	public static final String ZGSR ="ZGSR";
	/** Constants for POC Type **/
	public static final String APOC = "APOC";
	public static final String RPOC = "RPOC";
	/** Constants for ORDER being single Bulk Or MiniBulk **/
	public static final String BULK_ORDER="B";
	public static final String MINI_BULK_ORDER="M";
	public static final String SINGLE_ORDER = "S";
	/** Constatns for Date Format **/
	public static final String GRX_DATE_FORMAT = "dd/MM/yyyy";

	// Report Download
	public static final String REPORT_DOWNLOAD_DATE_FORMAT = "MM/dd/yyyy";
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	public static final String PQR_DATE_FORMAT = "yyyyMMdd";
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String SAP_CREDIT_DATE_FORMAT = "dd-MMM-yy";
	public static final String SIMPLE_DATE_FORMAT_NOTIME = "yyyy-MM-dd";
	/****** Error Codes start ******/

	/* Business Error Codes */

	public static final String GRX_AUTH_URL = "ds.app.url";
	public static final String GRX_AUTH_ID = "ds.app.id";
	public static final String GRX_AUTH_PASS = "ds.app.password";
	public static final String GRX_AUTH_MAC_CREATOR = "ds.app.mac.creator";

	/****** Error Codes start ******/

	// Device
	public static final String IMEI = "IMEI";
	public static final String MEID = "MEID";
	public static final String SERIAL_NUMBER = "SERIAL_NUMBER";

	public static final String STR_Y = "Y";
	public static final String STR_N = "N";
	public static final String STR_AC = "AC+";
	public static final String ORDER_VO = "ORDER_VO";
	public static final String SNL_REQUEST = "SNL_REQUEST";
	public static final String SOLD_TO_CONFIGURATION_VO = "SOLD_TO_CONFIGURATION_VO";
	public static final String SHIP_TO_REQUEST_VO = "SHIP_TO_REQUEST_VO";
	public static final String STATE_EXCL_LIST_META_DATA = "stateExclListMetaData";

	public static final String TRUE = "TRUE";
	public static final String FALSE = "FALSE";

	public static final int SN_LENGTH_11 = 11;
	public static final int SN_LENGTH_12 = 12;
	public static final int SN_LENGTH_13 = 13;
	public static final int SN_LENGTH_18 = 18;

	public static final Pattern SERIAL_NUMBER_PATTERN = Pattern.compile("^[a-zA-Z0-9]*$");
	public static final Pattern PO_NUMBER_PATTERN = Pattern.compile("^[a-zA-Z0-9/]*$");
	public static final String VALID_DIGITS = "0123456789ABCDEFGHJKLMNPQRSTUWXYZ";
	public static final String SERIAL_NUMBER_YEARPATTERN = "^[C-HJ-NP-TV-Zc-hj-np-tv-z]{1}$";
	public static final String SERIAL_NUMBER_WEEKPATTERN = "^[C-HJ-NP-RT-Yc-hj-np-rt-y1-9]{1}$";
	public static final String CHECKSUM_PATTERN = "^[A-HJ-NP-UW-Za-hj-np-uw-z0-9]*$";

	/**** Constants for Geneva Cache ****/
	public static final String GENEVA_CACHE_NAME_ADDRESS = "addressCache";
	public static final String COUNTRY_META_DATA = "countryMetaData";
	public static final String STATE_META_DATA = "stateMetaData";
	public static final String CITY_META_DATA = "cityMetaData";
	public static final String REGION_META_DATA = "regionMetaData";
	public static final String DISPLAY_MATRIX_META_DATA = "displayMatrixMetaData";
	public static final String PRIVILEGES_META_DATA = "privilegesMetaData";
	public static final String SALES_ORG_META_DATA = "salesOrgMetaData";
	public static final String SHIP_TO_ACCOUNT_META_DATA = "shipToAccountMetaData";
	public static final String SOLD_TO_ACCOUNT_META_DATA = "soldToAccountMetaData";
	public static final String ZIP_FORMAT_META_DATA = "zipFormatMetaData";
	public static final String ERROR_LOG_META_DATA = "errorLogMetaData";
	public static final String BLOCK_COUNTRY_META_DATA = "blockCountryMetaData";
	public static final String LANG_UI_META_DATA = "langUIMetaData";
	public static final String LANG_DATE_FORMAT_META_DATA = "langDateFormatMetaData";
	public static final String TIME_ZONE_META_DATA = "timeZoneMetaData";
	public static final String DS_SUPP_COUNTRY_DATA = "dsCountryMetaData";
	public static final String PROPERTY_META_DATA = "propertyMetaData";
	public static final String  COUNTRY_ZIPFORMAT_META_DATA= "cntryZipFormatMetaData";
	public static final String  SMS_META_DATA= "smsMetaData";
	public static final String  TEXT_META_DATA  = "logMetaData";


	public static final int CACHE_LOADING_TIME = 30000;
	// Session cache set to 30 mins
	public static final int CACHE_SESSION_TIME = 1800;
	public static final String CACHE_METADATA_PROPS = "com/apple/ist/grx/cache/cacheMetaData.properties";
	public static final String EH_CACHE_PROPS = "com/apple/ist/grx/cache/ehcache.properties";
	public static final String GENEVA_CACHE_PROPS_LOC = "com/apple/ist/grx/cache/genevaCache.properties";
	public static final String ENCODER_PROPS_LOC = "com/apple/ist/grx/encoder/encoder.properties";

	public static final String GENEVA_CACHE_NAME = "grxcache.name.session";

	public static final String GENEVA_ASSET_TEST_FILE_PUT = "com/apple/ist/grx/asset/GRXAssetSample_PUT.txt";
	public static final String GENEVA_ASSET_TEST_FILE_GET = "com/apple/ist/grx/asset/GRXAssetSample_GET.txt";

	/**** Constants for AgreementNumber Generation ****/
	public static final String AGNUM_SP_NAME = "SP_AGREEMENTID_GEN";
	public static final String AGNUM_SHIPTOCODE = "SHIPTOCODE";
	public static final String AGNUM_PREFIX_TEXT = "PREFIX_TEXT";
	public static final String AGNUM_SEQ_START = "SEQ_START";
	public static final String AGNUM_SUFFIX_TEXT = "SUFFIX_TEXT";
	public static final String AGNUM_SEQ_END = "SEQ_END";

	/**** Constants for BulkUploadFileKeyGenerator and BulkUploadIDSequenceGenerator Repository ****/
	public static final String BULKID_SP_NAME = "SP_BULKID_GEN";
	public static final String PONUM_SP_NAME = "SP_PONO_GEN";
	public static final String SP_QUEUEID_GEN = "SP_QUEUEID_GEN";
	public static final String SEQ_START = "SEQ_START";
	public static final String SEQ_END = "SEQ_END";
	public static final String TM_DATABASE_PREFIX = "TM_DATABASE_PREFIX";


	public static final String DS_AUTH_PROPS_LOC = "com/apple/ist/grx/security/processor/authentication.properties";

	public static final String GRX_SERVICE_PROPS_LOC = "com/apple/ist/grx/service/GRXService.properties";
	public static final String GRX_APPLICATION_HOST = "grx.application.host";
	public static final String GRX_APPLICATION_PORT = "grx.application.port";
	public static final String GRX_APPLICATION_PROTOCOL = "grx.application.protocol";
	public static final String GRX_INTERNAL_APPLICATION_HOST = "grx.internal.application.host";
	public static final String GRX_INTERNAL_APPLICATION_PORT = "grx.internal.application.port";
	public static final String GRX_INTERNAL_APPLICATION_PROTOCOL = "grx.internal.application.protocol";
	public static final String GRX_APPL_MAX_CONN_PER_HOST = "grx.application.max.connections.per.host";
	public static final String GRX_APPL_MAX_TOTAL_CONN = "grx.application.max.total.connections";
	public static final String GRX_SERVICE_CONTEXTROOT = "/services";
	public static final String GRX_INTERNAL_SERVICE_CONTEXTROOT = "/grx-internal-service";

	 // Should be picked up from constants
	public static final String GRX_SERVICE_DOMAIN = "hostname";
	public static final String CONTENT_TYPE_JSON = "application/json";
	public static final String REQ_HEADER_ACCEPT = "Accept";
	public static final String HOME_PAGE = "home";

	public static final String PRS_ID = "prsId";
	public static final String BLOCK_COUNTRY_LIST = "blockCountryList";
	public static final String DS_SUPP_COUNTRY_LIST = "dsSupCountryList";
	public static final String LANG = "lang";

	public static final String US_EN_LANG = "US-EN";
	public static final String EN_LANG = "en";

	public static final String SELECTED_SOLDTO_VALUE = "selectedSoldToValue";
	public static final String SELECTED_SHIPTO_VALUE = "selectedShipToValue";

	public static final String STR_CANCEL = "Cancel";
	public static final String STR_CREATE = "Create";
	public static final String STR_HOME = "Home";
	public static final String STR_SINGLE_ORDER = "SingleOrder";
	public static final String STR_LOOKUP = "Lookup";

	/**
	 * Constant for FIRST_NAME.
	 * */
	public static final String FIRST_NAME = "FirstName";
	/**
	 * Constant for USER_FIRST_NAME.
	 * */
	public static final String USER_FIRST_NAME = "firstName";
	/**
	 * Constant for USER_LAST_NAME.
	 * */
	public static final String USER_LAST_NAME = "lastName";
	/**
	 * Constant for SSO_HASH_TABLE.
	 * */
	public static final String SSO_HASH_TABLE = "ssoHashTable";
	/**
	 * Constant for LAST_NAME.
	 * */
	public static final String LAST_NAME = "LastName";
	/**
	 * Constant for DSID.
	 * */
	public static final String DSID = "DSID";
	public static final String DS_LANGUAGE_COOKIE ="dslang";

	/**
	 * Constant for GRXUSERACTIVITY OBJECT
	 */

	public static final String GRX_USER_ACTIVITY_OBJ = "GRX_USER_ACTIVITY_OBJ";



	/**
	 * Adding for encoding type
	 */
	public static final String UTF_ENCODING ="UTF-8";
	public static final String URL_BODY_DELIM_ASYNC_ORDER = "%^x001F^%";

	public static final String GMT_TIMEZONE = "GMT";

	public static final String GRX_USER_OBJECT = "GRX_USER_OBJECT";
	public static final String SPECIAL_CHAR_NEWLINE= "\n";
	public static final String SPECIAL_CHAR_TAB= "\t";

	/**
	 * Adding constants for User Activity Reporting
	 */

	public static final String CREATE_ORDER = "CREATE_ORDER";
	public static final String VERIFY_ORDER = "VERIFY_ORDER";
	public static final String CANCEL_ORDER = "CANCEL_ORDER";
	public static final String ORDER_LOOKUP = "ORDER_LOOKUP";
	public static final String LOGIN = "LOGIN";
	public static final String LOGOUT = "LOGOUT";

	public static final String JAVA_CREATE = "createOrder";
	public static final String JAVA_VERIFY = "verifyOrder";
	public static final String JAVA_CANCEL = "cancelOrder";
	public static final String JAVA_ORDER_LOOKUP = "lookupOrder";

	public static final String USER_ACTIVITY_STATUS_SUCCESS = "SUCCESS";
	public static final String USER_ACTIVITY_STATUS_FAILURE = "FAILURE";
	public static final String USER_ACTIVITY_STATUS_AUTH_FAILURE = "AUTHORIZATION_FAILURE";
	public static final String API = "API";
	public static final String UI = "UI";
	public static final String MINI_BULK = "MINIBULK";
	public static final String BULK = "BULK";
	public static final String MANUAL_PO="MANUAL";
	public static final String MANUAL_PO_PRIV_ID="9999";
	public static final String MANUAL_PO_PRIVILEGE="canEnterPoNumber";
	public static final String MINI_BULK_PRIV_ID="9998";
	public static final String MINI_BULK_PRIVILEGE="canPerformMiniBulk";
	public static final String SET_DATE_PRIV="canSetACDates";
	public static final String SET_CANCEL_DATE_PRIV="canSetCancelDates";
	public static final String CAN_ENTER_SEC_DEVICE_PRIV="canEnterSecondaryDeviceID";
	public static final String CREATE_GUI_PRIV="canCreateAEOrderGUI";
	public static final String CREATE_API_PRIV="canCreateAEOrderAPI";
	public static final String CANCEL_GUI_PRIV="canCancelAEOrderGUI";
	public static final String CANCEL_API_PRIV="canCancelAEOrderAPI";
	public static final String CAN_VIEW_360_GUI_PRIV="canView360GUI";
	public static final String CAN_VIEW_360_API_PRIV="canView360API";
	public static final String COUNTRY_ZIPFORMAT_LIST = "cntryZipFormatList";
	public static final String CAN_SEND_SMS = "canSendSMS";
	public static final String IS_NAME_MANDATORY_IT  = "1128";
	public static final String IS_NAME_MANDATORY  = "isNameMandatory";
	public static final String CAN_ENTER_MKT_FIELD_TEXT_ID  = "1129";
	public static final String CAN_ENTER_MKT_FIELD_TEXT  = "canEnterMarketFieldText";

	//Path for internal service GBI
		public static final String INTERNAL_SERVICE_PATH="/grx-internal-service/grxinternalservice/services/get-request-response-xml/";


		/*
		 * Adding the /ds-redirect for url rerouting
		 *
		 * */

			public static final String DS_REDIRECT = "ds-redirect";




	private GRXMetadataConstants(){}

}
