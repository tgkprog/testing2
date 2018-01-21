package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.ObjectGroup;

/**
 * Screen entity.
 */

public class Screen implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal screenId;
	private Application application;
	private BigDecimal appId;
	private String screenName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<ObjectGroup> objectGroups = new ArrayList<ObjectGroup>(0);

	/** default constructor */
	public Screen() {
		super();
	}

	/** minimal constructor */
	public Screen(BigDecimal screenid, Application application, String screenname, String createdby, Date createddatetime, String updatedby,
			Date updateddatetime) {
		this.screenId = screenid;
		this.application = application;
		this.screenName = screenname;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}

	public BigDecimal getScreenId() {
		return screenId;
	}

	public void setScreenId(BigDecimal screenId) {
		this.screenId = screenId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<ObjectGroup> getObjectGroups() {
		return objectGroups;
	}

	public void setObjectGroups(List<ObjectGroup> objectGroups) {
		this.objectGroups = objectGroups;
	}

	@Override
	public String toString() {
		return "Screen [screenId=" + screenId + ", application=" + application + ", appId=" + appId + ", screenName=" + screenName + ", description="
				+ description + "]";
	}

	
}