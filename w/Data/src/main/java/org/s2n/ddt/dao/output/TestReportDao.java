package org.s2n.ddt.dao.output;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestReport;

/**
 * This interface provides TestReport records by interacting with the database
 */
public interface TestReportDao {

	/**
	 * This method fetches one row from TestReport table from the database.
	 * 
	 * @param int testReportId
	 * @return records TestReport
	 * @throws DataAccessException
	 */
	TestReport getTestReportById(int testReportId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestReport data from the
	 * database, only matching records with testRunID will be fetched.
	 * 
	 * @param int testRunID
	 * @return records TestReport
	 * @throws DataAccessException
	 */
	TestReport getTestReportByRunId(int testRunID) throws DataAccessException;

	/**
	 * This method will insert the shallow TestReport data into the database
	 * 
	 * @param TestReport
	 *            the TestReport
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestReportDetails(TestReport testReport) throws DataAccessException;

	int insertTestReportGetKey(TestReport testReport) throws DataAccessException;
}
