package org.s2n.ddt.dao.impl;

import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.RunResultDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.MappingProjection;


public class RunResultDaoImpl implements RunResultDao {
	
	private QueryDslJdbcTemplate template;
	private QRunResult qRunResult = QRunResult.RunResult;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template= new QueryDslJdbcTemplate(dataSource);
	}
	
	private class ProjectRunResult extends MappingProjection<RunResult> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		//rrId(Primary Key), userId(foreign/ref key), summaryReport(yes/no), detailReport(yes/no), dateStarted, dateEnded.
		public ProjectRunResult(QRunResult qRunResult) {
			super(RunResult.class,qRunResult.runResultId,qRunResult.userName,qRunResult.summaryReport
					,qRunResult.detailReport,qRunResult.dateStarted,qRunResult.dateEnded);
		}

		@Override
		protected RunResult map(Tuple tuple) {
			RunResult rResult = new RunResult();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
			rResult.setRunResultId(tuple.get(qRunResult.runResultId));
			rResult.setUserId(tuple.get(qRunResult.userName).toString());
			rResult.setSummaryReport(tuple.get(qRunResult.summaryReport));
			rResult.setDetailReport(tuple.get(qRunResult.detailReport));
			try {
				rResult.setStartDate(sdf.parse(tuple.get(qRunResult.dateStarted)));
				rResult.setEndDate(sdf.parse(tuple.get(qRunResult.dateEnded)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return rResult;
		}

	}
	
	@Override
	public int InsertRunResult(final RunResult rResult) throws DataAccessException {
		long rId = template.insert(qRunResult, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause sqlInsert) {
				return sqlInsert.columns(qRunResult.userName,qRunResult.summaryReport,qRunResult.detailReport,qRunResult.dateStarted,qRunResult.dateEnded)
						.values(rResult.getUserId(),rResult.getSummaryReport(),rResult.getDetailReport()
								,rResult.getStartDate(),rResult.getEndDate()).executeWithKey(qRunResult.runResultId);
			}
		});
		return (int)rId;
	}

	@Override
	public RunResult getRunResultById(int rId) throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qRunResult).where(qRunResult.runResultId.eq(rId));
		return template.queryForObject(query,new ProjectRunResult(qRunResult));
	}

	@Override
	public void updateRunResult(final RunResult rResult) throws DataAccessException {
		template.update(qRunResult, new SqlUpdateCallback() {
			
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				return update.where(qRunResult.runResultId.eq(rResult.getRunResultId()))
						.set(qRunResult.userName, rResult.getUserId())
						.set(qRunResult.summaryReport,rResult.getSummaryReport())
						.set(qRunResult.detailReport, rResult.getDetailReport())
						.set(qRunResult.dateStarted,rResult.getStartDate().toString())
						.set(qRunResult.dateEnded, rResult.getEndDate().toString())
						.execute();
			}
		});

	}

}
