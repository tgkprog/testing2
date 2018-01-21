package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.output.TestPlan;

/**
 * ConditionGroup entity.
 */

public class ConditionGroup implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal conditionGroupId;
	private Application application;
	private BigDecimal appId;
	private String conditionGroupName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestStep> postConditionStepsList = new ArrayList<TestStep>(0);
	private List<Conditions> conditionsList = new ArrayList<Conditions>(0);
	private List<TestPlan> preConditionPlansList = new ArrayList<TestPlan>(0);
	private List<TestStep> preConditionStepsList = new ArrayList<TestStep>(0);
	private List<TestPlan> postConditionPlansList = new ArrayList<TestPlan>(0);

	/** default constructor */
	public ConditionGroup() {
		super();
	}

	/** minimal constructor */
	public ConditionGroup(BigDecimal condgrpid, Application application, String condgrpname) {
		this.conditionGroupId = condgrpid;
		this.application = application;
		this.conditionGroupName = condgrpname;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ConditionGroup [conditionGroupId=" + conditionGroupId + ", appId=" + appId + ", conditionGroupName="
				+ conditionGroupName + ", description=" + description + "]";
	}

	public BigDecimal getConditionGroupId() {
		return conditionGroupId;
	}

	public void setConditionGroupId(BigDecimal conditionGroupId) {
		this.conditionGroupId = conditionGroupId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public String getConditionGroupName() {
		return conditionGroupName;
	}

	public void setConditionGroupName(String conditionGroupName) {
		this.conditionGroupName = conditionGroupName;
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

	public List<TestStep> getPostConditionStepsList() {
		return postConditionStepsList;
	}

	public void setPostConditionStepsList(List<TestStep> postConditionStepsList) {
		this.postConditionStepsList = postConditionStepsList;
	}

	public List<Conditions> getConditionsList() {
		return conditionsList;
	}

	public void setConditionsList(List<Conditions> conditionsList) {
		this.conditionsList = conditionsList;
	}

	public List<TestPlan> getPreConditionPlansList() {
		return preConditionPlansList;
	}

	public void setPreConditionPlansList(List<TestPlan> preConditionPlansList) {
		this.preConditionPlansList = preConditionPlansList;
	}

	public List<TestStep> getPreConditionStepsList() {
		return preConditionStepsList;
	}

	public void setPreConditionStepsList(List<TestStep> preConditionStepsList) {
		this.preConditionStepsList = preConditionStepsList;
	}

	public List<TestPlan> getPostConditionPlansList() {
		return postConditionPlansList;
	}

	public void setPostConditionPlansList(List<TestPlan> postConditionPlansList) {
		this.postConditionPlansList = postConditionPlansList;
	}

}