package org.s2n.ddt.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.s2n.ddt.dao.output.TestPlanDao;
import org.s2n.ddt.dao.output.TestReportDao;
import org.s2n.ddt.dao.output.TestRunDataDao;
import org.s2n.ddt.dao.output.TestRunDetailsDao;
import org.s2n.ddt.dao.output.TestScenarioDao;
import org.s2n.ddt.dao.output.TestScenarioParamDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestReport;
import org.s2n.ddt.pojo.output.TestRunData;
import org.s2n.ddt.pojo.output.TestRunDetails;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestscenarioParamdataMap;
import org.s2n.ddt.service.OutputService;

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
public class OutputServiceImpl implements OutputService {

	private TestPlanDao testPlanDao;

	private TestReportDao testReportDao;

	private TestRunDataDao testRunDataDao;

	private TestRunDetailsDao testRunDetailsDao;

	private TestScenarioDao testScenarioDao;

	private TestScenarioParamDataDao testScenarioParamDataDao;

	// -------------------------------------------------------------------------
	// Setter methods for dependency injection
	// -------------------------------------------------------------------------

	public void setTestPlanDao(TestPlanDao testPlanDao) {
		this.testPlanDao = testPlanDao;
	}

	public void setTestReportDao(TestReportDao testReportDao) {
		this.testReportDao = testReportDao;
	}

	public void setTestRunDataDao(TestRunDataDao testRunDataDao) {
		this.testRunDataDao = testRunDataDao;
	}

	public void setTestRunDetailsDao(TestRunDetailsDao testRunDetailsDao) {
		this.testRunDetailsDao = testRunDetailsDao;
	}

	public void setTestScenarioDao(TestScenarioDao testScenarioDao) {
		this.testScenarioDao = testScenarioDao;
	}

	public void setTestScenarioParamDataDao(TestScenarioParamDataDao testScenarioParamDataDao) {
		this.testScenarioParamDataDao = testScenarioParamDataDao;
	}

	// -------------------------------------------------------------------------
	// Operation methods, implementing the DataModuleService interface
	// -------------------------------------------------------------------------

	public TestPlan getTestPlanDetailsById(int testPlanId) throws DataAccessException {
		return this.testPlanDao.getTestPlanDetailsById(testPlanId);
	}

	public TestReport getTestReportById(int testReportId) throws DataAccessException {
		return this.testReportDao.getTestReportById(testReportId);
	}

	public TestReport getTestReportByRunId(int testRunID) throws DataAccessException {
		return this.testReportDao.getTestReportByRunId(testRunID);
	}

	public TestRunData getTestRunDataById(int testRunDataId) throws DataAccessException {
		return this.testRunDataDao.getTestRunDataById(testRunDataId);
	}

	public List<TestRunData> getTestRunDataByTestCaseId(int testCaseId) throws DataAccessException {
		return this.testRunDataDao.getTestRunDataByTestCaseId(testCaseId);
	}

	public List<TestRunData> getTestRunDataByPlanId(int testPlanId) throws DataAccessException {
		return this.testRunDataDao.getTestRunDataByPlanId(testPlanId);
	}

	public TestRunDetails getTestRunDetailsById(int testRunId) throws DataAccessException {
		return this.testRunDetailsDao.getTestRunDetailsById(testRunId);
	}

	public List<TestRunDetails> getTestRunDetailsByPlanId(int testPlanId) throws DataAccessException {
		return this.testRunDetailsDao.getTestRunDetailsByPlanId(testPlanId);
	}

	public TestScenario getTestScenarioDetailsById(int testScenarioId) throws DataAccessException {
		return this.testScenarioDao.getTestScenarioDetailsById(testScenarioId);
	}

	public TestscenarioParamdataMap getTestScenarioParamDataById(int testScenarioId) throws DataAccessException {
		return this.testScenarioParamDataDao.getTestScenarioParamDataById(testScenarioId);
	}

	public TestscenarioParamdataMap getTestscenarioParamdataMapbyScenarioId(int testScenarioId) throws DataAccessException {
		return this.testScenarioParamDataDao.getTestscenarioParamdataMapbyScenarioId(testScenarioId);
	}

	public TestscenarioParamdataMap getTestscenarioParamdataMapbyParamDataId(int testParamDataId) throws DataAccessException {
		return this.testScenarioParamDataDao.getTestscenarioParamdataMapbyParamDataId(testParamDataId);
	}

	public List<TestScenario> getTestScenariosByAppId(int appId) throws DataAccessException {
		return this.testScenarioDao.getTestScenariosByAppId(appId);
	}

	public TestPlan getTestPlanDetailsByPreConditionId(int conditiongroupByPreconditionId) throws DataAccessException {
		return this.testPlanDao.getTestPlanDetailsByPreConditionId(conditiongroupByPreconditionId);
	}

	public TestPlan getTestPlanDetailsByPostConditionId(int conditiongroupByPostcondition) throws DataAccessException {
		return this.testPlanDao.getTestPlanDetailsByPostConditionId(conditiongroupByPostcondition);
	}

	@Override
	public int insertTestPlanGetKey(TestPlan testPlan) throws DataAccessException {
		return this.testPlanDao.insertTestPlanGetKey(testPlan);
	}

	@Override
	public int insertTestReportGetKey(TestReport testReport) throws DataAccessException {
		return this.testReportDao.insertTestReportGetKey(testReport);
	}

	@Override
	public int insertTestRunDataGetKey(TestRunData testRunData) throws DataAccessException {
		return this.testRunDataDao.insertTestRunDataGetKey(testRunData);
	}

	@Override
	public int insertTestRunDetailsGetKey(TestRunDetails testRunDetails) throws DataAccessException {
		return this.testRunDetailsDao.insertTestRunDetailsGetKey(testRunDetails);
	}

	@Override
	public int insertTestScenarioGetKey(TestScenario testScenario) throws DataAccessException {
		return this.testScenarioDao.insertTestScenarioGetKey(testScenario);
	}

	@Override
	public int insertTestscenarioParamdataMapGetKey(TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException {
		return this.testScenarioParamDataDao.insertTestscenarioParamdataMapGetKey(testscenarioParamdataMap);
	}

}
