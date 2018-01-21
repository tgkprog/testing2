package org.s2n.ddt.dao;

import java.util.List;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.input.Task;

public interface TaskDao {
	//taskId (Primary Key), laneId(foreign/ref key), testPlanXlsPath, taskName, dataSet, repeat, tagsToRun
	Task getTaskByTaskId(int taskId) throws DataAccessException;
	
	List<Task> getTasksByLaneId(int laneId) throws DataAccessException;
	
	String getPlanXlsPath(int taskId) throws DataAccessException;
	
	int insertTask(Task task,int tRepeat, int lRepeat,int lClone) throws DataAccessException;
	
}
