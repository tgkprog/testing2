package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TestCondDataId entity.
 */

public class TestCondDataId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal testCondDataId;
	private BigDecimal testPlanId;
	private BigDecimal conditionId;
	private BigDecimal paramId;
	private String paramValue;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public TestCondDataId() {
		super();
	}

	/** minimal constructor */
	public TestCondDataId(BigDecimal testconddataid, BigDecimal testplanid, BigDecimal paramid) {
		this.testCondDataId = testconddataid;
		this.testPlanId = testplanid;
		this.paramId = paramid;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestCondDataId [testconddataid=" + testCondDataId + ", testplanid=" + testPlanId + ", conditionid=" + conditionId + ", paramid=" + paramId
				+ "]";
	}

	public BigDecimal getTestCondDataId() {
		return testCondDataId;
	}

	public void setTestCondDataId(BigDecimal testCondDataId) {
		this.testCondDataId = testCondDataId;
	}

	public BigDecimal getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(BigDecimal testPlanId) {
		this.testPlanId = testPlanId;
	}

	public BigDecimal getConditionId() {
		return conditionId;
	}

	public void setConditionId(BigDecimal conditionId) {
		this.conditionId = conditionId;
	}

	public BigDecimal getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conditionId == null) ? 0 : conditionId.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((paramId == null) ? 0 : paramId.hashCode());
		result = prime * result + ((paramValue == null) ? 0 : paramValue.hashCode());
		result = prime * result + ((testCondDataId == null) ? 0 : testCondDataId.hashCode());
		result = prime * result + ((testPlanId == null) ? 0 : testPlanId.hashCode());
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
		TestCondDataId other = (TestCondDataId) obj;
		if (conditionId == null) {
			if (other.conditionId != null){
				return false;
			}
		} else if (!conditionId.equals(other.conditionId)){
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
		if (paramId == null) {
			if (other.paramId != null){
				return false;
			}
		} else if (!paramId.equals(other.paramId)){
			return false;
		}
		if (paramValue == null) {
			if (other.paramValue != null){
				return false;
			}
		} else if (!paramValue.equals(other.paramValue)){
			return false;
		}
		if (testCondDataId == null) {
			if (other.testCondDataId != null){
				return false;
			}
		} else if (!testCondDataId.equals(other.testCondDataId)){
			return false;
		}
		if (testPlanId == null) {
			if (other.testPlanId != null){
				return false;
			}
		} else if (!testPlanId.equals(other.testPlanId)){
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