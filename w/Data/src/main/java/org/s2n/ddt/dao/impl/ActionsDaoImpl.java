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

import org.s2n.ddt.dao.ActionsDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QActions;
import org.s2n.ddt.pojo.Actions;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class ActionsDaoImpl implements ActionsDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ActionsDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QActions qAction = QActions.Actions;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	private class MappingActionsProjection extends MappingProjection<Actions> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingActionsProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingActionsProjection(Expression<?>... args) {
			super(Actions.class, args);
		}

		@Override
		protected Actions map(Tuple tuple) {
			Actions actions = new Actions();
			actions.setActionId(new BigDecimal(tuple.get(qAction.actionId)));
			actions.setActionName(tuple.get(qAction.actionName));
			actions.setDescription(tuple.get(qAction.description));
			actions.setCreatedBy(tuple.get(qAction.createdBy));
			actions.setCreatedDateTime(tuple.get(qAction.createdDateTime));
			actions.setUpdatedBy(tuple.get(qAction.updatedBy));
			actions.setUpdatedDateTime(tuple.get(qAction.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + actions);
			}
			return actions;
		}
	}

	public Actions getActionsById(int actionid) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qAction).where(qAction.actionId.eq(actionid));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingActionsProjection(qAction.actionId, qAction.actionName, qAction.description,
					qAction.createdBy, qAction.createdDateTime, qAction.updatedBy, qAction.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public long insertActionsDetails(final Actions actions) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Actions: " + actions);
		try {
			result = template.insert(qAction, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qAction.actionId, qAction.actionName, qAction.description, qAction.createdBy, qAction.createdDateTime,
									qAction.updatedBy, qAction.updatedDateTime)
							.values(actions.getActionId(), actions.getActionName(), actions.getDescription(), actions.getCreatedBy(),
									actions.getCreatedDateTime(), actions.getUpdatedBy(), actions.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public int getActionIdByActionName(String actionName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qAction).where(qAction.actionName.eq(actionName));
			Integer actionId = template.queryForObject(sqlQuery, qAction.actionId);
			if (actionId != null) {
				return actionId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateActions(final Actions actions) throws DataAccessException {
		try {
			return template.update(qAction, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qAction.actionId.eq(actions.getActionId().intValue())).set(qAction.actionName, actions.getActionName())
							.set(qAction.description, actions.getDescription()).set(qAction.updatedBy, actions.getUpdatedBy())
							.set(qAction.updatedDateTime, new Date(actions.getUpdatedDateTime().getTime())).execute();
				}

			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public List<String> getAllActionNames() throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qAction);
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, qAction.actionName);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int insertActionsGetKey(final Actions actions) throws DataAccessException {
		int actionId = 0;
		try {
			actionId = template.insertWithKey(qAction, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qAction.actionId, qAction.actionName, qAction.description, qAction.createdBy, qAction.createdDateTime,
									qAction.updatedBy, qAction.updatedDateTime)
							.values(actions.getActionId(), actions.getActionName(), actions.getDescription(), actions.getCreatedBy(),
									actions.getCreatedDateTime(), actions.getUpdatedBy(), actions.getUpdatedDateTime()).executeWithKey(qAction.actionId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return actionId;
	}

}
