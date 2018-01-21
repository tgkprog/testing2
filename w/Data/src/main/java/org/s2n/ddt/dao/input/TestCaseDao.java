package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.TestCase;

/**
 * This interface provides TestCase records by interacting with the database
 */

public interface TestCaseDao {

	/**
	 * This method will insert the shallow TestCase data into the database
	 * 
	 * @param TestCase
	 *            the TestCase
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestCaseDetails(TestCase testcase) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database.
	 * 
	 * @param int testCaseId
	 * @return TestCase
	 * @throws DataAccessException
	 */
	TestCase getTestCaseDetailsById(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database, only matching records with testCaseId will be fetched.
	 * 
	 * @param int testCaseId
	 * @return TestCase
	 * @throws DataAccessException
	 */
	TestCase getTestCaseWithTestParamDataById(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database.
	 * 
	 * @param int testCaseId
	 * @return TestCase
	 * @throws DataAccessException
	 */
	TestCase getTestCaseWithparamgroupsById(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database.
	 * 
	 * @param int testCaseId
	 * @return TestCase
	 * @throws DataAccessException
	 */
	TestCase getTestCaseWithTestStepsById(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database.
	 * 
	 * @param int testCaseId
	 * @return TestCase
	 * @throws DataAccessException
	 */
	TestCase getTestCaseWithTestRunDatasById(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database, only matching records with testSuiteId will be fetched.
	 * 
	 * @param int testSuiteId
	 * @return TestCase list
	 * @throws DataAccessException
	 */
	List<TestCase> getTestSuiteDataByTestCaseId(int testSuiteId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCase data from the
	 * database, only matching records with runnerId will be fetched.
	 * 
	 * @param int runnerId
	 * @return TestCase list
	 * @throws DataAccessException
	 */
	List<TestCase> getTestCaseDetailsByRunnerId(int runnerId) throws DataAccessException;

	int getTestCaseIdByRunnerId(TestCase testCase) throws DataAccessException;

	long updateTestCase(TestCase testCase) throws DataAccessException;

	int getTestCaseDetailsOnlyByName(String testCaseName) throws DataAccessException;

	int insertTestCaseGetKey(TestCase testCase) throws DataAccessException;
}
