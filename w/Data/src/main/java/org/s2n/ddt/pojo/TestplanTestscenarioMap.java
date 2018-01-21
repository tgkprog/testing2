package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestScenario;

/**
 * TestplanTestscenarioMap entity.
 */

public class TestplanTestscenarioMap implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal planScenarioId;
	private TestScenario testScenario;
	private BigDecimal testScenarioId;
	private TestPlan testPlan;
	private BigDecimal testPlanId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public TestplanTestscenarioMap() {
		super();
	}

	/** full constructor */
	public TestplanTestscenarioMap(BigDecimal testplantestscenarioid, TestScenario testscenario, TestPlan testplan, String createdby,
			Date createddatetime, String updatedby, Date updateddatetime) {
		this.planScenarioId = testplantestscenarioid;
		this.testScenario = testscenario;
		this.testPlan = testplan;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}

	public BigDecimal getPlanScenarioId() {
		return planScenarioId;
	}

	public void setPlanScenarioId(BigDecimal planScenarioId) {
		this.planScenarioId = planScenarioId;
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

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public BigDecimal getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(BigDecimal testPlanId) {
		this.testPlanId = testPlanId;
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
		return "TestplanTestscenarioMap [planScenarioId=" + planScenarioId + ", testScenarioId=" + testScenarioId
				+ ", testPlanId=" + testPlanId + "]";
	}



}