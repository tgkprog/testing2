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
public class TestScenario implements Serializable {

	/**
	 * TestScenario Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestScenarioID;
	private String TestScenarioName;
	private String Description;
	private BigDecimal AppID;
	private BigDecimal TestSuiteID;
	private BigDecimal SortOrder;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public TestScenario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the testScenarioID
	 */
	public BigDecimal getTestScenarioID() {
		return TestScenarioID;
	}

	/**
	 * @param testScenarioID the testScenarioID to set
	 */
	public void setTestScenarioID(BigDecimal testScenarioID) {
		TestScenarioID = testScenarioID;
	}

	/**
	 * @return the testScenarioName
	 */
	public String getTestScenarioName() {
		return TestScenarioName;
	}

	/**
	 * @param testScenarioName the testScenarioName to set
	 */
	public void setTestScenarioName(String testScenarioName) {
		TestScenarioName = testScenarioName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
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
	 * @return the sortOrder
	 */
	public BigDecimal getSortOrder() {
		return SortOrder;
	}

	/**
	 * @param sortOrder the sortOrder to set
	 */
	public void setSortOrder(BigDecimal sortOrder) {
		SortOrder = sortOrder;
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
		return "TestScenario [TestScenarioID=" + TestScenarioID
				+ ", TestScenarioName=" + TestScenarioName + ", Description="
				+ Description + ", AppID=" + AppID + ", TestSuiteID="
				+ TestSuiteID + ", SortOrder=" + SortOrder + "]";
	}
}
