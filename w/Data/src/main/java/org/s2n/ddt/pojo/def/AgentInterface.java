package org.s2n.ddt.pojo.def;

public interface AgentInterface extends DdtRemoteInterface {

	void setAgentConfig(AgentConfiguration agentConfiguration);

	AgentStatus getAgentStatus();
}
