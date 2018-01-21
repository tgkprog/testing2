package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Param;

/**
 * This interface provides Param records by interacting with the database
 */
public interface ParamDao {

	/**
	 * This method will insert the shallow Param data into the database
	 * 
	 * @param Param
	 *            the Param
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertParamDetails(Param param) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Param data from the database
	 * 
	 * @param int the paramid
	 * @return Param list
	 * @throws DataAccessException
	 */
	List<Param> getParamDetailsById(int paramid) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with paramGroupId will be fetched.
	 * 
	 * @param int the paramGroupId
	 * @return Param list
	 * @throws DataAccessException
	 */
	List<Param> getParamDetailsByParamGroupId(int paramGroupId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with objectId will be fetched.
	 * 
	 * @param int the objectId
	 * @return Param list
	 * @throws DataAccessException
	 */
	List<Param> getParamDetailsByObjectId(int objectId) throws DataAccessException;

	int getParamIdDetailsByName(Param param) throws DataAccessException;

	long updateParam(Param param) throws DataAccessException;

	long deleteParamByGroupId(int paramGrpId) throws DataAccessException;

	int insertParamGetKey(Param param) throws DataAccessException;
	
	int getParamIdDetailsOnlyByName(String paramName) throws DataAccessException;
}
