package org.s2n.ddt.dao.impl.output;

import java.math.BigDecimal;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;

import org.s2n.ddt.dao.output.TestReportDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTestReport;
import org.s2n.ddt.pojo.output.TestReport;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class TestReportDaoImpl implements TestReportDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestReportDaoImpl.class);

	private QTestReport qTestReport = QTestReport.TestReport;

	private QueryDslJdbcTemplate template;

	public void setdataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public class MappingTestReportProjection extends MappingProjection<TestReport> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingTestReportProjection.class);

		public MappingTestReportProjection(Expression<?>... args) {
			super(TestReport.class, args);
		}

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		@Override
		protected TestReport map(Tuple tuple) {
			TestReport report = new TestReport();
			report.setTestReportId(new BigDecimal(tuple.get(qTestReport.testReportID)));
			report.setReportDetails(tuple.get(qTestReport.reportDetails));
			report.setCreatedBy(tuple.get(qTestReport.createdBy));
			report.setCreatedDateTime(tuple.get(qTestReport.createdDateTime));
			report.setUpdatedBy(tuple.get(qTestReport.updatedBy));
			report.setUpdatedDateTime(tuple.get(qTestReport.updatedDateTime));
			report.setTestRunId(new BigDecimal(tuple.get(qTestReport.testRunID)));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + report);
			}
			return report;
		}
	}

	public TestReport getTestReportById(int testReportId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestReport).where(qTestReport.testReportID.eq(testReportId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestReportProjection(qTestReport.testReportID, qTestReport.testRunID,
					qTestReport.reportDetails, qTestReport.createdBy, qTestReport.createdDateTime, qTestReport.updatedBy, qTestReport.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public TestReport getTestReportByRunId(int testRunID) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qTestReport).where(qTestReport.testRunID.eq(testRunID));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingTestReportProjection(qTestReport.testReportID, qTestReport.testRunID,
					qTestReport.reportDetails, qTestReport.createdBy, qTestReport.createdDateTime, qTestReport.updatedBy, qTestReport.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long insertTestReportDetails(final TestReport testReport) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + testReport.getTestReportId());
		try {
			result = template.insert(qTestReport, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qTestReport.testReportID, qTestReport.createdBy, qTestReport.createdDateTime, qTestReport.reportDetails,
									qTestReport.testRunID, qTestReport.updatedBy, qTestReport.updatedDateTime)
							.values(testReport.getTestReportId(), testReport.getCreatedBy(), testReport.getCreatedDateTime(),
									testReport.getReportDetails(), testReport.getTestRunId(), testReport.getUpdatedBy(), testReport.getUpdatedDateTime())
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

	@Override
	public int insertTestReportGetKey(final TestReport testReport) throws DataAccessException {
		int testReportId = 0;
		try {
			testReportId = template.insertWithKey(qTestReport, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qTestReport.testReportID, qTestReport.createdBy, qTestReport.createdDateTime, qTestReport.reportDetails,
									qTestReport.testRunID, qTestReport.updatedBy, qTestReport.updatedDateTime)
							.values(testReport.getTestReportId(), testReport.getCreatedBy(), testReport.getCreatedDateTime(),
									testReport.getReportDetails(), testReport.getTestRunId(), testReport.getUpdatedBy(), testReport.getUpdatedDateTime())
							.executeWithKey(qTestReport.testReportID);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return testReportId;
	}

}
