package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.s2n.ddt.pojo.output.TestScenario;

/**
 * SuiteScenarioMapping entity.
 */

public class SuiteScenarioMapping implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal suiteScenarioId;
	private TestScenario testScenario;
	private BigDecimal testScenarioId;
	private TestSuite testSuite;
	private BigDecimal testSuiteId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public SuiteScenarioMapping() {
		super();
	}

	/** full constructor */
	public SuiteScenarioMapping(BigDecimal suitescenarioid, TestScenario testscenario, TestSuite testsuite, String createdby, Date createddatetime,
			String updatedby, Date updateddatetime) {
		this.suiteScenarioId = suitescenarioid;
		this.testScenario = testscenario;
		this.testSuite = testsuite;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}

	public BigDecimal getSuiteScenarioId() {
		return suiteScenarioId;
	}

	public void setSuiteScenarioId(BigDecimal suiteScenarioId) {
		this.suiteScenarioId = suiteScenarioId;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}

	public BigDecimal getTestScenarioId() {
		return testScenarioId;
	}

	public void setTestScenarioId(BigDecimal testScenarioId) {
		this.testScenarioId = testScenarioId;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	public BigDecimal getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(BigDecimal testSuiteId) {
		this.testSuiteId = testSuiteId;
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
	public String toString() {
		return "SuiteScenarioMapping [suiteScenarioId=" + suiteScenarioId + ",  testScenarioId=" + testScenarioId
				+ ", testSuiteId=" + testSuiteId + "]";
	}


}