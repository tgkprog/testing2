package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.TestStep;

/**
 * This interface provides TestStep records by interacting with the database
 */

public interface TestStepDao {

	/**
	 * This method will insert the shallow TestStep data into the database
	 * 
	 * @param TestStep
	 *            the TestStep
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestStepDetails(TestStep testStep) throws DataAccessException;

	int insertTestStepGetKey(TestStep testStep) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database.
	 * 
	 * @param int testStepId
	 * @return TestStep
	 * @throws DataAccessException
	 */
	TestStep getTestStepDetailsById(int testStepId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with testCaseId will be fetched.
	 * 
	 * @param int testCaseId
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepsByCaseId(int testCaseId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with actionid will be fetched.
	 * 
	 * @param int actionid
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByActionId(int actionid) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with testCaseID will be fetched.
	 * 
	 * @param int testCaseID
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByTestcaseId(int testCaseID) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with runnerId will be fetched.
	 * 
	 * @param int runnerId
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByRunnerId(int runnerId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with conditiongroupByPostconditionId will
	 * be fetched.
	 * 
	 * @param int conditiongroupByPostconditionId
	 * @return TestStep
	 * @throws DataAccessException
	 */
	TestStep getTestStepDetailsByPostconditionId(int conditiongroupByPostconditionId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with conditiongroupByPreconditionId will
	 * be fetched.
	 * 
	 * @param int conditiongroupByPreconditionId
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByPreconditionId(int conditiongroupByPreconditionId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with paramgroupByInputparamId will be
	 * fetched.
	 * 
	 * @param int paramgroupByInputparamId
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByInputparamId(int paramgroupByInputparamId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestStep data from the
	 * database, only matching records with paramgroupByOutputparamId will be
	 * fetched.
	 * 
	 * @param int paramgroupByOutputparamId
	 * @return TestStep list
	 * @throws DataAccessException
	 */
	List<TestStep> getTestStepDetailsByOutputparamId(int paramgroupByOutputparamId) throws DataAccessException;

	int getTestStepByName(TestStep testStep) throws DataAccessException;

	long updateTestStep(TestStep testStep) throws DataAccessException;

	int getTestStepOnlByName(String testStepName) throws DataAccessException;

	long deleteTestStepByCaseId(int testCaseId) throws DataAccessException;
}
