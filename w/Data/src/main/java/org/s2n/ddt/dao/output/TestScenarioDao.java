package org.s2n.ddt.dao.output;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestScenario;

/**
 * This interface provides TestScenario records by interacting with the database
 */
public interface TestScenarioDao {

	/**
	 * This method fetches one row of the shallow TestRunDetails data from the
	 * database, only matching records with testPlanId will be fetched.
	 * 
	 * @param int testPlanId
	 * @return records TestRunDetails list
	 * @throws DataAccessException
	 */
	TestScenario getTestScenarioDetailsById(int testScenarioId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestScenario data from the
	 * database, only matching records with testPlanId appId be fetched.
	 * 
	 * @param int appId
	 * @return records TestScenario list
	 * @throws DataAccessException
	 */
	List<TestScenario> getTestScenariosByAppId(int appId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestScenario data into the database
	 * 
	 * @param TestScenario
	 *            the TestScenario
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestScenarioDetails(TestScenario testScenario) throws DataAccessException;

	int getTestScenarioByName(String testScenarioName) throws DataAccessException;

	int insertTestScenarioGetKey(TestScenario testScenario) throws DataAccessException;

	long updateTestScenario(TestScenario testScenario) throws DataAccessException;
}
