package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.ParamGroup;

/**
 * This interface provides ParamGroup records by interacting with the database
 */
public interface ParamGroupDao {

	/**
	 * This method will insert the shallow ParamGroup data into the database
	 * 
	 * @param ParamGroup
	 *            the ParamGroup
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	public long insertParamGroupDetails(ParamGroup paramGroup) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ParamGroup data from the
	 * database
	 * 
	 * @param int paramGroupId
	 * @return ParamGroup
	 * @throws DataAccessException
	 */
	ParamGroup getParamGroupDetailsById(int paramGroupId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ParamGroup data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int appId
	 * @return ParamGroup list
	 * @throws DataAccessException
	 */
	List<ParamGroup> getParamGroupsByAppId(int appId) throws DataAccessException;

	int getParamGroupIdDetailsByName(ParamGroup paramGroup) throws DataAccessException;

	long updateParamGroup(ParamGroup paramGroup) throws DataAccessException;

	int getParamGroupIdDetailsOnlyByName(String paramGroupName) throws DataAccessException;

	long deleteParamGroupById(int paramGrpId) throws DataAccessException;

	int insertParamGroupGetKey(ParamGroup paramGroup) throws DataAccessException;
}
