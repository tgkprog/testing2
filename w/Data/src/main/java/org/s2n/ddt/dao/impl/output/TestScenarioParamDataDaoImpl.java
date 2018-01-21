package org.s2n.ddt.dao.impl.output;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;

import org.s2n.ddt.dao.output.TestScenarioParamDataDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestScenarioParamDataMap;
import org.s2n.ddt.pojo.output.TestscenarioParamdataMap;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestScenarioParamDataDaoImpl implements TestScenarioParamDataDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(TestScenarioParamDataDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestScenarioParamDataMap qTestScenarioParamMap = QTestScenarioParamDataMap.TestScenarioParamDataMap;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingTestScenarioParamDataProjection extends MappingProjection<TestscenarioParamdataMap> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestScenarioParamDataProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestScenarioParamDataProjection(Expression<?>... args) {
			super(TestscenarioParamdataMap.class, args);
		}

		@Override
		protected TestscenarioParamdataMap map(Tuple tuple) {
			TestscenarioParamdataMap map = new TestscenarioParamdataMap();
			map.setCreatedBy(tuple.get(qTestScenarioParamMap.createdBy));
			map.setScenarioParamDataId(new BigDecimal(tuple.get(qTestScenarioParamMap.testScenarioParamDataId)));
			map.setTestScenarioId(new BigDecimal(tuple.get(qTestScenarioParamMap.testScenarioId)));
			map.setCreatedDateTime(tuple.get(qTestScenarioParamMap.createdDateTime));
			map.setUpdatedBy(tuple.get(qTestScenarioParamMap.updatedBy));
			map.setUpdatedDateTime(tuple.get(qTestScenarioParamMap.updatedDateTime));
			map.setTestParamDataId(new BigDecimal(tuple.get(qTestScenarioParamMap.testParamDataId)));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + map);
			}
			return map;
		}

	}

	public TestscenarioParamdataMap getTestScenarioParamDataById(int testScenarioParamId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestScenarioParamMap)
					.where(qTestScenarioParamMap.testScenarioParamDataId.eq(testScenarioParamId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestScenarioParamDataProjection(qTestScenarioParamMap.testScenarioParamDataId,
					qTestScenarioParamMap.testScenarioId, qTestScenarioParamMap.testParamDataId, qTestScenarioParamMap.createdBy,
					qTestScenarioParamMap.createdDateTime, qTestScenarioParamMap.updatedBy, qTestScenarioParamMap.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestscenarioParamdataMap getTestscenarioParamdataMapbyScenarioId(int testScenarioId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestScenarioParamMap).where(qTestScenarioParamMap.testScenarioId.eq(testScenarioId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestScenarioParamDataProjection(qTestScenarioParamMap.testScenarioParamDataId,
					qTestScenarioParamMap.testScenarioId, qTestScenarioParamMap.testParamDataId, qTestScenarioParamMap.createdBy,
					qTestScenarioParamMap.createdDateTime, qTestScenarioParamMap.updatedBy, qTestScenarioParamMap.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestscenarioParamdataMap getTestscenarioParamdataMapbyParamDataId(int testParamDataId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestScenarioParamMap).where(qTestScenarioParamMap.testParamDataId.eq(testParamDataId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestScenarioParamDataProjection(qTestScenarioParamMap.testScenarioParamDataId,
					qTestScenarioParamMap.testScenarioId, qTestScenarioParamMap.testParamDataId, qTestScenarioParamMap.createdBy,
					qTestScenarioParamMap.createdDateTime, qTestScenarioParamMap.updatedBy, qTestScenarioParamMap.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestScenarioDetails(final TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testscenarioParamdataMap.getScenarioParamDataId());
		try {
			result = template.insert(qTestScenarioParamMap, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTestScenarioParamMap.testScenarioParamDataId, qTestScenarioParamMap.createdBy,
									qTestScenarioParamMap.createdDateTime, qTestScenarioParamMap.testParamDataId, qTestScenarioParamMap.testScenarioId,
									qTestScenarioParamMap.updatedBy, qTestScenarioParamMap.updatedDateTime)
							.values(testscenarioParamdataMap.getScenarioParamDataId(), testscenarioParamdataMap.getCreatedBy(),
									testscenarioParamdataMap.getCreatedDateTime(), testscenarioParamdataMap.getTestParamDataId(),
									testscenarioParamdataMap.getTestScenarioId(), testscenarioParamdataMap.getUpdatedBy(),
									testscenarioParamdataMap.getUpdatedDateTime()).execute();
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
	public int insertTestscenarioParamdataMapGetKey(final TestscenarioParamdataMap testscenarioParamdataMap) throws DataAccessException {
		int testScenarioParamDataId = 0;
		try {
			testScenarioParamDataId = template.insertWithKey(qTestScenarioParamMap, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTestScenarioParamMap.testScenarioParamDataId, qTestScenarioParamMap.createdBy,
									qTestScenarioParamMap.createdDateTime, qTestScenarioParamMap.testParamDataId, qTestScenarioParamMap.testScenarioId,
									qTestScenarioParamMap.updatedBy, qTestScenarioParamMap.updatedDateTime)
							.values(testscenarioParamdataMap.getScenarioParamDataId(), testscenarioParamdataMap.getCreatedBy(),
									testscenarioParamdataMap.getCreatedDateTime(), testscenarioParamdataMap.getTestParamDataId(),
									testscenarioParamdataMap.getTestScenarioId(), testscenarioParamdataMap.getUpdatedBy(),
									testscenarioParamdataMap.getUpdatedDateTime()).executeWithKey(qTestScenarioParamMap.testScenarioParamDataId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testScenarioParamDataId;
	}

}
