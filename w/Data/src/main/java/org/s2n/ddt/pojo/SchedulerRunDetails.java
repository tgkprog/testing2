/**
 * 
 */
package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mohammedfirdos
 *
 */
public class SchedulerRunDetails implements Serializable {

	/**
	 * SchedulerRunDetails Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestRunID;
	private BigDecimal TestPlanID;
	private BigDecimal TestDataID;
	private BigDecimal ScheduleID;
	private String Status;
	private Date RunTime;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public SchedulerRunDetails() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the testRunID
	 */
	public BigDecimal getTestRunID() {
		return TestRunID;
	}

	/**
	 * @param testRunID the testRunID to set
	 */
	public void setTestRunID(BigDecimal testRunID) {
		TestRunID = testRunID;
	}

	/**
	 * @return the testPlanID
	 */
	public BigDecimal getTestPlanID() {
		return TestPlanID;
	}

	/**
	 * @param testPlanID the testPlanID to set
	 */
	public void setTestPlanID(BigDecimal testPlanID) {
		TestPlanID = testPlanID;
	}

	/**
	 * @return the testDataID
	 */
	public BigDecimal getTestDataID() {
		return TestDataID;
	}

	/**
	 * @param testDataID the testDataID to set
	 */
	public void setTestDataID(BigDecimal testDataID) {
		TestDataID = testDataID;
	}

	/**
	 * @return the scheduleID
	 */
	public BigDecimal getScheduleID() {
		return ScheduleID;
	}

	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(BigDecimal scheduleID) {
		ScheduleID = scheduleID;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return Status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		Status = status;
	}

	/**
	 * @return the runTime
	 */
	public Date getRunTime() {
		return RunTime;
	}

	/**
	 * @param runTime the runTime to set
	 */
	public void setRunTime(Date runTime) {
		RunTime = runTime;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return CreatedDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		CreatedDateTime = createdDateTime;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return UpdatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	/**
	 * @return the updatedDateTime
	 */
	public Date getUpdatedDateTime() {
		return UpdatedDateTime;
	}

	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(Date updatedDateTime) {
		UpdatedDateTime = updatedDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchedulerRunDetails [TestRunID=" + TestRunID + ", TestPlanID="
				+ TestPlanID + ", TestDataID=" + TestDataID + ", ScheduleID="
				+ ScheduleID + ", Status=" + Status + ", RunTime=" + RunTime
				+ "]";
	}
	
}
