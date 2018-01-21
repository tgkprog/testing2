package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Lane;

public interface LaneDao {
	
	// Returns the Lane object for the matching laneId
	Lane getLaneById (int laneId) throws DataAccessException;
	
	int LaneIdByLaneName(String laneName) throws DataAccessException;
	
	int insertLane(final Lane lane) throws DataAccessException;

	void updateLane(final Lane lane) throws DataAccessException;
	
	//laneUserName, agentName
	String getAgentName(int laneId) throws DataAccessException;

	String getUserName(int laneId) throws DataAccessException;
}
