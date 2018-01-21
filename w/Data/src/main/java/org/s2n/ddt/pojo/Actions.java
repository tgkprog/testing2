package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.TestStep;

/**
 * Actions entity.
 */

public class Actions implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private BigDecimal actionId;
	private String actionName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestStep> testSteps = new ArrayList<TestStep>(0);
	private List<ObjectType> objectTypes = new ArrayList<ObjectType>(0);

	/** default constructor */
	public Actions() {
		super();
	}

	/** minimal constructor */
	public Actions(BigDecimal actionid, String actionname, String createdby, Date createddatetime, String updatedby, Date updateddatetime) {
		this.actionId = actionid;
		this.actionName = actionname;
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
		return "Actions [actionId=" + actionId + ", actionName=" + actionName + ", description=" + description + "]";
	}

	public BigDecimal getActionId() {
		return actionId;
	}

	public void setActionId(BigDecimal actionId) {
		this.actionId = actionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	public List<ObjectType> getObjectTypes() {
		return objectTypes;
	}

	public void setObjectTypes(List<ObjectType> objectTypes) {
		this.objectTypes = objectTypes;
	}

}