/**
 * 
 */
package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mohammedfirdos
 *
 */
public class SchedulerLaneDetails implements Serializable {

	/**
	 * SchedulerLaneDetails Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ScheduleID;
	private BigDecimal ScheduleLaneID;
	private String LaneType;
	private String LaneUserName;
	private String RunnerType;
	private BigDecimal Clones;
	private BigDecimal Iterations;
	private BigDecimal RampUpDelay;
	private BigDecimal Duration;
	
	public SchedulerLaneDetails() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the scheduleID
	 */
	public BigDecimal getScheduleID() {
		return ScheduleID;
	}

	/**
	 * @param scheduleID the scheduleID to set
	 */
	public void setScheduleID(BigDecimal scheduleID) {
		ScheduleID = scheduleID;
	}

	/**
	 * @return the scheduleLaneID
	 */
	public BigDecimal getScheduleLaneID() {
		return ScheduleLaneID;
	}

	/**
	 * @param scheduleLaneID the scheduleLaneID to set
	 */
	public void setScheduleLaneID(BigDecimal scheduleLaneID) {
		ScheduleLaneID = scheduleLaneID;
	}

	/**
	 * @return the laneType
	 */
	public String getLaneType() {
		return LaneType;
	}

	/**
	 * @param laneType the laneType to set
	 */
	public void setLaneType(String laneType) {
		LaneType = laneType;
	}

	/**
	 * @return the laneUserName
	 */
	public String getLaneUserName() {
		return LaneUserName;
	}

	/**
	 * @param laneUserName the laneUserName to set
	 */
	public void setLaneUserName(String laneUserName) {
		LaneUserName = laneUserName;
	}

	/**
	 * @return the runnerType
	 */
	public String getRunnerType() {
		return RunnerType;
	}

	/**
	 * @param runnerType the runnerType to set
	 */
	public void setRunnerType(String runnerType) {
		RunnerType = runnerType;
	}

	/**
	 * @return the clones
	 */
	public BigDecimal getClones() {
		return Clones;
	}

	/**
	 * @param clones the clones to set
	 */
	public void setClones(BigDecimal clones) {
		Clones = clones;
	}

	/**
	 * @return the iterations
	 */
	public BigDecimal getIterations() {
		return Iterations;
	}

	/**
	 * @param iterations the iterations to set
	 */
	public void setIterations(BigDecimal iterations) {
		Iterations = iterations;
	}

	/**
	 * @return the rampUpDelay
	 */
	public BigDecimal getRampUpDelay() {
		return RampUpDelay;
	}

	/**
	 * @param rampUpDelay the rampUpDelay to set
	 */
	public void setRampUpDelay(BigDecimal rampUpDelay) {
		RampUpDelay = rampUpDelay;
	}

	/**
	 * @return the duration
	 */
	public BigDecimal getDuration() {
		return Duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(BigDecimal duration) {
		Duration = duration;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SchedulerLaneDetails [ScheduleID=" + ScheduleID
				+ ", ScheduleLaneID=" + ScheduleLaneID + ", LaneType="
				+ LaneType + ", LaneUserName=" + LaneUserName + ", RunnerType="
				+ RunnerType + ", Clones=" + Clones + ", Iterations="
				+ Iterations + ", RampUpDelay=" + RampUpDelay + ", Duration="
				+ Duration + "]";
	}
}
