package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

/**
 * TestRunDataId entity
 */
public class TestRunDataId implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Persistent Instance variables. This data is directly mapped to the
	 * columns of database table.
	 */
	private BigDecimal testRunDataId;
	private BigDecimal testPlanId;
	private BigDecimal testSuiteId;
	private BigDecimal testCaseId;
	private BigDecimal testStepId;
	private String exceptionMsg;
	private String status;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** Default Constructor */
	public TestRunDataId() {
		super();
	}

	@Override
	public String toString() {
		return "TestRunDataId [testRunDataId=" + testRunDataId + ", testPlanId=" + testPlanId + ", testSuiteId=" + testSuiteId + ", testCaseId="
				+ testCaseId + ", testStepId=" + testStepId + "]";
	}

	public BigDecimal getTestRunDataId() {
		return testRunDataId;
	}

	public void setTestRunDataId(BigDecimal testRunDataId) {
		this.testRunDataId = testRunDataId;
	}

	public BigDecimal getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(BigDecimal testPlanId) {
		this.testPlanId = testPlanId;
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
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

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((exceptionMsg == null) ? 0 : exceptionMsg.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((testCaseId == null) ? 0 : testCaseId.hashCode());
		result = prime * result + ((testPlanId == null) ? 0 : testPlanId.hashCode());
		result = prime * result + ((testRunDataId == null) ? 0 : testRunDataId.hashCode());
		result = prime * result + ((testStepId == null) ? 0 : testStepId.hashCode());
		result = prime * result + ((testSuiteId == null) ? 0 : testSuiteId.hashCode());
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
		TestRunDataId other = (TestRunDataId) obj;
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
		if (exceptionMsg == null) {
			if (other.exceptionMsg != null){
				return false;
			}
		} else if (!exceptionMsg.equals(other.exceptionMsg)){
			return false;
		}
		if (status == null) {
			if (other.status != null){
				return false;
			}
		} else if (!status.equals(other.status)){
			return false;
		}
		if (testCaseId == null) {
			if (other.testCaseId != null){
				return false;
			}
		} else if (!testCaseId.equals(other.testCaseId)){
			return false;
		}
		if (testPlanId == null) {
			if (other.testPlanId != null){
				return false;
			}
		} else if (!testPlanId.equals(other.testPlanId)){
			return false;
		}
		if (testRunDataId == null) {
			if (other.testRunDataId != null){
				return false;
			}
		} else if (!testRunDataId.equals(other.testRunDataId)){
			return false;
		}
		if (testStepId == null) {
			if (other.testStepId != null){
				return false;
			}
		} else if (!testStepId.equals(other.testStepId)){
			return false;
		}
		if (testSuiteId == null) {
			if (other.testSuiteId != null){
				return false;
			}
		} else if (!testSuiteId.equals(other.testSuiteId)){
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
