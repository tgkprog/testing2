package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Conditions entity.
 */

public class Conditions implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal conditionId;
	private ConditionGroup conditionGroup;
	private BigDecimal conditionGroupId;
	private String conditionName;
	private String description;
	private String expression;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public Conditions() {
		super();
	}

	/** minimal constructor */
	public Conditions(BigDecimal condid, ConditionGroup conditionGroup) {
		this.conditionId = condid;
		this.conditionGroup = conditionGroup;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "Conditions [conditionId=" + conditionId + ", conditionGroupId=" + conditionGroupId
				+ ", conditionName=" + conditionName + ", description=" + description + "]";
	}

	public BigDecimal getConditionId() {
		return conditionId;
	}

	public void setConditionId(BigDecimal conditionId) {
		this.conditionId = conditionId;
	}

	public ConditionGroup getConditionGroup() {
		return conditionGroup;
	}

	public void setConditionGroup(ConditionGroup conditionGroup) {
		this.conditionGroup = conditionGroup;
	}

	public BigDecimal getConditionGroupId() {
		return conditionGroupId;
	}

	public void setConditionGroupId(BigDecimal conditionGroupId) {
		this.conditionGroupId = conditionGroupId;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
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

}