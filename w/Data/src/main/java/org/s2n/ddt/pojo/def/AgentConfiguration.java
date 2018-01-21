package org.s2n.ddt.pojo.def;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Configuration for the app. Where input files (xls), helper to link to
 * resources (images, css etc) and where output goes.
 */
@SuppressWarnings("unused")
public class AgentConfiguration {

	private static Logger logger = Logger.getLogger(AgentConfiguration.class);
	
	

	/** Config sheet name. */
//	public static final String CONFIG_SHEET_NAME = "_config";
//
//	public static final String OBJECT_SHEET_NAME = "_objects";

	public static final int DEFAULT_SERVER_TIMEOUT = 25000;

	public static final int DEFAULT_PAGE_LOAD_TIME = 3500;

	public static final String JS_FILE = "/JS/amcharts.js";// TODO in DDT this
	// should be user
	// defined list,
	// sprint 3.
	
	

	public static final String CSS_FILE = "/CSS/styles.css";

	private boolean showUi = true;
	
	private String staticFiles = null;

	/** The excel acc. */
	//private ExcelAccess excelAcc = null;
	//private TestCase testCase;

//	private File objMapFile;
//	private long objMapModified;
//	private File configFile;
//	private long configModified;

	private String reportStaticFiles;

//	private File srcDir;
//	/** The sheet name. */
//	private String sheetName;
//	private String dateChecker;

	/** The server time. */
//	private int serverTime;
	private File[] files = new File[10];// todo make correct len
	private Map<String, String> objectHashMap = new HashMap<String, String>();

	/** Location of app code/ jar */
//	private File programDir;

	/** Folder where current test case's output html reports to be stored */
	private File htmlOutDir;

	/** Folder where current test case's output excel reports to be stored */
//	private File excelOutDir;

	/** Output xls */
//	private File excelOutFile;

	/**
	 * Folder where current test case's output snap shot/ screenshots to be
	 * stored
	 */
//	private File snapShotsOutDir;

	/*public File getSnapShotsOutDir() {
		return snapShotsOutDir;
	}

	public void setSnapShotsOutDir(File snapShotsOutDir) {
		this.snapShotsOutDir = snapShotsOutDir;
	}*/

	/**
	 * start folder of output or other folder as the default folder for relative
	 * paths
	 */
	private File defaultDir;

	/** The config input map. */
	private Map<String, String> configInputMap = new HashMap<String, String>();

	private int waitForPageTime;

	private String dateFormat;

	private String reportDateStr;

	private String inRptPathToStaticRes;

	private Date testCaseStartTime;

	private String snapShotRelativeDir;
/*
	public AgentConfiguration() {

		defaultInit();
	}
*/
	/*void defaultInit() {
		testCaseStartTime = new Date();
		String prgDirStr = "./";
		try {
			prgDirStr = AgentConfiguration.class.getProtectionDomain().getCodeSource()
					.getLocation().getPath();
		} catch (Exception e) {
//			logger.log(Level.ERROR, "getProtectionDomain err :" + e, e);
		}

		try {
			prgDirStr = URLDecoder.decode(prgDirStr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			prgDirStr = org.s2n.ddt.util.LangUtils
					.urlDecode(prgDirStr);
		}
		final String idePathCode = "/target/classes/";
		if (prgDirStr.endsWith(idePathCode)) {
			prgDirStr = prgDirStr.substring(0,
					prgDirStr.length() - idePathCode.length());
		}
		try {
			programDir = new File(prgDirStr);
			if (programDir.isFile()) {
				programDir = programDir.getParentFile();
			}
			programDir = programDir.getCanonicalFile();
			logger.log(Level.INFO, "program dir :" + programDir);
			defaultDir = new File("./").getCanonicalFile();
			logger.log(Level.INFO, "Initial defaultDir :[" + defaultDir + "]");
		} catch (Exception e) {
			logger.log(Level.ERROR, "App root or default dir set up err :" + e,
					e);

		}
	}*/

