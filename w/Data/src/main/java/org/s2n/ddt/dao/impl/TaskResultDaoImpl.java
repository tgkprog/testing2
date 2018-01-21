package org.s2n.ddt.dao.impl;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;
import org.springframework.data.jdbc.query.SqlUpdateCallback;

import org.s2n.ddt.dao.TaskResultDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTaskResult;
import org.s2n.ddt.pojo.output.TaskResult;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;
import com.mysema.query.types.MappingProjection;

public class TaskResultDaoImpl implements TaskResultDao {
	
	private QTaskResult qtr = QTaskResult.TaskResult;
	private QueryDslJdbcTemplate template;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	
	private class MappingTaskResult extends MappingProjection<TaskResult>{
		
		/**trId(Primary Key), testPlanResultId(foreign/ref key), runResultId(foreign/ref key), reportFilePath
		 * Default serial version id
		 */
		private static final long serialVersionUID = 1L;

		public MappingTaskResult(QTaskResult qTr) {
			super(TaskResult.class, qTr.taskResultId,qTr.taskId,qTr.testPlanResultId,qTr.runResultId,qTr.reportFilePath);
		}

		@Override
		protected TaskResult map(Tuple tuple) {
			TaskResult tr = new TaskResult();
			tr.setTaskResultId(tuple.get(qtr.taskResultId));
			tr.setRunResultId(tuple.get(qtr.runResultId));
			tr.setReportFilePath(new File(tuple.get(qtr.reportFilePath)));
			return tr;
		}
		
	}

	/*trId,tId,testPlanId,runResultId,reportFilePath,taskRepNo,laneRepNo,cloneNo,startTime,endTime*/
	
	@Override
	public int insertTaskResult(final TaskResult taskResult,final int tRepeat, final int lRepeat,final int lClone,final int taskId) throws DataAccessException {
		long trId=0;
		try{
			trId = template.insert(qtr,new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause query) {
				return query.columns(qtr.runResultId,qtr.taskId,qtr.taskRepeationNo,qtr.laneRepeationNo,qtr.cloneNo,qtr.startTime)
						.values(taskResult.getRunResultId(),taskId,tRepeat,lRepeat,lClone,taskResult.getStartTime())
						.executeWithKey(qtr.taskResultId);
			}
		});
		} catch (Exception ex) {
			throw new DataAccessException(ex.getMessage());
		}
		return (int)trId;
	}


	@Override
	public TaskResult getTaskResultByTaskId(int taskId)
			throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qtr).where(qtr.taskId.eq(taskId));
		return template.queryForObject(query, new MappingTaskResult(qtr));
	}


	@Override
	public TaskResult getTaskResultByTaskResultId(int taskResultId)
			throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qtr).where(qtr.taskId.eq(taskResultId));
		return template.queryForObject(query, new MappingTaskResult(qtr));
	}


	@Override
	public void udpateTaskResult(final TaskResult taskResult)
			throws DataAccessException {
		template.update(qtr, new SqlUpdateCallback() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			@Override
			public long doInSqlUpdateClause(SQLUpdateClause update) {
				return update.where(qtr.taskResultId.eq(taskResult.getTaskResultId()))
						.set(qtr.startTime,sdf.format(taskResult.getStartTime()))
						.set(qtr.endTime,sdf.format(taskResult.getEndTime()))
						.execute();						
			}
		});
		
	}
}
