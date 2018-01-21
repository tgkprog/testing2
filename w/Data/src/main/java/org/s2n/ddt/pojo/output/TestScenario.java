package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.input.ParamGroup;

/**
 * TestScenario entity.
 */

public class TestScenario implements Serializable, Cloneable {

	/**
	 * Generated serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal testScenarioId;
	private Application application;
	private BigDecimal appId;
	private String testScenarioName;
	private String description;
	private Long orderBy;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private int passCount;
	private int failCount;
	private int skipCount;
	private int totalCount;
	private int notExecuted;
	private int executed;
	private float percentagePassCount;
	private float percentageFailCount;
	private float percentageSkipCount;
	private float percentageNotExecuted;
	private List<ParamGroup> paramGroups = new ArrayList<ParamGroup>(0);
	private List<SuiteScenarioMapping> suiteScenarioMappings = new ArrayList<SuiteScenarioMapping>(0);
	private List<TestscenarioParamdataMap> scenarioParamDataMappingList = new ArrayList<TestscenarioParamdataMap>(0);
	private List<TestplanTestscenarioMap> planScenarioMappingList = new ArrayList<TestplanTestscenarioMap>(0);
	private List<TestSuite> testSuites = new ArrayList<TestSuite>(0);

	/** Default constructor */
	public TestScenario() {
		super();
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestScenario [testScenarioId=" + testScenarioId + " appId=" + appId + ", testScenarioName=" + testScenarioName + ", description="
				+ description + "]";
	}

	public BigDecimal getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(BigDecimal testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public String getTestScenarioName() {
		return testScenarioName;
	}

	public void setTestScenarioName(String testScenarioName) {
		this.testScenarioName = testScenarioName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getNotExecuted() {
		return notExecuted;
	}

	public void setNotExecuted(int notExecuted) {
		this.notExecuted = notExecuted;
	}

	public int getExecuted() {
		return executed;
	}

	public void setExecuted(int executed) {
		this.executed = executed;
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

	public List<ParamGroup> getParamGroups() {
		return paramGroups;
	}

	public void setParamGroups(List<ParamGroup> paramGroups) {
		this.paramGroups = paramGroups;
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappings() {
		return suiteScenarioMappings;
	}

	public void setSuiteScenarioMappings(List<SuiteScenarioMapping> suiteScenarioMappings) {
		this.suiteScenarioMappings = suiteScenarioMappings;
	}

	public List<TestscenarioParamdataMap> getScenarioParamDataMappingList() {
		return scenarioParamDataMappingList;
	}

	public void setScenarioParamDataMappingList(List<TestscenarioParamdataMap> scenarioParamDataMappingList) {
		this.scenarioParamDataMappingList = scenarioParamDataMappingList;
	}

	public List<TestplanTestscenarioMap> getPlanScenarioMappingList() {
		return planScenarioMappingList;
	}

	public void setPlanScenarioMappingList(List<TestplanTestscenarioMap> planScenarioMappingList) {
		this.planScenarioMappingList = planScenarioMappingList;
	}

	public List<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

}