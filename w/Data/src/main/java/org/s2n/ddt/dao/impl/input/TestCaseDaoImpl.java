package org.s2n.ddt.dao.impl.input;

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

import org.s2n.ddt.dao.input.TestCaseDao;
import org.s2n.ddt.dao.input.TestParamDataDao;
import org.s2n.ddt.dao.input.TestStepDao;
import org.s2n.ddt.dao.output.TestRunDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestCase;
import org.s2n.ddt.pojo.input.TestCase;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestCaseDaoImpl implements TestCaseDao {
	private static final Logger logger = Logger.getLogger(TestCaseDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private TestParamDataDao paramDataDao;
	private TestStepDao stepDao;
	private TestRunDataDao testRunDataDao;

	private static QTestCase qTestCase = QTestCase.TestCase;

	public void setParamDataDao(TestParamDataDao paramDataDao) {
		this.paramDataDao = paramDataDao;
	}

	public void setStepDao(TestStepDao stepDao) {
		this.stepDao = stepDao;
	}

	public void setTestRunDataDao(TestRunDataDao testRunDataDao) {
		this.testRunDataDao = testRunDataDao;
	}

	public void setDataSource(DataSource datasource) {
		this.template = new QueryDslJdbcTemplate(datasource);
	}

	public long insertTestCaseDetails(final TestCase testCase) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for testcase id : " + testCase.getTestCaseId());
		try {
			result = template.insert(qTestCase, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTestCase.testCaseID, qTestCase.classificationTag, qTestCase.caseName, qTestCase.runnerId, qTestCase.description,
									qTestCase.active, qTestCase.orderBy, qTestCase.testSuiteID, qTestCase.createdBy, qTestCase.createdDateTime,
									qTestCase.updatedBy, qTestCase.updatedDateTime)
							.values(testCase.getTestCaseId(), testCase.getClassificationTag(), testCase.getCaseName(), testCase.getRunnerId(),
									testCase.getDescription(), testCase.getActive(), testCase.getOrderBy(), testCase.getTestSuiteId(),
									testCase.getCreatedBy(), testCase.getCreatedDateTime(), testCase.getUpdatedBy(), testCase.getUpdatedDateTime())
							.execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingTestCaseProjection extends MappingProjection<TestCase> {
		private final Logger logger = Logger.getLogger(MappingTestCaseProjection.class);

		private static final long serialVersionUID = 1L;

		public MappingTestCaseProjection(Expression<?>... args) {
			super(TestCase.class, args);
		}

		@Override
		protected TestCase map(Tuple tuple) {
			TestCase testCase = new TestCase();
			testCase.setTestCaseId(new BigDecimal(tuple.get(qTestCase.testCaseID)));
			testCase.setCaseName(tuple.get(qTestCase.caseName));
			testCase.setDescription(tuple.get(qTestCase.description));
			testCase.setClassificationTag(tuple.get(qTestCase.classificationTag));
			testCase.setRunnerId(new BigDecimal(tuple.get(qTestCase.runnerId)));
			testCase.setActive(tuple.get(qTestCase.active));
			testCase.setPositive(tuple.get(qTestCase.positive));
			testCase.setOrderBy(Long.valueOf(tuple.get(qTestCase.orderBy)));
			testCase.setTestSuiteId(new BigDecimal(tuple.get(qTestCase.testSuiteID)));
			testCase.setCreatedBy(tuple.get(qTestCase.createdBy));
			testCase.setCreatedDateTime(tuple.get(qTestCase.createdDateTime));
			testCase.setUpdatedBy(tuple.get(qTestCase.updatedBy));
			testCase.setUpdatedDateTime(tuple.get(qTestCase.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + testCase);
			}
			return testCase;
		}
	}

	public TestCase getTestCaseDetailsById(int testCaseId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestCase).where(qTestCase.testCaseID.eq(testCaseId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestCaseProjection(qTestCase.testCaseID, qTestCase.caseName, qTestCase.description,
					qTestCase.testSuiteID, qTestCase.runnerId, qTestCase.classificationTag, qTestCase.active, qTestCase.orderBy, qTestCase.createdBy,
					qTestCase.createdDateTime, qTestCase.updatedBy, qTestCase.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestCase getTestCaseWithStepsById(int testCaseId) throws DataAccessException {
		try {
			TestCase testCase = getTestCaseDetailsById(testCaseId);
			testCase.setTestSteps(stepDao.getTestStepsByCaseId(testCaseId));
			logger.debug("returning data object : " + testCase);
			return testCase;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestCase getTestCaseWithTestParamDataById(int testCaseId) throws DataAccessException {
		try {
			TestCase testCase = getTestCaseDetailsById(testCaseId);
			testCase.setTestParamDatas(paramDataDao.getTestParamDataByTestCaseId(testCaseId));
			logger.debug("returning data object : " + testCase);
			return testCase;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestCase getTestCaseWithparamgroupsById(int testCaseId) throws DataAccessException {
		try {
			TestCase testCase = getTestCaseDetailsById(testCaseId);
			testCase.setTestParamDatas(paramDataDao.getTestParamDataByTestCaseId(testCaseId));
			logger.debug("returning data object : " + testCase);
			return testCase;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestCase getTestCaseWithTestStepsById(int testCaseId) throws DataAccessException {
		try {
			TestCase testCase = getTestCaseDetailsById(testCaseId);
			testCase.setTestSteps(stepDao.getTestStepsByCaseId(testCaseId));
			logger.debug("returning data object : " + testCase);
			return testCase;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestCase getTestCaseWithTestRunDatasById(int testCaseId) throws DataAccessException {
		try {
			TestCase testCase = getTestCaseDetailsById(testCaseId);
			testCase.setTestRunDatas(testRunDataDao.getTestRunDataByTestCaseId(testCaseId));
			logger.debug("returning data object : " + testCase);
			return testCase;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestCase> getTestSuiteDataByTestCaseId(int testSuiteId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestCase).where(qTestCase.testSuiteID.eq(testSuiteId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestCaseProjection(qTestCase.testCaseID, qTestCase.runnerId, qTestCase.classificationTag,
					qTestCase.caseName, qTestCase.description, qTestCase.active, qTestCase.positive, qTestCase.orderBy, qTestCase.createdDateTime,
					qTestCase.updatedBy, qTestCase.updatedDateTime, qTestCase.testSuiteID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestCase> getTestCaseDetailsByRunnerId(int runnerId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestCase).where(qTestCase.runnerId.eq(runnerId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestCaseProjection(qTestCase.testCaseID, qTestCase.runnerId, qTestCase.classificationTag,
					qTestCase.caseName, qTestCase.testSuiteID, qTestCase.description, qTestCase.active, qTestCase.positive, qTestCase.orderBy,
					qTestCase.createdDateTime, qTestCase.updatedBy, qTestCase.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getTestCaseIdByRunnerId(TestCase testCase) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTestCase)
					.where(qTestCase.orderBy.eq(testCase.getOrderBy().intValue()).and(qTestCase.testSuiteID.eq(testCase.getTestSuiteId().intValue()))
							.and(qTestCase.runnerId.eq(testCase.getRunnerId().intValue())));
			Integer TestcaseId = template.queryForObject(sqlQuery, qTestCase.testCaseID);
			if (TestcaseId != null) {
				return TestcaseId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long updateTestCase(final TestCase testCase) throws DataAccessException {
		try {
			return template.update(qTestCase, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause
							.where(qTestCase.testCaseID.eq(testCase.getTestCaseId().intValue())
									.and(qTestCase.testSuiteID.eq(testCase.getTestSuiteId().intValue()))
									.and(qTestCase.runnerId.eq(testCase.getRunnerId().intValue()))).set(qTestCase.description, testCase.getDescription())
							.set(qTestCase.orderBy, testCase.getOrderBy().intValue()).set(qTestCase.updatedBy, testCase.getUpdatedBy())
							.set(qTestCase.updatedDateTime, new Date(testCase.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestCaseDetailsOnlyByName(String testCaseName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestCase).where(qTestCase.caseName.eq(testCaseName));
			Integer testCaseId = template.queryForObject(sqlQuery, qTestCase.testCaseID);
			if (testCaseId != null) {
				return testCaseId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertTestCaseGetKey(final TestCase testCase) throws DataAccessException {
		int testCaseId = 0;
		try {
			testCaseId = template.insertWithKey(qTestCase, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTestCase.testCaseID, qTestCase.classificationTag, qTestCase.caseName, qTestCase.runnerId, qTestCase.description,
									qTestCase.active, qTestCase.orderBy, qTestCase.testSuiteID, qTestCase.createdBy, qTestCase.createdDateTime,
									qTestCase.updatedBy, qTestCase.updatedDateTime)
							.values(testCase.getTestCaseId(), testCase.getClassificationTag(), testCase.getCaseName(), testCase.getRunnerId(),
									testCase.getDescription(), testCase.getActive(), testCase.getOrderBy(), testCase.getTestSuiteId(),
									testCase.getCreatedBy(), testCase.getCreatedDateTime(), testCase.getUpdatedBy(), testCase.getUpdatedDateTime())
							.executeWithKey(qTestCase.testCaseID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testCaseId;
	}

}
