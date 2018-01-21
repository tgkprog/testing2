package org.s2n.ddt.pojo;

import java.io.Serializable;

import org.s2n.ddt.pojo.output.TestPlan;

/**
 * TestSuite entity.
 */
public class TestSuite implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private TestSuiteId testSuiteId;
	private Feature feature;
	private Application application;
	private Functional functional;

	/** default constructor */
	public TestSuite() {
		super();
	}

	/** minimal constructor */
	public TestSuite(TestSuiteId testsuiteid, Feature feature, Application application, Functional functional, TestPlan testPlan) {
		this.testSuiteId = testsuiteid;
		this.feature = feature;
		this.application = application;
		this.functional = functional;

	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */

	@Override
	public String toString() {
		return "TestSuite [testSuiteId=" + testSuiteId + "]";
	}

	public TestSuiteId getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(TestSuiteId testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public Feature getFeature() {
		return feature;
	}

	public void setFeature(Feature feature) {
		this.feature = feature;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Functional getFunctional() {
		return functional;
	}

	public void setFunctional(Functional functional) {
		this.functional = functional;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}