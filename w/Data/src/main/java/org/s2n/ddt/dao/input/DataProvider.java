package org.s2n.ddt.dao.input;

import java.io.File;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.input.ReusableData;
import org.s2n.ddt.util.CipherUtil;
import org.s2n.ddt.util.ExcelAccess;

public class DataProvider {
	private ExcelAccess access = new ExcelAccess();
	private CipherUtil cipherUtils;
	private ReusableData reusableData = new ReusableData();

	public DataProvider() {
		String s = "0123456789oiuhnm";
		try {
			cipherUtils = new CipherUtil(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String searchCrpt(String actionParam) {
		String decryptdVal = null;
		try {
			File wbCrpt = new File(UtlConf.getProperty("reusableDataFormat.path"));
			String sheetName = "Sheet1";
			access.openWorkBook(wbCrpt);
			int rowCnt;
			rowCnt = access.getTotalRowCount(sheetName);

			ReusableData reusableData = new ReusableData();
			for (int rowCtr = 0; rowCtr < rowCnt; rowCtr++) {
				reusableData.setValue(access.getCellValue(sheetName, rowCtr, 3));
				reusableData.setRealValue(access.getCellValue(sheetName, rowCtr, 4));
				boolean en = false;
				if (access.getCellValue(sheetName, rowCtr, 5).equals("1")) {
					en = true;
				} else {
					en = false;
				}
				reusableData.setEncrypted(en);
				if (reusableData.getValue().equals(actionParam)) {

					if (reusableData.isEncrypted() && reusableData.getRealValue() != "") {
						decryptdVal = cipherUtils.decrypt(reusableData.getRealValue());
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decryptdVal;
	}
}
