package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.ObjectGroup;

/**
 * This interface provides ObjectGroup records by interacting with the database
 */
public interface ObjectGroupDao {

	/**
	 * This method will insert the shallow ObjectGroup data into the database
	 * 
	 * @param objectGroup
	 *            the ObjectGroup
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertObjectGroupDetails(ObjectGroup objectGroup) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ObjectGroup data from the
	 * database
	 * 
	 * @param int objectGroupId
	 * @return object group the ObjectGroup
	 * @throws DataAccessException
	 */
	ObjectGroup getObjectGroupDetailsById(int objectGroupId) throws DataAccessException;

	/**
	 * This method fetches the ObjectGroup dependent object's data also. Here
	 * they are namely Objects
	 * 
	 * @param int the objectGroupId
	 * @return ObjectGroup the ObjectGroup
	 * @throws DataAccessException
	 */
	ObjectGroup getObjectGroupWithObjectsById(int objectGroupId) throws DataAccessException;

	/**
	 * This method fetches the ObjectGroup dependent object's data which are
	 * mapped with the appId. Here they are namely Objects
	 * 
	 * @param int the appId
	 * @return ObjectGroup the ObjectGroup
	 * @throws DataAccessException
	 */
	List<ObjectGroup> getObjectGroupsByAppId(int appId) throws DataAccessException;

	/**
	 * This method fetches the ObjectGroup dependent object's data which are
	 * mapped with the screenId. Here they are namely Objects
	 * 
	 * @param int screenId
	 * @return ObjectGroup the ObjectGroup
	 * @throws DataAccessException
	 */
	List<ObjectGroup> getObjectGroupWithObjectsByScreenId(int screenId) throws DataAccessException;

	int getObjectGroupsIdByAppId(ObjectGroup objectGroup) throws DataAccessException;

	long updateObjectGroups(ObjectGroup objectGroup) throws DataAccessException;

	int getObjectGroupWithObjectsByName(String Name) throws DataAccessException;

	int insertObjectGroupGetKey(ObjectGroup objectGroup) throws DataAccessException;
}
