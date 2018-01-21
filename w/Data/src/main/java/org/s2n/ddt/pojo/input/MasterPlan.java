package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MasterPlan implements Serializable, Cloneable {
	
	/**
	 * 1. Change only if you add a property and do not want old serialized objects not to be loaded.
	 * 2. Generally do not remove a property once in prod, but if you do then need to test.
	 */
	private static final long serialVersionUID = 1L;

	private List<Lane> lanes = new ArrayList<Lane>(0);

	private String masterPlanName;
	
	private URL coreDomain;//proto + domain + port like "https://core1.s2n.com:8080/" to this agent will add 
	//private String encPassword;//right now ignore, needs to be part of agent details
	
	private String loadErrorMsgs;
	private boolean loadedWithNoErrors = false;
	private List<String> loadWarnings = new ArrayList<String>(0);

	
	public String getMasterPlanName() {
		return masterPlanName;
	}
	public void setMasterPlanName(String masterPlanName) {
		this.masterPlanName = masterPlanName;
	}
	public URL getCoreDomain() {
		return coreDomain;
	}
	public void setCoreDomain(URL coreDomain) {
		this.coreDomain = coreDomain;
	}
	public String getLoadErrorMsgs() {
		return loadErrorMsgs;
	}
	public void setLoadErrorMsgs(String loadErrors) {
		this.loadErrorMsgs = loadErrors;
	}
	public boolean isLoadedWithNoErrors() {
		return loadedWithNoErrors;
	}
	public void setLoadedWithNoErrors(boolean loadedWithNoError) {
		this.loadedWithNoErrors = loadedWithNoError;
	}
	public List<Lane> getLanes() {
		return lanes;
	}
	public void setLanes(List<Lane> lanes) {
		this.lanes = lanes;
	}
	
	public List<String> getLoadWarnings() {
		return loadWarnings;
	}
	public void setLoadWarnings(List<String> loadWarnings) {
		this.loadWarnings = loadWarnings;
	}
	
	public void  addLoadWarning(String s) {
		loadWarnings.add(s);
	}

}
