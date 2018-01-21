/**
 * 
 */
package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mohammedfirdos
 *
 */
public class LaneResults implements Serializable {

	/**
	 * LaneResults Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestRunID;
	private BigDecimal ScheduleLaneID;
	private BigDecimal AgentID;
	private String BuildVersion;
	private String OS;
	private String Status;
	private String FailureDetails;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public LaneResults() {
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
	 * @return the scheduleLaneID
	 */
	public BigDecimal getScheduleLaneID() {
		return ScheduleLaneID;
	}

	/**
	 * @param scheduleLaneID the scheduleLaneID to set
	 */
	public void setScheduleLaneID(BigDecimal scheduleLaneID) {
		ScheduleLaneID = scheduleLaneID;
	}

	/**
	 * @return the agentID
	 */
	public BigDecimal getAgentID() {
		return AgentID;
	}

	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(BigDecimal agentID) {
		AgentID = agentID;
	}

	/**
	 * @return the buildVersion
	 */
	public String getBuildVersion() {
		return BuildVersion;
	}

	/**
	 * @param buildVersion the buildVersion to set
	 */
	public void setBuildVersion(String buildVersion) {
		BuildVersion = buildVersion;
	}

	/**
	 * @return the oS
	 */
	public String getOS() {
		return OS;
	}

	/**
	 * @param oS the oS to set
	 */
	public void setOS(String oS) {
		OS = oS;
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
	 * @return the failureDetails
	 */
	public String getFailureDetails() {
		return FailureDetails;
	}

	/**
	 * @param failureDetails the failureDetails to set
	 */
	public void setFailureDetails(String failureDetails) {
		FailureDetails = failureDetails;
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
		return "LaneResults [TestRunID=" + TestRunID + ", ScheduleLaneID="
				+ ScheduleLaneID + ", AgentID=" + AgentID + ", BuildVersion="
				+ BuildVersion + ", OS=" + OS + ", Status=" + Status
				+ ", FailureDetails=" + FailureDetails + "]";
	}
}
