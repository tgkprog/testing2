package org.s2n.ddt.dao.input;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.TestStepParamGroupMapping;

public interface TestStepParamGroupMappingDao {

	/**
	 * This method will insert the shallow ObjectGroup data into the database
	 * 
	 * @param objectGroup
	 *            the ObjectGroup
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestStepParamGroupMappingDetails(TestStepParamGroupMapping testStepParamGroupMapping) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow ObjectGroup data from the
	 * database
	 * 
	 * @param int objectGroupId
	 * @return object group the ObjectGroup
	 * @throws DataAccessException
	 */
	TestStepParamGroupMapping getTestStepParamGroupMappingDetailsById(int testStepParamGroupId) throws DataAccessException;

	long deleteRecordsByStepId(int testStepId) throws DataAccessException;

	long deleteRecordsByParamGroupId(int paramGroupId) throws DataAccessException;

	List<TestStepParamGroupMapping> getRecordsByTestStepId(int testStepId) throws DataAccessException;

	int insertTestStepParamGroupMappingGetKey(TestStepParamGroupMapping testStepParamGroupMapping) throws DataAccessException;

	List<TestStepParamGroupMapping> getRecordsByParamGroupId(final int paramGroupId) throws DataAccessException;
}
