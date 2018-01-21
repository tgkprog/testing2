package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Conditions;

/**
 * This interface provides Conditions records by interacting with the database
 */
public interface ConditionsDao {

	/**
	 * This method fetches one row of the Conditions data from the database
	 * 
	 * @param int conditionId
	 * @return Conditions data
	 * @throws DataAccessException
	 */
	Conditions getConditionsById(int conditionId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Feature data from the
	 * database, only matching records with condGrpId will be fetched.
	 * 
	 * @param int the condGrpId
	 * @return Conditions data
	 * @throws DataAccessException
	 */
	Conditions getConditionsByConditionGroupId(int condGrpId) throws DataAccessException;

	/**
	 * This method will insert the shallow Conditions data into the database
	 * 
	 * @param Conditions
	 *            the Conditions
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */

	int getConditionsIdByName(Conditions conditions) throws DataAccessException;

	long insertConditions(Conditions conditions) throws DataAccessException;

	long updateConditions(Conditions conditions) throws DataAccessException;

	long deleteConditionsByGroupId(int CondGrpId) throws DataAccessException;

	int insertConditionsGetKey(Conditions conditions) throws DataAccessException;
}
