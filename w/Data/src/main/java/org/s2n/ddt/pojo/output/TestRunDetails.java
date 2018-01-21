package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TestRunDetails entity.
 */

public class TestRunDetails implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */

	private static final long serialVersionUID = 1L;

	private BigDecimal testRunId;
	private TestPlan testPlan;
	private BigDecimal testPlanId;
	private Date runTime;
	private String status;
	private String notificationDetails;
	private String os;
	private String buildVersion;
	private String machineId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestReport> testReports = new ArrayList<TestReport>(0);

	/** default constructor */
	public TestRunDetails() {
		super();
	}

	/** minimal constructor */
	public TestRunDetails(BigDecimal testrunid, TestPlan testPlan, BigDecimal testPlanId, String os, String machineid) {
		this.testRunId = testrunid;
		this.testPlan = testPlan;
		this.testPlanId = testPlanId;
		this.os = os;
		this.machineId = machineid;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestRunDetails [testRunId=" + testRunId + ", testPlanId=" + testPlanId + " runTime=" + runTime + ", status=" + status + "]";
	}

	public BigDecimal getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(BigDecimal testRunId) {
		this.testRunId = testRunId;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public BigDecimal getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(BigDecimal testPlanId) {
		this.testPlanId = testPlanId;
	}

	public Date getRunTime() {
		return runTime;
	}

	public void setRunTime(Date runTime) {
		this.runTime = runTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNotificationDetails() {
		return notificationDetails;
	}

	public void setNotificationDetails(String notificationDetails) {
		this.notificationDetails = notificationDetails;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getBuildVersion() {
		return buildVersion;
	}

	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
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

	public List<TestReport> getTestReports() {
		return testReports;
	}

	public void setTestReports(List<TestReport> testReports) {
		this.testReports = testReports;
	}

}