package org.s2n.ddt.agent;

import java.util.ArrayList;

import org.s2n.ddt.pojo.input.TestCase;
import org.s2n.ddt.pojo.output.TestPlan;

public interface PlanMockI {//

	public abstract void makeMockTestCase1();

	public abstract ArrayList<TestCase> getCases();

	public abstract void setCases(ArrayList<TestCase> cases);

	public abstract TestPlan createPlan(String planRunName);

	public abstract TestPlan getTestPlan();

	public abstract void setTestPlan(TestPlan testPlan);

}
