package org.s2n.ddt.excelreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.util.ExcelAccess;

public class ReadExcel {
	private static String[] CellValues;
	private static String[] temp;
	private static Logger logger = Logger.getLogger(ReadExcel.class);

	public static String[] readFromExcel() {
		usage();
		// File inF = new File(args[0]);
		File inF = new File(getConfigFilePath());
		try {

			ExcelAccess xls = new ExcelAccess();
			xls.openWorkBook(inF);
			int sheetCnt = xls.getNumberOfSheets();
			System.out.println("sheetCnt = " + sheetCnt);
			for (int i = 0; i < sheetCnt; i++) {
				String sheetName = xls.getSheetNames(i);
				if (!sheetName.startsWith("_")) {
					String reqdDetails = xls.getCellValue(sheetName, 0, 0) + xls.getCellValue(sheetName, 0, 1);
					System.out.println("reqdDetails = " + reqdDetails);
					temp = reqdDetails.split(";");
					CellValues = new String[3];
					for (i = 0; i < temp.length; i++) {
						int colonPos = temp[i].indexOf(":");
						String cellvalues = temp[i].substring(colonPos + 1, temp[i].length());
						CellValues[i] = cellvalues;
					}
					for (i = 0; i < CellValues.length; i++) {
						System.out.println("CellValues = " + CellValues[i]);
						logger.info("Added " + CellValues[i] + " to array");
					}
				}
			}

		} catch (Exception e) {
			logger.log(Level.INFO, "err " + e, e);
		}
		return CellValues;
	}

	private static void usage() {
		System.out.println("Pass param 1 - excel sheet to process. All sheets whose name does not being with \"_\" will be processed.");
		System.out.println("Verify data validaity before processing. Press Enter key to continue OR ctrl-C to stop");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			reader.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Gets the configuration file path
	 * 
	 * @return String
	 */
	public static String getConfigFilePath() {
		String path = System.getProperty("TEST_CASE_XLS");
		if (path == null) {
			URL url = ReadExcel.class.getClassLoader().getResource("TestApp.xls");
			if (url != null) {
				path = url.getFile();
			}
		}
		logger.info("File path for common excel : " + path);
		return path;
	}

}
