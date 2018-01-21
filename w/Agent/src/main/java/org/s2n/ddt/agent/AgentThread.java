package org.s2n.ddt.agent;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.s2n.ddt.agent.lanes.LaneProcess;
import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanResult;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestScenarioResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.service.impl.TestDao;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.utils.DataConstants;

public final class AgentThread implements Runnable {
	// private static java.util.concurrent.Executors threadExe;
	private static final Logger logger = Logger.getLogger(AgentThread.class);
	private static java.util.concurrent.ThreadPoolExecutor threadExe;
	private String planRunName = null;

	private TestPlan plan = null;
	private ReadPlan planMK = null;
	private TaskResult taskResult;
	private LaneProcess laneProc;
	private CommonInformation commonInfo;
	private Agent2 agnt2 = null;

	// private TestSuite suite = null;
	// private TestCase testCase = null;
	// private TestStep step = null;
	// private TestStepResult stepResult = null;
	// private TestSuiteResultSummary tstSmmryFromCache = null;

	public AgentThread() {
		initDefault();
	}

	/**
	 * Constructor to re use other plan
	 * 
	 * @param planRunName
	 * @param planId
	 */
	public AgentThread(TestPlan plan, ReadPlan planMK, TaskResult tr, LaneProcess lp) {
		this.plan = plan;
		this.planMK = planMK;
		this.taskResult = tr;
		this.laneProc = lp;
		initDefault();
	}

	public AgentThread(String planRunName, BigDecimal planId) {
		this(planRunName, planId, null, null);
	}

	public AgentThread(String planRunName, BigDecimal planId, String path2, LaneProcess lp) {
		super();
		this.laneProc = lp;
		this.planRunName = planRunName;
		logger.info("inside Agent Thread constructor, planRunName :" + planRunName + ", path2 :" + path2 + ".");
		taskResult = new TaskResult();
		taskResult.setLaneCloneId(0);
		taskResult.setLaneRepeatId(0);
		taskResult.setTaskRepeatId(0);
		// goto Data source or
		// mock/Agent/src/main/java/org/s2n/ddt/Agent/PlanMock.java
		try {
			// String s = getXlsConfigFilePath();
			String path = null;
			if (path2 == null) {
				path = System.getProperty("TEST_CASE_XLS");
			} else {
				path = path2;
			}
			logger.info("Path for xls is :" + path);
			taskResult.setTestSuiteFilePath(path);
			if (path != null) {

				// Agent2 agent2 = new Agent2();
				planMK = new ReadPlan();

				plan = planMK.readPlanObj(path, null);

				logger.debug("read.");

			} else {
				logger.info("For Mock todo");
				// final String clz = System.getProperty("mockClass",
				// "org.s2n.ddt.agent.PlanMock");
				// Class c = Class.forName(clz);
				// taskResult.setTestSuiteFilePath(clz);
				// PlanMockI pm = (PlanMockI) c.newInstance();
				// pm.makeMockTestCase1();
				// plan = pm.createPlan(this.planRunName);
			}
			plan.setTestPlanRunName(planRunName);
			logger.info("planRunName >>>" + planRunName);

			// plan.getTestDebugInfoFile().mkdirs();
			// File fsnapShots = new File(plan.getTestDebugInfoFile(),
			// "snapShots");
			// fsnapShots.mkdir();

		} catch (InvalidFormatException e) {
			logger.error("AGT const load plan ex" + e, e);
		} catch (IOException e) {
			logger.error("AGT const load plan ex" + e, e);
		}
		initDefault();
	}

	public void init(TestPlan plan, ReadPlan planMK, TaskResult tr, LaneProcess lp) {
		this.plan = plan;
		this.planMK = planMK;
		this.taskResult = tr;
		this.laneProc = lp;

	}

	/**
	 * In general use pools from utils instead. This class is called from lane process now for threading
	 **/
	@Deprecated
	public static ThreadPoolExecutor getThreadExe() {
		if (threadExe == null) {
			/*
			 * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long ThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
			 * TimeUnit unit, BlockingQueue<Runnable> workQueue)
			 */

			LinkedBlockingQueue<Runnable> que = new LinkedBlockingQueue<Runnable>();
			threadExe = new ThreadPoolExecutor(1, 5, 100, TimeUnit.HOURS, que);
			logger.info("Inside ThreadPoolExecutor");
		}
		return threadExe;
	}

	public static String getInfo() {
		ThreadPoolExecutor tpe = AgentThread.getThreadExe();
		StringBuilder sb = new StringBuilder().append("Task Count :");
		sb.append(tpe.getTaskCount()).append(", completed count : ").append(tpe.getCompletedTaskCount()).append(", active :").append(tpe.getActiveCount());
		return sb.toString();
	}