	/*public void init(String[] args) {
		logger.log(Level.INFO, "Calling Init method");
		try {
			int fileCount = 0;
			if (args.length == 0) {
				// default
				logger.info("Executing with UI");
			} else {
				for (int i = 0; i < args.length; i++) {
					if (args[i].equals(CmdLine.UI_SHOW)) {
						setShowUi(true);
					} else if (args[i].equals(CmdLine.UI_HIDE)) {
						setShowUi(false);
					} else {
						String s = args[i];
						boolean isF = false;
						if (s.length() > 2) {
							if (s.charAt(0) != '-') {
								File file = new File(args[i]);

								if (file.exists() == false) {
									logger.log(Level.WARN,
											"File does not exist [" + s + "]");
								} else {
									isF = true;
									file = file.getCanonicalFile();
									files[fileCount] = file;
								}
								fileCount++;
							}
						}
						if (isF == false) {
							logger.log(Level.WARN, "Did not process argument ["
									+ s + "]");
						}
					}
				}
				if (fileCount > 0 && fileCount < 3) {
					for (int i = fileCount; i < 3; i++) {
						files[i] = files[i - 1];
					}
				}
			}
			File inputFile = files[ConfFiles.InputFile.getId()];
			if (inputFile != null && inputFile.exists()) {
				initConfigValues();
			}
		} catch (Exception e) {
			logger.log(Level.ERROR, "initConfigValues :" + e, e);
		}

	}*/

	/*public void initConfigUI(File confFile, File objFile, File testFile)
			throws IOException {
		files[ConfFiles.ConfFile.getId()] = confFile;
		files[ConfFiles.ObjectMapFile.getId()] = objFile;
		files[ConfFiles.InputFile.getId()] = testFile;
		File inputFile = files[ConfFiles.InputFile.getId()];
		File configFile = files[ConfFiles.InputFile.getId()];
		File objectFile = files[ConfFiles.InputFile.getId()];
		if ((inputFile != null && inputFile.exists()) || (configFile != null && configFile.exists()) || (objectFile != null && objectFile.exists()) ) {
			try {
				initConfigValues();
			} catch (Exception e) {
				logger.log(Level.ERROR, "initConfigUI :" + e, e);
				logger.log(Level.ERROR, "confFile :" + confFile);
				logger.log(Level.ERROR, "objFile :" + objFile);
				logger.log(Level.ERROR, "testFile :" + testFile);
			}
		}

	}*/

	/*public void initConfigValues() throws IOException {
		initObjectMap();
		initConfig();
		testCaseStartTime = new Date();
		logger.log(Level.INFO, "initConfigValues (b1) " + getInputFile());
		excelAcc.openWorkBook(getInputFile());
		initOther();

	}*/

