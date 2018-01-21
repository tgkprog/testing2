package org.s2n.ddt.test.xls;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.s2n.ddt.excelreader.ReadObjectsData;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.input.Task;

public class LoadMasterPlan {
	static{
		org.s2n.ddt.util.DdtIoUtls.printCurrentFolderName();
	}
	private static final Logger logger = Logger.getLogger(LoadMasterPlan.class);
	public static void main(String[] args) throws InvalidFormatException, IOException {

		// Lane lane = new Lane();
		// ReadObjectsData.getAllObjectsFromExcel();
		// System.out.println(lane.getNumberOfIteration());
		// ReadObjectsData.getAllObjectsFromExcel();
		
		File path = new File("C:/apps/xamps/exi/d/DdtMasterPlan.xls");
		try {
			MasterPlan mplan = ReadObjectsData.loadMasterPlan(path);
			logger.info("nm " + mplan.getMasterPlanName());
			List<Lane> lanes = mplan.getLanes();
			Lane l = lanes.get(0);
			List<Task> tasks = l.getTasks();
			Task t = tasks.get(1);
			logger.info("t " + t.getTestPlanXlsPath());
		} catch (Exception e) {
			logger.info("load master plan err " + e, e);;
		}

	}
}
