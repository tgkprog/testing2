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
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QFeature;
import org.s2n.ddt.generated.pojo.QFunctional;
import org.s2n.ddt.pojo.Feature;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class FeatureDaoImpl implements FeatureDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(FeatureDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QFeature qFeature = QFeature.Feature;
	private QFunctional qFunctional = QFunctional.Functional;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	@Override
	public Feature getFeatureDetailsById(int featureId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFeature).where(qFeature.featureId.eq(featureId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingFeatureProjection(qFeature.featureId, qFeature.featureName, qFeature.description,
					qFeature.functionalId, qFeature.createdBy, qFeature.createdDateTime, qFeature.updatedBy, qFeature.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public List<Feature> getAllFeatureDetails() throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFeature).where(qFeature.functionalId.eq(qFunctional.functionalId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingFeatureProjection(qFeature.featureId, qFeature.featureName, qFeature.description,
					qFeature.functionalId, qFeature.createdBy, qFeature.createdDateTime, qFeature.updatedBy, qFeature.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingFeatureProjection extends MappingProjection<Feature> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingFeatureProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingFeatureProjection(Expression<?>... args) {
			super(Feature.class, args);
		}

		@Override
		protected Feature map(Tuple tuple) {
			Feature feature = new Feature();
			feature.setFeatureId(new BigDecimal(tuple.get(qFeature.featureId)));
			feature.setFeatureName((tuple.get(qFeature.featureName)));
			feature.setDescription(tuple.get(qFeature.description));
			feature.setFunctionalId(new BigDecimal(tuple.get(qFeature.functionalId)));
			feature.setCreatedBy(tuple.get(qFeature.createdBy));
			feature.setCreatedDateTime(tuple.get(qFeature.createdDateTime));
			feature.setUpdatedBy(tuple.get(qFeature.updatedBy));
			feature.setUpdatedDateTime(tuple.get(qFeature.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + feature);
			}
			return feature;
		}

	}

	public List<Feature> getFeatureByFunctionalId(int functionalId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFeature).where(qFeature.functionalId.eq(functionalId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingFeatureProjection(qFeature.featureId, qFeature.featureName, qFeature.description,
					qFeature.functionalId, qFeature.createdBy, qFeature.createdDateTime, qFeature.updatedBy, qFeature.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getFeatureIdByName(Feature feature) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFeature)
					.where(qFeature.functionalId.eq(feature.getFunctionalId().intValue()).and(qFeature.featureName.eq(feature.getFeatureName())));
			Integer featureId = template.queryForObject(sqlQuery, qFeature.featureId);
			if (featureId != null) {
				return featureId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertFeature(final Feature feature) throws DataAccessException {
		Long result = 0L;
		logger.info("started inserting data for feature id : " + feature.getFeatureId());
		try {
			result = template.insert(qFeature, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qFeature.featureId, qFeature.featureName, qFeature.description, qFeature.functionalId, qFeature.createdBy,
									qFeature.createdDateTime, qFeature.updatedBy, qFeature.updatedDateTime)
							.values(feature.getFeatureId(), feature.getFeatureName(), feature.getDescription(), feature.getFunctionalId(),
									feature.getCreatedBy(), feature.getCreatedDateTime(), feature.getUpdatedBy(), feature.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public long updateFeature(final Feature feature) throws DataAccessException {
		try {
			return template.update(qFeature, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qFeature.featureId.eq(feature.getFeatureId().intValue()))
							.set(qFeature.featureName, feature.getFeatureName()).set(qFeature.description, feature.getDescription())
							.set(qFeature.updatedBy, feature.getUpdatedBy())
							.set(qFeature.updatedDateTime, new Date(feature.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getFeatureIdOnlyByName(String featureName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qFeature).where(qFeature.featureName.eq(featureName));
			Integer featureId = template.queryForObject(sqlQuery, qFeature.featureId);
			if (featureId != null) {
				return featureId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertFeatureGetKey(final Feature feature) throws DataAccessException {
		int featureId = 0;
		try {
			featureId = template.insertWithKey(qFeature, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qFeature.featureId, qFeature.featureName, qFeature.description, qFeature.functionalId, qFeature.createdBy,
									qFeature.createdDateTime, qFeature.updatedBy, qFeature.updatedDateTime)
							.values(feature.getFeatureId(), feature.getFeatureName(), feature.getDescription(), feature.getFunctionalId(),
									feature.getCreatedBy(), feature.getCreatedDateTime(), feature.getUpdatedBy(), feature.getUpdatedDateTime())
							.executeWithKey(qFeature.featureId);
				}
			});
			logger.info("Genrated feature Id : " + featureId);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return featureId;
	}

}
