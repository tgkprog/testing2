/**
 * 
 */
package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mohammedfirdos
 *
 */
public class TestPlan implements Serializable {

	/**
	 * TestPlan Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestPlanID;
	private String PlanName;
	private BigDecimal Description;
	private BigDecimal TestSuiteID;
	private BigDecimal AppID;
	private BigDecimal PreConditionGroupID;
	private BigDecimal PostConditionGroupID;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public TestPlan() {
		// TODO Auto-generated constructor stub
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
	 * @return the planName
	 */
	public String getPlanName() {
		return PlanName;
	}

	/**
	 * @param planName the planName to set
	 */
	public void setPlanName(String planName) {
		PlanName = planName;
	}

	/**
	 * @return the description
	 */
	public BigDecimal getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(BigDecimal description) {
		Description = description;
	}

	/**
	 * @return the testSuiteID
	 */
	public BigDecimal getTestSuiteID() {
		return TestSuiteID;
	}

	/**
	 * @param testSuiteID the testSuiteID to set
	 */
	public void setTestSuiteID(BigDecimal testSuiteID) {
		TestSuiteID = testSuiteID;
	}

	/**
	 * @return the appID
	 */
	public BigDecimal getAppID() {
		return AppID;
	}

	/**
	 * @param appID the appID to set
	 */
	public void setAppID(BigDecimal appID) {
		AppID = appID;
	}

	/**
	 * @return the preConditionGroupID
	 */
	public BigDecimal getPreConditionGroupID() {
		return PreConditionGroupID;
	}

	/**
	 * @param preConditionGroupID the preConditionGroupID to set
	 */
	public void setPreConditionGroupID(BigDecimal preConditionGroupID) {
		PreConditionGroupID = preConditionGroupID;
	}

	/**
	 * @return the postConditionGroupID
	 */
	public BigDecimal getPostConditionGroupID() {
		return PostConditionGroupID;
	}

	/**
	 * @param postConditionGroupID the postConditionGroupID to set
	 */
	public void setPostConditionGroupID(BigDecimal postConditionGroupID) {
		PostConditionGroupID = postConditionGroupID;
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
		return "TestPlan [TestPlanID=" + TestPlanID + ", PlanName=" + PlanName
				+ ", Description=" + Description + ", TestSuiteID="
				+ TestSuiteID + ", AppID=" + AppID + ", PreConditionGroupID="
				+ PreConditionGroupID + ", PostConditionGroupID="
				+ PostConditionGroupID + "]";
	}
}
