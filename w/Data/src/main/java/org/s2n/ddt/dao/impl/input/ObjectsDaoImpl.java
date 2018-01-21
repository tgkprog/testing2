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

import org.s2n.ddt.dao.input.ObjectsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QObjects;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ObjectsId;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ObjectsDao
 */
public class ObjectsDaoImpl implements ObjectsDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ObjectsDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QObjects qObjects = QObjects.Objects;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertObjectDetails(final Objects objects) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object id : " + objects.getObjectsId());
		try {
			result = template.insert(qObjects, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					ObjectsId id = objects.getObjectsId();
					return sqlInsertClause
							.columns(qObjects.objectId, qObjects.objName, qObjects.description, qObjects.objectGroupId, qObjects.objectTypeId,
									qObjects.identifierTypeId, qObjects.identifier, qObjects.appID, qObjects.createdBy, qObjects.createdDateTime,
									qObjects.updatedBy, qObjects.updatedDateTime)
							.values(id.getObjectId(), id.getObjectName(), id.getDescription(), id.getObjectGroupId(), id.getObjectTypeId(),
									id.getIdentifierTypeId(), id.getIndentifier(), id.getAppId(), id.getCreatedBy(), id.getCreatedDateTime(),
									id.getUpdatedBy(), id.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	private class MappingObjectsProjection extends MappingProjection<Objects> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingObjectsProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingObjectsProjection(Expression<?>... args) {
			super(Objects.class, args);
		}

		@Override
		protected Objects map(Tuple tuple) {
			Objects objects = new Objects();
			objects.setObjectsId(getObjectsIdMapping(tuple));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + objects);
			}
			return objects;
		}

		private ObjectsId getObjectsIdMapping(Tuple tuple) {
			ObjectsId id = new ObjectsId();
			id.setAppId(new BigDecimal(tuple.get(qObjects.appID)));
			id.setCreatedBy(tuple.get(qObjects.createdBy));
			id.setCreatedDateTime(tuple.get(qObjects.createdDateTime));
			id.setDescription(tuple.get(qObjects.description));
			id.setIdentifierTypeId(new BigDecimal(tuple.get(qObjects.identifierTypeId)));
			id.setIndentifier(tuple.get(qObjects.identifier));
			id.setObjectGroupId(new BigDecimal(tuple.get(qObjects.objectGroupId)));
			id.setObjectId(new BigDecimal(tuple.get(qObjects.objectId)));
			id.setObjectTypeId(new BigDecimal(tuple.get(qObjects.objectTypeId)));
			id.setObjectName(tuple.get(qObjects.objName));
			id.setUpdatedBy(tuple.get(qObjects.updatedBy));
			id.setUpdatedDateTime(tuple.get(qObjects.updatedDateTime));
			return id;
		}
	}

	public Objects getObjectsDetailsById(int objectId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.objectId.eq(objectId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingObjectsProjection(qObjects.objectId, qObjects.appID, qObjects.createdBy,
					qObjects.createdDateTime, qObjects.description, qObjects.identifier, qObjects.identifierTypeId, qObjects.objectGroupId,
					qObjects.objectId, qObjects.objectTypeId, qObjects.objName, qObjects.updatedBy, qObjects.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Objects> getObjectsByGroupId(int groupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.objectGroupId.eq(groupId));
			logger.info("generated query : " + sqlQuery);
			return getObjectsByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getObjectsIdOnlyByName(String objName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.objName.eq(objName));
			logger.info("generated query : " + sqlQuery);
			Integer objectId = template.queryForObject(sqlQuery, qObjects.objectId);
			if (objectId != null) {
				return objectId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public List<Objects> getObjectsByTypeId(int objTypeId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.objectTypeId.eq(objTypeId));
			logger.info("generated query : " + sqlQuery);
			return getObjectsByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Objects> getObjectsByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return getObjectsByQuery(sqlQuery);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private List<Objects> getObjectsByQuery(SQLQuery sqlQuery) {
		return template.query(sqlQuery, new MappingObjectsProjection(qObjects.objectId, qObjects.appID, qObjects.createdBy, qObjects.createdDateTime,
				qObjects.description, qObjects.identifier, qObjects.identifierTypeId, qObjects.objectGroupId, qObjects.objectId, qObjects.objectTypeId,
				qObjects.objName, qObjects.updatedBy, qObjects.updatedDateTime));
	}

	public List<Objects> getObjectsDetailsByIdentifiertypeId(int identifiertypeid) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjects).where(qObjects.identifierTypeId.eq(identifiertypeid));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingObjectsProjection(qObjects.objectId, qObjects.appID, qObjects.createdBy, qObjects.createdDateTime,
					qObjects.description, qObjects.identifier, qObjects.identifierTypeId, qObjects.objectGroupId, qObjects.objectId,
					qObjects.objectTypeId, qObjects.objName, qObjects.updatedBy, qObjects.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getObjectsIdByGroupId(Objects objects) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qObjects)
					.where(qObjects.objName.eq(objects.getObjectsId().getObjectName())
							.and(qObjects.objectGroupId.eq(objects.getObjectsId().getObjectGroupId().intValue()))
							.and(qObjects.identifierTypeId.eq(objects.getObjectsId().getIdentifierTypeId().intValue()))
							.and(qObjects.objectTypeId.eq(objects.getObjectsId().getObjectTypeId().intValue()))
							.and(qObjects.appID.eq(objects.getObjectsId().getAppId().intValue())));
			Integer objectId = template.queryForObject(sqlQuery, qObjects.objectId);
			if (objectId != null) {
				return objectId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;

	}

	public long updateObjects(final Objects objects) throws DataAccessException {
		try {
			return template.update(qObjects, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qObjects.objectId.eq(objects.getObjectsId().getObjectId().intValue()))
							.set(qObjects.objName, objects.getObjectsId().getObjectName())
							.set(qObjects.description, objects.getObjectsId().getDescription())
							.set(qObjects.updatedBy, objects.getObjectsId().getUpdatedBy())
							.set(qObjects.updatedDateTime, new Date(objects.getObjectsId().getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertObjectsGetKey(final Objects objects) throws DataAccessException {
		int objectId = 0;
		try {
			objectId = template.insertWithKey(qObjects, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					ObjectsId id = objects.getObjectsId();
					return insert
							.columns(qObjects.objectId, qObjects.objName, qObjects.description, qObjects.objectGroupId, qObjects.objectTypeId,
									qObjects.identifierTypeId, qObjects.identifier, qObjects.appID, qObjects.createdBy, qObjects.createdDateTime,
									qObjects.updatedBy, qObjects.updatedDateTime)
							.values(id.getObjectId(), id.getObjectName(), id.getDescription(), id.getObjectGroupId(), id.getObjectTypeId(),
									id.getIdentifierTypeId(), id.getIndentifier(), id.getAppId(), id.getCreatedBy(), id.getCreatedDateTime(),
									id.getUpdatedBy(), id.getUpdatedDateTime()).executeWithKey(qObjects.objectId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return objectId;
	}

}
