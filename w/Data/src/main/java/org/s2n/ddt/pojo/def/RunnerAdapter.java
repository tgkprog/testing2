package org.s2n.ddt.pojo.def;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

/**
 * Does nothing runner. Good to extend and over ride only functions you need
 * @author tusharkapila
 *
 */
public class RunnerAdapter implements Runner {

	@Override
	public void startTestPlan(TestPlan plan) {
		
		
	}

	@Override
	public void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote) {
		
		
	}

	@Override
	public void startTestCase(TestPlan plan, TestSuite suite, TestSuiteResultSummary suiteSumamryResult) {
		
		
	}

	@Override
	public void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult) {
		
		
	}

	@Override
	public void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep) {
		
		
	}

	@Override
	public String getConfigPath() {
		
		return null;
	}

	@Override
	public void setConfigPath(String configPath) {
		
		
	}

	@Override
	public Runner clone2() throws CloneNotSupportedException {		
		return (Runner) super.clone();
	}

}
