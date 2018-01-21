package org.s2n.tst.orderIdly.processor.request;

import java.io.Serializable;

public class CustomerRequest
  implements Serializable
{
  private static final long serialVersionUID = 8481552308790535228L;
  private String customerFirstName;
  private String customerLastName;
  private String customerEmailId;
  private String primaryPhoneNumber;
  private String companyName;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String zipCode;
  private String countryCode;
  private String stateCode;

  public String getCustomerFirstName()
  {
    return this.customerFirstName;
  }

  public void setCustomerFirstName(String customerFirstName)
  {
    this.customerFirstName = customerFirstName;
  }

  public String getCustomerLastName()
  {
    return this.customerLastName;
  }

  public void setCustomerLastName(String customerLastName)
  {
    this.customerLastName = customerLastName;
  }

  public String getCustomerEmailId()
  {
    return this.customerEmailId;
  }

  public void setCustomerEmailId(String customerEmailId)
  {
    this.customerEmailId = customerEmailId;
  }

  public String getPrimaryPhoneNumber()
  {
    return this.primaryPhoneNumber;
  }

  public void setPrimaryPhoneNumber(String primaryPhoneNumber)
  {
    this.primaryPhoneNumber = primaryPhoneNumber;
  }

  public String getCompanyName()
  {
    return this.companyName;
  }

  public void setCompanyName(String companyName)
  {
    this.companyName = companyName;
  }

  public String getAddressLine1()
  {
    return this.addressLine1;
  }

  public void setAddressLine1(String addressLine1)
  {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2()
  {
    return this.addressLine2;
  }

  public void setAddressLine2(String addressLine2)
  {
    this.addressLine2 = addressLine2;
  }

  public String getCity()
  {
    return this.city;
  }

  public void setCity(String city)
  {
    this.city = city;
  }

  public String getZipCode()
  {
    return this.zipCode;
  }

  public void setZipCode(String zipCode)
  {
    this.zipCode = zipCode;
  }

  public String getCountryCode()
  {
    return this.countryCode;
  }

  public void setCountryCode(String countryCode)
  {
    this.countryCode = countryCode;
  }

  public String getStateCode()
  {
    return this.stateCode;
  }

  public void setStateCode(String stateCode)
  {
    this.stateCode = stateCode;
  }
}
