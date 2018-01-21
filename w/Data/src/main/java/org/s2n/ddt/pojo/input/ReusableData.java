package org.s2n.ddt.pojo.input;

import java.io.Serializable;

/**
 * ReusableData entity
 */
public class ReusableData implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String value;
	private String realValue;
	private boolean encrypted;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRealValue() {
		return realValue;
	}

	public void setRealValue(String realValue) {
		this.realValue = realValue;
	}

	public boolean isEncrypted() {
		return encrypted;
	}

	public void setEncrypted(boolean encrypted) {
		this.encrypted = encrypted;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "ReusableData [value=" + value + ", realValue=" + realValue + ", encrypted=" + encrypted + "]";
	}

}
