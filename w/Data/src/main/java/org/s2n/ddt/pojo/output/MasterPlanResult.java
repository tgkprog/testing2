package org.s2n.ddt.pojo.output;

import java.io.Serializable;

public class MasterPlanResult implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String planName;
	
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	
	

}
