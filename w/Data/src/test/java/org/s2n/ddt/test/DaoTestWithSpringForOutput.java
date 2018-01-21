package org.s2n.ddt.test;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.service.OutputService;

public class DaoTestWithSpringForOutput {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoTestWithSpringForOutput.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws DataAccessException {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });

		OutputService outputServiceTP = (OutputService) context.getBean("outputService");
		logger.info("getTestPlanDetailsById: " + outputServiceTP.getTestPlanDetailsById(1));
		logger.info("getTestPlanDetailsByPostConditionId: " + outputServiceTP.getTestPlanDetailsByPostConditionId(1));
		logger.info("getTestPlanDetailsByPreConditionId: " + outputServiceTP.getTestPlanDetailsByPreConditionId(1));

		OutputService outputServiceTR = (OutputService) context.getBean("outputService");
		logger.info("getTestReportById: " + outputServiceTR.getTestReportById(1));
		logger.info("getTestReportByRunId: " + outputServiceTR.getTestReportByRunId(101));

		OutputService outputServiceTRD = (OutputService) context.getBean("outputService");
		logger.info("getTestRunDataById: " + outputServiceTRD.getTestRunDataById(1));
		logger.info("getTestRunDataByPlanId: " + outputServiceTRD.getTestRunDataByPlanId(12));
		logger.info("getTestRunDataByTestCaseId: " + outputServiceTRD.getTestRunDataByTestCaseId(45));

		OutputService outputServiceTRDD = (OutputService) context.getBean("outputService");
		logger.info("getTestRunDetailsById: " + outputServiceTRDD.getTestRunDetailsById(11));
		logger.info("getTestRunDetailsByPlanId: " + outputServiceTRDD.getTestRunDetailsByPlanId(12));

		OutputService outputServiceTS = (OutputService) context.getBean("outputService");
		logger.info("getTestScenarioDetailsById: " + outputServiceTS.getTestScenarioDetailsById(1));
		logger.info("getTestScenariosByAppId: " + outputServiceTS.getTestScenariosByAppId(2));

		OutputService outputServiceTSP = (OutputService) context.getBean("outputService");
		logger.info("getTestScenarioParamDataById: " + outputServiceTSP.getTestScenarioParamDataById(3));
		logger.info("getTestscenarioParamdataMapbyParamDataId: " + outputServiceTSP.getTestscenarioParamdataMapbyParamDataId(5));
		logger.info("getTestscenarioParamdataMapbyScenarioId: " + outputServiceTSP.getTestscenarioParamdataMapbyScenarioId(2));

	}

}
