package org.s2n.ddt.dao.output;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestRunData;

/**
 * This interface provides TestRunData records by interacting with the database
 */

public interface TestRunDataDao {

	/**
	 * This method fetches one row from TestRunData table from the database.
	 * 
	 * @param int testRunDataId
	 * @return records TestRunData
	 * @throws DataAccessException
	 */
	TestRunData getTestRunDataById(int testRunDataId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestRunData data from the
	 * database, only matching records with testCaseId will be fetched.
	 * 
	 * @param int testCaseId
	 * @return records TestRunData list
	 * @throws DataAccessException
	 */
	List<TestRunData> getTestRunDataByTestCaseId(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestRunData data from the
	 * database, only matching records with testPlanId will be fetched.
	 * 
	 * @param int testPlanId
	 * @return records TestRunData list
	 * @throws DataAccessException
	 */
	List<TestRunData> getTestRunDataByPlanId(int testPlanId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestRunData data into the database
	 * 
	 * @param TestRunData
	 *            the TestRunData
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestRunDataDetails(TestRunData testRunData) throws DataAccessException;

	int insertTestRunDataGetKey(TestRunData testRunData) throws DataAccessException;
}
