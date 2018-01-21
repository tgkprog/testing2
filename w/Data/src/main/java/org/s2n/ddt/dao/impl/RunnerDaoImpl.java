package org.s2n.ddt.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.RunnerDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QRunner;
import org.s2n.ddt.pojo.Runner;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for RunnerDao
 */
public class RunnerDaoImpl implements RunnerDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RunnerDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QRunner qRunner = QRunner.Runner;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public Runner getRunnerById(int runnerId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qRunner).where(qRunner.runnerId.eq(runnerId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingRunnerProjection(qRunner.runnerId, qRunner.createdBy, qRunner.createdDateTime,
					qRunner.description, qRunner.runnerName, qRunner.updatedBy, qRunner.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingRunnerProjection extends MappingProjection<Runner> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingRunnerProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingRunnerProjection(Expression<?>... args) {
			super(Runner.class, args);
		}

		@Override
		protected Runner map(Tuple tuple) {
			Runner runner = new Runner();
			runner.setRunnerId(new BigDecimal(tuple.get(qRunner.runnerId)));
			runner.setRunnerName(tuple.get(qRunner.runnerName));
			runner.setDescription(tuple.get(qRunner.description));
			runner.setCreatedBy(tuple.get(qRunner.createdBy));
			runner.setCreatedDateTime(tuple.get(qRunner.createdDateTime));
			runner.setUpdatedBy(tuple.get(qRunner.updatedBy));
			runner.setUpdatedDateTime(tuple.get(qRunner.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + runner);
			}
			return runner;
		}
	}

	public long insertRunnerDetails(final Runner runner) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Runner : " + runner.getRunnerId());
		try {
			result = template.insert(qRunner, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qRunner.runnerId, qRunner.runnerName, qRunner.description, qRunner.createdBy, qRunner.createdDateTime,
									qRunner.updatedBy, qRunner.updatedDateTime)
							.values(runner.getRunnerId(), runner.getRunnerName(), runner.getDescription(), runner.getCreatedBy(),
									runner.getCreatedDateTime(), runner.getUpdatedBy(), runner.getUpdatedDateTime()).execute();
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
	public long updateRunner(final Runner runner) throws DataAccessException {
		try {
			return template.update(qRunner, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qRunner.runnerId.eq(runner.getRunnerId().intValue())).set(qRunner.runnerName, runner.getRunnerName())
							.set(qRunner.description, runner.getDescription()).set(qRunner.updatedBy, runner.getUpdatedBy())
							.set(qRunner.updatedDateTime, new Date(runner.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getRunnerIdByName(Runner runner) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qRunner).where(qRunner.runnerName.eq(runner.getRunnerName()));
			Integer runnerID = template.queryForObject(sqlQuery, qRunner.runnerId);
			if (runnerID != null) {
				return runnerID;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public int getRunnerIdOnlyByName(String runnerName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qRunner).where(qRunner.runnerName.eq(runnerName));
			Integer runnerID = template.queryForObject(sqlQuery, qRunner.runnerId);
			if (runnerID != null) {
				return runnerID;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertRunnerGetKey(final Runner runner) throws DataAccessException {
		int runnerId = 0;
		try {
			runnerId = template.insertWithKey(qRunner, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qRunner.runnerId, qRunner.runnerName, qRunner.description, qRunner.createdBy, qRunner.createdDateTime,
									qRunner.updatedBy, qRunner.updatedDateTime)
							.values(runner.getRunnerId(), runner.getRunnerName(), runner.getDescription(), runner.getCreatedBy(),
									runner.getCreatedDateTime(), runner.getUpdatedBy(), runner.getUpdatedDateTime()).executeWithKey(qRunner.runnerId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return runnerId;
	}

}
