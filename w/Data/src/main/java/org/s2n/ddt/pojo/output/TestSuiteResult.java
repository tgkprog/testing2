package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.s2n.ddt.pojo.TestSuite;

/** TestSuiteResult entity */
public class TestSuiteResult implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal suiteId;
	private String appName;
	private String suiteName;
	private String suiteDescription;
	private String suiteFeature;
	private int passCount;
	private int failCount;
	private int skipCount;
	private int totalCount;
	private int notExecuted;
	private int executed;
	private Double timeDuration;
	private BigDecimal testCaseId;
	private String testCaseName;
	private String suitSatus;
	private float percentagePassCount;
	private float percentageFailCount;
	private float percentageSkipCount;
	private float percentageNotExecuted;
	private TestSuite testSuite;
	private List<TestCaseResult> caseResultsList = new java.util.ArrayList<TestCaseResult>(1);
	private Map<BigDecimal, TestCaseResult> caseResultsMap = new java.util.HashMap<BigDecimal, TestCaseResult>(1);

	public TestSuiteResult() {
		super();
	}

	public int getCaseResultsListSize() {
		return caseResultsList.size();
	}

	public TestCaseResult getCaseResultsMap(BigDecimal id) {
		return this.caseResultsMap.get(id);
	}

	public void setCaseResultsMap(BigDecimal id, TestCaseResult testCaseResult) {
		this.caseResultsMap.put(id, testCaseResult);
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	public List<TestCaseResult> getCaseResultsList() {
		return caseResultsList;
	}

	public void setCaseResultsList(List<TestCaseResult> suiteResults) {
		this.caseResultsList = suiteResults;
	}

	public BigDecimal getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(BigDecimal suiteId) {
		this.suiteId = suiteId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public String getSuiteDescription() {
		return suiteDescription;
	}

	public void setSuiteDescription(String suiteDescription) {
		this.suiteDescription = suiteDescription;
	}

	public String getSuiteFeature() {
		return suiteFeature;
	}

	public void setSuiteFeature(String suiteFeature) {
		this.suiteFeature = suiteFeature;
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount += passCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount += failCount;
	}

	public int getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(int skipCount) {
		this.skipCount += skipCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount += totalCount;
	}

	public Double getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(Double timeDuration) {
		this.timeDuration = timeDuration;
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getSuitSatus() {
		return suitSatus;
	}

	public void setSuitSatus(String suitSatus) {
		this.suitSatus = suitSatus;
	}

	public int getNotExecuted() {
		return notExecuted;
	}

	public void setNotExecuted(int notExecuted) {
		this.notExecuted = notExecuted;
	}

	public float getPercentagePassCount() {
		return percentagePassCount;
	}

	public void setPercentagePassCount(float percentagePassCount) {
		this.percentagePassCount = percentagePassCount;
	}

	public float getPercentageFailCount() {
		return percentageFailCount;
	}

	public void setPercentageFailCount(float percentageFailCount) {
		this.percentageFailCount = percentageFailCount;
	}

	public float getPercentageSkipCount() {
		return percentageSkipCount;
	}

	public void setPercentageSkipCount(float percentageSkipCount) {
		this.percentageSkipCount = percentageSkipCount;
	}

	public float getPercentageNotExecuted() {
		return percentageNotExecuted;
	}

	public void setPercentageNotExecuted(float percentageNotExecuted) {
		this.percentageNotExecuted = percentageNotExecuted;
	}

	public int getExecuted() {
		return executed;
	}

	public void setExecuted(int executed) {
		this.executed = executed;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestSuiteResult [suiteId=" + suiteId + ", appName=" + appName + ", suiteName=" + suiteName + ", suiteDescription=" + suiteDescription
				+ "]";
	}

}
