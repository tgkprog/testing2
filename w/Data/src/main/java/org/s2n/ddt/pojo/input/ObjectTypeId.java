package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ObjectTypeId entity.
 */
public class ObjectTypeId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal objectTypeId;
	private BigDecimal appId;
	private BigDecimal defaultActionId;
	private String objectTypeName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public ObjectTypeId() {
		super();
	}

	/** minimal constructor */
	public ObjectTypeId(BigDecimal objecttypeid, BigDecimal appId, BigDecimal defaultActionId) {
		this.objectTypeId = objecttypeid;
		this.appId = appId;
		this.defaultActionId = defaultActionId;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during appId development, and possibly when
	 * appId is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ObjectTypeId [objecttypeid=" + objectTypeId + ", appId=" + appId + ", defaultActionId=" + defaultActionId + ", objecttypename="
				+ objectTypeName + ", description=" + description + "]";
	}

	public BigDecimal getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(BigDecimal objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public BigDecimal getDefaultActionId() {
		return defaultActionId;
	}

	public void setDefaultActionId(BigDecimal defaultActionId) {
		this.defaultActionId = defaultActionId;
	}

	public String getObjectTypeName() {
		return objectTypeName;
	}

	public void setObjectTypeName(String objectTypeName) {
		this.objectTypeName = objectTypeName;
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

}
