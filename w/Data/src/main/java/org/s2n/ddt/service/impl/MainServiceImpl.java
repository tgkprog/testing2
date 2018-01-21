package org.s2n.ddt.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.s2n.ddt.dao.ActionsDao;
import org.s2n.ddt.dao.AgentDetailsDao;
import org.s2n.ddt.dao.ApplicationDao;
import org.s2n.ddt.dao.ConditionGroupDao;
import org.s2n.ddt.dao.ConditionsDao;
import org.s2n.ddt.dao.FeatureDao;
import org.s2n.ddt.dao.FunctionalDao;
import org.s2n.ddt.dao.RunnerDao;
import org.s2n.ddt.dao.ScreenDao;
import org.s2n.ddt.dao.SuiteScenarioMappingDao;
import org.s2n.ddt.dao.TestPlanScenarioMapDao;
import org.s2n.ddt.dao.TestSuiteDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.ConditionGroup;
import org.s2n.ddt.pojo.Conditions;
import org.s2n.ddt.pojo.Feature;
import org.s2n.ddt.pojo.Functional;
import org.s2n.ddt.pojo.Runner;
import org.s2n.ddt.pojo.Screen;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.output.AgentDetails;
import org.s2n.ddt.service.MainService;

/**
 * Data Module primary business object.
 * 
 * <p>
 * This object makes use of DAO objects, decoupling it from the details of
 * working with persistence APIs. So although this application uses Spring Data
 * for data access, a different persistence tool could be dropped in without
 * breaking this class.
 * 
 * <p>
 * The DAOs are made available to the instance of this object using Dependency
 * Injection. (The DAOs are in turn configured using Dependency Injection
 * themselves.) We use Setter Injection here, exposing JavaBean setter methods
 * for each DAO. This means there is a JavaBean property for each DAO. In the
 * present case, the properties are write-only: there are no corresponding
 * getter methods. Getter methods for configuration properties are optional:
 * Implement them only if you want to expose those properties to other business
 * objects.
 * 
 * <p>
 * There is one instance of this class in the Data module application. In Spring
 * terminology, it is a "singleton", referring to a per-Application Context
 * singleton. The factory creates a single instance; there is no need for a
 * private constructor, static factory method etc as in the traditional
 * implementation of the Singleton Design Pattern.
 * 
 * <p>
 * This is a POJO. It does not depend on any Spring APIs. It's usable outside a
 * Spring container, and can be instantiated using new in a JUnit test. However,
 * we can still apply declarative transaction management to it using Spring AOP.
 * 
 * <p>
 * This class defines a default transaction annotation for all methods.
 * 
 */
@Transactional
public class MainServiceImpl implements MainService {

	private ActionsDao actionsDao;

	private AgentDetailsDao agentDetailsDao;

	private ApplicationDao applicationDao;

	private ConditionGroupDao conditionGroupDao;

	private ConditionsDao conditionsDao;

	private FeatureDao featureDao;

	private FunctionalDao functionalDao;

	private RunnerDao runnerDao;

	private ScreenDao screenDao;

	private SuiteScenarioMappingDao suiteScenarioMappingDao;

	private TestPlanScenarioMapDao testPlanScenarioMapDao;

	private TestSuiteDao testSuiteDao;

	// -------------------------------------------------------------------------
	// Setter methods for dependency injection
	// -------------------------------------------------------------------------

	public void setActionsDao(ActionsDao actionsDao) {
		this.actionsDao = actionsDao;
	}

	public void setAgentDetailsDao(AgentDetailsDao agentDetailsDao) {
		this.agentDetailsDao = agentDetailsDao;
	}

	public void setApplicationDao(ApplicationDao applicationDao) {
		this.applicationDao = applicationDao;
	}

	public void setConditionGroupDao(ConditionGroupDao conditionGroupDao) {
		this.conditionGroupDao = conditionGroupDao;
	}

	public void setConditionsDao(ConditionsDao conditionsDao) {
		this.conditionsDao = conditionsDao;
	}

	public void setFeatureDao(FeatureDao featureDao) {
		this.featureDao = featureDao;
	}

	public void setFunctionalDao(FunctionalDao functionalDao) {
		this.functionalDao = functionalDao;
	}

	public void setRunnerDao(RunnerDao runnerDao) {
		this.runnerDao = runnerDao;
	}

	public void setScreenDao(ScreenDao screenDao) {
		this.screenDao = screenDao;
	}

