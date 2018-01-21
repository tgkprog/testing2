package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.TestParamData;

/**
 * This interface provides TestParamData records by interacting with the
 * database
 */

public interface TestParamDataDao {

	/**
	 * This method will insert the shallow TestParamData data into the database
	 * 
	 * @param TestParamData
	 *            the TestParamData
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	public long insertTestParamDataDetails(TestParamData testParamData) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestParamData data from the
	 * database.
	 * 
	 * @param int TestParamDataId
	 * @return TestParamData
	 * @throws DataAccessException
	 */
	TestParamData getTestParamDataDetailsById(int TestParamDataId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestParamData data from the
	 * database, only matching records with testStepId will be fetched.
	 * 
	 * @param int testStepId
	 * @return TestParamData list
	 * @throws DataAccessException
	 */
	List<TestParamData> getTestParamDataByStepId(int testStepId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestParamData data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int appId
	 * @return TestParamData list
	 * @throws DataAccessException
	 */
	List<TestParamData> getTestParamDatasByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestParamData data from the
	 * database, only matching records with testCaseId will be fetched.
	 * 
	 * @param int testCaseId
	 * @return TestParamData list
	 * @throws DataAccessException
	 */
	List<TestParamData> getTestParamDataByTestCaseId(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestParamData data from the
	 * database, only matching records with paramid will be fetched.
	 * 
	 * @param int paramid
	 * @return TestParamData
	 * @throws DataAccessException
	 */
	List<TestParamData> getTestParamDataDetailsByParamId(int paramid) throws DataAccessException;

	int getTestParamDataIdByAppId(TestParamData testParamData) throws DataAccessException;

	long updateParamData(TestParamData testParamData) throws DataAccessException;

	long deleteTestParamDataByStepId(int testStepId) throws DataAccessException;

	int insertTestParamDataGetKey(TestParamData testParamData) throws DataAccessException;
}
