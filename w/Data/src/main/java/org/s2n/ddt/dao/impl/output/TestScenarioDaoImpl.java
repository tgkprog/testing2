package org.s2n.ddt.dao.impl.output;

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

import org.s2n.ddt.dao.output.TestScenarioDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestScenario;
import org.s2n.ddt.pojo.output.TestScenario;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestScenarioDaoImpl implements TestScenarioDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(TestScenarioDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestScenario qTstScenario = QTestScenario.TestScenario;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingTestScenarioProjection extends MappingProjection<TestScenario> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestScenarioProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestScenarioProjection(Expression<?>... args) {
			super(TestScenario.class, args);
		}

		@Override
		protected TestScenario map(Tuple tuple) {
			TestScenario scenario = new TestScenario();
			scenario.setCreatedBy(tuple.get(qTstScenario.createdBy));
			scenario.setCreatedDateTime(tuple.get(qTstScenario.createdDateTime));
			scenario.setDescription(tuple.get(qTstScenario.description));
			if (tuple.get(qTstScenario.orderBy) != null){
				scenario.setOrderBy(Long.valueOf(tuple.get(qTstScenario.orderBy)));
			}
			scenario.setTestScenarioId(new BigDecimal(tuple.get(qTstScenario.testScenarioId)));
			scenario.setTestScenarioName(tuple.get(qTstScenario.testScenarioName));
			scenario.setUpdatedBy(tuple.get(qTstScenario.updatedBy));
			scenario.setUpdatedDateTime(tuple.get(qTstScenario.updatedDateTime));
			scenario.setAppId(new BigDecimal(tuple.get(qTstScenario.appID)));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + scenario);
			}
			return scenario;
		}

	}

	public TestScenario getTestScenarioDetailsById(int testScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstScenario).where(qTstScenario.testScenarioId.eq(testScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestScenarioProjection(qTstScenario.testScenarioId, qTstScenario.testScenarioName,
					qTstScenario.description, qTstScenario.createdBy, qTstScenario.createdDateTime, qTstScenario.updatedBy, qTstScenario.updatedDateTime,
					qTstScenario.orderBy, qTstScenario.appID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestScenario> getTestScenariosByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstScenario).where(qTstScenario.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestScenarioProjection(qTstScenario.testScenarioId, qTstScenario.testScenarioName,
					qTstScenario.description, qTstScenario.createdBy, qTstScenario.createdDateTime, qTstScenario.updatedBy, qTstScenario.updatedDateTime,
					qTstScenario.orderBy, qTstScenario.appID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestScenarioDetails(final TestScenario testScenario) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testScenario.getTestScenarioId());
		try {
			result = template.insert(qTstScenario, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTstScenario.testScenarioId, qTstScenario.appID, qTstScenario.createdBy, qTstScenario.createdDateTime,
									qTstScenario.description, qTstScenario.orderBy, qTstScenario.testScenarioName, qTstScenario.updatedBy,
									qTstScenario.updatedDateTime)
							.values(testScenario.getTestScenarioId(), testScenario.getAppId(), testScenario.getCreatedBy(),
									testScenario.getCreatedDateTime(), testScenario.getDescription(), testScenario.getOrderBy(),
									testScenario.getTestScenarioName(), testScenario.getUpdatedBy(), testScenario.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public int getTestScenarioByName(String testScenarioName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTstScenario).where(qTstScenario.testScenarioName.eq(testScenarioName));
			Integer testScenarioId = template.queryForObject(sqlQuery, qTstScenario.testScenarioId);
			if (testScenarioId != null) {
				return testScenarioId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertTestScenarioGetKey(final TestScenario testScenario) throws DataAccessException {
		int testScenarioId = 0;
		try {
			testScenarioId = template.insertWithKey(qTstScenario, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTstScenario.testScenarioId, qTstScenario.appID, qTstScenario.createdBy, qTstScenario.createdDateTime,
									qTstScenario.description, qTstScenario.orderBy, qTstScenario.testScenarioName, qTstScenario.updatedBy,
									qTstScenario.updatedDateTime)
							.values(testScenario.getTestScenarioId(), testScenario.getAppId(), testScenario.getCreatedBy(),
									testScenario.getCreatedDateTime(), testScenario.getDescription(), testScenario.getOrderBy(),
									testScenario.getTestScenarioName(), testScenario.getUpdatedBy(), testScenario.getUpdatedDateTime())
							.executeWithKey(qTstScenario.testScenarioId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testScenarioId;
	}

	@Override
	public long updateTestScenario(final TestScenario testScenario) throws DataAccessException {
		try {
			return template.update(qTstScenario, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qTstScenario.testScenarioId.eq(testScenario.getTestScenarioId().intValue()))
							.set(qTstScenario.description, testScenario.getDescription()).set(qTstScenario.appID, testScenario.getAppId().intValue())
							.set(qTstScenario.updatedBy, testScenario.getUpdatedBy())
							.set(qTstScenario.updatedDateTime, new Date(testScenario.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

}
