package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.AgentDetails;

/**
 * This interface provides AgentDetails1 records by interacting with the
 * database
 */

public interface AgentDetailsDao {

	/**
	 * This method fetches one row of the shallow AgentDetails1 data from the
	 * database
	 * 
	 * @param int agentId
	 * @return records AgentDetails1
	 * @throws DataAccessException
	 */
	AgentDetails getAgentDetailsById(int agentId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow IdentifierType data from the
	 * database, only matching records with testPlanId will be fetched.
	 * 
	 * @param int testPlanId
	 * @return records AgentDetails1 list
	 * @throws DataAccessException
	 */
	List<AgentDetails> getAgentDetailsByTestPlanId(int testPlanId) throws DataAccessException;

	/**
	 * This method will insert the shallow AgentDetails1 data into the database
	 * 
	 * @param AgentDetails1
	 *            the AgentDetails1
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertAgentDetails(AgentDetails agentDetails) throws DataAccessException;

	int getAgentIdByAgentDetails(AgentDetails agentDetails) throws DataAccessException;

	long updateAgentDetails(AgentDetails agentDetails) throws DataAccessException;

	int insertAgentDetailsGetKey(AgentDetails agentDetails) throws DataAccessException;
}
