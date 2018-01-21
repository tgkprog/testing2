package com.apple.ist.grx.processor.request;

public class DeviceRequest
{
  private String deviceId;
  private String secondarySerialNumber;
  private String hardwareDateOfPurchase;

  public String getDeviceId()
  {
    return this.deviceId;
  }

  public void setDeviceId(String deviceId)
  {
    this.deviceId = deviceId;
  }

  public String getSecondarySerialNumber()
  {
    return this.secondarySerialNumber;
  }

  public void setSecondarySerialNumber(String secondarySerialNumber)
  {
    this.secondarySerialNumber = secondarySerialNumber;
  }

  public String getHardwareDateOfPurchase()
  {
    return this.hardwareDateOfPurchase;
  }

  public void setHardwareDateOfPurchase(String hardwareDateOfPurchase)
  {
    this.hardwareDateOfPurchase = hardwareDateOfPurchase;
  }
}
