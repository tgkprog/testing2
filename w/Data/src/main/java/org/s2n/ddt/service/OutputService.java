package org.s2n.ddt.service;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestReport;
import org.s2n.ddt.pojo.output.TestRunData;
import org.s2n.ddt.pojo.output.TestRunDetails;
import org.s2n.ddt.pojo.output.TestScenario;
import org.s2n.ddt.pojo.output.TestscenarioParamdataMap;

public interface OutputService {

	TestPlan getTestPlanDetailsById(int testPlanId) throws DataAccessException;

	TestReport getTestReportById(int testReportId) throws DataAccessException;

	TestReport getTestReportByRunId(int testRunID) throws DataAccessException;

	TestRunData getTestRunDataById(int testRunDataId) throws DataAccessException;

	List<TestRunData> getTestRunDataByTestCaseId(int testCaseId) throws DataAccessException;

	List<TestRunData> getTestRunDataByPlanId(int testPlanId) throws DataAccessException;

	TestRunDetails getTestRunDetailsById(int testRunId) throws DataAccessException;

	List<TestRunDetails> getTestRunDetailsByPlanId(int testPlanId) throws DataAccessException;

	TestScenario getTestScenarioDetailsById(int testScenarioId) throws DataAccessException;

	List<TestScenario> getTestScenariosByAppId(int appId) throws DataAccessException;

	TestscenarioParamdataMap getTestScenarioParamDataById(int testScenarioParamId) throws DataAccessException;

	TestscenarioParamdataMap getTestscenarioParamdataMapbyScenarioId(int testScenarioId) throws DataAccessException;

	TestscenarioParamdataMap getTestscenarioParamdataMapbyParamDataId(int testParamDataId) throws DataAccessException;

	TestPlan getTestPlanDetailsByPreConditionId(int conditiongroupByPreconditionId) throws DataAccessException;

	TestPlan getTestPlanDetailsByPostConditionId(int conditiongroupByPostcondition) throws DataAccessException;

	int insertTestPlanGetKey(TestPlan testPlan) throws DataAccessException;

	int insertTestReportGetKey(TestReport testReport) throws DataAccessException;

	int insertTestRunDataGetKey(TestRunData testRunData) throws DataAccessException;

	int insertTestRunDetailsGetKey(TestRunDetails testRunDetails) throws DataAccessException;

	int insertTestScenarioGetKey(TestScenario testScenario) throws DataAccessException;

	int insertTestscenarioParamdataMapGetKey(TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException;
}
