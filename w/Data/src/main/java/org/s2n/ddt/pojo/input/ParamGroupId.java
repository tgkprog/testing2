package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParamGroupId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal paramGroupId;
	private BigDecimal testScenarioId;
	private BigDecimal appId;
	private BigDecimal testCaseId;
	private BigDecimal testStepId;
	private String paramGroupName;
	private String description;
	private String tag;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestStep> inputParamStepsList = new ArrayList<TestStep>(0);
	private List<TestStep> outputParamStepsList = new ArrayList<TestStep>(0);
	private List<Param> paramsList = new ArrayList<Param>(0);
	private List<TestStepParamGroupMapping> stepGroupMappings = new ArrayList<TestStepParamGroupMapping>(0);

	/**
	 * Default Constructor
	 */
	public ParamGroupId() {
		super();
	}

	/**
	 * Minimal Constructor
	 * 
	 * @param paramgroupid
	 * @param testScenarioId
	 * @param appId
	 * @param testCaseId
	 * @param testStepId
	 */
	public ParamGroupId(BigDecimal paramgroupid, BigDecimal testScenarioId, BigDecimal applicationId, BigDecimal testCaseId, BigDecimal testStepId) {
		super();
		this.paramGroupId = paramgroupid;
		this.testScenarioId = testScenarioId;
		this.appId = applicationId;
		this.testCaseId = testCaseId;
		this.testStepId = testStepId;
	}

	public BigDecimal getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(BigDecimal paramGroupId) {
		this.paramGroupId = paramGroupId;
	}

	public List<TestStepParamGroupMapping> getStepGroupMappings() {
		return stepGroupMappings;
	}

	public void setStepGroupMappings(List<TestStepParamGroupMapping> stepGroupMappings) {
		this.stepGroupMappings = stepGroupMappings;
	}

	public BigDecimal getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(BigDecimal testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public BigDecimal getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(BigDecimal testCaseId) {
		this.testCaseId = testCaseId;
	}

	public BigDecimal getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(BigDecimal testStepId) {
		this.testStepId = testStepId;
	}

	public String getParamGroupName() {
		return paramGroupName;
	}

	public void setParamGroupName(String paramGroupName) {
		this.paramGroupName = paramGroupName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
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

	public List<TestStep> getInputParamStepsList() {
		return inputParamStepsList;
	}

	public void setInputParamStepsList(List<TestStep> inputParamStepsList) {
		this.inputParamStepsList = inputParamStepsList;
	}

	public List<TestStep> getOutputParamStepsList() {
		return outputParamStepsList;
	}

	public void setOutputParamStepsList(List<TestStep> outputParamStepsList) {
		this.outputParamStepsList = outputParamStepsList;
	}

	public List<Param> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<Param> paramsList) {
		this.paramsList = paramsList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((inputParamStepsList == null) ? 0 : inputParamStepsList.hashCode());
		result = prime * result + ((outputParamStepsList == null) ? 0 : outputParamStepsList.hashCode());
		result = prime * result + ((paramGroupId == null) ? 0 : paramGroupId.hashCode());
		result = prime * result + ((paramGroupName == null) ? 0 : paramGroupName.hashCode());
		result = prime * result + ((paramsList == null) ? 0 : paramsList.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((testCaseId == null) ? 0 : testCaseId.hashCode());
		result = prime * result + ((testScenarioId == null) ? 0 : testScenarioId.hashCode());
		result = prime * result + ((testStepId == null) ? 0 : testStepId.hashCode());
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
		ParamGroupId other = (ParamGroupId) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId)){
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
		if (inputParamStepsList == null) {
			if (other.inputParamStepsList != null){
				return false;
			}
		} else if (!inputParamStepsList.equals(other.inputParamStepsList)){
			return false;
		}
		if (outputParamStepsList == null) {
			if (other.outputParamStepsList != null){
				return false;
			}
		} else if (!outputParamStepsList.equals(other.outputParamStepsList)){
			return false;
		}
		if (paramGroupId == null) {
			if (other.paramGroupId != null){
				return false;
			}
		} else if (!paramGroupId.equals(other.paramGroupId)){
			return false;
		}
		if (paramGroupName == null) {
			if (other.paramGroupName != null){
				return false;
			}
		} else if (!paramGroupName.equals(other.paramGroupName)){
			return false;
		}
		if (paramsList == null) {
			if (other.paramsList != null){
				return false;
			}
		} else if (!paramsList.equals(other.paramsList)){
			return false;
		}
		if (tag == null) {
			if (other.tag != null){
				return false;
			}
		} else if (!tag.equals(other.tag)){
			return false;
		}
		if (testCaseId == null) {
			if (other.testCaseId != null){
				return false;
			}
		} else if (!testCaseId.equals(other.testCaseId)){
			return false;
		}
		if (testScenarioId == null) {
			if (other.testScenarioId != null){
				return false;
			}
		} else if (!testScenarioId.equals(other.testScenarioId)){
			return false;
		}
		if (testStepId == null) {
			if (other.testStepId != null){
				return false;
			}
		} else if (!testStepId.equals(other.testStepId)){
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

}
