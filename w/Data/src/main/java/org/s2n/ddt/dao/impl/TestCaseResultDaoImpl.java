package org.s2n.ddt.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;

import org.s2n.ddt.dao.TestCaseResultDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestCaseResult;
import org.s2n.ddt.pojo.output.TestCaseResult;
import com.mysema.query.sql.dml.SQLInsertClause;

public class TestCaseResultDaoImpl implements TestCaseResultDao {
	
	private QueryDslJdbcTemplate template;
	private QTestCaseResult qTestCaseResult = QTestCaseResult.TestCaseResult;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new QueryDslJdbcTemplate(dataSource);
	}
	
	/*tcrId,tcId,tsId,result,startdatetime,enddatetime,comment,exception,req,response,passcount,failcount,skipcount*/
	@Override
	public int insertTestCaseResult(final TestCaseResult tcr,final  int testCaseId,final int testSuiteId) throws DataAccessException {
		long testCaseResultId=0;
		try {
		testCaseResultId = template.insert(qTestCaseResult, new SqlInsertCallback() {
			
			@Override
			public long doInSqlInsertClause(SQLInsertClause insert) {
				return insert.columns(qTestCaseResult.testCaseName,qTestCaseResult.testCaseId,qTestCaseResult.taskResultId,qTestCaseResult.result,
						qTestCaseResult.startdatetime,qTestCaseResult.enddatetime,qTestCaseResult.comment,qTestCaseResult.exception,
						qTestCaseResult.request,qTestCaseResult.response,qTestCaseResult.passcount,qTestCaseResult.failcount,qTestCaseResult.skipcount,qTestCaseResult.duration)
						.values(tcr.getTestCaseName(),testCaseId,testSuiteId,tcr.getTestCaseResult(),tcr.getPlanRunStartDateTime(),tcr.getPlanRunEndDateTime(),
								tcr.getDescription(),tcr.getErrorMsg(),tcr.getRequest(),tcr.getResponse(),tcr.getPassCount(),tcr.getFailCount(),
								tcr.getSkipCount(),tcr.getTimeDuration()).executeWithKey(qTestCaseResult.testCaseResultId);
			}
		});} catch (Exception e) {
			throw new DataAccessException(e.getMessage());
		}
		return (int)testCaseResultId;
	}

}
