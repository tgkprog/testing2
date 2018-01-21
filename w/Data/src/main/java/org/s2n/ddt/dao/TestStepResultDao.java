package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestStepResult;

public interface TestStepResultDao {
	
	int insertTSResult(TestStepResult tsr,String rName,String obj,String aName,String sParam,String sValue,String status,String cmnt,String dMsg,String duration) throws DataAccessException;
	
	void updateTCResultIdByTSResultId(int testCaseResultId,int testStepResultId) throws DataAccessException;
	
//	TestStepResult getTSRById(int tsrId) throws DataAccessException;
	
//	TestStepResult getTSById(int tsId) throws DataAccessException;

}
