package org.s2n.ddt.dao.output;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestscenarioParamdataMap;

/**
 * This interface provides TestScenarioParamData records by interacting with the
 * database
 */
public interface TestScenarioParamDataDao {

	/**
	 * This method fetches one row of the shallow TestscenarioParamdataMap data
	 * from the database.
	 * 
	 * @param int testScenarioParamDataId
	 * @return records TestscenarioParamdataMap
	 * @throws DataAccessException
	 */
	TestscenarioParamdataMap getTestScenarioParamDataById(int testScenarioParamDataId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestscenarioParamdataMap data
	 * from the database, only matching records with testScenarioId will be
	 * fetched.
	 * 
	 * @param int testScenarioId
	 * @return records TestscenarioParamdataMap
	 * @throws DataAccessException
	 */
	TestscenarioParamdataMap getTestscenarioParamdataMapbyScenarioId(int testScenarioId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow TestscenarioParamdataMap data
	 * from the database, only matching records with testParamDataId will be
	 * fetched.
	 * 
	 * @param int testParamDataIds
	 * @return records TestscenarioParamdataMap
	 * @throws DataAccessException
	 */
	TestscenarioParamdataMap getTestscenarioParamdataMapbyParamDataId(int testParamDataId) throws DataAccessException;

	/**
	 * This method will insert the shallow TestscenarioParamdataMap data into
	 * the database
	 * 
	 * @param TestscenarioParamdataMap
	 *            the TestscenarioParamdataMap
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	long insertTestScenarioDetails(TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException;

	int insertTestscenarioParamdataMapGetKey(TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException;
}
