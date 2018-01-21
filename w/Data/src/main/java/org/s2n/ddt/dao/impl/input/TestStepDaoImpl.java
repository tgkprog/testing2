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

import org.s2n.ddt.dao.impl.output.TestScenarioParamDataDaoImpl;
import org.s2n.ddt.dao.input.TestStepDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestStep;
import org.s2n.ddt.pojo.input.TestStep;
import org.s2n.ddt.pojo.input.TestStepId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestStepDaoImpl implements TestStepDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(TestScenarioParamDataDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestStep qTeststep = QTestStep.TestStep;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertTestStepDetails(final TestStep testStep) throws DataAccessException {
		long result = 0L;
		try {
			result = template.insert(qTeststep, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestStepId testStepId = testStep.getTestStepId();
					return sqlInsertClause
							.columns(qTeststep.testStepID, qTeststep.stepName, qTeststep.description, qTeststep.testStepType, qTeststep.actionId,
									qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition, qTeststep.inputParam,
									qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
									qTeststep.updatedBy, qTeststep.updatedDateTime)
							.values(testStepId.getTestStepId(), testStepId.getStepName(), testStepId.getDescription(), testStepId.getTestStepType(),
									testStepId.getActionsId(), testStepId.getActive(), testStepId.getOrderBy(), testStepId.getPostConditionGroupId(),
									testStepId.getPreConditionGroupId(), testStepId.getInputParamGroupId(), testStepId.getOutputParamGroupId(),
									testStepId.getTestCaseId(), testStepId.getRunnerId(), testStepId.getCreatedBy(), testStepId.getCreatedDateTime(),
									testStepId.getUpdatedBy(), testStepId.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public int insertTestStepGetKey(final TestStep testStep) throws DataAccessException {
		int testStepId = 0;
		try {
			testStepId = template.insertWithKey(qTeststep, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestStepId testStepId = testStep.getTestStepId();
					return insert
							.columns(qTeststep.testStepID, qTeststep.stepName, qTeststep.description, qTeststep.testStepType, qTeststep.actionId,
									qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition, qTeststep.inputParam,
									qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
									qTeststep.updatedBy, qTeststep.updatedDateTime)
							.values(testStepId.getTestStepId(), testStepId.getStepName(), testStepId.getDescription(), testStepId.getTestStepType(),
									testStepId.getActionsId(), testStepId.getActive(), testStepId.getOrderBy(), testStepId.getPostConditionGroupId(),
									testStepId.getPreConditionGroupId(), testStepId.getInputParamGroupId(), testStepId.getOutputParamGroupId(),
									testStepId.getTestCaseId(), testStepId.getRunnerId(), testStepId.getCreatedBy(), testStepId.getCreatedDateTime(),
									testStepId.getUpdatedBy(), testStepId.getUpdatedDateTime()).executeWithKey(qTeststep.testStepID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testStepId;
	}

	private class MappingTestStepProjection extends MappingProjection<TestStep> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(TestScenarioParamDataDaoImpl.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTestStepProjection(Expression<?>... args) {
			super(TestStep.class, args);
		}

		@Override
		protected TestStep map(Tuple tuple) {
			TestStep step = new TestStep();
			step.setTestStepId(getTestStepIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + step);
			}
			return step;
		}

		private TestStepId getTestStepIdMapping(Tuple tuple) {
			TestStepId id = new TestStepId();
			id.setTestStepId(new BigDecimal(tuple.get(qTeststep.testStepID)));
			if ((qTeststep.postCondition != null) && (tuple.get(qTeststep.postCondition) != null)) {
				id.setPostConditionGroupId(new BigDecimal(tuple.get(qTeststep.postCondition)));
			}
			if ((qTeststep.preCondition != null) && (tuple.get(qTeststep.preCondition) != null)) {
				id.setPreConditionGroupId(new BigDecimal(tuple.get(qTeststep.preCondition)));
			}
			if ((qTeststep.inputParam != null) && (tuple.get(qTeststep.inputParam) != null)) {
				id.setInputParamGroupId(new BigDecimal(tuple.get(qTeststep.inputParam)));
			}
			id.setActionsId(new BigDecimal(tuple.get(qTeststep.actionId)));
			id.setTestCaseId(new BigDecimal(tuple.get(qTeststep.testCaseId)));
			id.setRunnerId(new BigDecimal(tuple.get(qTeststep.runnerId)));
			if ((qTeststep.outputParam != null) && (tuple.get(qTeststep.outputParam) != null)) {
				id.setOutputParamGroupId(new BigDecimal(tuple.get(qTeststep.outputParam)));
			}
			id.setStepName(tuple.get(qTeststep.stepName));
			id.setDescription(tuple.get(qTeststep.description));
			id.setTestStepType(tuple.get(qTeststep.testStepType));
			id.setActive(tuple.get(qTeststep.active));
			id.setOrderBy((long) tuple.get(qTeststep.orderBy));
			id.setCreatedBy(tuple.get(qTeststep.createdBy));
			id.setCreatedDateTime(tuple.get(qTeststep.createdDateTime));
			id.setUpdatedBy(tuple.get(qTeststep.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTeststep.updatedDateTime));
			return id;
		}

	}

	public TestStep getTestStepDetailsById(int testStepId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.testStepID.eq(testStepId));
			logger.info("Generated Query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepsByCaseId(int testCaseId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.testCaseId.eq(testCaseId));
			logger.info("generated query : " + sqlQuery);
			return getTestStepByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private List<TestStep> getTestStepByQuery(SQLQuery sqlQuery) {
		return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.preCondition, qTeststep.postCondition,
				qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.description, qTeststep.stepName,
				qTeststep.description, qTeststep.testStepType, qTeststep.active, qTeststep.orderBy, qTeststep.createdBy, qTeststep.createdDateTime,
				qTeststep.updatedBy, qTeststep.updatedDateTime, qTeststep.actionId));
	}

	public List<TestStep> getTestStepDetailsByActionId(int actionid) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.actionId.eq(actionid));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepDetailsByTestcaseId(int testCaseID) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.testCaseId.eq(testCaseID));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepDetailsByRunnerId(int runnerId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.runnerId.eq(runnerId));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestStep getTestStepDetailsByPostconditionId(int conditiongroupByPostconditionId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.postCondition.eq(conditiongroupByPostconditionId));
			logger.info("Generated Query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepDetailsByPreconditionId(int conditiongroupByPreconditionId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.preCondition.eq(conditiongroupByPreconditionId));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepDetailsByInputparamId(int paramgroupByInputparamId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.inputParam.eq(paramgroupByInputparamId));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestStep> getTestStepDetailsByOutputparamId(int paramgroupByOutputparamId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.outputParam.eq(paramgroupByOutputparamId));
			logger.info("Generated Query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestStepProjection(qTeststep.testStepID, qTeststep.stepName, qTeststep.description,
					qTeststep.testStepType, qTeststep.actionId, qTeststep.active, qTeststep.orderBy, qTeststep.postCondition, qTeststep.preCondition,
					qTeststep.inputParam, qTeststep.outputParam, qTeststep.testCaseId, qTeststep.runnerId, qTeststep.createdBy, qTeststep.createdDateTime,
					qTeststep.updatedBy, qTeststep.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestStepByName(TestStep testStep) throws DataAccessException {
		try {
			TestStepId id = testStep.getTestStepId();
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qTeststep)
					.where(qTeststep.stepName.eq(id.getStepName()).and(qTeststep.orderBy.eq(id.getOrderBy().intValue()))
							.and(qTeststep.testCaseId.eq(id.getTestCaseId().intValue())));
			Integer TestStepId = template.queryForObject(sqlQuery, qTeststep.testStepID);
			if (TestStepId != null) {
				return TestStepId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long updateTestStep(final TestStep testStep) throws DataAccessException {
		try {
			return template.update(qTeststep, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					TestStepId id = testStep.getTestStepId();
					return sqlUpdateClause.where(qTeststep.testStepID.eq(id.getTestStepId().intValue())).set(qTeststep.description, id.getDescription())
							.set(qTeststep.updatedBy, id.getUpdatedBy()).set(qTeststep.expectedResult, id.getExpectedResult())
							.set(qTeststep.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getTestStepOnlByName(String testStepName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTeststep).where(qTeststep.stepName.eq(testStepName));
			Integer testStepId = template.queryForObject(sqlQuery, qTeststep.testStepID);
			if (testStepId != null) {
				return testStepId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long deleteTestStepByCaseId(final int testCaseId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where test case id : " + testCaseId);
		try {
			result = template.delete(qTeststep, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qTeststep.testCaseId.eq(testCaseId)).execute();
				}
			});
			logger.info("number of rows deleted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

}
