package org.s2n.ddt.dao.impl.input;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.input.TestCondDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestCondData;
import org.s2n.ddt.pojo.input.TestCondData;
import org.s2n.ddt.pojo.input.TestCondDataId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestCondDataDaoImpl implements TestCondDataDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestCondDataDaoImpl.class);

	private QueryDslJdbcTemplate template;
	QTestCondData qTestCondData = QTestCondData.TestCondData;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertTestCondDataDetails(final TestCondData testCondData) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for testCondData id : " + testCondData.getTestCondDataId().getTestCondDataId());
		try {
			result = template.insert(qTestCondData, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestCondDataId id = testCondData.getTestCondDataId();
					return sqlInsertClause
							.columns(qTestCondData.testCondDataId, qTestCondData.conditionId, qTestCondData.testPlanID, qTestCondData.paramId,
									qTestCondData.paramValue, qTestCondData.createdBy, qTestCondData.createdDateTime, qTestCondData.updatedBy,
									qTestCondData.updatedDateTime)
							.values(id.getTestCondDataId(), id.getConditionId(), id.getTestPlanId(), id.getParamId(), id.getParamValue(),
									id.getCreatedBy(), id.getCreatedDateTime(), id.getUpdatedBy(), id.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public class MappingTestCondDataProjection extends MappingProjection<TestCondData> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestCondDataProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestCondDataProjection(Expression<?>... args) {
			super(TestCondData.class, args);

		}

		@Override
		protected TestCondData map(Tuple tuple) {
			TestCondData data = new TestCondData();
			data.setTestCondDataId(getTestCondDataIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + data);
			}
			return data;
		}

		private TestCondDataId getTestCondDataIdMapping(Tuple tuple) {
			TestCondDataId id = new TestCondDataId();
			id.setTestCondDataId(new BigDecimal(tuple.get(qTestCondData.testCondDataId)));
			id.setTestPlanId(new BigDecimal(tuple.get(qTestCondData.testPlanID)));
			id.setConditionId(new BigDecimal(tuple.get(qTestCondData.conditionId)));
			id.setParamId(new BigDecimal(tuple.get(qTestCondData.paramId)));
			id.setParamValue(tuple.get(qTestCondData.paramValue));
			id.setCreatedBy(tuple.get(qTestCondData.createdBy));
			id.setCreatedDateTime(tuple.get(qTestCondData.createdDateTime));
			id.setUpdatedBy(tuple.get(qTestCondData.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTestCondData.updatedDateTime));
			return id;
		}
	}

	public TestCondData getTestCondDataById(int testCondDataId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestCondData).where(qTestCondData.testCondDataId.eq(testCondDataId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestCondDataProjection(qTestCondData.testCondDataId, qTestCondData.testPlanID,
					qTestCondData.conditionId, qTestCondData.paramId, qTestCondData.paramValue, qTestCondData.createdBy, qTestCondData.createdDateTime,
					qTestCondData.updatedBy, qTestCondData.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getTestCondDataByCondId(TestCondData testCondData) throws DataAccessException {
		try {
			TestCondDataId id = testCondData.getTestCondDataId();
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTestCondData)
					.where(qTestCondData.conditionId.eq(id.getConditionId().intValue()).and(
							qTestCondData.paramId.eq(id.getParamId().intValue()).and(qTestCondData.paramValue.eq(id.getParamValue()))
									.and(qTestCondData.testPlanID.eq(id.getTestPlanId().intValue()))));
			Integer CondDataId = template.queryForObject(sqlQuery, qTestCondData.testCondDataId);
			if (CondDataId != null) {
				return CondDataId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateCondData(final TestCondData testCondData) throws DataAccessException {
		try {
			return template.update(qTestCondData, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					TestCondDataId id = testCondData.getTestCondDataId();
					return sqlUpdateClause.where(qTestCondData.testCondDataId.eq(id.getTestCondDataId().intValue()))
							.set(qTestCondData.updatedBy, id.getUpdatedBy())
							.set(qTestCondData.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertTestCondDataGetKey(final TestCondData testCondData) throws DataAccessException {
		int testCondDataId = 0;
		try {
			testCondDataId = template.insertWithKey(qTestCondData, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestCondDataId id = testCondData.getTestCondDataId();
					return insert
							.columns(qTestCondData.testCondDataId, qTestCondData.conditionId, qTestCondData.testPlanID, qTestCondData.paramId,
									qTestCondData.paramValue, qTestCondData.createdBy, qTestCondData.createdDateTime, qTestCondData.updatedBy,
									qTestCondData.updatedDateTime)
							.values(id.getTestCondDataId(), id.getConditionId(), id.getTestPlanId(), id.getParamId(), id.getParamValue(),
									id.getCreatedBy(), id.getCreatedDateTime(), id.getUpdatedBy(), id.getUpdatedDateTime())
							.executeWithKey(qTestCondData.testCondDataId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testCondDataId;
	}

}