	public static void start(AgentThread agntTh) {
		logger.info("inside start ");
		getThreadExe().execute(agntTh);

	}

	public void run() {
		process();
	}

	// same as run , name is more indicative of the fact that for master Lanes
	// we call from
	// outside without using this classes' thread pool
	// TODO refactor so loops are in seperate functions
	// TODO refactor to smaller functions for sub tasks
	public Map<String, Object> process() {
		RunResult rr = this.laneProc.getRunResult();
		plan.setTestPlanRunName(rr.getRunName());
		Map<String, Object> rtn = new HashMap<String, Object>();
		logger.debug("inside process");
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		String runnerFilesForThisTaskDir = "./reports";
		// String reportsRootDir =
		// "/war/reports";
		runnerFilesForThisTaskDir = UtlConf.getProperty("reports.home", runnerFilesForThisTaskDir) + "/r/";
		File fRunnerFilesForThisTaskDir = new File(runnerFilesForThisTaskDir, plan.getTestPlanRunName() + "/" + sdf.format(dt));
		logger.debug("f Runner Files For This TaskDir :" + fRunnerFilesForThisTaskDir.getPath());
		fRunnerFilesForThisTaskDir.mkdirs();
		logger.debug("Function :" + plan.getTestPlanId().getFunctionals().get(0).getFunctionalName());
		// TODO move this to run result and pass that to runners
		plan.setTestDebugInfoFile(fRunnerFilesForThisTaskDir);
		agnt2.setCommonInfo(commonInfo);
		logger.debug(" test Plan ID :" + plan.getTestPlanRunName());
		boolean warnLogger = LangUtils.isTrue(UtlConf.getProperty("logging.warn.agent2", "0"), false);
		boolean dbSave = LangUtils.isTrue(UtlConf.getProperty("dbSave", "0"), false);
		agnt2.startTestPlan(plan);

		TestSuiteResultSummary testSuiteResultSummary = null;

		// todo move this out
		taskResult.setAgentOS(System.getProperty("os.name") + "-" + System.getProperty("os.version"));
		taskResult.setTestPlan(plan);
		taskResult.setStartTime(new Date());

		TestPlanResult planResult = new TestPlanResult();
		TestScenarioResult testScenarioResult = new TestScenarioResult();

		List<Functional> functionalList = plan.getTestPlanId().getFunctionals();
		List<Functional> functionals = new ArrayList<Functional>();
		Iterator<Functional> iter1 = functionalList.iterator();

		List<TestScenarioResult> testScenarioResults = planResult.getTestScenarioResults();

		List<TestSuiteResult> testSuiteResults = testScenarioResult.getSuiteResultsList();
		List<Feature> featuresLists1 = new ArrayList<Feature>();
		// For Functional
		while (iter1.hasNext()) {
			Functional functional = iter1.next();
			List<Feature> featureList = functional.getFeatures();
			Iterator<Feature> iter2 = featureList.iterator();
			logger.debug("For Functional ++++");
			// For Features
			while (iter2.hasNext()) {
				Feature feature = iter2.next();
				List<TestScenario> testScList = feature.getTestScenarios();
				Iterator<TestScenario> iter3 = testScList.iterator();
				logger.debug("For Features ++++");
				// For TestScenario
				while (iter3.hasNext()) {
					TestScenario testScenario = iter3.next();

					List<TestSuite> suiteList = testScenario.getTestSuites();
					Iterator<TestSuite> iter4 = suiteList.iterator();
					logger.debug("For TestScenario ++++");
					// For TestSuite
					while (iter4.hasNext()) {

						TestSuite suite = iter4.next();
						TestSuiteResult testSuiteResult = new TestSuiteResult();
						long beforeSuiteStart = System.currentTimeMillis();
						testSuiteResult.setTestSuite(suite);
						agnt2.startTestSuite(plan, suite, testSuiteResultSummary);

						int caseCnt = suite.getTestSuiteId().getTestCases().size();
						int currToTest = 0;
						int tstCaseCntr = 0;

						List<TestCaseResult> testCaseResults = testSuiteResult.getCaseResultsList();
						logger.debug("For TestSuite ++++");
						// For TestCase
						logger.debug("Suite size " + suite.getTestSuiteId().getTestCases().size());
						for (; tstCaseCntr < caseCnt; tstCaseCntr++) {

							boolean failed = false;
							TestCase tcase = suite.getTestSuiteId().getTestCases().get(tstCaseCntr);

							TestCaseResult testCaseResult = new TestCaseResult();
							testCaseResult.setTestCaseId(tcase.getTestCaseId());
							testCaseResult.setTestCaseName(tcase.getCaseName());
							testCaseResult.setDescription(tcase.getDescription());
							testCaseResult.setPlanRunStartDateTime(dt);
							testCaseResult.setTestCase(tcase);
							// int skipCount = 0;
							int tcrId = Integer.parseInt(UtlConf.randomNum());

							if (tcase.getActive() == 0) {
								// skipCount = skipCount+1;
								testCaseResult.setActive(0);
								testCaseResult.setTestCaseResult("Skipped");
								testCaseResult.setRunStatus(DataConstants.USER_SKIPPED);
								testCaseResult.setTimeDuration((double) 0);
								testCaseResults.add(testCaseResult);
								continue;
							} else {

								String stepErrorMsg = "";
								String stepSuccessMsg = "";
								int passCount = 0;
								int failCount = 0;
								// long beforeStart = System.currentTimeMillis();
								logger.debug("For TestCase ++++");

								// For TestSteps
								List<TestStepResult> testStepResults = new ArrayList<TestStepResult>();
								logger.debug("tcase.getTestSteps().size() ==" + tcase.getTestSteps().size());
								logger.debug("tcase.getTestSteps().size() ==" + tcase.getCaseName());
								for (int tstStepCtr = 0; tstStepCtr < tcase.getTestSteps().size(); tstStepCtr++) {
									logger.debug("For TestSteps ++++");

									logger.log(Level.INFO, "Case :" + tstCaseCntr + ", tst step :" + tstStepCtr + ", currToTest " + currToTest);
									try {
										TestStep step = tcase.getTestSteps().get(tstStepCtr);
										logger.debug("step >>>>" + step.getTestStepId().getStepName());
										TestStepResult testStepResult = new TestStepResult();
										// testStepResult.setTestStep(step);//memory
										// need this? TODO
										if (step.getActions() != null) {
											logger.log(Level.DEBUG, "getActionname :" + step.getActions().getActionName());

										} else {
											logger.info("Action obj null");
										}
										// final id can be be null but
										// containing hierarchy should be non
										// null for every step
										String obj1Id = step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId()
												.getIndentifier();
										testStepResult.setTestStep(step);
										testStepResult.setTestCaseResultId(tcrId);
										if ((obj1Id == null) || (obj1Id.length() == 0) || obj1Id.equalsIgnoreCase("NoObjectFound")) {
											String objRealName = step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects().getObjectsId()
													.getObjectName();
											logger.debug(objRealName + " not found in Object Map");
											testStepResult.setComment(objRealName + " not found in Object Map");
											testStepResult.setResult(false);
											testStepResult.setTimeTaken(1);

										} else {
											agnt2.testStep(plan, suite, tcase, testCaseResult, step, testStepResult, testSuiteResultSummary);
											logger.debug("Called the respective Runner & testStepResult is =" + testStepResult.getResult());

										}
										testStepResults.add(testStepResult);
										// Calling DB Part to save TestStep,
										// Needs to check for the neccessity of
										// saving test step & at agent end.
										if (dbSave) {
											dbSaveTestResult(testStepResult);
										}

										if (!testStepResult.getResult()) {
											failed = true;
											stepErrorMsg = testStepResult.getComment();
											// Added for Performance testing
											if (warnLogger) {
												logger.warn("TestCase Failed:" + tcase.getCaseName() + " At TestStep No:" + tstStepCtr
														+ " Failure comment:\n" + testStepResult.getComment());
												logger.warn("TestStep Detailed Msg:" + testStepResult.getDetailMsgs());
											}

											for (tstStepCtr++; tstStepCtr < tcase.getTestSteps().size(); tstStepCtr++) {
												step = tcase.getTestSteps().get(tstStepCtr);
												testStepResult = new TestStepResult();
												testStepResult.setTestCaseResultId(tcrId);
												testStepResult.setResult(false);
												testStepResult.setComment("Skipping step as predecssar failed");
												testStepResult.setTestStep(step);
												testStepResults.add(testStepResult);
												logger.log(Level.INFO, "skipping step as predecssar failed case  step :" + (tstStepCtr + 1)
														+ ", currToTest " + currToTest);
												// Calling DB Part to save
												// TestStep
												if (dbSave) {
													dbSaveTestResult(testStepResult);
												}
											}
										} else {
											stepSuccessMsg = testStepResult.getComment();
										}
									} catch (Exception e) {
										logger.warn("step error " + e, e);
									}
								}// steps
								if ((stepErrorMsg != null) && !stepErrorMsg.equals("")) {
									testCaseResult.setDescription(stepErrorMsg);
									testCaseResult.setErrorMsg(stepErrorMsg);

								} else {
									testCaseResult.setDescription(stepSuccessMsg);

								}
								testCaseResult.setTestStepResultsList(testStepResults);
								Iterator<TestStepResult> itr1 = testStepResults.iterator();
								while (itr1.hasNext()) {
									TestStepResult testStepRslt = itr1.next();
									if (testStepRslt.getResult()) {
										passCount++;
									} else {
										failCount++;

									}
								}

								if (testStepResults.size() == passCount) {
									testCaseResult.setRunStatus(DataConstants.PASSED_AFTER_RUN);
									testCaseResult.setTestCaseResult("Pass");

								} else if (failCount > 0) {
									testCaseResult.setRunStatus(DataConstants.FAILED_AFTER_RUN);
									testCaseResult.setTestCaseResult("Failed");

								}

								testCaseResult.setTotalCount(testStepResults.size());
								testCaseResult.setPassCount(passCount);
								testCaseResult.setFailCount(failCount);
								testCaseResult.setDescription(tcase.getDescription());
								testCaseResult.setTimeDuration((double) tCaseDuration(testCaseResult));
								// Added for performance testing
								if (warnLogger) {
									logger.warn("TestCase Description:" + tcase.getDescription() + " pass-cnt :" + passCount + "; fail-cnt :" + failCount
											+ " case-Duration:" + tCaseDuration(testCaseResult));
									// logger.warn("-------");
								}
								Date eDt = new Date();
								testCaseResult.setPlanRunEndDateTime(eDt);
								// dbSave
								if (dbSave) {
									dbSaveTestResult(testCaseResult, tcrId);
								}

								agnt2.endTestCase(plan, suite, tcase, null, null);
								testCaseResults.add(testCaseResult);

							}
						}
						testSuiteResult.setCaseResultsList(testCaseResults);
						List<TestCaseResult> testCaseRslt = testSuiteResult.getCaseResultsList();
						Iterator<TestCaseResult> itr2 = testCaseRslt.iterator();

						while (itr2.hasNext()) {
							TestCaseResult testCasRsl = itr2.next();
							if (testCasRsl.getRunStatus() == 0) {
								testSuiteResult.setSkipCount(1);
							} else if (testCasRsl.getRunStatus() == 1) {
								testSuiteResult.setPassCount(1);
							} else if (testCasRsl.getRunStatus() == 2) {
								testSuiteResult.setFailCount(1);
							} else if (testCasRsl.getRunStatus() == 3) {
								testSuiteResult.setNotExecuted(1);
							}
						}
						long afterSuiteEnd = System.currentTimeMillis();
						double d1 = afterSuiteEnd - beforeSuiteStart;
						testSuiteResult.setTimeDuration(d1);
						testSuiteResult.setTotalCount(testCaseRslt.size());
						testSuiteResult.setSuiteName(suite.getTestSuiteId().getSuiteName());
						testSuiteResult.setSuiteId(suite.getTestSuiteId().getTestSuiteId());
						testSuiteResult.setSuitSatus("Complete");
						testSuiteResults.add(testSuiteResult);

					}
					testScenarioResult.setSuiteResultsList(testSuiteResults);
					testScenarioResults.add(testScenarioResult);
					feature.setTestScenariosResult(testScenarioResults);

				}
				List<TestSuiteResult> suitResults = testScenarioResult.getSuiteResultsList();
				Iterator<TestSuiteResult> iterator2 = suitResults.iterator();
				while (iterator2.hasNext()) {
					TestSuiteResult suitResult = iterator2.next();
					if (suitResult != null) {
						feature.setPassCount(suitResult.getPassCount());
						feature.setFailCount(suitResult.getFailCount());
						feature.setTotalCount(suitResult.getTotalCount());
						feature.setSkipCount(suitResult.getSkipCount());
					}
					featuresLists1.add(feature);
				}
				logger.debug("feature Cases >>" + feature.getTotalCount());
			}
			functional.setFeatures(featuresLists1);
			List<Feature> featureResults = functional.getFeatures();
			Iterator<Feature> iterator3 = featureResults.iterator();
			while (iterator3.hasNext()) {
				Feature featureResult = iterator3.next();
				if (featureResult != null) {
					functional.setPassCount(featureResult.getPassCount());
					functional.setFailCount(featureResult.getFailCount());
					functional.setTotalCount(featureResult.getTotalCount());
					functional.setSkipCount(featureResult.getSkipCount());
				}
			}
			functionals.add(functional);
			logger.debug("functional Cases>>" + functional.getTotalCount());
		}// features

		// testScenarioResults.add(testScenarioResult);//TODO check
		planResult.setTestScenarioResults(testScenarioResults);
		planResult.setFunctionalList(functionals);
		planResult.setTestplanid(plan.getTestPlanId().getTestPlanId());
		planResult.setTestPlanRunName(plan.getTestPlanId().getPlanName());
		taskResult.setEndTime(new Date());
		taskResult.setTestPlanResult(planResult);
		taskResult.setTestPlan(plan);

		String agentRunnerId = System.getProperty("Agent.self.id", "1");
		taskResult.setAgentId(new BigDecimal(agentRunnerId));
		String nm = "agent" + agentRunnerId + "name";
		String agentname = UtlConf.getProperty(nm, "tushar");
		taskResult.setAgentName(agentname);
		nm = "agent" + agentRunnerId + "ip";
		taskResult.setIp(UtlConf.getProperty(nm, "localhost"));
		nm = "agent" + agentRunnerId + "port";
		taskResult.setPort(Integer.parseInt(UtlConf.getProperty(nm, "8180")));

		logger.info("After setting the agent Name :" + taskResult.getAgentName() + "; ip :" + taskResult.getIp() + ", port :" + taskResult.getPort() + ".");

		/**
		 * Need two path 1 for main Report & 2nd for TestCase so removing the 2nd parameter from File
		 */
		StringBuilder sb = new StringBuilder().append("Completed Lane ");
		if (this.taskResult != null) {
			sb.append(" :");
			sb.append(taskResult.getLaneName());
			sb.append(", clone :");
			sb.append(taskResult.getLaneCloneId());

			sb.append(", repeat ");
			sb.append(taskResult.getLaneRepeatId());

			sb.append(", task :");
			sb.append(taskResult.getTaskName());
			sb.append(", repeat :");
			sb.append(taskResult.getTaskRepeatId());
		}
		logger.info(sb);
		// DB
		if (dbSave) {
			TestDao.updateTaskResult(taskResult);
		}
		if (LangUtils.isTrue(UtlConf.getProperty("postBack", "0"), false)) {
			AgentPools.endPlanDetailed(rr, taskResult);
		}
		logger.info("done  task (AgentThread process)");
		return rtn;
	}

