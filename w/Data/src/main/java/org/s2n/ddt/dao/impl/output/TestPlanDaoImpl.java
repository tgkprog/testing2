package org.s2n.ddt.dao.impl.output;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.output.TestPlanDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestPlan;
import org.s2n.ddt.pojo.output.TestPlan;
import org.s2n.ddt.pojo.output.TestPlanId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestPlanDaoImpl implements TestPlanDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(TestPlanDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QTestPlan qTestPlan = QTestPlan.TestPlan;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingTestPlanProjection extends MappingProjection<TestPlan> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestPlanProjection.class);

		private static final long serialVersionUID = 1L;

		public MappingTestPlanProjection(Expression<?>... args) {
			super(TestPlan.class, args);
		}

		@Override
		protected TestPlan map(Tuple tuple) {
			TestPlan plan = new TestPlan();
			plan.setTestPlanId(getTestPlanIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + plan);
			}
			return plan;
		}

		private TestPlanId getTestPlanIdMapping(Tuple tuple) {
			TestPlanId id = new TestPlanId();
			id.setTestPlanId(new BigDecimal(tuple.get(qTestPlan.testPlanID)));
			id.setPlanName(tuple.get(qTestPlan.planName));
			id.setDescription(tuple.get(qTestPlan.description));
			id.setPreConditionGroupId(new BigDecimal(tuple.get(qTestPlan.preCondition)));
			id.setPostConditionGroupId(new BigDecimal(tuple.get(qTestPlan.postCondition)));
			id.setCreatedBy(tuple.get(qTestPlan.createdBy));
			id.setCreatedDateTime(tuple.get(qTestPlan.createdDateTime));
			id.setUpdatedBy(tuple.get(qTestPlan.updatedBy));
			id.setUpdatedDateTime(tuple.get(qTestPlan.updatedDateTime));
			return id;
		}

	}

	public TestPlan getTestPlanDetailsById(int testPlanId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlan).where(qTestPlan.testPlanID.eq(testPlanId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestPlanProjection(qTestPlan.testPlanID, qTestPlan.preCondition, qTestPlan.postCondition,
					qTestPlan.planName, qTestPlan.description, qTestPlan.createdBy, qTestPlan.createdDateTime, qTestPlan.updatedBy,
					qTestPlan.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestPlan getTestPlanDetailsByPreConditionId(int conditiongroupByPreconditionId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlan).where(qTestPlan.preCondition.eq(conditiongroupByPreconditionId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestPlanProjection(qTestPlan.testPlanID, qTestPlan.preCondition, qTestPlan.postCondition,
					qTestPlan.planName, qTestPlan.description, qTestPlan.createdBy, qTestPlan.createdDateTime, qTestPlan.updatedBy,
					qTestPlan.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestPlan getTestPlanDetailsByPostConditionId(int conditiongroupByPostcondition) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlan).where(qTestPlan.postCondition.eq(conditiongroupByPostcondition));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestPlanProjection(qTestPlan.testPlanID, qTestPlan.preCondition, qTestPlan.postCondition,
					qTestPlan.planName, qTestPlan.description, qTestPlan.createdBy, qTestPlan.createdDateTime, qTestPlan.updatedBy,
					qTestPlan.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestPlanDetails(final TestPlan testPlan) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testPlan.getTestPlanId().getTestPlanId());
		try {
			result = template.insert(qTestPlan, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					TestPlanId id = testPlan.getTestPlanId();
					return sqlInsertClause
							.columns(qTestPlan.testPlanID, qTestPlan.createdBy, qTestPlan.createdDateTime, qTestPlan.description, qTestPlan.planName,
									qTestPlan.postCondition, qTestPlan.preCondition, qTestPlan.updatedBy, qTestPlan.updatedDateTime)
							.values(id.getTestPlanId(), id.getCreatedBy(), id.getCreatedDateTime(), id.getDescription(), id.getPlanName(),
									id.getPostConditionGroupId(), id.getPreConditionGroupId(), id.getUpdatedBy(), id.getUpdatedDateTime()).execute();
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
	public int getTestPlanIdOnlyByName(String testPlanName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestPlan).where(qTestPlan.planName.eq(testPlanName));
			Integer testPlanId = template.queryForObject(sqlQuery, qTestPlan.testPlanID);
			if (testPlanId != null) {
				return testPlanId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertTestPlanGetKey(final TestPlan testPlan) throws DataAccessException {
		int testPlanId = 0;
		try {
			testPlanId = template.insertWithKey(qTestPlan, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					TestPlanId id = testPlan.getTestPlanId();
					return insert
							.columns(qTestPlan.testPlanID, qTestPlan.createdBy, qTestPlan.createdDateTime, qTestPlan.description, qTestPlan.planName,
									qTestPlan.postCondition, qTestPlan.preCondition, qTestPlan.updatedBy, qTestPlan.updatedDateTime)
							.values(id.getTestPlanId(), id.getCreatedBy(), id.getCreatedDateTime(), id.getDescription(), id.getPlanName(),
									id.getPostConditionGroupId(), id.getPreConditionGroupId(), id.getUpdatedBy(), id.getUpdatedDateTime())
							.executeWithKey(qTestPlan.testPlanID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testPlanId;
	}

	public long updateTestPlan(final TestPlan testPlan) throws DataAccessException {
		try {
			return template.update(qTestPlan, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					TestPlanId id = testPlan.getTestPlanId();
					return sqlUpdateClause.where(qTestPlan.testPlanID.eq(id.getTestPlanId().intValue())).set(qTestPlan.description, id.getDescription())
							.set(qTestPlan.preCondition, id.getPreConditionGroupId().intValue())
							.set(qTestPlan.postCondition, id.getPostConditionGroupId().intValue()).set(qTestPlan.updatedBy, id.getUpdatedBy())
							.set(qTestPlan.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}
}
