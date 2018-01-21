package org.s2n.ddt.excelreader;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReplacementString implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private String value;
	private String level;
	private String foreignId;
	private String appName;
	private int encrypted;
	private String name;

	public ReplacementString() {
		super();
	}

	public ReplacementString(BigDecimal id, String value, String level, String foreignId, String appName, int encrypted, String name) {
		super();
		this.id = id;
		this.value = value;
		this.level = level;
		this.foreignId = foreignId;
		this.appName = appName;
		this.encrypted = encrypted;
		this.name = name;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getForeignId() {
		return foreignId;
	}

	public void setForeignId(String foreignId) {
		this.foreignId = foreignId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(int encrypted) {
		this.encrypted = encrypted;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ReplacementString [id=" + id + ", value=" + value + ", level=" + level + ", foreignId=" + foreignId + ", appName=" + appName
				+ ", encrypted=" + encrypted + ", name=" + name + "]";
	}

}
