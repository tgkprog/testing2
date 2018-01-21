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

import org.s2n.ddt.dao.AgentDetailsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QAgentDetails;
import org.s2n.ddt.pojo.output.AgentDetails;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class AgentDetailsDaoImpl implements AgentDetailsDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AgentDetailsDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QAgentDetails qAgentDetails = QAgentDetails.AgentDetails;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingAgentDetailsProjection extends MappingProjection<AgentDetails> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingAgentDetailsProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingAgentDetailsProjection(Expression<?>... args) {
			super(AgentDetails.class, args);
		}

		@Override
		protected AgentDetails map(Tuple tuple) {
			AgentDetails details = new AgentDetails();
			details.setAgentId(new BigDecimal(tuple.get(qAgentDetails.agentId)));
			details.setAgentName(tuple.get(qAgentDetails.agentName));
			details.setIp(tuple.get(qAgentDetails.ip));
			details.setPort(tuple.get(qAgentDetails.port));
			details.setInstance(tuple.get(qAgentDetails.instance));
			details.setTestPlanId(new BigDecimal(tuple.get(qAgentDetails.testPlanID)));
			details.setCreatedBy(tuple.get(qAgentDetails.createdBy));
			details.setCreatedDateTime(tuple.get(qAgentDetails.createdDateTime));
			details.setUpdatedBy(tuple.get(qAgentDetails.updatedBy));
			details.setUpdatedDateTime(tuple.get(qAgentDetails.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + details);
			}
			return details;
		}
	}

	public AgentDetails getAgentDetailsById(int agentId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qAgentDetails).where(qAgentDetails.agentId.eq(agentId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingAgentDetailsProjection(qAgentDetails.agentId, qAgentDetails.agentName, qAgentDetails.ip,
					qAgentDetails.port, qAgentDetails.instance, qAgentDetails.testPlanID, qAgentDetails.createdBy, qAgentDetails.createdDateTime,
					qAgentDetails.updatedBy, qAgentDetails.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<AgentDetails> getAgentDetailsByTestPlanId(int testPlanId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qAgentDetails).where(qAgentDetails.testPlanID.eq(testPlanId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingAgentDetailsProjection(qAgentDetails.agentId, qAgentDetails.agentName, qAgentDetails.ip,
					qAgentDetails.port, qAgentDetails.instance, qAgentDetails.testPlanID, qAgentDetails.createdBy, qAgentDetails.createdDateTime,
					qAgentDetails.updatedBy, qAgentDetails.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getAgentIdByAgentDetails(AgentDetails agentDetails) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qAgentDetails)
					.where(qAgentDetails.agentName
							.eq(agentDetails.getAgentName())
							.and(qAgentDetails.ip.eq(agentDetails.getIp()))
							.and(qAgentDetails.port.eq(agentDetails.getPort()))
							.and(qAgentDetails.instance.eq(agentDetails.getInstance()).and(
									qAgentDetails.testPlanID.eq(agentDetails.getTestPlanId().intValue()))));
			Integer agentID = template.queryForObject(sqlQuery, qAgentDetails.agentId);
			if (agentID != null) {
				return agentID;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertAgentDetails(final AgentDetails agentDetails) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Agents : " + agentDetails.getAgentId());
		try {
			result = template.insert(qAgentDetails, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qAgentDetails.agentId, qAgentDetails.agentName, qAgentDetails.ip, qAgentDetails.port, qAgentDetails.instance,
									qAgentDetails.testPlanID, qAgentDetails.createdBy, qAgentDetails.createdDateTime, qAgentDetails.updatedBy,
									qAgentDetails.updatedDateTime)
							.values(agentDetails.getAgentId(), agentDetails.getAgentName(), agentDetails.getIp(), agentDetails.getPort(),
									agentDetails.getInstance(), agentDetails.getTestPlanId(), agentDetails.getCreatedBy(),
									agentDetails.getCreatedDateTime(), agentDetails.getUpdatedBy(), agentDetails.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public long updateAgentDetails(final AgentDetails agentDetails) throws DataAccessException {
		try {
			return template.update(qAgentDetails, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qAgentDetails.agentId.eq(agentDetails.getAgentId().intValue()))
							.set(qAgentDetails.updatedBy, agentDetails.getUpdatedBy())
							.set(qAgentDetails.updatedDateTime, new Date(agentDetails.getUpdatedDateTime().getTime())).execute();
				}

			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertAgentDetailsGetKey(final AgentDetails agentDetails) throws DataAccessException {
		int agentId = 0;
		try {
			agentId = template.insertWithKey(qAgentDetails, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qAgentDetails.agentId, qAgentDetails.agentName, qAgentDetails.ip, qAgentDetails.port, qAgentDetails.instance,
									qAgentDetails.testPlanID, qAgentDetails.createdBy, qAgentDetails.createdDateTime, qAgentDetails.updatedBy,
									qAgentDetails.updatedDateTime)
							.values(agentDetails.getAgentId(), agentDetails.getAgentName(), agentDetails.getIp(), agentDetails.getPort(),
									agentDetails.getInstance(), agentDetails.getTestPlanId(), agentDetails.getCreatedBy(),
									agentDetails.getCreatedDateTime(), agentDetails.getUpdatedBy(), agentDetails.getUpdatedDateTime())
							.executeWithKey(qAgentDetails.agentId);

				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return agentId;
	}

}