	/*private void initConfig() throws IOException {
		File curConfFile = files[ConfFiles.ConfFile.getId()];

		if (curConfFile.equals(configFile)
				&& configModified == curConfFile.lastModified()) {
			logger.info("Config file is same, using already loaded");
			return;
		}
		configModified = curConfFile.lastModified();
		configFile = curConfFile;
		ExcelAccess exlConfAcc = new ExcelAccess();
		String configKey;
		logger.info(" Current Object File  "+curConfFile);
		exlConfAcc.openWorkBook(curConfFile);
		sheetName = CONFIG_SHEET_NAME;
		final int tot = exlConfAcc.getTotalRowCount(sheetName);
		logger.info(" Total row cnt "+tot);
		for (int rowStart = CellValues.ROW_START.valueColoumn; rowStart <= tot; rowStart++) {
			configKey = exlConfAcc.getCellValue(sheetName, rowStart,
					CellValues.KEY_COLOUMN.valueColoumn);
			configInputMap.put(configKey, rowStart + "");
		}
		logger.info(" ConfigInput  "+configInputMap);
		String usrDef = extractValFromSht(exlConfAcc,"userDef", configInputMap,
				ConfigSheetKeyValues.DEFAULT_FILE.getTextValue, null,
				CONFIG_SHEET_NAME, 2, 2);// from
		logger.info("User defined message "+usrDef);
		File userDefault = null;
		if (usrDef != null && usrDef.length() > 0) {
			userDefault = new File(usrDef.trim());
		}
		if (userDefault == null || !userDefault.exists()) {
			userDefault = getDefaultDir();
			logger.info("User default not defined or not found using default :"
					+ userDefault);
		} else {
			String usrDfXtra = null;
			try {
				usrDfXtra = exlConfAcc.getCellValue(CONFIG_SHEET_NAME, 2, 4);
				logger.log(Level.INFO, "usrDef xtra :" + usrDfXtra);
				usrDfXtra = sdfDateProcess(usrDfXtra);
				if (usrDfXtra != null) {
					usrDef += usrDfXtra;
				}
				File f = new File(usrDef);
				f.mkdirs();
				f = new File(usrDef).getCanonicalFile();
				logger.log(Level.INFO, "usrDef with xtra :" + usrDef
						+ ", exists :" + f.exists());
				if (f.exists()) {
					userDefault = f;
				}
			} catch (Exception e) {
				logger.log(Level.ERROR, "usrDef xtra err " + e + ", xtra :"
						+ usrDfXtra, e);
			}
			setDefaultDir(userDefault);
		}
		usrDef = extractValFromSht(exlConfAcc,"progDef", null, null, null,
				CONFIG_SHEET_NAME, 1, 2);
		File prgDirUsr = null;
		if (usrDef != null && usrDef.length() > 0) {
			prgDirUsr = new File(usrDef.trim());
		}
		if (prgDirUsr == null || !prgDirUsr.exists()) {
			prgDirUsr = getProgramDir();
			logger.info("User prog dir not defined or not found using default :"
					+ prgDirUsr);
		} else {
			setProgramDir(prgDirUsr);
		}

		boolean runProgpath = false;
		if (userDefault.exists()) {
			// defaultDir = userDefault;
			initHtmlRpt();
		} else {
			logger.log(Level.WARN, "Your default folder :[" + usrDef
					+ "] does not exist, using " + defaultDir);
			runProgpath = true;
		}
		
		String datePicker = extractValFromSht(exlConfAcc,"datepicker", null, null, null,
				CONFIG_SHEET_NAME, 18, 2);
		logger.info("datePicker  "+datePicker);
		if(datePicker.equals("0")){
			setDateChecker("via-txt");
			
		}
		else{
			setDateChecker("viaCustomized");
		}
		if (runProgpath) {

			String serverResponseTime = null;
			try {
				serverResponseTime = configInputMap
						.get(ConfigSheetKeyValues.SERVER_RESPONSE_TIME.getTextValue);
				if (serverResponseTime.equals("")) {
					serverTime = DEFAULT_SERVER_TIMEOUT;
				} else {
					serverTime = Integer.parseInt(serverResponseTime);
				}
			} catch (Exception ee) {
				logger.log(Level.WARN, "srvr response time e:" + ee + ", val :"
						+ serverResponseTime, ee);
			}
		}

	}*/

