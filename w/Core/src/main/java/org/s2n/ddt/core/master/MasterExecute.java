package org.s2n.ddt.core.master;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import org.s2n.ddt.bean.UtlConf;
import org.s2n.ddt.common.PConsts;
import org.s2n.ddt.core.Agents;
import org.s2n.ddt.pojo.def.LaneListener;
import org.s2n.ddt.pojo.input.Lane;
import org.s2n.ddt.pojo.input.MasterPlan;
import org.s2n.ddt.pojo.input.Task;
import org.s2n.ddt.pojo.output.AgentDetails;
import org.s2n.ddt.pojo.output.LaneResult;
import org.s2n.ddt.pojo.output.RunResult;
import org.s2n.ddt.pojo.output.TaskResult;
import org.s2n.ddt.report.detail.DetailedReportHelperTask;
import org.s2n.ddt.util.LangUtils;
import org.s2n.ddt.util.http.HttpData;
import org.s2n.ddt.util.http.NetSend;

public class MasterExecute {
	private static final Logger logger = Logger.getLogger(MasterExecute.class);

	private MasterPlan mplan;
	private RunResult rResult;
	private static boolean runModeSingleVm = false;
	private static LaneListener laneListener;

	static {
		// reinitPool();
	}

	public static boolean isRunModeSingleVm() {
		return runModeSingleVm;
	}

	public static void setRunModeSingleVm(boolean runModeSingleVm) {
		MasterExecute.runModeSingleVm = runModeSingleVm;
	}

	public MasterPlan readMasterPlan(String path, String appid, String planName, BigInteger plnId) {
		File fMasterPlan = new File(path);
		try {
			mplan = org.s2n.ddt.excelreader.ReadObjectsData.loadMasterPlan(fMasterPlan);
		} catch (Exception e) {
			mplan = null;
			logger.warn("planDo a " + e, e);
		}
		return mplan;
	}

	public void planDo(String path, String appid, String planName, BigInteger plnId, RunResult runr) {
		// app project level TODO
		String communiProto = UtlConf.getProperty("http.protocol", "http");
		try {

			List<Lane> lanes = mplan.getLanes();
			if (isRunModeSingleVm() && laneListener != null) {
				// org.s2n.ddt.agent.lanes.AgentCache.clear();
				laneListener.masterPlanStart(runr, null);
			} else {
				List<String> agentsCalled = new ArrayList();
				//master plan msg starting 
				for (int laneId = 0; laneId < lanes.size(); laneId++) {
					Lane lane = lanes.get(laneId);
					if (agentsCalled.contains(lane.getAgentName())) {
						continue;
					}
					AgentDetails det = Agents.getAgent(lane.getAgentName());
					final String nm = lane.getAgentName();
					if(!agentsCalled.contains(nm)){
						continue;
					}
					agentsCalled.add(nm);
					if (det == null) {
						logger.warn("planDo master plan start: Agent not configured (skipping) :" + lane.getAgentName());
						continue;
					}
					String domain = communiProto + "://" + det.getIp() + ":" + det.getPort();
					String url = domain + "/agent/a/masterPlanStart.jsp";
					HttpData hDat = new HttpData(url, 0, null);
					Object rtn = NetSend.sendObjects("lane", hDat, (Serializable) lane, (Serializable) runr);
					logger.log(Level.INFO, "Start master plan msg " + lane.getLaneName() + ",  " + lane.getIterations() + "-" + lane.getClones() + ".");
					logger.log(Level.TRACE, "Rtn " + rtn);
						
				}
			}
			//job start msg
			for (int laneId = 0; laneId < lanes.size(); laneId++) {
				Lane lane = lanes.get(laneId);
				if (isRunModeSingleVm() && laneListener != null) {
					List<LaneResult> results = laneListener.process(lane);
				} else {
					AgentDetails det = Agents.getAgent(lane.getAgentName());
					if (det == null) {
						logger.warn("planDo : Agent not configured (skipping) :" + lane.getAgentName());
						continue;
					}
					String domain = communiProto + "://" + det.getIp() + ":" + det.getPort();
					String url = domain + "/agent/a/laneProcess.jsp";
					HttpData hDat = new HttpData(url, 0, null);
					Object rtn = NetSend.sendObj((Serializable) lane, hDat, "lane");
					logger.log(Level.INFO, "Start lane " + lane.getLaneName() + ",  " + lane.getIterations() + "-" + lane.getClones() + ".");
					logger.log(Level.TRACE, "Rtn " + rtn);
				}

			}
			// Agents.getAgent(mpla);
		} catch (Exception e) {
			logger.warn("planDo a " + e, e);
		}
	}

