package org.s2n.tst.orderIdly.processor.response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CreateOrderOutput", namespace="org.s2n.tst.orderIdly.processor.response")
@XmlRootElement(name="CreateOrderOutput")
public class CreateOrderOutput
  implements Serializable
{
  private static final long serialVersionUID = 245344714939356624L;
  private String agreementId;
  private String coverageDurationStatement;
  private String termConditionURL;
  private String warmingMessage;
  private String partNumber;
  private String partType;
  private String availability;
  private String purchaseMode;
  private String errorCode;
  private Boolean eligible;
  private DeviceEligibilityResponse[] deviceEligibilityResponse;
  private String appleCareSalesDate;
  private String marketFieldLabelDescription;
  private String monthlyPayment;
  private String pocType;
  private String purchaseOrderNumber;
  private String[] validationList;

  public Boolean getEligible()
  {
    return this.eligible;
  }

  public void setEligible(Boolean eligible) {
    this.eligible = eligible;
  }

  public String getAgreementId()
  {
    return this.agreementId;
  }

  public void setAgreementId(String agreementId)
  {
    this.agreementId = agreementId;
  }

  public String getCoverageDurationStatement()
  {
    return this.coverageDurationStatement;
  }

  public void setCoverageDurationStatement(String coverageDurationStatement)
  {
    this.coverageDurationStatement = coverageDurationStatement;
  }

  public String getTermConditionURL()
  {
    return this.termConditionURL;
  }

  public void setTermConditionURL(String termConditionURL)
  {
    this.termConditionURL = termConditionURL;
  }

  public String getWarmingMessage()
  {
    return this.warmingMessage;
  }

  public void setWarmingMessage(String warmingMessage)
  {
    this.warmingMessage = warmingMessage;
  }

  public String getPartNumber()
  {
    return this.partNumber;
  }

  public void setPartNumber(String partNumber)
  {
    this.partNumber = partNumber;
  }

  public String getPartType()
  {
    return this.partType;
  }

  public void setPartType(String partType)
  {
    this.partType = partType;
  }

  public String getAvailability()
  {
    return this.availability;
  }

  public void setAvailability(String availability)
  {
    this.availability = availability;
  }

  public String getPurchaseMode()
  {
    return this.purchaseMode;
  }

  public void setPurchaseMode(String purchaseMode)
  {
    this.purchaseMode = purchaseMode;
  }

  public String getErrorCode()
  {
    return this.errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public DeviceEligibilityResponse[] getDeviceEligibilityResponse()
  {
    return this.deviceEligibilityResponse;
  }

  public void setDeviceEligibilityResponse(DeviceEligibilityResponse[] deviceEligibilityResponse)
  {
    this.deviceEligibilityResponse = ((DeviceEligibilityResponse[])deviceEligibilityResponse.clone());
  }

  public String getAppleCareSalesDate()
  {
    return this.appleCareSalesDate;
  }

  public void setAppleCareSalesDate(String appleCareSalesDate)
  {
    this.appleCareSalesDate = appleCareSalesDate;
  }

  public String getMarketFieldLabelDescription()
  {
    return this.marketFieldLabelDescription;
  }

  public void setMarketFieldLabelDescription(String marketFieldLabelDescription)
  {
    this.marketFieldLabelDescription = marketFieldLabelDescription;
  }

  public String getMonthlyPayment()
  {
    return this.monthlyPayment;
  }

  public void setMonthlyPayment(String monthlyPayment)
  {
    this.monthlyPayment = monthlyPayment;
  }

  public String getPocType()
  {
    return this.pocType;
  }

  public void setPocType(String pocType)
  {
    this.pocType = pocType;
  }

  public String getPurchaseOrderNumber()
  {
    return this.purchaseOrderNumber;
  }

  public void setPurchaseOrderNumber(String purchaseOrderNumber)
  {
    this.purchaseOrderNumber = purchaseOrderNumber;
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
