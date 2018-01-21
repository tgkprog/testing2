package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * TestParamDataId entity.
 */
public class TestParamDataId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testParamDataId;
	private BigDecimal testCaseId;
	private BigDecimal testStepId;
	private BigDecimal paramId;
	private String paramValue;
	private byte[] valueBig;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private String testSet;
	private BigDecimal appId;

	/** default constructor */
	public TestParamDataId() {
	}

	/** minimal constructor */
	public TestParamDataId(BigDecimal testparamdataid, BigDecimal testcaseid, BigDecimal teststepid, BigDecimal paramid) {
		this.testParamDataId = testparamdataid;
		this.testCaseId = testcaseid;
		this.testStepId = teststepid;
		this.paramId = paramid;
	}

	@Override
	public String toString() {
		return "TestParamDataId [testParamDataId=" + testParamDataId + ", testCaseId=" + testCaseId + ", testStepId=" + testStepId + ", paramId="
				+ paramId + ", paramValue=" + paramValue + "]";
	}

	public BigDecimal getTestParamDataId() {
		return testParamDataId;
	}

	public void setTestParamDataId(BigDecimal testParamDataId) {
		this.testParamDataId = testParamDataId;
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

	public BigDecimal getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public byte[] getValueBig() {
		return valueBig;
	}

	public void setValueBig(byte[] valueBig) {
		this.valueBig = valueBig;
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

	public String getTestSet() {
		return testSet;
	}

	public void setTestSet(String testSet) {
		this.testSet = testSet;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

}