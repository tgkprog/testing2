package org.s2n.ddt.pojo.input;

import java.io.Serializable;

import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.output.TestScenario;

/**
 * ParamGroupDao entity.
 */
public class ParamGroup implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private ParamGroupId paramGroupId;
	private TestScenario testScenario;
	private Application application;
	private TestCase testCase;
	private TestStep testStep;

	/** default constructor */
	public ParamGroup() {
		super();
	}

	/**
	 * Full Constructor
	 */
	public ParamGroup(ParamGroupId paramgroupid, TestScenario testScenario, Application application, TestCase testCase, TestStep testStep) {
		super();
		this.paramGroupId = paramgroupid;
		this.testScenario = testScenario;
		this.application = application;
		this.testCase = testCase;
		this.testStep = testStep;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ParamGroup [paramgroupid=" + paramGroupId + "]";
	}

	public ParamGroupId getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(ParamGroupId paramGroupId) {
		this.paramGroupId = paramGroupId;
	}

	public TestScenario getTestScenario() {
		return testScenario;
	}

	public void setTestScenario(TestScenario testScenario) {
		this.testScenario = testScenario;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public TestStep getTestStep() {
		return testStep;
	}

	public void setTestStep(TestStep testStep) {
		this.testStep = testStep;
	}

}