	public Map<String, Object> planDo(String path, String appid, String planName, BigInteger plnId, RunResult runr, java.io.Writer wrt,
			boolean writelinks) {
		// DdtPools.getPool("coreMaster");
		Map<String, Object> rtnList = new HashMap<String, Object>();
		File fMasterPlan = new File(path);
		String communiProto = UtlConf.getProperty("http.protocol", "http");
		try {
			List<Lane> lanes = mplan.getLanes();
			if (isRunModeSingleVm() && laneListener != null) {
				// List<LaneResult> results = laneListener.process(lane);
			} else {
				List<String> agentsCalled = new ArrayList();
				for (int laneId = 0; laneId < lanes.size(); laneId++) {
					Lane lane = lanes.get(laneId);
					if (agentsCalled.contains(lane.getAgentName())) {
						continue;
					}
					AgentDetails det = Agents.getAgent(lane.getAgentName());
					agentsCalled.add(lane.getAgentName());
					if (det == null) {
						logger.warn("planDo master plan start: Agent not configured (skipping) :" + lane.getAgentName());
						continue;
					}
					String honourAgentStatus = UtlConf.getProperty("honourAgentStatus", "1");
					boolean hnrStat = LangUtils.isTrue(honourAgentStatus, true);
					if (!det.getStatus() && hnrStat) {
						logger.warn("Agent not active and honour status is true");
						continue;
					}
					String domain = communiProto + "://" + det.getIp() + ":" + det.getPort();
					String url = domain + "/agent/a/masterPlanStart.jsp";
					HttpData hDat = new HttpData(url, 0, null);
					Object rtn = NetSend.sendObjects("lane", hDat, (Serializable) lane, (Serializable) runr);
					logger.log(Level.INFO, "Start mp " + lane.getLaneName() + ",  " + lane.getIterations() + "-" + lane.getClones() + ".");
					logger.log(Level.TRACE, "Rtn " + rtn);
				}
			}
			for (int laneId = 0; laneId < lanes.size(); laneId++) {
				Lane lane = lanes.get(laneId);
				if (isRunModeSingleVm() && laneListener != null) {
					List<LaneResult> results = laneListener.process(lane);
				} else {
					AgentDetails det = Agents.getAgent(lane.getAgentName());
					if (det == null) {
						logger.warn("planDo : Agent not configured (skipping) :" + lane.getAgentName());
						continue;
					}

					String domain = communiProto + "://" + det.getIp() + ":" + det.getPort();
					String url = domain + "/agent/planStart.jsp";
					HttpData hDat = new HttpData(url, 0, null);
					Object rtn = NetSend.sendObjects("lane", hDat, (Serializable) lane, (Serializable) runr);
					rtnList.put(laneId + "", rtn);
					logger.log(Level.INFO, "Start lane :" + laneId + ", "+ lane.getLaneName() + ",  " + lane.getIterations() + "-" + lane.getClones() + ".");
					logger.log(Level.TRACE, "Rtn :" + rtn);
					if (writelinks) {
						wrt.write("\n<br>Agent reply lane " + (laneId + 1) + " " + lane.getLaneName() + " <a href=\"http://" + det.getIp() + ":" + det.getPort() + "/agent/\">" + det.getAgentName() + "</a> ");
						wrt.write(":" + rtn);
						boolean otherError = true;
						if (runr.getSummaryReport()) {
							wrt.write("\n<h3>Summary Report</h3>\n");
							File sumReport = new File(runr.getReportFilePath(), "/" + "Report.html");
							// out.println("<br><h3>" + rr.getReportFilePath() + "/" + a.getAgentName() + "Report.html" + "</h3>");
							String s = sumReport.getAbsolutePath();
							int i = s.indexOf("/reports/r");
							if (i > 0) {
								s = s.substring(i);
								String n = s.replace(".html", "");
								n = s.replace("/reports/r", "");
								n = n.replace("/", " ");
								n = n.replace("_", " ");
								s = ".." + s;
								wrt.write("<br>" + "Summary Report:" + "<a href=\"	" + s + "\" >" + n + "</a>");
							}
						}
						if (runr.getDetailReport()) {
							wrt.write("\n<h3>Detailed Reports</h3>\n");
						}
						if (rtn instanceof String) {
							String mp = (String) rtn;
							boolean bAccepted = mp.indexOf(PConsts.getAgentAcceptStr()) > -1;

							otherError = false;
							if (bAccepted) {
								if (writelinks) {
									linkWrite(mplan, runr, wrt, lane, det);
								}
							} else {
								wrt.write("Lane :" + lane.getLaneNumberInPlan() + " " + lane.getLaneName() + " not accepted by " + lane.getAgentName());
							}

						}
						if (otherError) {
							wrt.write("Lane :" + lane.getLaneNumberInPlan() + " " + lane.getLaneName()
									+ " might have been accetpted/reply unknown check at agent :" + lane.getAgentName());
						}
					}
				}
			}
			// Agents.getAgent(mpla);
		} catch (Exception e) {
			logger.warn("planDo a " + e, e);
		}
		return rtnList;
	}

