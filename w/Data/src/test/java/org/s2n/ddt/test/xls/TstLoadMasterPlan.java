package org.s2n.ddt.test.xls;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.excelreader.ReadObjectsData;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.input.Task;

public class TstLoadMasterPlan {
	static {
		org.s2n.ddt.util.DdtIoUtls.printCurrentFolderName();
	}
	private static final Logger logger = Logger.getLogger(TstLoadMasterPlan.class);

	public static void main(String[] args) throws InvalidFormatException, IOException {

		// Lane lane = new Lane();
		// ReadObjectsData.getAllObjectsFromExcel();
		// System.out.println(lane.getNumberOfIteration());
		// ReadObjectsData.getAllObjectsFromExcel();

		File path = new File(UtlConf.getProperty("xlsPath.path"));
		try {
			MasterPlan mplan = ReadObjectsData.loadMasterPlan(path);
			logger.info("nm " + mplan.getMasterPlanName());
			List<Lane> lanes = mplan.getLanes();
			logger.info("lanes" + lanes.size());
			Lane l = lanes.get(0);
			List<Task> tasks = l.getTasks();
			Task t = tasks.get(1);
			logger.info("t " + t.getTestPlanXlsPath());

			// junit -> total Lanes for w xls (one with 1 lane, one with 3)
			// Each lane check path of xls file for 1-3 tasks
		} catch (Throwable e) {
			logger.info("load master plan err " + e, e);
			;
		}

	}
}
