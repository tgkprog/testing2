package org.s2n.ddt.pojo.output;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;

/*
 TestPlan
 TestScenarios

 TestSuite
 TestCase
 TestStep


 AgentDetails1 (1 or 2... where TestPlan is run)

 TestPlanResult - 1 object per TestPlanRun
 TestAgentRun (1 per Agent that ran)
 TestPlanResult (have reference to TestPlan) 
 TestScenarioResult (have reference to TestScenario) 
 TestSuiteResult (have reference to TestSuite) 
 TestCaseResult (have reference to TestCase)  
 TestStepResult  (have reference to TestStep)  

 * 
 * **/

public class PlanAgentResult implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal agentDetailsId;

	private AgentDetails agent;

	private File reportFilePath; // like "/data/ddt.../reports/3434/PLan1.html
	private URL reportURL;// like "http://coreUrl.local/reports/3434/PLan1.html"
	private String reportUrlPath;// like "/reports/3434/PLan1.html"

	public BigDecimal getAgentDetailsId() {
		return agentDetailsId;
	}

	public void setAgentDetailsId(BigDecimal agentDetailsId) {
		this.agentDetailsId = agentDetailsId;
	}

	public AgentDetails getAgent() {
		return agent;
	}

	public void setAgent(AgentDetails agent) {
		this.agent = agent;
	}

	public File getReportFilePath() {
		return reportFilePath;
	}

	public void setReportFilePath(File reportFilePath) {
		this.reportFilePath = reportFilePath;
	}

	public URL getReportURL() {
		return reportURL;
	}

	public void setReportURL(URL reportURL) {
		this.reportURL = reportURL;
	}

	public String getReportUrlPath() {
		return reportUrlPath;
	}

	public void setReportUrlPath(String reportUrlPath) {
		this.reportUrlPath = reportUrlPath;
	}

}
