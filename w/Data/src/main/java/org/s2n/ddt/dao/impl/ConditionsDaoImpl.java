package org.s2n.ddt.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlDeleteCallback;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.ConditionsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QConditions;
import org.s2n.ddt.pojo.Conditions;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class ConditionsDaoImpl implements ConditionsDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConditionsDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QConditions qConditions = QConditions.Conditions;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingConditionProjection extends MappingProjection<Conditions> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingConditionProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingConditionProjection(Expression<?>... args) {
			super(Conditions.class, args);
		}

		@Override
		protected Conditions map(Tuple tuple) {
			Conditions conditions = new Conditions();
			conditions.setConditionId(new BigDecimal(tuple.get(qConditions.condId)));
			conditions.setConditionName(tuple.get(qConditions.condName));
			conditions.setDescription(tuple.get(qConditions.description));
			conditions.setExpression(tuple.get(qConditions.expression));
			conditions.setConditionGroupId(new BigDecimal(tuple.get(qConditions.condGrpId)));
			conditions.setCreatedBy(tuple.get(qConditions.createdBy));
			conditions.setCreatedDateTime(tuple.get(qConditions.createdDateTime));
			conditions.setUpdatedBy(tuple.get(qConditions.updatedBy));
			conditions.setUpdatedDateTime(tuple.get(qConditions.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + conditions);
			}
			return conditions;
		}
	}

	public Conditions getConditionsById(int condId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qConditions).where(qConditions.condId.eq(condId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingConditionProjection(qConditions.condId, qConditions.condName, qConditions.description,
					qConditions.expression, qConditions.condGrpId, qConditions.createdBy, qConditions.createdDateTime, qConditions.updatedBy,
					qConditions.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public Conditions getConditionsByConditionGroupId(int condGrpId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qConditions).where(qConditions.condGrpId.eq(condGrpId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingConditionProjection(qConditions.condId, qConditions.condName, qConditions.description,
					qConditions.expression, qConditions.condGrpId, qConditions.createdBy, qConditions.createdDateTime, qConditions.updatedBy,
					qConditions.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getConditionsIdByName(Conditions conditions) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template
					.newSqlQuery()
					.from(qConditions)
					.where(qConditions.condGrpId.eq(conditions.getConditionGroupId().intValue()).and(
							qConditions.condName.eq(conditions.getConditionName())));
			Integer conditionsId = template.queryForObject(sqlQuery, qConditions.condId);
			if (conditionsId != null) {
				return conditionsId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertConditions(final Conditions conditions) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Conditions : " + conditions.getConditionId());
		try {
			result = template.insert(qConditions, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qConditions.condId, qConditions.condName, qConditions.description, qConditions.expression, qConditions.condGrpId,
									qConditions.createdBy, qConditions.createdDateTime, qConditions.updatedBy, qConditions.updatedDateTime)
							.values(conditions.getConditionId(), conditions.getConditionName(), conditions.getDescription(), conditions.getExpression(),
									conditions.getConditionGroupId(), conditions.getCreatedBy(), conditions.getCreatedDateTime(),
									conditions.getUpdatedBy(), conditions.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public long updateConditions(final Conditions conditions) throws DataAccessException {
		try {
			return template.update(qConditions, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qConditions.condId.eq(conditions.getConditionId().intValue()))
							.set(qConditions.condName, conditions.getConditionName()).set(qConditions.description, conditions.getDescription())
							.set(qConditions.expression, conditions.getExpression()).set(qConditions.updatedBy, conditions.getUpdatedBy())
							.set(qConditions.updatedDateTime, new Date(conditions.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public long deleteConditionsByGroupId(final int CondGrpId) throws DataAccessException {
		long result = 0L;
		logger.info("deleting the records where condition group id : " + CondGrpId);
		try {
			result = template.delete(qConditions, new SqlDeleteCallback() {

				@Override
				public long doInSqlDeleteClause(SQLDeleteClause delete) {
					return delete.where(qConditions.condGrpId.eq(CondGrpId)).execute();
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
	public int insertConditionsGetKey(final Conditions conditions) throws DataAccessException {
		int condId = 0;
		try {
			condId = template.insertWithKey(qConditions, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qConditions.condId, qConditions.condName, qConditions.description, qConditions.expression, qConditions.condGrpId,
									qConditions.createdBy, qConditions.createdDateTime, qConditions.updatedBy, qConditions.updatedDateTime)
							.values(conditions.getConditionId(), conditions.getConditionName(), conditions.getDescription(), conditions.getExpression(),
									conditions.getConditionGroupId(), conditions.getCreatedBy(), conditions.getCreatedDateTime(),
									conditions.getUpdatedBy(), conditions.getUpdatedDateTime()).executeWithKey(qConditions.condId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return condId;
	}

}
