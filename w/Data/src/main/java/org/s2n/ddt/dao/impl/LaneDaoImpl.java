package org.s2n.ddt.dao.impl;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.LaneDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QLane;
import org.s2n.ddt.pojo.input.Lane;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class LaneDaoImpl implements LaneDao {
	private QLane qLane = QLane.Lane;
	private QueryDslJdbcTemplate template;
	private final Logger logger = Logger.getLogger(LaneDaoImpl.class);

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingLaneProjection extends MappingProjection<Lane> {
		/**
		 * Logger for this class laneId (Primary Key), laneType, laneUserName,
		 * agentName(foreign/ref key), clones, iterations, runnerType,
		 */
		private final Logger logger = Logger.getLogger(MappingLaneProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingLaneProjection(Expression<?>... args) {
			super(Lane.class, args);
		}

		@Override
		protected Lane map(Tuple tuple) {
			Lane lane = new Lane();
			lane.setAgentName(tuple.get(qLane.agentName));
			lane.setLaneUserName(tuple.get(qLane.laneUserName));
			lane.setLaneName(tuple.get(qLane.laneType));
			lane.setLaneId(tuple.get(qLane.laneId));
			lane.setClones(tuple.get(qLane.clones));
			lane.setIterations(tuple.get(qLane.iterations));
			lane.setRunnerType(tuple.get(qLane.runnerType));
			logger.info("Lane Object of Id:" + lane.getLaneId());
			return lane;
		}
	}

	@Override
	public Lane getLaneById(int laneId) throws DataAccessException {
		try {
			SQLQuery query = template.newSqlQuery().from(qLane).where(qLane.laneId.eq(laneId));
			return template.queryForObject(query, new MappingLaneProjection(qLane.laneId, qLane.laneType, qLane.laneUserName, qLane.agentName,
					qLane.clones, qLane.iterations));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int LaneIdByLaneName(String laneName) throws DataAccessException {
		try {
			SQLQuery query = template.newSqlQuery().from(qLane).where(qLane.laneUserName.eq(laneName));
			return template.queryForObject(query, qLane.laneId);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertLane(final Lane lane) throws DataAccessException {
		int laneId = 0;
		try {
			laneId = template.insertWithKey(qLane, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qLane.laneType, qLane.laneUserName, qLane.agentName, qLane.clones, qLane.iterations, qLane.runnerType)
							.values(lane.getLaneName(), lane.getLaneUserName(), lane.getAgentName(), lane.getClones(), lane.getIterations(),
									lane.getRunnerType()).executeWithKey(qLane.laneId);
				}
			});
			return laneId;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public void updateLane(final Lane lane) throws DataAccessException {
		try {
			template.update(qLane, new SqlUpdateCallback() {
				@Override
				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdate) {
					return sqlUpdate.where(qLane.laneId.eq(lane.getLaneId())).set(qLane.laneType, lane.getLaneName())
							.set(qLane.laneUserName, lane.getLaneUserName()).set(qLane.agentName, lane.getAgentName()).set(qLane.clones, lane.getClones())
							.set(qLane.iterations, lane.getIterations()).set(qLane.runnerType, lane.getRunnerType()).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public String getAgentName(int laneId) throws DataAccessException {
		try {
			SQLQuery query = template.newSqlQuery().from(qLane).where(qLane.laneId.eq(laneId));
			return template.queryForObject(query, qLane.agentName);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public String getUserName(int laneId) throws DataAccessException {
		try {
			SQLQuery query = template.newSqlQuery().from(qLane).where(qLane.laneId.eq(laneId));
			return template.queryForObject(query, qLane.laneUserName);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}
}
