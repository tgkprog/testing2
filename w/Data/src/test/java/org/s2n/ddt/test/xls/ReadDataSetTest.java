package org.s2n.ddt.test.xls;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.junit.Test;

import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.output.TestPlan;

public class ReadDataSetTest {
	private static final Logger logger = Logger.getLogger(ReadDataSetTest.class);
	private ReadPlan readPlan = new ReadPlan();
	private TestPlan plan = new TestPlan();

	@Test
	public void readSetTest() throws InvalidFormatException, IOException {
		// DdtRemoteInterface fwRemote = new AgentDataSet();
		// plan = readPlan.readPlanObj(filePath, null);
		// logger.info(" Case   "+plan.getTestPlanId().getTestScenariosList().get(0).getTestSuites().get(0).getTestSuiteId().getTestCases().get(2).getCaseName());
		// readPlan.readPlanDataSet(fwRemote);
	}
}
