package org.s2n.ddt.pojo.def;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestCaseResult;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public interface DdtRemoteInterface2 {

	void startTestPlan(TestPlan plan);

	void startTestSuite(TestPlan plan, TestSuite suite, TestSuiteResultSummary tstSmmryFromRemote);

	void startTestCase(TestPlan plan, TestSuite suite, TestSuiteResultSummary suiteSumamryResult,TestCase cases);

	void testStep(TestPlan obj, TestSuite suite, TestCase tcase, TestCaseResult caseResult, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteSumamryResult);

	void endTestCase(TestPlan plan, TestSuite suite, TestCase testCase, TestCaseResult caseResult, TestStepResult lastStep);
	
	void endTestSuite(TestPlan plan, TestSuite suite,TestSuiteResult testSuiteResult);

	// TestSuiteResultSummary suiteSumamryResult

	String getConfigPath();

	void setConfigPath(String configPath);

}
