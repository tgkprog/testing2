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

import org.s2n.ddt.dao.ConditionGroupDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QConditionGroup;
import org.s2n.ddt.pojo.ConditionGroup;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class ConditionGroupDaoImpl implements ConditionGroupDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConditionGroupDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QConditionGroup qConditionGroup = QConditionGroup.ConditionGroup;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingConditionGroupProjection extends MappingProjection<ConditionGroup> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingConditionGroupProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingConditionGroupProjection(Expression<?>... args) {
			super(ConditionGroup.class, args);
		}

		@Override
		protected ConditionGroup map(Tuple tuple) {
			ConditionGroup group = new ConditionGroup();
			group.setConditionGroupId(new BigDecimal(tuple.get(qConditionGroup.condGrpId)));
			group.setConditionGroupName(tuple.get(qConditionGroup.condGrpName));
			group.setDescription(tuple.get(qConditionGroup.description));
			group.setAppId(new BigDecimal(tuple.get(qConditionGroup.appID)));
			group.setCreatedBy(tuple.get(qConditionGroup.createdBy));
			group.setCreatedDateTime(tuple.get(qConditionGroup.createdDateTime));
			group.setUpdatedBy(tuple.get(qConditionGroup.updatedBy));
			group.setUpdatedDateTime(tuple.get(qConditionGroup.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + group);
			}
			return group;
		}
	}

	public ConditionGroup getConditionGroupById(int condGrpId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qConditionGroup).where(qConditionGroup.condGrpId.eq(condGrpId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingConditionGroupProjection(qConditionGroup.condGrpId, qConditionGroup.condGrpName,
					qConditionGroup.description, qConditionGroup.appID, qConditionGroup.createdBy, qConditionGroup.createdDateTime,
					qConditionGroup.updatedBy, qConditionGroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<ConditionGroup> getConditionGroupsByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qConditionGroup).where(qConditionGroup.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingConditionGroupProjection(qConditionGroup.appID, qConditionGroup.condGrpId,
					qConditionGroup.condGrpName, qConditionGroup.description, qConditionGroup.createdBy, qConditionGroup.createdDateTime,
					qConditionGroup.updatedBy, qConditionGroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getConditionGroupIdByName(ConditionGroup conditionGroup) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qConditionGroup)
					.where(qConditionGroup.appID.eq(conditionGroup.getAppId().intValue()).and(
							qConditionGroup.condGrpName.eq(conditionGroup.getConditionGroupName())));
			Integer conditionGroupId = template.queryForObject(sqlQuery, qConditionGroup.condGrpId);
			if (conditionGroupId != null) {
				return conditionGroupId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertConditionGroup(final ConditionGroup conditionGroup) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Condition Group : " + conditionGroup.getConditionGroupId());
		try {
			result = template.insert(qConditionGroup, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qConditionGroup.condGrpId, qConditionGroup.condGrpName, qConditionGroup.description, qConditionGroup.appID,
									qConditionGroup.createdBy, qConditionGroup.createdDateTime, qConditionGroup.updatedBy, qConditionGroup.updatedDateTime)
							.values(conditionGroup.getConditionGroupId(), conditionGroup.getConditionGroupName(), conditionGroup.getDescription(),
									conditionGroup.getAppId(), conditionGroup.getCreatedBy(), conditionGroup.getCreatedDateTime(),
									conditionGroup.getUpdatedBy(), conditionGroup.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public long updateConditionGroup(final ConditionGroup conditionGroup) throws DataAccessException {
		try {
			return template.update(qConditionGroup, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qConditionGroup.condGrpId.eq(conditionGroup.getConditionGroupId().intValue()))
							.set(qConditionGroup.condGrpName, conditionGroup.getConditionGroupName())
							.set(qConditionGroup.description, conditionGroup.getDescription())
							.set(qConditionGroup.updatedBy, conditionGroup.getUpdatedBy())
							.set(qConditionGroup.updatedDateTime, new Date(conditionGroup.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertConditionGroupGetKey(final ConditionGroup conditionGroup) throws DataAccessException {
		int condGrpId = 0;
		try {
			condGrpId = template.insertWithKey(qConditionGroup, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qConditionGroup.condGrpId, qConditionGroup.condGrpName, qConditionGroup.description, qConditionGroup.appID,
									qConditionGroup.createdBy, qConditionGroup.createdDateTime, qConditionGroup.updatedBy, qConditionGroup.updatedDateTime)
							.values(conditionGroup.getConditionGroupId(), conditionGroup.getConditionGroupName(), conditionGroup.getDescription(),
									conditionGroup.getAppId(), conditionGroup.getCreatedBy(), conditionGroup.getCreatedDateTime(),
									conditionGroup.getUpdatedBy(), conditionGroup.getUpdatedDateTime()).executeWithKey(qConditionGroup.condGrpId);

				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return condGrpId;
	}

}
