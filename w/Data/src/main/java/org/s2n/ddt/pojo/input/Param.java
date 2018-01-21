package org.s2n.ddt.pojo.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Param entity.
 */

public class Param implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal paramId;
	private ParamGroup paramGroup;
	private BigDecimal paramGroupId;
	private String paramName;
	private String description;
	private Long orderBy;
	private Objects objects;
	private BigDecimal objectId;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<TestParamData> testParamDatas = new ArrayList<TestParamData>(0);
	private List<TestCondData> testCondDatas = new ArrayList<TestCondData>(0);

	/** default constructor */
	public Param() {
		super();
	}

	/** minimal constructor */
	public Param(BigDecimal paramid, ParamGroup paramGroup, BigDecimal objectid) {
		this.paramId = paramid;
		this.paramGroup = paramGroup;
		this.objectId = objectid;
	}

	public BigDecimal getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public ParamGroup getParamGroup() {
		return paramGroup;
	}

	public void setParamGroup(ParamGroup paramGroup) {
		this.paramGroup = paramGroup;
	}

	public BigDecimal getParamGroupId() {
		return paramGroupId;
	}

	public void setParamGroupId(BigDecimal paramGroupId) {
		this.paramGroupId = paramGroupId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public Objects getObjects() {
		return objects;
	}

	public void setObjects(Objects objects) {
		this.objects = objects;
	}

	public BigDecimal getObjectId() {
		return objectId;
	}

	public void setObjectId(BigDecimal objectId) {
		this.objectId = objectId;
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

	public List<TestParamData> getTestParamDatas() {
		return testParamDatas;
	}

	public void setTestParamDatas(List<TestParamData> testParamDatas) {
		this.testParamDatas = testParamDatas;
	}

	public List<TestCondData> getTestCondDatas() {
		return testCondDatas;
	}

	public void setTestCondDatas(List<TestCondData> testCondDatas) {
		this.testCondDatas = testCondDatas;
	}

}