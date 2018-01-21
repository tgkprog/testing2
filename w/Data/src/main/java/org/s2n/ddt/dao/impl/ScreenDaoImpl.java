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

import org.s2n.ddt.dao.ScreenDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QScreen;
import org.s2n.ddt.pojo.Screen;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

/**
 * The implementation class for ScreenDao
 */
public class ScreenDaoImpl implements ScreenDao {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ScreenDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QScreen qScreen = QScreen.Screen;

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public Screen getScreenById(int screenId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qScreen).where(qScreen.screenId.eq(screenId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingScreenProjection(qScreen.screenId, qScreen.appID, qScreen.createdBy,
					qScreen.createdDateTime, qScreen.description, qScreen.screenName, qScreen.updatedBy, qScreen.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingScreenProjection extends MappingProjection<Screen> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingScreenProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingScreenProjection(Expression<?>... args) {
			super(Screen.class, args);
		}

		@Override
		protected Screen map(Tuple tuple) {
			Screen screen = new Screen();
			screen.setScreenId(new BigDecimal(tuple.get(qScreen.screenId)));
			screen.setScreenName(tuple.get(qScreen.screenName));
			screen.setDescription(tuple.get(qScreen.description));
			screen.setCreatedBy(tuple.get(qScreen.createdBy));
			screen.setCreatedDateTime(tuple.get(qScreen.createdDateTime));
			screen.setUpdatedBy(tuple.get(qScreen.updatedBy));
			screen.setUpdatedDateTime(tuple.get(qScreen.updatedDateTime));
			screen.setAppId(new BigDecimal(tuple.get(qScreen.appID)));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + screen);
			}
			return screen;
		}
	}

	public List<Screen> getScreensByAppId(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qScreen).where(qScreen.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.query(sqlQuery, new MappingScreenProjection(qScreen.screenId, qScreen.appID, qScreen.createdBy, qScreen.createdDateTime,
					qScreen.description, qScreen.screenName, qScreen.updatedBy, qScreen.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getScreenIdByName(Screen screen) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qScreen)
					.where(qScreen.appID.eq(screen.getAppId().intValue()).and(qScreen.screenName.eq(screen.getScreenName())));
			Integer screenId = template.queryForObject(sqlQuery, qScreen.screenId);
			if (screenId != null) {
				return screenId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long insertScreenDetails(final Screen screen) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Screen : " + screen.getScreenId());
		try {
			result = template.insert(qScreen, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qScreen.screenId, qScreen.screenName, qScreen.description, qScreen.appID, qScreen.createdBy, qScreen.createdDateTime,
									qScreen.updatedBy, qScreen.updatedDateTime)
							.values(screen.getScreenId(), screen.getScreenName(), screen.getDescription(), screen.getAppId(), screen.getCreatedBy(),
									screen.getCreatedDateTime(), screen.getUpdatedBy(), screen.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public long updateScreen(final Screen screen) throws DataAccessException {
		try {
			return template.update(qScreen, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qScreen.screenId.eq(screen.getScreenId().intValue())).set(qScreen.screenName, screen.getScreenName())
							.set(qScreen.description, screen.getDescription()).set(qScreen.updatedBy, screen.getUpdatedBy())
							.set(qScreen.updatedDateTime, new Date(screen.getUpdatedDateTime().getTime())).execute();
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getScreenIdByScreenName(String name) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qScreen).where(qScreen.screenName.eq(name));
			Integer screenId = template.queryForObject(sqlQuery, qScreen.screenId);
			if (screenId != null) {
				return screenId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	@Override
	public int insertScreenGetKey(final Screen screen) throws DataAccessException {
		int screenId = 0;
		try {
			screenId = template.insertWithKey(qScreen, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qScreen.screenId, qScreen.screenName, qScreen.description, qScreen.appID, qScreen.createdBy, qScreen.createdDateTime,
									qScreen.updatedBy, qScreen.updatedDateTime)
							.values(screen.getScreenId(), screen.getScreenName(), screen.getDescription(), screen.getAppId(), screen.getCreatedBy(),
									screen.getCreatedDateTime(), screen.getUpdatedBy(), screen.getUpdatedDateTime()).executeWithKey(qScreen.screenId);
				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return screenId;
	}

}
