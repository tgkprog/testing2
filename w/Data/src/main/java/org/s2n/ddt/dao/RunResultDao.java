package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.RunResult;

public interface RunResultDao {
	
	//rrId(Primary Key), userId(foreign/ref key), summaryReport(yes/no), detailReport(yes/no), dateStarted, dateEnded
	
	int InsertRunResult(RunResult rResult) throws DataAccessException;
	
    RunResult getRunResultById(int rId) throws DataAccessException;
    
    void updateRunResult(RunResult rResult) throws DataAccessException;

}
