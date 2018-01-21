package org.s2n.ddt.pojo.input;

import java.io.Serializable;

import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.output.TestPlan;

/**
 * TestParamData entity.
 */

public class TestParamData implements Serializable {

	/**
	 * Default serial version testParamDataId
	 */
	private static final long serialVersionUID = 1L;
	private TestParamDataId testParamDataId;
	private Param param;
	private TestCase testCase;
	private TestStep testStep;
	private Application application;
	private String value;//This should be the test param value in  from the xls cell/ text box
	private byte[] valueBig;//if len is too big convert to bytes or if value is a file, store here

	/** default constructor */
	public TestParamData() {
		super();
	}

	/** full constructor */
	public TestParamData(TestParamDataId id, Param param, TestCase testCase, TestStep testStep, TestPlan testPlan, Application application) {
		this.testParamDataId = id;
		this.param = param;
		this.testCase = testCase;
		this.testStep = testStep;
		this.application = application;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestParamData [testParamDataId=" + testParamDataId + "]";
	}

	public TestParamDataId getTestParamDataId() {
		return testParamDataId;
	}

	public void setTestParamDataId(TestParamDataId testParamDataId) {
		this.testParamDataId = testParamDataId;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
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

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public byte[] getValueBig() {
		return valueBig;
	}

	public void setValueBig(byte[] valueBig) {
		this.valueBig = valueBig;
	}

}