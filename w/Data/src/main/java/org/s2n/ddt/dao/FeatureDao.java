package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Feature;

/**
 * This interface provides Feature records by interacting with the database
 */

public interface FeatureDao {

	/**
	 * This method fetches one row of the Feature data from the database
	 * 
	 * @param int Feature
	 * @return Feature data
	 * @throws DataAccessException
	 */
	Feature getFeatureDetailsById(int featureId) throws DataAccessException;

	/**
	 * This method returns all the number of records present in the database.
	 * 
	 * @param
	 * @return Feature list
	 * @throws DataAccessException
	 */
	List<Feature> getAllFeatureDetails() throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Feature data from the
	 * database, only matching records with functionalId will be fetched.
	 * 
	 * @param int the functionalId
	 * @return Feature
	 * @throws DataAccessException
	 */
	List<Feature> getFeatureByFunctionalId(int functionalId) throws DataAccessException;

	int getFeatureIdByName(Feature feature) throws DataAccessException;

	long insertFeature(Feature feature) throws DataAccessException;

	long updateFeature(Feature feature) throws DataAccessException;

	int getFeatureIdOnlyByName(String featureName) throws DataAccessException;

	int insertFeatureGetKey(Feature feature) throws DataAccessException;
}
