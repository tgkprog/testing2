package org.s2n.ddt.excelreader;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.util.ExcelAccess;

public class ReadReplacementStrings {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReadReplacementStrings.class);

	private static String xlsPath;

	public static String getXlsPath() {
		return xlsPath;
	}

	public static void setXlsPath(String xlsPath) {
		ReadReplacementStrings.xlsPath = xlsPath;
	}

	public static Map<String, ReplacementString> getReplacementString() {
		Map<String, ReplacementString> rMap = new HashMap<String, ReplacementString>();
		ReplacementString rString = null;
		ExcelAccess excelAccess = new ExcelAccess();
		excelAccess.openWorkBook(new File(UtlConf.getProperty("xlsPath.replacementString")));
		int sheetCnt = excelAccess.getNumberOfSheets();
		for (int i = 0; i < sheetCnt; i++) {
			String sheetName = excelAccess.getSheetNames(i);
			for (int i1 = 5; i1 < excelAccess.getTotalColumnCount(sheetName); i1++) {
				rString = new ReplacementString();
				rString.setAppName(excelAccess.getCellValue(sheetName, i1, 2));
				rString.setName(excelAccess.getCellValue(sheetName, i1, 5));
				rString.setValue(excelAccess.getCellValue(sheetName, i1, 6));
				rString.setEncrypted((int) excelAccess.getCellNumericValue(sheetName, i1, 7));
				rMap.put(rString.getName(), rString);
			}
		}
		logger.info("values are filled with excel data : " + rMap);
		return rMap;
	}

}
