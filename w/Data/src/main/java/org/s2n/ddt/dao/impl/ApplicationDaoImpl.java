package org.s2n.ddt.dao.impl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlInsertWithKeyCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.ApplicationDao;
import org.s2n.ddt.dao.impl.input.IdentifierTypeDaoImpl;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QApplication;
import org.s2n.ddt.pojo.Application;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.Expression;
import com.mysema.query.types.MappingProjection;

public class ApplicationDaoImpl implements ApplicationDao {
	/**
	 * Logger for this class
	 */
	private final Logger logger = Logger.getLogger(ApplicationDaoImpl.class);

	private QueryDslJdbcTemplate template;
	private QApplication qApplication = QApplication.application;
	private FunctionalDaoImpl funDaoImpl;
	private IdentifierTypeDaoImpl identifierDaoImpl;

	public void setFunDaoImpl(FunctionalDaoImpl funDaoImpl) {
		this.funDaoImpl = funDaoImpl;
	}

	public void setIdentifierDaoImpl(IdentifierTypeDaoImpl identifierDaoImpl) {
		this.identifierDaoImpl = identifierDaoImpl;
	}

	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	public long insertApplicationDetails(final Application application) throws DataAccessException {
		long result = 0L;
		logger.info("started inserting data for Application : " + application.getAppId());
		try {
			result = template.insert(qApplication, new SqlInsertCallback() {

				public long doInSqlInsertClause(SQLInsertClause sqlInsertClause) {
					return sqlInsertClause
							.columns(qApplication.appID, qApplication.appName, qApplication.description, qApplication.createdBy,
									qApplication.createdDateTime, qApplication.updatedBy, qApplication.updatedDateTime)
							.values(application.getAppId(), application.getAppName(), application.getDescription(), application.getCreatedBy(),
									application.getCreatedDateTime(), application.getUpdatedBy(), application.getUpdatedDateTime()).execute();
				}
			});
			logger.info("number of rows inserted : " + result);
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return result;
	}

	public int insertApplicationGetKey(final Application application) throws DataAccessException {
		int appId = 0;
		try {
			appId = template.insertWithKey(qApplication, new SqlInsertWithKeyCallback<Integer>() {
				@Override
				public Integer doInSqlInsertWithKeyClause(SQLInsertClause insert) throws SQLException {
					return insert
							.columns(qApplication.appID, qApplication.appName, qApplication.description, qApplication.createdBy,
									qApplication.createdDateTime, qApplication.updatedBy, qApplication.updatedDateTime)
							.values(application.getAppId(), application.getAppName(), application.getDescription(), application.getCreatedBy(),
									application.getCreatedDateTime(), application.getUpdatedBy(), application.getUpdatedDateTime())
							.executeWithKey(qApplication.appID);

				}
			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return appId;
	}

	public Application getApplicationDetailsById(int appId) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qApplication).where(qApplication.appID.eq(appId));
			logger.info("generated query : " + sqlQuery);
			return template.queryForObject(sqlQuery, new MappingApplicationProjection(qApplication.appID, qApplication.appName, qApplication.description,
					qApplication.createdBy, qApplication.createdDateTime, qApplication.updatedBy, qApplication.updatedDateTime));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	private class MappingApplicationProjection extends MappingProjection<Application> {
		/**
		 * Logger for this class
		 */
		private final Logger logger = Logger.getLogger(MappingApplicationProjection.class);

		/**
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingApplicationProjection(Expression<?>... args) {
			super(Application.class, args);
		}

		@Override
		protected Application map(Tuple tuple) {
			Application application = new Application();
			application.setAppId(new BigDecimal(tuple.get(qApplication.appID)));
			application.setAppName(tuple.get(qApplication.appName));
			application.setDescription(tuple.get(qApplication.description));
			application.setCreatedBy(tuple.get(qApplication.createdBy));
			application.setCreatedDateTime(tuple.get(qApplication.createdDateTime));
			application.setUpdatedBy(tuple.get(qApplication.updatedBy));
			application.setUpdatedDateTime(tuple.get(qApplication.updatedDateTime));
			if (logger.isDebugEnabled()) {
				logger.debug("returning data object : " + application);
			}
			return application;
		}

	}

	public Application getApplicationDetailsTillFunctional(int appId) throws DataAccessException {
		try {
			Application application = getApplicationDetailsById(appId);
			application.setFunctionals(funDaoImpl.getFunctionalsByAppId(appId));
			logger.info("returning data object : " + application);
			return application;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public Application getApplicationDetailsTillIdentifierType(int appId) throws DataAccessException {
		try {
			Application application = getApplicationDetailsById(appId);
			application.setFunctionals(funDaoImpl.getFunctionalsByAppId(appId));
			application.setIdentifierTypes(identifierDaoImpl.getIdentifierTypesByAppId(appId));
			logger.info("returning data object : " + application);
			return application;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public Application getApplicationDetailsByAppName(String AppName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qApplication).where(qApplication.appName.eq(AppName));
			return template.queryForObject(sqlQuery, new MappingApplicationProjection(qApplication.appName));
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	public int getAppIdByAppName(String appName) throws DataAccessException {
		try {
			SQLQuery sqlQuery = template.newSqlQuery().from(qApplication).where(qApplication.appName.eq(appName));
			Integer appId = template.queryForObject(sqlQuery, qApplication.appID);
			if (appId != null) {
				return appId;
			}
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
		return 0;
	}

	public long updateApplication(final Application application) throws DataAccessException {
		try {
			return template.update(qApplication, new SqlUpdateCallback() {

				public long doInSqlUpdateClause(SQLUpdateClause sqlUpdateClause) {
					return sqlUpdateClause.where(qApplication.appID.eq(application.getAppId().intValue()))
							.set(qApplication.appName, application.getAppName()).set(qApplication.description, application.getDescription())
							.set(qApplication.updatedBy, application.getUpdatedBy())
							.set(qApplication.updatedDateTime, new Date(application.getUpdatedDateTime().getTime())).execute();
				}

			});
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}

	@Override
	public int getMaxAppId() throws DataAccessException {
		try {
			int maxAppId = template.getJdbcOperations().queryForInt("select max(AppID) from APPLICATION");
			logger.info("returning max id : " + maxAppId);
			return maxAppId;
		} catch (Exception e) {
			logger.error("error occured due to : " + e, e);
			throw new DataAccessException(e.getMessage());
		}
	}
}
