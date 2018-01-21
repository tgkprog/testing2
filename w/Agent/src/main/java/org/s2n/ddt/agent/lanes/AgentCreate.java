package org.s2n.ddt.agent.lanes;

	/**
	 * "Agent.create" in main.properties default is "task" <- no change to current, so even if you do not have it; things will work as now.
	 *	Possible values for "Agent.create" :
	 *	"clone" -> every clone - this is good for performance testing
	 *	"clone_repeat" -> before iterating thru all tasks and their repeats
	 *	"task_repeat" -> for one task, before its repeats
	 *	"task" -> for each task, for each repetition make new, this is as now and the default
	 * @author tushar kapila
	 *
	 */
public enum AgentCreate {
	LANE("lane"), CLONE("clone"), CLONE_REPEAT("clone_repeat"), TASK_REPEAT("task_repeat"), TASK("task");
	private String description;
	//"lane" -> not implemented
	private AgentCreate(String dd) {
		description = dd;
	}

	public String getDescription() {
		return description;
	}
	
	 @Override
	    public String toString() {
	        return this.description;
	    }

	    public static AgentCreate fromDescription(final String description) {
	        if (description != null) {
	            for (final AgentCreate crt : AgentCreate.values()) {
	                if (description.equalsIgnoreCase(crt.description)) {
	                    return crt;
	                }
	            }
	        }
	        return TASK;
	    }

}
