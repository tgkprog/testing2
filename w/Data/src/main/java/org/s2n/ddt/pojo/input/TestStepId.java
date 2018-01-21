package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.output.TestRunData;

/**
 * TestStep entity.
 */

public class TestStepId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testStepId;
	private BigDecimal postConditionGroupId;
	private BigDecimal inputParamGroupId;
	private BigDecimal actionsId;
	private BigDecimal testCaseId;
	private BigDecimal runnerId;
	private BigDecimal outputParamGroupId;
	private BigDecimal preConditionGroupId;
	private String stepName;
	private String description;
	private String testStepType;
	private Integer active;
	private Long orderBy;
	private String expectedResult;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<ParamGroup> paramGroups = new ArrayList<ParamGroup>(0);
	private List<TestParamData> testParamDatas = new ArrayList<TestParamData>(0);
	private List<TestRunData> testRunDatas = new ArrayList<TestRunData>(0);
	private List<TestStepParamGroupMapping> stepGroupMappings = new ArrayList<TestStepParamGroupMapping>(0);

	/** default constructor */
	public TestStepId() {
		super();
	}

	/** minimal constructor */
	public TestStepId(BigDecimal teststepid, BigDecimal conditiongroupByPostconditionId, BigDecimal paramgroupByInputparamId, BigDecimal testCaseId,
			BigDecimal runnerId, BigDecimal paramgroupByOutputparamId, BigDecimal conditiongroupByPreconditionId) {
		this.testStepId = teststepid;
		this.postConditionGroupId = conditiongroupByPostconditionId;
		this.inputParamGroupId = paramgroupByInputparamId;
		this.testCaseId = testCaseId;
		this.runnerId = runnerId;
		this.outputParamGroupId = paramgroupByOutputparamId;
		this.preConditionGroupId = conditiongroupByPreconditionId;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestStep [teststepid=" + testStepId + ", conditiongroupByPostconditionId=" + postConditionGroupId + ", paramgroupByInputparamId="
				+ inputParamGroupId + ", actionsId=" + actionsId + "]";
	}

	public List<TestStepParamGroupMapping> getStepGroupMappings() {
		return stepGroupMappings;
	}

	public void setStepGroupMappings(List<TestStepParamGroupMapping> stepGroupMappings) {
		this.stepGroupMappings = stepGroupMappings;
	}

	public BigDecimal getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(BigDecimal testStepId) {
		this.testStepId = testStepId;
	}

	public BigDecimal getPostConditionGroupId() {
		return postConditionGroupId;
	}

	public void setPostConditionGroupId(BigDecimal postConditionGroupId) {
		this.postConditionGroupId = postConditionGroupId;
	}

	public BigDecimal getInputParamGroupId() {
		return inputParamGroupId;
	}

	public void setInputParamGroupId(BigDecimal inputParamGroupId) {
		this.inputParamGroupId = inputParamGroupId;
	}

	public BigDecimal getActionsId() {
		return actionsId;
	}

	public void setActionsId(BigDecimal actionsId) {
		this.actionsId = actionsId;
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public BigDecimal getRunnerId() {
		return runnerId;
	}

	public void setRunnerId(BigDecimal runnerId) {
		this.runnerId = runnerId;
	}

	public BigDecimal getOutputParamGroupId() {
		return outputParamGroupId;
	}

	public void setOutputParamGroupId(BigDecimal outputParamGroupId) {
		this.outputParamGroupId = outputParamGroupId;
	}

	public BigDecimal getPreConditionGroupId() {
		return preConditionGroupId;
	}

	public void setPreConditionGroupId(BigDecimal preConditionGroupId) {
		this.preConditionGroupId = preConditionGroupId;
	}

	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTestStepType() {
		return testStepType;
	}

	public void setTestStepType(String testStepType) {
		this.testStepType = testStepType;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
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

	public List<ParamGroup> getParamGroups() {
		return paramGroups;
	}

	public void setParamGroups(List<ParamGroup> paramGroups) {
		this.paramGroups = paramGroups;
	}

	public List<TestParamData> getTestParamDatas() {
		return testParamDatas;
	}

	public void setTestParamDatas(List<TestParamData> testParamDatas) {
		this.testParamDatas = testParamDatas;
	}

	public List<TestRunData> getTestRunDatas() {
		return testRunDatas;
	}

	public void setTestRunDatas(List<TestRunData> testRunDatas) {
		this.testRunDatas = testRunDatas;
	}

}