package org.s2n.ddt.excelreader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.def.AgentConfiguration;
import org.s2n.ddt.pojo.def.AgentInterface;
import org.s2n.ddt.pojo.def.AgentStatus;
import org.s2n.ddt.pojo.def.DdtRemoteInterface;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public class AgentDataSet implements DdtRemoteInterface {
	private static final Logger logger = Logger.getLogger(AgentDataSet.class);

	@Override
	public void startTestPlan(TestPlan plan) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startTestCase(TestPlan plan, TestSuite suite, TestSuiteResultSummary suiteSumamryResult) {
		// TODO Auto-generated method stub
	}

	@Override
	public void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult) {
		
		logger.log(Level.INFO, "  TestCasename " + tcase.getCaseName());
		logger.log(Level.INFO, "  Action " + step.getActions().getActionName());
		logger.log(Level.INFO, "StepParam     " + step.getStepParam());
		logger.log(Level.INFO, "Objects  "
				+ step.getTestStepId().getTestParamDatas().get(0).getParam().getObjects()
						.getObjectsId().getIndentifier());
		logger.log(Level.INFO, "Params  "
				+ step.getTestStepId().getTestParamDatas().get(0).getParam().getParamName());
		// TODO Auto-generated method stub
		
	}

	@Override
	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getConfigPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConfigPath(String configPath) {
		// TODO Auto-generated method stub
		
	}

}
