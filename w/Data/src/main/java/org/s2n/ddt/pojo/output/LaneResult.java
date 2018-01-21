package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.sql.Timestamp;

public class LaneResult implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TaskResult task;
	private Timestamp totalTimeTaken;
	private String testStatus;
	private Timestamp numberOfIteration;

	public TaskResult getTask() {
		return task;
	}

	public void setTask(TaskResult task) {
		this.task = task;
	}

	public Timestamp getTotalTimeTaken() {
		return totalTimeTaken;
	}

	public void setTotalTimeTaken(Timestamp totalTimeTaken) {
		this.totalTimeTaken = totalTimeTaken;
	}

	public String getTestStatus() {
		return testStatus;
	}

	public void setTestStatus(String testStatus) {
		this.testStatus = testStatus;
	}

	public Timestamp getNumberOfIteration() {
		return numberOfIteration;
	}

	public void setNumberOfIteration(Timestamp numberOfIteration) {
		this.numberOfIteration = numberOfIteration;
	}

	@Override
	public String toString() {

		return "Lane [task=" + task + ", totalTimeTaken=" + totalTimeTaken + ", testStatus=" + testStatus + ", numberOfIteration=" + numberOfIteration
				+ "]";
	}
}
