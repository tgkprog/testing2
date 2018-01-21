package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.ConditionGroup;
import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.output.TestRunData;

/**
 * TestStep entity.
 */

public class TestStep implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private TestStepId testStepId;
	private ConditionGroup postConditionGroup;
	private ParamGroup inputParamGroup;
	private Actions actions;
	private TestCase testCase;
	private Runner runner;
	private ParamGroup outputParamGroup;
	private ConditionGroup preConditionGroup;
	private String stepParam;
	

	/** default constructor */
	public TestStep() {
		super();
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestStep [teststepid=" + testStepId + "]";
	}

	public TestStepId getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(TestStepId testStepId) {
		this.testStepId = testStepId;
	}

	public ConditionGroup getPostConditionGroup() {
		return postConditionGroup;
	}

	public void setPostConditionGroup(ConditionGroup postConditionGroup) {
		this.postConditionGroup = postConditionGroup;
	}

	public ParamGroup getInputParamGroup() {
		return inputParamGroup;
	}

	public void setInputParamGroup(ParamGroup inputParamGroup) {
		this.inputParamGroup = inputParamGroup;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public Runner getRunner() {
		return runner;
	}

	public void setRunner(Runner runner) {
		this.runner = runner;
	}

	public ParamGroup getOutputParamGroup() {
		return outputParamGroup;
	}

	public void setOutputParamGroup(ParamGroup outputParamGroup) {
		this.outputParamGroup = outputParamGroup;
	}

	public ConditionGroup getPreConditionGroup() {
		return preConditionGroup;
	}

	public void setPreConditionGroup(ConditionGroup preConditionGroup) {
		this.preConditionGroup = preConditionGroup;
	}

	public String getStepParam() {
		return stepParam;
	}

	public void setStepParam(String stepParam) {
		this.stepParam = stepParam;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		return super.clone();
	}
}