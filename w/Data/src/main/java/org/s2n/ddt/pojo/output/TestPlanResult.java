package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.s2n.ddt.pojo.Functional;

public class TestPlanResult implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testPlanId;
	private String testPlanRunName;
	private String buildVersion;
	private boolean mainStatus = false;
	private String statusSummary;
	private Date dateStarted;
	private Date dateCompleted;
	private String testBedSummary;
	private String testRunId;
	private BigDecimal testAgentId;
	private BigDecimal testScenarioId;
	private BigDecimal testSuiteId;
	private BigDecimal testCaseId;
	private BigDecimal testStepId;
	private boolean result;
	private String msg;
	private List<TestScenarioResult> scenarioResultsList = new ArrayList<TestScenarioResult>(0);
	private Map<BigDecimal, TestScenarioResult> scenarioResultsMap = new HashMap<BigDecimal, TestScenarioResult>(0);
	private Map<BigDecimal, Functional> functionalMap = new HashMap<BigDecimal, Functional>(0);
	private List<Functional> functionalList = new ArrayList<Functional>(0);

	public TestPlanResult() {
		super();
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestPlanResult [testPlanId=" + testPlanId + ", testPlanRunName=" + testPlanRunName + "]";
	}

	public List<Functional> getFunctionalList() {
		return functionalList;
	}

	public void setFunctionalList(List<Functional> functionalList) {
		this.functionalList = functionalList;
	}

	public BigDecimal getTestplanid() {
		return testPlanId;
	}

	public void setTestplanid(BigDecimal testplanid) {
		this.testPlanId = testplanid;
	}

	public String getTestPlanRunName() {
		return testPlanRunName;
	}

	/**
	 * unique name for a run, key to this and TestPlanResult of this run, use in
	 * Map as key. populated by core just before sending to Agent with unique
	 * name
	 * */
	public void setTestPlanRunName(String testPlanRunName) {
		this.testPlanRunName = testPlanRunName;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public boolean isMainStatus() {
		return mainStatus;
	}

	public void setMainStatus(boolean mainStatus) {
		this.mainStatus = mainStatus;
	}

	public String getStatusSummary() {
		return statusSummary;
	}

	public void setStatusSummary(String statusSummary) {
		this.statusSummary = statusSummary;
	}

	public java.util.Date getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(java.util.Date dateStarted) {
		this.dateStarted = dateStarted;
	}

	public java.util.Date getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(java.util.Date dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public List<TestScenarioResult> getTestScenarioResults() {
		return scenarioResultsList;
	}

	public void setTestScenarioResults(List<TestScenarioResult> testScenarioResults) {
		this.scenarioResultsList = testScenarioResults;
	}

	public Map<BigDecimal, TestScenarioResult> getScenarioResults() {
		return scenarioResultsMap;
	}

	public void setScenarioResults(Map<BigDecimal, TestScenarioResult> scenarioResults) {
		this.scenarioResultsMap = scenarioResults;
	}

	public Map<BigDecimal, Functional> getFunctional() {
		return functionalMap;
	}

	public void setFunctional(Map<BigDecimal, Functional> functional) {
		this.functionalMap = functional;
	}

	public String getTestBedSummary() {
		return testBedSummary;
	}

	public void setTestBedSummary(String testBedSummary) {
		this.testBedSummary = testBedSummary;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(String testRunId) {
		this.testRunId = testRunId;
	}

	public BigDecimal getTestAgentId() {
		return testAgentId;
	}

	public void setTestAgentId(BigDecimal testAgentId) {
		this.testAgentId = testAgentId;
	}

	public BigDecimal getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(BigDecimal testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public BigDecimal getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(BigDecimal testStepId) {
		this.testStepId = testStepId;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public TestScenarioResult getScenarioResult(BigDecimal id) {
		return this.scenarioResultsMap.get(id);
	}

	public void setScenarioResult(BigDecimal id, TestScenarioResult testScenarioResult) {
		this.scenarioResultsMap.put(id, testScenarioResult);
	}

	public Functional getFunctional(BigDecimal id) {
		return this.functionalMap.get(id);
	}

	public void setFunctional(BigDecimal id, Functional functional) {
		this.functionalMap.put(id, functional);
	}

}
