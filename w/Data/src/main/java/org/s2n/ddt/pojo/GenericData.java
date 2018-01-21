/**
 * 
 */
package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mohammedfirdos
 *
 */
public class GenericData implements Serializable {

	/**
	 * GenericData Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ID;
	private String KeyName;
	private String Value;
	private BigDecimal AppID;
	
	public GenericData() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the iD
	 */
	public BigDecimal getID() {
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(BigDecimal iD) {
		ID = iD;
	}

	/**
	 * @return the key
	 */
	public String getKeyName() {
		return KeyName;
	}

	/**
	 * @param key the key to set
	 */
	public void setKeyName(String keyName) {
		KeyName = keyName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return Value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		Value = value;
	}

	/**
	 * @return the appID
	 */
	public BigDecimal getAppID() {
		return AppID;
	}

	/**
	 * @param appID the appID to set
	 */
	public void setAppID(BigDecimal appID) {
		AppID = appID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GenericData [ID=" + ID + ", KeyName=" + KeyName + ", Value="
				+ Value + ", AppID=" + AppID + "]";
	}
}