	/*private void initObjectMap() {
		File curObjFile = files[ConfFiles.ObjectMapFile.getId()];
		logger.log(Level.INFO, " curObjFile " + curObjFile);
		String objectPath;
		int tcStartRow = 1;
		int tcEndRow = 1;
		int rowCtr;
		String curValue;
		String foundStart = TestCaseConstants.STRING_FALSE;
		String foundEnd = TestCaseConstants.STRING_FALSE;
		String foundEndSuite = TestCaseConstants.STRING_FALSE;
		int srtCnt = 1;
		if (curObjFile.equals(objMapFile)
				&& objMapModified == curObjFile.lastModified()) {
			logger.info("Object Map file is same, using already loaded");
			return;
		}
		objMapModified = curObjFile.lastModified();
		objMapFile = curObjFile;
		ExcelAccess exlObjAcc = new ExcelAccess();
		logger.info("  Cur Obj file  "+objMapFile);
		exlObjAcc.openWorkBook(objMapFile);
		while (true) {
			for (rowCtr = tcStartRow; rowCtr <= exlObjAcc
					.getTotalRowCount(OBJECT_SHEET_NAME); rowCtr++) {
				curValue = exlObjAcc.getCellValue(OBJECT_SHEET_NAME, rowCtr, 0);
				if (foundStart.equals(TestCaseConstants.STRING_FALSE)) {
					if (curValue.equals(TestCaseConstants.OBJECT_MAP_START)) {
						tcStartRow = rowCtr + 1;
						foundStart = TestCaseConstants.STRING_TRUE;
					}
				}
				if (foundEndSuite.equals(TestCaseConstants.STRING_FALSE)) {
					if (curValue.equals(TestCaseConstants.OBJECT_MAPPING_ENDS)) {
						foundEndSuite = TestCaseConstants.STRING_TRUE;
					}
				}
			}
			if (foundEndSuite.equals(TestCaseConstants.STRING_FALSE)) {
				logger.log(Level.ERROR,
						"Object Mapping Ends is not defined in the Object Sheet");
				break;
			}
			logger.info("foundStart   " + foundStart);
			if (foundStart.equals(TestCaseConstants.STRING_FALSE) && srtCnt < 2) {
				logger.info("Strt cnt " + srtCnt);
				srtCnt = srtCnt + 1;
				logger.log(Level.ERROR,
						"Object Map Starts is not defined in the Object Sheet");
				break;
			}
			for (rowCtr = tcStartRow; rowCtr <= exlObjAcc
					.getTotalRowCount(OBJECT_SHEET_NAME); rowCtr++) {
				String formNameColumn = exlObjAcc.getCellValue(
						OBJECT_SHEET_NAME, rowCtr, 0);
				String objectColumn = exlObjAcc.getCellValue(OBJECT_SHEET_NAME,
						rowCtr, 1);
				if (formNameColumn.equals(TestCaseConstants.OBJECT_MAP_END)) {
					tcEndRow = (rowCtr - 1);
					foundEnd = TestCaseConstants.STRING_TRUE;
					break;
				}
				objectHashMap.put(formNameColumn, objectColumn);

			}

			if (foundEnd.equals(TestCaseConstants.STRING_FALSE)) {
				logger.log(Level.ERROR,
						"Object Map End is not defined in the Object Sheet");
				break;
			}
			tcStartRow = (tcEndRow + 2);
			foundStart = TestCaseConstants.STRING_FALSE;
			foundEnd = TestCaseConstants.STRING_FALSE;

			setObjectHashMap(objectHashMap);
		}

	}

	private String extractValFromSht(ExcelAccess exlConfAcc, String nm, Map<String,String> confRowMap, String key,
			String defVal, String shtName, int defRow, int col) {
		String configValue = defVal;
		int row = 0;
		logger.log(Level.INFO, "extract conf value enter:" + nm + ", def "
				+ defVal + ", sht " + shtName + ", row :" + defRow + ", col"
				+ col);
		try {
			String configRowStr = confRowMap == null ? null
					: (String) confRowMap.get(key);
			if (configRowStr != null) {
				row = Integer.parseInt(configRowStr);
			} else {
				row = defRow;
			}
			if (row > 0) {
				
				configValue = exlConfAcc.getCellValue(shtName, row, col);
				logger.info(" configValue "+configValue);
			}
		} catch (Exception e) {
			logger.log(Level.WARN, "extract conf value :" + nm + ", def "
					+ defVal + ", sht " + shtName + ", row :" + row + ", " + e,
					e);
			logger.log(Level.WARN, "extract conf value col :" + col + ", key "
					+ key + ",  defRow :" + defRow + e);
		}

		return configValue;
	}

	private void initOther() {

		try {
			dateFormat = LangUtils.getSdfForFormat("yyyy-mm-dd").format(
					testCaseStartTime);
			String waitTime = configInputMap
					.get(ConfigSheetKeyValues.WAIT_TO_LOAD_TIME.getTextValue);
			if (waitTime.equals("")) {
				waitForPageTime = DEFAULT_PAGE_LOAD_TIME;
			} else {
				waitForPageTime = Integer.parseInt(waitTime);
			}

		} catch (NumberFormatException numberFormatExe) {
		}

		excelOutDir = new File(getDefaultDir(), "xls");
		excelOutDir.mkdirs();
		String actionWorkbook = FileUtils.getBaseName(getInputFile().getName())
				+ "-" + TestCaseConstants.REPORT + getReportsDateFormat()
				+ ".xls";
		File activeIn = new File(getExcelOutDir(), actionWorkbook);
		FileUtils.fileCopy(getInputFile(), activeIn);
		if (activeIn.exists()) {
			excelOutFile = activeIn;
			logger.log(Level.INFO, "Copied Input file for execution :"
					+ activeIn);
		}

	}*/

