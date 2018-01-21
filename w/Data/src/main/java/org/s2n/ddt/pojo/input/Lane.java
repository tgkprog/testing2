package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Lane implements Serializable {

	
	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 2L;
	
	// Added for DB operations.
	private int laneId;
	
	private List<Task> tasks = new ArrayList<Task> (0);
	private int clones;
	private int iterations;
	private String runnerType;
	private String laneName;//full sheet name
	private String laneUserName;
	//private int laneSheetInt;
	private String  agentName;
	private int laneNumberInPlan;
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public int getClones() {
		return clones;
	}
	public void setClones(int clones) {
		this.clones = clones;
	}
	public int getIterations() {
		return iterations;
	}
	public void setIterations(int iterations) {
		this.iterations = iterations;
	}
	public String getRunnerType() {
		return runnerType;
	}
	public void setRunnerType(String runnerType) {
		this.runnerType = runnerType;
	}
	public String getLaneName() {
		return laneName;
	}
	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}
	public String getLaneUserName() {
		return laneUserName;
	}
	public void setLaneUserName(String laneUserName) {
		this.laneUserName = laneUserName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public void setLaneNumberInPlan(int i) {
		laneNumberInPlan = i;
	}
	
	public int getLaneNumberInPlan() {
		return laneNumberInPlan;
	}
	public int getLaneId() {
		return laneId;
	}
	public void setLaneId(int laneId) {
		this.laneId = laneId;
	}
}
