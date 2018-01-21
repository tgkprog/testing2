package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;

/**
 * This interface provides TestPlanScenarioMap data by interacting with the
 * database
 */
public interface TestPlanScenarioMapDao {

	/**
	 * This method fetches one row of the shallow TestPlanScenarioMap table from
	 * the database
	 * 
	 * @param int the testPlanScenarioId
	 * @return TestplanTestscenarioMap
	 * @throws DataAccessException
	 */
	TestplanTestscenarioMap getTestplanTestscenarioMapById(int testPlanScenarioId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestPlanScenarioMap data from
	 * the database
	 * 
	 * @param int the testPlanId
	 * @return TestplanTestscenarioMap list
	 * @throws DataAccessException
	 */
	List<TestplanTestscenarioMap> getTestplanTestscenarioMapByPlanId(int testPlanId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ConditionGroup data from the
	 * database, only matching records with testScenarioId will be fetched.
	 * 
	 * @param int the testScenarioId
	 * @return TestplanTestscenarioMap list
	 * @throws DataAccessException
	 */
	List<TestplanTestscenarioMap> getTestplanTestscenarioMapbyScenarioId(int testScenarioId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestplanTestscenarioMap data into the
	 * database
	 * 
	 * @param TestplanTestscenarioMap
	 *            the TestplanTestscenarioMap
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestplanTestscenarioDetails(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	long updateTestplanTestscenario(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	int getTestplanTestscenarioByTestScenarioId(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;

	int insertTestplanTestscenarioMapGetKey(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException;
}
