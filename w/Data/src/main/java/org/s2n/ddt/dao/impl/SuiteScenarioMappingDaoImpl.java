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

import org.s2n.ddt.dao.SuiteScenarioMappingDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QSuiteScenarioMapping;
import org.s2n.ddt.pojo.SuiteScenarioMapping;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for SuiteScenarioMappingDao
 */
public class SuiteScenarioMappingDaoImpl implements SuiteScenarioMappingDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SuiteScenarioMappingDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QSuiteScenarioMapping qSuiteScenarioMapping = QSuiteScenarioMapping.SuiteScenarioMapping;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public SuiteScenarioMapping getSuiteScenarioMappingById(int suiteScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qSuiteScenarioMapping).where(qSuiteScenarioMapping.suiteScenarioId.eq(suiteScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingSuiteScenarioProjection(qSuiteScenarioMapping.suiteScenarioId,
					qSuiteScenarioMapping.createdBy, qSuiteScenarioMapping.createdDateTime, qSuiteScenarioMapping.testScenarioId,
					qSuiteScenarioMapping.testSuiteId, qSuiteScenarioMapping.updatedBy, qSuiteScenarioMapping.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingSuiteScenarioProjection extends MappingProjection<SuiteScenarioMapping> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingSuiteScenarioProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingSuiteScenarioProjection(Expression<?>... args) {
			super(SuiteScenarioMapping.class, args);
		}

		@Override
		protected SuiteScenarioMapping map(Tuple tuple) {
			SuiteScenarioMapping map = new SuiteScenarioMapping();
			map.setCreatedBy(tuple.get(qSuiteScenarioMapping.createdBy));
			map.setTestSuiteId(new BigDecimal(tuple.get(qSuiteScenarioMapping.testSuiteId)));
			map.setSuiteScenarioId(new BigDecimal(tuple.get(qSuiteScenarioMapping.suiteScenarioId)));
			map.setTestScenarioId(new BigDecimal(tuple.get(qSuiteScenarioMapping.testScenarioId)));
			map.setCreatedDateTime(tuple.get(qSuiteScenarioMapping.createdDateTime));
			map.setUpdatedBy(tuple.get(qSuiteScenarioMapping.updatedBy));
			map.setUpdatedDateTime(tuple.get(qSuiteScenarioMapping.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + map);
			}
			return map;
		}
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappingBytestScenarioId(int testScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qSuiteScenarioMapping).where(qSuiteScenarioMapping.testScenarioId.eq(testScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingSuiteScenarioProjection(qSuiteScenarioMapping.suiteScenarioId, qSuiteScenarioMapping.createdBy,
					qSuiteScenarioMapping.createdDateTime, qSuiteScenarioMapping.testScenarioId, qSuiteScenarioMapping.testSuiteId,
					qSuiteScenarioMapping.updatedBy, qSuiteScenarioMapping.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<SuiteScenarioMapping> getSuiteScenarioMappingBytestSuiteId(int testSuiteId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qSuiteScenarioMapping).where(qSuiteScenarioMapping.testSuiteId.eq(testSuiteId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingSuiteScenarioProjection(qSuiteScenarioMapping.suiteScenarioId, qSuiteScenarioMapping.createdBy,
					qSuiteScenarioMapping.createdDateTime, qSuiteScenarioMapping.testScenarioId, qSuiteScenarioMapping.testSuiteId,
					qSuiteScenarioMapping.updatedBy, qSuiteScenarioMapping.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getSuiteScenarioId(SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qSuiteScenarioMapping)
					.where(qSuiteScenarioMapping.testSuiteId.eq(suiteScenarioMapping.getTestSuiteId().intValue()).and(
							qSuiteScenarioMapping.testScenarioId.eq(suiteScenarioMapping.getTestScenarioId().intValue())));
			Integer suiteScenarioId = template.queryForObject(sqlQuery, qSuiteScenarioMapping.suiteScenarioId);
			if (suiteScenarioId != null) {
				return suiteScenarioId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long updateSuiteScenarioMapping(final SuiteScenarioMapping scenarioMapping) throws DataAccessException {
		try {
			return template.update(qSuiteScenarioMapping, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qSuiteScenarioMapping.suiteScenarioId.eq(scenarioMapping.getSuiteScenarioId().intValue()))
							.set(qSuiteScenarioMapping.updatedBy, scenarioMapping.getUpdatedBy())
							.set(qSuiteScenarioMapping.updatedDateTime, new Date(scenarioMapping.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public long insertSuiteScenarioMappingDetails(final SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for SuiteScenarioMapping  : " + suiteScenarioMapping.getSuiteScenarioId());
		try {
			result = template.insert(qSuiteScenarioMapping, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qSuiteScenarioMapping.suiteScenarioId, qSuiteScenarioMapping.testSuiteId, qSuiteScenarioMapping.testScenarioId,
									qSuiteScenarioMapping.createdBy, qSuiteScenarioMapping.createdDateTime, qSuiteScenarioMapping.updatedBy,
									qSuiteScenarioMapping.updatedDateTime)
							.values(suiteScenarioMapping.getSuiteScenarioId(), suiteScenarioMapping.getTestSuiteId(),
									suiteScenarioMapping.getTestScenarioId(), suiteScenarioMapping.getCreatedBy(),
									suiteScenarioMapping.getCreatedDateTime(), suiteScenarioMapping.getUpdatedBy(),
									suiteScenarioMapping.getUpdatedDateTime()).execute();
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
	public int insertSuiteScenarioMappingGetKey(final SuiteScenarioMapping suiteScenarioMapping) throws DataAccessException {
		int suiteScenarioId = 0;
		try {
			suiteScenarioId = template.insertWithKey(qSuiteScenarioMapping, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qSuiteScenarioMapping.suiteScenarioId, qSuiteScenarioMapping.testSuiteId, qSuiteScenarioMapping.testScenarioId,
									qSuiteScenarioMapping.createdBy, qSuiteScenarioMapping.createdDateTime, qSuiteScenarioMapping.updatedBy,
									qSuiteScenarioMapping.updatedDateTime)
							.values(suiteScenarioMapping.getSuiteScenarioId(), suiteScenarioMapping.getTestSuiteId(),
									suiteScenarioMapping.getTestScenarioId(), suiteScenarioMapping.getCreatedBy(),
									suiteScenarioMapping.getCreatedDateTime(), suiteScenarioMapping.getUpdatedBy(),
									suiteScenarioMapping.getUpdatedDateTime()).executeWithKey(qSuiteScenarioMapping.suiteScenarioId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return suiteScenarioId;
	}

}
