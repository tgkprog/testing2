package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.s2n.ddt.pojo.input.IdentifierType;
import org.s2n.ddt.pojo.input.ObjectGroup;
import org.s2n.ddt.pojo.input.ObjectType;
import org.s2n.ddt.pojo.input.Objects;
import org.s2n.ddt.pojo.input.ParamGroup;
import org.s2n.ddt.pojo.input.TestParamData;
import org.s2n.ddt.pojo.output.TestScenario;

/**
 * Application entity.
 */

public class Application implements Serializable {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private BigDecimal appId;
	private String appName;
	private String autVersion;
	private String description;
	private String createdBy;
	private Date createdDateTime;
	private String updatedBy;
	private Date updatedDateTime;
	private List<Screen> screens = new ArrayList<Screen>(0);
	private List<IdentifierType> identifierTypes = new ArrayList<IdentifierType>(0);
	private List<ConditionGroup> conditionGroups = new ArrayList<ConditionGroup>(0);
	private List<TestScenario> testScenarios = new ArrayList<TestScenario>(0);
	private List<ParamGroup> paramGroups = new ArrayList<ParamGroup>(0);
	private List<ObjectGroup> objectGroups = new ArrayList<ObjectGroup>(0);
	private List<Functional> functionals = new ArrayList<Functional>(0);
	private List<ObjectType> objectTypes = new ArrayList<ObjectType>(0);
	private List<Objects> objects = new ArrayList<Objects>(0);
	private List<TestSuite> testSuites = new ArrayList<TestSuite>(0);
	private List<TestParamData> testParamDatas = new ArrayList<TestParamData>(0);

	/** default constructor */
	public Application() {
		super();
	}

	/** minimal constructor */
	public Application(BigDecimal appid) {
		this.appId = appid;
	}

	/**
	 * toString will return String object representing the state of this
	 * valueObject. This is useful during application development, and possibly
	 * when application is writing object states in textlog.
	 */
	@Override
	public String toString() {
		return "Application [appId=" + appId + ", appName=" + appName + ", description=" + description + "]";
	}

	public BigDecimal getAppId() {
		return appId;
	}

	public void setAppId(BigDecimal appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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

	public List<Screen> getScreens() {
		return screens;
	}

	public void setScreens(List<Screen> screens) {
		this.screens = screens;
	}

	public List<IdentifierType> getIdentifierTypes() {
		return identifierTypes;
	}

	public void setIdentifierTypes(List<IdentifierType> identifierTypes) {
		this.identifierTypes = identifierTypes;
	}

	public List<ConditionGroup> getConditionGroups() {
		return conditionGroups;
	}

	public void setConditionGroups(List<ConditionGroup> conditionGroups) {
		this.conditionGroups = conditionGroups;
	}

	public List<TestScenario> getTestScenarios() {
		return testScenarios;
	}

	public void setTestScenarios(List<TestScenario> testScenarios) {
		this.testScenarios = testScenarios;
	}

	public List<ParamGroup> getParamGroups() {
		return paramGroups;
	}

	public void setParamGroups(List<ParamGroup> paramGroups) {
		this.paramGroups = paramGroups;
	}

	public List<ObjectGroup> getObjectGroups() {
		return objectGroups;
	}

	public void setObjectGroups(List<ObjectGroup> objectGroups) {
		this.objectGroups = objectGroups;
	}

	public List<Functional> getFunctionals() {
		return functionals;
	}

	public void setFunctionals(List<Functional> functionals) {
		this.functionals = functionals;
	}

	public List<ObjectType> getObjectTypes() {
		return objectTypes;
	}

	public void setObjectTypes(List<ObjectType> objectTypes) {
		this.objectTypes = objectTypes;
	}

	public List<Objects> getObjects() {
		return objects;
	}

	public void setObjects(List<Objects> objects) {
		this.objects = objects;
	}

	public List<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	public List<TestParamData> getTestParamDatas() {
		return testParamDatas;
	}

	public void setTestParamDatas(List<TestParamData> testParamDatas) {
		this.testParamDatas = testParamDatas;
	}

	public String getAutVersion() {
		return autVersion;
	}

	public void setAutVersion(String autVersion) {
		this.autVersion = autVersion;
	}

}