package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.ObjectType;

/**
 * This interface provides ObjectType records by interacting with the database
 */
public interface ObjectTypeDao {

	/**
	 * This method will insert the shallow ObjectType data into the database
	 * 
	 * @param ObjectType
	 *            the ObjectType
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertObjectTypeDetails(ObjectType objectType) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ObjectType data from the
	 * database
	 * 
	 * @param int the ObjectTypeId
	 * @return ObjectType
	 * @throws DataAccessException
	 */
	ObjectType getObjectTypeById(int objectId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database.
	 * 
	 * @param int the objTypeId
	 * @return ObjectType
	 * @throws DataAccessException
	 */
	ObjectType getMinimalDependentObjectsByTypeId(int objTypeId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the appId
	 * @return ObjectType
	 * @throws DataAccessException
	 */
	List<ObjectType> getObjectTypesByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with defaultActionId will be fetched.
	 * 
	 * @param int the defaultActionId
	 * @return ObjectType
	 * @throws DataAccessException
	 */
	List<ObjectType> getObjectTypeBydefaultActionId(int defaultActionId) throws DataAccessException;

	int getObjectTypeIdByName(ObjectType objectType) throws DataAccessException;

	long updateObjects(ObjectType objectType) throws DataAccessException;

	int insertObjectTypeGetKey(ObjectType objectType) throws DataAccessException;
}
