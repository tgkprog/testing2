package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.input.TestStep;

/**
 * Runner entity.
 */

public class Runner implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal runnerId;
	private String runnerName;
	private String runnerClass;//which class to use to make the concrete implementation. Example "org.s2n.ddt.runner.selenium.SeleniumRunner"
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestCase> testCases = new ArrayList<TestCase>(0);
	private List<TestStep> testSteps = new ArrayList<TestStep>(0);

	/** default constructor */
	public Runner() {
		super();
	}

	/** minimal constructor */
	public Runner(BigDecimal runnerid, String runnername, String createdby, Date createddatetime, String updatedby, Date updateddatetime) {
		this.runnerId = runnerid;
		this.runnerName = runnername;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}
	
	public BigDecimal getRunnerId() {
		return runnerId;
	}

	public void setRunnerId(BigDecimal runnerId) {
		this.runnerId = runnerId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<TestCase> getTestCases() {
		return testCases;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	public List<TestStep> getTestSteps() {
		return testSteps;
	}

	public void setTestSteps(List<TestStep> testSteps) {
		this.testSteps = testSteps;
	}

	@Override
	public String toString() {
		return "Runner [runnerId=" + runnerId + ", runnerName=" + runnerName + ", runnerClass=" + runnerClass + ", description=" + description
				+ "]";
	}



}