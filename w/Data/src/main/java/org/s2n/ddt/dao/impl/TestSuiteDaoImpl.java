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

import org.s2n.ddt.dao.TestSuiteDao;
import org.s2n.ddt.dao.impl.input.TestCaseDaoImpl;
import org.s2n.ddt.dao.input.TestCaseDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestSuite;
import org.s2n.ddt.pojo.TestSuite;
import org.s2n.ddt.pojo.TestSuiteId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestSuiteDaoImpl implements TestSuiteDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestCaseDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestSuite qTestSuite = QTestSuite.TestSuite;
	private TestCaseDao testCaseDao;

	public void setTestCaseDao(TestCaseDao testCaseDao) {
		this.testCaseDao = testCaseDao;
	}

	private class MappingTestSuiteProjection extends MappingProjection<TestSuite> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(TestCaseDaoImpl.class);

		private static final long serialVersionUID = 1L;

		public MappingTestSuiteProjection(Expression<?>... args) {
			super(TestSuite.class, args);
		}

		@Override
		protected TestSuite map(Tuple tuple) {
			TestSuite suite = new TestSuite();
			suite.setTestSuiteId(getTestSuiteIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + suite);
			}
			return suite;
		}

		private TestSuiteId getTestSuiteIdMapping(Tuple tuple) {
			TestSuiteId id = new TestSuiteId();
			id.setTestSuiteId(new BigDecimal(tuple.get(qTestSuite.testSuiteID)));
			id.setFeatureId(new BigDecimal(tuple.get(qTestSuite.featureId)));
			id.setFunctionalId(new BigDecimal(tuple.get(qTestSuite.functionalId)));
			id.setAppId(new BigDecimal(tuple.get(qTestSuite.appID)));
			id.setSuiteName(tuple.get(qTestSuite.suiteName));
			id.setDescription(tuple.get(qTestSuite.description));
			if (qTestSuite.orderBy != null && tuple.get(qTestSuite.orderBy) != null) {
				id.setOrderBy(Long.valueOf(tuple.get(qTestSuite.orderBy)));
			}
			id.setCreatedBy(tuple.get(qTestSuite.createdBy));
			id.setCreatedDateTime(tuple.get(qTestSuite.createdDateTime));
			id.setUpdatedBy(tuple.get(qTestSuite.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTestSuite.updatedDateTime));
			return id;
		}

	}

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public TestSuite getTestSuiteDetailsById(int testSuiteId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.testSuiteID.eq(testSuiteId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestSuiteProjection(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description,
					qTestSuite.appID, qTestSuite.functionalId, qTestSuite.featureId, qTestSuite.createdBy, qTestSuite.createdDateTime,
					qTestSuite.updatedBy, qTestSuite.updatedDateTime, qTestSuite.orderBy));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestSuite> getTestSuitesByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestSuiteProjection(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description,
					qTestSuite.appID, qTestSuite.functionalId, qTestSuite.featureId, qTestSuite.createdBy, qTestSuite.createdDateTime,
					qTestSuite.updatedBy, qTestSuite.updatedDateTime, qTestSuite.orderBy));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestSuite getTestSuiteWithTestCaseById(int testSuiteId) throws DataAccessException {
		try {
			TestSuite testSuite = getTestSuiteDetailsById(testSuiteId);
			testSuite.getTestSuiteId().setTestCases(testCaseDao.getTestSuiteDataByTestCaseId(testSuiteId));
			logger.info("returning data object : " + testSuite);
			return testSuite;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestSuite> getTestSuiteDetailsByFunctionalId(int functionalId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.functionalId.eq(functionalId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestSuiteProjection(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description,
					qTestSuite.appID, qTestSuite.functionalId, qTestSuite.featureId, qTestSuite.createdBy, qTestSuite.createdDateTime,
					qTestSuite.updatedBy, qTestSuite.updatedDateTime, qTestSuite.orderBy));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestSuite> getTestSuiteDetailsByFeatureId(int featureId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.featureId.eq(featureId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestSuiteProjection(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description,
					qTestSuite.appID, qTestSuite.functionalId, qTestSuite.featureId, qTestSuite.createdBy, qTestSuite.createdDateTime,
					qTestSuite.updatedBy, qTestSuite.updatedDateTime, qTestSuite.orderBy));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public TestSuite getTestCaseWithSuiteScenarioMappingById(int testSuiteId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.testSuiteID.eq(testSuiteId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestSuiteProjection(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description,
					qTestSuite.appID, qTestSuite.functionalId, qTestSuite.featureId, qTestSuite.createdBy, qTestSuite.createdDateTime,
					qTestSuite.updatedBy, qTestSuite.updatedDateTime, qTestSuite.orderBy));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public long insertTestSuiteDetails(final TestSuite suite) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for TestSuite: " + suite.getTestSuiteId().getTestSuiteId());
		try {
			result = template.insert(qTestSuite, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestSuiteId id = suite.getTestSuiteId();
					return sqlInsertClause
							.columns(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description, qTestSuite.appID, qTestSuite.featureId,
									qTestSuite.functionalId, qTestSuite.createdBy, qTestSuite.createdDateTime, qTestSuite.updatedBy,
									qTestSuite.updatedDateTime, qTestSuite.orderBy)
							.values(id.getTestSuiteId(), id.getSuiteName(), id.getDescription(), id.getAppId(), id.getFeatureId(), id.getFunctionalId(),
									id.getCreatedBy(), id.getCreatedDateTime(), id.getUpdatedBy(), id.getUpdatedDateTime(), id.getOrderBy()).execute();
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
	public long updateTestSuite(final TestSuite suite) throws DataAccessException {
		try {
			return template.update(qTestSuite, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qTestSuite.testSuiteID.eq(suite.getTestSuiteId().getTestSuiteId().intValue()))
							.set(qTestSuite.suiteName, suite.getTestSuiteId().getSuiteName())
							.set(qTestSuite.description, suite.getTestSuiteId().getDescription())
							.set(qTestSuite.orderBy, suite.getTestSuiteId().getOrderBy().intValue())
							.set(qTestSuite.updatedBy, suite.getTestSuiteId().getUpdatedBy())
							.set(qTestSuite.updatedDateTime, new Date(suite.getTestSuiteId().getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestSuiteDetailsByName(TestSuite suite) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTestSuite)
					.where(qTestSuite.appID.eq(suite.getTestSuiteId().getAppId().intValue())
							.and(qTestSuite.featureId.eq(suite.getTestSuiteId().getFeatureId().intValue()))
							.and(qTestSuite.functionalId.eq(suite.getTestSuiteId().getFunctionalId().intValue()))
							.and(qTestSuite.suiteName.eq(suite.getTestSuiteId().getSuiteName())));
			Integer testSuiteId = template.queryForObject(sqlQuery, qTestSuite.testSuiteID);
			if (testSuiteId != null) {
				return testSuiteId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public int getTestSuiteDetailsOnlyByName(String suiteName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestSuite).where(qTestSuite.suiteName.eq(suiteName));
			Integer suiteId = template.queryForObject(sqlQuery, qTestSuite.testSuiteID);
			if (suiteId != null) {
				return suiteId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertTestSuiteGetKey(final TestSuite testSuite) throws DataAccessException {
		int testSuiteId = 0;
		try {
			testSuiteId = template.insertWithKey(qTestSuite, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestSuiteId id = testSuite.getTestSuiteId();
					return insert
							.columns(qTestSuite.testSuiteID, qTestSuite.suiteName, qTestSuite.description, qTestSuite.appID, qTestSuite.featureId,
									qTestSuite.functionalId, qTestSuite.createdBy, qTestSuite.createdDateTime, qTestSuite.updatedBy,
									qTestSuite.updatedDateTime, qTestSuite.orderBy)
							.values(id.getTestSuiteId(), id.getSuiteName(), id.getDescription(), id.getAppId(), id.getFeatureId(), id.getFunctionalId(),
									id.getCreatedBy(), id.getCreatedDateTime(), id.getUpdatedBy(), id.getUpdatedDateTime(), id.getOrderBy())
							.executeWithKey(qTestSuite.testSuiteID);

				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testSuiteId;
	}

}
