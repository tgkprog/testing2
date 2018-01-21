package org.s2n.ddt.runner.selenium;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.common.Runner2;
import org.s2n.ddt.common.share.CommonInformation;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.Runner;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public class SeleniumRunner implements Runner, Runner2 {
	private static final Logger logger = Logger.getLogger(SeleniumRunner.class);
	private static volatile Map<String, Method> methods = new HashMap<String, Method>(20);

	private ActionScript actionScript = new ActionScript();

	public SeleniumRunner() {

	}

	public void startTestPlan(TestPlan obj) {

	}

	public void startTestSuite(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {

	}

	public void startTestCase(TestPlan obj, TestSuite suite, TestSuiteResultSummary tstSmmryFromCache) {

	}

	public void testStep(TestPlan testPlan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step,
			TestStepResult testStepResult, TestSuiteResultSummary tstSmmryFromCache) {
		testStepResult.setResult(false);// make it true in each method if it
		// passes
		String action = step.getActions().getActionName();
		Method actionMethd = null;
		caseResult.setValidationByAgent(true);
		synchronized (methods) {
			actionMethd = methods.get(action);
			if (actionMethd == null) {
				try {
					// init(o.output.TestPlan, .pojo.TestSuite,
					// org.s2n.ddt.pojo.input.TestCase,
					// org.s2n.ddt.pojo.input.TestStep,
					// org.s2n.ddt.pojo.output.TestStepResult,
					// org.s2n.ddt.pojo.output.TestSuiteResultSummary
					actionMethd = getActionScript().getClass().getMethod(action, TestPlan.class, TestSuite.class, TestCase.class,
							TestCaseResult.class, TestStep.class, TestStepResult.class, TestSuiteResultSummary.class);// <String,
					// String>
					methods.put(action, actionMethd);
				} catch (SecurityException e) {
					logger.warn("SE Err " + e, e);
					// TODO add stack trace to detail, based on app or test plan
					// option
					testStepResult.setDetailMsgs("Java security, bad setup ERR :" + e);
					testStepResult.setComment("Could not process action :[" + action + "] java error.");
				} catch (NoSuchMethodException e) {
					logger.warn("No method Err :" + e, e);
					testStepResult.setDetailMsgs("Bad action - no corresponding impl ERR :" + e);
					testStepResult.setComment("Could not process action :[" + action + "] not implemented this.");
				}
			}

		}

		if (actionMethd != null) {
			try {
				logger.info("Action :" + action);
				actionMethd.invoke(SeleniumRunner.this.getActionScript(), testPlan, suite, testCase, caseResult, step, testStepResult,
						tstSmmryFromCache);
			} catch (Exception e) {
				logger.warn("Calling action Err :" + e, e);
				testStepResult.setDetailMsgs("Bad action - ERR :" + e);
			}
		}
	}

	public String getConfigPath() {

		return null;
	}

	public void setConfigPath(String configPath) {

	}

	public ActionScript getActionScript() {
		if (actionScript == null) {
			actionScript = new ActionScript();
		}
		return actionScript;
	}

	public static final String getVersion() {
		return "1.2.3";
	}

	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {

	}

	public Runner clone2() throws CloneNotSupportedException {

		return (Runner) clone();
	}

	/**
	 * Not yet implemented. Useful if we want to make runners who know about each other. Example: SeleniumRunner2 which can take browser and
	 * some other information from original SeleniumRunner
	 * */

	public void setRunnersInformation(Map<String, Runner> runners) {
	}

	/**
	 * For caller/ agent to set our common information provider, thru which we can share strings and binary objects with other runners in
	 * Agent
	 * */

	public void setCommonInformationProvider(CommonInformation ci) {
		actionScript.setCommonInformationProvider(ci);

	}
}