	private String replaceMulti(String in, String... vargs) {
		for (int i = 0; i < vargs.length; i += 2) {
			in = in.replace(vargs[i], vargs[i + 1]);
		}
		return in;
	}

	/**
	 * Code path - to get resources
	 * 
	 * @return the root directory as File
	 */
	/*public File getProgramDirectory() {

		return programDir;// excelReports = dirPath;
	}

	public void setProgramDir(File codeDir) {

		this.programDir = codeDir;
	}
*/
	/**
	 * Html report path. TODO review this - can be replaced by generic init for
	 * all files objects?
	 */
	/*private void initHtmlRpt() throws IOException {
		try {
			htmlOutDir = new File(defaultDir, "html");
			htmlOutDir.mkdir();
			if (!htmlOutDir.exists()) {
				logger.log(Level.WARN, "html rpt dir [" + htmlOutDir
						+ "] has an issue trying other.");
				int i = 0;
				while (i < 100) {
					htmlOutDir = new File(defaultDir, "html" + i);
					if (!htmlOutDir.exists()) {
						htmlOutDir.mkdir();
					}
					if (htmlOutDir.exists()) {
						break;
					}
					i++;
				}
			}

		} catch (Exception e) {
			logger.log(Level.WARN, "html rpt dir init " + e, e);
			htmlOutDir = defaultDir;
		}

		logger.log(Level.INFO, "htmlOutDir :[" + htmlOutDir + "]");
		snapShotsOutDir = new File(defaultDir, "snaps");// TODO
		snapShotsOutDir.mkdir();
		snapShotRelativeDir = "../snaps/";

		logger.log(Level.INFO, "snapsShotDir :[" + snapShotsOutDir + "]");
		inRptPathToStaticRes = excelAcc.getCellValue(CONFIG_SHEET_NAME, 14, 2);
		String toCopyRes = excelAcc.getCellValue(CONFIG_SHEET_NAME, 12, 2);
		if (LangUtils.isTrue(toCopyRes, true)) {
			String staticFilesFromStr = extractValFromSht(excelAcc,"staticFilesFromStr",
					null, null, null, CONFIG_SHEET_NAME, 11, 2);
			if (staticFilesFromStr != null && staticFilesFromStr.length() > 0) {
				staticFilesFromStr = replaceMulti(staticFilesFromStr,
						"--default-path--", getDefaultDir().getAbsolutePath(),
						"--program-path--", getProgramDir().getAbsolutePath());
				File staticFilesFromDir = new File(staticFilesFromStr);
				if (!staticFilesFromDir.exists()) {
					staticFilesFromDir = new File(getProgramDir(), "resources");
				}
				if (staticFilesFromDir.exists()) {
					String resToParent = excelAcc.getCellValue(
							CONFIG_SHEET_NAME, 13, 2);
					File resParentDir = new File(resToParent);
					if (resParentDir.exists()) {
						File resDir = new File(resParentDir, "JS");// TODO loop
						// for more
						// than 2
						if (!resDir.exists()) {
							File frm = new File(staticFilesFromDir, "JS");
							logger.log(Level.INFO, "Copy js dir [" + frm
									+ "]  Dest  [" + resDir + "]");
							org.apache.commons.io.FileUtils.copyDirectory(frm,
									resDir);
						}
						resDir = new File(resParentDir, "CSS");
						if (!resDir.exists()) {
							File frm = new File(staticFilesFromDir, "CSS");
							logger.log(Level.INFO, "Copy css dir [" + frm
									+ "]  Dest  [" + resDir + "]");
							org.apache.commons.io.FileUtils.copyDirectory(frm,
									resDir);
						}
					} else {
						logger.log(Level.WARN, "resParentDir does not exist :"
								+ resParentDir + ", not copying resources.");
					}
				} else {
					logger.log(Level.ERROR, "cant find static files dir ["
							+ staticFilesFromDir.getAbsolutePath()
							+ "] staticFilesFromStr [" + staticFilesFromStr
							+ "]");
				}
			}
		}

	}

	private String sdfDateProcess(String inStr) {
		if (inStr == null) {
			return null;
		}
		int idx = inStr.indexOf("<date>");
		if (idx > -1) {
			int idx2 = inStr.indexOf("</date>");
			if (idx2 > idx) {
				String dt = inStr.substring(idx + 6, idx2);
				SimpleDateFormat sdf = LangUtils.getSdfForFormat(dt);
				dt = sdf.format(testCaseStartTime);
				inStr = inStr.substring(0, idx) + dt
						+ inStr.substring(idx2 + 7);
			}
		}
		return inStr;
	}
*/
	/**
	 * File path check.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the string
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	/*public String filePathCheck(String filePath)
			throws UnsupportedEncodingException {

		String reportFileName;
		if (!filePath.equals("")) {
			File reportsFile = new File(filePath);
			if (reportsFile.isAbsolute()) {
				if (!reportsFile.isDirectory()) {
					reportsFile.mkdirs();
				}
				reportFileName = filePath;
			} else {
				reportFileName = getRootDirectory() + filePath;
				File createUserFolder = new File(reportFileName);
				if (!createUserFolder.isDirectory()) {
					createUserFolder.mkdirs();
					return createUserFolder.toString();
				} else {
					return reportFileName;
				}
			}
		} else {
			reportFileName = getRootDirectory() + TestCaseConstants.REPORT;
		}
		return reportFileName;
	}*/

