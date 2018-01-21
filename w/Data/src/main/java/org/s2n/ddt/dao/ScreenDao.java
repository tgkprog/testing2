package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.Screen;

/**
 * This interface provides Screen data by interacting with the database
 */
public interface ScreenDao {

	/**
	 * This method fetches one row of the shallow Screen data from the database
	 * 
	 * @param int the screenId
	 * @return Screen
	 * @throws DataAccessException
	 */
	Screen getScreenById(int screenId) throws DataAccessException;

	/**
	 * This method fetches one row of the shallow Screen data from the database,
	 * only matching records with appId will be fetched.
	 * 
	 * @param int the appId
	 * @return Screen list
	 * @throws DataAccessException
	 */
	List<Screen> getScreensByAppId(int appID) throws DataAccessException;

	/**
	 * This method will insert the shallow Screen data into the database
	 * 
	 * @param Screen
	 *            the Screen
	 * @return number of rows inserted
	 * @throws DataAccessException
	 */

	int getScreenIdByName(Screen screen) throws DataAccessException;

	long insertScreenDetails(Screen screen) throws DataAccessException;

	long updateScreen(Screen screen) throws DataAccessException;

	int getScreenIdByScreenName(String name) throws DataAccessException;

	int insertScreenGetKey(Screen screen) throws DataAccessException;
}
