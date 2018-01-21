package org.s2n.ddt.pojo.input;

import java.io.Serializable;

import org.s2n.ddt.pojo.Application;

/**
 * Objects entity.
 */
public class Objects implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private ObjectsId objectsId;
	private ObjectGroup objectGroup;
	private ObjectType objectType;
	private IdentifierType identifierType;
	private Application application;

	/** default constructor */
	public Objects() {
		super();
	}

	/**
	 * Full Constructor for this value object
	 * 
	 * @param objectsId
	 * @param objectGroup
	 * @param objectType
	 * @param identifierType
	 * @param application
	 */
	public Objects(ObjectsId objectsId, ObjectGroup objectGroup, ObjectType objectType, IdentifierType identifierType, Application application) {
		super();
		this.objectsId = objectsId;
		this.objectGroup = objectGroup;
		this.objectType = objectType;
		this.identifierType = identifierType;
		this.application = application;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "Objects [objectsId=" + objectsId + "]";
	}

	public ObjectsId getObjectsId() {
		return objectsId;
	}

	public void setObjectsId(ObjectsId objectsId) {
		this.objectsId = objectsId;
	}

	public ObjectGroup getObjectGroup() {
		return objectGroup;
	}

	public void setObjectGroup(ObjectGroup objectGroup) {
		this.objectGroup = objectGroup;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}

	public IdentifierType getIdentifierType() {
		return identifierType;
	}

	public void setIdentifierType(IdentifierType identifierType) {
		this.identifierType = identifierType;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

}