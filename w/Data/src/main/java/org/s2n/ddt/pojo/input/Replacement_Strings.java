/**
 * 
 */
package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mohammedfirdos
 *
 */
public class Replacement_Strings implements Serializable {

	/**
	 * Replacement_Strings Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal ID;
	private BigDecimal AppID;
	private String Level;
	private String Foreign_ID;
	private String Name;
	private String Value;
	private String Encrypted;
	
	public Replacement_Strings() {
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

	/**
	 * @return the level
	 */
	public String getLevel() {
		return Level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		Level = level;
	}

	/**
	 * @return the foreign_ID
	 */
	public String getForeign_ID() {
		return Foreign_ID;
	}

	/**
	 * @param foreign_ID the foreign_ID to set
	 */
	public void setForeign_ID(String foreign_ID) {
		Foreign_ID = foreign_ID;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
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
	 * @return the encrypted
	 */
	public String getEncrypted() {
		return Encrypted;
	}

	/**
	 * @param encrypted the encrypted to set
	 */
	public void setEncrypted(String encrypted) {
		Encrypted = encrypted;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Replacement_Strings [ID=" + ID + ", AppID=" + AppID
				+ ", Level=" + Level + ", Foreign_ID=" + Foreign_ID + ", Name="
				+ Name + ", Value=" + Value + ", Encrypted=" + Encrypted + "]";
	}
}