	/**
	 * App root directory. Default is where you started program from. Can be
	 * over ridden in config sheet.
	 * 
	 * @return the root directory as File
	 */
	/*public File getRootDirectory() {

		return srcDir;// excelReports = dirPath;
	}
*/
	/**
	 * File path check.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the string
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 */
	public File filePathCheck(File filePath, File def, String sub1) {
		if (filePath.exists() && filePath.canExecute()) {
			return filePath;
		}
		File f = new File(def, sub1);
		return f;
	}

	/**
	 * Server response time.
	 * 
	 * @return the int
	 */
	/*public int getServerResponseTime() {

		return serverTime;
	}*/

	/**
	 * Wait for page load time.
	 * 
	 * @return the int
	 */
	public int waitForPageLoadTime() {

		return waitForPageTime;
	}

	/**
	 * Reports date format.
	 * 
	 * @return the string
	 */
	public String getReportsDateFormat() {
		return dateFormat;
	}

	/**
	 * Reports data date.
	 * 
	 * @return the string
	 */

	public String reportsDataDate() {
		return reportDateStr;
	}

	/**
	 * Date format check.
	 * 
	 * @param dateFormat
	 *            the date format
	 * @return the string
	 */
	public String dateFormatCheck(String dateFormat) {

		try {
			Date date = new Date();
			SimpleDateFormat simpleDate1 = new SimpleDateFormat("hh:mm:ss");
			String simpleDateTime = simpleDate1.format(date);
			SimpleDateFormat simpleDate = new SimpleDateFormat(dateFormat);
			String simpleDatePattern = simpleDate.format(date);
			simpleDate.applyPattern(simpleDatePattern);
			dateFormat = simpleDatePattern + simpleDateTime;
		} catch (IllegalArgumentException illegal) {
		}
		return dateFormat;
	}

