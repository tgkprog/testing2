package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AgentDetails entity.
 */
public class AgentDetails implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	// todo Agent main app path.
	private BigDecimal agentId;
	private TestPlan testPlan;
	private BigDecimal testPlanId;
	private String agentName;
	private String ip;
	private int port;
	private BigDecimal machineId;
	private String os;
	private String instance;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private Boolean status;

	/** default constructor */
	public AgentDetails() {
		super();
	}

	/** minimal constructor */
	public AgentDetails(BigDecimal agentid, TestPlan testPlan, String agentname, String createdby, Date createddatetime, String updatedby,
			Date updateddatetime) {
		this.agentId = agentid;
		this.testPlan = testPlan;
		this.agentName = agentname;
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
		return "AgentDetails [agentId=" + agentId + ", testPlan=" + testPlan + ", testPlanId=" + testPlanId + ", agentName=" + agentName + ", ip=" + ip
				+ ", port=" + port + "]";
	}

	/** similar to "toString()" function which returns ip, port & agentname */
	public String strA() {
		StringBuilder sb = new StringBuilder().append("[").append(this.getAgentName()).append(", ip :").append(this.getIp()).append(", port :")
				.append(this.getPort()).append("]");
		return sb.toString();
	}

	public BigDecimal getAgentId() {
		return agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
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

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public BigDecimal getMachineId() {
		return machineId;
	}

	public void setMachineId(BigDecimal machineId) {
		this.machineId = machineId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}