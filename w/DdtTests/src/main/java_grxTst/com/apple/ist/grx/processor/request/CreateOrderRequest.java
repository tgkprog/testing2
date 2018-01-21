package com.apple.ist.grx.processor.request;

import java.io.Serializable;

public class CreateOrderRequest
  implements Serializable
{
  private static final long serialVersionUID = 8481552308790535228L;
  private String purchaseOrderNumber;
  private char monthlyPayment;
  private String marketField;
  private String pocLanguage;
  private String pocDeliveryPreference;
  private String overridePocFlag;
  private String appleCareSalesDate;
  private DeviceRequest[] deviceRequest;
  private CustomerRequest customerRequest;
  private char emailFlag;
  private char smsFlag;

  public String getPurchaseOrderNumber()
  {
    return this.purchaseOrderNumber;
  }

  public void setPurchaseOrderNumber(String purchaseOrderNumber)
  {
    this.purchaseOrderNumber = purchaseOrderNumber;
  }

  public char getMonthlyPayment()
  {
    return this.monthlyPayment;
  }

  public void setMonthlyPayment(char monthlyPayment)
  {
    this.monthlyPayment = monthlyPayment;
  }

  public String getMarketField()
  {
    return this.marketField;
  }

  public void setMarketField(String marketField)
  {
    this.marketField = marketField;
  }

  public String getPocLanguage()
  {
    return this.pocLanguage;
  }

  public void setPocLanguage(String pocLanguage)
  {
    this.pocLanguage = pocLanguage;
  }

  public String getPocDeliveryPreference()
  {
    return this.pocDeliveryPreference;
  }

  public void setPocDeliveryPreference(String pocDeliveryPreference)
  {
    this.pocDeliveryPreference = pocDeliveryPreference;
  }

  public String getOverridePocFlag()
  {
    return this.overridePocFlag;
  }

  public void setOverridePocFlag(String overridePocFlag)
  {
    this.overridePocFlag = overridePocFlag;
  }

  public DeviceRequest[] getDeviceRequest()
  {
    return this.deviceRequest;
  }

  public void setDeviceRequest(DeviceRequest[] deviceRequest)
  {
    DeviceRequest[] requests = deviceRequest;
    this.deviceRequest = requests;
  }

  public CustomerRequest getCustomerRequest()
  {
    return this.customerRequest;
  }

  public void setCustomerRequest(CustomerRequest customerRequest)
  {
    this.customerRequest = customerRequest;
  }

  public String getAppleCareSalesDate()
  {
    return this.appleCareSalesDate;
  }

  public void setAppleCareSalesDate(String appleCareSalesDate)
  {
    this.appleCareSalesDate = appleCareSalesDate;
  }

  public char getEmailFlag()
  {
    return this.emailFlag;
  }

  public void setEmailFlag(char emailFlag)
  {
    this.emailFlag = emailFlag;
  }

  public char getSmsFlag()
  {
    return this.smsFlag;
  }

  public void setSmsFlag(char smsFlag)
  {
    this.smsFlag = smsFlag;
  }
}