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

import org.s2n.ddt.dao.FeatureDao;
import org.s2n.ddt.dao.FunctionalDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QApplication;
import org.s2n.ddt.generated.pojo.QFunctional;
import org.s2n.ddt.pojo.Functional;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class FunctionalDaoImpl implements FunctionalDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(FunctionalDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QFunctional qFunctional = QFunctional.Functional;
	private QApplication qApplication = QApplication.application;
	private FeatureDao featureDao;

	public void setFeatureDao(FeatureDao featureDao) {
		this.featureDao = featureDao;
	}

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingFunctionalProjection extends MappingProjection<Functional> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingFunctionalProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingFunctionalProjection(Expression<?>... args) {
			super(Functional.class, args);
		}

		@Override
		protected Functional map(Tuple tuple) {
			Functional functional = new Functional();
			functional.setFunctionalId(new BigDecimal(tuple.get(qFunctional.functionalId)));
			functional.setFunctionalName(tuple.get(qFunctional.functionalName));
			functional.setDescription(tuple.get(qFunctional.description));
			functional.setAppId(new BigDecimal(tuple.get(qFunctional.appID)));
			functional.setCreatedBy(tuple.get(qFunctional.createdBy));
			functional.setCreatedDateTime(tuple.get(qFunctional.createdDateTime));
			functional.setUpdatedBy(tuple.get(qFunctional.updatedBy));
			functional.setUpdatedDateTime(tuple.get(qFunctional.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + functional);
			}
			return functional;
		}
	}

	public Functional getFunctionalDetailsById(int functionalId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFunctional).where(qFunctional.functionalId.eq(functionalId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingFunctionalProjection(qFunctional.functionalId, qFunctional.functionalName,
					qFunctional.description, qFunctional.createdBy, qFunctional.createdDateTime, qFunctional.updatedBy, qFunctional.updatedDateTime,
					qFunctional.appID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Functional> getAllFunctionalDetails() throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFunctional).from(qApplication).where(qFunctional.appID.eq(qApplication.appID));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingFunctionalProjection(qFunctional.functionalId, qFunctional.functionalName, qFunctional.description,
					qFunctional.createdBy, qFunctional.createdDateTime, qFunctional.updatedBy, qFunctional.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public List<Functional> getFunctionalsByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFunctional).where(qFunctional.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingFunctionalProjection(qFunctional.functionalId, qFunctional.functionalName, qFunctional.description,
					qFunctional.createdBy, qFunctional.createdDateTime, qFunctional.updatedBy, qFunctional.updatedDateTime, qFunctional.appID));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public Functional getFunctionalDetailsTillFeature(int functionalId) throws DataAccessException {
		try {
			Functional functional = getFunctionalDetailsById(functionalId);
			functional.setFeatures(featureDao.getFeatureByFunctionalId(functionalId));
			logger.info("returning data object : " + functional);
			return functional;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getFunctionalDetailsByName(int appId, String functionalName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFunctional)
					.where((qFunctional.appID.eq(appId)).and(qFunctional.functionalName.eq(functionalName)));
			Integer functionalId = template.queryForObject(sqlQuery, qFunctional.functionalId);
			if (functionalId != null) {
				return functionalId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertFunctionalDetails(final Functional functional) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for object group id : " + functional.getFunctionalId());
		try {
			result = template.insert(qFunctional, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qFunctional.functionalId, qFunctional.functionalName, qFunctional.description, qFunctional.appID,
									qFunctional.createdBy, qFunctional.createdDateTime, qFunctional.updatedBy, qFunctional.updatedDateTime)
							.values(functional.getFunctionalId(), functional.getFunctionalName(), functional.getDescription(), functional.getAppId(),
									functional.getCreatedBy(), functional.getCreatedDateTime(), functional.getUpdatedBy(), functional.getUpdatedDateTime())
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

	public long updateFunctionalDetails(final Functional functional) throws DataAccessException {
		try {
			return template.update(qFunctional, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qFunctional.functionalId.eq(functional.getFunctionalId().intValue()))
							.set(qFunctional.functionalName, functional.getFunctionalName()).set(qFunctional.description, functional.getDescription())
							.set(qFunctional.updatedBy, functional.getUpdatedBy())
							.set(qFunctional.updatedDateTime, new Date(functional.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getFunctionalIdOnlyByName(String functionalName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFunctional).where(qFunctional.functionalName.eq(functionalName));
			Integer functionalId = template.queryForObject(sqlQuery, qFunctional.functionalId);
			if (functionalId != null) {
				return functionalId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertFunctionalGetKey(final Functional functional) throws DataAccessException {
		int functionalId = 0;
		try {
			functionalId = template.insertWithKey(qFunctional, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qFunctional.functionalId, qFunctional.functionalName, qFunctional.description, qFunctional.appID,
									qFunctional.createdBy, qFunctional.createdDateTime, qFunctional.updatedBy, qFunctional.updatedDateTime)
							.values(functional.getFunctionalId(), functional.getFunctionalName(), functional.getDescription(), functional.getAppId(),
									functional.getCreatedBy(), functional.getCreatedDateTime(), functional.getUpdatedBy(), functional.getUpdatedDateTime())
							.executeWithKey(qFunctional.functionalId);

				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return functionalId;
	}

}