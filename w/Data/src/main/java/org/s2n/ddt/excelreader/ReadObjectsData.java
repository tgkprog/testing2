package org.s2n.ddt.excelreader;

import java.math.BigDecimal;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.Screen;
import org.s2n.ddt.pojo.input.IdentifierType;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.input.ObjectGroup;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.ParamGroupId;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.util.ExcelAccess;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.utils.DataConstants;

public class ReadObjectsData {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReadObjectsData.class);

	public List<Param> getObjectsFromExcel(String xlsPath) {

		ExcelAccess excelAccess = new ExcelAccess();
		excelAccess.openWorkBook(new File(xlsPath));
		String sheetName = DataConstants.OBJECTS;
		ArrayList<Param> paramsList = new ArrayList<Param>();

		for (int rwCtr = 2; rwCtr <= excelAccess.getTotalRowCount(sheetName) - 2; rwCtr++) {
			Application application = new Application();
			if (excelAccess.getCellValue(sheetName, 0, 5) != "") {
				application.setAppName(excelAccess.getCellValue(sheetName, 0, 5));
			}

			IdentifierType identifierType = new IdentifierType();
			if (excelAccess.getCellValue(sheetName, 0, 4) != "") {
				identifierType.setIndentifierTypeName(excelAccess.getCellValue(sheetName, 0, 4));
			}

			Screen screen = new Screen();
			screen.setApplication(application);
			if (excelAccess.getCellValue(sheetName, rwCtr, 2) != "") {
				screen.setScreenName(excelAccess.getCellValue(sheetName, rwCtr, 2));
			}

			ObjectGroup objectGroup = new ObjectGroup();
			ParamGroup paramGroup = new ParamGroup();
			ParamGroupId paramGroupId = new ParamGroupId();
			objectGroup.setScreen(screen);
			if (excelAccess.getCellValue(sheetName, rwCtr, 3) != "") {
				objectGroup.setObjectGroupName(excelAccess.getCellValue(sheetName, rwCtr, 3));
				paramGroupId.setParamGroupName(excelAccess.getCellValue(sheetName, rwCtr, 3));
			}
			paramGroup.setParamGroupId(paramGroupId);
			Objects objects = new Objects();
			Param param = new Param();
			ObjectsId objectsId = new ObjectsId();
			objects.setObjectGroup(objectGroup);
			if (excelAccess.getCellValue(sheetName, rwCtr, 1) != "") {
				objectsId.setIndentifier(excelAccess.getCellValue(sheetName, rwCtr, 1));
			}
			if (excelAccess.getCellValue(sheetName, rwCtr, 0) != "") {
				objectsId.setObjectName(excelAccess.getCellValue(sheetName, rwCtr, 0));
				param.setParamName(excelAccess.getCellValue(sheetName, rwCtr, 0));
			}
			param.setParamGroup(paramGroup);
			objects.setObjectsId(objectsId);
			objects.setIdentifierType(identifierType);
			param.setObjects(objects);
			paramsList.add(param);
		}
		logger.info("Objects/Param data from excel : " + paramsList);
		return paramsList;

	}

	public static MasterPlan loadMasterPlan(File path) throws Exception {
		MasterPlan masterPlan = new MasterPlan();
		ExcelAccess excelAccess = new ExcelAccess();
		

		// TestPlan testPlan = null;
		// ReadPlan readPlan = new ReadPlan();

		// System.out.println("testPlan" + testPlan);
		// task.setTestPlan(testPlan);
		// //taskObjects.add(task);
		

		if (!path.exists()) {
			logger.info("File not found :" + path.getAbsolutePath() + "]");
			masterPlan.setLoadErrorMsgs("File not found");
			masterPlan.setLoadedWithNoErrors(false);
			return masterPlan;
		}
		excelAccess.openWorkBook(path);
		loadMasterPlanSheet(excelAccess, masterPlan);
		loadLanes(masterPlan, excelAccess);
		logger.info("loaded master plan with lanes : " + masterPlan.getLanes().size());
		return masterPlan;
	}

	private static void loadLanes(MasterPlan masterPlan, ExcelAccess excelAccess) {
		String sheetName;
		int sheetNo = excelAccess.getNumberOfSheets();
		Lane lane = null;
		// lane sheets
		for (int j = 0; j < sheetNo; j++) {

			sheetName = excelAccess.getSheetNames(j);
			/*if (sheetName.startsWith("_")) {
				logger.info("Loading lanes, skipping _ sheet : " + sheetName);
				continue;
			}
			if (sheetName.startsWith("plan")) {
				logger.info("Loading lanes, skipping plan sheet : " + sheetName);
				continue;
			}*/
			if (!sheetName.startsWith("lane")) {
				logger.info("Loading lanes, skipping sheet : " + sheetName);
				continue;
			}
			// sheet with new Lane
			lane = new Lane();
			masterPlan.getLanes().add(lane);
			lane.setLaneNumberInPlan(masterPlan.getLanes().size());
			String laneNm = excelAccess.getCellValue(sheetName, 0, 1);
			lane.setLaneName(laneNm);
			String laneId = "";
			if (sheetName.length() > 4) {
				laneId = sheetName.substring(4);
			} else {
				masterPlan.addLoadWarning("Lane with no index at:" + j);
			}
			String clonS = excelAccess.getCellValue(sheetName, 0, 8);
			int clones = 0;
			if (clonS == null || clonS.length() == 0) {
				masterPlan.addLoadWarning("Clone not set in lane :" + j + " " + laneId);
			} else {
				clones = LangUtils.getInt(clonS, 0, "Clones not parsable for sheet :" + sheetName + ", using 0");
			}
			lane.setClones(clones);
			String sItertns = excelAccess.getCellValue(sheetName, 0, 4);
			int iterations = 0;
			if (sItertns == null || sItertns.length() == 0) {
				masterPlan.addLoadWarning("Lane Repeat not set in lane :" + j + " " + laneId);
			} else {
				iterations = LangUtils.getInt(sItertns, 0, "Lane Repeat not parsable for sheet :" + sheetName + ", using 0");
			}
			lane.setIterations(iterations);
			String sRunner = excelAccess.getCellValue(sheetName, 0, 12);
			lane.setRunnerType(sRunner);
			String sAgentName = excelAccess.getCellValue(sheetName, 0, 10);
			lane.setAgentName(sAgentName);
			String laneName = excelAccess.getCellValue(sheetName, 0, 1);
			lane.setLaneName(laneName);
			loadTasks(lane, excelAccess, sheetName);

		}
	}

	private static void loadMasterPlanSheet( ExcelAccess excelAccess, MasterPlan masterPlan) {
		String sheetNameforMasterPlan = DataConstants.MASTERPLAN;
		masterPlan.setMasterPlanName(excelAccess.getCellValue(sheetNameforMasterPlan, 0, 2));
		
	}
			
	private static void loadTasks(Lane lane, ExcelAccess excelAccess, String sheetName) {
		Task task = null;
		int rwCtr = 5;
		String taskSlNo = excelAccess.getCellValue(sheetName, rwCtr, 0);
		while (taskSlNo != null && taskSlNo.length() > 0) {
			task = new Task();
			task.setTaskId(new BigDecimal(rwCtr - 5));
			String tNm = excelAccess.getCellValue(sheetName, rwCtr, 6);
			task.setTaskName(tNm);
			task.setTestPlanXlsPath(excelAccess.getCellValue(sheetName, rwCtr, 1));
			task.setDataSet(excelAccess.getCellValue(sheetName, rwCtr, 2));
			int rpt = 0;
			try {
				rpt = (int) excelAccess.getCellNumericValue(sheetName, rwCtr, 4);
			} catch (Exception e) {
				logger.warn("task repeat not an int will set to 0 for task : " + tNm + ", lane :" + lane.getLaneName() + ", " + lane.getLaneNumberInPlan()  + ", e :" + e, e);
			}
			task.setRepeats(rpt);			
			String tagsToRun = excelAccess.getCellValue(sheetName, rwCtr, 5);
			task.setTagsToRun(tagsToRun );
			final String befrClones = excelAccess.getCellValue(sheetName, rwCtr, 8);
			final boolean beforeClones = LangUtils.isTrue(befrClones, false);
			task.setBeforeClones(beforeClones);
			lane.getTasks().add(task);
			
			rwCtr++;
			taskSlNo = excelAccess.getCellValue(sheetName, rwCtr, 0);
		}

	}

	
}
