package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.s2n.ddt.pojo.Actions;
import org.s2n.ddt.pojo.Application;

/**
 * ObjectType entity.
 */

public class ObjectType implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private ObjectTypeId objectTypeId;
	private Application application;
	private Actions actions;
	private List<Objects> objectsList = new ArrayList<Objects>(0);

	/** default constructor */
	public ObjectType() {
		super();
	}

	/** full constructor */
	public ObjectType(ObjectTypeId objecttypeid, Application application, Actions actions, List<Objects> objects) {
		this.objectTypeId = objecttypeid;
		this.application = application;
		this.actions = actions;
		this.objectsList = objects;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ObjectTypeId [objecttypeid=" + objectTypeId + "]";
	}

	public ObjectTypeId getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(ObjectTypeId objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	public List<Objects> getObjectsList() {
		return objectsList;
	}

	public void setObjectsList(List<Objects> objectsList) {
		this.objectsList = objectsList;
	}

}