package org.s2n.ddt.test;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.service.MainService;

public class DaoTestWithSpringForMain {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoTestWithSpringForMain.class);

	/**
	 * @param args
	 * @throws DataAccessException
	 */
	public static void main(String[] args) throws DataAccessException {

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });
		MainService mainServiceApp = (MainService) context.getBean("mainService");

		logger.info("getApplicationDetailsTillFunctional : " + mainServiceApp.getApplicationDetailsTillFunctional(1));
		logger.info("getMaxAppId : " + mainServiceApp.getMaxAppId());
		logger.info("getApplicationDetailsById : " + mainServiceApp.getApplicationDetailsById(1));
		logger.info("getApplicationDetailsTillIdentifierType : " + mainServiceApp.getApplicationDetailsTillIdentifierType(1));

		MainService mainServiceFun = (MainService) context.getBean("mainService");
		logger.info("getFunctionalDetailsById : " + mainServiceFun.getFunctionalDetailsById(1));
		logger.info("getFunctionalDetailsTillFeature : " + mainServiceFun.getFunctionalDetailsTillFeature(1));
		logger.info("getFunctionalsByAppId : " + mainServiceFun.getFunctionalsByAppId(1));

		MainService mainServiceAct = (MainService) context.getBean("mainService");
		logger.info("getActionsById : " + mainServiceAct.getActionsById(1));

		MainService mainServiceAge = (MainService) context.getBean("mainService");
		logger.info("getAgentDetailsById : " + mainServiceAge.getAgentDetailsById(3));
		logger.info("getAgentDetailsByTestPlanId : " + mainServiceAge.getAgentDetailsByTestPlanId(3));

		MainService mainServiceCgp = (MainService) context.getBean("mainService");
		logger.info("getConditionGroupById : " + mainServiceCgp.getConditionGroupById(1));
		logger.info("getConditionGroupsByAppId : " + mainServiceCgp.getConditionGroupsByAppId(1));

		MainService mainServiceCon = (MainService) context.getBean("mainService");
		logger.info("getConditionsByConditionGroupId : " + mainServiceCon.getConditionsByConditionGroupId(1));
		logger.info("getConditionsById : " + mainServiceCon.getConditionsById(1));

		MainService mainServiceFea = (MainService) context.getBean("mainService");
		logger.info("getFeatureByFunctionalId : " + mainServiceFea.getFeatureByFunctionalId(1));
		logger.info("getFeatureDetailsById : " + mainServiceFea.getFeatureDetailsById(14));

		MainService mainServiceRun = (MainService) context.getBean("mainService");
		logger.info("getRunnerById : " + mainServiceRun.getRunnerById(1));

		MainService mainServiceSrn = (MainService) context.getBean("mainService");
		logger.info("getScreenById : " + mainServiceSrn.getScreenById(3));
		logger.info("getScreensByAppId : " + mainServiceSrn.getScreensByAppId(2));

		MainService mainServiceSSM = (MainService) context.getBean("mainService");
		logger.info("getSuiteScenarioMappingById : " + mainServiceSSM.getSuiteScenarioMappingById(3));
		logger.info("getSuiteScenarioMappingBytestScenarioId : " + mainServiceSSM.getSuiteScenarioMappingBytestScenarioId(4));
		logger.info("getSuiteScenarioMappingBytestSuiteId : " + mainServiceSSM.getSuiteScenarioMappingBytestSuiteId(2));

		MainService mainServiceTSM = (MainService) context.getBean("mainService");
		logger.info("getTestCaseWithSuiteScenarioMappingById : " + mainServiceTSM.getTestCaseWithSuiteScenarioMappingById(1));

		MainService mainServiceTTM = (MainService) context.getBean("mainService");
		logger.info("getTestplanTestscenarioMapById : " + mainServiceTTM.getTestplanTestscenarioMapById(1));
		logger.info("getTestplanTestscenarioMapByPlanId : " + mainServiceTTM.getTestplanTestscenarioMapByPlanId(1));
		logger.info("getTestplanTestscenarioMapbyScenarioId : " + mainServiceTTM.getTestplanTestscenarioMapbyScenarioId(1));

		MainService mainServiceTsu = (MainService) context.getBean("mainService");
		logger.info("getTestSuiteDetailsByFeatureId : " + mainServiceTsu.getTestSuiteDetailsByFeatureId(1));
		logger.info("getTestSuiteDetailsByFunctionalId : " + mainServiceTsu.getTestSuiteDetailsByFunctionalId(1));
		logger.info("getTestSuiteDetailsById : " + mainServiceTsu.getTestSuiteDetailsById(1));
		logger.info("getTestSuiteWithTestCaseById : " + mainServiceTsu.getTestSuiteWithTestCaseById(1));

	}
}
