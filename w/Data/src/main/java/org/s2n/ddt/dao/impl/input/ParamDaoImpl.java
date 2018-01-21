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

import org.s2n.ddt.dao.input.ParamDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QParam;
import org.s2n.ddt.pojo.input.Param;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ParamDao
 */
public class ParamDaoImpl implements ParamDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ParamDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QParam qParam = QParam.Param;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertParamDetails(final Param param) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Param id : " + param.getParamId());
		try {
			result = template.insert(qParam, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qParam.paramId, qParam.paramGroupId, qParam.paramName, qParam.description, qParam.objectId, qParam.orderBy,
									qParam.createdBy, qParam.createdDateTime, qParam.updatedBy, qParam.updatedDateTime)
							.values(param.getParamId(), param.getParamGroupId(), param.getParamName(), param.getDescription(), param.getObjectId(),
									param.getOrderBy(), param.getCreatedBy(), param.getCreatedDateTime(), param.getUpdatedBy(), param.getUpdatedDateTime())
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
	public long deleteParamByGroupId(final int paramGrpId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where ParamGroup id : " + paramGrpId);
		try {
			result = template.delete(qParam, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qParam.paramGroupId.eq(paramGrpId)).execute();
				}
			});
			logger.info("number of rows deleted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingParamProjection extends MappingProjection<Param> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingParamProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingParamProjection(Expression<?>... args) {
			super(Param.class, args);
		}

		@Override
		protected Param map(Tuple tuple) {
			Param param = new Param();
			param.setParamId(new BigDecimal(tuple.get(qParam.paramId)));
			param.setParamName(tuple.get(qParam.paramName));
			param.setDescription(tuple.get(qParam.description));
			param.setObjectId(new BigDecimal(tuple.get(qParam.objectId)));
			param.setCreatedBy(tuple.get(qParam.createdBy));
			param.setCreatedDateTime(tuple.get(qParam.createdDateTime));
			param.setUpdatedBy(tuple.get(qParam.updatedBy));
			param.setUpdatedDateTime(tuple.get(qParam.updatedDateTime));
			if (qParam.orderBy != null && tuple.get(qParam.orderBy) != null) {
				param.setOrderBy(Long.valueOf(tuple.get(qParam.orderBy)));
			}
			param.setParamGroupId(new BigDecimal(tuple.get(qParam.paramGroupId)));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + param);
			}
			return param;
		}
	}

	public List<Param> getParamDetailsById(int paramId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParam).where(qParam.paramId.eq(paramId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingParamProjection(qParam.paramId, qParam.paramName, qParam.description, qParam.paramGroupId,
					qParam.objectId, qParam.createdBy, qParam.createdDateTime, qParam.updatedBy, qParam.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Param> getParamDetailsByParamGroupId(int paramGroupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParam).where(qParam.paramGroupId.eq(paramGroupId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingParamProjection(qParam.paramId, qParam.paramName, qParam.description, qParam.paramGroupId,
					qParam.objectId, qParam.createdBy, qParam.createdDateTime, qParam.updatedBy, qParam.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Param> getParamDetailsByObjectId(int objectId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParam).where(qParam.objectId.eq(objectId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingParamProjection(qParam.paramId, qParam.paramName, qParam.description, qParam.paramGroupId,
					qParam.objectId, qParam.createdBy, qParam.createdDateTime, qParam.updatedBy, qParam.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getParamIdDetailsByName(Param param) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qParam)
					.where(qParam.paramGroupId.eq(param.getParamGroupId().intValue()).and(qParam.paramName.eq(param.getParamName()))
							.and(qParam.objectId.eq(param.getObjectId().intValue())).and(qParam.orderBy.eq(param.getOrderBy().intValue())));
			Integer paramID = template.queryForObject(sqlQuery, qParam.paramId);
			if (paramID != null) {
				return paramID;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int getParamIdDetailsOnlyByName(String paramName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qParam).where(qParam.paramName.eq(paramName));
			Integer paramId = template.queryForObject(sqlQuery, qParam.paramId);
			if (paramId != null) {
				return paramId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateParam(final Param param) throws DataAccessException {
		try {
			return template.update(qParam, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qParam.paramId.eq(param.getParamId().intValue())).set(qParam.description, param.getDescription())
							.set(qParam.updatedBy, param.getUpdatedBy()).set(qParam.paramName, param.getParamName())
							.set(qParam.updatedDateTime, new Date(param.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertParamGetKey(final Param param) throws DataAccessException {
		int paramId = 0;
		try {
			paramId = template.insertWithKey(qParam, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qParam.paramId, qParam.paramGroupId, qParam.paramName, qParam.description, qParam.objectId, qParam.orderBy,
									qParam.createdBy, qParam.createdDateTime, qParam.updatedBy, qParam.updatedDateTime)
							.values(param.getParamId(), param.getParamGroupId(), param.getParamName(), param.getDescription(), param.getObjectId(),
									param.getOrderBy(), param.getCreatedBy(), param.getCreatedDateTime(), param.getUpdatedBy(), param.getUpdatedDateTime())
							.executeWithKey(qParam.paramId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return paramId;
	}

}
