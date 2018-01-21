package org.s2n.ddt.service;

import java.util.List;

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
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import org.s2n.ddt.pojo.output.AgentDetails;

public interface MainService {

	Actions getActionsById(int actionId) throws DataAccessException;

	AgentDetails getAgentDetailsById(int agentId) throws DataAccessException;

	List<AgentDetails> getAgentDetailsByTestPlanId(int testPlanId) throws DataAccessException;

	Application getApplicationDetailsById(int appId) throws DataAccessException;

	Application getApplicationDetailsTillFunctional(int appId) throws DataAccessException;

	Application getApplicationDetailsTillIdentifierType(int appId) throws DataAccessException;

	ConditionGroup getConditionGroupById(int condGrpId) throws DataAccessException;

	List<ConditionGroup> getConditionGroupsByAppId(int appId) throws DataAccessException;

	Conditions getConditionsById(int conditionId) throws DataAccessException;

	Feature getFeatureDetailsById(int featureId) throws DataAccessException;

	List<Feature> getFeatureByFunctionalId(int functionalId) throws DataAccessException;

	Functional getFunctionalDetailsById(int functionalId) throws DataAccessException;

	List<Functional> getFunctionalsByAppId(int appId) throws DataAccessException;

	Functional getFunctionalDetailsTillFeature(int functionalId) throws DataAccessException;

	Runner getRunnerById(int runnerId) throws DataAccessException;

	Screen getScreenById(int screenId) throws DataAccessException;

	List<Screen> getScreensByAppId(int appID) throws DataAccessException;

	SuiteScenarioMapping getSuiteScenarioMappingById(int suiteScenarioId) throws DataAccessException;

	TestplanTestscenarioMap getTestplanTestscenarioMapById(int testPlanScenarioId) throws DataAccessException;

	List<TestplanTestscenarioMap> getTestplanTestscenarioMapByPlanId(int testPlanId) throws DataAccessException;

	List<TestplanTestscenarioMap> getTestplanTestscenarioMapbyScenarioId(int testScenarioId) throws DataAccessException;

	TestSuite getTestSuiteDetailsById(int testSuiteId) throws DataAccessException;

	TestSuite getTestSuiteWithTestCaseById(int testSuiteId) throws DataAccessException;

	TestSuite getTestCaseWithSuiteScenarioMappingById(int testSuiteId) throws DataAccessException;

	List<SuiteScenarioMapping> getSuiteScenarioMappingBytestScenarioId(int testScenarioId) throws DataAccessException;

	List<TestSuite> getTestSuiteDetailsByFunctionalId(int functionalId) throws DataAccessException;

	List<TestSuite> getTestSuiteDetailsByFeatureId(int featureId) throws DataAccessException;

	List<SuiteScenarioMapping> getSuiteScenarioMappingBytestSuiteId(int testSuiteId) throws DataAccessException;

	Conditions getConditionsByConditionGroupId(int condGrpId) throws DataAccessException;

	Application getApplicationDetailsByAppName(String AppName) throws DataAccessException;

	List<TestSuite> getTestSuitesByAppId(int appId) throws DataAccessException;

	int getMaxAppId() throws DataAccessException;

	long insertRunnerDetails(final Runner runner) throws DataAccessException;

	long insertTestplanTestscenarioDetails(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	long insertTestSuiteDetails(final TestSuite suite) throws DataAccessException;

	int getAppIdByAppName(String AppName) throws DataAccessException;

	long insertApplicationDetails(final Application application) throws DataAccessException;

	long updateApplication(final Application application) throws DataAccessException;

	int getActionIdByActionName(String actionName) throws DataAccessException;

	long insertActionsDetails(final Actions actions) throws DataAccessException;

	List<String> getAllActionNames() throws DataAccessException;

	long updateActions(final Actions actions) throws DataAccessException;

	int getAgentIdByAgentDetails(AgentDetails agentDetails) throws DataAccessException;

	long insertAgentDetails(final AgentDetails agentDetails) throws DataAccessException;

	long updateAgentDetails(final AgentDetails agentDetails) throws DataAccessException;

	int getConditionGroupIdByName(ConditionGroup conditionGroup) throws DataAccessException;

	long insertConditionGroup(final ConditionGroup conditionGroup) throws DataAccessException;

	long updateConditionGroup(final ConditionGroup conditionGroup) throws DataAccessException;

	int getConditionsIdByName(Conditions conditions) throws DataAccessException;

	long insertConditions(final Conditions conditions) throws DataAccessException;

	long updateConditions(final Conditions conditions) throws DataAccessException;

	int getFeatureIdByName(Feature feature) throws DataAccessException;

	long insertFeature(final Feature feature) throws DataAccessException;

	long updateFeature(final Feature feature) throws DataAccessException;

	int getFunctionalDetailsByName(int appId, String functionalName) throws DataAccessException;

	long insertFunctionalDetails(final Functional functional) throws DataAccessException;

	long updateFunctionalDetails(final Functional functional) throws DataAccessException;

	int getScreenIdByName(Screen screen) throws DataAccessException;

	long insertScreenDetails(final Screen screen) throws DataAccessException;

	long updateScreen(final Screen screen) throws DataAccessException;

	long updateTestSuite(final TestSuite suite) throws DataAccessException;

	int getTestSuiteDetailsByName(TestSuite suite) throws DataAccessException;

	long updateTestplanTestscenario(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	int getTestplanTestscenarioByTestScenarioId(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	long updateRunner(final Runner runner) throws DataAccessException;

	int getRunnerIdByName(Runner runner) throws DataAccessException;

	int getSuiteScenarioId(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;

	long insertSuiteScenarioMappingDetails(final SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;

	long updateSuiteScenarioMapping(final SuiteScenarioMapping scenarioMapping) throws DataAccessException;

	int insertApplicationGetKey(final Application application) throws DataAccessException;

	int insertActionsGetKey(Actions actions) throws DataAccessException;

	int insertAgentDetailsGetKey(AgentDetails agentDetails) throws DataAccessException;

	int insertConditionGroupGetKey(ConditionGroup conditionGroup) throws DataAccessException;

	int insertConditionsGetKey(Conditions conditions) throws DataAccessException;

	int insertFeatureGetKey(Feature feature) throws DataAccessException;

	int insertFunctionalGetKey(Functional functional) throws DataAccessException;

	int insertRunnerGetKey(Runner runner) throws DataAccessException;

	int insertScreenGetKey(Screen screen) throws DataAccessException;

	int insertSuiteScenarioMappingGetKey(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;

	int insertTestplanTestscenarioMapGetKey(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	int insertTestSuiteGetKey(TestSuite testSuite) throws DataAccessException;
}