	/*public boolean isShowUi() {

		return showUi;
	}

	public void setShowUi(boolean showUi) {

		this.showUi = showUi;
	}

	public ExcelAccess getExcelAcc() {

		return excelAcc;
	}

	public void setExcelAcc(ExcelAccess excelAcc) {

		this.excelAcc = excelAcc;
	}

	public String getSheetName() {

		return sheetName;
	}

	public void setSheetName(String sheetName) {

		this.sheetName = sheetName;
	}

	public String getHtmlFolder() {

		return null;
	}

	public int getServerTime() {

		return serverTime;
	}

	public void setServerTime(int serverTime) {

		this.serverTime = serverTime;
	}

	public Map<String, String> getConfigInputMap() {

		return configInputMap;
	}

	public void setConfigInputMap(Map<String, String> configInputMap) {

		this.configInputMap = configInputMap;
	}

	public File getDefaultDir() {

		return defaultDir;
	}

	public void setDefaultDir(File defaultDir) {

		this.defaultDir = defaultDir;
	}

	public File getInputFile() {

		return files[ConfFiles.InputFile.getId()];
	}

	public void setInputFile(File inputFile) {

		this.files[ConfFiles.InputFile.getId()] = inputFile;
	}

	public String getStaticFiles() {

		return staticFiles;
	}

	public void setStaticFiles(String staticFiles) {

		this.staticFiles = staticFiles;
	}

	public String getReportStaticFiles() {

		return reportStaticFiles;
	}

	public void setReportStaticFiles(String reportStaticFiles) {

		this.reportStaticFiles = reportStaticFiles;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		AgentConfiguration.logger = logger;
	}

	public File getHtmlOutDir() {
		return htmlOutDir;
	}

	public void setHtmlOutDir(File htmlOutDir) {
		this.htmlOutDir = htmlOutDir;
	}

	public File getExcelOutDir() {
		return excelOutDir;
	}

	public void setExcelOutDir(File excelOutDir) {
		this.excelOutDir = excelOutDir;
	}

	public File getProgramDir() {
		return programDir;
	}

	public File getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(File srcDir) {
		this.srcDir = srcDir;
	}

	public String getInRptPathToStaticRes() {
		return inRptPathToStaticRes;
	}

	public File getExcelOutFile() {
		return excelOutFile;
	}

	public String getSnapShotRelativeDir() {
		return snapShotRelativeDir;
	}

	public File getObjectFile() {
		return files[ConfFiles.ObjectMapFile.getId()];
	}

	public void setObjectFile(File objectFile) {
		this.files[ConfFiles.ObjectMapFile.getId()] = objectFile;
	}

	public File getConfFile() {
		return files[ConfFiles.ConfFile.getId()];
	}

	public void setConfFile(File confFile) {
		this.files[ConfFiles.ConfFile.getId()] = confFile;
	}

	public Map<String, String> getObjectMap() {
		return objectHashMap;
	}

	public void setObjectHashMap(Map<String, String> objectHashMap) {
		this.objectHashMap = objectHashMap;
	}
	public String getDateChecker() {
		return dateChecker;
	}

	public void setDateChecker(String dateChecker) {
		this.dateChecker = dateChecker;
	}*/
}
