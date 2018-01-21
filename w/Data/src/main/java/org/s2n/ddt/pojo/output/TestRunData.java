package org.s2n.ddt.pojo.output;

import java.io.Serializable;

import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;

/**
 * TestRunData entity.
 */
public class TestRunData implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private TestRunDataId testRunDataId;
	private TestPlan testPlan;
	private TestSuite testSuite;
	private TestCase testCase;
	private TestStep testStep;

	public TestRunData() {
		super();
	}

	/** Full constructor */
	public TestRunData(TestRunDataId testRunDataId, TestPlan testPlan, TestSuite testSuite, TestCase testCase, TestStep testStep) {
		this.testRunDataId = testRunDataId;
		this.testPlan = testPlan;
		this.testSuite = testSuite;
		this.testCase = testCase;
		this.testStep = testStep;

	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestRunData [testRunDataId=" + testRunDataId + "]";
	}

	public TestRunDataId getTestRunDataId() {
		return testRunDataId;
	}

	public void setTestRunDataId(TestRunDataId testRunDataId) {
		this.testRunDataId = testRunDataId;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public TestStep getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStep testStep) {
		this.testStep = testStep;
	}

}
