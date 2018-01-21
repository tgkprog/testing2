package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.TestSuite;

/**
 * This interface provides TestSuite data by interacting with the database
 */
public interface TestSuiteDao {

	/**
	 * This method fetches one row from TestSuite table from the database
	 * 
	 * @param int the testSuiteId
	 * @return TestSuite
	 * @throws DataAccessException
	 */
	TestSuite getTestSuiteDetailsById(int testSuiteId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestSuite data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the appId
	 * @return TestSuite list
	 * @throws DataAccessException
	 */
	List<TestSuite> getTestSuitesByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestSuite records from the
	 * database, only matching records with testScenarioId will be fetched.
	 * 
	 * @param int the testScenarioId
	 * @return TestSuite
	 * @throws DataAccessException
	 */
	TestSuite getTestSuiteWithTestCaseById(int testSuiteId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestSuite data from the
	 * database.
	 * 
	 * @param int the testSuiteId
	 * @return TestSuite
	 * @throws DataAccessException
	 */
	TestSuite getTestCaseWithSuiteScenarioMappingById(int testSuiteId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestSuite data from the
	 * database, only matching records with functionalId will be fetched.
	 * 
	 * @param int the functionalId
	 * @return TestSuite list
	 * @throws DataAccessException
	 */
	List<TestSuite> getTestSuiteDetailsByFunctionalId(int functionalId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestSuite data from the
	 * database, only matching records with featureId will be fetched.
	 * 
	 * @param int the featureId
	 * @return TestSuite list
	 * @throws DataAccessException
	 */
	List<TestSuite> getTestSuiteDetailsByFeatureId(int featureId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestSuite data into the database
	 * 
	 * @param TestSuite
	 *            the TestSuite
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestSuiteDetails(TestSuite suite) throws DataAccessException;

	long updateTestSuite(TestSuite suite) throws DataAccessException;

	int getTestSuiteDetailsByName(TestSuite suite) throws DataAccessException;

	int getTestSuiteDetailsOnlyByName(String suite) throws DataAccessException;

	int insertTestSuiteGetKey(TestSuite testSuite) throws DataAccessException;
}
