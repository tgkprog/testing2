package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Objects;

/**
 * This interface provides Objects records by interacting with the database
 */
public interface ObjectsDao {

	/**
	 * This method will insert the shallow Objects data into the database
	 * 
	 * @param objects
	 *            the Objects
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertObjectDetails(Objects objects) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the database
	 * 
	 * @param int objectId
	 * @return records Objects type
	 * @throws DataAccessException
	 */
	Objects getObjectsDetailsById(int objectId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with groupId will be fetched.
	 * 
	 * @param int the groupId
	 * @return Objects
	 * @throws DataAccessException
	 */
	List<Objects> getObjectsByGroupId(int groupId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with typeId will be fetched.
	 * 
	 * @param int the objectId
	 * @return Objects
	 * @throws DataAccessException
	 */
	List<Objects> getObjectsByTypeId(int objTypeId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with AppId will be fetched.
	 * 
	 * @param int the appId
	 * @return Objects
	 * @throws DataAccessException
	 */
	List<Objects> getObjectsByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with identifiertypeId will be fetched.
	 * 
	 * @param int the identifiertypeid
	 * @return Objects
	 * @throws DataAccessException
	 */
	List<Objects> getObjectsDetailsByIdentifiertypeId(int identifiertypeid) throws DataAccessException;

	int getObjectsIdByGroupId(Objects objects) throws DataAccessException;

	long updateObjects(Objects objects) throws DataAccessException;

	int insertObjectsGetKey(Objects objects) throws DataAccessException;
	
	int getObjectsIdOnlyByName(String objName) throws DataAccessException;
}
