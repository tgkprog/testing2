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
public class TestConditionData implements Serializable {

	/**
	 * TestConditionData Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestConditionDataID;
	private BigDecimal TestDataID;
	private BigDecimal ConditionGroupID;
	private BigDecimal ConditionID;
	private String ConditionValue;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public TestConditionData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the testConditionDataID
	 */
	public BigDecimal getTestConditionDataID() {
		return TestConditionDataID;
	}

	/**
	 * @param testConditionDataID the testConditionDataID to set
	 */
	public void setTestConditionDataID(BigDecimal testConditionDataID) {
		TestConditionDataID = testConditionDataID;
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
	 * @return the conditionGroupID
	 */
	public BigDecimal getConditionGroupID() {
		return ConditionGroupID;
	}

	/**
	 * @param conditionGroupID the conditionGroupID to set
	 */
	public void setConditionGroupID(BigDecimal conditionGroupID) {
		ConditionGroupID = conditionGroupID;
	}

	/**
	 * @return the conditionID
	 */
	public BigDecimal getConditionID() {
		return ConditionID;
	}

	/**
	 * @param conditionID the conditionID to set
	 */
	public void setConditionID(BigDecimal conditionID) {
		ConditionID = conditionID;
	}

	/**
	 * @return the conditionValue
	 */
	public String getConditionValue() {
		return ConditionValue;
	}

	/**
	 * @param conditionValue the conditionValue to set
	 */
	public void setConditionValue(String conditionValue) {
		ConditionValue = conditionValue;
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
		return "TestConditionData [TestConditionDataID=" + TestConditionDataID
				+ ", TestDataID=" + TestDataID + ", ConditionGroupID="
				+ ConditionGroupID + ", ConditionID=" + ConditionID
				+ ", ConditionValue=" + ConditionValue + "]";
	}
}
