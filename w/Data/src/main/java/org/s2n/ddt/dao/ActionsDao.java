package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Actions;

/**
 * This interface provides Action records by interacting with the database
 */

public interface ActionsDao {

	/**
	 * This method fetches one row of the shallow Action data from the database
	 * 
	 * @param int actionId
	 * @return records Action
	 * @throws DataAccessException
	 */
	Actions getActionsById(int actionId) throws DataAccessException;

	/**
	 * This method will insert the shallow Actions data into the database
	 * 
	 * @param Actions
	 *            the Actions
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertActionsDetails(Actions actions) throws DataAccessException;

	int getActionIdByActionName(String actionName) throws DataAccessException;

	long updateActions(Actions actions) throws DataAccessException;

	List<String> getAllActionNames() throws DataAccessException;

	int insertActionsGetKey(Actions actions) throws DataAccessException;
}
