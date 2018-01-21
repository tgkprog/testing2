package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.IdentifierType;

/**
 * This interface provides IdentifierType records by interacting with the
 * database
 */
public interface IdentifierTypeDao {

	/**
	 * This method will insert the shallow IdentifierType data into the database
	 * 
	 * @param IdentifierType
	 *            the IdentifierType
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertIdentifierTypeDetails(IdentifierType identifierType) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow IdentifierType data from the
	 * database
	 * 
	 * @param int identifierId
	 * @return records IdentifierType
	 * @throws DataAccessException
	 */
	IdentifierType getIdentifierTypeById(int identifierId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow IdentifierType data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int appId
	 * @return records IdentifierType
	 * @throws DataAccessException
	 */
	List<IdentifierType> getIdentifierTypesByAppId(int appId) throws DataAccessException;

	/**
	 * This method return the number of records present in the database.
	 * 
	 * @param
	 * @return numbers of records
	 * @throws DataAccessException
	 */
	int getMaxIdentifierId() throws DataAccessException;

	int getIdentifierTypeIdByName(IdentifierType identifierType) throws DataAccessException;

	long updateIdentifierType(IdentifierType identifierType) throws DataAccessException;

	int insertIdentifierTypeGetKey(IdentifierType identifierType) throws DataAccessException;
	
	int getIdentifierTypeIdOnlyByName(String identifierTypeName) throws DataAccessException;
}
