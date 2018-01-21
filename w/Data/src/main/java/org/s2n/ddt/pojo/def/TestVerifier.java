package org.s2n.ddt.pojo.def;

import java.util.Map;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestStepResult;
import org.s2n.ddt.pojo.output.TestSuiteResultSummary;

public interface TestVerifier {

	/**
	 * For now please ignore param reqHeaders and param additionalInfo, they are
	 * for possible future use.
	 * */
	public void testStepVerify(TestPlan plan, TestSuite suite, TestCase testCase, TestStep step, TestStepResult stepResult,
			TestSuiteResultSummary suiteResult, String rawReq, Map<String, String> reqHeaders, Map<String, String> requestMap, String rawResponse,
			Map<String, String> responseMap, Map<String, Object> additionalInfo);

}
