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
public class AppFunctionality implements Serializable {

	/**
	 * AppFunctionality Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal FunctionalID;
	private String FunctionalName;
	private String Description;
	private BigDecimal AppID;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public AppFunctionality() {
		// TODO Auto-generated constructor stub
	}

	public BigDecimal getFunctionalID() {
		return FunctionalID;
	}

	public void setFunctionalID(BigDecimal functionalID) {
		FunctionalID = functionalID;
	}

	public String getFunctionalName() {
		return FunctionalName;
	}

	public void setFunctionalName(String functionalName) {
		FunctionalName = functionalName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public BigDecimal getAppID() {
		return AppID;
	}

	public void setAppID(BigDecimal appID) {
		AppID = appID;
	}

	public String getCreatedBy() {
		return CreatedBy;
	}

	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return CreatedDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		CreatedDateTime = createdDateTime;
	}

	public String getUpdatedBy() {
		return UpdatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	public Date getUpdatedDateTime() {
		return UpdatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		UpdatedDateTime = updatedDateTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppFunctionality [FunctionalID=" + FunctionalID
				+ ", FunctionalName=" + FunctionalName + ", Description="
				+ Description + ", AppID=" + AppID + "]";
	}
}
