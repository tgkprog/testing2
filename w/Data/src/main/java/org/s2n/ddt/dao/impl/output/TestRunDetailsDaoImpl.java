package org.s2n.ddt.dao.impl.output;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;

import org.s2n.ddt.dao.output.TestRunDetailsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestRunDetails;
import org.s2n.ddt.pojo.output.TestRunDetails;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestRunDetailsDaoImpl implements TestRunDetailsDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestRunDetailsDaoImpl.class);

	private QTestRunDetails qTestRunDetails = QTestRunDetails.TestRunDetails;

	private QueryDslJdbcTemplate template;

	public void setdataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public class MappingTestRunDataProjection extends MappingProjection<TestRunDetails> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestRunDataProjection.class);

		public MappingTestRunDataProjection(Expression<?>... args) {
			super(TestRunDetails.class, args);
		}

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected TestRunDetails map(Tuple tuple) {
			TestRunDetails details = new TestRunDetails();
			details.setTestRunId(new BigDecimal(tuple.get(qTestRunDetails.testRunID)));
			details.setRunTime(tuple.get(qTestRunDetails.runTime));
			details.setBuildVersion(tuple.get(qTestRunDetails.buildVersion));
			details.setCreatedBy(tuple.get(qTestRunDetails.createdBy));
			details.setCreatedDateTime(tuple.get(qTestRunDetails.createdDateTime));
			details.setMachineId(tuple.get(qTestRunDetails.machineId));
			details.setNotificationDetails(tuple.get(qTestRunDetails.notificationDetails));
			details.setOs(tuple.get(qTestRunDetails.os));
			details.setStatus(tuple.get(qTestRunDetails.status));
			details.setUpdatedBy(tuple.get(qTestRunDetails.updatedBy));
			details.setUpdatedDateTime(tuple.get(qTestRunDetails.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + details);
			}
			return details;
		}

	}

	public TestRunDetails getTestRunDetailsById(int testRunId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestRunDetails).where(qTestRunDetails.testRunID.eq(testRunId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestRunDataProjection(qTestRunDetails.testRunID, qTestRunDetails.testPlanID,
					qTestRunDetails.runTime, qTestRunDetails.status, qTestRunDetails.notificationDetails, qTestRunDetails.os,
					qTestRunDetails.buildVersion, qTestRunDetails.machineId, qTestRunDetails.createdBy, qTestRunDetails.createdDateTime,
					qTestRunDetails.updatedBy, qTestRunDetails.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<TestRunDetails> getTestRunDetailsByPlanId(int testPlanId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestRunDetails).where(qTestRunDetails.testPlanID.eq(testPlanId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingTestRunDataProjection(qTestRunDetails.testRunID, qTestRunDetails.testPlanID,
					qTestRunDetails.runTime, qTestRunDetails.status, qTestRunDetails.notificationDetails, qTestRunDetails.os,
					qTestRunDetails.buildVersion, qTestRunDetails.machineId, qTestRunDetails.createdBy, qTestRunDetails.createdDateTime,
					qTestRunDetails.updatedBy, qTestRunDetails.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestRunDetails(final TestRunDetails testRunDetails) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testRunDetails.getTestRunId());
		try {
			result = template.insert(qTestRunDetails, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTestRunDetails.testRunID, qTestRunDetails.buildVersion, qTestRunDetails.createdBy, qTestRunDetails.createdDateTime,
									qTestRunDetails.machineId, qTestRunDetails.notificationDetails, qTestRunDetails.os, qTestRunDetails.runTime,
									qTestRunDetails.status, qTestRunDetails.testPlanID, qTestRunDetails.updatedBy, qTestRunDetails.updatedDateTime)
							.values(testRunDetails.getTestRunId(), testRunDetails.getBuildVersion(), testRunDetails.getCreatedBy(),
									testRunDetails.getCreatedDateTime(), testRunDetails.getMachineId(), testRunDetails.getNotificationDetails(),
									testRunDetails.getOs(), testRunDetails.getRunTime(), testRunDetails.getStatus(), testRunDetails.getTestPlanId(),
									testRunDetails.getUpdatedBy(), testRunDetails.getUpdatedDateTime()).execute();
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
	public int insertTestRunDetailsGetKey(final TestRunDetails testRunDetails) throws DataAccessException {
		int testRunId = 0;
		try {
			testRunId = template.insertWithKey(qTestRunDetails, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTestRunDetails.testRunID, qTestRunDetails.buildVersion, qTestRunDetails.createdBy, qTestRunDetails.createdDateTime,
									qTestRunDetails.machineId, qTestRunDetails.notificationDetails, qTestRunDetails.os, qTestRunDetails.runTime,
									qTestRunDetails.status, qTestRunDetails.testPlanID, qTestRunDetails.updatedBy, qTestRunDetails.updatedDateTime)
							.values(testRunDetails.getTestRunId(), testRunDetails.getBuildVersion(), testRunDetails.getCreatedBy(),
									testRunDetails.getCreatedDateTime(), testRunDetails.getMachineId(), testRunDetails.getNotificationDetails(),
									testRunDetails.getOs(), testRunDetails.getRunTime(), testRunDetails.getStatus(), testRunDetails.getTestPlanId(),
									testRunDetails.getUpdatedBy(), testRunDetails.getUpdatedDateTime()).executeWithKey(qTestRunDetails.testRunID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testRunId;
	}

}
