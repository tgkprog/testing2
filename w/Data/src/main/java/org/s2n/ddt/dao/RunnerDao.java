package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Runner;

/**
 * This interface provides Runner data by interacting with the database
 */
public interface RunnerDao {

	/**
	 * This method fetches one row of the shallow Runner data from the database
	 * 
	 * @param int the runnerId
	 * @return Runner
	 * @throws DataAccessException
	 */
	Runner getRunnerById(int runnerId) throws DataAccessException;

	/**
	 * This method will insert the shallow Runner data into the database
	 * 
	 * @param Runner
	 *            the Runner
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */

	long insertRunnerDetails(Runner runner) throws DataAccessException;

	long updateRunner(Runner runner) throws DataAccessException;

	int getRunnerIdByName(Runner runner) throws DataAccessException;

	int getRunnerIdOnlyByName(String runnerName) throws DataAccessException;

	int insertRunnerGetKey(Runner runner) throws DataAccessException;
}
