package org.s2n.ddt.dao;

import org.s2n.ddt.exception.DataAccessException;
import org.s2n.ddt.pojo.output.TaskResult;

public interface TaskResultDao {

	int insertTaskResult(TaskResult taskResult,int tRepeat,int lRepeat,int lClone,int taskId) throws DataAccessException;
	
	TaskResult getTaskResultByTaskId(int taskId) throws DataAccessException;
	
	TaskResult getTaskResultByTaskResultId(int taskResultId) throws DataAccessException;
	
	void udpateTaskResult(TaskResult taskResult) throws DataAccessException; 
}
