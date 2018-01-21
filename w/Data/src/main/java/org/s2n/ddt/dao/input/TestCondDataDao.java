package org.s2n.ddt.dao.input;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.TestCondData;

/**
 * This interface provides TestCondData records by interacting with the database
 */

public interface TestCondDataDao {

	/**
	 * This method will insert the shallow TestCondData data into the database
	 * 
	 * @param TestCondData
	 *            the TestCondData
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	public long insertTestCondDataDetails(TestCondData testCondData) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestCondData data from the
	 * database.
	 * 
	 * @param int testCondDataId
	 * @return TestCondData
	 * @throws DataAccessException
	 */
	TestCondData getTestCondDataById(int testCondDataId) throws DataAccessException;

	int getTestCondDataByCondId(TestCondData testCondData) throws DataAccessException;

	long updateCondData(TestCondData testCondData) throws DataAccessException;

	int insertTestCondDataGetKey(TestCondData testCondData) throws DataAccessException;
}
