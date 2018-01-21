package org.s2n.ddt.excelreader;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.s2n.ddt.util.ExcelAccess;
import org.s2n.ddt.utils.DataConstants;

public class GetObjs {
	private ExcelAccess excelAccess;

	public GetObjs(ExcelAccess excelAccess) {
		this.excelAccess = excelAccess;
	}

	public String getObjectPath(ExcelAccess excelAccess, String formName) throws InvalidFormatException, IOException {
		String objectPath;
		String curValue;
		int tcStartRow = 1;
		int tcEndRow = 1;
		int objectMaxRows;
		String sheet = "_objects";
		int rowCtr;
		String foundStart = "false";
		String foundEnd = "false";
		String foundEndSuite = "false";
		String object_found = "False";
		String realPath = null;
		while (true) {
			// For the current test case, get the input parameter values and the
			// endrow.
			for (rowCtr = tcStartRow; rowCtr <= excelAccess.getTotalRowCount(sheet); rowCtr++) {
				curValue = excelAccess.getCellValue(sheet, rowCtr, DataConstants.DEFAULT_VAL);
				if (foundStart.equals("false")) {
					if (curValue.equals("ObjectMap Start")) {
						// Set the next row as the Start line number of test
						// case and break the loop
						tcStartRow = rowCtr + 1;
						foundStart = "true";
					}
				}
				if (foundEndSuite.equals("false")) {
					if (curValue.equals("Object Mapping Ends")) {
						foundEndSuite = "true";
						objectMaxRows = rowCtr + 1;
					}
				}
			}
			if (foundEndSuite.equals("false")) {

				break;
			}
			if (foundStart.equals("false")) {
				break;
			}

			// For the current test case, get the input parameter values and the
			// endrow.
			for (rowCtr = tcStartRow; rowCtr <= excelAccess.getTotalRowCount(sheet); rowCtr++) {

				curValue = excelAccess.getCellValue(sheet, rowCtr, DataConstants.DEFAULT_VAL);
				if (curValue.equals("ObjectMap End")) {
					tcEndRow = (rowCtr - 1);
					foundEnd = "true";
					break;
				}
				String formNameColumn = excelAccess.getCellValue(sheet, rowCtr, DataConstants.DEFAULT_VAL);
				objectPath = excelAccess.getCellValue(sheet, rowCtr, DataConstants.DEFAULT_VAL + 1);

				if (formNameColumn.equals(formName)) {

					object_found = "True";
					realPath = objectPath;
				}
			}
			if (object_found.equals("False")) {
				realPath = "NoObjectFound";
			}
			if (foundEnd.equals("False")) {
				break;
			}
			tcStartRow = (tcEndRow + 2);
			foundStart = "false";
			foundEnd = "false";

		}
		return realPath;

	}
}