	public void dbSaveTestResult(TestStepResult testStepResult) {
		if (LangUtils.isTrue(UtlConf.getProperty("DB.save.stepResult", "0"), true)) {
			int tsrId = TestDao.insertTestSuiteResult(testStepResult);
			testStepResult.setTestStepResultId(tsrId);
		}
	}

	public void dbSaveTestResult(TestCaseResult testCaseResult, int tcId) {
		if (LangUtils.isTrue(UtlConf.getProperty("DB.save.caseResult", "0"), true)) {
			int tcrId = TestDao.insertTestCaseResult(testCaseResult, 0, taskResult.getTaskResultId());
			testCaseResult.setTestCaseResultId(tcrId);
			TestDao.updateTestStepResult(testCaseResult.getTestCaseResultId(), tcId);
			// System.out.println("Task Result Id"+taskResult.getTaskResultId());
		}
	}

	public void getCoreDetails() throws InvalidFormatException, IOException {

		/*
		 * ReadObjectsData readObjectsData = new ReadObjectsData(); Map<String, Object> addObj = new HashMap<String, Object>();
		 * addObj=ReadObjectsData.getAllObjectsFromExcel(); System.out.println("addObj..........."+addObj);
		 */

	}

	public AgentRunResult getAgent(AgentRunResult runReslt) {
		return runReslt;
	}

	public static final String getVersion() {
		return "1.6.10e";
	}

	// Added for performance testing
	public long tCaseDuration(TestCaseResult caseResult) {
		long caseDuration = 0L;

		for (TestStepResult stepResult : caseResult.getTestStepResultsList()) {
			caseDuration += stepResult.getTimeTaken();
		}
		return caseDuration;
	}

	public static void main(String[] args) throws InvalidFormatException, IOException {

		AgentThread agentThread = new AgentThread();
		agentThread.getCoreDetails();
		// ReadObjectsData.getAllObjectsFromExcel();
		// System.out.println(lane.getNumberOfIteration());
		// ReadObjectsData.readTestPlan();

	}

	public TaskResult getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(TaskResult taskResultInstance) {
		this.taskResult = taskResultInstance;
	}

	public void setCommonInfo(CommonInformation commonInfo) {
		this.commonInfo = commonInfo;

	}

	public void initDefault() {
		agnt2 = new Agent2();

	}
}
