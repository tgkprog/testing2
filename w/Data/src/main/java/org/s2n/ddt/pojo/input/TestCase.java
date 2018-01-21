package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.output.TestRunData;

/**
 * TestCase entity.
 */

public class TestCase implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testCaseId;
	private Runner runner;
	private BigDecimal runnerId;
	private TestSuite testSuite;
	private BigDecimal testSuiteId;
	private String classificationTag;
	private String caseName;
	private String description;
	private Integer active;
	private Integer positive;
	private Long orderBy;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestParamData> testParamDatas = new ArrayList<TestParamData>(0);
	private List<ParamGroup> paramGroups = new ArrayList<ParamGroup>(0);
	private List<TestStep> testSteps = new ArrayList<TestStep>(0);
	private List<TestRunData> testRunDatas = new ArrayList<TestRunData>(0);

	/** default constructor */
	public TestCase() {
		super();
	}

	/** minimal constructor */
	public TestCase(BigDecimal testcaseid, Runner runner, BigDecimal testSuiteId) {
		this.testCaseId = testcaseid;
		this.runner = runner;
		this.testSuiteId = testSuiteId;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestCase [testcaseid=" + testCaseId + ",  classificationtag=" + classificationTag + ", casename=" + caseName + ", description="
				+ description + ", active=" + active + ", positive=" + positive + "]";
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public BigDecimal getRunnerId() {
		return runnerId;
	}

	public void setRunnerId(BigDecimal runnerId) {
		this.runnerId = runnerId;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getClassificationTag() {
		return classificationTag;
	}

	public void setClassificationTag(String classificationTag) {
		this.classificationTag = classificationTag;
	}

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getPositive() {
		return positive;
	}

	public void setPositive(Integer positive) {
		this.positive = positive;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public List<TestParamData> getTestParamDatas() {
		return testParamDatas;
	}

	public void setTestParamDatas(List<TestParamData> testParamDatas) {
		this.testParamDatas = testParamDatas;
	}

	public List<ParamGroup> getParamGroups() {
		return paramGroups;
	}

	public void setParamGroups(List<ParamGroup> paramGroups) {
		this.paramGroups = paramGroups;
	}

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	public List<TestRunData> getTestRunDatas() {
		return testRunDatas;
	}

	public void setTestRunDatas(List<TestRunData> testRunDatas) {
		this.testRunDatas = testRunDatas;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}