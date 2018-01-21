package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.ConditionGroup;

/**
 * This interface provides ConditionGroup records by interacting with the
 * database
 */
public interface ConditionGroupDao {

	/**
	 * This method fetches one row of the ConditionGroup data from the database
	 * 
	 * @param int condGrpId
	 * @return ConditionGroup data
	 * @throws DataAccessException
	 */
	ConditionGroup getConditionGroupById(int condGrpId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ConditionGroup data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the appId
	 * @return ConditionGroup data
	 * @throws DataAccessException
	 */
	List<ConditionGroup> getConditionGroupsByAppId(int appId) throws DataAccessException;

	/**
	 * This method will insert the shallow ConditionGroup data into the database
	 * 
	 * @param ConditionGroup
	 *            the ConditionGroup
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */

	int getConditionGroupIdByName(ConditionGroup conditionGroup) throws DataAccessException;

	long insertConditionGroup(ConditionGroup conditionGroup) throws DataAccessException;

	long updateConditionGroup(ConditionGroup conditionGroup) throws DataAccessException;

	int insertConditionGroupGetKey(ConditionGroup conditionGroup) throws DataAccessException;
}
