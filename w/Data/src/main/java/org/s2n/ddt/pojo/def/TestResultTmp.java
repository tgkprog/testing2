package org.s2n.ddt.pojo.def;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestResultTmp implements Serializable {

	/**
	 * Default
	 */
	private static final long serialVersionUID = 1L;

	private String runName;
	private String agentName;
	private BigDecimal planId;
	private BigDecimal scenarioId;
	private BigDecimal suiteId;
	private BigDecimal caseId;
	private BigDecimal stepId;
	private Boolean stepResult;
	private String runnerMsg;
	private long time;
	private String detailError;
	private TestStage stage;
	private long count;
	private Map<String, String> extn = new HashMap<String, String>(0);

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public BigDecimal getPlanId() {
		return planId;
	}

	public void setPlanId(BigDecimal planId) {
		this.planId = planId;
	}

	public BigDecimal getScenarioId() {
		return scenarioId;
	}

	public void setScenarioId(BigDecimal scenarioId) {
		this.scenarioId = scenarioId;
	}

	public BigDecimal getSuiteId() {
		return suiteId;
	}

	public void setSuiteId(BigDecimal suiteId) {
		this.suiteId = suiteId;
	}

	public BigDecimal getCaseId() {
		return caseId;
	}

	public void setCaseId(BigDecimal caseId) {
		this.caseId = caseId;
	}

	public BigDecimal getStepId() {
		return stepId;
	}

	public void setStepId(BigDecimal stepId) {
		this.stepId = stepId;
	}

	public Boolean getStepResult() {
		return stepResult;
	}

	public void setStepResult(Boolean stepResult) {
		this.stepResult = stepResult;
	}

	public String getRunnerMsg() {
		return runnerMsg;
	}

	public void setRunnerMsg(String runnerMsg) {
		this.runnerMsg = runnerMsg;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getDetailError() {
		return detailError;
	}

	public void setDetailError(String detailError) {
		this.detailError = detailError;
	}

	public TestStage getStage() {
		return stage;
	}

	public void setStage(TestStage stage) {
		this.stage = stage;
	}

	public Map<String, String> getExtn() {
		return extn;
	}

	public void setExtn(Map<String, String> extn) {
		this.extn = extn;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	@Override
	public String toString() {
		return "TestResultTmp [runName=" + runName + ", agentName=" + agentName + ", planId=" + planId + ", scenarioId=" + scenarioId + ", suiteId="
				+ suiteId + ", caseId=" + caseId + ", stepId=" + stepId + ", stepResult=" + stepResult + ", runnerMsg=" + runnerMsg + ", time=" + time
				+ ", detailError=" + detailError + ", count=" + count + ", extn=" + extn + ", stage=" + stage + "]";
	}

}
