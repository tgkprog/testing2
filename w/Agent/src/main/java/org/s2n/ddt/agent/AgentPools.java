package org.s2n.ddt.agent;

import java.io.Serializable;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.http.NetSend;
import org.s2n.ddt.util.http.PostBackAsync;
import org.s2n.ddt.util.threads.PoolOptions;
import org.s2n.ddt.util.threads.DdtPools;

public class AgentPools {
	private static final Logger logger = Logger.getLogger(AgentPools.class);

	private static String agentPostBackPoolName = "AGENT_POST_BACK";
	static {

		initPostBackPool(null);
	}

	public static void endPlanDetailed(RunResult rResult, TaskResult tsakResult) {
		String ss = UtlConf.getProperty("agent.postBack.async", "1");
		boolean agentAsync = LangUtils.isTrue(ss, false);
		String log1 = UtlConf.getProperty("agent.postBack.logLvl", "warn");
		PostBackAsync postBack = new PostBackAsync().setLogLvl(log1).setLogMsg(
				"plan end cln id:" + tsakResult.getLaneCloneId() + ", clon rpt :" + tsakResult.getRepeatationNumberOfCLone() + ", task rpt :"
						+ tsakResult.getReputationNameOfTask());

		HttpData hDat = Agent2.getCoreUrl();
		String logOk = UtlConf.getProperty("agent.postBack.logLvlOk", "info");

		postBack.setObjType("planEnd").setLogLvlOk(logOk).setHttpParams(hDat).add(tsakResult).add(rResult).start(agentPostBackPoolName, agentAsync);

		if (hDat.getUrl() != null) {
			Object rtn = NetSend.sendObjects("planEnd", hDat, (Serializable) tsakResult, (Serializable) rResult);
			logger.log(Level.INFO, "Rtn " + rtn);
		} else {
			logger.info("Core Url null, not posting back");
		}
	}

	public static void initPostBackPool(String ss) {
		if (ss != null) {
			agentPostBackPoolName = ss;
		}
		String smax = UtlConf.getProperty("agent.postBack.lanePoolSize", "2");
		int max = LangUtils.getInt(smax, 2, "agent post back lane Pool Size");
		logger.warn("pool :" + agentPostBackPoolName + ", threads :" + max);
		PoolOptions options = new PoolOptions();
		options.setMaxThreads(max);
		options.setCoreThreads(max);
		DdtPools.initPool(agentPostBackPoolName, options);
	}
}
