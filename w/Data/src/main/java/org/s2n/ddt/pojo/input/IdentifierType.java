package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.Application;

/**
 * IdentifierType entity.
 */

public class IdentifierType implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal identifierTypeId;
	private Application application;
	private BigDecimal appId;
	private String indentifierTypeName;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<Objects> objectsList = new ArrayList<Objects>(0);

	/** default constructor */
	public IdentifierType() {
		super();
	}

	/** minimal constructor */
	public IdentifierType(BigDecimal identifiertypeid, Application application) {
		this.identifierTypeId = identifiertypeid;
		this.application = application;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "IdentifierType [identifierTypeId=" + identifierTypeId + ",  appId=" + appId + ", indentifierTypeName="
				+ indentifierTypeName + ", description=" + description + "]";
	}

	public BigDecimal getIdentifierTypeId() {
		return identifierTypeId;
	}

	public void setIdentifierTypeId(BigDecimal identifierTypeId) {
		this.identifierTypeId = identifierTypeId;
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

	public String getIndentifierTypeName() {
		return indentifierTypeName;
	}

	public void setIndentifierTypeName(String indentifierTypeName) {
		this.indentifierTypeName = indentifierTypeName;
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