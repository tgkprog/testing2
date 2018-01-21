
/**
 * Test Stages as in App, Function, Feature .. TestSuite, Test Case, Test Step.
 * 
 */
package org.s2n.ddt.pojo.def;

public enum TestStage {

	APP(1, "App"), FUNCTION(2, "Function"), FEATURE(3, "Feature"), SUITE(4, "Test Suite"), CASE(5, "Case"), STEP(6, "Step")
	, PLAN(20, "Step"), SCENARIO(21, "Scenario");

	private int id;
	private String name;

	private TestStage(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
