package org.s2n.ddt.agent;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.Runner2;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.excelreader.ReadReplacementStrings;
import org.s2n.ddt.excelreader.ReplacementString;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.AgentConfiguration;
import org.s2n.ddt.pojo.def.AgentInterface;
import org.s2n.ddt.pojo.def.AgentStatus;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.input.Param;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.util.CipherUtil;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.http.NetSend;

public class Agent2 implements AgentInterface {

	private static final Logger logger = Logger.getLogger(Agent2.class);

	private CommonInformation commonInfo;
	private static String appClearKey;

	private static CipherUtil appCiper;

	private static String urlTo = "http://localhost:8080/ddtCore/TestStepGet.jsp";
	private static int options = 0;
	private static HttpData hDat = new HttpData(urlTo, options, null);
	private static String agentRunnerId = System.getProperty("Agent.self.id", "1");

	private Map<String, Runner> runners = new HashMap<String, Runner>(2, 1);

	private BigInteger currentApp = null;
	private BigInteger currentFunc = null;

	/**
	 * key made by core just before
	 */
	// private Map<String, TestPlan> runningPlans = new HashMap<String,
	// TestPlan>();

	private static boolean mockRunner = false;

	private int dCnt;

	public static void refreshCipher() {
		// right now cipjer only used for replace so do not init if not used
		String enableReplaceS = UtlConf.getProperty("agent.replaceStr.enable", null);
		boolean enableReplace = LangUtils.isTrue(enableReplaceS, false);
		if (!enableReplace) {
			return;
		}
		String appEncKey = UtlConf.getProperty("appEncKey");
		try {
			Agent2.setReplacementMap(ReadReplacementStrings.getReplacementString());
			appClearKey = org.s2n.ddt.util.DdtMainCypher.dec(appEncKey);
			appCiper = new CipherUtil(appClearKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("Error in Agent2 refresh method " + e);

		}
	}

	public Agent2() {
		initRunners(true);
		initRunners(false);
		logger.warn("runners Map ->>" + runners.toString());
	}

	private void initRunners(boolean common) {
		String className = null;
		Runner runner = null;
		try {

			logger.info("Agent2 create");
			Class clz = null;
			// agent1runner2.className=org.s2n.ddt.Api.ApiRunner.ApiRunner
			// agent1runner2.name=API agent1runner2.active=1
			int cnt = 1;
			String agentPre = null;
			if (common) {
				agentPre = "agentCommon.";
			} else {
				agentPre = "agent" + agentRunnerId;
			}
			String nm = agentPre + "runner" + cnt + ".name";
			String runnerName = UtlConf.getProperty(nm, null);

			logger.debug("runnerName before while   " + runnerName);
			while (runnerName != null) {
				String active = agentPre + "runner" + cnt + ".active";
				logger.debug("active runner >>" + active);

				active = UtlConf.getProperty(active, null);

				logger.debug("active runner getting from property >>" + active);
				className = agentPre + "runner" + cnt + ".className";
				className = UtlConf.getProperty(className, null);
				logger.info("Runner class : " + className + ", active " + active + ", Sl no :" + agentRunnerId);
				if ("1".equals(active)) {
					try {
						// c = Class.forName(s);
						clz = Class.forName(className);
						runner = (Runner) clz.newInstance();
						runners.put(runnerName, runner);
					} catch (Throwable e) {
						logger.log(Level.WARN, "Agent instance :" + e + "] classname:" + className + "] agentName [" + runnerName + "]", e);
					}
				}
				cnt++;
				nm = agentPre + "runner" + cnt + ".name";
				runnerName = UtlConf.getProperty(nm, null);
				logger.debug("runnerName -->>" + runnerName);
				logger.debug("runners Map >>" + runners.toString());

			}
		} catch (Throwable e) {
			logger.log(Level.WARN, "Agent cont :" + e + "] classname:" + className + "]", e);
		}
	}

	public static void setCoreUrl(String s) {
		hDat.setUrl(s);
	}

	public static HttpData getCoreUrl() {
		return hDat;
	}

	public void startTestPlan(TestPlan plan) {
		BigInteger planAppId = null;
		BigInteger planFuncId = null;
		if (currentApp == null) {
			currentApp = planAppId;
			// String sql =
			// "select * from Replacement_Strings where appId = <currentApp> and level = 'AP'";
			// replacementMapApp.clear();
		}
		if (currentFunc == null) {
			// if (currentFunc == null || !currentApp.equals(planFuncId)) {
			currentApp = planAppId;
			// "select * from Replacement_Strings where appId = <currentApp> and level = 'AP'"
		}
	}

	public void startTestSuite(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		Collection<Runner> runrs = this.runners.values();
		Iterator<Runner> irnrs = runrs.iterator();
		while (irnrs.hasNext()) {
			Runner rnr = irnrs.next();
			if (rnr instanceof Runner2) {
				Runner2 rnr2 = (Runner2) rnr;
				rnr2.setCommonInformationProvider(getCommonInfo());
				rnr2.setRunnersInformation(runners);
			}
		}
		// BigInteger planAppId = null;
		// if (currentApp == null || !currentApp.equals(planAppId)) {
		// currentApp = planAppId;
		// String sql =
		// "select * from Replacement_Strings where appId = <currentApp> and level = 'SU'";
		// }
	}

	public void startTestCase(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromCache) {

	}

	private static Map<String, ReplacementString> replacementMap = new HashMap<String, ReplacementString>();

	public static Map<String, ReplacementString> getReplacementMap() {
		return replacementMap;
	}

	public static void setReplacementMap(Map<String, ReplacementString> replacementMap) {
		Agent2.replacementMap = replacementMap;
	}

	public void replaceParamData(TestStep testStep) {
		String enableReplaceS = UtlConf.getProperty("agent.replaceStr.enable", null);
		boolean enableReplace = LangUtils.isTrue(enableReplaceS, false);
		if (!enableReplace) {
			return;
		}
		List<TestParamData> paramDatas = testStep.getTestStepId().getTestParamDatas();
		List<TestParamData> testParamDatas = new ArrayList<TestParamData>();
		for (TestParamData paramData : paramDatas) {
			Param param = paramData.getParam();
			String actionParam = param.getParamName();
			// logger.info("encrypted param name : " + actionParam);
			if (actionParam.contains("[[[")) {
				String strToFind = null;
				try {
					int strtIndex = actionParam.indexOf("[[[");
					int endIndex = actionParam.indexOf("]]]");
					if ((strtIndex > -1) && (endIndex > strtIndex)) {
						strToFind = actionParam.substring(strtIndex + 3, endIndex);
						Map<String, ReplacementString> map = Agent2.getReplacementMap();
						ReplacementString rString = map.get(strToFind);
						if (rString.getEncrypted() == 1) {
							String decryptedVal = appCiper.decrypt(rString.getValue());
							rString.setValue(decryptedVal);
						}
						actionParam = actionParam.substring(0, strtIndex) + rString.getValue() + actionParam.substring(endIndex + 3);
					}
				} catch (Exception e) {
					logger.error("Error occured while encryptindg the value :" + e, e);
				}
			}
			if (actionParam != null && actionParam.length() != 0) {
				param.setParamName(actionParam);
			}
			paramData.setParam(param);
			testParamDatas.add(paramData);
		}
		testParamDatas.addAll(paramDatas);
		testStep.getTestStepId().setTestParamDatas(testParamDatas);
		// return testStep;
	}

	public void testStep(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary tstSmmryFromCache) {
		logger.debug("<< inside agent2 testStep >>");
		// java.util.Map dataToSend = new java.util.HashMap();

		dCnt++;
		long timeStart = System.currentTimeMillis();
		final long timeStart2 = timeStart;
		String actionName = null;
		if (step != null && step.getActions() != null && step.getActions().getActionName() != null) {
			actionName = step.getActions().getActionName();
		}
		logger.trace("Agent2 isMockRunner " + isMockRunner());
		String runnerName = null;
		if (mockRunner) {
			logger.info("Calling Mock");
			logger.debug("mock run ");
			stepResult.setComment("c " + dCnt);
			stepResult.setResult(true);// dCnt % 6 == 0
			Random rnd = new Random();
			int t = rnd.nextInt(230) + 40;
			LangUtils.sleep(t);

		} else {
			logger.trace("Calling Real Runner");
			Runner runner = null;
			String runnerFrom = "Test Case";
			runnerName = testCase.getRunner().getRunnerName();
			logger.debug("runnerName " + runnerName);
			// if step over riding the runner
			if (step.getRunner() != null && step.getRunner().getRunnerName() != null && step.getRunner().getRunnerName().length() != 0) {
				runnerName = step.getRunner().getRunnerName();
				logger.debug("Using step runner :" + runnerName + "]");
				runnerFrom = "Test Step";
			}
			runner = runners.get(runnerName);
			if (runner == null) {
				logger.info("runner not found will put fail msg :" + runnerName + "]");
				stepResult.setResult(false);
				stepResult.setComment("Runner not configured : " + runnerName);
				stepResult.setDetailMsgs("Runner from " + runnerFrom);
			} else {

				String replaceEnabled = UtlConf.getProperty("agent.replaceEnabled", "0");
				if ("1".equals(replaceEnabled)) {
					replaceParamData(step);
				}
				timeStart = System.currentTimeMillis();
				logger.info("runner-info :" + runner.toString() + " prepare time :" + (timeStart - timeStart2));
				runner.testStep(plan, suite, testCase, caseResult, step, stepResult, tstSmmryFromCache);

				logger.debug("result :" + stepResult.getResult() + ", comment :" + stepResult.getComment() + ", detailed message : "
						+ stepResult.getDetailMsgs());

			}
		}
		long timeEnd = System.currentTimeMillis();
		final long timTkn = timeEnd - timeStart;
		stepResult.setTimeTaken(timTkn);
		String dtlM = stepResult.getDetailMsgs();
		String dtlMFlatS = UtlConf.getProperty("agent.logs.detailWarnFaltten", "1");
		if(LangUtils.isTrue(dtlMFlatS, true)){
			dtlM = dtlM.replace('\n', ' ');
			dtlM = dtlM.replace('\r', ' ');
		}
		String log1 = "RunnerName (tm) :" + runnerName + "; action :" + actionName + "; time :" + timTkn + "; result :" + stepResult.getResult()
				+ "; case :" + testCase.getCaseName() + "; step :" + step.getTestStepId().getTestStepId() + "; cmnt :" + stepResult.getComment()
				+ "; dtl :" + dtlM + ";";
		if (runnerName != null && actionName != null) {
			if (runnerName.startsWith("HTTP") && !actionName.startsWith("submit") && timTkn > 10) {
				log1 = log1 + (", long action ");
			}
		}

		logger.warn(log1);
		// RunnerName (tm) :HTTP;Action :parameter; time :50;

	}

	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {

	}

	public String getConfigPath() {
		// unused
		return null;
	}

	public void setConfigPath(String configPath) {

	}

	public void setAgentConfig(AgentConfiguration agentConfiguration) {

	}

	public AgentStatus getAgentStatus() {
		// impl from thread pool in jsp for now
		return null;
	}

	/**
	 * done at task level now
	 */
	public void endPlan(TestPlan plan) {
		// String r = plan.getTestPlanRunName();
		// Progress status back to Core, or let Core ping us for status when it
		// wants
		// TestResultTmp r = new TestResultTmp();
		// r.setRunName(plan.getTestPlanRunName());
		// r.setPlanId(plan.getTestplanid().getTestplanid());
		// BigDecimal sceId = new BigDecimal(1);
		// r.setScenarioId(sceId);// TODO
		//
		// r.setStage(TestStage.PLAN);
		// r.setDetailError(null);// TODO
		//
		// r.setRunnerMsg("done at " + new java.util.Date());
		// r.setTime(timeEnd - timeStart);//TODO plan time

		// NetSend.sendObj((Serializable)r, hDat, "planEnd");

	}

	public static boolean isMockRunner() {
		return mockRunner;
	}

	public static void setMockRunner(boolean mockRunner) {
		Agent2.mockRunner = mockRunner;
	}

	public void endPlan(AgentRunResult agentRunResult) {
		//currently unused
		if (hDat.getUrl() != null) {
			Object rtn = NetSend.sendObj((Serializable) agentRunResult, hDat, "planEnd");
			logger.log(Level.INFO, "Send plan end " + agentRunResult.getTestPlan().getTestPlanRunName() + ", from " + agentRunResult.getAgentName() + "-"
					+ agentRunResult.getIp() + ":" + agentRunResult.getPort() + "-" + agentRunResult.getRunBy());
			logger.log(Level.INFO, "Rtn " + rtn);
		} else {
			logger.info("Core Url null, not posting back");
		}
	}

	public void endPlanDetail(TaskResult tsakResult) {
		//unused
		if (hDat.getUrl() != null) {
			Object rtn = NetSend.sendObj((Serializable) tsakResult, hDat, "planEnd");
			logger.log(Level.INFO, "Rtn " + rtn);
		} else {
			logger.info("Core Url null, not posting back");
		}
	}

	public static String getAgentRunnerId() {
		return agentRunnerId;
	}

	public static void setAgentRunnerId(String agentRunnerId) {
		Agent2.agentRunnerId = agentRunnerId;
	}

	public String runnersInfo() {
		StringBuilder sb = new StringBuilder().append("");
		Iterator<String> it = runners.keySet().iterator();
		while (it.hasNext()) {
			sb.append(it.next());
			if (it.hasNext()) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	public void fillAgentData(AgentRunResult agentRunResult) {

		String agentName = UtlConf.getProperty("agent" + agentRunnerId + "name", "unknown");
		agentRunResult.setAgentName(agentName);
		String ip = UtlConf.getProperty("agent" + agentRunnerId + "ip", "ip.0");
		agentRunResult.setIp(ip);
		String port = UtlConf.getProperty("agent" + agentRunnerId + "port", "65122");
		try {
			agentRunResult.setPort(Integer.parseInt(port));
		} catch (NumberFormatException e) {
			// okay to skip stacktrace : if try-catch over 1 line code and have
			// unique log message & print applicable values to help debug
			logger.info("fillAgentData - port not paseable as int :" + port + ", " + e);
		}

	}

	public CommonInformation getCommonInfo() {
		return commonInfo;
	}

	public void setCommonInfo(CommonInformation commonInfo) {
		this.commonInfo = commonInfo;
	}

	public static void main(String[] args) {
		Agent2 agt = new Agent2();
		System.out.println(agt.runnersInfo());
	}

}
