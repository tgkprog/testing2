package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;

import org.s2n.ddt.pojo.output.TestPlan;

public class Task implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal taskId;
	private int laneId;
	private String testPlanXlsPath;
	private TestPlan testPlan;
	private String taskName;
	private String dataSet;
	private Integer repeats;
	private String tagsToRun;
	private Boolean beforeClones;
	
	public BigDecimal getTaskId() {
		return taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public String getTestPlanXlsPath() {
		return testPlanXlsPath;
	}

	public void setTestPlanXlsPath(String testPlanXlsPath) {
		this.testPlanXlsPath = testPlanXlsPath;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDataSet() {
		return dataSet;
	}

	public void setDataSet(String dataSet) {
		this.dataSet = dataSet;
	}

	public Integer getRepeats() {
		return repeats;
	}

	public void setRepeats(Integer repeats) {
		this.repeats = repeats;
	}

	public String getTagsToRun() {
		return tagsToRun;
	}

	public void setTagsToRun(String tagsToRun) {
		this.tagsToRun = tagsToRun;
	}

	public Boolean getBeforeClones() {
		return beforeClones;
	}

	public void setBeforeClones(Boolean beforeClones) {
		this.beforeClones = beforeClones;
	}

	public int getLaneId() {
		return laneId;
	}

	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}

	





	

}
