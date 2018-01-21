package org.s2n.ddt.excelreader;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.ObjectTypeId;
import org.s2n.ddt.service.InputService;
import org.s2n.ddt.util.ExcelAccess;
import org.s2n.ddt.utils.DataConstants;

public class ReadObjectTypesData {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReadObjectTypesData.class);

	private ExcelAccess excelAccess;

	private InputService inputService;

	public void setInputService(InputService inputService) {
		this.inputService = inputService;
	}

	public void setExcelAccess(ExcelAccess excelAccess) {
		this.excelAccess = excelAccess;
	}

	public void createObjectXLReader() {
		logger.info("started reading excel for Object Types");
		excelAccess.openWorkBook(new File(ReadExcel.getConfigFilePath()));
		String sheetName = DataConstants.OBJECT_TYPE_SHEET;

		for (int rwCtr = 2; rwCtr <= excelAccess.getTotalRowCount(sheetName); rwCtr++) {
			ObjectType objectType = new ObjectType();
			ObjectTypeId objecttypeid = new ObjectTypeId();

			objecttypeid.setDefaultActionId(new BigDecimal(excelAccess.getCellNumericValue(sheetName, rwCtr, 3)));
			objecttypeid.setDescription(excelAccess.getCellValue(sheetName, rwCtr, 2));
			objecttypeid.setObjectTypeName(excelAccess.getCellValue(sheetName, rwCtr, 1));

			populateObjectType(objecttypeid);
			objectType.setObjectTypeId(objecttypeid);
			logger.info("Object types read from excel : " + objectType);
			insertObjectTypes(objectType);
		}
	}

	private void populateObjectType(ObjectTypeId objectTypeId) {
		objectTypeId.setCreatedBy(System.getenv(DataConstants.DEFAULT_USER));
		objectTypeId.setCreatedDateTime(new Date());
		objectTypeId.setUpdatedBy(System.getenv(DataConstants.DEFAULT_USER));
		objectTypeId.setUpdatedDateTime(new Date());
	}

	private void insertObjectTypes(ObjectType objectType) {
		try {
			inputService.insertObjectsTypeDetails(objectType);
		} catch (Exception e) {
			logger.error("Object Type Details were not inserted properly due to : " + e, e);
		}
	}

}
