package org.s2n.ddt.dao.impl.input;

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

import org.s2n.ddt.dao.input.IdentifierTypeDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QIdentifierType;
import org.s2n.ddt.pojo.input.IdentifierType;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class IdentifierTypeDaoImpl implements IdentifierTypeDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IdentifierTypeDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QIdentifierType qIdentifierType = QIdentifierType.IdentifierType;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertIdentifierTypeDetails(final IdentifierType identifierType) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for identifier Type id : " + identifierType.getIdentifierTypeId());
		try {
			result = template.insert(qIdentifierType, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qIdentifierType.identifierTypeId, qIdentifierType.indentifierTypeName, qIdentifierType.appID,
									qIdentifierType.description, qIdentifierType.createdBy, qIdentifierType.createdDateTime, qIdentifierType.updatedBy,
									qIdentifierType.updatedDateTime)
							.values(identifierType.getIdentifierTypeId(), identifierType.getIndentifierTypeName(), identifierType.getAppId(),
									identifierType.getDescription(), identifierType.getCreatedBy(), identifierType.getCreatedDateTime(),
									identifierType.getUpdatedBy(), identifierType.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingIdentifierTypeProjection extends MappingProjection<IdentifierType> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingIdentifierTypeProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingIdentifierTypeProjection(Expression<?>... args) {
			super(IdentifierType.class, args);
		}

		@Override
		protected IdentifierType map(Tuple tuple) {
			if (qIdentifierType != null) {
				IdentifierType type = new IdentifierType();
				type.setAppId(new BigDecimal(tuple.get(qIdentifierType.appID)));
				type.setCreatedBy(tuple.get(qIdentifierType.createdBy));
				type.setCreatedDateTime(tuple.get(qIdentifierType.createdDateTime));
				type.setDescription(tuple.get(qIdentifierType.description));
				type.setIdentifierTypeId(new BigDecimal(tuple.get(qIdentifierType.identifierTypeId)));
				type.setIndentifierTypeName(tuple.get(qIdentifierType.indentifierTypeName));
				type.setUpdatedBy(tuple.get(qIdentifierType.updatedBy));
				type.setUpdatedDateTime(tuple.get(qIdentifierType.updatedDateTime));
				if (logger.isDebugEnabled()) {
					logger.debug("returning data object : " + type);
				}
				return type;
			}
			return null;
		}
	}

	public IdentifierType getIdentifierTypeById(int identifierTypeId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qIdentifierType).where(qIdentifierType.identifierTypeId.eq(identifierTypeId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingIdentifierTypeProjection(qIdentifierType.identifierTypeId,
					qIdentifierType.indentifierTypeName, qIdentifierType.description, qIdentifierType.appID, qIdentifierType.createdBy,
					qIdentifierType.createdDateTime, qIdentifierType.updatedBy, qIdentifierType.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getIdentifierTypeIdOnlyByName(String identifierTypeName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qIdentifierType).where(qIdentifierType.indentifierTypeName.eq(identifierTypeName));
			logger.info("generated query : " + sqlQuery);
			Integer identifierTypeId = template.queryForObject(sqlQuery, qIdentifierType.identifierTypeId);
			if (identifierTypeId != null) {
				return identifierTypeId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public List<IdentifierType> getIdentifierTypesByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qIdentifierType).where(qIdentifierType.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingIdentifierTypeProjection(qIdentifierType.identifierTypeId, qIdentifierType.indentifierTypeName,
					qIdentifierType.description, qIdentifierType.appID, qIdentifierType.createdBy, qIdentifierType.createdDateTime,
					qIdentifierType.updatedBy, qIdentifierType.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getIdentifierTypeIdByName(IdentifierType identifierType) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qIdentifierType)
					.where(qIdentifierType.appID.eq(identifierType.getAppId().intValue()).and(
							qIdentifierType.indentifierTypeName.eq(identifierType.getIndentifierTypeName())));
			Integer identifierId = template.queryForObject(sqlQuery, qIdentifierType.identifierTypeId);
			if (identifierId != null) {
				return identifierId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int getMaxIdentifierId() throws DataAccessException {
		try {
			int maxIdentifierId = template.getJdbcOperations().queryForInt("select max(IdentifierTypeId) from IdentifierType");
			return maxIdentifierId;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public long updateIdentifierType(final IdentifierType identifierType) throws DataAccessException {
		try {
			return template.update(qIdentifierType, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qIdentifierType.identifierTypeId.eq(identifierType.getIdentifierTypeId().intValue()))
							.set(qIdentifierType.indentifierTypeName, identifierType.getIndentifierTypeName())
							.set(qIdentifierType.description, identifierType.getDescription())
							.set(qIdentifierType.updatedBy, identifierType.getUpdatedBy())
							.set(qIdentifierType.updatedDateTime, new Date(identifierType.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertIdentifierTypeGetKey(final IdentifierType identifierType) throws DataAccessException {
		int identifierTypeId = 0;
		try {
			identifierTypeId = template.insertWithKey(qIdentifierType, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qIdentifierType.identifierTypeId, qIdentifierType.indentifierTypeName, qIdentifierType.appID,
									qIdentifierType.description, qIdentifierType.createdBy, qIdentifierType.createdDateTime, qIdentifierType.updatedBy,
									qIdentifierType.updatedDateTime)
							.values(identifierType.getIdentifierTypeId(), identifierType.getIndentifierTypeName(), identifierType.getAppId(),
									identifierType.getDescription(), identifierType.getCreatedBy(), identifierType.getCreatedDateTime(),
									identifierType.getUpdatedBy(), identifierType.getUpdatedDateTime()).executeWithKey(qIdentifierType.identifierTypeId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return identifierTypeId;
	}

}
