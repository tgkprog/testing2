package org.s2n.ddt.dao.impl.input;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.input.TestParamDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestParamData;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.input.TestParamDataId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestParamDataDaoImpl implements TestParamDataDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(TestParamDataDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestParamData qTstParamData = QTestParamData.TestParamData;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertTestParamDataDetails(final TestParamData testParamData) throws DataAccessException {
		long result = 0L;
		try {
			result = template.insert(qTstParamData, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestParamDataId testParamDataId = testParamData.getTestParamDataId();
					return sqlInsertClause
							.columns(qTstParamData.testParamDataId, qTstParamData.testCaseID, qTstParamData.testStepID, qTstParamData.appID,
									qTstParamData.paramId, qTstParamData.paramValue, qTstParamData.valueBig, qTstParamData.createdBy,
									qTstParamData.createdDateTime, qTstParamData.updatedBy, qTstParamData.updatedDateTime)
							.values(testParamDataId.getTestParamDataId(), testParamDataId.getTestCaseId(), testParamDataId.getTestStepId(),
									testParamDataId.getAppId(), testParamDataId.getParamId(), testParamDataId.getParamValue(),
									testParamDataId.getValueBig(), testParamDataId.getCreatedBy(), testParamDataId.getCreatedDateTime(),
									testParamDataId.getUpdatedBy(), testParamDataId.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingTestParamDataProjection extends MappingProjection<TestParamData> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestParamDataProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestParamDataProjection(Expression<?>... args) {
			super(TestParamData.class, args);
		}

		@Override
		protected TestParamData map(Tuple tuple) {
			TestParamData data = new TestParamData();
			data.setTestParamDataId(getTestParamDataIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + data);
			}
			return data;
		}

		private TestParamDataId getTestParamDataIdMapping(Tuple tuple) {
			TestParamDataId id = new TestParamDataId();
			id.setTestParamDataId(new BigDecimal(tuple.get(qTstParamData.testParamDataId)));
			id.setTestCaseId(new BigDecimal(tuple.get(qTstParamData.testCaseID)));
			id.setTestStepId(new BigDecimal(tuple.get(qTstParamData.testStepID)));
			id.setParamId(new BigDecimal(tuple.get(qTstParamData.paramId)));
			id.setParamValue(tuple.get(qTstParamData.paramValue));
			id.setCreatedBy(tuple.get(qTstParamData.createdBy));
			id.setCreatedDateTime(tuple.get(qTstParamData.createdDateTime));
			id.setUpdatedBy(tuple.get(qTstParamData.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTstParamData.updatedDateTime));
			id.setValueBig(tuple.get(qTstParamData.valueBig));
			id.setTestSet(tuple.get(qTstParamData.testSet));
			id.setAppId(new BigDecimal(tuple.get(qTstParamData.appID)));
			return id;
		}
	}

	public TestParamData getTestParamDataDetailsById(int TestParamDataId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstParamData).where(qTstParamData.testParamDataId.eq(TestParamDataId));
			logger.info("Generatewd query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestParamDataProjection(qTstParamData.testParamDataId, qTstParamData.testStepID,
					qTstParamData.testCaseID, qTstParamData.appID, qTstParamData.paramId, qTstParamData.paramValue, qTstParamData.valueBig,
					qTstParamData.createdBy, qTstParamData.createdDateTime, qTstParamData.updatedBy, qTstParamData.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestParamData> getTestParamDataByStepId(int testStepId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstParamData).where(qTstParamData.testStepID.eq(testStepId));
			logger.info("Generatewd query : " + sqlQuery);
			return getTestParamDatasByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestParamData> getTestParamDatasByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstParamData).where(qTstParamData.appID.eq(appId));
			logger.info("Generatewd query : " + sqlQuery);
			return getTestParamDatasByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private List<TestParamData> getTestParamDatasByQuery(SQLQuery sqlQuery) {
		return template.query(sqlQuery, new MappingTestParamDataProjection(qTstParamData.testParamDataId, qTstParamData.testStepID,
				qTstParamData.testCaseID, qTstParamData.paramId, qTstParamData.appID, qTstParamData.paramValue, qTstParamData.valueBig,
				qTstParamData.createdBy, qTstParamData.createdDateTime, qTstParamData.updatedBy, qTstParamData.updatedDateTime));
	}

	public List<TestParamData> getTestParamDataByTestCaseId(int testCaseId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstParamData).where(qTstParamData.testCaseID.eq(testCaseId));
			logger.info("generated query : " + sqlQuery);
			return getTestParamDatasByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestParamData> getTestParamDataDetailsByParamId(int paramid) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstParamData).where(qTstParamData.paramId.eq(paramid));
			logger.info("Generatewd query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestParamDataProjection(qTstParamData.testParamDataId, qTstParamData.testStepID,
					qTstParamData.testCaseID, qTstParamData.paramId, qTstParamData.paramValue, qTstParamData.valueBig, qTstParamData.createdBy,
					qTstParamData.createdDateTime, qTstParamData.updatedBy, qTstParamData.updatedDateTime, qTstParamData.appID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestParamDataIdByAppId(TestParamData testParamData) throws DataAccessException {
		try {
			TestParamDataId id = testParamData.getTestParamDataId();
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTstParamData)
					.where(qTstParamData.appID.eq(id.getAppId().intValue()).and(qTstParamData.testCaseID.eq(id.getTestCaseId().intValue()))
							.and(qTstParamData.paramId.eq(id.getParamId().intValue())).and(qTstParamData.paramValue.eq(id.getParamValue()))
							.and(qTstParamData.testStepID.eq(id.getTestStepId().intValue())));
			Integer ParamDataId = template.queryForObject(sqlQuery, qTstParamData.testParamDataId);
			if (ParamDataId != null) {
				return ParamDataId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long updateParamData(final TestParamData testParamData) throws DataAccessException {
		try {
			return template.update(qTstParamData, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					TestParamDataId id = testParamData.getTestParamDataId();
					return sqlUpdateClause.where(qTstParamData.testParamDataId.eq(id.getTestParamDataId().intValue()))
							.set(qTstParamData.testSet, id.getTestSet()).set(qTstParamData.updatedBy, id.getUpdatedBy())
							.set(qTstParamData.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long deleteTestParamDataByStepId(final int testStepId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where testStep id : " + testStepId);
		try {
			result = template.delete(qTstParamData, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qTstParamData.testStepID.eq(testStepId)).execute();
				}
			});
			logger.info("number of rows deleted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	@Override
	public int insertTestParamDataGetKey(final TestParamData testParamData) throws DataAccessException {
		int testParamDataId = 0;
		try {
			testParamDataId = template.insertWithKey(qTstParamData, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestParamDataId testParamDataId = testParamData.getTestParamDataId();
					return insert
							.columns(qTstParamData.testParamDataId, qTstParamData.testCaseID, qTstParamData.testStepID, qTstParamData.appID,
									qTstParamData.paramId, qTstParamData.paramValue, qTstParamData.valueBig, qTstParamData.createdBy,
									qTstParamData.createdDateTime, qTstParamData.updatedBy, qTstParamData.updatedDateTime)
							.values(testParamDataId.getTestParamDataId(), testParamDataId.getTestCaseId(), testParamDataId.getTestStepId(),
									testParamDataId.getAppId(), testParamDataId.getParamId(), testParamDataId.getParamValue(),
									testParamDataId.getValueBig(), testParamDataId.getCreatedBy(), testParamDataId.getCreatedDateTime(),
									testParamDataId.getUpdatedBy(), testParamDataId.getUpdatedDateTime()).executeWithKey(qTstParamData.testParamDataId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testParamDataId;
	}

}
