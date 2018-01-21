package org.s2n.ddt.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.dao.impl.LaneDaoImpl;
import org.s2n.ddt.dao.impl.RunResultDaoImpl;
import org.s2n.ddt.dao.impl.TaskDaoImpl;
import org.s2n.ddt.dao.impl.TaskResultDaoImpl;
import org.s2n.ddt.dao.impl.TestCaseResultDaoImpl;
import org.s2n.ddt.dao.impl.TestStepResultDaoImpl;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestStepResult;

public class TestDao {

	private static final Logger logger = Logger.getLogger(TestDao.class);
	private static ClassPathXmlApplicationContext context;

	static {
		context = new ClassPathXmlApplicationContext("test.xml");
	}

	public static int insertLane(Lane lane) {
		LaneDaoImpl laneDao = (LaneDaoImpl) context.getBean("laneDao");
		int laneId = 0;
		try {
			laneId = laneDao.insertLane(lane);
			System.out.println("Lane Row Key:" + laneId);
		} catch (DataAccessException e) {
			logger.error("error while inserting the Lane" + e);
		}

		List<Task> tasks = lane.getTasks();
		for (int taskId = 0; taskId < tasks.size(); taskId++) {
			tasks.get(taskId).setLaneId(laneId);
			int taskNo = insertTask(tasks.get(taskId), tasks.get(taskId).getRepeats(), lane.getIterations(), lane.getClones());
			tasks.get(taskId).setTaskId(new BigDecimal(taskNo));
		}
		return laneId;
	}

	public static int insertRunResult(RunResult rResult) {
		int rrId = 0;
		RunResultDaoImpl rrDaoImpl = (RunResultDaoImpl) context.getBean("rResultDao");
		try {
			rrId = rrDaoImpl.InsertRunResult(rResult);
		} catch (DataAccessException e) {
			logger.error("error while inserting the RunResult" + e);
		}
		return rrId;
	}

	public static int insertTask(Task task, int tRepeat, int lRepeat, int lClone) {
		int taskId = 0;
		TaskDaoImpl taskDao = (TaskDaoImpl) context.getBean("taskDao");
		try {
			taskId = taskDao.insertTask(task, tRepeat, lRepeat, lClone);
			System.out.println("Taks Row Key:" + taskId);

		} catch (DataAccessException e) {
			logger.error("error while inserting the Task" + e);
		}
		return taskId;

	}

	public static int insertTaskResult(TaskResult taskResult, int tRepeat, int lRepeat, int cloneNo, int taskId) {
		int taskResultId = 0;
		TaskResultDaoImpl taskResultDao = (TaskResultDaoImpl) context.getBean("tResultDao");
		try {
			taskResultId = taskResultDao.insertTaskResult(taskResult, tRepeat, lRepeat, cloneNo, taskId);
			System.out.println("Task Result Row Key:" + taskResultId);

		} catch (DataAccessException e) {
			logger.error("error while inserting the TaskResult" + e);
		}
		return taskResultId;
	}

	public static void updateTaskResult(TaskResult taskResult) {
		TaskResultDaoImpl taskResultDao = (TaskResultDaoImpl) context.getBean("tResultDao");
		try {
			logger.info("Updating the TaskResult table");
			taskResultDao.udpateTaskResult(taskResult);

		} catch (DataAccessException e) {
			logger.error("error while inserting the TaskResult" + e);
		}
	}

