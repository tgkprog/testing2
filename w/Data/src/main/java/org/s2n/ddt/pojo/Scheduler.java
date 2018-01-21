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
public class Scheduler implements Serializable {

	/**
	 * Scheduler Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ScheduleID;
	private BigDecimal TestPlanID;
	private BigDecimal TestDataID;
	private Date ScheduleTime;
	private String Status;
	private String Frequency;
	private String Notifications;
	private String MultiLanes;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public Scheduler() {
		// TODO Auto-generated constructor stub
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
	 * @return the scheduleTime
	 */
	public Date getScheduleTime() {
		return ScheduleTime;
	}

	/**
	 * @param scheduleTime the scheduleTime to set
	 */
	public void setScheduleTime(Date scheduleTime) {
		ScheduleTime = scheduleTime;
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
	 * @return the frequency
	 */
	public String getFrequency() {
		return Frequency;
	}

	/**
	 * @param frequency the frequency to set
	 */
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}

	/**
	 * @return the notifications
	 */
	public String getNotifications() {
		return Notifications;
	}

	/**
	 * @param notifications the notifications to set
	 */
	public void setNotifications(String notifications) {
		Notifications = notifications;
	}

	/**
	 * @return the multiLanes
	 */
	public String getMultiLanes() {
		return MultiLanes;
	}

	/**
	 * @param multiLanes the multiLanes to set
	 */
	public void setMultiLanes(String multiLanes) {
		MultiLanes = multiLanes;
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
		return "Scheduler [ScheduleID=" + ScheduleID + ", TestPlanID="
				+ TestPlanID + ", TestDataID=" + TestDataID + ", ScheduleTime="
				+ ScheduleTime + ", Status=" + Status + ", Frequency="
				+ Frequency + ", Notifications=" + Notifications
				+ ", MultiLanes=" + MultiLanes + "]";
	}
	
}
