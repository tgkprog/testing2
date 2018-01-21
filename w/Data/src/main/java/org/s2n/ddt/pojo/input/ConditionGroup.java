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
public class ConditionGroup implements Serializable {

	/**
	 * ConditionGroup Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ConditionGroupID;
	private String ConditionGroupName;
	private String Description;
	private BigDecimal AppID;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public BigDecimal getConditionGroupID() {
		return ConditionGroupID;
	}

	public void setConditionGroupID(BigDecimal conditionGroupID) {
		ConditionGroupID = conditionGroupID;
	}

	public String getConditionGroupName() {
		return ConditionGroupName;
	}

	public void setConditionGroupName(String conditionGroupName) {
		ConditionGroupName = conditionGroupName;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ConditionGroup() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ConditionGroup [ConditionGroupID=" + ConditionGroupID
				+ ", ConditionGroupName=" + ConditionGroupName
				+ ", Description=" + Description + ", AppID=" + AppID + "]";
	}
}