	public void setSuiteScenarioMappingDao(SuiteScenarioMappingDao suiteScenarioMappingDao) {
		this.suiteScenarioMappingDao = suiteScenarioMappingDao;
	}

	public void setTestPlanScenarioMapDao(TestPlanScenarioMapDao testPlanScenarioMapDao) {
		this.testPlanScenarioMapDao = testPlanScenarioMapDao;
	}

	public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
		this.testSuiteDao = testSuiteDao;
	}

	// -------------------------------------------------------------------------
	// Operation methods, implementing the DataModuleService interface
	// -------------------------------------------------------------------------

	public Actions getActionsById(int actionId) throws DataAccessException {
		return this.actionsDao.getActionsById(actionId);
	}

	public AgentDetails getAgentDetailsById(int agentId) throws DataAccessException {
		return this.agentDetailsDao.getAgentDetailsById(agentId);
	}

	public List<AgentDetails> getAgentDetailsByTestPlanId(int testPlanId) throws DataAccessException {
		return this.agentDetailsDao.getAgentDetailsByTestPlanId(testPlanId);
	}

	public Application getApplicationDetailsById(int appId) throws DataAccessException {
		return this.applicationDao.getApplicationDetailsById(appId);
	}

	public Application getApplicationDetailsTillFunctional(int appId) throws DataAccessException {
		return this.applicationDao.getApplicationDetailsTillFunctional(appId);
	}

	public ConditionGroup getConditionGroupById(int condGrpId) throws DataAccessException {
		return this.conditionGroupDao.getConditionGroupById(condGrpId);
	}

	public List<ConditionGroup> getConditionGroupsByAppId(int appId) throws DataAccessException {
		return this.conditionGroupDao.getConditionGroupsByAppId(appId);
	}

	public Conditions getConditionsById(int conditionId) throws DataAccessException {
		return this.conditionsDao.getConditionsById(conditionId);
	}

	public Feature getFeatureDetailsById(int featureId) throws DataAccessException {
		return this.featureDao.getFeatureDetailsById(featureId);
	}

	public List<Feature> getFeatureByFunctionalId(int functionalId) throws DataAccessException {
		return this.featureDao.getFeatureByFunctionalId(functionalId);
	}

	public Functional getFunctionalDetailsById(int functionalId) throws DataAccessException {
		return this.functionalDao.getFunctionalDetailsById(functionalId);
	}

	public List<Functional> getFunctionalsByAppId(int appId) throws DataAccessException {
		return this.functionalDao.getFunctionalsByAppId(appId);
	}

	public Functional getFunctionalDetailsTillFeature(int functionalId) throws DataAccessException {
		return this.functionalDao.getFunctionalDetailsTillFeature(functionalId);
	}

	public Runner getRunnerById(int runnerId) throws DataAccessException {
		return this.runnerDao.getRunnerById(runnerId);
	}

	public Screen getScreenById(int screenId) throws DataAccessException {
		return this.screenDao.getScreenById(screenId);
	}

	public List<Screen> getScreensByAppId(int appID) throws DataAccessException {
		return this.screenDao.getScreensByAppId(appID);
	}

	public SuiteScenarioMapping getSuiteScenarioMappingById(int suiteScenarioId) throws DataAccessException {
		return this.suiteScenarioMappingDao.getSuiteScenarioMappingById(suiteScenarioId);
	}

	public TestplanTestscenarioMap getTestplanTestscenarioMapById(int testPlanScenarioId) throws DataAccessException {
		return this.testPlanScenarioMapDao.getTestplanTestscenarioMapById(testPlanScenarioId);
	}

	public List<TestplanTestscenarioMap> getTestplanTestscenarioMapByPlanId(int testPlanId) throws DataAccessException {
		return this.testPlanScenarioMapDao.getTestplanTestscenarioMapByPlanId(testPlanId);
	}

	public List<TestplanTestscenarioMap> getTestplanTestscenarioMapbyScenarioId(int testScenarioId) throws DataAccessException {
		return this.testPlanScenarioMapDao.getTestplanTestscenarioMapbyScenarioId(testScenarioId);
	}

	public TestSuite getTestSuiteDetailsById(int testSuiteId) throws DataAccessException {
		return this.testSuiteDao.getTestSuiteDetailsById(testSuiteId);
	}

	public TestSuite getTestSuiteWithTestCaseById(int testSuiteId) throws DataAccessException {
		return this.testSuiteDao.getTestSuiteWithTestCaseById(testSuiteId);
	}

	public TestSuite getTestCaseWithSuiteScenarioMappingById(int testSuiteId) throws DataAccessException {
		return this.testSuiteDao.getTestCaseWithSuiteScenarioMappingById(testSuiteId);
	}

	public List<TestSuite> getTestSuiteDetailsByFunctionalId(int functionalId) throws DataAccessException {
		return this.testSuiteDao.getTestSuiteDetailsByFunctionalId(functionalId);
	}

	public List<TestSuite> getTestSuiteDetailsByFeatureId(int featureId) throws DataAccessException {
		return this.testSuiteDao.getTestSuiteDetailsByFeatureId(featureId);
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappingBytestScenarioId(int testScenarioId) throws DataAccessException {
		return this.suiteScenarioMappingDao.getSuiteScenarioMappingBytestScenarioId(testScenarioId);
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappingBytestSuiteId(int testSuiteId) throws DataAccessException {
		return this.suiteScenarioMappingDao.getSuiteScenarioMappingBytestSuiteId(testSuiteId);
	}

	public Conditions getConditionsByConditionGroupId(int condGrpId) throws DataAccessException {
		return this.conditionsDao.getConditionsByConditionGroupId(condGrpId);
	}

	public Application getApplicationDetailsByAppName(String AppName) throws DataAccessException {
		return this.applicationDao.getApplicationDetailsByAppName(AppName);
	}

	@Override
	public Application getApplicationDetailsTillIdentifierType(int appId) throws DataAccessException {

		return this.applicationDao.getApplicationDetailsTillIdentifierType(appId);
	}

	@Override
	public List<TestSuite> getTestSuitesByAppId(int appId) throws DataAccessException {
		return this.testSuiteDao.getTestSuitesByAppId(appId);
	}

	@Override
	public int getMaxAppId() throws DataAccessException {
		return this.applicationDao.getMaxAppId();
	}

	@Override
	public int getAppIdByAppName(String AppName) throws DataAccessException {

		return this.applicationDao.getAppIdByAppName(AppName);
	}

	@Override
	public long updateApplication(Application application) throws DataAccessException {
		return this.applicationDao.updateApplication(application);
	}

	public long insertApplicationDetails(final Application application) throws DataAccessException {
		int id = this.applicationDao.getAppIdByAppName(application.getAppName());
		long result = 0L;
		if (id == 0) {
			result = this.applicationDao.insertApplicationDetails(application);
		} else {
			application.setAppId(new BigDecimal(id));
			result = this.applicationDao.updateApplication(application);
		}
		return result;
	}

	@Override
	public int getActionIdByActionName(String actionName) throws DataAccessException {

		return this.actionsDao.getActionIdByActionName(actionName);
	}

	@Override
	public long updateActions(Actions actions) throws DataAccessException {
		return this.actionsDao.updateActions(actions);
	}

	public long insertActionsDetails(final Actions actions) throws DataAccessException {
		int id = this.actionsDao.getActionIdByActionName(actions.getActionName());
		long result = 0L;
		if (id == 0) {
			result = this.actionsDao.insertActionsDetails(actions);
		} else {
			actions.setActionId(new BigDecimal(id));
			result = this.actionsDao.updateActions(actions);
		}
		return result;
	}

	@Override
	public List<String> getAllActionNames() throws DataAccessException {
		return this.actionsDao.getAllActionNames();
	}

	@Override
	public int getAgentIdByAgentDetails(AgentDetails agentDetails) throws DataAccessException {
		return this.agentDetailsDao.getAgentIdByAgentDetails(agentDetails);
	}

	@Override
	public long updateAgentDetails(AgentDetails agentDetails) throws DataAccessException {
		return this.agentDetailsDao.updateAgentDetails(agentDetails);
	}

	public long insertAgentDetails(final AgentDetails agentDetails) throws DataAccessException {
		int id = this.agentDetailsDao.getAgentIdByAgentDetails(agentDetails);
		long result = 0L;
		if (id == 0) {
			result = this.agentDetailsDao.insertAgentDetails(agentDetails);
		} else {
			result = this.agentDetailsDao.updateAgentDetails(agentDetails);
		}
		return result;
	}

	@Override
	public int getConditionGroupIdByName(ConditionGroup conditionGroup) throws DataAccessException {
		return this.conditionGroupDao.getConditionGroupIdByName(conditionGroup);
	}

	@Override
	public long updateConditionGroup(ConditionGroup conditionGroup) throws DataAccessException {
		return this.conditionGroupDao.updateConditionGroup(conditionGroup);
	}

	public long insertConditionGroup(final ConditionGroup conditionGroup) throws DataAccessException {
		int id = this.conditionGroupDao.getConditionGroupIdByName(conditionGroup);
		long result = 0L;
		if (id == 0) {
			result = this.conditionGroupDao.insertConditionGroup(conditionGroup);
		} else {
			conditionGroup.setConditionGroupId(new BigDecimal(id));
			result = this.conditionGroupDao.updateConditionGroup(conditionGroup);
		}
		return result;
	}

	@Override
	public int getConditionsIdByName(Conditions conditions) throws DataAccessException {
		return this.conditionsDao.getConditionsIdByName(conditions);
	}

	@Override
	public long updateConditions(final Conditions conditions) throws DataAccessException {
		return this.conditionsDao.updateConditions(conditions);
	}

	public long insertConditions(final Conditions conditions) throws DataAccessException {
		int id = this.conditionsDao.getConditionsIdByName(conditions);
		long result = 0L;
		if (id == 0) {
			result = this.conditionsDao.insertConditions(conditions);
		} else {
			conditions.setConditionId(new BigDecimal(id));
			result = this.conditionsDao.updateConditions(conditions);
		}
		return result;
	}

	@Override
	public int getFeatureIdByName(Feature feature) throws DataAccessException {
		return this.featureDao.getFeatureIdByName(feature);
	}

	@Override
	public long updateFeature(final Feature feature) throws DataAccessException {
		return this.featureDao.updateFeature(feature);
	}

	public long insertFeature(final Feature feature) throws DataAccessException {
		int id = this.featureDao.getFeatureIdByName(feature);
		long result = 0L;
		if (id == 0) {
			result = this.featureDao.insertFeature(feature);
		} else {
			feature.setFeatureId(new BigDecimal(id));
			result = this.featureDao.updateFeature(feature);
		}
		return result;
	}

	@Override
	public int getScreenIdByName(Screen screen) throws DataAccessException {
		return this.screenDao.getScreenIdByName(screen);
	}

	@Override
	public long updateScreen(final Screen screen) throws DataAccessException {
		return this.screenDao.updateScreen(screen);
	}

	public long insertScreenDetails(final Screen screen) throws DataAccessException {
		int id = this.screenDao.getScreenIdByName(screen);
		long result = 0L;
		if (id == 0) {
			result = this.screenDao.insertScreenDetails(screen);
		} else {
			screen.setScreenId(new BigDecimal(id));
			result = this.screenDao.updateScreen(screen);
		}
		return result;
	}

	@Override
	public int getFunctionalDetailsByName(int appId, String functionalName) throws DataAccessException {
		return this.functionalDao.getFunctionalDetailsByName(appId, functionalName);
	}

	@Override
	public long updateFunctionalDetails(final Functional functional) throws DataAccessException {
		return this.functionalDao.updateFunctionalDetails(functional);
	}

	public long insertFunctionalDetails(final Functional functional) throws DataAccessException {
		int id = this.functionalDao.getFunctionalDetailsByName(functional.getAppId().intValue(), functional.getFunctionalName());
		long result = 0L;
		if (id == 0) {
			result = this.functionalDao.insertFunctionalDetails(functional);
		} else {
			functional.setFunctionalId(new BigDecimal(id));
			result = this.functionalDao.updateFunctionalDetails(functional);
		}
		return result;
	}

	@Override
	public long updateTestSuite(TestSuite suite) throws DataAccessException {
		return this.testSuiteDao.updateTestSuite(suite);
	}

	@Override
	public int getTestSuiteDetailsByName(TestSuite suite) throws DataAccessException {
		return this.testSuiteDao.getTestSuiteDetailsByName(suite);
	}

	public long insertTestSuiteDetails(final TestSuite suite) throws DataAccessException {
		int id = this.getTestSuiteDetailsByName(suite);
		long result = 0L;
		if (id == 0) {
			result = this.testSuiteDao.insertTestSuiteDetails(suite);
		} else {
			TestSuiteId testSuiteId = suite.getTestSuiteId();
			testSuiteId.setTestSuiteId(new BigDecimal(id));
			suite.setTestSuiteId(testSuiteId);
			result = this.testSuiteDao.updateTestSuite(suite);
		}
		return result;
	}

	@Override
	public long updateTestplanTestscenario(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		return this.testPlanScenarioMapDao.updateTestplanTestscenario(testplanTestscenarioMap);
	}

	@Override
	public int getTestplanTestscenarioByTestScenarioId(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		return this.testPlanScenarioMapDao.getTestplanTestscenarioByTestScenarioId(testplanTestscenarioMap);
	}

	public long insertTestplanTestscenarioDetails(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		int id = this.getTestplanTestscenarioByTestScenarioId(testplanTestscenarioMap);
		long result = 0L;
		if (id == 0) {
			result = this.testPlanScenarioMapDao.insertTestplanTestscenarioDetails(testplanTestscenarioMap);
		} else {
			testplanTestscenarioMap.setPlanScenarioId(new BigDecimal(id));
			result = this.testPlanScenarioMapDao.updateTestplanTestscenario(testplanTestscenarioMap);
		}
		return result;
	}

	@Override
	public long updateRunner(Runner runner) throws DataAccessException {
		return this.runnerDao.updateRunner(runner);
	}

	@Override
	public int getRunnerIdByName(Runner runner) throws DataAccessException {
		return this.runnerDao.getRunnerIdByName(runner);
	}

	public long insertRunnerDetails(final Runner runner) throws DataAccessException {
		int id = this.getRunnerIdByName(runner);
		long result = 0L;
		if (id == 0) {
			result = this.runnerDao.insertRunnerDetails(runner);
		} else {
			runner.setRunnerId(new BigDecimal(id));
			result = this.runnerDao.updateRunner(runner);
		}
		return result;
	}

	@Override
	public int getSuiteScenarioId(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		return this.suiteScenarioMappingDao.getSuiteScenarioId(suiteScenarioMapping);
	}

	@Override
	public long updateSuiteScenarioMapping(final SuiteScenarioMapping scenarioMapping) throws DataAccessException {
		return this.suiteScenarioMappingDao.updateSuiteScenarioMapping(scenarioMapping);
	}

	public long insertSuiteScenarioMappingDetails(final SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		int id = this.getSuiteScenarioId(suiteScenarioMapping);
		long result = 0L;
		if (id == 0) {
			result = this.suiteScenarioMappingDao.insertSuiteScenarioMappingDetails(suiteScenarioMapping);
		} else {
			suiteScenarioMapping.setSuiteScenarioId(new BigDecimal(id));
			result = this.suiteScenarioMappingDao.updateSuiteScenarioMapping(suiteScenarioMapping);
		}
		return result;
	}

	@Override
	public int insertApplicationGetKey(Application application) throws DataAccessException {
		return this.applicationDao.insertApplicationGetKey(application);
	}

	@Override
	public int insertActionsGetKey(Actions actions) throws DataAccessException {
		return this.actionsDao.insertActionsGetKey(actions);
	}

	@Override
	public int insertAgentDetailsGetKey(AgentDetails agentDetails) throws DataAccessException {
		return this.agentDetailsDao.insertAgentDetailsGetKey(agentDetails);
	}

	@Override
	public int insertConditionGroupGetKey(ConditionGroup conditionGroup) throws DataAccessException {
		return this.conditionGroupDao.insertConditionGroupGetKey(conditionGroup);
	}

	@Override
	public int insertConditionsGetKey(Conditions conditions) throws DataAccessException {
		return this.conditionsDao.insertConditionsGetKey(conditions);
	}

	@Override
	public int insertFeatureGetKey(Feature feature) throws DataAccessException {
		return this.featureDao.insertFeatureGetKey(feature);
	}

	@Override
	public int insertFunctionalGetKey(Functional functional) throws DataAccessException {
		return this.functionalDao.insertFunctionalGetKey(functional);
	}

	@Override
	public int insertRunnerGetKey(Runner runner) throws DataAccessException {
		return this.runnerDao.insertRunnerGetKey(runner);
	}

	@Override
	public int insertScreenGetKey(Screen screen) throws DataAccessException {
		return this.screenDao.insertScreenGetKey(screen);
	}

	@Override
	public int insertSuiteScenarioMappingGetKey(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		return this.suiteScenarioMappingDao.insertSuiteScenarioMappingGetKey(suiteScenarioMapping);
	}

	@Override
	public int insertTestplanTestscenarioMapGetKey(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		return this.testPlanScenarioMapDao.insertTestplanTestscenarioMapGetKey(testplanTestscenarioMap);
	}

	@Override
	public int insertTestSuiteGetKey(TestSuite testSuite) throws DataAccessException {
		return this.testSuiteDao.insertTestSuiteGetKey(testSuite);
	}

}
