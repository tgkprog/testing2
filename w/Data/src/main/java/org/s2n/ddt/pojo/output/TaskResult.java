package org.s2n.ddt.pojo.output;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TaskResult implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal taskId;
	private int laneCloneId;
	private int laneRepeatId;
	private int taskRepeatId;
	private int taskResultId;
	private int runResultId;
	
	private TestPlanResult testPlanResult;
	private TestPlan testPlan;
	private File reportFilePath;
	private String nextTask;
	private AgentDetails agent;//Strings, ints
	private String agentName;
	private BigDecimal agentId;
	private String agentOS;	
	private BigDecimal runnerSet;
	private String agentRunnerType;
	private String runnerName;
	//private List<TestSuite> testSuite = new ArrayList<TestSuite>();
	private BigDecimal dataSet;
	private String testSuiteFilePath;
	private String agentPassword;
	private String ip;
	private int port;
	private int numberOfLaneCloning;
	private int repeatationNumberOfCLone;	
	private int laneNo;	
	private String repeatationNameOfTask;
	//any name user types - for report display
	private String laneName;
	
	//any name user types - for report display
	private String taskName;
	
	private File taskDetailReportDir;
	
	//Task Result Start time and End time for reporting
	private Date startTime;
	private Date endTime;
	
	public File getTaskDetailReportDir() {
		return taskDetailReportDir;
	}

	public void setTaskDetailReportDir(File taskDetailReportDir1) {
		taskDetailReportDir = taskDetailReportDir1;
	}

	public BigDecimal getTaskId() {
		return taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public AgentDetails getAgent() {
		return agent;
	}

	public void setAgent(AgentDetails agent) {
		this.agent = agent;
	}

	public String getNextTask() {
		return nextTask;
	}

	public void setNextTask(String nextTask) {
		this.nextTask = nextTask;
	}

	public BigDecimal getRunnerSet() {
		return runnerSet;
	}

	public void setRunnerSet(BigDecimal runnerSet) {
		this.runnerSet = runnerSet;
	}

	public String getAgentRunnerType() {
		return agentRunnerType;
	}

	public void setAgentRunnerType(String agentRunnerType) {
		this.agentRunnerType = agentRunnerType;
	}

	public BigDecimal getDataSet() {
		return dataSet;
	}

	public void setDataSet(BigDecimal dataSet) {
		this.dataSet = dataSet;
	}

	public String getTestSuiteFilePath() {
		return testSuiteFilePath;
	}

	public void setTestSuiteFilePath(String testSuiteFilePath) {
		this.testSuiteFilePath = testSuiteFilePath;
	}

	public String getAgentPassword() {
		return agentPassword;
	}

	public void setAgentPassword(String agentPassword) {
		this.agentPassword = agentPassword;
	}

	public int getNumberOfLaneCloning() {
		return numberOfLaneCloning;
	}

	public void setNumberOfLaneCloning(int numberOfLaneCloning) {
		this.numberOfLaneCloning = numberOfLaneCloning;
	}

	@Override
	public String toString() {

		return "Task [taskId=" + taskId + ", agent=" + agent + ", runnerSet=" + runnerSet + ", agentRunnerType=" + agentRunnerType + ",  dataSet=" + dataSet + "]";
	}

	public String getReputationNameOfTask() {
		return repeatationNameOfTask;
	}

	public void setReputationNameOfTask(String reputationNameOfTask) {
		this.repeatationNameOfTask = reputationNameOfTask;
	}

	public TestPlanResult getTestPlanResult() {
		return testPlanResult;
	}

	public void setTestPlanResult(TestPlanResult testPlanResult) {
		this.testPlanResult = testPlanResult;
	}

	public String getRunnerName() {
		return runnerName;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}

	public String getLaneName() {
		return laneName;
	}

	public void setLaneName(String laneName) {
		this.laneName = laneName;
	}
	//delete use laneCloneId
	public int getRepeatationNumberOfCLone() {
		return repeatationNumberOfCLone;
	}

	public void setRepeatationNumberOfCLone(int repeatationNumberOfCLone) {
		this.repeatationNumberOfCLone = repeatationNumberOfCLone;
	}

	public int getLaneNo() {
		return laneNo;
	}

	public void setLaneNo(int laneNo) {
		this.laneNo = laneNo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public File getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(File reportFilePath) {
		this.reportFilePath = reportFilePath;
	}

	public BigDecimal getAgentId() {
		return agentId;
	}

	public void setAgentId(BigDecimal agentId) {
		this.agentId = agentId;
	}

	public String getAgentOS() {
		return agentOS;
	}

	public void setAgentOS(String agentOS) {
		this.agentOS = agentOS;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public int getLaneCloneId() {
		return laneCloneId;
	}

	public void setLaneCloneId(int laneCloneId) {
		this.laneCloneId = laneCloneId;
	}

	public int getLaneRepeatId() {
		return laneRepeatId;
	}

	public void setLaneRepeatId(int laneRepeatId) {
		this.laneRepeatId = laneRepeatId;
	}

	public int getTaskRepeatId() {
		return taskRepeatId;
	}

	public void setTaskRepeatId(int taskRepeatId) {
		this.taskRepeatId = taskRepeatId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getTaskResultId() {
		return taskResultId;
	}

	public void setTaskResultId(int taskResultId) {
		this.taskResultId = taskResultId;
	}

	public int getRunResultId() {
		return runResultId;
	}

	public void setRunResultId(int runResultId) {
		this.runResultId = runResultId;
	}
}
