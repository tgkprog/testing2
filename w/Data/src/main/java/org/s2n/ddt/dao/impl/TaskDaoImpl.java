package org.s2n.ddt.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.query.QueryDslJdbcTemplate;
import org.springframework.data.jdbc.query.SqlInsertCallback;

import org.s2n.ddt.dao.TaskDao;
import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.generated.pojo.QTask;
import org.s2n.ddt.pojo.input.Task;
import com.mysema.query.Tuple;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.dml.SQLInsertClause;

import com.mysema.query.types.MappingProjection;

public class TaskDaoImpl implements TaskDao {
	private QueryDslJdbcTemplate template;
	private QTask qTask = QTask.Task;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.template = new QueryDslJdbcTemplate(dataSource);
	}

	// taskId (Primary Key), laneId(foreign/ref key), testPlanXlsPath, taskName,
	// dataSet, repeat, tagsToRun
	private class MappingTask extends MappingProjection<Task> {

		private static final long serialVersionUID = 1L;

		public MappingTask(QTask qTask) {
			super(Task.class, qTask.taskId, qTask.laneId, qTask.testPlanXlsPath, qTask.taskName, qTask.dataSet, qTask.repeatNo, qTask.tagsToRun,qTask.laneRepeat,qTask.laneClone);
		}
		
		@Override
		protected Task map(Tuple tuple) {
			Task task = new Task();
			task.setTaskId(new BigDecimal(tuple.get(qTask.taskId)));
			task.setLaneId(tuple.get(qTask.laneId));
			task.setTestPlanXlsPath(tuple.get(qTask.testPlanXlsPath));
			task.setTaskName(tuple.get(qTask.taskName));
			task.setDataSet(tuple.get(qTask.dataSet));
			task.setRepeats(tuple.get(qTask.repeatNo));
			task.setTagsToRun(tuple.get(qTask.tagsToRun));
			return task;
		}

	}

	@Override
	public Task getTaskByTaskId(int taskId) throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qTask).where(qTask.taskId.eq(taskId));
		return template.queryForObject(query, new MappingTask(qTask));
	}

	@Override
	public List<Task> getTasksByLaneId(int laneId) throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qTask).where(qTask.laneId.eq(laneId));
		return template.query(query, new MappingTask(qTask));
	}

	@Override
	public String getPlanXlsPath(int taskId) throws DataAccessException {
		SQLQuery query = template.newSqlQuery().from(qTask).where(qTask.taskId.eq(taskId));
		return template.queryForObject(query, qTask.testPlanXlsPath);
	}
	
	// taskId (Primary Key), laneId(foreign/ref key), testPlanXlsPath, taskName,
	// dataSet, repeat, tagsToRun
	
	@Override
	public int insertTask(final Task task,final int tRepeat, final int lRepeat,final int lClone) throws DataAccessException {
		long taskId = template.insert(qTask, new SqlInsertCallback() {
			@Override
			public long doInSqlInsertClause(SQLInsertClause sqlInsert) {
				return sqlInsert.columns(qTask.laneId,qTask.testPlanXlsPath, qTask.taskName, qTask.dataSet, 
						qTask.repeatNo, qTask.tagsToRun,qTask.laneRepeat,qTask.laneClone).values(task.getLaneId(),task.getTestPlanXlsPath(),
								task.getTaskName(),task.getDataSet(),tRepeat,task.getTagsToRun(),lRepeat,lClone).executeWithKey(qTask.taskId);
			}
		});
		return (int)taskId;
	}
}
