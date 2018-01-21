package org.s2n.ddt.test;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.service.MainService;

public class DaoTestWithSpring {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DaoTestWithSpring.class);
	static String user = "user.name";

	/**
	 * @param args
	 * @throws DataAccessException
	 */
	public static void main(String[] args) throws DataAccessException {

		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml", "dataAccessContext-local.xml" });

		MainService mainService = (MainService) context.getBean("mainService");

		Application application2 = new Application();
		application2.setAppName("test test3");
		application2.setDescription("test test");
		application2.setCreatedBy(System.getProperty(user));
		application2.setCreatedDateTime(new Date());
		application2.setUpdatedBy(System.getProperty(user));
		application2.setUpdatedDateTime(new Date());
		logger.info(mainService.insertApplicationDetails(application2));

		/*
		 * Actions actions = new Actions();
		 * actions.setActionname("GRX_Automation2");
		 * actions.setDescription("GRX_Automation");
		 * actions.setCreatedby(System.getProperty(user));
		 * actions.setCreateddatetime(new Date());
		 * actions.setUpdatedby(System.getProperty(user));
		 * actions.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertActionsDetails(actions));
		 */

		// Pojo needs to be created for AgentDetails
		// AgentDetails agentDetails = new AgentDetails();
		// agentDetails.setAgentid(new BigDecimal(613));
		// agentDetails.setAgentname("test563");
		// agentDetails.setIp("hsdjn");
		// agentDetails.setInstance("dfg");
		// agentDetails.setTestPlanId(new BigDecimal(856));
		// agentDetails.setCreatedby(System.getProperty(user));
		// agentDetails.setCreateddatetime(new Date());
		// agentDetails.setUpdatedby(System.getProperty(user));
		// agentDetails.setUpdateddatetime(new Date());
		// logger.info(mainService.insertAgentDetails(agentDetails));

		/*
		 * ConditionGroup conditionGroup = new ConditionGroup();
		 * conditionGroup.setCondgrpname("Condition6");
		 * conditionGroup.setDescription("Condition1 DescTest");
		 * conditionGroup.setAppId(new BigDecimal(654));
		 * conditionGroup.setCreatedby(System.getProperty(user));
		 * conditionGroup.setCreateddatetime(new Date());
		 * conditionGroup.setUpdatedby(System.getProperty(user));
		 * conditionGroup.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertConditionGroup(conditionGroup));
		 */

		/*
		 * Conditions conditions = new Conditions();
		 * conditions.setCondname("Condition 1");
		 * conditions.setDescription("Condition1 Desc test");
		 * conditions.setExpression("TC1='P'");
		 * conditions.setCondtionGroupId(new BigDecimal(654));
		 * conditions.setCreatedby(System.getProperty(user));
		 * conditions.setCreateddatetime(new Date());
		 * conditions.setUpdatedby(System.getProperty(user));
		 * conditions.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertConditions(conditions));
		 */

		/*
		 * Feature feature = new Feature();
		 * feature.setFeaturename("Create Order1");
		 * feature.setDescription("Create Order Desc Test");
		 * feature.setFunctionalId(new BigDecimal(654));
		 * feature.setCreatedby(System.getProperty(user));
		 * feature.setCreateddatetime(new Date());
		 * feature.setUpdatedby(System.getProperty(user));
		 * feature.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertFeature(feature));
		 */

		/*
		 * Functional functional = new Functional();
		 * functional.setFunctionalname("Order123");
		 * functional.setDescription("Order Desc1234"); functional.setAppId(new
		 * BigDecimal(654)); functional.setCreatedby(System.getProperty(user));
		 * functional.setCreateddatetime(new Date());
		 * functional.setUpdatedby(System.getProperty(user));
		 * functional.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertFunctionalDetails(functional));
		 */

		/*
		 * Screen screen = new Screen(); screen.setScreenname("OrderName1234");
		 * screen.setDescription("Order Screen by name test");
		 * screen.setAppId(new BigDecimal(654));
		 * screen.setCreatedby(System.getProperty(user));
		 * screen.setCreateddatetime(new Date());
		 * screen.setUpdatedby(System.getProperty(user));
		 * screen.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertScreenDetails(screen));
		 */

		/*
		 * SuiteScenarioMapping suiteScenarioMapping = new
		 * SuiteScenarioMapping(); suiteScenarioMapping.setTestSuiteId(new
		 * BigDecimal(8)); suiteScenarioMapping.setTestScenarioId(new
		 * BigDecimal(8));
		 * suiteScenarioMapping.setCreatedby(System.getProperty(user));
		 * suiteScenarioMapping.setCreateddatetime(new Date());
		 * suiteScenarioMapping.setUpdatedby(System.getProperty(user));
		 * suiteScenarioMapping.setUpdateddatetime(new Date());
		 * logger.info(mainService
		 * .insertSuiteScenarioMappingDetails(suiteScenarioMapping));
		 */

		/*
		 * Runner runner = new Runner(); runner.setRunnername("ABCtest");
		 * runner.setDescription("Test123243");
		 * runner.setCreatedby(System.getProperty(user));
		 * runner.setCreateddatetime(new Date());
		 * runner.setUpdatedby(System.getProperty(user));
		 * runner.setUpdateddatetime(new Date());
		 * logger.info(mainService.insertRunnerDetails(runner));
		 */

		/*
		 * TestplanTestscenarioMap testplanTestscenarioMap = new
		 * TestplanTestscenarioMap(); testplanTestscenarioMap.setTestPlanId(new
		 * BigDecimal(72)); testplanTestscenarioMap.setTestScenarioId(new
		 * BigDecimal(1213));
		 * testplanTestscenarioMap.setCreatedby(System.getProperty(user));
		 * testplanTestscenarioMap.setCreateddatetime(new Date());
		 * testplanTestscenarioMap.setUpdatedby(System.getProperty(user));
		 * testplanTestscenarioMap.setUpdateddatetime(new Date());
		 * logger.info(mainService
		 * .insertTestplanTestscenarioDetails(testplanTestscenarioMap));
		 */

		/*
		 * TestSuiteId testSuiteId = new TestSuiteId();
		 * testSuiteId.setSuitename("Testsuitedeb3");
		 * testSuiteId.setDescription("test56232");
		 * testSuiteId.setApplicationId(new BigDecimal(91));
		 * testSuiteId.setFeatureId(new BigDecimal(72));
		 * testSuiteId.setFunctional(new BigDecimal(91));
		 * testSuiteId.setOrderby((long)62);
		 * testSuiteId.setCreatedby(System.getProperty(user));
		 * testSuiteId.setCreateddatetime(new Date());
		 * testSuiteId.setUpdatedby(System.getProperty(user));
		 * testSuiteId.setUpdateddatetime(new Date()); TestSuite testSuite = new
		 * TestSuite(); testSuite.setTestsuiteid(testSuiteId);
		 * logger.info(mainService.insertTestSuiteDetails(testSuite));
		 */

		// InputService inputService = (InputService)
		// context.getBean("inputService");
		// IdentifierType identifier= new IdentifierType();
		// identifier.setAppId(new BigDecimal(54));
		// identifier.setIdentifiertypeid(new BigDecimal(564));
		// identifier.setIndentifiertypename("test APP");
		// identifier.setDescription("hjdsc");
		// identifier.setCreatedby(System.getProperty(user));
		// identifier.setCreateddatetime(new Date());
		// identifier.setUpdatedby(System.getProperty(user));
		// identifier.setUpdateddatetime(new Date());
		// logger.info(inputService.insertIdentifierTypeDetails(identifier));

		// ObjectGroup object = new ObjectGroup();
		// object.setObjectgroupid(new BigDecimal(1000));
		// object.setObjectgroupname("aDffdgfgh");
		// object.setDescription("dhjkfgdlgjkjkn");
		// object.setAppId(new BigDecimal(2742));
		// object.setScreenId(new BigDecimal(72));
		// object.setCreatedby(System.getProperty(user));
		// object.setCreateddatetime(new Date());
		// object.setUpdatedby(System.getProperty(user));
		// object.setUpdateddatetime(new Date());

		// ObjectsId object = new ObjectsId();
		// object.setObjectid(new BigDecimal(970));
		// object.setObjname("test");
		// object.setObjectgroupid(new BigDecimal(8367489));
		// object.setObjecttypeid(new BigDecimal(6734));
		// object.setDescription("New Description");
		// object.setIdentifiertypeid(new BigDecimal(345366));
		// object.setIndentifier("hjdgfsdfgfhhg");
		// object.setAppid(new BigDecimal(7898));
		// object.setCreatedby(System.getProperty(user));
		// object.setCreateddatetime(new Date());
		// object.setUpdatedby(System.getProperty(user));
		// object.setUpdateddatetime(new Date());
		//
		//
		// Objects objects = new Objects();
		// objects.setObjectsId(object);
		//
		// logger.info(inputService.insertObjectsDetails(objects));

		// ObjectTypeId objectTypeId = new ObjectTypeId();
		// objectTypeId.setObjecttypeid(new BigDecimal(789));
		// objectTypeId.setObjecttypename("gjksdf");
		// objectTypeId.setDescription("New Description by xyz");
		// objectTypeId.setAppId(new BigDecimal(78935));
		// objectTypeId.setDefaultActionId(new BigDecimal(789345));
		// objectTypeId.setCreatedby(System.getProperty(user));
		// objectTypeId.setCreateddatetime(new Date());
		// objectTypeId.setUpdatedby(System.getProperty(user));
		// objectTypeId.setUpdateddatetime(new Date());
		//
		// ObjectType objectType = new ObjectType();
		// objectType.setObjecttypeid(objectTypeId);
		//
		// logger.info(inputService.insertObjectsTypeDetails(objectType));

		// ParamGroupId paramGroupId = new ParamGroupId();
		// paramGroupId.setParamgroupid(new BigDecimal(789));
		// paramGroupId.setParamgroupname("hjkdsg");
		// paramGroupId.setDescription("Description");
		// paramGroupId.setTag("no");
		// paramGroupId.setApplicationId(new BigDecimal(7897));
		// paramGroupId.setTestScenarioId(new BigDecimal(789));
		// paramGroupId.setTestCaseId(new BigDecimal(897345));
		// paramGroupId.setTestStepId(new BigDecimal(89234));
		// paramGroupId.setCreatedby(System.getProperty(user));
		// paramGroupId.setCreateddatetime(new Date());
		// paramGroupId.setUpdatedby(System.getProperty(user));
		// paramGroupId.setUpdateddatetime(new Date());
		//
		// ParamGroup paramGroup = new ParamGroup();
		// paramGroup.setParamgroupid(paramGroupId);
		//
		// logger.info(inputService.insertParamGroupDetails(paramGroup));

		// TestCondDataId testCondDataId = new TestCondDataId();
		// testCondDataId.setTestconddataid(new BigDecimal(566));
		// testCondDataId.setTestplanid(new BigDecimal(1422));
		// testCondDataId.setConditionid(new BigDecimal(122));
		// testCondDataId.setParamid(new BigDecimal(123));
		// testCondDataId.setParamvalue("Test Update");
		// testCondDataId.setCreatedby(System.getProperty(user));
		// testCondDataId.setCreateddatetime(new Date());
		// testCondDataId.setUpdatedby(System.getProperty(user));
		// testCondDataId.setUpdateddatetime(new Date());
		//
		// TestCondData testCondData = new TestCondData();
		// testCondData.setId(testCondDataId);
		//
		// logger.info(inputService.insertTestCondDataDetails(testCondData));

		// TestParamDataId testParamDataId = new TestParamDataId();
		// testParamDataId.setTestparamdataid(new BigDecimal(400));
		// testParamDataId.setAppId(new BigDecimal(89));
		// testParamDataId.setTeststepid(new BigDecimal(83));
		// testParamDataId.setTestSet("8");
		// testParamDataId.setTestcaseid(new BigDecimal(78));
		// testParamDataId.setParamid(new BigDecimal(78));
		// testParamDataId.setParamvalue("jkdsfh");
		// testParamDataId.setCreatedby(System.getProperty(user));
		// testParamDataId.setCreateddatetime(new Date());
		// testParamDataId.setUpdatedby(System.getProperty(user));
		// testParamDataId.setUpdateddatetime(new Date());
		//
		// TestParamData testParamData = new TestParamData();
		// testParamData.setId(testParamDataId);
		//
		// logger.info(inputService.insertTestParamDataDetails(testParamData));

		// TestStepId testStepId = new TestStepId();
		// testStepId.setTeststepid(new BigDecimal(800));
		// testStepId.setStepname("test");
		// testStepId.setDescription("hjd");
		// testStepId.setTeststeptype("jksfhs");
		// testStepId.setActionsId(new BigDecimal(67));
		// testStepId.setActive((int)7);
		// testStepId.setOrderby((long)8);
		// testStepId.setConditiongroupByPostconditionId(new BigDecimal(7));
		// testStepId.setConditiongroupByPreconditionId(new BigDecimal(8));
		// testStepId.setParamgroupByInputparam(new BigDecimal(6));
		// testStepId.setParamgroupByOutputparam(new BigDecimal(4));
		// testStepId.setTestCase(new BigDecimal(9));
		// testStepId.setRunnerId(new BigDecimal(8));
		// testStepId.setExpectedResult("pass");
		// testStepId.setCreatedby(System.getProperty(user));
		// testStepId.setCreateddatetime(new Date());
		// testStepId.setUpdatedby(System.getProperty(user));
		// testStepId.setUpdateddatetime(new Date());
		//
		// TestStep testStep = new TestStep();
		// testStep.setTeststepid(testStepId);
		//
		// logger.info(inputService.insertTestStepDetails(testStep));
		//

		//
		// Param param = new Param();
		// param.setParamid(new BigDecimal(328748));
		// param.setParamname("dhjfkhdl");
		// param.setDescription("new description by xyz");
		// param.setOrderby((long)8745);
		// param.setParamGroupId(new BigDecimal(9089));
		// param.setObjectid(new BigDecimal(7486));
		// param.setCreatedby(System.getProperty(user));
		// param.setCreateddatetime(new Date());
		// param.setUpdatedby(System.getProperty(user));
		// param.setUpdateddatetime(new Date());

		// TestCase testCase = new TestCase();
		// testCase.setTestcaseid(new BigDecimal(2634789));
		// testCase.setClassificationtag("shjkfhds");
		// testCase.setCasename("test");
		// testCase.setRunnerId(new BigDecimal(89));
		// testCase.setDescription("description");
		// testCase.setActive(1);
		// testCase.setPositive(1);
		// testCase.setOrderby((long)678);
		// testCase.setTestSuiteId(new BigDecimal(78394));
		// testCase.setCreatedby(System.getProperty(user));
		// testCase.setCreateddatetime(new Date());
		// testCase.setUpdatedby(System.getProperty(user));
		// testCase.setUpdateddatetime(new Date());
		// logger.info(inputService.insertTestCaseDetails(testCase));
	}

}
