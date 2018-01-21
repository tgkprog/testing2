package org.s2n.ddt.dao.output;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestPlan;

/**
 * This interface provides TestPlan records by interacting with the database
 */
public interface TestPlanDao {

	/**
	 * This method fetches one row from TestPlan table from the database.
	 * 
	 * @param int testPlanId
	 * @return records TestPlan
	 * @throws DataAccessException
	 */
	TestPlan getTestPlanDetailsById(int testPlanId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestPlan data from the
	 * database, only matching records with conditiongroupByPreconditionId will
	 * be fetched.
	 * 
	 * @param int conditiongroupByPreconditionId
	 * @return records TestPlan
	 * @throws DataAccessException
	 */
	TestPlan getTestPlanDetailsByPreConditionId(int conditiongroupByPreconditionId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestPlan data from the
	 * database, only matching records with conditiongroupByPostcondition will
	 * be fetched.
	 * 
	 * @param int conditiongroupByPostcondition
	 * @return records TestPlan
	 * @throws DataAccessException
	 */
	TestPlan getTestPlanDetailsByPostConditionId(int conditiongroupByPostcondition) throws DataAccessException;

	/**
	 * This method will insert the shallow TestPlan data into the database
	 * 
	 * @param TestPlan
	 *            the TestPlan
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestPlanDetails(TestPlan testPlan) throws DataAccessException;

	int insertTestPlanGetKey(TestPlan testPlan) throws DataAccessException;
	
	long updateTestPlan(TestPlan testPlan) throws DataAccessException;
	
	int getTestPlanIdOnlyByName(String testPlanName) throws DataAccessException;
}
