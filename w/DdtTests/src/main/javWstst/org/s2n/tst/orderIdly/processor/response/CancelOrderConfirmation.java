package org.s2n.tst.orderIdly.processor.response;

import org.s2n.tst.orderIdly.enums.OrderStatus;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CancelOrderResponse")
@XmlRootElement(name="CancelOrderResponse")
public class CancelOrderConfirmation
  implements Serializable
{
  private static final long serialVersionUID = -8100633701879746360L;
  private String deviceId;
  private long agreementId;
  private boolean isEligible;
  private boolean cancelOrderStatus;
  private String serialNumber;
  private String secondarySerialNumber;
  private String poNumber;
  private String partNumber;
  private String partType;
  private OrderStatus orderStatus;
  private String[] validationList;
  private Date applecareSalesDate;
  private char monthlyPayment;
  private String errorMessage;

  public String getErrorMessage()
  {
    return this.errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }

  public long getAgreementId() {
    return this.agreementId;
  }

  public void setAgreementId(long agreementId) {
    this.agreementId = agreementId;
  }

  public boolean isEligible()
  {
    return this.isEligible;
  }

  public void setEligible(boolean isEligible)
  {
    this.isEligible = isEligible;
  }

  public boolean isCancelOrderStatus()
  {
    return this.cancelOrderStatus;
  }

  public void setCancelOrderStatus(boolean cancelOrderStatus)
  {
    this.cancelOrderStatus = cancelOrderStatus;
  }

  public String getSerialNumber() {
    return this.serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public String getSecondarySerialNumber() {
    return this.secondarySerialNumber;
  }

  public void setSecondarySerialNumber(String secondarySerialNumber) {
    this.secondarySerialNumber = secondarySerialNumber;
  }

  public String getPoNumber()
  {
    return this.poNumber;
  }

  public void setPoNumber(String poNumber)
  {
    this.poNumber = poNumber;
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

  public String[] getValidationList()
  {
    return this.validationList;
  }

  public void setValidationList(String[] validationList) {
    String[] array = validationList;
    this.validationList = array;
  }

  public Date getApplecareSalesDate() {
    return this.applecareSalesDate;
  }

  public void setApplecareSalesDate(Date applecareSalesDate) {
    this.applecareSalesDate = applecareSalesDate;
  }

  public char getMonthlyPayment() {
    return this.monthlyPayment;
  }

  public void setMonthlyPayment(char monthlyPayment) {
    this.monthlyPayment = monthlyPayment;
  }

  public OrderStatus getOrderStatus()
  {
    return this.orderStatus;
  }

  public void setOrderStatus(OrderStatus orderStatus) {
    this.orderStatus = orderStatus;
  }
}
