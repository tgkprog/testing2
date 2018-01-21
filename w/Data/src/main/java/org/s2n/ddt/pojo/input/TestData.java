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
public class TestData implements Serializable {

	/**
	 * TestData Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal TestDataID;
	private BigDecimal AppID;
	private String TestDataDescription;
	private String Status;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public TestData() {
		// TODO Auto-generated constructor stub
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
	 * @return the testDataDescription
	 */
	public String getTestDataDescription() {
		return TestDataDescription;
	}

	/**
	 * @param testDataDescription the testDataDescription to set
	 */
	public void setTestDataDescription(String testDataDescription) {
		TestDataDescription = testDataDescription;
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
		return "TestData [TestDataID=" + TestDataID + ", AppID=" + AppID
				+ ", TestDataDescription=" + TestDataDescription + ", Status="
				+ Status + "]";
	}
}
