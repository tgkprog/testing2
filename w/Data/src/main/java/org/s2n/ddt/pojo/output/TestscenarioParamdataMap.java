package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.s2n.ddt.pojo.input.TestParamData;

/**
 * TestscenarioParamdataMap entity.
 */
public class TestscenarioParamdataMap implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal scenarioParamDataId;
	private TestScenario testScenario;
	private BigDecimal testScenarioId;
	private TestParamData testParamData;
	private BigDecimal testParamDataId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public TestscenarioParamdataMap() {
		super();
	}

	/** full constructor */
	public TestscenarioParamdataMap(BigDecimal testscenarioparamdataid, TestScenario testscenario, TestParamData testparamdata, String createdby,
			Date createddatetime, String updatedby, Date updateddatetime) {
		this.scenarioParamDataId = testscenarioparamdataid;
		this.testScenario = testscenario;
		this.testParamData = testparamdata;
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
		return "TestscenarioParamdataMap [scenarioParamDataId=" + scenarioParamDataId + ", testScenarioId="
				+ testScenarioId + "]";
	}

	public BigDecimal getScenarioParamDataId() {
		return scenarioParamDataId;
	}

	public void setScenarioParamDataId(BigDecimal scenarioParamDataId) {
		this.scenarioParamDataId = scenarioParamDataId;
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

	public TestParamData getTestParamData() {
		return testParamData;
	}

	public void setTestParamData(TestParamData testParamData) {
		this.testParamData = testParamData;
	}

	public BigDecimal getTestParamDataId() {
		return testParamDataId;
	}

	public void setTestParamDataId(BigDecimal testParamDataId) {
		this.testParamDataId = testParamDataId;
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
