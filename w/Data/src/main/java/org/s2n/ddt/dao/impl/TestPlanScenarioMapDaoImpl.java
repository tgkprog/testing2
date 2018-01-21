package org.s2n.ddt.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.TestPlanScenarioMapDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestPlanTestScenarioMap;
import org.s2n.ddt.pojo.TestplanTestscenarioMap;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestPlanScenarioMapDaoImpl implements TestPlanScenarioMapDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestPlanScenarioMapDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestPlanTestScenarioMap qTestPlanTestScenarioMap = QTestPlanTestScenarioMap.TestPlanTestScenarioMap;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public TestplanTestscenarioMap getTestplanTestscenarioMapById(int testPlanScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlanTestScenarioMap)
					.where(qTestPlanTestScenarioMap.testPlanTestScenarioId.eq(testPlanScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new TestplanTestscenarioMapProjection(qTestPlanTestScenarioMap.testScenarioId,
					qTestPlanTestScenarioMap.testPlanId, qTestPlanTestScenarioMap.createdBy, qTestPlanTestScenarioMap.createdDateTime,
					qTestPlanTestScenarioMap.updatedBy, qTestPlanTestScenarioMap.updatedDateTime, qTestPlanTestScenarioMap.testPlanTestScenarioId));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public class TestplanTestscenarioMapProjection extends MappingProjection<TestplanTestscenarioMap> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(TestplanTestscenarioMapProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public TestplanTestscenarioMapProjection(Expression<?>... args) {
			super(TestplanTestscenarioMap.class, args);

		}

		@Override
		protected TestplanTestscenarioMap map(Tuple tuple) {
			TestplanTestscenarioMap map = new TestplanTestscenarioMap();
			map.setCreatedBy(tuple.get(qTestPlanTestScenarioMap.createdBy));
			map.setPlanScenarioId(new BigDecimal(tuple.get(qTestPlanTestScenarioMap.testPlanTestScenarioId)));
			map.setTestPlanId(new BigDecimal(tuple.get(qTestPlanTestScenarioMap.testPlanId)));
			map.setTestScenarioId(new BigDecimal(tuple.get(qTestPlanTestScenarioMap.testScenarioId)));
			map.setCreatedDateTime(tuple.get(qTestPlanTestScenarioMap.createdDateTime));
			map.setUpdatedDateTime(tuple.get(qTestPlanTestScenarioMap.updatedDateTime));
			map.setUpdatedBy(tuple.get(qTestPlanTestScenarioMap.updatedBy));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + map);
			}
			return map;
		}
	}

	public List<TestplanTestscenarioMap> getTestplanTestscenarioMapByPlanId(int testPlanId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlanTestScenarioMap).where(qTestPlanTestScenarioMap.testPlanId.eq(testPlanId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new TestplanTestscenarioMapProjection(qTestPlanTestScenarioMap.testScenarioId,
					qTestPlanTestScenarioMap.testPlanId, qTestPlanTestScenarioMap.createdBy, qTestPlanTestScenarioMap.createdDateTime,
					qTestPlanTestScenarioMap.updatedBy, qTestPlanTestScenarioMap.updatedDateTime, qTestPlanTestScenarioMap.testPlanTestScenarioId));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestplanTestscenarioMap> getTestplanTestscenarioMapbyScenarioId(int testScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlanTestScenarioMap).where(qTestPlanTestScenarioMap.testScenarioId.eq(testScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new TestplanTestscenarioMapProjection(qTestPlanTestScenarioMap.testScenarioId,
					qTestPlanTestScenarioMap.testPlanId, qTestPlanTestScenarioMap.createdBy, qTestPlanTestScenarioMap.createdDateTime,
					qTestPlanTestScenarioMap.updatedBy, qTestPlanTestScenarioMap.updatedDateTime, qTestPlanTestScenarioMap.testPlanTestScenarioId));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public long insertTestplanTestscenarioDetails(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for TestplanTestscenarioMap  : " + testplanTestscenarioMap.getPlanScenarioId());
		try {
			result = template.insert(qTestPlanTestScenarioMap, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTestPlanTestScenarioMap.testPlanTestScenarioId, qTestPlanTestScenarioMap.testPlanId,
									qTestPlanTestScenarioMap.testScenarioId, qTestPlanTestScenarioMap.createdBy, qTestPlanTestScenarioMap.createdDateTime,
									qTestPlanTestScenarioMap.updatedBy, qTestPlanTestScenarioMap.updatedDateTime)
							.values(testplanTestscenarioMap.getPlanScenarioId(), testplanTestscenarioMap.getTestPlanId(),
									testplanTestscenarioMap.getTestScenarioId(), testplanTestscenarioMap.getCreatedBy(),
									testplanTestscenarioMap.getCreatedDateTime(), testplanTestscenarioMap.getUpdatedBy(),
									testplanTestscenarioMap.getUpdatedDateTime()).execute();
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
	public long updateTestplanTestscenario(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		try {
			return template.update(qTestPlanTestScenarioMap, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause
							.where(qTestPlanTestScenarioMap.testPlanTestScenarioId.eq(testplanTestscenarioMap.getPlanScenarioId().intValue()))
							.set(qTestPlanTestScenarioMap.updatedBy, testplanTestscenarioMap.getUpdatedBy())
							.set(qTestPlanTestScenarioMap.updatedDateTime, new Date(testplanTestscenarioMap.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestplanTestscenarioByTestScenarioId(TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTestPlanTestScenarioMap)
					.where(qTestPlanTestScenarioMap.testPlanId.eq(testplanTestscenarioMap.getTestPlanId().intValue()).and(
							qTestPlanTestScenarioMap.testScenarioId.eq(testplanTestscenarioMap.getTestScenarioId().intValue())));
			Integer testPlanTestScenarioID = template.queryForObject(sqlQuery, qTestPlanTestScenarioMap.testPlanTestScenarioId);
			if (testPlanTestScenarioID != null) {
				return testPlanTestScenarioID;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertTestplanTestscenarioMapGetKey(final TestplanTestscenarioMap testplanTestscenarioMap) throws DataAccessException {
		int testPlanTestScenarioId = 0;
		try {
			testPlanTestScenarioId = template.insertWithKey(qTestPlanTestScenarioMap, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTestPlanTestScenarioMap.testPlanTestScenarioId, qTestPlanTestScenarioMap.testPlanId,
									qTestPlanTestScenarioMap.testScenarioId, qTestPlanTestScenarioMap.createdBy, qTestPlanTestScenarioMap.createdDateTime,
									qTestPlanTestScenarioMap.updatedBy, qTestPlanTestScenarioMap.updatedDateTime)
							.values(testplanTestscenarioMap.getPlanScenarioId(), testplanTestscenarioMap.getTestPlanId(),
									testplanTestscenarioMap.getTestScenarioId(), testplanTestscenarioMap.getCreatedBy(),
									testplanTestscenarioMap.getCreatedDateTime(), testplanTestscenarioMap.getUpdatedBy(),
									testplanTestscenarioMap.getUpdatedDateTime()).executeWithKey(qTestPlanTestScenarioMap.testPlanTestScenarioId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testPlanTestScenarioId;
	}

}
