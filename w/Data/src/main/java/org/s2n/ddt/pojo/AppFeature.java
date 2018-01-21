/**
 * 
 */
package org.s2n.ddt.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author mohammedfirdos
 *
 */
public class AppFeature implements Serializable {

	/**
	 * AppFeature Entity
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal FeatureID;
	private String FeatureName;
	private String Description;
	private BigDecimal FunctionalID;
	private String CreatedBy;
	private Date CreatedDateTime;
	private String UpdatedBy;
	private Date UpdatedDateTime;
	private BigDecimal ScreenID;
	
	public AppFeature() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the featureID
	 */
	public BigDecimal getFeatureID() {
		return FeatureID;
	}

	/**
	 * @param featureID the featureID to set
	 */
	public void setFeatureID(BigDecimal featureID) {
		FeatureID = featureID;
	}

	/**
	 * @return the featureName
	 */
	public String getFeatureName() {
		return FeatureName;
	}

	/**
	 * @param featureName the featureName to set
	 */
	public void setFeatureName(String featureName) {
		FeatureName = featureName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * @return the functionalID
	 */
	public BigDecimal getFunctionalID() {
		return FunctionalID;
	}

	/**
	 * @param functionalID the functionalID to set
	 */
	public void setFunctionalID(BigDecimal functionalID) {
		FunctionalID = functionalID;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return CreatedBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}

	/**
	 * @return the createdDateTime
	 */
	public Date getCreatedDateTime() {
		return CreatedDateTime;
	}

	/**
	 * @param createdDateTime the createdDateTime to set
	 */
	public void setCreatedDateTime(Date createdDateTime) {
		CreatedDateTime = createdDateTime;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return UpdatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		UpdatedBy = updatedBy;
	}

	/**
	 * @return the updatedDateTime
	 */
	public Date getUpdatedDateTime() {
		return UpdatedDateTime;
	}

	/**
	 * @param updatedDateTime the updatedDateTime to set
	 */
	public void setUpdatedDateTime(Date updatedDateTime) {
		UpdatedDateTime = updatedDateTime;
	}

	/**
	 * @return the screenID
	 */
	public BigDecimal getScreenID() {
		return ScreenID;
	}

	/**
	 * @param screenID the screenID to set
	 */
	public void setScreenID(BigDecimal screenID) {
		ScreenID = screenID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AppFeature [FeatureID=" + FeatureID + ", FeatureName="
				+ FeatureName + ", Description=" + Description
				+ ", FunctionalID=" + FunctionalID + ", ScreenID=" + ScreenID
				+ "]";
	}
	
}
