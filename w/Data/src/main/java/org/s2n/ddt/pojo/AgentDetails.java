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
public class AgentDetails implements Serializable {

	/**
	 * AgentDetails Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal AgentID;
	private String AgentName;
	private String IP;
	private BigDecimal Port;
	private String MachineDetails;
	private String Instance;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
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
	 * @return the agentName
	 */
	public String getAgentName() {
		return AgentName;
	}
	/**
	 * @param agentName the agentName to set
	 */
	public void setAgentName(String agentName) {
		AgentName = agentName;
	}
	/**
	 * @return the iP
	 */
	public String getIP() {
		return IP;
	}
	/**
	 * @param iP the iP to set
	 */
	public void setIP(String iP) {
		IP = iP;
	}
	/**
	 * @return the port
	 */
	public BigDecimal getPort() {
		return Port;
	}
	/**
	 * @param port the port to set
	 */
	public void setPort(BigDecimal port) {
		Port = port;
	}
	/**
	 * @return the machineDetails
	 */
	public String getMachineDetails() {
		return MachineDetails;
	}
	/**
	 * @param machineDetails the machineDetails to set
	 */
	public void setMachineDetails(String machineDetails) {
		MachineDetails = machineDetails;
	}
	/**
	 * @return the instance
	 */
	public String getInstance() {
		return Instance;
	}
	/**
	 * @param instance the instance to set
	 */
	public void setInstance(String instance) {
		Instance = instance;
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
		return "AgentDetails [AgentID=" + AgentID + ", AgentName=" + AgentName
				+ ", IP=" + IP + ", Port=" + Port + ", MachineDetails="
				+ MachineDetails + ", Instance=" + Instance + "]";
	}
}
