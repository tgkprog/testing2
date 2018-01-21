package org.s2n.ddt.excelreader;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.input.RunnerType;
import org.s2n.ddt.util.ExcelAccess;
import org.s2n.ddt.utils.DataConstants;

public class ReadRunnerTypeData {

	private static final Logger logger = Logger.getLogger(ReadRunnerTypeData.class);

	public Map<String, RunnerType> getRunnerType(String fileName) {
		RunnerType runnerType = null;
		ExcelAccess excelAccess = new ExcelAccess();
		excelAccess.openWorkBook(new File(fileName));
		int sheetno = excelAccess.getNumberOfSheets();
		Map<String, RunnerType> loadXlsRunnerTypes = new HashMap<String, RunnerType>();
		List<String> sheetNames = new ArrayList<String>();
		List<RunnerType> runnerTypeList = new ArrayList<RunnerType>();
		for (int i = 0; i < sheetno; i++) {
			String sheetName = excelAccess.getSheetNames(i);
			if (sheetName.startsWith("runnerSet")){
				sheetNames.add(sheetName);
			}
		}

		String sheetName = DataConstants.RUNNERS;

		for (int rwCtr = 7; rwCtr <= excelAccess.getTotalRowCount(sheetName) - 7; rwCtr++) {
			if (excelAccess.getCellValue(sheetName, rwCtr, 1) != "") {
				runnerType = new RunnerType();
				if (excelAccess.getCellValue(sheetName, rwCtr, 2) != "") {
					runnerType.setActive(Integer.parseInt(excelAccess.getCellValue(sheetName, rwCtr, 2)));
				}
				if (excelAccess.getCellValue(sheetName, rwCtr, 3) != "") {
					runnerType.setRunnerName(excelAccess.getCellValue(sheetName, rwCtr, 3));
				}
				if (excelAccess.getCellValue(sheetName, rwCtr, 4) != "") {
					runnerType.setRunnerClass(excelAccess.getCellValue(sheetName, rwCtr, 4));
				}
				if (excelAccess.getCellValue(sheetName, rwCtr, 5) != "") {
					runnerType.setThreadSafe(Boolean.parseBoolean(excelAccess.getCellValue(sheetName, rwCtr, 5)));
				}
				if (excelAccess.getCellValue(sheetName, rwCtr, 6) != "") {
					runnerType.setCloneable(Boolean.parseBoolean(excelAccess.getCellValue(sheetName, rwCtr, 6)));
				}
				runnerTypeList.add(runnerType);
			}
		}
		for (int i = 1; i <= sheetNames.size(); i++) {
			String sheetName1 = excelAccess.getSheetNames(i);
			for (int rwCtr = 3; rwCtr <= excelAccess.getTotalRowCount(sheetName1); rwCtr++) {

				if (excelAccess.getCellValue(sheetName1, rwCtr, 1) != "") {
					runnerType = new RunnerType();
					runnerType.setRunnerName(excelAccess.getCellValue(sheetName1, rwCtr, 1));
					runnerTypeList.add(runnerType);
				}
			}
		}
		Iterator<RunnerType> runner1 = runnerTypeList.iterator();
		int i = 0;

		while (runner1.hasNext()) {
			RunnerType runner2 = runner1.next();

			loadXlsRunnerTypes.put("run" + i, runner2);
			i++;
		}
		return loadXlsRunnerTypes;

	}

}
