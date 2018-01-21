package org.s2n.ddt.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.pojo.output.AgentDetails;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.http.NetSend;

/**
 * List of agents controlled by this Core
 * 
 * @author tusharkapila
 * 
 */
public class Agents {
	private static final Logger logger = Logger.getLogger(Agents.class);

	private static Map<String, AgentDetails> agents = new HashMap<String, AgentDetails>();

	// AgentDetails agents1;

	public static AgentDetails getAgent(String n) {
		return agents.get(n);
	}

	/** put by name */
	public static void putAgent(String n, AgentDetails a) {
		agents.put(n, a);
	}

	public static Map<String, AgentDetails> getAgents() {
		return agents;
	}

	public static void setAgents(Map<String, AgentDetails> agents) {
		Agents.agents = agents;
	}

	/** assigns the agent details from main.properties to agents HashMap */
	public static void init() {
		AgentDetails aDetails;
		int i = 1;
		logger.debug("Inside Agents Init");
		while (UtlConf.getProperty("agent" + i + "name") != null && UtlConf.getProperty("agent" + i + "name").length() != 0) {
			aDetails = new AgentDetails();
			final String nm = UtlConf.getProperty("agent" + i + "name");
			aDetails.setAgentName(nm);
			aDetails.setPort(Integer.parseInt(UtlConf.getProperty("agent" + i + "port")));
			aDetails.setIp(UtlConf.getProperty("agent" + i + "ip"));

			String sActive = UtlConf.getProperty("agent" + i + "active");
			aDetails.setStatus(LangUtils.isTrue(sActive, true));
			logger.warn( "agent " + nm + ", status " + sActive );
			agents.put(aDetails.getAgentName(), aDetails);
			logger.info("Agent" + i + "Details" + agents.get(aDetails.getAgentName()) + aDetails.getStatus());
			i++;
		}
	}
	
	public static void proc(java.io.Writer wrt, String path) {
		Map<String, AgentDetails> agents = org.s2n.ddt.core.Agents.getAgents();
		Set<String> names = agents.keySet();
		String communiProto = UtlConf.getProperty("http.protocol", "http");
		String url = null;
		HttpData hDat = new HttpData(url, 0, null);
		for(String aname : names){
			AgentDetails det = agents.get(aname);
			String domain = communiProto + "://" + det.getIp() + ":" + det.getPort();
			url = domain + path;//"/agent/a/quick.jsp"
			hDat.setUrl(url);
			try {
				Object rtn = NetSend.send(hDat, "", null);
				wrt.write("<br><b>");
				wrt.write(det.getAgentName());
				wrt.write("</b> ");
				wrt.write(url);
				wrt.write("<br><pre>");
				if(rtn != null){
					wrt.write(rtn.toString());
				}else{
					wrt.write("error");
				}
				wrt.write("</pre>");
			} catch (Exception e) {
				logger.info("Quick err " + e, e);
			}
		}
	}//m

}
