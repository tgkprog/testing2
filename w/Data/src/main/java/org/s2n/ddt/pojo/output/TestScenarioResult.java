package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class TestScenarioResult implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal testScenarioResultId;
	private TestPlanId testPlanId;
	private BigDecimal scenarioId;
	private TestScenario testScenario;
	private List<TestSuiteResult> suiteResultsList = new java.util.ArrayList<TestSuiteResult>(1);
	private Map<BigDecimal, TestSuiteResult> suiteResultsMap = new java.util.HashMap<BigDecimal, TestSuiteResult>(1);

	/** Default Constructor */
	public TestScenarioResult() {
		super();
	}

	/** Full Constructor */
	public TestScenarioResult(BigDecimal testScenarioResultId, TestPlanId testPlanId, BigDecimal scenarioId, TestScenario testScenario,
			List<TestSuiteResult> suiteResultsList, Map<BigDecimal, TestSuiteResult> suiteResultsMap) {
		super();
		this.testScenarioResultId = testScenarioResultId;
		this.testPlanId = testPlanId;
		this.scenarioId = scenarioId;
		this.testScenario = testScenario;
		this.suiteResultsList = suiteResultsList;
		this.suiteResultsMap = suiteResultsMap;
	}

	@Override
	public String toString() {
		return "TestScenarioResult [testScenarioResultId=" + testScenarioResultId + ", testPlanId=" + testPlanId + ", scenarioId=" + scenarioId
				+ "]";
	}

	public BigDecimal getTestScenarioResultId() {
		return testScenarioResultId;
	}

	public void setTestScenarioResultId(BigDecimal testScenarioResultId) {
		this.testScenarioResultId = testScenarioResultId;
	}

	public TestPlanId getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(TestPlanId testPlanId) {
		this.testPlanId = testPlanId;
	}

	public BigDecimal getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(BigDecimal scenarioId) {
		this.scenarioId = scenarioId;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}

	public List<TestSuiteResult> getSuiteResultsList() {
		return suiteResultsList;
	}

	public void setSuiteResultsList(List<TestSuiteResult> suiteResultsList) {
		this.suiteResultsList = suiteResultsList;
	}

	public TestSuiteResult getSuiteResultsMap(BigDecimal id) {
		return suiteResultsMap.get(id);
	}

	public void setSuiteResultsMap(BigDecimal id, TestSuiteResult testSuiteResult) {
		this.suiteResultsMap.put(id, testSuiteResult);
	}

}
