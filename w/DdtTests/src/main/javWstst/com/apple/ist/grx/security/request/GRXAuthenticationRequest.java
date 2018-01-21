package com.apple.ist.grx.security.request;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name="GRXAuthenticationRequest")
@XmlRootElement(name="GRXAuthenticationRequest")
public class GRXAuthenticationRequest
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String userId;
  private String passWord;
  private String soldTo;
  private String shipTo;
  private String langCode;

  public String getUserId()
  {
    return this.userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getPassWord() {
    return this.passWord;
  }

  public void setPassWord(String passWord) {
    this.passWord = passWord;
  }

  public String getSoldTo() {
    return this.soldTo;
  }

  public void setSoldTo(String soldTo) {
    this.soldTo = soldTo;
  }

  public String getShipTo() {
    return this.shipTo;
  }

  public void setShipTo(String shipTo) {
    this.shipTo = shipTo;
  }

  public String getLangCode() {
    return this.langCode;
  }

  public void setLangCode(String langCode) {
    this.langCode = langCode;
  }
}
