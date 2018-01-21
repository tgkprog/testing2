package com.apple.ist.grx.processor.request;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="CancelOrderRequest")
@XmlRootElement(name="CancelOrderRequest")
public class CancelOrderRequest  implements Serializable{
  private static final long serialVersionUID = 1382307620003301255L;
  private String deviceId;
  private Date cancellationDate;
  private String purchaseOrderNumber;
  private String shipToCode;

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public Date getCancellationDate() {
    return this.cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public String getPurchaseOrderNumber() {
    return this.purchaseOrderNumber;
  }

  public void setPurchaseOrderNumber(String purchaseOrderNumber) {
    this.purchaseOrderNumber = purchaseOrderNumber;
  }

  public String getShipToCode() {
    return this.shipToCode;
  }

  public void setShipToCode(String shipToCode) {
    this.shipToCode = shipToCode;
  }

  public static long getSerialversionuid() {
    return 1382307620003301255L;
  }
}