package org.s2n.ddt.dao.impl.input;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;

import org.s2n.ddt.dao.input.TestStepParamGroupMappingDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestStepParamGroupMapping;
import org.s2n.ddt.pojo.input.TestStepParamGroupMapping;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestStepParamGroupMappingDaoImpl implements TestStepParamGroupMappingDao {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestStepParamGroupMappingDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestStepParamGroupMapping qStepParamGroupMapping = QTestStepParamGroupMapping.TestStepParamGroupMapping;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	@Override
	public long insertTestStepParamGroupMappingDetails(final TestStepParamGroupMapping testStepParamGroupMapping) throws DataAccessException {
		long result = 0L;
		try {
			result = template.insert(qStepParamGroupMapping, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qStepParamGroupMapping.testStepParamGroupID, qStepParamGroupMapping.paramGroupID, qStepParamGroupMapping.testStepID,
									qStepParamGroupMapping.testCaseID, qStepParamGroupMapping.testScenarioId, qStepParamGroupMapping.createdBy,
									qStepParamGroupMapping.createdDateTime, qStepParamGroupMapping.updatedBy, qStepParamGroupMapping.updatedDateTime)
							.values(testStepParamGroupMapping.getTestStepParamGrpId(), testStepParamGroupMapping.getParamGroupId(),
									testStepParamGroupMapping.getTestStepId(), testStepParamGroupMapping.getTestCaseId(),
									testStepParamGroupMapping.getTestScenarioId(), testStepParamGroupMapping.getCreatedBy(),
									testStepParamGroupMapping.getCreatedDateTime(), testStepParamGroupMapping.getUpdatedBy(),
									testStepParamGroupMapping.getUpdatedDateTime()).execute();
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
	public TestStepParamGroupMapping getTestStepParamGroupMappingDetailsById(int testStepParamGroupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qStepParamGroupMapping)
					.where(qStepParamGroupMapping.testStepParamGroupID.eq(testStepParamGroupId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestStepParamGroupMappingProjection(qStepParamGroupMapping.testStepParamGroupID,
					qStepParamGroupMapping.testStepID, qStepParamGroupMapping.testScenarioId, qStepParamGroupMapping.testCaseID,
					qStepParamGroupMapping.paramGroupID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingTestStepParamGroupMappingProjection extends MappingProjection<TestStepParamGroupMapping> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestStepParamGroupMappingProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestStepParamGroupMappingProjection(Expression<?>... args) {
			super(TestStepParamGroupMapping.class, args);
		}

		@Override
		protected TestStepParamGroupMapping map(Tuple tuple) {
			if (qStepParamGroupMapping != null) {
				TestStepParamGroupMapping mapping = new TestStepParamGroupMapping();
				mapping.setTestStepParamGrpId(new BigDecimal(tuple.get(qStepParamGroupMapping.testStepParamGroupID)));
				mapping.setTestStepId(new BigDecimal(tuple.get(qStepParamGroupMapping.testStepID)));
				mapping.setTestScenarioId(new BigDecimal(tuple.get(qStepParamGroupMapping.testScenarioId)));
				mapping.setTestCaseId(new BigDecimal(tuple.get(qStepParamGroupMapping.testCaseID)));
				mapping.setParamGroupId(new BigDecimal(tuple.get(qStepParamGroupMapping.paramGroupID)));
				mapping.setCreatedBy(tuple.get(qStepParamGroupMapping.createdBy));
				mapping.setCreatedDateTime(tuple.get(qStepParamGroupMapping.createdDateTime));
				mapping.setUpdatedBy(tuple.get(qStepParamGroupMapping.updatedBy));
				mapping.setUpdatedDateTime(tuple.get(qStepParamGroupMapping.updatedDateTime));
				if (logger.isDebugEnabled()) {
					logger.debug("returning data object : " + mapping);
				}
				return mapping;
			}
			return null;
		}

	}

	@Override
	public List<TestStepParamGroupMapping> getRecordsByTestStepId(final int testStepId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qStepParamGroupMapping).where(qStepParamGroupMapping.testStepID.eq(testStepId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepParamGroupMappingProjection(qStepParamGroupMapping.testStepParamGroupID,
					qStepParamGroupMapping.paramGroupID, qStepParamGroupMapping.testStepID, qStepParamGroupMapping.testCaseID,
					qStepParamGroupMapping.testScenarioId, qStepParamGroupMapping.createdBy, qStepParamGroupMapping.createdDateTime,
					qStepParamGroupMapping.updatedBy, qStepParamGroupMapping.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public List<TestStepParamGroupMapping> getRecordsByParamGroupId(final int paramGroupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qStepParamGroupMapping).where(qStepParamGroupMapping.paramGroupID.eq(paramGroupId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepParamGroupMappingProjection(qStepParamGroupMapping.testStepParamGroupID,
					qStepParamGroupMapping.paramGroupID, qStepParamGroupMapping.testStepID, qStepParamGroupMapping.testCaseID,
					qStepParamGroupMapping.testScenarioId, qStepParamGroupMapping.createdBy, qStepParamGroupMapping.createdDateTime,
					qStepParamGroupMapping.updatedBy, qStepParamGroupMapping.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long deleteRecordsByStepId(final int testStepId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where ParamGroup id : " + testStepId);
		try {
			result = template.delete(qStepParamGroupMapping, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qStepParamGroupMapping.testStepID.eq(testStepId)).execute();
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
	public long deleteRecordsByParamGroupId(final int paramGroupId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where ParamGroup id : " + paramGroupId);
		try {
			result = template.delete(qStepParamGroupMapping, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qStepParamGroupMapping.paramGroupID.eq(paramGroupId)).execute();
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
	public int insertTestStepParamGroupMappingGetKey(final TestStepParamGroupMapping testStepParamGroupMapping) throws DataAccessException {
		int testStepParamGroupId = 0;
		try {
			testStepParamGroupId = template.insertWithKey(qStepParamGroupMapping, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qStepParamGroupMapping.testStepParamGroupID, qStepParamGroupMapping.paramGroupID, qStepParamGroupMapping.testStepID,
									qStepParamGroupMapping.testCaseID, qStepParamGroupMapping.testScenarioId, qStepParamGroupMapping.createdBy,
									qStepParamGroupMapping.createdDateTime, qStepParamGroupMapping.updatedBy, qStepParamGroupMapping.updatedDateTime)
							.values(testStepParamGroupMapping.getTestStepParamGrpId(), testStepParamGroupMapping.getParamGroupId(),
									testStepParamGroupMapping.getTestStepId(), testStepParamGroupMapping.getTestCaseId(),
									testStepParamGroupMapping.getTestScenarioId(), testStepParamGroupMapping.getCreatedBy(),
									testStepParamGroupMapping.getCreatedDateTime(), testStepParamGroupMapping.getUpdatedBy(),
									testStepParamGroupMapping.getUpdatedDateTime()).executeWithKey(qStepParamGroupMapping.testStepParamGroupID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testStepParamGroupId;
	}

}