	public static int insertTestSuiteResult(TestStepResult tsr) {

		String detailMsg = "";
		String objName, stepValue = "", runner = "", actionName = "", stepParam, cmnt = "", status;

		SimpleDateFormat sdf = new SimpleDateFormat("S");
		if (tsr.getResult() == true) {
			status = "PASS";
		} else {
			status = "FAIL";
		}

		detailMsg = tsr.getDetailMsgs();
		if (tsr.getDetailMsgs() == null) {
			detailMsg = "";
		}

		TestStep tStep = tsr.getTestStep();

		if (tStep != null) {
			if (tStep.getRunner() != null) {
				runner = tStep.getRunner().getRunnerName();
			}
			if (tStep.getActions() != null) {
				actionName = tStep.getActions().getActionName();
			}

			if (tStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier() != null) {
				objName = tStep.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId().getIndentifier();
				if (tStep.getTestStepId().getTestParamDatas().get(0).getTestParamDataId().getParamValue() != null) {
					stepValue = tStep.getTestStepId().getTestParamDatas().get(0).getTestParamDataId().getParamValue();
				} else {
					stepValue = "";
				}
			} else {
				objName = "";
			}

			if (tStep.getStepParam() != null) {
				stepParam = tStep.getStepParam();
			} else {
				stepParam = "";
			}

		} else {
			objName = "";
			stepParam = "";
			stepValue = "";
			actionName = "";
		}

		if (runner.length() == 0 || runner == null) {
			runner = "";
		}

		cmnt = tsr.getComment();
		if (tsr.getComment() == null) {
			cmnt = "";
		}

		// testStepResultDao
		TestStepResultDaoImpl tsrDaoImpl = (TestStepResultDaoImpl) context.getBean("testStepResultDao");
		int rtn = 0;
		try {
			rtn = tsrDaoImpl.insertTSResult(tsr, runner, objName, actionName, stepParam, stepValue, status, cmnt, detailMsg,
					sdf.format(tsr.getTimeTaken()));
		} catch (DataAccessException e) {
			logger.error("error while inserting the TestStepResult" + e);
		}
		return rtn;
	}

	public static int insertTestCaseResult(TestCaseResult tcr, int tcId, int tsId) {
		TestCaseResultDaoImpl tcrDaoImpl = (TestCaseResultDaoImpl) context.getBean("testCaseResultDao");
		int testCaseResultId = 0;
		try {
			testCaseResultId = tcrDaoImpl.insertTestCaseResult(tcr, tcId, tsId);
		} catch (DataAccessException e) {
			logger.error("error while inserting the TestCaseResult" + e);
		}
		return testCaseResultId;
	}

	public static void updateTestStepResult(int tcrId, int tsrId) {
		TestStepResultDaoImpl tsrDaoImpl = (TestStepResultDaoImpl) context.getBean("testStepResultDao");
		try {
			tsrDaoImpl.updateTCResultIdByTSResultId(tcrId, tsrId);
		} catch (DataAccessException e) {
			logger.error("error while updating the TestStepResult" + e);
		}
	}
}

/*
 * private static void insert(LaneDao lanedb) { Lane lane = new Lane();
 * lane.setAgentName("Test2"); lane.setClones(1); lane.setIterations(2);
 * //lane.setLaneId(1); lane.setLaneName("TEst Lane3");
 * lane.setLaneUserName("TEst User2"); //lane.setRunnerType("SEL,API,HTTP");
 * //test inserting the DB try { System.out.println(lanedb.insertLane(lane)); }
 * catch (DataAccessException e) { // TODO Auto-generated catch block
 * e.printStackTrace(); } } private static void update(LaneDao lanedb) { Lane
 * lane = new Lane(); lane.setAgentName("Test"); lane.setClones(1);
 * lane.setIterations(2); lane.setLaneId(1); lane.setLaneName("TEst Lane");
 * lane.setLaneUserName("TEst User"); lane.setRunnerType("SEL,API,HTTP"); try {
 * lanedb.updateLane(lane); } catch (DataAccessException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } } public static void
 * main(String[] args) { //TestDao.insert(lanedb); //TestDao.update(lanedb); try
 * { ClassPathXmlApplicationContext context = new
 * ClassPathXmlApplicationContext("test.xml"); LaneDao lanedb = (LaneDao)
 * context.getBean("lane");
 * System.out.println("Agent Name:"+lanedb.getAgentName(
 * 1)+"\nUserName:"+lanedb.getUserName(1)); Lane lane = lanedb.getLaneById(1);
 * System.out.println("Lane Obj:"+lane.getRunnerType()); } catch
 * (DataAccessException e) { System.out.println("Erro in Main Thread:"+e); } }
 */