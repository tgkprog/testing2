package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TestCaseResult;

public interface TestCaseResultDao {

	int insertTestCaseResult(TestCaseResult tcr,int testCaseId,int testSuiteId) throws DataAccessException;	
}
