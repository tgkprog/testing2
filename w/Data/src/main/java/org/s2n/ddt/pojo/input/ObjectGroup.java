package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Application;
import org.s2n.ddt.pojo.Screen;

/**
 * ObjectGroup entity.
 */

public class ObjectGroup implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	private BigDecimal objectGroupId;
	private Screen screen;
	private BigDecimal screenId;
	private Application application;
	private BigDecimal appId;
	private String objectGroupName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<Objects> objectsList = new ArrayList<Objects>(0);

	// Constructors

	/** default constructor */
	public ObjectGroup() {
		super();
	}

	/** minimal constructor */
	public ObjectGroup(BigDecimal objectgroupid, Screen screen, Application application, String objectgroupname, String createdby, Date createddatetime,
			String updatedby, Date updateddatetime) {
		this.objectGroupId = objectgroupid;
		this.screen = screen;
		this.application = application;
		this.objectGroupName = objectgroupname;
		this.createdBy = createdby;
		this.createdDateTime = createddatetime;
		this.updatedBy = updatedby;
		this.updatedDateTime = updateddatetime;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ObjectGroup [objectGroupId=" + objectGroupId + ",  screenId=" + screenId + ",  appId=" + appId + ", objectGroupName=" + objectGroupName
				+ ", description=" + description + "]";
	}

	public BigDecimal getObjectGroupId() {
		return objectGroupId;
	}

	public void setObjectGroupId(BigDecimal objectGroupId) {
		this.objectGroupId = objectGroupId;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
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

	public String getObjectGroupName() {
		return objectGroupName;
	}

	public void setObjectGroupName(String objectGroupName) {
		this.objectGroupName = objectGroupName;
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

	public List<Objects> getObjectsList() {
		return objectsList;
	}

	public void setObjectsList(List<Objects> objectsList) {
		this.objectsList = objectsList;
	}

}