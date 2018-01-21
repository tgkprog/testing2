package org.s2n.ddt.pojo.def;

import java.util.List;

import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.output.LaneResult;
import org.s2n.ddt.pojo.output.RunResult;

/**
 * For testing and to run DDT without app container and in one jvm
 * */
public interface LaneListener {
	
	List<LaneResult> process(Lane lane);
	void masterPlanStart(RunResult runr, MasterPlan mplan);
}
