package org.s2n.ddt.dao.output;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestRunDetails;

/**
 * This interface provides TestRunDetails records by interacting with the
 * database
 */
public interface TestRunDetailsDao {

	/**
	 * This method fetches one row of the shallow TestRunDetails data from the
	 * database.
	 * 
	 * @param int testRunId
	 * @return records TestRunDetails
	 * @throws DataAccessException
	 */
	TestRunDetails getTestRunDetailsById(int testRunId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestRunDetails data from the
	 * database, only matching records with testPlanId will be fetched.
	 * 
	 * @param int testPlanId
	 * @return records TestRunDetails list
	 * @throws DataAccessException
	 */
	List<TestRunDetails> getTestRunDetailsByPlanId(int testPlanId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestRunDetails data into the database
	 * 
	 * @param TestRunDetails
	 *            the TestRunDetails
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestRunDetails(TestRunDetails testRunDetails) throws DataAccessException;

	int insertTestRunDetailsGetKey(TestRunDetails testRunDetails) throws DataAccessException;
}
