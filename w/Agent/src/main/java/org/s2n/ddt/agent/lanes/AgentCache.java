package org.s2n.ddt.agent.lanes;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import org.s2n.ddt.excelreader.ReadPlan;
import org.s2n.ddt.pojo.output.TestPlan;

public class AgentCache {
	private static final Logger logger = Logger.getLogger(LaneProcess.class);

	private static final AgentCache app = new AgentCache();

	private Map<String, ReadPlan> rplans = new HashMap<String, ReadPlan>();

	public static AgentCache getInstance() {
		return app;
	}

	public void clear() {
		rplans.clear();
		logger.info("cleared the read plan cache");
	}

	public ReadPlan getReader(String xlsPath, String appId) {
		ReadPlan rp = rplans.get(xlsPath);

		if (rp == null) {
			rp = new ReadPlan();
			try {
				rp.readPlanObj(xlsPath, null);
				rplans.put(xlsPath, rp);
			} catch (Exception e) {
				logger.warn("run read plan err :" + e, e);
			}
		}
		return rp;
	}

}
