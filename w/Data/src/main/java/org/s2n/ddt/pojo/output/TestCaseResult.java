package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.s2n.ddt.pojo.input.TestCase;

/**
 * TestCaseResult entity
 */
public class TestCaseResult implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	private int testCaseResultId;
	private int passCount;
	private int failCount;
	private int skipCount;
	private int totalCount;
	private Double timeDuration;
	private BigDecimal testCaseId;
	private String testCaseName;
	private int active;
	private String description;
	private BigDecimal runnerId;
	private BigDecimal testSuiteId;
	private String testCaseResult;
	private TestCase testCase;
	private Date planRunStartDateTime;
	private Date planRunEndDateTime;
	private String errorMsg;
	private int runStatus;
	private String response;
	private String request;
	private boolean validationByAgent;

	public ArrayList<String> getReqyestPart() {
		return reqyestPart;
	}

	public void setReqyestPart(ArrayList<String> reqyestPart) {
		this.reqyestPart = reqyestPart;
	}

	private ArrayList<String> reqyestPart;
	private List<TestStepResult> testStepResultsList = new ArrayList<TestStepResult>(0);
	private Map<BigDecimal, TestStepResult> testStepResultsMap = new HashMap<BigDecimal, TestStepResult>(0);

	public TestCaseResult() {
		super();
	}

	@Override
	public String toString() {
		return "TestCaseResult [passCount=" + passCount + ", failCount=" + failCount + ", skipCount=" + skipCount + ", totalCount=" + totalCount
				+ " , testCaseName=" + testCaseName + ", active=" + active + "]";
	}

	public int getPassCount() {
		return passCount;
	}

	public void setPassCount(int passCount) {
		this.passCount = passCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public int getSkipCount() {
		return skipCount;
	}

	public void setSkipCount(int skipCount) {
		this.skipCount = skipCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRunnerId() {
		return runnerId;
	}

	public void setRunnerId(BigDecimal runnerId) {
		this.runnerId = runnerId;
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getTestCaseResult() {
		return testCaseResult;
	}

	public void setTestCaseResult(String testCaseResult) {
		this.testCaseResult = testCaseResult;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public Date getPlanRunStartDateTime() {
		return planRunStartDateTime;
	}

	public void setPlanRunStartDateTime(Date planRunStartDateTime) {
		this.planRunStartDateTime = planRunStartDateTime;
	}

	public Date getPlanRunEndDateTime() {
		return planRunEndDateTime;
	}

	public void setPlanRunEndDateTime(Date planRunEndDateTime) {
		this.planRunEndDateTime = planRunEndDateTime;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public int getRunStatus() {
		return runStatus;
	}

	public void setRunStatus(int runStatus) {
		this.runStatus = runStatus;
	}

	public List<TestStepResult> getTestStepResultsList() {
		return testStepResultsList;
	}

	public void setTestStepResultsList(List<TestStepResult> testStepResultsList) {
		this.testStepResultsList = testStepResultsList;
	}

	public TestStepResult getTestStepResultsMap(BigDecimal id) {
		return testStepResultsMap.get(id);
	}

	public void setTestStepResultsMap(BigDecimal id, TestStepResult testStepResult) {
		this.testStepResultsMap.put(id, testStepResult);
	}

	public boolean isValidationByAgent() {
		return validationByAgent;
	}

	public void setValidationByAgent(boolean validationByAgent) {
		this.validationByAgent = validationByAgent;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public int getTestCaseResultId() {
		return testCaseResultId;
	}

	public void setTestCaseResultId(int testCaseResultId) {
		this.testCaseResultId = testCaseResultId;
	}

}
