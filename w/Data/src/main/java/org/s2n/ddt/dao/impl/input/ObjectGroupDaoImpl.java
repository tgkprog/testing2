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

import org.s2n.ddt.dao.input.ObjectGroupDao;
import org.s2n.ddt.dao.input.ObjectsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QObjectGroup;
import org.s2n.ddt.pojo.input.ObjectGroup;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ObjectGroupDao
 */
public class ObjectGroupDaoImpl implements ObjectGroupDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ObjectGroupDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QObjectGroup qObjectGroup = QObjectGroup.ObjectGroup;
	private ObjectsDao objDao;

	public void setObjDao(ObjectsDao objDao) {
		this.objDao = objDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertObjectGroupDetails(final ObjectGroup objectGroup) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + objectGroup.getObjectGroupId());
		try {
			result = template.insert(qObjectGroup, new SqlInsertCallback() {
				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qObjectGroup.objectGroupId, qObjectGroup.objectGroupName, qObjectGroup.description, qObjectGroup.appID,
									qObjectGroup.screenId, qObjectGroup.createdBy, qObjectGroup.createdDateTime, qObjectGroup.updatedBy,
									qObjectGroup.updatedDateTime)
							.values(objectGroup.getObjectGroupId(), objectGroup.getObjectGroupName(), objectGroup.getDescription(),
									objectGroup.getAppId(), objectGroup.getScreenId(), objectGroup.getCreatedBy(), objectGroup.getCreatedDateTime(),
									objectGroup.getUpdatedBy(), objectGroup.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public ObjectGroup getObjectGroupDetailsById(int objectGroupId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectGroup).where(qObjectGroup.objectGroupId.eq(objectGroupId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingObjectGroupProjection(qObjectGroup.objectGroupId, qObjectGroup.objectGroupName,
					qObjectGroup.description, qObjectGroup.appID, qObjectGroup.screenId, qObjectGroup.createdDateTime, qObjectGroup.updatedBy,
					qObjectGroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingObjectGroupProjection extends MappingProjection<ObjectGroup> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingObjectGroupProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingObjectGroupProjection(Expression<?>... args) {
			super(ObjectGroup.class, args);
		}

		@Override
		protected ObjectGroup map(Tuple tuple) {
			if (qObjectGroup != null) {
				ObjectGroup objectGroup = new ObjectGroup();
				objectGroup.setObjectGroupId(new BigDecimal(tuple.get(qObjectGroup.objectGroupId)));
				objectGroup.setObjectGroupName(tuple.get(qObjectGroup.objectGroupName));
				objectGroup.setDescription(tuple.get(qObjectGroup.description));
				objectGroup.setAppId(new BigDecimal(tuple.get(qObjectGroup.appID)));
				objectGroup.setScreenId(new BigDecimal(tuple.get(qObjectGroup.screenId)));
				objectGroup.setCreatedBy(tuple.get(qObjectGroup.createdBy));
				objectGroup.setCreatedDateTime(tuple.get(qObjectGroup.createdDateTime));
				objectGroup.setUpdatedBy(tuple.get(qObjectGroup.updatedBy));
				objectGroup.setUpdatedDateTime(tuple.get(qObjectGroup.updatedDateTime));
				if (logger.isDebugEnabled()) {
					logger.debug("returning data object : " + objectGroup);
				}
				return objectGroup;
			}
			return null;
		}

	}

	public ObjectGroup getObjectGroupWithObjectsById(int objectGroupId) throws DataAccessException {
		try {
			ObjectGroup group = getObjectGroupDetailsById(objectGroupId);
			group.setObjectsList(objDao.getObjectsByGroupId(objectGroupId));
			logger.info("returning data object : " + group);
			return group;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<ObjectGroup> getObjectGroupsByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectGroup).where(qObjectGroup.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingObjectGroupProjection(qObjectGroup.objectGroupId, qObjectGroup.objectGroupName,
					qObjectGroup.description, qObjectGroup.appID, qObjectGroup.screenId, qObjectGroup.createdDateTime, qObjectGroup.updatedBy,
					qObjectGroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<ObjectGroup> getObjectGroupWithObjectsByScreenId(int screenId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectGroup).where(qObjectGroup.screenId.eq(screenId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingObjectGroupProjection(qObjectGroup.objectGroupId, qObjectGroup.objectGroupName,
					qObjectGroup.description, qObjectGroup.appID, qObjectGroup.screenId, qObjectGroup.createdDateTime, qObjectGroup.updatedBy,
					qObjectGroup.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getObjectGroupWithObjectsByName(String Name) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qObjectGroup).where(qObjectGroup.objectGroupName.eq(Name));
			logger.info("generated query : " + sqlQuery);
			Integer objectGroupId = template.queryForObject(sqlQuery, qObjectGroup.objectGroupId);
			if (objectGroupId != null) {
				return objectGroupId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public int getObjectGroupsIdByAppId(ObjectGroup objectGroup) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qObjectGroup)
					.where(qObjectGroup.appID.eq(objectGroup.getAppId().intValue()).and(qObjectGroup.objectGroupName.eq(objectGroup.getObjectGroupName())));
			Integer objectGroupId = template.queryForObject(sqlQuery, qObjectGroup.objectGroupId);
			if (objectGroupId != null) {
				return objectGroupId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateObjectGroups(final ObjectGroup objectGroup) throws DataAccessException {
		try {
			return template.update(qObjectGroup, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qObjectGroup.objectGroupId.eq(objectGroup.getObjectGroupId().intValue()))
							.set(qObjectGroup.objectGroupName, objectGroup.getObjectGroupName())
							.set(qObjectGroup.description, objectGroup.getDescription()).set(qObjectGroup.updatedBy, objectGroup.getUpdatedBy())
							.set(qObjectGroup.updatedDateTime, new Date(objectGroup.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertObjectGroupGetKey(final ObjectGroup objectGroup) throws DataAccessException {
		int objectGroupId = 0;
		try {
			objectGroupId = template.insertWithKey(qObjectGroup, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qObjectGroup.objectGroupId, qObjectGroup.objectGroupName, qObjectGroup.description, qObjectGroup.appID,
									qObjectGroup.screenId, qObjectGroup.createdBy, qObjectGroup.createdDateTime, qObjectGroup.updatedBy,
									qObjectGroup.updatedDateTime)
							.values(objectGroup.getObjectGroupId(), objectGroup.getObjectGroupName(), objectGroup.getDescription(),
									objectGroup.getAppId(), objectGroup.getScreenId(), objectGroup.getCreatedBy(), objectGroup.getCreatedDateTime(),
									objectGroup.getUpdatedBy(), objectGroup.getUpdatedDateTime()).executeWithKey(qObjectGroup.objectGroupId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return objectGroupId;
	}

}
