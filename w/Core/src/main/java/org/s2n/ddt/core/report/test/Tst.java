package org.s2n.ddt.core.report.test;

import java.util.ArrayList;
import java.util.List;

import org.s2n.ddt.pojo.output.AgentRunResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.test.Reports;

public class Tst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Reports rs = new Reports();
		RunResult result = new RunResult();
		List<AgentRunResult>  agentRunResults = new ArrayList<AgentRunResult>();
	//	agentRunResults.add(agentRunResult);
		result.setAgentRunResultList(agentRunResults);
	//	logger.info("agentRunResults >>>"+agentRunResults.toString());
		try {
		
			//rs.planEnd(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
