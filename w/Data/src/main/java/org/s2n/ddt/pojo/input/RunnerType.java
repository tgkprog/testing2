package org.s2n.ddt.pojo.input;

import java.io.Serializable;

public class RunnerType implements Serializable{
	private Boolean threadSafe;
	private Boolean cloneable;
	private String runnerName;
	private String runnerClass;
	private int Active;
	public Boolean getThreadSafe() {
		return threadSafe;
	}
	public void setThreadSafe(Boolean threadSafe) {
		this.threadSafe = threadSafe;
	}
	public Boolean getCloneable() {
		return cloneable;
	}
	public void setCloneable(Boolean cloneable) {
		this.cloneable = cloneable;
	}
	public String getRunnerName() {
		return runnerName;
	}
	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}
	public String getRunnerClass() {
		return runnerClass;
	}
	public void setRunnerClass(String runnerClass) {
		this.runnerClass = runnerClass;
	}
	public int getActive() {
		return Active;
	}
	public void setActive(int active) {
		Active = active;
	}
	

}
