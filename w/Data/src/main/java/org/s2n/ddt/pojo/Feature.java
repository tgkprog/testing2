package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestScenarioResult;

/**
 * Feature entity.
 */

public class Feature implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal featureId;
	private Functional functional;
	private BigDecimal functionalId;
	private String featureName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private int passCount;
	private int failCount;
	private int skipCount;
	private int totalCount;
	private double timeDuration;
	private int executedCount;
	private int notExecutedCount;
	private float percentagePassCount;
	private float percentageFailCount;
	private float percentageSkipCount;
	private float percentageNotExecuted;
	private List<TestSuite> testSuites = new ArrayList<TestSuite>(0);
	private List<TestScenario> testScenarios = new ArrayList<TestScenario>(0);
	private List<TestScenarioResult> testScenariosResult = new ArrayList<TestScenarioResult>(0);

	/** default constructor */
	public Feature() {
		super();
	}

	/** minimal constructor */
	public Feature(BigDecimal featureid, Functional functional, String featurename, String createdby, Date createddatetime, String updatedby,
			Date updateddatetime) {
		this.featureId = featureid;
		this.functional = functional;
		this.featureName = featurename;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "Feature [featureId=" + featureId + ", functionalId=" + functionalId + ", featureName=" + featureName
				+ ", description=" + description + "]";
	}

	public BigDecimal getFeatureId() {
		return featureId;
	}

	public void setFeatureId(BigDecimal featureId) {
		this.featureId = featureId;
	}

	public Functional getFunctional() {
		return functional;
	}

	public void setFunctional(Functional functional) {
		this.functional = functional;
	}

	public BigDecimal getFunctionalId() {
		return functionalId;
	}

	public void setFunctionalId(BigDecimal functionalId) {
		this.functionalId = functionalId;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public double getTimeDuration() {
		return timeDuration;
	}

	public void setTimeDuration(double timeDuration) {
		this.timeDuration = timeDuration;
	}

	public int getExecutedCount() {
		return executedCount;
	}

	public void setExecutedCount(int executedCount) {
		this.executedCount += executedCount;
	}

	public int getNotExecutedCount() {
		return notExecutedCount;
	}

	public void setNotExecutedCount(int notExecutedCount) {
		this.notExecutedCount += notExecutedCount;
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

	public List<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	public List<TestScenario> getTestScenarios() {
		return testScenarios;
	}

	public void setTestScenarios(List<TestScenario> testScenarios) {
		this.testScenarios = testScenarios;
	}

	public List<TestScenarioResult> getTestScenariosResult() {
		return testScenariosResult;
	}

	public void setTestScenariosResult(List<TestScenarioResult> testScenariosResult) {
		this.testScenariosResult = testScenariosResult;
	}

}