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

import org.s2n.ddt.dao.ActionsDao;
import org.s2n.ddt.dao.input.ObjectTypeDao;
import org.s2n.ddt.dao.input.ObjectsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QObjectType;
import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.ObjectTypeId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ObjectTypeDao
 */
public class ObjectTypeDaoImpl implements ObjectTypeDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ObjectTypeDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QObjectType qObjectType = QObjectType.ObjectType;
	private ActionsDao actionsDao;
	private ObjectsDao objDao;

	public void setActionsDao(ActionsDao actionsDao) {
		this.actionsDao = actionsDao;
	}

	public void setObjDao(ObjectsDao objDao) {
		this.objDao = objDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertObjectTypeDetails(final ObjectType objectType) throws DataAccessException {
		logger.info("started inserting data for Object Type id : " + objectType.getObjectTypeId().getObjectTypeId());
		long result = 0L;
		try {
			result = template.insert(qObjectType, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					ObjectTypeId objectTypeId = objectType.getObjectTypeId();
					return sqlInsertClause
							.columns(qObjectType.objectTypeId, qObjectType.objectTypeName, qObjectType.appID, qObjectType.description,
									qObjectType.defaultActionId, qObjectType.createdBy, qObjectType.createdDateTime, qObjectType.updatedBy,
									qObjectType.updatedDateTime)
							.values(objectTypeId.getObjectTypeId(), objectTypeId.getObjectTypeName(), objectTypeId.getAppId(),
									objectTypeId.getDescription(), objectTypeId.getDefaultActionId(), objectTypeId.getCreatedBy(),
									objectTypeId.getCreatedDateTime(), objectTypeId.getUpdatedBy(), objectTypeId.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingObjectTypeProjection extends MappingProjection<ObjectType> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingObjectTypeProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingObjectTypeProjection(Expression<?>... args) {
			super(ObjectType.class, args);
		}

		@Override
		protected ObjectType map(Tuple tuple) {
			ObjectType type = new ObjectType();
			type.setObjectTypeId(getObjectTypeIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + type);
			}
			return type;
		}

		private ObjectTypeId getObjectTypeIdMapping(Tuple tuple) {
			ObjectTypeId id = new ObjectTypeId();
			id.setAppId(new BigDecimal(tuple.get(qObjectType.appID)));
			id.setCreatedBy(tuple.get(qObjectType.createdBy));
			id.setCreatedDateTime(tuple.get(qObjectType.createdDateTime));
			id.setDefaultActionId(new BigDecimal(tuple.get(qObjectType.defaultActionId)));
			id.setDescription(tuple.get(qObjectType.description));
			id.setObjectTypeId(new BigDecimal(tuple.get(qObjectType.objectTypeId)));
			id.setObjectTypeName(tuple.get(qObjectType.objectTypeName));
			id.setUpdatedBy(tuple.get(qObjectType.updatedBy));
			id.setUpdatedDateTime(tuple.get(qObjectType.updatedDateTime));
			return id;
		}
	}

	public ObjectType getObjectTypeById(int objectId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectType).where(qObjectType.objectTypeId.eq(objectId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingObjectTypeProjection(qObjectType.objectTypeId, qObjectType.appID, qObjectType.createdBy,
					qObjectType.createdDateTime, qObjectType.defaultActionId, qObjectType.description, qObjectType.objectTypeName, qObjectType.updatedBy,
					qObjectType.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public ObjectType getMinimalDependentObjectsByTypeId(int objTypeId) throws DataAccessException {
		try {
			ObjectType objType = getObjectTypeById(objTypeId);
			objType.setObjectsList(objDao.getObjectsByTypeId(objTypeId));
			objType.setActions(actionsDao.getActionsById(objType.getObjectTypeId().getDefaultActionId().intValue()));
			logger.info("returning data object : " + objType);
			return objType;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<ObjectType> getObjectTypesByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectType).where(qObjectType.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingObjectTypeProjection(qObjectType.objectTypeId, qObjectType.appID, qObjectType.createdBy,
					qObjectType.createdDateTime, qObjectType.defaultActionId, qObjectType.description, qObjectType.objectTypeName, qObjectType.updatedBy,
					qObjectType.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<ObjectType> getObjectTypeBydefaultActionId(int defaultActionId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectType).where(qObjectType.defaultActionId.eq(defaultActionId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingObjectTypeProjection(qObjectType.objectTypeId, qObjectType.appID, qObjectType.createdBy,
					qObjectType.createdDateTime, qObjectType.defaultActionId, qObjectType.description, qObjectType.objectTypeName, qObjectType.updatedBy,
					qObjectType.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getObjectTypeIdByName(ObjectType objectType) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qObjectType)
					.where(qObjectType.objectTypeName.eq(objectType.getObjectTypeId().getObjectTypeName()).and(
							qObjectType.defaultActionId.eq(objectType.getObjectTypeId().getDefaultActionId().intValue())));
			Integer objectTypeId = template.queryForObject(sqlQuery, qObjectType.objectTypeId);
			if (objectTypeId != null) {
				return objectTypeId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateObjects(final ObjectType objectType) throws DataAccessException {
		try {
			return template.update(qObjectType, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					ObjectTypeId id = objectType.getObjectTypeId();
					return sqlUpdateClause
							.where(qObjectType.appID.eq(id.getAppId().intValue()).and(qObjectType.objectTypeName.eq(id.getObjectTypeName()))
									.and(qObjectType.defaultActionId.eq(id.getDefaultActionId().intValue())))
							.set(qObjectType.description, id.getDescription()).set(qObjectType.updatedBy, id.getUpdatedBy())
							.set(qObjectType.objectTypeName, id.getObjectTypeName())
							.set(qObjectType.updatedDateTime, new Date(id.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertObjectTypeGetKey(final ObjectType objectType) throws DataAccessException {
		int objectTypeId = 0;
		try {
			objectTypeId = template.insertWithKey(qObjectType, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					ObjectTypeId objectTypeId = objectType.getObjectTypeId();
					return insert
							.columns(qObjectType.objectTypeId, qObjectType.objectTypeName, qObjectType.appID, qObjectType.description,
									qObjectType.defaultActionId, qObjectType.createdBy, qObjectType.createdDateTime, qObjectType.updatedBy,
									qObjectType.updatedDateTime)
							.values(objectTypeId.getObjectTypeId(), objectTypeId.getObjectTypeName(), objectTypeId.getAppId(),
									objectTypeId.getDescription(), objectTypeId.getDefaultActionId(), objectTypeId.getCreatedBy(),
									objectTypeId.getCreatedDateTime(), objectTypeId.getUpdatedBy(), objectTypeId.getUpdatedDateTime())
							.executeWithKey(qObjectType.objectTypeId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return objectTypeId;
	}

}
