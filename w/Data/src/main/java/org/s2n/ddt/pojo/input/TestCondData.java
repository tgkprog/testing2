package org.s2n.ddt.pojo.input;

import java.io.Serializable;

import org.s2n.ddt.pojo.Conditions;
import org.s2n.ddt.pojo.output.TestPlan;

/**
 * TestCondData entity.
 */

public class TestCondData implements Serializable {

	/**
	 * Default serial version testCondDataId
	 */
	private static final long serialVersionUID = 1L;
	private TestCondDataId testCondDataId;
	private Param param;
	private TestPlan testPlan;
	private Conditions conditions;

	/** default constructor */
	public TestCondData() {
		super();
	}

	/** full constructor */
	public TestCondData(TestCondDataId id, Param param, TestPlan testPlan, Conditions conditions) {
		this.testCondDataId = id;
		this.param = param;
		this.testPlan = testPlan;
		this.conditions = conditions;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestCondData [testCondDataId=" + testCondDataId + "]";
	}

	public TestCondDataId getTestCondDataId() {
		return testCondDataId;
	}

	public void setTestCondDataId(TestCondDataId testCondDataId) {
		this.testCondDataId = testCondDataId;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public TestPlan getTestPlan() {
		return testPlan;
	}

	public void setTestPlan(TestPlan testPlan) {
		this.testPlan = testPlan;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

}