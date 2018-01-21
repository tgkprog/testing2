package org.s2n.ddt.common;

import java.awt.AWTException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;
import org.s2n.ddt.util.RobotHelper;
import org.s2n.ddt.util.threads.DdtPools;
import org.s2n.ddt.utils.io.ReadStreamAsync;

public class CoreRunner implements Runner {
	private static final Logger logger = Logger.getLogger(CoreRunner.class);
	private static volatile Map<String, Method> methods = new HashMap<String, Method>(20);

	
	public void startTestPlan(TestPlan obj) {
		

	}

	
	public void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		// String s = UtlConf.getProperty("a");
		logger.debug("not implemnted CoreRunner Plan run: " + plan.getTestPlanRunName());

	}

	
	public void startTestCase(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromCache) {
		

	}

	
	public void testStep(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary tstSmmryFromCache) {
		stepResult.setResult(false);// make it true in each method if it passes
		String action = step.getActions().getActionName();
		Method actionMethd = null;
		caseResult.setValidationByAgent(true);
		synchronized (methods) {
			actionMethd = methods.get(action);
			if (actionMethd == null) {
				try {
					actionMethd = this.getClass().getMethod(action, TestPlan.class, TestSuite.class, TestCase.class, TestCaseResult.class, TestStep.class,
							TestStepResult.class, TestSuiteResultSummary.class);// <String,
					methods.put(action, actionMethd);
				} catch (SecurityException e) {
					logger.warn("SE Err " + e, e);
					// TODO add stack trace to detail, based on app or test plan
					// option
					stepResult.setDetailMsgs("Java security, bad setup ERR :" + e);
					stepResult.setComment("Could not process action :[" + action + "] java error.");
				} catch (NoSuchMethodException e) {
					logger.warn("No method Err :" + e, e);
					stepResult.setDetailMsgs("Bad action - no corresponding impl ERR :" + e);
					stepResult.setComment("Could not process action :[" + action + "] not implemented this.");
				}
			}
			if (actionMethd != null) {
				try {
					logger.info("Action :" + action);
					actionMethd.invoke(this, plan, suite, testCase, caseResult, step, stepResult, tstSmmryFromCache);
				} catch (Exception e) {
					logger.warn("Calling action Err :" + e, e);
					stepResult.setDetailMsgs("Bad action - ERR :" + e);
				}
			}
		}

	}

	
	public void endTestCase(TestPlan obj, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult testStep) {
	}

	public void keyPress(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary tstSmmryFromCache) {

	}

	public void snooze(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSummaryResult) {
		logger.debug("Calling Snooze action");
		int delay = 100;
		try {
			delay = Integer.parseInt(step.getStepParam());
			Thread.sleep(delay);
			DdtCoreUtls.stepRslt(stepResult, true, "snoozed for " + delay + " secs", null);
		} catch (InterruptedException e) {
			logger.warn("snooze Error : " + e, e);
			DdtCoreUtls.stepRslt(stepResult, false, "Interrupt occurred while snooze", null);
		}
	}

	public void runAppleScriptForLogin(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws Throwable {
		logger.info("Calling run Apple Script For Login action");
		String action = step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		try {
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("AppleScript");
			engine.eval("tell application \"Login\" to launch");
			DdtCoreUtls.stepRslt(stepResult, true, "Apple Script for Login is started", null);
		}

		catch (Exception ex) {
			DdtCoreUtls.stepRslt(stepResult, false, "Error in executing apple script", null);
		}
	}

	public void runAppleScriptForAlert(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep testStep,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws Throwable {
		logger.info(" Calling run apple script action");
		String action = testStep.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		try {
			ScriptEngineManager mgr = new ScriptEngineManager();
			logger.info("Action  " + action);
			ScriptEngine engine = mgr.getEngineByName("AppleScript");
			if (action.equals("ok")) {
				engine.eval("tell application \"ApplescriptToClickOk\" to launch");
			} else if (action.equals("cancel")) {
				engine.eval("tell application \"ApplescriptToClickCancel\" to launch");
			}
			DdtCoreUtls.stepRslt(stepResult, true, "Apple Script execution is started", null);
		}

		catch (Exception ex) {
			DdtCoreUtls.stepRslt(stepResult, false, "Error in executing apple script", null);
		}
	}

	
	public String getConfigPath() {
		
		return null;
	}

	
	public void setConfigPath(String configPath) {
		

	}

	// actions here

	/**
	 * If this works sometimes and not at other times : need to make sure control already has focus and currently browser window with your page has focus,
	 * this is a OS level key event so which ever window has focus will get the tab key
	 * */
	public void osTabOut(TestStep testStep, TestStepResult stepResult, TestPlan plan) throws AWTException, InterruptedException {
		logger.info("calling tab out action 1 second sleep and then 10 seconds wait for object load");
		if (RobotHelper.tabOut()) {
			DdtCoreUtls.stepRslt(stepResult, true, "tabed out", null);
			logger.info("Tabbed out in tabOutAction");
		} else {
			DdtCoreUtls.stepRslt(stepResult, false, "Failure to tabout", null);
			logger.info("Failure to tabout");
		}
	}

	/**
	 * If this works sometimes and not at other times : need to make sure control already has focus and currently browser window with your page has focus,
	 * this is a OS level key event so which ever window has focus will get the tab key
	 * */
	public void osKeys(TestStep step, TestStepResult stepResult, TestPlan plan) throws AWTException, InterruptedException {
		logger.debug("osKeys");
		int delay = 150;
		String keys = step.getStepParam();
		if (RobotHelper.sendKeys(keys, delay)) {
			DdtCoreUtls.stepRslt(stepResult, true, "osKeys", null);
			logger.debug("Tabbed out in tabOutAction");
		} else {
			DdtCoreUtls.stepRslt(stepResult, false, "Failure osKeys", keys);
			logger.info("Failure osKeys");
		}
	}

	/**
	 * Execute a OS dependent process
	 * */
	public void exec(TestStep step, TestStepResult stepResult, TestPlan plan) throws AWTException, InterruptedException {
		logger.debug("exec");
		// int delay = 150;
		String cmd = step.getStepParam();
		try {
			logger.trace("cmd " + cmd);
			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process proc = pb.start();
			InputStream is = proc.getInputStream();
			// (InputStream is, String pool, boolean ownThread, int logLevel, boolean saveStr, String encoding){
			// TODO params
			ReadStreamAsync rsaIn = new ReadStreamAsync(is, DdtPools.DEFAULT_POOL, false, 1, false, null);
			InputStream isE = proc.getErrorStream();
			ReadStreamAsync rsaErr = new ReadStreamAsync(isE, "default", false, 1, false, null);
			DdtCoreUtls.stepRslt(stepResult, true, "osKeys", null);
			logger.trace("exec done");
			proc.waitFor();
		} catch (Throwable e) {// catch whatever
			final String msg = "exec err :" + e + ", " + cmd;
			DdtCoreUtls.stepFillDetail(stepResult, msg, e);
			logger.warn(msg, e);
		}
	}

	public Runner clone2() throws CloneNotSupportedException {
		
		return (Runner) clone();
	}
	
	public static String getVersion(){
		return "1.6.10";
	}
}
