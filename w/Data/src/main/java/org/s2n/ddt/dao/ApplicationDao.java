package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Application;

/**
 * This interface provides Application records by interacting with the database
 */

public interface ApplicationDao {

	/**
	 * This method will insert the shallow Application data into the database
	 * 
	 * @param Application
	 *            the Application
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertApplicationDetails(Application application) throws DataAccessException;
	
	int insertApplicationGetKey(Application application) throws DataAccessException;
	/**
	 * This method fetches one row of the shallow Application data from the
	 * database.
	 * 
	 * @param int appId
	 * @return records Application
	 * @throws DataAccessException
	 */
	Application getApplicationDetailsById(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Application and Functional
	 * data from the database.
	 * 
	 * @param int appId
	 * @return records Application
	 * @throws DataAccessException
	 */
	Application getApplicationDetailsTillFunctional(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Application and IdentifierType
	 * data from the database.
	 * 
	 * @param int appId
	 * @return records Application
	 * @throws DataAccessException
	 */
	Application getApplicationDetailsTillIdentifierType(int appId) throws DataAccessException;

	Application getApplicationDetailsByAppName(String AppName) throws DataAccessException;

	int getAppIdByAppName(String AppName) throws DataAccessException;

	/**
	 * This method update the Application data in the database.
	 * 
	 * @param int application
	 * @return numbers of records updated
	 * @throws DataAccessException
	 */
	long updateApplication(Application application) throws DataAccessException;

	/**
	 * This method return the number of records present in the database.
	 * 
	 * @param
	 * @return numbers of records
	 * @throws DataAccessException
	 */
	int getMaxAppId() throws DataAccessException;
}
