package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.s2n.ddt.pojo.output.TestScenario;

public class TestStepParamGroupMapping implements Serializable {

	/**
	 * Default Serial Version Id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal testStepParamGrpId;
	private BigDecimal testStepId;
	private BigDecimal paramGroupId;
	private BigDecimal testCaseId;
	private BigDecimal testScenarioId;
	private TestCase testCase;
	private TestScenario testScenario;
	private TestStep testStep;
	private ParamGroup paramGroup;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

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

	public BigDecimal getTestStepParamGrpId() {
		return testStepParamGrpId;
	}

	public void setTestStepParamGrpId(BigDecimal testStepParamGrpId) {
		this.testStepParamGrpId = testStepParamGrpId;
	}

	public BigDecimal getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(BigDecimal testStepId) {
		this.testStepId = testStepId;
	}

	public BigDecimal getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(BigDecimal paramGroupId) {
		this.paramGroupId = paramGroupId;
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public BigDecimal getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(BigDecimal testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}

	public TestStep getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStep testStep) {
		this.testStep = testStep;
	}

	public ParamGroup getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(ParamGroup paramGroup) {
		this.paramGroup = paramGroup;
	}

	@Override
	public String toString() {
		return "TestStepParamGroupMapping [testStepParamGrpId=" + testStepParamGrpId + ", testStepId=" + testStepId + ", paramGroupId=" + paramGroupId
				+ ", testCaseId=" + testCaseId + ", testScenarioId=" + testScenarioId + "]";
	}

}
