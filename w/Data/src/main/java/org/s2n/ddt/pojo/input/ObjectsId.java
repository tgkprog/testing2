package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * ObjectsId entity
 */
public class ObjectsId implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal objectId;
	private String objectName;
	private String description;
	private BigDecimal objectGroupId;
	private BigDecimal objectTypeId;
	private BigDecimal identifierTypeId;
	private String indentifier;
	private BigDecimal appId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;

	/** default constructor */
	public ObjectsId() {
		super();
	}

	/**
	 * Minimal Constructor
	 * 
	 * @param objectid
	 * @param objectgroupid
	 * @param objecttypeid
	 * @param identifiertypeid
	 */
	public ObjectsId(BigDecimal objectid, BigDecimal objectgroupid, BigDecimal objecttypeid, BigDecimal identifiertypeid) {
		this.objectId = objectid;
		this.objectGroupId = objectgroupid;
		this.objectTypeId = objecttypeid;
		this.identifierTypeId = identifiertypeid;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ObjectsId [objectid=" + objectId + ", objname=" + objectName + ", description=" + description + "]";
	}

	public BigDecimal getObjectId() {
		return objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getObjectGroupId() {
		return objectGroupId;
	}

	public void setObjectGroupId(BigDecimal objectGroupId) {
		this.objectGroupId = objectGroupId;
	}

	public BigDecimal getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(BigDecimal objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public BigDecimal getIdentifierTypeId() {
		return identifierTypeId;
	}

	public void setIdentifierTypeId(BigDecimal identifierTypeId) {
		this.identifierTypeId = identifierTypeId;
	}

	public String getIndentifier() {
		return indentifier;
	}

	public void setIndentifier(String indentifier) {
		this.indentifier = indentifier;
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDateTime == null) ? 0 : createdDateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((identifierTypeId == null) ? 0 : identifierTypeId.hashCode());
		result = prime * result + ((indentifier == null) ? 0 : indentifier.hashCode());
		result = prime * result + ((objectGroupId == null) ? 0 : objectGroupId.hashCode());
		result = prime * result + ((objectId == null) ? 0 : objectId.hashCode());
		result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
		result = prime * result + ((objectTypeId == null) ? 0 : objectTypeId.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDateTime == null) ? 0 : updatedDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		ObjectsId other = (ObjectsId) obj;
		if (appId == null) {
			if (other.appId != null){
				return false;
			}
		} else if (!appId.equals(other.appId)){
			return false;
		}
		if (createdBy == null) {
			if (other.createdBy != null){
				return false;
			}
		} else if (!createdBy.equals(other.createdBy)){
			return false;
		}
		if (createdDateTime == null) {
			if (other.createdDateTime != null){
				return false;
			}
		} else if (!createdDateTime.equals(other.createdDateTime)){
			return false;
		}
		if (description == null) {
			if (other.description != null){
				return false;
			}
		} else if (!description.equals(other.description)){
			return false;
		}
		if (identifierTypeId == null) {
			if (other.identifierTypeId != null){
				return false;
			}
		} else if (!identifierTypeId.equals(other.identifierTypeId)){
			return false;
		}
		if (indentifier == null) {
			if (other.indentifier != null){
				return false;
			}
		} else if (!indentifier.equals(other.indentifier)){
			return false;
		}
		if (objectGroupId == null) {
			if (other.objectGroupId != null){
				return false;
			}
		} else if (!objectGroupId.equals(other.objectGroupId)){
			return false;
		}
		if (objectId == null) {
			if (other.objectId != null){
				return false;
			}
		} else if (!objectId.equals(other.objectId)){
			return false;
		}
		if (objectName == null) {
			if (other.objectName != null){
				return false;
			}
		} else if (!objectName.equals(other.objectName)){
			return false;
		}
		if (objectTypeId == null) {
			if (other.objectTypeId != null){
				return false;
			}
		} else if (!objectTypeId.equals(other.objectTypeId)){
			return false;
		}
		if (updatedBy == null) {
			if (other.updatedBy != null){
				return false;
			}
		} else if (!updatedBy.equals(other.updatedBy)){
			return false;
		}
		if (updatedDateTime == null) {
			if (other.updatedDateTime != null){
				return false;
			}
		} else if (!updatedDateTime.equals(other.updatedDateTime)){
			return false;
		}
		return true;
	}

}