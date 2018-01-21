package com.apple.ist.grx.processor.response;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CreateOrderResponse", namespace="com.apple.ist.grx.processor.response")
@XmlRootElement(name="CreateOrderResponse")
public class CreateOrderConfirmation
{
  private CreateOrderOutput[] createOrderConfirmation;
  private String[] validationList;
  private String errorMessage;

  public String[] getValidationList()
  {
    return this.validationList;
  }

  public void setValidationList(String[] validationList)
  {
    this.validationList = ((String[])validationList.clone());
  }

  public CreateOrderOutput[] getCreateOrderConfirmation()
  {
    return this.createOrderConfirmation;
  }

  public void setCreateOrderConfirmation(CreateOrderOutput[] createOrderConfirmation)
  {
    this.createOrderConfirmation = ((CreateOrderOutput[])createOrderConfirmation.clone());
  }

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage)
  {
    this.errorMessage = errorMessage;
  }
}