package org.s2n.ddt.run;

import org.s2n.ddt.pojo.output.AgentDetails;

public class AgentSetup {
	public void reinit() {
		// in real get from db
		System.out.println("DDT test inside DDT agentSetup ");
		org.s2n.ddt.core.Agents.getAgents().clear();

		AgentDetails a1 = new AgentDetails();
		a1.setAgentName("tushar");
		//a1.setIp("EXIMR-MB-071.local");// From system information/network/ Ethernet - advanced/ WINS
		a1.setIp("localhost");// From system information/network/ Ethernet - advanced/ WINS
		a1.setPort(8180);

		org.s2n.ddt.core.Agents.putAgent(a1.getAgentName(), a1);
	}
}
