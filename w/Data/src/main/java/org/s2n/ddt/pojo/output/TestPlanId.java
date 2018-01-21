package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.input.TestCondData;

/** TestPlanId entity */
public class TestPlanId implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal testPlanId;
	private BigDecimal postConditionGroupId;
	private BigDecimal preConditionGroupId;
	private String planName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String coreURL;
	private String updatedBy;
	private Date updatedDateTime;
	private List<AgentDetails> agentDetailsList = new ArrayList<AgentDetails>(0);
	private List<TestRunDetails> testRunDetailsList = new ArrayList<TestRunDetails>(0);
	private List<TestCondData> testCondDatas = new ArrayList<TestCondData>(0);
	private List<TestRunData> testRunDatas = new ArrayList<TestRunData>(0);
	private List<TestplanTestscenarioMap> planScenarioMappingList = new ArrayList<TestplanTestscenarioMap>(0);
	private List<TestScenario> testScenariosList = new ArrayList<TestScenario>(0);
	private List<Functional> functionals = new ArrayList<Functional>(0);

	/**
	 * Default Constructor
	 */
	public TestPlanId() {
		super();
	}

	/**
	 * Minimal Constructor
	 * 
	 * @param testPlanId
	 * @param postConditionGroupId
	 * @param testScenarioId
	 * @param preConditionGroupId
	 * @param testsuiteid
	 */
	public TestPlanId(BigDecimal testplanid, BigDecimal conditiongroupByPostconditionId, BigDecimal testScenarioId,
			BigDecimal conditiongroupByPreconditionId, BigDecimal testsuiteid) {
		super();
		this.testPlanId = testplanid;
		this.postConditionGroupId = conditiongroupByPostconditionId;
		this.preConditionGroupId = conditiongroupByPreconditionId;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestPlanId [testPlanId=" + testPlanId + ", postConditionGroupId=" + postConditionGroupId + ", preConditionGroupId=" + preConditionGroupId
				+ ", planName=" + planName + "]";
	}

	public BigDecimal getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(BigDecimal testPlanId) {
		this.testPlanId = testPlanId;
	}

	public BigDecimal getPostConditionGroupId() {
		return postConditionGroupId;
	}

	public void setPostConditionGroupId(BigDecimal postConditionGroupId) {
		this.postConditionGroupId = postConditionGroupId;
	}

	public BigDecimal getPreConditionGroupId() {
		return preConditionGroupId;
	}

	public void setPreConditionGroupId(BigDecimal preConditionGroupId) {
		this.preConditionGroupId = preConditionGroupId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
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

	public List<AgentDetails> getAgentDetailsList() {
		return agentDetailsList;
	}

	public void setAgentDetailsList(List<AgentDetails> agentDetailsList) {
		this.agentDetailsList = agentDetailsList;
	}

	public List<TestRunDetails> getTestRunDetailsList() {
		return testRunDetailsList;
	}

	public void setTestRunDetailsList(List<TestRunDetails> testRunDetailsList) {
		this.testRunDetailsList = testRunDetailsList;
	}

	public List<TestCondData> getTestCondDatas() {
		return testCondDatas;
	}

	public void setTestCondDatas(List<TestCondData> testCondDatas) {
		this.testCondDatas = testCondDatas;
	}

	public List<TestRunData> getTestRunDatas() {
		return testRunDatas;
	}

	public void setTestRunDatas(List<TestRunData> testRunDatas) {
		this.testRunDatas = testRunDatas;
	}

	public List<TestplanTestscenarioMap> getPlanScenarioMappingList() {
		return planScenarioMappingList;
	}

	public void setPlanScenarioMappingList(List<TestplanTestscenarioMap> planScenarioMappingList) {
		this.planScenarioMappingList = planScenarioMappingList;
	}

	public List<TestScenario> getTestScenariosList() {
		return testScenariosList;
	}

	public void setTestScenariosList(List<TestScenario> testScenariosList) {
		this.testScenariosList = testScenariosList;
	}

	public List<Functional> getFunctionals() {
		return functionals;
	}

	public void setFunctionals(List<Functional> functionals) {
		this.functionals = functionals;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentDetailsList == null) ? 0 : agentDetailsList.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((functionals == null) ? 0 : functionals.hashCode());
		result = prime * result + ((planName == null) ? 0 : planName.hashCode());
		result = prime * result + ((planScenarioMappingList == null) ? 0 : planScenarioMappingList.hashCode());
		result = prime * result + ((postConditionGroupId == null) ? 0 : postConditionGroupId.hashCode());
		result = prime * result + ((preConditionGroupId == null) ? 0 : preConditionGroupId.hashCode());
		result = prime * result + ((testCondDatas == null) ? 0 : testCondDatas.hashCode());
		result = prime * result + ((testPlanId == null) ? 0 : testPlanId.hashCode());
		result = prime * result + ((testRunDatas == null) ? 0 : testRunDatas.hashCode());
		result = prime * result + ((testRunDetailsList == null) ? 0 : testRunDetailsList.hashCode());
		result = prime * result + ((testScenariosList == null) ? 0 : testScenariosList.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDateTime == null) ? 0 : updatedDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		TestPlanId other = (TestPlanId) obj;
		if (agentDetailsList == null) {
			if (other.agentDetailsList != null){
				return false;
			}
		} else if (!agentDetailsList.equals(other.agentDetailsList)){
			return false;
		}
		if (createdBy == null) {
			if (other.createdBy != null){
				return false;
			}
		} else if (!createdBy.equals(other.createdBy)){
			return false;
		}
		if (createdDateTime == null) {
			if (other.createdDateTime != null){
				return false;
			}
		} else if (!createdDateTime.equals(other.createdDateTime)){
			return false;	
		}
		if (description == null) {
			if (other.description != null){
				return false;
			}
		} else if (!description.equals(other.description)){
			return false;
		}
		if (functionals == null) {
			if (other.functionals != null){
				return false;
			}
		} else if (!functionals.equals(other.functionals)){
			return false;
		}
		if (planName == null) {
			if (other.planName != null){
				return false;
			}
		} else if (!planName.equals(other.planName)){
			return false;
		}
		if (planScenarioMappingList == null) {
			if (other.planScenarioMappingList != null){
				return false;
			}
		} else if (!planScenarioMappingList.equals(other.planScenarioMappingList)){
			return false;
		}
		if (postConditionGroupId == null) {
			if (other.postConditionGroupId != null){
				return false;
			}
		} else if (!postConditionGroupId.equals(other.postConditionGroupId)){
			return false;
		}
		if (preConditionGroupId == null) {
			if (other.preConditionGroupId != null){
				return false;
			}
		} else if (!preConditionGroupId.equals(other.preConditionGroupId)){
			return false;
		}
		if (testCondDatas == null) {
			if (other.testCondDatas != null){
				return false;
			}
		} else if (!testCondDatas.equals(other.testCondDatas)){
			return false;
		}
		if (testPlanId == null) {
			if (other.testPlanId != null){
				return false;
			}
		} else if (!testPlanId.equals(other.testPlanId)){
			return false;
		}
		if (testRunDatas == null) {
			if (other.testRunDatas != null){
				return false;
			}
		} else if (!testRunDatas.equals(other.testRunDatas)){
			return false;
		}
		if (testRunDetailsList == null) {
			if (other.testRunDetailsList != null){
				return false;
			}
		} else if (!testRunDetailsList.equals(other.testRunDetailsList)){
			return false;
		}
		if (testScenariosList == null) {
			if (other.testScenariosList != null){
				return false;
			}
		} else if (!testScenariosList.equals(other.testScenariosList)){
			return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null){
				return false;
			}
		} else if (!updatedBy.equals(other.updatedBy)){
			return false;
		}
		if (updatedDateTime == null) {
			if (other.updatedDateTime != null){
				return false;
			}
		} else if (!updatedDateTime.equals(other.updatedDateTime)){
			return false;
		}
		return true;
	}

	public String getCoreURL() {
		return coreURL;
	}

	public void setCoreURL(String coreURL) {
		this.coreURL = coreURL;
	}

}
