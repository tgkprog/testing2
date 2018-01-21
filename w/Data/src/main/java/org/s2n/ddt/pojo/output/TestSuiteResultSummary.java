package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/** TestSuiteResultSummary */
public class TestSuiteResultSummary implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String testSuiteId;
	private String testSuiteName;
	private Date date;
	private BigDecimal duration;

	public String getTestSuiteId() {
		return testSuiteId;
	}

	public void setTestSuiteId(String testSuiteId) {
		this.testSuiteId = testSuiteId;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getDuration() {
		return duration;
	}

	public void setDuration(BigDecimal duration) {
		this.duration = duration;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestSuiteResultSummary [testSuiteId=" + testSuiteId + ", testSuiteName=" + testSuiteName + ", date=" + date + ", duration=" + duration
				+ "]";
	}

}
