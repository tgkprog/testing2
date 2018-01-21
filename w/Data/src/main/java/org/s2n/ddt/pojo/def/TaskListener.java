package org.s2n.ddt.pojo.def;

import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;

public interface TaskListener {
	void completed(Lane lane, Task task, TaskResult result, RunResult rr);
}
