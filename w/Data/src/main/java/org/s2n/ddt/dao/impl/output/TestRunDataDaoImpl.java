package org.s2n.ddt.dao.impl.output;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;

import org.s2n.ddt.dao.output.TestRunDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestRunData;
import org.s2n.ddt.pojo.output.TestRunData;
import org.s2n.ddt.pojo.output.TestRunDataId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestRunDataDaoImpl implements TestRunDataDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestRunDataDaoImpl.class);

	private QTestRunData qTestRunData = QTestRunData.TestRunData;

	private QueryDslJdbcTemplate template;

	public void setdataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public class MappingTestRunDataProjection extends MappingProjection<TestRunData> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestRunDataProjection.class);

		public MappingTestRunDataProjection(Expression<?>... args) {
			super(TestRunData.class, args);
		}

		/**
		 * Serial version id
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected TestRunData map(Tuple tuple) {
			TestRunData data = new TestRunData();
			data.setTestRunDataId(getTestRunDataIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + data);
			}
			return data;
		}

		private TestRunDataId getTestRunDataIdMapping(Tuple tuple) {
			TestRunDataId id = new TestRunDataId();
			id.setTestRunDataId(new BigDecimal(tuple.get(qTestRunData.testRunDataId)));
			id.setTestStepId(new BigDecimal(tuple.get(qTestRunData.testStepID)));
			id.setTestCaseId(new BigDecimal(tuple.get(qTestRunData.testCaseID)));
			id.setTestPlanId(new BigDecimal(tuple.get(qTestRunData.testPlanID)));
			id.setTestSuiteId(new BigDecimal(tuple.get(qTestRunData.testSuiteID)));
			id.setCreatedBy(tuple.get(qTestRunData.createdBy));
			id.setCreatedDateTime(tuple.get(qTestRunData.createdDateTime));
			id.setStatus(tuple.get(qTestRunData.status));
			id.setUpdatedBy(tuple.get(qTestRunData.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTestRunData.updatedDateTime));
			id.setExceptionMsg(tuple.get(qTestRunData.exceptionMessage));
			return id;
		}

	}

	public TestRunData getTestRunDataById(int testRunDataId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestRunData).where(qTestRunData.testRunDataId.eq(testRunDataId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestRunDataProjection(qTestRunData.testRunDataId, qTestRunData.testPlanID,
					qTestRunData.testCaseID, qTestRunData.testStepID, qTestRunData.testSuiteID, qTestRunData.exceptionMessage, qTestRunData.status,
					qTestRunData.createdBy, qTestRunData.createdDateTime, qTestRunData.updatedBy, qTestRunData.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestRunData> getTestRunDataByTestCaseId(int testCaseId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestRunData).where(qTestRunData.testCaseID.eq(testCaseId));
			logger.info("generated query : " + sqlQuery);
			return getTestRunDataByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private List<TestRunData> getTestRunDataByQuery(SQLQuery sqlQuery) {
		return template.query(sqlQuery, new MappingTestRunDataProjection(qTestRunData.testRunDataId, qTestRunData.testPlanID, qTestRunData.testSuiteID,
				qTestRunData.exceptionMessage, qTestRunData.status, qTestRunData.createdBy, qTestRunData.createdDateTime, qTestRunData.updatedBy,
				qTestRunData.updatedDateTime, qTestRunData.testStepID, qTestRunData.testCaseID));
	}

	public List<TestRunData> getTestRunDataByPlanId(int testPlanId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestRunData).where(qTestRunData.testPlanID.eq(testPlanId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestRunDataProjection(qTestRunData.testRunDataId, qTestRunData.testPlanID, qTestRunData.testCaseID,
					qTestRunData.testStepID, qTestRunData.testSuiteID, qTestRunData.exceptionMessage, qTestRunData.status, qTestRunData.createdBy,
					qTestRunData.createdDateTime, qTestRunData.updatedBy, qTestRunData.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestRunDataDetails(final TestRunData testRunData) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testRunData.getTestRunDataId().getTestRunDataId());
		try {
			result = template.insert(qTestRunData, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestRunDataId id = testRunData.getTestRunDataId();
					return sqlInsertClause
							.columns(qTestRunData.testRunDataId, qTestRunData.createdBy, qTestRunData.createdDateTime, qTestRunData.exceptionMessage,
									qTestRunData.status, qTestRunData.testCaseID, qTestRunData.testPlanID, qTestRunData.testStepID,
									qTestRunData.testSuiteID, qTestRunData.updatedBy, qTestRunData.updatedDateTime)
							.values(id.getTestRunDataId(), id.getCreatedBy(), id.getCreatedDateTime(), id.getExceptionMsg(), id.getStatus(),
									id.getTestCaseId(), id.getTestPlanId(), id.getTestStepId(), id.getTestSuiteId(), id.getUpdatedBy(),
									id.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	@Override
	public int insertTestRunDataGetKey(final TestRunData testRunData) throws DataAccessException {
		int testRunDataId = 0;
		try {
			testRunDataId = template.insertWithKey(qTestRunData, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestRunDataId id = testRunData.getTestRunDataId();
					return insert
							.columns(qTestRunData.testRunDataId, qTestRunData.createdBy, qTestRunData.createdDateTime, qTestRunData.exceptionMessage,
									qTestRunData.status, qTestRunData.testCaseID, qTestRunData.testPlanID, qTestRunData.testStepID,
									qTestRunData.testSuiteID, qTestRunData.updatedBy, qTestRunData.updatedDateTime)
							.values(id.getTestRunDataId(), id.getCreatedBy(), id.getCreatedDateTime(), id.getExceptionMsg(), id.getStatus(),
									id.getTestCaseId(), id.getTestPlanId(), id.getTestStepId(), id.getTestSuiteId(), id.getUpdatedBy(),
									id.getUpdatedDateTime()).executeWithKey(qTestRunData.testRunDataId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testRunDataId;
	}

}