	private void linkWrite(MasterPlan mplan, RunResult rr, Writer out, Lane lane, AgentDetails a) {
		if (!rr.getSummaryReport()) {
			return;
		}
		// can reuse this TaskResult as just using to make file name
		TaskResult tr = new TaskResult();
		List<Task> tasks = lane.getTasks();
		tr.setLaneName(lane.getLaneName());
		tr.setLaneNo(lane.getLaneNumberInPlan());
		for (int clone = 0; clone <= lane.getClones(); clone++) {
			tr.setLaneCloneId(clone);
			for (int laneRpt = 0; laneRpt <= lane.getIterations(); laneRpt++) {
				tr.setLaneRepeatId(laneRpt);
				for (int taskId = 0; taskId < tasks.size(); taskId++) {
					Task task = tasks.get(taskId);
					tr.setTaskName(task.getTaskName());
					tr.setTestSuiteFilePath(task.getTestPlanXlsPath());
					tr.setTaskId(task.getTaskId());
					// at least do the task once + any repeats
					for (int taskRpt = 0; taskRpt <= task.getRepeats(); taskRpt++) {
						tr.setTaskRepeatId(taskRpt);
						// Generating the Detailed report file link
						File nDir = DetailedReportHelperTask.getTaskReportDir(rr.getMasterReportName(), rr.getStartDate(),
								tr.getLaneName() + tr.getLaneNo(), tr.getLaneCloneId(), tr.getLaneRepeatId(), tr.getTaskName(), (taskId + 1),
								tr.getTaskRepeatId());
						tr.setTaskDetailReportDir(nDir);
						nDir = DetailedReportHelperTask.getTaskReportDetailFile(tr, rr);
						String st = nDir.getAbsolutePath();
						int j = st.indexOf("/reports/r");
						if (j > 0) {
							st = st.substring(j);
							String n = st.replace(".html", "");
							n = st.replace("/reports/r", "");
							n = n.replace("/", " ");
							n = n.replace("_", " ");
							st = ".." + st;
							try {
								out.write("\n</br>Detailed Report :<a href=\"" + st + "\">Lane " + tr.getLaneName() + " " + tr.getLaneNo() + "  clone "
										+ tr.getLaneCloneId() + ", repetition " + tr.getLaneRepeatId() + ", task " + (taskId + 1) + " "
										+ task.getTaskName() + ", repetition " + tr.getTaskRepeatId() + "" + " </a>");
							} catch (IOException e) {
								logger.warn("make link " + e, e);
							}
						}
					}
				}
			}
		}

	}

	public static final org.s2n.ddt.pojo.def.LaneListener getLaneListener() {
		return laneListener;
	}

	public static final void setLaneListener(org.s2n.ddt.pojo.def.LaneListener laneListener) {
		MasterExecute.laneListener = laneListener;
	}

	public MasterPlan getMasterPlan() {
		return mplan;
	}

	public void setMasterPlan(MasterPlan mplan) {
		this.mplan = mplan;
	}

	public RunResult getrResult() {
		return rResult;
	}

	public void setrResult(RunResult rResult) {
		this.rResult = rResult;
	}
}
