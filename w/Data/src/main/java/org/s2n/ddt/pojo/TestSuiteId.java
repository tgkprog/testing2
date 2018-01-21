package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestParamData;

/**
 * TestSuite entity.
 */

public class TestSuiteId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testSuiteId;
	private BigDecimal featureId;
	private BigDecimal appId;
	private BigDecimal functionalId;
	private String suiteName;
	private String description;
	private Long orderBy;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestCase> testCases = new ArrayList<TestCase>(0);
	private List<SuiteScenarioMapping> suiteScenarioMappingList = new ArrayList<SuiteScenarioMapping>(0);

	/** default constructor */
	public TestSuiteId() {
		super();
	}

	/** minimal constructor */
	public TestSuiteId(BigDecimal testsuiteid, BigDecimal feature, BigDecimal applicationId, BigDecimal functionalId) {
		this.testSuiteId = testsuiteid;
		this.featureId = feature;
		this.appId = applicationId;
		this.functionalId = functionalId;

	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	
	@Override
	public String toString() {
		return "TestSuiteId [testSuiteId=" + testSuiteId + ", featureId=" + featureId + ", appId=" + appId + ", functionalId=" + functionalId
				+ ", suiteName=" + suiteName + ", description=" + description + "]";
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public BigDecimal getFeatureId() {
		return featureId;
	}

	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public BigDecimal getFunctionalId() {
		return functionalId;
	}

	public void setFunctionalId(BigDecimal functionalId) {
		this.functionalId = functionalId;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
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

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappingList() {
		return suiteScenarioMappingList;
	}

	public void setSuiteScenarioMappingList(List<SuiteScenarioMapping> suiteScenarioMappingList) {
		this.suiteScenarioMappingList = suiteScenarioMappingList;
	}

	

}