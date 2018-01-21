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

import org.s2n.ddt.dao.input.ParamGroupDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QParamGroup;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.ParamGroupId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ParamGroupDao
 */
public class ParamGroupDaoImpl implements ParamGroupDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ParamGroupDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QParamGroup qParamgroup = QParamGroup.ParamGroup;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertParamGroupDetails(final ParamGroup paramGroup) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for paramGroup id : " + paramGroup.getParamGroupId());
		try {
			result = template.insert(qParamgroup, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					ParamGroupId paramGroupId = paramGroup.getParamGroupId();
					return sqlInsertClause
							.columns(qParamgroup.paramGroupId, qParamgroup.paramGroupName, qParamgroup.description, qParamgroup.tag, qParamgroup.appID,
									qParamgroup.createdBy, qParamgroup.createdDateTime, qParamgroup.updatedBy, qParamgroup.updatedDateTime)
							.values(paramGroupId.getParamGroupId(), paramGroupId.getParamGroupName(), paramGroupId.getDescription(),
									paramGroupId.getTag(), paramGroupId.getAppId(), paramGroupId.getCreatedBy(), paramGroupId.getCreatedDateTime(),
									paramGroupId.getUpdatedBy(), paramGroupId.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public ParamGroup getParamGroupDetailsById(int paramGroupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParamgroup).where(qParamgroup.paramGroupId.eq(paramGroupId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingParamGroupProjection(qParamgroup.paramGroupId, qParamgroup.paramGroupName,
					qParamgroup.description, qParamgroup.tag, qParamgroup.appID, qParamgroup.createdBy, qParamgroup.createdDateTime,
					qParamgroup.updatedBy, qParamgroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingParamGroupProjection extends MappingProjection<ParamGroup> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingParamGroupProjection.class);

		private static final long serialVersionUID = 1L;

		public MappingParamGroupProjection(Expression<?>... args) {
			super(ParamGroup.class, args);
		}

		@Override
		protected ParamGroup map(Tuple tuple) {
			ParamGroup group = new ParamGroup();
			group.setParamGroupId(getParamGroupIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + group);
			}
			return group;

		}

		private ParamGroupId getParamGroupIdMapping(Tuple tuple) {
			ParamGroupId group = new ParamGroupId();
			group.setParamGroupId(new BigDecimal(tuple.get(qParamgroup.paramGroupId)));
			group.setParamGroupName(tuple.get(qParamgroup.paramGroupName));
			group.setDescription(tuple.get(qParamgroup.description));
			group.setTag(tuple.get(qParamgroup.tag));
			group.setCreatedBy(tuple.get(qParamgroup.createdBy));
			group.setCreatedDateTime(tuple.get(qParamgroup.createdDateTime));
			group.setUpdatedBy(tuple.get(qParamgroup.updatedBy));
			group.setUpdatedDateTime(tuple.get(qParamgroup.updatedDateTime));
			group.setAppId(new BigDecimal(tuple.get(qParamgroup.appID)));
			return group;
		}
	}

	public List<ParamGroup> getParamGroupsByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParamgroup).where(qParamgroup.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return getParamGroupByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private List<ParamGroup> getParamGroupByQuery(SQLQuery sqlQuery) {
		return template.query(sqlQuery,
				new MappingParamGroupProjection(qParamgroup.paramGroupId, qParamgroup.paramGroupName, qParamgroup.description, qParamgroup.tag,
						qParamgroup.appID, qParamgroup.createdBy, qParamgroup.createdDateTime, qParamgroup.updatedBy, qParamgroup.updatedDateTime));
	}

	public int getParamGroupIdDetailsByName(ParamGroup paramGroup) throws DataAccessException {
		try {
			ParamGroupId id = paramGroup.getParamGroupId();
			SQLQuery sqlQuery = template.newSqlQuery().from(qParamgroup)
					.where(qParamgroup.appID.eq(id.getAppId().intValue()).and(qParamgroup.paramGroupName.eq(id.getParamGroupName())));
			Integer ParamGroupId = template.queryForObject(sqlQuery, qParamgroup.paramGroupId);
			if (ParamGroupId != null) {
				return ParamGroupId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateParamGroup(final ParamGroup paramGroup) throws DataAccessException {
		try {
			return template.update(qParamgroup, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					ParamGroupId id = paramGroup.getParamGroupId();
					return sqlUpdateClause.where(qParamgroup.paramGroupId.eq(id.getParamGroupId().intValue()))
							.set(qParamgroup.description, id.getDescription()).set(qParamgroup.updatedBy, id.getUpdatedBy())
							.set(qParamgroup.paramGroupName, id.getParamGroupName()).set(qParamgroup.tag, id.getTag())
							.set(qParamgroup.appID, id.getAppId().intValue())
							.set(qParamgroup.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getParamGroupIdDetailsOnlyByName(String paramGroupName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParamgroup).where(qParamgroup.paramGroupName.eq(paramGroupName));
			Integer paramGroupId = template.queryForObject(sqlQuery, qParamgroup.paramGroupId);
			if (paramGroupId != null) {
				return paramGroupId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public long deleteParamGroupById(final int paramGrpId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where ParamGroup id : " + paramGrpId);
		try {
			result = template.delete(qParamgroup, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qParamgroup.paramGroupId.eq(paramGrpId)).execute();
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
	public int insertParamGroupGetKey(final ParamGroup paramGroup) throws DataAccessException {
		int paramGroupId = 0;
		try {
			paramGroupId = template.insertWithKey(qParamgroup, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					ParamGroupId paramGroupId = paramGroup.getParamGroupId();
					return insert
							.columns(qParamgroup.paramGroupId, qParamgroup.paramGroupName, qParamgroup.description, qParamgroup.tag, qParamgroup.appID,
									qParamgroup.createdBy, qParamgroup.createdDateTime, qParamgroup.updatedBy, qParamgroup.updatedDateTime)
							.values(paramGroupId.getParamGroupId(), paramGroupId.getParamGroupName(), paramGroupId.getDescription(),
									paramGroupId.getTag(), paramGroupId.getAppId(), paramGroupId.getCreatedBy(), paramGroupId.getCreatedDateTime(),
									paramGroupId.getUpdatedBy(), paramGroupId.getUpdatedDateTime()).executeWithKey(qParamgroup.paramGroupId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return paramGroupId;
	}

}
