package org.s2n.ddt.pojo.output;

import java.io.Serializable;

public class TestPlanRun implements Serializable {

	// Fields

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private TestPlanId testplanid;

	public TestPlanId getTestplanid() {
		return testplanid;
	}

	public void setTestplanid(TestPlanId testplanid) {
		this.testplanid = testplanid;
	}

	@Override
	public String toString() {
		return "TestPlanRun [testplanid=" + testplanid + "]";
	}

}
