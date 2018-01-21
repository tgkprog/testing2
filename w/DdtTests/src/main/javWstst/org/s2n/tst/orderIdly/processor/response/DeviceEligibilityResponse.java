package org.s2n.tst.orderIdly.processor.response;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="DeviceEligibilityResponse")
@XmlRootElement(name="DeviceEligibilityResponse")
public class DeviceEligibilityResponse
{
  private String secondaryDisplay;
  private String partDescription;
  private String coverageDuration;
  private String productStatement;
  private String deviceDateOfPurchase;
  private String deviceId;
  private String[] validationList;

  public String getSecondaryDisplay()
  {
    return this.secondaryDisplay;
  }

  public void setSecondaryDisplay(String secondaryDisplay)
  {
    this.secondaryDisplay = secondaryDisplay;
  }

  public String getPartDescription()
  {
    return this.partDescription;
  }

  public void setPartDescription(String partDescription)
  {
    this.partDescription = partDescription;
  }

  public String getCoverageDuration()
  {
    return this.coverageDuration;
  }

  public void setCoverageDuration(String coverageDuration)
  {
    this.coverageDuration = coverageDuration;
  }

  public String getProductStatement()
  {
    return this.productStatement;
  }

  public void setProductStatement(String productStatement)
  {
    this.productStatement = productStatement;
  }

  public String getDeviceDateOfPurchase()
  {
    return this.deviceDateOfPurchase;
  }

  public void setDeviceDateOfPurchase(String deviceDateOfPurchase)
  {
    this.deviceDateOfPurchase = deviceDateOfPurchase;
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }

  public String[] getValidationList()
  {
    return this.validationList;
  }

  public void setValidationList(String[] validationList)
  {
    this.validationList = ((String[])validationList.clone());
  }
}
