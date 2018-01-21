package org.s2n.ddt.pojo.output;

import java.io.File;
import java.io.Serializable;

import org.s2n.ddt.pojo.ConditionGroup;

/**
 * TestPlan entity.
 */

public class TestPlan implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private TestPlanId testPlanId;
	private ConditionGroup postConditionGroup;
	private ConditionGroup preConditionGroup;
	private String testPlanRunName;
	private File testDebugInfoFile;

	/** default constructor */
	public TestPlan() {
		super();
	}

	/**
	 * Full Constructor
	 * 
	 * @param testPlanId
	 * @param postConditionGroup
	 * @param preConditionGroup
	 */
	public TestPlan(TestPlanId testplanid, ConditionGroup conditiongroupByPostcondition, ConditionGroup conditiongroupByPrecondition) {
		super();
		this.testPlanId = testplanid;
		this.postConditionGroup = conditiongroupByPostcondition;
		this.preConditionGroup = conditiongroupByPrecondition;
	}

	/**
	 * unique name for a run, key to this and TestPlanResult of this run, use in
	 * Map as key. populated by core just before sending to Agent with unique
	 * name
	 * */
	public void setTestPlanRunName(String testPlanRunName) {
		this.testPlanRunName = testPlanRunName;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestPlan [testPlanId=" + testPlanId + "]";
	}

	public TestPlanId getTestPlanId() {
		return testPlanId;
	}

	public void setTestPlanId(TestPlanId testPlanId) {
		this.testPlanId = testPlanId;
	}

	public ConditionGroup getPostConditionGroup() {
		return postConditionGroup;
	}

	public void setPostConditionGroup(ConditionGroup postConditionGroup) {
		this.postConditionGroup = postConditionGroup;
	}

	public ConditionGroup getPreConditionGroup() {
		return preConditionGroup;
	}

	public void setPreConditionGroup(ConditionGroup preConditionGroup) {
		this.preConditionGroup = preConditionGroup;
	}

	public String getTestPlanRunName() {
		return testPlanRunName;
	}

	public File getTestDebugInfoFile() {
		return testDebugInfoFile;
	}

	public void setTestDebugInfoFile(File testDebugInfoFile) {
		this.testDebugInfoFile = testDebugInfoFile;
	}

}