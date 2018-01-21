package org.s2n.ddt.core;

import java.awt.AWTException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptEngine;
//TODO
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

public class ScriptTest {
	private static final Logger logger = Logger.getLogger(ScriptTest.class);
	private static volatile Map<String, Method> methods = new HashMap<String, Method>(20);


	public void runAppleScriptForLogin(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStep step,
			TestStepResult stepResult, TestSuiteResultSummary suiteSummaryResult) throws Throwable {
		logger.info("Calling run Apple Script For Login action");
		String action = step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName();
		try {
			//TODO
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("AppleScript");
			engine.eval("tell application \"Login\" to launch");

		}

		catch (Exception ex) {
		}
	}


}
