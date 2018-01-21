package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Functional;

/**
 * This interface provides Functional data by interacting with the database
 */
public interface FunctionalDao {

	/**
	 * This method fetches one row of the shallow Functional data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the functionalId
	 * @return Functional
	 * @throws DataAccessException
	 */
	Functional getFunctionalDetailsById(int functionalId) throws DataAccessException;

	/**
	 * This method fetches all rows from Functional table,
	 * 
	 * @param
	 * @return Functional list
	 * @throws DataAccessException
	 */
	List<Functional> getAllFunctionalDetails() throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Functional data from the
	 * database. only matching records with appId will be fetched.
	 * 
	 * @param int the appId
	 * @return Functional list
	 * @throws DataAccessException
	 */
	List<Functional> getFunctionalsByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Functional data from the
	 * database.
	 * 
	 * @param int the functionalId
	 * @return Functional
	 * @throws DataAccessException
	 */
	Functional getFunctionalDetailsTillFeature(int functionalId) throws DataAccessException;

	int getFunctionalDetailsByName(int appId, String functionalName) throws DataAccessException;

	/**
	 * This method will insert the shallow Functional data into the database
	 * 
	 * @param Functional
	 *            the Functional
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertFunctionalDetails(Functional functional) throws DataAccessException;

	long updateFunctionalDetails(Functional functional) throws DataAccessException;

	int getFunctionalIdOnlyByName(String functionalName) throws DataAccessException;

	int insertFunctionalGetKey(Functional functional) throws DataAccessException;
}
