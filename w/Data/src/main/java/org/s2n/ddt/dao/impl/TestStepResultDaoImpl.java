package org.s2n.ddt.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.TestStepResultDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestStepResult;
import org.s2n.ddt.pojo.output.TestStepResult;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class TestStepResultDaoImpl implements TestStepResultDao {
		
	private QueryDslJdbcTemplate template;
	private QTestStepResult qTestStepResult = QTestStepResult.TestStepResult;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		template = new QueryDslJdbcTemplate(dataSource);
	}
	
	@Override
	public int insertTSResult(final TestStepResult tsr,final String rName,final String obj,final String aName,final String sParam,final String sValue,final String status,final String cmnt,final String dMsg,final String duration) throws DataAccessException {		
		try {
		long tsrId = template.insert(qTestStepResult, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause query) {
			return	query.columns(qTestStepResult.testStepId,qTestStepResult.testCaseResultId,qTestStepResult.runnerName,qTestStepResult.paramObjectGroup,
						qTestStepResult.actionName,qTestStepResult.stepParam,qTestStepResult.paramData,qTestStepResult.result,qTestStepResult.comment,
						qTestStepResult.detailMsg,qTestStepResult.exception,qTestStepResult.request,qTestStepResult.response,qTestStepResult.duration)
						.values(null,tsr.getTestCaseResultId(),rName,obj,aName,sParam,sValue,status,cmnt,dMsg,null,null,null,duration)
						.executeWithKey(qTestStepResult.testStepResultId);	
			
			}
		});
		return (int)tsrId;
	} catch (Exception ex) {
		throw new DataAccessException(ex.getMessage());
	}
}

	@Override
	public void updateTCResultIdByTSResultId(final int testCaseResultId,final int testStepResultId)
			throws DataAccessException {
		try {
			template.update(qTestStepResult, new SqlUpdateCallback() {
				
				@Override
				public long doInSqlUpdateClause(SQLUpdateClause update) {
					return update.set(qTestStepResult.testCaseResultId, testCaseResultId).where(qTestStepResult.testCaseResultId.eq(testStepResultId)).execute();
				}
			});
		} catch (Exception ex) {
			throw new DataAccessException(ex.getMessage());
		}
	}	
}
