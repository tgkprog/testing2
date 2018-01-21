package org.s2n.ddt.pojo.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * TestReport entity.
 */
public class TestReport implements Serializable, Cloneable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal testReportId;
	private BigDecimal testRunId;
	private String reportDetails;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public TestReport() {
		super();
	}

	/** minimal constructor */
	public TestReport(BigDecimal testreportid, BigDecimal testRunId) {
		this.testReportId = testreportid;
		this.testRunId = testRunId;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "TestReport [testReportId=" + testReportId + ", testRunId=" + testRunId + ", reportDetails=" + reportDetails + "]";
	}

	public BigDecimal getTestReportId() {
		return testReportId;
	}

	public void setTestReportId(BigDecimal testReportId) {
		this.testReportId = testReportId;
	}

	public BigDecimal getTestRunId() {
		return testRunId;
	}

	public void setTestRunId(BigDecimal testRunId) {
		this.testRunId = testRunId;
	}

	public String getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

}