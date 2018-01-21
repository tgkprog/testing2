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
public class Conditions implements Serializable {

	/**
	 * Conditions Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ConditionID;
	private String ConditionName;
	private String Description;
	private String Expression;
	private BigDecimal ConditionGroupID;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	
	public Conditions() {
		// TODO Auto-generated constructor stub
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
	 * @return the conditionName
	 */
	public String getConditionName() {
		return ConditionName;
	}

	/**
	 * @param conditionName the conditionName to set
	 */
	public void setConditionName(String conditionName) {
		ConditionName = conditionName;
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
	 * @return the expression
	 */
	public String getExpression() {
		return Expression;
	}

	/**
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		Expression = expression;
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
		return "Conditions [ConditionID=" + ConditionID + ", ConditionName="
				+ ConditionName + ", Description=" + Description
				+ ", Expression=" + Expression + ", ConditionGroupID="
				+ ConditionGroupID + "]";
	}

}
