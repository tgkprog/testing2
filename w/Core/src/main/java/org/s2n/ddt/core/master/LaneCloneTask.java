package org.s2n.ddt.core.master;

import java.util.List;

import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.output.TestPlan;

//TODO not being used as yet.
public class LaneCloneTask implements Runnable {
	private Lane lane;
	private int cloneId;
	
	public LaneCloneTask(Lane lane, int clnid) {
		this.lane = lane;
		this.cloneId = clnid;
	}
	
	public void run() {
		//ReadPlan reader = new ReadPlan();
		List<Task> tasks = lane.getTasks();
		for(int laneRpt = 0; laneRpt < lane.getIterations(); laneRpt++) {
			for(int taskId = 0; taskId < tasks.size(); taskId++) {
				Task task = tasks.get(taskId);
				//TestPlan plan = reader.readPlanObj(task.getTestPlanXlsPath(), null);
				for(int taskRpt = 0 ; taskRpt < task.getRepeats(); taskRpt++) {
					if(MasterExecute.isRunModeSingleVm()) {
						
					}
				}
			}
		}
	}

}
