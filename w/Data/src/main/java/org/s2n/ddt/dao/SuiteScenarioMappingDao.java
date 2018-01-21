package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.SuiteScenarioMapping;

/**
 * This interface provides SuiteScenarioMapping data by interacting with the
 * database
 */
public interface SuiteScenarioMappingDao {

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the suiteScenarioId
	 * @return SuiteScenarioMapping
	 * @throws DataAccessException
	 */
	SuiteScenarioMapping getSuiteScenarioMappingById(int suiteScenarioId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Objects data from the
	 * database, only matching records with appId will be fetched.
	 * 
	 * @param int the suiteScenarioId
	 * @return SuiteScenarioMapping
	 * @throws DataAccessException
	 */
	List<SuiteScenarioMapping> getSuiteScenarioMappingBytestScenarioId(int testScenarioId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow SuiteScenarioMapping data from
	 * the database
	 * 
	 * @param int the testSuiteId
	 * @return SuiteScenarioMapping
	 * @throws DataAccessException
	 */
	List<SuiteScenarioMapping> getSuiteScenarioMappingBytestSuiteId(int testSuiteId) throws DataAccessException;

	/**
	 * This method will insert the shallow SuiteScenarioMapping data into the
	 * database
	 * 
	 * @param SuiteScenarioMapping
	 *            the SuiteScenarioMapping
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */
	int getSuiteScenarioId(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;

	long insertSuiteScenarioMappingDetails(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;

	long updateSuiteScenarioMapping(SuiteScenarioMapping scenarioMapping) throws DataAccessException;

	int insertSuiteScenarioMappingGetKey(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException;
}
